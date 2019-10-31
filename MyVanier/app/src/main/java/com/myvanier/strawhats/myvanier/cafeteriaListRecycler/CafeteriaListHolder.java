package com.myvanier.strawhats.myvanier.cafeteriaListRecycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.CafeteriaController;
import com.myvanier.strawhats.myvanier.dbController.Model.Cafeteria;

import java.util.List;

public class CafeteriaListHolder extends RecyclerView.ViewHolder
{
    private TextView txtName;
    private TextView txtPrice;
    private ImageView itemImage;
    private List<Cafeteria> cafeteriaList;
    private Cafeteria cafeteria;
    CafeteriaListAdapter cafeteriaListAdapter;
    CafeteriaController cafeteriaController;
    ImageView image;


    /**
     * Creates a list holder for cafeteria
     * @param itemView
     * @param iCafeteriaAdapter
     * @param iCafeteriaList
     */
    public CafeteriaListHolder(@NonNull View itemView, CafeteriaListAdapter iCafeteriaAdapter, List<Cafeteria> iCafeteriaList) {
        super(itemView);

        this.cafeteriaList = iCafeteriaList;
        this.cafeteriaListAdapter = iCafeteriaAdapter;


        this.txtName = (TextView) itemView.findViewById(R.id.txtNameCaf);
        this.txtPrice = (TextView)itemView.findViewById(R.id.txtPrice);
        this.itemImage = (ImageView) itemView.findViewById(R.id.cafImage);

        //this.image = (ImageView) itemView.findViewById(R.id.cafImage);
        cafeteriaController = new CafeteriaController(itemView.getContext());
    }

    /**
     * sets name and price data on the RecyclerView
     * @param cafeteria
     */
    public void setCafeteria(Cafeteria cafeteria)
    {
        //TODO: add the image of item based on the id of the item
        this.cafeteria = cafeteria;

        switch(cafeteria.getCafeteria())
        {
            case "DCaf":
                this.itemImage.setImageResource(R.drawable.dcafe);
                this.itemImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;

            case "NCaf":
                this.itemImage.setImageResource(R.drawable.ncafe);
                this.itemImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;

        }

        this.txtName.setText(cafeteria.getFoodName());
        this.txtPrice.setText(cafeteria.getPrice()+"");
    }
}
