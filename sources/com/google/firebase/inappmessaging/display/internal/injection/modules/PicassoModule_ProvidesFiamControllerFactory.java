package com.google.firebase.inappmessaging.display.internal.injection.modules;

import android.app.Application;
import com.google.firebase.inappmessaging.display.internal.PicassoErrorListener;
import com.squareup.picasso.Picasso;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class PicassoModule_ProvidesFiamControllerFactory implements Factory<Picasso> {
    private final Provider<Application> applicationProvider;
    private final PicassoModule module;
    private final Provider<PicassoErrorListener> picassoErrorListenerProvider;

    public PicassoModule_ProvidesFiamControllerFactory(PicassoModule picassoModule, Provider<Application> provider, Provider<PicassoErrorListener> provider2) {
        this.module = picassoModule;
        this.applicationProvider = provider;
        this.picassoErrorListenerProvider = provider2;
    }

    public Picasso get() {
        return providesFiamController(this.module, this.applicationProvider.get(), this.picassoErrorListenerProvider.get());
    }

    public static PicassoModule_ProvidesFiamControllerFactory create(PicassoModule picassoModule, Provider<Application> provider, Provider<PicassoErrorListener> provider2) {
        return new PicassoModule_ProvidesFiamControllerFactory(picassoModule, provider, provider2);
    }

    public static Picasso providesFiamController(PicassoModule picassoModule, Application application, PicassoErrorListener picassoErrorListener) {
        return (Picasso) Preconditions.checkNotNull(picassoModule.providesFiamController(application, picassoErrorListener), "Cannot return null from a non-@Nullable @Provides method");
    }
}
