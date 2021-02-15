package com.dd.plist;

import com.facebook.internal.ServerProtocol;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class XMLPropertyListParser {
    private static DocumentBuilderFactory docBuilderFactory;

    protected XMLPropertyListParser() {
    }

    private static synchronized void initDocBuilderFactory() throws ParserConfigurationException {
        synchronized (XMLPropertyListParser.class) {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            docBuilderFactory = newInstance;
            newInstance.setIgnoringComments(true);
            docBuilderFactory.setCoalescing(true);
        }
    }

    private static synchronized DocumentBuilder getDocBuilder() throws ParserConfigurationException {
        DocumentBuilder newDocumentBuilder;
        synchronized (XMLPropertyListParser.class) {
            if (docBuilderFactory == null) {
                initDocBuilderFactory();
            }
            newDocumentBuilder = docBuilderFactory.newDocumentBuilder();
            newDocumentBuilder.setEntityResolver(new EntityResolver() {
                public InputSource resolveEntity(String str, String str2) {
                    if ("-//Apple Computer//DTD PLIST 1.0//EN".equals(str) || "-//Apple//DTD PLIST 1.0//EN".equals(str)) {
                        return new InputSource(new ByteArrayInputStream(new byte[0]));
                    }
                    return null;
                }
            });
        }
        return newDocumentBuilder;
    }

    public static NSObject parse(File file) throws Exception {
        return parseDocument(getDocBuilder().parse(file));
    }

    public static NSObject parse(byte[] bArr) throws Exception {
        return parse((InputStream) new ByteArrayInputStream(bArr));
    }

    public static NSObject parse(InputStream inputStream) throws Exception {
        return parseDocument(getDocBuilder().parse(inputStream));
    }

    private static NSObject parseDocument(Document document) throws Exception {
        Node node;
        DocumentType doctype = document.getDoctype();
        if (doctype == null) {
            if (!document.getDocumentElement().getNodeName().equals("plist")) {
                throw new UnsupportedOperationException("The given XML document is not a property list.");
            }
        } else if (!doctype.getName().equals("plist")) {
            throw new UnsupportedOperationException("The given XML document is not a property list.");
        }
        if (document.getDocumentElement().getNodeName().equals("plist")) {
            List<Node> filterElementNodes = filterElementNodes(document.getDocumentElement().getChildNodes());
            if (filterElementNodes.isEmpty()) {
                throw new Exception("The given property list has no root element!");
            } else if (filterElementNodes.size() == 1) {
                node = filterElementNodes.get(0);
            } else {
                throw new Exception("The given property list has more than one root element!");
            }
        } else {
            node = document.getDocumentElement();
        }
        return parseObject(node);
    }

    private static NSObject parseObject(Node node) throws Exception {
        String nodeName = node.getNodeName();
        int i = 0;
        if (nodeName.equals("dict")) {
            NSDictionary nSDictionary = new NSDictionary();
            List<Node> filterElementNodes = filterElementNodes(node.getChildNodes());
            while (i < filterElementNodes.size()) {
                nSDictionary.put(getNodeTextContents(filterElementNodes.get(i)), parseObject(filterElementNodes.get(i + 1)));
                i += 2;
            }
            return nSDictionary;
        } else if (nodeName.equals("array")) {
            List<Node> filterElementNodes2 = filterElementNodes(node.getChildNodes());
            NSArray nSArray = new NSArray(filterElementNodes2.size());
            while (i < filterElementNodes2.size()) {
                nSArray.setValue(i, parseObject(filterElementNodes2.get(i)));
                i++;
            }
            return nSArray;
        } else if (nodeName.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            return new NSNumber(true);
        } else {
            if (nodeName.equals("false")) {
                return new NSNumber(false);
            }
            if (nodeName.equals("integer")) {
                return new NSNumber(getNodeTextContents(node));
            }
            if (nodeName.equals("real")) {
                return new NSNumber(getNodeTextContents(node));
            }
            if (nodeName.equals("string")) {
                return new NSString(getNodeTextContents(node));
            }
            if (nodeName.equals("data")) {
                return new NSData(getNodeTextContents(node));
            }
            if (nodeName.equals(OmnitureConstants.OMNITURE_FILTER_DATE)) {
                return new NSDate(getNodeTextContents(node));
            }
            return null;
        }
    }

    private static List<Node> filterElementNodes(NodeList nodeList) {
        ArrayList arrayList = new ArrayList(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == 1) {
                arrayList.add(nodeList.item(i));
            }
        }
        return arrayList;
    }

    private static String getNodeTextContents(Node node) {
        if (node.getNodeType() == 3 || node.getNodeType() == 4) {
            String wholeText = ((Text) node).getWholeText();
            if (wholeText == null) {
                return "";
            }
            return wholeText;
        }
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item.getNodeType() == 3 || item.getNodeType() == 4) {
                    String wholeText2 = ((Text) item).getWholeText();
                    if (wholeText2 == null) {
                        return "";
                    }
                    return wholeText2;
                }
            }
        }
        return "";
    }
}
