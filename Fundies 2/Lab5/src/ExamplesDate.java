// assignment 5
// Tran, Anh 
// anhtran9
// Krutiansky, Brett
// brekru
import tester.Tester;

class ExamplesDates {
    // Good dates
    Date d20100228 = new Date(2010, 2, 28);   // Feb 28, 2010
    Date d20091012 = new Date(2009, 10, 12);  // Oct 12, 2009
    Date d20140204 = new Date(02, 04); // Feb 04, 2014
    Date d20140209 = new Date(02, 10); // Feb 10, 2014
    //Date d201402100 = new Date(02, 100); // wrong month
    Date d200910121 = new Date("Oct", 12, 2009);
    Date d20140210 = new Date("Feb", 10, 2014);


    // Bad date
    //Date dn303323 = new Date(-30, 33, 23);

    // test
    boolean test1(Tester t) {
        return t.checkConstructorException(
                new IllegalArgumentException("Invalid year: 53000"),
                "Date", 53000, 12, 30);

    }


    // wrong exception
    /* boolean test2(Tester t) {
        return t.checkConstructorException(
                new IllegalArgumentException("Invalid day: 30"),
                "Date", 2000, 12, 30);

    } */
}


