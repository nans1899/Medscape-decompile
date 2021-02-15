package com.medscape.android.myinvites;

import android.content.Context;
import android.content.Intent;
import com.medscape.android.Constants;
import com.medscape.android.task.GetOpenMyInvitationsCount;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, d2 = {"com/medscape/android/myinvites/MyInvitationsManager$fetchOpenInvitations$getOpenInvitesCountTask$1", "Lcom/medscape/android/task/GetOpenMyInvitationsCount$GetOpenMyInvitationsCountListener;", "onGetOpenMyInvitationsCountComplete", "", "json", "Lorg/json/JSONObject;", "onGetOpenMyInvitationsCountError", "onGetOpenMyInvitationsCountNoResults", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MyInvitationsManager.kt */
public final class MyInvitationsManager$fetchOpenInvitations$getOpenInvitesCountTask$1 implements GetOpenMyInvitationsCount.GetOpenMyInvitationsCountListener {
    final /* synthetic */ MyInvitationsManager this$0;

    public void onGetOpenMyInvitationsCountError() {
    }

    MyInvitationsManager$fetchOpenInvitations$getOpenInvitesCountTask$1(MyInvitationsManager myInvitationsManager) {
        this.this$0 = myInvitationsManager;
    }

    public void onGetOpenMyInvitationsCountNoResults() {
        this.this$0.updateOpenInvitations(0);
        Intent intent = new Intent(Constants.INVITATIONS_BROADCAST_UPDATE);
        if (this.this$0.ctx != null) {
            Context access$getCtx$p = this.this$0.ctx;
            Intrinsics.checkNotNull(access$getCtx$p);
            access$getCtx$p.sendBroadcast(intent);
        }
    }

    public void onGetOpenMyInvitationsCountComplete(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "json");
        this.this$0.updateOpenInvitations(MyInvitationsManager.Companion.getOpenMyInvitationsCount(jSONObject));
        Intent intent = new Intent(Constants.INVITATIONS_BROADCAST_UPDATE);
        if (this.this$0.ctx != null) {
            Context access$getCtx$p = this.this$0.ctx;
            Intrinsics.checkNotNull(access$getCtx$p);
            access$getCtx$p.sendBroadcast(intent);
        }
    }
}
