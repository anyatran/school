import java.util.*;

/*
 n ln: divide and concur
 n^2: 2 nested loops over the list
 ln: binary search
 exponential: 

 Generics: list-of<T>

 */

// something that can be iterated over
interface Iterable<T>{
    Iterator<T> getIterator();
}

// to provide a swquence of values for iterable
interface Iterator<T>{
    boolean hasNext();
    T next();

}

// ArrayList<T> items;
// an iterator that all possible pairs
class PairIterator<T> implements Iterator<Pair<T, T>>{
    List<T> source;
    Iterator<T> firstIt;
    Iterator<T> secondIt;
    PairIterator(List<T> source){
        this.source = source;
        this.firstIt = source.getIterator();
        this.secondIt = source.getIterator();
    }
    boolean hasNext(){
        return this.firstIt.hasNext() || this.secondIt.hasNext();
    }
    Pair<T, T> next(){
        if(this.secondIt.hasNext()){
            return new Pair
        }
        else if (!this.secondIt.hasNext()){
            this.secondIt = this.source.getIterator();
            this.firstIt.next();
            return this.next();
        }
    }
}














