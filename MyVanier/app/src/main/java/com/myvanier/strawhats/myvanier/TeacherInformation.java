package com.myvanier.strawhats.myvanier;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.teacherListTabFragments.SearchTeacher;
import com.myvanier.strawhats.myvanier.teacherListTabFragments.YourList;

public class TeacherInformation extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    SearchTeacher searchTab = new SearchTeacher();
                    return searchTab;
                case 1:
                    YourList yourListTab = new YourList();
                    return yourListTab;
                default:
                    return null;
            }
        }

        @Override
        public int getCount()
        {
            return 2;
        }

        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "SEARCH";
                case 1:
                    return "YOUR LIST";
            }
            return null;
        }
    }
}
