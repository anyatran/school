// assignment 1
// Tran Anh
// anhtran9
// Dylan Boudro
// dboody

import tester.*;

// to represent a list of Strings
interface ILoS {
    // combine all Strings in this list into one
    public String combine();

    public ILoS sort();

    public ILoS insert(String b);

    public boolean isSorted();

    public boolean correctPlace(String b);

    public ILoS merge(ILoS other);

    public ILoS mergeHelper(String b);

    public ILoS filter(String b);
}

// to represent an empty list of Strings
/*Template:
Fields:
none

Methods:
this.combine()                      -- String
this.sort()                         -- ILoS
this.insert(String)                 -- ILoS
this.isSorted()                     -- boolean
this.correctPlace(String)           -- String
this.merge(ILoS)                    -- ILoS
this.mergeHelper(ILoS)              -- ILoS
this.filter(String)                 -- ILoS

Methods for fields:
none


 */
class MtLoS implements ILoS {
    MtLoS() { // empty list
    }

    // combine all Strings in this list into one
    public String combine() {
        return "";
    }

    // to sort the list of Strings in lexicographic order
    public ILoS sort() {
        return this;
    }

    // helper functions for sort. insert the string to its correct place
    public ILoS insert(String b) {
        return new ConsLoS(b, this);
    }

    // checks if the list is sorted
    public boolean isSorted() {
        return true;
    }

    // checks is the String is in its correct place
    public boolean correctPlace(String b) {
        return true;
    }

    // merge two sorted list into one sorted list of Strings
    public ILoS merge(ILoS other) {
        return other;
    }

    // a helper for merge
    public ILoS mergeHelper(String b) {
        return new ConsLoS(b, this);
    }

    // creates a new list of Strings out of this
    // list that starts with a given letter
    public ILoS filter(String b) {
        return this;
    }
}

// to represent a nonempty list of Strings
/*Template:
Fields:
this.first                          -- String
this.rest                           -- ILoS

Methods:
this.combine()                      -- String
this.sort()                         -- ILoS
this.insert(String)                 -- ILoS
this.isSorted()                     -- boolean
this.correctPlace(String)           -- String
this.merge(ILoS)                    -- ILoS
this.mergeHelper(ILoS)              -- ILoS
this.filter(String)                 -- ILoS

Methods for fields:
this.first.concat(ILoS)             -- ILoS
this.rest.combine()                 -- String
this.rest.sort().insert(String)     -- ILoS
this.first.compareTo(String)        -- boolean
this.rest.insert(String)            -- ILoS
this.rest.correctPlace(String)      -- boolean
this.rest.isSorted()                -- boolean
this.rest.merge(ILoS)               -- ILoS
this.rest.mergeHelper(String)       -- ILoS
this.first.startWith(String)        -- Boolean
this.rest.filter(String)            -- ILoS

 */
class ConsLoS implements ILoS {
    String first;
    ILoS rest;

    ConsLoS(String first, ILoS rest) {
        this.first = first;
        this.rest = rest;
    }

    // combine all Strings in this list into one
    public String combine() {
        return this.first.concat(this.rest.combine());
    }

    // to sort the list of Strings in lexicographic order
    public ILoS sort() {
        return this.rest.sort().insert(this.first.toLowerCase());
    }

    // helper functions for sort. insert the string to its correct place
    public ILoS insert(String b) {
        if (this.first.compareTo(b) > 0) {
            return new ConsLoS(b, this);
        } 
        else {
            return new ConsLoS(this.first, this.rest.insert(b));
        }
    }

    // checks if the list is sorted
    public boolean isSorted() {
        return this.rest.correctPlace(this.first) && this.rest.isSorted();
    }

    // checks is the String is in its correct place
    public boolean correctPlace(String b) {
        return b.compareTo(this.first) <= 0;
    }

    // merge two sorted list into one sorted list of Strings
    public ILoS merge(ILoS other) {
        return this.rest.merge(other.mergeHelper(this.first));
    }

    // choose the correct String to insert in the list first
    public ConsLoS mergeHelper(String b) {
        if (this.first.compareTo(b) > 0) {
            return new ConsLoS(b, this);
        } 
        else if (this.first.compareTo(b) < 0) {
            return new ConsLoS(this.first, this.rest.mergeHelper(b));
        } 
        else {
            return new ConsLoS(b, new ConsLoS(this.first, this.rest));
        }
    }

