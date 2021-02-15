package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class SchedulerModule_ProvidesMainThreadSchedulerFactory implements Factory<Scheduler> {
    private final SchedulerModule module;

    public SchedulerModule_ProvidesMainThreadSchedulerFactory(SchedulerModule schedulerModule) {
        this.module = schedulerModule;
    }

    public Scheduler get() {
        return providesMainThreadScheduler(this.module);
    }

    public static SchedulerModule_ProvidesMainThreadSchedulerFactory create(SchedulerModule schedulerModule) {
        return new SchedulerModule_ProvidesMainThreadSchedulerFactory(schedulerModule);
    }

    public static Scheduler providesMainThreadScheduler(SchedulerModule schedulerModule) {
        return (Scheduler) Preconditions.checkNotNull(schedulerModule.providesMainThreadScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
