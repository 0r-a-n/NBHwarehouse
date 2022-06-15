package com.example.nbhwarehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RentProcess1_Activity extends AppCompatActivity {

    private static final String TAG = "RentProcess1_Activity";
    private EditText email, phoneNumber, rentHours;
    private Button btnProgress, btnEditEmail, btnEditPhone;
    private TextView emailWarn,phoneWarn, hoursWarn;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_process1);

        initViews();

        btnEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setEnabled(true);
            }
        });

        btnEditPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber.setEnabled(true);
            }
        });

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initProcess1();
            }
        });
    }

    private void initProcess1(){
        Log.d(TAG, "initRentProcess1: Started");

        if (validateData()){

            progress();

        }
    }

    private void progress(){
        Log.d(TAG, "showProgress: Started");
        emailWarn.setVisibility(View.GONE);
        phoneWarn.setVisibility(View.GONE);
        hoursWarn.setVisibility(View.GONE);

        Intent i = new Intent(RentProcess1_Activity.this, RentProcess2_Activity.class);
        startActivity(i);
    }

    private boolean validateData(){
        Log.d(TAG, "validateData: Started");

        if(email.getText().toString().equals("")){
            emailWarn.setVisibility(View.VISIBLE);
            emailWarn.setText("ادخل البريد الإلكتروني");
            return false;
        }else if(phoneNumber.getText().toString().equals("")){
            phoneWarn.setVisibility(View.VISIBLE);
            phoneWarn.setText("ادخل رقم الهاتف");
            return false;
        }else if(rentHours.getText().toString().equals("")){
            hoursWarn.setVisibility(View.VISIBLE);
            hoursWarn.setText("ادخل عدد ساعات الاستئجار");
            return false;
        }else return true;
    }

    private void initViews(){
        Log.d(TAG, "initViews: Started");

        //edit text
        email= findViewById(R.id.sellerEmail);
        phoneNumber= findViewById(R.id.sellerPhone);
        rentHours=findViewById(R.id.rentHours);

        email.setEnabled(false);
        phoneNumber.setEnabled(false);

        //buttons
        btnProgress= findViewById(R.id.btnProgress);
        btnEditEmail= findViewById(R.id.editEmail);
        btnEditPhone= findViewById(R.id.editPhone);

        //Text view for warning message
        emailWarn= findViewById(R.id.SEmailWarn);
        phoneWarn= findViewById(R.id.SPhoneWarn);
        hoursWarn= findViewById(R.id.hoursWarn);

        parent=findViewById(R.id.parent);
    }
}