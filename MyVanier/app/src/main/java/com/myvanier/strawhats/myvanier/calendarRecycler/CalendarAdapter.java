package com.myvanier.strawhats.myvanier.calendarRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.cafeteriaListRecycler.CafeteriaListHolder;
import com.myvanier.strawhats.myvanier.dbController.Model.Calendar;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarListHolder>
{
    private List<Calendar> calendarList;

    public CalendarAdapter(List<Calendar> calendarList)
    {
        this.calendarList = calendarList;
    }

    /**
     * Add an item from a list inside the LayoutManager
     * @param viewGroup
     * @param i
     * @return the list holder
     */
    @NonNull
    @Override
    public CalendarListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.calendar_item_layout, viewGroup, false);

        CalendarListHolder calendarListHolder = new CalendarListHolder(view, this, calendarList);

        return calendarListHolder;
    }


    /**
     * Adds data to the ViewHolder by using LayoutManager
     * @param calendarHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull CalendarListHolder calendarHolder, int i)
    {
        Calendar calendar = calendarList.get(i);

        calendarHolder.setCalendar(calendar);

    }

    /**
     * returns the size of the list
     * @return size
     */
    @Override
    public int getItemCount() {
        return calendarList.size();
    }

}
