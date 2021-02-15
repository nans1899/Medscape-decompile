package com.google.firebase.inappmessaging.internal;

import android.app.Application;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ProviderInstaller {
    private final Application application;

    @Inject
    ProviderInstaller(Application application2) {
        this.application = application2;
    }

    public void install() {
        try {
            com.google.android.gms.security.ProviderInstaller.installIfNeeded(this.application);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
    }
}
