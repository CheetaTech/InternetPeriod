package com.mobiledatatimerwidget.prefferences;
import android.content.Context;
import android.content.SharedPreferences;

import layout.MainWidget;

/**
 * Created by CCelik on 26-04-2016.
 */
public class SPreferences {

    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SPreferences(Context context) {
        this.context = context;

        preferences = context.getSharedPreferences(MainWidget.CheetatechPref, Context.MODE_PRIVATE);
        editor = preferences.edit();
        /*preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        */
    }

    public void putInteger(String tag, int value){
        editor.putInt(tag,value);
        //editor.commit();
    }
    public void commit()
    {
        editor.commit();
    }

    public int getInteger(String tag){
        return preferences.getInt(tag,-1);
    }

    public void removePreference(String tag){
        editor.remove(tag);
        editor.commit();
    }

}
