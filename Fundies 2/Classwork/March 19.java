interface IShape{
    boolean biggerThan(IShape i);
    double area();
    double distToOrg();
    double accept(IShapeVisitor<T> i);
}


interface IShapeVisitor<T>{ // function object
    T visitCircle(Circle c);
    T visitRect(Rect r);
    T visitTriangle(Triangle t);
    T visitSq(Square sq);
    T visitCombo(Combo combo);
}

class Circle{
    <T> public T accept(IShapeVisitor<T> f){
        return f.applyToCircle(this);
    }
}

class Rect{
    <T> public T accept(IShapeVisitor<T> f){
        return f.applyToRect(this);
    }
}


class Perimeter implements IShapeVisitor<Double>{
    public Double visitCircle(Circle c){
        return 2 * Math.PI * c.radius;
    }
    
    public Double visitSquare(Square s){
        return 4 * s.width;
    }
    
    public Double visitCombo(Combo c){
        return c.first.accept(this) + c.second.accept(this);
    }
}



class Examples{
    IShape c1 = new Circle(0, 0, 10);
    IShape s = new Square(3 4 5);
    
    IShapeVisitor<Double> perim = new Perimeter();
    
    // c.accept(perim) => 62.8....;
    // s.accept(perim) => 20;
            
}



