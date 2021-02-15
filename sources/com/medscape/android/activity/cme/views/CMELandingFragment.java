package com.medscape.android.activity.cme.views;

import android.view.View;
import com.medscape.android.landingfeed.view.BaseLandingFragment;
import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/activity/cme/views/CMELandingFragment;", "Lcom/medscape/android/landingfeed/view/BaseLandingFragment;", "()V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CMELandingFragment.kt */
public final class CMELandingFragment extends BaseLandingFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/activity/cme/views/CMELandingFragment$Companion;", "", "()V", "newInstance", "Lcom/medscape/android/activity/cme/views/CMELandingFragment;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: CMELandingFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CMELandingFragment newInstance() {
            CMELandingFragment cMELandingFragment = new CMELandingFragment();
            cMELandingFragment.setFeedType(3);
            return cMELandingFragment;
        }
    }
}
