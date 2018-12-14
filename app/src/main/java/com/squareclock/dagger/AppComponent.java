package com.squareclock.dagger;

import com.squareclock.app.SquareClockApplication;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        UIModule.class,
        ViewModelModule.class,
        ApiModule.class,
        AndroidInjectionModule.class
})
public interface AppComponent extends AndroidInjector<SquareClockApplication> {
}
