import java.util.ArrayList;
import java.util.Comparator;

import tester.Tester;

class ExamplesAlgorithms {
    ExamplesAlgorithms() { 
        //empty because it takes nothing
    }
    
    
    ArrayList<String> los1 = new ArrayList<String>();
    ArrayList<String> los2 = new ArrayList<String>();
    ArrayList<Integer> loi1 = new ArrayList<Integer>();
    
    Algorithms a1 = new Algorithms();
    Algorithms a2 = new Algorithms();
    
    Comparator<Integer> compInt = new CompareIntegers();
    Comparator<String> compSt = new CompareStrings();
    
    
    void initData() {
        this.loi1.add(2);
        this.loi1.add(5);
        this.loi1.add(8);
        this.loi1.add(19);
    }
    
    
    
    void initString() {
        this.los1.add("apple");
        this.los1.add("hello");
        this.los1.add("lamp");
        this.los1.add("orange");
    }
    
    void testArrayList(Tester t) {
        this.loi1.clear();
        t.checkExpect(this.loi1.size(), 0);
        this.initData();
        t.checkExpect(this.loi1.size(), 4);
        t.checkExpect(loi1.get(3), 19);
        t.checkExpect(this.los2.size(), 0);
        
        this.los1.clear();
        t.checkExpect(this.los1.size(), 0);
        this.initString();
        t.checkExpect(this.los1.size(), 4);
    }
    
    void testHelper(Tester t) {
        initData();
        initString();
        t.checkExpect(this.a2.binSearchHelper(2, loi1, this.compInt, 0, 0), 0);
        t.checkExpect(this.a2.binSearchHelper(2, loi1, this.compInt, 0, 3), 0);
        t.checkExpect(this.a2.binSearchHelper(5, loi1, this.compInt, 0, 2), 1);
        t.checkExpect(this.a2.binSearchHelper(2, loi1, this.compInt, 0, 2), 0);
        t.checkExpect(this.a2.binSearchHelper(
                0, loi1, this.compInt, 0, 3), -1);
        t.checkExpect(this.a2.binSearchHelper(1, loi1, this.compInt, 1, 1), -1);
        t.checkExpect(this.a2.binSearchHelper(5, loi1, this.compInt, 0, 3), 1);
        t.checkExpect(this.a2.binSearchHelper(8, loi1, this.compInt, 0, 3), 2);
        t.checkExpect(this.a2.binSearchHelper(5, loi1, this.compInt, 0, 0), -1);
        t.checkExpect(this.a2.binSearchHelper(19, loi1, this.compInt, 0, 3), 3);
        t.checkExpect(this.a2.binSearchHelper(
                19, loi1, this.compInt, 0, 2), -1);
        t.checkExpect(this.a2.binSearchHelper(
                19, loi1, this.compInt, 0, 5), -1);
        t.checkExpect(this.a2.binSearchHelper(
                11, loi1, this.compInt, 0, 3), -1);
        t.checkExpect(this.a2.binSearchHelper(
                22, loi1, this.compInt, 0, 3), -1);
        t.checkExpect(this.a2.binSearchHelper(
                19, loi1, this.compInt, 0, 3), 3);
        t.checkExpect(this.a1.binSearchHelper(
                "w", los1, this.compSt, 0, 3), -1);
        t.checkExpect(this.a1.binSearchHelper(
                "hello", los1, this.compSt, 0, 3), 1);
        t.checkExpect(this.a1.binSearchHelper(
                "air", los1, this.compSt, 0, 3), -1);
        t.checkExpect(this.a1.binSearchHelper(
                "lamp", los1, this.compSt, 0, 3), 2);
        t.checkExpect(this.a1.binSearchHelper(
                "orange", los1, this.compSt, 0, 3), 3);
        t.checkExpect(this.a1.binSearchHelper(
                "orange", los2, this.compSt, 0, 3), -1);
        
        t.checkExpect(this.a2.binSearch(2, loi1, this.compInt), 0);
        t.checkExpect(this.a2.binSearch(5, loi1, this.compInt), 1);
        t.checkExpect(this.a2.binSearch(8, loi1, this.compInt), 2);
        t.checkExpect(this.a2.binSearch(19, loi1, this.compInt), 3);
        t.checkExpect(this.a2.binSearch(11, loi1, this.compInt), -1);
        t.checkExpect(this.a2.binSearch(22, loi1, this.compInt), -1);
        t.checkExpect(this.a1.binSearch("w", los1, this.compSt), -1); 
        t.checkExpect(this.a1.binSearch("apple", los1, this.compSt), 0);
        t.checkExpect(this.a1.binSearch("hello", los1, this.compSt), 1);
        t.checkExpect(this.a1.binSearch("lamp", los1, this.compSt), 2);
        t.checkExpect(this.a1.binSearch("air", los1, this.compSt), -1);
        t.checkExpect(this.a1.binSearch("orange", los1, this.compSt), 3);
        t.checkExpect(this.a1.binSearch(
                "orange", los1, this.compSt), 3);
        
    }
    
}











