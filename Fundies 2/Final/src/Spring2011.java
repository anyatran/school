import java.util.ArrayList;
import java.util.Iterator;

interface IMaze extends Iterable<String> {
    /** Returns an iterator for the directions / path through
     * this maze . The iterator specifies the directions
     * ( in order ) that the player must move .
     * Repeated obligation from Iterable < String > */
    Iterator<String> iterator();
    /** Is this maze 's path valid for its width and
     * height ? ( Is it completely on the grid ?) */
    boolean isValid();
    /** Returns the width of this maze grid */
    int getWidth();
    /** Returns the height of this maze grid */
    int getHeight();
    /** Set the lives for the player */
    void setLives(int lives);
    /** Returns the player 's lives */
    int getLives();
    /** Record a bad move by decreasing the player 's lives */
    void badMove();
}

// represents the maze
class Maze implements IMaze{
    int width;
    int heigth;
    int lives;
    //Path path;
    ArrayList<String> path;
    Maze(int width, int heigth, int lives, ArrayList<String> path){
        this.width = width;
        this.heigth = heigth;
        this.lives = lives;
        this.path = path;
    }

    public boolean isValid(){
        return (this.path.x >= 0) && (this.path.x <= this.width) &&
                (this.path.y >= 0) && (this.path.y <= this.heigth);
        // the ending point should be at the left bottom
                
    }
    
    public Iterator<String> iterator(){
        return this.path.iterator();
    }
}

class Path{
    int x;
    int y;
    ArrayList<String> p;
    Path(int x, int y, ArrayList<String> p){
        this.x = x;
        this.y = y;
        this.p = p;
    }
}

class ExamplesMaze{
    ArrayList<String> p1;
    IMaze m;


    void init(){
        p1 = new ArrayList<String>();
        this.p1.add("down");
        this.p1.add("down");
        this.p1.add("right");
        this.p1.add("up");
        this.p1.add("right");
        this.p1.add("right");
        this.p1.add("down");
        this.p1.add("down");

        m  = new Maze(10, 10, 5, this.p1);
    }
}








