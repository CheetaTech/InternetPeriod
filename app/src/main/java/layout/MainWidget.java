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
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.mobiledatatimerwidget.MainActivity;
import com.mobiledatatimerwidget.R;

/**
 * Implementation of App Widget functionality.
 */
public class MainWidget extends AppWidgetProvider {




    private static final String ACTION_MINUS_CLICK = "com.mobiledatatimerwidget.action.MINUS_CLICK";
    private static final String ACTION_PLUS_CLICK = "com.mobiledatatimerwidget.action.PLUS_CLICK";
    private static final String ACTION_STARTSTOP_CLICK = "com.mobiledatatimerwidget.action.STARTSTOP_CLICK";
    private static final String ACTION_ONOFF_CLICK = "com.mobiledatatimerwidget.action.ONOFF_CLICK";


    private static int mCount = 0;
    private  static  int val = 0;

    private static String getMessage() {
        return String.valueOf(mCount++);
    }



    public static final String CheetatechPref = "Chetatech" ;
    public static final String TimeState = "TimeState1";
    public static final String WorkState = "WorkState1";
    public static final String OnTime = "OnTime1";
    public static final String OffTime = "OffTime1";

    SharedPreferences sharedpreferences;


    private static int timeState = 0; // off
    private static int workState = 0; // stop
    private static int onTime = 1; // default
    private static int offTime = 1; // default

    private static int onTimeHold =1;
    private static int offTimeHold =1;

    int vl = 1;

    private PendingIntent getPendingSelfIntent(Context context, String action) {
        // An explicit intent directed at the current class (the "self").
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        String message = getMessage();



        sharedpreferences = context.getSharedPreferences(CheetatechPref, Context.MODE_PRIVATE);
        sharedpreferences.getInt(OffTime, offTime);
        sharedpreferences.getInt(OnTime, onTime);
        sharedpreferences.getInt(WorkState, workState);
        sharedpreferences.getInt(TimeState, timeState);


        Log.e("TAG","1 : "+ offTime );
        Log.e("TAG","2 : " + onTime);
        Log.e("TAG","3 : "+  workState);
        Log.e("TAG", "4 : " + timeState);

        for (int appWidgetID : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.main_widget);


            Log.e("Deger", "" + onTimeHold + " :: " + offTimeHold);
            remoteViews.setTextViewText(R.id.on_text, String.valueOf(onTimeHold));
            remoteViews.setTextViewText(R.id.off_text, String.valueOf(offTimeHold));





            remoteViews.setOnClickPendingIntent(R.id.minus_button, getPendingSelfIntent(context, ACTION_MINUS_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.plus_button, getPendingSelfIntent(context, ACTION_PLUS_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.on_off_button,getPendingSelfIntent(context,ACTION_ONOFF_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.start_stop_button,getPendingSelfIntent(context,ACTION_STARTSTOP_CLICK));


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
        Log.e("TAG", "Button Clicked");
        if (ACTION_MINUS_CLICK.equals(intent.getAction())) {
            decriment();
            onUpdate(context);
            Log.e("TAG", "Button Minus Clicked");

        }

        if (ACTION_PLUS_CLICK.equals(intent.getAction())) {
            incremant();
            onUpdate(context);
            Log.e("TAG","Button Plus Clicked");

        }

        if (ACTION_ONOFF_CLICK.equals(intent.getAction())) {

            Log.e("TAG", "Button OnOff Clicked");

            sharedpreferences = context.getSharedPreferences(CheetatechPref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            timeState = (++timeState)%2;
            Log.e("TAG", "timestateChanged : " + timeState);
            editor.putInt(TimeState, timeState); // ya sifir ya bir olacak
            editor.commit();
            changeButtonText(context);

            onUpdate(context);

        }

        if (ACTION_STARTSTOP_CLICK.equals(intent.getAction())) {

            Log.e("TAG", "Button StartStop Clicked");
            //sharedpreferences = context.getApplicationContext().getSharedPreferences(CheetatechPref,Context.MODE_PRIVATE);

            sharedpreferences = context.getSharedPreferences(CheetatechPref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            workState = (++workState)%2;
            Log.e("TAG", "workstateChanged : " + workState);
            editor.putInt(WorkState, workState); // ya sifir ya bir olacak
            editor.commit();


            Log.e("TAG","x1 : "+  sharedpreferences.getInt(OffTime, offTimeHold));
            Log.e("TAG","x2 : " + sharedpreferences.getInt(OnTime, onTimeHold));
            Log.e("TAG","x3 : "+  sharedpreferences.getInt(WorkState, workState));
            Log.e("TAG","x4 : "+  sharedpreferences.getInt(TimeState, timeState));

            onUpdate(context);
        }
    }

    private void incremant() {

        if(timeState == 0) // kapali kalma süresi
        {
            if(++offTimeHold>60)
                offTimeHold = 60;
        }
        if(timeState == 1) // acik kalma süresi
        {
            if(++onTimeHold>60)
                onTimeHold = 60;
        }
    }

    private void decriment() {


        //FFB300
        // 263238
        if(timeState == 0) // kapali kalma süresi
        {
            if(--offTimeHold<1)
                offTimeHold = 1;
        }
        if(timeState == 1) // acik kalma süresi
        {
            if(--onTimeHold<1)
                onTimeHold = 1;
        }
    }

    private void changeButtonText(Context context) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance
                (context);

        // Uses getClass().getName() rather than MyWidget.class.getName() for
        // portability into any App Widget Provider Class
        ComponentName thisAppWidgetComponentName =
                new ComponentName(context.getPackageName(),getClass().getName()
                );
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                thisAppWidgetComponentName);
        // Loop for every App Widget instance that belongs to this provider.
        // Noting, that is, a user might have multiple instances of the same
        // widget on
        // their home screen.
        for (int appWidgetID : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.main_widget);
            String[] nstr = new String[]{"OFF","ON"};
            //remoteViews.setTextViewText(R.id.on_off_button, nstr[timeState]);

            remoteViews.setOnClickPendingIntent(R.id.start_stop_button, getPendingSelfIntent(context, ACTION_STARTSTOP_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.minus_button, getPendingSelfIntent(context, ACTION_MINUS_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.plus_button,getPendingSelfIntent(context,ACTION_PLUS_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.on_off_button,getPendingSelfIntent(context,ACTION_ONOFF_CLICK));
            appWidgetManager.updateAppWidget(appWidgetID, remoteViews);

        }
    }

}


