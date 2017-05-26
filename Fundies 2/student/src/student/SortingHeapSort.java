package student;
import java.util.*;

import sorting.SortAlgorithm;

abstract class SortingHeapSort<T> implements SortAlgorithm<T>{

  /** The <code>ArrayList</code> to sort */
  public ArrayList<T> alist;
  
  /**
   * Initialize the list to be sorted from the given data.
   * @param alist the <code>ArrayList</code> to sort
   */
  public void init(ArrayList<T> alistin){
    this.alist = new ArrayList<T>();
    for (T t: alistin){
      this.alist.add(t);
    }
  }
  
  
  /**
   * Sort the given <code>ArrayList</code> using the given 
   * <code>Comparator</code>. Return the sorted result.
   * For <em>in-place</em> algorithms <code>alist</code> should be
   * the result.
   * @param alist the <code>ArrayList</code> to sort
   * @param comp the <code>Comparator</code> that determines the ordering
   * @return the sorted <code>ArrayList</code>
   */
  public ArrayList<T> sort(Comparator<T> comp){
    return heapsort(this.alist, comp);
  }
  
  abstract public ArrayList<T> heapsort(ArrayList<T> alist, 
                                        Comparator<T> comp);
 
}
    