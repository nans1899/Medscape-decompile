package com.webmd.wbmdcmepulse.live_events.views;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.wbmdcmepulse.live_events.adapters.LiveEventsAdapter;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J&\u0010\u0016\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/views/LiveEventsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "liveEventsData", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "liveEventsView", "Landroidx/recyclerview/widget/RecyclerView;", "mContext", "Landroidx/fragment/app/FragmentActivity;", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "initLiveEventsView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsFragment.kt */
public final class LiveEventsFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    private ArrayList<LiveEventItem> liveEventsData;
    private RecyclerView liveEventsView;
    private FragmentActivity mContext;
    public View rootView;

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

    public final View getRootView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view;
    }

    public final void setRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.rootView = view;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x002d, code lost:
        r2 = r2.getIntent();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(android.view.LayoutInflater r2, android.view.ViewGroup r3, android.os.Bundle r4) {
        /*
            r1 = this;
            java.lang.String r4 = "inflater"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            int r4 = com.webmd.wbmdcmepulse.R.layout.live_events_fragment
            r0 = 0
            android.view.View r2 = r2.inflate(r4, r3, r0)
            java.lang.String r3 = "inflater.inflate(R.layou…agment, container, false)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.rootView = r2
            java.lang.String r3 = "rootView"
            if (r2 != 0) goto L_0x001a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x001a:
            int r4 = com.webmd.wbmdcmepulse.R.id.live_events_list
            android.view.View r2 = r2.findViewById(r4)
            java.lang.String r4 = "rootView.findViewById(R.id.live_events_list)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            androidx.recyclerview.widget.RecyclerView r2 = (androidx.recyclerview.widget.RecyclerView) r2
            r1.liveEventsView = r2
            androidx.fragment.app.FragmentActivity r2 = r1.mContext
            if (r2 == 0) goto L_0x003a
            android.content.Intent r2 = r2.getIntent()
            if (r2 == 0) goto L_0x003a
            java.lang.String r4 = "com.webmd.wbmdcmepulse.live.events.data"
            java.io.Serializable r2 = r2.getSerializableExtra(r4)
            goto L_0x003b
        L_0x003a:
            r2 = 0
        L_0x003b:
            if (r2 == 0) goto L_0x004c
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            r1.liveEventsData = r2
            r1.initLiveEventsView()
            android.view.View r2 = r1.rootView
            if (r2 != 0) goto L_0x004b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x004b:
            return r2
        L_0x004c:
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.collections.ArrayList<com.webmd.wbmdcmepulse.live_events.model.LiveEventItem> /* = java.util.ArrayList<com.webmd.wbmdcmepulse.live_events.model.LiveEventItem> */"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.live_events.views.LiveEventsFragment.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    private final void initLiveEventsView() {
        LiveEventsAdapter liveEventsAdapter;
        RecyclerView recyclerView = this.liveEventsView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("liveEventsView");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FragmentActivity fragmentActivity = this.mContext;
        if (fragmentActivity != null) {
            ArrayList<LiveEventItem> arrayList = this.liveEventsData;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("liveEventsData");
            }
            liveEventsAdapter = new LiveEventsAdapter(fragmentActivity, arrayList);
        } else {
            liveEventsAdapter = null;
        }
        recyclerView.setAdapter(liveEventsAdapter);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/views/LiveEventsFragment$Companion;", "", "()V", "newInstance", "Lcom/webmd/wbmdcmepulse/live_events/views/LiveEventsFragment;", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: LiveEventsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LiveEventsFragment newInstance() {
            return new LiveEventsFragment();
        }
    }
}
