package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.function.IOConsumer;

@FunctionalInterface
public interface IOConsumer<T> {
    void accept(T t) throws IOException;

    IOConsumer<T> andThen(IOConsumer<? super T> iOConsumer);

    /* renamed from: org.apache.commons.io.function.IOConsumer$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static IOConsumer $default$andThen(IOConsumer _this, IOConsumer iOConsumer) {
            Objects.requireNonNull(iOConsumer);
            return new IOConsumer(iOConsumer) {
                public final /* synthetic */ IOConsumer f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    IOConsumer.CC.lambda$andThen$0(IOConsumer.this, this.f$1, obj);
                }

                public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super T> iOConsumer) {
                    return IOConsumer.CC.$default$andThen(this, iOConsumer);
                }
            };
        }

        public static /* synthetic */ void lambda$andThen$0(IOConsumer _this, IOConsumer iOConsumer, Object obj) throws IOException {
            _this.accept(obj);
            iOConsumer.accept(obj);
        }
    }
}
