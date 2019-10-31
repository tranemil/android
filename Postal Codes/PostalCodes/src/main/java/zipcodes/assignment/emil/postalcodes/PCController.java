package zipcodes.assignment.emil.postalcodes;

import com.opencsv.CSVReader;

import org.apache.commons.lang3.ObjectUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PCController {
    private HashMap<String, PostalCode> postalCodes = new HashMap<>();
    private String csvFilePath;

    public PCController(String filePath)
    {
        csvFilePath = filePath;
    }

    public PCController()
    {

    }

    public HashMap<String, PostalCode> parse(InputStream input) {
        try {
            BufferedReader br = new BufferedReader((new InputStreamReader(input)));
            String[] array;

            while(br.ready()) {
                 array = br.readLine().split(",");

                 for (int i = 0; i < array.length; i++) {
                     PostalCode PC = new PostalCode(Integer.parseInt(array[0]), //id
                             array[1],    //country
                             array[2],    //postal code
                             array[3], //city
                             array[array.length - 3],   //province,
                             Double.parseDouble(array[array.length - 2]),   //latitude
                             Double.parseDouble(array[array.length - 1]));

                     postalCodes.put(PC.getPostalCode(), PC);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        /*
        for (Map.Entry<String, PostalCode> entry : postalCodes.entrySet()) {
            System.out.println("KEY: " + entry.getKey() + " VALUE: " + entry.getValue());
        }
        */
        return postalCodes;
    }

    public HashMap<String, PostalCode> parse() {

        String[] address;
        try {
            CSVReader csvFile = new CSVReader(new FileReader((csvFilePath)));

            while ((address = csvFile.readNext()) != null) {
                PostalCode PC = new PostalCode(Integer.parseInt(address[0]), //id
                                address[1],    //country
                                address[2],    //postal code
                                address[3], //city
                                address[address.length - 3],   //province,
                                Double.parseDouble(address[address.length - 2]),   //latitude
                                Double.parseDouble(address[address.length - 1]));  //longitude

                postalCodes.put(PC.getPostalCode(), PC);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        /*
        for (Map.Entry<String, PostalCode> entry : postalCodes.entrySet()) {
           System.out.println("KEY: " + entry.getKey() + " VALUE: " + entry.getValue());
        }
        */
        return postalCodes;
    }

    public String toString() {
        String result = "";
        for (Map.Entry<String, PostalCode> entry : postalCodes.entrySet()) {
            result += "KEY: " + entry.getKey() + " VALUE: " + entry.getValue();
        }
        return result;
    }

    public double distanceTo(String from, String to) {
        double distance = 0;
        try {
            int earthRadius = 6371;

            double fromLat = Math.toRadians(postalCodes.get(from.toUpperCase()).getLatitude());
            double fromLong = Math.toRadians(postalCodes.get(from.toUpperCase()).getLongitude());

            double toLat = Math.toRadians(postalCodes.get(to.toUpperCase()).getLatitude());
            double toLong = Math.toRadians(postalCodes.get(to.toUpperCase()).getLongitude());

            double dLat = (toLat - fromLat);
            double dLon = (toLong - fromLong);

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.sin(dLon / 2) * Math.sin(dLon / 2)
                    * Math.cos(fromLat) * Math.cos(toLat);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            distance = earthRadius * c;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        return distance;
    }

    public boolean isValidZip(String zip) {
        return postalCodes.containsKey(zip.toUpperCase());
    }

    public HashMap<String, PostalCode> nearbyLocations(String from, double radius) {
        HashMap<String, PostalCode> nearby = new HashMap<>();

        String fromPostalCode = postalCodes.get(from.toUpperCase()).getPostalCode();

        for (Map.Entry<String, PostalCode> entry : postalCodes.entrySet()) {
            if (distanceTo(fromPostalCode, entry.getKey()) <= radius && entry.getKey() != fromPostalCode) {
                nearby.put(entry.getKey(), entry.getValue());
                //System.out.println("NEARBY: " + entry.getValue().getCity() + " DISTANCE: " + distanceTo(fromPostalCode, entry.getKey()));
            }
        }
        return nearby;
    }
}

