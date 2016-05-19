package layout;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.mobiledatatimerwidget.MainActivity;
import com.mobiledatatimerwidget.R;

/**
 * Implementation of App Widget functionality.
 */
public class MainWidget extends AppWidgetProvider {


    private static final String ACTION_UPDATE_CLICK = "com.mobiledatatimerwidget.action.MINUS_CLICK";


    private static int mCount = 0;

    private static String getMessage() {
        return String.valueOf(mCount++);
    }

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

        // Loop for every App Widget instance that belongs to this provider.
        // Noting, that is, a user might have multiple instances of the same
        // widget on
        // their home screen.
        for (int appWidgetID : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.main_widget);

            remoteViews.setTextViewText(R.id.on_text, message);
            remoteViews.setOnClickPendingIntent(R.id.minus_button,
                    getPendingSelfIntent(context,
                            ACTION_UPDATE_CLICK)
            );

            appWidgetManager.updateAppWidget(appWidgetID, remoteViews);

        }
    }

    /**
     * A general technique for calling the onUpdate method,
     * requiring only the context parameter.
     *
     * @author John Bentley, based on Android-er code.
     * @see <a href="http://android-er.blogspot.com
     * .au/2010/10/update-widget-in-onreceive-method.html">
     * Android-er > 2010-10-19 > Update Widget in onReceive() method</a>
     */
    private void onUpdate(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance
                (context);

        // Uses getClass().getName() rather than MyWidget.class.getName() for
        // portability into any App Widget Provider Class
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
        if (ACTION_UPDATE_CLICK.equals(intent.getAction())) {
            onUpdate(context);
            Log.e("TAG","Button Clicked");
        }
    }

}


