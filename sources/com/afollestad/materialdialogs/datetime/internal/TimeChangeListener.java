package com.afollestad.materialdialogs.datetime.internal;

import android.content.Context;
import android.content.IntentFilter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\b\u0004*\u0001\u0012\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B@\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012%\b\u0002\u0010\u0006\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0007¢\u0006\u0002\u0010\fJ\u0006\u0010\u0014\u001a\u00020\u000bR\u0012\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R+\u0010\u0006\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012X\u0004¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/afollestad/materialdialogs/datetime/internal/TimeChangeListener;", "T", "", "context", "Landroid/content/Context;", "argument", "onChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "arg", "", "(Landroid/content/Context;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "Ljava/lang/Object;", "lastHour", "", "lastMinute", "receiver", "com/afollestad/materialdialogs/datetime/internal/TimeChangeListener$receiver$1", "Lcom/afollestad/materialdialogs/datetime/internal/TimeChangeListener$receiver$1;", "dispose", "datetime"}, k = 1, mv = {1, 1, 16})
/* compiled from: TimeChangeListener.kt */
public final class TimeChangeListener<T> {
    /* access modifiers changed from: private */
    public final T argument;
    private Context context;
    /* access modifiers changed from: private */
    public int lastHour;
    /* access modifiers changed from: private */
    public int lastMinute;
    /* access modifiers changed from: private */
    public Function1<? super T, Unit> onChange;
    private final TimeChangeListener$receiver$1 receiver;

    public TimeChangeListener(Context context2, T t, Function1<? super T, Unit> function1) {
        this.context = context2;
        this.argument = t;
        this.onChange = function1;
        this.lastHour = -1;
        this.lastMinute = -1;
        this.receiver = new TimeChangeListener$receiver$1(this);
        if (this.context == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        } else if (this.argument == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        } else if (this.onChange != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_SET");
            Context context3 = this.context;
            if (context3 == null) {
                Intrinsics.throwNpe();
            }
            context3.registerReceiver(this.receiver, intentFilter);
        } else {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TimeChangeListener(Context context2, Object obj, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, obj, (i & 4) != 0 ? null : function1);
    }

    public final void dispose() {
        Context context2 = this.context;
        if (context2 != null) {
            context2.unregisterReceiver(this.receiver);
        }
        this.context = null;
        this.onChange = null;
    }
}
