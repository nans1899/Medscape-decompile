package com.medscape.android.slideshow;

import com.medscape.android.util.Util;
import net.bytebuddy.description.type.TypeDescription;

public class SlideshowUtil {
    public static String toAppend(String str) {
        if (str == null || !Util.isHoneyCombOrHigher()) {
            return "";
        }
        return str.contains(TypeDescription.Generic.OfWildcardType.SYMBOL) ? "&responsetype=manifest" : "?responsetype=manifest";
    }

    public static String getContentUrlFromSchemeSpecificPart(String str) {
        return (str == null || !str.startsWith("//") || str.length() <= 2) ? "" : str.substring(2);
    }
}
