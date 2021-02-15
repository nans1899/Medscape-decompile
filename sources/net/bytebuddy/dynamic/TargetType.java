package net.bytebuddy.dynamic;

import net.bytebuddy.description.type.TypeDescription;

public final class TargetType {
    public static final TypeDescription DESCRIPTION = TypeDescription.ForLoadedType.of(TargetType.class);

    public static TypeDescription resolve(TypeDescription typeDescription, TypeDescription typeDescription2) {
        int i = 0;
        TypeDescription typeDescription3 = typeDescription;
        while (typeDescription3.isArray()) {
            typeDescription3 = typeDescription3.getComponentType();
            i++;
        }
        return typeDescription3.represents(TargetType.class) ? TypeDescription.ArrayProjection.of(typeDescription2, i) : typeDescription;
    }

    private TargetType() {
        throw new UnsupportedOperationException("This class only serves as a marker type and should not be instantiated");
    }
}
