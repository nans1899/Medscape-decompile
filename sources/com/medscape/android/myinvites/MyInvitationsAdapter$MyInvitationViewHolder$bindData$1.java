package com.medscape.android.myinvites;

import android.view.View;
import com.medscape.android.myinvites.MyInvitationsAdapter;
import com.medscape.android.myinvites.specific.Invitation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: MyInvitationsAdapter.kt */
final class MyInvitationsAdapter$MyInvitationViewHolder$bindData$1 implements View.OnClickListener {
    final /* synthetic */ Invitation $invitation;
    final /* synthetic */ MyInvitationsAdapter.MyInvitationViewHolder this$0;

    MyInvitationsAdapter$MyInvitationViewHolder$bindData$1(MyInvitationsAdapter.MyInvitationViewHolder myInvitationViewHolder, Invitation invitation) {
        this.this$0 = myInvitationViewHolder;
        this.$invitation = invitation;
    }

    public final void onClick(View view) {
        MyInvitationsAdapter.MyInvitationViewHolder myInvitationViewHolder = this.this$0;
        Intrinsics.checkNotNullExpressionValue(view, "it");
        myInvitationViewHolder.openLink(view.getContext(), this.$invitation);
    }
}
