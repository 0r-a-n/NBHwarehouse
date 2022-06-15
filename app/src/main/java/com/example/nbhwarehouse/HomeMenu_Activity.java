package com.example.nbhwarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeMenu_Activity extends AppCompatActivity {

    private Button btnRent, btnSell, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        btnRent= findViewById(R.id.btnRentHome);
        btnSell= findViewById(R.id.btnSellHome);
        btnLogin= findViewById(R.id.btnLoginHome);

        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMenu_Activity.this, KitsHome_Activity.class);
                startActivity(i);
            }
        });

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMenu_Activity.this, SellProcess1_Activity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMenu_Activity.this, Login_Activity.class);
                startActivity(i);
            }
        });
    }
}