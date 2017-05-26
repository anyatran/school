import java.awt.Color;
import java.util.Random;

import tester.*;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;


// class that represent a player
class Player{
    Posn p;
    Player(Posn p) {
        this.p = p;
    }
    // 36 x 38 px
    WorldImage playerImage(){
        return new FromFileImage(this.p, "Player.png");
    } 

    // move the player (the player is always gonna be at the bottom of the scene)
    Player movePlayer(String ke, int h){
        if (ke.equals("right")){
            return new Player(new Posn(this.p.x + 40, h - 20));
        }
        else if (ke.equals("left")){
            return new Player(new Posn(this.p.x - 40, h - 20));
        }
        else return this;
    }



    // checks if the player hits the borders on the left and right
    // and move to the other side of the scene
    Player borders(int max, int min){
        if (this.p.x >= max)
            return new Player (new Posn(0, p.y));
        else if (this.p.x <= min)
            return new Player (new Posn(max, p.y));
        else return this;

    }
    // if the stone and the player are colliding then the player dies
    // assume it's 10 px for now; 28.28 is 1/2 of the diagonal of the player image
    boolean hit(Stone s){
        return this.distance(s) <= 56.56;
    }

    // calculate the distance
    double distance(Stone s){
        return Math.sqrt(((this.p.x - s.p.x) * (this.p.x - s.p.x)) + 
                ((this.p.y - s.p.y) * (this.p.y - s.p.y)));
    }
}



// class that represent the stone
class Stone{
    Posn p;
    int radius = 10;
    IColor c = new Black();
    Stone(Posn p) {
        this.p = p;
    }


    // 40 x 40 px
    WorldImage stoneImage(){
        return new FromFileImage(this.p, "Stone.png");
    } 

    // move the stone from top to bottom
    Stone moveStone(int max){
        return new Stone(new Posn(randomInt(max), this.p.y + 1)); // create a funciton that deals when the stone hit the height
    }

    // helper method to generate a random number in the range 0 to n the width of the scene
    public int randomInt(int max){
        return (int) (0 + (Math.random() * max));
    }
} 

// represents a list of stones
interface ILoS {
    public boolean isEmpty();
    public Stone getFirst();
    public ILoS getRest();
    public ILoS moveAllStones(int max);
    public WorldImage drawStones(GameWorld g);
}



// represent an empty list of stones
class MtLoS implements ILoS{
    MtLoS() { }

    // checks if the list is empty
    public boolean isEmpty(){
        return true;
    }

    // get the first of the list
    public Stone getFirst(){
        throw new RuntimeException("there is no first in the empty list");
    }

    // get the rest of this list
    public ILoS getRest(){
        return this;
    }

    // move all stones on the list
    public ILoS moveAllStones(int max){
        throw new RuntimeException("there is no rest in the empty list");
    }

    // draw stones on the scene. for empty case, just return the canvas
    public WorldImage drawStones(GameWorld g) {
        return new RectangleImage(new Posn (200, 250), g.width, g.height, new White());
    }

  

}


// represent a non-empty list of stones
class ConsLoS implements ILoS{
    Stone first;
    ILoS rest;
    ConsLoS(Stone first, ILoS rest) {
        this.first = first;
        this.rest = rest;
    }

    // checks if the list is empty
    public boolean isEmpty(){
        return false;
    }
    // get the first of the list 
    public Stone getFirst(){
        return this.first;
    }

    // get the rest of the list
    public ILoS getRest(){
        return this.rest;
    }

    // move all stones in the list
    // ****** check if the list is empty then procede moveAllStones*********
    public ILoS moveAllStones(int max){
        return new ConsLoS(this.first.moveStone(max), this.rest.moveAllStones(max));
    }
    // draw stones on the scene
    public WorldImage drawStones(GameWorld g) {
        return new OverlayImages(this.first.stoneImage(), this.rest.drawStones(g));
    }
}

class GameWorld extends World{
    int width = 400;
    int height = 500;
    Player a;
    ILoS stones;
    GameWorld(Player a, ILoS stones){
        super();
        this.a = a;
        this.stones = stones;
    }


    //
    public WorldImage bg() {
        return new OverlayImages (new RectangleImage(new Posn(200, 250), this.width, this.height, new White()),
                this.stones.drawStones(this));
    }

    public WorldImage makeImage() {
        return new OverlayImages (this.bg(),this.a.playerImage());
    }
    
    // last scene (have a text list game over)
    public WorldImage lastImage(String s){
        return new OverlayImages(this.makeImage(),
            new TextImage(new Posn(200, 250), s, 
                Color.red));
    }
}

/** On tick move the Blob in a random direction.
 */
//public World onTick() {
//  return new GameWorld(this.a, this.stones.moveAllStones(this));


/**
 * On mouse click move the blob to the mouse location, make the color red.

    public World onMouseClicked(Posn loc){
        return new BlobWorldFun(new Blob(loc, 20, new Red()));
    }*/


class ExamplesGame{
    
    // examples of data for the Blob class:
    Player p1 = new Player(new Posn(200, 480));
    
    Stone s1 = new Stone(new Posn(300, 100));
    Stone s2 = new Stone(new Posn(23, 100));
    Stone s3 = new Stone(new Posn(34, 90));
    Stone s4 = new Stone(new Posn(51, 90));
    Stone s5 = new Stone(new Posn(104, 90));
    Stone s6 = new Stone(new Posn(435, 90));
    Stone s7 = new Stone(new Posn(234, 90));
    Stone s8 = new Stone(new Posn(345, 90));
    
    
    // examples of list of stones
    ILoS l1 = new ConsLoS(s1, new ConsLoS(s2, new ConsLoS(s3, new ConsLoS(s4, new ConsLoS(s5, new MtLoS())))));
    
    
    
    // examples of data for the BlobWorldFun class:
    GameWorld g1 = new GameWorld(this.p1, this.l1);
    boolean runAnimation = this.g1.bigBang(400, 500, 0.3);
}

    
    
    
    
    
    