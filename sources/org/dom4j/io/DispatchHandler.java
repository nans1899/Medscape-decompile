package org.dom4j.io;

import java.util.ArrayList;
import java.util.HashMap;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;

class DispatchHandler implements ElementHandler {
    private boolean atRoot = true;
    private ElementHandler defaultHandler;
    private ArrayList handlerStack = new ArrayList();
    private HashMap handlers = new HashMap();
    private String path = "/";
    private ArrayList pathStack = new ArrayList();

    public void addHandler(String str, ElementHandler elementHandler) {
        this.handlers.put(str, elementHandler);
    }

    public ElementHandler removeHandler(String str) {
        return (ElementHandler) this.handlers.remove(str);
    }

    public boolean containsHandler(String str) {
        return this.handlers.containsKey(str);
    }

    public ElementHandler getHandler(String str) {
        return (ElementHandler) this.handlers.get(str);
    }

    public int getActiveHandlerCount() {
        return this.handlerStack.size();
    }

    public void setDefaultHandler(ElementHandler elementHandler) {
        this.defaultHandler = elementHandler;
    }

    public void resetHandlers() {
        this.atRoot = true;
        this.path = "/";
        this.pathStack.clear();
        this.handlerStack.clear();
        this.handlers.clear();
        this.defaultHandler = null;
    }

    public String getPath() {
        return this.path;
    }

    public void onStart(ElementPath elementPath) {
        ElementHandler elementHandler;
        Element current = elementPath.getCurrent();
        this.pathStack.add(this.path);
        if (this.atRoot) {
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.path));
            stringBuffer.append(current.getName());
            this.path = stringBuffer.toString();
            this.atRoot = false;
        } else {
            StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(this.path));
            stringBuffer2.append("/");
            stringBuffer2.append(current.getName());
            this.path = stringBuffer2.toString();
        }
        HashMap hashMap = this.handlers;
        if (hashMap != null && hashMap.containsKey(this.path)) {
            ElementHandler elementHandler2 = (ElementHandler) this.handlers.get(this.path);
            this.handlerStack.add(elementHandler2);
            elementHandler2.onStart(elementPath);
        } else if (this.handlerStack.isEmpty() && (elementHandler = this.defaultHandler) != null) {
            elementHandler.onStart(elementPath);
        }
    }

    public void onEnd(ElementPath elementPath) {
        ElementHandler elementHandler;
        HashMap hashMap = this.handlers;
        if (hashMap != null && hashMap.containsKey(this.path)) {
            ArrayList arrayList = this.handlerStack;
            arrayList.remove(arrayList.size() - 1);
            ((ElementHandler) this.handlers.get(this.path)).onEnd(elementPath);
        } else if (this.handlerStack.isEmpty() && (elementHandler = this.defaultHandler) != null) {
            elementHandler.onEnd(elementPath);
        }
        ArrayList arrayList2 = this.pathStack;
        this.path = (String) arrayList2.remove(arrayList2.size() - 1);
        if (this.pathStack.size() == 0) {
            this.atRoot = true;
        }
    }
}
