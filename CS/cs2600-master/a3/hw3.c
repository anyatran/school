/* --------------
Nathan Lilienthal
Homework 3
Oct 16, 2012
----------------- */

#include <stdio.h>
#define IN   1  /* inside a word */
#define OUT  0  /* outside a word */

// Lines, words, chars, counter done with arrays.
lwc_arrays() {
  printf("%s\n", "Counting lines, words, and chars with classic arrays.");

  char x1[100] = "The quick brown fox jumped over the lazy dog.";

  int lines, words, chars, state, c;
  state = OUT;
  words = chars = 0;
  lines = 1;          // lines should start at 1, since the string starts on the first line

  char *x1p = x1;
  while ((c = *(x1p + chars)) != '\0') {
    chars++;
    if (c == '\n') {
      lines++;
    }
    if (c == ' ' || c == '\n' || c == '\t') {
      state = OUT;
    } else if (state == OUT) {
      state = IN;
      words++;
    }
  }

  printf("%d %s %d %s %d %s\n", lines, "lines, ", words, "words, ", chars, "chars, ");

  return 0;
}

lwc_array_style_pointers() {
  printf("%s\n", "Counting lines, words, and chars with array style pointers.");

  char x1[100] = "The quick brown fox jumped over the lazy dog.";

  int lines, words, chars, state, c;
  state = OUT;
  words = chars = 0;
  lines = 1;          // lines should start at 1, since the string starts on the first line

  char *x1p = x1;
  while ((c = *(x1p + chars)) != '\0') {
    chars++;
    if (c == '\n') {
      lines++;
    }
    if (c == ' ' || c == '\n' || c == '\t') {
      state = OUT;
    } else if (state == OUT) {
      state = IN;
      words++;
    }
  }

  printf("%d %s %d %s %d %s\n", lines, "lines, ", words, "words, ", chars, "chars, ");

  return 0;
}

lwc_native_pointers() {
  printf("%s\n", "Counting lines, words, and chars with native pointers.");

  char x1[100] = "The quick brown fox jumped over the lazy dog.";

  int lines, words, chars, state, c;
  state = OUT;
  words = chars = 0;
  lines = 1;          // lines should start at 1, since the string starts on the first line

  char *x1p = x1;
  while ((c = *x1p) != '\0') {
    chars++;
    if (c == '\n') {
      lines++;
    }
    if (c == ' ' || c == '\n' || c == '\t') {
      state = OUT;
    } else if (state == OUT) {
      state = IN;
      words++;
    }
    *x1p++;
  }

  printf("%d %s %d %s %d %s\n", lines, "lines, ", words, "words, ", chars, "chars, ");

  return 0;
}

digits_arrays() {
  printf("%s\n", "Counting occurrence of numbers with classic arrays.");

  char x1[100] = "The 25 quick brown foxes jumped over the 27 lazy dogs 17 times.";

  int curr, c, i, nwhite, nother;
  int ndigit[10];
  curr = nwhite = nother = 0;

  for (i = 0; i < 10; ++i) {
    ndigit[i] = 0;
  }

  while ((c = x1[curr]) != '\0') {
    if (c >= '0' && c <= '9') {
      ++ndigit[c-'0'];
    } else if (c == ' ' || c == '\n' || c == '\t') {
      ++nwhite;
    } else {
      ++nother;
    }
    curr++;
  }

  printf("digits =");
  for (i = 0; i < 10; ++i) printf(" %d", ndigit[i]);
  printf(", white space = %d, other = %d\n", nwhite, nother);

  return 0;
}

digits_array_style_pointers() {
  printf("%s\n", "Counting occurrence of numbers with array style pointers");

  char x1[100] = "The 25 quick brown foxes jumped over the 27 lazy dogs 17 times.";

  int c, i, nwhite, nother;
  int ndigit[10];
  nwhite = nother = 0;

  int curr = 0;
  char *x1pt = x1;
  int *ndigitpt = ndigit;

  for (i = 0; i < 10; ++i) {
    *(ndigitpt + i) = 0;
  }

  while ((c = *(x1pt + curr)) != '\0') {
    if (c >= '0' && c <= '9') {
      ++ndigit[c-'0'];
    } else if (c == ' ' || c == '\n' || c == '\t') {
      ++nwhite;
    } else {
      ++nother;
    }
    curr++;
  }

  printf("digits =");
  for (i = 0; i < 10; ++i) printf(" %d", *(ndigitpt + i));
  printf(", white space = %d, other = %d\n", nwhite, nother);

  return 0;
}

digits_native_pointers() {
  printf("%s\n", "Counting occurrence of numbers with native pointers.");

  char x1[100] = "The 25 quick brown foxes jumped over the 27 lazy dogs 17 times.";

  int c, i, nwhite, nother;
  int ndigit[10];
  nwhite = nother = 0;

  char *x1pt = x1;
  int *ndigitpt;

  ndigitpt = ndigit;
  for (i = 0; i < 10; ++i) {
    *ndigitpt = 0;
    ndigitpt++;
  }

  while ((c = *x1pt) != '\0') {
    if (c >= '0' && c <= '9') {
      ++ndigit[c-'0'];
    } else if (c == ' ' || c == '\n' || c == '\t') {
      ++nwhite;
    } else {
      ++nother;
    }
    x1pt++;
  }

  printf("digits =");
  ndigitpt = ndigit;
  for (i = 0; i < 10; ++i) {
    printf(" %d", *ndigitpt);
    ndigitpt++;
  }
  printf(", white space = %d, other = %d\n", nwhite, nother);
}

// Useage, ./a.out [NUMBER]
// 1 - 3 = Assignment 1 (A - C)
// 4 - 6 = Assignment 2 (A - C)
// Example: ./a.out 3 => Assignment 1C
int main(int argc, char const *argv[]) {
  if (!strcmp(argv[1], "1")) lwc_arrays();
  else if (!strcmp(argv[1], "2")) lwc_array_style_pointers();
  else if (!strcmp(argv[1], "3")) lwc_native_pointers();
  else if (!strcmp(argv[1], "4")) digits_arrays();
  else if (!strcmp(argv[1], "5")) digits_array_style_pointers();
  else if (!strcmp(argv[1], "6")) digits_native_pointers();
}