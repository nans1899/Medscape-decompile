package com.afollestad.materialdialogs.datetime.internal;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.afollestad.materialdialogs.datetime.R;
import com.medscape.android.slideshow.SlideshowPageFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u0011"}, d2 = {"Lcom/afollestad/materialdialogs/datetime/internal/DateTimePickerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "()V", "destroyItem", "", "container", "Landroid/view/ViewGroup;", "position", "", "object", "", "getCount", "instantiateItem", "isViewFromObject", "", "view", "Landroid/view/View;", "datetime"}, k = 1, mv = {1, 1, 16})
/* compiled from: DateTimePickerAdapter.kt */
public final class DateTimePickerAdapter extends PagerAdapter {
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "container");
        Intrinsics.checkParameterIsNotNull(obj, SlideshowPageFragment.ARG_OBJECT);
    }

    public int getCount() {
        return 2;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int i2;
        Intrinsics.checkParameterIsNotNull(viewGroup, "container");
        if (i == 0) {
            i2 = R.id.datetimeDatePicker;
        } else if (i == 1) {
            i2 = R.id.datetimeTimePicker;
        } else {
            throw new IllegalStateException("Unexpected position: " + i);
        }
        View findViewById = viewGroup.findViewById(i2);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "container.findViewById(resId)");
        return findViewById;
    }

    public boolean isViewFromObject(View view, Object obj) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(obj, SlideshowPageFragment.ARG_OBJECT);
        return view == ((View) obj);
    }
}
