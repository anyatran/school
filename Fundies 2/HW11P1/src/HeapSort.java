import java.util.*;

class Heapsort<T> {
    public ArrayList<T> heapsort(ArrayList<T> alist, Comparator<T> comp) {
        ArrayList<T> result = new ArrayList<T>();
        
        PriorityQueue<T> p = new PriorityQueue<T>(comp);

        for (T t: alist) {
            p.insert(t);
            
        }
        while (p.alist.size() > 1) {
            result.add(p.remove());
        }
        return result;
    }
}


