// assignment 5
// Tran, Anh 
// anhtran9
// Krutiansky, Brett
// brekru

// to represent a calendar date
class Date {
    int year;
    int month;
    int day;

    // represents a Date with year, month and day
    Date(int year, int month, int day) {
        if (this.validYear(year)) {
            this.year = year;
        }
        else {
            throw new IllegalArgumentException("Invalid year: " + year);
        }

        if (this.validMonth(month)) {
            this.month = month;
        }
        else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }

        if (this.validDay(day)) {
            this.day = day;
        }
        else {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
    }
    // represents a Date with a month, a day, and fixed year

    Date(int month, int day) {
        if (this.validYear(2014)) {
            this.year = 2014;
        }

        if (this.validMonth(month)) {
            this.month = month;
        }
        else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }

        if (this.validDay(day)) {
            this.day = day;
        }
        else {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

    // represents a Date where month is a String
    Date(String month, int day, int year) {
        // Invoke the prinmary constructor, with a valid month
        this(year, 1, day);

        // Change the month to the given one
        this.month = this.getMonthNo(month);
    }




    // Convert a three letter month into the numeric value
    int getMonthNo(String month) {
        if (month.equals("Jan")) { 
            return 1; 
        }
        else if (month.equals("Feb")) { 
            return 2; 
        }
        else if (month.equals("Mar")) { 
            return 3; 
        }
        else if (month.equals("Apr")) { 
            return 4; 
        }
        else if (month.equals("May")) { 
            return 5; 
        }
        else if (month.equals("Jun")) { 
            return 6; 
        }
        else if (month.equals("Jul")) { 
            return 7; 
        }
        else if (month.equals("Aug")) { 
            return 8; 
        }
        else if (month.equals("Sep")) { 
            return 9; 
        }
        else if (month.equals("Oct")) { 
            return 10; 
        }
        else if (month.equals("Nov")) { 
            return 11; 
        }
        else if (month.equals("Dec")) { 
            return 12; 
        }
        else {
            throw new IllegalArgumentException("Invalid month");
        }
    }


    // checks if the number is within the range from 0 to 100
    boolean validNumber(int min, int max, int number) {
        return (min <= number) &&
                (number < max);
    }

    boolean validDay(int day) {
        return this.validNumber(1, 32, day);
    }

    boolean validMonth(int month) {
        return this.validNumber(1, 13, month);
    }

    boolean validYear(int year) {
        return this.validNumber(1500, 50000, year);
    }

}