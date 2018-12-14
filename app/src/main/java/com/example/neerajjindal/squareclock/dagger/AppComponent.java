package com.example.neerajjindal.myapplication.dagger;

import com.example.neerajjindal.myapplication.app.SquareClockApplication;
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
