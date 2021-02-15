package com.google.firebase.inappmessaging.internal;

import com.google.firebase.FirebaseApp;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class SharedPreferencesUtils_Factory implements Factory<SharedPreferencesUtils> {
    private final Provider<FirebaseApp> firebaseAppProvider;

    public SharedPreferencesUtils_Factory(Provider<FirebaseApp> provider) {
        this.firebaseAppProvider = provider;
    }

    public SharedPreferencesUtils get() {
        return new SharedPreferencesUtils(this.firebaseAppProvider.get());
    }

    public static SharedPreferencesUtils_Factory create(Provider<FirebaseApp> provider) {
        return new SharedPreferencesUtils_Factory(provider);
    }

    public static SharedPreferencesUtils newInstance(FirebaseApp firebaseApp) {
        return new SharedPreferencesUtils(firebaseApp);
    }
}
