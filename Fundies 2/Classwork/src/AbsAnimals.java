// zoo animals
interface IZooAnimal { }

// abstract
abstract class AAnimal implements IZooAnimal {
    String name;
    int weight;
    AAnimal(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}


// lion
class Lion extends AAnimal {
    int meat;
    Lion(String name, int weight, int meat) {
        super(name, weight);
        this.meat = meat;
    }

}

// snake
class Snake extends AAnimal {
    int length;
    Snake(String name, int weight, int length) {
        super(name, weight);
        this.length = length;
    }
}

class Monkey extends AAnimal {
    String food;
    Monkey(String name, int weight, String food) {
        super(name, weight);
        this.food = food;
    }
}

class ExamplesAnimals {
    ExamplesAnimals() {}
    IZooAnimal leo = new Lion("Leo", 300, 5);
    IZooAnimal boa = new Snake("Ana", 150, 5);
    IZooAnimal george = new Monkey("George", 150, "kiwi");
}







