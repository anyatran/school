// assignment 4
// Tran, Anh
// anhtran9
// Krutiansky, Brett
// brekru

import java.awt.Color;
import tester.*;
import javalib.funworld.*;
import javalib.colors.*;
//import javalib.worldcanvas.*;
import javalib.worldimages.*;
/*Template for Player:
 Fields:
 this.p                                      -- Posn

 Methods:
 this.movePlayer(String, int)                -- Player
 this.borders(ing, int)                      -- boolean

Methods for Fields:
---
 */

// class that represent a player
class Player {
    Posn p;

    Player(Posn p) {
        this.p = p;
    }

    // 36 x 38 px
    WorldImage playerImage() {
        return new FromFileImage(this.p, "Player.png");
    }

    // move the player left and right (the player is always gonna be at the
    // bottom of the scene)
    Player movePlayer(String ke, int h) {
        if (ke.equals("right")) {
            return new Player(new Posn(this.borders(420, -10) + 10, h - 20));
        } 
        else if (ke.equals("left")) {
            return new Player(new Posn(this.borders(420, -10) - 10, h - 20));
        } 
        else {
            return this;
        }
    }

    // checks if the player hits the borders on the left and right
    // and move to the other side of the scene
    int borders(int max, int min) {
        if (this.p.x >= max) {
            return min;
        }
        else if (this.p.x <= min) {
            return max;
        }
        else {
            return this.p.x;
        }
    }

}

/*Template for Stone:
Fields:
this.p                                      -- Posn

Methods:
this.moveStone()                            -- Player
this.randomIntX(int)                        -- int
this.randomIntY(int)                        -- int
this.back()                                 -- Stone
this.hit(Player)                            -- boolean
this.distance(Player)                       -- int

Methods for Fields:

 */
// class that represent the stone
class Stone {
    Posn p;

    Stone(Posn p) {
        this.p = p;
    }

    // 40 x 40 px
    WorldImage stoneImage() {
        return new FromFileImage(this.p, "Stone.png");
    }

    // move the stone from top to bottom on every tick
    Stone moveStone() {
        if (this.p.y > 520) {
            return this.back();
        } 
        else {
            return new Stone(new Posn(this.p.x, this.p.y + 6));
        }
    }

    // helper method to generate a random number for X posns in the range 0 to
    // width
    public int randomIntX(int max) {
        return (int) (-10 + (Math.random() * max));
    }

    // helper method to generate a random number for Y posns in range from -20
    // to height
    public int randomIntY(int max) {
        return (int) (-100 + (Math.random() * max));
    }

    // if the stone hits the bottom of the scene return back
    public Stone back() {
        if (this.p.y >= 510) {
            return new Stone(new Posn(randomIntX(410), randomIntY(0)));
        } 
        else {
            return this;
        }
    }

    // if the stone and the player are colliding then the player dies
    boolean hit(Player a) {
        return this.distance(a) <= 35.0;
    }

    // calculate the distance
    double distance(Player a) {
        return Math.sqrt(((a.p.x - this.p.x) * (a.p.x - this.p.x))
                + ((a.p.y - this.p.y) * (a.p.y - this.p.y)));
    }
}

// represents a list of stones
interface ILoS {
    public ILoS moveAllStones();

    public WorldImage drawStones(GameWorld g);

    public boolean getHit(Player a);
}


/* Template for an empty list
 Fields:
 --
 Methods:
 this.moveAllStones()                              -- ILoS
 this.drawStones(GameWorld)                        -- WorldImage
 this.getHit(Player)                               -- boolean

 Methods for fields:
 ---
 */
// represent an empty list of stones
class MtLoS implements ILoS {
    // move all stones on the list
    public ILoS moveAllStones() {
        return this;
    }

    // draw stones on the scene
    public WorldImage drawStones(GameWorld g) {
        return g.bg();
    }

    // checks if the stones hit the player
    public boolean getHit(Player a) {
        return false;
    }
}

/* Template for a non empty list
Fields:
this.first                                        -- Stone
this.rest                                         -- ILoS
Methods:
this.moveAllStones()                              -- ILoS
this.drawStones(GameWorld)                        -- WorldImage
this.getHit(Player)                               -- boolean

Methods for fields:
this.first.moveStone()                            -- Stone
this.rest.moveAllStones()                         -- ILoS
this.rest.drawStones(GameWorld)                   -- WorldImage
this.first.stoneImage()                           -- WorldImage
this.first.hic(Player)                            -- boolean
this.rest.getHit(Player)                          -- boolean
 */
