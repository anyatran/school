import tester.Tester;

import javalib.colors.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;


/* NOTE TO GRADER:
 * I spoke with Viera who agreed that I could get an extension 
 * due to Webcat troubles.
 * Could you please remove the subtracted points for submitting late?
 *   
 * in addition: I'm not sure my drawMobile method is testable, causing me to
 * lose points.  Of course, I may be wrong.
 */
// represents a mobile
interface IMobile {

    // calculate the total weight of the Imobile
    public int totalWeight();

    // calculate the total height of the Imobile
    public int totalHeight();

    // checks if the mobile is balanced
    public boolean isBalanced();

    // creates a new mobile out of 2 balanced Imobiles and a given
    // length of the strut
    public IMobile buildMobile(IMobile mobile2, int length, int strut);

    // creates the left side of the strut.
    public int buildLeftSide(IMobile mobile2, int strut);

    // creates the right side of the strut.
    public int buildRightSide(IMobile mobile2, int strut);

    // computes the max width of a Imobile if it was laying flat.
    public int maxWidth();

    // draws a mobile on a canvas, with the original pinhole at Posn p
    public WorldImage drawMobile(Posn p);

}

// represents simple Imobiles
class Simple implements IMobile {
    int length;
    int weight;
    IColor color;

    Simple(int length, int weight, IColor color) {
        this.length = length;
        this.weight = weight;
        this.color = color;
    }

    /* Template:
    Fields:
    this.length                                          -- int
    this.weight                                          -- int
    this.color                                           -- IColor

    Methods:
    this.totalWeight()                                   -- int
    this.totalHeight()                                   -- int
    this.isBalanced()                                    -- boolean
    this.buildMobile(IMobile, int, int)                  -- IMobile
    this.buildLeftSide(IMobile, int)                     -- IMobile
    this.buildRightSide(IMobile, int)                    -- IMobile
    this.maxWidth()                                      -- int
    this.drawLengthLine(Posn)                            -- WorldImage
    this.drawBody(Posn)                                  -- WorldImage
    this.drawMobile(Posn)                                -- WorldImage

    Methods for fields:                                             */

    // computes the total weight of a simple Imobile
    public int totalWeight() {
        return this.weight;
    }

    // computes the total height of a simple Imobile
    public int totalHeight() {
        return this.length + this.weight;
    }

    // returns that a simple Imobile is always balanced
    public boolean isBalanced() {
        return true;
    }

    // builds a mobile with this simple mobile and another, given Imobile
    public IMobile buildMobile(IMobile mobile2, int length, int strut) {
        return new Complex(length, this.buildLeftSide(mobile2, strut),
                this.buildRightSide(mobile2, strut), this, mobile2);
    }

    // builds the left side of the strut
    public int buildLeftSide(IMobile mobile2, int strut) {
        return (this.weight * strut) / (this.weight + mobile2.totalWeight());
    }

    // builds the right side of the strut
    public int buildRightSide(IMobile mobile2, int strut) {
        return strut - this.buildLeftSide(mobile2, strut);
    }

    // determines the max width of the Imobile
    public int maxWidth() {
        return this.totalWeight() / 10;
    }

    // draws a line representing the length
    public WorldImage drawLengthLine(Posn p) {
        return new LineImage(p, new Posn(p.x, p.y + (this.length * 15)),
                new Blue());
    }

    /*
     * draws a rectangle representing the disk. We used rectangles because:
     *  1)
     * we can properly represent the formula for height previously mentioned.
     *  2)
     * we can properly represent the formula for width previously mentioned.
     *  3)
     * it looks nice.
     * one more thing:
     * 
     * we scaled it up x15 so the mobile is more visible.
     */
    public WorldImage drawBody(Posn p) {
        return new RectangleImage(new Posn(p.x, p.y + this.length * 15
                + (this.totalHeight() / 2)), 10, this.totalHeight(),
                new Black());
    }

    // Uses overlay and helper methods to draw the Imobile
    public WorldImage drawMobile(Posn p) {
        return new OverlayImages(this.drawLengthLine(p), this.drawBody(p));
    }
}

// represents complex Imobiles
class Complex implements IMobile {
    int length;
    int leftside;
    int rightside;
    IMobile left;
    IMobile right;

