

// Represents a list of items of type T
interface ILo<T> {

    // accept the visitor that produces the result of the type R
    public <R> R accept(ILoVisitor<R, T> ilov);
    
  //is this list empty?
    public boolean isEmpty();
 
}                                

// Represents an empty list of items of type T
class MtLo<T> implements ILo<T> {


    // accept the visitor that produces the result of the type R
    public <R> R accept(ILoVisitor<R, T> ilov) {
        return ilov.forMt();
    }
    
  //is this list empty?
    public boolean isEmpty() {
        return true;
    }
}                                              

// Represents a nonempty list of items of type T
class ConsLo<T> implements ILo<T> {
    T first;
    ILo<T> rest;
  
    ConsLo(T first, ILo<T> rest) {
        this.first = first;
        this.rest = rest;
    }
  
    /* Template:
     *   Fields:
     *     ... this.first ...    -- T
     *     ... this.rest ...     -- ILo<T>
     *
     *   Methods:
     *     ... <R> this.accept(ILoVisitor<R, T>) ...   -- R
     *     ... this.isEmpty() ...                       ---boolean
     *
     *   Methods for Fields:
     *     ... <R> this.rest.accept(ILoVisitor<R, T>) ...   -- R
     *     ...  this.rest.isEmpty() ...                     -- boolean
     */

    // accept the visitor that produces the result of the type R
    public <R> R accept(ILoVisitor<R, T> ilov) {
        return ilov.forCons(this.first, this.rest);
    }
    
    //is this list empty?
    public boolean isEmpty() {
        return false;
        
    }
}                                                    
