package org.jsoup.parser;

import androidx.core.app.NotificationCompat;
import com.appboy.Constants;
import com.facebook.appevents.UserDataStore;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.google.firebase.messaging.Constants;
import com.medscape.android.cache.Cache;
import com.medscape.android.slideshow.SlideshowPageFragment;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.webmd.wbmdcmepulse.models.articles.ActivityTest;
import com.webmd.wbmdcmepulse.models.articles.HtmlTableRow;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;

public class Tag implements Cloneable {
    private static final String[] blockTags;
    private static final String[] emptyTags = {JSONAPISpecConstants.META, "link", "base", "frame", "img", "br", "wbr", "embed", "hr", "input", "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
    private static final String[] formListedTags = {"button", "fieldset", "input", "keygen", SlideshowPageFragment.ARG_OBJECT, "output", "select", "textarea"};
    private static final String[] formSubmitTags = {"input", "keygen", SlideshowPageFragment.ARG_OBJECT, "select", "textarea"};
    private static final String[] formatAsInlineTags = {"title", Constants.APPBOY_PUSH_CONTENT_KEY, "p", "h1", "h2", "h3", "h4", "h5", "h6", ActivityTest.FORMAT_TYPE_PRE, "address", "li", HtmlTableRow.ROW_TYPE_TABLE_HEADER, HtmlTableRow.Row_TYPE_TABLE_DATA, "script", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "ins", "del", Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY};
    private static final String[] inlineTags = {SlideshowPageFragment.ARG_OBJECT, "base", "font", "tt", "i", "b", "u", "big", "small", UserDataStore.EMAIL, "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", Cache.Caches.TIME, "acronym", "mark", "ruby", "rt", "rp", Constants.APPBOY_PUSH_CONTENT_KEY, "img", "br", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", "span", "input", "select", "textarea", Constants.ScionAnalytics.PARAM_LABEL, "button", "optgroup", "option", "legend", "datalist", "keygen", "output", NotificationCompat.CATEGORY_PROGRESS, "meter", "area", "param", "source", "track", "summary", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track", "data", "bdi", com.appboy.Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY};
    private static final String[] preserveWhitespaceTags = {ActivityTest.FORMAT_TYPE_PRE, "plaintext", "title", "textarea"};
    private static final Map<String, Tag> tags = new HashMap();
    private boolean empty = false;
    private boolean formList = false;
    private boolean formSubmit = false;
    private boolean formatAsBlock = true;
    private boolean isBlock = true;
    private String normalName;
    private boolean preserveWhitespace = false;
    private boolean selfClosing = false;
    private String tagName;

    static {
        String[] strArr = {"html", "head", "body", "frameset", "script", "noscript", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, JSONAPISpecConstants.META, "link", "title", "frame", "noframes", "section", "nav", "aside", "hgroup", "header", "footer", "p", "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol", ActivityTest.FORMAT_TYPE_PRE, "div", "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", "dl", "dt", "dd", "li", com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE, ShareConstants.FEED_CAPTION_PARAM, "thead", "tfoot", "tbody", "colgroup", "col", "tr", HtmlTableRow.ROW_TYPE_TABLE_HEADER, HtmlTableRow.Row_TYPE_TABLE_DATA, "video", "audio", "canvas", "details", Category.K_MENU_CATEGORY, "plaintext", MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE, "article", "main", "svg", "math", "center"};
        blockTags = strArr;
        for (String tag : strArr) {
            register(new Tag(tag));
        }
        for (String tag2 : inlineTags) {
            Tag tag3 = new Tag(tag2);
            tag3.isBlock = false;
            tag3.formatAsBlock = false;
            register(tag3);
        }
        for (String str : emptyTags) {
            Tag tag4 = tags.get(str);
            Validate.notNull(tag4);
            tag4.empty = true;
        }
        for (String str2 : formatAsInlineTags) {
            Tag tag5 = tags.get(str2);
            Validate.notNull(tag5);
            tag5.formatAsBlock = false;
        }
        for (String str3 : preserveWhitespaceTags) {
            Tag tag6 = tags.get(str3);
            Validate.notNull(tag6);
            tag6.preserveWhitespace = true;
        }
        for (String str4 : formListedTags) {
            Tag tag7 = tags.get(str4);
            Validate.notNull(tag7);
            tag7.formList = true;
        }
        for (String str5 : formSubmitTags) {
            Tag tag8 = tags.get(str5);
            Validate.notNull(tag8);
            tag8.formSubmit = true;
        }
    }

    private Tag(String str) {
        this.tagName = str;
        this.normalName = Normalizer.lowerCase(str);
    }

    public String getName() {
        return this.tagName;
    }

    public String normalName() {
        return this.normalName;
    }

    public static Tag valueOf(String str, ParseSettings parseSettings) {
        Validate.notNull(str);
        Tag tag = tags.get(str);
        if (tag != null) {
            return tag;
        }
        String normalizeTag = parseSettings.normalizeTag(str);
        Validate.notEmpty(normalizeTag);
        String lowerCase = Normalizer.lowerCase(normalizeTag);
        Tag tag2 = tags.get(lowerCase);
        if (tag2 == null) {
            Tag tag3 = new Tag(normalizeTag);
            tag3.isBlock = false;
            return tag3;
        } else if (!parseSettings.preserveTagCase() || normalizeTag.equals(lowerCase)) {
            return tag2;
        } else {
            Tag clone = tag2.clone();
            clone.tagName = normalizeTag;
            return clone;
        }
    }

    public static Tag valueOf(String str) {
        return valueOf(str, ParseSettings.preserveCase);
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public boolean formatAsBlock() {
        return this.formatAsBlock;
    }

    public boolean isInline() {
        return !this.isBlock;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isSelfClosing() {
        return this.empty || this.selfClosing;
    }

    public boolean isKnownTag() {
        return tags.containsKey(this.tagName);
    }

    public static boolean isKnownTag(String str) {
        return tags.containsKey(str);
    }

    public boolean preserveWhitespace() {
        return this.preserveWhitespace;
    }

    public boolean isFormListed() {
        return this.formList;
    }

    public boolean isFormSubmittable() {
        return this.formSubmit;
    }

    /* access modifiers changed from: package-private */
    public Tag setSelfClosing() {
        this.selfClosing = true;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) obj;
        if (this.tagName.equals(tag.tagName) && this.empty == tag.empty && this.formatAsBlock == tag.formatAsBlock && this.isBlock == tag.isBlock && this.preserveWhitespace == tag.preserveWhitespace && this.selfClosing == tag.selfClosing && this.formList == tag.formList && this.formSubmit == tag.formSubmit) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((this.tagName.hashCode() * 31) + (this.isBlock ? 1 : 0)) * 31) + (this.formatAsBlock ? 1 : 0)) * 31) + (this.empty ? 1 : 0)) * 31) + (this.selfClosing ? 1 : 0)) * 31) + (this.preserveWhitespace ? 1 : 0)) * 31) + (this.formList ? 1 : 0)) * 31) + (this.formSubmit ? 1 : 0);
    }

    public String toString() {
        return this.tagName;
    }

    /* access modifiers changed from: protected */
    public Tag clone() {
        try {
            return (Tag) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void register(Tag tag) {
        tags.put(tag.tagName, tag);
    }
}
