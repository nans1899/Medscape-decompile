package com.afollestad.materialdialogs.checkbox;

import android.widget.CompoundButton;
import com.afollestad.materialdialogs.MaterialDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "checked", "", "onCheckedChanged", "com/afollestad/materialdialogs/checkbox/DialogCheckboxExtKt$checkBoxPrompt$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: DialogCheckboxExt.kt */
final class DialogCheckboxExtKt$checkBoxPrompt$$inlined$run$lambda$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ boolean $isCheckedDefault$inlined;
    final /* synthetic */ Function1 $onToggle$inlined;
    final /* synthetic */ int $res$inlined;
    final /* synthetic */ String $text$inlined;
    final /* synthetic */ MaterialDialog $this_checkBoxPrompt$inlined;

    DialogCheckboxExtKt$checkBoxPrompt$$inlined$run$lambda$1(MaterialDialog materialDialog, String str, int i, boolean z, Function1 function1) {
        this.$this_checkBoxPrompt$inlined = materialDialog;
        this.$text$inlined = str;
        this.$res$inlined = i;
        this.$isCheckedDefault$inlined = z;
        this.$onToggle$inlined = function1;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Function1 function1 = this.$onToggle$inlined;
        if (function1 != null) {
            Unit unit = (Unit) function1.invoke(Boolean.valueOf(z));
        }
    }
}
