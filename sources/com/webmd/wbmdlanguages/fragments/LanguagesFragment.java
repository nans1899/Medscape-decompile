package com.webmd.wbmdlanguages.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.wbmdlanguages.R;
import com.webmd.wbmdlanguages.adapters.LanguagesAdapter;
import com.webmd.wbmdlanguages.viewmodels.LanguageSupportViewModel;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J&\u0010!\u001a\u0004\u0018\u00010\u00182\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010&\u001a\u00020\u001eH\u0002J\u0006\u0010'\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006)"}, d2 = {"Lcom/webmd/wbmdlanguages/fragments/LanguagesFragment;", "Landroidx/fragment/app/Fragment;", "()V", "languageSupportViewModel", "Lcom/webmd/wbmdlanguages/viewmodels/LanguageSupportViewModel;", "languagesAdapter", "Lcom/webmd/wbmdlanguages/adapters/LanguagesAdapter;", "getLanguagesAdapter", "()Lcom/webmd/wbmdlanguages/adapters/LanguagesAdapter;", "setLanguagesAdapter", "(Lcom/webmd/wbmdlanguages/adapters/LanguagesAdapter;)V", "languagesList", "Landroidx/recyclerview/widget/RecyclerView;", "getLanguagesList", "()Landroidx/recyclerview/widget/RecyclerView;", "setLanguagesList", "(Landroidx/recyclerview/widget/RecyclerView;)V", "mContext", "Landroidx/fragment/app/FragmentActivity;", "getMContext", "()Landroidx/fragment/app/FragmentActivity;", "setMContext", "(Landroidx/fragment/app/FragmentActivity;)V", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setObserver", "setupRecyclerView", "Companion", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LanguagesFragment.kt */
public final class LanguagesFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public LanguageSupportViewModel languageSupportViewModel;
    public LanguagesAdapter languagesAdapter;
    public RecyclerView languagesList;
    public FragmentActivity mContext;
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

    public static final /* synthetic */ LanguageSupportViewModel access$getLanguageSupportViewModel$p(LanguagesFragment languagesFragment) {
        LanguageSupportViewModel languageSupportViewModel2 = languagesFragment.languageSupportViewModel;
        if (languageSupportViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageSupportViewModel");
        }
        return languageSupportViewModel2;
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

    public final FragmentActivity getMContext() {
        FragmentActivity fragmentActivity = this.mContext;
        if (fragmentActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        return fragmentActivity;
    }

    public final void setMContext(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<set-?>");
        this.mContext = fragmentActivity;
    }

    public final RecyclerView getLanguagesList() {
        RecyclerView recyclerView = this.languagesList;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languagesList");
        }
        return recyclerView;
    }

    public final void setLanguagesList(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.languagesList = recyclerView;
    }

    public final LanguagesAdapter getLanguagesAdapter() {
        LanguagesAdapter languagesAdapter2 = this.languagesAdapter;
        if (languagesAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languagesAdapter");
        }
        return languagesAdapter2;
    }

    public final void setLanguagesAdapter(LanguagesAdapter languagesAdapter2) {
        Intrinsics.checkNotNullParameter(languagesAdapter2, "<set-?>");
        this.languagesAdapter = languagesAdapter2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getActivity() != null) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                this.mContext = activity;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
        }
        FragmentActivity fragmentActivity = this.mContext;
        if (fragmentActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        LanguageSupportViewModel languageSupportViewModel2 = fragmentActivity != null ? (LanguageSupportViewModel) ViewModelProviders.of(fragmentActivity).get(LanguageSupportViewModel.class) : null;
        Intrinsics.checkNotNullExpressionValue(languageSupportViewModel2, "mContext?.let { ViewMode…tViewModel::class.java) }");
        this.languageSupportViewModel = languageSupportViewModel2;
        if (languageSupportViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageSupportViewModel");
        }
        FragmentActivity fragmentActivity2 = this.mContext;
        if (fragmentActivity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        languageSupportViewModel2.loadLanguages(fragmentActivity2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_languages, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…guages, container, false)");
        this.rootView = inflate;
        setupRecyclerView();
        setObserver();
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view;
    }

    public final void setupRecyclerView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById = view.findViewById(R.id.list);
        if (findViewById != null) {
            RecyclerView recyclerView = (RecyclerView) findViewById;
            this.languagesList = recyclerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languagesList");
            }
            FragmentActivity fragmentActivity = this.mContext;
            if (fragmentActivity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(fragmentActivity));
            FragmentActivity fragmentActivity2 = this.mContext;
            if (fragmentActivity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            }
            LanguagesAdapter languagesAdapter2 = new LanguagesAdapter(fragmentActivity2);
            this.languagesAdapter = languagesAdapter2;
            if (languagesAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languagesAdapter");
            }
            LanguageSupportViewModel languageSupportViewModel2 = this.languageSupportViewModel;
            if (languageSupportViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languageSupportViewModel");
            }
            languagesAdapter2.setData(languageSupportViewModel2.getLanguagesList());
            RecyclerView recyclerView2 = this.languagesList;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languagesList");
            }
            LanguagesAdapter languagesAdapter3 = this.languagesAdapter;
            if (languagesAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languagesAdapter");
            }
            recyclerView2.setAdapter(languagesAdapter3);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
    }

    private final void setObserver() {
        LanguageSupportViewModel languageSupportViewModel2 = this.languageSupportViewModel;
        if (languageSupportViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageSupportViewModel");
        }
        if (languageSupportViewModel2 != null) {
            LanguageSupportViewModel languageSupportViewModel3 = this.languageSupportViewModel;
            if (languageSupportViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languageSupportViewModel");
            }
            languageSupportViewModel3.getCurrentCheckedPosition().observe(this, new LanguagesFragment$setObserver$1(this));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/webmd/wbmdlanguages/fragments/LanguagesFragment$Companion;", "", "()V", "newInstance", "Lcom/webmd/wbmdlanguages/fragments/LanguagesFragment;", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: LanguagesFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LanguagesFragment newInstance() {
            return new LanguagesFragment();
        }
    }
}
