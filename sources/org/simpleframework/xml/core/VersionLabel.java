package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

class VersionLabel extends TemplateLabel {
    private Decorator decorator;
    private Introspector detail;
    private Format format;
    private Version label;
    private String name;
    private Expression path;
    private boolean required;
    private Class type;

    public String getEmpty(Context context) {
        return null;
    }

    public boolean isAttribute() {
        return true;
    }

    public boolean isData() {
        return false;
    }

    public VersionLabel(Contact contact, Version version, Format format2) {
        this.detail = new Introspector(contact, this, format2);
        this.decorator = new Qualifier(contact);
        this.required = version.required();
        this.type = contact.getType();
        this.name = version.name();
        this.format = format2;
        this.label = version;
    }

    public Decorator getDecorator() throws Exception {
        return this.decorator;
    }

    public Converter getConverter(Context context) throws Exception {
        String empty = getEmpty(context);
        Contact contact = getContact();
        if (context.isFloat((Type) contact)) {
            return new Primitive(context, contact, empty);
        }
        throw new AttributeException("Cannot use %s to represent %s", this.label, contact);
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
