package org.simpleframework.xml.stream;

import java.util.Iterator;
import org.simpleframework.xml.stream.Node;

public interface NodeMap<T extends Node> extends Iterable<String> {
    T get(String str);

    String getName();

    T getNode();

    Iterator<String> iterator();

    T put(String str, String str2);

    T remove(String str);
}
