import java.util.Comparator;

import tester.Tester;
/*
 Tran, Anh
 anhtran9

 Krutiansky, Brett
 brekru

 */
// represent a Deque

/*Template:
 Fields:
 this.node                                    -- Sentinel

 Methods:
 this.size()                                  -- int
 this.addAtHead()                             -- void
 this.addAtTail()                             -- void
 this.removeFromHead()                        -- void
 this.removeFromTail()                        -- void
 this.find(String)                            -- Node
 this.removeNode(Node)                        -- Deque
 this.insertSorted(Comparator, T)             -- void
 this.removeSorted(comparator, T)             -- void



 */
class Deque<T> {
    Sentinel<T> sentinel;
    Deque() {
        //has to be empty
    }


    // calculate the size of the Deque
    int size() {
        if (this.sentinel == null) {
            return 0;
        }
        else {
            return this.sentinel.size();
        }
    }

    // add a Node at the beginning of this Deque
    void addAtHead(T s) {
        Node<T> n = new Node<T>(s);

        if (this.sentinel == null) {
            this.sentinel = new Sentinel<T>();
            this.sentinel.next = n;
            this.sentinel.prev = n;
            n.next = this.sentinel;
            n.prev = this.sentinel;
        }
        else {
            n.prev = this.sentinel;
            n.next = this.sentinel.next;
            this.sentinel.next.prev = n;
            this.sentinel.next = n;
        }
    }
    // add a Node at the end of this Deque
    void addAtTail(T s) {
        Node<T> n = new Node<T>(s);

        if (this.sentinel == null) {
            this.sentinel = new Sentinel<T>();
            this.sentinel.next = n;
            this.sentinel.prev = n;
            n.next = this.sentinel;
            n.prev = this.sentinel;
        }
        else {
            n.next = this.sentinel;
            n.prev = this.sentinel.prev;
            n.prev.next = n;
            this.sentinel.prev = n;
        }
    }
    // remove a Node from the beginning of this Deque
    void removeFromHead() {
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
    }
    // remove a Node from the end of this Deque
    void removeFromTail() {
        this.sentinel.prev.prev.next = this.sentinel;
        this.sentinel.prev = this.sentinel.prev.prev;
    }
    //find a Node with a given String in this Deque
    Node<T> find(T s) {
        return this.sentinel.find(s); 
    }
    // remove a given Node from this Deque
    void removeNode(Node<T> n) {
        if (!(this.sentinel.find(n.data).isSentinel() || n.isSentinel())) {

            n.prev.next = n.next;
            n.next.prev = n.prev;
            n.next = null;
            n.prev = null;

        }
    }

    // insert the node in the right position and updates the deque
    void insertSorted(T t, Comparator<T> pred) {
        if (this.sentinel == null) {
            this.sentinel = new Sentinel<T>();
            this.addAtHead(t);
        }
        else if (this.sentinel.next.isSentinel() ||
                (pred.compare(t, this.sentinel.next.data) < 0)) {
            this.addAtHead(t);
        }
        else {
            this.sentinel.next.insertSorted(t, pred);
        }
    }

    // remove the given node and update the deque
    void removeSorted(T t, Comparator<T> pred) {
        if (this.sentinel.next.isSentinel()) {
            throw new RuntimeException("Item " + t.toString() + 
                    " is not in this queue");
        }
        else {
            this.sentinel.next.removeSorted(t, pred);
        }
    }
}

// represent a Node
/*Template:
 Fields:
 this.data                            -- Stirng
 this.next                            -- Node
 this.prev                            -- Node

 Methods:                     
 this.isSentinel()                    -- Boolean
 this.size()                          -- int
 this.find(String)                    -- Node

 Methods of fields:
 this.prev.isSentinel()               -- Boolean
 this.next.isSentinel()               -- Boolean
 this.next.size()                     -- int
 this.next.find(String)               -- Node
 this.insertSorted(Comparator, T)     -- void
 this.removeSorted(comparator, T)     -- void


 */
