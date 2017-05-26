
// Represents a List of Accounts
public interface ILoA{
    public boolean isEmpty();
    public Account getFirst();
    public ILoA getRest();
    public int deposit(int amount, int acctnum);
    public ILoA removeAcct(int acct);
}

