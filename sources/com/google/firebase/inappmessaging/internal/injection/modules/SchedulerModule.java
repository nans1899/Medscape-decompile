package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class SchedulerModule {
    @Singleton
    @Provides
    @Named("io")
    public Scheduler providesIOScheduler() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @Named("compute")
    public Scheduler providesComputeScheduler() {
        return Schedulers.computation();
    }

    @Singleton
    @Provides
    @Named("main")
    public Scheduler providesMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
