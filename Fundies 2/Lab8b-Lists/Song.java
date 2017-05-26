
// Represents a song
public class Song{
    String title;
    String artist;
    int duration;  // in seconds
    
    public Song(String title, String artist, int duration){
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }
    
    /* Template
     *   Fields
     *     ... this.title ...       -- String
     *     ... this.artist ...      -- String
     *     ... this.duration ...    -- int
     *
     *   Methods 
     *     ... this.mmm() ...       -- ??
     */

    public String toString(){
        return this.title.concat(", ").concat(this.artist.concat(", ").concat(String.valueOf(this.duration)));
    }
}