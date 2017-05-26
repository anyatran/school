
// Represents a savings account
public class Savings extends Account{

    double interest; // The interest rate

    public Savings(int accountNum, int balance, String name, double interest){
        super(accountNum, balance, name);
        this.interest = interest;
    }
    // no limit
    public int withdraw(int amount){
        this.balance = this.balance - amount;
        return this.balance;
    }
    
    public int deposit(int amount){
        this.balance = this.balance + amount;
        return this.balance;
    }
}
