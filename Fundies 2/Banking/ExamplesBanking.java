import tester.*;

// Bank Account Examples and Tests
public class ExamplesBanking {

    public ExamplesBanking(){}

    Account check1 = new Checking(1, 100, "First Checking Account", 20);
    Account savings1 = new Savings(4, 200, "First Savings Account", 2.5);    
    Account check2 = new Checking(2, 400, "Second Checking Account", 10);
    Account savings2 = new Savings(3, 900, "Second Savings Account", 15);
    Account check3 = new Checking(5, 450, "Third Checking Account", 4);
    Account savings3 = new Savings(6, 873, "Third Savings Account", 3.2);
    Account savings3s = new Savings(6, 873, "Third Savings Account", 3.2);
    Account check3s = new Checking(5, 450, "Third Checking Account", 4);
    Account savingSame3 = new Savings(5, 450, "Third Checking Account", 4);



    // Tests the exceptions we expect to be thrown when
    //   performing an "illegal" action.
    public boolean testAmtAvailable(Tester t){
        return
                t.checkExpect(this.check1.amtAvailable(), 80) &&
                t.checkExpect(this.savings1.amtAvailable(), 200) &&
                t.checkExpect(this.savings2.amtAvailable(), 900) &&
                t.checkExpect(this.savings3.amtAvailable(), 873) &&
                t.checkExpect(this.check2.amtAvailable(), 390) &&
                t.checkExpect(this.check3.amtAvailable(), 446) ;
    }
    public boolean testSame(Tester t){
        return 
                t.checkExpect(this.check1.same(savings1), false) &&
                t.checkExpect(this.check3s.same(savingSame3), false) &&
                t.checkExpect(this.check3s.same(check3), true);
    }
    
    

}