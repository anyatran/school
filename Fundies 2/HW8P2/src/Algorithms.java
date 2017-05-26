import java.util.ArrayList;
import java.util.Comparator;


/* Template
 class algorithms
 this.binarySearchHelper(T, ArrayList<T>, Comparator<T>, int, int) --int
 this.binarysearch(T, ArrayList<T>, Comparator<T>)                 --int
 this.comparestrings(string, string) --int
 this.compareintegers(int, int)      --int
 */

class Algorithms {
    <T> int binSearchHelper(T t, ArrayList<T> list, Comparator<T> pred, 
            int lower, int upper ) {
        int midpoint = (upper + lower) / 2;
        if (list.size() == 0) {
            return -1;
        }
        else if ((upper - lower == 0) && 
                (pred.compare(t, list.get(upper)) == 0)) {
            return upper;
        }
        else if ((upper - lower == 0) && 
                (pred.compare(t, list.get(upper)) != 0)) {
            return -1;
        }
        else if (pred.compare(t, list.get(midpoint)) < 0) {
            return this.binSearchHelper(t, list, pred, lower, midpoint - 1);
        }
        else if (pred.compare(t, list.get(midpoint)) > 0) {
            return this.binSearchHelper(t, list, pred, midpoint + 1, upper);
        }
        else {
            return midpoint;      
        }
    }    

    <T> int binSearch(T t, ArrayList<T> list, Comparator<T> pred) {
        return this.binSearchHelper(t, list, pred, 0, list.size() - 1);
    }
}


//comparator
//negative = before, positive = after
class CompareStrings implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
//
class CompareIntegers implements Comparator<Integer> {
    public int compare(Integer i1, Integer i2 ) {
        return i1 - i2;
    }
}