
interface ISelect<T>{
    // a predicate that determines the properties of the given item
    public boolean select(T t);

}

class BookByPrice implements ISelect<Book>{
    int low;
    int high;

    public BookByPrice(int low, int high){
        this.low = low;
        this.high = high;
    }

    public boolean select(Book b){
        return ((this.low <= b.price) && (b.price <= this.high));
    }
}

class SongBy implements ISelect<Song>{
    String singer;
    public SongBy(String singer){
        this.singer = singer;
    }

    public boolean select(Song s){
        return s.artist.equals(this.singer);
    }
}