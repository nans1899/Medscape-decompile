package com.wbmd.omniture;

import com.facebook.places.model.PlaceFields;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0004J\u0015\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/wbmd/omniture/PageNameFormatter;", "", "page", "", "", "(Ljava/util/List;)V", "formatPageName", "withOutBaseUrl", "", "formatPageName$wbmd_omniture_release", "normalizeString", "replaceSpaceWithDashString", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PageNameFormatter.kt */
public final class PageNameFormatter {
    private List<String> page;

    public PageNameFormatter(List<String> list) {
        Intrinsics.checkNotNullParameter(list, PlaceFields.PAGE);
        this.page = list;
    }

    public final String formatPageName() {
        return formatPageName$wbmd_omniture_release(false);
    }

    public final String formatPageName$wbmd_omniture_release(boolean z) {
        String str;
        String str2;
        OmnitureState instance = OmnitureState.Companion.getInstance();
        IOmnitureAppSettings appSettings = instance.getAppSettings();
        Boolean bool = null;
        if ((appSettings != null ? Boolean.valueOf(appSettings.getNormalizePageNames()) : null).booleanValue()) {
            this.page = normalizeString(this.page);
        }
        IOmnitureAppSettings appSettings2 = instance.getAppSettings();
        if ((appSettings2 != null ? Boolean.valueOf(appSettings2.getReplaceSpaceWithDash()) : null).booleanValue()) {
            this.page = replaceSpaceWithDashString(this.page);
        }
        if (z) {
            str = "";
        } else {
            str = instance.getAppSettings().getBaseUrl();
        }
        if (instance.getCurrentSection().length() > 0) {
            str2 = str + '/' + instance.getCurrentSection() + '/' + CollectionsKt.joinToString$default(this.page, "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        } else {
            str2 = str + '/' + CollectionsKt.joinToString$default(this.page, "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        IOmnitureAppSettings appSettings3 = instance.getAppSettings();
        if ((appSettings3 != null ? Boolean.valueOf(appSettings3.getEnableTrailingForwardSlash()) : null).booleanValue()) {
            str2 = str2 + "/";
        }
        IOmnitureAppSettings appSettings4 = instance.getAppSettings();
        if (appSettings4 != null) {
            bool = Boolean.valueOf(appSettings4.getPageNameToLowerCase());
        }
        if (!bool.booleanValue()) {
            return str2;
        }
        if (str2 != null) {
            String lowerCase = str2.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
            return lowerCase;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    private final List<String> normalizeString(List<String> list) {
        List<String> arrayList = new ArrayList<>();
        for (String replace : list) {
            arrayList.add(new Regex("[^A-Za-z0-9-]").replace((CharSequence) replace, ""));
        }
        return arrayList;
    }

    private final List<String> replaceSpaceWithDashString(List<String> list) {
        List<String> arrayList = new ArrayList<>();
        for (String replace$default : list) {
            arrayList.add(StringsKt.replace$default(replace$default, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-", false, 4, (Object) null));
        }
        return arrayList;
    }
}
