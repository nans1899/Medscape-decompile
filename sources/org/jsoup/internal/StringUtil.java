package org.jsoup.internal;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import net.bytebuddy.description.type.TypeDescription;
import org.jsoup.helper.Validate;

public final class StringUtil {
    private static final int MaxCachedBuilderSize = 8192;
    private static final int MaxIdleBuilders = 8;
    private static final Stack<StringBuilder> builders = new Stack<>();
    static final String[] padding = {"", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ", "           ", "            ", "             ", "              ", "               ", "                ", "                 ", "                  ", "                   ", "                    "};

    public static boolean isActuallyWhitespace(int i) {
        return i == 32 || i == 9 || i == 10 || i == 12 || i == 13 || i == 160;
    }

    public static boolean isInvisibleChar(int i) {
        return i == 8203 || i == 173;
    }

    public static boolean isWhitespace(int i) {
        return i == 32 || i == 9 || i == 10 || i == 12 || i == 13;
    }

    public static String join(Collection collection, String str) {
        return join(collection.iterator(), str);
    }

    public static String join(Iterator it, String str) {
        if (!it.hasNext()) {
            return "";
        }
        String obj = it.next().toString();
        if (!it.hasNext()) {
            return obj;
        }
        StringBuilder borrowBuilder = borrowBuilder();
        borrowBuilder.append(obj);
        while (it.hasNext()) {
            borrowBuilder.append(str);
            borrowBuilder.append(it.next());
        }
        return releaseBuilder(borrowBuilder);
    }

    public static String join(String[] strArr, String str) {
        return join((Collection) Arrays.asList(strArr), str);
    }

    public static String padding(int i) {
        if (i >= 0) {
            String[] strArr = padding;
            if (i < strArr.length) {
                return strArr[i];
            }
            char[] cArr = new char[i];
            for (int i2 = 0; i2 < i; i2++) {
                cArr[i2] = ' ';
            }
            return String.valueOf(cArr);
        }
        throw new IllegalArgumentException("width must be > 0");
    }

    public static boolean isBlank(String str) {
        if (!(str == null || str.length() == 0)) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                if (!isWhitespace(str.codePointAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String normaliseWhitespace(String str) {
        StringBuilder borrowBuilder = borrowBuilder();
        appendNormalisedWhitespace(borrowBuilder, str, false);
        return releaseBuilder(borrowBuilder);
    }

    public static void appendNormalisedWhitespace(StringBuilder sb, String str, boolean z) {
        int length = str.length();
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (isActuallyWhitespace(codePointAt)) {
                if ((!z || z2) && !z3) {
                    sb.append(' ');
                    z3 = true;
                }
            } else if (!isInvisibleChar(codePointAt)) {
                sb.appendCodePoint(codePointAt);
                z2 = true;
                z3 = false;
            }
            i += Character.charCount(codePointAt);
        }
    }

    public static boolean in(String str, String... strArr) {
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean inSorted(String str, String[] strArr) {
        return Arrays.binarySearch(strArr, str) >= 0;
    }

    public static URL resolve(URL url, String str) throws MalformedURLException {
        if (str.startsWith(TypeDescription.Generic.OfWildcardType.SYMBOL)) {
            str = url.getPath() + str;
        }
        if (str.indexOf(46) == 0 && url.getFile().indexOf(47) != 0) {
            url = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/" + url.getFile());
        }
        return new URL(url, str);
    }

    public static String resolve(String str, String str2) {
        try {
            try {
                return resolve(new URL(str), str2).toExternalForm();
            } catch (MalformedURLException unused) {
                return "";
            }
        } catch (MalformedURLException unused2) {
            return new URL(str2).toExternalForm();
        }
    }

    public static StringBuilder borrowBuilder() {
        StringBuilder sb;
        synchronized (builders) {
            if (builders.empty()) {
                sb = new StringBuilder(8192);
            } else {
                sb = builders.pop();
            }
        }
        return sb;
    }

    public static String releaseBuilder(StringBuilder sb) {
        Validate.notNull(sb);
        String sb2 = sb.toString();
        if (sb.length() > 8192) {
            sb = new StringBuilder(8192);
        } else {
            sb.delete(0, sb.length());
        }
        synchronized (builders) {
            builders.push(sb);
            while (builders.size() > 8) {
                builders.pop();
            }
        }
        return sb2;
    }
}
