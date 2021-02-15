package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.UncheckedIOException;
import org.jsoup.nodes.Document;

public class CDataNode extends TextNode {
    public String nodeName() {
        return "#cdata";
    }

    public CDataNode(String str) {
        super(str);
    }

    public String text() {
        return getWholeText();
    }

    /* access modifiers changed from: package-private */
    public void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        appendable.append("<![CDATA[").append(getWholeText());
    }

    /* access modifiers changed from: package-private */
    public void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) {
        try {
            appendable.append("]]>");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public CDataNode clone() {
        return (CDataNode) super.clone();
    }
}
