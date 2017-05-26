import tester.*;

// runs tests for the buddies problem
public class ExamplesBuddies {

    ILoBuddy mt;
    Person ann;
    Person bob;
    Person cole;
    Person dan;
    Person ed;
    Person fay;
    Person gabi;
    Person hank;
    Person jan;
    Person kim;
    Person len;

    


    void initBuddies() {
        mt = new MTLoBuddy();
        ann = new Person("Ann");
        bob = new Person("Bob");
        cole = new Person("Cole");
        dan = new Person("Dan");
        ed = new Person("Ed");
        fay = new Person("Fay");
        gabi = new Person("Gabi");
        hank = new Person("Hank");
        jan = new Person("Jan");
        kim = new Person("Kim");
        len = new Person("Len");
        
        this.ann.addBuddy(this.bob);
        this.ann.addBuddy(this.cole);

        this.bob.addBuddy(this.hank);
        this.bob.addBuddy(this.ed);
        this.bob.addBuddy(this.ann);

        this.cole.addBuddy(this.dan);
        this.dan.addBuddy(this.cole);

        this.ed.addBuddy(this.fay);

        this.fay.addBuddy(this.gabi);
        this.fay.addBuddy(this.ed);
        this.gabi.addBuddy(this.ed);
        this.gabi.addBuddy(this.fay);
        this.jan.addBuddy(this.kim);
        this.jan.addBuddy(this.len);
        this.kim.addBuddy(this.jan);
        this.kim.addBuddy(this.len);
        this.len.addBuddy(this.jan);
        this.len.addBuddy(this.kim);
    }

    public boolean testAddBuddy(Tester t) {
        initBuddies();
        return 
          t.checkExpect(this.cole.buddies, 
               new ConsLoBuddy(this.dan, this.mt)) &&          
            t.checkExpect(this.ed.buddies,
                new ConsLoBuddy(
                      this.fay, this.mt)) &&
                  t.checkExpect(
                     this.ann.buddies, new ConsLoBuddy(this.cole,
                         new ConsLoBuddy(this.bob, this.mt))) &&
                     t.checkExpect(
                      this.fay.buddies, new ConsLoBuddy(
                               this.ed,
                          new ConsLoBuddy(
                              this.gabi, this.mt))) &&
                           t.checkExpect(
                            this.bob.buddies, 
                               new ConsLoBuddy(
                                    this.ann,
                                   new ConsLoBuddy(
                                       this.ed, new ConsLoBuddy(
                                           this.hank,
                                           this.mt)))) &&
                                    t.checkExpect(
                                       this.gabi.buddies, 
                                         new ConsLoBuddy(this.fay,
                                             new ConsLoBuddy(
                                                this.ed, this.mt)));

    }
    
    public boolean testIsEmpty(Tester t) {
        initBuddies();
        return t.checkExpect(this.hank.buddies.isEmpty(), true) &&
                t.checkExpect(this.ann.buddies.isEmpty(), false);
    }
    
    public boolean testGetFirst(Tester t) {
        initBuddies();
        return //t.checkExpect(this.ann, this.bob) &&
                t.checkException(
                        new RuntimeException("no first in the empty list"), 
                        this.mt, "getFirst");
    }

    public boolean testGetRest(Tester t) {
        initBuddies();
        return //t.checkExpect(this.ann, this.cole) &&
        		t.checkException(
                        new RuntimeException("no rest in the empty list"), 
                        this.mt, "getRest");
    }
    
   // boolean testContains(Tester t) { }

    public boolean testCount(Tester t) {
        initBuddies();
        return
        		t.checkExpect(this.ed.countCommonBuddies(this.ann), 0) &&
        		//t.checkExpect(this.jan.countCommonBuddies(this.mt), 0) &&
                t.checkExpect(this.bob.countCommonBuddies(this.fay), 0);
    }

    public boolean testDistance(Tester t) {
        initBuddies();
        return
                t.checkExpect(this.ann.hasDistantBuddy(this.hank), true) &&
                t.checkExpect(this.ed.hasDistantBuddy(this.len), false) &&
                t.checkExpect(this.ed.hasDistantBuddy(this.kim), false) &&
                t.checkExpect(this.ann.hasDistantBuddy(this.len), false) &&
                t.checkExpect(this.hank.buddies.accm(
                        new MTLoBuddy()), new MTLoBuddy());
    }

    

    public boolean testPartyCount(Tester t) {
        initBuddies();
        return
                t.checkExpect(this.ann.partyCount(), 8) &&
                t.checkExpect(this.bob.partyCount(), 8) &&
                t.checkExpect(this.hank.partyCount(), 1) &&
                t.checkExpect(this.gabi.partyCount(), 3) &&
                t.checkExpect(this.ann.buddies.accm(this.mt).length(), 8);
    }
    
    public boolean testgetBuddies(Tester t) {
    	initBuddies();
    	return
                t.checkExpect(this.ann,
                		new ConsLoBuddy(this.cole, new ConsLoBuddy(
                                this.bob, this.mt)));
    }
}










