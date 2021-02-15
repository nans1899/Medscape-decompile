package org.jsoup.nodes;

import org.jsoup.nodes.Document;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;

final class NodeUtils {
    NodeUtils() {
    }

    static Document.OutputSettings outputSettings(Node node) {
        Document ownerDocument = node.ownerDocument();
        if (ownerDocument == null) {
            ownerDocument = new Document("");
        }
        return ownerDocument.outputSettings();
    }

    static Parser parser(Node node) {
        Document ownerDocument = node.ownerDocument();
        return (ownerDocument == null || ownerDocument.parser() == null) ? new Parser(new HtmlTreeBuilder()) : ownerDocument.parser();
    }
}
