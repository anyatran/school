import tester.Tester;

//abstract
abstract class ABST{
    IBookComparator order;
    ABST(IBookComparator order){
        this.order = order;
    } 
    // insert a book into the tree
    public abstract ABST insert(Book b);
    // get the first book from the tree
    public abstract Book getFirst();
    // checks if the tree is a node
    public abstract boolean isNode();
    // get the rest of this tree
    public abstract ABST getRest();
    // get the data field from this tree
    public abstract Book getData();
    // get the right field of this tree
    public abstract ABST getRight();
    // get the left field of this tree
    public abstract ABST getLeft();
    // checks if this tree is the same as that tree
    public abstract boolean sameTree(ABST that);
    // checks if this tree has the same data as that tree
    public abstract boolean sameData(ABST that);
    // checks if this tree contains the same data as the list
    public abstract boolean sameAsList(ILoB that);
    // checks if this tree is a leaf 
    public abstract boolean isLeaf();
    // build a list out of a tree
    public abstract ILoB buildList(ILoB that);


}
// represents a leaf
/*
 Template:
 Fields:
 this.order                          -- IBookComparator

 Methods:
this.insert(Book b)                 -- ABST
this.getFirst()                     -- Book
this.isNode()                       -- boolean
this.getRest()                      -- ABST
this.getData()                      -- Book
this.getRight()                     -- ABST
this.getLeft()                      -- ABST
this.sameTree(ABST)                 -- boolean
this.sameData(ABST)                 -- boolean
this.sameAsList(ILoB)               -- boolean
this.isLeaf()                       -- boolean
this.buildList(ILoB)                -- ILoB

 Methods for fields:
 --



 */
class Leaf extends ABST{
    Leaf(IBookComparator order) {
        super(order);
    }
    // insert a book into the tree
    public ABST insert(Book b) {
        return new Node(this.order, b, this, this);
    }
    // get the first book from the tree
    public Book getFirst(){
        throw new RuntimeException("No first in an empty tree");
    }
    // checks if this tree is a node
    public boolean isNode(){
        return false;
    }
    // get the rest of this tree
    public ABST getRest(){
        throw new RuntimeException("No rest of an empty tree");
    }
    // get the data field from this tree
    public Book getData() {
        throw new RuntimeException("No books in an empty tree");
    }
    // get the right field from this tree
    public ABST getRight() {
        throw new RuntimeException("No books in an empty tree");
    }
    // get the left field from this tree
    public ABST getLeft() {
        throw new RuntimeException("No books in an empty tree");
    }
    // checks if this tree is the same as that tree
    public boolean sameTree(ABST that){
        if(that.isNode()){
            return false;
        }
        else return true;
    }
    // checks if this tree has the same data as that tree
    public boolean sameData(ABST that) {
        if (that.isLeaf()){
            return true;
        }
        else return false;
    }
    // checks if this tree has the same data as that list
    public boolean sameAsList(ILoB that) {
        if (that.isEmpty()){
            return true;
        }
        else return false;
    }
    // checks if this tree is a list
    public boolean isLeaf(){
        return true;
    }
    // build a list out of a tree
    public ILoB buildList(ILoB that){
        return that;
    }
}

// represents a node
/*
Template:
Fields:
this.order                          -- IBookComparator
this.data                           -- Book
this.left                           -- ABST
this.right                          -- ABST

Methods:
this.insert(Book b)                 -- ABST
this.getFirst()                     -- Book
this.isNode()                       -- boolean
this.getRest()                      -- ABST
this.getData()                      -- Book
this.getRight()                     -- ABST
this.getLeft()                      -- ABST
this.sameTree(ABST)                 -- boolean
this.sameData(ABST)                 -- boolean
this.sameAsList(ILoB)               -- boolean
this.isLeaf()                       -- boolean
this.buildList(ILoB)                -- ILoB

Methods for fields:
this.order.compare(Book Book)       -- boolean
this.right.insert(Book)             -- ABST
this.left.insert(Book)              -- ABST
this.left.isNode()                  -- boolean
this.right.isNode()                 -- boolean
this.left.getFirst()                -- Book
this.left.getRest()                 -- ABST
this.right.getData()                -- Book
this.right.getLeft()                -- ABST
this.right.getRight()               -- ABST
this.order.same(IBookComparator)    -- boolean
this.data.sameBook(Book)            -- boolean
this.left.sameTree(ABST)            -- boolean
this.right.sameTree(ABST)           -- boolean

 */
