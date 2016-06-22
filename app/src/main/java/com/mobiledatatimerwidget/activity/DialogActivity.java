package com.mobiledatatimerwidget.activity;

import android.Manifest;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mobiledatatimerwidget.AutoSize.AutoFitEditText;
import com.mobiledatatimerwidget.AutoSize.AutoFitEditTextUtil;
import com.mobiledatatimerwidget.R;
import com.mobiledatatimerwidget.Util;
import com.mobiledatatimerwidget.databinding.FragmentSoftwareDialogBinding;
import com.mobiledatatimerwidget.echo.WidgetValues;
import com.mobiledatatimerwidget.echo.onBinding;
import com.mobiledatatimerwidget.fragments.ErrorFragment;
import com.mobiledatatimerwidget.fragments.InfoFragment;

import layout.MainWidget;

public class DialogActivity extends FragmentActivity implements OnClickListener, onBinding {

    Button ok_btn, cancel_btn;

    AutoFitEditText etxtOffHour = null;
    AutoFitEditText etxtOffMin = null;
    AutoFitEditText etxtOnHour = null;
    AutoFitEditText etxtOnMin = null;

    LinearLayout mRootView;
    WidgetValues widgetValues = null;
    private SharedPreferences sharedpreferences;
    FragmentSoftwareDialogBinding binding = null;
    AdView mAdView;
    private String androidId = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //androidId = Settings.Secure.getString(this.getContentResolver(),Settings.Secure.ANDROID_ID);
        checkVersion();
        readingSharedValues();
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_software_dialog);
        binding.setWidgetValue(widgetValues);
        mRootView = (LinearLayout) findViewById(R.id.rlRoot);
        mAdView = (AdView)findViewById(R.id.adView);
        loadButton();
        loadEditText();
    }

    private void checkVersion() {
        if((Build.VERSION.SDK_INT >19) )
        {
            (new ErrorFragment()).show(getSupportFragmentManager(),"Error Dialog");
        }
    }
    private void loadAdv() {
        if(getAdRequest() !=null ){
            AdRequest adRequest = getAdRequest();
            mAdView.loadAd(adRequest);
        }
    }

    private int checkValue(int id, int value) {
        switch (id) {
            case R.id.btnOffHourPlus:
                value++;
                return (value > 23) ? 23 : value;
            case R.id.btnOffHourMinus:
                value--;
                return (value < 0) ? 0 : value;
            case R.id.btnOffMinPlus:
                value++;
                return (value > 59) ? 59 : value;
            case R.id.btnOffMinMinus:
                value--;
                return (value < 0) ? 0 : value;
            case R.id.btnOnHourPlus:
                value++;
                return (value > 23) ? 23 : value;
            case R.id.btnOnHourMinus:
                value--;
                return (value < 0) ? 0 : value;
            case R.id.btnOnMinPlus:
                value++;
                return (value > 59) ? 59 : value;
            case R.id.btnOnMinMinus:
                value--;
                return (value < 0) ? 0 : value;
        }
        return -1;
    }

    private void controlEditText(int id, String textValue) {
        int value;
        try {
            value = Integer.parseInt(textValue);
        } catch (NumberFormatException e) {
            value = 1;
        }


        switch (id) {
            case R.id.btnOffHourPlus:
                widgetValues.setOffHour(checkValue(R.id.btnOffHourPlus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOffHourMinus:
                widgetValues.setOffHour(checkValue(R.id.btnOffHourMinus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOffMinPlus:
                widgetValues.setOffMin(checkValue(R.id.btnOffMinPlus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOffMinMinus:
                widgetValues.setOffMin(checkValue(R.id.btnOffMinMinus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOnHourPlus:
                widgetValues.setOnHour(checkValue(R.id.btnOnHourPlus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOnHourMinus:
                widgetValues.setOnHour(checkValue(R.id.btnOnHourMinus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOnMinPlus:
                widgetValues.setOnMin(checkValue(R.id.btnOnMinPlus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOnMinMinus:
                widgetValues.setOnMin(checkValue(R.id.btnOnMinMinus, value));
                binding.setWidgetValue(widgetValues);
                break;
        }


    }


    private void loadEditText() {

        etxtOffHour = (AutoFitEditText) findViewById(R.id.etxtOffHour);
        etxtOffMin = (AutoFitEditText) findViewById(R.id.etxtOffMin);
        etxtOnHour = (AutoFitEditText) findViewById(R.id.etxtOnHour);
        etxtOnMin = (AutoFitEditText) findViewById(R.id.etxtOnMin);

        initAutoFitEditText(etxtOffHour);
        initAutoFitEditText(etxtOffMin);
        initAutoFitEditText(etxtOnHour);
        initAutoFitEditText(etxtOnMin);
    }

    private void loadButton() {
        ( findViewById(R.id.btn_save)).setOnClickListener(this);
        ( findViewById(R.id.btn_cancel)).setOnClickListener(this);
        /*
        cancel_btn = (Button) findViewById(R.id.btn_cancel);
        ok_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        */
        findViewById(R.id.btnOffHourPlus).setOnClickListener(this);
        findViewById(R.id.btnOffHourMinus).setOnClickListener(this);
        findViewById(R.id.btnOffMinPlus).setOnClickListener(this);
        findViewById(R.id.btnOffMinMinus).setOnClickListener(this);
        findViewById(R.id.btnOnHourPlus).setOnClickListener(this);
        findViewById(R.id.btnOnHourMinus).setOnClickListener(this);
        findViewById(R.id.btnOnMinPlus).setOnClickListener(this);
        findViewById(R.id.btnOnMinMinus).setOnClickListener(this);
        findViewById(R.id.btn_info).setOnClickListener(this);

        /*

                ((ImageButton) findViewById(R.id.btnOffHourPlus)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btnOffHourMinus)).setOnClickListener(this);
        findViewById(R.id.btnOffMinPlus).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btnOffMinMinus)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btnOnHourPlus)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btnOnHourMinus)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btnOnMinPlus)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btnOnMinMinus)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btn_info)).setOnClickListener(this);
         */
    }

    private void initAutoFitEditText(AutoFitEditText autoFitEditText) {
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

    private void initAutoFitEditText(View RootView, AutoFitEditText autoFitEditText) {
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_save:
                saveFileFunction();
                updateWidgetValues();
                this.finish();
                break;
            case R.id.btn_cancel:
                this.finish();
                break;
            case R.id.btnOffHourPlus:
                controlEditText(R.id.btnOffHourPlus, widgetValues.getOffHour().toString());
                break;

            case R.id.btnOffHourMinus:
                controlEditText(R.id.btnOffHourMinus, widgetValues.getOffHour().toString());
                break;
            case R.id.btnOffMinPlus:
                controlEditText(R.id.btnOffMinPlus, widgetValues.getOffMin().toString());
                break;
            case R.id.btnOffMinMinus:
                controlEditText(R.id.btnOffMinMinus, widgetValues.getOffMin().toString());
                break;
            case R.id.btnOnHourPlus:
                controlEditText(R.id.btnOnHourPlus, widgetValues.getOnHour().toString());
                break;
            case R.id.btnOnHourMinus:
                controlEditText(R.id.btnOnHourMinus, widgetValues.getOnHour().toString());
                break;
            case R.id.btnOnMinPlus:
                controlEditText(R.id.btnOnMinPlus, widgetValues.getOnMin().toString());
                break;
            case R.id.btnOnMinMinus:
                controlEditText(R.id.btnOnMinMinus, widgetValues.getOnMin().toString());
                break;
            case R.id.btn_info:
                (new InfoFragment()).show(getSupportFragmentManager(),",Info Dialog");
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


    public void readingSharedValues() {

        sharedpreferences = getApplicationContext().getSharedPreferences(MainWidget.CheetatechPref, Context.MODE_PRIVATE);
        int offHour = 0, offMin = 0, onHour = 0, onMin = 0;
        offHour = sharedpreferences.getInt(MainWidget.OffHour, 0);
        offMin = sharedpreferences.getInt(MainWidget.OffMin, 0);
        onHour = sharedpreferences.getInt(MainWidget.OnHour, 0);
        onMin = sharedpreferences.getInt(MainWidget.OnMin, 0);
        widgetValues = new WidgetValues(offHour, offMin, onHour, onMin);
    }

    private void saveFileFunction() {
        int offHour = widgetValues.getIntOffHour();
        int offMin = widgetValues.getIntOffMin();
        int onHour = widgetValues.getIntOnHour();
        int onMin = widgetValues.getIntOnMin();
        sharedpreferences = getApplicationContext().getSharedPreferences(MainWidget.CheetatechPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(MainWidget.OffHour, offHour);
        editor.putInt(MainWidget.OffMin, offMin);
        editor.putInt(MainWidget.OnHour, onHour);
        editor.putInt(MainWidget.OnMin, onMin);
        editor.commit();
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null
                && this.getCurrentFocus().getWindowToken() != null)
            inputMethodManager.hideSoftInputFromWindow(this
                    .getCurrentFocus().getWindowToken(), 0);
    }


    @Override
    public void onBindingAccept() {
        if (binding == null)
            return;
        if (widgetValues == null)
            return;
        binding.setWidgetValue(widgetValues);
    }

    private void updateWidgetValues() {
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), MainWidget.class));
        MainWidget myWidget = new MainWidget();
        myWidget.onUpdate(this, AppWidgetManager.getInstance(this), ids);
    }


    @Override
    public void onStart() {
        super.onStart();
        loadAdv();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public AdRequest getAdRequest() {
        boolean test = false;
        AdRequest ret = null;
        if (test) {
            boolean hasReadPhoneStatePermission = checkPermission(Manifest.permission.READ_PHONE_STATE);
            if (hasReadPhoneStatePermission) {
                ret= new AdRequest.Builder().addTestDevice("7BF05AEC1F0B70DA492154074AD25816").addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, Util.REQUEST_READ_PHONE_STATE);
            }
        } else {
            Log.e("TAG","NORMAL REKLAM");
            ret= new AdRequest.Builder().build();
        }
        return ret;
    }
    private boolean checkPermission(String readPhoneState) {
        boolean ret = ContextCompat.checkSelfPermission(this, readPhoneState) == PackageManager.PERMISSION_GRANTED;
        return ret;
    }
    public String getPhoneId() {
        String deviceId = MD5(androidId);
        return  deviceId.toUpperCase();
        /*
        String ret = "";
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId();
        Log.e("Device Id","id is "+deviceid);
        return deviceid;
        */
    }
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}

