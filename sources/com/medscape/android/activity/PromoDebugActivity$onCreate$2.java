package com.medscape.android.activity;

import android.widget.RadioGroup;
import com.medscape.android.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "group", "Landroid/widget/RadioGroup;", "kotlin.jvm.PlatformType", "checkedId", "", "onCheckedChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: PromoDebugActivity.kt */
final class PromoDebugActivity$onCreate$2 implements RadioGroup.OnCheckedChangeListener {
    final /* synthetic */ PromoDebugActivity this$0;

    PromoDebugActivity$onCreate$2(PromoDebugActivity promoDebugActivity) {
        this.this$0 = promoDebugActivity;
    }

    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rdo_landscape:
                this.this$0.setOrientationLock("landscape");
                return;
            case R.id.rdo_portrait:
                this.this$0.setOrientationLock("portrait");
                return;
            default:
                return;
        }
    }
}
