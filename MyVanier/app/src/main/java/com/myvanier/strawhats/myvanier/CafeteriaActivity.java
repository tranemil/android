package com.myvanier.strawhats.myvanier;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.JakesMenu;


public class CafeteriaActivity extends AppCompatActivity implements View.OnClickListener{


    /**
     * Initializes components when activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria);

        Button btnJakes = (Button) findViewById(R.id.btnJakes);
        btnJakes.setOnClickListener(this);

        Button btnDCaf = (Button) findViewById(R.id.btnDCaf);
        btnDCaf.setOnClickListener(this);

        Button btnNCaf = (Button) findViewById(R.id.btnNCaf);
        btnNCaf.setOnClickListener(this);


    }

    /**
     * performs an action when a specific button is clicked
     * @param v
     */
    @Override
    public void onClick(View v)
    {
        Intent intent1 = new Intent(this, JakesMenu.class);
        Intent intent2 = new Intent(this, NCafMenu.class);
        Intent intent3 = new Intent(this,DCafMenu.class);
        //Intent intent3 = new Intent(this, DCafMenu.class);
        switch (v.getId())
        {
            case R.id.btnJakes:
                Toast.makeText(this, "Jakes Menu Open", Toast.LENGTH_LONG).show();
                startActivity(intent1);
                break;
            case R.id.btnNCaf:
                Toast.makeText(this, "NCaf Menu Open", Toast.LENGTH_LONG).show();
                startActivity(intent2);
                break;
            case R.id.btnDCaf:
                Toast.makeText(this, "DCaf Menu Open", Toast.LENGTH_LONG).show();
                startActivity(intent3);

        }

    }
}
