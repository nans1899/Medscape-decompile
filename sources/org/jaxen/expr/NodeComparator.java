package org.jaxen.expr;

import java.util.Comparator;
import java.util.Iterator;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

class NodeComparator implements Comparator {
    private Navigator navigator;

    NodeComparator(Navigator navigator2) {
        this.navigator = navigator2;
    }

    public int compare(Object obj, Object obj2) {
        if (this.navigator == null) {
            return 0;
        }
        if (!isNonChild(obj) || !isNonChild(obj2)) {
            try {
                int depth = getDepth(obj);
                int depth2 = getDepth(obj2);
                Object obj3 = obj;
                while (depth > depth2) {
                    obj3 = this.navigator.getParentNode(obj3);
                    depth--;
                }
                if (obj3 == obj2) {
                    return 1;
                }
                while (depth2 > depth) {
                    obj2 = this.navigator.getParentNode(obj2);
                    depth2--;
                }
                if (obj2 == obj) {
                    return -1;
                }
                while (true) {
                    Object parentNode = this.navigator.getParentNode(obj3);
                    Object parentNode2 = this.navigator.getParentNode(obj2);
                    if (parentNode == parentNode2) {
                        return compareSiblings(obj3, obj2);
                    }
                    obj3 = parentNode;
                    obj2 = parentNode2;
                }
            } catch (UnsupportedAxisException unused) {
                return 0;
            }
        } else {
            try {
                Object parentNode3 = this.navigator.getParentNode(obj);
                Object parentNode4 = this.navigator.getParentNode(obj2);
                if (parentNode3 == parentNode4) {
                    if (this.navigator.isNamespace(obj) && this.navigator.isAttribute(obj2)) {
                        return -1;
                    }
                    if (this.navigator.isNamespace(obj2) && this.navigator.isAttribute(obj)) {
                        return 1;
                    }
                }
                return compare(parentNode3, parentNode4);
            } catch (UnsupportedAxisException unused2) {
                return 0;
            }
        }
    }

    private boolean isNonChild(Object obj) {
        return this.navigator.isAttribute(obj) || this.navigator.isNamespace(obj);
    }

    private int compareSiblings(Object obj, Object obj2) throws UnsupportedAxisException {
        Iterator followingSiblingAxisIterator = this.navigator.getFollowingSiblingAxisIterator(obj);
        while (followingSiblingAxisIterator.hasNext()) {
            if (followingSiblingAxisIterator.next().equals(obj2)) {
                return -1;
            }
        }
        return 1;
    }

    private int getDepth(Object obj) throws UnsupportedAxisException {
        int i = 0;
        while (true) {
            obj = this.navigator.getParentNode(obj);
            if (obj == null) {
                return i;
            }
            i++;
        }
    }
}
