package com.myvanier.strawhats.myvanier.dbController.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cafeteria
{
    private String cafeteria;
    private String food_name;
    private String food_type;
    private int food_id;
    private Double price;

    public Cafeteria ()
    {

    }

    /**
     * reads all the data from the cafeteria class
     * @param foodName
     * @param type
     * @param price
     * @param cafeteria
     * @param id
     */
    public Cafeteria(String foodName, String type, double price, String cafeteria, int id)
    {
        this.food_name = foodName;
        this.food_type = type;
        this.price = price;
        this.cafeteria = cafeteria;
        this.food_id = id;

    }

    /**
     * gets the food name
     * @return food name
     */
    public String getFoodName(){return food_name;}

    /**
     * sets the food name
     * @param foodName
     */
    public void setFoodName(String foodName){this.food_name = foodName;}

    /**
     * gets the food type
     * @return the food type
     */
    public String getFoodType(){return food_type;}

    /**
     * sets the food type
     * @param
     */
    public void setFoodType(String foodType){this.food_type = foodType;}

    /**
     * gets the price of the food
     * @return the price of the food
     */
    public double getPrice(){return price;}

    /**
     * sets the food type
     * @param p
     */
    public void setPrice(double p){this.price = p;}

    /**
     * gets the cafeteria name
     * @return name of the cafeteria
     */
    public String getCafeteria(){return cafeteria;}

    /**
     * sets the cafeteria
     * @param caf
     */
    public void setCafeteria(String caf){this.cafeteria = caf;}

    /**
     * gets the id of the food
     * @return food id
     */
    public int getFoodId(){return food_id;}

    /**
     *sets the food id
     * @param id
     */
    public void setFood_id(int id){this.food_id = id;}


}
