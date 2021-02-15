package com.webmd.wbmdcmepulse.live_events.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/model/SpecialityModel;", "", "id", "", "liveeventid", "", "(ILjava/lang/String;)V", "getId", "()I", "setId", "(I)V", "getLiveeventid", "()Ljava/lang/String;", "setLiveeventid", "(Ljava/lang/String;)V", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SpecialityModel.kt */
public final class SpecialityModel {
    private int id;
    private String liveeventid;

    public SpecialityModel(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "liveeventid");
        this.id = i;
        this.liveeventid = str;
    }

    public final int getId() {
        return this.id;
    }

    public final String getLiveeventid() {
        return this.liveeventid;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final void setLiveeventid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.liveeventid = str;
    }
}
