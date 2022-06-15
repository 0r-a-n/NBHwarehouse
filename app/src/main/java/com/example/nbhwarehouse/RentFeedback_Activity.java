package com.example.nbhwarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RentFeedback_Activity extends AppCompatActivity {

    private TextView rentOrderNum ;
    private Button backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_feedback);

        rentOrderNum=findViewById(R.id.rentOrderNumber);
        backHome= findViewById(R.id.btnBackHome);

        rentOrderNum.setText("#7988664670");

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RentFeedback_Activity.this, KitsHome_Activity.class);
                startActivity(i);
            }
        });
    }
}