package jptdraw;

import java.awt.*;
import java.awt.geom.*;

import colors.*;

import edu.neu.ccs.gui.*;

/**
 * A wrapper class that implements for the JPT <code>BufferedPanel</code> 
 * simple 'draw' methods used in the 'draw' and 'idraw' libraries
 * 
 * @author Viera K. Proulx
 * @since 4 April 2008
 */
public class JPTCanvas{
  BufferedPanel window;
  
  public JPTCanvas(BufferedPanel window){
    this.window = window;
  }
  
  /**
   * Implementation of the drawCircle method from the draw libraries
   * for use with the JPT <code>BufferedPanel</code>
   * -- using <code>IColor</code> from the <code>colors</code> package
   * 
   * @param p the center of the disk
   * @param radius the radius of the disk
   * @param color the <color>IColor</color> of the disk
   */  
  public boolean drawCircle(Posn center, int radius, IColor color){
    return drawCircle(center, radius, color.thisColor());
  }

  /**
   * Implementation of the drawCircle method from the draw libraries
   * for use with the JPT <code>BufferedPanel</code>
   * -- using <code>IColor</code> from the <code>colors</code> package
   * 
   * @param p the center of the disk
   * @param radius the radius of the disk
   * @param color the <color>IColor</color> of the disk
   */
  public boolean drawCircle(Posn center, int radius, Color color){
    int diameter = 2 * radius;

    // get graphics context and set the color
    Graphics2D G = window.getBufferGraphics();
    G.setPaint(color);

    // paint the disk
    Ellipse2D.Double E = 
      new Ellipse2D.Double(center.x - radius, center.y - radius, 
          diameter, diameter);
    G.draw(E);
    window.repaint();
    return true;
  }
  
  /**
   * Implementation of the drawDisk method from the draw libraries
   * for use with the JPT <code>BufferedPanel</code>
   * -- using <code>IColor</code> from the <code>colors</code> package
   * 
   * @param p the center of the disk
   * @param radius the radius of the disk
   * @param color the <color>IColor</color> of the disk
   */
  public boolean drawDisk(Posn center, int radius, IColor color){
    return drawDisk(center, radius, color.thisColor());
  }

  /**
   * Implementation of the drawDisk method from the draw libraries
   * for use with the JPT <code>BufferedPanel</p>
   * 
   * @param p the center of the disk
   * @param radius the radius of the disk
   * @param color the <color>IColor</color> of the disk
   */
  public boolean drawDisk(Posn center, int radius, Color color){
    int diameter = 2 * radius;

    // get graphics context and set the color
    Graphics2D G = window.getBufferGraphics();
    G.setPaint(color);

    // paint the disk
    Ellipse2D.Double E = 
      new Ellipse2D.Double(center.x - radius, center.y - radius, 
          diameter, diameter);
    G.fill(E);
    window.repaint();
    return true;
  }
  
  /**
   * Implementation of the drawRect method from the draw libraries
   * for use with the JPT <code>BufferedPanel</code>
   * -- using <code>IColor</code> from the <code>colors</code> package
   * 
   * @param nw the NorthWest corner of the rectangle
   * @param width of the rectangle
   * @param height of the rectangle
   * @param color the <color>IColor</color> of the rectangle
   */
  public boolean drawRect(Posn nw, int width, int height, IColor color){
    return drawRect(nw, width, height, color.thisColor());
  }

  /**
   * Implementation of the drawRect method from the draw libraries
   * for use with the JPT <code>BufferedPanel</p>
   * 
   * @param nw the NorthWest corner of the rectangle
   * @param width of the rectangle
   * @param height of the rectangle
   * @param color the <color>IColor</color> of the rectangle
   */
  public boolean drawRect(Posn nw, int width, int height, Color color){
    
    // get graphics context and set the color
    Graphics2D G = window.getBufferGraphics();
    G.setPaint(color);

    // paint the disk
    Rectangle2D.Double R = 
      new Rectangle2D.Double(nw.x, nw.y, width, height);
    G.fill(R);
    window.repaint();
    return true;
  }
  
  /**
   * Implementation of the drawLine method from the draw libraries
   * for use with the JPT <code>BufferedPanel</p>
   * 
   * @param p1 the start point of the line
   * @param p2 the end point of the line
   * @param c the <color>IColor</color> of the disk
   */
  public boolean drawLine(Posn p1, Posn p2, IColor color){
    return drawLine(p1, p2, color.thisColor());
  }
  
  /**
   * Implementation of the drawLine method from the draw libraries
   * for use with the JPT <code>BufferedPanel</p>
   * 
   * @param p1 the start point of the line
   * @param p2 the end point of the line
   * @param c the <color>IColor</color> of the disk
   */
  public boolean drawLine(Posn p1, Posn p2, Color color){
    // get graphics context and set the color
    Graphics2D G = window.getBufferGraphics();
    G.setPaint(color);

    // paint the line
    Line2D.Double L =
      new Line2D.Double(p1.x, p1.y, p2.x, p2.y); 
    G.draw(L);
    window.repaint();
    return true;
  }
  
  /**
   * Implementation of the drawString method from the draw libraries
   * for use with the JPT <code>BufferedPanel</p>
   * 
   * @param p the low left corner where the <code>String</code> starts
   * @param s the <code>String</code> to display
   */
  public boolean drawString(Posn p, String s){
    if (s == null)
      s = "";
    
    // get graphics context and set the color
    Graphics2D G = window.getBufferGraphics();
    Paint oldPaint = G.getPaint();
    
    G.setPaint(Color.black);

    // paint the string
    G.drawString(s, p.x, p.y);

    G.setPaint(oldPaint); 
    window.repaint();
    return true;
  }
}
