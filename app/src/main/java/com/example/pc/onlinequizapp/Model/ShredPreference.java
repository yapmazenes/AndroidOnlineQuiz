package com.example.pc.onlinequizapp.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class ShredPreference {
    static final String Pref_Name = "Login";


    SharedPreferences settings;

    public ShredPreference(Context context) {

        settings = context.getSharedPreferences(Pref_Name, Context.MODE_PRIVATE);
    }

    public void Save(String Key, String Value)

    {

        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Key, Value);
        editor.commit();
    }

    public void SaveBoolean(String Key, Boolean Value)

    {

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Key, Value);
        editor.commit();
    }

    public String getValue(String Key) {

        String text = settings.getString(Key, null);
        return text;
    }

    public Boolean getValueBoolean(String Key) {

        Boolean text = settings.getBoolean(Key, false);
        return text;
    }

    public void Clear() {
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();

    }

    public void Remove(String Key) {
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(Key);
        editor.commit();

    }
}


