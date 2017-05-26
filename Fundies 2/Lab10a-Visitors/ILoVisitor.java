
// A visitor for the ILo<T> classes that 
// and produces the result of the type R
interface ILoVisitor<R, T> {
    // method for the empty list
    public R forMt(); 

    // method for the nonempty list
    public R forCons(T first, ILo<T> rest);
}

// A visitor that computes the total download time for all files 
// in the list of image files
class ILoImageDownloadTimeVisitor implements ILoVisitor<Integer, Image> {

    int speed;
    ILoImageDownloadTimeVisitor(int speed) {
        this.speed = speed;
    }

    // method for the empty list
    public Integer forMt() {
        return 0;
    }

    // method for the nonempty list
    public Integer forCons(Image first, ILo<Image> rest) {
        return first.fileSize / speed + rest.accept(this);
    }
    
    
    /* Template:
     *   Fields:
     *   
     *   Methods:
     *     ... this.forMt()                        -- Integer
     *     ... this.forCons(Image, ILo<Image>)     -- Integer
     *
     *   Methods for Fields:
     *
     **/
    
}

// A visitor that produce a list of song titles from given list
// of songs
class ILoSongTitles implements ILoVisitor<ILo<String>, Song> {

    // method for the empty list
    public ILo<String> forMt() {
        return new MtLo<String>();
    }

    // method for the nonempty list
    public ILo<String> forCons(Song first, ILo<Song> rest) {

        return new ConsLo<String>(first.title, rest.accept(this));

    }
    
    /* Template:
     *   Fields:
     *   
     *   Methods:
     *     ... this.forMt()                        -- ILo<String>
     *     ... this.forCons(Song, ILo<Song>)     -- ILo<String>
     *
     *   Methods for Fields:
     *
     **/

}

// A visitor that computes a string of the titles of all the 
// books in the list of books
class ILoBookTitles implements ILoVisitor<String, Book> {



    //method for the empty list
    public String forMt() {
        return "";
    }

    // method for the nonempty list
    public String forCons(Book first, ILo<Book> rest) {
        if (rest.isEmpty()) {
            return first.title;
        }
        else {
            return first.title + "\n" + rest.accept(this);


        }
    }
    
    /* Template:
     *   Fields:
     *   
     *   Methods:
     *     ... this.forMt()                        -- String
     *     ... this.forCons(Book, ILo<Book>)       -- String
     *
     *   Methods for Fields:
     *
     **/
}


