package com.afollestad.date.controllers;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Vibrator;
import androidx.core.content.ContextCompat;
import com.afollestad.date.R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\bH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/afollestad/date/controllers/VibratorController;", "", "context", "Landroid/content/Context;", "typedArray", "Landroid/content/res/TypedArray;", "(Landroid/content/Context;Landroid/content/res/TypedArray;)V", "selectionVibrates", "", "selectionVibrates$annotations", "()V", "getSelectionVibrates", "()Z", "vibrator", "Landroid/os/Vibrator;", "hasPermission", "vibrateForSelection", "", "Companion", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: VibratorController.kt */
public final class VibratorController {
    @Deprecated
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long VIBRATION_DURATION = 15;
    private final Context context;
    private final boolean selectionVibrates;
    private final Vibrator vibrator;

    public static /* synthetic */ void selectionVibrates$annotations() {
    }

    public VibratorController(Context context2, TypedArray typedArray) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(typedArray, "typedArray");
        this.context = context2;
        this.selectionVibrates = typedArray.getBoolean(R.styleable.DatePicker_date_picker_selection_vibrates, true);
        Object systemService = this.context.getSystemService("vibrator");
        if (systemService != null) {
            this.vibrator = (Vibrator) systemService;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.Vibrator");
    }

    public final boolean getSelectionVibrates() {
        return this.selectionVibrates;
    }

    public final void vibrateForSelection() {
        if (this.selectionVibrates && hasPermission()) {
            this.vibrator.vibrate(15);
        }
    }

    private final boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.VIBRATE") == 0;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/afollestad/date/controllers/VibratorController$Companion;", "", "()V", "VIBRATION_DURATION", "", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: VibratorController.kt */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
