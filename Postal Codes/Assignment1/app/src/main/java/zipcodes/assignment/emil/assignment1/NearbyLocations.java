package zipcodes.assignment.emil.assignment1;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NearbyLocations extends DialogFragment implements View.OnClickListener {
    String zipCode;
    double radius;
    Button btnFindNearby;
    Button btnCancel;

    public interface FindNearbyLocations {
        void passData (String zipCode,double radius);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNearby:
            try {
                EditText userZip = (EditText) getDialog().findViewById(R.id.txt_zipCode);
                EditText userRadius = (EditText) getDialog().findViewById(R.id.txt_Radius);
                zipCode = userZip.getText().toString();
                radius = Double.parseDouble(userRadius.getText().toString());
                FindNearbyLocations listener = (NearbyLocations.FindNearbyLocations) getActivity();
                listener.passData(zipCode, radius);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Enter a valid radius", Toast.LENGTH_SHORT).show();
            } catch (NullPointerException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Enter a valid 3 digit area code", Toast.LENGTH_SHORT).show();
            }
            if (radius <= 0) {
                Toast.makeText(getActivity(), "Enter a positive radius", Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.btnCancel:
                getDialog().dismiss();
                break;
        }

    }


    public NearbyLocations() {
        // Required empty public constructor
    }

    public static NearbyLocations newInstance(String title) {
        NearbyLocations fragment = new NearbyLocations();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nearby_locations, container, false);
        btnFindNearby = (Button) v.findViewById(R.id.btnNearby);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnFindNearby.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return v;
    }
}
