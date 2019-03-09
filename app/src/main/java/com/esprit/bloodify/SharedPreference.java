package com.esprit.bloodify;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private SharedPreferences sharedPreferences ;
    private Context context ;



    public SharedPreference(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_pref),Context.MODE_PRIVATE);


    }



    public  void writeLoginStatus(boolean status) {


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_stat),status);
        editor.commit();


    }





    public boolean readLoginStatus()

    {

        boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_stat),false);
        return  status ;




    }


}
