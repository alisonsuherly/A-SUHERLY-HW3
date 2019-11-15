package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatDetailedActivity extends AppCompatActivity {

    private TextView nameTextView;
    private ImageView catImageView;
    private TextView descriptionTextView;
    private TextView originTextView;
    private TextView weightTextView;
    private TextView lifeSpanTextView;
    private TextView temperamentTextView;
    private TextView dogFriendLvlTextView;
    private TextView linkTextView;
    private Button favouriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_detail_activity);

        Intent intent = getIntent();
        String catId = intent.getStringExtra("CatID");
        final Cat cat = CatDatabase.getCatById(catId);

        nameTextView = findViewById(R.id.catName);
        catImageView = findViewById(R.id.catImage);
        descriptionTextView = findViewById(R.id.catDescription);
        originTextView = findViewById(R.id.catOrigin);
        weightTextView = findViewById(R.id.catWeight);
        lifeSpanTextView = findViewById(R.id.lifeSpan);
        temperamentTextView = findViewById(R.id.temperament);
        dogFriendLvlTextView = findViewById(R.id.dogFriendlinessLevel);
        linkTextView = findViewById(R.id.wikiLink);
        favouriteButton = findViewById(R.id.favouriteButton);


        nameTextView.setText(cat.getName());
        descriptionTextView.setText("You have clicked into the informational page for the "+cat.getName()+". For more information on the "+cat.getName()+", please refer to the list of relevant facts below and follow the Wikipedia link!");
        originTextView.setText(cat.getOrigin());
        weightTextView.setText(cat.getWeight_imperial());
        lifeSpanTextView.setText(cat.getLife_span());
        temperamentTextView.setText(cat.getTemperament());
        String friendly = String.valueOf(cat.getDog_friendly());
        dogFriendLvlTextView.setText(friendly);
        linkTextView.setText(cat.getWikipedia_url());

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CatDatabase.saveFavCatsToDatabase(cat);
            }
        });

        final RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Image[] imageResponse = gson.fromJson(response, Image[].class);
                List<Image> list = Arrays.asList(imageResponse);
                int size = imageResponse.length;

                if (imageResponse.length>0) {
                    String string = imageResponse[size-1].getUrl();
                    Glide.with(getApplicationContext()).load(string).into(catImageView);
                } else {
                    catImageView.setImageResource(R.drawable.no_image);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };

        String url = "https://api.thecatapi.com/v1/images/search?breed_id="+cat.getId();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);


    }
}
