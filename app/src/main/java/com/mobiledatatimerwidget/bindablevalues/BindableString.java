package com.mobiledatatimerwidget.bindablevalues;

import android.databinding.BaseObservable;
import android.os.Build;

import java.util.Objects;
public class BindableString extends BaseObservable {
    String value;

    public String get() {
        return value != null ? value : "1";
    }

    public void set(String value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!Objects.equals(this.value, value)) {
                this.value = value;
                notifyChange();
            }
        }
    }
    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}