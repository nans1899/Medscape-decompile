package com.afollestad.materialdialogs.datetime.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"com/afollestad/materialdialogs/datetime/internal/TimeChangeListener$receiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "datetime"}, k = 1, mv = {1, 1, 16})
/* compiled from: TimeChangeListener.kt */
public final class TimeChangeListener$receiver$1 extends BroadcastReceiver {
    final /* synthetic */ TimeChangeListener this$0;

    TimeChangeListener$receiver$1(TimeChangeListener timeChangeListener) {
        this.this$0 = timeChangeListener;
    }

    public void onReceive(Context context, Intent intent) {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        int i2 = instance.get(12);
        if (this.this$0.argument == null) {
            return;
        }
        if (this.this$0.lastHour != i || this.this$0.lastMinute != i2) {
            Function1 access$getOnChange$p = this.this$0.onChange;
            if (access$getOnChange$p != null) {
                Unit unit = (Unit) access$getOnChange$p.invoke(this.this$0.argument);
            }
            this.this$0.lastHour = i;
            this.this$0.lastMinute = i2;
        }
    }
}
