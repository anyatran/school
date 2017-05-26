#include <stdio.h>
#define IN   1  /* inside a word */
#define OUT  0  /* outside a word */

/* count lines, words, and characters in input */
/* to run, try:    gcc THIS_FILE.c ; cat ANY_FILE | ./a.out */
/* PROBLEM 1, SOLUTION 2 */
int main() {
    char x1[100] = "The quick brown fox \njumped over the \nlazy dog.";
    int i, nl, nw, nc, state;
    state = OUT;
    nl = i = nw = nc = 0;
    char *x1p = x1;

   /* for(int i;*(x1p + i)<sizeof(x1); i++) {*/
    while(*(x1p+i) != '\0') {
/*	if(*(x1p + i)  != '\0')*/
	    nc++;
	if(*(x1p + i) == '\n') 
	    nl++;
	if(*(x1p + i) == ' ' || *(x1p + i) == '\n' || *(x1 + i) == '\t') 
	    state = OUT;
	else if (state == OUT) {
	    state = IN;
	    nw++;
	}
	i++;
    }
	printf("Number of lines: %d\n", nl);
	printf("Number of words: %d\n", nw);
	printf("Number of characters: %d\n", nc);  
    
    return 0;
}
