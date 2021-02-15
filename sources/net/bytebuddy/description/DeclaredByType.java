package net.bytebuddy.description;

import net.bytebuddy.description.type.TypeDefinition;

public interface DeclaredByType {
    TypeDefinition getDeclaringType();
}
