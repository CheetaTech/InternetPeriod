package com.mobiledatatimerwidget;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobiledatatimerwidget.AutoSize.AutoFitEditText;
import com.mobiledatatimerwidget.AutoSize.AutoFitEditTextUtil;

import static com.mobiledatatimerwidget.R.layout.fragment_software_dialog;

public class DialogActivity extends Activity implements OnClickListener {

    Button ok_btn , cancel_btn;
    AutoResizeEditText mAutoResizeEditText = null;
    AutoFitEditText etxtOffHour = null;
    AutoFitEditText etxtOffMin = null;
    AutoFitEditText etxtOffSec = null;
    AutoFitEditText etxtOnHour= null;
    AutoFitEditText etxtOnMin = null;
    AutoFitEditText etxtOnSec = null;
    LinearLayout mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(fragment_software_dialog);
        mRootView = (LinearLayout)findViewById(R.id.rlRoot);
        loadButton();
        loadEditText();
    }

    private void loadEditText()
    {
        etxtOffHour = (AutoFitEditText)findViewById(R.id.etxtOffHour);
        etxtOffMin =(AutoFitEditText)findViewById(R.id.etxtOffMin);
        etxtOffSec =(AutoFitEditText)findViewById(R.id.etxtOffSec);
        etxtOnHour=(AutoFitEditText)findViewById(R.id.etxtOnHour);
        etxtOnMin =(AutoFitEditText)findViewById(R.id.etxtOnMin);
        etxtOnSec =(AutoFitEditText)findViewById(R.id.etxtOnSec);

        initAutoFitEditText(etxtOffHour);
        initAutoFitEditText(etxtOffMin);
        initAutoFitEditText(etxtOffSec);
        initAutoFitEditText(etxtOnHour);
        initAutoFitEditText(etxtOnMin);
        initAutoFitEditText(etxtOnSec);
    }
    private void loadButton()
    {
        ok_btn = (Button) findViewById(R.id.ok_btn_id);
        cancel_btn = (Button) findViewById(R.id.cancel_btn_id);
        ok_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
    }

    private void initAutoFitEditText(AutoFitEditText autoFitEditText)
    {
        autoFitEditText.setEnabled(true);
        autoFitEditText.setFocusableInTouchMode(true);
        autoFitEditText.setFocusable(true);
        autoFitEditText.setEnableSizeCache(false);
        //might cause crash on some devices
        autoFitEditText.setMovementMethod(null);
        // can be added after layout inflation;
        autoFitEditText.setMaxHeight(330);
        //don't forget to add min text size programmatically
        autoFitEditText.setMinTextSize(60f);

        AutoFitEditTextUtil.setNormalization(this, mRootView, autoFitEditText);
    }
    private void initAutoFitEditText(View RootView,AutoFitEditText autoFitEditText)
    {
        autoFitEditText.setEnabled(true);
        autoFitEditText.setFocusableInTouchMode(true);
        autoFitEditText.setFocusable(true);
        autoFitEditText.setEnableSizeCache(false);
        //might cause crash on some devices
        autoFitEditText.setMovementMethod(null);
        // can be added after layout inflation;
        autoFitEditText.setMaxHeight(330);
        //don't forget to add min text size programmatically
        autoFitEditText.setMinTextSize(60f);

        AutoFitEditTextUtil.setNormalization(this, RootView, autoFitEditText);
    }
    /*
    public void initAutoFitEditText() {

        mAutoFitEditText.setEnabled(true);
        mAutoFitEditText.setFocusableInTouchMode(true);
        mAutoFitEditText.setFocusable(true);
        mAutoFitEditText.setEnableSizeCache(false);
        //might cause crash on some devices
        mAutoFitEditText.setMovementMethod(null);
        // can be added after layout inflation;
        mAutoFitEditText.setMaxHeight(330);
        //don't forget to add min text size programmatically
        mAutoFitEditText.setMinTextSize(60f);

        AutoFitEditTextUtil.setNormalization(this, mRootView, mAutoFitEditText);
    }*/
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        case R.id.ok_btn_id:

            showToastMessage("Ok Button Clicked");
            this.finish();

            break;

        case R.id.cancel_btn_id:

            showToastMessage("Cancel Button Clicked");
            this.finish();

            break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show();
    }




    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null
                && this.getCurrentFocus().getWindowToken() != null)
            inputMethodManager.hideSoftInputFromWindow(this
                    .getCurrentFocus().getWindowToken(), 0);
    }




}
