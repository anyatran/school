/*
 
 inderface Dict<K, V>{
    V get(K key);
    void set(K key, V val)
    void remove(K key)
    boolean hasKey(K key)
    int size()
    Iterator<K> keys();
 
 
 Dict<K, V>
 
 AL<Pair<K, V>>
 
 class Pair<K, V>{
   k key;
   v val;
   }
   
                     get         set      hasKey       size
 List<Pair<K, V>>|  O(size)    O(size)    O(size)     O(size)
 
 HashMap<K, V>   |  O(1)        O(1)       O(1)         O(1)
 
 AL<Pair<K, V>>  | O(ln size)  O(size)    O(ln size)   O(1)
 (sorted)
 
 
 
        a
      b    d
   c    e f   h
 g   i   k  l   n 
 
 1) depth first: a b c g i e d f k h l n (T L R)
 2) bredth first: a b d c e f h g i k l n
 3) in order: g c i b e a k f d l h n (L T R)
 4) post order: g i c e b k f l n h d a (L R T)
        
 
 
    T
 L      R
*/

class DepthKFirstIterator<T> implements iterator<T> {
    // you need a STACK of tree nodes that you have left to visit
    boolean hasNext(){
         return stackIsNotEmpty();
    }
    T next(){
        Tree current = stack.pop();
        stack.push(current.right); //if there is one));
        stack.push(current.left); // if there is one
    }
}

/*
 Stack's size = O(depth) ~ O(ln n)
 Queue's size = O(Width) = O(n)
 
 */

class FibNumbersBelow50 implements Iterator<Integer>{
    int prev = 0;
    int cur = 1;
    
    public boolean hasNext(){
        return this.cur < 50;
    }
    public int next(){
        int c = this.cur;
        this.cur = this.prev + c;
        this.prev = c;
        return c;
    }
    
}





