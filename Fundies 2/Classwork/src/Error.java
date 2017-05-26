import tester.Tester;
/*
// ClassTime(int h, int m, boolean isPM)

class ClockTime {
    int hours; // in range [0, 24)
    int min; // in range [0, 60)

    ClockTime(int hours, int min) {
        if (this.isInRange(0, 24, hours)) {
            this.hours = hours;
        } 
        else { // exception/error
            throw new RunTimeException("hour out of range");
        }
        if (this.isInRange(0, 60, min)) {
            this.min = min;
        } 
        else {
            throw new RunTimeException("minutes out of range");
        }
        
        public boolean isPM
    }

    boolean isInRange(int min, int max, int n) {
        return (min <= n) && (n < max);
    }
}

class RunTimeException {
    String message;

    RunTimeException(String message) {
        this.message = message;
    }
}

class ExamplesClock {
    boolean testClock(Tester t) {
        return t.checkConstructorException("ClockTime", new RunTimeException("hour out of range"), -5, 72);
    }
}
*/









/*
--- CSU213 Fall 2006 Lecture Notes ---------
Copyright 2006 Viera K. Proulx

Lecture 14: October 5, 2006
Overloading Constructors: Assuring Integrity of Data

(Runs in Java 1.5)
*/
public class ClockTime {
  
  int hours;
  int minutes;
  
  // data verifying constructor
  public ClockTime(int hours, int minutes){
    if (this.validHours(hours))
      this.hours = hours;
    else
      throw new RuntimeException("Invalid hours");
    
    if (this.validMinutes(minutes))
      this.minutes = minutes;
    else throw new RuntimeException("Invalid minutes");
  }
  
  // constructor that defines minutes by default
  public ClockTime(int hours){
    this(hours, 0);
  }
  
  
  private static boolean validHours(int hours){
    return 0 <= hours && hours < 24;
  }
  
  private static boolean validMinutes(int minutes){
    return 0 <= minutes && minutes < 60;
  }
  
  // to verify the data represents a correct time
  public boolean validTime(int hours, int minutes){
    return this.validHours(hours)
    && this.validMinutes(minutes);
  }
  
  // to represent this data as a string
  public String asString(){
    return new String("class ClockTime \n" +
        "  hours = " + this.hours + "\n" +
        "  minutes = " + this.minutes + "\n");
  }
  
  public static void main(String argv[]){
    
    ClockTime ct1 = new ClockTime(23, 38);
    ClockTime ct2 = new ClockTime(3);
    //ClockTime badHours = new ClockTime(25, 30);
    //ClockTime badMinutes = new ClockTime(22, 70);
    
    
    System.out.println(ct1.asString());
    System.out.println(ct2.asString());
    
    boolean testValidHours1 = ct1.validHours(23) == true;
    boolean testValidHours2 = ct1.validHours(27) == false;
      
    boolean testValidMinutes1 = ct1.validMinutes(38) == true;
    boolean testValidMinutes2 = ct1.validMinutes(70) == false;
    
    boolean testValidTime1 = ct1.validTime(12, 38) == true;
    boolean testValidTime2 = ct1.validTime(12, 70) == false;
    boolean testValidTime3 = ct1.validTime(27, 38) == false;
    boolean testValidTime4 = ct1.validTime(27, 70) == false;
    
    System.out.println(
        "testValidHours1: " + testValidHours1 + "\n" +
        "testValidHours2: " + testValidHours2 + "\n" +
        "testValidMinutes1: " + testValidMinutes1 + "\n" +
        "testValidMinutes2: " + testValidMinutes2 + "\n" + 
        "testValidTime1: " + testValidTime1 + "\n" + 
        "testValidTime1: " + testValidTime1 + "\n" + 
        "testValidTime2: " + testValidTime2 + "\n" + 
        "testValidTime3: " + testValidTime3 + "\n" + 
        "testValidTime4: " + testValidTime4); 
  }
}










