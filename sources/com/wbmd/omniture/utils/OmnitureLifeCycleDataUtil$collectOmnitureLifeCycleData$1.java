package com.wbmd.omniture.utils;

import android.app.Activity;
import android.content.Context;
import com.adobe.mobile.Config;
import com.adobe.mobile.MobilePrivacyStatus;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.wbmdcmepulse.models.CPEvent;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.wbmd.omniture.utils.OmnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1", f = "OmnitureLifeCycleDataUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: OmnitureLifeCycleDataUtil.kt */
final class OmnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $activity;
    int label;
    private CoroutineScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OmnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1(Context context, Continuation continuation) {
        super(2, continuation);
        this.$activity = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        OmnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1 omnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1 = new OmnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1(this.$activity, continuation);
        omnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1.p$ = (CoroutineScope) obj;
        return omnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((OmnitureLifeCycleDataUtil$collectOmnitureLifeCycleData$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str = "00000000-0000-0000-0000-000000000000";
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.$activity);
                Intrinsics.checkNotNullExpressionValue(advertisingIdInfo, OmnitureConstants.OMNITURE_LINK_EVENT_INFO);
                if (!advertisingIdInfo.isLimitAdTrackingEnabled() && Config.getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
                    String id = advertisingIdInfo.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "info.id");
                    str = id;
                }
            } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            }
            Context context = this.$activity;
            if (context != null) {
                Config.collectLifecycleData((Activity) context, MapsKt.mapOf(TuplesKt.to("wapp.maid", str)));
                return Unit.INSTANCE;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
