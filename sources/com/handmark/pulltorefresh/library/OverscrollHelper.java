package com.handmark.pulltorefresh.library;

import android.util.Log;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

public final class OverscrollHelper {
    static final float DEFAULT_OVERSCROLL_SCALE = 1.0f;
    static final String LOG_TAG = "OverscrollHelper";

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, boolean z) {
        overScrollBy(pullToRefreshBase, i, i2, i3, i4, 0, z);
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, boolean z) {
        overScrollBy(pullToRefreshBase, i, i2, i3, i4, i5, 0, 1.0f, z);
    }

    /* renamed from: com.handmark.pulltorefresh.library.OverscrollHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.handmark.pulltorefresh.library.PullToRefreshBase$Orientation[] r0 = com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation = r0
                com.handmark.pulltorefresh.library.PullToRefreshBase$Orientation r1 = com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation     // Catch:{ NoSuchFieldError -> 0x001d }
                com.handmark.pulltorefresh.library.PullToRefreshBase$Orientation r1 = com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.handmark.pulltorefresh.library.OverscrollHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, int i6, float f, boolean z) {
        int i7;
        int i8;
        int i9;
        if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[pullToRefreshBase.getPullToRefreshScrollDirection().ordinal()] != 1) {
            i9 = pullToRefreshBase.getScrollY();
            i8 = i3;
            i7 = i4;
        } else {
            i9 = pullToRefreshBase.getScrollX();
            i8 = i;
            i7 = i2;
        }
        if (pullToRefreshBase.isPullToRefreshOverScrollEnabled() && !pullToRefreshBase.isRefreshing()) {
            PullToRefreshBase.Mode mode = pullToRefreshBase.getMode();
            if (mode.permitsPullToRefresh() && !z && i8 != 0) {
                int i10 = i8 + i7;
                Log.d(LOG_TAG, "OverScroll. DeltaX: " + i + ", ScrollX: " + i2 + ", DeltaY: " + i3 + ", ScrollY: " + i4 + ", NewY: " + i10 + ", ScrollRange: " + i5 + ", CurrentScroll: " + i9);
                if (i10 < 0 - i6) {
                    if (mode.showHeaderLoadingLayout()) {
                        if (i9 == 0) {
                            pullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.setHeaderScroll((int) (f * ((float) (i9 + i10))));
                    }
                } else if (i10 > i5 + i6) {
                    if (mode.showFooterLoadingLayout()) {
                        if (i9 == 0) {
                            pullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.setHeaderScroll((int) (f * ((float) ((i9 + i10) - i5))));
                    }
                } else if (Math.abs(i10) <= i6 || Math.abs(i10 - i5) <= i6) {
                    pullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
                }
            } else if (z && PullToRefreshBase.State.OVERSCROLLING == pullToRefreshBase.getState()) {
                pullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
            }
        }
    }

    static boolean isAndroidOverScrollEnabled(View view) {
        return view.getOverScrollMode() != 2;
    }
}
