package com.mobiledatatimerwidget.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mobiledatatimerwidget.R;

public class ErrorActivity extends FragmentActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_activity_layout);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().setLayout((int) (displayMetrics.widthPixels * 0.8f), (int) (displayMetrics.heightPixels * 0.8f));

        ((Button)findViewById(R.id.btn_quit)).setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_quit:
                this.finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
