package com.mobiledatatimerwidget.hardwareClasses;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class MobileDataReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        MobileDataClass mobileData = new MobileDataClass(context);
        MobileDataController mobileDataController = new MobileDataController(context);

        if(mobileData.state == false){
            //turn on wifi and set alarm manager for running time
            mobileDataController.toggle();
            mobileData.updateAlarm(mobileData.runningTime, context);
            Log.e("TAGG","mobile data aciliyor.mobiledatarunningtime : "+ mobileData.runningTime);
        }else{
            mobileDataController.toggle();
            mobileData.updateAlarm(mobileData.shutDownTime, context);
            Log.e("TAGG","mobile data kapatılıyor.mobiledatashutdowntime : " + mobileData.shutDownTime);
        }
    }
}
