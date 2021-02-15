package org.dom4j.bean;

import java.util.AbstractList;
import org.dom4j.Attribute;
import org.dom4j.QName;

public class BeanAttributeList extends AbstractList {
    private BeanAttribute[] attributes;
    private BeanMetaData beanMetaData;
    private BeanElement parent;

    public boolean remove(Object obj) {
        return false;
    }

    public BeanAttributeList(BeanElement beanElement, BeanMetaData beanMetaData2) {
        this.parent = beanElement;
        this.beanMetaData = beanMetaData2;
        this.attributes = new BeanAttribute[beanMetaData2.attributeCount()];
    }

    public BeanAttributeList(BeanElement beanElement) {
        this.parent = beanElement;
        Object data = beanElement.getData();
        BeanMetaData beanMetaData2 = BeanMetaData.get(data != null ? data.getClass() : null);
        this.beanMetaData = beanMetaData2;
        this.attributes = new BeanAttribute[beanMetaData2.attributeCount()];
    }

    public Attribute attribute(String str) {
        return attribute(this.beanMetaData.getIndex(str));
    }

    public Attribute attribute(QName qName) {
        return attribute(this.beanMetaData.getIndex(qName));
    }

    public BeanAttribute attribute(int i) {
        if (i < 0) {
            return null;
        }
        BeanAttribute[] beanAttributeArr = this.attributes;
        if (i > beanAttributeArr.length) {
            return null;
        }
        BeanAttribute beanAttribute = beanAttributeArr[i];
        if (beanAttribute != null) {
            return beanAttribute;
        }
        BeanAttribute createAttribute = createAttribute(this.parent, i);
        this.attributes[i] = createAttribute;
        return createAttribute;
    }

    public BeanElement getParent() {
        return this.parent;
    }

    public QName getQName(int i) {
        return this.beanMetaData.getQName(i);
    }

    public Object getData(int i) {
        return this.beanMetaData.getData(i, this.parent.getData());
    }

    public void setData(int i, Object obj) {
        this.beanMetaData.setData(i, this.parent.getData(), obj);
    }

    public int size() {
        return this.attributes.length;
    }

    public Object get(int i) {
        BeanAttribute beanAttribute = this.attributes[i];
        if (beanAttribute != null) {
            return beanAttribute;
        }
        BeanAttribute createAttribute = createAttribute(this.parent, i);
        this.attributes[i] = createAttribute;
        return createAttribute;
    }

    public boolean add(Object obj) {
        throw new UnsupportedOperationException("add(Object) unsupported");
    }

    public void add(int i, Object obj) {
        throw new UnsupportedOperationException("add(int,Object) unsupported");
    }

    public Object set(int i, Object obj) {
        throw new UnsupportedOperationException("set(int,Object) unsupported");
    }

    public Object remove(int i) {
        BeanAttribute beanAttribute = (BeanAttribute) get(i);
        String value = beanAttribute.getValue();
        beanAttribute.setValue((String) null);
        return value;
    }

    public void clear() {
        for (BeanAttribute beanAttribute : this.attributes) {
            if (beanAttribute != null) {
                beanAttribute.setValue((String) null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public BeanAttribute createAttribute(BeanElement beanElement, int i) {
        return new BeanAttribute(this, i);
    }
}
