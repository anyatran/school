import java.util.*;

import tester.*;

/** Tests and Examples for USMap and Graphs */
public class Examples {

    /** data for the methods and tests */
    HashMap<City, State> newEngland = new HashMap<City, State>();
    ArrayList<String> NHneighbors = new ArrayList<String>();
    ArrayList<String> VTneighbors = new ArrayList<String>();
    ArrayList<String> MEneighbors = new ArrayList<String>();
    ArrayList<String> MAneighbors = new ArrayList<String>();
    ArrayList<String> CTneighbors = new ArrayList<String>();
    ArrayList<String> RIneighbors = new ArrayList<String>();

    City concord = new City(3301, "Concord", "NH", 71.527734, 43.218525);
    City montpellier = new City(5602, "Montpelier", "VT", 72.576992, 44.264082);
    City augusta = new City(4330, "Augusta", "ME", 69.766548, 44.323228);
    City boston = new City(2115, "Boston", "MA", 71.092215, 42.342706);
    City hartford = new City(6120, "Hartford", "CT", 72.675807, 41.78596);
    City providence = new City(2908, "Providence", "RI", 71.437684, 41.838294);
    
    City boston2 = new City(2115, "Boston", "MA", 71.092215, 42.342706);

    State NH = new State("NH", concord, NHneighbors );
    State VT = new State("VT", montpellier, VTneighbors );
    State ME = new State("ME", augusta, MEneighbors );
    State MA = new State("MA", boston, MAneighbors);
    State CT = new State("CT", hartford, CTneighbors);
    State RI = new State("RI", providence, RIneighbors);



    /** Generate lists of neighboring states */
    public void makeNewEngland() {
        NHneighbors.add("ME");
        NHneighbors.add("MA");
        NHneighbors.add("VT");

        VTneighbors.add("NH");
        VTneighbors.add("MA");

        MEneighbors.add("NH");
        
        MAneighbors.add("CT");
        MAneighbors.add("RI");
        MAneighbors.add("VT");
        MAneighbors.add("NH");
        
        CTneighbors.add("RI");
        CTneighbors.add("MA");
        
        RIneighbors.add("CT");
        RIneighbors.add("MA");
        
    }

    /** Generate New England states */
    public void makeStates() {
        newEngland.put(concord, NH);
        newEngland.put(montpellier, VT);
        newEngland.put(augusta, ME);
        newEngland.put(boston, MA);
        newEngland.put(boston2, MA);
        newEngland.put(providence, RI);
        newEngland.put(hartford, CT);
        
    }

    ArrayList<City> cities = new ArrayList<City>();

    /** initialize cities with the data from a file */
    public void initCities() {
        Traversal<City> tr = new InFileCityTraversal();

        try{
            while (!tr.isEmpty()) {
                cities.add(tr.getFirst());
                tr= tr.getRest();
            }
        }
        catch(IllegalUseOfTraversalException e) {
            System.out.println(
                    "IllegalUseOfTraversalException: cannot happen" +
                            e.getMessage());
        } 
    }
    
    

    /** Display the instances using toString... */
    public void testPrint(Tester t) {
        this.makeNewEngland();
        this.makeStates();

        System.out.println("After initialization:");
        System.out.println("Concord: " + newEngland.get(concord));
        System.out.println("Montpellier: " + newEngland.get(montpellier));
        System.out.println("Augusta: " + newEngland.get(augusta));
        System.out.println("Boston: " + newEngland.get(boston));
        System.out.println("Hartford: " + newEngland.get(hartford));
        System.out.println("Providence: " + newEngland.get(providence));
        

        initCities();
        for (City c: cities) {
            System.out.println(c.toString());
        }
    }
    
    public void testHash(Tester t) {
        this.makeStates();
        this.makeNewEngland();
        t.checkExpect(this.newEngland.size(), 7);
        t.checkExpect(this.newEngland.get(hartford), CT);
        //t.checkExpect(this.boston.equals(this.boston2), true);
        //t.checkExpect(this.boston.equals(this.augusta), false);
        t.checkExpect(this.boston.hashCode() == this.boston2.hashCode(), true);
    }
}















