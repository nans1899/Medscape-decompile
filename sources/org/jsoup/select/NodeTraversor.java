package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

public class NodeTraversor {
    public static void traverse(NodeVisitor nodeVisitor, Node node) {
        Node node2 = node;
        int i = 0;
        while (node2 != null) {
            nodeVisitor.head(node2, i);
            if (node2.childNodeSize() > 0) {
                node2 = node2.childNode(0);
                i++;
            } else {
                while (node2.nextSibling() == null && i > 0) {
                    nodeVisitor.tail(node2, i);
                    node2 = node2.parentNode();
                    i--;
                }
                nodeVisitor.tail(node2, i);
                if (node2 != node) {
                    node2 = node2.nextSibling();
                } else {
                    return;
                }
            }
        }
    }

    public static void traverse(NodeVisitor nodeVisitor, Elements elements) {
        Validate.notNull(nodeVisitor);
        Validate.notNull(elements);
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            traverse(nodeVisitor, (Node) (Element) it.next());
        }
    }

    public static NodeFilter.FilterResult filter(NodeFilter nodeFilter, Node node) {
        Node node2 = node;
        int i = 0;
        while (node2 != null) {
            NodeFilter.FilterResult head = nodeFilter.head(node2, i);
            if (head == NodeFilter.FilterResult.STOP) {
                return head;
            }
            if (head != NodeFilter.FilterResult.CONTINUE || node2.childNodeSize() <= 0) {
                while (node2.nextSibling() == null && i > 0) {
                    if ((head == NodeFilter.FilterResult.CONTINUE || head == NodeFilter.FilterResult.SKIP_CHILDREN) && (head = nodeFilter.tail(node2, i)) == NodeFilter.FilterResult.STOP) {
                        return head;
                    }
                    Node parentNode = node2.parentNode();
                    i--;
                    if (head == NodeFilter.FilterResult.REMOVE) {
                        node2.remove();
                    }
                    head = NodeFilter.FilterResult.CONTINUE;
                    node2 = parentNode;
                }
                if ((head == NodeFilter.FilterResult.CONTINUE || head == NodeFilter.FilterResult.SKIP_CHILDREN) && (head = nodeFilter.tail(node2, i)) == NodeFilter.FilterResult.STOP) {
                    return head;
                }
                if (node2 == node) {
                    return head;
                }
                Node nextSibling = node2.nextSibling();
                if (head == NodeFilter.FilterResult.REMOVE) {
                    node2.remove();
                }
                node2 = nextSibling;
            } else {
                node2 = node2.childNode(0);
                i++;
            }
        }
        return NodeFilter.FilterResult.CONTINUE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x000a A[LOOP:0: B:1:0x000a->B:4:0x001c, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void filter(org.jsoup.select.NodeFilter r2, org.jsoup.select.Elements r3) {
        /*
            org.jsoup.helper.Validate.notNull(r2)
            org.jsoup.helper.Validate.notNull(r3)
            java.util.Iterator r3 = r3.iterator()
        L_0x000a:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x001e
            java.lang.Object r0 = r3.next()
            org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
            org.jsoup.select.NodeFilter$FilterResult r0 = filter((org.jsoup.select.NodeFilter) r2, (org.jsoup.nodes.Node) r0)
            org.jsoup.select.NodeFilter$FilterResult r1 = org.jsoup.select.NodeFilter.FilterResult.STOP
            if (r0 != r1) goto L_0x000a
        L_0x001e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.select.NodeTraversor.filter(org.jsoup.select.NodeFilter, org.jsoup.select.Elements):void");
    }
}
