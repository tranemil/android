package com.myvanier.strawhats.myvanier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.fragments.CalendarDialog;
import com.myvanier.strawhats.myvanier.fragments.CalendarFragment;

import org.w3c.dom.Text;

public class CalendarActivity  extends AppCompatActivity implements View.OnClickListener
{
    private SectionsPagerAdapter mSectionPageAdapter2;

    private ViewPager mViewPager;
    private CalendarView mCalendarView;

    private Bundle bundle;


    /**
     * Initializes components when activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.getSupportActionBar().hide();


        ImageButton button = (ImageButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        Button mainBtn = (Button) findViewById(R.id.btnMain);
        Button mapBtn = (Button) findViewById(R.id.btnMap);
        Button librBtn = (Button) findViewById(R.id.btnLibrary);
        Button cafBtn = (Button) findViewById(R.id.btnCafeteria);

        mainBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);
        librBtn.setOnClickListener(this);
        cafBtn.setOnClickListener(this);



//        String test = "123";
//        CalendarFragment cal = new CalendarFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("test1",test);
//        cal.setArguments(bundle);



        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                String selectedDate = month + 1 + "/" + dayOfMonth + "/" + year;
                //Toast.makeText(CalendarActivity.this,selectedDate,Toast.LENGTH_SHORT).show();

                CalendarFragment cal = new CalendarFragment();
                Bundle bundle = new Bundle();
                bundle.putString("dateSelected", selectedDate);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                cal.setArguments(bundle);
                ft.replace(R.id.fragment, cal);
                ft.commit();




            }
        });



    }

    /**
     * Performs an action when specific button is created
     * @param v
     */
    public void onClick(View v)
    {
        Intent mainIntent = new Intent(this,MainActivity.class);
        // Intent mapIntent = new Intent(this, Map.class);
        Intent libraryIntent = new Intent(this, LibrarySharing.class);
        Intent cafeteriaIntent = new Intent(this, CafeteriaActivity.class);

        switch(v.getId())
        {
            case R.id.btnMain: startActivity(mainIntent);
                break;

//            case R.id.btnMap: startActivity(mapIntent);
//            break;
//
           case R.id.btnLibrary: startActivity(libraryIntent);
           break;

          case R.id.btnCafeteria: startActivity(cafeteriaIntent);
          break;
        }

    }


    /**
     * opens calendar dialog
     */
    public void openDialog() {
        CalendarDialog calDialog = new CalendarDialog();
        calDialog.show(getSupportFragmentManager(), "show");

    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm,Bundle bundle1) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            CalendarFragment calFrag = new CalendarFragment();
            return calFrag;

        }

        @Override
        public int getCount()
        {
            return 0;
        }

    }


}