// represent a non-empty list of stones
class ConsLoS implements ILoS {
    Stone first;
    ILoS rest;

    ConsLoS(Stone first, ILoS rest) {
        this.first = first;
        this.rest = rest;
    }

    // move all stones in the list
    public ILoS moveAllStones() {
        return new ConsLoS(this.first.moveStone(), this.rest.moveAllStones());
    }

    // draw stones on the scene
    public WorldImage drawStones(GameWorld g) {
        return new OverlayImages(this.rest.drawStones(g),
                this.first.stoneImage());
    }

    // checks if the stones hit the player
    public boolean getHit(Player a) {
        return this.first.hit(a) || this.rest.getHit(a);
    }
}

/* Template for the GameWorld
 Fields:
 this.width                                    -- int
 this.height                                   -- int
 this.a                                        -- Player
 this.stones                                   -- ILoS

 Methods:
 this.onKeyEvent(String)                       -- World
 this.bg()                                     -- WorldImage
 this.makeImage()                              -- WorldImage
 this.lastImage(String)                        -- WorldImage
 this.onTick()                                 -- World 
 this.worldEnds()                              -- WorldEnd

 Methods for Fields:
 this.a.movePlayer(String, int)                -- Player
 this.a.playerImage()                          -- WorldImage
 this.stones.drawStondes(GameWorld)            -- WorldImage
 this.stones.moveAllStones()                   -- ILoS
 this.stones.getHit(Player)                    -- boolean

 */
class GameWorld extends World {
    int width = 400;
    int height = 500;
    Player a;
    ILoS stones;

    GameWorld(Player a, ILoS stones) {
        super();
        this.a = a;
        this.stones = stones;
    }

    // move the Player when the key is pressed
    public World onKeyEvent(String ke) {
        return new GameWorld(this.a.movePlayer(ke, this.height), this.stones);
    }

    // drawing the background + Player
    public WorldImage bg() {
        return new OverlayImages(new RectangleImage(new Posn(200, 250),
                this.width, this.height, new White()), this.a.playerImage());
    }

    // drawing bg() + List of Stones
    public WorldImage makeImage() {
        return new OverlayImages(this.bg(), this.stones.drawStones(this));
    }

    // last scene (have a text list game over)
    public WorldImage lastImage(String s) {
        return new OverlayImages(this.makeImage(), new TextImage(new Posn(200,
                250), s, Color.red));
    }

    // On tick move ILoS from top to bottom
    public World onTick() {
        return new GameWorld(this.a, this.stones.moveAllStones());
    }

    // gameover
    public WorldEnd worldEnds() {
        // if the player got hit by the stone - stop
        if (this.stones.getHit(this.a)) {
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(200, 250),
                            "Game Over", Color.red)));
        } 
        else {
            return new WorldEnd(false, this.makeImage());
        }
    }

}

class ExamplesGame {
    // examples of data for the Blob class:
    Player p1 = new Player(new Posn(200, 480));
    Player p2 = new Player(new Posn(210, 480));
    Player p3 = new Player(new Posn(190, 480));
    Stone s1 = new Stone(new Posn(300, 0));
    Stone s2 = new Stone(new Posn(23, -50));
    Stone s3 = new Stone(new Posn(34, -130));
    Stone s4 = new Stone(new Posn(51, -260));
    Stone s5 = new Stone(new Posn(104, -300));
    Stone s6 = new Stone(new Posn(530, -367));
    Stone s7 = new Stone(new Posn(234, -412));
    Stone s8 = new Stone(new Posn(0, -560));
    Stone s9 = new Stone(new Posn(80, -24));
    Stone s10 = new Stone(new Posn(309, -73));
    Stone s11 = new Stone(new Posn(79, -346));
    Stone s12 = new Stone(new Posn(245, -545));
    Stone s13 = new Stone(new Posn(345, -165));
    Stone s14 = new Stone(new Posn(210, 490));
    Stone s15 = new Stone(new Posn(210, 511));
    // examples of list of stones
    ILoS l1 = new ConsLoS(s1, new ConsLoS(s2, new ConsLoS(s3, new ConsLoS(s4,
            new ConsLoS(s5, new ConsLoS(s6,
                    new ConsLoS(s7, new ConsLoS(s8, new ConsLoS(s9,
                            new ConsLoS(s10, new ConsLoS(s11, new ConsLoS(s12,
                                    new ConsLoS(s13, new MtLoS())))))))))))));
    ILoS l2 = new ConsLoS(s1, new MtLoS());
    ILoS l3 = new ConsLoS(s1, new ConsLoS(s14, new MtLoS()));
    ILoS l4 = new ConsLoS(s14, new MtLoS());
    ILoS mt = new MtLoS();

