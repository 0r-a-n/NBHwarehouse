package com.example.nbhwarehouse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class KitsRecViewAdapter extends RecyclerView.Adapter<KitsRecViewAdapter.ViewHolder>{

    private ArrayList<Kit_Model> kits= new ArrayList<>();
    private Context context;

    public KitsRecViewAdapter(Context context) {
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.kits_list_items, parent, false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.kitName.setText(kits.get(position).getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context, kitInformation_Activity.class);
                i.putExtra("kitID", kits.get(position).getId());
                context.startActivity(i);
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(kits.get(position).getImage())
                .into(holder.kitImage);
    }

    @Override
    public int getItemCount() {
        return kits.size();
    }

    public void setKits(ArrayList<Kit_Model> kits) {
        this.kits = kits;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView kitName;
        private CardView parent;
        private ImageView kitImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kitName= itemView.findViewById(R.id.kitName);
            parent= itemView.findViewById(R.id.parent);
            kitImage= itemView.findViewById(R.id.kitImage);
        }
    }


}
