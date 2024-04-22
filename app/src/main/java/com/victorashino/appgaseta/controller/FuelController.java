package com.victorashino.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import com.victorashino.appgaseta.data.GasEtaDB;
import com.victorashino.appgaseta.model.Fuel;
import com.victorashino.appgaseta.view.GasEtaActivity;

import java.util.List;

public class FuelController extends GasEtaDB {

    SharedPreferences preferences;
    SharedPreferences.Editor dataPreferences;
    public static final String NAME_PREFERENCES = "pref_gaseta";

    public FuelController (GasEtaActivity activity) {
        super(activity);
        preferences = activity.getSharedPreferences(NAME_PREFERENCES, 0);

        dataPreferences = preferences.edit();
    }

    public void save(Fuel fuel) {

        ContentValues data = new ContentValues();

        dataPreferences.putString("Fuel", fuel.getFuel());
        dataPreferences.putFloat("FuelPrice", (float) fuel.getFuelPrice());
        dataPreferences.putString("Recommendation", fuel.getRecommendation());
        dataPreferences.apply();

        data.put("name", fuel.getFuel());
        data.put("price", fuel.getFuelPrice());
        data.put("recommendation", fuel.getRecommendation());

        saveObject("Fuel", data);
    }

    public void modify(Fuel fuel) {
        ContentValues data = new ContentValues();

        data.put("id", fuel.getId());
        data.put("name", fuel.getFuel());
        data.put("price", fuel.getFuelPrice());
        data.put("recommendation", fuel.getRecommendation());

        modifyData("Fuel", data);
    }

    public List<Fuel> getListData() {
        return listData();
    }

    public void delete(int id) {
        delete("Fuel", id);
    }

    public void clear() {
        dataPreferences.clear();
        dataPreferences.apply();
    }
}
