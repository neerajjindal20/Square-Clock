package com.squareclock.domain.service.impl;


import com.squareclock.domain.model.DateTime;
import com.squareclock.domain.service.DateTimeAPI;
import io.reactivex.Observable;

public class DateTimeAPIImpl implements DateTimeAPI {

    private DateTimeAPI dateTimeAPI;

    public DateTimeAPIImpl(DateTimeAPI dateTimeAPI) {
        this.dateTimeAPI = dateTimeAPI;
    }

    @Override
    public Observable<DateTime> getDateTime() {
        return dateTimeAPI.getDateTime();
    }
}
