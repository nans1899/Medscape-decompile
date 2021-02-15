package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.FirebaseApp;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ApiClientModule_ProvidesFirebaseAppFactory implements Factory<FirebaseApp> {
    private final ApiClientModule module;

    public ApiClientModule_ProvidesFirebaseAppFactory(ApiClientModule apiClientModule) {
        this.module = apiClientModule;
    }

    public FirebaseApp get() {
        return providesFirebaseApp(this.module);
    }

    public static ApiClientModule_ProvidesFirebaseAppFactory create(ApiClientModule apiClientModule) {
        return new ApiClientModule_ProvidesFirebaseAppFactory(apiClientModule);
    }

    public static FirebaseApp providesFirebaseApp(ApiClientModule apiClientModule) {
        return (FirebaseApp) Preconditions.checkNotNull(apiClientModule.providesFirebaseApp(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
