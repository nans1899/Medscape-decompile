package net.bytebuddy.description;

public interface NamedElement {
    public static final String EMPTY_NAME = "";
    public static final String NO_NAME = null;

    public interface WithGenericName extends WithRuntimeName {
        String toGenericString();
    }

    public interface WithOptionalName extends NamedElement {
        boolean isNamed();
    }

    public interface WithRuntimeName extends NamedElement {
        String getInternalName();

        String getName();
    }

    String getActualName();
}
