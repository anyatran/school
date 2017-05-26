interface ITrain { }
interface Schedule { }
interface Route { }
interface Stops { }

abstract class Train implements ITrain {
    Schedule s;
    Route r;
    Train(Schedule s, Route r) {
        this.s = s;
        this.r = r;
    }

}

class Express extends Train {
    Stops st;
    String name;
    Express(Schedule s, Route r, Stops st, String name) {
        super(s, r);
        this.st = st;
        this.name = name;
    }
}

//------------------------------------//---------------------------
interface IRest { }
class Place { 
    Place() {}
}

abstract class Restaurant implements IRest {
    String name;
    String price;
    Place place;
    Restaurant(String name, String price, Place place) {
        this.name = name;
        this.price = price;
        this.place = place;
    }
}

class ChineseRest extends Restaurant {
    boolean usesMSG;
    ChineseRest(String name, String price, Place place, boolean usesMSG) {
        super(name, price, place);
        this.usesMSG = usesMSG;
    }
}

//------------------------------------//---------------------------




















