package com.example.neerajjindal.myapplication.dagger;

import com.example.neerajjindal.myapplication.activity.SquareClockActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UIModule {

    @ContributesAndroidInjector
    abstract SquareClockActivity provideSquareClockActivity();
}
