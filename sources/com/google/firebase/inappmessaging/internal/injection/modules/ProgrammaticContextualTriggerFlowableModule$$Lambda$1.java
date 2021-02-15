package com.google.firebase.inappmessaging.internal.injection.modules;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ProgrammaticContextualTriggerFlowableModule$$Lambda$1 implements FlowableOnSubscribe {
    private final ProgrammaticContextualTriggerFlowableModule arg$1;

    private ProgrammaticContextualTriggerFlowableModule$$Lambda$1(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule) {
        this.arg$1 = programmaticContextualTriggerFlowableModule;
    }

    public static FlowableOnSubscribe lambdaFactory$(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule) {
        return new ProgrammaticContextualTriggerFlowableModule$$Lambda$1(programmaticContextualTriggerFlowableModule);
    }

    public void subscribe(FlowableEmitter flowableEmitter) {
        this.arg$1.triggers.setListener(ProgrammaticContextualTriggerFlowableModule$$Lambda$2.lambdaFactory$(flowableEmitter));
    }
}
