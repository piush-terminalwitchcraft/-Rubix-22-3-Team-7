package com.krishana.androidhackathontemplates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.ViewHolder>{
    private List<NutritionModel> NutritionList;
    private Context context;

    public NutritionAdapter(List<NutritionModel> NutritionList, Context context) {
        this.NutritionList = NutritionList;
        this.context = context;
    }

    @NonNull
    @Override
    public NutritionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nutriton_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionAdapter.ViewHolder holder, int position) {
        NutritionModel NutritionModel = NutritionList.get(position);
        holder.A_nutri_name.setText(NutritionModel.getNutrition());
        holder.A_nutri_Value.setText(NutritionModel.getValue());
    }

    @Override
    public int getItemCount() {
        return NutritionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //define view objects
        CardView A_cv5;
        TextView A_nutri_name , A_nutri_Value;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            A_cv5 = itemView.findViewById(R.id.cv5);
            A_nutri_name = itemView.findViewById(R.id.Nutri_Name);
            A_nutri_Value = itemView.findViewById(R.id.Nutri_Value);


        }
    }
}
