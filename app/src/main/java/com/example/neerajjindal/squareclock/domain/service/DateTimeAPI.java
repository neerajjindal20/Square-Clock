package com.example.neerajjindal.myapplication.domain.service;

import com.example.neerajjindal.myapplication.domain.model.DateTime;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DateTimeAPI {

    @GET(".")
    Observable<DateTime> getDateTime();
}
