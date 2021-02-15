package org.mozilla.javascript.xml.impl.xmlbeans;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;

public class LogicalEquality {
    public static boolean nodesEqual(XmlCursor xmlCursor, XmlCursor xmlCursor2) {
        if (xmlCursor.isStartdoc()) {
            xmlCursor.toFirstContentToken();
        }
        if (xmlCursor2.isStartdoc()) {
            xmlCursor2.toFirstContentToken();
        }
        if (xmlCursor.currentTokenType() == xmlCursor2.currentTokenType()) {
            if (xmlCursor.isEnddoc()) {
                return true;
            }
            if (xmlCursor.isAttr()) {
                return attributesEqual(xmlCursor, xmlCursor2);
            }
            if (xmlCursor.isText()) {
                return textNodesEqual(xmlCursor, xmlCursor2);
            }
            if (xmlCursor.isComment()) {
                return commentsEqual(xmlCursor, xmlCursor2);
            }
            if (xmlCursor.isProcinst()) {
                return processingInstructionsEqual(xmlCursor, xmlCursor2);
            }
            if (xmlCursor.isStart()) {
                return elementsEqual(xmlCursor, xmlCursor2);
            }
        }
        return false;
    }

    private static boolean elementsEqual(XmlCursor xmlCursor, XmlCursor xmlCursor2) {
        if (!qnamesEqual(xmlCursor.getName(), xmlCursor2.getName())) {
            return false;
        }
        nextToken(xmlCursor);
        nextToken(xmlCursor2);
        boolean z = true;
        while (xmlCursor.currentTokenType() == xmlCursor2.currentTokenType()) {
            if (xmlCursor.isEnd() || xmlCursor.isEnddoc()) {
                return z;
            }
            if (xmlCursor.isAttr()) {
                z = attributeListsEqual(xmlCursor, xmlCursor2);
                continue;
            } else {
                if (xmlCursor.isText()) {
                    z = textNodesEqual(xmlCursor, xmlCursor2);
                } else if (xmlCursor.isComment()) {
                    z = commentsEqual(xmlCursor, xmlCursor2);
                } else if (xmlCursor.isProcinst()) {
                    z = processingInstructionsEqual(xmlCursor, xmlCursor2);
                } else if (xmlCursor.isStart()) {
                    z = elementsEqual(xmlCursor, xmlCursor2);
                }
                nextToken(xmlCursor);
                nextToken(xmlCursor2);
                continue;
            }
            if (!z) {
                return z;
            }
        }
        return false;
    }

    private static boolean attributeListsEqual(XmlCursor xmlCursor, XmlCursor xmlCursor2) {
        TreeMap loadAttributeMap = loadAttributeMap(xmlCursor);
        TreeMap loadAttributeMap2 = loadAttributeMap(xmlCursor2);
        if (loadAttributeMap.size() != loadAttributeMap2.size()) {
            return false;
        }
        Set keySet = loadAttributeMap.keySet();
        Set keySet2 = loadAttributeMap2.keySet();
        Iterator it = keySet.iterator();
        Iterator it2 = keySet2.iterator();
        boolean z = true;
        while (z && it.hasNext()) {
            String str = (String) it.next();
            String str2 = (String) it2.next();
            if (!str.equals(str2) || !qnamesEqual((QName) loadAttributeMap.get(str), (QName) loadAttributeMap2.get(str2))) {
                z = false;
            }
        }
        return z;
    }

    private static TreeMap loadAttributeMap(XmlCursor xmlCursor) {
        TreeMap treeMap = new TreeMap();
        while (xmlCursor.isAttr()) {
            treeMap.put(xmlCursor.getTextValue(), xmlCursor.getName());
            nextToken(xmlCursor);
        }
        return treeMap;
    }

    private static boolean attributesEqual(XmlCursor xmlCursor, XmlCursor xmlCursor2) {
        return xmlCursor.isAttr() && xmlCursor2.isAttr() && qnamesEqual(xmlCursor.getName(), xmlCursor2.getName()) && xmlCursor.getTextValue().equals(xmlCursor2.getTextValue());
    }

    private static boolean textNodesEqual(XmlCursor xmlCursor, XmlCursor xmlCursor2) {
        return xmlCursor.isText() && xmlCursor2.isText() && xmlCursor.getChars().equals(xmlCursor2.getChars());
    }

    private static boolean commentsEqual(XmlCursor xmlCursor, XmlCursor xmlCursor2) {
        return xmlCursor.isComment() && xmlCursor2.isComment() && xmlCursor.getTextValue().equals(xmlCursor2.getTextValue());
    }

    private static boolean processingInstructionsEqual(XmlCursor xmlCursor, XmlCursor xmlCursor2) {
        return xmlCursor.isProcinst() && xmlCursor2.isProcinst() && qnamesEqual(xmlCursor.getName(), xmlCursor2.getName()) && xmlCursor.getTextValue().equals(xmlCursor2.getTextValue());
    }

    private static boolean qnamesEqual(QName qName, QName qName2) {
        return qName.getNamespaceURI().equals(qName2.getNamespaceURI()) && qName.getLocalPart().equals(qName2.getLocalPart());
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void nextToken(org.apache.xmlbeans.XmlCursor r1) {
        /*
        L_0x0000:
            r1.toNextToken()
            boolean r0 = r1.isText()
            if (r0 != 0) goto L_0x000a
            goto L_0x0018
        L_0x000a:
            java.lang.String r0 = r1.getChars()
            java.lang.String r0 = r0.trim()
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0000
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.LogicalEquality.nextToken(org.apache.xmlbeans.XmlCursor):void");
    }
}
