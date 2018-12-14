package com.example.neerajjindal.myapplication.dagger;

import com.example.neerajjindal.myapplication.domain.service.DateTimeAPI;
import com.example.neerajjindal.myapplication.domain.viewmodel.DateTimeViewModel;
import com.example.neerajjindal.myapplication.domain.viewmodel.impl.DateTimeViewModelImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ViewModelModule {

    @Provides
    @Singleton
    DateTimeViewModel provideDateTimeViewModel(DateTimeAPI dateTimeAPI) {
        return new DateTimeViewModelImpl(dateTimeAPI);
    }
}
