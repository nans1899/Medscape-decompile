package net.bytebuddy.implementation;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.privilege.SetAccessibleAction;

public interface LoadedTypeInitializer {

    public enum NoOp implements LoadedTypeInitializer {
        INSTANCE;

        public boolean isAlive() {
            return false;
        }

        public void onLoad(Class<?> cls) {
        }
    }

    boolean isAlive();

    void onLoad(Class<?> cls);

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForStaticField implements LoadedTypeInitializer, Serializable {
        private static final Object STATIC_FIELD = null;
        private static final long serialVersionUID = 1;
        private final String fieldName;
        private final Object value;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForStaticField forStaticField = (ForStaticField) obj;
            return this.fieldName.equals(forStaticField.fieldName) && this.value.equals(forStaticField.value);
        }

        public int hashCode() {
            return ((527 + this.fieldName.hashCode()) * 31) + this.value.hashCode();
        }

        public boolean isAlive() {
            return true;
        }

        protected ForStaticField(String str, Object obj) {
            this.fieldName = str;
            this.value = obj;
        }

        public void onLoad(Class<?> cls) {
            try {
                Field declaredField = cls.getDeclaredField(this.fieldName);
                if (!Modifier.isPublic(declaredField.getModifiers()) || !Modifier.isPublic(declaredField.getDeclaringClass().getModifiers())) {
                    AccessController.doPrivileged(new SetAccessibleAction(declaredField));
                }
                declaredField.set(STATIC_FIELD, this.value);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Cannot access " + this.fieldName + " from " + cls, e);
            } catch (NoSuchFieldException e2) {
                throw new IllegalStateException("There is no field " + this.fieldName + " defined on " + cls, e2);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Compound implements LoadedTypeInitializer, Serializable {
        private static final long serialVersionUID = 1;
        private final List<LoadedTypeInitializer> loadedTypeInitializers;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.loadedTypeInitializers.equals(((Compound) obj).loadedTypeInitializers);
        }

        public int hashCode() {
            return 527 + this.loadedTypeInitializers.hashCode();
        }

        public Compound(LoadedTypeInitializer... loadedTypeInitializerArr) {
            this((List<? extends LoadedTypeInitializer>) Arrays.asList(loadedTypeInitializerArr));
        }

        public Compound(List<? extends LoadedTypeInitializer> list) {
            this.loadedTypeInitializers = new ArrayList();
            for (LoadedTypeInitializer loadedTypeInitializer : list) {
                if (loadedTypeInitializer instanceof Compound) {
                    this.loadedTypeInitializers.addAll(((Compound) loadedTypeInitializer).loadedTypeInitializers);
                } else if (!(loadedTypeInitializer instanceof NoOp)) {
                    this.loadedTypeInitializers.add(loadedTypeInitializer);
                }
            }
        }

        public void onLoad(Class<?> cls) {
            for (LoadedTypeInitializer onLoad : this.loadedTypeInitializers) {
                onLoad.onLoad(cls);
            }
        }

        public boolean isAlive() {
            for (LoadedTypeInitializer isAlive : this.loadedTypeInitializers) {
                if (isAlive.isAlive()) {
                    return true;
                }
            }
            return false;
        }
    }
}
