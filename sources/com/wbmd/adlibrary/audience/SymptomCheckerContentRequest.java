package com.wbmd.adlibrary.audience;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/wbmd/adlibrary/audience/SymptomCheckerContentRequest;", "Lcom/wbmd/adlibrary/audience/ContentRequest;", "()V", "businessReference", "", "getBusinessReference", "()Ljava/lang/String;", "setBusinessReference", "(Ljava/lang/String;)V", "packageName", "getPackageName", "setPackageName", "packageType", "getPackageType", "setPackageType", "stageCondition", "getStageCondition", "setStageCondition", "wbmdadlibrary_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SymptomCheckerContentRequest.kt */
public final class SymptomCheckerContentRequest extends ContentRequest {
    private String businessReference = "Symptom Checker";
    private String packageName = "core-symptom-checker";
    private String packageType = "symptom checker - nosp";
    private String stageCondition = "";

    public final String getStageCondition() {
        return this.stageCondition;
    }

    public final void setStageCondition(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stageCondition = str;
    }

    public final String getBusinessReference() {
        return this.businessReference;
    }

    public final void setBusinessReference(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.businessReference = str;
    }

    public String getPackageType() {
        return this.packageType;
    }

    public void setPackageType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.packageType = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.packageName = str;
    }
}
