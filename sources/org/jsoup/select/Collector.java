package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

public class Collector {
    private Collector() {
    }

    public static Elements collect(Evaluator evaluator, Element element) {
        Elements elements = new Elements();
        NodeTraversor.traverse((NodeVisitor) new Accumulator(element, elements, evaluator), (Node) element);
        return elements;
    }

    private static class Accumulator implements NodeVisitor {
        private final Elements elements;
        private final Evaluator eval;
        private final Element root;

        public void tail(Node node, int i) {
        }

        Accumulator(Element element, Elements elements2, Evaluator evaluator) {
            this.root = element;
            this.elements = elements2;
            this.eval = evaluator;
        }

        public void head(Node node, int i) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if (this.eval.matches(this.root, element)) {
                    this.elements.add(element);
                }
            }
        }
    }

    public static Element findFirst(Evaluator evaluator, Element element) {
        FirstFinder firstFinder = new FirstFinder(element, evaluator);
        NodeTraversor.filter((NodeFilter) firstFinder, (Node) element);
        return firstFinder.match;
    }

    private static class FirstFinder implements NodeFilter {
        private final Evaluator eval;
        /* access modifiers changed from: private */
        public Element match = null;
        private final Element root;

        FirstFinder(Element element, Evaluator evaluator) {
            this.root = element;
            this.eval = evaluator;
        }

        public NodeFilter.FilterResult head(Node node, int i) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if (this.eval.matches(this.root, element)) {
                    this.match = element;
                    return NodeFilter.FilterResult.STOP;
                }
            }
            return NodeFilter.FilterResult.CONTINUE;
        }

        public NodeFilter.FilterResult tail(Node node, int i) {
            return NodeFilter.FilterResult.CONTINUE;
        }
    }
}
