package com.google.firebase.inappmessaging.internal.injection.modules;

import android.app.Application;
import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application2) {
        this.application = application2;
    }

    @Singleton
    @Provides
    public Application providesApplication() {
        return this.application;
    }

    @Singleton
    @Provides
    public DeveloperListenerManager developerListenerManager() {
        return new DeveloperListenerManager();
    }
}
