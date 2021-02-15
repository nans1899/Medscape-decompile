package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ApplicationModule_DeveloperListenerManagerFactory implements Factory<DeveloperListenerManager> {
    private final ApplicationModule module;

    public ApplicationModule_DeveloperListenerManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public DeveloperListenerManager get() {
        return developerListenerManager(this.module);
    }

    public static ApplicationModule_DeveloperListenerManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_DeveloperListenerManagerFactory(applicationModule);
    }

    public static DeveloperListenerManager developerListenerManager(ApplicationModule applicationModule) {
        return (DeveloperListenerManager) Preconditions.checkNotNull(applicationModule.developerListenerManager(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
