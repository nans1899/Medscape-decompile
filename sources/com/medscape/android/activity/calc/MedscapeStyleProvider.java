package com.medscape.android.activity.calc;

import com.wbmd.qxcalculator.QxContentStyleProvider;
import kotlin.Metadata;
import org.apache.commons.io.IOUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/activity/calc/MedscapeStyleProvider;", "Lcom/wbmd/qxcalculator/QxContentStyleProvider;", "()V", "moreInfoCSSString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeStyleProvider.kt */
public final class MedscapeStyleProvider implements QxContentStyleProvider {
    public String moreInfoCSSString() {
        return "body{background-color:#ffffff; color:#4A4A4A;}" + IOUtils.LINE_SEPARATOR_UNIX + "p{font-family:roboto_regular; font-size:16px;}" + IOUtils.LINE_SEPARATOR_UNIX + "a{color:#007CB0;text-decoration:none}";
    }
}
