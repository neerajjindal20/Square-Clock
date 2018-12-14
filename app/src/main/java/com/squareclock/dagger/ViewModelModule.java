package com.squareclock.dagger;

import com.squareclock.domain.service.DateTimeAPI;
import com.squareclock.domain.viewmodel.DateTimeViewModel;
import com.squareclock.domain.viewmodel.impl.DateTimeViewModelImpl;
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
