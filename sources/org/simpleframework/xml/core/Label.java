package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;

interface Label {
    Annotation getAnnotation();

    Contact getContact();

    Converter getConverter(Context context) throws Exception;

    Decorator getDecorator() throws Exception;

    Type getDependent() throws Exception;

    Object getEmpty(Context context) throws Exception;

    String getEntry() throws Exception;

    Expression getExpression() throws Exception;

    Object getKey() throws Exception;

    Label getLabel(Class cls) throws Exception;

    String getName() throws Exception;

    String[] getNames() throws Exception;

    String getOverride();

    String getPath() throws Exception;

    String[] getPaths() throws Exception;

    Class getType();

    Type getType(Class cls) throws Exception;

    boolean isAttribute();

    boolean isCollection();

    boolean isData();

    boolean isInline();

    boolean isRequired();

    boolean isText();

    boolean isTextList();

    boolean isUnion();

    String toString();
}
