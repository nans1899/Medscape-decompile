package com.medscape.android.consult.postupdates.views;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.models.BodyUpdates;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.postupdates.viewmodels.ConsultPostUpdateViewModel;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0002J\u0012\u0010\"\u001a\u00020!2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0012\u0010%\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0010\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020!2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d¨\u0006,"}, d2 = {"Lcom/medscape/android/consult/postupdates/views/ConsultPostUpdateActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "bodyUpdates", "Lcom/medscape/android/consult/models/BodyUpdates;", "getBodyUpdates", "()Lcom/medscape/android/consult/models/BodyUpdates;", "setBodyUpdates", "(Lcom/medscape/android/consult/models/BodyUpdates;)V", "consultPost", "Lcom/medscape/android/consult/models/ConsultPost;", "getConsultPost", "()Lcom/medscape/android/consult/models/ConsultPost;", "setConsultPost", "(Lcom/medscape/android/consult/models/ConsultPost;)V", "isEditing", "", "()Z", "setEditing", "(Z)V", "viewModel", "Lcom/medscape/android/consult/postupdates/viewmodels/ConsultPostUpdateViewModel;", "getViewModel", "()Lcom/medscape/android/consult/postupdates/viewmodels/ConsultPostUpdateViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initializeFragment", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "setActionBarTitle", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultPostUpdateActivity.kt */
public final class ConsultPostUpdateActivity extends AppCompatActivity {
    private String TAG = "ConsultPostUpdateActivity";
    private HashMap _$_findViewCache;
    private BodyUpdates bodyUpdates;
    private ConsultPost consultPost;
    private boolean isEditing;
    private final Lazy viewModel$delegate = LazyKt.lazy(new ConsultPostUpdateActivity$viewModel$2(this));

    private final ConsultPostUpdateViewModel getViewModel() {
        return (ConsultPostUpdateViewModel) this.viewModel$delegate.getValue();
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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.TAG = str;
    }

    public final boolean isEditing() {
        return this.isEditing;
    }

    public final void setEditing(boolean z) {
        this.isEditing = z;
    }

    public final ConsultPost getConsultPost() {
        return this.consultPost;
    }

    public final void setConsultPost(ConsultPost consultPost2) {
        this.consultPost = consultPost2;
    }

    public final BodyUpdates getBodyUpdates() {
        return this.bodyUpdates;
    }

    public final void setBodyUpdates(BodyUpdates bodyUpdates2) {
        this.bodyUpdates = bodyUpdates2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_consult_post_update);
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.consult_post_update_toolbar));
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.isEditing = extras.getBoolean(Constants.EXTRA_IS_EDITING);
            this.consultPost = (ConsultPost) extras.getParcelable(Constants.EXTRA_CONSULT_POST);
            this.bodyUpdates = (BodyUpdates) extras.getParcelable(Constants.EXTRA_CONSULT_POST_BODY);
        }
        setActionBarTitle(this.isEditing);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.consult_blue));
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setBackgroundDrawable(colorDrawable);
        }
        ActionBar supportActionBar3 = getSupportActionBar();
        if (supportActionBar3 != null) {
            supportActionBar3.setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
        }
        if (bundle == null) {
            initializeFragment();
        }
    }

    private final void setActionBarTitle(boolean z) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            return;
        }
        if (z) {
            supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.consult_edit_update) + "</font>"));
            return;
        }
        supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.consult_add_update) + "</font>"));
    }

    private final void initializeFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ConsultPostUpdateFragment.Companion.newInstance(this.isEditing, this.consultPost, this.bodyUpdates)).commitNow();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.consult_post_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (menuItem.getItemId() == 16908332 || menuItem.getItemId() == R.id.action_post_cancel) {
            getViewModel().handleCancel(this);
            return true;
        }
        super.onOptionsItemSelected(menuItem);
        return true;
    }
}
