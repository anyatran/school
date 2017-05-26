/*
    +--------------------------+
    | interface ISame          |
    +--------------------------+
    | boolean same(Object obj) |
    +--------------------------+
              / \
               |
       - - - - - 
       |           
+-------------+ 
| Person      |   
+-------------+  
| String name |  
| int phone   |  
+-------------+  

           +-----------+                
           | ILoObject |<------------+
           +-----------+             |
           +-----------+             |
                / \                  |
                ---                  |
                 |                   |
       -------------------           |
       |                 |           |
+------------+    +----------------+ |
| MTLoObject |    | ConsLoObject   | |
+------------+    +----------------+ |
+------------+  +-| Object first   | |
                | | ILoObject rest |-+
                | +----------------+ 
                |
                |   +--------------------------+
                |   | interface ISame          |
                |   +--------------------------+
                |   | boolean same(Object obj) |
                |   +--------------------------+
                |             / \
                |              |
                |    - - - - - - 
                v    |
          +------------+
          | Object     |
          +------------+
          | Type ???   |
          | ...        |
          +------------+
*/

interface ISame{
  // is this the same as the given object?
  boolean same(Object obj);
}

class Person implements ISame{
  String name;
  int phone;
  
  Person(String name, int phone){
    this.name = name; 
    this.phone = phone;
  }

  // effect: change the phone number for this person to the given number
  void newPhone(int newNumber){
    this.phone = newNumber;
  }

  // is this the same person as the given object?
  boolean same(Object obj){
    if (obj instanceof Person)
      return this.samePerson((Person)obj);
         else
        return false;
/* alternately:
        throw new ClasCastException(
          "Cannot compare a Book with other object");
*/
  }

  // is this the same person as the given person?
  boolean samePerson(Person that){
    return 
      this.name.equals(that.name)
    && this.phone == that.phone;
  }
}


interface ILoObject extends ISame{
  // to compute the size of this list
  int size();

  // is the given book in this list?
  boolean contains (Object that);

  // is this the same as the given object?
  boolean same(Object obj);

  // change the phone number for the person with the given name 
  ILoObject changePhone(String name, int phone);

  // give a new phone number to the person with the given name 
  // effect: the person's phone number changes wherever person is referenced
  void newPhone(String name, int phone);

  // remove the person with the given name from this list
  // effect: the person will no longer be in this list
  void removePerson(String name);
}

class MTLoObject implements ILoObject{
  MTLoObject() {}

  int size(){ return 0; }

  boolean contains (Object that){
    return false;
  }

  // is this the same as the given object?
  boolean same(Object obj){ return obj instanceof MTLoObject; }

  // change the phone number for the person with the given name 
  ILoObject changePhone(String name, int phone){
    return this;
  }

  // give a new phone number to the person with the given name 
  // effect: the person's phone number changes wherever person is referenced
  void newPhone(String name, int phone){}

  // remove the person with the given name from this list
  // effect: the person will no longer be in this list
  void removePerson(String name){}
}

class ConsLoObject implements ILoObject{
  Object first;
  ILoObject rest;

  ConsLoObject(Object first, ILoObject rest){
    this.first = first;
    this.rest = rest;
  }

/* TEMPLATE:
... this.first ...                   -- Object
... this.rest ...                    -- ILoObject
... this.rest.size() ...             -- int
... this.rest.contains( Object ) ... -- boolean
*/
  int size(){ 
    return 1 + this.rest.size(); 
  }

  boolean contains (Object that){
    return ((ISame)this.first).same(that)
        || this.rest.contains(that);
  }

  // is this the same as the given object?
  boolean same(Object obj){ 
    if (obj instanceof ConsLoObject)
      return this.sameConsLoObject(((ConsLoObject)obj));
  }

  // is this the same as the given ConsLoObject?
  boolean sameConsLoObject(ConsLoObject that){ 
    return ((ISame)this.first).same(that.first) &&
           this.rest.same(that.rest);
  }
 

  // change the phone number for the person with the given name 
  ILoObject changePhone(String name, int phone){
    if (((Person)this.first).name.equals(name))
      return new ConsLoObject(new Person(name, phone),
                           this.rest);
    else
      return new ConsLoObject(this.first, 
                           this.rest.changePhone(name, phone));
  }

  // give a new phone number to the person with the given name 
  // effect: the person's phone number changes wherever person is referenced
  void newPhone(String name, int phone){
    if (((Person)this.first).name.equals(name))
      ((Person)this.first).newPhone(phone);
    else
      this.rest.changePhone(name, phone);
  }

  // remove the person with the given name from this list
  // effect: the person will no longer be in this list
  void removePerson(String name){
    if (((Person)this.first).name.equals(name))
      if (this.rest instanceof ConsLoObject){
        this.first = ((ConsLoObject)this.rest).first;
        this.rest = ((ConsLoObject)this.rest).rest;
      }
      else 
        signalError();
      
    else
      if (this.rest instanceof MTLoObject)
        signalError();
      else
        ((ConsLoObject)this.rest).removeAfter(name, this);
  }

  // remove the given from this rest of the list, knowing original list
  // effect: the given person will no longer be in the original list
  void removeAfter(String name, ConsLoObject acc){
    if (((Person)this.first).name.equals(name))
      if (this.rest instanceof MTLoObject)
        acc.rest = new MTLoObject();
      else{
        acc.first = ((ConsLoObject)this.rest).first;
        acc.rest = ((ConsLoObject)this.rest).rest;
      }
   else
     if (this.rest instanceof MTLoObject)
       signalError();
     else
      ((ConsLoObject)this.rest).removeAfter(name, this);
  }

