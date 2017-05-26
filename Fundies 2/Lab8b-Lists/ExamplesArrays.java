import tester.*;
import java.util.*;

/**
 * A class designed to explore and validate the methods defined in the 
 * <code>ArrayList</code> class.
 * 
 * @since 23 February 2013
 */
class ExamplesArrayList{
    ExamplesArrayList(){}

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

    /**
     * EFFECT:
     * Run the tests to validate the design of the <code>ArrayList</code> class.
     * 
     * @param t The tester that runs the tests and reports the results
     */
    public void testArrayList(Tester t){
        // we cannot assume the arblist is empty
        this.arblist.clear();
        t.checkExpect(this.arblist.size(), 0);  
        this.initBookList();
        t.checkExpect(this.arblist.size(), 4);

        // we cannot assume the arslist is empty
        this.arslist.clear();
        t.checkExpect(this.arslist.size(), 0); 
        this.initSongList();
        t.checkExpect(this.arslist.size(), 3);    
    }

    /**
     * Test the methods set and get for the class <code>ArrayList</code>
     *
     * @param t The tester that runs the tests and reports the results
     */
    public void testGet(Tester t){
        // initialize the data to use in this set of tests
        this.initSongList();

        t.checkExpect(this.arblist.get(0), this.eos);
        t.checkExpect(this.arslist.get(2), this.help);

        Song bobby = new Song("Me and my Bobby McGee", "Janis", 297);

        // invoke the method and test the result
        t.checkExpect(this.arslist.set(2, bobby), this.help);

        // verify the effects
        t.checkExpect(this.arslist.get(2), bobby);
    }

    /**
     * Run the tests defined in the <code>ExamplesArrayList</code> class.
     * 
     * @param argv unused
     */
    public static void main(String[] argv){
        ExamplesArrayList e = new ExamplesArrayList();
        // run tests and report the results: print all test results, print all data
        Tester.runReport(e, true, true);
    }
}