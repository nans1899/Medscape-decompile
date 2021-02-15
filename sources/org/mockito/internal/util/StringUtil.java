package org.mockito.internal.util;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

public class StringUtil {
    private static final Pattern CAPS = Pattern.compile("([A-Z\\d][^A-Z\\d]*)");

    private StringUtil() {
    }

    public static String removeFirstLine(String str) {
        return str.replaceFirst(".*?\n", "");
    }

    public static String join(Object... objArr) {
        return join(IOUtils.LINE_SEPARATOR_UNIX, Arrays.asList(objArr));
    }

    public static String join(String str, Collection<?> collection) {
        return join(str, "", collection);
    }

    public static String join(String str, String str2, Collection<?> collection) {
        if (collection.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str);
        for (Object next : collection) {
            sb.append(str2);
            sb.append(next);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String decamelizeMatcher(String str) {
        if (str.length() == 0) {
            return "<custom argument matcher>";
        }
        String decamelizeClassName = decamelizeClassName(str);
        if (decamelizeClassName.length() == 0) {
            return HtmlObject.HtmlMarkUp.OPEN_BRACKER + str + HtmlObject.HtmlMarkUp.CLOSE_BRACKER;
        }
        return HtmlObject.HtmlMarkUp.OPEN_BRACKER + decamelizeClassName + HtmlObject.HtmlMarkUp.CLOSE_BRACKER;
    }

    private static String decamelizeClassName(String str) {
        Matcher matcher = CAPS.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            if (sb.length() == 0) {
                sb.append(matcher.group());
            } else {
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(matcher.group().toLowerCase());
            }
        }
        return sb.toString();
    }
}
