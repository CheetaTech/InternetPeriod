package com.mobiledatatimerwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.telephony.TelephonyManager;
import android.util.Log;


import java.io.File;



public class Util {
    public static final boolean TEST = false;
    public static int INSERT = 2;
    public static int UPDATE = 1;

    public static Typeface typeFace;
    public static final int REQUEST_READ_PHONE_STATE = 0;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    public static final int REQUEST_INTERNET = 2;

    //public static String diary_fullscreen="ca-app-pub-3189216220506080/8898459054";

    public static Typeface getKalam(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "fonts/Kalam-Bold.ttf");
    }

    public static Typeface getOctin(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "fonts/octin spraypaint a rg.ttf");
    }

    public static void createAppFolder(String path, String folderName) {
        File file = new File(path + "/" + folderName);
        if (!file.exists()) {
            if (file.mkdir()) {
                Log.i("Util", folderName + " folder created at " + path);
            }
        }
    }

    public static String getPhoneId(Activity activity) {
        String ret = "";
        final TelephonyManager tm = (TelephonyManager) activity.getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId();
        return deviceid;
    }

   /* public static AdRequest getAdRequest(Activity activity) {
        boolean test = true;
        if (true) {

            return new AdRequest.Builder().addTestDevice(getPhoneId(activity)).build();
        } else {
            return new AdRequest.Builder().build();
        }
    }*/


}
