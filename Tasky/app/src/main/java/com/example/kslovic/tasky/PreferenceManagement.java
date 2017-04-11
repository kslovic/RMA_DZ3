package com.example.kslovic.tasky;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PreferenceManagement {
    public static String PREFS_FILE = "MyPreferences";
    public static String CATEGORIES="categories";
    public static Set<String> defValues = Collections.EMPTY_SET;
    public void saveCategory(Context context, Set<String> list) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(CATEGORIES, list);
        editor.commit();
    }
    public Set<String> retrieveCategory(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(CATEGORIES,defValues);
    }
}
