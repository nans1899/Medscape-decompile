package com.medscape.android.webmdrx.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/webmdrx/model/RxData;", "", "gp10_s", "", "id", "title", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getGp10_s", "()Ljava/lang/String;", "getId", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RxData.kt */
public final class RxData {
    private final String gp10_s;
    private final String id;
    private final String title;

    public RxData() {
        this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ RxData copy$default(RxData rxData, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rxData.gp10_s;
        }
        if ((i & 2) != 0) {
            str2 = rxData.id;
        }
        if ((i & 4) != 0) {
            str3 = rxData.title;
        }
        return rxData.copy(str, str2, str3);
    }

    public final String component1() {
        return this.gp10_s;
    }

    public final String component2() {
        return this.id;
    }

    public final String component3() {
        return this.title;
    }

    public final RxData copy(String str, String str2, String str3) {
        return new RxData(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RxData)) {
            return false;
        }
        RxData rxData = (RxData) obj;
        return Intrinsics.areEqual((Object) this.gp10_s, (Object) rxData.gp10_s) && Intrinsics.areEqual((Object) this.id, (Object) rxData.id) && Intrinsics.areEqual((Object) this.title, (Object) rxData.title);
    }

    public int hashCode() {
        String str = this.gp10_s;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.id;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.title;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "RxData(gp10_s=" + this.gp10_s + ", id=" + this.id + ", title=" + this.title + ")";
    }

    public RxData(String str, String str2, String str3) {
        this.gp10_s = str;
        this.id = str2;
        this.title = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RxData(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getGp10_s() {
        return this.gp10_s;
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }
}
