package com.medscape.android.myinvites;

import com.medscape.android.Constants;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.util.JSONParser;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.medscape.android.myinvites.MyInvitationsManager$fetchSpecificInvitations$1", f = "MyInvitationsManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MyInvitationsManager.kt */
final class MyInvitationsManager$fetchSpecificInvitations$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ MyInvitationsManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MyInvitationsManager$fetchSpecificInvitations$1(MyInvitationsManager myInvitationsManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = myInvitationsManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        MyInvitationsManager$fetchSpecificInvitations$1 myInvitationsManager$fetchSpecificInvitations$1 = new MyInvitationsManager$fetchSpecificInvitations$1(this.this$0, continuation);
        myInvitationsManager$fetchSpecificInvitations$1.p$ = (CoroutineScope) obj;
        return myInvitationsManager$fetchSpecificInvitations$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((MyInvitationsManager$fetchSpecificInvitations$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            JSONObject jSONFromUrl = new JSONParser().getJSONFromUrl(this.this$0.getSpecificInvitationEndpoint(), false, this.this$0.ctx);
            if (jSONFromUrl != null) {
                SharedPreferenceProvider.get().save(Constants.PREF_SPECIFIC_INVITATIONS, jSONFromUrl.toString());
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
