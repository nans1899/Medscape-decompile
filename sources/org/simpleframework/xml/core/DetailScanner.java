package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

class DetailScanner implements Detail {
    private DefaultType access;
    private NamespaceList declaration;
    private List<FieldDetail> fields;
    private Annotation[] labels;
    private List<MethodDetail> methods;
    private String name;
    private Namespace namespace;
    private Order order;
    private DefaultType override;
    private boolean required;
    private Root root;
    private boolean strict;
    private Class type;

    public DetailScanner(Class cls) {
        this(cls, (DefaultType) null);
    }

    public DetailScanner(Class cls, DefaultType defaultType) {
        this.methods = new LinkedList();
        this.fields = new LinkedList();
        this.labels = cls.getDeclaredAnnotations();
        this.override = defaultType;
        this.strict = true;
        this.type = cls;
        scan(cls);
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean isStrict() {
        return this.strict;
    }

    public boolean isPrimitive() {
        return this.type.isPrimitive();
    }

    public boolean isInstantiable() {
        if (Modifier.isStatic(this.type.getModifiers())) {
            return true;
        }
        return !this.type.isMemberClass();
    }

    public Root getRoot() {
        return this.root;
    }

    public String getName() {
        return this.name;
    }

    public Class getType() {
        return this.type;
    }

    public Order getOrder() {
        return this.order;
    }

    public DefaultType getOverride() {
        return this.override;
    }

    public DefaultType getAccess() {
        DefaultType defaultType = this.override;
        if (defaultType != null) {
            return defaultType;
        }
        return this.access;
    }

    public Namespace getNamespace() {
        return this.namespace;
    }

    public NamespaceList getNamespaceList() {
        return this.declaration;
    }

    public List<MethodDetail> getMethods() {
        return this.methods;
    }

    public List<FieldDetail> getFields() {
        return this.fields;
    }

    public Annotation[] getAnnotations() {
        return this.labels;
    }

    public Constructor[] getConstructors() {
        return this.type.getDeclaredConstructors();
    }

    public Class getSuper() {
        Class<Object> superclass = this.type.getSuperclass();
        if (superclass == Object.class) {
            return null;
        }
        return superclass;
    }

    private void scan(Class cls) {
        methods(cls);
        fields(cls);
        extract(cls);
    }

    private void extract(Class cls) {
        for (Annotation annotation : this.labels) {
            if (annotation instanceof Namespace) {
                namespace(annotation);
            }
            if (annotation instanceof NamespaceList) {
                scope(annotation);
            }
            if (annotation instanceof Root) {
                root(annotation);
            }
            if (annotation instanceof Order) {
                order(annotation);
            }
            if (annotation instanceof Default) {
                access(annotation);
            }
        }
    }

    private void methods(Class cls) {
        for (Method methodDetail : cls.getDeclaredMethods()) {
            this.methods.add(new MethodDetail(methodDetail));
        }
    }

    private void fields(Class cls) {
        for (Field fieldDetail : cls.getDeclaredFields()) {
            this.fields.add(new FieldDetail(fieldDetail));
        }
    }

    private void root(Annotation annotation) {
        if (annotation != null) {
            Root root2 = (Root) annotation;
            String simpleName = this.type.getSimpleName();
            if (root2 != null) {
                String name2 = root2.name();
                if (isEmpty(name2)) {
                    name2 = Reflector.getName(simpleName);
                }
                this.strict = root2.strict();
                this.root = root2;
                this.name = name2;
            }
        }
    }

    private boolean isEmpty(String str) {
        return str.length() == 0;
    }

    private void order(Annotation annotation) {
        if (annotation != null) {
            this.order = (Order) annotation;
        }
    }

    private void access(Annotation annotation) {
        if (annotation != null) {
            Default defaultR = (Default) annotation;
            this.required = defaultR.required();
            this.access = defaultR.value();
        }
    }

    private void namespace(Annotation annotation) {
        if (annotation != null) {
            this.namespace = (Namespace) annotation;
        }
    }

    private void scope(Annotation annotation) {
        if (annotation != null) {
            this.declaration = (NamespaceList) annotation;
        }
    }

    public String toString() {
        return this.type.toString();
    }
}
