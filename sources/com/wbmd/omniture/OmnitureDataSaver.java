package com.wbmd.omniture;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006¨\u0006\b"}, d2 = {"Lcom/wbmd/omniture/OmnitureDataSaver;", "", "()V", "saveUserData", "", "data", "", "", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureDataSaver.kt */
public final class OmnitureDataSaver {
    public final void saveUserData(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "data");
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.regid", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.regid")));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.dcntry", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.dcntry")));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.dprofsn", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.dprofsn")));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.dspclty", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.dspclty")));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.site", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.site")));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.dprofsnid", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.dprofsnid")));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.dspcltyid", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.dspcltyid")));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, OmnitureKeys.AFFILIATE_USER_ID, String.valueOf(OmnitureState.Companion.getInstance().getUserData().get(OmnitureKeys.AFFILIATE_USER_ID)));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.chn", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.chn")));
        ExtensionsKt.addIfValueInMapIsNullOrEmpty(map, "wapp.doccptnid", String.valueOf(OmnitureState.Companion.getInstance().getUserData().get("wapp.doccptnid")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.regid", String.valueOf(map.get("wapp.regid")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.dcntry", String.valueOf(map.get("wapp.dcntry")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.dprofsn", String.valueOf(map.get("wapp.dprofsn")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.dspclty", String.valueOf(map.get("wapp.dspclty")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.site", String.valueOf(map.get("wapp.site")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.dprofsnid", String.valueOf(map.get("wapp.dprofsnid")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.dspcltyid", String.valueOf(map.get("wapp.dspcltyid")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), OmnitureKeys.AFFILIATE_USER_ID, String.valueOf(map.get(OmnitureKeys.AFFILIATE_USER_ID)));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.chn", String.valueOf(map.get("wapp.chn")));
        ExtensionsKt.addIfValueIsNotNullOrEmpty(OmnitureState.Companion.getInstance().getUserData(), "wapp.doccptnid", String.valueOf(map.get("wapp.doccptnid")));
    }
}
