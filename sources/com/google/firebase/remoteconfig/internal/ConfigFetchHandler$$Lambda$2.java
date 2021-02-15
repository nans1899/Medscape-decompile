package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Date;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class ConfigFetchHandler$$Lambda$2 implements Continuation {
    private final ConfigFetchHandler arg$1;
    private final Date arg$2;

    private ConfigFetchHandler$$Lambda$2(ConfigFetchHandler configFetchHandler, Date date) {
        this.arg$1 = configFetchHandler;
        this.arg$2 = date;
    }

    public static Continuation lambdaFactory$(ConfigFetchHandler configFetchHandler, Date date) {
        return new ConfigFetchHandler$$Lambda$2(configFetchHandler, date);
    }

    public Object then(Task task) {
        return this.arg$1.updateLastFetchStatusAndTime(task, this.arg$2);
    }
}
