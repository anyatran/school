import java.awt.*;
import edu.neu.ccs.console.*;
import edu.neu.ccs.gui.*;
import edu.neu.ccs.filter.*;

/** 
 * An input channel to generate a Balloon object from the console input.
 *  
 * @since 31 March 2010
 * @author Viera K. Proulx
 */
class ConsoleBalloonInput implements ConsoleAware{
  
  /** The constructor */
  public ConsoleBalloonInput(){ }
  
  /*--------------------------------------------------------------
   Methods
   *------------------------------------------------------------*/
  /**
   * Request the values for the fields of a new <code>Balloon</code> 
   * instance from the console. Report errors and re-try.
   * Throw <code>CancelledException</code> if no data is given.
   */
  public Balloon requestBalloon () throws CancelledException{
    return new Balloon(
        console.in.requestInt("x: "),
        console.in.requestInt("y: "),
        console.in.requestInt("radius: "),
        new Color(requestShade("red shade: " ),
            requestShade("green shade: "),
            requestShade("blue shade: ")));
  }
  
  /**
   * Demand the values for the fields of a new <code>Balloon</code> 
   * instance from the console. Report errors and re-try till successful.
   */
  public Balloon demandBalloon(){
    return new Balloon
    (console.in.demandInt("x: "),
        console.in.demandInt("y: "),
        console.in.demandInt("radius: "),
        new Color(demandShade("red shade: " ),
            demandShade("green shade: "),
            demandShade("blue shade: ")));
  }
  
  /**
   * Demand the value of the color shade - must be in the range from 0 to 255
   * Report errors and re-try till successful.
   */
  protected int demandShade(String s){
    return console.in.demandInt(s, 0, new RangeFilter.Long(0, 255));
  }
  
  /**
   * Demand the value of the color shade - must be in the range 
   * from 0 to 255. Report errors and re-try.
   * Throw <code>CancelledException</code> if no data is given.
   */
  protected int requestShade(String s) throws CancelledException{
    return console.in.requestInt(s, new RangeFilter.Long(0, 255));
  }
}
