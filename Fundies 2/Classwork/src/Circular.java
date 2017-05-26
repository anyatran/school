class Author {
    String name;
    int year;
    Author(String name, int year){
        this.title = title;
        this.books = new MtLoB();
        this.year = year;
    }
    // modify the field books for this author, so it contains the given book
    void addBook(Book b){
        this.books = new ConsLoB(b, this.books;
    }
}

interface ILoB { }

class MtLoB implements ILoB{
    MtLoB() { }
}
class ConsLoB implements ILoB{
    Book first;
    ILoB rest;
    ConsLoB(Book first, ILoB rest){
        this.first = first;
        this.rest = rest;
    }
}


class Examples{
    // Examples of authors:
    Author db = new Author("DB", 1957);
    Author pc = new Author("PC", 1946);
    Author ap = new Author("AP", 1950);

    // Examples of lists of authors:
    ALoA dblist = new ConsLoA(this.db, new MTLoA());
    ALoA pclist = new ConsLoA(this.pc, new MTLoA());
    ALoA allauthors = new ConsLoA(this.ap,new ConsLoA(this.pc, this.dblist));


    // Examples of books:
    Book dvc = new Book("DVC", this.dblist, 2003);
    Book mls = new Book("MLS", this.pclist, 2002);
    Book snx = new Book("SN", this.allauthors, 2001);

    // Examples of lists of books
    ALoB booklist1 = new MTLoB();
    ALoB booklist2 = new ConsLoB(this.dvc, 
            new ConsLoB(this.mls,
                    this.booklist1));
    ALoB booklist3 = new ConsLoB(this.snx, this.booklist2);

    // tests for the method addBook in the class Author
    boolean testAddBook(Author a, Book b){
        // make a new copy of the Author object
        Author aMod = new Author(a.name, a.year);
        Author aRes = new Author(a.name, a.year);

        // the constructor srats with an empty list of books
        // so change it to match the given Author's list of books
        aMod.books = a.books;
        aRes.books = new ConsLoB(b, a.books);

        // add the given book to the book list for our copy 
        aMod.addBook(b);

        // compare our copy with the given book added to the original booklist
        return aMod.same(aRes);
    }

}