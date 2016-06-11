package com.mobiledatatimerwidget.hardwareClasses;

/**
 * Created by CCelik on 30-04-2016.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import java.lang.reflect.Method;

//TODO: Report exceptions so the developer can fix them
public class MobileDataController
{
    private static MobileDataController instance;
    private ConnectivityManager connectivityManager;
    private Method setMobileDataEnabledMethod;
    private Method getMobileDataEnabled;

    public MobileDataController(Context context)
    {
        try
        {
            this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Class connectivityManagerClass = Class.forName(this.connectivityManager.getClass().getName());

            this.getMobileDataEnabled = connectivityManagerClass.getDeclaredMethod("getMobileDataEnabled");
            this.getMobileDataEnabled.setAccessible(true);
            this.setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            this.setMobileDataEnabledMethod.setAccessible(true);
        }
        catch(Exception ex)
        {
            //Pokemon exception handling: gotta catch 'em all
        }
    }

    public boolean toggle()
    {
        boolean currentState = this.getCurrentState();
        try
        {
            this.setMobileDataEnabledMethod.invoke(this.connectivityManager, !currentState);
            currentState = !currentState;
        }
        catch(Exception ex)
        {}

        return currentState;
    }

    public boolean getCurrentState()
    {
        boolean state = false;
        try
        {
            state = (Boolean)this.getMobileDataEnabled.invoke(this.connectivityManager);
        }
        catch(Exception ex)
        {}

        return state;
    }

    public static MobileDataController getInstance(Context context)
    {
        if(MobileDataController.instance == null)
        {
            MobileDataController.instance = new MobileDataController(context);
        }

        return MobileDataController.instance;
    }
}