// assignment 1
// Tran Anh
// anhtran9
// Boudro Dylan
// Dboody

// represents a person
class Person {

    /* TEMPLATE:
    Fields:
    ... this.name ...        -- String
    ... this.yob ...         -- int
    ... this.state ...       -- String
    ... this.citizen ...     -- boolean
     */

    String name;
    int yob;
    String state;
    boolean citizen;

    Person(String name, int yob, String state, boolean citizen) {
        this.name = name;
        this.yob = yob;
        this.state = state;
        this.citizen = citizen;
    }
}

// examples for the Person class
class ExamplePersons {
    Person jakie = new Person("Jakie Robinson", 1920, "NY", true);
    Person golda = new Person("Golda Meir", 1930, "MA", false);
    Person anh = new Person("Anh Tran", 1994, "MA", true);
    Person dylan = new Person("Dylan Boudro", 1994, "MA", true);
}

