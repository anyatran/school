import tester.*;
import java.util.*;

/**
 * A class designed to explore and validate the methods defined in the 
 * <code>ArrayList</code> class.
 * 
 * @since 23 February 2013
 */
class ExamplesLecture{
  ExamplesLecture(){}
  
  /** A sample <code>ArrayList</code> of books */
  ArrayList<Book> arblist = new ArrayList<Book>();
  
  /** A samples <code>ArrayList</code> of songs */
  ArrayList<Song> arslist = new ArrayList<Song>();

  /** examples of books */
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
  Song hotelc = new Song("Hote l California", "Eagles", 276);
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
   * Produce the first item in the given <code> ArrayList</code>. 
   * Return <CODE>null</CODE> if the <code>ArrayList<code> is empty.
   * 
   * @param alist an <code>ArrayList</code> of elements of the type T.
   * @return the first item in the given list or <code.NULL,/code.
   */
  <T> T firstItem(ArrayList<T> alist){
    if (alist.isEmpty())
      return null;
    else
      return alist.get(0);
  }
  
  void testFirstItem(Tester t){
    t.checkExpect(this.firstItem(arblist), null);
    this.initBookList();
    t.checkExpect(this.firstItem(arblist), this.eos);
    ArrayList<Book> blist2 = arblist;
    t.checkExpect(this.firstItem(blist2), this.eos);
    ArrayList<Book> blist3 = new ArrayList<Book>(arblist);
    t.checkExpect(this.firstItem(blist3), this.eos);
    this.arblist.remove(0);
    t.checkExpect(this.firstItem(blist3), this.eos);
    //t.checkExpect(this.firstItem(blist2), this.eos);
    
    t.checkExpect(blist2.get(0).title, "Little Lisper");
    t.checkExpect(blist3.get(1).title, "Little Lisper");
    t.checkExpect(arblist.get(0).title, "Little Lisper");
    
  }
  
  /**
   * The main method that runs the program
   * @param argv ignored
   */  
    public static void main(String[] argv){
        ExamplesBooksSongs el = new ExamplesBooksSongs();
    
    Tester.run(el);
  }
}
  