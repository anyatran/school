import tester.*;
/**
 * HtDC Lectures
 * Lecture 6a: Methods for self-referential unions of classes
 *             that represent lists
 * 
 * Copyright 2013 Viera K. Proulx
 * This program is distributed under the terms of the 
 * GNU Lesser General Public License (LGPL)
 * 
 * @since 13 September 2013
 */

/*
                +-----------------------+                   
                | ILoB                  |<-------------+
                +-----------------------+              |
                +-----------------------+              |
                | int count()           |              |
                | int totalPrice()      |              |
                | ILoB allBefore(int x) |              |
                +-----------------------+              |
                           |                           |
                          / \                          |
                          ---                          |
                           |                           |
             -----------------------------             |
             |                           |             |
 +-----------------------+   +-----------------------+ |
 | MtLoB                 |   | ConsLoB               | |
 +-----------------------+   +-----------------------+ |
 +-----------------------+ +-| Book first            | |
 | int count()           | | | ILoB rest             |-+
 | int totalPrice()      | | +-----------------------+ 
 | ILoB allBefore(int x) | | | int count()           |
 +-----------------------+ | | int totalPrice()      |
                           | | ILoB allBefore(int x) | 
                           | +-----------------------+ 
                           v
                 +---------------+
                 | Book          |
                 +---------------+
                 | String title  |
                 | String author |
                 | int year      |
                 | int price     |
                 +---------------+    
 */

// to represent a book
class Book {
    String title;
    String author;
    int year;
    int price;
    
    Book(String title, String author, int year, int price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }
    
    // was this book produced before the given year?
    boolean producedBefore(int year) {
        return this.year < year;
    }
    
    // is the title of this book before the title of the given book?
    // lexicographically 
    boolean titleBefore(Book that) {
        return this.title.compareTo(that.title) <= 0;
    }
}


// to represent a list of books
interface ILoB {
    // count the books in this list
    public int count();
    
    // add to the count the count of all books in this list
    public int countAcc(int acc);
    
    // calculate the total price of all books in this list
    public int totalPrice(); 
    
    // add to the accumulator the total price of all books in this list
    public int totalPriceAcc(int acc);
    
    // produce a list of all books published before the given date 
    // from this list of books
    public ILoB allBefore(int year);
    
    // add to the given accumulator a list of all books that were
    // published before the given date from this list of books 
    public ILoB allBeforeAcc(int year, ILoB acc);
    
    // produce a list of all books in this list, sorted by their title
    public ILoB sort();
    
    // insert the given book into this list of books already sorted by title
    public ILoB insert(Book b);
    
}

// to represent an empty list of books
class MtLoB implements ILoB {
    
    MtLoB() {
    }
    
    // count the books in this list
    public int count() {
        return 0;
    }
    
    // add to the count the count of all books in this list
    public int countAcc(int acc) { 
        return acc; 
    }
    
    // calculate the total price of all books in this list
    public int totalPrice() { 
        return 0; 
    }
    
    // add to the accumulator the total price of all books in this list
    public int totalPriceAcc(int acc) { 
        return acc; 
    } 
    
    // produce a list of all books published before the given date 
    // from this empty list of books
    public ILoB allBefore(int year) { 
        return this; 
    }
    
    // add to the given accumulator a list of all books that were
    // published before the given date from this empty list of books 
    public ILoB allBeforeAcc(int year, ILoB acc) { 
        return acc; 
    }
    
    // produce a list of all books in this list, sorted by their title
    public ILoB sort() {
        return this;
    }
    
    // insert the given book into this list of books already sorted by title
    public ILoB insert(Book b) {
        return new ConsLoB(b, this);
    }
}

// to represent a nonempty list of books
class ConsLoB implements ILoB {
    Book first;
    ILoB rest;
    
    ConsLoB(Book first, ILoB rest) {
        this.first = first;
        this.rest = rest;
    }
    