  int error = 0;
  void signalError(){ error = error + 1; }
}


 

class Examples{
  Examples () {}

  Person bill = new Person("Bill", 2345);
  Person matt = new Person("Matt", 1234);
  Person anne = new Person("Anne", 7896);
  Person john = new Person("John", 8866);
  Person jane = new Person("Jane", 3456);
  
  ILoObject mtphlist = 
            new MTLoObject();
  ILoObject family = 
            new ConsLoObject(bill,
            new ConsLoObject(matt,
            new ConsLoObject(anne,
                           mtphlist)));

  ILoObject friends = 
            new ConsLoObject(anne,
            new ConsLoObject(john,
                           mtphlist));

  ILoObject work = 
            new ConsLoObject(john,
            new ConsLoObject(jane,
                           mtphlist));

  boolean testSize1 = 
    this.mtphlist.size() == 0;
  boolean testSize2 = 
    this.family.size() == 3;

  boolean testContains1 = 
    this.mtphlist.contains(this.bill) == 
      false;
  boolean testContains2 = 
    this.family.contains(this.matt) == 
      true;
  boolean testContains3 = 
    this.friends.contains(bill) == 
      false;

  boolean testChangePhone1 = 
    this.family.changePhone("Anne", 4444).same(
    new  ConsLoObject(bill,
            new ConsLoObject(matt,
            new ConsLoObject(new Person("Anne", 4444),
                             mtphlist))));

  boolean testChangePhone2 = 
    this.friends.same(
            new ConsLoObject(new Person("Anne", 4444),
            new ConsLoObject(john,
                           mtphlist)));

  // tests for the method newPhone in the class Person
  boolean testNewPhone(){
    Person rick = new Person("Rick", 3344);
    Person alie = new Person("Alie", 7777);
    rick.newPhone(5566);
    return rick.samePerson(new Person("Rick", 5566)) &&
           alie.samePerson(new Person("Alie", 7777));
  }

  boolean testNewPhoneResult = this.testNewPhone();

  // tests for removal of a person from a list
  boolean testRemovePerson1(){
    ILoObject list1 = 
            new ConsLoObject(bill,
            new ConsLoObject(anne,
            new ConsLoObject(matt,
                           mtphlist)));

    ILoObject list2 = 
            new ConsLoObject(anne,
            new ConsLoObject(john,
                           mtphlist));
    list1.removePerson("Anne");
    return list1.contains(anne) == false &&
           list2.contains(anne) == true;
  }

  boolean testRemovePerson2(){
    ILoObject list1 = 
            new ConsLoObject(bill,
            new ConsLoObject(anne,
            new ConsLoObject(matt,
                           mtphlist)));

    ILoObject list2 = 
            new ConsLoObject(bill,
            new ConsLoObject(john,
                           mtphlist));
    list1.removePerson("Bill");
    return list1.contains(bill) == false &&
           list2.contains(bill) == true;
  }

  boolean testRemovePerson3(){
    ILoObject list1 = 
            new ConsLoObject(bill,
            new ConsLoObject(anne,
            new ConsLoObject(matt,
                           mtphlist)));

    ILoObject list2 = 
            new ConsLoObject(anne,
            new ConsLoObject(matt,
                           mtphlist));
    list1.removePerson("Matt");
    return list1.contains(matt) == false &&
           list2.contains(matt) == true;
  }

  boolean testRemovePersonResult1 = testRemovePerson1();
  boolean testRemovePersonResult2 = testRemovePerson2();
  boolean testRemovePersonResult3 = testRemovePerson3();

  boolean testRemovePerson4(){
    ILoObject mtphlist = 
            new MTLoObject();
    ILoObject work = 
            new ConsLoObject(bill,
            new ConsLoObject(matt,
            new ConsLoObject(anne,
                           mtphlist)));

    ILoObject friends = 
            new ConsLoObject(john,
                           work);

    work.removePerson("Matt");
    return work.same(new ConsLoObject(bill
                     new ConsLoObject(anne,
                     mtphlist))) &&
           friends.same(new ConsLoObject(john,
                        new ConsLoObject(bill,
                        new ConsLoObject(matt,
                        new ConsLoObject(anne,
                        mtphlist)))));
  }

  boolean testRemovePersonResult4 = testRemovePerson4();

/*
Before:

   friends               work
     |                     |
     |                     |
     |          +-----+    |       +-----+           +-----+           +-----+
     v          |     v    v       |     v           |     v           |     v
+-------------+ |  +-------------+ | +-------------+ | +-------------+ | +----------+   
| first: john | |  | first: bill | | | first: matt | | | first: anne | | | mtphlist |  
| rest: --------+  | rest: --------+ | rest: --------+ | rest: --------+ +----------+  
+-------------+    +-------------+   +-------------+   +-------------+      


After:

   friends               work
     |                     |
     |                     |
     |          +-----+    |       +-----+           +-----+
     v          |     v    v       |     v           |     v
+-------------+ |  +-------------+ | +-------------+ | +----------+   
| first: john | |  | first: bill | | | first: anne | | | mtphlist |  
| rest: --------+  | rest: --------+ | rest: --------+ +----------+  
+-------------+    +-------------+   +-------------+      

*/

  
}
 