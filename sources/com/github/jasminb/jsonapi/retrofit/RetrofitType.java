package com.github.jasminb.jsonapi.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class RetrofitType {
    private boolean collection;
    private boolean isParentType = false;
    private Class<?> type;
    private boolean valid = true;

    public RetrofitType(Type type2) {
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type2;
            if (parameterizedType.getRawType().equals(JSONAPIDocument.class)) {
                initialize(parameterizedType.getActualTypeArguments()[0]);
                this.isParentType = true;
                return;
            }
        }
        initialize(type2);
    }

    private void initialize(Type type2) {
        if (type2 instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type2).getActualTypeArguments();
            if (actualTypeArguments == null || actualTypeArguments.length <= 0) {
                this.valid = false;
            } else if (actualTypeArguments[0] instanceof Class) {
                this.type = (Class) actualTypeArguments[0];
                this.collection = true;
            } else {
                this.valid = false;
            }
        } else if (type2 instanceof Class) {
            this.type = (Class) type2;
        } else {
            this.valid = false;
        }
    }

    public boolean isJSONAPIDocumentType() {
        return this.isParentType;
    }

    public Class<?> getType() {
        return this.type;
    }

    public boolean isCollection() {
        return this.collection;
    }

    public boolean isValid() {
        return this.valid;
    }
}
