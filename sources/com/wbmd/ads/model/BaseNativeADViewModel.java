package com.wbmd.ads.model;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import kotlin.Metadata;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/wbmd/ads/model/BaseNativeADViewModel;", "", "()V", "label", "", "getLabel", "()Ljava/lang/String;", "setLabel", "(Ljava/lang/String;)V", "labelVisibility", "", "getLabelVisibility", "()Z", "setLabelVisibility", "(Z)V", "nativeAD", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "getNativeAD", "()Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "setNativeAD", "(Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;)V", "title", "getTitle", "setTitle", "titleVisibility", "getTitleVisibility", "setTitleVisibility", "bindVariables", "", "performClick", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BaseNativeADViewModel.kt */
public class BaseNativeADViewModel {
    private String label = "";
    private boolean labelVisibility;
    private NativeCustomTemplateAd nativeAD;
    private String title = "";
    private boolean titleVisibility;

    public final String getLabel() {
        return this.label;
    }

    public final void setLabel(String str) {
        this.label = str;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final boolean getLabelVisibility() {
        return this.labelVisibility;
    }

    public final void setLabelVisibility(boolean z) {
        this.labelVisibility = z;
    }

    public final boolean getTitleVisibility() {
        return this.titleVisibility;
    }

    public final void setTitleVisibility(boolean z) {
        this.titleVisibility = z;
    }

    public final NativeCustomTemplateAd getNativeAD() {
        return this.nativeAD;
    }

    public final void setNativeAD(NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.nativeAD = nativeCustomTemplateAd;
    }

    public void bindVariables(NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.nativeAD = nativeCustomTemplateAd;
        CharSequence charSequence = null;
        this.label = String.valueOf(nativeCustomTemplateAd != null ? nativeCustomTemplateAd.getText("Label") : null);
        if (nativeCustomTemplateAd != null) {
            charSequence = nativeCustomTemplateAd.getText("Title");
        }
        this.title = String.valueOf(charSequence);
        CharSequence charSequence2 = this.label;
        boolean z = false;
        this.labelVisibility = !(charSequence2 == null || charSequence2.length() == 0) && !StringsKt.equals(this.label, "null", true);
        CharSequence charSequence3 = this.title;
        if (!(charSequence3 == null || charSequence3.length() == 0) && !StringsKt.equals(this.title, "null", true)) {
            z = true;
        }
        this.titleVisibility = z;
    }

    public void performClick() {
        NativeCustomTemplateAd nativeCustomTemplateAd = this.nativeAD;
        if (nativeCustomTemplateAd != null) {
            nativeCustomTemplateAd.performClick("Title");
        }
    }
}
