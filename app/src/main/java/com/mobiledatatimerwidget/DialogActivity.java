package com.mobiledatatimerwidget;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
    WidgetValues widgetValues = null;
    public int val = 5;
    FragmentSoftwareDialogBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.fragment_software_dialog);
        binding = DataBindingUtil.setContentView(this,R.layout.fragment_software_dialog);
        widgetValues  = new WidgetValues("01","02","03","04","05","06");
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
            case R.id.btnOffSecPlus:
                value++;
                return (value>59)?59:value;
            case R.id.btnOffSecMinus:
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
            case R.id.btnOnSecPlus:
                value++;
                return (value>59)?59:value;
            case R.id.btnOnSecMinus:
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
            case R.id.btnOffSecPlus:
                widgetValues.setOffSec(checkValue(R.id.btnOffSecPlus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOffSecMinus:
                widgetValues.setOffSec(checkValue(R.id.btnOffSecMinus, value));
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
            case R.id.btnOnSecPlus:
                widgetValues.setOnSec(checkValue(R.id.btnOnSecPlus, value));
                binding.setWidgetValue(widgetValues);
                break;
            case R.id.btnOnSecMinus:
                widgetValues.setOnSec(checkValue(R.id.btnOnSecMinus, value));
                binding.setWidgetValue(widgetValues);
                break;
        }



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
        ((ImageButton)findViewById(R.id.btnOffHourPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffHourMinus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffMinPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffMinMinus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffSecPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOffSecMinus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnHourPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnHourMinus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnMinPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnMinMinus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnSecPlus)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btnOnSecMinus)).setOnClickListener(this);
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
        case R.id.btnOffSecPlus:
            controlEditText(R.id.btnOffSecPlus,widgetValues.getOffSec().toString());
            break;
        case R.id.btnOffSecMinus:
            controlEditText(R.id.btnOffSecMinus, widgetValues.getOffSec().toString());
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
        case R.id.btnOnSecPlus:
            controlEditText(R.id.btnOnSecPlus,widgetValues.getOnSec().toString());
            break;
        case R.id.btnOnSecMinus:
            controlEditText(R.id.btnOnSecMinus,widgetValues.getOnSec().toString());
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
