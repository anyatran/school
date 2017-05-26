#include <stdio.h>

int factorial(int x) {
  if (x == 0)
    return 1;
  else {
    return x * factorial(x-1);
  }
}

int main() {
  int number;
  printf("Positive integer: ");
  scanf("%d", &number); /* argument is ptr to "int box"; scanf will insert */
  printf("The value of 'factorial(%d)' is:  %d\n",
	 number, factorial(number));
  return 0;  /* return 0 to operating system means:  success;
              * return any other integer to operating system means:  error code
              */
}
