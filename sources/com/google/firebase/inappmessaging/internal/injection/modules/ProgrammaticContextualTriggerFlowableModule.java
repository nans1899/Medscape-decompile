package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.internal.ProgramaticContextualTriggers;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.ProgrammaticTrigger;
import dagger.Module;
import dagger.Provides;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import javax.inject.Singleton;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ProgrammaticContextualTriggerFlowableModule {
    private ProgramaticContextualTriggers triggers;

    public ProgrammaticContextualTriggerFlowableModule(ProgramaticContextualTriggers programaticContextualTriggers) {
        this.triggers = programaticContextualTriggers;
    }

    @Singleton
    @ProgrammaticTrigger
    @Provides
    public ProgramaticContextualTriggers providesProgramaticContextualTriggers() {
        return this.triggers;
    }

    @Singleton
    @ProgrammaticTrigger
    @Provides
    public ConnectableFlowable<String> providesProgramaticContextualTriggerStream() {
        ConnectableFlowable<String> publish = Flowable.create(ProgrammaticContextualTriggerFlowableModule$$Lambda$1.lambdaFactory$(this), BackpressureStrategy.BUFFER).publish();
        publish.connect();
        return publish;
    }
}
