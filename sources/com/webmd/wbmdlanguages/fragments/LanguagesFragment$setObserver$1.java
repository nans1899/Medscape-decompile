package com.webmd.wbmdlanguages.fragments;

import android.content.Intent;
import androidx.lifecycle.Observer;
import com.webmd.wbmdlanguages.models.LanguageModel;
import com.webmd.wbmdlanguages.utils.LanguageConstants;
import com.webmd.wbmdlanguages.utils.Settings;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "checkedPosition", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Integer;)V"}, k = 3, mv = {1, 4, 0})
/* compiled from: LanguagesFragment.kt */
final class LanguagesFragment$setObserver$1<T> implements Observer<Integer> {
    final /* synthetic */ LanguagesFragment this$0;

    LanguagesFragment$setObserver$1(LanguagesFragment languagesFragment) {
        this.this$0 = languagesFragment;
    }

    public final void onChanged(Integer num) {
        LanguagesFragment.access$getLanguageSupportViewModel$p(this.this$0).getLanguagesList().get(LanguagesFragment.access$getLanguageSupportViewModel$p(this.this$0).getCurrentIndex()).setSelected(false);
        ArrayList<LanguageModel> languagesList = LanguagesFragment.access$getLanguageSupportViewModel$p(this.this$0).getLanguagesList();
        Intrinsics.checkNotNullExpressionValue(num, "checkedPosition");
        languagesList.get(num.intValue()).setSelected(true);
        this.this$0.getLanguagesAdapter().setData(LanguagesFragment.access$getLanguageSupportViewModel$p(this.this$0).getLanguagesList());
        new Settings(this.this$0.getMContext()).singleton().saveSetting(LanguageConstants.SETTINGS_LANGUAGE_SELECTED, LanguagesFragment.access$getLanguageSupportViewModel$p(this.this$0).getLanguagesList().get(num.intValue()).getLanguage());
        Intent intent = new Intent();
        intent.setFlags(67108864);
        this.this$0.getMContext().setResult(-1, intent);
        this.this$0.getMContext().finish();
    }
}
