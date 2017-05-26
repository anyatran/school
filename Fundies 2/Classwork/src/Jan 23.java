/*
 * IShape:

Circle:

CartPtt p ----
int r
String color ----
double area()
double distToOrg()
boolean closer(IShape) ----

Square
CartPtt p (posns) ----
ins size
String color ----
double area()
double distToOrg()
boolean closer(IShape) ----

Rectangle
CartPtt p
int h, w
String color


interface IShape {
    public double area();
    public double distToOrg();
    public boolean closer(IShape s);
    public boolean larger(IShape s);
}
 */

interface IShape { 
    public IShape combo();
}

// ABSTRACT CLASS FOR closer(IShape)
abstract class AShape implements IShape { //implements IShape 
    CartPtt p;
    String color;
    AShape(CartPtt p, String color) {
        this.p = p;
        this.color = color;


    }
    abstract double distToOrg();
    boolean closer(AShape that) {
        return this.distToOrg() < that.distToOrg();
    }

    abstract double area();
    boolean larger(AShape that) {
        return this.area() > that.area();
    }
}



class Circle3 extends AShape { // only needs R because x and y are in abstract already
    int radius;
    Circle3(CartPtt p, int r, String color) {
        super(p, color); // call subclasses from abs
        this.radius = r;

    }

    double area() {
        return Math.PI * this.radius * this.radius;
    }

    double distToOrg() {
        return this.p.distToOrg()  - this.radius;
    }
}


class Rect3 extends AShape {
    int h;
    int w;
    Rect3(CartPtt p, int h, int w, String color) {
        super(p, color);
        this.h = h;
        this.w = w;
    }

    double area() {
        return this.w * this.h;
    }

    double distToOrg() {
        return this.p.distToOrg();
    }
}


class Square3 extends Rect3 {
    Square3(CartPtt p, String c, int size) {
        super(p, size, size, c);
    }
}

class CartPtt {
    int x;
    int y;
    CartPtt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double distToOrg() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
}


// World is an abstracted class cos it has no fields


