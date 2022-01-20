package com.krishana.androidhackathontemplates;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class to_buy_list_adapter extends FirestoreRecyclerAdapter<RecyclerViewData,to_buy_list_adapter.MyViewHolderbuy> {

    public void deleteData(int position)
    {
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    public to_buy_list_adapter(@NonNull FirestoreRecyclerOptions<RecyclerViewData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolderbuy holder, int position, @NonNull RecyclerViewData model) {
        holder.itemTextView.setText(model.getItem());
        holder.categoryTextView.setText(model.getCategory());
        int resourceID ,foregroundColor = Color.WHITE;
        holder.itemTextView.setTextColor(foregroundColor);
        holder.categoryTextView.setTextColor(foregroundColor);
    }

    @NonNull
    @Override
    public MyViewHolderbuy onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_buy_items_item,parent,false);
        return new MyViewHolderbuy(view);
    }


    class MyViewHolderbuy extends RecyclerView.ViewHolder{
        private Context context;
        public ConstraintLayout item;
        private TextView itemTextView;
        private TextView categoryTextView;

        public MyViewHolderbuy(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            item = itemView.findViewById(R.id.item_background);
            itemTextView = itemView.findViewById(R.id.item);
            categoryTextView = itemView.findViewById(R.id.editTextCategory);
        }

    }

}

