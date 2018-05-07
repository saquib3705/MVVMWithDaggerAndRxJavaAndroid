package com.saquib.mvvmwithrxjavademo;

import android.app.Application;
import android.content.Context;

import com.saquib.mvvmwithrxjavademo.di.AppComponent;
import com.saquib.mvvmwithrxjavademo.di.AppModule;
import com.saquib.mvvmwithrxjavademo.di.DaggerAppComponent;
import com.saquib.mvvmwithrxjavademo.di.UtilsModule;


/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
