package com.google.firebase.inappmessaging.internal.injection.modules;

import android.app.Application;
import com.google.firebase.inappmessaging.internal.ForegroundNotifier;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.AppForeground;
import dagger.Module;
import dagger.Provides;
import io.reactivex.flowables.ConnectableFlowable;
import javax.inject.Singleton;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ForegroundFlowableModule {
    @AppForeground
    @Singleton
    @Provides
    public ConnectableFlowable<String> providesAppForegroundEventStream(Application application) {
        ForegroundNotifier foregroundNotifier = new ForegroundNotifier();
        ConnectableFlowable<String> foregroundFlowable = foregroundNotifier.foregroundFlowable();
        foregroundFlowable.connect();
        application.registerActivityLifecycleCallbacks(foregroundNotifier);
        return foregroundFlowable;
    }
}
