/*
    Book                          Author
    St title               St name
    ILoA authors           ILoB books


        ILoA                                       ILoB
MTLoA             ConsLoA             MtLoB(addBook)        ConsLoB
                  Author first                             Book first
                  ILoA rest                                ILoB rest

 */

// MF: LL, HTDP, RR
// SK: HTDP, PLAI
// MFL: LL
// JKR: HP


// LL: MF, MFL 
// HTDP: MF, SK
// RR: MF
// PLAI: SK
// HP: JKR

/*
1) Author
2) Book
 */

class Author {
    String name;
    ILoB books;
    Author(String name) {
        this.name = name;
        this.books = new MtLoB();
        // MT???
    }
    // EFFECT: add new book to the given authors
    // PURPOSE: construct a new book
    void addBook(Book b) {
        this.books = new ConsLoB(b, this.books);
    }
}

class Book {
    String title;
    ILoA authors;
    Book(String title, ILoS authors) {
        this.title = title;
        this.authors = authors;
        this.authors.addBook(this);
    }
}

class MtLoA {
    // add the given book to each author to LoB in this list
    // void: side EFFECT
    void addBook(Book b) {// no authors
    }
}




/*
 Template:
 Fiels
 this.first
 this.rest
 
 Methods:
 this.addBooks
 
 Methods of Fields
 this.first.addBook (in the author)
 this.rest.addBook
 */
class ConsLoA {
    // add the given book to each author to LoB in this list
    // void: side EFFECT
    void addBook(Book b) {
        this.rest.addBook(b);
        this.first.addBook(b);
    }
}

// to build examples, start with the one that has no outgoing arrows
class ExamplesB {
    Author mf = new Author("MF");
    Author sk = new Author("SK");
    Author mfl = new Author("MFL");
    Author jkr = new Author("JKR");
    
    ILoA l1 = new ConsLoA(this.mf, new ConsLoA(this.sk, new MtLoA()));
    
    // check does mf has no books?
    // define length
    Book ll = new Book("LL", this.l1);
    // check does MF has ll as a book?
    // same
}

















