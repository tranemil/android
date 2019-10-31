package com.myvanier.strawhats.myvanier.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.myvanier.strawhats.myvanier.R;

public class NutrionalValues extends AppCompatDialogFragment
{


    public NutrionalValues() {
        // Required empty public constructor
    }


    /**
     * Displays the Nutrition value fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutritional, container, false);
    }
}
