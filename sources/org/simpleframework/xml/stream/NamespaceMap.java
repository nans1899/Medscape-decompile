package org.simpleframework.xml.stream;

import java.util.Iterator;

public interface NamespaceMap extends Iterable<String> {
    String getPrefix();

    String getPrefix(String str);

    String getReference(String str);

    Iterator<String> iterator();

    String setReference(String str);

    String setReference(String str, String str2);
}
