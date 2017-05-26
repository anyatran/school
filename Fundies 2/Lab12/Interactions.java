import java.awt.*;

import edu.neu.ccs.gui.*;
import edu.neu.ccs.jpf.*;

import jptdraw.*;
import colors.*;

import tester.*;

/**
 * Class to construct Java Power Frame that allows us to execute repeatedly
 * every method that is <code>public void</code> with no arguments from
 * a GUI that includes a buffered graphics <code>JPTCanvas</code> panel.
 * 
 * @author Viera K. Proulx (using code modified by Richard Rasala)
 * @since 31 April 2010
 */
public class Interactions extends JPF 
{
  /** the wrapper for the JPT <code>BufferedPanel window</code> */
  JPTCanvas theCanvas;
  
  /** Here is where the twine of reflection starts */
  public static void main(String[] args) { 
    new Interactions();
  }	
  
  /** the constructor - creates a new instance of <code>theCanvas</code> */
  Interactions(){
    super();
    this.theCanvas = new JPTCanvas(this.window);
  }
  
  /** A method that invokes the test suite for this project */
  public void runTestSuite(){
    ExamplesBalloons e = new ExamplesBalloons();
    Tester.runFullReport(e);
  }
  
  /** A method that creates the GUI input to demand one balloon */
  public void runGuiInputDemand(){
    
    // initialize a balloon using the static demandOneBalloon method
    // for BalloonInputView
    console.out.println("The balloon is initialized by a static method " + 
                        "'demandOneBalloon' for BalloonInputView.\n");
    
    Balloon b = BalloonInputView.demandOneBalloon();
    
    console.out.println("The balloon values are:\n" + b);
    console.out.println("The balloon will be shown in the Canvas\n");
    b.paintBalloon(this.theCanvas);
  }
  
  /** A method that creates the GUI input to request one balloon */
  public void runGuiInputRequest(){
    
    try{
      // initialize a balloon using the static requestOneBalloon 
      // method for BalloonInputView
      console.out.println("The balloon is initialized by a static method " + 
                          "'requestOneBalloon' for the BalloonInputView.\n");
      Balloon b = BalloonInputView.requestOneBalloon();

      console.out.println("The balloon values are:\n" + b);
      console.out.println("The balloon will be shown in the Canvas\n");
      b.paintBalloon(this.theCanvas);
    } 
    catch(CancelledException e){
      console.out.println("No balloon given");
    }		
  }
  
  /** 
   * A method that demands one Balloon from the console (modal with 
   * default values given) and requests one Balloon from the console.
   * --- Report errors and re-try.
   * Throw <code>CancelledException</code> if no data is given.
   */
  public void runConsoleInput(){
    ConsoleBalloonInput cbi = new ConsoleBalloonInput();
    
    // demand a balloon and paint it
    (cbi.demandBalloon()).paintBalloon(this.theCanvas);
    
    // request a balloon
    try{
      (cbi.requestBalloon()).paintBalloon(this.theCanvas);
    }
    catch(CancelledException e){
      console.out.println("No balloon given");
    }
    
  }
  
  /**
   * Construct an OK dialog for Balloon data inputs 
   */
  public void runBalloonControl(){
    BalloonControl bc = new BalloonControl(this.window);
    GeneralDialog.showOKDialog(bc, "Balloon Control");		
  }
  
  /**
   * This method illustrates the draw methods available for drawing 
   * in the JPTCanvas graphics display window.
   */
  public void testJPTCanvas(){
    this.theCanvas.drawCircle(new Posn(20, 20), 20, Color.red);
    this.theCanvas.drawCircle(new Posn(20, 60), 20, new Red());
    this.theCanvas.drawDisk(new Posn(20, 100), 20, Color.blue);
    this.theCanvas.drawDisk(new Posn(20, 140), 20, new Blue());
    this.theCanvas.drawRect(new Posn(100, 100), 80, 60, Color.red);
    this.theCanvas.drawRect(new Posn(300, 300), 60, 80, Color.yellow);
    this.theCanvas.drawLine(new Posn(220, 220), new Posn(120, 120), Color.red);
    this.theCanvas.drawLine(new Posn(220, 120), new Posn(120, 220), Color.green);
    this.theCanvas.drawString(new Posn(200, 200), "finito");
  }
  
}
