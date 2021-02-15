package org.jaxen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Context implements Serializable {
    private static final long serialVersionUID = 2315979994685591055L;
    private ContextSupport contextSupport;
    private List nodeSet = Collections.EMPTY_LIST;
    private int position = 0;
    private int size = 0;

    public Context(ContextSupport contextSupport2) {
        this.contextSupport = contextSupport2;
    }

    public void setNodeSet(List list) {
        this.nodeSet = list;
        int size2 = list.size();
        this.size = size2;
        if (this.position >= size2) {
            this.position = 0;
        }
    }

    public List getNodeSet() {
        return this.nodeSet;
    }

    public void setContextSupport(ContextSupport contextSupport2) {
        this.contextSupport = contextSupport2;
    }

    public ContextSupport getContextSupport() {
        return this.contextSupport;
    }

    public Navigator getNavigator() {
        return getContextSupport().getNavigator();
    }

    public String translateNamespacePrefixToUri(String str) {
        return getContextSupport().translateNamespacePrefixToUri(str);
    }

    public Object getVariableValue(String str, String str2, String str3) throws UnresolvableException {
        return getContextSupport().getVariableValue(str, str2, str3);
    }

    public Function getFunction(String str, String str2, String str3) throws UnresolvableException {
        return getContextSupport().getFunction(str, str2, str3);
    }

    public void setSize(int i) {
        this.size = i;
    }

    public int getSize() {
        return this.size;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public int getPosition() {
        return this.position;
    }

    public Context duplicate() {
        Context context = new Context(getContextSupport());
        List nodeSet2 = getNodeSet();
        if (nodeSet2 != null) {
            ArrayList arrayList = new ArrayList(nodeSet2.size());
            arrayList.addAll(nodeSet2);
            context.setNodeSet(arrayList);
            context.setPosition(this.position);
        }
        return context;
    }
}
