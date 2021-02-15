package com.webmd.wbmdlanguages.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/webmd/wbmdlanguages/models/LanguageModel;", "", "language", "", "isSelected", "", "(Ljava/lang/String;Z)V", "()Z", "setSelected", "(Z)V", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LanguageModel.kt */
public final class LanguageModel {
    private boolean isSelected;
    private String language;

    public static /* synthetic */ LanguageModel copy$default(LanguageModel languageModel, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = languageModel.language;
        }
        if ((i & 2) != 0) {
            z = languageModel.isSelected;
        }
        return languageModel.copy(str, z);
    }

    public final String component1() {
        return this.language;
    }

    public final boolean component2() {
        return this.isSelected;
    }

    public final LanguageModel copy(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "language");
        return new LanguageModel(str, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LanguageModel)) {
            return false;
        }
        LanguageModel languageModel = (LanguageModel) obj;
        return Intrinsics.areEqual((Object) this.language, (Object) languageModel.language) && this.isSelected == languageModel.isSelected;
    }

    public int hashCode() {
        String str = this.language;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isSelected;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "LanguageModel(language=" + this.language + ", isSelected=" + this.isSelected + ")";
    }

    public LanguageModel(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "language");
        this.language = str;
        this.isSelected = z;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.language = str;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }
}
