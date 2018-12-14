package com.example.neerajjindal.myapplication.dagger;

import com.example.neerajjindal.myapplication.domain.retrofit.RetrofitBuilder;
import com.example.neerajjindal.myapplication.domain.service.DateTimeAPI;
import com.example.neerajjindal.myapplication.domain.service.impl.DateTimeAPIImpl;
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
