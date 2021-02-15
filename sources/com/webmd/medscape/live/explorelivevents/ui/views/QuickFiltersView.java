package com.webmd.medscape.live.explorelivevents.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.OnFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.ui.adapters.FilterButtonRecyclerViewAdapter;
import com.webmd.medscape.live.explorelivevents.ui.decorators.MiddleDividerItemDecoration;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.ZonedDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0016\u0010\u0013\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0002R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/views/QuickFiltersView;", "Landroid/widget/RelativeLayout;", "Lcom/webmd/medscape/live/explorelivevents/common/OnFilterSelectedListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "btSpecificDate", "Landroid/widget/TextView;", "layoutRes", "", "listener", "mButtons", "Landroidx/recyclerview/widget/RecyclerView;", "onClick", "", "title", "", "datesSelected", "Lkotlin/Pair;", "Lorg/threeten/bp/ZonedDateTime;", "onSpecificFilterSelected", "setOnFilterSelectedListener", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuickFiltersView.kt */
public final class QuickFiltersView extends RelativeLayout implements OnFilterSelectedListener {
    private HashMap _$_findViewCache;
    private final TextView btSpecificDate;
    private final int layoutRes;
    /* access modifiers changed from: private */
    public OnFilterSelectedListener listener;
    private final RecyclerView mButtons;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QuickFiltersView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        int i = R.layout.quick_filters_view;
        this.layoutRes = i;
        View.inflate(context, i, this);
        View findViewById = findViewById(R.id.rv_buttons);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.rv_buttons)");
        this.mButtons = (RecyclerView) findViewById;
        View findViewById2 = findViewById(R.id.bt_specific_date);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.bt_specific_date)");
        this.btSpecificDate = (TextView) findViewById2;
        OnFilterSelectedListener onFilterSelectedListener = this;
        this.listener = onFilterSelectedListener;
        RecyclerView recyclerView = this.mButtons;
        recyclerView.setAdapter(new FilterButtonRecyclerViewAdapter(context, onFilterSelectedListener));
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2, 1, false));
        recyclerView.addItemDecoration(new MiddleDividerItemDecoration(context, MiddleDividerItemDecoration.Companion.getALL()));
        this.btSpecificDate.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ QuickFiltersView this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(View view) {
                OnFilterSelectedListener access$getListener$p = this.this$0.listener;
                if (access$getListener$p != null) {
                    access$getListener$p.onSpecificFilterSelected();
                }
            }
        });
    }

    public void onDateRangeFilterSelected() {
        OnFilterSelectedListener.DefaultImpls.onDateRangeFilterSelected(this);
    }

    public void onLocationFilterSelected() {
        OnFilterSelectedListener.DefaultImpls.onLocationFilterSelected(this);
    }

    public void onSpecialtyFilterSelected() {
        OnFilterSelectedListener.DefaultImpls.onSpecialtyFilterSelected(this);
    }

    public final void setOnFilterSelectedListener(OnFilterSelectedListener onFilterSelectedListener) {
        Intrinsics.checkNotNullParameter(onFilterSelectedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = onFilterSelectedListener;
    }

    public void onClick(String str, Pair<ZonedDateTime, ZonedDateTime> pair) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(pair, "datesSelected");
        OnFilterSelectedListener onFilterSelectedListener = this.listener;
        if (onFilterSelectedListener != null) {
            onFilterSelectedListener.onClick(str, pair);
        }
    }

    public void onSpecificFilterSelected() {
        OnFilterSelectedListener onFilterSelectedListener = this.listener;
        if (onFilterSelectedListener != null) {
            onFilterSelectedListener.onSpecificFilterSelected();
        }
    }
}
