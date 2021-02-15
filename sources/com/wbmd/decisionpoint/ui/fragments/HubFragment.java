package com.wbmd.decisionpoint.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.databinding.FragmentHubBinding;
import com.wbmd.decisionpoint.domain.interfaces.HubListener;
import com.wbmd.decisionpoint.ui.adapters.HubAdapter;
import com.wbmd.decisionpoint.viewmodels.HubViewModel;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J&\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001a\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006 "}, d2 = {"Lcom/wbmd/decisionpoint/ui/fragments/HubFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/wbmd/decisionpoint/databinding/FragmentHubBinding;", "hubAdapter", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter;", "hubListener", "Lcom/wbmd/decisionpoint/domain/interfaces/HubListener;", "viewModel", "Lcom/wbmd/decisionpoint/viewmodels/HubViewModel;", "getViewModel", "()Lcom/wbmd/decisionpoint/viewmodels/HubViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initActionBar", "", "initViewModel", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Companion", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HubFragment.kt */
public final class HubFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    private FragmentHubBinding binding;
    private HubAdapter hubAdapter;
    private HubListener hubListener;
    private final Lazy viewModel$delegate = LazyKt.lazy(new HubFragment$viewModel$2(this));

    private final HubViewModel getViewModel() {
        return (HubViewModel) this.viewModel$delegate.getValue();
    }

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/wbmd/decisionpoint/ui/fragments/HubFragment$Companion;", "", "()V", "newInstance", "Lcom/wbmd/decisionpoint/ui/fragments/HubFragment;", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HubFragment newInstance() {
            return new HubFragment();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        FragmentHubBinding inflate = FragmentHubBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "FragmentHubBinding.infla…flater, container, false)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        inflate.setViewmodel(getViewModel());
        FragmentHubBinding fragmentHubBinding = this.binding;
        if (fragmentHubBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentHubBinding.setLifecycleOwner(getViewLifecycleOwner());
        FragmentHubBinding fragmentHubBinding2 = this.binding;
        if (fragmentHubBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return fragmentHubBinding2.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        HubListener hubListener2 = this.hubListener;
        if (hubListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hubListener");
        }
        this.hubAdapter = new HubAdapter(hubListener2);
        initViewModel();
        initActionBar();
    }

    private final void initViewModel() {
        if (getContext() != null) {
            HubViewModel viewModel = getViewModel();
            HubAdapter hubAdapter2 = this.hubAdapter;
            if (hubAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("hubAdapter");
            }
            viewModel.setHubAdapter(hubAdapter2);
            getViewModel().setItems();
            HubViewModel viewModel2 = getViewModel();
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recycler_view);
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recycler_view");
            viewModel2.getDecisionPoints(recyclerView);
        }
    }

    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof HubListener) {
            this.hubListener = (HubListener) context;
            return;
        }
        throw new RuntimeException("Attach HubListener on your activity!");
    }

    private final void initActionBar() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            FragmentHubBinding fragmentHubBinding = this.binding;
            if (fragmentHubBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            appCompatActivity.setSupportActionBar(fragmentHubBinding.toolbar);
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back_white);
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setTitle((CharSequence) "");
                FragmentHubBinding fragmentHubBinding2 = this.binding;
                if (fragmentHubBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                }
                fragmentHubBinding2.toolbar.setNavigationOnClickListener(new HubFragment$initActionBar$$inlined$apply$lambda$1(this, appCompatActivity));
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }
}
