package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

interface Detail {
    DefaultType getAccess();

    Annotation[] getAnnotations();

    Constructor[] getConstructors();

    List<FieldDetail> getFields();

    List<MethodDetail> getMethods();

    String getName();

    Namespace getNamespace();

    NamespaceList getNamespaceList();

    Order getOrder();

    DefaultType getOverride();

    Root getRoot();

    Class getSuper();

    Class getType();

    boolean isInstantiable();

    boolean isPrimitive();

    boolean isRequired();

    boolean isStrict();
}
