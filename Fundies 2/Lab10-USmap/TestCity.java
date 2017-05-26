import junit.framework.TestCase;


public class TestCity extends TestCase {
    public void testCity(){
        assertEquals(
            new City(3301, "Concord", "NH", 71.527734, 43.218525).toString(),
            "new City(03301, Concord, NH, 71.527734, 43.218525)");
        assertEquals(
            new City(4330, "Augusta", "ME", 69.766548, 44.323228).toString(),
            "new City(04330, Augusta, ME, 69.766548, 44.323228)");
    }
}
