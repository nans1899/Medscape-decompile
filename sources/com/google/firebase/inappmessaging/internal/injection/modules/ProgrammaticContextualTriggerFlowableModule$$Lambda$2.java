package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.internal.ProgramaticContextualTriggers;
import io.reactivex.FlowableEmitter;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ProgrammaticContextualTriggerFlowableModule$$Lambda$2 implements ProgramaticContextualTriggers.Listener {
    private final FlowableEmitter arg$1;

    private ProgrammaticContextualTriggerFlowableModule$$Lambda$2(FlowableEmitter flowableEmitter) {
        this.arg$1 = flowableEmitter;
    }

    public static ProgramaticContextualTriggers.Listener lambdaFactory$(FlowableEmitter flowableEmitter) {
        return new ProgrammaticContextualTriggerFlowableModule$$Lambda$2(flowableEmitter);
    }

    public void onEventTrigger(String str) {
        this.arg$1.onNext(str);
    }
}
