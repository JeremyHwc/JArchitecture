package com.jeremy.jarch;

import android.app.Application;
import android.content.Context;

public class EnApplication extends Application {
    private static Context sContext;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context get() {
        return sContext;
    }
}
