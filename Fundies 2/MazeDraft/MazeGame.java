import java.awt.Color;
import java.util.*;

import javalib.colors.*;
import javalib.impworld.*;
import javalib.worldimages.*;
//import javalib.worldimages.WorldImage;

// represents a Maze

class MazeGame extends World {

    int width;
    int height;
    Tree t;
    Maze m;
    Searching a;
    Boolean algSWitch;
    Boolean paths;
    Boolean walls;
    Boolean nothing;
    Boolean everything;
    Boolean backtrack;
    Boolean flag;
    MazeGame(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.t = new Tree();
        this.t.generateNodes(this.height, this.width);
        this.t.buildTree(this.t.possibleEdges());
        this.m = new Maze(this.t);
        this.a = new Searching(this.t);
        this.algSWitch = false;
        this.paths = false;
        this.walls = true;
        this.nothing = false;
        this.everything = false;
        this.flag = false;
        this.backtrack = false;
    }


    public WorldImage alg() {
        return new OverlayImages(new RectangleImage(new Posn(0, 0), 
                this.height * 2, this.width * 2, Color.LIGHT_GRAY),
                new TextImage(new Posn(this.height / 2, this.width / 2),
                        "choose algorithm: D = DFS; B = BFS;", 30, 
                        Color.green).overlayImages(new TextImage(
                                new Posn(this.height / 2, this.width / 2 + 80),
                                        "M = Manual", 30, Color.green)));
    }



    public WorldImage drawWalls() {
        return new OverlayImages(new OverlayImages(new OverlayImages(
            new OverlayImages(new OverlayImages(new RectangleImage(
                new Posn(0, 0), this.height * 2, this.width * 2, 
                Color.LIGHT_GRAY), this.m.drawMaze()), 
                new RectangleImage(new Posn(this.t.nodes.get(0).x, 
                    this.t.nodes.get(0).y), 20, 20, Color.green)),
                    new RectangleImage(new Posn(this.t.nodes.get(
                        this.t.nodes.size() - 1).x, this.t.nodes.get(
                            this.t.nodes.size() - 1).y), 20, 20, Color.green)), 
                            this.a.drawVisited()), 
                            this.a.drawCurrent()).overlayImages(
                                this.a.drawUltPath());
    }
    
    public WorldImage drawBacktrack() {
        return new OverlayImages(new OverlayImages(
            new OverlayImages(new OverlayImages(new RectangleImage(
                new Posn(0, 0), this.height * 2, this.width * 2, 
                Color.LIGHT_GRAY), this.m.drawMaze()), 
                new RectangleImage(new Posn(this.t.nodes.get(0).x, 
                    this.t.nodes.get(0).y), 20, 20, Color.green)),
                    new RectangleImage(new Posn(this.t.nodes.get(
                        this.t.nodes.size() - 1).x, this.t.nodes.get(
                            this.t.nodes.size() - 1).y), 20, 20, Color.green)),
                                    this.a.drawUltPath());
                            
    }


    public WorldImage drawPath() {
        return new OverlayImages(new OverlayImages(new OverlayImages(
            new OverlayImages(new RectangleImage(new Posn(0, 0), 
                this.height * 2, this.width * 2, Color.LIGHT_GRAY),
                this.m.drawTree()), new RectangleImage(
                    new Posn(this.t.nodes.get(0).x, 
                        this.t.nodes.get(0).y), 20, 20, Color.green)),
                        new RectangleImage(new Posn(this.t.nodes.get(
                            this.t.nodes.size() - 1).x, this.t.nodes.get(
                                this.t.nodes.size() - 1).y),
                                20, 20, Color.green)), 
                                this.a.drawVisited()).overlayImages(
                                    this.a.drawCurrent()).overlayImages(
                                        this.a.drawUltPath());
    }

    public WorldImage drawBoth() {
        return new OverlayImages(new OverlayImages(new OverlayImages(
            new OverlayImages(new RectangleImage(new Posn(0, 0), 
                this.height * 2, this.width * 2, Color.LIGHT_GRAY),
                this.m.drawMaze().overlayImages(this.m.drawTree())), 
                new RectangleImage(
                    new Posn(this.t.nodes.get(0).x, 
                        this.t.nodes.get(0).y), 20, 20, Color.green)),
                        new RectangleImage(new Posn(this.t.nodes.get(
                            this.t.nodes.size() - 1).x, this.t.nodes.get(
                                this.t.nodes.size() - 1).y),
                                20, 20, Color.green)),
                                this.a.drawVisited()).overlayImages(
                                    this.a.drawCurrent()).overlayImages(
                                        this.a.drawUltPath());
    }

