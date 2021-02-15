package com.medscape.android.activity;

import android.view.View;
import android.widget.AdapterView;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0016¨\u0006\r"}, d2 = {"com/medscape/android/activity/PromoDebugActivity$setupSpinner$1", "Landroid/widget/AdapterView$OnItemSelectedListener;", "onItemSelected", "", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "position", "", "id", "", "onNothingSelected", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PromoDebugActivity.kt */
public final class PromoDebugActivity$setupSpinner$1 implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ PromoDebugActivity this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    PromoDebugActivity$setupSpinner$1(PromoDebugActivity promoDebugActivity) {
        this.this$0 = promoDebugActivity;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (i == 0) {
            this.this$0.getUrlType().setVisibility(8);
            this.this$0.getPromoUrl().setVisibility(8);
            this.this$0.getOrientationLayout().setVisibility(8);
            this.this$0.getBtnLaunch().setEnabled(false);
            this.this$0.getBtnLaunch().setClickable(false);
            this.this$0.getBtnRestore().setEnabled(false);
            this.this$0.getBtnRestore().setClickable(false);
            MedscapeApplication medscapeApplication = MedscapeApplication.get();
            Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
            medscapeApplication.getPreferences().edit().putString(Constants.PREF_DEBUG_SLIDE_DEMO_URL, "").commit();
            MedscapeApplication medscapeApplication2 = MedscapeApplication.get();
            Intrinsics.checkNotNullExpressionValue(medscapeApplication2, "MedscapeApplication.get()");
            medscapeApplication2.getPreferences().edit().putInt(Constants.PREF_DEBUG_SLIDE_DEMO_MODE, 0).commit();
            MedscapeApplication medscapeApplication3 = MedscapeApplication.get();
            Intrinsics.checkNotNullExpressionValue(medscapeApplication3, "MedscapeApplication.get()");
            medscapeApplication3.getPreferences().edit().putString(Constants.PREF_DEBUG_SLIDE_DEMO_ORIENTATION, (String) null).commit();
        } else if (i == 1) {
            PromoDebugActivity promoDebugActivity = this.this$0;
            String string = promoDebugActivity.getResources().getString(R.string.manifest_url);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.manifest_url)");
            promoDebugActivity.setupSpinnerSelection(string, 1, Constants.PROMO_SLIDESHOW_DEFAULT_URL);
            this.this$0.getOrientationLayout().setVisibility(0);
        } else if (i == 2) {
            PromoDebugActivity promoDebugActivity2 = this.this$0;
            String string2 = promoDebugActivity2.getResources().getString(R.string.promo_url);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.promo_url)");
            promoDebugActivity2.setupSpinnerSelection(string2, 2, Constants.PROMO_SLIDESHOW_2_0_DEFAULT_URL);
            this.this$0.getOrientationLayout().setVisibility(0);
        } else if (i == 3) {
            PromoDebugActivity promoDebugActivity3 = this.this$0;
            String string3 = promoDebugActivity3.getResources().getString(R.string.manifest_url);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.manifest_url)");
            promoDebugActivity3.setupSpinnerSelection(string3, 3, Constants.PROMO_BRANDPLAY_DEFAULT_URL);
            this.this$0.getOrientationLayout().setVisibility(0);
        } else if (i == 4) {
            PromoDebugActivity promoDebugActivity4 = this.this$0;
            String string4 = promoDebugActivity4.getResources().getString(R.string.promo_url);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.promo_url)");
            promoDebugActivity4.setupSpinnerSelection(string4, 4, "");
            this.this$0.getOrientationLayout().setVisibility(0);
        }
        this.this$0.setDemoType(i);
    }
}
