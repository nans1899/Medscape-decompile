package com.afollestad.viewpagerdots;

import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"com/afollestad/viewpagerdots/DotsIndicator$internalPageChangeListener$1", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "com.afollestad.viewpagerdots"}, k = 1, mv = {1, 1, 11})
/* compiled from: DotsIndicator.kt */
public final class DotsIndicator$internalPageChangeListener$1 implements ViewPager.OnPageChangeListener {
    final /* synthetic */ DotsIndicator this$0;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    DotsIndicator$internalPageChangeListener$1(DotsIndicator dotsIndicator) {
        this.this$0 = dotsIndicator;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.getAdapter();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageSelected(int r2) {
        /*
            r1 = this;
            com.afollestad.viewpagerdots.DotsIndicator r0 = r1.this$0
            androidx.viewpager.widget.ViewPager r0 = r0.viewPager
            if (r0 == 0) goto L_0x0013
            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
            if (r0 == 0) goto L_0x0013
            int r0 = r0.getCount()
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            if (r0 > 0) goto L_0x0017
            return
        L_0x0017:
            com.afollestad.viewpagerdots.DotsIndicator r0 = r1.this$0
            r0.internalPageSelected(r2)
            com.afollestad.viewpagerdots.DotsIndicator r0 = r1.this$0
            r0.lastPosition = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.viewpagerdots.DotsIndicator$internalPageChangeListener$1.onPageSelected(int):void");
    }
}
