#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// This is a simulation of a Row
// not intended to be functional, it does not actually
// contain any data, it simply knows if it's valid or not.
typedef struct {
  int tag;
  int valid;
} Row;

// A Set is a indexs container for multiple rows.
typedef struct {
  int index;
  int num_rows;
  int eviction_row;
  Row* rows;
} Set;

// A Cache has multiple Sets, the amount of sets, and
// the size of the sets depends on the type of cache.
typedef struct {
  int size;
  int block_size;
  int associativity;
  Set* sets;
} Cache;

typedef struct {
  int index;
  int tag;
} Address;

/* HELPER FUNCTIONS */

// Number of sets in a cache
int cacheNumSets( Cache cache ) {
  return cache.size / (cache.associativity * cache.block_size);
}

/* STRUCT CONSTRUCTORS */

// Returns a new Row.
// all fields of this row are 0
Row new_row() {
  Row r;
  r.tag   = 0;
  r.valid = 0;
  return r;
}

// Returns a new Set.
// the set's index is the given index, and it has an array of
// rows of the given length.
Set new_set( int index, int num_rows, int block_size ) {
  Set s;
  s.index    = index;
  s.num_rows = num_rows;
  s.eviction_row = 0;

  s.rows = malloc(num_rows * sizeof(Row));
  for (int i = 0; i < num_rows; i++) {
    s.rows[i] = new_row();
  }

  return s;
}

// Returns a new Cache
// the cache's size is the given size, and the it has
// size / associativity * block_size Sets.
Cache new_cache( int size, int block_size, int associativity ) {
  Cache c;
  c.size = size;
  c.block_size = block_size;
  c.associativity = associativity;

  int num_sets = cacheNumSets(c);
  c.sets = malloc(num_sets * sizeof(Set));
  for (int i = 0; i < num_sets; i++) {
    c.sets[i] = new_set(i, associativity, block_size);
  }

  return c;
}

/* PRINTING */

// prints a single row
void print_row( Row row ) {
  printf("<<T: %d, V: %d>>", row.tag, row.valid);
}
// prints a set, with all the rows
void print_set( Set set ) {
  printf("[ INDEX %d :", set.index);

  for (int i = 0; i < set.num_rows; i++) {
    printf(" ");
    print_row(set.rows[i]);
    printf(", ");
  }

  printf(" ]\n");
}
// sexy cache printing.
void print_cache( Cache cache ) {
  printf("CACHE INFO size=%d, data block size=%d, k=%d \n",
    cache.size, cache.block_size, cache.associativity);

  for (int i = 0; i < cacheNumSets(cache); i++) {
    print_set(cache.sets[i]);
  }
}


/* SIMULATION */

Address addressFromInt( Cache cache, int addr ) {
  Address a;
  a.tag   = addr / (cache.size / cache.associativity);
  a.index = addr / cache.block_size % cacheNumSets(cache);
  return a;
}

void loadData( Cache cache, Address addr ) {
  int num_sets = cacheNumSets(cache);
  for (int i = 0; i < num_sets; i++) {

    if (cache.sets[i].index == addr.index) {

      // Check for a hit
      for (int n = 0; n < cache.sets[i].num_rows; n++) {
        if (cache.sets[i].rows[n].tag == addr.tag &&
            cache.sets[i].rows[n].valid) {
          printf("HIT\n");
          return;
        }
      }

      // ok, not a hit load data.
      for (int n = 0; n < cache.sets[i].num_rows; n++) {
        if (!cache.sets[i].rows[n].valid) {
          cache.sets[i].rows[n].tag = addr.tag;
          cache.sets[i].rows[n].valid = 1;
          printf("MISS\n");
          return;
          // insert data... oh wait lol.
        }
      }

      // everything was valid you say?
      cache.sets[i].rows[cache.sets[i].eviction_row].tag = addr.tag;
      printf("MISS evicting row %d\n", cache.sets[i].eviction_row);

      // update eviction row.
      cache.sets[i].eviction_row = (cache.sets[i].eviction_row + 1) % cache.sets[i].num_rows;

    }

  }
}

void loadDataFromInt( Cache cache, int addr ) {
  Address a = addressFromInt(cache, addr);
  printf("Address: %d \t Tag: %d \t Index: %d \t", addr, a.tag, a.index);
  loadData(cache, a);
}


int main(int argc, char const *argv[]) {

  int test_sequence[] = { 0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52,
                          56, 60, 64, 68, 72, 76, 80, 0, 4, 8, 12, 16, 71, 3,
                          41, 81, 39, 38, 71, 15, 39, 11, 51, 57, 41 };

  /*
  direct-mapped is 1 set, or k = size/block_size
    [INDEX 0 : <<>> <<>> <<>> <<>> <<>> <<>> <<>> ... ]
  fully associative is size/block_size sets, or k = 1
    [INDEX 0 : <<>> ]
    [INDEX 1 : <<>> ]
    [INDEX 2 : <<>> ]
    [INDEX 3 : <<>> ]
    .
    .
    .
  */

  // Assume a 128-byte, direct-mapped cache with 8-byte cache blocks
  printf("\033[;41mTEST 1\033[0;m\n");
  Cache test1 = new_cache(128, 8, 1);
  for (int i = 0; i < (sizeof(test_sequence) / sizeof(int)); i++) {
    loadDataFromInt(test1, test_sequence[i]);
  }
  print_cache(test1);

  // Assume a 64-byte, 2-way set associative cache with 8-byte cache blocks
  printf("\033[;41mTEST 2\033[0;m\n");
  Cache test2 = new_cache(64, 8, 2);
  for (int i = 0; i < (sizeof(test_sequence) / sizeof(int)); i++) {
    loadDataFromInt(test2, test_sequence[i]);
  }
  print_cache(test2);

  // Assume a 128-byte, direct-mapped cache with 16-byte cache blocks
  printf("\033[;41mTEST 3\033[0;m\n");
  Cache test3 = new_cache(128, 8, 1);
  for (int i = 0; i < (sizeof(test_sequence) / sizeof(int)); i++) {
    loadDataFromInt(test3, test_sequence[i]);
  }
  print_cache(test3);

  // Assume a 64-byte, fully associative cache with 8-byte cache blocks
  printf("\033[;41mTEST 4\033[0;m\n");
  Cache test4 = new_cache(64, 8, 8);
  for (int i = 0; i < (sizeof(test_sequence) / sizeof(int)); i++) {
    loadDataFromInt(test4, test_sequence[i]);
  }
  print_cache(test4);

  return 0;
}