package org.dom4j;

import java.util.Map;

public interface ProcessingInstruction extends Node {
    String getTarget();

    String getText();

    String getValue(String str);

    Map getValues();

    boolean removeValue(String str);

    void setTarget(String str);

    void setValue(String str, String str2);

    void setValues(Map map);
}
