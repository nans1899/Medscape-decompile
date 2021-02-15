package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

class CacheParameter implements Parameter {
    private final Annotation annotation;
    private final boolean attribute;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final String name;
    private final String path;
    private final boolean primitive;
    private final boolean required;
    private final String string;
    private final boolean text;
    private final Class type;

    public CacheParameter(Parameter parameter, Label label) throws Exception {
        this.annotation = parameter.getAnnotation();
        this.expression = parameter.getExpression();
        this.attribute = parameter.isAttribute();
        this.primitive = parameter.isPrimitive();
        this.required = label.isRequired();
        this.string = parameter.toString();
        this.text = parameter.isText();
        this.index = parameter.getIndex();
        this.name = parameter.getName();
        this.path = parameter.getPath();
        this.type = parameter.getType();
        this.key = label.getKey();
    }

    public Object getKey() {
        return this.key;
    }

    public Class getType() {
        return this.type;
    }

    public int getIndex() {
        return this.index;
    }

    public Annotation getAnnotation() {
        return this.annotation;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean isPrimitive() {
        return this.primitive;
    }

    public boolean isAttribute() {
        return this.attribute;
    }

    public boolean isText() {
        return this.text;
    }

    public String toString() {
        return this.string;
    }
}
