package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class Primitive implements Converter {
    private final Context context;
    private final String empty;
    private final Class expect;
    private final PrimitiveFactory factory;
    private final Type type;

    public Primitive(Context context2, Type type2) {
        this(context2, type2, (String) null);
    }

    public Primitive(Context context2, Type type2, String str) {
        this.factory = new PrimitiveFactory(context2, type2);
        this.expect = type2.getType();
        this.context = context2;
        this.empty = str;
        this.type = type2;
    }

    public Object read(InputNode inputNode) throws Exception {
        if (inputNode.isElement()) {
            return readElement(inputNode);
        }
        return read(inputNode, this.expect);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        if (obj == null) {
            return read(inputNode);
        }
        throw new PersistenceException("Can not read existing %s for %s", this.expect, this.type);
    }

    public Object read(InputNode inputNode, Class cls) throws Exception {
        String value = inputNode.getValue();
        if (value == null) {
            return null;
        }
        String str = this.empty;
        if (str == null || !value.equals(str)) {
            return readTemplate(value, cls);
        }
        return this.empty;
    }

    private Object readElement(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        if (!instance.isReference()) {
            return readElement(inputNode, instance);
        }
        return instance.getInstance();
    }

    private Object readElement(InputNode inputNode, Instance instance) throws Exception {
        Object read = read(inputNode, this.expect);
        if (instance != null) {
            instance.setInstance(read);
        }
        return read;
    }

    private Object readTemplate(String str, Class cls) throws Exception {
        String property = this.context.getProperty(str);
        if (property != null) {
            return this.factory.getInstance(property, cls);
        }
        return null;
    }

    public boolean validate(InputNode inputNode) throws Exception {
        if (inputNode.isElement()) {
            validateElement(inputNode);
            return true;
        }
        inputNode.getValue();
        return true;
    }

    private boolean validateElement(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        if (instance.isReference()) {
            return true;
        }
        instance.setInstance((Object) null);
        return true;
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        String text = this.factory.getText(obj);
        if (text != null) {
            outputNode.setValue(text);
        }
    }
}
