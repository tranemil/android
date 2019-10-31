package com.myvanier.strawhats.myvanier.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myvanier.strawhats.myvanier.R;


public class EveryonePrintFileUpload extends Fragment implements View.OnClickListener {

    private Button selectFile;
    private Button uploadFile;

    public EveryonePrintFileUpload() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_everyone_print_file_upload, container, false);

        selectFile = (Button) v.findViewById(R.id.selectFileBtn);
        uploadFile = (Button) v.findViewById(R.id.uploadFileBtn);
        selectFile.setOnClickListener(this);
        uploadFile.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.selectFileBtn:
                break;
            case R.id.uploadFileBtn:
                break;
        }
    }
}
