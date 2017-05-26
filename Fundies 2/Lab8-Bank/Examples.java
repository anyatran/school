import tester.*;

// Bank Account Examples and Tests
public class Examples {

    public Examples(){ 
        reset(); 
    }

    Account check1;
    Account savings1;
    Account check1a;
    ILoA list1;
    Bank b1;

    Checking c1 = new Checking(123, 300, "checking 1", 20);
    Savings s1 = new Savings(234, 200, "saving 1", 11.0);
    Credit cr1 = new Credit(333, 500, "credit 1", 100, 10.0);


    // Initializes accounts to use for testing with effects.
    // We place inside reset() so we can "reuse" the accounts
    public void reset(){

        // Initialize the account examples
        check1 = new Checking(1, 100, "First Checking Account", 20);
        check1a = new Checking(1, 75, "First Checking Account", 20);
        savings1 = new Savings(4, 200, "First Savings Account", 2.5);
        list1 = new ConsLoA(this.check1, new ConsLoA(this.s1, new MtLoA()));
        b1 = new Bank("me");
    }

    // Tests the exceptions we expect to be thrown when
    //   performing an "illegal" action.
    public void testExceptions(Tester t){
        reset();
        t.checkException("Test for invalid Checking withdraw",
                new RuntimeException("1000 is not available"),
                this.check1,
                "withdraw",
                1000);
    }

    // Test the withdraw method(s)
    public void testWithdraw(Tester t){
        reset();
        t.checkExpect(check1.withdraw(25), 75);
        t.checkExpect(check1, new Checking(1, 75, "First Checking Account", 20));
        reset();
    }

    // tests for add
    void testAdd(Tester t){
        reset();
        b1.add(check1a);
        b1.deposit(10, 1);
        t.checkExpect(b1.accounts, new ConsLoA(this.check1a, new MtLoA()));
        t.checkException("Test for invalid deposit",
                new RuntimeException("not found"),
                this.b1,
                "deposit",
                10, 22);
        t.checkExpect(b1.accounts.getFirst().balance, 85);
    }
    
    void testRemove(Tester t){
        reset();
        b1.add(check1a);
        b1.removeAccount(12);
        t.checkExpect(this.b1.accounts.isEmpty(), false);
    }


}
