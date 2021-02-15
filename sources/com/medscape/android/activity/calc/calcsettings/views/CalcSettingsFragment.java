package com.medscape.android.activity.calc.calcsettings.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.medscape.android.R;
import com.medscape.android.activity.calc.calcsettings.viewmodels.CalcSettingsViewModel;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/medscape/android/activity/calc/calcsettings/views/CalcSettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "viewModel", "Lcom/medscape/android/activity/calc/calcsettings/viewmodels/CalcSettingsViewModel;", "getViewModel", "()Lcom/medscape/android/activity/calc/calcsettings/viewmodels/CalcSettingsViewModel;", "setViewModel", "(Lcom/medscape/android/activity/calc/calcsettings/viewmodels/CalcSettingsViewModel;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setUpViews", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CalcSettingsFragment.kt */
public final class CalcSettingsFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    public CalcSettingsViewModel viewModel;

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

    public final CalcSettingsViewModel getViewModel() {
        CalcSettingsViewModel calcSettingsViewModel = this.viewModel;
        if (calcSettingsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return calcSettingsViewModel;
    }

    public final void setViewModel(CalcSettingsViewModel calcSettingsViewModel) {
        Intrinsics.checkNotNullParameter(calcSettingsViewModel, "<set-?>");
        this.viewModel = calcSettingsViewModel;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ViewModel viewModel2 = ViewModelProviders.of(activity).get(CalcSettingsViewModel.class);
            Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProviders.of(it…ngsViewModel::class.java)");
            this.viewModel = (CalcSettingsViewModel) viewModel2;
        }
        return layoutInflater.inflate(R.layout.fragment_calc_settings, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setUpViews();
    }

    private final void setUpViews() {
        boolean z;
        UserManager instance = UserManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "UserManager.getInstance()");
        DBUser dbUser = instance.getDbUser();
        Switch switchR = (Switch) _$_findCachedViewById(R.id.auto_question_switch);
        Intrinsics.checkNotNullExpressionValue(switchR, "auto_question_switch");
        Intrinsics.checkNotNullExpressionValue(dbUser, "dbUser");
        if (dbUser.getAutoEnterFirstQuestion() == null) {
            z = false;
        } else {
            Boolean autoEnterFirstQuestion = dbUser.getAutoEnterFirstQuestion();
            Intrinsics.checkNotNullExpressionValue(autoEnterFirstQuestion, "dbUser.autoEnterFirstQuestion");
            z = autoEnterFirstQuestion.booleanValue();
        }
        switchR.setChecked(z);
        ((Switch) _$_findCachedViewById(R.id.auto_question_switch)).setOnCheckedChangeListener(new CalcSettingsFragment$setUpViews$1(this, dbUser));
        ((RelativeLayout) _$_findCachedViewById(R.id.default_units_layout)).setOnClickListener(new CalcSettingsFragment$setUpViews$2(this));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/activity/calc/calcsettings/views/CalcSettingsFragment$Companion;", "", "()V", "newInstance", "Lcom/medscape/android/activity/calc/calcsettings/views/CalcSettingsFragment;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: CalcSettingsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CalcSettingsFragment newInstance() {
            return new CalcSettingsFragment();
        }
    }
}