class Node extends ABST{
    Book data;
    ABST left;
    ABST right;
    Node(IBookComparator order, Book data, ABST left, ABST right) {
        super(order);
        this.data = data;
        this.left = left;
        this.right = right;
    }
    // insert a book into the tree
    public ABST insert(Book b) {
        if (this.order.compare(b, this.data) >= 0) {
            return new Node(this.order, this.data, this.left, this.right.insert(b));
        }
        else return new Node(this.order, this.data, this.left.insert(b), this.right);
    }
    // checks if this tree is a node
    public boolean isNode() {
        return true;
    }
    // get the first book from the tree
    public Book getFirst(){
        if (this.left.isNode()){
            return this.left.getFirst();
        }
        else return this.data;
    }
    // get the data field from this tree
    public Book getData(){
        return this.data;
    }
    // get the left field from this tree
    public ABST getLeft(){
        return this.left;
    }
    // get the right field from this tree
    public ABST getRight(){
        return this.right;
    }
    // get the rest of this tree
    public ABST getRest(){
        if (this.left.isNode() &&
                this.right.isNode()){
            return new Node(this.order, this.data, this.left.getRest(), this.right);
        }
        else if (this.left.isNode() &&
                !this.right.isNode()){
            return new Node(this.order, this.data, this.left.getRest(), this.right);
        }
        else if (!this.left.isNode() &&
                this.right.isNode()){
            return new Node(this.order, this.right.getData(), this.right.getLeft(), this.right.getRight());
        }
        else return new Leaf(this.order);
    }
    // checks if this tree is the same as that tree
    public boolean sameTree(ABST that){
        if (!that.isNode()){
            return false;
        }
        else {
            return (this.order.same(that.order) &&
                    this.data.sameBook(that.getData()) &&
                    this.left.sameTree(that.getLeft()) &&
                    this.right.sameTree(that.getRight()));
        }
    }



    // checks if this tree has the same data as that tree
    public boolean sameData(ABST that){
        if (!that.isNode()){
            return false;
        }
        else if (this.getFirst().sameBook(that.getFirst())){
            return this.getRest().sameData(that.getRest());
        }
        else return false;
    }

    // checks if this tree has the same data as that list
    public boolean sameAsList(ILoB that) {
        if (that.isEmpty()){
            return false;
        }
        else return (this.getFirst().sameBook(that.first()) &&
                this.getRest().sameAsList(that.rest()));

    }
    // checks if this tree is a leaf
    public boolean isLeaf(){
        return false;
    }

    // build a list out of a tree
    public ILoB buildList(ILoB that){
        if (that.isEmpty()){
            return new ConsLoB(this.getFirst(), this.getRest().buildList(that));
        }
        else if (this.order.compare(this.getFirst(), that.first()) >= 0) { // this is after that
            return new ConsLoB(that.first(), this.buildList(that.rest()));
        }
        else return new ConsLoB(this.getFirst(), this.getRest().buildList(that));

    }
}


// represents a book
/*
Template:
Fields:
this.title                            -- String
this.author                           -- String
this.price                            -- int

Methods:
this.sameBook(Book)                   -- boolean

*/
class Book {
    String title;
    String author;
    int price;
    Book (String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;

    }