    /*
     TEMPLATE:
     Fields:
     ... this.first ...                                  -- Book
     ... this.rest ...                                   -- ILoB
     
     Methods:
     ... this.count() ...                                 -- int
     ... this.countAcc(int acc) ...                       -- int
     ... this.countAccUp(int acc) ...                     -- int
     ... this.updateCount(Book b, int acc) ...            -- int
     ... this.allBefore(int year) ...                     -- ILoB
     ... this.allBeforeAcc(int year, ILoB acc) ...        -- ILoB
     ... this.allBeforeAccUp(int year, ILoB acc) ...      -- ILoB
     ... this.updateAllBefore(int year, Book b, ILoB acc) -- ILoB
     ... this.sort() ...                                  -- ILoB
     ... this.insert(ILoB - sorted) ...                   -- ILoB
     
     Methods for fields:
     ... this.rest.count() ...                                 -- int
     ... this.rest.countAcc(int acc) ...                       -- int
     ... this.rest.countAccUp(int acc) ...                     -- int
     ... this.rest.allBefore(int year) ...                     -- ILoB
     ... this.rest.allBeforeAcc(int year, ILoB acc) ...        -- ILoB
     ... this.rest.allBeforeAccUp(int year, ILoB acc) ...      -- ILoB
     ... this.rest.updateAllBefore(int year, Book b, ILoB acc) -- ILoB
     ... this.rest.sort() ...                                  -- ILoB
     ... this.rest.insert(ILoB - sorted) ...                   -- ILoB
     */
    
    // count the books in this list
    public int count() { 
        return 1 + this.rest.count();
    }
    
    // add to the count the count of all books in this list
    public int countAcc(int acc) { 
        return this.rest.countAcc(1 + acc); 
    }
    
    // calculate the total price of all books in this list
    public int totalPrice() { 
        return this.first.price + this.rest.totalPrice(); 
    }
    
    // add to the accumulator the total price of all books in this list
    public int totalPriceAcc(int acc) { 
        return this.rest.totalPriceAcc(this.first.price + acc); 
    } 
    
    
    // produce a list of all books published before the given date 
    // from this empty list of books
    public ILoB allBefore(int year) { 
        if (this.first.producedBefore(year)) {
            return new ConsLoB(this.first, this.rest.allBefore(year));
        }
        else {
            return this.rest.allBefore(year);
        }
    }
    
    // add to the given accumulator a list of all books that were
    // published before the given date from this empty list of books 
    public ILoB allBeforeAcc(int year, ILoB acc) { 
        if (this.first.producedBefore(year)) {
            return this.rest.allBeforeAcc(year, new ConsLoB(this.first, acc));
        }
        else {
            return this.rest.allBeforeAcc(year, acc);
        }
    }
     
    
    // update the accumulated list by adding the given book if appropriate
    public ILoB updateAllBefore(int year, Book b, ILoB acc) {
        if (b.producedBefore(year)) {
            return new ConsLoB(b, acc);
        }
        else {
            return acc;
        }
    }
    
    // produce a list of all books in this list, sorted by their title
    public ILoB sort() {
        return this.rest.sort().insert(this.first);
    }
    
    // insert the given book into this list of books already sorted by title
    public ILoB insert(Book b) {
        if (this.first.titleBefore(b)) {
            return new ConsLoB(this.first, this.rest.insert(b));
        }
        else {
            return new ConsLoB(b, this);
        }
    }
    
}

// examples and tests for the lists of books
class ExamplesBookList {
    ExamplesBookList() { }
    
    // sample books
    Book htdp = new Book("HtDP", "MF", 2001, 60);
    Book lpp = new Book("LPP", "STX", 1942, 10);
    Book ll = new Book("LL", "FF", 1986, 25);
    
    // tests for the method producedBefore:
    boolean testProducedBefore(Tester t) {
        return
        t.checkExpect(this.htdp.producedBefore(1950), false) && 
        t.checkExpect(this.lpp.producedBefore(1950), true);
    }
    
    // tests for the method titleBefore:
    boolean testTitleBefore(Tester t) {
        return
        t.checkExpect(this.htdp.titleBefore(this.lpp), true) && 
        t.checkExpect(this.lpp.titleBefore(this.ll), false);
    }
    
