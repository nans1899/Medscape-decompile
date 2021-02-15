package org.simpleframework.xml.core;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;

class SignatureCreator implements Creator {
    private final List<Parameter> list;
    private final Signature signature;
    private final Class type;

    public SignatureCreator(Signature signature2) {
        this.type = signature2.getType();
        this.list = signature2.getAll();
        this.signature = signature2;
    }

    public Class getType() {
        return this.type;
    }

    public Signature getSignature() {
        return this.signature;
    }

    public Object getInstance() throws Exception {
        return this.signature.create();
    }

    public Object getInstance(Criteria criteria) throws Exception {
        Object[] array = this.list.toArray();
        for (int i = 0; i < this.list.size(); i++) {
            array[i] = getVariable(criteria, i);
        }
        return this.signature.create(array);
    }

    private Object getVariable(Criteria criteria, int i) throws Exception {
        Variable remove = criteria.remove(this.list.get(i).getKey());
        if (remove != null) {
            return remove.getValue();
        }
        return null;
    }

    public double getScore(Criteria criteria) throws Exception {
        Signature copy = this.signature.copy();
        for (Object next : criteria) {
            Parameter parameter = copy.get(next);
            Variable variable = criteria.get(next);
            Contact contact = variable.getContact();
            if (parameter != null && !Support.isAssignable(variable.getValue().getClass(), parameter.getType())) {
                return -1.0d;
            }
            if (contact.isReadOnly() && parameter == null) {
                return -1.0d;
            }
        }
        return getPercentage(criteria);
    }

    private double getPercentage(Criteria criteria) throws Exception {
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        for (Parameter next : this.list) {
            if (criteria.get(next.getKey()) != null) {
                d += 1.0d;
            } else if (next.isRequired() || next.isPrimitive()) {
                return -1.0d;
            }
        }
        return getAdjustment(d);
    }

    private double getAdjustment(double d) {
        double size = ((double) this.list.size()) / 1000.0d;
        if (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return size + (d / ((double) this.list.size()));
        }
        return d / ((double) this.list.size());
    }

    public String toString() {
        return this.signature.toString();
    }
}
