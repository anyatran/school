
// Represents the empty List of Accounts
public class MtLoA implements ILoA{
    
    MtLoA(){}
    
    public boolean isEmpty(){
        return true;
    }
    
    public Account getFirst(){
        throw new RuntimeException("no first");
    }
    
    public ILoA getRest(){
        throw new RuntimeException("no rest");
    }

    public int deposit(int amount, int acctnum){
        throw new RuntimeException("not found");
    }
    
    public ILoA removeAcct(int acct){
        throw new RuntimeException("not able");
    }
}

