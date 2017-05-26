
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*represent a row */

typedef struct {
    int tag;
    int valid;
} Row;


typedef struct {
    int index;
    //Row rows[];
    Row *rows;
    int timeIn;
    int numOfLines;
} Set;

typedef struct {
	int tag;
	int index;
	int offset;
} Address;

typedef struct {
	Set *sets;
} Cache ;

int numOfSets(int size, int way, int wordSize) {
	return size / (way * wordSize);
}
Address convert(int addr, int way, int size, int wordSize) {
	Address a;
	int words = (wordSize/4);
	//int i = (int) log2(size) / way;
	a.offset = addr % words;
	//a.offset = countOffset(addr, way, size, wordSize);
	a.index = (addr / words) % numOfSets(size, way, wordSize);
	a.tag = addr / words / numOfSets(size, way, wordSize);
	
	
	// a.offset = countOffset(addr, way, size, wordSize);
	// a.index = countIndex(addr, way, size, wordSize);
	// a.tag = countTag(addr, way, size, wordSize);
	return a;
}
Row init_row() {
	Row r;
	r.tag = 0;
	r.valid = 0;
	return r;
}

Set init_set(int ind, int size, int way, int wordSize) {
	Set s;
	s.index = ind;
	s.timeIn = 0;
	s.numOfLines = way;

	s.rows = malloc(s.numOfLines * sizeof(Row));
	for(int i = 0; i < way; i++) {
		s.rows[i] = init_row();
	}
	return s;
}

Cache init_cache(int size, int way, int wordSize) {
	Cache c;
	int numberOfSets = numOfSets(size, way, wordSize);
	c.sets = malloc(numberOfSets * sizeof(Set));
	for(int i = 0; i < numberOfSets; i++) {
		c.sets[i] = init_set(i, size, way, wordSize);
	}
	return c;
}

void makeCache(int size, int way, int wordSize, Cache c, int addr) {
	Address a = convert(addr, way, size, wordSize);
	printf("%d - ", addr);
	printf("(%d, ", a.tag);
    printf("%d, ", a.index);
    printf("%d) - ", a.offset);
	int numberOfSets = numOfSets(size, way, wordSize);

	for (int i = 0; i < numberOfSets; i++) {

    if (c.sets[i].index == a.index) {

      for (int n = 0; n < c.sets[i].numOfLines; n++) {
        if (c.sets[i].rows[n].tag == a.tag &&
            c.sets[i].rows[n].valid) {
          printf("HIT\n");
          return;
        }
      }

      for (int n = 0; n < c.sets[i].numOfLines; n++) {
        if (!c.sets[i].rows[n].valid) {
          c.sets[i].rows[n].tag = a.tag;
          c.sets[i].rows[n].valid = 1;
          printf("MISS\n");
          return;
        }
      }

      c.sets[i].rows[c.sets[i].timeIn].tag = a.tag;
      printf("MISS. Replace tag\n");
      c.sets[i].timeIn = (c.sets[i].timeIn + 1) % c.sets[i].numOfLines;
  }
}
}



int main(int argc, char const *argv[]) {
	int test_set[] = {1, 4, 8, 5, 20, 17, 19, 56, 
	9, 11, 4, 43, 5, 6, 9, 17};
	int test2[] = {0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72,
  76, 80, 0, 4, 8, 12, 16, 71, 3, 41, 81, 39, 38, 71, 15, 39, 11, 51, 57, 41};

	printf("128-byte, direct-mapped cache with 8-byte cache lines\n");
    int size4 = 128;
    int ws4 = 8; // one word
    int way4 = 1;
    Cache c4 = init_cache(size4, way4, ws4);
    for (int i = 0; i < (sizeof(test2) / sizeof(int)); i++) {
    makeCache(size4, way4, ws4, c4, test2[i]);
  }

	printf("64-byte, 2-way set associative cache with 8-byte cache lines\n");
    int size1 = 64;
    int ws1 = 8; 
    int way1 = 2;
    Cache c1 = init_cache(size1, way1, ws1);
    for (int i = 0; i < (sizeof(test_set) / sizeof(int)); i++) {
    makeCache(size1, way1, ws1, c1, test_set[i]);
  }

    printf("128-byte, direct-mapped cache with 16-byte cache lines\n");
    int size2 = 128;
    int ws2 = 16; 
    int way2 = 1;
    Cache c2 = init_cache(size2, way2, ws2);
    for (int i = 0; i < (sizeof(test_set) / sizeof(int)); i++) {
    makeCache(size2, way2, ws2, c2, test_set[i]);
  }
    printf("64-byte, fully associative cache with 8-byte cache lines\n");
    int size3 = 64;
    int ws3 = 8; 
    int way3 = 8;
    Cache c3 = init_cache(size3, way3, ws3);
    for (int i = 0; i < (sizeof(test_set) / sizeof(int)); i++) {
    makeCache(size3, way3, ws3, c3, test_set[i]);
  }
  
  return 0;

}
