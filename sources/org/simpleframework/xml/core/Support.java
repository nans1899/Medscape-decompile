package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.filter.PlatformFilter;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;
import org.simpleframework.xml.transform.Transformer;

class Support implements Filter {
    private final DetailExtractor defaults;
    private final DetailExtractor details;
    private final Filter filter;
    private final Format format;
    private final InstanceFactory instances;
    private final LabelExtractor labels;
    private final Matcher matcher;
    private final ScannerFactory scanners;
    private final Transformer transform;

    public Support() {
        this(new PlatformFilter());
    }

    public Support(Filter filter2) {
        this(filter2, new EmptyMatcher());
    }

    public Support(Filter filter2, Matcher matcher2) {
        this(filter2, matcher2, new Format());
    }

    public Support(Filter filter2, Matcher matcher2, Format format2) {
        this.defaults = new DetailExtractor(this, DefaultType.FIELD);
        this.transform = new Transformer(matcher2);
        this.scanners = new ScannerFactory(this);
        this.details = new DetailExtractor(this);
        this.labels = new LabelExtractor(format2);
        this.instances = new InstanceFactory();
        this.matcher = matcher2;
        this.filter = filter2;
        this.format = format2;
    }

    public String replace(String str) {
        return this.filter.replace(str);
    }

    public Style getStyle() {
        return this.format.getStyle();
    }

    public Format getFormat() {
        return this.format;
    }

    public Instance getInstance(Value value) {
        return this.instances.getInstance(value);
    }

    public Instance getInstance(Class cls) {
        return this.instances.getInstance(cls);
    }

    public Transform getTransform(Class cls) throws Exception {
        return this.matcher.match(cls);
    }

    public Label getLabel(Contact contact, Annotation annotation) throws Exception {
        return this.labels.getLabel(contact, annotation);
    }

    public List<Label> getLabels(Contact contact, Annotation annotation) throws Exception {
        return this.labels.getList(contact, annotation);
    }

    public Detail getDetail(Class cls) {
        return getDetail(cls, (DefaultType) null);
    }

    public Detail getDetail(Class cls, DefaultType defaultType) {
        if (defaultType != null) {
            return this.defaults.getDetail(cls);
        }
        return this.details.getDetail(cls);
    }

    public ContactList getFields(Class cls) throws Exception {
        return getFields(cls, (DefaultType) null);
    }

    public ContactList getFields(Class cls, DefaultType defaultType) throws Exception {
        if (defaultType != null) {
            return this.defaults.getFields(cls);
        }
        return this.details.getFields(cls);
    }

    public ContactList getMethods(Class cls) throws Exception {
        return getMethods(cls, (DefaultType) null);
    }

    public ContactList getMethods(Class cls, DefaultType defaultType) throws Exception {
        if (defaultType != null) {
            return this.defaults.getMethods(cls);
        }
        return this.details.getMethods(cls);
    }

    public Scanner getScanner(Class cls) throws Exception {
        return this.scanners.getInstance(cls);
    }

    public Object read(String str, Class cls) throws Exception {
        return this.transform.read(str, cls);
    }

    public String write(Object obj, Class cls) throws Exception {
        return this.transform.write(obj, cls);
    }

    public boolean valid(Class cls) throws Exception {
        return this.transform.valid(cls);
    }

    public String getName(Class cls) throws Exception {
        String name = getScanner(cls).getName();
        if (name != null) {
            return name;
        }
        return getClassName(cls);
    }

    private String getClassName(Class<?> cls) throws Exception {
        if (cls.isArray()) {
            cls = cls.getComponentType();
        }
        String simpleName = cls.getSimpleName();
        if (cls.isPrimitive()) {
            return simpleName;
        }
        return Reflector.getName(simpleName);
    }

    public boolean isPrimitive(Class cls) throws Exception {
        if (cls == String.class || cls == Float.class || cls == Double.class || cls == Long.class || cls == Integer.class || cls == Boolean.class || cls.isEnum() || cls.isPrimitive()) {
            return true;
        }
        return this.transform.valid(cls);
    }

    public boolean isContainer(Class cls) {
        if (!Collection.class.isAssignableFrom(cls) && !Map.class.isAssignableFrom(cls)) {
            return cls.isArray();
        }
        return true;
    }

    public static boolean isFloat(Class cls) throws Exception {
        if (cls == Double.class || cls == Float.class || cls == Float.TYPE || cls == Double.TYPE) {
            return true;
        }
        return false;
    }

    public static boolean isAssignable(Class cls, Class cls2) {
        if (cls.isPrimitive()) {
            cls = getPrimitive(cls);
        }
        if (cls2.isPrimitive()) {
            cls2 = getPrimitive(cls2);
        }
        return cls2.isAssignableFrom(cls);
    }

    public static Class getPrimitive(Class cls) {
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        return cls == Byte.TYPE ? Byte.class : cls;
    }
}
