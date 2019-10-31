package zipcodes.assignment.emil.assignment1;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import zipcodes.assignment.emil.postalcodes.PCController;
import zipcodes.assignment.emil.postalcodes.PostalCode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ComputeDistance.ComputeDistanceDialogListener, NearbyLocations.FindNearbyLocations {

    public static final String TAG = "MainActivity";
    PCController PC = new PCController();

    /**
     * The default constructor.
     */
    public MainActivity() {

    }

    /**
     * During the onCreate lifecycle of the Main Activity,
     * instantiate buttons and set onClickListener to
     * the buttons.
     *
     * Accesses the zipcodes.csv located in the raw directory
     * and parses it.
     *
     * @param inputStream   the data returned from the zipcodes.csv
     * @param btnDistance   the Compute Distance button
     * @param btnNear       the Find Nearby Locations button
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnParse = (Button) findViewById(R.id.btnParse);
        Button btnDistance = (Button) findViewById(R.id.btnDistance);
        Button btnNear = (Button) findViewById(R.id.btnNearby);

        btnParse.setOnClickListener(this);
        btnDistance.setOnClickListener(this);
        btnNear.setOnClickListener(this);

        InputStream inputStream = getResources().openRawResource(R.raw.zipcodes);
        try {
            PC.parse(inputStream);
            Log.i(TAG, "CSV file parsed");
            Toast.makeText(this, "Parsed successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "An error has occurred while trying to parse the CSV file. Error: " + e.getMessage());
            Toast.makeText(this, "An error has occurred", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * Creates a new fragment and displays it
     *
     * @param fm                            The Fragment Manager.
     * @param compute_distance_fragment     The Fragment that will be shown when the button is pressed.
     */
    private void showDistanceFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ComputeDistance compute_distance_fragment = ComputeDistance.newInstance("Compute Distance");
        compute_distance_fragment.show(fm, "fragment_compute_distance");
    }

    /**
     * Creates a new fragment and displays it
     *
     * @param fm                            The Fragment Manager.
     * @param nearby_location_fragment      The fragment that will be shown when the button is pressed
     */
    private void showNearbyLocationsFragment() {
        FragmentManager fm = getSupportFragmentManager();
        NearbyLocations nearby_location_fragment = NearbyLocations.newInstance("Nearby locations");
        nearby_location_fragment.show(fm, "fragment_nearby_locations");
    }

    /**
     *  The onClick listener.
     *
     * @param v         The view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnParse:
                Log.i(TAG, "Parsing in progress...");
                InputStream inputStream = getResources().openRawResource(R.raw.zipcodes);
                try {
                    PC.parse(inputStream);
                    Log.i(TAG, "CSV file parsed");
                    Toast.makeText(this, "Parsed successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(TAG, "An error has occurred while trying to parse the CSV file. Error: " + e.getMessage());
                    Toast.makeText(this, "An error has occurred", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            break;
            case R.id.btnDistance:
                showDistanceFragment();
                break;
            case R.id.btnNearby:
                showNearbyLocationsFragment();
                break;
        }
    }

    /**
     *  Computes the distance between 2 user inputted 3-digit postal codes.
     *
     * @param distanceAlertDialog   The AlertDialog that will be shown when distance is calculated.
     * @param zipCodeFrom           A zip code passed from the fragment that will be used to calculate distance from the "zipCodeTo".
     * @param zipCodeTo             A zip code passed from the fragment that will be used to calculate distance frm the "zipCodeFrom".
     */
    @Override
    public void computeDistance(String zipCodeFrom, String zipCodeTo) {
        AlertDialog.Builder distanceAlertDialog = new AlertDialog.Builder(this);
        distanceAlertDialog.setTitle("Computed distance");
        if (!PC.isValidZip(zipCodeFrom) || !PC.isValidZip(zipCodeTo)) {
            Toast.makeText(this, "Zip code not found in database", Toast.LENGTH_SHORT).show();
        }
        else {
            String distance = String.valueOf(PC.distanceTo(zipCodeFrom, zipCodeTo));

            distanceAlertDialog.setMessage("The distance between " + zipCodeFrom.toUpperCase() + " and " + zipCodeTo.toUpperCase() + " is " + distance + " KM");
            distanceAlertDialog.show();

            Log.i(TAG, distance);
        }
    }

    /**
     * Finds nearby cities from the user inputted 3 digit postal code and radius.
     *
     * @param nearbyLocationsDialog The AlertDialog that will be shown when all nearby cities are found.
     * @param zipCode               A zip code used to find nearby locations.
     * @param radius                The radius of the nearby locations.
     */
    @Override
    public void findNearbyLocations(String zipCode, double radius) {
        try {
            AlertDialog.Builder nearbyLocationsDialog = new AlertDialog.Builder(this);
            nearbyLocationsDialog.setTitle("Nearby locations");
            String foundLocations = "";
            HashMap<String, PostalCode> nearbyLocations = PC.nearbyLocations(zipCode, radius);

            for (Map.Entry<String, PostalCode> entry : nearbyLocations.entrySet()) {
                foundLocations += "\n" + entry.getValue().getCity();
            }

            if(nearbyLocations.isEmpty()) {
                nearbyLocationsDialog.setMessage("No nearby locations");
            }
            else {
                nearbyLocationsDialog.setMessage(foundLocations);
            }

            nearbyLocationsDialog.show();
            //Toast.makeText(this, foundLocations, Toast.LENGTH_SHORT).show();
            Log.i(TAG, foundLocations);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}