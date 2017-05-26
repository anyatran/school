t.checkExpect(new CartPt(3, 4), new CartPt(3, 4));

class CartPt {
    double x;
    double y;
    CartPt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    boolean same(Cart Pt that) {
        return (this.x == that.x) &&
                (this.y == that.y);
    }
}

//==========================================

//                       Ancestor Tree (boolean same(IAT that))
//          Mother(name, yob)                     Father(name, yob)




/*
 3 strategies:
 1) "isUnknown" / "isPerson" helpers: checked/ safe casting
 2) "instanceOf" / "casting": fails badly on subcalsses
 3) double dispatch
 (new Rect(3, 3)) same(new Sq(3)) => true x     FALSE, beacuse it checks if it's a rect or not(type)
(new Sq(3) same new Rect(3,3)) => false

 */



class Unknown {
    boolean same(IAT that) {
        if (that.instanceOF Unknown) 

    }

    boolean isPerson(){
        return false;
    }
    boolean isUnknown{
        return true;
    }
    Unknown toUnknown(){
        return this;
    }
}


class Person{
    boolean same (IAT, that)
    return (this.name == that.name) &&
            (this.yob == that.yob);
    
    Person toPerson() {
        throw new Bad()
    }
    boolean isPerson() {
        return true;
    }
    
    boolean isUnkown() {
        return false;
    }
    Unknown toUnknown() {
        return new Bad();
    }
    Person toPerson() {
        return this;
    }

}


boolean same(Ishape that) {
    return that.sameRect(this);
}

boolean same(Ishape that) {
    return that.sameCircle(this);
}

boolean same(Ishape that) {
    return that.sameSquare(this);
}

boolean same(Rect that) {
    return (this.w == that.w) &&
            (this.h == that.h);
}

boolean sameRect (Rect that) {
    return false;
}


/*
               sameRect |  sameCircle | sameSq
circle           F            T          F
rect             T            F          F
square           F            F          T
          
 */


abstract class dShape extends IShape {
    boolean sameRect(Rect that) { 
        return false;
    }
    boolean sameCircle(Circle that) { 
        return false;
    }
    boolean sameSq(Sq that) { 
        return false;
    }
}







