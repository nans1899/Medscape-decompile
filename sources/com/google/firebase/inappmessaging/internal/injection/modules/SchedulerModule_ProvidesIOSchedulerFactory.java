package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class SchedulerModule_ProvidesIOSchedulerFactory implements Factory<Scheduler> {
    private final SchedulerModule module;

    public SchedulerModule_ProvidesIOSchedulerFactory(SchedulerModule schedulerModule) {
        this.module = schedulerModule;
    }

    public Scheduler get() {
        return providesIOScheduler(this.module);
    }

    public static SchedulerModule_ProvidesIOSchedulerFactory create(SchedulerModule schedulerModule) {
        return new SchedulerModule_ProvidesIOSchedulerFactory(schedulerModule);
    }

    public static Scheduler providesIOScheduler(SchedulerModule schedulerModule) {
        return (Scheduler) Preconditions.checkNotNull(schedulerModule.providesIOScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
