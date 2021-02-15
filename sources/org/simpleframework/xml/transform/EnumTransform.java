package org.simpleframework.xml.transform;

class EnumTransform implements Transform<Enum> {
    private final Class type;

    public EnumTransform(Class cls) {
        this.type = cls;
    }

    public Enum read(String str) throws Exception {
        return Enum.valueOf(this.type, str);
    }

    public String write(Enum enumR) throws Exception {
        return enumR.name();
    }
}
