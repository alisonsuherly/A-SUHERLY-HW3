package com.example.hw3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;

    public SearchFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.search_fragment, container, false);
        recyclerView = view.findViewById(R.id.search_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        SearchView searchView = view.findViewById(R.id.searchView);
;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                final CatAdapter catAdapter = new CatAdapter();

                final RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cat[] catBreedResponse = gson.fromJson(response, Cat[].class);
                        List<Cat> list = Arrays.asList(catBreedResponse);
                        catAdapter.setData(list);
                        recyclerView.setAdapter(catAdapter);

                        CatDatabase.saveCatsToCatDatabase(list);

                        int length = catBreedResponse.length;

                    }

                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        requestQueue.stop();
                    }
                };


                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.thecatapi.com/v1/breeds/search?q="+query, responseListener, errorListener);
                requestQueue.add(stringRequest);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                return false;
            }
        });

        return view;
    }
}
