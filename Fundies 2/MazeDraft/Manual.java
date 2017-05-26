import java.util.*;

import javalib.colors.*;
import javalib.funworld.*;
import javalib.worldimages.*;

import java.awt.Color;
import java.awt.color.*;


class Manual{
    Tree t;
    Node current;
    HashMap<String, Node> visited;
    Boolean finished;
    Boolean left;
    Boolean right;
    Boolean top;
    Boolean bottom;
    Manual(Tree t){
        this.t = t;
        this.current = this.t.nodes.get(0);
        this.visited = new HashMap<String, Node>();
        this.finished = false;
        this.left = false;  
        this.right = false; 
        this.top = false;   
        this.bottom = false;
    }
    
    
    
    public void play(){
        Node lastNode = t.nodes.get(t.nodes.size() - 1); // getting the last node in the tree list
        //this.visited.put(this.t.nodes.get(0).name, this.t.nodes.get(0));
        this.current = this.t.nodes.get(0); // get the last item in the ArrayList
        System.out.println("ff");
        if (this.current.equals(lastNode)){
            this.visited.put(this.current.name, this.current);
            this.finished = true;
            return;
        }
        while (!this.current.equals(lastNode)){
            if (this.right && current.rightNeighbor()){
                this.checkVisited(current);
                this.current = this.current.getRight();

            }

            else if (this.left && current.leftNeighbor()){
                this.checkVisited(current);
                current = current.getLeft();
            }
            else if (this.top && current.topNeighbor()){
                this.checkVisited(current);
                current = current.getTop();
            }
            else if (this.bottom && current.bottomNeighbor()){
                this.checkVisited(current);
                current = current.getBottom();
            }
        }
    }

    public void checkVisited(Node n){
        Boolean isVisited = false;
        for (String s: this.visited.keySet()){
            isVisited = isVisited || n.name.equals(s);
        }
        if (!isVisited){
            this.visited.put(n.name, n);
        }
    }
    
    public WorldImage drawVisited(){
        WorldImage canvas = new RectangleImage(new Posn(0, 0), 0, 0, new White());
        for (String s: this.visited.keySet()){
            canvas = new OverlayImages(canvas, this.visited.get(s).drawNode());

        } return canvas;
    }
}