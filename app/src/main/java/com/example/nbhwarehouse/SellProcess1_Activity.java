package com.example.nbhwarehouse;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SellProcess1_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SellProcess1_Activity";
    private final int GALLERY_REQ_CODE= 1000;
    private EditText kitPreName, kitPreDesc, kitPrePrice;
    private TextView kitNameWarn, kitImgWarn, catSpinWarn, kitDescWarn, kitPriceWarn;
    private Spinner catSpin;
    private ImageView kitPreImg;
    private Button btnUpKitImg, btnNextToSell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_process1);

        initViews();

        //Deal with spinner ************************************************************
        catSpin =findViewById(R.id.catSpin);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorySpin, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        catSpin.setAdapter(adapter);
        catSpin.setOnItemSelectedListener(this);
        //*******************************************************************************

        btnUpKitImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery= new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });

        btnNextToSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initSell_1();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==GALLERY_REQ_CODE){
                kitPreImg.setImageURI(data.getData());
            }
        }
    }

    //To deal with Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "You selected"+parent.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "You selected: "+parent.getSelectedItem(), Toast.LENGTH_SHORT).show();
    }
    //To deal with Spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initSell_1(){
        Log.d(TAG, "initSellProcess1: Started");

        if (validateData()){

            progress();
        }
    }

    private void progress(){
        Log.d(TAG, "showSellProgress1: Started");
        kitNameWarn.setVisibility(View.GONE);
        kitDescWarn.setVisibility(View.GONE);
        kitPriceWarn.setVisibility(View.GONE);
        //kitImgWarn.setVisibility(View.GONE);
        //catSpinWarn.setVisibility(View.GONE);

        Intent i = new Intent(SellProcess1_Activity.this, SellProcess2_Activity.class);
        startActivity(i);
    }

    private boolean validateData(){
        Log.d(TAG, "validateData: Started");
        if(kitPreName.getText().toString().equals("")){
            kitNameWarn.setVisibility(View.VISIBLE);
            kitNameWarn.setText("ادخل اسم المنتج");
            return false;
        }else if(kitPreDesc.getText().toString().equals("")){
            kitDescWarn.setVisibility(View.VISIBLE);
            kitDescWarn.setText("أوصف معلومات المنتج");
            return false;
        }else if(kitPrePrice.getText().toString().equals("")){
            kitPriceWarn.setVisibility(View.VISIBLE);
            kitPriceWarn.setText("ادخل السعر المقترح");
            return false;
            //TODO: check Uploaded image or not
        }else return true;
    }

    private void initViews(){
        Log.d(TAG, "initViews: Started");

        //Edit Texts
        kitPreName= findViewById(R.id.kitPreName);
        kitPreDesc= findViewById(R.id.kitPreDesc);
        kitPrePrice= findViewById(R.id.kitPrePrice);

        //Text Views fpr Warnings
        kitNameWarn= findViewById(R.id.kitNameWarn);
        kitImgWarn= findViewById(R.id.kitImgWarn);
        catSpinWarn= findViewById(R.id.catSpinWarn);
        kitDescWarn= findViewById(R.id.kitDescWarn);
        kitPriceWarn= findViewById(R.id.kitPriceWarn);


        //Kit Image
        kitPreImg= findViewById(R.id.kitPreImg);

        //Buttons
        btnUpKitImg= findViewById(R.id.btnUpKitImg);
        btnNextToSell= findViewById(R.id.btnNextToSell);
    }

}