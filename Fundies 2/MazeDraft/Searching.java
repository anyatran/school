import java.util.*;

import javalib.colors.*;
import javalib.funworld.*;
import javalib.worldimages.*;

import java.awt.Color;
import java.awt.color.*;

public class Searching {
    HashMap<String, Node> visited;
    ArrayList<Node> toDo;
    Searching(){
        this.visited = new HashMap<String, Node>();
        this.toDo = new ArrayList<Node>();
    }
    
    public void doDFS(Tree t){
        Node visiting = new Node(0, 0);
        this.toDo.add(t.nodes.get(0));
        int nodesSize = t.nodes.size();

//        if (visiting.equals(t.nodes.get(nodesSize - 1))){
//            this.backtrack();
//        }
        if(!visiting.equals(t.nodes.get(nodesSize - 1))){
            visiting = this.toDo.get(nodesSize - 1);
            for (Node n: visiting.neighbors){
                if (!this.visited.containsKey(n.name)){
                    this.toDo.add(n);
                }
                this.visited.put(visiting.name, visiting);
            }
        }
    }

    public ArrayList<Edge> backtrack(){
        return new ArrayList<Edge>();
    }

    public void doBFS(Tree t) {
        Node visiting = new Node(0, 0);
        toDo.add(t.nodes.get(0));
        int nodesSize = t.nodes.size();

//        if (visiting.equals(t.nodes.get(nodesSize - 1))){
//            this.backtrack();
//        }
        if (!visiting.equals(t.nodes.get(nodesSize - 1))){
            visiting = this.toDo.get(0);
            this.toDo.remove(0);
            for (Node n: visiting.neighbors){
                if (!this.visited.containsKey(n.name)){
                    this.toDo.add(n);
                }
                this.visited.put(visiting.name, visiting);
            }
        }
    }

    public WorldImage drawVisited(){
        WorldImage canvas = new RectangleImage(new Posn(0, 0), 0, 0, new White());
        for (String s: this.visited.keySet()){
            canvas.overlayImages(this.visited.get(s).drawNode());

        } return canvas;
    }


}













