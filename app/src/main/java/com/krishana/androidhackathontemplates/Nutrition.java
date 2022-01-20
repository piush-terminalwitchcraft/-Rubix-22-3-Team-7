package com.krishana.androidhackathontemplates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Nutrition extends AppCompatActivity {
    ImageView n_recipe_img;
    TextView n_recipe_name;
    private RecyclerView recyclerView5;
    private RecyclerView.Adapter adapter5;
    private List<NutritionModel> NutritionList;
    String Nutrition_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        Bundle bundle = getIntent().getExtras();
        String Recipe_Name = bundle.getString("recipeName");
        String Image = bundle.getString("image");
        int id = bundle.getInt("id");
        n_recipe_img = findViewById(R.id.n_recipe_img);
        n_recipe_name = findViewById(R.id.n_recipe_name);
        n_recipe_name.setText(Recipe_Name);
        Picasso.get().load(Image).into(n_recipe_img);

        recyclerView5 = (RecyclerView) findViewById(R.id.rc5);
        recyclerView5.setHasFixedSize(true);
        recyclerView5.setLayoutManager(new LinearLayoutManager(this));
        NutritionList = new ArrayList<>();
        Nutrition_url ="https://api.spoonacular.com/recipes/"+id+"/nutritionWidget.json?apiKey=f52a16c386a8431b8158cee6f63ac4a6";

        loadRecyclerView();
    }

    private void loadRecyclerView() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING DATA....... ");
        progressDialog.show();

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, Nutrition_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    //getting data  from json object
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("bad");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
//
                        NutritionModel item = new NutritionModel(

                                o.getString("title"),
                                o.getString("amount")
                        );
//                        Log.e("tag",o.getString("name"));
//                        Log.e("tag",o.getString("image"));
//                        Log.e("tag",o2.getString("value"));
//                        Log.e("tag",o2.getString("unit"));

                        NutritionList.add(item);

                    }
                    adapter5 = new NutritionAdapter(NutritionList, getApplicationContext());
//                    GridLayoutManager gridLayoutManager  = new GridLayoutManager(this,2);
//                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView5.setAdapter(adapter5);
                    //adapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "oops!! something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(stringRequest2);
    }

}