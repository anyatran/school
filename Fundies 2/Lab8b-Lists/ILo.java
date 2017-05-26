
// Represents a list of items of type T
interface ILo<T>{

    // Is there an even number of items in this list?
    public boolean evenCount();
    public int length();
    public ILo<String> listString();
    public ILo<T> filter(ISelect<T> pred);
    public T find(ISelect<T> pred);
}                                

// Represents an empty list of items of type T
class MtLo<T> implements ILo<T>{
    MtLo(){}

    // Is there an even number of items in this list?
    public boolean evenCount(){
        return true;
    }
    public int length(){
        return 0;
    }
    public ILo<String> listString(){
        return new MtLo<String>();
    }

    public ILo<T> filter(ISelect<T> pred){
        return this;
    }

    public T find(ISelect<T> pred){
        throw new NoSuchElementException("the list is empty");
    }
}                                              

// Represents a nonempty list of items of type T
class ConsLo<T> implements ILo<T>{
    T first;
    ILo<T> rest;

    ConsLo(T first, ILo<T> rest){
        this.first = first;
        this.rest = rest;
    }

    /* Template:
     *   Fields:
     *     ... this.first ...    -- T
     *     ... this.rest ...     -- ILo<T>
     *
     *   Methods:
     *     ... this.evenCount(T) ...       -- boolean
     *
     *   Methods for Fields:
     *     ... this.rest.evenCount() ...  -- boolean
     */

    // Is there an even number of items in this list?
    public boolean evenCount(){
        return !this.rest.evenCount();
    }
    public int length(){
        return 1 + this.rest.length();
    }
    public ILo<String> listString(){
        return new ConsLo<String>(this.first.toString(), this.rest.listString());
    }


    public ILo<T> filter(ISelect<T> pred){
        if (pred.select(this.first)){
            return new ConsLo<T>(this.first, this.rest.filter(pred));
        }
        else return this.rest.filter(pred);
    }

    public T find(ISelect<T> pred){
        if (pred.select(this.first)){
            return this.first;
        }
        else return this.rest.find(pred);
    }

}                                                    










