import java.util.*;
import tester.*;

/** Class to hold examples of data and tests 
 * @author CS2510
 * @version 11-09-2013
 */
public class ExamplesWords{
    
    /** Test Word Classes... 
     *
     * @param t the tester instance that runs the tests
     */
    public void testWords(Tester t){        
        
        t.checkExpect(new Word("hey").equals(new Word("hey")), true);
        t.checkExpect(new Word("hey").equals(new Word("you")), false);
        
        WordCounter wc = new WordCounter();
        wc.countWords(new StringIterator(new StringBuffer("The Words Are")));
        t.checkExpect(wc.words(), 3);
        
        WordCounter macbeth = new WordCounter();
        macbeth.countWords(new StringIterator("Macbeth.txt"));
        macbeth.printWords(10);
    }
    
    /** Test Word Classes... 
     *
     * @param t the tester instance that runs the tests
     */
    public void testStringIter(Tester t){
        StringIterator wrds = 
                new StringIterator(new StringBuffer("The Words Are"));
        
        int i = 0;
        for(Word w : wrds){
            System.out.println(" Word["+ (i++) +"] : "+w);
        }
    }
}