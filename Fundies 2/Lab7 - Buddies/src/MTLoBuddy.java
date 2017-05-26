
// represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {
    MTLoBuddy() { //empty lists are always empty
    }

    // EFFECT:
    // Change this person's buddy list so that it includes the given person


    public ILoBuddy addBuddy(Person buddy) {
        return new ConsLoBuddy(buddy, this);
    }

    public boolean isEmpty() {
        return true;
    }
    
    // get the first of the list
    public Person getFirst() {
        throw new RuntimeException("no first in the empty list");
    }
    // get the rest of the list
    public ILoBuddy getRest() {
        throw new RuntimeException("no rest in the empty list");
    }

    public int countCommon(ILoBuddy that) {
        if (!that.isEmpty()) {
            return 0;
        }
        else {
            return 1;
        }
    }
    
    public boolean contains(Person that) {
        return false;
    }
    
    public boolean connected(Person that) {
        return false;
    }
    
    public int length() {
        return 0;
    }
    
    public ILoBuddy accm(ILoBuddy a) {
        return a;
    }
    
    public int countAll(ILoBuddy checked) {
        return checked.length();
    }

}
