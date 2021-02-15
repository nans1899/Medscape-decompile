package com.medscape.android.activity.calc.managers;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.wbmd.qxcalculator.model.db.DBRestriction;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "which", "", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ContentItemLaunchManager.kt */
final class ContentItemLaunchManager$launchContentItem$2 implements DialogInterface.OnClickListener {
    final /* synthetic */ FragmentActivity $fromActivity;
    final /* synthetic */ DBRestriction $restriction;

    ContentItemLaunchManager$launchContentItem$2(DBRestriction dBRestriction, FragmentActivity fragmentActivity) {
        this.$restriction = dBRestriction;
        this.$fromActivity = fragmentActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.$fromActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.$restriction.getAlternateUrl())));
    }
}
