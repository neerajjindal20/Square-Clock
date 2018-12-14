package com.squareclock.domain.service;

import com.squareclock.domain.model.DateTime;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DateTimeAPI {

    @GET(".")
    Observable<DateTime> getDateTime();
}
