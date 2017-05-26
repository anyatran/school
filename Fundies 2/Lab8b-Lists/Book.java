
// Represents a Book
public class Book{
    String title;
    String author;
    int price;     // in dollars
    
    public Book(String title, String author, int price){
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    /* Template
     *   Fields
     *     ... this.title ...       -- String
     *     ... this.author ...      -- String
     *     ... this.price ...       -- int
     *
     *   Methods 
     *     ... this.mmm() ...       -- ??
     */

    public String toString(){
        return this.title.concat(", ").concat(this.author.concat(", ").concat(String.valueOf(this.price)));
    }
}
