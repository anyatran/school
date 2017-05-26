import tester.Tester;

// to represent a list of persons
interface ILoP{ 
    public ILoP append(ILoP that);
    public boolean contains(ILoP that);


}


// to represent an empty list of persons
class MtLoP implements ILoP{
    MtLoP(){}

    public ILoP append(ILoP that){
        return that;
    }
    
    public boolean contains(ILoP that){
        return false;
    }
}


// to represent a nonempty list of persons
class ConsLoP implements ILoP{
    AT first;
    ILoP rest;
    ConsLoP(AT first, ILoP rest){
        this.first = first;
        this.rest = rest;
    }

    public ILoP append(ILoP that){
        return new ConsLoP(this.first, this.rest.append(that));
    }
    
    public boolean contains(ILoP that){
        if(this.first.samePerson(that)){
            return true;
        }
        else return this.rest.contains(that);
    }
}


// to represent an ancestor tree of a person, or an unknown ancestor
interface AT{ 
    public boolean isUnknown();
    public ILoP ancestorList();
    public boolean hasCommon(Person that);
    public boolean samePerson(AT mom);

}


// to represent an unknown ancestor in an ancestor tree
class Unknown implements AT{
    Unknown(){}
    public boolean isUnknown(){
        return true;
    }

    public ILoP ancestorList(){
        return new MtLoP();
    }
    
    public boolean hasCommon(Person that){
        return that.isUnknown();
    }

    public boolean samePerson(AT that){
        return that.isUnknown();
    }

}


// to represent a person in an ancestor tree
class Person implements AT{
    String name;
    AT mom;
    AT dad;
    Person(String name, AT mom, AT dad){
        this.name = name;
        this.mom = mom;
        this.dad = dad;
    }
    public boolean isUnknown(){
        return false;
    }


    public boolean samePerson(Person that){
        if (that.isUnknown()){
            return false;
        }
        else return (this.name.equals(that.name) && 
                this.mom.samePerson(that.mom) && 
                this.dad.samePerson(that.dad));
    }
    
    
    public ILoP ancestorList(){
        if (this.mom.isUnknown() && this.dad.isUnknown()){
            return new ConsLoP(this, new MtLoP());
        }
        else if (this.mom.isUnknown() && !this.dad.isUnknown()){
            return new ConsLoP(this, this.dad.ancestorList());
        }
        else if (!this.mom.isUnknown() && this.dad.isUnknown()){
            return new ConsLoP(this, this.mom.ancestorList());
        }
        else return new ConsLoP(this, this.mom.ancestorList().append(this.dad.ancestorList()));
    }
    
    
    public boolean hasCommon(Person that){
        if (this.ancestorList().contains(that)){
            return true;
        }
        else if (this.con)
        
    }
}

class ExamplesFamily{
    AT unknown = new Unknown();
    AT jon = new Person("Jon", this.unknown, this.unknown);
    AT val = new Person("Val", this.unknown, this.unknown);
    AT fay = new Person("Fay", this.unknown, this.unknown);
    AT liz = new Person("Liz", this.unknown, this.unknown);
    AT tom = new Person("Tom", this.unknown, this.unknown);
    AT cal = new Person("Cal", this.unknown, this.unknown);
    AT ron = new Person("Ron", this.unknown, this.unknown);
    AT may = new Person("May", this.unknown, this.jon);

    AT eli = new Person("Eli", this.liz, this.tom);
    AT pat = new Person("Pat", this.may, this.eli);
    AT sam = new Person("Sam", this.val, this.ron);
    AT dan = new Person("Dan", this.fay, this.ron);
    AT kim = new Person("Kim", this.pat, this.cal);
    AT zoe = new Person("Zoe", this.may, this.sam);
    AT ann = new Person("Ann", this.may, this.sam);


    ILoP mt = new MtLoP();
    ILoP list1 = new ConsLoP(this.ann, new ConsLoP(this.fay, new ConsLoP(this.tom, this.mt)));

    boolean testA(Tester t){
        return
                t.checkExpect(this.cal.ancestorList(),
                        new ConsLoP(this.cal, this.mt)) &&
                        t.checkExpect(this.eli.ancestorList(),
                                new ConsLoP(this.eli,
                                        new ConsLoP(this.liz,
                                                new ConsLoP(this.tom, this.mt)))) &&
                                                t.checkExpect(this.pat.ancestorList(),
                                                        new ConsLoP(this.pat,
                                                                new ConsLoP(this.may,
                                                                        new ConsLoP(this.jon,
                                                                                new ConsLoP(this.eli,
                                                                                        new ConsLoP(this.liz,
                                                                                                new ConsLoP(this.tom, this.mt)))))));

    }

}