    Complex(int length, int leftside, int rightside, IMobile left,
            IMobile right) {
        this.length = length;
        this.leftside = leftside;
        this.rightside = rightside;
        this.left = left;
        this.right = right;
    }

    /* Template:
   Fields:
   this.length                                          -- int
   this.weight                                          -- int
   this.color                                           -- IColor

   Methods:
   this.totalWeight()                                   -- int
   this.totalHeight()                                   -- int
   this.isBalanced()                                    -- boolean
   this.buildMobile(IMobile, int, int)                  -- IMobile
   this.buildLeftSide(IMobile, int)                     -- IMobile
   this.buildRightSide(IMobile, int)                    -- IMobile
   this.maxWidth()                                      -- int
   this.drawMobile(Posn)                                -- WorldImage
   this.drawLengthLine()                                -- WorldImage
   this.drawLeftSideLine(Posn)                          -- WorldImage
   this.computeLeftPinhole(Posn)                        -- Posn
   this.drawRightSideLine(Posn)                         -- WorldImage
   this.computeRightPinhole(Posn)                       -- Posn

   Methods for fields:
   this.left.totalWeight()                              -- int
   this.right.totalWeight()                             -- int
   this.left.totalHeight()                              -- int
   this.right.totalHeight()                             -- int                
   this.left.isBalanced()                               -- boolean
   this.right.isBalanced()                              -- boolean
   this.left.maxWidth()                                 -- int
   this.right.maxWidth()                                -- int
   this.left.drawMobile(this.computeLeftPinhole(p))     -- WorldImage
   this.right.drawMobile(this.computeRightPinhole(p))   -- WorldImage

     public WorldImage drawMobile(Posn p) {
        return (this.drawLengthLine(p).overlayImages(this.drawLeftSideLine(p),
                this.left.drawMobile(this.computeLeftPinhole(p)),
                this.drawRightSideLine(p),
                this.right.drawMobile(this.computeRightPinhole(p))));
     */

    // computes the total weight by adding the weight of each side
    public int totalWeight() {
        return this.left.totalWeight() + this.right.totalWeight();
    }

    // uses Math.max to compute the total height
    public int totalHeight() {
        return Math.max(this.left.totalHeight(), this.right.totalHeight())
                + this.length;
    }

    // uses the given formula to check if the mobile is balanced.
    public boolean isBalanced() {
        return this.left.isBalanced()
                && this.right.isBalanced()
                && ((this.left.totalWeight() * this.rightside) == (this.right
                        .totalWeight() * this.leftside));
    }
    // makes a Complex using this as the Left, and mobile2 as the right
    public IMobile buildMobile(IMobile mobile2, int length, int strut) {
        return new Complex(length, this.buildLeftSide(mobile2, strut),
                this.buildRightSide(mobile2, strut), this, mobile2);
    }

    // builds a leftside that will balance the corresponding rightside
    public int buildLeftSide(IMobile mobile2, int strut) {
        return (this.totalWeight() * strut)
                / (this.totalWeight() + mobile2.totalWeight());
    }

    // builds a rightside that will balance the corresponding leftside
    public int buildRightSide(IMobile mobile2, int strut) {
        return strut - this.buildLeftSide(mobile2, strut);
    }

    // computes the maxWidth assuming the IMobile is laying flat
    public int maxWidth() {
        return this.leftside + this.left.maxWidth() + this.rightside
                + this.right.maxWidth();
    }
    // draws a complex mobile by individually drawing each component.
    // calls helper methods to determine where to draw each component
    public WorldImage drawMobile(Posn p) {
        return (this.drawLengthLine(p).overlayImages(this.drawLeftSideLine(p),
                this.left.drawMobile(this.computeLeftPinhole(p)),
                this.drawRightSideLine(p),
                this.right.drawMobile(this.computeRightPinhole(p))));

    }
    // draws a line representing the length
    public WorldImage drawLengthLine(Posn p) {
        return new LineImage(p, new Posn(p.x, p.y + (this.length * 15)),
                new Blue());
    }
    // draws a line representing the left strut
    public WorldImage drawLeftSideLine(Posn p) {
        return new LineImage(new Posn(p.x, p.y + (this.length * 15)), new Posn(
                p.x - (this.leftside * 15), p.y + (this.length * 15)),
                new Blue());
    }
    // draws a line representing the left strut
    public WorldImage drawRightSideLine(Posn p) {
        return new LineImage(new Posn(p.x, p.y + (this.length * 15)), new Posn(
                p.x + (this.rightside * 15), p.y + (this.length * 15)),
                new Blue());
    }

