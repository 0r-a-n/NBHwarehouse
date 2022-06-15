package com.example.nbhwarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SellFeedback_Activity extends AppCompatActivity {

    private Button btnBackHomeMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_feedback);

        btnBackHomeMenu= findViewById(R.id.btnBackHome);

        btnBackHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellFeedback_Activity.this, HomeMenu_Activity.class);
                startActivity(i);
            }
        });
    }
}