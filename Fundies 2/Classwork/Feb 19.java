/*
 Person                 ILoP
 St name        MtLoP                     ConsLoP
 int number                            Person first
                                        ILoP rest




 */

class Person{
    String name;
    int number;
    // this will be a new person but not updating the old person's number
    Person changeNumber(int newNumber){
        return new Person(this.name, newNumber);
    }
    
    // 3: mutation
    void changeNumber(int num){
        this.number = num;
    }
    
}


// 2
// only updates the person on 1 list that it was used 
// if the person on the other list as well, it won't get updated
class ConsLoP {
    void changePersonsNumber(String name, int number){
        if (this.first.name.equals.name){
            this.first = this.first.changeNumber(number);
        }
        else this.rest.changePersonsNumber(name, number);
    }
// add the person as the 2 on the list
    void insert(Person new, ICompare order){
        this.rest = new ConsLoP(new, this.rest);
    }
}
class ExamplePhone{
    Person abby = new Person("Abby", 123);
    Person bill = new Person("Bill", 512);
    Person clyde = new Person("Clyde", 234);
    Person debbie = new Person("Debbie", 236);
    Person ern = new Person("Ern", 304); 

    ILoP family = new ConsLoP(this.abby, new ConsLoP(this.bill, new ConsLoP(this.clyde, new MtLoP())));
    ILoP friends = new ConsLoP(this.debbie, new ConsLoP(this.ern, new ConsLoP(this.clyde, new MtLoP())));

    bill = bill.changeNiumber(672);
    t.checkExpect(bill.number, 672); ---> fail

    family.chanePersonsNumber("bill", 672); ---> fail
    t.checkExpect(bill.number, 672);

    oldBill = bill;
    bill = bill.changeNumber(777);
    newBill = oldBill.changeNumber(777);
    t.checkExpect(newBill, bill); ----> pass
    t.checkExpect(bill. oldBill); ---> fail

    Test:
        insert to front of list/end/same name
}


