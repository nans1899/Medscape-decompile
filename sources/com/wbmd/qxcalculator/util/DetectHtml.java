package com.wbmd.qxcalculator.util;

import java.util.regex.Pattern;

public class DetectHtml {
    public static final String htmlEntity = "&[a-zA-Z][a-zA-Z0-9]+;";
    public static final Pattern htmlPattern = Pattern.compile("(\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>.*\\</\\w+\\>)|(\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>)|(&[a-zA-Z][a-zA-Z0-9]+;)", 32);
    public static final String tagEnd = "\\</\\w+\\>";
    public static final String tagSelfClosing = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>";
    public static final String tagStart = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>";

    public static boolean isHtml(String str) {
        if (str != null) {
            return htmlPattern.matcher(str).find();
        }
        return false;
    }
}
