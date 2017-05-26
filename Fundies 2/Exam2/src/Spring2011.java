import tester.Tester;
import javalib.worldimages.Posn;

// to represent a location on the scene
class CartPt extends Posn{
    CartPt(int x, int y){
        super(x, y);
    }

    // produce a location offset from this one
    // by the given dx, dy
    CartPt offset(int dx, int dy){
        return new CartPt(this.x + dx, this.y + dy);
    }
    // compute the distance from this location
    // to the given one
    double distTo(Posn p){
        return Math.sqrt((this.x - p.x)*(this.x - p.x) +
                (this.y - p.y)*(this.y - p.y));
    }
}
// to represent a circle in a scene
class Circle{
    CartPt loc;
    int rad;
    Circle(CartPt loc, int rad){
        this.loc = loc;
        this.rad = rad;
    }

    public boolean overlaping(Circle c2){
        return (this.rad + c2.rad) > this.loc.distTo(c2.loc);
    }

    public Circle combine(Circle that){
        return new Circle(new CartPt(((this.loc.x + that.loc.x)/2), ((this.loc.y + that.loc.y)/2)), (this.rad + that.rad));

    }
}

//to represent a list of circles
interface ILoCircle{
    public boolean overlapHelper(Circle c);
    public Circle getFirst();
    public boolean isEmpty();
    public ILoCircle getRest();
    public boolean overlap();
    public ILoCircle coalesceHelper(Circle c);
    public ILoCircle coalesce();


}



//to represent an empty list of circles
class MtLoCircle implements ILoCircle{
    MtLoCircle(){}

    public boolean overlapHelper(Circle c){
        return false;
    }
    public Circle getFirst(){
        throw new RuntimeException("no first");
    }

    public ILoCircle getRest(){
        throw new RuntimeException("no rest");
    }

    public boolean isEmpty(){
        return true;
    }
    public boolean overlap(){
        return false;
    }

    public ILoCircle coalesceHelper(Circle c){
        return this;
    }
    public ILoCircle coalesce(){
        return this;
    }
}


//to represent a nonempty list of circles
class ConsLoCircle implements ILoCircle{
    Circle first;
    ILoCircle rest;
    ConsLoCircle(Circle first, ILoCircle rest){
        this.first = first;
        this.rest = rest;
    }
    public Circle getFirst(){
        return this.first;
    }

    public boolean isEmpty(){
        return false;
    }
    public ILoCircle getRest(){
        return this.rest;
    }
    public boolean overlapHelper(Circle c){
        if (c.overlaping(this.first)){
            return true;
        }
        else return (this.rest.overlapHelper(c));

    }
    public boolean overlap(){
        return this.rest.overlapHelper(this.first) || this.rest.overlap();
    }

    public ILoCircle coalesceHelper(Circle c){
        if (c.overlaping(this.first)){
            return new ConsLoCircle(c.combine(this.first), this.rest.coalesceHelper(c));
        }
        else return this.rest.coalesceHelper(c);
    }

    public ILoCircle coalesce(){
        if(!this.overlap()){
            return this;
        }
        else {
            return this.rest.coalesceHelper(this.first);
        }

    }
}

//to represent an ordering of circles
interface CircleOrdering{
    //is the first circle before the second one?
    int isBefore(Circle c1, Circle c2);
}

class CirclesBySize implements CircleOrdering{
    public int isBefore(Circle c1, Circle c2){
        return c1.rad - c2.rad;
    }
}

class CirclesToTop implements CircleOrdering{
    public int isBefore(Circle c1, Circle c2){
        return c1.loc.y - c2.loc.y;
    }



}


class ExamplesCircle{
    CartPt p1 = new CartPt(50, 100);
    CartPt p2 = new CartPt(-10, 100);
    CartPt p3 = new CartPt(50, 300);
    CartPt p4 = new CartPt(50, 450);
    CartPt p5 = new CartPt(450, 100);
    Circle c1 = new Circle(new CartPt(60, 40), 10);
    Circle c2 = new Circle(new CartPt(70, 20), 15);
    Circle c3 = new Circle(new CartPt(50, 90), 10);
    Circle c4 = new Circle(new CartPt(20, 20), 25);
    Circle c5 = new Circle(new CartPt(80, 40), 5);
    Circle c6 = new Circle(new CartPt(100, 20), 15);
    Circle c7 = new Circle(new CartPt(70, 80), 20);

    ILoCircle mt = new MtLoCircle();
    ILoCircle list1 = new ConsLoCircle(this.c5, new ConsLoCircle(this.c1, new ConsLoCircle(this.c3, this.mt)));
    ILoCircle list2 = new ConsLoCircle(this.c5, new ConsLoCircle(this.c1, new ConsLoCircle(this.c2, this.mt)));
    //ILoCircle list2 = new ConsLoCircle(this.c5, new ConsLoCircle(this.c1, new ConsLoCircle(this.c2, this.mt)));

    CircleOrdering size = new CirclesBySize();
    CircleOrdering top = new CirclesToTop();

    boolean testOrdering(Tester t){
        return t.checkExpect(this.size.isBefore(this.c1, this.c2), -5) &&
                t.checkExpect(this.top.isBefore(this.c1, this.c2), 20);
    }

    boolean testOverlaping(Tester t){
        return t.checkExpect(this.c1.overlaping(this.c2), true) &&
                t.checkExpect(this.c1.overlaping(this.c3), false);
    }

    boolean testOverlapHelper(Tester t){
        return t.checkExpect(this.list1.getRest().overlapHelper(this.list1.getFirst()), false) &&
                t.checkExpect(this.list2.getRest().overlapHelper(this.list2.getFirst()), false);
    }


    boolean testOverlap(Tester t){
        return t.checkExpect(this.list1.overlap(), false) &&
                t.checkExpect(this.list2.overlap(), true);
    }

    boolean testH(Tester t){
        return t.checkExpect(this.c5.combine(c1), new Circle(22, 44, 552));
    }

    boolean testC(Tester t){
        return t.checkExpect(this.list2.coalesceHelper(this.list2.getFirst()), this.list1);
    }

}





