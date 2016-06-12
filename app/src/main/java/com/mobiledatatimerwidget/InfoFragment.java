package com.mobiledatatimerwidget;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoFragment extends DialogFragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.info_fragment, container,
                false);


        getDialog().setTitle("Net Guardion Usage");

        /*
        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.verticalMargin = 0;
        params.horizontalMargin = 0;
        params.width = getResources().getDimensionPixelSize(R.dimen.dialog_withhhh);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        */
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }
}