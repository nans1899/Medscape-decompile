package com.medscape.android.activity.saved.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.medscape.android.activity.saved.model.TabLayoutElement;
import com.medscape.android.activity.saved.views.SavedArticlesByTypeFragment;
import com.medscape.android.slideshow.SlideshowPageFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/activity/saved/adapters/ViewPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "tabElements", "", "Lcom/medscape/android/activity/saved/model/TabLayoutElement;", "(Landroidx/fragment/app/FragmentManager;Ljava/util/List;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "getItemPosition", "object", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ViewPagerAdapter.kt */
public final class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<TabLayoutElement> tabElements;

    public int getItemPosition(Object obj) {
        Intrinsics.checkNotNullParameter(obj, SlideshowPageFragment.ARG_OBJECT);
        return -2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewPagerAdapter(FragmentManager fragmentManager, List<? extends TabLayoutElement> list) {
        super(fragmentManager);
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        Intrinsics.checkNotNullParameter(list, "tabElements");
        this.tabElements = list;
    }

    public Fragment getItem(int i) {
        return SavedArticlesByTypeFragment.Companion.newInstance(this.tabElements.get(i).getName());
    }

    public int getCount() {
        return this.tabElements.size();
    }
}
