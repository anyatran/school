
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <string.h>
#include <signal.h>
#include <fcntl.h>

#define MAXLINE 200  /* This is how we declare constants in C */
#define MAXARGS 20
#define STDOUT_FILENO 1
#define STDIN_FILENO 0

pid_t childpid;
/* In C, "static" means not visible outside of file.  This is different
 * from the usage of "static" in Java.
 * Note that end_ptr is an output parameter.
 */
static char * getword(char * begin, char **end_ptr, int *piped) {
    char * end = begin;

    while ( *begin == ' ' )
        begin++;  /* Get rid of leading spaces. */
    end = begin;
    while ( *end != '\0' && *end != '\n' && *end != ' ' && *end != '#') {
        if (*end == '|')
            *piped = 1;
        else if (*end == '<')
            *piped = 2;
        else if (*end == '>')
            *piped = 3;
        else if (*end == '&')
            *piped = 4;
        end++;  /* Keep going. */
    }
    if ( end == begin )
        return NULL;  /* if no more words, return NULL */
    *end = '\0';  /* else put string terminator at end of this word. */
    *end_ptr = end;
    if (begin[0] == '$') { /* if this is a variable to be expanded */
        begin = getenv(begin+1); /* begin+1, to skip past '$' */
	if (begin == NULL) {
	    perror("getenv");
	    begin = "UNDEFINED";
        }
    }
    return begin; /* This word is now a null-terminated string.  return it. */
}

/* In C, "int" is used instead of "bool", and "0" means false, any
 * non-zero number (traditionally "1") means true.
 */
/* argc is _count_ of args (*argcp == argc); argv is array of arg _values_*/
static void getargs(char cmd[], int *argcp, char *argv[], int *piped)
{
    char *cmdp = cmd;
    char *end;
    int i = 0;
    int loop = 0;

    /* fgets creates null-terminated string. stdin is pre-defined C constant
     *   for standard intput.  feof(stdin) tests for file:end-of-file.
     */
    do {
        loop = 0;

        if (fgets(cmd, MAXLINE, stdin) == NULL && feof(stdin)) {
            printf("Couldn't read from standard input. End of file? Exiting ...\n");
            exit(1);  /* any non-zero value for exit means failure. */
        }
        while ((cmdp = getword(cmdp, &end, piped)) != NULL ) { /* end is output param */
            /* getword converts word into null-terminated string */
            argv[i++] = cmdp;
            /* "end" brings us only to the '\0' at end of string */
	        cmdp = end + 1;
        }
    } while (loop == 1);

    argv[i] = NULL; /* Create additional null word at end for safety. */
    *argcp = i;
}

static void execute(int argc, char *argv[], int *piped)
{
    int pfd[2];
    int childpid;

    if (*piped == 1) {
        if (pipe(pfd) == -1) { 
            perror("pipe"); exit(EXIT_FAILURE); }

        if (fork() == 0) {
            close(pfd[0]);
            dup2(pfd[1], STDOUT_FILENO);  /* Now any output to stdout goes to pipe. */
	        close(pfd[1]);        
	        char *const args[] = { argv[0], NULL };
            execvp(args[0], args);
            exit(EXIT_FAILURE);
        }
        if ((childpid = fork()) == 0) {
            close(pfd[1]);
            dup2(pfd[0], STDIN_FILENO);  /* Now any output to stdout goes to pipe. */
	        close(pfd[0]);        
            char *const args[] = {argv[argc - 1], NULL };
            execvp(args[0], args);
            exit(EXIT_FAILURE);
        }

        close(pfd[0]);
        close(pfd[1]);

        /* wait for the two children we forked */
        wait(NULL);
        wait(NULL);

        waitpid(childpid, NULL, 0);

        return;
    } else if (*piped == 2) {
        /* < */
        int i = 0;
        char * args[] = {NULL, NULL};

        if ((childpid = fork()) == 0) {
            while(i < argc) {
                if (strcmp(argv[i], "<") == 0) {
                    int fd = freopen(argv[i+1], "r", stdin);
                    if (fd == -1) perror("open for reading");
                    i++;
                } else {
                    args[0] = argv[i];
                }

                i++;
            }

            execvp(args[0], args);
            exit(EXIT_FAILURE);
        }

        waitpid(childpid, NULL, 0);

        return;
    } else if (*piped == 3) {
        /* > */
        int i = 0;
        char * args[] = {NULL, NULL};

        if ((childpid = fork()) == 0) {
            while(i < argc) {
                if (strcmp(argv[i], ">") == 0) {
                    int fd = freopen(argv[i+1], "w", stdout);
                    if (fd == -1) perror("open for writing");
                    i++;
                } else {
                    args[0] = argv[i];
                }

                i++;
            }
     
            execvp(args[0], args);
            exit(EXIT_FAILURE);
        }

        waitpid(childpid, NULL, 0);
        return;
    } else if (*piped == 4) {
        /* & */
        if ((childpid = fork()) == 0) {    
            char *const args[] = {argv[0], NULL };
            execvp(args[0], args);
            exit(EXIT_FAILURE);
        }

        printf("[1] %d\n", childpid);
        return;
    }
 
    // ======= ELSE IF NOT A PIPE =======
    childpid = fork();
    if (childpid == -1) { 
        perror("fork");
        printf("  (failed to execute command)\n");
    }
    else if (childpid == 0) {
        if (-1 == execvp(argv[0], argv)) {
          perror("execvp");
          printf("  (couldn't find command)\n");
        }
        exit(1);
    } else 
        waitpid(childpid, NULL, 0);
    
    return;
}

void interrupt_handler(int signum) {
    printf("^C is recognized\n");
}


int main(int argc, char *argv[])
{
    char cmd[MAXLINE];
    char *childargv[MAXARGS];
    int childargc;

    int foo = -1;
    int* piped = &foo;
    
    signal(SIGINT, interrupt_handler);
    if (argc > 1) {
	    freopen(argv[1], "r", stdin); 
    }

    while (1) {
	*piped = -1;
        printf("%% ");
        fflush(stdout);
	    getargs(cmd, &childargc, childargv, piped);
	    if ( childargc > 0 && strcmp(childargv[0], "exit") == 0 )
            exit(0);
	    else if ( childargc > 0 && strcmp(childargv[0], "logout") == 0 )
            exit(0);
        else
	        execute(childargc, childargv, piped);
    }

    /* NOT REACHED */
}





