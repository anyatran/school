import tester.Tester;
// Anh Tran
// anhtran9

/*
 PROPERTY OF BINARY SEARCH TREE:
 Assume we have a Node with a given value. Anything on the left of this Node should have a smaller value,
 and whatever on the right of this Node should have a bigger value
 */

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


//compares movies by showtime
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
    // EFFECT: add the movie into the box office
    void addMovie(MovieInfo movie) {
        if(!this.schedule.isNoMovie()){
            this.schedule.addMovie(movie, this.ranker);
        }
        else {
            this.schedule = new Showing(movie, this.schedule, this.schedule);
        }
    }

    // Effect: remove the movie from the schedule
    // HAVENT FINISHED
    //    void removeMovie(MovieInfo movie){
    //        if(this.schedule.getLeft().isNoMovie() && this.schedule.getRight().isNoMovie() &&
    //                this.schedule.getMovie().sameMovie(movie)){
    //            this.schedule = new NoMovie();
    //        }
    //else this.schedule.removeMovie(movie, this.ranker);
    //            if(this.schedule.getLeft().isNoMovie() && !this.schedule.getRight().isNoMovie()){
    //            if(this.schedule.getMovie().sameMovie(movie)){
    //                this.schedule = this.schedule.getRight();
    //            }
    //            else this.schedule.getRight().removeMovie(movie, this.ranker);
    //        }
    //        else if(!this.schedule.getLeft().isNoMovie() && this.schedule.getRight().isNoMovie()){
    //            if(this.schedule.getMovie().sameMovie(movie)){
    //                this.schedule = this.schedule.getLeft();
    //            }
    //            else this.schedule.getLeft().removeMovie(movie, this.ranker);
    //        }
    //        
    
    //    }
    //    

}

// Abstract base class representing a set of movies, organized as a binary search tree
abstract class MovieSchedule {
    // convert the schedule into a string
    abstract public String toString();
    // EFFECT: adds the movie into the schedule
    abstract void addMovie(MovieInfo movie, IMovieRanking ranker);
    // checks if the BST is satisfying its properties
    abstract boolean satisfiesBSTProperty(IMovieRanking ranker);
    // checks if the schedule doesnt have any movies
    abstract boolean isNoMovie();
    // get the movie field
    abstract MovieInfo getMovie();
    // delete the movie from the schedule
    //abstract void removeMovie(MovieInfo movie, IMovieRanking ranker);
    // get the right of the movie
    abstract MovieSchedule getRight();
    // get left of the movieschedule
    abstract MovieSchedule getLeft();
    // append two schedules 
    abstract MovieSchedule append(MovieSchedule that, IMovieRanking ranker);
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

    public MovieInfo getMovie(){
        return this.movie;
    }
    public MovieSchedule getRight(){
        return this.right;
    }

