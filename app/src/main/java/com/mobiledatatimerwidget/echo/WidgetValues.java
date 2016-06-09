package com.mobiledatatimerwidget.echo;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.mobiledatatimerwidget.bindablevalues.BindableString;

import it.cosenonjaviste.databinding.util.TextWatcherAdapter;

//android:background="#FFB300" Sarı renk
public class WidgetValues{
    public BindableString offHour = new BindableString();
    public BindableString offMin = new BindableString();
    public BindableString offSec = new BindableString();
    public BindableString onHour = new BindableString();
    public BindableString onMin = new BindableString();
    public BindableString onSec = new BindableString();
    private onBinding onBinding = null;

    public WidgetValues()
    {

    }
    public WidgetValues(String offHour,String offMin,String offSec,String onHour,String onMin,String onSec)
    {
        this.offHour.set(offHour);
        this.offMin.set(offMin);
        this.offSec.set(offSec);
        this.onHour.set(onHour);
        this.onMin.set(onMin);
        this.onSec.set(onSec);
    }
    public WidgetValues(String offHour,String offMin,String onHour,String onMin)
    {
        this.offHour.set(offHour);
        this.offMin.set(offMin);
        this.onHour.set(onHour);
        this.onMin.set(onMin);
    }
    public WidgetValues(int offHour,int offMin,int onHour,int onMin)
    {
        this.offHour.set(String.valueOf(offHour));
        this.offMin.set(String.valueOf(offMin));
        this.onHour.set(String.valueOf(onHour));
        this.onMin.set(String.valueOf(onMin));
    }

    public void setListener(onBinding binding)
    {
        this.onBinding = binding;
    }

    public void setOffHour(String offHour)
    {
        this.offHour.set(offHour);
    }
    public void setOffMin(String offMin)
    {
        this.offMin.set(offMin);
    }
    public void setOffSec(String offSec)
    {
        this.offSec.set(offSec);
    }

    public void setOnHour(String onHour)
    {
        this.onHour.set(onHour);
    }
    public void setOnMin(String onMin)
    {
        this.onMin.set(onMin);
    }
    public void setOnSec(String onSec)
    {
        this.onSec.set(onSec);
    }

//
    public void setOffHour(int offHour)
    {
        this.offHour.set(String.valueOf(offHour));
    }
    public void setOffMin(int offMin)
    {
        this.offMin.set(String.valueOf(offMin));
    }
    public void setOffSec(int offSec)
    {
        this.offSec.set(String.valueOf(offSec));
    }
    public void setOnHour(int onHour)
    {
        this.onHour.set(String.valueOf(onHour));
    }
    public void setOnMin(int onMin)
    {
        this.onMin.set(String.valueOf(onMin));
    }
    public void setOnSec(int onSec)
    {
        this.onSec.set(String.valueOf(onSec));
    }
//
    public String getOffHour()
    {
        return this.offHour.get();
    }

    public String getOffMin()
    {
        return this.offMin.get();
    }
    public String getOffSec()
    {
        return this.offSec.get();
    }
    public String getOnHour()
    {
        return this.onHour.get();
    }
    public String getOnMin()
    {
        return this.onMin.get();
    }


    public String getOnSec()
    {
        return this.onSec.get();
    }


    public int getIntOffHour()
    {
        return Integer.parseInt(this.offHour.get());
    }
    public int getIntOffMin()
    {
        return Integer.parseInt(this.offMin.get());
    }
    public int getIntOnHour()
    {
        return Integer.parseInt(this.onHour.get());
    }
    public int getIntOnMin()
    {
        return Integer.parseInt(this.onMin.get());
    }


    private String controlEditValue(String val,int border)
    {
        int number ;
        try {
            number = Integer.parseInt(val);
        }catch (NumberFormatException e)
        {
            number = 1;
        }
        return (number>border) ? String.valueOf(border) : String.valueOf(number);
    }


    public TextWatcher watcher = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            Log.e("TAG","In Watcher....");
            offHour.set(s.toString());
            /*if (!Objects.equals(onHour.get(), s.toString())) {
                offHour.set(s.toString());
            }
            */
        }


    };
    public TextWatcher watcherOffHour = new TextWatcherAdapter() {

        @Override public void afterTextChanged(Editable s) {
            Log.e("TAG", "In Watcher...." + s.toString());
            offHour.set(controlEditValue(s.toString(),23));
            //offHour.set(s.toString());
            //onBinding.onBindingAccept();
        }

        /*
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.e("TAG", "In Watcher...." + s.toString());
            offHour.set(controlEditValue(s.toString(), 23));
            onBinding.onBindingAccept();
        }*/

    };
    public TextWatcher watcherOffMin = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            //offMin.set(s.toString());
            offMin.set(controlEditValue(s.toString(), 59));
        }
    };
    public TextWatcher watcherOffSec = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            offSec.set(controlEditValue(s.toString(), 59));
            //offSec.set(s.toString());
        }
    };
    public TextWatcher watcherOnHour = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            onHour.set(controlEditValue(s.toString(), 23));
            //onHour.set(s.toString());
        }
    };
    public TextWatcher watcherOnMin = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            //onMin.set(s.toString());
            onMin.set(controlEditValue(s.toString(), 59));
        }
    };
    public TextWatcher watcherOnSec = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            //onSec.set(s.toString());
            onSec.set(controlEditValue(s.toString(), 59));
        }
    };


}