    public boolean sameBook(Book that){
        return (this.author.equals(that.author) &&
                this.title.equals(that.title) &&
                this.price == that.price);

    }
}
//interface to compare books
interface IBookComparator {
    //given comparator that will later be used to compare by title, author and price 
    int compare (Book b1, Book b2);
    // is the book is compared by title
    boolean isByTitle();
    // is the book is compared by author
    boolean isByAuthor();
    // is the book is compared by price
    boolean isByPrice();
    // checks if 2 books are the same title
    boolean same(IBookComparator that);

}
//compares books by their titles 
class BooksByTitle implements IBookComparator{
    public int compare(Book b1, Book b2){
        return b1.title.compareTo(b2.title);
    }       
    // is the book is compared by title
    public boolean isByTitle(){
        return true;
    }
    // is the book is compared by author
    public boolean isByAuthor(){
        return false;
    }
    // is the book is compared by price
    public boolean isByPrice(){
        return false;
    }
    // if two books have the same title
    public boolean same(IBookComparator that){
        if (that.isByTitle()){
            return true;
        }
        else return false;
    }
}
//compares books by their authors 
class BooksByAuthor implements IBookComparator{
    public int compare(Book b1, Book b2){
        return b1.author.compareTo(b2.author);
    }
    // is the book is compared by title
    public boolean isByTitle(){
        return false;
    }
    // is the book is compared by author
    public boolean isByAuthor(){
        return true;
    }
    // is the book is compared by price
    public boolean isByPrice(){
        return false;
    }
    // if 2 books have the same title
    public boolean same(IBookComparator that){
        if (that.isByAuthor()){
            return true;
        }
        else return false;
    }
}

// compares books by their prices
class BooksByPrice implements IBookComparator{
    public int compare(Book b1, Book b2){
        if (b1.price < b2.price){
            return -1;
        }
        else if (b1.price == b2.price){
            return 0;
        }
        else {
            return 1;
        }
    }
    // is the book is compared by title
    public boolean isByTitle(){
        return false;
    }
    // is the book is compared by author
    public boolean isByAuthor(){
        return false;
    }
    // is the book is compared by price
    public boolean isByPrice(){
        return true;
    }
    // if 2 books have the same title
    public boolean same(IBookComparator that){
        if (that.isByPrice()){
            return true;
        }
        else return false;
    }
}


interface ILoB {
    // checks if the list is empty
    public boolean isEmpty();
    // get the first of the list
    public Book first();
    // get the rest of the list
    public ILoB rest();
    // build the tree
    public ABST buildTree(ABST that);
}
/*
Template:
Fields:

Methods:
this.isEmpty                          -- boolean
this.first                            -- Book
this.rest                             -- ILoB
this.buildTree(ABST)                  -- ABST
*/
class MtLoB implements ILoB{
    MtLoB(){};
    // checks if the list is empty
    public boolean isEmpty(){
        return true;
    }
    // get the first of the list
    public Book first(){
        throw new RuntimeException("No first in an empty list");
    }
    // get the rest of the list
    public ILoB rest(){
        throw new RuntimeException("No rest in an empty list");
    }
    // build the tree
    public ABST buildTree(ABST that){
        return that;
    }

}

// represents a non empty list
/*
Template:
Fields:
this.first                            -- Book
this.rest                             -- ILoB

Methods:
this.isEmpty                          -- boolean
this.first                            -- Book
this.rest                             -- ILoB
this.buildTree(ABST)                  -- ABST

Methods for Fields:
this.rest.buildTree(ABST)             -- ABST
*/
class ConsLoB implements ILoB{
    Book first;
    ILoB rest;
    ConsLoB(Book first, ILoB rest){
        this.first = first;
        this.rest = rest;
    }
    // checks if the list is empty
    public boolean isEmpty(){
        return false;
    }
    // get the first of the list
    public Book first(){
        return this.first;
    }
    // get the rest of the list
    public ILoB rest(){
        return this.rest;
    }
    // build the tree
    public ABST buildTree(ABST that){
        return this.rest.buildTree(that.insert(this.first));
    }

}


// represents examples
class ExampleTree{
    IBookComparator byTitle = new BooksByTitle();
    IBookComparator byAuthor = new BooksByAuthor();
    IBookComparator byPrice = new BooksByPrice();

