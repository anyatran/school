import java.awt.Color;
import java.util.Random;

import tester.*;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;



/*
Q1: how to do moveBlock(), halfWay() because you have to check if the block is on the same level as the player
Q2: how to draw the Player on the canvas (overlay?)
Q3: how to animate

 */
// represent a Player
class Player {
    Posn location;
    Player(Posn l) {
        this.location = l;
    }

    WorldImage playerImage(){ // 36x38 px
        return new FromFileImage(this.location, "Player.png");
    }

    //method move: moves the player either to left or right depending on the key event 
    public Player move(String ke){
        if (ke.equals("right")){
            return new Player(new Posn (this.location.x + 30, this.location.y));
        }
        else if (ke.equals("left")){
            return new Player(new Posn (this.location.x - 30, this.location.y));
        }
        else return this;
    }


    // moves player down every tick 
    Player gravity() {
        return new Player (new Posn (this.location.x, (this.location.y + 10)));
    }

    // hitedges: checks if it has hit the edges and if it has it changes
    // it to the other side (i.e. if it hits 0 give 95 and if it hits 100 give 5)
    Player hitEdges(boolean result, int maxwidth, int minwidth) {
        if (this.maxEdges(maxwidth)) {
            return new Player (new Posn(5, this.location.y));
        }
        else if (this.minEdges(minwidth)) {
            return new Player (new Posn(maxwidth - 5, this.location.y));
        }
        else return this;
    }
    // maxedges : helper for hitEdges
    // checks if the player has hit the max width of the canvas 

    boolean maxEdges(int maxwidth) {
        return this.location.x >= maxwidth;
    }


    // minedges : helper for hitEdges
    // checks if the player has hit the min width of the canvas 
    boolean minEdges(int minwidth) {
        return this.location.x <= minwidth;
    }

    //method hitmax 
    // checks if y-cord of player is the same as the height (then die)
    // takes player and canvas and returns boolean
    boolean hitMax(int maxHeight) {
        return this.location.y >= maxHeight;
    }
 // calculate the distance between the player and the blockm
    double distance(Block b) {
        return Math.sqrt(((b.location.x - this.location.x) * (b.location.x - this.location.x)) +
                ((b.location.y - this.location.y) * (b.location.y - this.location.y)));
    }
    
    
    // move the player up (jump) if it hits the block
    Player collide(ILoB listB, int tolerance) {
        if (listB.isEmpty()) {
            return this;
        } // posns are in the bottom left corner of the image
        else if (this.distance(listB.getFirst()) <= tolerance) {
            return new Player(new Posn( this.location.x, this.location.y - 10));
        }
        else {
            return this.collide(listB.getRest(), tolerance);
        }
    }
}


// represent a Block
class Block {
    Posn location;
    Block(Posn loc) {
        this.location = loc;
    }
    WorldImage blockImage() { // 50 x 15 px
        return new FromFileImage(this.location, "Blocks.png");
    }
    
    
}




//ASK TUTOR!!!!!!!!!!!!!!!
// represents DoodleJumpWorld
class DoodleJumpWorld extends World{
    int w = 700; // not sure
    int h = 400; // not sure
    Player p;
    ILoB blocks;
    DoodleJumpWorld(int w, int h, IColor bg, Player p, ILoB blocks) {
        this.w = w;
        this.h = h;
        this.p = p;
        this.blocks = blocks;
    }
    

    /** Move the player when the player presses a key */
    // NOT FINISHED
    public World onKeyEvent(String ke) {
        return new DoodleJumpWorld(this.p.move(ke), this.blocks.moveBlocks());
    }

    /** On tick move the Blob in a random direction.
     * NOT FINISHED
     */
    public World onTick() {
        return new DoodleJumpWorld(this.p.gravity(), this.blocks);
    }


    //method helperjump {}- it checks the x and y cord of the player and compares it to the list of blocks in the canvas
    //                      takes a list of blocks and a player and returns a boolean
    boolean helperJump(Player p, Block b) {
        return p.location.y == b.location.y &&
                p.location.x
    }
    // ASK TUTOR!!!!!!!!!!!!!!!!!!!!!!!!!


    // method halfway- checks if the player is at the 1/4 point of the so generate a new list of blocks
    boolean quarterway(Player p) {
        return p.location.y == (this.h - this.h / 4); 
    }

    // method movedown- if player is at halfway point delate bottom 25% of canvas and create new top 25% of canvas
    //                  takes in a boolean (from halfway) and a cavas and returns a canvas



    /**
     * The entire background image for this world
     * It illustrates the use of most of the <code>WorldImage</code> shapes
     */
    public WorldImage scene = 
            new OverlayImages(new RectangleImage(new Posn(200, 350), 
                    this.width, this.height, new Blue()),
                    new OverlayImages(new EllipseImage(new Posn(12, 12), 25, 25, new Green()),
                            new OverlayImages(new DiskImage(new Posn(100, 150), 10, new Black()),
}    



// represent a list of Blocks
interface ILoB {
    //method newlist {}-checks if its halfway point and if it is it creates a new list of blocks but only 25% of whats on the canvas
    //                  takes in a boolean and a list of blocks and returns a list of blocks


    public boolean isEmpty();
    public Block getFirst();
    public ILoB getRest();
    
}


// represent an empty list of blocks
class MtLoB implements ILoB{
    MtLoB() { }

    // checks if the list is empty
    public boolean isEmpty() {
        return true;
    }

    // get the first of the emptu list and throw an error
    public Block getFirst() {
        throw new IllegalArgumentException("no first in the empty list");
    }

    // get the rest of the list
    public ILoB getRest() {
        return this;
    }

}

// represent a non-empty list of blocks
class ConsLoB implements ILoB{
    Block first;
    ILoB rest;
    ConsLoB(Block first, ILoB rest) {
        this.first = first;
        this.rest = rest;
    }

    // checks if the list is empty
    public boolean isEmpty() {
        return false;
    }

    // get the first of this list
    public Block getFirst() {
        return this.first;
    }

    // return the rest of this list
    // dont forget to check is the rest is empty when using this method
    public ILoB getRest() {
        return this.rest;
    }
    
}






