package org.jaxen;

import java.io.Serializable;
import java.util.Iterator;
import org.jaxen.saxpath.SAXPathException;

public interface Navigator extends Serializable {
    Iterator getAncestorAxisIterator(Object obj) throws UnsupportedAxisException;

    Iterator getAncestorOrSelfAxisIterator(Object obj) throws UnsupportedAxisException;

    Iterator getAttributeAxisIterator(Object obj) throws UnsupportedAxisException;

    String getAttributeName(Object obj);

    String getAttributeNamespaceUri(Object obj);

    String getAttributeQName(Object obj);

    String getAttributeStringValue(Object obj);

    Iterator getChildAxisIterator(Object obj) throws UnsupportedAxisException;

    String getCommentStringValue(Object obj);

    Iterator getDescendantAxisIterator(Object obj) throws UnsupportedAxisException;

    Iterator getDescendantOrSelfAxisIterator(Object obj) throws UnsupportedAxisException;

    Object getDocument(String str) throws FunctionCallException;

    Object getDocumentNode(Object obj);

    Object getElementById(Object obj, String str);

    String getElementName(Object obj);

    String getElementNamespaceUri(Object obj);

    String getElementQName(Object obj);

    String getElementStringValue(Object obj);

    Iterator getFollowingAxisIterator(Object obj) throws UnsupportedAxisException;

    Iterator getFollowingSiblingAxisIterator(Object obj) throws UnsupportedAxisException;

    Iterator getNamespaceAxisIterator(Object obj) throws UnsupportedAxisException;

    String getNamespacePrefix(Object obj);

    String getNamespaceStringValue(Object obj);

    short getNodeType(Object obj);

    Iterator getParentAxisIterator(Object obj) throws UnsupportedAxisException;

    Object getParentNode(Object obj) throws UnsupportedAxisException;

    Iterator getPrecedingAxisIterator(Object obj) throws UnsupportedAxisException;

    Iterator getPrecedingSiblingAxisIterator(Object obj) throws UnsupportedAxisException;

    String getProcessingInstructionData(Object obj);

    String getProcessingInstructionTarget(Object obj);

    Iterator getSelfAxisIterator(Object obj) throws UnsupportedAxisException;

    String getTextStringValue(Object obj);

    boolean isAttribute(Object obj);

    boolean isComment(Object obj);

    boolean isDocument(Object obj);

    boolean isElement(Object obj);

    boolean isNamespace(Object obj);

    boolean isProcessingInstruction(Object obj);

    boolean isText(Object obj);

    XPath parseXPath(String str) throws SAXPathException;

    String translateNamespacePrefixToUri(String str, Object obj);
}
