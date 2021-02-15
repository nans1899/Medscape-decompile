package net.bytebuddy.implementation.bytecode.collection;

import java.util.List;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;

public interface CollectionFactory {
    TypeDescription.Generic getComponentType();

    StackManipulation withValues(List<? extends StackManipulation> list);
}
