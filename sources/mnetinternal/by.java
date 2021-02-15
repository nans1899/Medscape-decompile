package mnetinternal;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.internal.ServerProtocol;

public final class by {

    public static class b {
        private static String a = "<iframe src=\"%s\" data-top=\"%d%%\" data-left=\"%d%%\" data-bottom=\"%d%%\" data-right=\"%d%%\"></iframe>";
        private static String b = "<iframe data-top=\"%d%%\" data-left=\"%d%%\" data-bottom=\"%d%%\" data-right=\"%d%%\">%s</iframe>";

        public static String a(String str, int i, int i2, int i3, int i4) {
            return String.format(a, new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
        }
    }

    public static class a {
        private static String a = "<b visibility='%s' font-size='%d'>%s</b>";
        private static String b = "<b name='%s' id='%d' visibility='%s' font-size='%d'>%s</b>";

        public static String a(TextView textView, int i) {
            String str = textView.getVisibility() == 0 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
            String htmlEncode = TextUtils.htmlEncode(textView.getText().toString());
            if (textView.getId() == -1) {
                return String.format(a, new Object[]{str, Integer.valueOf(i), htmlEncode});
            }
            return String.format(b, new Object[]{ca.a((View) textView), Integer.valueOf(textView.getId()), str, Integer.valueOf(i), htmlEncode});
        }
    }

    public static class c {
        private static String a = "<i visibility='%s' font-size='%d'>%s</i>";
        private static String b = "<i name='%s' id='%d' visibility='%s' font-size='%d'>%s</i>";

        public static String a(TextView textView, int i) {
            String str = textView.getVisibility() == 0 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
            String htmlEncode = TextUtils.htmlEncode(textView.getText().toString());
            if (textView.getId() == -1) {
                return String.format(a, new Object[]{str, Integer.valueOf(i), htmlEncode});
            }
            return String.format(b, new Object[]{ca.a((View) textView), Integer.valueOf(textView.getId()), str, Integer.valueOf(i), htmlEncode});
        }
    }

    public static class d {
        private static String a = "<p visibility='%s' font-size='%d'>%s</p>";
        private static String b = "<p name='%s' id='%d' visibility='%s' font-size='%d'>%s</p>";

        public static String a(TextView textView, int i) {
            String str = textView.getVisibility() == 0 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
            String htmlEncode = TextUtils.htmlEncode(textView.getText().toString());
            if (textView.getId() == -1) {
                return String.format(a, new Object[]{str, Integer.valueOf(i), htmlEncode});
            }
            return String.format(b, new Object[]{ca.a((View) textView), Integer.valueOf(textView.getId()), str, Integer.valueOf(i), htmlEncode});
        }
    }
}
