package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;

public class TextNode extends LeafNode {
    public String nodeName() {
        return "#text";
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

    public TextNode(String str) {
        this.value = str;
    }

    public String text() {
        return StringUtil.normaliseWhitespace(getWholeText());
    }

    public TextNode text(String str) {
        coreValue(str);
        return this;
    }

    public String getWholeText() {
        return coreValue();
    }

    public boolean isBlank() {
        return StringUtil.isBlank(coreValue());
    }

    public TextNode splitText(int i) {
        String coreValue = coreValue();
        Validate.isTrue(i >= 0, "Split offset must be not be negative");
        Validate.isTrue(i < coreValue.length(), "Split offset must not be greater than current text length");
        String substring = coreValue.substring(0, i);
        String substring2 = coreValue.substring(i);
        text(substring);
        TextNode textNode = new TextNode(substring2);
        if (parent() != null) {
            parent().addChildren(siblingIndex() + 1, textNode);
        }
        return textNode;
    }

    /* access modifiers changed from: package-private */
    public void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        boolean prettyPrint = outputSettings.prettyPrint();
        if (prettyPrint && ((siblingIndex() == 0 && (this.parentNode instanceof Element) && ((Element) this.parentNode).tag().formatAsBlock() && !isBlank()) || (outputSettings.outline() && siblingNodes().size() > 0 && !isBlank()))) {
            indent(appendable, i, outputSettings);
        }
        Entities.escape(appendable, coreValue(), outputSettings, false, prettyPrint && !Element.preserveWhitespace(this.parentNode), prettyPrint && (this.parentNode instanceof Document));
    }

    public String toString() {
        return outerHtml();
    }

    public TextNode clone() {
        return (TextNode) super.clone();
    }

    public static TextNode createFromEncoded(String str) {
        return new TextNode(Entities.unescape(str));
    }

    static String normaliseWhitespace(String str) {
        return StringUtil.normaliseWhitespace(str);
    }

    static String stripLeadingWhitespace(String str) {
        return str.replaceFirst("^\\s+", "");
    }

    static boolean lastCharIsWhitespace(StringBuilder sb) {
        return sb.length() != 0 && sb.charAt(sb.length() - 1) == ' ';
    }
}