    // sample lilsts of books
    ILoB mtlist = new MtLoB();
    ILoB lista = new ConsLoB(this.lpp, this.mtlist);
    ILoB listb =   new ConsLoB(this.htdp, this.mtlist);
    ILoB listc = new ConsLoB(this.lpp, 
                   new ConsLoB(this.ll, this.listb));
    ILoB listcSorted = new ConsLoB(this.ll, 
                         new ConsLoB(this.lpp, this.listb));
    ILoB listd = new ConsLoB(this.htdp, 
                   new ConsLoB(this.ll, 
                     new ConsLoB(this.lpp, this.mtlist)));
    ILoB listdUnsorted = new ConsLoB(this.lpp, 
                           new ConsLoB(this.htdp, 
                             new ConsLoB(this.ll, this.mtlist)));
    
    
    // tests for the method count
    boolean testCount(Tester t) {
        return
        t.checkExpect(this.mtlist.count(), 0) &&
        t.checkExpect(this.lista.count(), 1) &&
        t.checkExpect(this.listd.count(), 3);
    }
    
    // tests for the method totalPrice
    boolean testTotalPrice(Tester t) {
        return
        t.checkExpect(this.mtlist.totalPrice(), 0) &&
        t.checkExpect(this.lista.totalPrice(), 10) &&
        t.checkExpect(this.listc.totalPrice(), 95) &&
        t.checkExpect(this.listd.totalPrice(), 95);
    }
    
    // tests for the method allBefore
    boolean testBefore(Tester t) {
        return
        t.checkExpect(this.mtlist.allBefore(2001), this.mtlist) &&
        t.checkExpect(this.lista.allBefore(2001), this.lista) &&
        t.checkExpect(this.listb.allBefore(2001), this.mtlist) &&
        t.checkExpect(this.listc.allBefore(2001), 
             new ConsLoB(this.lpp, new ConsLoB(this.ll, this.mtlist))) &&
        t.checkExpect(this.listd.allBefore(2001),
             new ConsLoB(this.ll, new ConsLoB(this.lpp, this.mtlist)));
    }
    
    // tests for the method totalPriceAcc
    boolean testTotalPriceAcc(Tester t) {
        return
        t.checkExpect(this.mtlist.totalPriceAcc(0), 0) &&
        t.checkExpect(this.lista.totalPriceAcc(0), 10) &&
        t.checkExpect(this.listc.totalPriceAcc(0), 95) &&
        t.checkExpect(this.listd.totalPriceAcc(0), 95);
    }
    
    // tests for the method allBefore
    boolean testBeforeAcc(Tester t) {
        return
        t.checkExpect(this.mtlist.allBeforeAcc(2001, this.mtlist), 
                      this.mtlist) &&
        t.checkExpect(this.lista.allBeforeAcc(2001, this.mtlist), 
                      this.lista) &&
        t.checkExpect(this.listb.allBeforeAcc(2001, this.mtlist), 
                      this.mtlist) &&
        // The following 2 tests FAIL! Read the last section of the comment 
        // block in the beginning of the file. 
        // Pay attention to the trace provided there. 
        // t.checkExpect(this.listc.allBeforeAcc(2001, this.mtlist), 
        //      new ConsLoB(this.lpp, new ConsLoB(this.ll, this.mtlist))) &&
        // t.checkExpect(this.listd.allBeforeAcc(2001, this.mtlist),
        //      new ConsLoB(this.ll, new ConsLoB(this.lpp, this.mtlist))) 
        
        // These 2 tests succeed. 
        // The result list from the accumulator function 
        // has the books in reverse order. 
        t.checkExpect(this.listc.allBeforeAcc(2001, this.mtlist), 
             new ConsLoB(this.ll, new ConsLoB(this.lpp, this.mtlist))) &&
        t.checkExpect(this.listd.allBeforeAcc(2001, this.mtlist),
             new ConsLoB(this.lpp, new ConsLoB(this.ll, this.mtlist)));
    }  
    
    // test the method sort for the lists of books
    boolean testSort(Tester t) {
        return
        t.checkExpect(this.listc.sort(), this.listd) &&
        t.checkExpect(this.listdUnsorted.sort(), this.listd);
    }
    
    // test the method insert for the lists of books
    boolean testInsert(Tester t) {
        return
        t.checkExpect(this.lista.insert(this.htdp).insert(this.ll), 
                      this.listd) &&
        t.checkExpect(this.listb.insert(this.lpp).insert(this.ll),  
                      this.listd);
    }
    
}