package com.medscape.android.activity.calc.calcsettings.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.medscape.android.R;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0002J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002¨\u0006\u0013"}, d2 = {"Lcom/medscape/android/activity/calc/calcsettings/views/DefaultUnitsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setUpViews", "updateDefaultUnits", "dbUser", "Lcom/wbmd/qxcalculator/model/db/DBUser;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DefaultUnitsFragment.kt */
public final class DefaultUnitsFragment extends Fragment {
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

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_default_units, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setUpViews();
    }

    private final void setUpViews() {
        UserManager instance = UserManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "com.wbmd.qxcalculator.ma…UserManager.getInstance()");
        DBUser dbUser = instance.getDbUser();
        updateDefaultUnits(dbUser);
        ((RelativeLayout) _$_findCachedViewById(R.id.si_units_layout)).setOnClickListener(new DefaultUnitsFragment$setUpViews$1(this, dbUser));
        ((RelativeLayout) _$_findCachedViewById(R.id.us_units_layout)).setOnClickListener(new DefaultUnitsFragment$setUpViews$2(this, dbUser));
    }

    /* access modifiers changed from: private */
    public final void updateDefaultUnits(DBUser dBUser) {
        if (dBUser != null && dBUser.getDefaultUnits() != null) {
            String defaultUnits = dBUser.getDefaultUnits();
            Intrinsics.checkNotNullExpressionValue(defaultUnits, "dbUser.defaultUnits");
            if (!(defaultUnits.length() > 0)) {
                return;
            }
            if (Unit.UnitType.convertStringToType(dBUser.getDefaultUnits()) == Unit.UnitType.SI_UNITS) {
                ImageView imageView = (ImageView) _$_findCachedViewById(R.id.si_units_checkmark);
                Intrinsics.checkNotNullExpressionValue(imageView, "si_units_checkmark");
                imageView.setVisibility(0);
                ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.us_units_checkmark);
                Intrinsics.checkNotNullExpressionValue(imageView2, "us_units_checkmark");
                imageView2.setVisibility(4);
            } else if (Unit.UnitType.convertStringToType(dBUser.getDefaultUnits()) == Unit.UnitType.US_UNITS) {
                ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.us_units_checkmark);
                Intrinsics.checkNotNullExpressionValue(imageView3, "us_units_checkmark");
                imageView3.setVisibility(0);
                ImageView imageView4 = (ImageView) _$_findCachedViewById(R.id.si_units_checkmark);
                Intrinsics.checkNotNullExpressionValue(imageView4, "si_units_checkmark");
                imageView4.setVisibility(4);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/activity/calc/calcsettings/views/DefaultUnitsFragment$Companion;", "", "()V", "newInstance", "Lcom/medscape/android/activity/calc/calcsettings/views/DefaultUnitsFragment;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: DefaultUnitsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DefaultUnitsFragment newInstance() {
            return new DefaultUnitsFragment();
        }
    }
}
