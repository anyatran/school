/* See Chapter 5 of Advanced UNIX Programming:  http://www.basepath.com/aup/
 *   for further related examples of systems programming.  (That home page
 *   has pointers to download this chapter free.
 *
 * Copyright (c) Gene Cooperman, 2006; May be freely copied as long as this
 *   copyright notice remains.  There is no warranty.
 */

/* To know which "includes" to ask for, do 'man' on each system call used.
 * For example, "man fork" (or "man 2 fork" or man -s 2 fork") requires:
 *   <sys/types.h> and <unistd.h>
 */
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <string.h>
#include <signal.h>

#define MAXLINE 200  /* This is how we declare constants in C */
#define MAXARGS 20
#define STDOUT 1
#define STDIN 0

pid_t childpid;
/* In C, "static" means not visible outside of file.  This is different
 * from the usage of "static" in Java.
 * Note that end_ptr is an output parameter.
 */
static char * getword(char * begin, char **end_ptr) {
    char * end = begin;

    while ( *begin == ' ' )
        begin++;  /* Get rid of leading spaces. */
    end = begin;
    while ( *end != '\0' && *end != '\n' && *end != ' ' && *end != '#') 
        end++;  /* Keep going. */
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

    /* fgets creates null-terminated string. stdin is pre-defined C constant
     *   for standard intput.  feof(stdin) tests for file:end-of-file.
     */
    if (fgets(cmd, MAXLINE, stdin) == NULL && feof(stdin)) {
        printf("Couldn't read from standard input. End of file? Exiting ...\n");
        exit(1);  /* any non-zero value for exit means failure. */
    }
    else if (*cmdp == '|') {
    	*piped = 1;
    }
    else if (*cmdp == '>') {
    	//do out
    }
    else if (*cmdp == '<') {
    	//do in
    }

    printf(cmdp);

    while ( (cmdp = getword(cmdp, &end)) != NULL ) { /* end is output param */
        /* getword converts word into null-terminated string */
        argv[i++] = cmdp;
        /* "end" brings us only to the '\0' at end of string */
	cmdp = end + 1;
    }
    argv[i] = NULL; /* Create additional null word at end for safety. */
    *argcp = i;
}

static void execute(int argc, char *argv[], int *piped)
{
	int pfd[2];

	if (*piped == -1) {
		if (pipe(pfd) == -1) { 
            perror("pipe"); exit(EXIT_FAILURE); }
	}
    childpid = fork();
    if (childpid == -1) { /* in parent (returned error) */
        perror("fork"); /* perror => print error string of last system call */
        printf("  (failed to execute command)\n");
    } 
    else if (childpid == 0) { /* child:  in child, childpid was set to 0 */
        /* Executes command in argv[0];  It searches for that file in
	 *  the directories specified by the environment variable PATH.
         */
	 	close(pfd[1]);

	 	if (fork() == 0) { /* if we are child1, do:  "ls | ..." */
	        close(STDOUT);
	        dup(pfd[1]); /* set up empty STDOUT to be pipe_fd[1] */
	        close(pfd[1]); /* let child2 know we won't write to pipe here */
	        execvp(argv[0], argv);
	    } else if (fork() == 0) { /* if we are child2, do:  "... | wc" */
	        close(STDIN);
	        dup(pfd[0]); /* set up empty STDIN to be pipe_fd[0] */
	        close(pfd[1]); /* let child2 know we won't write to pipe */
	        execvp(argv[0], argv);
	    } else { /* else we're parent */
	        int status;
	        close(pfd[1]); /* let child2 know we won't write to pipe */
	        wait(NULL);        /* wait on any child. ignore status */
	        wait(NULL);        /* wait on any child. ignore status */
	    }
        /* NOT REACHED unless error occurred */
        //exit(1);
    } else {/* parent:  in parent, childpid was set to pid of child process */
        waitpid(childpid, NULL, 0);  /* wait until child process finishes */
    } 
    return;
}

void interrupt_handler(int signum) {
    kill(childpid, SIGINT);
    printf("^C is recognized");
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
        printf("%% "); /* printf uses %d, %s, %x, etc.  See 'man 3 printf' */
        fflush(stdout); /* flush from output buffer to terminal itself */
	getargs(cmd, &childargc, childargv, piped); /* childargc and childargv are
            output args; on input they have garbage, but getargs sets them. */
        /* Check first for built-in commands. */
	if ( childargc > 0 && strcmp(childargv[0], "exit") == 0 )
            exit(0);
	else if (childargc > 0 && strcmp(childargv[0], "logout") == 0 )
            exit(0);
        else
	    execute(childargc, childargv, piped);
    }
    /* NOT REACHED */
}






