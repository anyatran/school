// assignment 1

// represents type of pizza

interface IPizza {
}

// represents a plain pizza

class Plain implements IPizza {

    /*
     * TEMPLATE:
     * 
     * Fields:
     * 
     * ... this.crust ... -- String
     * 
     * ... this.cheese ... -- String
     */
    String crust;
    String cheese;
    Plain(String crust, String cheese) {
        this.crust = crust;
        this.cheese = cheese;
    }
}

// represents a fancy pizza

class Fancy implements IPizza {

    /*
     * TEMPLATE:
     * 
     * Fields:
     * 
     * ... this.base ... -- IPizza
     * 
     * ... this.topping ... -- topping
     */
    IPizza base;
    String topping;
    Fancy(IPizza base, String topping) {
        this.base = base;
        this.topping = topping;

    }

}

// examples class for IPizzas

class ExamplesPizza {
    IPizza order1 = new Fancy(new Fancy(new Plain("thin crust", "mozarella"),
    "anchovies"), "onions");
    IPizza order2 = new Fancy(new Fancy(new Fancy(new Plain(
    "deep dish", "mixed"), "pepperoni"), "peppers"), "mushrooms");

}