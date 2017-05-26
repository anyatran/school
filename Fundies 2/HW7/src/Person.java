
// represents a Person with a user name and a list of buddies
class Person {

    String username;
    ILoBuddy buddies;

    Person(String username) {
        this.username = username;
        this.buddies = new MTLoBuddy();
    }



    // EFFECT:
    // Change this person's buddy list so that it includes the given person
    void addBuddy(Person buddy) {
        this.buddies = new ConsLoBuddy(buddy, this.buddies);
    }


    boolean samePerson(Person that) {
        return (this.username.equals(that.username));
    }


    // returns the number of people that are direct buddies
    // of both this and that person
    int countCommonBuddies(Person that) {
        return this.buddies.countCommon(that.buddies);
    }
    // returns true if this Person has that as a direct buddy
    boolean hasDirectBuddy(Person that) {
        return this.buddies.contains(that);
    }

    // will the given person be invited to a party 
    // organized by this person?
//    boolean hasDistantBuddy(Person that) {
//        if (this.buddies.getFirst().hasDirectBuddy(that)){
//            return true;
//        }
//        else return this.buddies.getRest().connected(that);
//    }

    boolean hasDistantBuddy(Person that) {
        return this.buddies.accm(new MTLoBuddy()).contains(that);
    }



    int partyCount() {
        if (this.buddies.isEmpty()) {
            return 1;
        }
        else {
            return this.buddies.countAll(new MTLoBuddy());
        }

    }

    ILoBuddy getBuddies() {
        return this.buddies;
    }



}















