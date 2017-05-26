import tester.Tester;

interface ILoS{
    public boolean contains(String c);
    public ILoS append(ILoS l);

}

class MtLoS implements ILoS{
    MtLoS(){};

    public boolean contains(String c){
        return false;
    }
    
    public ILoS append(ILoS l){
        return l;
    }

}

class ConsLoS implements ILoS{
    String first;
    ILoS rest;
    ConsLoS(String first, ILoS rest){
        this.first = first;
        this.rest = rest;
    }
    // 
    public boolean contains(String c){
        return this.first.equals(c) ||
                this.rest.contains(c);
    }
    
    public ILoS append(ILoS l){
        return new ConsLoS(this.first, this.rest.append(l));
    }

}


class ExamlpesString{
    ILoS s1 = new ConsLoS("a", new ConsLoS("w", new ConsLoS("f", new ConsLoS("q", new MtLoS()))));
    ILoS s2 = new ConsLoS("1", new ConsLoS("4", new ConsLoS("2", new ConsLoS("3", new MtLoS()))));
    ILoS s3 = new ConsLoS("a", new ConsLoS("w", new ConsLoS("f", new ConsLoS("q",
            new ConsLoS("1", new ConsLoS("4", new ConsLoS("2", new ConsLoS("3", new MtLoS()))))))));
    
    void testStirng(Tester t){
        t.checkExpect(s1.contains("s"), false);
        t.checkExpect(this.s1.append(s2), this.s3);
        
    }
    
}