class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;


    Node(T data) {
        this.data = data;   
    }


    // checks if this node is a sentinel
    boolean isSentinel() {
        return false;
    } 

    // calculate the size of the sentinel
    int size() {
        if (this.prev.isSentinel() && this.next.isSentinel()) {
            return 1;
        }
        else if (this.prev.isSentinel()) {
            return 1 + this.next.size();
        }
        else if (this.next.isSentinel()) {
            return 1;
        }
        else {
            return 1 + this.next.size();
        }

    }
    //find a Node with a given string
    Node<T> find(T s) {
        if (s.equals(this.data)) {
            return this;
        }
        else if (!s.equals(this.data) && this.next.isSentinel()) {
            return this.next;
        }
        else {
            return this.next.find(s);
        }
    }

    //insert the node
    void insertSorted(T t, Comparator<T> pred) {
        Node<T> n = new Node<T>(t);
        if (pred.compare(t, this.data) <= 0) {
            this.prev.next = n;
            this.prev = n;
            n.prev = this.prev;
            n.next = this;

        }
        else {
            this.next.insertSorted(t, pred);
        }
    }

    // remove the node 
    void removeSorted(T t, Comparator<T> pred) {
        if (this.isSentinel()) {
            throw new RuntimeException("Item " + t.toString() + 
                    " is not in this queue");
        }
        else if (pred.compare(t, this.data) == 0) {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.next = null;
            this.prev = null;
        }
        else {
            this.next.removeSorted(t, pred);
        }
    }

}

// represent a sentinel
/*Template:
 Fields:
 this.next              -- Node
 this.prev              -- Node

   Methods:
   this.isSentinel      -- Boolean
   this.size            -- int
   this.find            -- Node

   Methods for Fields:
   this.next.size()     -- int
   this.next.find()     -- Node
 */
class Sentinel<T> extends Node<T> {

    Sentinel() {
        super(null);
        this.next = this;
        this.prev = this;
    }

    // checks if its a sentinel
    boolean isSentinel() {
        return true;
    }

    // claculate the size of a sentinel
    int size() {

        if (this.next.isSentinel() && this.prev.isSentinel()) {
            return 0;
        }
        else {
            return this.next.size();
        }
    }

    //find a node with a given string
    Node<T> find(T s) {
        if (this.next.isSentinel()) {
            return this;
        }
        else {
            return this.next.find(s);
        }
    }


}

// represents a person
class Person {
    String name;
    String from;
    int age;
    Person(String name, String from, int age) {
        this.name = name;
        this.from = from;
        this.age = age;
    }

}

// compares strings lexicographically
class ByString implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }

}

// compare person's age
class ByPerson implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return p1.age - p2.age;
    }
}


class ExamplesDeque {
    Person ann = new Person("Ann", "DC", 21);
    Person emma = new Person("Emma", "CA", 24);
    Person dave = new Person("Dave", "MA", 25);
    Person nick = new Person("Nick", "NY", 29);
    Person mike = new Person("Mike", "CN", 25);
    Person grace = new Person("Grace", "TX", 30);

    Sentinel<String> a1;
    Sentinel<String> mtsentinel;
    Node<String> abcnode;
    Node<String> bcdnode;
    Node<String> cdfnode;
    Node<String> defnode;
    Node<String> danode;
    Node<String> g;
    Sentinel<String> mtg;
    Sentinel<String> mt1;

    Deque<String> d1;
    Deque<String> mtdeque;
    Deque<String> d2;
    Deque<Person> dnull;


    Sentinel<Person> a2;
    Sentinel<Person> mtp;
    Node<Person> annnode;
    Node<Person> emmanode;
    Node<Person> davenode;
    Node<Person> nicknode;
    Node<Person> mikenode;

    Deque<Person> dp1;

    Comparator<String> bystring = new ByString();
    Comparator<Person> byperson = new ByPerson();



