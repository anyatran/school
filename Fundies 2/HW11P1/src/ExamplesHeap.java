import java.util.ArrayList;
import java.util.Comparator;

import tester.Tester;

public class ExamplesHeap { 

    ArrayList<Integer> a;
    Comparator<Integer> pred = new ByInt();
    PriorityQueue<Integer> p1;
    PriorityQueue<Integer> p2;
    PriorityQueue<Integer> p3;
    PriorityQueue<Integer> p4;
    PriorityQueue<Integer> p5;
    Heapsort<Integer> hs = new Heapsort<Integer>();

    public void initHeap() {

        a = new ArrayList<Integer>();

        p1 = new PriorityQueue<Integer>(pred);
        p2 = new PriorityQueue<Integer>(pred);
        p3 = new PriorityQueue<Integer>(pred);
        p4 = new PriorityQueue<Integer>(pred);
        p5 = new PriorityQueue<Integer>(pred);


        p1.alist.add(80);
        p1.alist.add(50);
        p1.alist.add(40);
        p1.alist.add(45);
        p1.alist.add(20);
        p1.alist.add(30);

        p2.alist.add(50);
        p2.alist.add(45);
        p2.alist.add(40);
        p2.alist.add(30);
        p2.alist.add(20);


        p3.alist.add(70);
        p3.alist.add(45);
        p3.alist.add(50);
        p3.alist.add(30);
        p3.alist.add(20);
        p3.alist.add(40);



        p5.alist.add(80);
        p5.alist.add(30);

        this.a.add(30);
        this.a.add(20);
        this.a.add(60);
        this.a.add(10);
        this.a.add(40);
        this.a.add(20);

    }

    public void testIsLeaf(Tester t) {
        initHeap();
        t.checkExpect(this.p1.alist.size(), 7);
        t.checkExpect(this.p2.alist.size(), 6);
        t.checkExpect(this.p1.isLeaf(4), true);
        t.checkExpect(this.p5.isLeaf(1), false);
        t.checkExpect(this.p1.isLeaf(3), false);
        t.checkExpect(this.p1.alist.size(), 7);
        t.checkException("not found", 
                new RuntimeException("the node is not found"),
                this.p1, "isLeaf", 9);
        t.checkExpect(this.p1.higherPriorityChild(3), 6);
        t.checkExpect(this.p1.higherPriorityChild(1), 2);
        t.checkExpect(this.p3.higherPriorityChild(1), 3);
        t.checkException("not aplicable", 
                new RuntimeException("not applicable for a leaf"),
                this.p1, "higherPriorityChild", 4);

    }

    public void testInsert(Tester t) {
        initHeap();
        this.p2.insert(80);
        this.p2.insert(60);
        this.p2.insert(10);
        this.p4.insert(4);
        t.checkExpect(this.p2.alist.size(), 9);
        t.checkExpect(this.p2.alist.get(8), 10);
        t.checkExpect(this.p2.alist.get(7), 50);
        t.checkExpect(this.p2.alist.get(6), 40); 
        t.checkExpect(this.p2.alist.get(3), 60); 
        t.checkExpect(this.p2.alist.get(1), 80);
        t.checkExpect(this.p4.alist.get(1), 4);

    }

    public void testRemove(Tester t) {
        initHeap();
        this.p3.remove();
        this.p5.remove();
        this.p5.remove();
        this.p1.remove();

        t.checkExpect(this.p3.alist.get(1), 50);
        t.checkExpect(this.p3.alist.get(2), 45);
        t.checkExpect(this.p3.alist.get(3), 40);
        t.checkExpect(this.p3.alist.get(4), 30);
        t.checkExpect(this.p3.alist.get(5), 20);
        t.checkExpect(this.p3.alist.size(), 6);


        t.checkExpect(this.p2.remove(), 50);
        t.checkExpect(this.p5.alist.size(), 1);

        t.checkExpect(this.p1.alist.get(1), 50);
        t.checkExpect(this.p1.alist.get(2), 45);
        t.checkExpect(this.p1.alist.get(3), 40);
        t.checkExpect(this.p1.alist.get(4), 30);
        t.checkExpect(this.p1.alist.get(5), 20);
        t.checkExpect(this.p1.alist.size(), 6);

    }

    public void testHeapSort(Tester t) {
        initHeap();
        t.checkExpect(a.size(), 6);
        t.checkExpect(hs.heapsort(a, pred).get(0), 60);
        t.checkExpect(hs.heapsort(a, pred).get(1), 40);
        t.checkExpect(hs.heapsort(a, pred).get(2), 30);
        t.checkExpect(hs.heapsort(a, pred).get(3), 20);
        t.checkExpect(hs.heapsort(a, pred).get(4), 20);
        t.checkExpect(hs.heapsort(a, pred).get(5), 10);
    }
}









