package com.demo.searchview.Online_Search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.searchview.R;

import java.util.ArrayList;

public class Country_Adapter extends RecyclerView.Adapter<Country_Adapter.MyViewHolder> {
    ArrayList<Country_Model> conVideoArrayList;
    Context context;

    String str_userid, str_name, str_fname, str_lname;

    public interface OnItemClickListener {
        void onItemClick(int positon, Country_Model item, View view);
    }

    /* access modifiers changed from: 0000 */
    public void setFilter(ArrayList<Country_Model> filterdNames) {
        this.conVideoArrayList = filterdNames;
        notifyDataSetChanged();
    }

    public Country_Adapter(ArrayList<Country_Model> conVideoArrayList, AppCompatActivity context) {
        this.conVideoArrayList = conVideoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Country_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new Country_Adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Country_Adapter.MyViewHolder holder, final int position) {
        final Country_Model classModel = conVideoArrayList.get(position);


        holder.name.setText(classModel.name);


        holder.ll_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + classModel.name, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return conVideoArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, city, mobile, age, blood_grp, id;
        ImageView image_app;
        LinearLayout ll_;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image_app = itemView.findViewById(R.id.image);
            ll_ = itemView.findViewById(R.id.lyt_parent);



        }


    }


}

