import edu.neu.ccs.console.*;
import edu.neu.ccs.gui.*;

/** 
 * An input channel to generate a Balloon object from GUI input.
 *  
 * @since 31 March 2010
 * @author Viera K. Proulx
 */
class GUIBalloonInput implements ConsoleAware{
 
  /** The view used to provide and display the <code>Balloon</code> data */
  BalloonInputView bGUI;

  /** The constructor */
  GUIBalloonInput(){		
    
    // build the GUI for input of one Balloon object
    this.bGUI = new BalloonInputView();
    GeneralDialog.showOKDialog(this.bGUI, "Balloon Input");
  }
  
  /**
   * Request the values for the fields of a new <code>Balloon</code> 
   * instance from the BalloonInputView. Report errors and re-try.
   * Throw <code>CancelledException</code> if no data is given.
   */
  public Balloon requestBalloon() throws CancelledException{
    return this.bGUI.requestBalloon();
  }
  
  /**
   * Demand the values for the fields of a new <code>Balloon</code> 
   * instance from the BalloonInputView. 
   * Report errors and re-try till successful.
   */
  public Balloon demandBalloon(){
    return this.bGUI.demandBalloon();
  }
}
