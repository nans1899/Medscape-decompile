package com.medscape.android.reference;

import android.view.View;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.interfaces.INightModeListener;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.reference.adapters.ReferenceTOCDataAdapter;
import com.medscape.android.reference.interfaces.ISectionItemClickListener;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingActivity.kt */
final class ClinicalReferenceArticleLandingActivity$setUpMenuListeners$3 implements View.OnClickListener {
    final /* synthetic */ ClinicalReferenceArticleLandingActivity this$0;

    ClinicalReferenceArticleLandingActivity$setUpMenuListeners$3(ClinicalReferenceArticleLandingActivity clinicalReferenceArticleLandingActivity) {
        this.this$0 = clinicalReferenceArticleLandingActivity;
    }

    public final void onClick(View view) {
        INightModeListener nightModeListener;
        ReferenceTOCDataAdapter adapter;
        ReferenceTOCDataAdapter adapter2;
        if (this.this$0.getNightSwitch().isChecked()) {
            ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p = this.this$0.crLandingViewModel;
            if (!(access$getCrLandingViewModel$p == null || (adapter2 = access$getCrLandingViewModel$p.getAdapter(this.this$0, (ISectionItemClickListener) null)) == null)) {
                adapter2.setNightMode(true);
            }
            OmnitureManager.get().trackModule(this.this$0, Constants.OMNITURE_CHANNEL_REFERENCE, "night-mode", DebugKt.DEBUG_PROPERTY_VALUE_ON, (Map<String, Object>) null);
            SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
            StringBuilder sb = new StringBuilder();
            sb.append(Constants.PREF_REFERENCE_NIGHT_MODE);
            AuthenticationManager instance = AuthenticationManager.getInstance(this.this$0);
            Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.ge…ceArticleLandingActivity)");
            sb.append(instance.getMaskedGuid());
            sharedPreferenceProvider.save(sb.toString(), 1);
            this.this$0.getRootView().setBackgroundColor(this.this$0.getResources().getColor(R.color.black));
        } else {
            ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p2 = this.this$0.crLandingViewModel;
            if (!(access$getCrLandingViewModel$p2 == null || (adapter = access$getCrLandingViewModel$p2.getAdapter(this.this$0, (ISectionItemClickListener) null)) == null)) {
                adapter.setNightMode(false);
            }
            OmnitureManager.get().trackModule(this.this$0, Constants.OMNITURE_CHANNEL_REFERENCE, "night-mode", DebugKt.DEBUG_PROPERTY_VALUE_OFF, (Map<String, Object>) null);
            SharedPreferenceProvider sharedPreferenceProvider2 = SharedPreferenceProvider.get();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Constants.PREF_REFERENCE_NIGHT_MODE);
            AuthenticationManager instance2 = AuthenticationManager.getInstance(this.this$0);
            Intrinsics.checkNotNullExpressionValue(instance2, "AuthenticationManager.ge…ceArticleLandingActivity)");
            sb2.append(instance2.getMaskedGuid());
            sharedPreferenceProvider2.save(sb2.toString(), 0);
            this.this$0.getRootView().setBackgroundColor(this.this$0.getResources().getColor(R.color.white));
        }
        if (this.this$0.getNightModeListener() != null && (nightModeListener = this.this$0.getNightModeListener()) != null) {
            nightModeListener.onNightModeChanged(this.this$0.getNightSwitch().isChecked());
        }
    }
}
