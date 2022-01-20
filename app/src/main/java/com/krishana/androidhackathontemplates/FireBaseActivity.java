package com.krishana.androidhackathontemplates;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FireBaseActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText foodItem;
    TextView expiryDate;
    Button textViewSave;
    String expiry;
    long send;
    AutoCompleteTextView editTextCategory;
    String[] days = new String[]{"meat","fruit","dairy","leftovers","drinks","vegetables","packaged food"};
    ArrayAdapter<String> adapter;
    ImageView ie ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_page);
        editTextCategory = findViewById(R.id.editTextCategory);
        adapter = new ArrayAdapter<>(this,R.layout.dropdown,days);
        editTextCategory.setAdapter(adapter);


        foodItem = findViewById(R.id.foodItem);
        expiryDate = findViewById(R.id.expiryDate);
        textViewSave = findViewById(R.id.textView_save);
        editTextCategory = findViewById(R.id.editTextCategory);
        foodItem.setHint("food item");
        expiryDate.setText("16/04/2022");


        expiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        textViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String food = foodItem.getText().toString();
                String category = editTextCategory.getText().toString();
                uploadData(food,send,category);
                foodItem.getText().clear();
                foodItem.setHint("food item");
                expiryDate.setText("16/04/2022");
                editTextCategory.setText("Select Category");
                adapter = new ArrayAdapter<>(FireBaseActivity.this,R.layout.dropdown,days);
                editTextCategory.setAdapter(adapter);
            }
        });


    }

    public void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONDAY),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void uploadData(String food,long send,String category)
    {
        Map<String, Object> item = new HashMap<>();
        item.put("expiryDate",send);
        item.put("item",food);
        item.put("category",category);

        db.collection("yash")
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("tag", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(FireBaseActivity.this, "Item Uploaded!!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("tag", "Error adding document", e);
                    }
                });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        Date systemDate = new Date();
        String inputString1 = myFormat.format(systemDate);
        String inputString2 = i2+" "+(i1+1)+" "+i;

        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            send = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        expiry = i+"/"+(i1+1)+"/"+i2;
        expiryDate.setText(expiry);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FireBaseActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();

    }
}
