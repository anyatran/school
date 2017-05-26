
// represents a list of Person's buddies
interface ILoBuddy {

    public ILoBuddy addBuddy(Person buddy);


    public boolean isEmpty();

    public Person getFirst();

    public ILoBuddy getRest();

    public int countCommon(ILoBuddy that);
    
    public boolean contains(Person that);
    
    public boolean connected(Person that);
    
    public int length();
    
    public ILoBuddy accm(ILoBuddy a);
    
    public int countAll(ILoBuddy checked);

}
