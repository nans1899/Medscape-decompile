package org.simpleframework.xml.core;

abstract class TemplateParameter implements Parameter {
    public boolean isAttribute() {
        return false;
    }

    public boolean isText() {
        return false;
    }

    protected TemplateParameter() {
    }
}
