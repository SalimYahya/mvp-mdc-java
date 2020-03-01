package com.example.shrinematerialdesign.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static SharedPreferenceManager instance;
    private  Context ctx;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String SHARED_PREF_NAME = "sharedpredfile12";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_ID = "id";

    private SharedPreferenceManager(Context context){
        ctx = context;
        sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public static synchronized SharedPreferenceManager getInstance(Context context){
        if (instance == null)
            instance = new SharedPreferenceManager(context);

        return instance;
    }


    public int getUserID(){
        return sharedPreferences.getInt(KEY_USER_ID, 0);
    }

    /**
     * Save username
     * */
    public void userLogin(String username){
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    /**
     * Is username saved
     * */
    public boolean isLoggedIn(){
        if (sharedPreferences.getString(KEY_USERNAME, null) != null)
            return true;

        return false;
    }

    /**
     * Clear username from
     * SharedPref file
     * */
    public void logout(){
        editor.clear();
        editor.apply();
    }
}
