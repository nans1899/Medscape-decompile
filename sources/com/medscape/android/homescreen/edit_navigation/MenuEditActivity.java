package com.medscape.android.homescreen.edit_navigation;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.homescreen.edit_navigation.adapter.EditNavAdapter;
import com.medscape.android.homescreen.edit_navigation.viewmodel.NavEditViewModel;
import com.medscape.android.homescreen.home_nav_tray.helpers.NavigationProvider;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.homescreen.home_nav_tray.views.MenuMoveCallcbacks;
import com.medscape.android.homescreen.interfaces.INavItemClickListener;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\u0012\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u0018\u00104\u001a\u00020-2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0015H\u0016J\u0010\u00108\u001a\u0002012\u0006\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020-H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006<"}, d2 = {"Lcom/medscape/android/homescreen/edit_navigation/MenuEditActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/medscape/android/homescreen/interfaces/INavItemClickListener;", "()V", "editViewModel", "Lcom/medscape/android/homescreen/edit_navigation/viewmodel/NavEditViewModel;", "getEditViewModel", "()Lcom/medscape/android/homescreen/edit_navigation/viewmodel/NavEditViewModel;", "setEditViewModel", "(Lcom/medscape/android/homescreen/edit_navigation/viewmodel/NavEditViewModel;)V", "itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "getItemTouchHelper", "()Landroidx/recyclerview/widget/ItemTouchHelper;", "setItemTouchHelper", "(Landroidx/recyclerview/widget/ItemTouchHelper;)V", "navigationProvider", "Lcom/medscape/android/homescreen/home_nav_tray/helpers/NavigationProvider;", "getNavigationProvider", "()Lcom/medscape/android/homescreen/home_nav_tray/helpers/NavigationProvider;", "procedureFolderId", "", "getProcedureFolderId", "()I", "setProcedureFolderId", "(I)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "viewAdapter", "Lcom/medscape/android/homescreen/edit_navigation/adapter/EditNavAdapter;", "getViewAdapter", "()Lcom/medscape/android/homescreen/edit_navigation/adapter/EditNavAdapter;", "setViewAdapter", "(Lcom/medscape/android/homescreen/edit_navigation/adapter/EditNavAdapter;)V", "viewManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "getViewManager", "()Landroidx/recyclerview/widget/LinearLayoutManager;", "setViewManager", "(Landroidx/recyclerview/widget/LinearLayoutManager;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onNavItemClicked", "navItem", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "position", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MenuEditActivity.kt */
public final class MenuEditActivity extends AppCompatActivity implements INavItemClickListener {
    private HashMap _$_findViewCache;
    public NavEditViewModel editViewModel;
    public ItemTouchHelper itemTouchHelper;
    private final NavigationProvider navigationProvider = new NavigationProvider();
    private int procedureFolderId = 44;
    public RecyclerView recyclerView;
    public EditNavAdapter viewAdapter;
    public LinearLayoutManager viewManager;

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

    public final LinearLayoutManager getViewManager() {
        LinearLayoutManager linearLayoutManager = this.viewManager;
        if (linearLayoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewManager");
        }
        return linearLayoutManager;
    }

