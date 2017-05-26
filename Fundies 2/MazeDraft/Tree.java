import java.util.ArrayList;
import java.util.Collections;

class Tree {
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    Tree() {
        this.nodes = new ArrayList<Node>();
        this.edges = new ArrayList<Edge>();

    }

    // generates nodes based on width and height
    public void generateNodes(int width, int height) {
        for (Integer y = 0; y <= height; y++){
            for (Integer x = 0; x <= width; x++){
                Node n = new Node(x, y);
                n.name = x.toString() + ", " + y.toString();
                this.nodes.add(n);
                Collections.shuffle(this.nodes);
            }          
        }  
    }

    // create a list of all possible edges
    public ArrayList<Edge> possibleEdges() {
        ArrayList<Edge> allEdges = new ArrayList<Edge>();
        for (int i1 = 0; i1 < this.nodes.size(); i1++) {
            for (int i2 = 0; i2 < this.nodes.size(); i2++) {
                if (this.nodes.get(i1).neigbors(this.nodes.get(i2))) {
                    Edge e = new Edge();
                    e.from = this.nodes.get(i1);
                    e.to = this.nodes.get(i2);
                    //this.nodes.get(i1).top = this.nodes.get(i2);
                    allEdges.add(e);
                }
            } 
        } return allEdges;
    }

    // generates a random index
    public int randomIndex(int max) {
        return (int) (0 + (Math.random() * max));
    }

    // get the random edge and remove from the list
    public Edge getEdge(ArrayList<Edge> allEdges) {
        Edge e = new Edge();
        int index = this.randomIndex(allEdges.size());
        e = allEdges.get(index);
        allEdges.remove(index);

        return e;
    }


    public void buildTree(ArrayList<Edge> allEdges){
        if (this.edges.size() == 0) { // if the the list of edges is empty
            Edge e = this.getEdge(allEdges);
            this.edges.add(e); // you just add an Edge
            e.from.top = e.to; //setting from's top to to

        }
        //else if()
    }

}
















