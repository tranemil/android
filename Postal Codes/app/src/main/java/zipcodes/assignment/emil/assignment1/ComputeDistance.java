package zipcodes.assignment.emil.assignment1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ComputeDistance extends DialogFragment implements View.OnClickListener {
    public Button btnComputeDistance;
    public Button btnCancel;
    String zipCode_from;
    String zipCode_to;

    /**
     *
     */
    public interface ComputeDistanceDialogListener {
        void computeDistance(String zipCodeFrom, String zipCodeTo);
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnComputeDistance:
                EditText zipFrom = (EditText) getDialog().findViewById(R.id.txt_zipFrom);
                EditText zipTo = (EditText) getDialog().findViewById(R.id.txt_zipTo);
                zipCode_from = zipFrom.getText().toString();
                zipCode_to = zipTo.getText().toString();
                ComputeDistanceDialogListener listener = (ComputeDistanceDialogListener) getActivity();
                listener.computeDistance(zipCode_from, zipCode_to);
            break;
            case R.id.btnCancel:
                getDialog().dismiss();
                showProgressBar();
                break;

        }
    }

    /**
     *  Creates and displays a horizontal progress bar.
     */
    public void showProgressBar() {
        super.onDetach();
        ProgressDialog progressBar = new ProgressDialog(getContext());
        progressBar.setTitle("Loading...");
        progressBar.setMessage("Progress bar");

        progressBar.show();
        while(progressBar.getProgress() != 100) {
            progressBar.incrementProgressBy(1);
            progressBar.show();
            if (progressBar.getProgress() == 100) {
                progressBar.dismiss();
            }
        }



    }

    /**
     *
     */
    public ComputeDistance() {
        // Required empty public constructor
    }

    /**
     *
     * @param title
     * @return
     */
    public static ComputeDistance newInstance(String title) {
        ComputeDistance frag = new ComputeDistance();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_compute_distance, container, false);
        btnComputeDistance = (Button) v.findViewById(R.id.btnComputeDistance);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnComputeDistance.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return v;
    }
}


