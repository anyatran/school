import javalib.worldcanvas.WorldCanvas;
import javalib.worldimages.WorldImage;
import tester.Tester;


public class ExamplesMaze1 {

    Maze m1 = new Maze();
    Node zz = new Node(0,0);
    Node z1 = new Node(0,1);
    Node z2 = new Node(0,2);

    Node oneZ = new Node(1,0);
    Node oneOne = new Node(1,1);
    Node oneTwo = new Node(1,2);

    Node twoZ = new Node(2,0);
    Node twoOne = new Node(2,1);
    Node twoTwo = new Node(2,2);

    
    // dickin around
    boolean testAss(Tester t) {
        this.m1.genNodes(2, 2);
        this.m1.genEdge();
        this.m1.genMaze();
       
        

        
        
        
        return t.checkExpect(this.zz.name, "(0,0)") &&
                t.checkExpect(zz, zz);
    }
    // display the image on the canvas (no real tests are possible)

    boolean testDrawImage(Tester t) {
        WorldCanvas c = new WorldCanvas(100, 100);
        this.m1.genNodes(50, 50);
        this.m1.genEdge();
        this.m1.genMaze();
        
        return t.checkExpect(
                c.show() &&
                c.drawImage(this.m1.drawMaze()), true);
    }
    

}
