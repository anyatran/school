import java.util.Iterator;

// produce an ArraysList of all Strings of len < 4
ArrayList<String> ShortStrings(ArrayList<String> str){
    ArrayList<String> ShortList = new ArrayList<String>; // a new list
    for (String s: str){ // for each loop
        if(s.length() < 4){
            ShortList.add(s);
        }
    }
}


// produce an AL of the position of al Strings of L<4
// for loops
ArrayList<Integer> ShortStrings(ArrayList<String> str){
    // base cases
    ArrayList<String> dest = new ArrayList<Integer>();
    //starting point of the loop  cond: ending criteria   update: from 1 iteration to the rest
    for(int index = 0,          index < str.length();    index = index + 1){
        //body
        if(str.get(index).length() < 4){
            dest.add(index);
        }
    } // return 
    return dest;
}



// Does the AL contain the fiven string?
boolean contain(ArrayList<String> str, String s){
    boolean result = false; // initial result
    for(Stirng item: str){
        if(item.equals(s)){
            result = true;
        }
    }
    return result;
}



// return the location of the String in the AL (or -1 if not found)
int indexOf(ArrayList<String> str, Stirng s){
    for(int i = 0; i < str.length(); i = i + 1){

    }

}

// return prefix of list for which pred is true
// for loop
ArrayList<T> takeWhile(IPred<T> pred, ArrayList<T> src){
    ArrrayList<T> ret = new ArrayList<T>();
    for(int index = 0; index < src.size(); index = index + 1 ){ // && pred.test(src.get(index)))
        if(pred.test(src.get(index))){
            ret.add(src.get(index));
        }
        else
            return ret;
    }
}


/*WHILE LOOP
setup (bascase)
While(test){
    body;
    updates from itiration to itirations
    return
 */

ArrayList<T> takeWhile2(IPred<T> pred, ArrayList<T> src){
    ArrayList<T> ret = new ArrayList<T>();
    int index = 0;
    While(index < src.size(), && pred.test(src.get(index))){
        ret.add(src.get(index));
        index = index + 1 // 
    }
    return ret;
}


int fib1(int n){
    if(n == 1){
        return 1;
    }
    else if (n == 2){
        return 1;
    }
    else return fib1(n - 1) + fib(n - 2);
}
// fib 6 = fib 5 + fib 4...
// n, fib(n-2), fib(n-1), fib(n)
int fib(int Ndes){
    int n = 3;
    int fn2 = 1;
    int fn1 = 1;
    int fn = 2;
    if(Ndes == 1){
        return 1;
    }
    else if (Ndes == 2){
        return 1;
    }
    else {
        While(n < Ndes){
            fn2 = fn1;
            fn = fn;
            fn = fn2 + fn1;
            n = n + 1;
        }
        return fn;
    }
}
/*
// for each loop
for (T t: tlist){
    ...
}
vvvvvvvvvvv

Iterable<T> tlist;
Iterator<T> iter = tlist.getIterator();
while(iter.hasNext()){
    T t = iter.getNext();


// for loop
for (int index = 0; index < tlist.length(); index = index + 1){
    T t = tlist.get(index);
}

// while loop
while (index < tlist.length()){
    T t = tlist.get(index);
    .
    .
    .
    index = index + 1;
}

*/
interface Iterable<T>{
    Iterator<T> getIterator();
}


interface Iterator<T>{
    boolean hasNext();
    T getNext();
    
    while(iter.hasNext()){
        T t = iter,getNext();
    }
}
class ArrayList<T> implements Iterator<T> {
    int curIndex;
    ArrayList<T> data;
    
    // PURPOSE: returns the next item
    // EFFECT: increment the current index
    T getNext(){
        if(this.curIndex >= this.data.length()){
            throw new OutOfBounceException();
        }
        this.curIndex = this.curIndex + 1;
        return this.data.get(this.curIndex - 1);
    }
    
    boolean hasNext(){
        return this.curIndex < this.data.length();
    }
}
// problems with arraylists: ciclical lists
// 

class ArrayList<T> implements Interable<T>{
    public Interator<T> getIterator(){
        return new ArrayList<Iterator>(0, this);
    }
}

DequeIterator<T> implements Iterator<T>{
    Node<T> cur;
    public boolean hasNext(){
        return !this.cur.isSentinel();
    }
    
    public T getNext(){
        T ret = this.cur.data;
        this.cur = this.cur.next;
        return ret.cur.data;
    }
}


Deque<T> implements Iterable<T>{
    public Iterator<T> getIterator(){
        return new ArrayList<Iterator>(this.sentinel, next);    
    }
}





