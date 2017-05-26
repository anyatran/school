import java.awt.Color;
import javalib.impworld.*;
import javalib.worldimages.*;

/** represents the entire Maze Game
 * @author anhtran9
 * @version 16-04-2014

 */

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
    /**
     * 
     * @param width of the background
     * @param height of the background
     */
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

/**
 * 
 * @return the image when you start the game
 */
    public WorldImage alg() {
        return new OverlayImages(new RectangleImage(new Posn(0, 0), 
                this.height * 2, this.width * 2, Color.LIGHT_GRAY),
                new TextImage(new Posn(this.height / 2, this.width / 2),
                        "choose algorithm: D = DFS; B = BFS;", 30, 
                        Color.green));
    }


/**
 * 
 * @return the game with walls only
 */
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
    
    /**
     * 
     * @return the image with final backtrack with walls only
     */
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

/**
 * 
 * @return the image with the path only (MST)
 */
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

    /**
     * 
     * @return the image of path with walls
     */
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

    /**
     * 
     * @return the image without walls and path
     */
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
    
    /*
     * drawing the entire game
     */
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

    /**
     * control the game using keyboard
     */
    public void onKeyEvent(String ke) {
        if (ke.equals("n") && this.a.done) {
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
        else if (ke.equals("5")) { // only backtrack without visited
            this.paths = false;
            this.walls = false;
            this.everything = false;
            this.nothing = false;
            this.backtrack = true;
        }
    }

    /**
     * running the game on each tick
     */
    public void onTick() {
        if (!flag) {
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

    
    /**
     * 
     * @param argv
     * run the entire game
     */
    public static void main(String[] argv) {
        MazeGame mg = new MazeGame(600, 600);
        mg.run();
    }

    /**
     * run the game
     */
    public void run() {
        this.bigBang(this.width, this.height, 0.000001);
    }

    
    
}











