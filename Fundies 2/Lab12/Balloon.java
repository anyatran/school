import java.awt.*;			    // to get Color
import jptdraw.*;           // for the JPTCanvas
//import javalib.worldimages.*;          // to get Posn
  
/**
 * A simple class to use for learning about classes and objects.
 * 
 * @since  4 April 2010
 * @author Viera K. Proulx
 */
class Balloon {
  
  /*-------------------------------------------------------------------------
   Member data 
   *-----------------------------------------------------------------------*/
  
  /** x coordinate of the center of the balloon */
  int x;
  
  /** y coordinate of the center of the balloon */
  int y;
  
  /** the radius of the balloon */
  int radius;
  
  /** the color of the balloon */
  Color c;

 /*-------------------------------------------------------------------------
  The Constructors
  *----------------------------------------------------------------------*/
  /**
   * The constructor
   */ 
  public Balloon (int some_x, int some_y, int some_radius, Color some_color){
    this.x = some_x;
    this.y = some_y;
    this.radius = some_radius;
    this.c = some_color;
  }
  
  /** 
   * The copy constructor: builds a new instance from the given one
   */
  public Balloon(Balloon someBalloon){
    this.x      = someBalloon.x;
    this.y      = someBalloon.y;
    this.radius = someBalloon.radius;
    this.c      = new Color(someBalloon.c.getRGB());
  }
  
  /** 
   * The default constructor
   */
  public Balloon(){
    this.x      = 200;
    this.y      = 150;
    this.radius = 45;
    this.c      = Color.red;
  }
  
  /*-------------------------------------------------------------------------
  The Methods
  *----------------------------------------------------------------------*/
  /**
   * Move this balloon by the given distance in both directions 
   */
  Balloon moveBalloon(int dx, int dy){
    return new Balloon(this.x + dx, this.y + dy, this.radius, this.c);
  }
 
  /** compute the diameter of the balloon */
  int getDiameter(){
    return 2 * this.radius;
  }
  
  /** compute the distance of this balloon from the top of window */
  int distanceFromTop(){
    return this.y - this.radius;
  }
  
  /** compare the top of this and the given other balloon */
  boolean isHigherThan(Balloon otherBln){
    return this.distanceFromTop() < otherBln.distanceFromTop() ;
  }
  
  /** show balloon in the given JPTCanvas */
  void paintBalloon(JPTCanvas window){
    window.drawDisk(new Posn(this.x, this.y), this.radius, this.c);
    window.drawLine(new Posn(this.x, this.y + this.radius), 
                    new Posn(this.x, this.y + 3 * this.radius),
                    this.c);
  } 
  
  /** erase the balloon */
  void eraseBalloon(){
      this.c = Color.white;
      
  }
    
   
  /** override equals */
  public boolean equals(Object obj){
    if (obj instanceof Balloon)
      return
      this.x == ((Balloon)obj).x &&
      this.y == ((Balloon)obj).y &&
      this.radius == ((Balloon)obj).radius &&
      this.c.equals(((Balloon)obj).c);
    else
      return false;
  }
  
  /** override hashCode */
  public int hashCode(){
    return 37 * (37 * (37 * this.x + this.y) + this.radius) 
    + this.c.hashCode();
  }
  
  
  /** print the balloon data */
  public String toString(){
    return ("new " + getClass() + "(" + 
        this.x + ", " + 
        this.y + ", " + 
        this.radius + ", " + 
        this.c + ")");
  }
  
}
