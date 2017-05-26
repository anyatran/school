import java.awt.Color;
import javalib.worldimages.*;
/**
 * represents the maze
 * @author anntran.9
 *
 */
class Maze {
    Tree t;
    Maze(Tree t) {
        this.t = t;
    }
    /**
     * draws all the edges in the tree to create a path
     * @return WorldImage of all edges drawn on top of the canvas
     */
    public WorldImage drawTree() {
        WorldImage canvas = new RectangleImage(
                new Posn(0, 0), 0, 0, Color.cyan);
        for (Edge e: this.t.edges) {
            canvas = new OverlayImages(canvas, e.drawEdge());
        }
        return canvas;
    }
    /**
     * draws the maze with its walls
     * @return WorldImage of canvas with the maze walls
     * */
    public WorldImage drawMaze() {
        WorldImage canvas = new RectangleImage(
                new Posn(0, 0), 0, 0, Color.cyan);
        for (Node n: this.t.nodes) {
            canvas = new OverlayImages(canvas, n.drawWalls());
        }
        return canvas;
    }
}