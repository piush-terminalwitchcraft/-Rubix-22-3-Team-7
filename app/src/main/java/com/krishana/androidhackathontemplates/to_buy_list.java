package com.krishana.androidhackathontemplates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class to_buy_list extends AppCompatActivity {
    private RecyclerView recyclerView;
    private to_buy_list_adapter  recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_buy_list);
        setRecyclerView();
    }

    private void setRecyclerView()
    {
        Query query = FirebaseFirestore.getInstance()
                .collection("yash")
                .orderBy("expiryDate", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<RecyclerViewData> options = new FirestoreRecyclerOptions.Builder<RecyclerViewData>()
                .setQuery(query, RecyclerViewData.class)
                .build();

        recyclerViewAdapter = new to_buy_list_adapter(options);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                recyclerViewAdapter.deleteData(viewHolder.getAdapterPosition());
                Toast.makeText(to_buy_list.this, "Item Deleted!!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapter.stopListening();
    }
}