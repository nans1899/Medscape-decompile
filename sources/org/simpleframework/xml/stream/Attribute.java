package org.simpleframework.xml.stream;

interface Attribute {
    String getName();

    String getPrefix();

    String getReference();

    Object getSource();

    String getValue();

    boolean isReserved();
}
