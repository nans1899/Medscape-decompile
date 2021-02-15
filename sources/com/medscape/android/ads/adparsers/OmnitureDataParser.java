package com.medscape.android.ads.adparsers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/ads/adparsers/OmnitureDataParser;", "", "()V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureDataParser.kt */
public final class OmnitureDataParser {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/ads/adparsers/OmnitureDataParser$Companion;", "", "()V", "parseOmnitureData", "Lcom/medscape/android/ads/adparsers/OmnitureDataModel;", "stringData", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: OmnitureDataParser.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final OmnitureDataModel parseOmnitureData(String str) {
            if (str == null) {
                return null;
            }
            if (str.length() == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("mmodule", "");
            String optString2 = jSONObject.optString("exiturl", "");
            String optString3 = jSONObject.optString("mlink", "");
            Intrinsics.checkNotNullExpressionValue(optString, "module");
            Intrinsics.checkNotNullExpressionValue(optString2, "pageview");
            Intrinsics.checkNotNullExpressionValue(optString3, "mlink");
            return new OmnitureDataModel(optString, optString2, optString3);
        }
    }
}
