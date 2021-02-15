package com.google.firebase.inappmessaging.display.internal.bindingwrappers;

import android.view.LayoutInflater;
import com.google.firebase.inappmessaging.display.internal.InAppMessageLayoutConfig;
import com.google.firebase.inappmessaging.model.InAppMessage;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class ModalBindingWrapper_Factory implements Factory<ModalBindingWrapper> {
    private final Provider<InAppMessageLayoutConfig> configProvider;
    private final Provider<LayoutInflater> inflaterProvider;
    private final Provider<InAppMessage> messageProvider;

    public ModalBindingWrapper_Factory(Provider<InAppMessageLayoutConfig> provider, Provider<LayoutInflater> provider2, Provider<InAppMessage> provider3) {
        this.configProvider = provider;
        this.inflaterProvider = provider2;
        this.messageProvider = provider3;
    }

    public ModalBindingWrapper get() {
        return new ModalBindingWrapper(this.configProvider.get(), this.inflaterProvider.get(), this.messageProvider.get());
    }

    public static ModalBindingWrapper_Factory create(Provider<InAppMessageLayoutConfig> provider, Provider<LayoutInflater> provider2, Provider<InAppMessage> provider3) {
        return new ModalBindingWrapper_Factory(provider, provider2, provider3);
    }

    public static ModalBindingWrapper newInstance(InAppMessageLayoutConfig inAppMessageLayoutConfig, LayoutInflater layoutInflater, InAppMessage inAppMessage) {
        return new ModalBindingWrapper(inAppMessageLayoutConfig, layoutInflater, inAppMessage);
    }
}