    public final void setViewManager(LinearLayoutManager linearLayoutManager) {
        Intrinsics.checkNotNullParameter(linearLayoutManager, "<set-?>");
        this.viewManager = linearLayoutManager;
    }

    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        return recyclerView2;
    }

    public final void setRecyclerView(RecyclerView recyclerView2) {
        Intrinsics.checkNotNullParameter(recyclerView2, "<set-?>");
        this.recyclerView = recyclerView2;
    }

    public final EditNavAdapter getViewAdapter() {
        EditNavAdapter editNavAdapter = this.viewAdapter;
        if (editNavAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        return editNavAdapter;
    }

    public final void setViewAdapter(EditNavAdapter editNavAdapter) {
        Intrinsics.checkNotNullParameter(editNavAdapter, "<set-?>");
        this.viewAdapter = editNavAdapter;
    }

    public final NavEditViewModel getEditViewModel() {
        NavEditViewModel navEditViewModel = this.editViewModel;
        if (navEditViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        return navEditViewModel;
    }

    public final void setEditViewModel(NavEditViewModel navEditViewModel) {
        Intrinsics.checkNotNullParameter(navEditViewModel, "<set-?>");
        this.editViewModel = navEditViewModel;
    }

    public final ItemTouchHelper getItemTouchHelper() {
        ItemTouchHelper itemTouchHelper2 = this.itemTouchHelper;
        if (itemTouchHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemTouchHelper");
        }
        return itemTouchHelper2;
    }

    public final void setItemTouchHelper(ItemTouchHelper itemTouchHelper2) {
        Intrinsics.checkNotNullParameter(itemTouchHelper2, "<set-?>");
        this.itemTouchHelper = itemTouchHelper2;
    }

    public final int getProcedureFolderId() {
        return this.procedureFolderId;
    }

    public final void setProcedureFolderId(int i) {
        this.procedureFolderId = i;
    }

    public final NavigationProvider getNavigationProvider() {
        return this.navigationProvider;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_menu_edit);
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.menu_edit_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.medscape_blue));
        ActionBar supportActionBar2 = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar2);
        supportActionBar2.setBackgroundDrawable(colorDrawable);
        ViewModel viewModel = ViewModelProviders.of((FragmentActivity) this).get(NavEditViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProviders.of(th…ditViewModel::class.java)");
        this.editViewModel = (NavEditViewModel) viewModel;
        Context context = this;
        this.viewManager = new LinearLayoutManager(context);
        NavEditViewModel navEditViewModel = this.editViewModel;
        if (navEditViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        List<NavItem> items = navEditViewModel.getItems(context);
        NavEditViewModel navEditViewModel2 = this.editViewModel;
        if (navEditViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        Boolean value = navEditViewModel2.isEditMode().getValue();
        if (value == null) {
            value = false;
        }
        Intrinsics.checkNotNullExpressionValue(value, "editViewModel.isEditMode…\n                ?: false");
        this.viewAdapter = new EditNavAdapter(items, value.booleanValue(), this);
        View findViewById = findViewById(R.id.edit_menu_recycler_view);
        RecyclerView recyclerView2 = (RecyclerView) findViewById;
        LinearLayoutManager linearLayoutManager = this.viewManager;
        if (linearLayoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewManager");
        }
        recyclerView2.setLayoutManager(linearLayoutManager);
        EditNavAdapter editNavAdapter = this.viewAdapter;
        if (editNavAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        recyclerView2.setAdapter(editNavAdapter);
        Unit unit = Unit.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<androidx.re…r = viewAdapter\n        }");
        this.recyclerView = recyclerView2;
        EditNavAdapter editNavAdapter2 = this.viewAdapter;
        if (editNavAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        this.itemTouchHelper = new ItemTouchHelper(new MenuMoveCallcbacks(editNavAdapter2));
        NavEditViewModel navEditViewModel3 = this.editViewModel;
        if (navEditViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        navEditViewModel3.isEditMode().observe(this, new MenuEditActivity$onCreate$2(this));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        NavEditViewModel navEditViewModel = this.editViewModel;
        if (navEditViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        if (Intrinsics.areEqual((Object) navEditViewModel.isEditMode().getValue(), (Object) true)) {
            getMenuInflater().inflate(R.menu.edit_nav_dome_menu, menu);
        } else {
            getMenuInflater().inflate(R.menu.edit_nav_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
        } else if (itemId == R.id.done) {
            OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "done", (Map<String, Object>) null);
            NavEditViewModel navEditViewModel = this.editViewModel;
            if (navEditViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
            }
            navEditViewModel.isEditMode().setValue(false);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.home_menu_edit_title);
            }
        } else if (itemId == R.id.edit) {
            OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "edit", (Map<String, Object>) null);
            NavEditViewModel navEditViewModel2 = this.editViewModel;
            if (navEditViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
            }
            navEditViewModel2.isEditMode().setValue(true);
            ActionBar supportActionBar2 = getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setTitle((int) R.string.home_menu_edit_title_activated);
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onNavItemClicked(NavItem navItem, int i) {
        Intrinsics.checkNotNullParameter(navItem, "navItem");
        if (navItem.getId() == 4) {
            navItem.setFolderId(this.procedureFolderId);
        }
        this.navigationProvider.navigateTo(this, navItem);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.procedureFolderId = Util.getFolderIdForProcedures(this);
        OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "view", (String) null, (String) null, (Map<String, Object>) null);
    }
}
