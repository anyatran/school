// to represent types of books
interface IBook { 
    public int daysOverdue(int due);
    public boolean isOverdue(int due);
    public int computeFine(int fine);
}

// to represent an abstraction of books

abstract class ABook implements IBook {
    String title;
    String author;
    int dayTaken;
    ABook(String title, String author, int dayTaken) {
        this.title = title;
        this.author = author;
        this.dayTaken = dayTaken;
    }
}


// to represent a reference book
class RefBook extends ABook {
    RefBook(String title, int dayTaken) {
        super(title, dayTaken);
    }
    public int daysOverdue(int due) {
        return due - (this.dayTaken + 2);
    }
    public boolean isOverdue(int due) {
        return 0 > this.daysOverdue(due);
    }
    public int computeFine(int fine, int due) {
        if (this.isOverdue(due)) {
            return this.daysOverdue(due) * 10;
        }
        else {
            return 0;
    }
}

// to reresent audiobook
class AudioBook extends ABook {
    AudioBook(String title, String author, int dayTaken) {
        super(title, author, dayTaken);
    }

    public int daysOverdue(int due) {
        return due - (this.dayTaken + 14);
    }

    public boolean isOverdue(int due) {
        return 0 > this.daysOverdue(due);
    }
    public int computeFine(int fine, int due) {
        if (this.isOverdue(due)) {
            return this.daysOverdue(due) * 20;
        }
        else {
            return 0;
        }
    }
}
}

