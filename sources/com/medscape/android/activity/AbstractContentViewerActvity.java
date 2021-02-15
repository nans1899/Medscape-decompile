package com.medscape.android.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.calc.CalcArticleActivity;
import com.medscape.android.activity.interactions.DrugInteractionActivity;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.db.model.Drug;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.drugs.model.DrugFindHelper;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.updater.UpdateManager;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.wbmdcommons.receivers.ShareDataObservable;
import com.wbmd.wbmdcommons.receivers.ShareReceiver;
import com.wbmd.wbmdcommons.utils.Util;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public abstract class AbstractContentViewerActvity extends NavigableBaseActivity implements Observer {
    protected boolean isMedlineArticle = false;

    /* access modifiers changed from: protected */
    public abstract String getContentLink();

    /* access modifiers changed from: protected */
    public abstract String getContentTeaserForEmail();

    /* access modifiers changed from: protected */
    public abstract String getContentTitle();

    /* access modifiers changed from: protected */
    public abstract int getMenuItemsLayout();

    /* access modifiers changed from: protected */
    public abstract boolean isContentTitleDisplayed();

    /* access modifiers changed from: protected */
    public abstract void saveContent();

    @Deprecated
    public void showUpButton() {
    }

    /* access modifiers changed from: protected */
    public boolean isLoggedOut() {
        return !AccountProvider.isUserLoggedIn(this);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.content_page_save_empty, menu);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isDataUpdateRequired() {
        if (getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", -1.0f) == -1.0f) {
            return true;
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        PendingIntent pendingIntent;
        Drug drug = null;
        switch (menuItem.getItemId()) {
            case R.id.action_add_to_interactions /*2131361964*/:
                Intent intent = new Intent(this, DrugInteractionActivity.class);
                if (this instanceof DrugMonographMainActivity) {
                    drug = ((DrugMonographMainActivity) this).getDrugForInteraction();
                }
                if (drug != null) {
                    intent.putExtra("drug", drug);
                    startActivity(intent);
                    OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "bartool-add", "ic", (Map<String, Object>) null);
                }
                return true;
            case R.id.action_remove /*2131362004*/:
            case R.id.action_save /*2131362005*/:
                saveContent();
                return true;
            case R.id.action_share /*2131362010*/:
                PendingIntent createPendingIntent = new ShareReceiver().createPendingIntent(this);
                if (Build.VERSION.SDK_INT <= 22) {
                    OmnitureManager.get().trackModule(this, getOmnitureChannel(), ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), AdParameterKeys.SECTION_ID, (Map<String, Object>) null);
                    pendingIntent = null;
                } else {
                    ShareDataObservable.INSTANCE.addObserver(this);
                    pendingIntent = createPendingIntent;
                }
                Util.share(this, getContentLink(), getContentTitle(), getSubjectForShareEmail(), (String) null, pendingIntent);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        showUpButton();
        getSupportActionBar().setIcon((int) R.drawable.ic_action_previous_item);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(isContentTitleDisplayed());
    }

    /* access modifiers changed from: protected */
    public void setupActionBarNoIcon() {
        super.setupActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(isContentTitleDisplayed());
    }

    public void update(Observable observable, Object obj) {
        if (observable instanceof ShareDataObservable) {
            if (obj instanceof String) {
                OmnitureManager.get().trackModule(this, getOmnitureChannel(), ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), obj.toString(), (Map<String, Object>) null);
            }
            ShareDataObservable.INSTANCE.deleteObserver(this);
        }
    }

    public class SharePopupMenuItemListener implements PopupMenu.OnMenuItemClickListener {
        DrugFindHelper.DrugFindMenuClickListener drugFindMenuClickListener = null;

        public SharePopupMenuItemListener() {
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_to_interactions /*2131361964*/:
                    Intent intent = new Intent(AbstractContentViewerActvity.this, DrugInteractionActivity.class);
                    Drug drug = null;
                    AbstractContentViewerActvity abstractContentViewerActvity = AbstractContentViewerActvity.this;
                    if (abstractContentViewerActvity instanceof DrugMonographMainActivity) {
                        drug = ((DrugMonographMainActivity) abstractContentViewerActvity).getDrugForInteraction();
                    }
                    if (drug != null) {
                        intent.putExtra("drug", drug);
                        AbstractContentViewerActvity.this.startActivity(intent);
                        OmnitureManager.get().trackModule(AbstractContentViewerActvity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "bartool-add", "ic", (Map<String, Object>) null);
                    }
                    return true;
                case R.id.action_find /*2131361990*/:
                    DrugFindHelper.DrugFindMenuClickListener drugFindMenuClickListener2 = this.drugFindMenuClickListener;
                    if (drugFindMenuClickListener2 != null) {
                        drugFindMenuClickListener2.onFindMenuItemClicked();
                        OmnitureManager.get().trackModule(AbstractContentViewerActvity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "find-on-page", "drgs", (Map<String, Object>) null);
                    }
                    return true;
                case R.id.action_remove /*2131362004*/:
                    AbstractContentViewerActvity.this.saveContent();
                    return true;
                case R.id.action_save /*2131362005*/:
                    AbstractContentViewerActvity.this.saveContent();
                    return true;
                default:
                    return false;
            }
        }
    }

    public void startActivity(Intent intent) {
        intent.putExtra("parentExtra", getIntent().getExtras());
        super.startActivity(intent);
    }

    private String getOmnitureChannel() {
        return (!(this instanceof ClinicalReferenceArticleActivity) && !(this instanceof CalcArticleActivity) && !(this instanceof DrugMonographMainActivity)) ? "other" : Constants.OMNITURE_CHANNEL_REFERENCE;
    }

    private String getSubjectForShareEmail() {
        if (!(this instanceof DrugMonographMainActivity)) {
            return getString(R.string.email_title);
        }
        return "Medscape: " + getContentTitle();
    }
}
