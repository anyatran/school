import java.util.ArrayList;
import javalib.colors.*;
import javalib.worldimages.*;
import javalib.impworld.*;
public class Node {
    String name;
    int x;
    int y;
    // Nodes on boundary representing directions. i.e. n = north
    Node n;
    Node s; 
    Node e; 
    Node w;
    //ArrayList keeps track of nodes on list of path
    ArrayList<Node> pathList = new ArrayList<Node>();
    boolean visited;
    
    Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = ("(" + this.x + "," + this.y + ")");
        pathList.add(this);
        visited = false;
    }
    //return white for connected node sides and black for null
    IColor col(Node n) {
        if(n == null) {
            return new Black();
        }
        else {
            return new White();
        }
    }
    
    //Updates pathLists for each node in list
    void fixPathLists() {
        for(int i = 0; i < this.pathList.size(); i = i + 1) {
            this.pathList.get(i).pathList = this.pathList;
        }
    }
    
    
   
    
    //Draw node as square with proper colored lines
    WorldImage drawNode() {
        int xx = this.x * 20;
        int yy = this.y * 20;
        WorldImage nLine = new LineImage(new Posn(xx, yy), new Posn(xx + 20, yy), this.col(this.n));
        WorldImage sLine = new LineImage(new Posn(xx, yy + 20), new Posn(xx + 20, yy + 20), this.col(this.s));
        WorldImage eLine = new LineImage(new Posn(xx + 20, yy), new Posn(xx + 20, yy + 20), this.col(this.e));
        WorldImage wLine = new LineImage(new Posn(xx, yy), new Posn(xx, yy + 20), this.col(this.w));
        
        return nLine.overlayImages(sLine, eLine, wLine);
    }
    
}