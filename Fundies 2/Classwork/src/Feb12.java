//CLASS 15 2/3/14
Mutation and clyclic data
class Book {
    String title;
    Author author; }
class Author {
    String name;
    int yob;
    Book book;}

Book htdp = new Book(“htdp”, this.mf);
Author mf = new Author (“mf”, 1950, this.htdp);

// null pointer exception- you haven’t defined this yet.
// we want to be able to mutate data → void methods are for mutation
// create an author without a book THEN add the book to the author
// Q: could we make two constructors? 
// created a constructor that does NOT initialize all our fields
// - author doesn’t initialize the book field

// => make a method that changes the book field for an author
class Author
void setBook (Book  b) {
    // ‘sets the variable to the value on the right’
    this.book = b;
}
Book(String t, Author a) {
    this.title = t;
    this.author.a 
    a.setBook(this);
    // effect: set this authors bookfield to the given book
    // sideeffect: none
    setBook
    code that have sideeffects are statements rather than expressions
    [Counter Example]
            be really careful testing when dealing with mutation
            void testAuthors() {
        Author mf = new AUthor(“MF”, 19500
                // in author
                boolean sameAuthor(Author that)
        return this.name.equals(that.name) &&
                this.yob == that.yob &&
                this.book.sameBook(that.book);
    }
    // in Book
    bool sameBook(Book that) {
        return this.title.equals(that.title) && this.author.sameAuthor(that.author);
        ‘cyclic data structure’
        → we need to define equality
        NOTE: we need new techniques for cyclic data
        -return nothing
                -mutation
                → don’t use mutation unless you need to
                if(a) return true
                        else truen b || c 
                        return a || b || c
