package com.mobiledatatimerwidget.hardwareClasses;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import layout.MainWidget;


public class MobileDataClass {
    public int runningTime = 0;
    public int shutDownTime = 0;
    public boolean state = false; //False : shuttedDown, True : Rnning
    Context context;

    public MobileDataClass(Context context){
        this.context = context;
        MobileDataController controller = new MobileDataController(context);
        //SPreferences preferences = new SPreferences(context);
        runningTime = MainWidget.onTimeMin; //preferences.getInteger("MobileDataRunningTime");
        shutDownTime = MainWidget.offTimeMin; //preferences.getInteger("MobileDataShutDownTime");
        state = controller.getCurrentState();
    }

    public void setAlarm(int time,Context context){
        Intent in = new Intent(context,MobileDataReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,13,in,0);                //Request code 13, bununla tanıyoruz
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+time*1000*60,pi);

    }
    public void updateAlarm(int time,Context context){
        Intent in = new Intent(context,MobileDataReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,13,in,PendingIntent.FLAG_UPDATE_CURRENT);                //Request code 13, bununla tanıyoruz
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time * 1000 * 60, pi);
    }

    public void destroyAlarm (Context context){
        Intent in = new Intent(context,MobileDataReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,13,in,PendingIntent.FLAG_CANCEL_CURRENT);                //Request code 12 ,bununla tanıyoruz
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }



}