    void init() {
        a1 = new Sentinel<String>();
        mtsentinel = new Sentinel<String>();
        mtg = new Sentinel<String>();
        mt1 = new Sentinel<String>();
        d1 = new Deque<String>();
        mtdeque = new Deque<String>();
        d2 = new Deque<String>();
        dp1 = new Deque<Person>();
        dnull = new Deque<Person>();

        abcnode = new Node<String>("abc");
        bcdnode = new Node<String>("bcd");
        cdfnode = new Node<String>("cdf");
        defnode = new Node<String>("def");
        danode = new Node<String>("da");
        g = new Node<String>("g");

        this.a1.next = this.abcnode;
        this.abcnode.next = this.bcdnode;
        this.bcdnode.next = this.cdfnode;
        this.cdfnode.next = this.defnode;
        this.defnode.next = this.a1;
        this.a1.prev = this.defnode;
        this.defnode.prev = this.cdfnode;
        this.cdfnode.prev = this.bcdnode;
        this.bcdnode.prev = this.abcnode;
        this.abcnode.prev = this.a1;
        this.d1.sentinel = this.a1;

        this.mtg.next = this.g;
        this.g.prev = this.mtg;
        this.mtg.prev = this.g;
        this.g.next = this.mtg;
        this.d2.sentinel = this.mtg;

        this.mt1.next = this.mt1;
        this.mt1.prev = this.mt1;


        this.mtsentinel.next = this.mtsentinel;
        this.mtsentinel.prev = this.mtsentinel;
        this.mtdeque.sentinel  = this.mtsentinel;



        a2 = new Sentinel<Person>();
        mtp = new Sentinel<Person>();
        annnode = new Node<Person>(ann);
        emmanode = new Node<Person>(emma);
        davenode = new Node<Person>(dave);
        nicknode = new Node<Person>(nick);
        mikenode = new Node<Person>(mike);


        this.a2.next = this.annnode;
        this.annnode.next = this.emmanode;
        this.emmanode.next = this.davenode;
        this.davenode.next = this.nicknode;
        this.nicknode.next = this.a2;
        this.a2.prev = this.nicknode;
        this.nicknode.prev = this.davenode;
        this.davenode.prev = this.emmanode;
        this.emmanode.prev = this.annnode;
        this.annnode.prev = this.a2;
        this.dp1.sentinel = this.a2;
    }

    boolean testIsSentinel(Tester t) {
        init();
        return t.checkExpect(this.abcnode.next.isSentinel(), false) &&
                t.checkExpect(this.abcnode.next, this.bcdnode) &&
                t.checkExpect(this.a1.prev.isSentinel(), false) &&
                t.checkExpect(this.a1.isSentinel(), true) &&
                t.checkExpect(this.a2.isSentinel(), true);

    }

    boolean testSize(Tester t) {
        init();
        this.mtdeque.addAtHead("g");
        return t.checkExpect(this.mtdeque.size(), 1) &&
                t.checkExpect(this.d1.size(), 4) &&
                t.checkExpect(this.mt1.size(), 0) &&
                t.checkExpect(this.dp1.size(), 4) &&
                t.checkExpect(this.dnull.size(), 0);


    }

    boolean testAddAtHead(Tester t) {
        init();
        this.d1.addAtHead("g");
        this.dp1.addAtHead(mike);
        this.dnull.addAtHead(mike);
        return 
                t.checkExpect(this.d1.size(), 5) &&
                t.checkExpect(this.d1.sentinel.next.data, "g") &&
                t.checkExpect(this.dp1.size(), 5) &&
                t.checkExpect(this.dp1.sentinel.next.data, mike) &&
                t.checkExpect(this.dnull.size(), 1);

    }

    boolean testAddAtTail(Tester t) {
        init();
        this.d1.addAtTail("g");
        this.dp1.addAtTail(mike);
        this.dnull.addAtTail(mike);
        return 
                t.checkExpect(this.d1.size(), 5) &&
                t.checkExpect(this.d1.sentinel.prev.data, "g") &&
                t.checkExpect(this.dp1.size(), 5) &&
                t.checkExpect(this.dp1.sentinel.prev.data, mike) &&
                t.checkExpect(this.dnull.size(), 1);

    }

