package com.wbmd.decisionpoint.extensions;

import android.content.SharedPreferences;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a.\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0001H\b¢\u0006\u0002\u0010\u0006\u001a*\u0010\u0007\u001a\u00020\b\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u0002H\u0001H\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"get", "T", "Landroid/content/SharedPreferences;", "key", "", "defaultValue", "(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "put", "", "value", "(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/Object;)V", "wbmd.decisionpoint_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: ContextExtensions.kt */
public final class ContextExtensionsKt {
    public static final /* synthetic */ <T> T get(SharedPreferences sharedPreferences, String str, T t) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "$this$get");
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.reifiedOperationMarker(4, "T");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            if (t != null) {
                T valueOf = Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) t).booleanValue()));
                Intrinsics.reifiedOperationMarker(1, "T");
                return (Object) valueOf;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            if (t != null) {
                T valueOf2 = Float.valueOf(sharedPreferences.getFloat(str, ((Float) t).floatValue()));
                Intrinsics.reifiedOperationMarker(1, "T");
                return (Object) valueOf2;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            if (t != null) {
                T valueOf3 = Integer.valueOf(sharedPreferences.getInt(str, ((Integer) t).intValue()));
                Intrinsics.reifiedOperationMarker(1, "T");
                return (Object) valueOf3;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            if (t != null) {
                T valueOf4 = Long.valueOf(sharedPreferences.getLong(str, ((Long) t).longValue()));
                Intrinsics.reifiedOperationMarker(1, "T");
                return (Object) valueOf4;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            if (t != null) {
                T string = sharedPreferences.getString(str, (String) t);
                Intrinsics.reifiedOperationMarker(1, "T");
                return (Object) string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (!(t instanceof Set)) {
            return t;
        } else {
            T stringSet = sharedPreferences.getStringSet(str, (Set) t);
            Intrinsics.reifiedOperationMarker(1, "T");
            return (Object) stringSet;
        }
    }

    public static final /* synthetic */ <T> void put(SharedPreferences sharedPreferences, String str, T t) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "$this$put");
        Intrinsics.checkNotNullParameter(str, "key");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Intrinsics.reifiedOperationMarker(4, "T");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            if (t != null) {
                edit.putBoolean(str, ((Boolean) t).booleanValue());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            if (t != null) {
                edit.putFloat(str, ((Float) t).floatValue());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            if (t != null) {
                edit.putInt(str, ((Integer) t).intValue());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            if (t != null) {
                edit.putLong(str, ((Long) t).longValue());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            if (t != null) {
                edit.putString(str, (String) t);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (t instanceof Set) {
            edit.putStringSet(str, (Set) t);
        }
        edit.apply();
    }
}
