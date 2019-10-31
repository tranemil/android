package com.myvanier.strawhats.myvanier.cafeteriaTabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvanier.strawhats.myvanier.DCafMenu;
import com.myvanier.strawhats.myvanier.JakesMenu;
import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.cafeteriaListRecycler.CafeteriaListAdapter;
import com.myvanier.strawhats.myvanier.cafeteriaListRecycler.CafeteriaListHolder;
import com.myvanier.strawhats.myvanier.dbController.CafeteriaController;
import com.myvanier.strawhats.myvanier.dbController.Model.Cafeteria;

import java.util.List;

public class SideMeals extends Fragment
{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<Cafeteria> cafeteriaList;
    private RecyclerView.Adapter<CafeteriaListHolder> adapter;
    CafeteriaController cafController = new CafeteriaController(getContext());
    private String cafe;
    private String type = "Side Meal";

    /**
     * Method that is called when fragment is created, alongside its components
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment_desserts, container, false);

        if(frag.getContext() instanceof JakesMenu)
        {
            cafe = "Jake's Cafe";
        }
        else if (frag.getContext() instanceof DCafMenu)
        {
            cafe = "DCaf";
        }
        else
        {
            cafe = "NCaf";
        }


        recyclerView = (RecyclerView) frag.findViewById(R.id.menuRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());

        //cafeteriaList = cafController.readAll();
        cafeteriaList = cafController.readFiltered(cafe,type);

        adapter = new CafeteriaListAdapter(cafeteriaList);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        return frag;
    }
}
