package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lifesolutions.bd.Activities.VideoListActivity;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

public class ClassItemAdapter extends RecyclerView.Adapter<ClassItemAdapter.ClassListViewHolder> {

    private Context mContext;
    private ArrayList<String> items;

    public ClassItemAdapter(Context mContext, ArrayList<String> items) {
        this.mContext = mContext;
        this.items = items;
    }


    @NonNull
    @Override
    public ClassItemAdapter.ClassListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassItemAdapter.ClassListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.class_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ClassItemAdapter.ClassListViewHolder holder, final int position) {

        holder.title.setText(items.get(position));
        holder.item.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, VideoListActivity.class).putExtra("reference",items.get(position))));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ClassListViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CardView item;

        public ClassListViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_class_layout);
            item = itemView.findViewById(R.id.item_class_layout);
        }
    }
}
