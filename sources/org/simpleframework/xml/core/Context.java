package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

interface Context {
    Object getAttribute(Object obj);

    Caller getCaller(Class cls) throws Exception;

    Decorator getDecorator(Class cls) throws Exception;

    Instance getInstance(Class cls);

    Instance getInstance(Value value);

    String getName(Class cls) throws Exception;

    Value getOverride(Type type, InputNode inputNode) throws Exception;

    String getProperty(String str);

    Schema getSchema(Class cls) throws Exception;

    Session getSession();

    Style getStyle();

    Support getSupport();

    Class getType(Type type, Object obj);

    Version getVersion(Class cls) throws Exception;

    boolean isFloat(Class cls) throws Exception;

    boolean isFloat(Type type) throws Exception;

    boolean isPrimitive(Class cls) throws Exception;

    boolean isPrimitive(Type type) throws Exception;

    boolean isStrict();

    boolean setOverride(Type type, Object obj, OutputNode outputNode) throws Exception;
}
