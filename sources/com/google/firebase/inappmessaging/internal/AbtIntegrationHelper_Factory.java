package com.google.firebase.inappmessaging.internal;

import com.google.firebase.abt.FirebaseABTesting;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class AbtIntegrationHelper_Factory implements Factory<AbtIntegrationHelper> {
    private final Provider<FirebaseABTesting> abTestingProvider;

    public AbtIntegrationHelper_Factory(Provider<FirebaseABTesting> provider) {
        this.abTestingProvider = provider;
    }

    public AbtIntegrationHelper get() {
        return new AbtIntegrationHelper(this.abTestingProvider.get());
    }

    public static AbtIntegrationHelper_Factory create(Provider<FirebaseABTesting> provider) {
        return new AbtIntegrationHelper_Factory(provider);
    }

    public static AbtIntegrationHelper newInstance(FirebaseABTesting firebaseABTesting) {
        return new AbtIntegrationHelper(firebaseABTesting);
    }
}
