package com.mobiledatatimerwidget;


import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class InfoFragment extends DialogFragment implements View.OnClickListener {



    private OnFragmentInteractionListener mListener;

    public InfoFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.e("onResume", "onResume");
        this.getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.e("OnCreateDialog", "OnCreateDialog");
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
        View view = getActivity().getLayoutInflater().inflate(R.layout.info_fragment, null);
        dialog.getWindow().setContentView(view);

        //
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        dialog.getWindow().setLayout((int) (displayMetrics.widthPixels * 0.8f), (int) (displayMetrics.heightPixels * 0.8f));
        dialog.setTitle("Internet Period Usage");
        dialog.setCanceledOnTouchOutside(false);

        ((Button)view.findViewById(R.id.btn_quit)).setOnClickListener(this);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("onViewCreated","onViewCreated");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_quit:
                    getDialog().dismiss();
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

