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
    // move the player left and right (the player is always gonna be at the bottom of the scene)
    Player movePlayer(String ke, int w){
        if (ke.equals("right")){
            return new Player(new Posn(this.borders(420, -10) + 10, w - 20));
        }
        else if (ke.equals("left")){
            return new Player(new Posn(this.borders(420, -10) - 10, w - 20));
        }
        else return this;
    }
    // checks if the player hits the borders on the left and right
    // and move to the other side of the scene
    int borders(int max, int min){
        if (this.p.x >= max)
            return min;
        else if (this.p.x <= min)
            return max;
        else return this.p.x;
    }
    
}
// class that represent the stone
class Stone{
    Posn p;
    //int radius = 20;
    //IColor c = new Black();
    Stone(Posn p) {
        this.p = p;
    }
    // 40 x 40 px
    WorldImage stoneImage(){
        return new FromFileImage(this.p, "Stone.png");
        //return new CircleImage(this.p, this.radius, this.c);
    } 
    // move the stone from top to bottom
    Stone moveStone(){
        if (this.p.y > 520) {
            return this.back();
        }
        else {
            return new Stone(new Posn(this.p.x, this.p.y + 6));
        }
    }
    // helper method to generate a random number for X posns in the range 0 to width
    public int randomIntX(int max){
        return (int) (-10 + (Math.random() * max));
    }

    // helper method to generate a random number for Y posn in randge from -20 to height
    public int randomIntY(int max){
        return (int) (-100 + (Math.random() * max));
    }


    // if the stone hits the bottom of the scene return back
    public Stone back(){
        if(this.p.y >= 510){
            return new Stone(new Posn(randomIntX(410), randomIntY(0)));
        }
        else return this;
    }
    
    
 // if the stone and the player are colliding then the player dies
    // assume it's 10 px for now; 28.28 is 1/2 of the diagonal of the player image
    boolean hit(Player a){
        return this.distance(a) <= 35.0;
    }
    // calculate the distance
    double distance(Player a){
        return Math.sqrt(((a.p.x - this.p.x) * (a.p.x - this.p.x)) + 
                ((a.p.y - this.p.y) * (a.p.y - this.p.y)));
    }
} 
// represents a list of stones
interface ILoS {
    public boolean isEmpty();
    // public Stone getFirst();
    //public ILoS getRest();
    public ILoS moveAllStones();
    public WorldImage drawStones(GameWorld g);
    public boolean getHit(Player a);
}
// represent an empty list of stones
class MtLoS implements ILoS{
    MtLoS() { }
    // checks if the list is empty
    public boolean isEmpty(){
        return true;
    }
     // get the first of the list
    /*public Stone getFirst(){
        throw new RuntimeException("there is no first in the empty list");
    }*/
    /* // get the rest of this list
    public ILoS getRest(){
        return this;
    } */
    // move all stones on the list
    public ILoS moveAllStones(){
        return this;
    }
    // draw stones on the scene. for empty case, just return the canvas
    public WorldImage drawStones(GameWorld g) {
        return g.bg();
    }
    
    public boolean getHit(Player a) {
        return false;
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
    /* // get the first of the list 
    public Stone getFirst(){
        return this.first;
    } */
    // get the rest of the list
    /*public ILoS getRest(){
        return this.rest;
    } */
    // move all stones in the list
    // ****** check if the list is empty then procede moveAllStones*********
    public ILoS moveAllStones(){
        return new ConsLoS(this.first.moveStone(), this.rest.moveAllStones());
    }
    // draw stones on the scene
    public WorldImage drawStones(GameWorld g) {
        return new OverlayImages(this.rest.drawStones(g), this.first.stoneImage());
    }
    
    public boolean getHit(Player a){
        return this.first.hit(a) || this.rest.getHit(a);
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
    public World onKeyEvent(String ke) {
        return new GameWorld(this.a.movePlayer(ke, this.height), 
                this.stones);
    }

    // generate a list of stones randomly
    /*public ILoS makeList(int n){
        return new ConsLoS(this.stones.getFirst().moveStone(), 
                this.makeList(n - 1));
    }
*/

    // random
    /*public int randomNum(int n){
        return (int) (0 + (Math.random() * n));
    }*/

    //
    public WorldImage bg() {
        return new OverlayImages (new RectangleImage(new Posn(200, 250), this.width, this.height, new White()),
                this.a.playerImage());
    }
    public WorldImage makeImage() {
        return new OverlayImages (this.bg(), this.stones.drawStones(this));
    }
    // last scene (have a text list game over)
    public WorldImage lastImage(String s){
        return new OverlayImages(this.makeImage(),
                new TextImage(new Posn(200, 250), s, 
                        Color.red));
    }
    /** On tick move the Blob in a random direction.
     */
    public World onTick() {
        return new GameWorld(this.a, this.stones.moveAllStones());
    }
    
    // gameover
    public WorldEnd worldEnds(){
        // if the player got hit by the stone - stop
        if (this.stones.getHit(this.a)) {
            return new WorldEnd(true,
                    new OverlayImages(this.makeImage(),
                            new TextImage(new Posn(200, 250), "Game Over", 
                                          Color.red)));
        }
        else return new WorldEnd(false, this.makeImage());
    }

}
/**
 * On mouse click move the blob to the mouse location, make the color red.
    public World onMouseClicked(Posn loc){
        return new BlobWorldFun(new Blob(loc, 20, new Red()));
    }*/
class ExamplesGame{
    // examples of data for the Blob class:
    Player p1 = new Player(new Posn(200, 480));
     Stone s1 = new Stone(new Posn(300, 0));
    Stone s2 = new Stone(new Posn(23, -50));
    Stone s3 = new Stone(new Posn(34, -130));
    Stone s4 = new Stone(new Posn(51, -260));
    Stone s5 = new Stone(new Posn(104, -300));
    Stone s6 = new Stone(new Posn(435, -367));
    Stone s7 = new Stone(new Posn(234, -412));
    Stone s8 = new Stone(new Posn(0, -560));
    Stone s9 = new Stone(new Posn(80, -24));
    Stone s10 = new Stone(new Posn(309, -73));
    Stone s11 = new Stone(new Posn(79, -346));
    Stone s12 = new Stone(new Posn(245, -545));
    Stone s13 = new Stone(new Posn(345, -165));
    // examples of list of stones
     ILoS l1 = new ConsLoS(s1, new ConsLoS(s2, new ConsLoS(s3, new ConsLoS(s4, 
             new ConsLoS(s5, new ConsLoS(s6, new ConsLoS(s7, new ConsLoS(s8,
                     new ConsLoS(s9, new ConsLoS(s10, new ConsLoS(s11, new ConsLoS(s12,
                             new ConsLoS(s13, new MtLoS())))))))))))));
    // examples of data for the BlobWorldFun class:
    GameWorld g1 = new GameWorld(this.p1, l1);
    boolean runAnimation = this.g1.bigBang(400, 500, 0.05);
}







