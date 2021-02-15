package net.bytebuddy.description;

import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatchers;

public interface TypeVariableSource extends ModifierReviewable.OfAbstraction {
    public static final TypeVariableSource UNDEFINED = null;

    public interface Visitor<T> {

        public enum NoOp implements Visitor<TypeVariableSource> {
            INSTANCE;

            public TypeVariableSource onMethod(MethodDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape;
            }

            public TypeVariableSource onType(TypeDescription typeDescription) {
                return typeDescription;
            }
        }

        T onMethod(MethodDescription.InDefinedShape inDefinedShape);

        T onType(TypeDescription typeDescription);
    }

    <T> T accept(Visitor<T> visitor);

    TypeDescription.Generic findVariable(String str);

    TypeVariableSource getEnclosingSource();

    TypeList.Generic getTypeVariables();

    boolean isGenerified();

    public static abstract class AbstractBase extends ModifierReviewable.AbstractBase implements TypeVariableSource {
        public TypeDescription.Generic findVariable(String str) {
            TypeList.Generic generic = (TypeList.Generic) getTypeVariables().filter(ElementMatchers.named(str));
            if (!generic.isEmpty()) {
                return (TypeDescription.Generic) generic.getOnly();
            }
            TypeVariableSource enclosingSource = getEnclosingSource();
            if (enclosingSource == null) {
                return TypeDescription.Generic.UNDEFINED;
            }
            return enclosingSource.findVariable(str);
        }
    }
}
