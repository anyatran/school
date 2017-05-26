import java.util.*;
import javalib.worldimages.*;

import java.awt.Color;

/** represents a single node
 * @author anhtran9
 * @version 16-04-2014
 * Template:
 Fields:
 this.name            -- String
 this.x               -- Integer
 this.y               -- Integer
 this.repr            -- Node
 this.from            -- Node
 this.neighbors       -- ArrayList<Node>
 Methods:
 this.drawNode()      -- WorldImage
 this.drawBacktrack() -- WorldImage
 this.distance(Node)  -- Double
 this.neigbors(Node)  -- Boolean
 this.equals()        -- Boolean
 this.getRepr()       -- Node
 this.unions(Node)    -- Boolean
 this.rightNeighbor() -- Boolean
 this.leftNeighbor()  -- Boolean
 this.topNeighbor()   -- Boolean
 this.bottomNeighbor()-- Booeanl
 this.getLeft()       -- Node
 this.getRight()      -- Node
 this.getBottom()     -- Node
 this.getTop()        -- Node
 this.drawWalls()     -- WorldImage

 */

class Node {
    String name;
    Integer x;
    Integer y;
    Node repr;
    Node from;
    ArrayList<Node> neighbors;
    /**
     * @param x is x coordinate of a node
     * @return the right neighbor
     * */
    Node(Integer x, Integer y) {
        this.name = " ";
        this.x = x;
        this.y = y;
        this.repr = this;
        this.neighbors = new ArrayList<Node>();

    }
    /**
     * drawing a node that has abeen visited
     * @return a grey rectangle 
     * */
    public WorldImage drawNode() {
        return new RectangleImage(
                new Posn(this.x, this.y), 18, 18, Color.GRAY);
    }

    /**
     * drawing a node that has abeen visited
     * @return a grey rectangle 
     * */
    public WorldImage drawCurrent() {
        return new RectangleImage(
                new Posn(this.x, this.y), 18, 18, Color.magenta);
    }

    /**
     * drawing a node in the backtrack path
     * @return a white rectangle 
     * */
    public WorldImage drawBacktrack() {
        return new RectangleImage(
                new Posn(this.x, this.y), 18, 18, Color.white);
    
    }

    /**
     * drawing a node that has abeen visited
     * @param another node n
     * @return a distance between two nodes
     * */
    public double distance(Node n) {
        return (Math.sqrt(((this.x - n.x) * (this.x - n.x))
                + ((this.y - n.y) * (this.y - n.y))));

    }

    /**
     * checks if two nodes can be neighbors to each otehr
     * @param another node n
     * @return true if they can be neighbors 
     * */
    public boolean neigbors(Node n) {
        return this.distance(n) == 20;
    }

    /**
     * overriding equals
     * @param an object that is compared to the node
     * @return a boolean indicating if two nodes are equal
     * */    
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        Node n = (Node)obj;
        return this.name == n.name;
    }

    /**
     * get the representative of this node
     * @return this node's representative
     * */
    public Node getRepr() {
        if (this.equals(this.repr)) { //if its pointing to itself
            return this;
        }
        else {
            return this.repr.getRepr();
        }
    }

    /**
     * checks if two nodes are unions
     * @param another node n
     * @return true if they are unions
     * */
    public boolean unions(Node n) {
        return (this.getRepr().equals(n.getRepr()));
    }

    /**
     * checks if the node has a right neighbor
     * @return true if it has a right neigbor
     * */
    public boolean rightNeighbor() {
        Boolean result = false;
        for (Node n: this.neighbors) {
            result = result || n.x > this.x;
        }
        return result;
    }

    /**
     * get the right neighbor of this node if it has one
     * @return the right neighbor
     * */
    public Node getRight() {
        Node r = new Node(0, 0);
        for (int index = 0; index < this.neighbors.size(); index++) {
            if (this.neighbors.get(index).x > this.x) {
                r = this.neighbors.get(index);
            }
        }
        return r;
    }
    /**
     * checks if the node has a left neighbor
     * @return true if it has a left neighbor
     * */
    public boolean leftNeighbor() {
        Boolean result = false;
        for (Node n: this.neighbors) {
            result = result || n.x < this.x;
        }
        return result;
    }
    /**
     * get the left neighbor of this node if it has one
     * @return the left neighbor
     * */
    public Node getLeft() {
        Node r = new Node(0, 0);
        for (int index = 0; index < this.neighbors.size(); index++) {
            if (this.neighbors.get(index).x < this.x) {
                r = this.neighbors.get(index);
            }
        }
        return r;
    }

    /**
     * checks if the node has a top neighbor
     * @return true if it has a top neighbor
     * */
    public boolean topNeighbor() {
        Boolean result = false;
        for (Node n: this.neighbors) {
            result = result || n.y < this.y;
        }
        return result;
    }
    /**
     * get the upper neighbor of this node if it has one
     * @return the top neighbor
     * */
    public Node getTop() {
        Node r = new Node(0, 0);
        for (int index = 0; index < this.neighbors.size(); index++) {
            if (this.neighbors.get(index).y < this.y) {
                r = this.neighbors.get(index);
            }
        }
        return r;
    }
    /**
     * checks if the node has a bottom neighbor
     * @return true if it has a bottom neighbor
     * */
    public boolean bottomNeighbor() {
        Boolean result = false;
        for (Node n: this.neighbors) {
            result = result || n.y > this.y;
        }
        return result;
    }

    /**
     * get the bottom neighbor of this node if it has one
     * @return the bottom neighbor
     * */
    public Node getBottom() {
        Node r = new Node(0, 0);
        for (int index = 0; index < this.neighbors.size(); index++) {
            if (this.neighbors.get(index).y > this.y) {
                r = this.neighbors.get(index);
            }
        }
        return r;
    }

    /**
     * get the right neighbor of this node if it has one
     * @return the image that wraps the tree
     * */
    public WorldImage drawWalls() {
        WorldImage image = new RectangleImage(
                new Posn(0, 0), 0, 0, Color.green);
        if (!this.leftNeighbor()) {
            image = image.overlayImages(new LineImage(
                    new Posn(this.x - 10, this.y - 10), 
                    new Posn(this.x - 10, this.y + 10), Color.green));
        }
        if (!this.rightNeighbor()) {
            image = image.overlayImages(new LineImage(
                    new Posn(this.x + 10, this.y - 10),
                    new Posn(this.x + 10, this.y + 10), Color.green));
        }
        if (!this.topNeighbor()) {
            image = image.overlayImages(new LineImage(
                    new Posn(this.x - 10, this.y - 10), 
                    new Posn(this.x + 10, this.y - 10), Color.green));
        }
        if (!this.bottomNeighbor()) {
            image = image.overlayImages(new LineImage(
                    new Posn(this.x - 10, this.y + 10), 
                    new Posn(this.x + 10, this.y + 10), Color.green));
        }

        return image;
    }


}





