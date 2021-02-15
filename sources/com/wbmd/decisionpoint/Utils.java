package com.wbmd.decisionpoint;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/wbmd/decisionpoint/Utils;", "", "()V", "Companion", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Utils.kt */
public final class Utils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\r¨\u0006\u000e"}, d2 = {"Lcom/wbmd/decisionpoint/Utils$Companion;", "", "()V", "showSnackBar", "", "layout", "Landroid/view/View;", "length", "", "message", "", "action", "actionListener", "Landroid/view/View$OnClickListener;", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Utils.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void showSnackBar(View view, int i, String str, String str2, View.OnClickListener onClickListener) {
            if (view != null && view.getContext() != null) {
                view.postDelayed(new Utils$Companion$showSnackBar$1(view, str, i, str2, onClickListener), 200);
            }
        }
    }
}
