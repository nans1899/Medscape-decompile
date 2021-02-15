package com.medscape.android.activity.help.viewholders;

import android.app.Activity;
import android.view.View;
import com.google.android.gms.common.Scopes;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.activity.login.LogoutHandler;
import java.util.Map;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: HelpViewHolder.kt */
final class HelpViewHolder$bind$1 implements View.OnClickListener {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ HelpViewHolder this$0;

    HelpViewHolder$bind$1(HelpViewHolder helpViewHolder, Activity activity) {
        this.this$0 = helpViewHolder;
        this.$activity = activity;
    }

    public final void onClick(View view) {
        OmnitureManager.get().trackPageView(this.$activity, "other", Scopes.PROFILE, "logout", (String) null, (String) null, (Map<String, Object>) null);
        new LogoutHandler().handleLogout(this.$activity);
        this.this$0.resetDefaultUnits();
    }
}
