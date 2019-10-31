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

    public MainActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button btnParse = (Button) findViewById(R.id.btnParse);
        Button btnDistance = (Button) findViewById(R.id.btnDistance);
        Button btnNear = (Button) findViewById(R.id.btnNearby);

        //btnParse.setOnClickListener(this);
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

    private void showDistanceFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ComputeDistance fragment = ComputeDistance.newInstance("Show distance fragment");
        fragment.show(fm, "fragment_compute_distance");
    }

    private void showNearbyLocationsFragment() {
        FragmentManager fm = getSupportFragmentManager();
        NearbyLocations fragment = NearbyLocations.newInstance("Nearby locations fragment");
        fragment.show(fm, "fragment_nearby_locations");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*
            case R.id.btnParse:
                Log.i(TAG, "Parsing in progress...");
                InputStream inputStream = getResources().openRawResource(R.raw.zipcodes);
                try {
                    PC.parse(inputStream);
                    Log.i(TAG, "Parsing is done yo!");
                    Toast.makeText(this, "Parsed successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(TAG, "An error has occurred while trying to parse the CSV file. Error: " + e.getMessage());
                    Toast.makeText(this, "An error has occurred", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            break;*/
            case R.id.btnDistance:
                showDistanceFragment();
                break;
            case R.id.btnNearby:
                showNearbyLocationsFragment();
                break;
        }
    }

    @Override
    public void passData(String zipCodeFrom, String zipCodeTo) {
        if (!PC.isValidZip(zipCodeFrom) || !PC.isValidZip(zipCodeTo)) {
            Toast.makeText(this, "Zip code not found in database", Toast.LENGTH_SHORT).show();
        }
        else {
            String distance = String.valueOf(PC.distanceTo(zipCodeFrom, zipCodeTo));
            AlertDialog.Builder distanceAlertDialog = new AlertDialog.Builder(this);

            distanceAlertDialog.setMessage("The distance between from " + zipCodeFrom + " and " + zipCodeTo + " is " + distance + " KM");
            distanceAlertDialog.show();

            Log.i(TAG, distance);
        }
    }

    @Override
    public void passData(String zipCode, double radius) {
        try {
            String foundLocations = "";
            HashMap<String, PostalCode> nearbyLocations = PC.nearbyLocations(zipCode, radius);
            for (Map.Entry<String, PostalCode> entry : nearbyLocations.entrySet()) {
                foundLocations += "\n" + entry.getValue().getCity();
            }
            Toast.makeText(this, foundLocations, Toast.LENGTH_SHORT).show();
            Log.i(TAG, foundLocations);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}