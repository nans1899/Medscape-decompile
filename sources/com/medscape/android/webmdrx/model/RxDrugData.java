package com.medscape.android.webmdrx.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/medscape/android/webmdrx/model/RxDrugData;", "", "code", "", "data", "Lcom/medscape/android/webmdrx/model/RxData;", "status", "", "(Ljava/lang/Integer;Lcom/medscape/android/webmdrx/model/RxData;Ljava/lang/String;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getData", "()Lcom/medscape/android/webmdrx/model/RxData;", "getStatus", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Lcom/medscape/android/webmdrx/model/RxData;Ljava/lang/String;)Lcom/medscape/android/webmdrx/model/RxDrugData;", "equals", "", "other", "hashCode", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RxDrugData.kt */
public final class RxDrugData {
    private final Integer code;
    private final RxData data;
    private final String status;

    public RxDrugData() {
        this((Integer) null, (RxData) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ RxDrugData copy$default(RxDrugData rxDrugData, Integer num, RxData rxData, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = rxDrugData.code;
        }
        if ((i & 2) != 0) {
            rxData = rxDrugData.data;
        }
        if ((i & 4) != 0) {
            str = rxDrugData.status;
        }
        return rxDrugData.copy(num, rxData, str);
    }

    public final Integer component1() {
        return this.code;
    }

    public final RxData component2() {
        return this.data;
    }

    public final String component3() {
        return this.status;
    }

    public final RxDrugData copy(Integer num, RxData rxData, String str) {
        return new RxDrugData(num, rxData, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RxDrugData)) {
            return false;
        }
        RxDrugData rxDrugData = (RxDrugData) obj;
        return Intrinsics.areEqual((Object) this.code, (Object) rxDrugData.code) && Intrinsics.areEqual((Object) this.data, (Object) rxDrugData.data) && Intrinsics.areEqual((Object) this.status, (Object) rxDrugData.status);
    }

    public int hashCode() {
        Integer num = this.code;
        int i = 0;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        RxData rxData = this.data;
        int hashCode2 = (hashCode + (rxData != null ? rxData.hashCode() : 0)) * 31;
        String str = this.status;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "RxDrugData(code=" + this.code + ", data=" + this.data + ", status=" + this.status + ")";
    }

    public RxDrugData(Integer num, RxData rxData, String str) {
        this.code = num;
        this.data = rxData;
        this.status = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RxDrugData(Integer num, RxData rxData, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : rxData, (i & 4) != 0 ? null : str);
    }

    public final Integer getCode() {
        return this.code;
    }

    public final RxData getData() {
        return this.data;
    }

    public final String getStatus() {
        return this.status;
    }
}
