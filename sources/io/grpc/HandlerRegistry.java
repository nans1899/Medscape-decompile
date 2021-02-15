package io.grpc;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public abstract class HandlerRegistry {
    @Nullable
    public abstract ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2);

    public List<ServerServiceDefinition> getServices() {
        return Collections.emptyList();
    }

    @Nullable
    public final ServerMethodDefinition<?, ?> lookupMethod(String str) {
        return lookupMethod(str, (String) null);
    }
}
