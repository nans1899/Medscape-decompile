package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class Traverser {
    private final Context context;
    private final Style style;

    public Traverser(Context context2) {
        this.style = context2.getStyle();
        this.context = context2;
    }

    private Decorator getDecorator(Class cls) throws Exception {
        return this.context.getDecorator(cls);
    }

    public Object read(InputNode inputNode, Class cls) throws Exception {
        Object read = getComposite(cls).read(inputNode);
        if (read != null) {
            return read(inputNode, read.getClass(), read);
        }
        return null;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Class<?> cls = obj.getClass();
        return read(inputNode, cls, getComposite(cls).read(inputNode, obj));
    }

    private Object read(InputNode inputNode, Class cls, Object obj) throws Exception {
        if (getName(cls) != null) {
            return obj;
        }
        throw new RootException("Root annotation required for %s", cls);
    }

    public boolean validate(InputNode inputNode, Class cls) throws Exception {
        Composite composite = getComposite(cls);
        if (getName(cls) != null) {
            return composite.validate(inputNode);
        }
        throw new RootException("Root annotation required for %s", cls);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        write(outputNode, obj, obj.getClass());
    }

    public void write(OutputNode outputNode, Object obj, Class cls) throws Exception {
        Class<?> cls2 = obj.getClass();
        String name = getName(cls2);
        if (name != null) {
            write(outputNode, obj, cls, name);
        } else {
            throw new RootException("Root annotation required for %s", cls2);
        }
    }

    public void write(OutputNode outputNode, Object obj, Class cls, String str) throws Exception {
        OutputNode child = outputNode.getChild(str);
        Type type = getType(cls);
        if (obj != null) {
            Class<?> cls2 = obj.getClass();
            Decorator decorator = getDecorator(cls2);
            if (decorator != null) {
                decorator.decorate(child);
            }
            if (!this.context.setOverride(type, obj, child)) {
                getComposite(cls2).write(child, obj);
            }
        }
        child.commit();
    }

    private Composite getComposite(Class cls) throws Exception {
        Type type = getType(cls);
        if (cls != null) {
            return new Composite(this.context, type);
        }
        throw new RootException("Can not instantiate null class", new Object[0]);
    }

    private Type getType(Class cls) {
        return new ClassType(cls);
    }

    /* access modifiers changed from: protected */
    public String getName(Class cls) throws Exception {
        return this.style.getElement(this.context.getName(cls));
    }
}
