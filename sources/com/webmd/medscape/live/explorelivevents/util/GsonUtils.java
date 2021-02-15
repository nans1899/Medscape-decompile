package com.webmd.medscape.live.explorelivevents.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\u0004\u0018\u0001H\u0004\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bJ'\u0010\f\u001a\u00020\b\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\r\u001a\u0002H\u0004¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/util/GsonUtils;", "", "()V", "deserialize", "T", "clazz", "Ljava/lang/Class;", "json", "", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "getGson", "Lcom/google/gson/Gson;", "serialize", "model", "(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/String;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: GsonUtils.kt */
public final class GsonUtils {
    public static final GsonUtils INSTANCE = new GsonUtils();

    private GsonUtils() {
    }

    public final Gson getGson() {
        Gson create = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Intrinsics.checkNotNullExpressionValue(create, "GsonBuilder()\n          …n()\n            .create()");
        return create;
    }

    public final <T> T deserialize(Class<T> cls, String str) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        return getGson().fromJson(str, cls);
    }

    public final <T> String serialize(Class<T> cls, T t) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        String json = getGson().toJson((Object) t, (Type) cls);
        Intrinsics.checkNotNullExpressionValue(json, "getGson().toJson(model, clazz)");
        return json;
    }
}
