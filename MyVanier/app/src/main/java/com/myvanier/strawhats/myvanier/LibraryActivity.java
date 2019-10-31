package com.myvanier.strawhats.myvanier;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.library_tab_fragments.PersonalLibrary;
import com.myvanier.strawhats.myvanier.library_tab_fragments.VanierLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LibraryActivity extends AppCompatActivity {

    private LibraryPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        mSectionsPagerAdapter = new LibraryPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        File file = new File(getFilesDir(), "myFile");
        file.setWritable(true);
        file.setReadable(true);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Toast.makeText(this, file.getName(), Toast.LENGTH_SHORT).show();

        try
        {
            FileOutputStream fileOutputStream = openFileOutput(file.getName(), MODE_PRIVATE);
            fileOutputStream.write(("Hello World!").getBytes());
            fileOutputStream.write(("\nSup!").getBytes());
            fileOutputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

//        try {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("myFile")));
//            String line;
//            Toast.makeText(this, "" + bufferedReader.readLine(), Toast.LENGTH_SHORT).show();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public class LibraryPagerAdapter extends FragmentPagerAdapter {

        public LibraryPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    VanierLibrary vanierLibraryTab = new VanierLibrary();
                    return vanierLibraryTab;
                case 1:
                    PersonalLibrary personalLibraryTab = new PersonalLibrary();
                    return personalLibraryTab;
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
                    return "Vanier Library";
                case 1:
                    return "Personal Library";
            }
            return null;
        }
    }
}
