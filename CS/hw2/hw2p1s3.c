#include <stdio.h>
#define IN   1  /* inside a word */
#define OUT  0  /* outside a word */

/* count lines, words, and characters in input */
/* to run, try:    gcc THIS_FILE.c ; cat ANY_FILE | ./a.out */
/* PROBLEM 1, SOLUTION 3 */
int main() {
    char x1[100] = "The quick brown fox \njumped over the \nlazy dog.";
    int  nl, nw, nc, state;
    state = OUT;
    nl = nw = nc = 0;
    char *x1p = x1;
    
    while (*x1p != '\0') {

	    nc++;
	if(*x1p == '\n') 
	    nl++;
	if(*x1p == ' ' || *x1p == '\n' || *x1p == '\t') 
	    state = OUT;
	else if (state == OUT) {
	    state = IN;
	    nw++;
	}
    }
	printf("Number of lines: %d\n", nl);
	printf("Number of words: %d\n", nw);
	printf("Number of characters: %d\n", nc);  
	    
    return 0;
}

