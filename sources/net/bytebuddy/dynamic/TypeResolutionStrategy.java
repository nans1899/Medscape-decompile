package net.bytebuddy.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.NexusAccessor;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.implementation.LoadedTypeInitializer;

public interface TypeResolutionStrategy {

    public interface Resolved {
        <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy);

        TypeInitializer injectedInto(TypeInitializer typeInitializer);
    }

    Resolved resolve();

    public enum Passive implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        public Resolved resolve() {
            return this;
        }

        public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            Map<TypeDescription, Class<?>> load = classLoadingStrategy.load(s, dynamicType.getAllTypes());
            for (Map.Entry next : dynamicType.getLoadedTypeInitializers().entrySet()) {
                ((LoadedTypeInitializer) next.getValue()).onLoad(load.get(next.getKey()));
            }
            return new HashMap(load);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Active implements TypeResolutionStrategy {
        private final NexusAccessor nexusAccessor;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.nexusAccessor.equals(((Active) obj).nexusAccessor);
        }

        public int hashCode() {
            return 527 + this.nexusAccessor.hashCode();
        }

        public Active() {
            this(new NexusAccessor());
        }

        public Active(NexusAccessor nexusAccessor2) {
            this.nexusAccessor = nexusAccessor2;
        }

        public Resolved resolve() {
            return new Resolved(this.nexusAccessor, new Random().nextInt());
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class Resolved implements Resolved {
            private final int identification;
            private final NexusAccessor nexusAccessor;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Resolved resolved = (Resolved) obj;
                return this.identification == resolved.identification && this.nexusAccessor.equals(resolved.nexusAccessor);
            }

            public int hashCode() {
                return ((527 + this.nexusAccessor.hashCode()) * 31) + this.identification;
            }

            protected Resolved(NexusAccessor nexusAccessor2, int i) {
                this.nexusAccessor = nexusAccessor2;
                this.identification = i;
            }

            public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
                return typeInitializer.expandWith(new NexusAccessor.InitializationAppender(this.identification));
            }

            public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
                HashMap hashMap = new HashMap(dynamicType.getLoadedTypeInitializers());
                TypeDescription typeDescription = dynamicType.getTypeDescription();
                Map<TypeDescription, Class<?>> load = classLoadingStrategy.load(s, dynamicType.getAllTypes());
                this.nexusAccessor.register(typeDescription.getName(), load.get(typeDescription).getClassLoader(), this.identification, (LoadedTypeInitializer) hashMap.remove(typeDescription));
                for (Map.Entry entry : hashMap.entrySet()) {
                    ((LoadedTypeInitializer) entry.getValue()).onLoad(load.get(entry.getKey()));
                }
                return load;
            }
        }
    }

    public enum Lazy implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        public Resolved resolve() {
            return this;
        }

        public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            return classLoadingStrategy.load(s, dynamicType.getAllTypes());
        }
    }

    public enum Disabled implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        public Resolved resolve() {
            return this;
        }

        public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            throw new IllegalStateException("Cannot initialize a dynamic type for a disabled type resolution strategy");
        }
    }
}