    public MovieSchedule getLeft(){
        return this.left;
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

    // checks if the BST is satisfying its properties
    public boolean satisfiesBSTProperty(IMovieRanking ranker){
        if(this.left.isNoMovie() && this.right.isNoMovie()){
            return true;
        }
        else if (this.left.isNoMovie() && !this.right.isNoMovie()){
            return (ranker.compare(this.movie, this.right.getMovie()) <= 0) &&
                    (this.right.satisfiesBSTProperty(ranker));
        }
        else if (!this.left.isNoMovie() && this.right.isNoMovie()){
            return (ranker.compare(this.movie, this.left.getMovie()) > 0) &&
                    (this.left.satisfiesBSTProperty(ranker));
        }
        else return (ranker.compare(this.movie, this.right.getMovie()) <= 0) &&
                (this.right.satisfiesBSTProperty(ranker)) &&
                (ranker.compare(this.movie, this.left.getMovie()) > 0) &&
                (this.left.satisfiesBSTProperty(ranker));
    }

    public void addMovie(MovieInfo movie, IMovieRanking ranker){
        Showing s = new Showing(movie, new NoMovie(), new NoMovie());
        if(this.left.isNoMovie() && this.right.isNoMovie()){
            if(ranker.compare(movie, this.movie) <= 0){ // movie before this
                this.left = s;
            }
            else { // after
                this.right = s;
            }
        }
        else if(this.left.isNoMovie() && !this.right.isNoMovie()){
            if(ranker.compare(movie, this.movie) <= 0){
                this.left = s;
            }
            else this.right.addMovie(movie, ranker);
        }
        else if(!this.left.isNoMovie() && this.right.isNoMovie()){
            if(ranker.compare(movie, this.movie) > 0){
                this.right = s;
            }
            else this.left.addMovie(movie, ranker);
        }
        else if(ranker.compare(movie, this.movie) <= 0){
            this.left.addMovie(movie, ranker);
        }
        else this.right.addMovie(movie, ranker);
    }



    // HAVENT FINISHED

    //    void removeMovie(MovieInfo movie, IMovieRanking ranker){
    //        if(this.left.isNoMovie() && this.right.isNoMovie() && this.movie.sameMovie(movie)){
    //            this.movie = null;
    //            
    //        }
    //        else if(this.left.isNoMovie() && !this.right.isNoMovie()){
    //            if(this.movie.sameMovie(movie)){
    //                this.movie = this.right.getMovie();
    //                this.right = this.right.getRight();
    //            }
    //            else this.right.removeMovie(movie, ranker);
    //        }
    //        else if(!this.left.isNoMovie() && this.right.isNoMovie()){
    //            if(this.movie.sameMovie(movie)){
    //                this.movie = this.left.getMovie();
    //                this.left = this.left.getLeft();
    //            }
    //            else this.left.removeMovie(movie, ranker);
    //        }
    //        else if (this.movie.sameMovie(movie)){
    //            this.movie = this.getRight().getMovie();
    //            this.right = this.getRight().getRight();
    //            this.left = this.getRight().append(this.getLeft(), ranker);
    //        }
    //        else if (ranker.compare(movie, this.movie) < 0) { // before
    //            this.left.removeMovie(movie, ranker);
    //        }
    //        else this.right.removeMovie(movie, ranker);
    //
    //    }



    MovieSchedule append (MovieSchedule that, IMovieRanking ranker){
        if(this.left.isNoMovie() && this.right.isNoMovie()){
            if(ranker.compare(that.getMovie(), this.movie) <= 0){ // movie before this
                this.left = that;
            }
            else { // after
                this.right = that;
            }
        }
        else if(this.left.isNoMovie() && !this.right.isNoMovie()){
            if(ranker.compare(that.getMovie(), this.movie) <= 0){
                this.left = that;
            }
            else this.right.append(that, ranker);
        }
        else if(!this.left.isNoMovie() && this.right.isNoMovie()){
            if(ranker.compare(that.getMovie(), this.movie) > 0){
                this.right = that;
            }
            else this.left.append(that, ranker);
        }
        else if(ranker.compare(that.getMovie(), this.movie) <= 0){
            this.left.append(that, ranker);
        }
        else this.right.append(that, ranker);

        return this;

    }
}

// Represents an empty tree of movies
class NoMovie extends MovieSchedule {

    NoMovie() { 
    }

    // 
    public boolean isNoMovie(){
        return true;
    }

    public String toString(){
        return "";
    }

    public boolean satisfiesBSTProperty(IMovieRanking ranker){
        return true;
    }
    public MovieInfo getMovie(){
        throw new RuntimeException("no movie");
    }

    public void addMovie(MovieInfo movie, IMovieRanking ranker){
        // doing nothing
    }

    //void removeMovie(MovieInfo movie, IMovieRanking ranker){
    // do nothing because you don't have a movie here
    //}

    public MovieSchedule getRight(){
        throw new RuntimeException("no right");
    }
    public MovieSchedule getLeft(){
        throw new RuntimeException("no left");
    }

    public MovieSchedule append(MovieSchedule that, IMovieRanking ranker){

        return that;
    }

}

class ExamplesMovie {
    MovieInfo hp = new MovieInfo(3, "Harry Potter");
    MovieInfo hg = new MovieInfo(5, "The Hungry Games");
    MovieInfo mi = new MovieInfo(7, "Mission Impossible");
    MovieInfo sh = new MovieInfo(9, "Sherlock Holmes");

