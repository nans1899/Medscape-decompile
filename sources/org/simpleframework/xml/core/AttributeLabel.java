package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.stream.Format;

class AttributeLabel extends TemplateLabel {
    private Decorator decorator;
    private Introspector detail;
    private String empty;
    private Format format;
    private Attribute label;
    private String name;
    private Expression path;
    private boolean required;
    private Class type;

    public boolean isAttribute() {
        return true;
    }

    public boolean isData() {
        return false;
    }

    public AttributeLabel(Contact contact, Attribute attribute, Format format2) {
        this.detail = new Introspector(contact, this, format2);
        this.decorator = new Qualifier(contact);
        this.required = attribute.required();
        this.type = contact.getType();
        this.empty = attribute.empty();
        this.name = attribute.name();
        this.format = format2;
        this.label = attribute;
    }

    public Decorator getDecorator() throws Exception {
        return this.decorator;
    }

    public Converter getConverter(Context context) throws Exception {
        return new Primitive(context, getContact(), getEmpty(context));
    }

    public String getEmpty(Context context) {
        if (this.detail.isEmpty(this.empty)) {
            return null;
        }
        return this.empty;
    }

    public String getName() throws Exception {
        return this.format.getStyle().getAttribute(this.detail.getName());
    }

    public String getPath() throws Exception {
        return getExpression().getAttribute(getName());
    }

    public Expression getExpression() throws Exception {
        if (this.path == null) {
            this.path = this.detail.getExpression();
        }
        return this.path;
    }

    public Annotation getAnnotation() {
        return this.label;
    }

    public String getOverride() {
        return this.name;
    }

    public Contact getContact() {
        return this.detail.getContact();
    }

    public Class getType() {
        return this.type;
    }

    public boolean isRequired() {
        return this.required;
    }

    public String toString() {
        return this.detail.toString();
    }
}
