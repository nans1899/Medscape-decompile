package com.medscape.android.consult.util;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import com.appboy.Constants;
import com.facebook.appevents.UserDataStore;
import com.wbmd.wbmdcommons.utils.Extensions;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.nio.CharBuffer;
import javax.xml.parsers.SAXParserFactory;
import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ConsultTimelineHtmlConverter implements ContentHandler {
    private final String HTML_TAG_BOLD = "bold";
    private final String HTML_TAG_EMPHASIZED_TEXT = UserDataStore.EMAIL;
    private final String HTML_TAG_ITALIC = "i";
    private final String HTML_TAG_PARAGRAPH = "p";
    private XMLReader mReader;
    private String mSource;
    private SpannableStringBuilder mSpannableStringBuilder;

    public void endDocument() throws SAXException {
    }

    public void endPrefixMapping(String str) throws SAXException {
    }

    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
    }

    public void processingInstruction(String str, String str2) throws SAXException {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void skippedEntity(String str) throws SAXException {
    }

    public void startDocument() throws SAXException {
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
    }

    public ConsultTimelineHtmlConverter(String str) throws Exception {
        this.mSource = str;
        this.mSpannableStringBuilder = new SpannableStringBuilder();
        this.mReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
    }

    public String removeTextCopyHtmlMarkup(String str) {
        String removeHtmlTag = removeHtmlTag(removeHtmlTag(removeHtmlTag(removeHtmlTag(str, "bold"), UserDataStore.EMAIL), "i"), UserDataStore.EMAIL);
        if (!removeHtmlTag.contains(IOUtils.LINE_SEPARATOR_UNIX)) {
            return removeHtmlTag;
        }
        StringBuilder sb = new StringBuilder();
        String[] split = removeHtmlTag.split(IOUtils.LINE_SEPARATOR_UNIX);
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i]);
            if (i < split.length - 1) {
                sb.append("<br/>");
            }
        }
        return sb.toString();
    }

    public String removeTextCopyHtmlMarkupIncludingParagraph(String str) {
        String removeHtmlTag = removeHtmlTag(removeHtmlTag(removeHtmlTag(removeHtmlTag(removeHtmlTag(str, "bold"), UserDataStore.EMAIL), "i"), "p"), UserDataStore.EMAIL);
        if (!removeHtmlTag.contains(IOUtils.LINE_SEPARATOR_UNIX)) {
            return removeHtmlTag;
        }
        StringBuilder sb = new StringBuilder();
        String[] split = removeHtmlTag.split(IOUtils.LINE_SEPARATOR_UNIX);
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i]);
            if (i < split.length - 1) {
                sb.append("<br/>");
            }
        }
        return sb.toString();
    }

    private String removeHtmlTag(String str, String str2) {
        if (!str.contains(HtmlObject.HtmlMarkUp.OPEN_BRACKER + str2 + HtmlObject.HtmlMarkUp.CLOSE_BRACKER)) {
            return str;
        }
        String replace = str.replace(HtmlObject.HtmlMarkUp.OPEN_BRACKER + str2 + HtmlObject.HtmlMarkUp.CLOSE_BRACKER, "");
        return replace.replace("</" + str2 + HtmlObject.HtmlMarkUp.CLOSE_BRACKER, "");
    }

    private void handleStartTagForConsultTimeline(String str, Attributes attributes) {
        if (str.equalsIgnoreCase(UserDataStore.EMAIL)) {
            start(this.mSpannableStringBuilder, new Italic());
        } else if (str.equalsIgnoreCase("b")) {
            start(this.mSpannableStringBuilder, new Bold());
        } else if (str.equalsIgnoreCase("strong")) {
            start(this.mSpannableStringBuilder, new Bold());
        } else if (str.equalsIgnoreCase("i")) {
            start(this.mSpannableStringBuilder, new Italic());
        } else if (str.equalsIgnoreCase(Constants.APPBOY_PUSH_CONTENT_KEY)) {
            startA(this.mSpannableStringBuilder, attributes);
        }
    }

    private void handleEndTagForConsultTimeline(String str) {
        if (str.equalsIgnoreCase(UserDataStore.EMAIL)) {
            end(this.mSpannableStringBuilder, Italic.class, new StyleSpan(2));
        } else if (str.equalsIgnoreCase("b")) {
            end(this.mSpannableStringBuilder, Bold.class, new StyleSpan(1));
        } else if (str.equalsIgnoreCase("strong")) {
            end(this.mSpannableStringBuilder, Italic.class, new StyleSpan(2));
        } else if (str.equalsIgnoreCase("i")) {
            end(this.mSpannableStringBuilder, Italic.class, new StyleSpan(2));
        } else if (str.equalsIgnoreCase(Constants.APPBOY_PUSH_CONTENT_KEY)) {
            endA(this.mSpannableStringBuilder);
        }
    }

    private static Object getLast(Spanned spanned, Class cls) {
        Object[] spans = spanned.getSpans(0, spanned.length(), cls);
        if (spans.length == 0) {
            return null;
        }
        return spans[spans.length - 1];
    }

    private static void start(SpannableStringBuilder spannableStringBuilder, Object obj) {
        int length = spannableStringBuilder.length();
        spannableStringBuilder.setSpan(obj, length, length, 17);
    }

    private static void end(SpannableStringBuilder spannableStringBuilder, Class cls, Object obj) {
        int length = spannableStringBuilder.length();
        Object last = getLast(spannableStringBuilder, cls);
        int spanStart = spannableStringBuilder.getSpanStart(last);
        spannableStringBuilder.removeSpan(last);
        if (spanStart != length) {
            spannableStringBuilder.setSpan(obj, spanStart, length, 33);
        }
    }

    private static void startA(SpannableStringBuilder spannableStringBuilder, Attributes attributes) {
        String value = attributes.getValue("", "href");
        String value2 = attributes.getValue("", "typeof");
        int length = spannableStringBuilder.length();
        spannableStringBuilder.setSpan(new Href(value, value2, !Extensions.IsNullOrEmpty(value2)), length, length, 17);
    }

    private static void endA(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length();
        Object last = getLast(spannableStringBuilder, Href.class);
        int spanStart = spannableStringBuilder.getSpanStart(last);
        spannableStringBuilder.removeSpan(last);
        if (spanStart != length) {
            Href href = (Href) last;
        }
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        handleStartTagForConsultTimeline(str2, attributes);
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        handleEndTagForConsultTimeline(str2);
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        this.mSpannableStringBuilder.append(CharBuffer.wrap(cArr, i, i2));
    }

    private static class Bold {
        private Bold() {
        }
    }

    private static class Italic {
        private Italic() {
        }
    }

    private static class Href {
        public boolean mHasTypeOfAttribute = true;
        public String mHref;
        public String mTypeOf;

        public Href(String str, String str2, boolean z) {
            this.mHref = str;
            this.mTypeOf = str2;
        }
    }
}
