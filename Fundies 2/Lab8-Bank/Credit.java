
// Represents a credit line account
public class Credit extends Account{

    int creditLine;  // Maximum amount accessible
    double interest; // The interest rate charged

    public Credit(int accountNum, int balance, String name, int creditLine, double interest){
        super(accountNum, balance, name);
        this.creditLine = creditLine;
        this.interest = interest;
    }
    // cant withdraw if the account is bellow -500
    public int withdraw(int amount){
        if (this.balance + amount > this.creditLine){
            throw new RuntimeException("Over credit limit");
        }
        else {
            this.balance = this.balance + amount;
            return this.balance;
        }
    }
    
    public int deposit(int amount){
        this.balance = this.balance - amount;
        return this.balance;
    }
    
}
