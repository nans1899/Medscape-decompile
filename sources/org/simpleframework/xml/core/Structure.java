package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;

class Structure {
    private final Instantiator factory;
    private final Model model;
    private final boolean primitive;
    private final Label text;
    private final Label version;

    public Structure(Instantiator instantiator, Model model2, Label label, Label label2, boolean z) {
        this.primitive = z;
        this.factory = instantiator;
        this.version = label;
        this.model = model2;
        this.text = label2;
    }

    public Instantiator getInstantiator() {
        return this.factory;
    }

    public Section getSection() {
        return new ModelSection(this.model);
    }

    public boolean isPrimitive() {
        return this.primitive;
    }

    public Version getRevision() {
        Label label = this.version;
        if (label != null) {
            return (Version) label.getContact().getAnnotation(Version.class);
        }
        return null;
    }

    public Label getVersion() {
        return this.version;
    }

    public Label getText() {
        return this.text;
    }
}
