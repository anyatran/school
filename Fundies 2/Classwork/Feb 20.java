interface IDequeStrings{
    int indexOf(String needle);
    void insert(int index, String val);
    void sort();
    int length();
    String concat();
    void append(IDequeStrings dq2);
    String remove(int index);
    String set(int index, String val);
    String get(int index);
    void swap(int from, int to);
}

// Array<String>: class 
// we have positional access/ direct access
// not just first and rest, but alsto tenth...


// implement indexOf for a sorted list by using binary search
//int indexOf(String needle) - want
int indexOf(String needle, int low, int high){ //- helper; [low, high)
    if (low == high){
        return -1; // we didn't find anything
    }
    else {
        int midIndx = ((low + high) / 2); //   ?? if 2.5
        int comp = needle.compareTo(this.get(midIndx));
        if (comp == 0) {
            return midIndx;
        }
        else if (compe < 0) {
            return this.indexOf(needle, low, high);
        }
        else return this.indexOf(needle, midIndx + 1, high);

    }

    int indexOf(String needle){
        return this.indexOf(needle, 0, this.length());
    }
    
    void swap(int from, int to){
        //String curFrom = this.get(from);
        //String curTo = this.get(to);
        this.set(to, this.set(from, curTo), this.get(to));
        //this.set(to, curFrom);
    }
    
    Deque makeD(){
        Sentinel s = new Sentilen;
        Node a = new Node 
    }
    
    
    
    



