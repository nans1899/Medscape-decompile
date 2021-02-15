package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

abstract class TemplateLabel implements Label {
    private final KeyBuilder builder = new KeyBuilder(this);

    public Type getDependent() throws Exception {
        return null;
    }

    public String getEntry() throws Exception {
        return null;
    }

    public Label getLabel(Class cls) throws Exception {
        return this;
    }

    public boolean isAttribute() {
        return false;
    }

    public boolean isCollection() {
        return false;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isText() {
        return false;
    }

    public boolean isTextList() {
        return false;
    }

    public boolean isUnion() {
        return false;
    }

    protected TemplateLabel() {
    }

    public Type getType(Class cls) throws Exception {
        return getContact();
    }

    public String[] getNames() throws Exception {
        return new String[]{getPath(), getName()};
    }

    public String[] getPaths() throws Exception {
        return new String[]{getPath()};
    }

    public Object getKey() throws Exception {
        return this.builder.getKey();
    }
}
