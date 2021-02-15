package org.dom4j.bean;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.DocumentFactory;
import org.dom4j.QName;

public class BeanMetaData {
    private static final DocumentFactory DOCUMENT_FACTORY = BeanDocumentFactory.getInstance();
    protected static final Object[] NULL_ARGS = new Object[0];
    private static Map singletonCache = new HashMap();
    private Class beanClass;
    private Map nameMap = new HashMap();
    private PropertyDescriptor[] propertyDescriptors;
    private QName[] qNames;
    private Method[] readMethods;
    private Method[] writeMethods;

    /* access modifiers changed from: protected */
    public void handleException(Exception exc) {
    }

    public BeanMetaData(Class cls) {
        this.beanClass = cls;
        if (cls != null) {
            try {
                this.propertyDescriptors = Introspector.getBeanInfo(cls).getPropertyDescriptors();
            } catch (IntrospectionException e) {
                handleException(e);
            }
        }
        if (this.propertyDescriptors == null) {
            this.propertyDescriptors = new PropertyDescriptor[0];
        }
        int length = this.propertyDescriptors.length;
        this.qNames = new QName[length];
        this.readMethods = new Method[length];
        this.writeMethods = new Method[length];
        for (int i = 0; i < length; i++) {
            PropertyDescriptor propertyDescriptor = this.propertyDescriptors[i];
            String name = propertyDescriptor.getName();
            QName createQName = DOCUMENT_FACTORY.createQName(name);
            this.qNames[i] = createQName;
            this.readMethods[i] = propertyDescriptor.getReadMethod();
            this.writeMethods[i] = propertyDescriptor.getWriteMethod();
            Integer num = new Integer(i);
            this.nameMap.put(name, num);
            this.nameMap.put(createQName, num);
        }
    }

    public static BeanMetaData get(Class cls) {
        BeanMetaData beanMetaData = (BeanMetaData) singletonCache.get(cls);
        if (beanMetaData != null) {
            return beanMetaData;
        }
        BeanMetaData beanMetaData2 = new BeanMetaData(cls);
        singletonCache.put(cls, beanMetaData2);
        return beanMetaData2;
    }

    public int attributeCount() {
        return this.propertyDescriptors.length;
    }

    public BeanAttributeList createAttributeList(BeanElement beanElement) {
        return new BeanAttributeList(beanElement, this);
    }

    public QName getQName(int i) {
        return this.qNames[i];
    }

    public int getIndex(String str) {
        Integer num = (Integer) this.nameMap.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public int getIndex(QName qName) {
        Integer num = (Integer) this.nameMap.get(qName);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public Object getData(int i, Object obj) {
        try {
            return this.readMethods[i].invoke(obj, NULL_ARGS);
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public void setData(int i, Object obj, Object obj2) {
        try {
            this.writeMethods[i].invoke(obj, new Object[]{obj2});
        } catch (Exception e) {
            handleException(e);
        }
    }
}
