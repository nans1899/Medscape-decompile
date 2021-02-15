package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.internal.ProgramaticContextualTriggers;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggersFactory implements Factory<ProgramaticContextualTriggers> {
    private final ProgrammaticContextualTriggerFlowableModule module;

    public ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggersFactory(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule) {
        this.module = programmaticContextualTriggerFlowableModule;
    }

    public ProgramaticContextualTriggers get() {
        return providesProgramaticContextualTriggers(this.module);
    }

    public static ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggersFactory create(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule) {
        return new ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggersFactory(programmaticContextualTriggerFlowableModule);
    }

    public static ProgramaticContextualTriggers providesProgramaticContextualTriggers(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule) {
        return (ProgramaticContextualTriggers) Preconditions.checkNotNull(programmaticContextualTriggerFlowableModule.providesProgramaticContextualTriggers(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
