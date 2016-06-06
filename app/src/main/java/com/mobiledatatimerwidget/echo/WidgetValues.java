package com.mobiledatatimerwidget.echo;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.mobiledatatimerwidget.bindablevalues.BindableString;

import it.cosenonjaviste.databinding.util.TextWatcherAdapter;

//android:background="#FFB300" Sarı renk
public class WidgetValues {
    public BindableString offHour = new BindableString();
    public BindableString offMin = new BindableString();
    public BindableString offSec = new BindableString();
    public BindableString onHour = new BindableString();
    public BindableString onMin = new BindableString();
    public BindableString onSec = new BindableString();

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
            offHour.set(s.toString());
        }
    };
    public TextWatcher watcherOffMin = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            offMin.set(s.toString());
        }
    };
    public TextWatcher watcherOffSec = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            offSec.set(s.toString());
        }
    };
    public TextWatcher watcherOnHour = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            onHour.set(s.toString());
        }
    };
    public TextWatcher watcherOnMin = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            onMin.set(s.toString());
        }
    };
    public TextWatcher watcherOnSec = new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
            onSec.set(s.toString());
        }
    };
}
