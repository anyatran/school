
// Represents a checking account
public class Checking extends Account {

    int minimum; // The minimum account balance allowed

    public Checking(int accountNum, int balance, String name, int minimum) {
        super(accountNum, balance, name);
        this.minimum = minimum;
    }

    /* TEMPLATE:
     Fields:
     ... this.accountNum ...     -- int
     ... this.balance ...        -- int
     ... this.name ...           -- String
     ... this.minimum ...        -- int

     Methods:
     ... this.amtAvailable() ...  -- int
     ... this.sameChecking(Checking)-- bool
     ... this.isSavings()...      -- bool
     ... this.isChecking()...     -- bool
     ... this.toChecking()...     -- bool
     ... this.toSavings()...      -- bool
     ... this.same(Account)...    -- bool
     */
    // produce the amount available for withdrawal from this account
    public int amtAvailable() {
        return this.balance - this.minimum;
    }

    // checks if two checking accounts are the same
    public boolean sameChecking(Checking that) {
        return (this.accountNum == that.accountNum)
                && (this.balance == that.balance)
                && (this.name.equals(that.name))
                && (this.minimum == that.minimum);
    }

    // this method checks if the account is a checking account
    public boolean isChecking() {
        return true;
    }

    // this method checks if the account is a savings account
    public boolean isSavings() {
        return false;
    }

    // Produce a checking account from this account
    public Checking toChecking() {
        return this;
    }

    // Produce a checking account from this account
    public Savings toSavings() {
        throw new RuntimeException("wrong account");
    }

    // Is the given Account the same as this?
    public boolean same(Account that) {
        if (that.isChecking()) {
            return this.sameChecking(that.toChecking());
        } 
        else {
            return false;
        }
    }
}