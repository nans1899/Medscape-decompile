package com.google.firebase.inappmessaging.internal;

import dagger.internal.Factory;
import io.reactivex.Scheduler;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class Schedulers_Factory implements Factory<Schedulers> {
    private final Provider<Scheduler> computeSchedulerProvider;
    private final Provider<Scheduler> ioSchedulerProvider;
    private final Provider<Scheduler> mainThreadSchedulerProvider;

    public Schedulers_Factory(Provider<Scheduler> provider, Provider<Scheduler> provider2, Provider<Scheduler> provider3) {
        this.ioSchedulerProvider = provider;
        this.computeSchedulerProvider = provider2;
        this.mainThreadSchedulerProvider = provider3;
    }

    public Schedulers get() {
        return new Schedulers(this.ioSchedulerProvider.get(), this.computeSchedulerProvider.get(), this.mainThreadSchedulerProvider.get());
    }

    public static Schedulers_Factory create(Provider<Scheduler> provider, Provider<Scheduler> provider2, Provider<Scheduler> provider3) {
        return new Schedulers_Factory(provider, provider2, provider3);
    }

    public static Schedulers newInstance(Scheduler scheduler, Scheduler scheduler2, Scheduler scheduler3) {
        return new Schedulers(scheduler, scheduler2, scheduler3);
    }
}