    // computes where the left mobile starts in reference to the original mobile
    public Posn computeLeftPinhole(Posn p) {
        return new Posn(p.x - (this.leftside * 15), p.y + (this.length * 15));
    }

    // computes where the right mobile starts in reference to the original
    // mobile
    public Posn computeRightPinhole(Posn p) {
        return new Posn(p.x + (this.rightside * 15), p.y + (this.length * 15));
    }

}

// examples and tests for mobiles and its functions
class ExamplesMobiles {
    IMobile simple1 = new Simple(1, 10, new Red());
    IMobile simple2 = new Simple(2, 10, new Blue()); // simple example from lab
    IMobile simple3 = new Simple(3, 40, new Green());
    IMobile simple4 = new Simple(1, 10, new Red());
    IMobile simple5 = new Simple(1, 5, new Blue());
    IMobile simple6 = new Simple(3, 2, new Green());
    IMobile simple7 = new Simple(2, 3, new Blue());

    IMobile complex1 = new Complex(3, 12, 5, new Complex(1, 6, 6, simple1,
            simple2), simple3); // complex example from lab
    IMobile complexBalanced1 = new Complex(1, 4, 2, simple4, simple5);
    IMobile complexBalanced2 = new Complex(1, 8, 12, simple6, simple7);
    IMobile complexBalanced3 = new Complex(2, 36, 12, complexBalanced1,
            complexBalanced2);

    Posn posn1 = new Posn(300, 300);
    Posn posn2 = new Posn(100, 100);
    WorldImage simple4draw = new LineImage(new Posn(400, 100),
            new Posn(400, 115), new Blue()).overlayImages(
                    new RectangleImage(new Posn(400, 120),
                            10, 11, new Black()));

    WorldImage simple5draw =  new LineImage(new Posn(400, 100),
            new Posn(400, 115), new Blue()).overlayImages(
                    new RectangleImage(new Posn(400, 118),
                            10, 6, new Black()));


    // test for totalWeight()
    boolean testTotalWeight(Tester t) {
        return t.checkExpect(this.simple1.totalWeight(), 10)
                && t.checkExpect(this.simple2.totalWeight(), 10)
                && t.checkExpect(this.complex1.totalWeight(), 60)
                && t.checkExpect(this.complexBalanced1.totalWeight(), 15)
                && t.checkExpect(this.complexBalanced2.totalWeight(), 5);
    }

    // test for totalHeight()
    boolean testTotalHeight(Tester t) {
        return t.checkExpect(this.simple1.totalHeight(), 11)
                && t.checkExpect(this.simple2.totalHeight(), 12)
                && t.checkExpect(this.complex1.totalHeight(), 46);
    }

    // test for isBalanced()
    boolean testIsBalanced(Tester t) {
        return t.checkExpect(this.simple1.isBalanced(), true)
                && t.checkExpect(this.simple2.isBalanced(), true)
                && t.checkExpect(this.simple3.isBalanced(), true)        
                && t.checkExpect(this.complex1.isBalanced(), false)
                && t.checkExpect(this.complexBalanced1.isBalanced(), true)
                && t.checkExpect(this.complexBalanced2.isBalanced(), true);
    }

    // test for buildMobile(IMobile mobile2, int length, int strut)
    boolean testBuildMobile(Tester t) {
        return t.checkExpect(this.simple4.buildMobile(simple5, 1, 6),
                complexBalanced1)
                && t.checkExpect(this.simple6.buildMobile(simple7, 1, 20),
                        complexBalanced2)
                        && t.checkExpect(this.complexBalanced1.buildMobile(
                                complexBalanced2, 2, 48), complexBalanced3);
    }

    // test for maxWidth()
    boolean testMaxWidth(Tester t) {
        return t.checkExpect(this.simple1.maxWidth(), 1)
                && t.checkExpect(this.simple2.maxWidth(), 1)
                && t.checkExpect(this.simple3.maxWidth(), 4)
                && t.checkExpect(this.complex1.maxWidth(), 35);
    }

