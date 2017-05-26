/* This code is modified from code found on the WEB at
 *  http://www.arai.pe.u-tokyo.ac.jp/~yuasa/InfoSys2/sample/quicksort.c.HTML
 *    at the web site of Prof. Hideo Yuasa at Tokyo University.
 *  						- Gene Cooperman
 */

/* quicksort.c */
/*
#include <stdio.h>
  USED FOR:  #define EOF (-1)

#define MAX 100
*/

void quicksort(char * [ ], int, int);
void print_array(char * [ ], const int);

int main(void)
{
  char * data[] = {"Joe", "Jenny", "Jill", "John", "Jeff", "Joyce",
		"Jerry", "Janice", "Jake", "Jonna", "Jack", "Jocelyn",
		"Jessie", "Jess", "Janet", "Jane"};
  int size = 16;

  printf("Initial array is:\n");
  print_array(data, size);
  quicksort(data, 0, size - 1);
  printf("Quick sort is finished!\n");
  print_array(data, size);
  exit(0);
}

int str_lt (char *x, char *y) {
  for (; *x!='\0' && *y!='\0'; x++, y++) {
    if ( *x < *y ) return 1;
    if ( *y < *x ) return 0;
  }
  if ( *y == '\0' ) return 0;
  else return 1;
}

void quicksort(char * a[ ], int first, int last)
{
  int i, j;
  char *x, *t;

  x = a[(first + last) /2];
  i = first; j = last;
  /* printf("Quicksort routine is executed! first = %d, last = %d, x = %d\n",
   *         first, last, x);
   */
  for(;;)
  {
    while( str_lt(a[i], x) ) i++;
    while( str_lt(x, a[j])) j--;
    if (i >= j) break;
    t = a[i]; a[i] = a[j]; a[j] = t;
    /* printf("Data is swapped!\n"); */
    i++; j--;
  }
  if(first < i-1) quicksort( a, first, i-1 );
  if(j+1 < last) quicksort( a, j+1, last );
}

void print_array(char * a[ ], const int size)
{
  int i=0;
  printf("[");
  while(i < size) printf("  %s", a[i++]);
  printf(" ]\n");
}
