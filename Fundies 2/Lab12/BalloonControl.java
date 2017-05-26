import edu.neu.ccs.util.*;
import edu.neu.ccs.console.*;
import edu.neu.ccs.gui.*;

import jptdraw.*;

/**
 * An input channel that generates a Balloon object from GUI input.
 * 
 * @since  4 April 2010
 * @author Viera K. Proulx
 */
class BalloonControl extends DisplayPanel
implements ConsoleAware, JPTConstants{
  
  /*--------------------------------------------------------------
   Member data
   *------------------------------------------------------------*/
  
  /** The GUI for user input for Balloon information --- the View*/
  public BalloonInputView bView;
  
  /** The Balloon object --- the Model*/
  public Balloon b = new Balloon();
  
  /** The panel that holds the graphics window for painting the balloon */
  public BufferedPanel window;

  /** The 'draw' and 'idraw' library wrapper for the graphics window */
  public JPTCanvas theCanvas;

  /** The paint action. */
  protected SimpleAction paintAction =
    new SimpleAction("Errase") {
    public void perform() { 
      paintAction();
    }
  };
  
  /** The new Balloon action. */
  protected SimpleAction newAction =
    new SimpleAction("New") {
    public void perform() { 
      newAction();
    }
  };
  
  /** The cancel action. */
  protected SimpleAction cancelAction =
    new SimpleAction("Cancel") {
    public void perform() { cancelAction(); }
  };
  
  /**
   * The constructor
   * @param window the <code>BufferedPanel</code> to initialize theCanvas
   */
  BalloonControl(BufferedPanel window){	

    this.window = window;	
    this.theCanvas = new JPTCanvas(window);
    
    // build the GUI for input of one Balloon object
    this.bView = new BalloonInputView();
    TablePanel tp = new TablePanel(new Object[]{this.bView, 
        this.paintAction, 
        this.newAction, 
        this.cancelAction},
        VERTICAL);
    
    add(tp);								 
  }
  
  /*--------------------------------------------------------------
   Action classes that define the button behavior
   *------------------------------------------------------------*/
  /**
   * New button action: get the balloon values from the GUI 
   *                    and print them
   */
  protected void newAction(){
    this.b.paintBalloon(theCanvas);
    console.out.println(this.b); 	
  }
  
  /**
   * Paint button action: show the current <code>Balloon</code>
   * in theCanvas
   */
  protected void paintAction(){
    this.b.eraseBalloon();; 	
  }
  
  /**
   * Cancel button action: allows to cancel the input if input error
   * cannot be corrected or we abandon the creation of a new Balloon.
   */
  protected void cancelAction(){
    console.out.println("Balloon data not read");
  }
}