    Book cookbook = new Book("The Anarchist Cookbook", "William Powell", 23);
    Book mormon = new Book("Book of Mormon", "Joesph Smith", 17);
    Book danish = new Book("How to Be Danish", "Partick Kingsley", 17);
    Book disappear = new Book ("How to Disappear Completly and Never be Found", "Doug Richmond", 2);

    Book deadcat = new Book("Z More uses for a Dead Cat", "Simon bond", 4);

    //Examples of lists of books
    ILoB mt = new MtLoB();
    ILoB list = new ConsLoB(this.cookbook, (new ConsLoB (this.mormon, (
            new ConsLoB (this.danish, (new ConsLoB (this.disappear, this.mt)))))));
    ILoB list2 = new ConsLoB(this.cookbook, (new ConsLoB (this.mormon, (new ConsLoB (
            this.danish, (new ConsLoB (this.disappear, (new ConsLoB (this.deadcat, this.mt)))))))));
    ILoB list0 = new ConsLoB(this.deadcat, this.mt);
    ILoB list1 = new ConsLoB(this.disappear, this.mt);
    ILoB list3 = new ConsLoB(this.deadcat, new ConsLoB (this.danish, this.mt));



    ABST l1 = new Leaf(this.byTitle);
    ABST l2 = new Leaf(this.byAuthor);
    ABST l3 = new Leaf(this.byPrice);

    // by title: mormon, danish, disappear, cookbook 
    // by author: disappear, mormon, danish, cookbook
    // by price: disappear, danish, mormon, cookbook 

    // by title:
    ABST dan = new Node(this.byTitle, this.danish, this.l1, this.l1);
    ABST nt1 = new Node(this.byTitle, this.mormon, this.l1, this.l1);
    ABST nt3 = new Node(this.byTitle, this.cookbook, this.l1, this.l1);
    ABST nt4 = new Node(this.byTitle, this.deadcat, this.l1, this.l1);
    ABST nt3a = new Node(this.byTitle, this.cookbook, this.l1, this.nt4);

    ABST nt2 = new Node(this.byTitle, this.danish, this.nt1, this.l1);
    ABST nt2a = new Node(this.byTitle, this.danish, this.nt1, this.dan);

    ABST bt1 = new Node(this.byTitle, this.disappear, this.nt2, this.nt3);
    ABST bt1a = new Node(this.byTitle, this.disappear, this.nt2a, this.nt3a);



    ILoB listofbt1 = new ConsLoB(this.mormon, new ConsLoB(this.danish, 
            (new ConsLoB(this.disappear, (new ConsLoB(this.cookbook, this.mt))))));


    ABST nt5 = new Node(this.byTitle, this.cookbook, this.l1, this.nt4);
    ABST nt6 = new Node(this.byTitle, this.danish, this.l1, this.l1);

    // by author
    ABST na1 = new Node(this.byAuthor, this.disappear, this.l2, this.l2);
    ABST na2 = new Node(this.byAuthor, this.mormon, this.na1, this.l2);
    ABST na3 = new Node(this.byAuthor, this.cookbook, this.l2, this.l2);
    ABST bt2 = new Node(this.byAuthor, this.danish, this.na2, this.na3);
    ILoB listofbt2 = new ConsLoB(this.disappear, new ConsLoB(this.mormon, 
            (new ConsLoB(this.danish, (new ConsLoB(this.cookbook, this.mt))))));


    ABST na3a = new Node(this.byAuthor, this.danish, this.l2, this.l2);
    ABST na2a = new Node(this.byAuthor, this.mormon, this.na1, this.na3a);

    ABST bt2a = new Node(this.byAuthor, this.cookbook, this.na2a, this.l2);



    ABST bt0 = new Node(this.byAuthor,this.danish,this.na2, this.na3);
    ILoB listofbt0 = new ConsLoB(this.disappear, new ConsLoB(this.mormon, 
            (new ConsLoB(this.danish, (new ConsLoB(this.cookbook, this.mt))))));
    // by price:
    ABST np1 = new Node(this.byPrice, this.danish, this.l3, this.l3);
    ABST np2 = new Node(this.byPrice, this.cookbook, this.l3, this.l3);
    ABST np3 = new Node(this.byPrice, this.disappear, this.l3, this.np1);
    ABST bt3 = new Node(this.byPrice, this.mormon, this.np3, this.np2);

