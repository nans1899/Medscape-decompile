package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.stream.Format;

class ElementListUnionParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key = this.label.getKey();
    private final Label label;
    private final String name = this.label.getName();
    private final String path = this.label.getPath();
    private final Class type = this.label.getType();

    public ElementListUnionParameter(Constructor constructor, ElementListUnion elementListUnion, ElementList elementList, Format format, int i) throws Exception {
        Contact contact2 = new Contact(elementList, constructor, i);
        this.contact = contact2;
        ElementListUnionLabel elementListUnionLabel = new ElementListUnionLabel(contact2, elementListUnion, elementList, format);
        this.label = elementListUnionLabel;
        this.expression = elementListUnionLabel.getExpression();
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

    private static class Contact extends ParameterContact<ElementList> {
        public Contact(ElementList elementList, Constructor constructor, int i) {
            super(elementList, constructor, i);
        }

        public String getName() {
            return ((ElementList) this.label).name();
        }
    }
}
