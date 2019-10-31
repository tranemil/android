package com.myvanier.strawhats.myvanier.dbController.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Calendar
{
    private String date;
    private String event;

    public Calendar(){

    }

    /**
     * reads all the data from the cafeteria class
     * @param date
     * @param event
     */
    public Calendar(String date,String event){
        this.date = date;
        this.event = event;
    }


    /**
     * gets date
     * @return date
     */
    public String getDate(){return date;}

    /**
     * sets date
     * @param date
     */
    public void setDate(String date){this.date = date;}

    /**
     * gets the event
     * @return event
     */
    public String getEvent(){return event;}

    /**
     * sets event
     * @param event
     */
    public void setEvent(String event){this.event = event;}





}
