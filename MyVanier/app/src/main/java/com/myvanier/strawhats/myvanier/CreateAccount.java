package com.myvanier.strawhats.myvanier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.dbController.AccountController;
import com.myvanier.strawhats.myvanier.dbController.Model.Account;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    EditText password;
    EditText name;
    EditText confirmPassword;
    AccountController ac;
    List<Account> list;
    private static Pattern userNamePattern = Pattern.compile("^[a-z0-9]{4,12}$"); //REGEX for alphanumeric characters. 4-12 length.
    private static Pattern passwordPattern = Pattern.compile("[\\w]{6,}"); //REGEX for alphanumeric + '-' & '_' characters.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ac = new AccountController(this);
        list = ac.readAll();

        Button btnCreate = findViewById(R.id.btnCreateAccount);
        btnCreate.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = ac.readAll();
    }

    public static boolean validateUserName(String userName) {

        Matcher match = userNamePattern.matcher(userName);
        return match.matches();
    }

    public static boolean validatePassword(String passWord) {
        Matcher match = passwordPattern.matcher(passWord);
        return match.matches();
    }

    @Override
    public void onClick(View v) {

        username = findViewById(R.id.newUsername);
        password = findViewById(R.id.newPassword);
        name = findViewById(R.id.newProfileName);
        confirmPassword = findViewById(R.id.confirmPassword);

        String user = username.getText().toString().toLowerCase();
        String pass = password.getText().toString();
        String confirm = confirmPassword.getText().toString();
        String profile_name = name.getText().toString();

        //Check if username is in database
        Account account = new Account(user, pass, profile_name);
        int size = ac.ifUsernameExist(account).size();

        if (size > 0 )
        {
            Toast.makeText(this, "Username is already used", Toast.LENGTH_SHORT).show();
        }
        else {
            //If valid username and passwords match, create account
            if (validateUserName(user) && validatePassword(pass) && pass.equals(confirm)) {
                ac.createAccount(new Account(user, pass, profile_name));
                onBackPressed();
            }
            else if (user.length() < 4) {
                Toast.makeText(this, "Username must contain at least 4 characters", Toast.LENGTH_SHORT).show();
            }
            else if (user.length() > 12) {
                Toast.makeText(this, "Username must not contain more than 12 characters", Toast.LENGTH_SHORT).show();
            }
            else if (!pass.equals(confirm)){
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
            else if (!validatePassword(pass)) {
                Toast.makeText(this, "Password is invalid", Toast.LENGTH_SHORT).show();
            }
            else if (profile_name.isEmpty()) {
                Toast.makeText(this, "Enter a profile name", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Invalid account", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
