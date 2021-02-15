package com.medscape.android.ads.adparsers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/medscape/android/ads/adparsers/OmnitureDataModel;", "", "mmodule", "", "exiturl", "mlink", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getExiturl", "()Ljava/lang/String;", "setExiturl", "(Ljava/lang/String;)V", "getMlink", "setMlink", "getMmodule", "setMmodule", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureDataModel.kt */
public final class OmnitureDataModel {
    private String exiturl;
    private String mlink;
    private String mmodule;

    public OmnitureDataModel() {
        this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ OmnitureDataModel copy$default(OmnitureDataModel omnitureDataModel, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = omnitureDataModel.mmodule;
        }
        if ((i & 2) != 0) {
            str2 = omnitureDataModel.exiturl;
        }
        if ((i & 4) != 0) {
            str3 = omnitureDataModel.mlink;
        }
        return omnitureDataModel.copy(str, str2, str3);
    }

    public final String component1() {
        return this.mmodule;
    }

    public final String component2() {
        return this.exiturl;
    }

    public final String component3() {
        return this.mlink;
    }

    public final OmnitureDataModel copy(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "mmodule");
        Intrinsics.checkNotNullParameter(str2, "exiturl");
        Intrinsics.checkNotNullParameter(str3, "mlink");
        return new OmnitureDataModel(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OmnitureDataModel)) {
            return false;
        }
        OmnitureDataModel omnitureDataModel = (OmnitureDataModel) obj;
        return Intrinsics.areEqual((Object) this.mmodule, (Object) omnitureDataModel.mmodule) && Intrinsics.areEqual((Object) this.exiturl, (Object) omnitureDataModel.exiturl) && Intrinsics.areEqual((Object) this.mlink, (Object) omnitureDataModel.mlink);
    }

    public int hashCode() {
        String str = this.mmodule;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.exiturl;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mlink;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "OmnitureDataModel(mmodule=" + this.mmodule + ", exiturl=" + this.exiturl + ", mlink=" + this.mlink + ")";
    }

    public OmnitureDataModel(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "mmodule");
        Intrinsics.checkNotNullParameter(str2, "exiturl");
        Intrinsics.checkNotNullParameter(str3, "mlink");
        this.mmodule = str;
        this.exiturl = str2;
        this.mlink = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OmnitureDataModel(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3);
    }

    public final String getExiturl() {
        return this.exiturl;
    }

    public final String getMlink() {
        return this.mlink;
    }

    public final String getMmodule() {
        return this.mmodule;
    }

    public final void setExiturl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.exiturl = str;
    }

    public final void setMlink(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mlink = str;
    }

    public final void setMmodule(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mmodule = str;
    }
}