    ILoB listofbt3 = new ConsLoB(this.danish, new ConsLoB(this.disappear, 
            (new ConsLoB(this.mormon, (new ConsLoB(this.cookbook, this.mt))))));

    ABST bt4 = new Node(this.byPrice, this.mormon, this.np1, this.np2);

    ILoB listSorted = new ConsLoB(this.disappear, new ConsLoB(this.deadcat, 
            new ConsLoB(this.danish, new ConsLoB(this.mormon, new ConsLoB(this.cookbook, this.mt)))));
    ILoB listSorted2 = new ConsLoB(this.disappear, 
            new ConsLoB(this.danish, new ConsLoB(this.mormon, new ConsLoB(this.cookbook, this.mt))));


    // by price:
    ABST np5 = new Node(this.byPrice, this.disappear, this.l3, this.l3);
    ABST np6 = new Node(this.byPrice, this.cookbook, this.l3, this.l3);
    ABST np7 = new Node(this.byPrice, this.mormon, this.l3, this.np6);
    ABST bt5 = new Node(this.byPrice, this.danish, this.np5, this.np7);
    ILoB listofbt5 = new ConsLoB(this.disappear, new ConsLoB(this.danish, 
            (new ConsLoB(this.mormon, (new ConsLoB(this.cookbook, this.mt))))));

    boolean testInsert(Tester t) {
        return t.checkExpect(this.bt1.insert(this.deadcat),
                new Node(this.byTitle, this.disappear, this.nt2, this.nt5));
    }
    boolean testGetFirst(Tester t) {
        return t.checkExpect(this.bt2.getFirst(),
                this.disappear) &&
                t.checkExpect(this.bt1.getFirst(),
                        this.mormon);
    }
    boolean testGetRest(Tester t) {
        return t.checkExpect(this.bt1.getRest(),
                new Node(this.byTitle, this.disappear, this.nt6, this.nt3)) &&
                t.checkExpect(this.bt3.getRest(), this.bt4);
    }
    boolean testRest(Tester t){
        return t.checkExpect(this.listofbt1.rest(), new ConsLoB(this.danish, 
                (new ConsLoB(this.disappear, (new ConsLoB(this.cookbook, this.mt))))));
    }

    boolean testFirst(Tester t){
        return t.checkExpect(this.listofbt1.first(), this.mormon);
    }


    boolean testSameBook(Tester t) {
        return t.checkExpect(this.bt1.getFirst().sameBook(this.listofbt1.first()), true);
    }


    boolean testSameTree(Tester t) {
        return t.checkExpect(this.bt1.sameTree(this.bt2), false) &&
                t.checkExpect(this.bt2.sameTree(this.bt0), true);
    }

    boolean testSameData(Tester t) {
        return 
                t.checkExpect(this.bt2.sameData(this.bt2a), true) ;
    }                    

    boolean testSameAsList(Tester t) {
        return t.checkExpect(this.bt1.sameAsList(this.listofbt1), true) &&
                t.checkExpect(this.bt2.sameAsList(this.listofbt1), false) &&
                t.checkExpect(this.bt4.sameAsList(this.list1), false) &&
                t.checkExpect(this.bt4.sameAsList(this.list0), false);
    }




    boolean testBuildTree(Tester t){
        return t.checkExpect(this.mt.buildTree(this.bt1), this.bt1) &&
                t.checkExpect(this.list3.buildTree(this.bt1),this.bt1a);

    }
    boolean testBuildList(Tester t) {
        return t.checkExpect(this.bt3.buildList(this.list0), this.listSorted) &&
                t.checkExpect(this.bt3.buildList(this.mt), this.listSorted2) &&
                t.checkExpect(this.l2.buildList(this.list0), this.list0);
    }
}
















