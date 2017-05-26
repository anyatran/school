
/**
 * Represents a predicate for the chosen data type
 * @author Viera K. Proulx
 */
interface ISelect<T>{
    /**
     * a predicate that determines whether the given item
     * has the desired property 
     * @param t the given item
     * @return true if the given item has the desired property 
     */
    public boolean select(T t);
}

class FilterShort implements ISelect<String>{
    public boolean select(String s){
        return s.length() < 4;
    }
}

class FilterAs implements ISelect<String>{
    public boolean select(String s){
        return s.substring(0, 1).equals("a");
    }
}