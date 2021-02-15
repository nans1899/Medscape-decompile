package org.jsoup.nodes;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.medscape.android.Constants;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;

public class Attribute implements Map.Entry<String, String>, Cloneable {
    private static final String[] booleanAttributes = {"allowfullscreen", "async", "autofocus", "checked", MessengerShareContentUtility.WEBVIEW_RATIO_COMPACT, "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", Constants.OMNITURE_MLINK_OPEN, "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
    private String key;
    Attributes parent;
    private String val;

    public Attribute(String str, String str2) {
        this(str, str2, (Attributes) null);
    }

    public Attribute(String str, String str2, Attributes attributes) {
        Validate.notNull(str);
        String trim = str.trim();
        Validate.notEmpty(trim);
        this.key = trim;
        this.val = str2;
        this.parent = attributes;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        int indexOfKey;
        Validate.notNull(str);
        String trim = str.trim();
        Validate.notEmpty(trim);
        Attributes attributes = this.parent;
        if (!(attributes == null || (indexOfKey = attributes.indexOfKey(this.key)) == -1)) {
            this.parent.keys[indexOfKey] = trim;
        }
        this.key = trim;
    }

    public String getValue() {
        return Attributes.checkNotNull(this.val);
    }

    public boolean hasDeclaredValue() {
        return this.val != null;
    }

    public String setValue(String str) {
        String str2 = this.val;
        Attributes attributes = this.parent;
        if (attributes != null) {
            str2 = attributes.get(this.key);
            int indexOfKey = this.parent.indexOfKey(this.key);
            if (indexOfKey != -1) {
                this.parent.vals[indexOfKey] = str;
            }
        }
        this.val = str;
        return Attributes.checkNotNull(str2);
    }

    public String html() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        try {
            html(borrowBuilder, new Document("").outputSettings());
            return StringUtil.releaseBuilder(borrowBuilder);
        } catch (IOException e) {
            throw new SerializationException((Throwable) e);
        }
    }

    protected static void html(String str, String str2, Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        appendable.append(str);
        if (!shouldCollapseAttribute(str, str2, outputSettings)) {
            appendable.append("=\"");
            Entities.escape(appendable, Attributes.checkNotNull(str2), outputSettings, true, false, false);
            appendable.append('\"');
        }
    }

    /* access modifiers changed from: protected */
    public void html(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        html(this.key, this.val, appendable, outputSettings);
    }

    public String toString() {
        return html();
    }

    public static Attribute createFromEncoded(String str, String str2) {
        return new Attribute(str, Entities.unescape(str2, true), (Attributes) null);
    }

    /* access modifiers changed from: protected */
    public boolean isDataAttribute() {
        return isDataAttribute(this.key);
    }

    protected static boolean isDataAttribute(String str) {
        return str.startsWith("data-") && str.length() > 5;
    }

    /* access modifiers changed from: protected */
    public final boolean shouldCollapseAttribute(Document.OutputSettings outputSettings) {
        return shouldCollapseAttribute(this.key, this.val, outputSettings);
    }

    protected static boolean shouldCollapseAttribute(String str, String str2, Document.OutputSettings outputSettings) {
        return outputSettings.syntax() == Document.OutputSettings.Syntax.html && (str2 == null || (("".equals(str2) || str2.equalsIgnoreCase(str)) && isBooleanAttribute(str)));
    }

    protected static boolean isBooleanAttribute(String str) {
        return Arrays.binarySearch(booleanAttributes, str) >= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        String str = this.key;
        if (str == null ? attribute.key != null : !str.equals(attribute.key)) {
            return false;
        }
        String str2 = this.val;
        String str3 = attribute.val;
        if (str2 != null) {
            return str2.equals(str3);
        }
        if (str3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.key;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.val;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public Attribute clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
