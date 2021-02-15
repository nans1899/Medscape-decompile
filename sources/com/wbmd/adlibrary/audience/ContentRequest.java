package com.wbmd.adlibrary.audience;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0018\u0010\u0003\u001a\u00020\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\u00020\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/wbmd/adlibrary/audience/ContentRequest;", "", "()V", "packageName", "", "getPackageName", "()Ljava/lang/String;", "setPackageName", "(Ljava/lang/String;)V", "packageType", "getPackageType", "setPackageType", "primaryTopicId", "getPrimaryTopicId", "setPrimaryTopicId", "sponsorBrand", "getSponsorBrand", "setSponsorBrand", "sponsorProgram", "getSponsorProgram", "setSponsorProgram", "wbmdadlibrary_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ContentRequest.kt */
public abstract class ContentRequest {
    public String primaryTopicId;
    private String sponsorBrand = "";
    private String sponsorProgram = "";

    public abstract String getPackageName();

    public abstract String getPackageType();

    public abstract void setPackageName(String str);

    public abstract void setPackageType(String str);

    public final String getPrimaryTopicId() {
        String str = this.primaryTopicId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("primaryTopicId");
        }
        return str;
    }

    public final void setPrimaryTopicId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.primaryTopicId = str;
    }

    public final String getSponsorBrand() {
        return this.sponsorBrand;
    }

    public final void setSponsorBrand(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sponsorBrand = str;
    }

    public final String getSponsorProgram() {
        return this.sponsorProgram;
    }

    public final void setSponsorProgram(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sponsorProgram = str;
    }
}
