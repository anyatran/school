import tester.*;

// Examples and tests for Books, Songs, Images and lists of Books,
//   Songs, and Images
class ExamplesLists{
    ExamplesLists(){}

    Book oms = new Book("Old Man and the Sea", "Hemingway", 30);
    Book eos = new Book("Elements of Style", "EBW", 20);
    Book htdp = new Book("HtDP", "MF", 60);
    Book ll = new Book("Little Lisper", "MF", 25);

    ILo<Book> mtlob = new MtLo<Book>();
    ILo<Book> blist2 = new ConsLo<Book>(this.oms,
            new ConsLo<Book>(this.eos, this.mtlob));
    ILo<Book> blist3 = new ConsLo<Book>(this.htdp, this.blist2);
    ILo<Book> blist4 = new ConsLo<Book>(this.ll, this.blist3);

    Song help = new Song("Help", "Beatles", 283);
    Song hotelc = new Song("Hotel California", "Eagles", 276);
    Song yesterday = new Song("Yesterday", "Beatles", 195);
    Song safe = new Song("Safe and sound", "Taylor Swift", 201);

    ILo<Song> mtlos = new MtLo<Song>();
    ILo<Song> slist2 = new ConsLo<Song>(this.help,
            new ConsLo<Song>(this.hotelc, this.mtlos));
    ILo<Song> slist3 = new ConsLo<Song>(this.yesterday, this.slist2);
    ILo<Song> slist4 = new ConsLo<Song>(this.safe, this.slist3);

    ISelect<Book> price1 = new BookByPrice(26, 60);
    ISelect<Song> byTaylor = new SongBy("Taylor Swift");
    ISelect<Song> byZedd = new SongBy("Zedd");



    // Test the evenCount method for all lists
    void testEvenCount(Tester t){
        t.checkExpect(mtlob.evenCount(), true);
        t.checkExpect(blist2.evenCount(), true);
        t.checkExpect(blist3.evenCount(), false);
    }

    void testLength(Tester t){
        t.checkExpect(blist4.length(), 4);
    }

    void testToString(Tester t){
        t.checkExpect(this.safe.toString(), "Safe and sound, Taylor Swift, 201");
        t.checkExpect(this.eos.toString(), "Elements of Style, EBW, 20");
    }

    void testListString(Tester t){
        t.checkExpect(this.blist2.listString(), new ConsLo<String>("Old Man and the Sea, Hemingway, 30", 
                new ConsLo<String>("Elements of Style, EBW, 20", new MtLo<String>())));
        t.checkExpect(this.slist2.listString(), new ConsLo<String>("Help, Beatles, 283", 
                new ConsLo<String>("Hotel California, Eagles, 276", new MtLo<String>())));
    }

    void testFilter(Tester t){
        t.checkExpect(this.blist4.filter(this.price1), 
                new ConsLo<Book>(this.htdp, new ConsLo<Book>(this.oms, new MtLo<Book>())));
        t.checkExpect(this.slist4.filter(this.byTaylor), new ConsLo<Song>(this.safe, new MtLo<Song>()));
        t.checkExpect(this.slist4.filter(this.byZedd), new MtLo<Song>());
    }

    void testFind(Tester t){
        t.checkExpect(this.blist4.find(this.price1), this.htdp); 
        t.checkExpect(this.slist4.find(this.byTaylor), this.safe);
        
    }
}












