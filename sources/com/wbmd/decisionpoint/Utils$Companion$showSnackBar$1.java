package com.wbmd.decisionpoint;

import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
/* compiled from: Utils.kt */
final class Utils$Companion$showSnackBar$1 implements Runnable {
    final /* synthetic */ String $action;
    final /* synthetic */ View.OnClickListener $actionListener;
    final /* synthetic */ View $layout;
    final /* synthetic */ int $length;
    final /* synthetic */ String $message;

    Utils$Companion$showSnackBar$1(View view, String str, int i, String str2, View.OnClickListener onClickListener) {
        this.$layout = view;
        this.$message = str;
        this.$length = i;
        this.$action = str2;
        this.$actionListener = onClickListener;
    }

    public final void run() {
        View.OnClickListener onClickListener;
        View view = this.$layout;
        String str = this.$message;
        if (str == null) {
            str = "Unknown Error";
        }
        Snackbar make = Snackbar.make(view, (CharSequence) str, this.$length);
        Intrinsics.checkNotNullExpressionValue(make, "Snackbar.make(layout, me…:\"Unknown Error\", length)");
        String str2 = this.$action;
        if (str2 != null) {
            CharSequence charSequence = str2;
            int length = charSequence.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) charSequence.charAt(!z ? i : length), 32) <= 0;
                if (!z) {
                    if (!z2) {
                        z = true;
                    } else {
                        i++;
                    }
                } else if (!z2) {
                    break;
                } else {
                    length--;
                }
            }
            if (!StringsKt.equals(charSequence.subSequence(i, length + 1).toString(), "", true) && (onClickListener = this.$actionListener) != null) {
                make.setAction((CharSequence) this.$action, onClickListener);
                make.setActionTextColor(ContextCompat.getColor(this.$layout.getContext(), R.color.snackbar_action));
            }
        }
        View view2 = make.getView();
        Intrinsics.checkNotNullExpressionValue(view2, "snackBar.view");
        View findViewById = view2.findViewById(R.id.snackbar_text);
        if (findViewById != null) {
            TextView textView = (TextView) findViewById;
            textView.setTextColor(-1);
            textView.setMaxLines(10);
            view2.setBackgroundColor(ContextCompat.getColor(this.$layout.getContext(), R.color.snackbar_bg));
            make.show();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
    }
}
