package com.wbmd.ads.model;

import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\t\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\r\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005J\"\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/wbmd/ads/model/AdContentData;", "", "()V", "data", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "addLeadConcept", "leadConcept", "addLeadSpeciality", "leadSpeciality", "addRelatedConcept", "relatedConcept", "addRelatedSpeciality", "relatedSpeciality", "getData", "Companion", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdContentData.kt */
public final class AdContentData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String LEAD_CONCEPT = "scg";
    public static final String LEAD_SPECIALITY = "ssp";
    public static final String RELATED_CONCEPT = "ac";
    public static final String RELATED_SPECIALITY = "as";
    private HashMap<String, String> data = new HashMap<>();

    public final AdContentData addLeadConcept(String str) {
        AdContentData adContentData = this;
        if (str != null) {
            adContentData.data.put(LEAD_CONCEPT, str);
        }
        return adContentData;
    }

    public final AdContentData addRelatedConcept(String str) {
        AdContentData adContentData = this;
        if (str != null) {
            adContentData.data.put("ac", str);
        }
        return adContentData;
    }

    public final AdContentData addLeadSpeciality(String str) {
        AdContentData adContentData = this;
        if (str != null) {
            adContentData.data.put(LEAD_SPECIALITY, str);
        }
        return adContentData;
    }

    public final AdContentData addRelatedSpeciality(String str) {
        AdContentData adContentData = this;
        if (str != null) {
            adContentData.data.put(RELATED_SPECIALITY, str);
        }
        return adContentData;
    }

    public final HashMap<String, String> getData() {
        return this.data;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/wbmd/ads/model/AdContentData$Companion;", "", "()V", "LEAD_CONCEPT", "", "LEAD_SPECIALITY", "RELATED_CONCEPT", "RELATED_SPECIALITY", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdContentData.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
