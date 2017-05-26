import java.awt.Color;
import javalib.worldimages.*;
/**
 * represents an edge
 * @author anntran9
 *
 */
class Edge {
    Node from;
    Node to;
   
    /**
     * checks if the edge contains the given node
     * @param a node n
     * @return true if this edge has the node
     * */
    public boolean hasNode(Node n) {
        return (this.from.name == n.name) || (this.to.name == n.name);
    }
    
    
    /**
     * draws the edge with a red line
     * @return an image of a red line
     * */
    public WorldImage drawEdge() {
        return new LineImage(new Posn(this.from.x, this.from.y), 
                new Posn(this.to.x, this.to.y), Color.red);
    }
    
    /**
     * is given edge the same as this edge?
     * For example, edge A to B should be equal to edge B to A
     * @param Edge e
     * @return true if 2 edges are the same
     * */
    public boolean sameEdge(Edge e) {
        return (this.from.equals(e.from) && this.to.equals(e.to)) ||
                (this.from.equals(e.to) && this.to.equals(e.from));
    }
    
    
    
}