
// Represents a bank account
public abstract class Account {

    int accountNum;  // Must be unique
    int balance;     // Must remain above zero 
                     // (others Accts have more restrictions)
    String name;     // Name on the account

    public Account(int accountNum, int balance, String name) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.name = name;
    }

    // are these 2 accounts are the same
    public boolean same(Account that) {
        return (this.accountNum == that.accountNum) &&
                (this.balance == that.balance) &&
                (this.name.equals(that.name));
    }

    // produce the amount available for withdrawal from this account
    public abstract int amtAvailable();

    // checks if this account is savings
    public abstract boolean isSavings();
    // checks if this account is checking
    public abstract boolean isChecking();

    // Convert this Account into a Checking
    public abstract Checking toChecking();
    // Convert this Account into a Savings
    public abstract Savings toSavings();
}