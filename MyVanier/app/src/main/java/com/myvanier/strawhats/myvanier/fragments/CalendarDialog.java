package com.myvanier.strawhats.myvanier.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.calendarRecycler.CalendarAdapter;
import com.myvanier.strawhats.myvanier.calendarRecycler.CalendarListHolder;
import com.myvanier.strawhats.myvanier.dbController.CalendarController;
import com.myvanier.strawhats.myvanier.dbController.Model.Calendar;

import java.util.List;


public class CalendarDialog extends AppCompatDialogFragment
{

    private Button addEvent;
    EditText date;
    EditText event;
    CalendarController cController;

    /**
     * Creates a dialog for the calendar activity
     * @param savedInstanceState
     * @return the dialog builder
     */

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        cController = new CalendarController(getContext());



        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_calendar,null);

//        addEvent = (Button) view.findViewById(R.id.addEventBtn);
//        addEvent.setOnClickListener(this);

        final View eventView = getActivity().findViewById(R.id.eventListView);

        builder.setView(view)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                date = (EditText) view.findViewById(R.id.newDate);
                event = (EditText)view.findViewById(R.id.newEvent);

                String newDate = date.getText().toString();
                String newEvent = event.getText().toString();
                Toast.makeText(getContext(),"Date: " + newDate + " Event: " + newEvent,Toast.LENGTH_SHORT).show();
               cController.addEvent(new Calendar(newDate,newEvent));


            }
        });
        return builder.create();
    }


}
