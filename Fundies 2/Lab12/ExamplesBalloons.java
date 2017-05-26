import java.awt.Color;
import tester.*;

/**
 * A sample of tests for the classes that handle user interactions
 * with the <code>Balloon</code> data.
 * 
 * @author Viera K. Proulx
 * @since 4 April 2010
 */
public class ExamplesBalloons{

    public ExamplesBalloons(){}
    BalloonControl bc;
    Balloon b1;
    Balloon b2;
    Balloon b3;
    void init(){
        /** sample Balloon instance */
        b1 = new Balloon(100, 200, 25, Color.red);

        //ther sample Balloon instance */
        b2 = new Balloon(200, 100, 25, Color.blue);

        //ther sampel Balloon */
        b3 = new Balloon(200, 100, 25, Color.green);
        
        bc = new BalloonControl(null);
    }

    /**
     * The test suite for the class <code>Balloon</code>
     * @param t The instance of the <code>Tester</code> that manages the tests
     */
    public void tests(Tester t){
        init();
        this.b3.eraseBalloon();
        // test the methods 'equal' in the class Balloon
        t.checkExpect(new Balloon(200, 100, 25, Color.blue), this.b2, "equal");

        // test the methods 'hashCode' in the class Balloon
        t.checkExpect((new Balloon(200, 100, 25, Color.blue)).hashCode(), 
                this.b2.hashCode(), "hashCode");	

        // test the methods 'getDiameter' in the class Balloon
        t.checkExpect(this.b2.getDiameter(), 50, "getDiameter");

        // test the methods 'moveBalloon' in the class Balloon
        t.checkExpect(this.b1.moveBalloon(50, -50), new Balloon(150, 150, 25, Color.red),
                "moveBalloon");

        // test the methods 'distanceFromTop' in the class Balloon
        t.checkExpect(this.b1.distanceFromTop(), 200 - 25, "distanceFromTop");

        // test the methods 'isHigherThan' in the class Balloon
        t.checkExpect(this.b1.isHigherThan(this.b2), false, "isHigherThan");
        
        t.checkExpect(this.b3.c, Color.white);

        

        /*
     t.checkExpect(b2.paintBalloon(window), b1.paintBalloon(window),
     "paintBalloon");
         */
    }
}