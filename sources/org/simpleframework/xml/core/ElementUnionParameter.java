package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.stream.Format;

class ElementUnionParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key = this.label.getKey();
    private final Label label;
    private final String name = this.label.getName();
    private final String path = this.label.getPath();
    private final Class type = this.label.getType();

    public ElementUnionParameter(Constructor constructor, ElementUnion elementUnion, Element element, Format format, int i) throws Exception {
        Contact contact2 = new Contact(element, constructor, i);
        this.contact = contact2;
        ElementUnionLabel elementUnionLabel = new ElementUnionLabel(contact2, elementUnion, element, format);
        this.label = elementUnionLabel;
        this.expression = elementUnionLabel.getExpression();
        this.index = i;
    }

    public Object getKey() {
        return this.key;
    }

    public String getPath() {
        return this.path;
    }

    public String getName() {
        return this.name;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public Class getType() {
        return this.type;
    }

    public Annotation getAnnotation() {
        return this.contact.getAnnotation();
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isRequired() {
        return this.label.isRequired();
    }

    public boolean isPrimitive() {
        return this.type.isPrimitive();
    }

    public String toString() {
        return this.contact.toString();
    }

    private static class Contact extends ParameterContact<Element> {
        public Contact(Element element, Constructor constructor, int i) {
            super(element, constructor, i);
        }

        public String getName() {
            return ((Element) this.label).name();
        }
    }
}
