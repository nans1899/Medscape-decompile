package org.simpleframework.xml.core;

interface Model extends Iterable<String> {
    LabelMap getAttributes() throws Exception;

    LabelMap getElements() throws Exception;

    Expression getExpression();

    int getIndex();

    ModelMap getModels() throws Exception;

    String getName();

    String getPrefix();

    Label getText();

    boolean isAttribute(String str);

    boolean isComposite();

    boolean isElement(String str);

    boolean isEmpty();

    boolean isModel(String str);

    Model lookup(String str, int i);

    Model lookup(Expression expression);

    Model register(String str, String str2, int i) throws Exception;

    void register(Label label) throws Exception;

    void registerAttribute(String str) throws Exception;

    void registerAttribute(Label label) throws Exception;

    void registerElement(String str) throws Exception;

    void registerElement(Label label) throws Exception;

    void registerText(Label label) throws Exception;

    void validate(Class cls) throws Exception;
}
