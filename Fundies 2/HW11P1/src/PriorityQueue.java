import java.util.*;


// represents the Priority Queue
/*
 TEmplate:
 this.alist        -- ArrayList<T>
 this.pred         -- Comparator<T>
 
 Methods:
 this.isLeaf(int)  -- boolean
 this.higherPriorityChild(int) -- int
 this.insert(T)    -- void
 this.remove()     -- T
 
 */
class PriorityQueue<T> {
    ArrayList<T> alist;
    Comparator<T> pred;
    PriorityQueue(Comparator<T> pred) {
        this.alist = new ArrayList<T>();
        this.alist.add(null);
        this.pred = pred;
    }

    // checks if the node is a leaf
    public boolean isLeaf(int label) {
        if (label > this.alist.size() - 1) {
            throw new RuntimeException("the node is not found");
        }
        else {
            return (label * 2) > this.alist.size() - 1;

        }

    }
    // chose a child with a higher priority
    public int higherPriorityChild(int label) {

        if (this.isLeaf(label)) {
            throw new RuntimeException("not applicable for a leaf");
        }
        else if ((label * 2 + 1) > (this.alist.size() - 1)) {
            return (label * 2);
        }
        else if (this.pred.compare(this.alist.get(label * 2),
                this.alist.get(label * 2 + 1)) >= 0) {
            return (label * 2);
        }
        else {
            return (label * 2 + 1);
        }
    }

    // insert the node into the queue
    public void insert(T t) {
        this.alist.add(t);
        int tloc = this.alist.size() - 1;
        int k = tloc / 2;
        while ((k >= 1) &&
                (this.pred.compare(t, this.alist.get(k)) > 0)) {
            Collections.swap(this.alist, tloc, k);
            tloc = k;
            k = k / 2;

        }
    }


    // remove the highers priority node 
    public T remove() {
        T removed = this.alist.get(1);
        T last = this.alist.get(this.alist.size() - 1);
        int k = 1;
        this.alist.set(1, last);
        this.alist.remove(this.alist.size() - 1);

        if (alist.size() <= 2) {
            return removed;
        }
        while (!this.isLeaf(k)) {
            int ck = this.higherPriorityChild(k);
            if (this.pred.compare(this.alist.get(ck), this.alist.get(k)) > 0) {
                Collections.swap(this.alist, ck, k);
                k = ck; 
            }
            else {
                break;
            }
        }
        return removed;
    }
}


// compares nodes by its values
class ByInt implements Comparator<Integer> {
    public int compare(Integer n1, Integer n2) {
        return n1 - n2;
    }
}











