package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.iid.FirebaseInstanceId;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ApiClientModule_ProvidesFirebaseInstanceIdFactory implements Factory<FirebaseInstanceId> {
    private final ApiClientModule module;

    public ApiClientModule_ProvidesFirebaseInstanceIdFactory(ApiClientModule apiClientModule) {
        this.module = apiClientModule;
    }

    public FirebaseInstanceId get() {
        return providesFirebaseInstanceId(this.module);
    }

    public static ApiClientModule_ProvidesFirebaseInstanceIdFactory create(ApiClientModule apiClientModule) {
        return new ApiClientModule_ProvidesFirebaseInstanceIdFactory(apiClientModule);
    }

    public static FirebaseInstanceId providesFirebaseInstanceId(ApiClientModule apiClientModule) {
        return (FirebaseInstanceId) Preconditions.checkNotNull(apiClientModule.providesFirebaseInstanceId(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
