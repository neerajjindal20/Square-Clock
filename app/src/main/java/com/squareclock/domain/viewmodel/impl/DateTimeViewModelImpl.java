package com.squareclock.domain.viewmodel.impl;

import com.squareclock.domain.model.DateTime;
import com.squareclock.domain.service.DateTimeAPI;
import com.squareclock.domain.viewmodel.DateTimeViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeViewModelImpl implements DateTimeViewModel {

    private static final int INTERVAL_TIME = 1;
    private DateTimeAPI dateTimeAPI;
    SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public DateTimeViewModelImpl(DateTimeAPI dateTimeAPI) {
        this.dateTimeAPI = dateTimeAPI;
    }

    @Override
    public Observable<String> getDateTime() {

        return Observable.interval(INTERVAL_TIME, TimeUnit.MILLISECONDS)
                .flatMap(intervalTime -> dateTimeAPI.getDateTime())
                .doOnError(throwableObservable -> dateTimeAPI.getDateTime())
                .flatMap(this::formatTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<String> formatTime(DateTime dateTime) {
        try {
            Date date = parseFormat.parse(dateTime.getDateTime());
            return Observable.just(timeFormat.format(date));
        } catch (Exception e) {
            return Observable.just(e.getMessage());
        }
    }
}
