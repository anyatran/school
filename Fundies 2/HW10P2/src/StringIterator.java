
import java.util.Iterator;
import java.io.*;

/** StringIterator is a concrete class for iterating over all the
 *    words in a StringBuffer or text file.  At present a word is defined
 *    to be a maximal contiguous sequence of English letters.
 * @author CS2510
 * @version 11-09-2013
 
 */
public class StringIterator implements Iterator<Word>, Iterable<Word> {
    
    /** defines the parsing strategy */
    private StreamTokenizer tok;
    
    /** 
     * Create a StringIterator over the words in the given filename 
     * @param filename the name of file to read
     
     */
    public StringIterator(String filename) {
        try {
            FileInputStream fin = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fin);
            BufferedReader br = new BufferedReader(isr);
            this.tok = wordTokenizer(br);
        }
        catch (FileNotFoundException e) {
            this.tok = wordTokenizer(new StringReader(""));
            System.err.println("StringIterator: File \"" + filename + 
                                "\" not found.");
        }
    }
    
    /** Create a StringIterator over the words in the given StringBuffer 
     * @param sb the buffer to hold the text we read
     
     */
    public StringIterator(StringBuffer sb) {
        this.tok = wordTokenizer(new StringReader(sb.toString()));
    }
    
    /** Is there another word in this StringIterator
     * @return true if there are more words to process
     */
    public boolean hasNext() {
        int tt = nextToken();
        while ((tt != StreamTokenizer.TT_EOF) && 
               (tt != StreamTokenizer.TT_WORD)) {
            tt = nextToken();
        }
        // Pretend we haven't seen this token yet.
        this.tok.pushBack();
        return tt == StreamTokenizer.TT_WORD;
    }
    
    /** Return the next word in the Iterator 
     * @return the next word in our text
     */
    public Word next() {
        int tt = nextToken();
        while ((tt != StreamTokenizer.TT_EOF) && 
               (tt != StreamTokenizer.TT_WORD)) {
            tt = nextToken();
        }
        if (tt == StreamTokenizer.TT_WORD) {
            return new Word(tok.sval);
        }
        throw new RuntimeException(eofError);
    }
    
    /** Not implemented, not needed. */
    public void remove() {
        throw new RuntimeException("StringIterator: Remove Not Possible");
    }
    
    /** Make this iterator available for FOR-EACH loops 
     * @return the iterator over our input text
     */
    public Iterator<Word> iterator() { 
        return this; 
    }
    
    
    /** Behaves like tok.nextToken(), but catches any IOException
     *    and treats it as though it were the end of input. 
     * @return the next word, or end of input if an exception is thrown
     */
    private int nextToken() {
        int tt = 0;
        try {
            tt = tok.nextToken();
        }
        catch (IOException e) {
            tt = StreamTokenizer.TT_EOF;
        }
        return tt;
    }
    
    /** error message */
    private String eofError = "Tried to read past end of input.";
    
    /** Given a Reader, returns a StreamTokenizer for that Reader
     *    that parses words. 
     * @param in the input reader instance
     * @return the <code>StreamTokenizer</code> for our input text 
     */
    private static StreamTokenizer wordTokenizer(Reader in) {
        StreamTokenizer tok = new StreamTokenizer(in);
        tok.resetSyntax();
        tok.lowerCaseMode(true);
        tok.wordChars('a', 'z');
        tok.wordChars('A', 'Z');
        tok.eolIsSignificant(false);
        return tok;
    }
}
