package com.wbmd.wbmdcommons.extensions;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import org.apache.commons.io.IOUtils;

public class StringExtensions {
    public static String EMPTY = "";

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static String capitalizeEachWord(String str) {
        StringBuilder sb = new StringBuilder();
        String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        for (int i = 0; i < split.length; i++) {
            if (split[i] != null && split[i].length() > 0) {
                sb.append(Character.toUpperCase(split[i].charAt(0)));
                sb.append(split[i].substring(1));
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
        }
        return sb.toString().trim();
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static String capitalizeFirstWord(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String removeTrailingSlash(String str) {
        return (isNullOrEmpty(str) || !str.substring(str.length() + -1, str.length()).equals("/")) ? str : str.substring(0, str.length() - 1);
    }

    public static String removeHTMLTags(String str) {
        return str.replace("\\n", "").replace("\\t", "").replace(IOUtils.LINE_SEPARATOR_UNIX, "").replace("\t", "").trim();
    }
}
