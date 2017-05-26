
// Represents a bank account
public abstract class Account {

    int accountNum;  // Must be unique
    int balance;     // Must remain above zero (others Accts have more restrictions)
    String name;     // Name on the account

    public Account(int accountNum, int balance, String name){
        this.accountNum = accountNum;
        this.balance = balance;
        this.name = name;
    }

    public boolean same(Account that){
        return (this.accountNum == that.accountNum) &&
                (this.balance == that.balance) &&
                (this.name.equals(that.name));
    }

    // produce the amount available for withdrawal from this account
    public abstract int amtAvailable();

    public abstract boolean isSavings();
    public abstract boolean isChecking();

    // Convert this Account into a Checking
    public abstract Checking toChecking();
    
    public abstract Savings toSavings();
}