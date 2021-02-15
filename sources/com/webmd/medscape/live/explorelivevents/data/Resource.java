package com.webmd.medscape.live.explorelivevents.data;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 \u0010*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0010B#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "T", "", "status", "Lcom/webmd/medscape/live/explorelivevents/data/Status;", "data", "message", "", "(Lcom/webmd/medscape/live/explorelivevents/data/Status;Ljava/lang/Object;Ljava/lang/String;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMessage", "()Ljava/lang/String;", "getStatus", "()Lcom/webmd/medscape/live/explorelivevents/data/Status;", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Resource.kt */
public final class Resource<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final T data;
    private final String message;
    private final Status status;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u0001H\u0005¢\u0006\u0002\u0010\tJ+\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u0001H\u0005¢\u0006\u0002\u0010\tJ)\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u0002H\u0005¢\u0006\u0002\u0010\t¨\u0006\f"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/Resource$Companion;", "", "()V", "error", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "T", "msg", "", "data", "(Ljava/lang/String;Ljava/lang/Object;)Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "loading", "success", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Resource.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T> Resource<T> success(String str, T t) {
            return new Resource<>(Status.SUCCESS, t, str, (DefaultConstructorMarker) null);
        }

        public final <T> Resource<T> error(String str, T t) {
            return new Resource<>(Status.ERROR, t, str, (DefaultConstructorMarker) null);
        }

        public final <T> Resource<T> loading(String str, T t) {
            return new Resource<>(Status.LOADING, t, str, (DefaultConstructorMarker) null);
        }
    }

    private Resource(Status status2, T t, String str) {
        this.status = status2;
        this.data = t;
        this.message = str;
    }

    public /* synthetic */ Resource(Status status2, Object obj, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(status2, obj, str);
    }

    public final T getData() {
        return this.data;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Status getStatus() {
        return this.status;
    }
}
