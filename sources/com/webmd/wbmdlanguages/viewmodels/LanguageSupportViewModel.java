package com.webmd.wbmdlanguages.viewmodels;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.webmd.wbmdlanguages.R;
import com.webmd.wbmdlanguages.models.LanguageModel;
import com.webmd.wbmdlanguages.utils.LanguageConstants;
import com.webmd.wbmdlanguages.utils.Settings;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0005R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR*\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001e"}, d2 = {"Lcom/webmd/wbmdlanguages/viewmodels/LanguageSupportViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "currentCheckedPosition", "Landroidx/lifecycle/MutableLiveData;", "", "getCurrentCheckedPosition", "()Landroidx/lifecycle/MutableLiveData;", "setCurrentCheckedPosition", "(Landroidx/lifecycle/MutableLiveData;)V", "currentIndex", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "languagesList", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdlanguages/models/LanguageModel;", "Lkotlin/collections/ArrayList;", "getLanguagesList", "()Ljava/util/ArrayList;", "setLanguagesList", "(Ljava/util/ArrayList;)V", "getCurrentLanguage", "context", "Landroid/content/Context;", "loadLanguages", "", "setCurrentCheckedPositionValue", "position", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LanguageSupportViewModel.kt */
public final class LanguageSupportViewModel extends ViewModel {
    private MutableLiveData<Integer> currentCheckedPosition = new MutableLiveData<>();
    private int currentIndex;
    private ArrayList<LanguageModel> languagesList = new ArrayList<>();

    public final ArrayList<LanguageModel> getLanguagesList() {
        return this.languagesList;
    }

    public final void setLanguagesList(ArrayList<LanguageModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.languagesList = arrayList;
    }

    public final MutableLiveData<Integer> getCurrentCheckedPosition() {
        return this.currentCheckedPosition;
    }

    public final void setCurrentCheckedPosition(MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.currentCheckedPosition = mutableLiveData;
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public final void loadLanguages(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String[] stringArray = context.getResources().getStringArray(R.array.languages_settings);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…array.languages_settings)");
        List<String> mutableList = ArraysKt.toMutableList((T[]) stringArray);
        String language = getCurrentLanguage(context).getLanguage();
        for (String str : mutableList) {
            LanguageModel languageModel = new LanguageModel(str, Intrinsics.areEqual((Object) str, (Object) language));
            this.currentIndex = mutableList.indexOf(language);
            this.languagesList.add(languageModel);
        }
    }

    public final LanguageModel getCurrentLanguage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new LanguageModel(new Settings(context).singleton().getSetting(LanguageConstants.SETTINGS_LANGUAGE_SELECTED, "English"), true);
    }

    public final void setCurrentCheckedPositionValue(int i) {
        this.currentCheckedPosition.setValue(Integer.valueOf(i));
    }
}
