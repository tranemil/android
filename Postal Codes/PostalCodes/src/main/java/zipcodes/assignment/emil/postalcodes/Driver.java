package zipcodes.assignment.emil.postalcodes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Driver {

    //private static PCController PC = new PCController("PostalCodes/data/zipcodes.csv");
    static PCController PC = new PCController();
    static InputStream input;

    static {
        try {
            input = new FileInputStream("PostalCodes/data/zipcodes.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Driver() {
    }

    public static void main(String[] args)  {
        PC.parse(input);
        //testParse();
        testDistance("g1h", "K2J");
        testNearbyLocation("k2j", 5.0);
        System.out.println(PC.isValidZip("k2j"));
    }

    public static void testParse() {
        PC.parse();
    }

    public static void testDistance(String zip1, String zip2) {
        System.out.println(PC.distanceTo(zip1.toUpperCase(), zip2.toUpperCase()));
    }

    public static void testNearbyLocation(String zip, double radius) {
        PC.nearbyLocations(zip.toUpperCase(), radius);
    }
}