    // examples of the background
    WorldImage bg1 = new OverlayImages(new RectangleImage(new Posn(200, 250),
            400, 500, new White()), this.p1.playerImage());

    WorldImage bg2 = new OverlayImages(new RectangleImage(new Posn(200, 250),
            400, 500, new White()), this.p2.playerImage());

    // examples of data for the BlobWorldFun class:
    GameWorld g1 = new GameWorld(this.p1, l1);
    GameWorld g2 = new GameWorld(this.p1, l2);
    GameWorld g3 = new GameWorld(this.p2, l3);
    GameWorld g4 = new GameWorld(this.p1, l4);
    boolean runAnimation = this.g1.bigBang(400, 500, 0.05);


    // test for movePlayer()
    boolean testMovePlayer(Tester t) {
        return t.checkExpect(this.p1.movePlayer("right", 500), p2) &&
                t.checkExpect(this.p1.movePlayer("left", 500), p3);
    }

    // test for borders()
    boolean testBorders(Tester t) {
        return t.checkExpect(this.p1.borders(500, 0), 200) &&
                t.checkExpect(this.p2.borders(190, 0), 0) &&
                t.checkExpect(this.p1.borders(600, 300), 600);
    }
    // tests for moveStone()
    boolean testMoveStone(Tester t) {
        return t.checkExpect(this.s1.moveStone(), 
                new Stone(new Posn(300, 6))) &&
                t.checkExpect(this.s2.moveStone(), 
                        new Stone(new Posn(23, -44))) ;
    }

    // test for hit()
    boolean testHit(Tester t) {
        return t.checkExpect(this.s14.hit(this.p1), true) &&
                t.checkExpect(this.s13.hit(this.p1), false);
    }

    // test for distance()
    boolean testDistance(Tester t) {
        return t.checkInexact(this.s14.distance(this.p1), 
                14.142135623730951, 1) &&
                t.checkInexact(this.s13.distance(this.p1), 661.09, 1);

    }

    // test for moveAllStones() (random case left)
    boolean testMoveAllStones(Tester t) {
        return t.checkExpect(this.mt.moveAllStones(), this.mt) &&
                t.checkExpect(this.l2.moveAllStones(), 
                        new ConsLoS(new Stone(new Posn(300, 6)), mt));
    }

    // test for drawStones
    boolean testDrawStones(Tester t) {
        return t.checkExpect(this.mt.drawStones(g2), 
                this.bg1) &&
                t.checkExpect(this.l2.drawStones(g2),
                        new OverlayImages(this.bg1,
                                new FromFileImage(
                                        new Posn(300, 0), "Stone.png")));
    }

    // tests for getHit()
    boolean testGetHit(Tester t) {
        return t.checkExpect(this.mt.getHit(p1), false) &&
                t.checkExpect(this.l2.getHit(p1), false) &&
                t.checkExpect(this.l3.getHit(p1), true);
    }

    // tests for onKeyEvent()
    boolean testOnKeyEvent(Tester t) {
        return t.checkExpect(this.g2.onKeyEvent("left"),
                new GameWorld(this.p3, this.l2)) &&
                t.checkExpect(this.g1.onKeyEvent("right"),
                        new GameWorld(this.p2, this.l1));
    }

    // tests for bg()
    boolean testBg(Tester t) {
        return t.checkExpect(g1.bg(),
                this.bg1) &&
                t.checkExpect(g3.bg(),
                        this.bg2);
    }

    // test for onTick
    boolean testOnTick(Tester t) {
        return t.checkExpect(this.g2.onTick(),
                new GameWorld(this.p1, 
                        new ConsLoS(new Stone(new Posn(300, 6)), mt)));
    }
    // tests for worldEnds()
    boolean testWorldEnds(Tester t) {
        return t.checkExpect(g4.worldEnds(), 
                new WorldEnd(true, new OverlayImages(g4.makeImage(),
                        new TextImage(new Posn(200, 250), "Game Over", 
                                Color.red)))) &&
                                t.checkExpect(g1.worldEnds(), 
                                        new WorldEnd(false, g1.makeImage()));
    }
}