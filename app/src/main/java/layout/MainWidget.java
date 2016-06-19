package layout;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import com.mobiledatatimerwidget.DialogActivity;
import com.mobiledatatimerwidget.ErrorActivity;
import com.mobiledatatimerwidget.R;
import com.mobiledatatimerwidget.SPreferences;
import com.mobiledatatimerwidget.hardwareClasses.MobileDataClass;
import com.mobiledatatimerwidget.hardwareClasses.MobileDataController;

/**
 * Implementation of App Widget functionality.
 */
public class MainWidget extends AppWidgetProvider {




    private static final String ACTION_MINUS_CLICK = "com.mobiledatatimerwidget.action.MINUS_CLICK";
    private static final String ACTION_PLUS_CLICK = "com.mobiledatatimerwidget.action.PLUS_CLICK";
    public static final String ACTION_STARTSTOP_CLICK = "com.mobiledatatimerwidget.action.STARTSTOP_CLICK";
    public static final String ACTION_ONOFF_CLICK = "com.mobiledatatimerwidget.action.ONOFF_CLICK";


    private static int mCount = 0;
    private  static  int val = 0;




    public static final String CheetatechPref = "Chetatech" ;
    public static final String TimeState = "TimeState1";
    public static final String WorkState = "WorkState1";
    public static final String OnTime = "OnTime1";
    public static final String OffTime = "OffTime1";
    public static final String OffHour = "OffHour";
    public static final String OffMin = "OffMin";

    public static final String OnHour = "OnHour";
    public static final String OnMin = "OnMin";

    SharedPreferences sharedpreferences;


    private static int timeState = 0; // off
    private static int workState = 0; // stop
    private static int onTime = 1; // default
    private static int offTime = 1; // default
    private static int onHour = 1; // default
    private static int offHour = 1; // default
    private static int onMin = 1; // default
    private static int offMin = 1; // default


    private static String offString = "";
    private static String onString = "";

    private static int onTimeHold =1;
    private static int offTimeHold =1;

    private Context context = null;
    private static int ac = 0;
    int vl = 1;
    public static int offTimeMin = 1;
    public static int onTimeMin = 1;


    public static String getMessage(int hour,int min)
    {
        return new String(hour+" h : "+min + " m");
    }
    private PendingIntent getPendingSelfIntent(Context context, String action) {
        // An explicit intent directed at the current class (the "self").
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;




    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        this.context = context;

        //ask(context);
        //insertDummyContactWrapper();



        sharedpreferences = context.getSharedPreferences(CheetatechPref, Context.MODE_PRIVATE);
        sharedpreferences.getInt(OffTime, offTime);
        sharedpreferences.getInt(OnTime, onTime);


        offHour = sharedpreferences.getInt(OffHour, 0);
        offMin = sharedpreferences.getInt(OffMin, 0);
        onHour = sharedpreferences.getInt(OnHour, 0);
        onMin = sharedpreferences.getInt(OnMin, 0);



        sharedpreferences.getInt(WorkState, workState);
        sharedpreferences.getInt(TimeState, timeState);


        Log.e("TAG","1 : "+ offTime );
        Log.e("TAG","2 : " + onTime);
        Log.e("TAG", "3 : " + workState);
        Log.e("TAG", "4 : " + timeState);
        Log.e("TAG","1_1 : "+ offHour );
        Log.e("TAG","2_2 : " + offMin);
        Log.e("TAG","3_3 : "+  onHour);
        Log.e("TAG", "4_4 : " + onMin);



        for (int appWidgetID : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.main_widget);


            Log.e("Deger", "" + onTimeHold + " :: " + offTimeHold);
            remoteViews.setTextViewText(R.id.on_text, getMessage(onHour,onMin));
            remoteViews.setTextViewText(R.id.off_text, getMessage(offHour,offMin));

            //remoteViews.setTextViewText(R.id.on_text, String.valueOf(onTimeHold));
            //remoteViews.setTextViewText(R.id.off_text, String.valueOf(offTimeHold));



            //if(ac = 1)
            /*
            {
                Log.e("TAG","AC = 1");
                Intent launchActivity = new Intent(context,MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,0, launchActivity, 0);
                remoteViews.setOnClickPendingIntent(R.id.minus_button, getPendingSelfIntent(context, MainActivity.EXTRA_DEFAULT_FRAGMENT));
                ac = 0;
            }
            */

            /*
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.minus_button, pendingIntent);
            */


            if((Build.VERSION.SDK_INT > 19) )
            {
                Intent i = new Intent(context, ErrorActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
                remoteViews.setOnClickPendingIntent(R.id.minus_button, pendingIntent);
            }else {
                Intent i2 = new Intent(context, DialogActivity.class);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, i2, 0);
                remoteViews.setOnClickPendingIntent(R.id.minus_button, pendingIntent2);
            }

            /*
            Intent i = new Intent(context, DialogActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
            remoteViews.setOnClickPendingIntent(R.id.minus_button, pendingIntent);
            */




            //remoteViews.setOnClickPendingIntent(R.id.minus_button, getPendingSelfIntent(context, ACTION_MINUS_CLICK));
            //remoteViews.setOnClickPendingIntent(R.id.plus_button, getPendingSelfIntent(context, ACTION_PLUS_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.on_off_button,getPendingSelfIntent(context,ACTION_ONOFF_CLICK));

            if((Build.VERSION.SDK_INT > 19) )
            {
                Intent i2 = new Intent(context, ErrorActivity.class);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, i2, 0);
                remoteViews.setOnClickPendingIntent(R.id.start_stop_button, pendingIntent2);
            }else {
                remoteViews.setOnClickPendingIntent(R.id.start_stop_button,getPendingSelfIntent(context,ACTION_STARTSTOP_CLICK));
            }



