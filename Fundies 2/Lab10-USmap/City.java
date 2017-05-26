
import java.text.*;  // to get DecimalFormat
import java.util.HashMap;

/** Represents a City */
class City {
    /** Decimal format to print leading zeros in zip code */
    static DecimalFormat zipFormat = new DecimalFormat("00000");

    int zip;
    String name;
    String state;
    double longitude;
    double latitude;


    /** The full constructor */
    public City (int zip, String name, String state, 
            double longitude, double latitude) {
        this.zip   = zip;
        this.name  = name;
        this.state = state;
        this.longitude = longitude;
        this.latitude  = latitude;
    }    

    /** Represent  city data as a String for printed display */
    public String toString() {
        return ("new City("
                + City.zipFormat.format(this.zip) + ", " 
                + this.name  + ", " + this.state + ", "
                + this.longitude + ", " + this.latitude + ")");
    }

//    public boolean equals(Object obj){
//        if (obj == null)
//            return false;
//        City temp = (City)obj;
//        return 
//                this.name.equals(temp.name) &&
//                this.zip == temp.zip &&
//                this.state.equals(temp.state) &&
//                this.sameDouble(this.latitude, temp.latitude) &&
//                this.sameDouble(this.longitude, temp.longitude);
//
//    }
//
//
//    public boolean sameDouble(double n, double m){
//        return n == m;
//    }
    
    public int hashCode(){
        return
                this.name.hashCode() + this.zip + 
                this.state.hashCode() + (int) this.latitude + (int) this.longitude;
    }
}












