package com.example.hw3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouriteFragment extends Fragment {

    private RecyclerView recyclerView;

    CatAdapter catAdapter = new CatAdapter();

    public FavouriteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.favourite_fragment, container, false);
        recyclerView = view.findViewById(R.id.fav_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Cat> favCatList = CatDatabase.getAllFavCats();

        catAdapter.setData(favCatList);
        recyclerView.setAdapter(catAdapter);

        return view;
    }

}
