package com.squareclock.dagger;

import com.squareclock.activity.SquareClockActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UIModule {

    @ContributesAndroidInjector
    abstract SquareClockActivity provideSquareClockActivity();
}
