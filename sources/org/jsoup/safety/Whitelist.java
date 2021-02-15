package org.jsoup.safety;

import com.appboy.Constants;
import com.facebook.appevents.UserDataStore;
import com.facebook.share.internal.ShareConstants;
import com.tapstream.sdk.http.RequestBuilders;
import com.webmd.wbmdcmepulse.models.articles.ActivityTest;
import com.webmd.wbmdcmepulse.models.articles.HtmlTableRow;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public class Whitelist {
    private Map<TagName, Set<AttributeKey>> attributes = new HashMap();
    private Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes = new HashMap();
    private boolean preserveRelativeLinks = false;
    private Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols = new HashMap();
    private Set<TagName> tagNames = new HashSet();

    public static Whitelist none() {
        return new Whitelist();
    }

    public static Whitelist simpleText() {
        return new Whitelist().addTags("b", UserDataStore.EMAIL, "i", "strong", "u");
    }

    public static Whitelist basic() {
        return new Whitelist().addTags(Constants.APPBOY_PUSH_CONTENT_KEY, "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt", UserDataStore.EMAIL, "i", "li", "ol", "p", ActivityTest.FORMAT_TYPE_PRE, "q", "small", "span", "strike", "strong", "sub", "sup", "u", "ul").addAttributes(Constants.APPBOY_PUSH_CONTENT_KEY, "href").addAttributes("blockquote", "cite").addAttributes("q", "cite").addProtocols(Constants.APPBOY_PUSH_CONTENT_KEY, "href", "ftp", "http", RequestBuilders.DEFAULT_SCHEME, "mailto").addProtocols("blockquote", "cite", "http", RequestBuilders.DEFAULT_SCHEME).addProtocols("cite", "cite", "http", RequestBuilders.DEFAULT_SCHEME).addEnforcedAttribute(Constants.APPBOY_PUSH_CONTENT_KEY, "rel", "nofollow");
    }

    public static Whitelist basicWithImages() {
        return basic().addTags("img").addAttributes("img", "align", "alt", GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, "src", "title", GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH).addProtocols("img", "src", "http", RequestBuilders.DEFAULT_SCHEME);
    }

    public static Whitelist relaxed() {
        return new Whitelist().addTags(Constants.APPBOY_PUSH_CONTENT_KEY, "b", "blockquote", "br", ShareConstants.FEED_CAPTION_PARAM, "cite", "code", "col", "colgroup", "dd", "div", "dl", "dt", UserDataStore.EMAIL, "h1", "h2", "h3", "h4", "h5", "h6", "i", "img", "li", "ol", "p", ActivityTest.FORMAT_TYPE_PRE, "q", "small", "span", "strike", "strong", "sub", "sup", com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE, "tbody", HtmlTableRow.Row_TYPE_TABLE_DATA, "tfoot", HtmlTableRow.ROW_TYPE_TABLE_HEADER, "thead", "tr", "u", "ul").addAttributes(Constants.APPBOY_PUSH_CONTENT_KEY, "href", "title").addAttributes("blockquote", "cite").addAttributes("col", "span", GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH).addAttributes("colgroup", "span", GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH).addAttributes("img", "align", "alt", GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, "src", "title", GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH).addAttributes("ol", "start", "type").addAttributes("q", "cite").addAttributes(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE, "summary", GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH).addAttributes(HtmlTableRow.Row_TYPE_TABLE_DATA, "abbr", "axis", "colspan", "rowspan", GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH).addAttributes(HtmlTableRow.ROW_TYPE_TABLE_HEADER, "abbr", "axis", "colspan", "rowspan", "scope", GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH).addAttributes("ul", "type").addProtocols(Constants.APPBOY_PUSH_CONTENT_KEY, "href", "ftp", "http", RequestBuilders.DEFAULT_SCHEME, "mailto").addProtocols("blockquote", "cite", "http", RequestBuilders.DEFAULT_SCHEME).addProtocols("cite", "cite", "http", RequestBuilders.DEFAULT_SCHEME).addProtocols("img", "src", "http", RequestBuilders.DEFAULT_SCHEME).addProtocols("q", "cite", "http", RequestBuilders.DEFAULT_SCHEME);
    }

    public Whitelist addTags(String... strArr) {
        Validate.notNull(strArr);
        for (String str : strArr) {
            Validate.notEmpty(str);
            this.tagNames.add(TagName.valueOf(str));
        }
        return this;
    }

    public Whitelist removeTags(String... strArr) {
        Validate.notNull(strArr);
        for (String str : strArr) {
            Validate.notEmpty(str);
            TagName valueOf = TagName.valueOf(str);
            if (this.tagNames.remove(valueOf)) {
                this.attributes.remove(valueOf);
                this.enforcedAttributes.remove(valueOf);
                this.protocols.remove(valueOf);
            }
        }
        return this;
    }

    public Whitelist addAttributes(String str, String... strArr) {
        Validate.notEmpty(str);
        Validate.notNull(strArr);
        Validate.isTrue(strArr.length > 0, "No attribute names supplied.");
        TagName valueOf = TagName.valueOf(str);
        this.tagNames.add(valueOf);
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.notEmpty(str2);
            hashSet.add(AttributeKey.valueOf(str2));
        }
        if (this.attributes.containsKey(valueOf)) {
            this.attributes.get(valueOf).addAll(hashSet);
        } else {
            this.attributes.put(valueOf, hashSet);
        }
        return this;
    }

    public Whitelist removeAttributes(String str, String... strArr) {
        Validate.notEmpty(str);
        Validate.notNull(strArr);
        Validate.isTrue(strArr.length > 0, "No attribute names supplied.");
        TagName valueOf = TagName.valueOf(str);
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.notEmpty(str2);
            hashSet.add(AttributeKey.valueOf(str2));
        }
        if (this.tagNames.contains(valueOf) && this.attributes.containsKey(valueOf)) {
            Set set = this.attributes.get(valueOf);
            set.removeAll(hashSet);
            if (set.isEmpty()) {
                this.attributes.remove(valueOf);
            }
        }
        if (str.equals(":all")) {
            for (TagName next : this.attributes.keySet()) {
                Set set2 = this.attributes.get(next);
                set2.removeAll(hashSet);
                if (set2.isEmpty()) {
                    this.attributes.remove(next);
                }
            }
        }
        return this;
    }

    public Whitelist addEnforcedAttribute(String str, String str2, String str3) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notEmpty(str3);
        TagName valueOf = TagName.valueOf(str);
        this.tagNames.add(valueOf);
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        AttributeValue valueOf3 = AttributeValue.valueOf(str3);
        if (this.enforcedAttributes.containsKey(valueOf)) {
            this.enforcedAttributes.get(valueOf).put(valueOf2, valueOf3);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put(valueOf2, valueOf3);
            this.enforcedAttributes.put(valueOf, hashMap);
        }
        return this;
    }

    public Whitelist removeEnforcedAttribute(String str, String str2) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        TagName valueOf = TagName.valueOf(str);
        if (this.tagNames.contains(valueOf) && this.enforcedAttributes.containsKey(valueOf)) {
            AttributeKey valueOf2 = AttributeKey.valueOf(str2);
            Map map = this.enforcedAttributes.get(valueOf);
            map.remove(valueOf2);
            if (map.isEmpty()) {
                this.enforcedAttributes.remove(valueOf);
            }
        }
        return this;
    }

    public Whitelist preserveRelativeLinks(boolean z) {
        this.preserveRelativeLinks = z;
        return this;
    }

    public Whitelist addProtocols(String str, String str2, String... strArr) {
        Map map;
        Set set;
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notNull(strArr);
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        if (this.protocols.containsKey(valueOf)) {
            map = this.protocols.get(valueOf);
        } else {
            HashMap hashMap = new HashMap();
            this.protocols.put(valueOf, hashMap);
            map = hashMap;
        }
        if (map.containsKey(valueOf2)) {
            set = (Set) map.get(valueOf2);
        } else {
            HashSet hashSet = new HashSet();
            map.put(valueOf2, hashSet);
            set = hashSet;
        }
        for (String str3 : strArr) {
            Validate.notEmpty(str3);
            set.add(Protocol.valueOf(str3));
        }
        return this;
    }

    public Whitelist removeProtocols(String str, String str2, String... strArr) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notNull(strArr);
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        Validate.isTrue(this.protocols.containsKey(valueOf), "Cannot remove a protocol that is not set.");
        Map map = this.protocols.get(valueOf);
        Validate.isTrue(map.containsKey(valueOf2), "Cannot remove a protocol that is not set.");
        Set set = (Set) map.get(valueOf2);
        for (String str3 : strArr) {
            Validate.notEmpty(str3);
            set.remove(Protocol.valueOf(str3));
        }
        if (set.isEmpty()) {
            map.remove(valueOf2);
            if (map.isEmpty()) {
                this.protocols.remove(valueOf);
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean isSafeTag(String str) {
        return this.tagNames.contains(TagName.valueOf(str));
    }

    /* access modifiers changed from: protected */
    public boolean isSafeAttribute(String str, Element element, Attribute attribute) {
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(attribute.getKey());
        Set set = this.attributes.get(valueOf);
        if (set == null || !set.contains(valueOf2)) {
            if (this.enforcedAttributes.get(valueOf) != null) {
                Attributes enforcedAttributes2 = getEnforcedAttributes(str);
                String key = attribute.getKey();
                if (enforcedAttributes2.hasKeyIgnoreCase(key)) {
                    return enforcedAttributes2.getIgnoreCase(key).equals(attribute.getValue());
                }
            }
            if (str.equals(":all") || !isSafeAttribute(":all", element, attribute)) {
                return false;
            }
            return true;
        } else if (!this.protocols.containsKey(valueOf)) {
            return true;
        } else {
            Map map = this.protocols.get(valueOf);
            if (!map.containsKey(valueOf2) || testValidProtocol(element, attribute, (Set) map.get(valueOf2))) {
                return true;
            }
            return false;
        }
    }

    private boolean testValidProtocol(Element element, Attribute attribute, Set<Protocol> set) {
        String absUrl = element.absUrl(attribute.getKey());
        if (absUrl.length() == 0) {
            absUrl = attribute.getValue();
        }
        if (!this.preserveRelativeLinks) {
            attribute.setValue(absUrl);
        }
        for (Protocol protocol : set) {
            String protocol2 = protocol.toString();
            if (!protocol2.equals("#")) {
                if (Normalizer.lowerCase(absUrl).startsWith(protocol2 + ":")) {
                    return true;
                }
            } else if (isValidAnchor(absUrl)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidAnchor(String str) {
        return str.startsWith("#") && !str.matches(".*\\s.*");
    }

    /* access modifiers changed from: package-private */
    public Attributes getEnforcedAttributes(String str) {
        Attributes attributes2 = new Attributes();
        TagName valueOf = TagName.valueOf(str);
        if (this.enforcedAttributes.containsKey(valueOf)) {
            for (Map.Entry entry : this.enforcedAttributes.get(valueOf).entrySet()) {
                attributes2.put(((AttributeKey) entry.getKey()).toString(), ((AttributeValue) entry.getValue()).toString());
            }
        }
        return attributes2;
    }

    static class TagName extends TypedValue {
        TagName(String str) {
            super(str);
        }

        static TagName valueOf(String str) {
            return new TagName(str);
        }
    }

    static class AttributeKey extends TypedValue {
        AttributeKey(String str) {
            super(str);
        }

        static AttributeKey valueOf(String str) {
            return new AttributeKey(str);
        }
    }

    static class AttributeValue extends TypedValue {
        AttributeValue(String str) {
            super(str);
        }

        static AttributeValue valueOf(String str) {
            return new AttributeValue(str);
        }
    }

    static class Protocol extends TypedValue {
        Protocol(String str) {
            super(str);
        }

        static Protocol valueOf(String str) {
            return new Protocol(str);
        }
    }

    static abstract class TypedValue {
        private String value;

        TypedValue(String str) {
            Validate.notNull(str);
            this.value = str;
        }

        public int hashCode() {
            String str = this.value;
            return 31 + (str == null ? 0 : str.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TypedValue typedValue = (TypedValue) obj;
            String str = this.value;
            if (str != null) {
                return str.equals(typedValue.value);
            }
            if (typedValue.value == null) {
                return true;
            }
            return false;
        }

        public String toString() {
            return this.value;
        }
    }
}
