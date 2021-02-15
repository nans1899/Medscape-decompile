package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedHashMap;

class ModelMap extends LinkedHashMap<String, ModelList> implements Iterable<ModelList> {
    private final Detail detail;

    public ModelMap(Detail detail2) {
        this.detail = detail2;
    }

    public ModelMap getModels() throws Exception {
        ModelMap modelMap = new ModelMap(this.detail);
        for (String str : keySet()) {
            ModelList modelList = (ModelList) get(str);
            if (modelList != null) {
                modelList = modelList.build();
            }
            if (!modelMap.containsKey(str)) {
                modelMap.put(str, modelList);
            } else {
                throw new PathException("Path with name '%s' is a duplicate in %s ", str, this.detail);
            }
        }
        return modelMap;
    }

    public Model lookup(String str, int i) {
        ModelList modelList = (ModelList) get(str);
        if (modelList != null) {
            return modelList.lookup(i);
        }
        return null;
    }

    public void register(String str, Model model) {
        ModelList modelList = (ModelList) get(str);
        if (modelList == null) {
            modelList = new ModelList();
            put(str, modelList);
        }
        modelList.register(model);
    }

    public Iterator<ModelList> iterator() {
        return values().iterator();
    }
}
