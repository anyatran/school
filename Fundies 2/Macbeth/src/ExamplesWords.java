import java.util.*;

import tester.*;

/** Class to hold examples of data and tests 
 * @author CS2510
 * @version 11-09-2013
 */
public class ExamplesWords {
    Word hey = new Word("hey");
    Word hey1 = new Word("hey");
    Word you = new Word("you");
    Word hello = new Word("hello");
    Comparator<Word> heybyfreq = hey.new WordsByFreq();
    Comparator<Word> hey1byfreq = hey1.new WordsByFreq();
    Comparator<Word> youbyfreq = you.new WordsByFreq();


    public void testWords(Tester t) {     

        this.hey.increment();
        t.checkExpect(this.hey.equals(this.hey1), true);
        t.checkExpect(this.hey.equals(this.you), false);
        t.checkExpect(this.hey.equals("hey"), false);
        t.checkExpect(this.hey.hashCode(), 103196);
        t.checkExpect(this.hey1.hashCode(), 103196);
        t.checkExpect(this.hey.toString(), "hey, 2");
        t.checkExpect(this.you.toString(), "you, 1");
        t.checkExpect(this.hey.counter, 2);
        t.checkExpect(this.hey1byfreq.compare(this.hey, this.you), -1);
        t.checkExpect(this.youbyfreq.compare(this.you, this.hey), 1);


        WordCounter wc = new WordCounter();
        WordCounter wc1 = new WordCounter();
        wc.countWords(new StringIterator(new StringBuffer(
                "The Words Are The Words Is is Is is")));
        wc1.countWords(new StringIterator(new StringBuffer()));

        wc.printWords(2);
        t.checkExpect(wc.words(), 4);
        t.checkExpect(wc.get("is").w, "is");
        t.checkExpect(wc.get("is").counter, 4);
        t.checkExpect(wc.size(), 4);

        WordCounter macbeth = new WordCounter();
        macbeth.countWords(new StringIterator("Macbeth.txt"));
        macbeth.printWords(10);
        t.checkExpect(macbeth.get("macbeth").counter, 288);
        t.checkExpect(t.checkExpect(macbeth.get("the").counter, 732));
        t.checkExpect(macbeth.words(), 3201);
    }

    /** Test Word Classes... 
     *
     * @param t the tester instance that runs the tests
     */
    public void testStringIter(Tester t) {
        StringIterator wrds = 
                new StringIterator(new StringBuffer("The Words Are"));

        int i = 0;
        for (Word w : wrds) {
            System.out.println(" Word[" + (i++) + "] : " + w);
        }
    }
}