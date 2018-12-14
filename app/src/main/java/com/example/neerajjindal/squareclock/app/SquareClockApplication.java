package com.example.neerajjindal.myapplication.app;

import android.app.Activity;
import android.app.Application;
import com.example.neerajjindal.myapplication.dagger.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import javax.inject.Inject;

public class SquareClockApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> hasActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return hasActivityInjector;
    }
}
