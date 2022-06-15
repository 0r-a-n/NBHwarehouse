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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SellProcess2_Activity extends AppCompatActivity {

    private static final String TAG = "SellProcess2_Activity";
    private EditText sellerFName, sellerLName, sellerEmail, sellerPhone,sellerIBAN;
    private TextView SFNameWarn, SLNameWarn, SEmailWarn, SPhoneWarn, SLocationWarn, bankAccWarn, viewSellPolicies;
    private Spinner SLocationSpinner;
    private CheckBox sellPolicyCB;
    private Button btnFNameEdit, btnLNameEdit, btnEmailEdit, btnPhoneEdit, btnSellDone,btnOpenSellPolicies, btnAcceptSellPolicies;
    private AlertDialog.Builder policyBuilder;
    private AlertDialog policyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_process2);

        initViews();

        btnOpenSellPolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpSellPolicyWindow();
            }
        });

        btnSellDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSell_2();
            }
        });
    }

    private void popUpSellPolicyWindow(){
        policyBuilder= new AlertDialog.Builder(this);
        final  View sellPoliciesPopup= getLayoutInflater().inflate(R.layout.sell_policies_popup, null);
        viewSellPolicies= sellPoliciesPopup.findViewById(R.id.viewSellPolicies);
        btnAcceptSellPolicies= sellPoliciesPopup.findViewById(R.id.btnAcceptSellPolicies);
        policyBuilder.setView(sellPoliciesPopup);
        policyDialog= policyBuilder.create();
        policyDialog.show();

        btnAcceptSellPolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                policyDialog.dismiss();
            }
        });
    }

    private void initSell_2(){
        Log.d(TAG, "initSellProcess2: Started");

        if (validateData()){
            if(sellPolicyCB.isChecked()){
                sellSuccess();

            }else {
                Toast.makeText(this, "يجب الموافقة على سياسات البيع", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sellSuccess(){
        Log.d(TAG, "showSellProgress1: Started");
        SFNameWarn.setVisibility(View.GONE);
        SLNameWarn.setVisibility(View.GONE);
        SEmailWarn.setVisibility(View.GONE);
        SPhoneWarn.setVisibility(View.GONE);
        bankAccWarn.setVisibility(View.GONE);
        //SLocationWarn.setVisibility(View.GONE);

        Intent i = new Intent(SellProcess2_Activity.this, SellFeedback_Activity.class);
        startActivity(i);
    }

    private boolean validateData(){
        //TODO: feched data from DB
        //TODO: disable and enable edit texts
        Log.d(TAG, "validateData: Started");
        if(sellerFName.getText().toString().equals("")){
            SFNameWarn.setVisibility(View.VISIBLE);
            SFNameWarn.setText("ادخل الاسم الأول");
            return false;
        }else if(sellerLName.getText().toString().equals("")){
            SLNameWarn.setVisibility(View.VISIBLE);
            SLNameWarn.setText("ادخل الاسم الثاني");
            return false;
        }else if(sellerEmail.getText().toString().equals("")) {
            SEmailWarn.setVisibility(View.VISIBLE);
            SEmailWarn.setText("ادخل ايميل للتواصل");
            return false;
        }else if(sellerPhone.getText().toString().equals("")) {
            SPhoneWarn.setVisibility(View.VISIBLE);
            SPhoneWarn.setText("ادخل رقم التواصل");
            return false;
        }else if (sellerIBAN.getText().toString().equals("")){
            bankAccWarn.setVisibility(View.VISIBLE);
            bankAccWarn.setText("ادخل الحساب البنكي");
            return false;
            //TODO: check seller Location spinner is empty or not
        }else return true;
    }

    private void initViews(){
        Log.d(TAG, "initViews: Started");

        //Edit Texts
        sellerFName= findViewById(R.id.sellerFName);
        sellerLName= findViewById(R.id.sellerLName);
        sellerEmail= findViewById(R.id.sellerEmail);
        sellerPhone= findViewById(R.id.sellerPhone);
        sellerIBAN= findViewById(R.id.sellerIBAN);

        //Text Views fpr Warnings
        SFNameWarn= findViewById(R.id.SFNameWarn);
        SLNameWarn= findViewById(R.id.SLNameWarn);
        SEmailWarn= findViewById(R.id.SEmailWarn);
        SPhoneWarn= findViewById(R.id.SPhoneWarn);
        SLocationWarn= findViewById(R.id.SLocationWarn);
        bankAccWarn= findViewById(R.id.bankAccWarn);

        //Category Spinner
        SLocationSpinner= findViewById(R.id.SLocationSpinner);

        //ِAgreement Selling Policy check box
        sellPolicyCB= findViewById(R.id.sellPolicyCB);

        //Buttons
        btnFNameEdit= findViewById(R.id.btnFNameEdit);
        btnLNameEdit= findViewById(R.id.btnLNameEdit);
        btnEmailEdit= findViewById(R.id.btnEmailEdit);
        btnPhoneEdit= findViewById(R.id.btnPhoneEdit);
        btnOpenSellPolicies= findViewById(R.id.btnOpenSellPolicies);
        btnSellDone= findViewById(R.id.btnSellDone);
    }
}