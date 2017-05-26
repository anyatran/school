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



 */
class Deque {
    Sentinel node;
    Deque(Sentinel node) {
        this.node = node;
    }
    // calculate the size of the Deque
    int size() {
        return this.node.size();
    }

    // add a Node at the beginning of this Deque
    void addAtHead(String s) {
        Node n = new Node(s);
        n.prev = this.node;
        n.next = this.node.next;
        this.node.next.prev = n;
        this.node.next = n;
    }
    // add a Node at the end of this Deque
    void addAtTail(String s) {
        Node n = new Node(s);

        n.next = this.node;
        n.prev = this.node.prev;
        n.prev.next = n;
        this.node.prev = n;
    }

    // remove a Node from the beginning of this Deque
    void removeFromHead() {
        this.node.next.next.prev = this.node;
        this.node.next = this.node.next.next;
    }
    // remove a Node from the end of this Deque
    void removeFromTail() {
        this.node.prev.prev.next = this.node;
        this.node.prev = this.node.prev.prev;
    }
    // find a Node with a given String in this Deque
    Node find(String s) {
        return this.node.find(s);
    }
    // remove a given Node from this Deque
    Deque removeNode(Node n) {
        if (this.node.find(n.data).isSentinel() || n.isSentinel()) {
            return this;
        }
        else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n.next = null;
            n.prev = null;

            return this;
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


 */
class Node {
    String data;
    Node next;
    Node prev;


    Node(String data) {
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
    // find a Node with a given string
    Node find(String s) {
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
class Sentinel extends Node {

    Sentinel() {
        super("");
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

    // find a node with a given string
    Node find(String s) {
        if (this.next.isSentinel()) {
            return this;
        }
        else {
            return this.next.find(s);
        }
    }


}


class ExamplesDeque {
    Sentinel a1;
    Sentinel mtsentinel;
    Node abcnode;
    Node bcdnode;
    Node cdfnode;
    Node defnode;
    Node g;
    Sentinel mtg;
    Sentinel mt1;

    Deque d1;
    Deque mtdeque;
    Deque d2;

    void init() {
        a1 = new Sentinel();
        mtsentinel = new Sentinel();
        mtg = new Sentinel();
        mt1 = new Sentinel();

        abcnode = new Node("abc");
        bcdnode = new Node("bcd");
        cdfnode = new Node("cdf");
        defnode = new Node("def");
        g = new Node("g");

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

        this.mtg.next = this.g;
        this.g.prev = this.mtg;
        this.mtg.prev = this.g;
        this.g.next = this.mtg;

        this.mt1.next = this.mt1;
        this.mt1.prev = this.mt1;


        this.mtsentinel.next = this.mtsentinel;
        this.mtsentinel.prev = this.mtsentinel;

        this.d1 = new Deque(this.a1);
        this.mtdeque  = new Deque(this.mtsentinel);
        this.d2 = new Deque(this.mtg);
    }

    boolean testIsSentinel(Tester t) {
        init();
        return t.checkExpect(this.abcnode.next.isSentinel(), false) &&
                t.checkExpect(this.abcnode.next, this.bcdnode) &&
                t.checkExpect(this.a1.prev.isSentinel(), false) &&
                t.checkExpect(this.a1.isSentinel(), true);
    }

    boolean testSize(Tester t) {
        init();
        this.mtdeque.addAtHead("g");
        return t.checkExpect(this.mtdeque.size(), 1) &&
                t.checkExpect(this.d1.size(), 4) &&
                t.checkExpect(this.mt1.size(), 0);

    }

    boolean testAddAtHead(Tester t) {
        init();
        this.d1.addAtHead("g");
        return 
                t.checkExpect(this.d1.size(), 5) &&
                t.checkExpect(this.d1.node.next.data, "g");
    }

    boolean testAddAtTail(Tester t) {
        init();
        this.d1.addAtTail("g");
        return 
                t.checkExpect(this.d1.size(), 5) &&
                t.checkExpect(this.d1.node.prev.data, "g");
    }

    boolean testRemoveTail(Tester t) {
        init();
        this.d1.removeFromTail();
        return 
                t.checkExpect(this.d1.size(), 3) &&
                t.checkExpect(this.d1.node.prev.data, "cdf");
    }

    boolean testRemoveHead(Tester t) {
        init();
        this.d1.removeFromHead();
        return 
                t.checkExpect(this.d1.size(), 3) &&
                t.checkExpect(this.d1.node.next.data, "bcd");
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
        return 
                t.checkExpect(this.d1.size(), 3) &&
                t.checkExpect(this.d1.find("bcd"), this.a1) &&
                t.checkExpect(this.d1.removeNode(this.a1), this.d1) &&
                t.checkExpect(this.d1.removeNode(this.g), this.d1) &&
                t.checkExpect(this.d2.removeNode(this.abcnode), this.d2) &&
                t.checkExpect(this.d2.removeNode(this.a1), this.d2);


    }


}



