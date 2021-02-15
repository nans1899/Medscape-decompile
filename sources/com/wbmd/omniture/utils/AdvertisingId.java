package com.wbmd.omniture.utils;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/wbmd/omniture/utils/AdvertisingId;", "", "()V", "get", "", "context", "Landroid/content/Context;", "update", "", "Companion", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdvertisingId.kt */
public final class AdvertisingId {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String advertisingIdentifier = "advertising_identifier";
    public static final String defaultMaidID = "00000000-0000-0000-0000-000000000000";

    public final void update(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new AdvertisingId$update$1(context, (Continuation) null), 2, (Object) null);
    }

    public final String get(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SharedPreferencesManager(context).getSetting("advertising_identifier", (String) null);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/wbmd/omniture/utils/AdvertisingId$Companion;", "", "()V", "advertisingIdentifier", "", "defaultMaidID", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdvertisingId.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
