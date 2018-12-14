package com.example.neerajjindal.myapplication.domain.service.impl;

import com.example.neerajjindal.myapplication.domain.model.DateTime;
import com.example.neerajjindal.myapplication.domain.service.DateTimeAPI;
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
