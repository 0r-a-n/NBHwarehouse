package com.example.nbhwarehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class KitsHome_Activity extends AppCompatActivity {

    private RecyclerView kitsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kits_home);

        kitsRecView= findViewById(R.id.kitsRecView);


        KitsRecViewAdapter adapter= new KitsRecViewAdapter(this);
        adapter.setKits(Utils.getInstance().getAllKits());

        kitsRecView.setAdapter(adapter);
        kitsRecView.setLayoutManager(new GridLayoutManager(this, 2));

    }
}