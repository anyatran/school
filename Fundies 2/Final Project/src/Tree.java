import java.util.*;

/**
 * represents the Tree (path)
 * @author anna_tran2008
 *
 */
class Tree {
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    /**
     * 
     */
    Tree() {
        this.nodes = new ArrayList<Node>();
        this.edges = new ArrayList<Edge>();

    }

    /**
     * generates nodes based on width and height
     * @param width of the game image
     * @param height of the game image
     */
    public void generateNodes(int width, int height) {
        for (Integer y = 20; y < height - 19; y = y + 20) {
            for (Integer x = 20; x < width - 19; x = x + 20) {
                Node n = new Node(x, y);
                n.name = "(" + x.toString() + ", " + y.toString() + ")";
                this.nodes.add(n);
            }          
        }  
    }

    /**
     * create a list of all possible edges
     * @return the list of all possible edges on this tree
     */
    public ArrayList<Edge> possibleEdges() {
        ArrayList<Edge> allEdges = new ArrayList<Edge>();
        for (int i1 = 0; i1 < this.nodes.size(); i1++) {
            for (int i2 = 0; i2 < this.nodes.size(); i2++) {
                if (this.nodes.get(i1).neigbors(this.nodes.get(i2))) {
                    Edge e = new Edge();
                    e.from = this.nodes.get(i1);
                    e.to = this.nodes.get(i2);
                    allEdges.add(e);

                }
            } 
        } return allEdges;
    }

    /**
     * generates a random index
     * @param max which is the max number it can generate
     * @return an integer that it generated
     */
    public int randomIndex(int max) {
        return (int) (0 + (Math.random() * max));
    }

    /**
     * get the random edge and remove from the list
     * @param allEdges that is possible in this tree
     * @return an edge that it picks randomly
     */
    public Edge getEdge(ArrayList<Edge> allEdges) {
        Edge e = new Edge();
        int index = this.randomIndex(allEdges.size());
        e = allEdges.get(index);
        allEdges.remove(index);

        return e;
    }

    /**
     * build the minimum spanning tree
     * @param allEdges
     */
    public void buildTree(ArrayList<Edge> allEdges) {
        while (allEdges.size() > 0) {
            Edge e = this.getEdge(allEdges);
            if (!e.from.unions(e.to)) {
                this.edges.add(e); 
                Node from =  e.from.getRepr();
                from.repr = e.to.getRepr();
                e.to.neighbors.add(e.from);
                e.from.neighbors.add(e.to);

            }
        }
    }

    /**
     * checks if the tree contains the giving edge
     * @param e2
     * @return true if it does
     */
    public boolean contains(Edge e2) {
        Boolean result = false;
        for (Edge e1: this.edges) {
            result = result || e1.sameEdge(e2);
        }
        return result;
    }
}


















