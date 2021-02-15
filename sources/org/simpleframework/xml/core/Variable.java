package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class Variable implements Label {
    private final Label label;
    private final Object value;

    public Label getLabel(Class cls) {
        return this;
    }

    public Variable(Label label2, Object obj) {
        this.label = label2;
        this.value = obj;
    }

    public Type getType(Class cls) throws Exception {
        return this.label.getType(cls);
    }

    public String[] getNames() throws Exception {
        return this.label.getNames();
    }

    public String[] getPaths() throws Exception {
        return this.label.getPaths();
    }

    public Object getValue() {
        return this.value;
    }

    public Decorator getDecorator() throws Exception {
        return this.label.getDecorator();
    }

    public Converter getConverter(Context context) throws Exception {
        Converter converter = this.label.getConverter(context);
        if (converter instanceof Adapter) {
            return converter;
        }
        return new Adapter(converter, this.label, this.value);
    }

    public Object getEmpty(Context context) throws Exception {
        return this.label.getEmpty(context);
    }

    public Contact getContact() {
        return this.label.getContact();
    }

    public Type getDependent() throws Exception {
        return this.label.getDependent();
    }

    public Object getKey() throws Exception {
        return this.label.getKey();
    }

    public String getEntry() throws Exception {
        return this.label.getEntry();
    }

    public String getName() throws Exception {
        return this.label.getName();
    }

    public Annotation getAnnotation() {
        return this.label.getAnnotation();
    }

    public String getPath() throws Exception {
        return this.label.getPath();
    }

    public Expression getExpression() throws Exception {
        return this.label.getExpression();
    }

    public String getOverride() {
        return this.label.getOverride();
    }

    public Class getType() {
        return this.label.getType();
    }

    public boolean isData() {
        return this.label.isData();
    }

    public boolean isInline() {
        return this.label.isInline();
    }

    public boolean isAttribute() {
        return this.label.isAttribute();
    }

    public boolean isCollection() {
        return this.label.isCollection();
    }

    public boolean isRequired() {
        return this.label.isRequired();
    }

    public boolean isText() {
        return this.label.isText();
    }

    public boolean isTextList() {
        return this.label.isTextList();
    }

    public boolean isUnion() {
        return this.label.isUnion();
    }

    public String toString() {
        return this.label.toString();
    }

    private static class Adapter implements Repeater {
        private final Label label;
        private final Converter reader;
        private final Object value;

        public Adapter(Converter converter, Label label2, Object obj) {
            this.reader = converter;
            this.value = obj;
            this.label = label2;
        }

        public Object read(InputNode inputNode) throws Exception {
            return read(inputNode, this.value);
        }

        public Object read(InputNode inputNode, Object obj) throws Exception {
            Position position = inputNode.getPosition();
            String name = inputNode.getName();
            Converter converter = this.reader;
            if (converter instanceof Repeater) {
                return ((Repeater) converter).read(inputNode, obj);
            }
            throw new PersistenceException("Element '%s' is already used with %s at %s", name, this.label, position);
        }

        public boolean validate(InputNode inputNode) throws Exception {
            Position position = inputNode.getPosition();
            String name = inputNode.getName();
            Converter converter = this.reader;
            if (converter instanceof Repeater) {
                return ((Repeater) converter).validate(inputNode);
            }
            throw new PersistenceException("Element '%s' declared twice at %s", name, position);
        }

        public void write(OutputNode outputNode, Object obj) throws Exception {
            write(outputNode, obj);
        }
    }
}
