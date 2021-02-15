package com.medscape.android.analytics.remoteconfig;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/google/android/gms/tasks/Task;", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "onComplete"}, k = 3, mv = {1, 4, 0})
/* compiled from: RemoteConfig.kt */
final class RemoteConfig$fetchRemoteConfig$1<TResult> implements OnCompleteListener<Void> {
    final /* synthetic */ RemoteConfig this$0;

    RemoteConfig$fetchRemoteConfig$1(RemoteConfig remoteConfig) {
        this.this$0 = remoteConfig;
    }

    public final void onComplete(Task<Void> task) {
        Intrinsics.checkNotNullParameter(task, "it");
        if (task.isSuccessful()) {
            this.this$0.getMFirebaseRemoteConfig().activate();
        }
    }
}
