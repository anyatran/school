import tester.Tester;

interface ILoC{
    public boolean contains(String c);
    
}

class MtLoC implements ILoC{
    MtLoC(){};
    
    
    public boolean contains(String c){
        return false;
    }
}

class ConsLoC implements ILoC{
    Course first;
    ILoC rest;
    ConsLoC(Course first, ILoC rest){
        this.first = first;
        this.rest = rest;
    }
    // 
    public boolean contains(String c){
        return ((this.first.name.equals(c)) ||
                this.rest.contains(c));
    }
}



interface ILoS{
    public boolean contains(String s);
    
}

class MtLoS implements ILoS{
    MtLoS(){};
    public boolean contains(String s){
        return false;
    }
}

class ConsLoS implements ILoS{
    Student first;
    ILoS rest;
    ConsLoS(Student first, ILoS rest){
        this.first = first;
        this.rest = rest;
    }
    public boolean contains(String s){
        return ((this.first.name.equals(s)) ||
                this.rest.contains(s));
    }
    
}



class Student{
    String name;
    int id;
    ILoC courses = new MtLoC();
    Student(String name, int id){
        this.name = name;
        this.id = id;
    }

}

class Course{
    String name;
    int credits;
    ILoS students = new MtLoS();
    Course(String name, int credits){
        this.name = name;
        this.credits = credits;
    }

}


class Registrar{
    ILoC courses;
    ILoS students;
    Registrar(ILoC courses, ILoS students){
        this.courses = courses;
        this.students = students;
    }

}





class ExamplesReg{
    // sample students
    Student jan = new Student("Jan", 123);
    Student dan = new Student("Dan", 234);
    Student kim = new Student("Kim", 345);
    Student pat = new Student("Pat", 567);
    // sample courses
    Course math = new Course("Math", 4);
    Course chem = new Course("Chem", 4);
    Course engl = new Course("Engl", 4);
    Course hist = new Course("Hist", 4);
    Course phys = new Course("Phys", 4);
    Course comp = new Course("Comp", 4);
    Course band = new Course("Band", 2);
    Course swim = new Course("Swim", 2);
    Course draw = new Course("Draw", 2);
    Course choir = new Course("Choir", 2);

    ILoC mtc = new MtLoC();
    ILoS mts = new MtLoS();
    ILoC allcourses = new ConsLoC(this.math, new ConsLoC(this.chem,
            new ConsLoC(this.engl, new ConsLoC(this.hist, new ConsLoC(this.phys,
                    new ConsLoC(this.comp, new ConsLoC(this.band, new ConsLoC(this.swim,
                            new ConsLoC(this.draw, this.mtc)))))))));

    ILoS allstudents = new ConsLoS(this.jan, new ConsLoS(this.dan,
            new ConsLoS(this.kim, new ConsLoS(this.pat, this.mts))));

    Registrar reg = new Registrar(this.allcourses, this.allstudents);

    void testContains(Tester t){
        t.checkExpect(this.allcourses.contains(this.draw), true);
        t.checkExpect(this.allcourses.contains(this.choir), false);
        t.checkExpect(this.allstudents.contains(this.dan), true);
    }
}







