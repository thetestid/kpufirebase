package com.webnatues.kpufirebase;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<PI> arrayList;
    private Context context;
    public DatabaseReference mDatabase;



    public CustomAdapter(ArrayList<PI> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // listview연결 후 viewholder를 최초로 만들어줌
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // item의 매칭
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);
        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_W.setText(String.valueOf(arrayList.get(position).getW()));
        holder.tv_sWitch.setText(arrayList.get(position).getsWitch());
    }
    private static final String TAG = "MainActivity";

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_W;
        TextView tv_sWitch;
        Button button;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById((R.id.iv_profile));
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_W = itemView.findViewById(R.id.tv_W);
            this.tv_sWitch = itemView.findViewById(R.id.tv_sWitch);
            this.button = itemView.findViewById(R.id.button2);
            mDatabase = FirebaseDatabase.getInstance().getReference("PI");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( tv_sWitch.getText().toString() == "on"){
                        tv_sWitch.setText("off");
                        mDatabase.child(tv_id.getText().toString()).child("sWitch").setValue("off");
                    }else{
                        tv_sWitch.setText("on");
                        mDatabase.child(tv_id.getText().toString()).child("sWitch").setValue("on");
                    }

                }
            });
        }
    }


}
