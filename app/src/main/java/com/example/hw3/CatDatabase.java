package com.example.hw3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CatDatabase {
    public static HashMap<String, Cat> cats = new HashMap<>();

    public static HashMap<String, Cat> favCats = new HashMap<>();

    public static HashMap<String, Image> catImage = new HashMap<>();


    public static Cat getCatById(String catId) {
        return cats.get(catId);
    }

    public static List<Cat> getAllCats() {
        return (List) cats.values();
    }

    public static void saveCatsToCatDatabase(List<Cat> catsToSave) {
        for(int i = 0; i < catsToSave.size(); i++) {
            Cat cat = catsToSave.get(i);
            cats.put(cat.getId(), cat);
        }
    }

    public static List<Cat> getAllFavCats() {

        Collection<Cat> values = favCats.values();
        List<Cat> listOfValues = new ArrayList<Cat>(values);
        return listOfValues;

    }

    public static void saveFavCatsToDatabase(Cat favCatsToSave) {

        favCats.put(favCatsToSave.getId(), favCatsToSave);

    }

}
