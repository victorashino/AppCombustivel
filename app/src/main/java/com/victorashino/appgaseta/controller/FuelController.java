package com.victorashino.appgaseta.controller;

import android.content.SharedPreferences;

import com.victorashino.appgaseta.model.Fuel;
import com.victorashino.appgaseta.view.GasEtaActivity;

public class FuelController {

    SharedPreferences preferences;
    SharedPreferences.Editor dataPreferences;
    public static final String NAME_PREFERENCES = "pref_gaseta";

    public FuelController (GasEtaActivity activity) {
        preferences = activity.getSharedPreferences(NAME_PREFERENCES, 0);

        dataPreferences = preferences.edit();
    }

    public void save(Fuel fuel) {

        dataPreferences.putString("Fuel", fuel.getFuel());
        dataPreferences.putFloat("FuelPrice", (float) fuel.getFuelPrice());
        dataPreferences.putString("Recommendation", fuel.getRecommendation());
        dataPreferences.apply();

    }

    public void clear() {
        dataPreferences.clear();
        dataPreferences.apply();
    }

}
