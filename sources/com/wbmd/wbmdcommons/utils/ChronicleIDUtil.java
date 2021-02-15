package com.wbmd.wbmdcommons.utils;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004¨\u0006\b"}, d2 = {"Lcom/wbmd/wbmdcommons/utils/ChronicleIDUtil;", "", "()V", "generateAssetId", "", "articleId", "assetId", "uri", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ChronicleIDUtil.kt */
public final class ChronicleIDUtil {
    public final String generateAssetId(String str, String str2, String str3) {
        CharSequence charSequence = str;
        if (!(charSequence == null || charSequence.length() == 0)) {
            CharSequence charSequence2 = str2;
            if (!(charSequence2 == null || charSequence2.length() == 0)) {
                CharSequence charSequence3 = str3;
                if (!(charSequence3 == null || charSequence3.length() == 0)) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("['%s'], ['%s'], ['%s']", Arrays.copyOf(new Object[]{str, str2, str3}, 3));
                    Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
                    return format;
                }
            }
        }
        if (charSequence == null || charSequence.length() == 0) {
            CharSequence charSequence4 = str2;
            if (!(charSequence4 == null || charSequence4.length() == 0)) {
                CharSequence charSequence5 = str3;
                if (!(charSequence5 == null || charSequence5.length() == 0)) {
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String format2 = String.format("[], ['%s'], ['%s']", Arrays.copyOf(new Object[]{str2, str3}, 2));
                    Intrinsics.checkNotNullExpressionValue(format2, "java.lang.String.format(format, *args)");
                    return format2;
                }
            }
        }
        if (charSequence == null || charSequence.length() == 0) {
            CharSequence charSequence6 = str2;
            if (charSequence6 == null || charSequence6.length() == 0) {
                CharSequence charSequence7 = str3;
                if (!(charSequence7 == null || charSequence7.length() == 0)) {
                    StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                    String format3 = String.format("[], [], ['%s']", Arrays.copyOf(new Object[]{str3}, 1));
                    Intrinsics.checkNotNullExpressionValue(format3, "java.lang.String.format(format, *args)");
                    return format3;
                }
            }
        }
        if (charSequence == null || charSequence.length() == 0) {
            CharSequence charSequence8 = str2;
            if (charSequence8 == null || charSequence8.length() == 0) {
                CharSequence charSequence9 = str3;
                if (charSequence9 == null || charSequence9.length() == 0) {
                    StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                    String format4 = String.format("[], [], []", Arrays.copyOf(new Object[0], 0));
                    Intrinsics.checkNotNullExpressionValue(format4, "java.lang.String.format(format, *args)");
                    return format4;
                }
            }
        }
        if (charSequence == null || charSequence.length() == 0) {
            CharSequence charSequence10 = str2;
            if (!(charSequence10 == null || charSequence10.length() == 0)) {
                CharSequence charSequence11 = str3;
                if (charSequence11 == null || charSequence11.length() == 0) {
                    StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                    String format5 = String.format("[], ['%s'], []", Arrays.copyOf(new Object[]{str2}, 1));
                    Intrinsics.checkNotNullExpressionValue(format5, "java.lang.String.format(format, *args)");
                    return format5;
                }
            }
        }
        if (!(charSequence == null || charSequence.length() == 0)) {
            CharSequence charSequence12 = str2;
            if (charSequence12 == null || charSequence12.length() == 0) {
                CharSequence charSequence13 = str3;
                if (charSequence13 == null || charSequence13.length() == 0) {
                    StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                    String format6 = String.format("['%s'], [], []", Arrays.copyOf(new Object[]{str}, 1));
                    Intrinsics.checkNotNullExpressionValue(format6, "java.lang.String.format(format, *args)");
                    return format6;
                }
            }
        }
        if (!(charSequence == null || charSequence.length() == 0)) {
            CharSequence charSequence14 = str2;
            if (!(charSequence14 == null || charSequence14.length() == 0)) {
                CharSequence charSequence15 = str3;
                if (charSequence15 == null || charSequence15.length() == 0) {
                    StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                    String format7 = String.format("['%s'], ['%s'], []", Arrays.copyOf(new Object[]{str, str2}, 2));
                    Intrinsics.checkNotNullExpressionValue(format7, "java.lang.String.format(format, *args)");
                    return format7;
                }
            }
        }
        if (charSequence == null || charSequence.length() == 0) {
            return null;
        }
        CharSequence charSequence16 = str2;
        if (!(charSequence16 == null || charSequence16.length() == 0)) {
            return null;
        }
        CharSequence charSequence17 = str3;
        if (charSequence17 == null || charSequence17.length() == 0) {
            return null;
        }
        StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
        String format8 = String.format("['%s'], [], ['%s']", Arrays.copyOf(new Object[]{str, str3}, 2));
        Intrinsics.checkNotNullExpressionValue(format8, "java.lang.String.format(format, *args)");
        return format8;
    }
}
