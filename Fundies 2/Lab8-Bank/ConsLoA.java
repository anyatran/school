
// Represents a non-empty List of Accounts...
public class ConsLoA implements ILoA{

    Account first;
    ILoA rest;

    public ConsLoA(Account first, ILoA rest){
        this.first = first;
        this.rest = rest;
    }
    
    /* Template
     *  Fields:
     *    ... this.first ...         --- Account
     *    ... this.rest ...          --- ILoA
     *
     *  Methods:
     *
     *  Methods for Fields:
     *
     */
    
    public boolean isEmpty(){
        return false;
    }
    
    public Account getFirst(){
        return this.first;
    }
    public ILoA getRest(){
        return this.rest;
    }
    
    public int deposit(int amount, int acctnum){
        if(this.first.accountNum == acctnum){
            return this.first.deposit(amount);
        }
        else if (!(this.first.accountNum == acctnum)){
            return this.rest.deposit(amount, acctnum);
        }
        else
            return this.first.balance;
    }

    public ILoA removeAcct(int acct){
        if(this.first.accountNum == acct){
            return this.rest;
        }
        else return new ConsLoA(this.first, this.rest.removeAcct(acct));
    }
    
    
    
    
    
    
    
}