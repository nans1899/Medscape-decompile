package org.jsoup.nodes;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;

public class DocumentType extends LeafNode {
    private static final String NAME = "name";
    private static final String PUBLIC_ID = "publicId";
    public static final String PUBLIC_KEY = "PUBLIC";
    private static final String PUB_SYS_KEY = "pubSysKey";
    private static final String SYSTEM_ID = "systemId";
    public static final String SYSTEM_KEY = "SYSTEM";

    public String nodeName() {
        return "#doctype";
    }

    /* access modifiers changed from: package-private */
    public void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) {
    }

    public /* bridge */ /* synthetic */ String absUrl(String str) {
        return super.absUrl(str);
    }

    public /* bridge */ /* synthetic */ String attr(String str) {
        return super.attr(str);
    }

    public /* bridge */ /* synthetic */ Node attr(String str, String str2) {
        return super.attr(str, str2);
    }

    public /* bridge */ /* synthetic */ String baseUri() {
        return super.baseUri();
    }

    public /* bridge */ /* synthetic */ int childNodeSize() {
        return super.childNodeSize();
    }

    public /* bridge */ /* synthetic */ Node empty() {
        return super.empty();
    }

    public /* bridge */ /* synthetic */ boolean hasAttr(String str) {
        return super.hasAttr(str);
    }

    public /* bridge */ /* synthetic */ Node removeAttr(String str) {
        return super.removeAttr(str);
    }

    public DocumentType(String str, String str2, String str3) {
        Validate.notNull(str);
        Validate.notNull(str2);
        Validate.notNull(str3);
        attr("name", str);
        attr(PUBLIC_ID, str2);
        attr(SYSTEM_ID, str3);
        updatePubSyskey();
    }

    public void setPubSysKey(String str) {
        if (str != null) {
            attr(PUB_SYS_KEY, str);
        }
    }

    private void updatePubSyskey() {
        if (has(PUBLIC_ID)) {
            attr(PUB_SYS_KEY, PUBLIC_KEY);
        } else if (has(SYSTEM_ID)) {
            attr(PUB_SYS_KEY, SYSTEM_KEY);
        }
    }

    public String name() {
        return attr("name");
    }

    public String publicId() {
        return attr(PUBLIC_ID);
    }

    public String systemId() {
        return attr(SYSTEM_ID);
    }

    /* access modifiers changed from: package-private */
    public void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.syntax() != Document.OutputSettings.Syntax.html || has(PUBLIC_ID) || has(SYSTEM_ID)) {
            appendable.append("<!DOCTYPE");
        } else {
            appendable.append("<!doctype");
        }
        if (has("name")) {
            appendable.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(attr("name"));
        }
        if (has(PUB_SYS_KEY)) {
            appendable.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(attr(PUB_SYS_KEY));
        }
        if (has(PUBLIC_ID)) {
            appendable.append(" \"").append(attr(PUBLIC_ID)).append('\"');
        }
        if (has(SYSTEM_ID)) {
            appendable.append(" \"").append(attr(SYSTEM_ID)).append('\"');
        }
        appendable.append('>');
    }

    private boolean has(String str) {
        return !StringUtil.isBlank(attr(str));
    }
}
