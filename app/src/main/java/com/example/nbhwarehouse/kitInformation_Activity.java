package com.example.nbhwarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class kitInformation_Activity extends AppCompatActivity {

    private ImageView kitImgView;
    private TextView TVkitName, TVkitType, TVkitDescription, TVkitPrice;
    private Button btnRentNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kit_information);

        initViews();

        Intent i= getIntent();
        if (null != i){
            int kitID= i.getIntExtra("kitID", -1);
            if(kitID != -1){
                Kit_Model incomingKit= Utils.getInstance().getKitByID(kitID);
                if (null != incomingKit){
                    setKitInfo(incomingKit);
                }
            }
        }

        btnRentNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(kitInformation_Activity.this, RentProcess1_Activity.class);
                startActivity(i);
            }
        });
    }

    private void setKitInfo(Kit_Model kit) {
        TVkitName.setText(kit.getName());
        TVkitType.setText(kit.getCategory());
        TVkitDescription.setText(kit.getDescription());
        TVkitPrice.setText(String.valueOf(kit.getPrice()));

        //Glide.with(this).asBitmap().load(kit.getImage()).into(kitImgView);

    }

    private void initViews(){

        //Kit Image
        kitImgView= findViewById(R.id.kitImage);

        //Kit Text views
        TVkitName= findViewById(R.id.TVkitName);
        TVkitType= findViewById(R.id.TVkitType);
        TVkitDescription= findViewById(R.id.TVkitDescription);
        //
        TVkitPrice= findViewById(R.id.TVkitPrice);

        btnRentNow= findViewById(R.id.btnRentNow);
    }

}