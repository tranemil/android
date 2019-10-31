package com.myvanier.strawhats.myvanier;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.myvanier.strawhats.myvanier.dbController.Model.Account;

import org.jsoup.select.Evaluator;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView profile;
    String name;
    Intent intent;

    private ImageView profilePicImgView;

    static final int REQUEST_IMAGE_CAPTURE = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Button goCafeteria = (Button) findViewById(R.id.btn_goCafe);
        Button goSupportTickets = (Button) findViewById(R.id.btn_goSupport);
        Button goTeacherInfo = (Button) findViewById(R.id.btn_goTeacher);
        Button goCalendar = (Button) findViewById(R.id.btn_goCalendar);
        Button goEPrint = (Button) findViewById(R.id.btn_goPrint);
        Button goSharing = (Button) findViewById(R.id.btn_goShare);
        Button goLibrary = (Button) findViewById(R.id.btn_goLibrary);
        Button goMap = (Button) findViewById(R.id.btn_goMap);

        goSupportTickets.setOnClickListener(this);
        goCafeteria.setOnClickListener(this);
        goTeacherInfo.setOnClickListener(this);
        goCalendar.setOnClickListener(this);
        goEPrint.setOnClickListener(this);
        goSharing.setOnClickListener(this);
        goLibrary.setOnClickListener(this);
        goMap.setOnClickListener(this);

        TextView date = findViewById(R.id.dateText);
        date.setText(getCurrentDate());

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        name = sharedPreferences.getString("Name", "Error");

        profilePicImgView = (ImageView) findViewById(R.id.profilePictureImgView);
        registerForContextMenu(profilePicImgView);

        profile = findViewById(R.id.profileNameTxt);
        profile.setText(name);
    }

    /**
     *
     * @param menu
     * @param view
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        if (view.getId() == R.id.profilePictureImgView) getMenuInflater().inflate(R.menu.profile_picture_context_menu, menu);
        //else implied, will launch context menu for recycler view items
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.editProfilePicture:
                //TODO: MARK ADD THE CODE HERE
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                break;
        }
        return super.onContextItemSelected(menuItem);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            profilePicImgView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onBackPressed() {
        //disable back button in MainActivity
    }

    public String getCurrentDate() {
        String date =
                LocalDate.now().getDayOfWeek().name() + ", " +
                LocalDate.now().getMonth().name() + " " +
                LocalDate.now().getDayOfMonth() + " " +
                LocalDate.now().getYear();

        return date;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogOut:
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("RememberMe", false);
                editor.commit();
            startIntent(LogInPage.class);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        intent.getStringExtra("profileName");

        profile = findViewById(R.id.profileNameTxt);
        profile.setText(name);
    }

    public void startIntent(Class activity) {
        intent = new Intent(this, activity);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btn_goCafe: startIntent(CafeteriaActivity.class);
            break;

            case R.id.btn_goSupport: startIntent(SupportTickets.class);
            break;

            case R.id.btn_goTeacher: startIntent(TeacherInformation.class);
            break;

            case R.id.btn_goPrint: startIntent(EveryonePrint.class);
            break;

            case R.id.btn_goCalendar: startIntent(CalendarActivity.class);
            break;

            case R.id.btn_goShare: startIntent(LibrarySharing.class);
            break;

            case R.id.btn_goLibrary: startIntent(LibraryActivity.class);
            break;

            case R.id.btn_goMap: startIntent(IndoorMap.class);
            break;
        }
    }
}
