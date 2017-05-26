import java.util.*;

import tester.Tester;

class Decode{
    public void allSingles(ArrayList<String> letter){
        for(int index = 0; index < letter.size(); index++){
            if (letter.get(index) == null){
                letter.set(index, " ");
            }
            else if (letter.get(index).length() > 1){
                letter.set(index, letter.get(index).substring(0, 1));
            }
        }
    }

}
abstract class AHT {
    int freq;
    AHT(int freq){
        this.freq = freq;
    }
    public abstract boolean isLeaf();
    public abstract boolean simpleTree();
    public abstract String whereTo(ArrayList<Boolean> list);
    public abstract String getName();
    public abstract String whereToHelper(ArrayList<Boolean> list, int index);
    public abstract boolean validTree();
}


class LFreq extends AHT{

    String s;
    LFreq(int freq, String s){
        super(freq);
        this.s = s;
    }
    public boolean equals(Object obj){
        if (!(obj instanceof LFreq)){
            return false;
        }
        LFreq a = (LFreq) obj; 
        return a.s.equals(this.s); 
    }

    public boolean isLeaf(){
        return true;

    }

    public boolean simpleTree(){
        return false;
    }

    public String getName(){
        return this.s;
    }
    public String whereTo(ArrayList<Boolean> list){
        throw new RuntimeException("invalid path");
    }
    public String whereToHelper(ArrayList<Boolean> list, int index){
        throw new RuntimeException("invalid path(helper)");
    }
    
    public boolean validTree(){
        return true;
    }
}

class Node extends AHT{
    AHT left;
    AHT right;
    Node(int freq, AHT left, AHT right){
        super(freq);
        this.left = left;
        this.right = right;
    }
    public boolean isLeaf(){
        return false;
    }

    public boolean simpleTree(){
        return this.left.isLeaf() && this.right.isLeaf();
    }
    public String getName(){
        throw new RuntimeException("not a leaf");
    }

    public String whereTo(ArrayList<Boolean> list){
        String a = "";

        if (this.simpleTree() && list.size() > 1){ 
            throw new RuntimeException ("not found");
        }
        else if (!this.simpleTree() && list.size() == 1){ 
            throw new RuntimeException ("not found");
        }
        else return this.whereToHelper(list, 0);
    }


    public String whereToHelper(ArrayList<Boolean> list, int index) {
        if (this.simpleTree() && index == list.size() - 1) {
            if(list.get(index)) { // left
                return this.left.getName();
            }
            else { // right
                return this.right.getName();
            }
        }
        else if (!list.get(index)) {
            return this.right.whereToHelper(list, index + 1);
        }
        else { 
            return this.left.whereToHelper(list, index + 1);
        }
    }
    public boolean validTree(){
            return (this.freq == this.left.freq + this.right.freq) &&
                    this.left.validTree() && this.right.validTree();
    }
}

class Frequency extends HashMap<String, Integer>{
    HashMap<String, Integer> f;


    public void computeHistogram(ArrayList<String> list){
        Decode d = new Decode();
        d.allSingles(list);
        for(int index = 0; index < list.size(); index++){
            if (!this.containsKey(list.get(index))){
                this.put(list.get(index), 1);
            }
            else this.put(list.get(index), this.get(list.get(index)) + 1);

        } 
    }
}

class ByFreq implements Comparator<LFreq>{
    public int compare (LFreq n, LFreq m){
        return n.freq - m.freq;
    }
}






class ExamplesDecode{
    Decode d = new Decode();
    ArrayList<String> a1;
    ArrayList<String> a2;

    AHT t = new LFreq(3, "t");
    AHT a = new LFreq(3, "a");
    AHT c = new LFreq(1, "c");
    AHT b = new LFreq(1, "b");
    AHT sp = new LFreq(2, " ");

    AHT n2 = new Node(2, c, b);
    AHT n6 = new Node(6, t, a);
    AHT n4 = new Node(4, n2, sp);
    AHT n10 = new Node(10, n6, n4);


    AHT r = new LFreq(2, "r");
    AHT h = new LFreq(2, "h");
    AHT e = new LFreq(4, "t");
    AHT t1 = new LFreq(1, "t");
    AHT sp1 = new LFreq(1, " ");

    AHT n4a = new Node(4, r, h);
    AHT n2a = new Node(2, t1, sp1);
    AHT n6a = new Node(7, e, n2a);
    AHT n10a = new Node(10, n4a, n6a);

    ArrayList<Boolean> list1 = new ArrayList<Boolean>(Arrays.asList(true));
    ArrayList<Boolean> list2 = new ArrayList<Boolean>(Arrays.asList(true, true));


    void init(){
        a1 = new ArrayList<String>();
        a2 = new ArrayList<String>();
        this.a1.add("fly");
        this.a1.add("window");
        this.a1.add(null);
        this.a1.add("elephant");
        this.a1.add("ella");
        this.a1.add("food");
        this.a1.add("tree");
        this.a1.add("fundies");
        this.a1.add("tomorrow");
        this.a1.add("when");

        this.a2.add("go");
        this.a2.add("on");
        this.a2.add("and");
        this.a2.add("do");
        this.a2.add("great");
        this.a2.add("things");

    }

    void testAll(Tester t){
        init();
        this.d.allSingles(a1);
        t.checkExpect(a1.get(0), "f");
        t.checkExpect(a1.get(1), "w");
        t.checkExpect(a1.get(2), " ");
        t.checkExpect(a1.get(3), "e");

        Frequency f = new Frequency();
        f.computeHistogram(a2);
        //System.out.println(f.get("g"));
        t.checkExpect(f.get("g"), 2);
        t.checkExpect(f.get("o"), 1);
        t.checkExpect(f.get("d"), 1);
        t.checkExpect(f.get("t"), 1);
        t.checkExpect(this.n6.whereTo(list1), "t");
        t.checkExpect(this.n10.whereTo(list2), "t");
        t.checkException("e1", new RuntimeException("not found"), this.n6, "whereTo", list2);
        t.checkException("e2", new RuntimeException("not found"), this.n10, "whereTo", list1);
        t.checkExpect(this.n10.validTree(), true);
        t.checkExpect(this.n10a.validTree(), false);

    }
}