    MovieInfo n = new MovieInfo(2, "Noah");
    MovieInfo ha = new MovieInfo(4, "Home Alone");
    MovieInfo gg = new MovieInfo(6, "Great Gatsby");
    MovieInfo r = new MovieInfo(10, "Rio");



    IMovieRanking bytime = new ByTime();
    MovieSchedule nm = new NoMovie();
    MovieSchedule nm1 = new NoMovie();
    MovieSchedule s1 = new Showing(this.hp, this.nm, this.nm);
    MovieSchedule s4 = new Showing(this.sh, this.nm, this.nm);
    MovieSchedule s3 = new Showing(this.mi, this.nm, this.s4);
    MovieSchedule s2 = new Showing(this.hg, this.s1, this.s3);

    // not sorted
    MovieSchedule s1a = new Showing(this.n, this.nm, this.nm);
    MovieSchedule s2a = new Showing(this.ha, this.nm, this.nm);
    MovieSchedule s3a = new Showing(this.gg, this.s1a, this.s2a);

    // sorted
    MovieSchedule s1b = new Showing(this.ha, this.nm, this.nm);
    MovieSchedule s2b = new Showing(this.r, this.nm, this.nm);
    MovieSchedule s3b = new Showing(this.n, this.nm, this.s1b);
    MovieSchedule s4b = new Showing(this.gg, this.s3b, this.s2b);

    BoxOffice b1 = new BoxOffice(this.bytime);
    BoxOffice b2 = new BoxOffice(this.bytime);

    void init(){

        s2.addMovie(ha, bytime);
        s2.addMovie(gg, bytime);
        s2.addMovie(r, bytime);
        nm1.addMovie(gg, bytime);

        s3a.append(s4b, bytime);
        b1.addMovie(hp);
        b1.addMovie(mi);
        b1.addMovie(r);
        b1.addMovie(hg);
        b1.addMovie(gg);
        b1.addMovie(sh);
        b1.addMovie(ha);

        b2.addMovie(n);
    }

    void testN(Tester t){
        init();
        t.checkExpect(this.s2.toString(), 
                "Harry Potter @ 3, Home Alone @ 4, The Hungry Games @ 5, "
                        + "Great Gatsby @ 6, Mission Impossible @ 7, Sherlock Holmes @ 9, Rio @ 10");
        t.checkExpect(this.nm.toString(), "");
        t.checkExpect(this.s2.satisfiesBSTProperty(bytime), true);
        t.checkExpect(this.s3a.satisfiesBSTProperty(bytime), false);
        t.checkExpect(this.nm.satisfiesBSTProperty(bytime), true);
        t.checkExpect(this.bytime.compare(gg, n), 4);
        t.checkExpect(b1.schedule.toString(), 
                "Harry Potter @ 3, Home Alone @ 4, The Hungry Games @ 5, "
                        + "Great Gatsby @ 6, Mission Impossible @ 7, Sherlock Holmes @ 9, Rio @ 10");
        t.checkExpect(this.b1.schedule.satisfiesBSTProperty(bytime), true);
        t.checkExpect(b2.schedule.isNoMovie(), false);
        t.checkExpect(this.s3a.toString(), 
                "Noah @ 2, Noah @ 2, Home Alone @ 4, Great Gatsby @ 6, Rio @ 10, Great Gatsby @ 6, Home Alone @ 4");
        t.checkExpect(this.s2.getLeft().toString(), "Harry Potter @ 3, Home Alone @ 4");
        t.checkExpect(this.s2.getRight().toString(), "Great Gatsby @ 6, Mission Impossible @ 7, Sherlock Holmes @ 9, Rio @ 10");
        t.checkExpect(this.s2.getMovie(), this.hg);
        t.checkException(new RuntimeException("no movie"), this.nm, "getMovie");
        t.checkException(new RuntimeException("no left"), this.nm, "getLeft");
        t.checkException(new RuntimeException("no right"), this.nm, "getRight");
        t.checkExpect(this.nm1.toString(), "");

    }

}












