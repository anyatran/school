import java.util.*;

import javalib.colors.*;
import javalib.worldimages.*;


/**
 * represents DFS and BFS algorithms
 * @author anntran9
 *
 */
public class Searching {
    HashMap<String, Node> visited;
    ArrayList<Node> toDo;
    ArrayList<Node> backtrack;
    Tree t;
    Boolean d;
    Boolean b;
    Boolean done;
    Boolean finished;
    Node current;
    /**
     * 
     * @param t is a given tree (path)
     * */
    Searching(Tree t) {
        this.finished = false;
        this.visited = new HashMap<String, Node>();
        this.toDo = new ArrayList<Node>();
        this.t = t;
        this.backtrack = new ArrayList<Node>();
        this.toDo.add(t.nodes.get(0)); 
        this.d = false;
        this.b = false;
        this.done = false;
        this.current = this.t.nodes.get(0);

    }
    
    /**
     * do DFS
     * @param t - the tree
     * @return do the searching and returns true if it has finished
     */
    public boolean doDFS(Tree t) {
        
        Node lastNode = t.nodes.get(t.nodes.size() - 1); 

        int getFromStack = this.toDo.size() - 1; 

        this.current = this.toDo.get(getFromStack); 

        if (this.current.equals(lastNode)) {
            this.visited.put(this.current.name, this.current);
            this.finished = true;
            this.backtrack.add(this.t.nodes.get(this.t.nodes.size() - 1));
            return true;
        }
        else {
            this.toDo.remove(getFromStack); 
            for (Node n: this.current.neighbors) { 
                if (!this.visited.containsKey(n.name)) {
                    n.from = current;
                    this.toDo.add(n);

                }
            }
            this.visited.put(this.current.name, this.current); 
            return false;
        }
    }



/**
 * do BFS
 * @param t - the tree
 * @return do the searching and return true if it has finished
 */
    public boolean doBFS(Tree t) {

        // getting the last node in the tree list
        Node lastNode = t.nodes.get(t.nodes.size() - 1); 

        // get the last item in the ArrayList
        this.current = this.toDo.get(0); 
        if (this.current.equals(lastNode)) {
            this.visited.put(this.current.name, this.current);
            this.finished = true;
            this.backtrack.add(this.t.nodes.get(this.t.nodes.size() - 1));
            return true;
        }
        else {

            this.toDo.remove(0); 

            for (Node n: this.current.neighbors) { 
                if (!this.visited.containsKey(n.name)) {
                    n.from = this.current;
                    this.toDo.add(n);
                }            
            }
            this.visited.put(this.current.name, this.current); 
            return false;
        }
    }

/**
 * draws all visited nodes
 * @return an image of nodes
 */
    public WorldImage drawVisited() {
        WorldImage canvas = new RectangleImage(new Posn(0, 0), 
                0, 0, new White());
        for (String s: this.visited.keySet()) {
            canvas = new OverlayImages(canvas, 
                    this.visited.get(s).drawNode());

        } return canvas;
    }

    /**
     * draws the current node
     * @return an image of the node
     */
    public WorldImage drawCurrent() {
        return this.current.drawCurrent();
    }

/**
 * draws the backtracked path
 * @return an image of the path
 */
    public WorldImage drawUltPath() {
        WorldImage canvas = new RectangleImage(new Posn(0, 0),
                0, 0, new White());
        if (this.finished) {
            for (Node n: this.backtrack) {
                canvas = new OverlayImages(canvas,
                        n.drawBacktrack());
            }
        }
        return canvas;
    }

    /**
     * adding all the nodes that builds the backtrack to the backtrack list
     */
    public void addTrack() {
        if (this.finished) {
            if (this.backtrack.get(this.backtrack.size() - 1).from == null) {
                this.done = true;
                return;
            }

            else if (this.backtrack.get(
                    this.backtrack.size() - 1).from != null) {
                this.backtrack.add(this.backtrack.get(
                        this.backtrack.size() - 1).from);
            }
        }           

    }
}












