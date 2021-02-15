package org.dom4j;

import java.util.Iterator;
import java.util.List;

public interface Branch extends Node {
    void add(Comment comment);

    void add(Element element);

    void add(Node node);

    void add(ProcessingInstruction processingInstruction);

    Element addElement(String str);

    Element addElement(String str, String str2);

    Element addElement(QName qName);

    void appendContent(Branch branch);

    void clearContent();

    List content();

    Element elementByID(String str);

    int indexOf(Node node);

    Node node(int i) throws IndexOutOfBoundsException;

    int nodeCount();

    Iterator nodeIterator();

    void normalize();

    ProcessingInstruction processingInstruction(String str);

    List processingInstructions();

    List processingInstructions(String str);

    boolean remove(Comment comment);

    boolean remove(Element element);

    boolean remove(Node node);

    boolean remove(ProcessingInstruction processingInstruction);

    boolean removeProcessingInstruction(String str);

    void setContent(List list);

    void setProcessingInstructions(List list);
}
