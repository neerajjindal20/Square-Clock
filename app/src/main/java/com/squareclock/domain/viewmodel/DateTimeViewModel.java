package com.squareclock.domain.viewmodel;

import io.reactivex.Observable;

public interface DateTimeViewModel {
    Observable<String> getDateTime();
}