    boolean testRemoveTail(Tester t) {
        init();
        this.d1.removeFromTail();
        this.dp1.removeFromTail();
        return 
                t.checkExpect(this.d1.size(), 3) &&
                t.checkExpect(this.d1.sentinel.prev.data, "cdf") &&
                t.checkExpect(this.dp1.size(), 3) &&
                t.checkExpect(this.dp1.sentinel.prev.data, dave);
    }

    boolean testRemoveHead(Tester t) {
        init();
        this.d1.removeFromHead();
        this.dp1.removeFromHead();
        return 
                t.checkExpect(this.d1.size(), 3) &&
                t.checkExpect(this.d1.sentinel.next.data, "bcd") &&
                t.checkExpect(this.dp1.size(), 3) &&
                t.checkExpect(this.dp1.sentinel.next.data, emma);
    }


    boolean testFind(Tester t) {
        init();
        return 
                t.checkExpect(this.d1.find("23423423"), this.a1) &&
                t.checkExpect(this.d1.find("def"), this.defnode) &&
                t.checkExpect(this.abcnode.find("abc"), this.abcnode) &&
                t.checkExpect(this.abcnode.find("bfdss"), 
                        this.bcdnode.find("bfdss")) &&
                        t.checkExpect(this.defnode.find("sddf"), this.a1) &&
                        t.checkExpect(this.defnode.find("bfds"), this.a1) &&
                        t.checkExpect(this.a1.find("abc"), this.abcnode) &&
                        t.checkExpect(this.a1.find("jhksdfgkjhsadf"),
                                this.a1) &&
                                t.checkExpect(this.mt1.find("ss"), this.mt1);
    }

    boolean testRemoveNode(Tester t) {
        init();
        this.d1.removeNode(this.bcdnode);
        this.dp1.removeNode(this.emmanode);
        return 
                t.checkExpect(this.d1.size(), 3) &&
                t.checkExpect(this.d1.find("bcd"), this.a1) &&
                t.checkExpect(this.dp1.size(), 3) &&
                t.checkExpect(this.dp1.sentinel.next.next, this.davenode);
    }


    boolean testInsert(Tester t) {
        init();
        this.d1.insertSorted("da", bystring);
        this.d1.insertSorted("az", bystring);
        this.mtdeque.insertSorted("hd", bystring);
        this.dp1.insertSorted(mike, byperson);
        this.dnull.insertSorted(mike, byperson);
        return
                t.checkExpect(this.d1.size(), 6) &&
                t.checkExpect(this.d1.sentinel.next.next.data, "az") &&
                t.checkExpect(this.d1.sentinel.prev.prev.data, "da") &&
                t.checkExpect(this.mtdeque.sentinel.next.data, "hd") &&
                t.checkExpect(this.mtdeque.sentinel.prev.data, "hd") &&
                t.checkExpect(this.dp1.sentinel.next.next.next.data, mike) &&
                t.checkExpect(this.dnull.size(), 1);
    }

    boolean testInsertHelper(Tester t) {
        init();
        this.abcnode.insertSorted("da", bystring);
        this.abcnode.insertSorted("az", bystring);
        this.annnode.insertSorted(mike, byperson);
        return
                t.checkExpect(this.abcnode.next.data, "az") &&
                t.checkExpect(this.abcnode.prev.prev.prev.data, "da") &&
                t.checkExpect(this.annnode.next.next.data, mike);
    }

    boolean testRemoveSorted(Tester t) {
        init();
        this.d1.removeSorted("abc", bystring);
        this.dp1.removeSorted(mike, byperson);
        return
                t.checkExpect(this.d1.size(), 3) &&
                t.checkExpect(this.d1.sentinel.next.data, "bcd") &&
                t.checkExpect(this.dp1.sentinel.size(), 3) &&
                t.checkException(
                        new RuntimeException("Item zzz is not in this queue"), 
                        this.d1,
                        "removeSorted", "zzz", bystring) && 
                        t.checkException(new RuntimeException(
                                "Item " + grace.toString() + 
                                " is not in this queue"), 
                                this.dp1,
                                "removeSorted", grace, byperson); 

    }

}



