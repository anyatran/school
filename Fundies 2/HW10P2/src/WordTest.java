
import java.util.Comparator;
import java.util.HashMap;

import junit.framework.TestCase;

import org.junit.Test;
/**
 * WordTest is a JUnit test for Word
 * 
 * @author CS2510
 * @version 11-09-2013
 *
 */

public class WordTest extends TestCase {
    /**
     */
    Word hey = new Word("hey");
    
    /**
     */
    Word hey1 = new Word("hey");
    /**
     */
    Word you = new Word("you");
    /**
     */
    Word you1 = new Word("you");
    /**
     */
    Word hello = new Word("hello");
    /**
     */
    HashMap<String, Word> h1 = new HashMap<String, Word>();
    /**
     */
    Comparator<Word> heybyfreq = hey.new WordsByFreq();
    /**
     */
    Comparator<Word> hey1byfreq = hey1.new WordsByFreq();
    /**
     */
    Comparator<Word> youbyfreq = you.new WordsByFreq();

    /**
     * initializing the test
     */
    void init() {
//        hey = new Word("hey");
//        hey1 = new Word("hey");
//        you = new Word("you");
//        hello = new Word("hello");
//        h1 = new HashMap<String, Word>();
//        heybyfreq = hey.new WordsByFreq();
//        hey1byfreq = hey1.new WordsByFreq();
//        youbyfreq = you.new WordsByFreq();
        h1.put("hey", hey);
        h1.put("hey", hey1);
        h1.put("you", you);


    }
    /**
     */
    @Test
    public void testHashCode() {     
        init();
        this.hey.increment();
        assertEquals(this.hey.w.hashCode(), 103196);
        assertEquals(this.hey1.w.hashCode(), 103196);
        assertEquals(this.you.hashCode(), 119839);
        assertEquals(this.you1.hashCode(), 119839);
        
        
    }
    /**
     */
    public void testEqual() {     
        init();
        this.hey.increment();
        String s = "Hello";
        assertFalse(this.hey.equals(s));
        assertEquals(this.hey, this.hey1);
        assertFalse(this.hey.equals(this.you));
        assertNotNull(this.hey);
    }
    /**
     */
    public void testToString() {
        init();
        this.hey.increment();
        assertEquals(this.hey.toString(), "hey, 2");
        assertEquals(this.you.toString(), "you, 1");
        assertEquals(this.hey.counter, 2);

    }
    /**
     */
    public void testCompare() {
        init();
        this.hey.increment();
        assertEquals(this.hey1byfreq.compare(this.hey, this.you), -1);
        assertEquals(this.youbyfreq.compare(this.you, this.hey), 1);



        WordCounter wc = new WordCounter();
        WordCounter wc1 = new WordCounter();
        wc.countWords(new StringIterator(new StringBuffer(
                "The Words Are The Words Is is Is is")));
        wc1.countWords(new StringIterator(new StringBuffer()));

        wc.printWords(2);
        assertEquals(wc.words(), 4);
        assertEquals(wc.get("is").w, "is");
        assertEquals(wc.get("is").counter, 4);
        assertEquals(wc.size(), 4);

        WordCounter macbeth = new WordCounter();
        macbeth.countWords(new StringIterator("Macbeth.txt"));
        macbeth.printWords(10);
        assertEquals(macbeth.get("macbeth").counter, 288);
        assertEquals(macbeth.get("the").counter, 732);
        assertEquals(macbeth.words(), 3201);
    }


}













