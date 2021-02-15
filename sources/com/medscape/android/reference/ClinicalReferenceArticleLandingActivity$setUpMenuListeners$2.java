package com.medscape.android.reference;

import android.widget.SeekBar;
import androidx.lifecycle.MutableLiveData;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.contentviewer.ContentUtils;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.reference.adapters.ReferenceTOCDataAdapter;
import com.medscape.android.reference.interfaces.ISectionItemClickListener;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"com/medscape/android/reference/ClinicalReferenceArticleLandingActivity$setUpMenuListeners$2", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "onProgressChanged", "", "seekBar", "Landroid/widget/SeekBar;", "progress", "", "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingActivity.kt */
public final class ClinicalReferenceArticleLandingActivity$setUpMenuListeners$2 implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ ClinicalReferenceArticleLandingActivity this$0;

    public void onStartTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
    }

    ClinicalReferenceArticleLandingActivity$setUpMenuListeners$2(ClinicalReferenceArticleLandingActivity clinicalReferenceArticleLandingActivity) {
        this.this$0 = clinicalReferenceArticleLandingActivity;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        ReferenceTOCDataAdapter adapter;
        MutableLiveData<Integer> textSize;
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        if (z) {
            ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p = this.this$0.crLandingViewModel;
            if (!(access$getCrLandingViewModel$p == null || (textSize = access$getCrLandingViewModel$p.getTextSize()) == null)) {
                textSize.setValue(Integer.valueOf(i));
            }
            ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p2 = this.this$0.crLandingViewModel;
            if (!(access$getCrLandingViewModel$p2 == null || (adapter = access$getCrLandingViewModel$p2.getAdapter(this.this$0, (ISectionItemClickListener) null)) == null)) {
                adapter.setTextSizeIndex(i);
            }
            SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
            StringBuilder sb = new StringBuilder();
            sb.append(Constants.PREF_REFERENCE_TEXT_SIZE_INDEX);
            AuthenticationManager instance = AuthenticationManager.getInstance(this.this$0);
            Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.ge…ceArticleLandingActivity)");
            sb.append(instance.getMaskedGuid());
            sharedPreferenceProvider.save(sb.toString(), this.this$0.getFontSeekBar().getProgress());
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        OmnitureManager.get().trackModule(this.this$0, Constants.OMNITURE_CHANNEL_REFERENCE, "font-resizer", ContentUtils.getOmnitureValueForFontSize(seekBar.getProgress()), (Map<String, Object>) null);
    }
}
