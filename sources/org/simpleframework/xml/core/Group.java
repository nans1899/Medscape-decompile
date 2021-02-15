package org.simpleframework.xml.core;

interface Group {
    LabelMap getElements() throws Exception;

    Label getLabel(Class cls);

    Label getText() throws Exception;

    boolean isInline();

    boolean isTextList();

    String toString();
}