    // creates a new list of Strings out of this list that starts with
    // a given letter
    public ILoS filter(String b) {
        if (this.first.startsWith(b)) {
            return new ConsLoS(this.first, this.rest.filter(b));
        } 
        else {
            return this.rest.filter(b);
        }
    }
}

// to represent examples for lists of strings
class ExamplesStrings {
    ILoS mt = new MtLoS();
    ILoS mary = new ConsLoS("Mary ", new ConsLoS("had ", new ConsLoS("a ",
            new ConsLoS("little ", new ConsLoS("lamb.", new MtLoS())))));
    ILoS marySorted = new ConsLoS("a ",
            new ConsLoS("had ", new ConsLoS("lamb.", new ConsLoS("little ",
                    new ConsLoS("mary ", new MtLoS())))));
    ILoS mcDonald = new ConsLoS("Old ", new ConsLoS("McDonald ", new ConsLoS(
            "had ", new ConsLoS("a ", new ConsLoS("farm.", new MtLoS())))));
    ILoS mcDonaldSorted = new ConsLoS("a ",
            new ConsLoS("farm.", new ConsLoS("had ", new ConsLoS("mcdonald ",
                    new ConsLoS("old ", new MtLoS())))));
    ILoS maryMcDonald = new ConsLoS("a ", new ConsLoS("a ", new ConsLoS(
            "farm.", new ConsLoS("had ", new ConsLoS("had ", new ConsLoS(
                    "lamb.", new ConsLoS("little ", new ConsLoS("mary ",
                            new ConsLoS("mcdonald ", new ConsLoS("old ",
                                    new MtLoS()))))))))));

    // test the method combine for the lists of Strings
    boolean testCombine(Tester t) {
        return t.checkExpect(this.mt.combine(), "")
                && t.checkExpect(this.mary.combine(), 
                        "Mary had a little lamb.");
    }

    // tests for sort()
    boolean testSort(Tester t) {
        return t.checkExpect(this.mt.sort(), mt)
                && t.checkExpect(this.mary.sort(), this.marySorted);
    }

    // test for correctPlace()
    boolean testCorrectPlace(Tester t) {
        return t.checkExpect(this.mt.correctPlace("a"), true)
                && t.checkExpect(this.mary.correctPlace("Mary "), true)
                && t.checkExpect(this.mary.correctPlace("lamb."), false)
                && t.checkExpect(this.marySorted.correctPlace("had "), false);
    }

    // tests for insert()
    boolean testInsert(Tester t) {
        return t.checkExpect(this.mt.insert("a"), new ConsLoS("a", mt))
                && t.checkExpect(this.mcDonaldSorted.insert("b "), new ConsLoS(
                        "a ", new ConsLoS("b ", new ConsLoS("farm.",
                                new ConsLoS("had ", new ConsLoS("mcdonald ",
                                        new ConsLoS("old ", new MtLoS())))))));
    }

    // tests for isSorted()
    boolean testisSorted(Tester t) {
        return t.checkExpect(this.mt.isSorted(), true)
                && t.checkExpect(this.mary.isSorted(), false)
                && t.checkExpect(this.marySorted.isSorted(), true);
    }

    // tests for merge()
    boolean testMerge(Tester t) {
        return t.checkExpect(this.mt.merge(marySorted), marySorted)
                && t.checkExpect(this.marySorted.merge(mcDonaldSorted),
                        maryMcDonald);
    }

    // tests for mergeHelper()
    boolean testMergeHelper(Tester t) {
        return t.checkExpect(this.mt.mergeHelper("a "), new ConsLoS("a ", mt))
                && t.checkExpect(this.mcDonaldSorted.mergeHelper("mary "),
                        new ConsLoS("a ", new ConsLoS("farm.", new ConsLoS(
                                "had ", new ConsLoS("mary ", new ConsLoS(
                                        "mcdonald ", new ConsLoS("old ",
                                                new MtLoS())))))));
    }

    // tests for filter()
    boolean testFilter(Tester t) {
        return t.checkExpect(this.mt.filter("a"), mt)
                && t.checkExpect(this.mary.filter("l"), new ConsLoS("little ",
                        new ConsLoS("lamb.", new MtLoS())))
                        && t.checkExpect(this.mcDonaldSorted.filter("f"), 
                                new ConsLoS("farm.", new MtLoS()));
    }
}