package com.medscape.android.landingfeed.model;

import java.io.Serializable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/medscape/android/landingfeed/model/Eligibility;", "Ljava/io/Serializable;", "type", "", "credits", "", "(Ljava/lang/String;Ljava/lang/Float;)V", "getCredits", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getType", "()Ljava/lang/String;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Eligibility.kt */
public class Eligibility implements Serializable {
    private final Float credits;
    private final String type;

    public Eligibility() {
        this((String) null, (Float) null, 3, (DefaultConstructorMarker) null);
    }

    public Eligibility(String str, Float f) {
        this.type = str;
        this.credits = f;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Eligibility(String str, Float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : f);
    }

    public final String getType() {
        return this.type;
    }

    public final Float getCredits() {
        return this.credits;
    }
}