            Drawable startDrawable = context.getResources().getDrawable(R.drawable.ic_startbutton_96_96_white);
            Drawable stopDrawable = context.getResources().getDrawable(R.drawable.ic_stop_button_9696_white);
            Bitmap startBitmap = ((BitmapDrawable)startDrawable).getBitmap();
            Bitmap stopBitmap = ((BitmapDrawable)stopDrawable).getBitmap();

            Bitmap[] bitmaps = new Bitmap[]{startBitmap,stopBitmap};
            //workState = (workState+1) %2;
            remoteViews.setImageViewBitmap(R.id.start_stop_button, bitmaps[workState]);
            appWidgetManager.updateAppWidget(appWidgetID, remoteViews);
        }
    }



    private void onUpdate(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance
                (context);

        ComponentName thisAppWidgetComponentName =
                new ComponentName(context.getPackageName(),getClass().getName()
                );
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                thisAppWidgetComponentName);
        onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_ONOFF_CLICK.equals(intent.getAction())) {
            sharedpreferences = context.getSharedPreferences(CheetatechPref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            timeState = (++timeState)%2;
            Log.e("TAG", "timestateChanged : " + timeState);
            editor.putInt(TimeState, timeState); // ya sifir ya bir olacak
            editor.commit();
            //changeButtonText(context);
            onUpdate(context);
        }

        if (ACTION_STARTSTOP_CLICK.equals(intent.getAction())) {


            Log.e("TAG", "Button StartStop Clicked");
            sharedpreferences = context.getSharedPreferences(CheetatechPref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            workState = (++workState)%2;
            Log.e("TAG", "workstateChanged : " + workState);
            editor.putInt(WorkState, workState); // ya sifir ya bir olacak
            editor.commit();
            onUpdate(context);

            if(workState == 1) // work
            {
                startSystem();
                Log.e("STARTTIME","Starttime is working...");
            }else{
                stopSystem();
                Log.e("STOPTIME", "Stop is working...");
            }

        }
    }

    private void startSystem() {
        SPreferences pref = new SPreferences(this.context);
        offTimeMin = calculateMin(pref.getInteger(OffHour),pref.getInteger(OffMin));
        onTimeMin = calculateMin(pref.getInteger(OnHour),pref.getInteger(OnMin));
        Log.e("TAG","Offtime "+offTimeMin + " OnTimeMin "+ onTimeMin );
        MobileDataClass mobileDataClass = new MobileDataClass(this.context);
        MobileDataController mobileDataController = new MobileDataController(context);
        if(mobileDataController.getCurrentState() == false)
            mobileDataController.turnOn();
        mobileDataClass.setAlarm(onTimeMin,this.context); // calismasini baslattik. Oncelik acik kalma süresi.
    }

    public static int calculateMin(int hour,int min)
    {
        Log.e("TAG","Hour " + hour + " min "+ min );
        if(hour==-1) hour = 0;
        if(min == -1 ) min = 1;
        return ((60*hour) + min);
    }
    private int calculateOffTime(SPreferences pref)
    {
        int hour = pref.getInteger(OffHour);
        int min = pref.getInteger(OffMin);
        if(hour==-1) hour = 0;
        if(min == -1 ) min = 1;
        return ((60*hour) + min);
    }
    private int calculateOnTime(SPreferences pref)
    {
        int hour = pref.getInteger(OnHour);
        int min = pref.getInteger(OnMin);
        if(hour==-1) hour = 0;
        if(min == -1 ) min = 1;
        return ((60*hour) + min);
    }
    private void stopSystem() {
        MobileDataClass mobileDataClass = new MobileDataClass(this.context);
        mobileDataClass.destroyAlarm(this.context);
    }
    private void changeButtonText(Context context) {

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
            remoteViews.setOnClickPendingIntent(R.id.start_stop_button, getPendingSelfIntent(context, ACTION_STARTSTOP_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.on_off_button,getPendingSelfIntent(context,ACTION_ONOFF_CLICK));
            appWidgetManager.updateAppWidget(appWidgetID, remoteViews);
        }
    }

}


