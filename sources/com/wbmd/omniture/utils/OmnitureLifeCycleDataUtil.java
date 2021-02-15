package com.wbmd.omniture.utils;

import android.content.Context;
import com.adobe.mobile.Config;
import com.adobe.mobile.MobilePrivacyStatus;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/wbmd/omniture/utils/OmnitureLifeCycleDataUtil;", "", "()V", "collectOmnitureLifeCycleData", "", "activity", "Landroid/content/Context;", "setOmniturePrivacyStatus", "hasOptedIn", "", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureLifeCycleDataUtil.kt */
public final class OmnitureLifeCycleDataUtil {
    public static final OmnitureLifeCycleDataUtil INSTANCE = new OmnitureLifeCycleDataUtil();

    private OmnitureLifeCycleDataUtil() {
    }

    public final void setOmniturePrivacyStatus(boolean z, Context context) {
        Intrinsics.checkNotNullParameter(context, "activity");
        if (z) {
            Config.setPrivacyStatus(MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN);
        } else {
            Config.setPrivacyStatus(MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT);
        }
        collectOmnitureLifeCycleData(context);
    }

    public final void collectOmnitureLifeCycleData(Context context) {
        Intrinsics.checkNotNullParameter(context, "activity");
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new OmnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1(context, (Continuation) null), 2, (Object) null);
    }
}
