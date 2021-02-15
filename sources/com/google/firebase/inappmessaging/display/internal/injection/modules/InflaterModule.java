package com.google.firebase.inappmessaging.display.internal.injection.modules;

import android.app.Application;
import android.view.LayoutInflater;
import com.google.firebase.inappmessaging.display.internal.InAppMessageLayoutConfig;
import com.google.firebase.inappmessaging.model.InAppMessage;
import dagger.Module;
import dagger.Provides;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class InflaterModule {
    private final Application application;
    private final InAppMessage inAppMessage;
    private final InAppMessageLayoutConfig inAppMessageLayoutConfig;

    public InflaterModule(InAppMessage inAppMessage2, InAppMessageLayoutConfig inAppMessageLayoutConfig2, Application application2) {
        this.inAppMessage = inAppMessage2;
        this.inAppMessageLayoutConfig = inAppMessageLayoutConfig2;
        this.application = application2;
    }

    @Provides
    public LayoutInflater providesInflaterservice() {
        return (LayoutInflater) this.application.getSystemService("layout_inflater");
    }

    /* access modifiers changed from: package-private */
    @Provides
    public InAppMessage providesBannerMessage() {
        return this.inAppMessage;
    }

    /* access modifiers changed from: package-private */
    @Provides
    public InAppMessageLayoutConfig inAppMessageLayoutConfig() {
        return this.inAppMessageLayoutConfig;
    }
}
