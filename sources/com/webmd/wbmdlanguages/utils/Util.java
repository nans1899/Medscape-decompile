package com.webmd.wbmdlanguages.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/webmd/wbmdlanguages/utils/Util;", "", "()V", "updateLocale", "", "currentLanguage", "", "context", "Landroid/content/Context;", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Util.kt */
public final class Util {
    public final void updateLocale(String str, Context context) {
        Intrinsics.checkNotNullParameter(str, "currentLanguage");
        Intrinsics.checkNotNullParameter(context, "context");
        String str2 = "en";
        switch (str.hashCode()) {
            case -1463714219:
                if (str.equals("Portuguese")) {
                    str2 = AdParameterKeys.PRIMARY_ID;
                    break;
                }
                break;
            case -347177772:
                if (str.equals("Spanish")) {
                    str2 = "es";
                    break;
                }
                break;
            case 60895824:
                boolean equals = str.equals("English");
                break;
            case 2112439738:
                if (str.equals("French")) {
                    str2 = "fr";
                    break;
                }
                break;
            case 2129449382:
                if (str.equals("German")) {
                    str2 = "de";
                    break;
                }
                break;
        }
        Locale locale = new Locale(str2);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        Resources resources = context.getResources();
        Resources resources2 = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources2, "context.resources");
        resources.updateConfiguration(configuration, resources2.getDisplayMetrics());
    }
}
