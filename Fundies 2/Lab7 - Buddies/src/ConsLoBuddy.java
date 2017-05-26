// represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

    Person first;
    ILoBuddy rest;

    ConsLoBuddy(Person first, ILoBuddy rest) {
        this.first = first;
        this.rest = rest;
    }

    // EFFECT:
    // Change this person's buddy list so that it includes the given person
    public ILoBuddy addBuddy(Person buddy) {
        if (this.contains(buddy)) {
            //(this.hasDirectBuddy(buddy)){
            return this;
        }
        else {
            return new ConsLoBuddy(buddy, this);
        }
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    // get the first of the list
    public Person getFirst() {
        return this.first;
        
    }
    // get the rest of the list
    public ILoBuddy getRest() {
        return this.rest;
        
    }

    //calculates the number of common friends in 2 lists
    public int countCommon(ILoBuddy that) {
        if (that.isEmpty()) {
            return 0;
        }
        else if (this.first.samePerson(that.getFirst())) {
            return 1 + (this.rest.countCommon(that.getRest()));
        }
        else {
            return this.rest.countCommon(that);
        }
    }

    public boolean contains(Person that) {
        if (this.first.samePerson(that)) {
            return true;
        }
        else {
            return this.rest.contains(that);
        }
    }


    public boolean connected(Person that) {
        if (this.contains(that)) {
            return true;
        }
        else {
            return (this.first.buddies.contains(that) || 
                    this.rest.connected(that));
        }
    }

    public int length() {
        return 1 + this.rest.length();
    }


    public ILoBuddy accm(ILoBuddy checked) {
        if (checked.contains(this.first)) {
            return this.rest.accm(checked);
        }
        else if (this.rest.isEmpty()) {
            return this.first.buddies.accm(
                    new ConsLoBuddy(this.first, checked));
        }
        else {
            return this.rest.accm(this.first.buddies.accm(
                    new ConsLoBuddy(this.first, checked)));
        }
                
    }
    
    public int countAll(ILoBuddy checked) {
        return this.accm(checked).length();
    }


}













