package com.squareclock.dagger;

import com.squareclock.domain.retrofit.RetrofitBuilder;
import com.squareclock.domain.service.DateTimeAPI;
import com.squareclock.domain.service.impl.DateTimeAPIImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@Module
public class ApiModule {

    @Provides
    @Singleton
    DateTimeAPI provideDateTimeAPI(Retrofit retrofit) {
        return new DateTimeAPIImpl(retrofit.create(DateTimeAPI.class));
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return RetrofitBuilder.getBuilder();
    }
}
