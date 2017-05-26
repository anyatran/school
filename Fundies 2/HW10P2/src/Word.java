import java.util.*;

/** represents a word and its number of occurrences
 * @author CS2510
 * @version 11-09-2013
 * Template:
 Fields:
 this.s               -- String
 this.freq            -- int
 Methods:
 this.equals(Object)  -- boolean
 this.hashCode()      -- int
 this.toString()      -- String
 this.compare(Word,Word) -- int
 */

public class Word {
    /** This field holds the string value of the word */
    String w;
    /** This field holds the frequency of the word */
    int counter;
    /**
     * @param w a field of Word  
     * */
    public Word(String w) {
        /**  */
        this.w = w;
        /**  */
        this.counter = 1;
    }

    /** Is this Word equal to the given Object 
     * @param obj that was given
     * @return true if the object is the same as this word
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Word)) {
            return false;
        }
        Word a = (Word)obj;
        return this.w.equals(a.w);
    }

    /** Produce this Word's hashCode 
     * @return a hashcode of a Word
     */
    public int hashCode() {
        return this.w.hashCode();
    }

    /** Produce a String representation of this Word 
     * @return a String version of the Word
     */
    public String toString() {
        return  this.w + ", " + this.counter;
    }
    /** increases the frequency by 1
     */
    public void increment() {
        this.counter++;
    }

    
    /**  compares words by frequencies
     */
     
    public class WordsByFreq implements Comparator<Word> {
        /**
         * @param n and 
         * @param m are words
         * @return the difference between m and n
         */
        public int compare(Word n, Word m) {
            return m.counter - n.counter;
        }
    }

}