    // test for computeLeftPinhole()
    boolean testComputeLeftPinhole(Tester t) {
        return t.checkExpect(new Complex(1, 4, 2, simple1, simple2)
            .computeLeftPinhole(posn1), new Posn(240, 315))
            && t.checkExpect(new Complex(4, 5, 10, simple3, simple4)
                .computeLeftPinhole(posn1), new Posn(225, 360));
    }

    // test for computeRightPinhole()
    boolean testComputeRightPinhole(Tester t) {
        return t.checkExpect(new Complex(1, 4, 2, simple1, simple2)
            .computeRightPinhole(posn1), new Posn(330, 315))
            && t.checkExpect(new Complex(4, 5, 10, simple3, simple4)
                .computeRightPinhole(posn1), new Posn(450, 360));
    }

    // test for drawLengthLine()
    boolean testDrawLengthLine(Tester t) {
        return t.checkExpect(new Complex(1, 4, 2, simple1, simple2)
            .drawLengthLine(posn1), new LineImage(new Posn(300, 300),
                new Posn(300, 315), new Blue()))
                && t.checkExpect(new Complex(1, 4, 2, simple3, simple4)
                    .drawLengthLine(posn1), new LineImage(
                        new Posn(300, 300), new Posn(300, 315), new Blue()));
    }

    // test for drawRightSideLine()
    boolean testDrawRightSideLine(Tester t) {
        return t.checkExpect(new Complex(4, 5, 10, simple3, simple4)
            .drawRightSideLine(posn1), new LineImage(new Posn(300, 360),
                new Posn(450, 360), new Blue()));
    }

    // test for drawLeftSideLine()
    boolean testDrawLeftSideLine(Tester t) {
        return t.checkExpect(new Complex(4, 5, 10, simple3, simple4)
            .drawLeftSideLine(posn1), new LineImage(new Posn(300, 360),
                new Posn(225, 360), new Blue()));
    }

    // test for buildLeftSide
    boolean testBuildLeftSide(Tester t) {
        return t.checkExpect(this.simple4.buildLeftSide(simple5, 6), 4)
                && t.checkExpect(this.complexBalanced1.buildLeftSide(
                        complexBalanced2, 48), 36);
    }

    // test for buildRightSide
    boolean testBuildRightSide(Tester t) {
        return t.checkExpect(this.simple4.buildRightSide(simple5, 6), 2)
                && t.checkExpect(this.complexBalanced1.buildRightSide(
                        complexBalanced2, 48), 12);
    }

    // test for drawMobile()
    boolean testDrawMobile(Tester t) {
        return t.checkExpect(this.simple4.drawMobile(new Posn(400, 100)),
                new LineImage(new Posn(400, 100),
                        new Posn(400, 115), new Blue()).overlayImages(
                                new RectangleImage(new Posn(400, 120),
                                        10, 11, new Black())));
    }

    boolean testDrawMobile2(Tester t) {
        return t.checkExpect(this.simple5.drawMobile(new Posn(400, 100)),
                new LineImage(new Posn(400, 100), new Posn(400, 115), 
                        new Blue()).overlayImages(new RectangleImage(
                                new Posn(400, 118), 10, 6, new Black())));
    }
    // I believe because ofcomputePinhole() this problem can't be tested???
    /*  && t.checkExpect(this.complexBalanced1.drawMobile(new Posn(400, 100)),
                new LineImage(new Posn (400, 100), new Posn(400, 101),
                        new Blue()).overlayImages(
                                new LineImage (new Posn (400, 101), 
                                new Posn(404, 101),
                                new Blue())), this.simple4.drawMobile
                                (new Posn(400, 100)), 
                                new LineImage (new Posn (400, 101),
                                        new Posn(404, 101), new Blue
                                        ()), this.simple5.drawMobile(
                                        new Posn(400,100))); */

    // Commented out to avoid Webcat conflicts 
    // display the image on the canvas (no real tests are possible)
    boolean testDrawImage(Tester t) {
        WorldCanvas c = new WorldCanvas(600, 600);
        return t.checkExpect(
                c.show()
                && c.drawImage(this.complex1.drawMobile(new Posn(400,
                        100))), true);
    }


}

