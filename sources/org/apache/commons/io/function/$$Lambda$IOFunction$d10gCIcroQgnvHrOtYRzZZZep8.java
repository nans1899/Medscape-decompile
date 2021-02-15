package org.apache.commons.io.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.io.function.IOFunction;

/* renamed from: org.apache.commons.io.function.-$$Lambda$IOFunction$d10gCIcr-oQgnvHrOtYRzZZZep8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$IOFunction$d10gCIcroQgnvHrOtYRzZZZep8 implements IOFunction {
    public static final /* synthetic */ $$Lambda$IOFunction$d10gCIcroQgnvHrOtYRzZZZep8 INSTANCE = new $$Lambda$IOFunction$d10gCIcroQgnvHrOtYRzZZZep8();

    private /* synthetic */ $$Lambda$IOFunction$d10gCIcroQgnvHrOtYRzZZZep8() {
    }

    public /* synthetic */ IOConsumer<T> andThen(Consumer<? super R> consumer) {
        return IOFunction.CC.$default$andThen((IOFunction) this, (Consumer) consumer);
    }

    public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super R> iOConsumer) {
        return IOFunction.CC.$default$andThen((IOFunction) this, (IOConsumer) iOConsumer);
    }

    public /* synthetic */ <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> function) {
        return IOFunction.CC.$default$andThen((IOFunction) this, (Function) function);
    }

    public /* synthetic */ <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> iOFunction) {
        return IOFunction.CC.$default$andThen((IOFunction) this, (IOFunction) iOFunction);
    }

    public final Object apply(Object obj) {
        return IOFunction.CC.lambda$identity$8(obj);
    }

    public /* synthetic */ <V> IOFunction<V, R> compose(Function<? super V, ? extends T> function) {
        return IOFunction.CC.$default$compose((IOFunction) this, (Function) function);
    }

    public /* synthetic */ <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> iOFunction) {
        return IOFunction.CC.$default$compose((IOFunction) this, (IOFunction) iOFunction);
    }

    public /* synthetic */ IOSupplier<R> compose(Supplier<? extends T> supplier) {
        return IOFunction.CC.$default$compose((IOFunction) this, (Supplier) supplier);
    }

    public /* synthetic */ IOSupplier<R> compose(IOSupplier<? extends T> iOSupplier) {
        return IOFunction.CC.$default$compose((IOFunction) this, (IOSupplier) iOSupplier);
    }
}
