package com.sunshinexu.mobilelearn.utils;


import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtil {

    /**
     *调度器，避免重复控制线程代码
     * @param <T>
     * @return
     */

    public static <T> ObservableTransformer<T, T> SchedulerTransformer() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
