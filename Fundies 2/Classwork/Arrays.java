/*
boolean where there any...ormap
int how many...filter
ILoR find all (Runner -> Boolean)...filter
Runner find min...fold
find ___ of runners (Runner -> ???) ...map: return different data type






ABSTRACT TYPE OF THE DATA
 */

interface IRunner2String{
    String transform(Runner r);
}

interface IRunner2int{
    int tranasform(Runner r);
}

interface IRunner2bool{
    boolean transform(Runner r);
}

// List-of T
// abstract all the type and replace by T
interface IList<T>{
    IList<T> filter(Pred<T> pred);
    <U> IList<U> map(IFun<T, U> f); // declare U
    <U> U fold(IFolder<T, U> f, U base);
    int length();
    IList<T> sortBy(IComparable<T> compare);
}


class Mt implement IList<T>{
    public <U> IList<U> map (IFun<T,U> f){
        return new Mt<U>();
    }
}

class Cons<T> implements IList<T>{
    T first;
    IList<T> rest;
    Cons(T first, IList<T> rest){
        this.first = first;
        this.rest = rest;
    }

    public <U> IList<U> map(IFun<T,U>, f){
        return new Cons<U>(f.apply(this.first), this.rest.map(f));
    }

    // get runner name
    IList<Runner> names = this.runner map(new GetRunnerName());

}

class Integer {
    int value;
}

class Boolean {
    boolean value;
}

class getRunnerName implements IFun<Runner, String>{
    String apply(Runner r) {
        return r.name;
    }
}

interface Pred<T> {
    boolean test (T t);
}
// for map
interface IFun<S, T>{
    T apply(S s);
}

interface IFolder<T, U>{
    U folder(T elem, U accm);
}
// build in in java
interface IComparable<T>{
    int compare(<T> t1, <T> t2);
}

/*GENERICS - an abstraction over classes (MTof...)
you can put any type of objects in them. Have special syntax
 */
interface IList<T> {

}

class Cons<T> implements IList<T>{
    public <U> IList<U> map(IFun<T,U>, fun);// we havent define U yet so e have to include at the beginning

}
class StringLength implements IFun<String, Integer> {
    public Integer apply(String s) {
        return s.length();
    }
}

class Alogorithm{
    static <T,U> ArrayList<U> map(IFun<T,U> f, ArrayList<T> source) {
        if (source.length() == 0){
            return new ArrayList();
        }
        else return Alogorithm.mapHelp(f, source, dest, index)
    }
    // to return the dest
    // effect: to add the next result to dest and also all remaining
    static <T,U> ArrayList<U> mapHelp(IFun<T,U> f, ArrayList<T> source, ArrayList<U> dest){
        if (index == source.lenght()){
            return dest;
        }
        else dest(f.apply(source.get(index)));
        return mapHelp(f, source, index + 1);
    }
}

// new version of map
// put each loop in its own methods so you can test them
<T,U> map(IFun<T,U> f, ArrayList<T> lsit){
    ArrayList<U> dest = new ArrayList<U>(); // SETUP STEP

    for (T t: list){ // T - type, t - variable, list - a list to apply on
        dest.add(f.apply(t)); //UPDATE
        return dest;//RETURN
    }

    <T> ArrayList<T> filter(IPred<T> pred, ArrayList<T> src){
        ArrayList<T> dest = new ArrayList<T>();
        for(T t: src){ // for each T t  in source
            if (pred.test(t)){
                dest.add(t);
            }
            return dest;
        }
    }
    // ormap
    static <T> boolean ormap(IPred<T> pred, ArrayList<t> src){
        boolean val = false;
        // first way
        for(T t: src){
            if (pred.test(t)){
                val = true;
            }
            return val;
        }
        // second way - not identical: it will stop evaluating whenever it hits T
        // due to mutation
        val = val || pred.test(t);
    }

    // andmap
    static <T> boolean andmap(IPred<T> pred, ArrayList<t> src){
        boolean val = true;

        for(T t: src){
            if(pred.test(t)){ // if the test fails
                val = false;
            }
            return val;
        }
    }

    interface IComparator<T> {
        int compare(T t1, T t2);
    }

    class InteferComparator implements IComparator<Integer>{
        public int compare(Integer t1, Integer t2){
            return t1 - t2;
        }
    }

    static<T> T findMin(ArrayList<T> list, IComparator comp){
        if(list.size() == 0){
            throw new RuntimeException("empty");
        }
        T min = list.get(0); // the first of the list

        for(T t: list){
            if(comp.compare(t, min) < 0) { // if t < min
                min = t;
            }
        }
        return min;
    }

    // for loop
    static <T> T findmin (ArrayList<T> list, IComparator comp){
        if(list.size() == 0){
            throw new RuntimeException("empty");
        }
        T min = list.get(0); // get the first item
        for(int index = 0; // init (2) assumes we start from the beginning
                index < list.size() // (5) (8) termination condition. within 0 to lenght-1. check if we already finished
                index = index + 1) // (4) (7) update

            // body
            if (comp.compare(list.fet(index), min)) { //(3)
                min = list.get(index); // (6)
            }
        return min;
    }



    static <T> int findminstartinfrom (ArrayList<T> list, IComparator comp, int start){
        if(start < 0 || start >= list.size()){
            throw new RuntimeException("empty");
        }
        int indexMin = start;
        for(int index = start; // 
                index < list.size() // 
                index = index + 1) // 

            // body
            if (comp.compare(list.get(index), list.get(indexMin)) < 0) { //
                indexMin = index; // 
            }
    }
    return indexMin;

    
    // sort
    static void sort(ArrayList<T> list, IComporator comp){
        // no setup because we are not returning anything but void
        for(int index = 0;
                index < list.size();
                index = index + 1){
            int findstartingfrom(list, comp, index);
            list.swap(index, minIndex);
        }
    }
}




class Examples{    
    IList<String> los = new Cons<String>("hello", new Cons<String>("world", new Mt<string>));
    los.map(new StringLength())

    Runner ann = new Runner(...);
    Runner alice = new Runner(...);
    Runner bob = new Runner(...);
    IList<Runner> runners = new Cons<Runner>(this.alice, new Cons<Runner>(this.bob, new Mt<Runner>()));

}









