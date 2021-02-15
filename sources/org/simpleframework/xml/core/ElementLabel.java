package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

class ElementLabel extends TemplateLabel {
    private Expression cache;
    private boolean data;
    private Decorator decorator;
    private Introspector detail;
    private Class expect;
    private Format format;
    private Element label;
    private String name;
    private String override;
    private String path;
    private boolean required;
    private Class type;

    public Object getEmpty(Context context) {
        return null;
    }

    public ElementLabel(Contact contact, Element element, Format format2) {
        this.detail = new Introspector(contact, this, format2);
        this.decorator = new Qualifier(contact);
        this.required = element.required();
        this.type = contact.getType();
        this.override = element.name();
        this.expect = element.type();
        this.data = element.data();
        this.format = format2;
        this.label = element;
    }

    public Decorator getDecorator() throws Exception {
        return this.decorator;
    }

    public Type getType(Class cls) {
        Contact contact = getContact();
        if (this.expect == Void.TYPE) {
            return contact;
        }
        return new OverrideType(contact, this.expect);
    }

    public Converter getConverter(Context context) throws Exception {
        Contact contact = getContact();
        if (context.isPrimitive((Type) contact)) {
            return new Primitive(context, contact);
        }
        if (this.expect == Void.TYPE) {
            return new Composite(context, contact);
        }
        return new Composite(context, contact, this.expect);
    }

    public String getName() throws Exception {
        if (this.name == null) {
            this.name = this.format.getStyle().getElement(this.detail.getName());
        }
        return this.name;
    }

    public String getPath() throws Exception {
        if (this.path == null) {
            this.path = getExpression().getElement(getName());
        }
        return this.path;
    }

    public Expression getExpression() throws Exception {
        if (this.cache == null) {
            this.cache = this.detail.getExpression();
        }
        return this.cache;
    }

    public Annotation getAnnotation() {
        return this.label;
    }

    public Contact getContact() {
        return this.detail.getContact();
    }

    public String getOverride() {
        return this.override;
    }

    public Class getType() {
        if (this.expect == Void.TYPE) {
            return this.type;
        }
        return this.expect;
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean isData() {
        return this.data;
    }

    public String toString() {
        return this.detail.toString();
    }
}
