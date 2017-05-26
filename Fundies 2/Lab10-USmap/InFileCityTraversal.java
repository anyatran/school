import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.text.ParseException;
import java.util.*;
import tester.*;

/**  An implementation of <code>Traversal</code> that reads a text file
 *     with <code>City</code> data encoded as well-formatted 
 *     <code>String</code>s and generates one instance at a time. */

public class InFileCityTraversal implements Traversal<City> {
    /** the Input reader */
    protected BufferedReader buffer;

    /** one line of input at a time */
    protected String line;

    /** a City object to hold values */
    protected City c;

    /** determines whether the general dialog has been closed */
    protected boolean closed = true;

    /** determines whether new T data has been submitted */
    protected boolean submitted = false;

    /** An instance of a class that converts <code>City</code> data
     *    to a <code>String</code> and produces a <code>City</code> from
     *    a well-formatted <code>String</code>
     */
    protected CityConverter cc = new CityConverter();

    public InFileCityTraversal() {

        /** Build a file chooser and have the user choose a file, 
         *    quitting this operation if the user canceled the choice */
        JFileChooser chooser = new JFileChooser(".");

        /** Set the file extension to be .txt */
        chooser.setFileFilter(new TextFileFilter());
        closed = false;

        /** See if a file was selected - quit if user canceled */
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            closed = true;
            return;
        }
        try{
            buffer = new BufferedReader(
                         new FileReader(chooser.getSelectedFile()));
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found exception: " + e);
            closed = true;        
        }

        /** read one line of input from the selected file 
         *  and extract values for the first city */
        getNext();
    }
    
    /** Process next item, if available */
    public void getNext() {
        if (!closed) {
            try{
                if (buffer.ready()) {
                    line = new String(buffer.readLine());
                    if ((line == null)||(line == "")) {     
                        buffer.close();
                        closed = true;
                    }
                    else { 
                        read(line);
                    }            
                }
                else {
                    buffer.close();
                    closed = true;
                }
            }
            catch (Exception e) {
                System.err.println("Error in reading line exception: " + e);
                closed = true;
            }
        }
    }

    /** advance to the next element in the dataset */
    public Traversal<City> getRest() {
        getNext();
        return this;
    }

    /** return the most recent submission - if available */
    public City getFirst() {
        if (!closed) {
            return c;
        }
        else{
            closed = true;
            throw new IllegalUseOfTraversalException("No Elements Left");
        }
    }

    /** Verify that an element is available */
    public boolean isEmpty() {
        return closed; 
    }

    /** Extract next City from the input string */
    public void read(String str) {
        try {
            c = this.cc.fromStringData(str);
        }
        catch (java.text.ParseException e) {
            System.err.println(e.getMessage() + " parse exception in line\n"
                               + line);
            c = null;
            closed = true;
        }
    }
}

/** 
 * A class to provide a conversion from <code>City</code> data 
 * to a well-formated  <code>String</code> and a conversion 
 * from a well-formatted <code>String</code>
 *    to an instance os a <code>City</code> */
class CityConverter {

    /** Convert <CODE>City</CODE> data to well structured string 
     *    for file output*/
    public String toStringData(City that) {
        String s = "" + that.zipFormat.format(that.zip) + ", "
        + that.name  + ", "
        + that.state + ", "
        + that.longitude + ", "
        + that.latitude  + "\n";
        return s;
    }

    /** Convert the <CODE>String</CODE> that represents a <CODE>City</CODE>
     *    to city data */
    public City fromStringData(String s) throws java.text.ParseException {
        City city = new City(0, "", "", 0, 0);
        try{
            /** set up the string tokenizer to extract city data */
            StringTokenizer st = new StringTokenizer(s, ",");

            city.zip       = Integer.valueOf(st.nextToken());
            city.name      = st.nextToken();
            city.state     = st.nextToken();
            city.longitude = Double.valueOf(st.nextToken());
            city.latitude  = Double.valueOf(st.nextToken());
            return city;
        }
        catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
            throw new java.text.ParseException("Tokenizer Error", 0);
        }
    }

    /** Show how the String conversion works */
    public void testScanner() {
        City boston = new City(2115, "Boston", "MA", 71.092215, 42.342706);

        // encode City data as a String
        String str = this.toStringData(boston);
        System.out.println("Encoded string: " + str);

        // use the encoded String to initialize new City fields
        try{
            City temp = this.fromStringData(str);

            // print out the new objecct
            System.out.println(temp);
        }
        catch (ParseException e) {
            System.err.println("Parse error: " + str + "\n" + e.getMessage());
        }
    }
}

/** Class used to assure that only file names with the suffix .txt are
 *    seen in the FileChooser dialog. */
class TextFileFilter extends FileFilter {

    public boolean accept(File f) {
        // accept if file name ends in .txt
        return f.getName().endsWith(".txt");
    }

    public String getDescription() {
        return "Text files";
    }
}
