#include <stdio.h>

/* count digits, white space, others */
/* to run, try:    gcc THIS_FILE.c ; cat ANY_FILE | ./a.out */
/*SOLUTION 1*/
int main() {
    char x1[100] = "The 25 quick brown foxes jumped over the 27 lazy dogs 15 times."; 
   /* char x1[100] = "t 5d";*/
    int i, nwhite, nother;
    int ndigit[10];
    nwhite = nother = 0;
    for (i = 0; i < 10; i++) {
        ndigit[i] = 0;	
    }
    for(int ind; x1[ind] != '\0'; ind++) {
	if (x1[ind] >= '0' && x1[ind] <= '9')
	   ++ndigit[x1[ind]-'0'];
	
        else if (x1[ind] == ' ' || x1[ind] == '\n' || x1[ind] == '\t')
            ++nwhite;
        else
            ++nother;
    }
    printf("digits =");
    for (i = 0; i < 10; ++i) {
        printf(" %d", ndigit[i]);
    }
    printf(", white space = %d, other = %d\n",
        nwhite, nother);
    return 0;
}
