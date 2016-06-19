package com.mobiledatatimerwidget.hardwareClasses;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mobiledatatimerwidget.prefferences.SPreferences;

import layout.MainWidget;


public class MobileDataReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        MobileDataClass mobileData = new MobileDataClass(context);
        MobileDataController mobileDataController = new MobileDataController(context);
        SPreferences pref = new SPreferences(context);
        if(mobileData.state == false){
            //turn on wifi and set alarm manager for running time
            mobileDataController.turnOn();
            int onTimeMin = MainWidget.calculateMin(pref.getInteger(MainWidget.OnHour), pref.getInteger(MainWidget.OnMin));
            mobileData.updateAlarm(MainWidget.onTimeMin, context);
            Log.e("TAG","mobile data aciliyor.mobiledatarunningtime : "+ onTimeMin);
        }else{
            mobileDataController.turnOff();
            int offTimeMin = MainWidget.calculateMin(pref.getInteger(MainWidget.OffHour),pref.getInteger(MainWidget.OffMin));
            mobileData.updateAlarm(offTimeMin, context);
            Log.e("TAG","mobile data kapatılıyor.mobiledatashutdowntime : " + offTimeMin);
        }
    }
}
