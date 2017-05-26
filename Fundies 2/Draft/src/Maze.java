import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javalib.impworld.*;
import javalib.worldcanvas.WorldCanvas;
import javalib.worldimages.*;
import javalib.colors.*;


public class Maze {
    ArrayList<ArrayList<Node>> grid;
    //list of nodes already visited 
    ArrayList<Node> mazeList = new ArrayList<Node>();
    //List of edges for maze creation
    ArrayList<Edge> edgeList = new ArrayList<Edge>();
    //used in prim's algorithm. not needed for project
    ArrayList<Node> wallList = new ArrayList<Node>();
    //Hashmap for storing and retrieving nodes with ease
    HashMap<String, Node> hm = new HashMap<String, Node>();
    Node current;
    int row;
    int column;

    //empty constructor
    Maze() {
        //empty constructor
    }

    // generates a grid with given # of columns and rows
    void genNodes(int rows, int cols) {
        this.row = rows;
        this.column = cols;
        ArrayList<ArrayList<Node>> result = new ArrayList<ArrayList<Node>>();

        for (int index = 0; index < cols; index = index + 1) {
            ArrayList<Node> colResult = new ArrayList<Node>();
            for (int i = 0; i < rows; i = i + 1) {
                Node n = new Node(index, i);
                colResult.add(n);
                this.hm.put(n.name, n);
            }
            result.add(colResult);
        } 
        grid = result;
    }

    //Generates edge list
    void genEdge() {
        for(int i = 0; i < column; i = i + 1) {
            for(int k = 0; k < row; k = k + 1) {
                Node a = this.hm.get("(" + i + "," + k + ")");
                Node b = this.hm.get("(" + (i + 1) + "," + k + ")");
                Node c = this.hm.get("(" + i + "," + (k + 1) + ")");
                Node d = this.hm.get("(" + (i - 1) + "," + k + ")");
                Node e = this.hm.get("(" + i + "," + (k - 1) + ")");
                Edge e1 = new Edge(a, b);
                Edge e2 = new Edge(a, c);
                Edge e3 = new Edge(a, d);
                Edge e4 = new Edge(a, e);
                this.edgeList.add(e1);
                this.edgeList.add(e2);
                this.edgeList.add(e3);
                this.edgeList.add(e4);

            }
        }
    }

    //Draw maze
    WorldImage drawMaze() {  
        WorldImage c = new RectangleImage(new Posn((this.column * 1000) / 2, (this.row * 10) / 2), 
                this.column * 10, this.row * 10, new White());
        for(int i = 0; i < column; i = i + 1) {
            for(int k = 0; k < row; k = k + 1) {
                Node a = this.hm.get("(" + i + "," + k + ")");
                c = c.overlayImages(this.hm.get("(" + i + "," + k + ")").drawNode());
            }
        }
        return c;

    }

    //Generates maze using Kruskal's alg
    void genMaze() {
        Random rand = new Random();
        while(this.edgeList.size() > 0) {
            int value = rand.nextInt(this.edgeList.size());
            Edge e1 = this.edgeList.remove(value);
            if(!(e1.end == null) && !(e1.start == null) && Collections.disjoint(e1.start.pathList, e1.end.pathList)) {


                if( (e1.start.x < e1.end.x) && (e1.start.e == null)){
                    e1.start.e = e1.end;
                    e1.end.w = e1.start;
                    e1.start.pathList.addAll(e1.end.pathList);
                    e1.start.fixPathLists();
                }
                else if((e1.start.y < e1.end.y) && (e1.start.s == null)) {
                    e1.start.s = e1.end;
                    e1.end.n = e1.start;
                    e1.start.pathList.addAll(e1.end.pathList);
                    e1.start.fixPathLists();
                }
                else if((e1.start.x > e1.end.x) && (e1.start.w == null)){
                    e1.start.w = e1.end;
                    e1.end.e = e1.start;
                    e1.start.pathList.addAll(e1.end.pathList);
                    e1.start.fixPathLists();
                }
                else if((e1.start.y > e1.end.y) && (e1.start.n == null)) {
                    e1.start.n = e1.end;
                    e1.end.s = e1.start;
                    e1.start.pathList.addAll(e1.end.pathList);
                    e1.start.fixPathLists();
                }
                else {

                }
            }
            else {

            }
        }
    }

    //Breadth First search
    //ending node is bottom right corner. start is top left (0,0)
    void bfSearch() {
        ArrayList<Node> visited = new ArrayList<Node>();
        ArrayList<Edge> toDo = new ArrayList<Edge>();
        Node start = this.hm.get("(0,0)");
        start.visited = true;
        this.addNeighbors(toDo, start);
        while((toDo.size() > 0) && 
                (this.hm.get("(" + column + "," + row + ")")).visited == false) {
            //adding other neighbors to toDo list
            for(int i = 0; i < toDo.size(); i = i + 1) {
                this.addNeighbors(toDo,toDo.get(i).end);
                toDo.get(i).end.visited  = true;
            }

        }
        if(this.hm.get("(" + row + "," + column + ")").visited == true) {
            //do backtracking
        }
    }
    //Adds neighbors to list by creating edges
    void addNeighbors(ArrayList<Edge> a, Node n) {
        if(!(n.n == null) && (n.n.visited == false)) {
            a.add(new Edge(n, n.n)); 
        }
        else {

        }
        if(!(n.s == null) && (n.s.visited == false)) {
            a.add(new Edge(n, n.s)); 
        }
        else {

        }
        if(!(n.e == null) && (n.e.visited == false)) {
            a.add(new Edge(n, n.e)); 
        }
        else {

        }
        if(!(n.w == null) && (n.w.visited == false)) {
            a.add(new Edge(n, n.w)); 
        }
        else {

        }
    }

    void bfSearch() {
        ArrayList<Node> visited = new ArrayList<Node>();
        ArrayList<Edge> toDo = new ArrayList<Edge>();
        Node start = this.hm.get("(0,0)");
        start.visited = true;
        this.addNeighbors(toDo, start);
        while((toDo.size() > 0) && 
                (this.hm.get("(" + column + "," + row + ")")).visited == false) {
            // adding other neighbors to toDo list
            for(int i = 0; i < toDo.size(); i = i + 1) {
                this.addNeighbors(toDo,toDo.get(i).end);
                toDo.get(i).end.visited  = true;
            }
        }
        if(this.hm.get("(" + row + "," + column + ")").visited == true) {
            // do backtracking
        }
    }
    // Adds neighbors to the given list by creating edges
    void addNeighbors(ArrayList<Edge> a, Node n) {
        if(!(n.n == null) && (n.n.visited == false)) {
            a.add(new Edge(n, n.n)); 
        }
        else {

        }
        if(!(n.s == null) && (n.s.visited == false)) {
            a.add(new Edge(n, n.s)); 
        }
        else {

        }
        if(!(n.e == null) && (n.e.visited == false)) {
            a.add(new Edge(n, n.e)); 
        }
        else {

        }
        if(!(n.w == null) && (n.w.visited == false)) {
            a.add(new Edge(n, n.w)); 
        }
        else {

        }
    }
}




