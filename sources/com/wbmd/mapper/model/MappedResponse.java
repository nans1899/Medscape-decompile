package com.wbmd.mapper.model;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/wbmd/mapper/model/MappedResponse;", "T", "", "version", "", "data", "Lcom/wbmd/mapper/model/MappedDataObject;", "(ILcom/wbmd/mapper/model/MappedDataObject;)V", "getData", "()Lcom/wbmd/mapper/model/MappedDataObject;", "setData", "(Lcom/wbmd/mapper/model/MappedDataObject;)V", "getVersion", "()I", "setVersion", "(I)V", "wbmdmapper_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MappedResponse.kt */
public final class MappedResponse<T> {
    @SerializedName("data")
    private MappedDataObject data;
    @SerializedName("version")
    private int version;

    public MappedResponse() {
        this(0, (MappedDataObject) null, 3, (DefaultConstructorMarker) null);
    }

    public MappedResponse(int i, MappedDataObject mappedDataObject) {
        this.version = i;
        this.data = mappedDataObject;
    }

    public final int getVersion() {
        return this.version;
    }

    public final void setVersion(int i) {
        this.version = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MappedResponse(int i, MappedDataObject mappedDataObject, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? null : mappedDataObject);
    }

    public final MappedDataObject getData() {
        return this.data;
    }

    public final void setData(MappedDataObject mappedDataObject) {
        this.data = mappedDataObject;
    }
}
