package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

class TextParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key = this.label.getKey();
    private final Label label;
    private final String name = this.label.getName();
    private final String path = this.label.getPath();
    private final Class type = this.label.getType();

    public boolean isText() {
        return true;
    }

    public TextParameter(Constructor constructor, Text text, Format format, int i) throws Exception {
        Contact contact2 = new Contact(text, constructor, i);
        this.contact = contact2;
        TextLabel textLabel = new TextLabel(contact2, text, format);
        this.label = textLabel;
        this.expression = textLabel.getExpression();
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

    public String getPath(Context context) {
        return getPath();
    }

    public String getName(Context context) {
        return getName();
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

    private static class Contact extends ParameterContact<Text> {
        public String getName() {
            return "";
        }

        public Contact(Text text, Constructor constructor, int i) {
            super(text, constructor, i);
        }
    }
}
