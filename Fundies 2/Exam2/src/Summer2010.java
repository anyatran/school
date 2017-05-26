import tester.Tester;

interface ILo<T>{
    public boolean isEmpty();
    public T getFirst();
    public ILo<T> getRest();

}

class MtLo<T> implements ILo<T>{
    MtLo(){};

    public boolean isEmpty(){
        return true;
    }

    public T getFirst(){
        throw new RuntimeException("empty list");
    }
    public ILo<T> getRest(){
        throw new RuntimeException("empty list");
    }

}

class ConsLo<T> implements ILo<T>{
    T first;
    ILo<T> rest;
    ConsLo(T first, ILo<T> rest){
        this.first = first;
        this.rest = rest;
    }
    public boolean isEmpty(){
        return false;
    }

    public T getFirst(){
        return this.first;
    }
    public ILo<T> getRest(){
        return this.rest;
    }

}


class Student{
    String name;
    int id;
    ILo<Course> courses = new MtLo<Course>();
    Student(String name, int id){
        this.name = name;
        this.id = id;
    }

    public boolean inList(ILo<Student> regList){
        if (regList.isEmpty()){
            throw new RuntimeException("the registrar list of students is empty");
        }
        else return ((regList.getFirst().id == this.id) || 
                (this.inList(regList.getRest())));
    }
}

class Course{
    String name;
    int credits;
    ILo<Student> students = new MtLo<Student>();
    Course(String name, int credits){
        this.name = name;
        this.credits = credits;
    }

    public boolean inList(ILo<Course> regList){
        if (regList.isEmpty()){
            throw new RuntimeException("the registrar list of students is empty");
        }
        else return ((regList.getFirst().name.equals(this.name)) || 
                (this.inList(regList.getRest())));
    }
}


class Registrar{
    ILo<Course> courses;
    ILo<Student> students;
    Registrar(ILo<Course> courses, ILo<Student> students){
        this.courses = courses;
        this.students = students;
    }

    Registrar register(String st, int idnum, String cls){
        Student std = new Student(st, idnum);
        Course crs = new Course(cls, 4);
        
        if (!std.inList(this.students) || (!crs.inList(this.courses)){
            throw new RuntimeException("cant register");
        }
        else 

        

    }
}





class Examples{
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

    ILo<Course> mtc = new MtLo<Course>();
    ILo<Student> mts = new MtLo<Student>();
    ILo<Course> allcourses = new ConsLo<Course>(this.math, new ConsLo<Course>(this.chem,
            new ConsLo<Course>(this.engl, new ConsLo<Course>(this.hist, new ConsLo<Course>(this.phys,
                    new ConsLo<Course>(this.comp, new ConsLo<Course>(this.band, new ConsLo<Course>(this.swim,
                            new ConsLo<Course>(this.draw, this.mtc)))))))));

    ILo<Student> allstudents = new ConsLo<Student>(this.jan, new ConsLo<Student>(this.dan,
            new ConsLo<Student>(this.kim, new ConsLo<Student>(this.pat, this.mts))));

    Registrar reg = new Registrar(this.allcourses, this.allstudents);

    void testInList(Tester t){
        t.checkExpect(this.jan.inList(this.allstudents), true);
        t.checkExpect(this.engl.inList(this.allcourses), true);
    }

}







