package org.jaxen;

import java.util.Iterator;

public interface NamedAccessNavigator extends Navigator {
    Iterator getAttributeAxisIterator(Object obj, String str, String str2, String str3) throws UnsupportedAxisException;

    Iterator getChildAxisIterator(Object obj, String str, String str2, String str3) throws UnsupportedAxisException;
}
