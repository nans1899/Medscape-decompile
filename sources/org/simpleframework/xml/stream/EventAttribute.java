package org.simpleframework.xml.stream;

abstract class EventAttribute implements Attribute {
    public String getPrefix() {
        return null;
    }

    public String getReference() {
        return null;
    }

    public Object getSource() {
        return null;
    }

    public boolean isReserved() {
        return false;
    }

    EventAttribute() {
    }
}