    public WorldImage drawNothing() {
        return new OverlayImages(new OverlayImages(new OverlayImages(
            new RectangleImage(new Posn(0, 0), this.height * 2, this.width * 2,
                Color.LIGHT_GRAY), new RectangleImage(new Posn(
                    this.t.nodes.get(0).x, 
                    this.t.nodes.get(0).y), 20, 20, Color.green)),
                    new RectangleImage(new Posn(this.t.nodes.get(
                        this.t.nodes.size() - 1).x,
                        this.t.nodes.get(this.t.nodes.size() - 1).y),
                        20, 20, Color.green)),
                        this.a.drawVisited()).overlayImages(
                            this.a.drawCurrent()).overlayImages(
                                this.a.drawUltPath());
    }
    public WorldImage makeImage() {
        if (!this.algSWitch) {
            return this.alg();
        }
        else if (this.walls) {
            return this.drawWalls();
        }
        else if (this.everything) {
            return this.drawBoth();
        }
        else if (this.paths) {
            return this.drawPath();
        }
        else if (this.nothing) {
            return this.drawNothing();
        }
        else if (this.backtrack) {
            return this.drawBacktrack();
        }
        else {
            return this.makeImage();
        }
    }

    public void onKeyEvent(String ke) {
        if (ke.equals("n") && this.a.done) {
            this.width = width;
            this.height = height;
            this.t = new Tree();
            this.t.generateNodes(this.height, this.width);
            this.t.buildTree(this.t.possibleEdges());
            this.m = new Maze(this.t);
            this.a = new Searching(this.t);
            this.algSWitch = false;
            this.paths = false;
            this.walls = true;
            this.nothing = false;
            this.everything = false;
            this.flag = false;
        }
        else if (ke.equals("b") && !this.a.d) {
            this.algSWitch = true;
            this.a.b = true;
        }    
        else if (ke.equals("d") && !this.a.b) {
            this.algSWitch = true;
            this.a.d = true;
        }
        else if (ke.equals("m")) {
            this.algSWitch = true;
            this.a.m = true;
        }
        else if (ke.equals("1")) { // only path
            this.paths = true;
            this.walls = false;
            this.everything = false;
            this.nothing = false;
            this.backtrack = false;
        }
        else if (ke.equals("2")) { // only maze
            this.paths = false;
            this.walls = true;
            this.everything = false;
            this.nothing = false;
            this.backtrack = false;
        }
        else if (ke.equals("3")) { // both
            this.paths = false;
            this.walls = false;
            this.everything = true;
            this.nothing = false;
            this.backtrack = false;
        }
        else if (ke.equals("4")) { // none
            this.paths = false;
            this.walls = false;
            this.everything = false;
            this.nothing = true;
            this.backtrack = false;
        }
        else if (ke.equals("5")) { // none
            this.paths = false;
            this.walls = false;
            this.everything = false;
            this.nothing = false;
            this.backtrack = true;
        }
//        else if (ke.equals("right")) {
//            this.a.right = true;
//            this.a.left = false;   
//            this.a.top = false;   
//            this.a.bottom = false;
//        }
//        else if (ke.equals("left")) {
//            this.a.right = false;
//            this.a.left = true;   
//            this.a.top = false;   
//            this.a.bottom = false;
//        }
//        else if (ke.equals("up")) {
//            this.a.right = false;
//            this.a.left = false;   
//            this.a.top = true;   
//            this.a.bottom = false;
//        }
//        else if (ke.equals("down")) {
//            this.a.right = false;
//            this.a.left = false;   
//            this.a.top = false;   
//            this.a.bottom = true;
//        }
    }

    public void onTick() {
        if(flag == false) {
            if (this.a.d) {
                flag = this.a.doDFS(this.t);
            }
            else if (this.a.b) {
                flag = this.a.doBFS(t);
            }
        }
        else {
            this.a.addTrack();
        }
    }

    
    
    public static void main(String[] argv) {
        MazeGame mg = new MazeGame(600, 600);
        mg.run();
    }

    public void run() {
        this.bigBang(this.width, this.height, 0.000001);
    }

    
    
}











