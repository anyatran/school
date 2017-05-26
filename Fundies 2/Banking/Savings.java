
// Represents a savings account
public class Savings extends Account{

    double interest; // The interest rate

    public Savings(int accountNum, int balance, String name, 
    		           double interest){
        super(accountNum, balance, name);
        this.interest = interest;
    }

    /* TEMPLATE:
     Fields:
     ... this.accountNum ...     -- int
     ... this.balance ...        -- int
     ... this.name ...           -- String
     ... this.interest ...       -- double
     
     Methods:
     ... this.amtAvailable() ...  -- int
     ... this.name...             -- bool
     ... this.isSavings()...      -- bool
     */
    
    // produce the amount available for withdrawal from this account
    public int amtAvailable(){
    	  return this.balance;
    }
    public boolean sameSavings(Savings that) {
        return (this.accountNum == that.accountNum) &&
                (this.balance == that.balance) &&
                (this.name.equals(that.name)) &&
                (this.interest == that.interest);
    }
    
    public boolean isChecking() {
        return false;
    }
    
    public boolean isSavings() {
        return true;
    }
    
 // Produce a checking account from this account
    public Checking toChecking(){
      throw new RuntimeException("wrong account");
    }
    
    // produce a saving account from this account
    public Savings toSavings(){
        return this;
    }
    
 // Is the given Account the same as this?
    public boolean same(Account that){
        if(that.isSavings()){
            return this.sameSavings(that.toSavings());
        }
        else{
            return false;
        }
    }

}
