package com.wbmd.qxcalculator.activities.contentItems;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.DataObserverActivity;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.EventsManager;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.managers.InternetConnectivityManager;
import com.wbmd.qxcalculator.managers.QxFirebaseEventManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.parsedObjects.Promotion;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.FirebaseEventsConstants;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ContentItemActivity extends DataObserverActivity {
    public static final String KEY_EXTRA_CONTENT_ITEM = "ContentItemActivity.KEY_EXTRA_CONTENT_ITEM";
    private static final String KEY_EXTRA_START_TIME = "ContentItemActivity.KEY_EXTRA_START_TIME";
    public static final String KEY_IS_MORE_INFO_SECTION = "FileSourceHtmlActivity.KEY_IS_MORE_INFO_SECTION";
    private static final String TASK_ID_UPDATE_FAVORITE = "ContentItemActivity.TASK_ID_UPDATE_FAVORITE.";
    protected ContentItem contentItem;
    protected boolean hasAppeared = false;
    protected boolean isMoreInfoSection;
    protected Date useStartTime;

    /* access modifiers changed from: protected */
    public boolean requiresContentItem() {
        return true;
    }

    /* access modifiers changed from: protected */
    public List<String> getDataChangeTaskIdsToObserve() {
        List<String> dataChangeTaskIdsToObserve = super.getDataChangeTaskIdsToObserve();
        if (this.contentItem != null) {
            if (dataChangeTaskIdsToObserve == null) {
                dataChangeTaskIdsToObserve = new ArrayList<>();
            }
            dataChangeTaskIdsToObserve.add(getContentItemSpecificTaskId());
        }
        return dataChangeTaskIdsToObserve;
    }

    private String getContentItemSpecificTaskId() {
        return TASK_ID_UPDATE_FAVORITE + this.contentItem.identifier;
    }

    public boolean dataManagerMethodFinished(String str, boolean z, List<QxError> list, Bundle bundle) {
        if (!super.dataManagerMethodFinished(str, z, list, bundle) && str.equals(getContentItemSpecificTaskId())) {
            if (z) {
                DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(this.contentItem.identifier);
                this.contentItem.isFavorite = Boolean.valueOf(contentItemForIdentifier.getIsFavorite() == null ? false : contentItemForIdentifier.getIsFavorite().booleanValue());
                Toast.makeText(this, this.contentItem.isFavorite.booleanValue() ? R.string.fave_added : R.string.fave_removed, 0).show();
            } else {
                Toast.makeText(this, R.string.fave_update_error, 0).show();
            }
            invalidateOptionsMenu();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.isMoreInfoSection = getIntent().getBooleanExtra(KEY_IS_MORE_INFO_SECTION, false);
        if (bundle != null) {
            this.contentItem = (ContentItem) bundle.getParcelable(KEY_EXTRA_CONTENT_ITEM);
            long j = bundle.getLong(KEY_EXTRA_START_TIME, 0);
            if (j == 0) {
                this.useStartTime = new Date();
            } else {
                this.useStartTime = new Date(j);
            }
        } else {
            this.contentItem = (ContentItem) getIntent().getParcelableExtra(KEY_EXTRA_CONTENT_ITEM);
            this.useStartTime = new Date();
        }
        if (this.contentItem == null && requiresContentItem()) {
            finish();
        } else if (getSupportActionBar() == null) {
        } else {
            if (this.contentItem != null) {
                getSupportActionBar().setTitle((CharSequence) this.contentItem.overrideName != null ? this.contentItem.overrideName : this.contentItem.name);
                setTitle(this.contentItem.overrideName != null ? this.contentItem.overrideName : this.contentItem.name);
                return;
            }
            getSupportActionBar().setTitle((CharSequence) null);
            setTitle(getString(R.string.app_full_name));
        }
    }

    public void onResume() {
        super.onResume();
        invalidateOptionsMenu();
        if (this.contentItem != null && !this.isMoreInfoSection && !this.hasAppeared) {
            AnalyticsHandler.getInstance().trackPageView(this, this.contentItem.name);
        }
        ContentItem contentItem2 = this.contentItem;
        if (contentItem2 != null) {
            Promotion promotion = contentItem2.promotionToUse;
        }
        this.hasAppeared = true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.contentItem != null && !this.isMoreInfoSection) {
            AnalyticsHandler instance = AnalyticsHandler.getInstance();
            instance.trackPageView(this, this.contentItem.name + " Finished");
        }
        ContentItem contentItem2 = this.contentItem;
        if (contentItem2 != null && !this.isMoreInfoSection && contentItem2.promotionToUse != null) {
            long j = 0;
            if (this.useStartTime != null) {
                j = new Date().getTime() - this.useStartTime.getTime();
            }
            EventsManager instance2 = EventsManager.getInstance();
            ContentItem contentItem3 = this.contentItem;
            instance2.trackContentItemFinishedUse(contentItem3, contentItem3.promotionToUse, j);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(KEY_EXTRA_CONTENT_ITEM, this.contentItem);
        bundle.putLong(KEY_EXTRA_START_TIME, this.useStartTime.getTime());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.contentItem != null) {
            getMenuInflater().inflate(R.menu.menu_content_item, menu);
            MenuItem findItem = menu.findItem(R.id.action_more_info);
            menu.findItem(R.id.action_fave);
            if ((this.contentItem.moreInfoSource == null || this.contentItem.moreInfoSource.isEmpty()) && (this.contentItem.moreInfoSections == null || this.contentItem.moreInfoSections.isEmpty())) {
                findItem.setVisible(false);
            } else {
                findItem.getIcon().setColorFilter(new PorterDuffColorFilter(-234881025, PorterDuff.Mode.SRC_IN));
                findItem.setVisible(true);
            }
        }
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        PorterDuffColorFilter porterDuffColorFilter;
        if (this.contentItem == null) {
            return true;
        }
        MenuItem findItem = menu.findItem(R.id.action_fave);
        if (this.contentItem.isFavorite == null || !this.contentItem.isFavorite.booleanValue()) {
            porterDuffColorFilter = new PorterDuffColorFilter(-234881025, PorterDuff.Mode.SRC_IN);
        } else {
            porterDuffColorFilter = new PorterDuffColorFilter(-12530, PorterDuff.Mode.SRC_IN);
        }
        if (DataManager.getInstance().isTaskCurrentlyRunning(getContentItemSpecificTaskId())) {
            findItem.setActionView(R.layout.view_progress_bar_in_toolbar);
            View findViewById = findItem.getActionView().findViewById(R.id.progress_bar);
            if (findViewById != null && (findViewById instanceof ProgressBar)) {
                ((ProgressBar) findViewById).getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.toolbar_tint), PorterDuff.Mode.SRC_IN);
            }
        } else {
            findItem.setActionView((View) null);
        }
        findItem.getIcon().setColorFilter(porterDuffColorFilter);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_more_info) {
            if ((this.contentItem.moreInfoSource != null && !this.contentItem.moreInfoSource.isEmpty()) || (this.contentItem.moreInfoSections != null && !this.contentItem.moreInfoSections.isEmpty())) {
                showMoreInformation();
            }
            sendFirebaseEventForCalculatorPageAction(getString(R.string.more_info), FirebaseEventsConstants.CALC_TOOLBAR_ACTION);
            return true;
        } else if (itemId == R.id.action_fave) {
            if (UserManager.getInstance().needsUpgradeToUniversalAccounts()) {
                updateIsFavoriteStatus();
                DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(this.contentItem.identifier);
                if (this.contentItem.isFavorite.booleanValue()) {
                    UserManager.getInstance().getDbUser().addContentItemToFavorites(contentItemForIdentifier, this);
                } else {
                    UserManager.getInstance().getDbUser().removeContentItemToFavorites(contentItemForIdentifier, this);
                }
                invalidateOptionsMenu();
            } else if (!InternetConnectivityManager.getInstance().isConnectedToInternet()) {
                showErrorDialog(R.string.oops, R.string.no_internet_message);
            } else if ((!ContentDataManager.getInstance().shouldRefresh() || ContentDataManager.getInstance().getIsFetchingUpdates()) && (ContentDataManager.getInstance().accountRefreshErrors == null || ContentDataManager.getInstance().accountRefreshErrors.isEmpty())) {
                if (!DataManager.getInstance().isTaskCurrentlyRunning(getContentItemSpecificTaskId())) {
                    updateIsFavoriteStatus();
                    ContentDataManager.getInstance().setFavoriteStatus(this.contentItem.isFavorite.booleanValue(), this.contentItem.identifier, getContentItemSpecificTaskId());
                    invalidateOptionsMenu();
                }
                if (this.contentItem.isFavorite.booleanValue()) {
                    sendFirebaseEventForCalculatorPageAction(getString(R.string.favourite), FirebaseEventsConstants.CALC_TOOLBAR_ACTION);
                }
            } else {
                new AlertDialog.Builder(this).setTitle(R.string.dialog_sync_needed_title).setMessage(R.string.dialog_sync_needed_body).setNegativeButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
            }
            return true;
        } else if (itemId != R.id.action_share) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            shareButtonPressed();
            sendFirebaseEventForCalculatorPageAction(getString(R.string.share), FirebaseEventsConstants.CALC_TOOLBAR_ACTION);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void shareButtonPressed() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", "https://www.qxmd.com/calculate/" + this.contentItem.identifier);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getString(R.string.share_item)));
    }

    private void showMoreInformation() {
        Intent intent;
        if (this.contentItem.moreInfoSections == null || this.contentItem.moreInfoSections.isEmpty()) {
            String str = this.contentItem.moreInfoSource;
            if (str.toLowerCase().contains(ContentParser.PDF)) {
                intent = new Intent(this, PdfViewerActivity.class);
                if (!str.toLowerCase().startsWith("http")) {
                    str = FileHelper.getInstance().getResourceFolderPathForContentItem(this.contentItem) + str;
                }
                intent.putExtra(PdfViewerActivity.KEY_BUNDLE_URL, str);
            } else {
                Intent intent2 = new Intent(this, FileSourceHtmlActivity.class);
                if (!str.toLowerCase().startsWith("http")) {
                    str = "file://" + FileHelper.getInstance().getResourceFolderPathForContentItem(this.contentItem) + str;
                }
                intent2.putExtra(FileSourceHtmlActivity.KEY_INTENT_URL, str);
                intent2.putExtra(QxMDActivity.KEY_TRACKER_PAGE_NAME, this.contentItem.name + " - More Information Page");
                if (this.contentItem.trackerId != null && !this.contentItem.trackerId.isEmpty()) {
                    intent2.putExtra(QxMDActivity.KEY_TRACKER_ID, this.contentItem.trackerId);
                }
                intent2.putExtra(KEY_IS_MORE_INFO_SECTION, true);
                intent2.putExtra(KEY_EXTRA_CONTENT_ITEM, this.contentItem);
                intent = intent2;
            }
            startActivity(intent);
        } else {
            Intent intent3 = new Intent(this, MoreInfoMultiSectionActivity.class);
            intent3.putExtra(KEY_EXTRA_CONTENT_ITEM, this.contentItem);
            startActivity(intent3);
        }
        overridePendingTransition(R.anim.open_enter_modal, R.anim.open_exit_modal);
    }

    private void sendFirebaseEventForCalculatorPageAction(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseEventsConstants.CLICKED_TOOLBAR_ACTION_KEY, str);
        QxFirebaseEventManager.getInstance(this).sendEventName(str2, bundle);
    }

    private void updateIsFavoriteStatus() {
        if (this.contentItem.isFavorite != null) {
            ContentItem contentItem2 = this.contentItem;
            contentItem2.isFavorite = Boolean.valueOf(true ^ contentItem2.isFavorite.booleanValue());
            return;
        }
        this.contentItem.isFavorite = true;
    }
}
