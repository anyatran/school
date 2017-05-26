// assignment 6
// Tran Anh
// anhtran9
// Krutiansky, Brett
// brekru
import tester.*;
// to represent a list of Strings
interface ILoS {
    // combine all Strings in this list into one
    public String combine();
    public ILoS sort(StringCompare m);
    public ILoS insert(String b, StringCompare m);
    public boolean isSorted(StringCompare m);
    public boolean correctPlace(String b, StringCompare m);
    public ILoS merge(ILoS other, StringCompare m);
    public ILoS mergeHelper(String b, StringCompare m);
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
    public ILoS sort(StringCompare m) {
        return this;
    }
    // helper functions for sort. insert the string to its correct place
    public ILoS insert(String b, StringCompare m) {
        return new ConsLoS(b, this);
    }
    // checks if the list is sorted
    public boolean isSorted(StringCompare m) {
        return true;
    }
    // checks is the String is in its correct place
    public boolean correctPlace(String b, StringCompare m) {
        return true;
    }
    // merge two sorted list into one sorted list of Strings
    public ILoS merge(ILoS other, StringCompare m) {
        return other;
    }
    // a helper for merge
    public ILoS mergeHelper(String b, StringCompare m) {
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
    public ILoS sort(StringCompare m) {
        return this.rest.sort(m).insert(this.first.toLowerCase(), m);
    }
    // helper functions for sort. insert the string to its correct place
    public ILoS insert(String b, StringCompare m) {
        if (m.comesBefore(b, this.first)) {
            return new ConsLoS(b, this);
        } 
        else {
            return new ConsLoS(this.first, this.rest.insert(b, m));
        }
    }
    // checks if the list is sorted by one of the desired options
    public boolean isSorted(StringCompare m) {
        return this.rest.correctPlace(this.first, m) && 
                this.rest.isSorted(m);
    }
    // checks is the String is in its correct place
    public boolean correctPlace(String b, StringCompare m) {
        return m.comesBefore(b, this.first);
    }
    // merge two sorted list into one sorted list of Strings
    public ILoS merge(ILoS other, StringCompare m) {
        return this.rest.merge(other.mergeHelper(this.first, m), m);
    }
    // choose the correct String to insert in the list first
    // if s1 before s2, return s1
    // if s2 after s2, return s2
    public ConsLoS mergeHelper(String b, StringCompare m) {
        if (m.comesBefore(b, this.first)) {
            return new ConsLoS(b, this);
        } 
        else if (!(m.comesBefore(b, this.first))) {
            return new ConsLoS(this.first, this.rest.mergeHelper(b, m));
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
interface StringCompare {
    // checks if s1 is before s2
    public boolean comesBefore(String s1, String s2);
}
// checks if s1 is before s2
class StringLexComp implements StringCompare {
    public boolean comesBefore(String s1, String s2) {
        return s1.compareTo(s2) <= 0;
    }
}
//checks if s1 is before s2
class StringLengthComp implements StringCompare {
    public boolean comesBefore(String s1, String s2) {
        return s1.length() <= s2.length();
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
    ILoS randomSorted = new ConsLoS(
            "as ", new ConsLoS("ssq ", new ConsLoS("qqwf ", this.mt)));
    ILoS randomSorted2 = new ConsLoS(
            "asfd ", new ConsLoS("ssq ", new ConsLoS("qq ", this.mt)));
    StringCompare s1 = new StringLexComp();
    StringCompare s2 = new StringLengthComp();
    // test the method combine for the lists of Strings
    boolean testCombine(Tester t) {
        return t.checkExpect(this.mt.combine(), "")
                && t.checkExpect(this.mary.combine(), 
                        "Mary had a little lamb.");
    }
    // tests for sort()
    boolean testSort(Tester t) {
        return t.checkExpect(this.mt.sort(s1), mt)
                && t.checkExpect(this.mary.sort(s1), this.marySorted);
    }
    // test for correctPlace()
    boolean testCorrectPlace(Tester t) {
        return t.checkExpect(this.mt.correctPlace("a", s1), true)
                && t.checkExpect(this.mary.correctPlace("Mary ", s1), true)
                && t.checkExpect(this.mary.correctPlace("lamb.", s1), false)
                && t.checkExpect(this.marySorted.correctPlace("had ", s1), false)
                && t.checkExpect(this.marySorted.correctPlace("a ", s1), true)
                && t.checkExpect(this.mary.correctPlace("a ", s1), false)
                && t.checkExpect(this.marySorted.correctPlace("had ", s2), false)
                && t.checkExpect(this.randomSorted.correctPlace("a", s2), true);
    }
    // tests for insert()
    boolean testInsert(Tester t) {
        return t.checkExpect(this.mt.insert("a", s1), new ConsLoS("a", mt))
                && t.checkExpect(this.mcDonaldSorted.insert("b ", s1), 
                        new ConsLoS(
                                "a ", new ConsLoS("b ", new ConsLoS("farm.",
                                        new ConsLoS("had ", new ConsLoS("mcdonald ",
                                                new ConsLoS("old ", new MtLoS())))))));
    }
    // tests for isSorted()
    boolean testisSorted(Tester t) {
        return t.checkExpect(this.mt.isSorted(s1), true) &&
                t.checkExpect(this.marySorted.isSorted(s1), true) &&
                t.checkExpect(this.mary.isSorted(s1), false) && 
                t.checkExpect(this.marySorted.isSorted(s2), false) &&
                t.checkExpect(this.randomSorted2.isSorted(s2), false) &&
                t.checkExpect(this.randomSorted.isSorted(s2), true);
    }
    // tests for merge()
    boolean testMerge(Tester t) {
        return t.checkExpect(this.mt.merge(marySorted, s1), marySorted)
                && t.checkExpect(this.marySorted.merge(mcDonaldSorted, s1),
                        maryMcDonald);
    }
    // tests for mergeHelper()
    boolean testMergeHelper(Tester t) {
        return t.checkExpect(this.mt.mergeHelper("a ", s1), 
                new ConsLoS("a ", mt))
                && t.checkExpect(this.mcDonaldSorted.mergeHelper("mary ", s1),
                        new ConsLoS("a ", new ConsLoS("farm.", new ConsLoS(
                                "had ", new ConsLoS("mary ", new ConsLoS(
                                        "mcdonald ", new ConsLoS("old ",
                                                new MtLoS()))))))) &&

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