package com.mobiledatatimerwidget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobiledatatimerwidget.AutoSize.AutoFitEditText;
import com.mobiledatatimerwidget.AutoSize.AutoFitEditTextUtil;
import com.mobiledatatimerwidget.databinding.FragmentSoftwareDialogBinding;
import com.mobiledatatimerwidget.echo.WidgetValues;
import com.mobiledatatimerwidget.echo.onBinding;

import layout.MainWidget;

public class DialogActivity extends Activity implements OnClickListener ,onBinding{

    Button ok_btn , cancel_btn;
    AutoResizeEditText mAutoResizeEditText = null;
    AutoFitEditText etxtOffHour = null;
    AutoFitEditText etxtOffMin = null;
    AutoFitEditText etxtOnHour= null;
    AutoFitEditText etxtOnMin = null;
    LinearLayout mRootView;
    WidgetValues widgetValues = null;
    private SharedPreferences sharedpreferences;
    public int val = 5;


    FragmentSoftwareDialogBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.fragment_software_dialog);



        reaadingSharedValues();

        binding = DataBindingUtil.setContentView(this,R.layout.fragment_software_dialog);
        //widgetValues  = new WidgetValues("1","2","3","4","5","6");
        //widgetValues.setListener(this);
        binding.setWidgetValue(widgetValues);
        mRootView = (LinearLayout)findViewById(R.id.rlRoot);
        loadButton();
        loadEditText();
    }


    private int checkValue(int id,int value)
    {
        switch (id) {
            case R.id.btnOffHourPlus:
                    value++;
                return (value>23)?23:value;
            case R.id.btnOffHourMinus:
                    value--;
                return (value<0)?0:value;
            case R.id.btnOffMinPlus:
                value++;
                return (value>59)?59:value;
            case R.id.btnOffMinMinus:
                value--;
                return (value<0)?0:value;
            case R.id.btnOnHourPlus:
                value++;
                return (value>23)?23:value;
            case R.id.btnOnHourMinus:
                value--;
                return (value<0)?0:value;
            case R.id.btnOnMinPlus:
                value++;
                return (value>59)?59:value;
            case R.id.btnOnMinMinus:
                value--;
                return (value<0)?0:value;
        }
        return -1;
    }

    private void controlEditText(int id,String textValue)
    {
        int value;
        try {
            value = Integer.parseInt(textValue);
        }catch (NumberFormatException e)
        {
            value = 1;
        }


        switch (id)
        {
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


    private void loadEditText()
    {

        etxtOffHour = (AutoFitEditText)findViewById(R.id.etxtOffHour);
        etxtOffMin =(AutoFitEditText)findViewById(R.id.etxtOffMin);
        etxtOnHour=(AutoFitEditText)findViewById(R.id.etxtOnHour);
        etxtOnMin =(AutoFitEditText)findViewById(R.id.etxtOnMin);

        initAutoFitEditText(etxtOffHour);
        initAutoFitEditText(etxtOffMin);
        initAutoFitEditText(etxtOnHour);
        initAutoFitEditText(etxtOnMin);
    }
    private void loadButton()
    {
        ok_btn = (Button) findViewById(R.id.btn_save);
        cancel_btn = (Button) findViewById(R.id.btn_cancel);
        ok_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffHourPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffHourMinus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffMinPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffMinMinus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnHourPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnHourMinus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnMinPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnMinMinus)).setOnClickListener(this);
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
        case R.id.btn_save:

            showToastMessage("Ok Button Clicked");
            saveFileFunction();
            updateWidgetValues();
            this.finish();
            break;

        case R.id.btn_cancel:

            showToastMessage("Cancel Button Clicked");
            this.finish();
            break;


        case R.id.btnOffHourPlus:
            //Log.e("TAG", "Value is " + widgetValues.getOffMin().toString() + " :: " + widgetValues.getOffHour().toString());

            controlEditText(R.id.btnOffHourPlus, widgetValues.getOffHour().toString());
            break;

        case R.id.btnOffHourMinus:
            controlEditText(R.id.btnOffHourMinus,widgetValues.getOffHour().toString());
            break;
        case R.id.btnOffMinPlus:
            controlEditText(R.id.btnOffMinPlus,widgetValues.getOffMin().toString());
            break;
        case R.id.btnOffMinMinus:
            controlEditText(R.id.btnOffMinMinus,widgetValues.getOffMin().toString());
            break;
        case R.id.btnOnHourPlus:
            controlEditText(R.id.btnOnHourPlus,widgetValues.getOnHour().toString());
            break;
        case R.id.btnOnHourMinus:
            controlEditText(R.id.btnOnHourMinus,widgetValues.getOnHour().toString());
            break;
        case R.id.btnOnMinPlus:
            controlEditText(R.id.btnOnMinPlus,widgetValues.getOnMin().toString());
            break;
        case R.id.btnOnMinMinus:
            controlEditText(R.id.btnOnMinMinus,widgetValues.getOnMin().toString());
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


    public void reaadingSharedValues()
    {

        sharedpreferences = getApplicationContext().getSharedPreferences(MainWidget.CheetatechPref, Context.MODE_PRIVATE);
        int offHour = 0,offMin = 0,onHour = 0,onMin = 0;
        offHour = sharedpreferences.getInt(MainWidget.OffHour, 0);
        offMin = sharedpreferences.getInt(MainWidget.OffMin, 0);
        onHour = sharedpreferences.getInt(MainWidget.OnHour, 0);
        onMin = sharedpreferences.getInt(MainWidget.OnMin, 0);

        widgetValues = new WidgetValues(offHour,offMin,onHour,onMin);

        Log.e("TAG", "Veriler " + offHour + " : " + offMin + " : " + onHour + " : " + onMin);

    }

    private void saveFileFunction()
    {
        int offHour = widgetValues.getIntOffHour();
        int offMin = widgetValues.getIntOffMin();
        int onHour = widgetValues.getIntOnHour();
        int onMin = widgetValues.getIntOnMin();
        sharedpreferences = getApplicationContext().getSharedPreferences(MainWidget.CheetatechPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(MainWidget.OffHour,offHour);
        editor.putInt(MainWidget.OffMin,offMin);
        editor.putInt(MainWidget.OnHour,onHour);
        editor.putInt(MainWidget.OnMin,onMin);
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
        if(binding==null)
            return;
        if(widgetValues==null)
            return;
        binding.setWidgetValue(widgetValues);
    }

    private void updateWidgetValues()
    {
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), MainWidget.class));
        MainWidget myWidget = new MainWidget();
        myWidget.onUpdate(this, AppWidgetManager.getInstance(this),ids);
        /*
        Context context = this;
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance
                (context);

        ComponentName thisAppWidgetComponentName =
                new ComponentName(context.getPackageName(),getClass().getName()
                );
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                thisAppWidgetComponentName);

        for (int appWidgetID : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.main_widget);

            remoteViews.setTextViewText(R.id.on_text, MainWidget.getMessage(widgetValues.getIntOnHour(), widgetValues.getIntOnMin()));
            remoteViews.setTextViewText(R.id.off_text, MainWidget.getMessage(widgetValues.getIntOffHour(), widgetValues.getIntOffMin()));
            remoteViews.setOnClickPendingIntent(R.id.start_stop_button, getPendingSelfIntent(context, MainWidget.ACTION_STARTSTOP_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.on_off_button,getPendingSelfIntent(context,MainWidget.ACTION_ONOFF_CLICK));
            appWidgetManager.updateAppWidget(appWidgetID, remoteViews);
        }
        */
    }
    private PendingIntent getPendingSelfIntent(Context context, String action) {
        // An explicit intent directed at the current class (the "self").
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
