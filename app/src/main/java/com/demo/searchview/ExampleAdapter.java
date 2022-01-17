package com.demo.searchview;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends Adapter<ExampleAdapter.ExampleViewHolder> {
    private List<ExampleItem> exampleList;
    private List<ExampleItem> exampleListFull;

    class ExampleViewHolder extends ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;

        ExampleViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.image_app);
            this.textView1 = (TextView) itemView.findViewById(R.id.textview);
            this.textView2 = (TextView) itemView.findViewById(R.id.textview2);
        }
    }

    ExampleAdapter(List<ExampleItem> exampleList2) {
        this.exampleList = exampleList2;
        this.exampleListFull = new ArrayList(exampleList2);
    }

    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExampleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_apps, parent, false));
    }

    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        final ExampleItem currentItem = (ExampleItem) this.exampleList.get(position);
        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.textView1.setText(currentItem.getText1());
        holder.textView2.setText(currentItem.getText2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), ""+currentItem.getText1(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getItemCount() {
        return this.exampleList.size();
    }

    /* access modifiers changed from: 0000 */
    public void setFilter(List<ExampleItem> filterdNames) {
        this.exampleList = filterdNames;
        notifyDataSetChanged();
    }
}