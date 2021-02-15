package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class SchedulerModule_ProvidesComputeSchedulerFactory implements Factory<Scheduler> {
    private final SchedulerModule module;

    public SchedulerModule_ProvidesComputeSchedulerFactory(SchedulerModule schedulerModule) {
        this.module = schedulerModule;
    }

    public Scheduler get() {
        return providesComputeScheduler(this.module);
    }

    public static SchedulerModule_ProvidesComputeSchedulerFactory create(SchedulerModule schedulerModule) {
        return new SchedulerModule_ProvidesComputeSchedulerFactory(schedulerModule);
    }

    public static Scheduler providesComputeScheduler(SchedulerModule schedulerModule) {
        return (Scheduler) Preconditions.checkNotNull(schedulerModule.providesComputeScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
