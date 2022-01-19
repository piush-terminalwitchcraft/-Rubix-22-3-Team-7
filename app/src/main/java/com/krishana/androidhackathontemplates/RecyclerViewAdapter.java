package com.krishana.androidhackathontemplates;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RecyclerViewAdapter extends FirestoreRecyclerAdapter<RecyclerViewData,RecyclerViewAdapter.MyViewHolder>{

    public void deleteData(int position)
    {
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    public RecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<RecyclerViewData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull RecyclerViewData model) {
        long expiryDate = model.getExpiryDate();
        String expirydate;
        if(expiryDate>=0){
            expirydate = String.valueOf(expiryDate);
        }
        else{
            expirydate = "Expired";
        }
        holder.expirtDateTextView.setText(expirydate);
        holder.itemTextView.setText(model.getItem());
        holder.categoryTextView.setText(model.getCategory());
        int resourceID ,foregroundColor = Color.WHITE;
        if(model.getExpiryDate()<=0)
        {
            resourceID = R.drawable.item_background_on_expiry;
        }
        else if(model.getExpiryDate() <=3){
            resourceID = R.drawable.item_background_near_expiry;
        }
        else {
            resourceID = R.drawable.item_background;
        }
            holder.item.setBackground(holder.context.getResources().getDrawable(resourceID));
            holder.expirtDateTextView.setTextColor(foregroundColor);
            holder.itemTextView.setTextColor(foregroundColor);
            holder.categoryTextView.setTextColor(foregroundColor);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        public ConstraintLayout item;
        private TextView itemTextView;
        private TextView expirtDateTextView;
        private TextView categoryTextView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            item = itemView.findViewById(R.id.item_background);
            itemTextView = itemView.findViewById(R.id.item);
            expirtDateTextView = itemView.findViewById(R.id.expiryDateTextView);
            categoryTextView = itemView.findViewById(R.id.editTextCategory);
        }

    }

}
