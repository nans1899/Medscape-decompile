package javax.xml.stream.events;

import javax.xml.namespace.QName;

public interface Attribute extends XMLEvent {
    String getDTDType();

    QName getName();

    String getValue();

    boolean isSpecified();
}
