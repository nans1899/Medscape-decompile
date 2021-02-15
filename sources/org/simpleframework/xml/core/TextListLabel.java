package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.strategy.Type;

class TextListLabel extends TemplateLabel {
    private final String empty;
    private final Label label;
    private final Text text;

    public Decorator getDecorator() throws Exception {
        return null;
    }

    public boolean isCollection() {
        return true;
    }

    public boolean isTextList() {
        return true;
    }

    public TextListLabel(Label label2, Text text2) {
        this.empty = text2.empty();
        this.label = label2;
        this.text = text2;
    }

    public String[] getNames() throws Exception {
        return this.label.getNames();
    }

    public String[] getPaths() throws Exception {
        return this.label.getPaths();
    }

    public String getEmpty(Context context) throws Exception {
        return this.empty;
    }

    public Converter getConverter(Context context) throws Exception {
        Contact contact = getContact();
        if (this.label.isCollection()) {
            return new TextList(context, contact, this.label);
        }
        throw new TextException("Cannot use %s to represent %s", contact, this.label);
    }

    public String getName() throws Exception {
        return this.label.getName();
    }

    public String getPath() throws Exception {
        return this.label.getPath();
    }

    public Expression getExpression() throws Exception {
        return this.label.getExpression();
    }

    public Type getDependent() throws Exception {
        return this.label.getDependent();
    }

    public String getEntry() throws Exception {
        return this.label.getEntry();
    }

    public Object getKey() throws Exception {
        return this.label.getKey();
    }

    public Annotation getAnnotation() {
        return this.label.getAnnotation();
    }

    public Contact getContact() {
        return this.label.getContact();
    }

    public Class getType() {
        return this.label.getType();
    }

    public String getOverride() {
        return this.label.getOverride();
    }

    public boolean isData() {
        return this.label.isData();
    }

    public boolean isRequired() {
        return this.label.isRequired();
    }

    public boolean isInline() {
        return this.label.isInline();
    }

    public String toString() {
        return String.format("%s %s", new Object[]{this.text, this.label});
    }
}
