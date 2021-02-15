package org.simpleframework.xml.core;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.List;

class ClassInstantiator implements Instantiator {
    private final List<Creator> creators;
    private final Detail detail;
    private final Creator primary;
    private final ParameterMap registry;

    public ClassInstantiator(List<Creator> list, Creator creator, ParameterMap parameterMap, Detail detail2) {
        this.creators = list;
        this.registry = parameterMap;
        this.primary = creator;
        this.detail = detail2;
    }

    public boolean isDefault() {
        if (this.creators.size() > 1 || this.primary == null) {
            return false;
        }
        return true;
    }

    public Object getInstance() throws Exception {
        return this.primary.getInstance();
    }

    public Object getInstance(Criteria criteria) throws Exception {
        Creator creator = getCreator(criteria);
        if (creator != null) {
            return creator.getInstance(criteria);
        }
        throw new PersistenceException("Constructor not matched for %s", this.detail);
    }

    private Creator getCreator(Criteria criteria) throws Exception {
        Creator creator = this.primary;
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        for (Creator next : this.creators) {
            double score = next.getScore(criteria);
            if (score > d) {
                creator = next;
                d = score;
            }
        }
        return creator;
    }

    public Parameter getParameter(String str) {
        return (Parameter) this.registry.get(str);
    }

    public List<Parameter> getParameters() {
        return this.registry.getAll();
    }

    public List<Creator> getCreators() {
        return new ArrayList(this.creators);
    }

    public String toString() {
        return String.format("creator for %s", new Object[]{this.detail});
    }
}
