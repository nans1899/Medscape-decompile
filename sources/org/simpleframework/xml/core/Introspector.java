package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

class Introspector {
    private final Contact contact;
    private final Format format;
    private final Label label;
    private final Annotation marker;

    public Introspector(Contact contact2, Label label2, Format format2) {
        this.marker = contact2.getAnnotation();
        this.contact = contact2;
        this.format = format2;
        this.label = label2;
    }

    public Contact getContact() {
        return this.contact;
    }

    public Type getDependent() throws Exception {
        return this.label.getDependent();
    }

    public String getEntry() throws Exception {
        Class<?> type = getDependent().getType();
        if (type.isArray()) {
            type = type.getComponentType();
        }
        return getName(type);
    }

    private String getName(Class cls) throws Exception {
        String root = getRoot(cls);
        if (root != null) {
            return root;
        }
        return Reflector.getName(cls.getSimpleName());
    }

    private String getRoot(Class cls) {
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String root = getRoot(cls, cls2);
            if (root != null) {
                return root;
            }
        }
        return null;
    }

    private String getRoot(Class<?> cls, Class<?> cls2) {
        String simpleName = cls2.getSimpleName();
        Root root = (Root) cls2.getAnnotation(Root.class);
        if (root == null) {
            return null;
        }
        String name = root.name();
        if (!isEmpty(name)) {
            return name;
        }
        return Reflector.getName(simpleName);
    }

    public String getName() throws Exception {
        return !this.label.isInline() ? getDefault() : this.label.getEntry();
    }

    private String getDefault() throws Exception {
        String override = this.label.getOverride();
        if (!isEmpty(override)) {
            return override;
        }
        return this.contact.getName();
    }

    public Expression getExpression() throws Exception {
        String path = getPath();
        if (path != null) {
            return new PathParser(path, this.contact, this.format);
        }
        return new EmptyExpression(this.format);
    }

    public String getPath() throws Exception {
        Path path = (Path) this.contact.getAnnotation(Path.class);
        if (path == null) {
            return null;
        }
        return path.value();
    }

    public boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public String toString() {
        return String.format("%s on %s", new Object[]{this.marker, this.contact});
    }
}
