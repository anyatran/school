import java.awt.Color;
import java.util.*;
import javalib.worldimages.*;
import tester.Tester;


class ExamplesMaze {
    Tree t;
    MazeGame m;
    ArrayList<Edge> e;
    Node n1;
    Node n2;
    Node n3;
    Node n4;
    Node n5;
    Node n6;
    Edge e1;
    Edge e2;
    Edge e3;
    Edge e4;
    Searching dfs;

    void init() {

        m = new MazeGame(100, 100);
        e = new ArrayList<Edge>();
        e = m.t.possibleEdges();


        e1 = new Edge();
        e2 = new Edge();
        e3 = new Edge();
        e4 = new Edge();


        n1 = new Node(0, 0);
        n2 = new Node(0, 1);
        n3 = new Node(0, 2);
        n4 = new Node(1, 1);

        n5 = new Node(5, 4);
        n6 = new Node(4, 4);

        n1.name = "0, 0";
        n2.name = "0, 1";
        n3.name = "0, 2";
        n4.name = "1, 1";
        n5.name = "5, 4";
        n6.name = "4, 4";

        n2.repr = n1;
        n4.repr = n2;
        n3.repr = n2;

        n5.repr = n6;

        e1.from = n1;
        e1.to = n2;

        e2.to = n1;
        e2.from = n2;

        e3.to = n3;
        e3.from = n2;

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);

    }

    void testHelpers(Tester t) {
        init();
        //m.t.print();
        this.m.a.doBFS(this.m.t);
        t.checkExpect(m.t.nodes.size(), 16);
        t.checkExpect(e.size(), 48); 
        t.checkExpect(m.t.edges.size(), 15);

        //will pull out a random edge each time
        //t.checkExpect(m.t.getEdge(allEdges), some edge) 

        // as the maze updates each time, each time you running the
        // game the output will be different
        //t.checkExpect(m.t.contains(e.get(8)), true/false);

        t.checkExpect(m.t.contains(e2), false);
        t.checkExpect(n4.getRepr(), n2.getRepr());
        t.checkExpect(n3.unions(n4), true);
        t.checkExpect(n5.unions(n4), false);
        t.checkExpect(e1.sameEdge(e2), true);
        t.checkExpect(e1.sameEdge(e3), false);
        t.checkExpect(e2.sameEdge(e3), false);
        t.checkExpect(n1.drawNode(), new RectangleImage(
                new Posn(0, 0), 18, 18, Color.GRAY));
        t.checkExpect(n2.drawCurrent(), new RectangleImage(
                new Posn(0, 1), 18, 18, Color.magenta));
        t.checkExpect(n3.drawBacktrack(), new RectangleImage(
                new Posn(0, 2), 18, 18, Color.white));
        t.checkExpect(n1.distance(n2), 1.0);
        t.checkExpect(n1.equals(n2), false);
        t.checkExpect(n1.equals(n1), true);
        t.checkExpect(n1.equals("0, 0"), false);
        t.checkExpect(n2.getRepr().name, "0, 0");
        t.checkExpect(n2.leftNeighbor(), false);
        t.checkExpect(n2.rightNeighbor(), false);
        t.checkExpect(n2.topNeighbor(), true);
        t.checkExpect(n2.getTop(), n1);
        t.checkExpect(n2.bottomNeighbor(), true);
        t.checkExpect(n2.getBottom(), n3);
        t.checkExpect(e1.drawEdge(), new LineImage(new Posn(0, 0), 
                new Posn(0, 1), Color.red));


    }


    void testBFS(Tester t) {
        init();
        this.m.a.doBFS(this.m.t);
        t.checkExpect(this.m.a.visited.size() != 0, true);
        t.checkExpect(this.m.a.current.name, "(20, 20)");
    }

    void testDFS(Tester t) {
        init();
        this.m.a.doDFS(this.m.t);
        t.checkExpect(this.m.a.visited.size() != 0, true);
        t.checkExpect(this.m.a.current.name, "(20, 20)");
    }

    void testWorld(Tester t) {
        init();
        this.m.onKeyEvent("d");
        t.checkExpect(this.m.a.d, true);
        init();
        this.m.onKeyEvent("b");
        t.checkExpect(this.m.a.b, true);
        init();

        t.checkExpect(this.m.drawPath(), 
                 new OverlayImages(new OverlayImages(new OverlayImages(
                        new OverlayImages(new RectangleImage(new Posn(0, 0), 
                            100 * 2, 100 * 2, Color.LIGHT_GRAY),
                            this.m.m.drawTree()), new RectangleImage(
                                new Posn(m.t.nodes.get(0).x, 
                                    m.t.nodes.get(0).y), 20, 20, Color.green)),
                                    new RectangleImage(new Posn(m.t.nodes.get(
                                        m.t.nodes.size() - 1).x, m.t.nodes.get(
                                            m.t.nodes.size() - 1).y),
                                            20, 20, Color.green)), 
                                            m.a.drawVisited()).overlayImages(
                                            m.a.drawCurrent()).overlayImages(
                                                    m.a.drawUltPath()));
        
        t.checkExpect(this.m.drawNothing(),
                new OverlayImages(new OverlayImages(new OverlayImages(
                        new RectangleImage(new Posn(0, 0), 100 * 2, 100 * 2,
                            Color.LIGHT_GRAY), new RectangleImage(new Posn(
                                m.t.nodes.get(0).x, 
                                this.m.t.nodes.get(0).y), 20, 20, Color.green)),
                                new RectangleImage(new Posn(this.m.t.nodes.get(
                                    this.m.t.nodes.size() - 1).x,
                                    this.m.t.nodes.get(
                                        this.m.t.nodes.size() - 1).y),
                                    20, 20, Color.green)),
                                    this.m.a.drawVisited()).overlayImages(
                                        this.m.a.drawCurrent()).overlayImages(
                                            this.m.a.drawUltPath()));
        
        t.checkExpect(this.m.drawWalls(), 
                new OverlayImages(new OverlayImages(new OverlayImages(
                        new OverlayImages(new OverlayImages(new RectangleImage(
                            new Posn(0, 0), 100 * 2, 100 * 2, 
                            Color.LIGHT_GRAY), this.m.m.drawMaze()), 
                            new RectangleImage(new Posn(
                                    this.m.t.nodes.get(0).x,
                                this.m.t.nodes.get(0).y), 
                                20, 20, Color.green)),
                                new RectangleImage(new Posn(this.m.t.nodes.get(
                                    this.m.t.nodes.size() - 1).x, 
                                    this.m.t.nodes.get(
                                        this.m.t.nodes.size() - 1).y),
                                        20, 20, Color.green)), 
                                        this.m.a.drawVisited()), 
                                        this.m.a.drawCurrent()).overlayImages(
                                            this.m.a.drawUltPath()));
        
        t.checkExpect(this.m.drawBoth(),
                new OverlayImages(new OverlayImages(new OverlayImages(
                        new OverlayImages(new RectangleImage(new Posn(0, 0), 
                            100 * 2, 100 * 2, Color.LIGHT_GRAY),
                            this.m.m.drawMaze().overlayImages(
                                    this.m.m.drawTree())),
                            new RectangleImage(
                                new Posn(this.m.t.nodes.get(0).x, 
                                    this.m.t.nodes.get(0).y),
                                    20, 20, Color.green)),
                                    new RectangleImage(new Posn(
                                            this.m.t.nodes.get(
                                            this.m.t.nodes.size() - 1).x, 
                                        this.m.t.nodes.get(
                                            this.m.t.nodes.size() - 1).y),
                                            20, 20, Color.green)),
                                            this.m.a.drawVisited())
                                            .overlayImages(
                                                this.m.a.drawCurrent())
                                                .overlayImages(
                                                    this.m.a.drawUltPath()));
        
        init();
        this.m.onKeyEvent("1");
        t.checkExpect(this.m.paths, true);
        
        init();
        this.m.onKeyEvent("2");
        t.checkExpect(this.m.walls, true);
        
        init();
        this.m.onKeyEvent("3");
        t.checkExpect(this.m.everything, true);
        
        init();
        this.m.onKeyEvent("4");
        t.checkExpect(this.m.nothing, true);
    }


}












