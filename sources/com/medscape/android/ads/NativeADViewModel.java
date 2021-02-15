package com.medscape.android.ads;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.wbmd.ads.model.BaseNativeADViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020+H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u001a\u0010\u001b\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u001a\u0010\u001e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000eR\u001a\u0010$\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\f\"\u0004\b&\u0010\u000eR\u001a\u0010'\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\f\"\u0004\b)\u0010\u000e¨\u0006/"}, d2 = {"Lcom/medscape/android/ads/NativeADViewModel;", "Lcom/wbmd/ads/model/BaseNativeADViewModel;", "()V", "additionalLink1", "", "getAdditionalLink1", "()Ljava/lang/String;", "setAdditionalLink1", "(Ljava/lang/String;)V", "additionalLink1TxtViewVisibility", "", "getAdditionalLink1TxtViewVisibility", "()Z", "setAdditionalLink1TxtViewVisibility", "(Z)V", "additionalLink1Url", "Landroid/text/Spanned;", "getAdditionalLink1Url", "()Landroid/text/Spanned;", "setAdditionalLink1Url", "(Landroid/text/Spanned;)V", "additionalLink2", "getAdditionalLink2", "setAdditionalLink2", "additionalLink2TxtViewVisibility", "getAdditionalLink2TxtViewVisibility", "setAdditionalLink2TxtViewVisibility", "additionalLink2Url", "getAdditionalLink2Url", "setAdditionalLink2Url", "jobCode", "getJobCode", "setJobCode", "jobCodeVisibility", "getJobCodeVisibility", "setJobCodeVisibility", "linkDividerVisibility", "getLinkDividerVisibility", "setLinkDividerVisibility", "rootLayoutVisibility", "getRootLayoutVisibility", "setRootLayoutVisibility", "bindVariables", "", "nativeAD", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "performClick", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NativeADViewModel.kt */
public final class NativeADViewModel extends BaseNativeADViewModel {
    private String additionalLink1 = "";
    private boolean additionalLink1TxtViewVisibility;
    private Spanned additionalLink1Url = new SpannableString("");
    private String additionalLink2 = "";
    private boolean additionalLink2TxtViewVisibility;
    private Spanned additionalLink2Url = new SpannableString("");
    private String jobCode = "";
    private boolean jobCodeVisibility;
    private boolean linkDividerVisibility;
    private boolean rootLayoutVisibility;

    public final boolean getRootLayoutVisibility() {
        return this.rootLayoutVisibility;
    }

    public final void setRootLayoutVisibility(boolean z) {
        this.rootLayoutVisibility = z;
    }

    public final boolean getAdditionalLink1TxtViewVisibility() {
        return this.additionalLink1TxtViewVisibility;
    }

    public final void setAdditionalLink1TxtViewVisibility(boolean z) {
        this.additionalLink1TxtViewVisibility = z;
    }

    public final boolean getLinkDividerVisibility() {
        return this.linkDividerVisibility;
    }

    public final void setLinkDividerVisibility(boolean z) {
        this.linkDividerVisibility = z;
    }

    public final boolean getAdditionalLink2TxtViewVisibility() {
        return this.additionalLink2TxtViewVisibility;
    }

    public final void setAdditionalLink2TxtViewVisibility(boolean z) {
        this.additionalLink2TxtViewVisibility = z;
    }

    public final boolean getJobCodeVisibility() {
        return this.jobCodeVisibility;
    }

    public final void setJobCodeVisibility(boolean z) {
        this.jobCodeVisibility = z;
    }

    public final String getAdditionalLink1() {
        return this.additionalLink1;
    }

    public final void setAdditionalLink1(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.additionalLink1 = str;
    }

    public final Spanned getAdditionalLink1Url() {
        return this.additionalLink1Url;
    }

    public final void setAdditionalLink1Url(Spanned spanned) {
        Intrinsics.checkNotNullParameter(spanned, "<set-?>");
        this.additionalLink1Url = spanned;
    }

    public final String getAdditionalLink2() {
        return this.additionalLink2;
    }

    public final void setAdditionalLink2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.additionalLink2 = str;
    }

    public final Spanned getAdditionalLink2Url() {
        return this.additionalLink2Url;
    }

    public final void setAdditionalLink2Url(Spanned spanned) {
        Intrinsics.checkNotNullParameter(spanned, "<set-?>");
        this.additionalLink2Url = spanned;
    }

    public final String getJobCode() {
        return this.jobCode;
    }

    public final void setJobCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.jobCode = str;
    }

    public void bindVariables(NativeCustomTemplateAd nativeCustomTemplateAd) {
        super.bindVariables(nativeCustomTemplateAd);
        CharSequence label = getLabel();
        boolean z = false;
        if (!(label == null || label.length() == 0)) {
            CharSequence title = getTitle();
            if (!(title == null || title.length() == 0) && !StringsKt.equals(getLabel(), "null", true) && !StringsKt.equals(getTitle(), "null", true)) {
                this.rootLayoutVisibility = true;
                CharSequence charSequence = null;
                this.additionalLink1 = String.valueOf(nativeCustomTemplateAd != null ? nativeCustomTemplateAd.getText("AdditionalLinksText1") : null);
                this.additionalLink1Url = new SpannableString(String.valueOf(nativeCustomTemplateAd != null ? nativeCustomTemplateAd.getText("AdditionalLinksURL1") : null));
                this.additionalLink2 = String.valueOf(nativeCustomTemplateAd != null ? nativeCustomTemplateAd.getText("AdditionalLinksText2") : null);
                this.additionalLink2Url = new SpannableString(String.valueOf(nativeCustomTemplateAd != null ? nativeCustomTemplateAd.getText("AdditionalLinksURL2") : null));
                if (nativeCustomTemplateAd != null) {
                    charSequence = nativeCustomTemplateAd.getText("JobCode");
                }
                this.jobCode = String.valueOf(charSequence);
                if (!(this.additionalLink1.length() > 0) || StringsKt.equals(this.additionalLink1, "null", true)) {
                    this.additionalLink1TxtViewVisibility = false;
                } else {
                    this.additionalLink1TxtViewVisibility = true;
                    Spanned fromHtml = Html.fromHtml("<a href='" + this.additionalLink1Url + "'>" + this.additionalLink1 + "</a>");
                    Intrinsics.checkNotNullExpressionValue(fromHtml, "Html.fromHtml(\"<a href='…l'>$additionalLink1</a>\")");
                    this.additionalLink1Url = fromHtml;
                }
                if (!(this.additionalLink2.length() > 0) || StringsKt.equals(this.additionalLink2, "null", true)) {
                    this.additionalLink2TxtViewVisibility = false;
                } else {
                    this.linkDividerVisibility = true;
                    this.additionalLink2TxtViewVisibility = true;
                    Spanned fromHtml2 = Html.fromHtml("<a href='" + this.additionalLink2Url + "'>" + this.additionalLink2 + "</a>");
                    Intrinsics.checkNotNullExpressionValue(fromHtml2, "Html.fromHtml(\"<a href='…l'>$additionalLink2</a>\")");
                    this.additionalLink2Url = fromHtml2;
                }
                this.linkDividerVisibility = this.additionalLink1TxtViewVisibility && this.additionalLink2TxtViewVisibility;
                if ((this.jobCode.length() > 0) && !StringsKt.equals(this.jobCode, "null", true)) {
                    z = true;
                }
                this.jobCodeVisibility = z;
                return;
            }
        }
        this.rootLayoutVisibility = false;
    }

    public void performClick() {
        NativeCustomTemplateAd nativeAD = getNativeAD();
        if (nativeAD != null) {
            nativeAD.performClick("Title");
        }
    }
}
