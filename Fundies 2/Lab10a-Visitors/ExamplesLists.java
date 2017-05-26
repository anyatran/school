import tester.*;

// Examples and tests for Books, Songs, Images and lists of Books,
//   Songs, and Images
class ExamplesLists {

    Book oms = new Book("Old Man and the Sea", "Hemingway", 30);
    Book eos = new Book("Elements of Style", "EBW", 20);
    Book htdp = new Book("HtDP", "MF", 60);
    Book ll = new Book("Little Lisper", "MF", 25);
    Book lion = new Book("Lion King", "Disney", 15);

    ILo<Book> mtlob = new MtLo<Book>();
    ILo<Book> blist2 = new ConsLo<Book>(this.oms,
            new ConsLo<Book>(this.eos, this.mtlob));
    ILo<Book> blist3 = new ConsLo<Book>(this.htdp, this.blist2);
    ILo<Book> blist4 = new ConsLo<Book>(this.lion, this.blist3);

    Song help = new Song("Help", "Beatles", 283);
    Song hotelc = new Song("Hotel California", "Eagles", 276);
    Song yesterday = new Song("Yesterday", "Beatles", 195);
    Song yellow = new Song("Yellow", "Bob", 200);

    ILo<Song> mtlos = new MtLo<Song>();
    ILo<Song> slist2 = new ConsLo<Song>(this.help,
            new ConsLo<Song>(this.hotelc, this.mtlos));
    ILo<Song> slist3 = new ConsLo<Song>(this.yesterday, this.slist2);
    ILo<Song> slist4 = new ConsLo<Song>(this.yellow, this.slist3);

    Image beach = new Image("Beach", "jpeg", 24348);
    Image park = new Image("Park", "gif", 33246);
    Image party = new Image("Party", "png", 4532);
    Image vaca = new Image("Vaca", "jpeg", 1200);

    ILo<Image> mtloi = new MtLo<Image>();
    ILo<Image> ilist2 = new ConsLo<Image>(this.beach, 
            new ConsLo<Image>(this.park, this.mtloi));
    ILo<Image> ilist3 = new ConsLo<Image>(this.party, this.ilist2);
    ILo<Image> ilist4 = new ConsLo<Image>(this.vaca, this.ilist3);

    // produce the visitor that computes the image download time in seconds
    // at given download speed
    ILoVisitor<Integer, Image> imageDownloads = 
            new ILoImageDownloadTimeVisitor(200);

    ILoVisitor<Integer, Image> imageDownloads2 = 
            new ILoImageDownloadTimeVisitor(100);



    // test the use of the ILoVisitor by computing the image download times
    void testILoVisitor(Tester t) {
        t.checkExpect(this.mtloi.accept(this.imageDownloads), 0);
        t.checkExpect(this.ilist2.accept(this.imageDownloads), 287);  
        t.checkExpect(this.ilist3.accept(this.imageDownloads2), 620);
        t.checkExpect(this.ilist4.accept(this.imageDownloads2), 632);

        // test the use of the ILoVisitor by producing list of Song titles
        t.checkExpect(this.slist2.accept(new ILoSongTitles()), 
                new ConsLo<String>("Help", 
                        new ConsLo<String>("Hotel California",
                                new MtLo<String>())));
        t.checkExpect(this.slist3.accept(new ILoSongTitles()),
                new ConsLo<String>("Yesterday", 
                        new ConsLo<String>("Help",
                                new ConsLo<String>("Hotel California",
                                        new MtLo<String>()))));
        t.checkExpect(this.slist4.accept(new ILoSongTitles()),
                new ConsLo<String>("Yellow", 
                        new ConsLo<String>("Yesterday", 
                                new ConsLo<String>("Help",
                                        new ConsLo<String>("Hotel California",
                                                new MtLo<String>())))));
        t.checkExpect(this.mtlos.accept(new ILoSongTitles()),
                new MtLo<String>());

        // test the use of the ILoVisitor by producing a string of 
        //all book titles      
        t.checkExpect(this.blist2.accept(new ILoBookTitles()), 
                "Old Man and the Sea" + "\n" + "Elements of Style");
        t.checkExpect(this.blist3.accept(new ILoBookTitles()), 
                "HtDP" + "\n" + "Old Man and the Sea" + "\n" + 
                "Elements of Style");
        t.checkExpect(this.blist4.accept(new ILoBookTitles()),
                "Lion King" + "\n" + "HtDP" + "\n" + "Old Man and the Sea" + 
                        "\n" +  "Elements of Style");
        t.checkExpect(this.mtlob.accept(new ILoBookTitles()), "");
    }

}