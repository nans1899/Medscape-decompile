package com.bea.xml.stream;

import com.bea.xml.stream.util.ElementTypeNames;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;

public class EventState {
    private List attributes;
    private String data;
    private String extraData;
    private List namespaces;
    private QName qname;
    private int type;

    public EventState() {
    }

    public EventState(int i) {
        this.type = i;
        this.attributes = new ArrayList();
        this.namespaces = new ArrayList();
    }

    public void clear() {
        this.qname = null;
        this.attributes = new ArrayList();
        this.namespaces = new ArrayList();
        this.data = null;
        this.extraData = null;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public QName getName() {
        return this.qname;
    }

    public String getLocalName() {
        return this.qname.getLocalPart();
    }

    public String getPrefix() {
        return this.qname.getPrefix();
    }

    public String getNamespaceURI() {
        return this.qname.getNamespaceURI();
    }

    public void setName(QName qName) {
        this.qname = qName;
    }

    public void setAttributes(List list) {
        this.attributes = list;
    }

    public void addAttribute(Object obj) {
        this.attributes.add(obj);
    }

    public void addNamespace(Object obj) {
        this.namespaces.add(obj);
    }

    public List getAttributes() {
        return this.attributes;
    }

    public void setNamespaces(List list) {
        this.namespaces = list;
    }

    public List getNamespaces() {
        return this.namespaces;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String getExtraData() {
        return this.extraData;
    }

    public void setExtraData(String str) {
        this.extraData = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("[");
        stringBuffer2.append(ElementTypeNames.getEventTypeString(this.type));
        stringBuffer2.append("]");
        stringBuffer.append(stringBuffer2.toString());
        if (this.qname != null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("[name='");
            stringBuffer3.append(this.qname);
            stringBuffer3.append("']");
            stringBuffer.append(stringBuffer3.toString());
        }
        for (Object append : this.namespaces) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append(append);
            stringBuffer4.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer.append(stringBuffer4.toString());
        }
        for (Object append2 : this.attributes) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(append2);
            stringBuffer5.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer.append(stringBuffer5.toString());
        }
        if (this.data != null) {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append(",data=[");
            stringBuffer6.append(this.data);
            stringBuffer6.append("]");
            stringBuffer.append(stringBuffer6.toString());
        }
        if (this.extraData != null) {
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append(",extradata=[");
            stringBuffer7.append(this.extraData);
            stringBuffer7.append("]");
            stringBuffer.append(stringBuffer7.toString());
        }
        return stringBuffer.toString();
    }
}
