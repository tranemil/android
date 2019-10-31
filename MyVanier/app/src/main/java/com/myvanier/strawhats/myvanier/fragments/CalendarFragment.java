package com.myvanier.strawhats.myvanier.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.calendarRecycler.CalendarAdapter;
import com.myvanier.strawhats.myvanier.calendarRecycler.CalendarListHolder;
import com.myvanier.strawhats.myvanier.dbController.CalendarController;
import com.myvanier.strawhats.myvanier.dbController.Model.Calendar;

import java.util.List;

public class CalendarFragment extends Fragment
{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<Calendar> calendarList;
    private RecyclerView.Adapter<CalendarListHolder> adapter;
    CalendarController calController = new CalendarController(getContext());
    private TextView showDate;
    CalendarView calendar;
    String selectedDate;
    //private String date;
    //private String event;

    private View frag;

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

        //TODO: Fix Null pointer exception when trying to get data from activity



//         selectedDate = getArguments().getString("dateSelect");
        //Toast.makeText(getContext(),selectedDate,Toast.LENGTH_SHORT).show();

        frag = inflater.inflate(R.layout.fragment_calendarlist, container, false);

       //String txt = getArguments().getString("dateSelected");
        Toast.makeText(frag.getContext(),selectedDate = getArguments().getString("dateSelected"),Toast.LENGTH_LONG).show();
//        Bundle bundle = this.getArguments();
//
//        String date = bundle.getString("dateSelected");

        //Toast.makeText(frag.getContext(), date, Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) frag.findViewById(R.id.menuRecyclerView);

        layoutManager = new LinearLayoutManager(getContext());
        calendarList = calController.readBySelectedDate(selectedDate);
        adapter = new CalendarAdapter(calendarList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        return frag;
    }
}
