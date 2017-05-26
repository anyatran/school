import tester.*;

// Bank Account Examples and Tests
public class ExamplesBanking {

    Account check1 = new Checking(1, 100, "First Checking Account", 20);
    Account savings1 = new Savings(4, 200, "First Savings Account", 2.5);    
    Account check2 = new Checking(2, 400, "Second Checking Account", 10);
    Account savings2 = new Savings(3, 900, "Second Savings Account", 15);
    Account check3 = new Checking(5, 450, "Third Checking Account", 4);
    Account savings3 = new Savings(6, 873, "Third Savings Account", 3.2);
    Account savings3s = new Savings(6, 873, "Third Savings Account", 3.2);
    Account check3s = new Checking(5, 450, "Third Checking Account", 4);
    Account savingSame3 = new Savings(5, 450, "Third Checking Account", 4);

    Checking che1 = new Checking(1, 100, "First Checking Account", 20);    
    Checking che2 = new Checking(2, 400, "Second Checking Account", 10);
    Checking ch3 = new Checking(5, 450, "Third Checking Account", 4);
    Checking che3s = new Checking(5, 450, "Third Checking Account", 4);


    Savings sav1 = new Savings(4, 200, "First Savings Account", 2.5); 
    Savings sav2 = new Savings(3, 900, "Second Savings Account", 15);
    Savings sav3 = new Savings(6, 873, "Third Savings Account", 3.2);
    Savings sav3s = new Savings(6, 873, "Third Savings Account", 3.2);

    // Tests the exceptions we expect to be thrown when
    //   performing an "illegal" action.
    public boolean testAmtAvailable(Tester t) {
        return
                t.checkExpect(this.check1.amtAvailable(), 80) &&
                t.checkExpect(this.savings1.amtAvailable(), 200) &&
                t.checkExpect(this.savings2.amtAvailable(), 900) &&
                t.checkExpect(this.savings3.amtAvailable(), 873) &&
                t.checkExpect(this.check2.amtAvailable(), 390) &&
                t.checkExpect(this.check3.amtAvailable(), 446) ;
    }
    // tests for Same()
    public boolean testSame(Tester t) {
        return 
                t.checkExpect(this.check1.same(savings1), false) &&
                t.checkExpect(this.check3s.same(savingSame3), false) &&
                t.checkExpect(this.check3s.same(check3), true);
    }

    // tests for sameChecking()
    public boolean testSameChecking(Tester t) {
        return
                t.checkExpect(this.che1.sameChecking(che1), true) &&
                t.checkExpect(this.che1.sameChecking(che2), false);
    }
    // tests sameSavings()
    public boolean testSameSavings(Tester t) {
        return
                t.checkExpect(this.sav1.sameSavings(sav2), false) &&
                t.checkExpect(this.sav3.sameSavings(sav3s), true);
    }
    // tests for toChecking
    public boolean testToChecking(Tester t) {
        return
                t.checkExpect(this.che1.toChecking(), this.che1) &&
                t.checkException(new RuntimeException("wrong account"),
                        this.sav1, "toChecking");
    }
    // tests for toSaving()
    public boolean testToSaving(Tester t) {
        return
                t.checkException(new RuntimeException("wrong account"),
                        this.che1, "toSavings") &&
                t.checkExpect(this.sav1.toSavings(), this.sav1);
    }

    // tests isChecking()
    public boolean testIsChecking(Tester t) {
        return
                t.checkExpect(this.check1.isChecking(), true) &&
                t.checkExpect(this.savings1.isChecking(), false);
    }
    // tests isSaving
    public boolean testIsSaving(Tester t) {
        return
                t.checkExpect(this.check1.isSavings(), false) &&
                t.checkExpect(this.savings1.isSavings(), true);
    } 


}