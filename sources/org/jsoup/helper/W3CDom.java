package org.jsoup.helper;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class W3CDom {
    protected DocumentBuilderFactory factory;

    public W3CDom() {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        this.factory = newInstance;
        newInstance.setNamespaceAware(true);
    }

    public static Document convert(org.jsoup.nodes.Document document) {
        return new W3CDom().fromJsoup(document);
    }

    public static String asString(Document document, Map<String, String> map) {
        try {
            DOMSource dOMSource = new DOMSource(document);
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            if (map != null) {
                newTransformer.setOutputProperties(propertiesFromMap(map));
            }
            if (document.getDoctype() != null) {
                DocumentType doctype = document.getDoctype();
                if (!StringUtil.isBlank(doctype.getPublicId())) {
                    newTransformer.setOutputProperty("doctype-public", doctype.getPublicId());
                }
                if (!StringUtil.isBlank(doctype.getSystemId())) {
                    newTransformer.setOutputProperty("doctype-system", doctype.getSystemId());
                } else if (doctype.getName().equalsIgnoreCase("html") && StringUtil.isBlank(doctype.getPublicId()) && StringUtil.isBlank(doctype.getSystemId())) {
                    newTransformer.setOutputProperty("doctype-system", "about:legacy-compat");
                }
            }
            newTransformer.transform(dOMSource, streamResult);
            return stringWriter.toString();
        } catch (TransformerException e) {
            throw new IllegalStateException(e);
        }
    }

    static Properties propertiesFromMap(Map<String, String> map) {
        Properties properties = new Properties();
        properties.putAll(map);
        return properties;
    }

    public static HashMap<String, String> OutputHtml() {
        return methodMap("html");
    }

    public static HashMap<String, String> OutputXml() {
        return methodMap("xml");
    }

    private static HashMap<String, String> methodMap(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(FirebaseAnalytics.Param.METHOD, str);
        return hashMap;
    }

    public Document fromJsoup(org.jsoup.nodes.Document document) {
        Validate.notNull(document);
        try {
            DocumentBuilder newDocumentBuilder = this.factory.newDocumentBuilder();
            DOMImplementation dOMImplementation = newDocumentBuilder.getDOMImplementation();
            Document newDocument = newDocumentBuilder.newDocument();
            org.jsoup.nodes.DocumentType documentType = document.documentType();
            if (documentType != null) {
                newDocument.appendChild(dOMImplementation.createDocumentType(documentType.name(), documentType.publicId(), documentType.systemId()));
            }
            newDocument.setXmlStandalone(true);
            convert(document, newDocument);
            return newDocument;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void convert(org.jsoup.nodes.Document document, Document document2) {
        if (!StringUtil.isBlank(document.location())) {
            document2.setDocumentURI(document.location());
        }
        NodeTraversor.traverse((NodeVisitor) new W3CBuilder(document2), (Node) document.child(0));
    }

    public String asString(Document document) {
        return asString(document, (Map<String, String>) null);
    }

    protected static class W3CBuilder implements NodeVisitor {
        private static final String xmlnsKey = "xmlns";
        private static final String xmlnsPrefix = "xmlns:";
        private Element dest;
        private final Document doc;
        private final Stack<HashMap<String, String>> namespacesStack;

        public W3CBuilder(Document document) {
            Stack<HashMap<String, String>> stack = new Stack<>();
            this.namespacesStack = stack;
            this.doc = document;
            stack.push(new HashMap());
        }

        public void head(Node node, int i) {
            Element element;
            this.namespacesStack.push(new HashMap(this.namespacesStack.peek()));
            if (node instanceof org.jsoup.nodes.Element) {
                org.jsoup.nodes.Element element2 = (org.jsoup.nodes.Element) node;
                String str = (String) this.namespacesStack.peek().get(updateNamespaces(element2));
                String tagName = element2.tagName();
                if (str != null || !tagName.contains(":")) {
                    element = this.doc.createElementNS(str, tagName);
                } else {
                    element = this.doc.createElementNS("", tagName);
                }
                copyAttributes(element2, element);
                Element element3 = this.dest;
                if (element3 == null) {
                    this.doc.appendChild(element);
                } else {
                    element3.appendChild(element);
                }
                this.dest = element;
            } else if (node instanceof TextNode) {
                this.dest.appendChild(this.doc.createTextNode(((TextNode) node).getWholeText()));
            } else if (node instanceof Comment) {
                this.dest.appendChild(this.doc.createComment(((Comment) node).getData()));
            } else if (node instanceof DataNode) {
                this.dest.appendChild(this.doc.createTextNode(((DataNode) node).getWholeData()));
            }
        }

        public void tail(Node node, int i) {
            if ((node instanceof org.jsoup.nodes.Element) && (this.dest.getParentNode() instanceof Element)) {
                this.dest = (Element) this.dest.getParentNode();
            }
            this.namespacesStack.pop();
        }

        private void copyAttributes(Node node, Element element) {
            Iterator<Attribute> it = node.attributes().iterator();
            while (it.hasNext()) {
                Attribute next = it.next();
                String replaceAll = next.getKey().replaceAll("[^-a-zA-Z0-9_:.]", "");
                if (replaceAll.matches("[a-zA-Z_:][-a-zA-Z0-9_:.]*")) {
                    element.setAttribute(replaceAll, next.getValue());
                }
            }
        }

        private String updateNamespaces(org.jsoup.nodes.Element element) {
            String str;
            Iterator<Attribute> it = element.attributes().iterator();
            while (true) {
                str = "";
                if (!it.hasNext()) {
                    break;
                }
                Attribute next = it.next();
                String key = next.getKey();
                if (!key.equals("xmlns")) {
                    if (key.startsWith(xmlnsPrefix)) {
                        str = key.substring(6);
                    }
                }
                this.namespacesStack.peek().put(str, next.getValue());
            }
            int indexOf = element.tagName().indexOf(":");
            return indexOf > 0 ? element.tagName().substring(0, indexOf) : str;
        }
    }
}
