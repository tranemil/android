package com.myvanier.strawhats.myvanier;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.dbController.Model.Teacher;
import com.myvanier.strawhats.myvanier.dbController.AccountController;
import com.myvanier.strawhats.myvanier.dbController.Model.*;
import com.myvanier.strawhats.myvanier.dbController.TeacherController;

import java.io.InputStream;
import java.util.List;

public class LogInPage extends AppCompatActivity implements View.OnClickListener{
    EditText username;
    EditText password;
    Account account;
    boolean isVerified = false;
    String name;
    List<Account> accounts;
    AccountController ac;
    Intent intent;
    CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Button btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);

        rememberMe = findViewById(R.id.chkboxRememberUser);

        ac = new AccountController(this);
        accounts = ac.readAll();

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        boolean isRemembered = sharedPreferences.getBoolean("RememberMe", false);

        if (isRemembered) {
            account = new Account();
            account.setUsername(sharedPreferences.getString("Username", "ERROR"));
            account.setPassword(sharedPreferences.getString("Password", "ERROR"));
            account.setProfileName(sharedPreferences.getString("Name", "ERROR"));
            goToMain(account);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        accounts = ac.readAll();
    }

    private void goToMain(Account account) {
        intent = new Intent(this, MainActivity.class);
        isVerified = false;

        if (rememberMe.isChecked()) {
            writeToSharedPrefs(account,true);
        }
        else {
            writeToSharedPrefs(account,false);
        }
        startActivity(intent);
    }

    public void writeToSharedPrefs(Account account, boolean rememberMe) {
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Username", account.getUsername());
        editor.putString("Password", account.getPassword());
        editor.putString("Name", account.getProfileName());
        editor.putBoolean("RememberMe", rememberMe);
        editor.commit();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btnLogIn:
                String userName = username.getText().toString().toLowerCase();
                String passWord = password.getText().toString();
                name = ac.readSameUsername(userName);

                account = new Account(userName, passWord, name);

                if(accounts.contains(account))
                {
                    goToMain(account);
                }
                else Toast.makeText(this, "Invalid login" , Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.Register:
                intent = new Intent(this, CreateAccount.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
