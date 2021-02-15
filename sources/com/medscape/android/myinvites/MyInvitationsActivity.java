package com.medscape.android.myinvites;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.base.NavigableBaseActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\u0012\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsActivity;", "Lcom/medscape/android/base/NavigableBaseActivity;", "()V", "adapter", "Lcom/medscape/android/myinvites/MyInvitationsAdapter;", "viewModel", "Lcom/medscape/android/myinvites/MyInvitationsViewModel;", "initData", "", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupActionBar", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MyInvitationsActivity.kt */
public final class MyInvitationsActivity extends NavigableBaseActivity {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public MyInvitationsAdapter adapter;
    /* access modifiers changed from: private */
    public MyInvitationsViewModel viewModel;

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

    public static final /* synthetic */ MyInvitationsAdapter access$getAdapter$p(MyInvitationsActivity myInvitationsActivity) {
        MyInvitationsAdapter myInvitationsAdapter = myInvitationsActivity.adapter;
        if (myInvitationsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        return myInvitationsAdapter;
    }

    public static final /* synthetic */ MyInvitationsViewModel access$getViewModel$p(MyInvitationsActivity myInvitationsActivity) {
        MyInvitationsViewModel myInvitationsViewModel = myInvitationsActivity.viewModel;
        if (myInvitationsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return myInvitationsViewModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_my_invitations);
        setTitle(getResources().getString(R.string.my_invitations_title));
        initData();
        observeViewModel();
        MyInvitationsViewModel myInvitationsViewModel = this.viewModel;
        if (myInvitationsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        myInvitationsViewModel.load(this);
    }

    private final void initData() {
        ViewModel viewModel2 = ViewModelProviders.of((FragmentActivity) this).get(MyInvitationsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProviders.of(th…onsViewModel::class.java)");
        this.viewModel = (MyInvitationsViewModel) viewModel2;
        this.adapter = new MyInvitationsAdapter();
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recycler_view);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recycler_view");
        MyInvitationsAdapter myInvitationsAdapter = this.adapter;
        if (myInvitationsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        recyclerView.setAdapter(myInvitationsAdapter);
    }

    private final void observeViewModel() {
        MyInvitationsViewModel myInvitationsViewModel = this.viewModel;
        if (myInvitationsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        LifecycleOwner lifecycleOwner = this;
        myInvitationsViewModel.getLoadingData().observe(lifecycleOwner, new MyInvitationsActivity$observeViewModel$1(this));
        MyInvitationsViewModel myInvitationsViewModel2 = this.viewModel;
        if (myInvitationsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        myInvitationsViewModel2.getInvitationData().observe(lifecycleOwner, new MyInvitationsActivity$observeViewModel$2(this));
        MyInvitationsViewModel myInvitationsViewModel3 = this.viewModel;
        if (myInvitationsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        myInvitationsViewModel3.getErrorData().observe(lifecycleOwner, new MyInvitationsActivity$observeViewModel$3(this));
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setDisplayUseLogoEnabled(false);
        }
    }
}
