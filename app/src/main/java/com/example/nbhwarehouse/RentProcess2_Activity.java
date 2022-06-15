package com.example.nbhwarehouse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RentProcess2_Activity extends AppCompatActivity {

    private static final String TAG = "RentProcess2_Activity";
    private EditText cardNumber, expiredDate, CVV;
    private Button btnRentDone, btnOpenRentPolicies, btnAcceptPolicies;
    private TextView cardNumWarn, dateWarn, cvvWarn, totalRentPrice, viewRentPolicies;
    private CheckBox rentPolicyCB;
    private AlertDialog.Builder policyBuilder;
    private AlertDialog policyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_process2);

        initViews();

        btnOpenRentPolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpRentPolicyWindow();
            }
        });

        btnRentDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initRentProcess2();
            }
        });
    }

    private void popUpRentPolicyWindow(){
        policyBuilder= new AlertDialog.Builder(this);
        final  View rentPoliciesPopup= getLayoutInflater().inflate(R.layout.rent_policies_popup, null);
        viewRentPolicies= rentPoliciesPopup.findViewById(R.id.viewRentPolicies);
        btnAcceptPolicies= rentPoliciesPopup.findViewById(R.id.btnAcceptRentPolicies);
        policyBuilder.setView(rentPoliciesPopup);
        policyDialog= policyBuilder.create();
        policyDialog.show();

        btnAcceptPolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                policyDialog.dismiss();
            }
        });
    }

    private void initRentProcess2(){
        Log.d(TAG, "initRentProcess2: Started");

        if (validateData()){

            if(rentPolicyCB.isChecked()){
                rentSuccess();

            }else {
                Toast.makeText(this, "يجب الموافقة على سياسات الاستئجار", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void rentSuccess(){
        Log.d(TAG, "showSnackBar: Started");
        cardNumWarn.setVisibility(View.GONE);
        dateWarn.setVisibility(View.GONE);
        cvvWarn.setVisibility(View.GONE);

        Intent i = new Intent(RentProcess2_Activity.this, RentFeedback_Activity.class);
        startActivity(i);
    }

    private boolean validateData(){
        Log.d(TAG, "validateData: Started");
        if(cardNumber.getText().toString().equals("")){
            cardNumWarn.setVisibility(View.VISIBLE);
            cardNumWarn.setText("ادخل رقم البطاقة");
            return false;
        }else if(expiredDate.getText().toString().equals("")){
            dateWarn.setVisibility(View.VISIBLE);
            dateWarn.setText("ادخل تاريخ انتهاء البطاقة");
            return false;
        }else if(CVV.getText().toString().equals("")){
            cvvWarn.setVisibility(View.VISIBLE);
            cvvWarn.setText("ادخل الـ 3 أرقام خلف البطاقة");
            return false;

        } else return true;
    }

    private void initViews(){
        Log.d(TAG, "initViews: Started");

        //edit text
        cardNumber= findViewById(R.id.cardNumber);
        expiredDate= findViewById(R.id.expiredDate);
        CVV= findViewById(R.id.CVV);

        //Buttons
        btnRentDone= findViewById(R.id.btnRentDone);
        btnOpenRentPolicies= findViewById(R.id.btnOpenRentPolicies);

        //Text view for warning message
        cardNumWarn= findViewById(R.id.cardNumWarn);
        dateWarn=findViewById(R.id.expiredDateWarn);
        cvvWarn=findViewById(R.id.CVVwarn);
        totalRentPrice= findViewById(R.id.totalRentPrice); //TODO calculate total price (need DB)

        //ِAgreement Rent Policy check box
        rentPolicyCB=findViewById(R.id.rentPolicy);

        //TODO: create a pop up that contains Rent Policies

    }
}