// assignment 1
// Tran Anh
// anhtran9
// Boudro Dylan
// Dboody


// to represent a leg of travel
interface ILeg { }

// to represent traveling by car
class Carleg implements ILeg {
    /* TEMPLATE:
    Fields:
    ... this.start ...        -- City
    ... this.end ...          -- City
    ... this.distance ...     -- int
     */
    City start;
    City end;
    int distance;

    Carleg(City start, City end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}

// to represent traveling by plane
class Planeleg implements ILeg {

    /* TEMPLATE:
    Fields:
    ... this.start ...        -- City
    ... this.end ...          -- City
    ... this.distance ...     -- int
     */
    City start;
    City end;
    int distance;

    Planeleg(City start, City end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}
// to represent traveling by train
class Trainleg implements ILeg {
    
    /* TEMPLATE:
    Fields:
    ... this.start ...        -- City
    ... this.end ...          -- City
    ... this.distance ...     -- int
     */
    
    City start;
    City end;
    int distance;

    Trainleg(City start, City end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}

// to represent a City
class City {
    /* TEMPLATE:
    Fields:
    ... this.name ...            -- String
    ... this.state ...           -- String 
    ... this.transportation ...  -- String
    ... this.airport ...         -- String
     */
    String name;
    String state;
    String trainstation;
    String airport;

    City(String name, String state, String trainstation, String airport) {
        this.name = name;
        this.state = state;
        this.trainstation = trainstation;
        this.airport = airport;
    }
}

// all examples of different traveling routes
class ExamplesTravel {

    City nyc = new City("NYC", "NY", "Penn train station", "JFK airport"); 
    City boston = new City("Boston", "MA", 
            "South train station", "Logan airport"); 
    City chicago = new City("Chicago", "IL", "Union train station", 
            "O'Hare airport"); 
    City columbus = new City("Columbus", "OH", "", "CNH airport"); 
    City erie = new City("Erie", "PA", "Union train station", ""); 
    City manchester = new City("Manchester", "NH", "", 
            "Manchester Boston Regional Airport");

    ILeg trip1 = new Carleg(columbus, manchester, 812);
    ILeg trip2 = new Carleg(erie, columbus, 244);

    ILeg trip3 = new Planeleg(nyc, boston, 215);
    ILeg trip4 = new Planeleg(chicago, columbus, 353);

    ILeg trip5 = new Trainleg(boston, erie, 541);
    ILeg trip6 = new Trainleg(boston, nyc, 215);
}
