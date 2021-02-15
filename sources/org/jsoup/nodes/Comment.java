package org.jsoup.nodes;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import net.bytebuddy.description.type.TypeDescription;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

public class Comment extends LeafNode {
    public String nodeName() {
        return "#comment";
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

    public Comment(String str) {
        this.value = str;
    }

    public String getData() {
        return coreValue();
    }

    public Comment setData(String str) {
        coreValue(str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.prettyPrint() && ((siblingIndex() == 0 && (this.parentNode instanceof Element) && ((Element) this.parentNode).tag().formatAsBlock()) || outputSettings.outline())) {
            indent(appendable, i, outputSettings);
        }
        appendable.append("<!--").append(getData()).append("-->");
    }

    public String toString() {
        return outerHtml();
    }

    public Comment clone() {
        return (Comment) super.clone();
    }

    public boolean isXmlDeclaration() {
        String data = getData();
        return data.length() > 1 && (data.startsWith("!") || data.startsWith(TypeDescription.Generic.OfWildcardType.SYMBOL));
    }

    public XmlDeclaration asXmlDeclaration() {
        String data = getData();
        Document parse = Jsoup.parse(HtmlObject.HtmlMarkUp.OPEN_BRACKER + data.substring(1, data.length() - 1) + HtmlObject.HtmlMarkUp.CLOSE_BRACKER, baseUri(), Parser.xmlParser());
        if (parse.children().size() <= 0) {
            return null;
        }
        Element child = parse.child(0);
        XmlDeclaration xmlDeclaration = new XmlDeclaration(NodeUtils.parser(parse).settings().normalizeTag(child.tagName()), data.startsWith("!"));
        xmlDeclaration.attributes().addAll(child.attributes());
        return xmlDeclaration;
    }
}
