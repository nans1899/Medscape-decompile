package com.webmd.wbmdproffesionalauthentication.license;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J3\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\r¨\u0006 "}, d2 = {"Lcom/webmd/wbmdproffesionalauthentication/license/LicenseResponse;", "", "errorCode", "", "errorDescription", "", "status", "isValid", "", "(ILjava/lang/String;IZ)V", "getErrorCode", "()I", "setErrorCode", "(I)V", "getErrorDescription", "()Ljava/lang/String;", "setErrorDescription", "(Ljava/lang/String;)V", "()Z", "setValid", "(Z)V", "getStatus", "setStatus", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "wbmdprofessionalauthenticaion_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LicenseResponse.kt */
public final class LicenseResponse {
    private int errorCode;
    private String errorDescription;
    private boolean isValid;
    private int status;

    public static /* synthetic */ LicenseResponse copy$default(LicenseResponse licenseResponse, int i, String str, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = licenseResponse.errorCode;
        }
        if ((i3 & 2) != 0) {
            str = licenseResponse.errorDescription;
        }
        if ((i3 & 4) != 0) {
            i2 = licenseResponse.status;
        }
        if ((i3 & 8) != 0) {
            z = licenseResponse.isValid;
        }
        return licenseResponse.copy(i, str, i2, z);
    }

    public final int component1() {
        return this.errorCode;
    }

    public final String component2() {
        return this.errorDescription;
    }

    public final int component3() {
        return this.status;
    }

    public final boolean component4() {
        return this.isValid;
    }

    public final LicenseResponse copy(int i, String str, int i2, boolean z) {
        return new LicenseResponse(i, str, i2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LicenseResponse)) {
            return false;
        }
        LicenseResponse licenseResponse = (LicenseResponse) obj;
        return this.errorCode == licenseResponse.errorCode && Intrinsics.areEqual((Object) this.errorDescription, (Object) licenseResponse.errorDescription) && this.status == licenseResponse.status && this.isValid == licenseResponse.isValid;
    }

    public int hashCode() {
        int i = this.errorCode * 31;
        String str = this.errorDescription;
        int hashCode = (((i + (str != null ? str.hashCode() : 0)) * 31) + this.status) * 31;
        boolean z = this.isValid;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "LicenseResponse(errorCode=" + this.errorCode + ", errorDescription=" + this.errorDescription + ", status=" + this.status + ", isValid=" + this.isValid + ")";
    }

    public LicenseResponse(int i, String str, int i2, boolean z) {
        this.errorCode = i;
        this.errorDescription = str;
        this.status = i2;
        this.isValid = z;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final void setErrorCode(int i) {
        this.errorCode = i;
    }

    public final String getErrorDescription() {
        return this.errorDescription;
    }

    public final void setErrorDescription(String str) {
        this.errorDescription = str;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }

    public final boolean isValid() {
        return this.isValid;
    }

    public final void setValid(boolean z) {
        this.isValid = z;
    }
}
