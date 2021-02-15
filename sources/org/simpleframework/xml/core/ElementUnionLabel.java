package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

class ElementUnionLabel extends TemplateLabel {
    private Contact contact;
    private GroupExtractor extractor;
    private Label label;
    private Expression path;
    private ElementUnion union;

    public boolean isUnion() {
        return true;
    }

    public ElementUnionLabel(Contact contact2, ElementUnion elementUnion, Element element, Format format) throws Exception {
        this.extractor = new GroupExtractor(contact2, elementUnion, format);
        this.label = new ElementLabel(contact2, element, format);
        this.contact = contact2;
        this.union = elementUnion;
    }

    public Contact getContact() {
        return this.contact;
    }

    public Annotation getAnnotation() {
        return this.label.getAnnotation();
    }

    public Label getLabel(Class cls) throws Exception {
        Contact contact2 = getContact();
        if (this.extractor.isValid(cls)) {
            return this.extractor.getLabel(cls);
        }
        throw new UnionException("No type matches %s in %s for %s", cls, this.union, contact2);
    }

    public Type getType(Class cls) throws Exception {
        Contact contact2 = getContact();
        if (this.extractor.isValid(cls)) {
            return this.extractor.isDeclared(cls) ? new OverrideType(contact2, cls) : contact2;
        }
        throw new UnionException("No type matches %s in %s for %s", cls, this.union, contact2);
    }

    public Converter getConverter(Context context) throws Exception {
        Expression expression = getExpression();
        Contact contact2 = getContact();
        if (contact2 != null) {
            return new CompositeUnion(context, this.extractor, expression, contact2);
        }
        throw new UnionException("Union %s was not declared on a field or method", this.label);
    }

    public String[] getNames() throws Exception {
        return this.extractor.getNames();
    }

    public String[] getPaths() throws Exception {
        return this.extractor.getPaths();
    }

    public Object getEmpty(Context context) throws Exception {
        return this.label.getEmpty(context);
    }

    public Decorator getDecorator() throws Exception {
        return this.label.getDecorator();
    }

    public Type getDependent() throws Exception {
        return this.label.getDependent();
    }

    public String getEntry() throws Exception {
        return this.label.getEntry();
    }

    public String getName() throws Exception {
        return this.label.getName();
    }

    public String getPath() throws Exception {
        return this.label.getPath();
    }

    public Expression getExpression() throws Exception {
        if (this.path == null) {
            this.path = this.label.getExpression();
        }
        return this.path;
    }

    public String getOverride() {
        return this.label.getOverride();
    }

    public Class getType() {
        return this.label.getType();
    }

    public boolean isCollection() {
        return this.label.isCollection();
    }

    public boolean isData() {
        return this.label.isData();
    }

    public boolean isInline() {
        return this.label.isInline();
    }

    public boolean isRequired() {
        return this.label.isRequired();
    }

    public String toString() {
        return this.label.toString();
    }
}
