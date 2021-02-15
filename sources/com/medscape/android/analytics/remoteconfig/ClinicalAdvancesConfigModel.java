package com.medscape.android.analytics.remoteconfig;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigModel;", "", "()V", "speciality", "", "getSpeciality", "()Ljava/lang/String;", "setSpeciality", "(Ljava/lang/String;)V", "url", "getUrl", "setUrl", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClinicalAdvancesConfigModel.kt */
public final class ClinicalAdvancesConfigModel {
    private String speciality = "";
    private String url = "";

    public final String getSpeciality() {
        return this.speciality;
    }

    public final void setSpeciality(String str) {
        this.speciality = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }
}
