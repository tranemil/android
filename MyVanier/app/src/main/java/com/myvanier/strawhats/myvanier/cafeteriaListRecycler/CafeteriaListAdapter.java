package com.myvanier.strawhats.myvanier.cafeteriaListRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.Model.Cafeteria;

import java.util.List;

public class CafeteriaListAdapter extends RecyclerView.Adapter<CafeteriaListHolder>
{
    private List<Cafeteria> cafeteriaList;

    public CafeteriaListAdapter(List<Cafeteria> cafeteriaList)
    {
        this.cafeteriaList = cafeteriaList;
    }

    /**
     * Add an item from a list inside the LayoutManager
     * @param viewGroup
     * @param i
     * @return the list holder
     */
    @NonNull
    @Override
    public CafeteriaListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.cafeteria_item_layout, viewGroup, false);

        CafeteriaListHolder cafeteriaListHolder = new CafeteriaListHolder(view, this, cafeteriaList);

        return cafeteriaListHolder;
    }

    /**
     * Adds data to the ViewHolder by using LayoutManager
     * @param cafeteriaListHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull CafeteriaListHolder cafeteriaListHolder, int i)
    {
        Cafeteria cafeteria = cafeteriaList.get(i);

        cafeteriaListHolder.setCafeteria(cafeteria);

    }

    /**
     * returns the size of the list
     * @return size
     */
    @Override
    public int getItemCount() {
        return cafeteriaList.size();
    }
}
