
// Represents a Bank with list of accounts
public class Bank {

    String name;
    ILoA accounts;

    public Bank(String name){
        this.name = name;

        // Each bank starts with no accounts
        this.accounts = new MtLoA();
    }

    // EFFECT: Add a new account to this Bank
    void add(Account acct){
        this.accounts = new ConsLoA(acct, this.accounts);
    }

    void removeAccount(int acct){
        this.accounts = this.accounts.removeAcct(acct);
    }

    // EFFECT: deposit
    int deposit(int amount, int acctnum){
        if(this.accounts.isEmpty()){
            throw new RuntimeException("not found");
        }
        else return this.accounts.deposit(amount, acctnum);
    }
    
}
