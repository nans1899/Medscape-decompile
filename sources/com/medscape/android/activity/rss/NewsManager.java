package com.medscape.android.activity.rss;

import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.parser.model.Article;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsManager {
    private static String ARTICLE_ID = "art";
    public static String jsonString;
    public static String metaString;

    public static String parseArticleHtml(String str, String str2, Article article) {
        return parseArticleHtml(str, str2, article, false);
    }

    public static String parseArticleHtml(String str, String str2, Article article, boolean z) {
        parseMetaSegVar(str, z);
        parseAdSegvars(str);
        String[] screenSpecificDataFromHtml = AdsSegvar.getInstance().getScreenSpecificDataFromHtml();
        for (int i = 0; i < screenSpecificDataFromHtml.length; i++) {
            if (screenSpecificDataFromHtml.length >= i) {
                String str3 = screenSpecificDataFromHtml[i];
                if (str3.contains("=")) {
                    String[] split = str3.split("=");
                    if (split.length == 2 && split[0].equals(ARTICLE_ID)) {
                        article.mArticleId = split[1];
                    }
                }
            }
        }
        Matcher matcher = Pattern.compile("<!-- article content goes here  -->(.*?)<!-- article content ends here  -->", 32).matcher(str);
        String str4 = "";
        String group = matcher.find() ? matcher.group(0) : str4;
        Matcher matcher2 = Pattern.compile("<p id=\"authors\">(.*?)</p>").matcher(str);
        String group2 = matcher2.find() ? matcher2.group(0) : str4;
        Matcher matcher3 = Pattern.compile("<!-- Legal Block -->(.*?)<!-- /Legal Block -->", 32).matcher(str);
        if (matcher3.find()) {
            str4 = matcher3.group(0);
        }
        return "<html><head><title>" + str2 + "</title><style>p {font-family:Arial, Helvetica;} h3 {font:Times; color:#00315F;}</style></head><body><h3>" + str2 + "</h3>" + group2 + group + str4 + "</body></html>";
    }

    @Deprecated
    public static String parseMetaSegVar(String str) {
        return parseMetaSegVar(str, false);
    }

    public static String parseAdSegvars(String str) {
        jsonString = "";
        Matcher matcher = Pattern.compile("var DFPTargetKeys = (.*?);", 32).matcher(str);
        if (matcher.find()) {
            jsonString = matcher.group(1);
        }
        return jsonString;
    }

    public static HashMap<String, String> getScreenMapsFromHtml(String str) {
        parseAdSegvars(str);
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : AdsSegvar.getInstance().getScreenSpecificDataFromHtml()) {
            if (str2.contains("=")) {
                String[] split = str2.split("=");
                if (split.length == 2) {
                    hashMap.put(split[0], split[1]);
                }
            }
        }
        return hashMap;
    }

    @Deprecated
    public static String parseMetaSegVar(String str, boolean z) {
        metaString = "";
        try {
            int indexOf = str.indexOf("<meta name=\"metasegvar\" content=\"");
            if (indexOf == -1) {
                return metaString;
            }
            String substring = str.substring(indexOf + 33);
            if (z) {
                substring = substring.replace("pclass=content", "pclass=alertviewer");
            }
            String substring2 = substring.substring(0, substring.indexOf(HtmlObject.HtmlMarkUp.CLOSE_BRACKER));
            metaString = substring2;
            String replaceAll = substring2.replaceAll("\"", "");
            metaString = replaceAll;
            metaString = replaceAll.replaceAll(";", "&");
            return metaString;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
