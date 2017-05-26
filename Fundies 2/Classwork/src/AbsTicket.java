// tickets to a science museum
interface ITicket { }

abstract class Admission implements ITicket {
    Date d;
    int price;
    Admission(Date d, int price) {
        this.d = d;
        this.price = price;
    }
}


// museum ticket
class OmniMax extends Admission {
    ClockTime t;
    String title;
    OmniMax(Date d, int price, ClockTime t, String title) {
        super(d, price);
        this.t = t;
        this.title = title;
    }
}
// to represent time
class ClockTime {
    int hour;
    int min;
    ClockTime(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }
}

// to represent a laser show
class LaserShow extends Admission {
    ClockTime t;
    String row;
    int seat;
    LaserShow(Date d, int price, ClockTime t, String row, int seat) {
        super(d, price);
        this.t = t;
        this.row = row;
        this.seat = seat;
    }
}

// to represent a date

class Date {
    int day;
    int month;
    int year;
    Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
