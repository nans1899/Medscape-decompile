package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.flowables.ConnectableFlowable;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggerStreamFactory implements Factory<ConnectableFlowable<String>> {
    private final ProgrammaticContextualTriggerFlowableModule module;

    public ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggerStreamFactory(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule) {
        this.module = programmaticContextualTriggerFlowableModule;
    }

    public ConnectableFlowable<String> get() {
        return providesProgramaticContextualTriggerStream(this.module);
    }

    public static ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggerStreamFactory create(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule) {
        return new ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggerStreamFactory(programmaticContextualTriggerFlowableModule);
    }

    public static ConnectableFlowable<String> providesProgramaticContextualTriggerStream(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule) {
        return (ConnectableFlowable) Preconditions.checkNotNull(programmaticContextualTriggerFlowableModule.providesProgramaticContextualTriggerStream(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
