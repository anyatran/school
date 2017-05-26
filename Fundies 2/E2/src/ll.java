import tester.Tester;

/////////////////////// DO-NOT-MODIFY ///////////////////
// Represents a single Movie screening, holding its name and showtime
class MovieInfo {
    int time;
    String title;
    MovieInfo(int time, String title) {
        this.time = time;
        this.title = title;
    }
    boolean sameMovie(MovieInfo that) {
        return this.time == that.time && this.title.equals(that.title);
    }
    public String toString() {
        return this.title + " @ " + Integer.toString(this.time);
    }
}

// Represents a comparator between movies
interface IMovieRanking {
    // Returns a negative number when m1 comes before m2
    int compare(MovieInfo m1, MovieInfo m2);
}
////////////////////////// END DO-NOT-MODIFY ////////////////////

// compares movies by showtime
class ByTime implements IMovieRanking{
    public int compare(MovieInfo m1, MovieInfo m2){
        return m1.time - m2.time; // negative if m1 is before m2
    }
}


// Represents a schedule of movies available to see at a given movie theater.
// Allows adding movies to the schedule
class BoxOffice {
    MovieSchedule schedule;
    IMovieRanking ranker;
    BoxOffice(IMovieRanking ranker) {
        this.ranker = ranker;
        this.schedule = new NoMovie();
    }
    // ???
    void addMovie(MovieInfo movie) {
        this.schedule.addMovie(movie, this.ranker);
    }
}

// Abstract base class representing a set of movies, organized as a binary search tree
abstract class MovieSchedule {
    // converts the schedule into a string
    abstract public String toString();
    // ???
    abstract void addMovie(MovieInfo movie, IMovieRanking ranker);
    // ???
   // abstract boolean satisfiesBSTProperty(IMovieRanking ranker);
    
    // checks if no movie
    abstract boolean isNoMovie();
}

// Represents a non-empty tree of movies, organized as a binary search tree
class Showing extends MovieSchedule {
    MovieInfo movie;
    MovieSchedule left, right;
    Showing(MovieInfo movie, MovieSchedule left, MovieSchedule right) {
        this.movie = movie;
        this.left = left;
        this.right = right;
    }
    public boolean isNoMovie(){
        return false;
    }
    // converts the schedule into a string
    public String toString(){
        if(this.left.isNoMovie() && this.right.isNoMovie()){
            return this.movie.toString();
        }
        else if (this.left.isNoMovie() && !this.right.isNoMovie()){
            return this.movie.toString().concat(", ".concat(this.right.toString()));
        }
        else if (!this.left.isNoMovie() && this.right.isNoMovie()){
            return this.left.toString().concat(", ".concat(this.movie.toString()));
        }
        else return this.left.toString().concat(", ".concat(this.movie.toString().concat(", ".concat(this.right.toString()))));
    }
    
    
    public void addMovie(MovieInfo movie, IMovieRanking ranker){
        if ((this.left.isNoMovie() && this.right.isNoMovie()) 
                && ranker.compare(movie, this.movie) <= 0){ // movie is before this
            this.left.addMovie(movie, ranker);
        }
        else if ((this.left.isNoMovie() && this.right.isNoMovie()) &&
                ranker.compare(movie, this.movie) > 0){ // movie is after this
            this.right.addMovie(movie, ranker);
        }
        else if (ranker.compare(movie, this.movie) <= 0){
            this.right.addMovie(movie, ranker);
        }
        else this.left.addMovie(movie, ranker);
    }
}

// Represents an empty tree of movies
class NoMovie extends MovieSchedule {
    NoMovie() { }
    
    public boolean isNoMovie(){
        return true;
    }
    
    public String toString(){
        return "";
    }
    
    public void addMovie(MovieInfo movie, IMovieRanking ranker){
        Showing s = new Showing(null, new NoMovie(), new NoMovie());
        s.movie = movie;
    }
}


class ExamplesMovie {
    MovieInfo hp = new MovieInfo(3, "Harry Potter");
    MovieInfo hg = new MovieInfo(5, "The Hungry Games");
    MovieInfo mi = new MovieInfo(6, "Mission Impossible");
    MovieInfo sh = new MovieInfo(9, "Sherlock Holmes");
    
    MovieSchedule nomovie;
    Showing s1;
    Showing s2;
    Showing s3;
    Showing s4;
    
    IMovieRanking bytime = new ByTime();
    
    BoxOffice b1;
    
    void init(){
        nomovie = new NoMovie();
        //nomovie.addMovie(this.hg, bytime);
        s1 = new Showing(this.hp, nomovie, nomovie);
        s2 = new Showing(this.hg, nomovie, nomovie);
        s3 = new Showing(this.mi, nomovie, nomovie);
        s4 = new Showing(this.sh, nomovie, nomovie);
        
        b1 = new BoxOffice(this.bytime);
        
        this.s2.right = this.s3;
        this.s2.left = this.s1;
        this.s3.right = this.s3;
        this.s3.left = this.s2;
        
        
    }
    
    void testToString(Tester t){
        init();
        nomovie.addMovie(hp, bytime);
        t.checkExpect(this.hp.toString(), "Harry Potter @ 3");
        t.checkExpect(this.s1, false);
        //t.checkExpect(this.nomovie.toString(), "Harry Potter @ 3");
       // t.checkExpect(this.nomovie, 99);
    }
    
    
}













