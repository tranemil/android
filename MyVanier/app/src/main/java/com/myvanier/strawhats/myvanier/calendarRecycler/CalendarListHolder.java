package com.myvanier.strawhats.myvanier.calendarRecycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.CalendarController;
import com.myvanier.strawhats.myvanier.dbController.Model.Calendar;


import java.util.List;

public class CalendarListHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView txtDate;
    private TextView txtEvent;
    private List<Calendar> calendarList;
    private Calendar calendar;
    private Button removeEvent;
    CalendarAdapter calendarAdapter;
    CalendarController calendarController;


    /**
     * Creates a list holder for calendar
     * @param itemView
     * @param iCalendarAdapter
     * @param iCalendarList
     */
    public CalendarListHolder(@NonNull View itemView, CalendarAdapter iCalendarAdapter, List<Calendar> iCalendarList) {
        super(itemView);

        this.calendarList = iCalendarList;
        this.calendarAdapter = iCalendarAdapter;

        this.txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        this.txtEvent = (TextView)itemView.findViewById(R.id.txtEvent);

        this.removeEvent = (Button) itemView.findViewById(R.id.removeEvent);
        this.removeEvent.setOnClickListener(this);

        calendarController = new CalendarController(itemView.getContext());
    }

    /**
     * sets the date and event data on the RecyclerView
     * @param calendar
     */
    public void setCalendar(Calendar calendar)
    {
        this.calendar = calendar;
        this.txtDate.setText(calendar.getDate());
        this.txtEvent.setText(calendar.getEvent()+"");
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.removeEvent:
                int position = getAdapterPosition();
                deleteEvent(position);
                Toast.makeText(v.getContext(), "Removed!", Toast.LENGTH_SHORT).show();

        }
    }

    public void deleteEvent(int position)
    {
        Calendar calendar = calendarList.get(position);

        this.calendarList.remove(position);
        this.calendarAdapter.notifyItemRemoved(position);
        this.calendarAdapter.notifyItemRangeChanged(position, this.calendarList.size());

        calendarController.deleteEvent(calendar);
    }
}
