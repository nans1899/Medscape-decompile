package org.jaxen.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class FollowingSiblingAxisIterator implements Iterator {
    private Object contextNode;
    private Navigator navigator;
    private Iterator siblingIter;

    public FollowingSiblingAxisIterator(Object obj, Navigator navigator2) throws UnsupportedAxisException {
        this.contextNode = obj;
        this.navigator = navigator2;
        init();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0012 A[LOOP:0: B:3:0x0012->B:6:0x0026, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void init() throws org.jaxen.UnsupportedAxisException {
        /*
            r2 = this;
            org.jaxen.Navigator r0 = r2.navigator
            java.lang.Object r1 = r2.contextNode
            java.lang.Object r0 = r0.getParentNode(r1)
            if (r0 == 0) goto L_0x0029
            org.jaxen.Navigator r1 = r2.navigator
            java.util.Iterator r0 = r1.getChildAxisIterator(r0)
            r2.siblingIter = r0
        L_0x0012:
            java.util.Iterator r0 = r2.siblingIter
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L_0x002d
            java.util.Iterator r0 = r2.siblingIter
            java.lang.Object r0 = r0.next()
            java.lang.Object r1 = r2.contextNode
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0012
            goto L_0x002d
        L_0x0029:
            java.util.Iterator r0 = org.jaxen.JaxenConstants.EMPTY_ITERATOR
            r2.siblingIter = r0
        L_0x002d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaxen.util.FollowingSiblingAxisIterator.init():void");
    }

    public boolean hasNext() {
        return this.siblingIter.hasNext();
    }

    public Object next() throws NoSuchElementException {
        return this.siblingIter.next();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
