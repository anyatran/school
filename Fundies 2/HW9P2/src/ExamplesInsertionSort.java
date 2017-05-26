// assignment 9
// Tran, Anh
// anhtran9
// Stephanie Nguyen
// snguyen

import java.util.*;

import tester.Tester;
//compare string s lexicographically
class ByString implements Comparator<String> {
    public int compare(String t1, String t2) {
        return t1.compareTo(t2);
    }
}


//compare numbers by its magnitude
class ByInt implements Comparator<Integer> {
    public int compare(Integer t1, Integer t2) {
        return t1 - t2;
    }
}


public class ExamplesInsertionSort {

    // SortedInsert
    // adds given element of type T into the sorted ArrayList
    public <T> void sortedInsert(T t, ArrayList<T> list, Comparator<T> pred) {

        for (int index = 0; index < list.size(); index++) {
            if (list.size() == 0) {
                list.add(t);
            }
            // if t is before this
            else if (pred.compare(t, list.get(index)) <= 0) { 
                list.add(index, t);
                break;
            }

            else if (index == list.size() - 1 ) {
                list.add(t);
                break;
            }
            System.out.print(list);
        }
    }


    // InsertSort
    // produces a new sorted ArrayList from the given unsorted ArrayList
    public <T> ArrayList<T> insertSort(ArrayList<T> list, Comparator<T> pred) {
        ArrayList<T> result = new ArrayList<T>();

        for (T t: list) {
            if (result.size() == 0) {
                result.add(t);
            }
            else {
                this.sortedInsert(t, result, pred);
            }
        }
        return result;


    }


    // InsertionSort
    // mutates given ArrayList so it is in sorted order
    public <T> void insertionSort(ArrayList<T> list, Comparator<T> pred) {
        for (int index = 1; index < list.size(); index++) {

            // if item at i goes before the item at index, 
            // remove it from where it is and place it before the item.
            for (int i = 0; i < list.size(); i++) {
                if (pred.compare(list.get(index), list.get(i)) <= 0) { 
                    list.add(i, list.remove(index));
                }
            }
        }




    }





    ArrayList<String> stringsorted = new ArrayList<String>();
    ArrayList<String> stringsorted2 = new ArrayList<String>();
    ArrayList<String> stringsorted3 = new ArrayList<String>();
    ArrayList<String> stringUnsorted = new ArrayList<String>();
    ArrayList<String> stringUnsorted2 = new ArrayList<String>();


    ArrayList<Integer> intsorted = new ArrayList<Integer>();
    ArrayList<Integer> intsorted2 = new ArrayList<Integer>();
    ArrayList<Integer> intsorted3 = new ArrayList<Integer>();
    ArrayList<Integer> intUnsorted = new ArrayList<Integer>();
    ArrayList<Integer> intUnsorted2 = new ArrayList<Integer>();

    ExamplesInsertionSort algstring;
    ExamplesInsertionSort algint;

    Comparator<String> forstring = new ByString();
    Comparator<Integer> forint = new ByInt();

    public void init() {
        algstring = new ExamplesInsertionSort();
        algint = new ExamplesInsertionSort();

        this.stringsorted.clear();
        this.stringsorted.add("b");
        this.stringsorted.add("c");
        this.stringsorted.add("d");


        this.stringUnsorted.clear();
        this.stringUnsorted.add("d");
        this.stringUnsorted.add("a");
        this.stringUnsorted.add("b");
        this.stringUnsorted.add("g");
        this.stringUnsorted.add("e");

        this.stringsorted3.clear();
        this.stringsorted3.add("a");
        this.stringsorted3.add("b");
        this.stringsorted3.add("d");
        this.stringsorted3.add("e");
        this.stringsorted3.add("g");

        this.stringUnsorted2.clear();
        this.stringUnsorted2.add("m");
        this.stringUnsorted2.add("b");
        this.stringUnsorted2.add("c");
        this.stringUnsorted2.add("a");


        this.stringsorted2.clear();
        this.stringsorted2.add("a");
        this.stringsorted2.add("b");
        this.stringsorted2.add("c");
        this.stringsorted2.add("m");

        this.intsorted.clear();
        this.intsorted.add(1);
        this.intsorted.add(2);
        this.intsorted.add(5);
        this.intsorted.add(7);

        this.intUnsorted.clear();
        this.intUnsorted.add(4);
        this.intUnsorted.add(2);
        this.intUnsorted.add(7);
        this.intUnsorted.add(3);

        this.intsorted2.clear();
        this.intsorted2.add(2);
        this.intsorted2.add(3);
        this.intsorted2.add(4);
        this.intsorted2.add(7);

        this.intUnsorted2.clear();
        this.intUnsorted2.add(20);
        this.intUnsorted2.add(1);
        this.intUnsorted2.add(1);
        this.intUnsorted2.add(8);
        this.intUnsorted2.add(5);
        this.intUnsorted2.add(22);
        this.intUnsorted2.add(102);
        this.intUnsorted2.add(30);

        this.intsorted3.clear();
        this.intsorted3.add(1);
        this.intsorted3.add(1);
        this.intsorted3.add(5);
        this.intsorted3.add(8);
        this.intsorted3.add(20);
        this.intsorted3.add(22);
        this.intsorted3.add(30);
        this.intsorted3.add(102);



    }


    public void testArray(Tester t) {
        init();

        this.algstring.sortedInsert("a", this.stringsorted, this.forstring);
        t.checkExpect(this.stringsorted.size(), 4);
        t.checkExpect(this.stringsorted.get(1), "b");

        this.algint.sortedInsert(4, this.intsorted, this.forint);
        t.checkExpect(this.intsorted.size(), 5);
        t.checkExpect(this.intsorted.get(2), 4);


        t.checkExpect(this.algstring.insertSort(this.stringUnsorted, 
                this.forstring), this.stringsorted3);
        t.checkExpect(this.algint.insertSort(this.intUnsorted, this.forint),
                this.intsorted2); 

        this.algstring.insertionSort(stringUnsorted2, this.forstring);
        t.checkExpect(this.stringUnsorted2, this.stringsorted2);
        t.checkExpect(this.stringUnsorted2.get(1), "b");
        t.checkExpect(this.stringUnsorted2.get(3), "m");



        this.algint.insertionSort(this.intUnsorted2, this.forint);
        t.checkExpect(this.intUnsorted2, this.intsorted3);
        t.checkExpect(this.intUnsorted2.get(4), 20);
        t.checkExpect(this.intUnsorted2.get(0), 1);
        t.checkExpect(this.intUnsorted2.get(7), 102);


    }


}











