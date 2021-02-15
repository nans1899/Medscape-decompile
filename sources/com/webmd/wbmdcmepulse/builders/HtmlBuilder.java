package com.webmd.wbmdcmepulse.builders;

import android.content.Intent;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Xml;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.tapstream.sdk.http.RequestBuilders;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.activities.CmeWebActivity;
import com.webmd.wbmdcmepulse.fragments.ArticleReferencesFragment;
import com.webmd.wbmdcmepulse.models.articles.HtmlList;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import com.webmd.wbmdcmepulse.models.articles.References;
import com.webmd.wbmdcmepulse.models.parsers.articles.HtmlParser;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.unbescape.html.HtmlEscape;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class HtmlBuilder {
    /* access modifiers changed from: private */
    public static final String TAG = HtmlBuilder.class.getSimpleName();

    public static String buildHeading1(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<h1>");
        stringBuffer.append(getColoredString(str, -7829368));
        stringBuffer.append("</h1>");
        return stringBuffer.toString();
    }

    public static String buildHeading2(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<h2>");
        stringBuffer.append(getColoredString(str, Constants.COLOR_MEDSCAPE_RED));
        stringBuffer.append("</h2>");
        return stringBuffer.toString();
    }

    public static String buildParagraph(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<p>");
        stringBuffer.append(getColoredString(str, ViewCompat.MEASURED_STATE_MASK, 3));
        stringBuffer.append("</p>");
        return stringBuffer.toString();
    }

    public static String getHtmlForHtmlObject(HtmlObject htmlObject) {
        StringBuilder sb = new StringBuilder();
        if (htmlObject.htmlType.equals("p")) {
            sb.append(Html.fromHtml(HtmlEscape.unescapeHtml(htmlObject.content.toString())));
        } else if (htmlObject.htmlType.equals("ul") || htmlObject.htmlType.equals("ol")) {
            sb.append(buildHtmlList(htmlObject));
        }
        return sb.toString();
    }

    public static SpannableStringBuilder getHtmlStringForTextView(AppCompatActivity appCompatActivity, String str, List<String> list) {
        Spanned fromHtml = Html.fromHtml(HtmlEscape.unescapeHtml(str));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fromHtml);
        for (URLSpan makeLinkClickable : (URLSpan[]) spannableStringBuilder.getSpans(0, fromHtml.length(), URLSpan.class)) {
            makeLinkClickable(appCompatActivity, spannableStringBuilder, makeLinkClickable, list);
        }
        return spannableStringBuilder;
    }

    public static String removeTrailingLineBreaks(CharSequence charSequence) {
        if (charSequence != null) {
            try {
                if (charSequence.length() > 0) {
                    while (charSequence.charAt(charSequence.length() - 1) == 10) {
                        charSequence = charSequence.subSequence(0, charSequence.length() - 1);
                    }
                }
            } catch (Exception e) {
                Trace.e(TAG, e.getMessage());
            }
        }
        return charSequence.toString();
    }

    protected static String getColoredString(String str, int i) {
        return getColoredString(str, i, 4);
    }

    protected static String getColoredString(String str, int i, int i2) {
        return "<font size='" + i2 + "' color='" + i + "'>" + str + "</font>";
    }

    protected static XmlPullParser initalizeXmlParser(String str) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        newPullParser.setInput(getInputStream(str), (String) null);
        newPullParser.nextTag();
        return newPullParser;
    }

    private static void makeLinkClickable(final AppCompatActivity appCompatActivity, SpannableStringBuilder spannableStringBuilder, final URLSpan uRLSpan, final List<String> list) {
        int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
        int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
        int spanFlags = spannableStringBuilder.getSpanFlags(uRLSpan);
        if (uRLSpan.getURL().contains("ref://")) {
            spannableStringBuilder.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    String access$000 = HtmlBuilder.TAG;
                    Trace.i(access$000, "Clicked: " + uRLSpan.getURL());
                    String replace = uRLSpan.getURL().replace("ref://", "");
                    String access$0002 = HtmlBuilder.TAG;
                    Trace.i(access$0002, "Parsed: " + replace);
                    String[] split = replace.split(",|-");
                    ArrayList arrayList = new ArrayList();
                    boolean z = false;
                    int i = 0;
                    while (true) {
                        if (i >= split.length) {
                            break;
                        }
                        int parseInt = Integer.parseInt(split[i].trim());
                        if (parseInt > list.size()) {
                            z = true;
                            break;
                        }
                        arrayList.add(list.get(parseInt - 1));
                        i++;
                    }
                    References references = new References();
                    if (!z) {
                        references.content = arrayList;
                    } else {
                        references.content = list;
                    }
                    references.displayType = "none";
                    ArticleReferencesFragment.newInstance(references).show(appCompatActivity.getSupportFragmentManager(), Constants.FRAGMENT_TAG_ARTICLE_REFERENCES);
                }
            }, spanStart, spanEnd, spanFlags);
            spannableStringBuilder.removeSpan(uRLSpan);
        } else if (uRLSpan.getURL().contains("http") || uRLSpan.getURL().contains(RequestBuilders.DEFAULT_SCHEME)) {
            spannableStringBuilder.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    Intent intent = new Intent(appCompatActivity, CmeWebActivity.class);
                    intent.putExtra(Constants.RETURN_ACTIVITY, "ArticleActivity");
                    intent.putExtra(Constants.WEB_VIEW_URL_KEY, uRLSpan.getURL());
                    intent.putExtra(Constants.WEB_VIEW_TITLE_KEY, "Article Link");
                    appCompatActivity.startActivity(intent);
                }
            }, spanStart, spanEnd, spanFlags);
            spannableStringBuilder.removeSpan(uRLSpan);
        }
    }

    private static String buildHtmlList(HtmlObject htmlObject) {
        StringBuilder sb = new StringBuilder();
        HtmlList convertXmlListStringToHtmlList = convertXmlListStringToHtmlList(htmlObject.content, htmlObject.htmlType);
        if (convertXmlListStringToHtmlList != null) {
            if (convertXmlListStringToHtmlList.getListType().equals("ul")) {
                sb.append("<ul>");
            } else {
                sb.append("<ol>");
            }
            for (Integer intValue : convertXmlListStringToHtmlList.getList().keySet()) {
                int intValue2 = intValue.intValue();
                sb.append("<li>");
                sb.append(convertXmlListStringToHtmlList.getList().get(Integer.valueOf(intValue2)));
                sb.append("</li>");
            }
            if (convertXmlListStringToHtmlList.getListType().equals("ul")) {
                sb.append("</ul>");
            } else {
                sb.append("</ol>");
            }
        }
        return sb.toString();
    }

    private static HtmlList convertXmlListStringToHtmlList(String str, String str2) {
        HtmlParser htmlParser = new HtmlParser();
        try {
            if (str2.equals("ol")) {
                return htmlParser.parseOrderedXmlHtmlList(initalizeXmlParser(str));
            }
            return htmlParser.parseUnOrderedHtmlList(initalizeXmlParser(str));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static InputStream getInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
    }
}
