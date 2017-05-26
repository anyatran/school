import java.util.ArrayList;


/**
 * Represents a book
 * @author Viera K. Proulx
 */
public class Book{
    /** The title of this book */
    String title;

    /** The author of this book */
    String author;

    /** The price of this book in whole dollars */
    int price;     

    /**
     * The standard full constructor
     * @param title the title of this book
     * @param author the author of this book
     * @param price the price of this book in whole dollars
     */
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

}


class MapBook{


    //produces an ArrayList of Strings that are the titles of the books in the given list
    public ArrayList<String> mapBookTitle(ArrayList<Book> listb){
        // initialize
        ArrayList<String> result = new ArrayList<String>();

        // add the titles to the list
        for(Book b: listb){
            result.add(b.title);
        }
        // return the accm
        return result;
    }

    public ArrayList<String> mapBookAuthor(ArrayList<Book> listb){
        ArrayList<String> result = new ArrayList<String>();

        for(Book b: listb){
            result.add(b.author);
        }
        return result;
    }

    public ArrayList<Integer> mapBookPrice(ArrayList<Book> listb){
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (Book b: listb){
            result.add(b.price);
        }
        return result;
    }

    public <T> ArrayList<T> mapBookTfor(ArrayList<Book> listb, Book2T<T> pred){
        ArrayList<T> result = new ArrayList<T>();

        for(int index = 0; index < listb.size(); index = index + 1){
            result.add(pred.apply(listb.get(index)));
        }
        return result;
    }
}


interface Book2T<T>{
    public T apply(Book b);
}

class Book2Title implements Book2T<String>{
    public String apply(Book b){
        return b.title;
    }
}

class Book2Author implements Book2T<String>{
    public String apply(Book b){
        return b.author;
    }
}

class Book2Price implements Book2T<Integer>{
    public Integer apply(Book b){
        return b.price;
    }
}



/**
 * Represents a song
 * @author Viera K. Proulx
 */

class Song{
    /** The title of this song */
    String title;

    /** The artist of this song */
    String artist;

    /** The duration of this song in seconds */
    int duration;  

    /**
     * The standard full constructor
     * @param title the title of this song
     * @param artist the artist of this song
     * @param duration the duration of this song in seconds
     */
    public Song(String title, String artist, int duration){
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    /* Template
     *   Fields
     *     ... this.title ...       -- String
     *     ... this.artist ...      -- String
     *     ... this.duration ...    -- int
     *
     *   Methods 
     *     ... this.mmm() ...       -- ??
     */

}
class ExamplesBookSongs {

    /** A sample <code>ArrayList</code> */
    ArrayList<Book> arblist = new ArrayList<Book>();
    ArrayList<Song> arslist = new ArrayList<Song>();


    Book oms = new Book("Old Man and the Sea", "Hemingway", 30);
    Book eos = new Book("Elements of Style", "EBW", 20);
    Book htdp = new Book("HtDP", "MF", 60);
    Book ll = new Book("Little Lisper", "MF", 25);

    /**
     * EFFECT:
     * Initialize the <code>ArrayList</code> of songs with four data items
     * sorted by the price
     */
    void initBookList(){
        this.arblist.add(this.eos);
        this.arblist.add(this.ll);
        this.arblist.add(this.oms);
        this.arblist.add(this.htdp);
    }

    Song help = new Song("Help", "Beatles", 283);
    Song hotelc = new Song("Hotel California", "Eagles", 276);
    Song yesterday = new Song("Yesterday", "Beatles", 195);

    /**
     * EFFECT:
     * Initialize the <code>ArrayList</code> of songs with three data items
     * sorted by the duration
     */
    void initSongList(){
        this.arslist.add(this.yesterday);
        this.arslist.add(this.hotelc);
        this.arslist.add(this.help);
    }

}