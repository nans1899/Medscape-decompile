package org.jaxen;

import java.util.Iterator;
import org.jaxen.util.AncestorAxisIterator;
import org.jaxen.util.AncestorOrSelfAxisIterator;
import org.jaxen.util.DescendantAxisIterator;
import org.jaxen.util.DescendantOrSelfAxisIterator;
import org.jaxen.util.FollowingAxisIterator;
import org.jaxen.util.FollowingSiblingAxisIterator;
import org.jaxen.util.PrecedingAxisIterator;
import org.jaxen.util.PrecedingSiblingAxisIterator;
import org.jaxen.util.SelfAxisIterator;

public abstract class DefaultNavigator implements Navigator {
    public Object getDocument(String str) throws FunctionCallException {
        return null;
    }

    public Object getDocumentNode(Object obj) {
        return null;
    }

    public Object getElementById(Object obj, String str) {
        return null;
    }

    public String getProcessingInstructionData(Object obj) {
        return null;
    }

    public String getProcessingInstructionTarget(Object obj) {
        return null;
    }

    public String translateNamespacePrefixToUri(String str, Object obj) {
        return null;
    }

    public Iterator getChildAxisIterator(Object obj) throws UnsupportedAxisException {
        throw new UnsupportedAxisException("child");
    }

    public Iterator getDescendantAxisIterator(Object obj) throws UnsupportedAxisException {
        return new DescendantAxisIterator(obj, (Navigator) this);
    }

    public Iterator getParentAxisIterator(Object obj) throws UnsupportedAxisException {
        throw new UnsupportedAxisException("parent");
    }

    public Iterator getAncestorAxisIterator(Object obj) throws UnsupportedAxisException {
        return new AncestorAxisIterator(obj, this);
    }

    public Iterator getFollowingSiblingAxisIterator(Object obj) throws UnsupportedAxisException {
        return new FollowingSiblingAxisIterator(obj, this);
    }

    public Iterator getPrecedingSiblingAxisIterator(Object obj) throws UnsupportedAxisException {
        return new PrecedingSiblingAxisIterator(obj, this);
    }

    public Iterator getFollowingAxisIterator(Object obj) throws UnsupportedAxisException {
        return new FollowingAxisIterator(obj, this);
    }

    public Iterator getPrecedingAxisIterator(Object obj) throws UnsupportedAxisException {
        return new PrecedingAxisIterator(obj, this);
    }

    public Iterator getAttributeAxisIterator(Object obj) throws UnsupportedAxisException {
        throw new UnsupportedAxisException("attribute");
    }

    public Iterator getNamespaceAxisIterator(Object obj) throws UnsupportedAxisException {
        throw new UnsupportedAxisException("namespace");
    }

    public Iterator getSelfAxisIterator(Object obj) throws UnsupportedAxisException {
        return new SelfAxisIterator(obj);
    }

    public Iterator getDescendantOrSelfAxisIterator(Object obj) throws UnsupportedAxisException {
        return new DescendantOrSelfAxisIterator(obj, this);
    }

    public Iterator getAncestorOrSelfAxisIterator(Object obj) throws UnsupportedAxisException {
        return new AncestorOrSelfAxisIterator(obj, this);
    }

    public short getNodeType(Object obj) {
        if (isElement(obj)) {
            return 1;
        }
        if (isAttribute(obj)) {
            return 2;
        }
        if (isText(obj)) {
            return 3;
        }
        if (isComment(obj)) {
            return 8;
        }
        if (isDocument(obj)) {
            return 9;
        }
        if (isProcessingInstruction(obj)) {
            return 7;
        }
        return isNamespace(obj) ? (short) 13 : 14;
    }

    public Object getParentNode(Object obj) throws UnsupportedAxisException {
        Iterator parentAxisIterator = getParentAxisIterator(obj);
        if (parentAxisIterator == null || !parentAxisIterator.hasNext()) {
            return null;
        }
        return parentAxisIterator.next();
    }
}
