package com.medscape.android.activity.calc;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u0012\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0002J\u001a\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bJ,\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0010J\u000e\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b¨\u0006\u001c"}, d2 = {"Lcom/medscape/android/activity/calc/UtilCalc;", "", "()V", "getLastVisiblePosition", "", "rv", "Landroidx/recyclerview/widget/RecyclerView;", "getMLinkForOmniture", "", "category", "getMLinkFromCategoryName", "categoryName", "getSubStringForOmniture", "calcName", "length", "isValidLength", "", "name", "setBannerAdVisibility", "", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "listView", "adLayout", "Landroid/view/View;", "adCallComplete", "validateString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: UtilCalc.kt */
public final class UtilCalc {
    public final String getMLinkForOmniture(String str) {
        CharSequence charSequence = str;
        return !(charSequence == null || charSequence.length() == 0) ? getMLinkFromCategoryName(str) : "";
    }

    private final String getMLinkFromCategoryName(String str) {
        if (str != null) {
            Object[] array = StringsKt.split$default((CharSequence) str, new String[]{MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                int length = strArr.length;
                if (length > 1) {
                    String validateString = validateString(strArr[0]);
                    String validateString2 = validateString(strArr[1]);
                    if (!isValidLength(validateString) || !isValidLength(validateString2)) {
                        if (!isValidLength(validateString) || isValidLength(validateString2)) {
                            if (isValidLength(validateString) || !isValidLength(validateString2)) {
                                String str2 = validateString + validateString2;
                                Locale locale = Locale.ROOT;
                                Intrinsics.checkNotNullExpressionValue(locale, "Locale.ROOT");
                                if (str2 != null) {
                                    String lowerCase = str2.toLowerCase(locale);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                                    return lowerCase;
                                }
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                            } else if (strArr.length <= 2 || !isValidLength(strArr[2])) {
                                return getSubStringForOmniture(validateString2, length);
                            } else {
                                return getSubStringForOmniture(validateString2, length) + getSubStringForOmniture(validateString(strArr[2]), length);
                            }
                        } else if (Intrinsics.areEqual((Object) validateString2, (Object) "-")) {
                            return getSubStringForOmniture(validateString, 1);
                        } else {
                            if (strArr.length <= 2 || !isValidLength(strArr[2])) {
                                return getSubStringForOmniture(validateString, length);
                            }
                            return getSubStringForOmniture(validateString, length) + getSubStringForOmniture(validateString(strArr[2]), length);
                        }
                    } else if (StringsKt.startsWith$default(strArr[1], "(", false, 2, (Object) null)) {
                        return getSubStringForOmniture(validateString, 1);
                    } else {
                        return getSubStringForOmniture(validateString, length) + getSubStringForOmniture(validateString2, length);
                    }
                } else if (length == 1) {
                    String validateString3 = validateString(strArr[0]);
                    if (isValidLength(validateString3)) {
                        return getSubStringForOmniture(validateString3, length);
                    }
                    Locale locale2 = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(locale2, "Locale.ROOT");
                    if (validateString3 != null) {
                        String lowerCase2 = validateString3.toLowerCase(locale2);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
                        return lowerCase2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        return "";
    }

    public final boolean isValidLength(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return str.length() >= 2;
    }

    public final String validateString(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return str.length() >= 2 ? StringsKt.replace$default(str, "[^A-Za-z0-9]", "", false, 4, (Object) null) : str;
    }

    private final String getSubStringForOmniture(String str, int i) {
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            return "";
        }
        if (i == 1) {
            if (str != null) {
                String substring = str.substring(0, 2);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Locale locale = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(locale, "Locale.ROOT");
                if (substring != null) {
                    String lowerCase = substring.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                    return lowerCase;
                }
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        } else if (str != null) {
            String substring2 = str.substring(0, 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            Locale locale2 = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(locale2, "Locale.ROOT");
            if (substring2 != null) {
                String lowerCase2 = substring2.toLowerCase(locale2);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
                return lowerCase2;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        } else {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
    }

    public final int getLastVisiblePosition(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || !(layoutManager instanceof LinearLayoutManager)) {
            return 0;
        }
        return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
    }

    public final void setBannerAdVisibility(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, RecyclerView recyclerView, View view, boolean z) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(recyclerView, "listView");
        Intrinsics.checkNotNullParameter(view, "adLayout");
        if (new UtilCalc().getLastVisiblePosition(recyclerView) + 2 <= adapter.getItemCount() || !z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }
}
