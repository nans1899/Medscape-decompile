package com.wbmd.wbmdcommons.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import androidx.lifecycle.CoroutineLiveDataKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0007"}, d2 = {"hideRxSearchOverlay", "", "view", "Landroid/view/View;", "hideSaveOverlay", "showRxSearchOverLay", "showSaveOverLay", "wbmdcommons_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: ViewUtil.kt */
public final class ViewUtil {
    public static final void showSaveOverLay(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getContext() != null) {
            SharedPreferenceManager sharedPreferenceManager = SharedPreferenceManager.INSTANCE;
            Context context = view.getContext();
            Intrinsics.checkNotNull(context);
            if (!sharedPreferenceManager.isSavedInstructionOverlayShown(context)) {
                AnimUtil.INSTANCE.fadeIn(view, 800);
                new Handler().postDelayed(new ViewUtil$showSaveOverLay$1(view), CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                SharedPreferenceManager sharedPreferenceManager2 = SharedPreferenceManager.INSTANCE;
                Context context2 = view.getContext();
                Intrinsics.checkNotNull(context2);
                sharedPreferenceManager2.setIsSavedInstructionOverlayShown(context2, true);
            }
        }
    }

    public static final void hideSaveOverlay(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        AnimUtil.INSTANCE.fadeOut(view, 800);
    }

    public static final void showRxSearchOverLay(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getContext() != null) {
            SharedPreferenceManager sharedPreferenceManager = SharedPreferenceManager.INSTANCE;
            Context context = view.getContext();
            Intrinsics.checkNotNull(context);
            if (!sharedPreferenceManager.isRxSearchInstructionOverlayShown(context)) {
                AnimUtil.INSTANCE.fadeIn(view, 800);
                new Handler().postDelayed(new ViewUtil$showRxSearchOverLay$1(view), CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                SharedPreferenceManager sharedPreferenceManager2 = SharedPreferenceManager.INSTANCE;
                Context context2 = view.getContext();
                Intrinsics.checkNotNull(context2);
                sharedPreferenceManager2.setIsRxSearchInstructionOverlayShown(context2, true);
            }
        }
    }

    public static final void hideRxSearchOverlay(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        AnimUtil.INSTANCE.fadeOut(view, 800);
    }
}
