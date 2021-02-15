package org.simpleframework.xml.stream;

import java.util.ArrayList;

abstract class EventElement extends ArrayList<Attribute> implements EventNode {
    public int getLine() {
        return -1;
    }

    public String getValue() {
        return null;
    }

    public boolean isEnd() {
        return false;
    }

    public boolean isStart() {
        return true;
    }

    public boolean isText() {
        return false;
    }

    EventElement() {
    }
}
