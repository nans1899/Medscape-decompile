package com.google.firebase.inappmessaging.display.internal.injection.modules;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
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
}
