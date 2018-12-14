package com.example.neerajjindal.myapplication.domain.viewmodel;

import io.reactivex.Observable;

public interface DateTimeViewModel {
    Observable<String> getDateTime();
}
