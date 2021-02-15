package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;

class ModelList extends ArrayList<Model> {
    public ModelList build() {
        ModelList modelList = new ModelList();
        Iterator it = iterator();
        while (it.hasNext()) {
            modelList.register((Model) it.next());
        }
        return modelList;
    }

    public boolean isEmpty() {
        Iterator it = iterator();
        while (it.hasNext()) {
            Model model = (Model) it.next();
            if (model != null && !model.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Model lookup(int i) {
        if (i <= size()) {
            return (Model) get(i - 1);
        }
        return null;
    }

    public void register(Model model) {
        int index = model.getIndex();
        int size = size();
        for (int i = 0; i < index; i++) {
            if (i >= size) {
                add((Object) null);
            }
            int i2 = index - 1;
            if (i == i2) {
                set(i2, model);
            }
        }
    }

    public Model take() {
        while (!isEmpty()) {
            Model model = (Model) remove(0);
            if (!model.isEmpty()) {
                return model;
            }
        }
        return null;
    }
}
