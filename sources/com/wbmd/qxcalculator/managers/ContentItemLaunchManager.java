package com.wbmd.qxcalculator.managers;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.FragmentActivity;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.activities.contentItems.ContentItemActivity;
import com.wbmd.qxcalculator.activities.contentItems.DefinitionActivity;
import com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity;
import com.wbmd.qxcalculator.activities.contentItems.PdfViewerActivity;
import com.wbmd.qxcalculator.activities.contentItems.ReferenceBookActivity;
import com.wbmd.qxcalculator.custom.baxter.BaxterRxCalculatorActivity;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSectionItem;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.filesource.FileSource;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBFileSource;
import com.wbmd.qxcalculator.model.db.DBRestriction;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.CrashLogger;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ContentItemLaunchManager {
    private static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    protected static final String TASK_ID_SET_RECENT = "ContentItemLaunchManager.TASK_ID_SET_RECENT";
    /* access modifiers changed from: protected */
    public static LaunchQxCallback calcCallback;
    private static ContentItemLaunchManager mInstance;
    private String sourceCalculatorCameFrom;

    public static ContentItemLaunchManager getInstance() {
        if (mInstance == null) {
            mInstance = new ContentItemLaunchManager();
        }
        return mInstance;
    }

    protected ContentItemLaunchManager() {
    }

    public boolean launchContentItem(DBContentItem dBContentItem, FragmentActivity fragmentActivity, LaunchQxCallback launchQxCallback, Intent intent) {
        calcCallback = launchQxCallback;
        return launchContentItem(dBContentItem, fragmentActivity, intent, -1, (String) null, false);
    }

    public boolean launchContentItem(DBContentItem dBContentItem, FragmentActivity fragmentActivity) {
        return launchContentItem(dBContentItem, fragmentActivity, (String) null, false);
    }

    public boolean launchContentItem(DBContentItem dBContentItem, FragmentActivity fragmentActivity, Intent intent) {
        return launchContentItem(dBContentItem, fragmentActivity, intent, -1, (String) null, false);
    }

    public boolean launchContentItem(DBContentItem dBContentItem, FragmentActivity fragmentActivity, boolean z) {
        return launchContentItem(dBContentItem, fragmentActivity, (String) null, z);
    }

    public boolean launchContentItem(DBContentItem dBContentItem, FragmentActivity fragmentActivity, String str, boolean z) {
        return launchContentItem(dBContentItem, fragmentActivity, (Intent) null, -1, str, z);
    }

    public boolean launchContentItem(DBContentItem dBContentItem, FragmentActivity fragmentActivity, Intent intent, int i, boolean z) {
        return launchContentItem(dBContentItem, fragmentActivity, intent, i, (String) null, z);
    }

    public boolean launchContentItem(DBContentItem dBContentItem, FragmentActivity fragmentActivity, Intent intent, int i, String str, boolean z) {
        Intent intent2;
        String str2;
        boolean z2;
        Intent launchIntentForPackage;
        DBContentItem dBContentItem2 = dBContentItem;
        final FragmentActivity fragmentActivity2 = fragmentActivity;
        Intent intent3 = intent;
        int i2 = i;
        String str3 = str;
        int i3 = 0;
        if (fragmentActivity2 == null) {
            return false;
        }
        CrashLogger instance = CrashLogger.getInstance();
        instance.leaveBreadcrumb("launch item: " + dBContentItem.getIdentifier());
        String str4 = null;
        if (dBContentItem.getIsUpdating()) {
            new AlertDialog.Builder(fragmentActivity2).setTitle(R.string.oops).setMessage(R.string.content_update_in_progress).setNegativeButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
            return false;
        } else if ((dBContentItem.getRequiresUpdate() == null || !dBContentItem.getRequiresUpdate().booleanValue()) && (dBContentItem.getResourcesRequireUpdate() == null || !dBContentItem.getResourcesRequireUpdate().booleanValue())) {
            final DBRestriction contentItemRestriction = DBContentItem.getContentItemRestriction(dBContentItem);
            if (contentItemRestriction != null) {
                AlertDialog.Builder message = new AlertDialog.Builder(fragmentActivity2).setTitle(contentItemRestriction.getAlertTitle()).setMessage(contentItemRestriction.getAlertMessage());
                if (contentItemRestriction.getAlternateUrl() == null || contentItemRestriction.getAlternateUrl().isEmpty()) {
                    message.setNegativeButton(R.string.dismiss, (DialogInterface.OnClickListener) null);
                } else {
                    message.setPositiveButton(R.string.text_continue, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            fragmentActivity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(contentItemRestriction.getAlternateUrl())));
                        }
                    }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
                }
                message.create().show();
                return false;
            } else if (ContentDataManager.getInstance().findAndMarkMissingFilesForContentItem(dBContentItem2)) {
                new AlertDialog.Builder(fragmentActivity2).setTitle(R.string.oops).setMessage(R.string.content_item_needs_update).setPositiveButton(R.string.update_now, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContentDataManager.getInstance().fetchItemsThatNeedUpdating();
                    }
                }).setNegativeButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
                return false;
            } else {
                if (dBContentItem.getType().equals(ContentItem.CONTENT_ITEM_TYPE_CALCULATOR)) {
                    if (dBContentItem.getIdentifier().equalsIgnoreCase("calculator_235")) {
                        intent2 = new Intent(fragmentActivity2, BaxterRxCalculatorActivity.class);
                    } else {
                        intent2 = new Intent(fragmentActivity2, CalculatorActivity.class);
                    }
                } else if (dBContentItem.getType().equals(ContentItem.CONTENT_ITEM_TYPE_DEFINITION)) {
                    intent2 = new Intent(fragmentActivity2, DefinitionActivity.class);
                } else if (dBContentItem.getType().equals(ContentItem.CONTENT_ITEM_TYPE_REFERENCE_BOOK)) {
                    intent2 = new Intent(fragmentActivity2, ReferenceBookActivity.class);
                } else {
                    if (dBContentItem.getType().equals(ContentItem.CONTENT_ITEM_TYPE_FILE_SOURCE)) {
                        DBFileSource fileSource = dBContentItem.getFileSource();
                        String lowerCase = fileSource.getSource() == null ? null : fileSource.getSource().toLowerCase();
                        if (lowerCase != null) {
                            if (lowerCase.contains("qxmd.com/r/")) {
                                lowerCase = lowerCase.replace("www.", "").replaceAll("qxmd.com/r/", "read.qxmd.com/");
                            }
                            if (lowerCase.contains("read.qxmd.com")) {
                                Uri parse = Uri.parse(lowerCase);
                                Iterator<ResolveInfo> it = fragmentActivity.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", parse), 65536).iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        if (it.next().activityInfo.packageName.equals("com.qxmd.readbyqxmd")) {
                                            z2 = true;
                                            break;
                                        }
                                    } else {
                                        z2 = false;
                                        break;
                                    }
                                }
                                if (z2 && (launchIntentForPackage = fragmentActivity.getPackageManager().getLaunchIntentForPackage("com.qxmd.readbyqxmd")) != null) {
                                    ContentItem contentItem = new ContentItem(dBContentItem2);
                                    EventsManager.getInstance().trackContentItemUsed(contentItem.identifier, contentItem.promotionToUse);
                                    DBUser dbUser = UserManager.getInstance().getDbUser();
                                    if (!UserManager.getInstance().hasFinishedUpgradingToUniversalAccounts()) {
                                        dbUser.markContentItemUsed(dBContentItem2, new Date().getTime(), fragmentActivity2);
                                    } else if (InternetConnectivityManager.getInstance().isConnectedToInternet() && ((ContentDataManager.getInstance().accountRefreshErrors == null || ContentDataManager.getInstance().accountRefreshErrors.isEmpty()) && !ContentDataManager.getInstance().shouldRefresh())) {
                                        ContentDataManager.getInstance().setRecentlyUsed(dBContentItem.getIdentifier(), new Date(), TASK_ID_SET_RECENT);
                                    }
                                    launchIntentForPackage.setAction("android.intent.action.CALL");
                                    launchIntentForPackage.setData(parse);
                                    fragmentActivity2.startActivity(launchIntentForPackage);
                                    return true;
                                }
                            }
                        }
                        FileSource.FileSourceType fileSourceType = FileSource.getFileSourceType(fileSource.getType());
                        if (lowerCase != null && !lowerCase.endsWith(".pdf") && (fileSourceType == FileSource.FileSourceType.PDF_EXT || fileSourceType == FileSource.FileSourceType.PDF_INT)) {
                            fileSourceType = FileSource.FileSourceType.HTML_EXT_BROWSER;
                        } else if (lowerCase != null && !lowerCase.endsWith(".html") && (fileSourceType == FileSource.FileSourceType.HTML_EXT || fileSourceType == FileSource.FileSourceType.HTML_INT)) {
                            fileSourceType = FileSource.FileSourceType.HTML_EXT_BROWSER;
                        }
                        int i4 = AnonymousClass4.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$filesource$FileSource$FileSourceType[fileSourceType.ordinal()];
                        if (i4 == 1) {
                            intent2 = new Intent(fragmentActivity2, FileSourceHtmlActivity.class);
                        } else if (i4 == 2) {
                            str4 = fileSource.getSource();
                            intent2 = null;
                        } else if (i4 == 3) {
                            intent2 = new Intent(fragmentActivity2, FileSourceHtmlActivity.class);
                        } else if (i4 == 4) {
                            intent2 = new Intent(fragmentActivity2, PdfViewerActivity.class);
                        } else if (i4 == 5) {
                            intent2 = new Intent(fragmentActivity2, PdfViewerActivity.class);
                        }
                        str4 = null;
                    }
                    intent2 = null;
                    str4 = null;
                }
                if (intent2 == null && str4 == null) {
                    return false;
                }
                ContentItem contentItem2 = new ContentItem(dBContentItem2);
                EventsManager.getInstance().trackContentItemUsed(contentItem2.identifier, contentItem2.promotionToUse);
                DBUser dbUser2 = UserManager.getInstance().getDbUser();
                if (!UserManager.getInstance().hasFinishedUpgradingToUniversalAccounts()) {
                    dbUser2.markContentItemUsed(dBContentItem2, new Date().getTime(), fragmentActivity2);
                } else if (InternetConnectivityManager.getInstance().isConnectedToInternet() && ((ContentDataManager.getInstance().accountRefreshErrors == null || ContentDataManager.getInstance().accountRefreshErrors.isEmpty()) && !ContentDataManager.getInstance().shouldRefresh())) {
                    ContentDataManager.getInstance().setRecentlyUsed(dBContentItem.getIdentifier(), new Date(), TASK_ID_SET_RECENT);
                }
                if (str4 != null) {
                    AnalyticsHandler.getInstance().trackPageView(fragmentActivity2, dBContentItem.getName());
                    ArrayList<ResolveInfo> customTabsPackages = getCustomTabsPackages(fragmentActivity);
                    if (customTabsPackages != null) {
                        String str5 = null;
                        for (ResolveInfo next : customTabsPackages) {
                            String str6 = next.activityInfo.packageName;
                            if (str6 != null && next.preferredOrder > i3) {
                                i3 = next.preferredOrder;
                                str5 = str6;
                            }
                        }
                        if (str5 == null) {
                            Iterator<ResolveInfo> it2 = customTabsPackages.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                str2 = it2.next().activityInfo.packageName;
                                if (str2 != null && (str2.contains("chrome") || str2.contains("firefox"))) {
                                    break;
                                }
                                str5 = str2;
                            }
                        }
                        str2 = str5;
                    } else {
                        str2 = null;
                    }
                    if (str2 != null) {
                        try {
                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                            builder.setToolbarColor(fragmentActivity.getResources().getColor(R.color.action_bar_color_default));
                            CustomTabsIntent build = builder.build();
                            build.intent.setPackage(str2);
                            build.launchUrl(fragmentActivity2, Uri.parse(str4));
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                    intent2 = new Intent(fragmentActivity2, FileSourceHtmlActivity.class);
                }
                if (str3 != null) {
                    contentItem2.trackerId = str3;
                }
                contentItem2.initializeContentItem(UserManager.getInstance().getDbUser(), fragmentActivity2);
                intent2.putExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM, contentItem2);
                if (intent3 != null) {
                    intent2.putExtras(intent3);
                }
                if (z) {
                    intent2.putExtra(QxMDActivity.KEY_MODAL_ANIM_STYLE, true);
                }
                if (i2 != -1) {
                    fragmentActivity2.startActivityForResult(intent2, i2);
                } else {
                    LaunchQxCallback launchQxCallback = calcCallback;
                    if (launchQxCallback != null) {
                        launchQxCallback.onQxItemClicked(dBContentItem2, intent2.getExtras());
                    } else {
                        fragmentActivity2.startActivity(intent2);
                    }
                }
                if (z) {
                    fragmentActivity2.overridePendingTransition(R.anim.open_enter_modal, R.anim.open_exit_modal);
                }
                return true;
            }
        } else {
            new AlertDialog.Builder(fragmentActivity2).setTitle(R.string.oops).setMessage(R.string.content_item_needs_update).setPositiveButton(R.string.update_now, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ContentDataManager.getInstance().fetchItemsThatNeedUpdating();
                }
            }).setNegativeButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
            return false;
        }
    }

    /* renamed from: com.wbmd.qxcalculator.managers.ContentItemLaunchManager$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$contentItems$filesource$FileSource$FileSourceType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType[] r0 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$model$contentItems$filesource$FileSource$FileSourceType = r0
                com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r1 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.HTML_EXT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$filesource$FileSource$FileSourceType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r1 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.HTML_EXT_BROWSER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$filesource$FileSource$FileSourceType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r1 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.HTML_INT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$filesource$FileSource$FileSourceType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r1 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.PDF_EXT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$filesource$FileSource$FileSourceType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r1 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.PDF_INT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.managers.ContentItemLaunchManager.AnonymousClass4.<clinit>():void");
        }
    }

    public boolean launchExtraSectionItem(ExtraSectionItem extraSectionItem, String str, String str2, String str3, String str4, FragmentActivity fragmentActivity, Intent intent, int i) {
        Intent intent2;
        ExtraSectionItem extraSectionItem2 = extraSectionItem;
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        FragmentActivity fragmentActivity2 = fragmentActivity;
        Intent intent3 = intent;
        int i2 = i;
        if (extraSectionItem2.source.startsWith("$")) {
            DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(extraSectionItem2.source.substring(1));
            if (contentItemForIdentifier != null) {
                launchContentItem(contentItemForIdentifier, fragmentActivity, intent, i, false);
                return true;
            }
            CrashLogger.getInstance().logHandledException(new Exception("extra section item missing info " + extraSectionItem2.source));
            return false;
        }
        String str9 = extraSectionItem2.source;
        if (extraSectionItem2.source.toLowerCase().contains(ContentParser.PDF)) {
            intent2 = new Intent(fragmentActivity2, PdfViewerActivity.class);
            if (!str9.toLowerCase().startsWith("http")) {
                str9 = FileHelper.getInstance().getResourceFolderPathForContentItemIdentifier(str) + str9;
            }
            intent2.putExtra(PdfViewerActivity.KEY_BUNDLE_URL, str9);
        } else {
            intent2 = new Intent(fragmentActivity2, FileSourceHtmlActivity.class);
            if (!str9.toLowerCase().startsWith("http")) {
                str9 = "file://" + FileHelper.getInstance().getResourceFolderPathForContentItemIdentifier(str) + str9;
            }
            intent2.putExtra(FileSourceHtmlActivity.KEY_INTENT_URL, str9);
        }
        if (str8 != null) {
            intent2.putExtra(QxMDActivity.KEY_ACTIVITY_TITLE, str8);
        }
        if (str6 != null) {
            intent2.putExtra(QxMDActivity.KEY_TRACKER_ID, str2);
        }
        if (str7 != null) {
            intent2.putExtra(QxMDActivity.KEY_TRACKER_PAGE_NAME, str7);
        }
        if (intent3 != null) {
            intent2.putExtras(intent3);
        }
        if (i2 != -1) {
            fragmentActivity2.startActivityForResult(intent2, i2);
        } else {
            fragmentActivity2.startActivity(intent2);
        }
        return true;
    }

    public static ArrayList<ResolveInfo> getCustomTabsPackages(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("https://www.qxmd.com")), 0);
        ArrayList<ResolveInfo> arrayList = new ArrayList<>();
        for (ResolveInfo next : queryIntentActivities) {
            Intent intent = new Intent();
            intent.setAction("android.support.customtabs.action.CustomTabsService");
            intent.setPackage(next.activityInfo.packageName);
            if (packageManager.resolveService(intent, 0) != null) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
