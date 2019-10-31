package com.myvanier.strawhats.myvanier;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.AppComponentFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.myvanier.strawhats.myvanier.cafeteriaTabFragments.Desserts;
import com.myvanier.strawhats.myvanier.cafeteriaTabFragments.Drinks;
import com.myvanier.strawhats.myvanier.cafeteriaTabFragments.Meals;
import com.myvanier.strawhats.myvanier.cafeteriaTabFragments.SideMeals;

public class DCafMenu extends AppCompatActivity
{
    private SectionsPagerAdapter mSectionPageAdapter1;
    private ViewPager mViewPager;

    private Button clickMe;

    /**
     * initializes components when activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcaf);

        mSectionPageAdapter1 = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.cafView);
        mViewPager.setAdapter(mSectionPageAdapter1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.cafTabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * initializes when option menu is created
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_view, menu);
        return true;
    }

    /**
     * performs action when an option is selected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    /**
     * returns new fragment depending on tab
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Meals mealsTab = new Meals();
                    return mealsTab;
                case 1:
                    SideMeals sideMealTab = new SideMeals();
                    return sideMealTab;

                case 2:
                    Desserts dessertsTab = new Desserts();
                    return dessertsTab;

                case 3:
                    Drinks drinksTab = new Drinks();
                    return drinksTab;
                default:
                    return null;
            }
        }

        /**
         * gets the count
         * @return count
         */
        @Override
        public int getCount() {
            return 4;
        }

        /**
         * sets the title of tab
         * @param position
         * @return title name
         */
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Meals";
                case 1:
                    return "Side Meals";
                case 2:
                    return "Desserts";
                case 3:
                    return "Drinks";
            }
            return null;
        }


    }

}
