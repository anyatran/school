// to represent a pet owner
class Person {
    String name;
    Pet pet;
    int age;
 
    Person(String name, Pet pet, int age) {
        this.name = name;
        this.pet = pet;
        this.age = age;
    }
    
 // is this person older than the given person?
    boolean isOlder(Person other){
        return this.age > other.age;
    }
    
 // does the name of this person's pet match the given name?
    boolean samePetsName(Pet n){
        return this.name.equals(n.getName());
    }
}
// to represent a pet
interface Pet { 
    public String getName();
}
 
// to represent a pet cat
class Cat implements Pet {
    String name;
    String kind;
    boolean longhaired;
 
    Cat(String name, String kind, boolean longhaired) {
        this.name = name;
        this.kind = kind;
        this.longhaired = longhaired;
    }
    public String getName(){
        return this.name;
    }
    
    
}
 
// to represent a pet dog
class Dog implements Pet {
    String name;
    String kind;
    boolean male;
 
    Dog(String name, String kind, boolean male) {
        this.name = name;
        this.kind = kind;
        this.male = male;
    }
    
    public String getName(){
        return this.name;
    }
}



class NoPet implements Pet{
    NoPet() {}
    
    
    public String getName(){
        return "no pet";
    }
}












