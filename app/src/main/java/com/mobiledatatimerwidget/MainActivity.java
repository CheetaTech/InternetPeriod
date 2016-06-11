package com.mobiledatatimerwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_DEFAULT_FRAGMENT = "com.mobiledatatimerwidget.action.EXTRA_DEFAULT_FRAGMENT";

    public static final int FRAGMENT_STATISTICS = 0;
    public static final int FRAGMENT_DISCOVER = 1;
    public static android.support.v4.app.FragmentManager fragmentManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Fragment", "Managet is OK");
        fragmentManager = this.getSupportFragmentManager();

        //android:excludeFromRecents="true"
        //android:launchMode="singleInstance"
        //android:theme="@android:style/Theme.Holo.Light.Dialog"
    }


}
