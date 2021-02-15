package com.medscape.android.updater;

import android.content.Context;
import android.content.SharedPreferences;
import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.activity.calc.model.CalcsContract;
import com.medscape.android.db.FeedDetail;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.task.CheckVerXMLTask;
import com.medscape.android.task.DownloadUpdateTask;
import com.medscape.android.task.FetchPListTask;
import com.medscape.android.updater.model.Update;
import com.medscape.android.updater.model.UpdateProcess;
import com.medscape.android.util.Util;
import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.EnvironmentManager;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateManager {
    public static final int AD_BLOCKER_MESSAGE = 19;
    public static String APP_UPDATE_URL = "market://details?id=com.medscape.android";
    public static final int CLINICAL_REFERENCE = 2;
    public static final int CR_UPDATE = 2;
    public static final int DEMO_CONTENT = 20;
    public static final int DRUG = 1;
    public static final int DRUG_UPDATE = 1;
    public static final int ERROR_DOWNLOADING_VER_XML = 11;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    public static String MANDATORY_DATA_MESSAGE = "mandatorydatamessage";
    public static String OPTIONAL_DATA_MESSAGE = "optionaldatamessage";
    public static final String PASSWORD = "WE3M0HE41TH";
    public static final String SETTINGS_PREFS = "settings";
    public static String SINGLE_DATA_DEPENDENT_VERISON_CALC = "singledatadependentversioncalc";
    public static String SINGLE_DATA_DEPENDENT_VERISON_DRUG = "singledatadependentversiondrug";
    public static String SINGLE_DATA_DEPENDENT_VERISON_REF = "singledatadependentversionref";
    public static String SINGLE_DATA_DEPENDENT_VERSION_AD_SEGVARS = "singledatadependentversionadssegvars";
    public static String SINGLE_DATA_UPDATE_VERSION_CALC = "singledataupdateversioncalc";
    public static String SINGLE_DATA_UPDATE_VERSION_DRUG = "singledataupdateversiondrug";
    public static String SINGLE_DATA_UPDATE_VERSION_REF = "singledataupdateversionref";
    public static String SINGLE_DATA_URL_AD_SEGVARS = "singledataurladsegvars";
    public static String SINGLE_DATA_URL_CALC = "singledataurlcalc";
    public static String SINGLE_DATA_URL_DRUG = "singledataurldrug";
    public static String SINGLE_DATA_URL_REF = "singledataurlref";
    public static final int START_DATA_UPDATE_FLOW = 4;
    public static final String VERSION_PREF = "version";
    public static boolean isNotEncrypted = false;
    /* access modifiers changed from: private */
    public int crClientVersion;
    public double crServerVersion;
    protected int delta = -1;
    public boolean isVersionCheck = false;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public UpdateProcess mCurrentUpdateProcess;
    /* access modifiers changed from: private */
    public OnUpdateListener mListener;
    public double mServerVersion;
    /* access modifiers changed from: private */
    public double minServerDataVersion;

    public static UpdateManager createFrom(UpdateManager updateManager) {
        UpdateManager updateManager2 = new UpdateManager(updateManager.mContext);
        updateManager2.mCurrentUpdateProcess = updateManager.mCurrentUpdateProcess;
        updateManager2.mServerVersion = updateManager.mServerVersion;
        updateManager2.crServerVersion = updateManager.crServerVersion;
        updateManager2.crClientVersion = updateManager.crClientVersion;
        updateManager2.delta = updateManager.delta;
        return updateManager2;
    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.mListener = onUpdateListener;
    }

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    public void checkVerXml(final int i) {
        CheckVerXMLTask checkVerXMLTask = new CheckVerXMLTask(new CheckVerXMLTask.CheckVerXMLListener() {
            public void onContentsDownloaded(UpdateProcess updateProcess) {
                if (updateProcess != null) {
                    try {
                        UpdateProcess unused = UpdateManager.this.mCurrentUpdateProcess = updateProcess;
                        float f = UpdateManager.this.mContext.getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", 0.0f);
                        if (updateProcess.getData() != null) {
                            UpdateManager.this.mServerVersion = updateProcess.getData().getVersion();
                            double unused2 = UpdateManager.this.minServerDataVersion = updateProcess.getData().getMinVersion();
                            Settings.singleton(UpdateManager.this.mContext).saveSetting(UpdateManager.OPTIONAL_DATA_MESSAGE, updateProcess.getData().getOptionalMessage());
                            Settings.singleton(UpdateManager.this.mContext).saveSetting(UpdateManager.MANDATORY_DATA_MESSAGE, updateProcess.getData().getMandatoryMessage());
                        }
                        Settings.singleton(UpdateManager.this.mContext).saveSetting(Constants.PREF_OPTIONAL_SERVER_DATA_VERSION, Double.toString(UpdateManager.this.mServerVersion));
                        Settings.singleton(UpdateManager.this.mContext).saveSetting(Constants.PREF_MIN_SERVER_DATA_VERSION, Double.toString(UpdateManager.this.minServerDataVersion));
                        if (updateProcess.getSingleDrugData() != null) {
                            UpdateProcess.SilentUpdateData singleDrugData = updateProcess.getSingleDrugData();
                            Settings singleton = Settings.singleton(UpdateManager.this.mContext);
                            String str = UpdateManager.SINGLE_DATA_DEPENDENT_VERISON_DRUG;
                            singleton.saveSetting(str, singleDrugData.getDataVersion() + "");
                            Settings singleton2 = Settings.singleton(UpdateManager.this.mContext);
                            String str2 = UpdateManager.SINGLE_DATA_UPDATE_VERSION_DRUG;
                            singleton2.saveSetting(str2, singleDrugData.getUpdate() + "");
                            Settings.singleton(UpdateManager.this.mContext).saveSetting(UpdateManager.SINGLE_DATA_URL_DRUG, singleDrugData.getUrl());
                        }
                        if (updateProcess.getSingleAdSegvarData() != null) {
                            UpdateProcess.AdSegvarsData singleAdSegvarData = updateProcess.getSingleAdSegvarData();
                            Settings singleton3 = Settings.singleton(UpdateManager.this.mContext);
                            String str3 = UpdateManager.SINGLE_DATA_DEPENDENT_VERSION_AD_SEGVARS;
                            singleton3.saveSetting(str3, singleAdSegvarData.getVersion() + "");
                            Settings.singleton(UpdateManager.this.mContext).saveSetting(UpdateManager.SINGLE_DATA_URL_AD_SEGVARS, singleAdSegvarData.getUrl());
                        }
                        if (i != 555) {
                            UpdateManager.this.notifyOfAppUpgradeIfNecessary(1, -1, updateProcess);
                        } else if (((double) f) < UpdateManager.this.mServerVersion) {
                            UpdateManager.this.mListener.onUpdateAvailable(1, (List<Update>) null, 0);
                        } else {
                            UpdateManager.this.mListener.onUpdateNotAvailable(1);
                        }
                    } catch (Exception e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                        if (UpdateManager.this.mListener != null) {
                            UpdateManager.this.mListener.onNetworkError(11);
                        }
                    }
                }
            }

            public void setonError(int i) {
                if (UpdateManager.this.mListener != null) {
                    UpdateManager.this.mListener.onNetworkError(11);
                }
            }
        });
        String environmentWithDefault = new EnvironmentManager().getEnvironmentWithDefault(this.mContext, EnvironmentConstants.MODULE_CONTENT);
        checkVerXMLTask.execute(new String[]{UpdateUrls.getUrlForEnvironment(environmentWithDefault, UpdateUrls.CHECK_VERSION_URL) + UpdateUrls.getUrlForEnvironment(environmentWithDefault, UpdateUrls.VERSION_FILE_XML)});
    }

    public void getDrugPList(String str) {
        getDrugPList(str, true);
    }

    public void getDrugPList(String str, final boolean z) {
        FetchPListTask fetchPListTask = new FetchPListTask();
        fetchPListTask.setGetURLContentsListener(new FetchPListTask.FetchPListListener() {
            public void onContentsDownloaded(String str) {
                Update update;
                ArrayList arrayList = new ArrayList();
                try {
                    NSArray nSArray = (NSArray) ((NSDictionary) PropertyListParser.parse(str.getBytes("UTF-8"))).getHashMap().get("Actions");
                    int count = nSArray.count();
                    for (int i = 0; i < count; i++) {
                        NSDictionary nSDictionary = (NSDictionary) nSArray.objectAtIndex(i);
                        String obj = nSDictionary.objectForKey(CalcsContract.TYPE).toJavaObject().toString();
                        if (!obj.equalsIgnoreCase("purgeClinical")) {
                            if (!obj.equals("purgeSystem")) {
                                NSObject objectForKey = nSDictionary.objectForKey("UE");
                                UpdateManager.isNotEncrypted = objectForKey != null;
                                update = new Update(nSDictionary.objectForKey(CalcsContract.TYPE).toJavaObject().toString(), (objectForKey != null ? objectForKey.toJavaObject() : nSDictionary.objectForKey(FeedDetail.F_URL).toJavaObject()).toString(), ((Boolean) nSDictionary.objectForKey("IsCompressed").toJavaObject()).booleanValue());
                                arrayList.add(update);
                            }
                        }
                        update = new Update(obj, (String) null, false);
                        arrayList.add(update);
                    }
                } catch (Exception e) {
                    e.equals("");
                }
                if (z || UpdateManager.this.mListener == null) {
                    if (z) {
                        UpdateManager.this.getReferencePList(arrayList, true, true);
                    }
                } else if (arrayList.size() > 0) {
                    UpdateManager.this.mListener.onUpdateAvailable(1, arrayList, -1);
                } else {
                    UpdateManager.this.mListener.onUpdateNotAvailable(1);
                }
            }

            public void setContentsMaxProgress(int i) {
                UpdateManager.this.mListener.setMaxProgress(i);
            }

            public void showContentsDownloadedProgress(int i) {
                UpdateManager.this.mListener.onProgressUpdate(i);
            }

            public void setonError(int i) {
                if (UpdateManager.this.mListener != null) {
                    UpdateManager.this.mListener.onNetworkError(i);
                }
            }
        });
        fetchPListTask.execute(new String[]{str});
    }

    public void downloadAndInstallUpdate(int i, Update update, String str, Boolean bool) {
        if (update.getUrl() == null || update.getUrl().equals("")) {
            OnUpdateListener onUpdateListener = this.mListener;
            if (onUpdateListener != null) {
                onUpdateListener.onUpdateComplete(1, update);
                return;
            }
            return;
        }
        update.setPasswordProtected(bool.booleanValue());
        AsyncTaskHelper.execute(EXECUTOR_SERVICE, new DownloadUpdateTask(this.mListener, str), update);
    }

    public void updateComplete(int i, Update update) {
        OnUpdateListener onUpdateListener = this.mListener;
        if (onUpdateListener == null) {
            return;
        }
        if (update != null) {
            onUpdateListener.onUpdateComplete(i, update);
        } else {
            onUpdateListener.onNetworkError(9);
        }
    }

    public void markAllUpdatesCompleted(int i) {
        if (i == 2) {
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, String.valueOf(this.crServerVersion));
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_FAIL, AppEventsConstants.EVENT_PARAM_VALUE_NO);
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_PLIST_TEXT, "");
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_TOTAL_FILES, AppEventsConstants.EVENT_PARAM_VALUE_NO);
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
            return;
        }
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(SETTINGS_PREFS, 0);
        sharedPreferences.getFloat("version", -1.0f);
        sharedPreferences.edit().putFloat("version", (float) this.mServerVersion).commit();
        Settings.singleton(this.mContext).saveSetting(Constants.PREF_SINGLE_DRUG_UPDATE_CLIENT_VERISON, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_CALC_UPDATE_CLIENT_VERISON, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_REF_UPDATE_CLIENT_VERISON, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Settings.singleton(this.mContext).saveSetting(Constants.PREF_OPTIONAL_DATA_UPDATE_TIME, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Settings.singleton(this.mContext).saveSetting(Constants.PREF_DRUG_INSTALLTION_FAIL, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Settings.singleton(this.mContext).saveSetting(Constants.PREF_DRUG_INSTALLTION_PLIST_TEXT, "");
        Settings.singleton(this.mContext).saveSetting(Constants.PREF_DRUG_INSTALLTION_TOTAL_FILES, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.add(2, 3);
        Settings singleton = Settings.singleton(this.mContext);
        singleton.saveSetting(Constants.PREF_OPTIONAL_DATA_UPDATE_TIME, "" + instance.getTimeInMillis());
        Util.setLastUpdateTime(this.mContext);
    }

    public void getUpdatePList2(int i, String str) {
        UpdateProcess updateProcess = this.mCurrentUpdateProcess;
        if (updateProcess != null && updateProcess.getData() != null) {
            this.mServerVersion = this.mCurrentUpdateProcess.getData().getVersion();
            ArrayList arrayList = new ArrayList();
            Update update = new Update();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.mCurrentUpdateProcess.getData().getMajorUpdateTitle());
            stringBuffer.append(this.mCurrentUpdateProcess.getData().getDescription());
            update.setType(stringBuffer.toString());
            update.setmUpdateTitle(this.mCurrentUpdateProcess.getData().getMajorUpdateTitle());
            update.setmUpdateMsg(this.mCurrentUpdateProcess.getData().getDescription());
            arrayList.add(update);
            Update update2 = new Update();
            update2.setType(this.mCurrentUpdateProcess.getData().getUrl());
            arrayList.add(update2);
            Trace.d("smozzz", "mListener null check");
            OnUpdateListener onUpdateListener = this.mListener;
            if (onUpdateListener != null) {
                onUpdateListener.onUpdateAvailable(i, arrayList, -1);
            }
        }
    }

    public void getReferencePList() {
        getReferencePList((ArrayList<Update>) null, false, false);
    }

    public void getReferencePList(final ArrayList<Update> arrayList, final boolean z, final boolean z2) {
        new FetchPListTask(new FetchPListTask.FetchPListListener() {
            public void setContentsMaxProgress(int i) {
            }

            public void showContentsDownloadedProgress(int i) {
            }

            /* JADX WARNING: Removed duplicated region for block: B:70:0x0231 A[Catch:{ Exception -> 0x023d }] */
            /* JADX WARNING: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onContentsDownloaded(java.lang.String r20) {
                /*
                    r19 = this;
                    r1 = r19
                    java.lang.String r0 = ""
                    java.util.ArrayList r2 = r3
                    if (r2 != 0) goto L_0x000d
                    java.util.ArrayList r2 = new java.util.ArrayList
                    r2.<init>()
                L_0x000d:
                    java.lang.String r3 = "UTF-8"
                    r4 = r20
                    byte[] r3 = r4.getBytes(r3)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r3 = com.dd.plist.PropertyListParser.parse((byte[]) r3)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSDictionary r3 = (com.dd.plist.NSDictionary) r3     // Catch:{ Exception -> 0x023d }
                    java.util.HashMap r3 = r3.getHashMap()     // Catch:{ Exception -> 0x023d }
                    int r4 = r2.size()     // Catch:{ Exception -> 0x023d }
                    r5 = 1
                    if (r4 <= 0) goto L_0x003b
                    int r4 = r2.size()     // Catch:{ Exception -> 0x023d }
                    int r4 = r4 - r5
                    java.lang.Object r4 = r2.get(r4)     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.updater.model.Update r4 = (com.medscape.android.updater.model.Update) r4     // Catch:{ Exception -> 0x023d }
                    java.lang.String r4 = r4.getType()     // Catch:{ Exception -> 0x023d }
                    java.lang.String r6 = "purge"
                    boolean r4 = r4.contains(r6)     // Catch:{ Exception -> 0x023d }
                L_0x003b:
                    boolean r4 = r1.isNewerVersionAvailableForClinicalReference(r3)     // Catch:{ Exception -> 0x023d }
                    r6 = 2
                    if (r4 != 0) goto L_0x0053
                    int r4 = r2.size()     // Catch:{ Exception -> 0x023d }
                    if (r4 != 0) goto L_0x0053
                    com.medscape.android.updater.UpdateManager r0 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.updater.OnUpdateListener r0 = r0.mListener     // Catch:{ Exception -> 0x023d }
                    r0.onUpdateNotAvailable(r6)     // Catch:{ Exception -> 0x023d }
                    goto L_0x0241
                L_0x0053:
                    com.medscape.android.updater.UpdateManager r4 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    int r4 = r4.crClientVersion     // Catch:{ Exception -> 0x023d }
                    java.lang.String r8 = "purgeClinical"
                    r9 = 3
                    java.lang.String r10 = "IsCompressed"
                    java.lang.String r11 = "URL"
                    java.lang.String r12 = "UE"
                    java.lang.String r13 = "Type"
                    if (r4 <= 0) goto L_0x018b
                    com.medscape.android.updater.UpdateManager r4 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    int r4 = r4.delta     // Catch:{ Exception -> 0x023d }
                    if (r4 <= 0) goto L_0x018b
                    com.medscape.android.updater.UpdateManager r4 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    int r4 = r4.delta     // Catch:{ Exception -> 0x023d }
                    if (r4 > r9) goto L_0x018b
                    java.lang.String r4 = "Deltas"
                    java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSDictionary r3 = (com.dd.plist.NSDictionary) r3     // Catch:{ Exception -> 0x023d }
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x023d }
                    r4.<init>()     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.updater.UpdateManager r15 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    int r15 = r15.crClientVersion     // Catch:{ Exception -> 0x023d }
                    r4.append(r15)     // Catch:{ Exception -> 0x023d }
                    r4.append(r0)     // Catch:{ Exception -> 0x023d }
                    java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r4 = r3.objectForKey(r4)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSDictionary r4 = (com.dd.plist.NSDictionary) r4     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSDictionary r4 = (com.dd.plist.NSDictionary) r4     // Catch:{ Exception -> 0x023d }
                    java.lang.Class r4 = r19.getClass()     // Catch:{ Exception -> 0x023d }
                    java.lang.String r4 = r4.getName()     // Catch:{ Exception -> 0x023d }
                    java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x023d }
                    r15.<init>()     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.updater.UpdateManager r5 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    int r5 = r5.crClientVersion     // Catch:{ Exception -> 0x023d }
                    r15.append(r5)     // Catch:{ Exception -> 0x023d }
                    r15.append(r0)     // Catch:{ Exception -> 0x023d }
                    java.lang.String r5 = r15.toString()     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r5 = r3.objectForKey(r5)     // Catch:{ Exception -> 0x023d }
                    java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x023d }
                    com.wbmd.wbmdcommons.logging.Trace.d(r4, r5)     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.updater.UpdateManager r4 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    int r4 = r4.crClientVersion     // Catch:{ Exception -> 0x023d }
                L_0x00c5:
                    double r6 = (double) r4     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.updater.UpdateManager r5 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    double r14 = r5.crServerVersion     // Catch:{ Exception -> 0x023d }
                    int r5 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
                    if (r5 >= 0) goto L_0x017f
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x023d }
                    r5.<init>()     // Catch:{ Exception -> 0x023d }
                    r5.append(r4)     // Catch:{ Exception -> 0x023d }
                    r5.append(r0)     // Catch:{ Exception -> 0x023d }
                    java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r5 = r3.objectForKey(r5)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSDictionary r5 = (com.dd.plist.NSDictionary) r5     // Catch:{ Exception -> 0x023d }
                    java.lang.String r6 = "Parts"
                    com.dd.plist.NSObject r5 = r5.objectForKey(r6)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSArray r5 = (com.dd.plist.NSArray) r5     // Catch:{ Exception -> 0x023d }
                    int r6 = r5.count()     // Catch:{ Exception -> 0x023d }
                    r7 = 0
                L_0x00f0:
                    if (r7 >= r6) goto L_0x0178
                    com.dd.plist.NSObject r14 = r5.objectAtIndex(r7)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSDictionary r14 = (com.dd.plist.NSDictionary) r14     // Catch:{ Exception -> 0x023d }
                    boolean r15 = r14.containsKey((java.lang.String) r12)     // Catch:{ Exception -> 0x023d }
                    if (r15 == 0) goto L_0x0141
                    com.dd.plist.NSObject r15 = r14.objectForKey(r12)     // Catch:{ Exception -> 0x023d }
                    if (r15 == 0) goto L_0x0107
                    r16 = 1
                    goto L_0x0109
                L_0x0107:
                    r16 = 0
                L_0x0109:
                    com.medscape.android.updater.UpdateManager.isNotEncrypted = r16     // Catch:{ Exception -> 0x023d }
                    if (r15 == 0) goto L_0x0116
                    java.lang.Object r15 = r15.toJavaObject()     // Catch:{ Exception -> 0x023d }
                L_0x0111:
                    java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x023d }
                    goto L_0x011f
                L_0x0116:
                    com.dd.plist.NSObject r15 = r14.objectForKey(r11)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r15 = r15.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    goto L_0x0111
                L_0x011f:
                    com.medscape.android.updater.model.Update r9 = new com.medscape.android.updater.model.Update     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r17 = r14.objectForKey(r13)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r17 = r17.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    r18 = r0
                    java.lang.String r0 = r17.toString()     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r14 = r14.objectForKey(r10)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r14 = r14.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Exception -> 0x023d }
                    boolean r14 = r14.booleanValue()     // Catch:{ Exception -> 0x023d }
                    r9.<init>(r0, r15, r14)     // Catch:{ Exception -> 0x023d }
                    goto L_0x016e
                L_0x0141:
                    r18 = r0
                    com.medscape.android.updater.model.Update r9 = new com.medscape.android.updater.model.Update     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r0 = r14.objectForKey(r13)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r0 = r0.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r15 = r14.objectForKey(r11)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r15 = r15.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r14 = r14.objectForKey(r10)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r14 = r14.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Exception -> 0x023d }
                    boolean r14 = r14.booleanValue()     // Catch:{ Exception -> 0x023d }
                    r9.<init>(r0, r15, r14)     // Catch:{ Exception -> 0x023d }
                L_0x016e:
                    r2.add(r9)     // Catch:{ Exception -> 0x023d }
                    int r7 = r7 + 1
                    r0 = r18
                    r9 = 3
                    goto L_0x00f0
                L_0x0178:
                    r18 = r0
                    int r4 = r4 + 1
                    r9 = 3
                    goto L_0x00c5
                L_0x017f:
                    com.medscape.android.updater.model.Update r0 = new com.medscape.android.updater.model.Update     // Catch:{ Exception -> 0x023d }
                    r3 = 0
                    r4 = 0
                    r0.<init>(r8, r3, r4)     // Catch:{ Exception -> 0x023d }
                    r2.add(r0)     // Catch:{ Exception -> 0x023d }
                    goto L_0x020d
                L_0x018b:
                    boolean r0 = r4     // Catch:{ Exception -> 0x023d }
                    if (r0 != 0) goto L_0x020d
                    java.lang.String r0 = "Actions"
                    java.lang.Object r0 = r3.get(r0)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSArray r0 = (com.dd.plist.NSArray) r0     // Catch:{ Exception -> 0x023d }
                    int r3 = r0.count()     // Catch:{ Exception -> 0x023d }
                    r4 = 0
                L_0x019c:
                    if (r4 >= r3) goto L_0x020d
                    com.dd.plist.NSObject r5 = r0.objectAtIndex(r4)     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSDictionary r5 = (com.dd.plist.NSDictionary) r5     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r6 = r5.objectForKey(r13)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r6 = r6.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x023d }
                    boolean r7 = r6.equalsIgnoreCase(r8)     // Catch:{ Exception -> 0x023d }
                    if (r7 != 0) goto L_0x0200
                    java.lang.String r7 = "purgeSystem"
                    boolean r7 = r6.equals(r7)     // Catch:{ Exception -> 0x023d }
                    if (r7 == 0) goto L_0x01bf
                    goto L_0x0200
                L_0x01bf:
                    com.dd.plist.NSObject r6 = r5.objectForKey(r12)     // Catch:{ Exception -> 0x023d }
                    if (r6 == 0) goto L_0x01c7
                    r7 = 1
                    goto L_0x01c8
                L_0x01c7:
                    r7 = 0
                L_0x01c8:
                    com.medscape.android.updater.UpdateManager.isNotEncrypted = r7     // Catch:{ Exception -> 0x023d }
                    if (r6 == 0) goto L_0x01d5
                    java.lang.Object r6 = r6.toJavaObject()     // Catch:{ Exception -> 0x023d }
                L_0x01d0:
                    java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x023d }
                    goto L_0x01de
                L_0x01d5:
                    com.dd.plist.NSObject r6 = r5.objectForKey(r11)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r6 = r6.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    goto L_0x01d0
                L_0x01de:
                    com.medscape.android.updater.model.Update r7 = new com.medscape.android.updater.model.Update     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r9 = r5.objectForKey(r13)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r9 = r9.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x023d }
                    com.dd.plist.NSObject r5 = r5.objectForKey(r10)     // Catch:{ Exception -> 0x023d }
                    java.lang.Object r5 = r5.toJavaObject()     // Catch:{ Exception -> 0x023d }
                    java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ Exception -> 0x023d }
                    boolean r5 = r5.booleanValue()     // Catch:{ Exception -> 0x023d }
                    r7.<init>(r9, r6, r5)     // Catch:{ Exception -> 0x023d }
                    r5 = 0
                    r9 = 0
                    goto L_0x0207
                L_0x0200:
                    com.medscape.android.updater.model.Update r7 = new com.medscape.android.updater.model.Update     // Catch:{ Exception -> 0x023d }
                    r5 = 0
                    r9 = 0
                    r7.<init>(r6, r5, r9)     // Catch:{ Exception -> 0x023d }
                L_0x0207:
                    r2.add(r7)     // Catch:{ Exception -> 0x023d }
                    int r4 = r4 + 1
                    goto L_0x019c
                L_0x020d:
                    boolean r0 = r5     // Catch:{ Exception -> 0x023d }
                    if (r0 == 0) goto L_0x0229
                    com.medscape.android.updater.UpdateManager r0 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    int r0 = r0.delta     // Catch:{ Exception -> 0x023d }
                    r3 = 3
                    if (r0 <= r3) goto L_0x0229
                    com.medscape.android.updater.UpdateManager r0 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.Settings r0 = com.medscape.android.Settings.singleton(r0)     // Catch:{ Exception -> 0x023d }
                    java.lang.String r3 = "clinical_reference_version"
                    java.lang.String r4 = "0"
                    r0.saveSetting((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x023d }
                L_0x0229:
                    com.medscape.android.updater.UpdateManager r0 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.updater.OnUpdateListener r0 = r0.mListener     // Catch:{ Exception -> 0x023d }
                    if (r0 == 0) goto L_0x0241
                    com.medscape.android.updater.UpdateManager r0 = com.medscape.android.updater.UpdateManager.this     // Catch:{ Exception -> 0x023d }
                    com.medscape.android.updater.OnUpdateListener r0 = r0.mListener     // Catch:{ Exception -> 0x023d }
                    r3 = -1
                    r4 = 2
                    r0.onUpdateAvailable(r4, r2, r3)     // Catch:{ Exception -> 0x023d }
                    goto L_0x0241
                L_0x023d:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x0241:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.updater.UpdateManager.AnonymousClass3.onContentsDownloaded(java.lang.String):void");
            }

            private boolean isNewerVersionAvailableForClinicalReference(HashMap<String, NSObject> hashMap) {
                UpdateManager updateManager = UpdateManager.this;
                int unused = updateManager.crClientVersion = (int) Math.round(Double.parseDouble(Settings.singleton(updateManager.mContext).getSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO)));
                UpdateManager.this.crServerVersion = Double.valueOf((double) ((Integer) hashMap.get("MajorVersion").toJavaObject()).intValue()).doubleValue();
                String setting = Settings.singleton(UpdateManager.this.mContext).getSetting(Constants.PREF_CLINICAL_INSTALLTION_FAIL_VERSION, "");
                if (setting != null && !setting.equals("")) {
                    try {
                        if (UpdateManager.this.crServerVersion != Double.parseDouble(setting)) {
                            Settings.singleton(UpdateManager.this.mContext).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_FAIL, AppEventsConstants.EVENT_PARAM_VALUE_NO);
                            Settings.singleton(UpdateManager.this.mContext).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_PLIST_TEXT, "");
                            Settings.singleton(UpdateManager.this.mContext).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_TOTAL_FILES, AppEventsConstants.EVENT_PARAM_VALUE_NO);
                        }
                    } catch (Exception unused2) {
                    }
                }
                Settings singleton = Settings.singleton(UpdateManager.this.mContext);
                singleton.saveSetting(Constants.PREF_CLINICAL_INSTALLTION_FAIL_VERSION, UpdateManager.this.crServerVersion + "");
                if (((double) UpdateManager.this.crClientVersion) >= UpdateManager.this.crServerVersion) {
                    return false;
                }
                UpdateManager updateManager2 = UpdateManager.this;
                updateManager2.delta = (int) (updateManager2.crServerVersion - ((double) UpdateManager.this.crClientVersion));
                return true;
            }

            public void setonError(int i) {
                if (UpdateManager.this.mListener != null) {
                    UpdateManager.this.mListener.onNetworkError(i);
                }
            }
        }).execute(new String[]{UpdateUrls.getUrlForEnvironment(new EnvironmentManager().getEnvironmentWithDefault(this.mContext, EnvironmentConstants.MODULE_CONTENT), UpdateUrls.REFERENCEPLIST_URL)});
    }

    /* access modifiers changed from: private */
    public void notifyOfAppUpgradeIfNecessary(int i, int i2, UpdateProcess updateProcess) {
        ArrayList arrayList = new ArrayList();
        OnUpdateListener onUpdateListener = this.mListener;
        if (onUpdateListener != null) {
            onUpdateListener.onUpdateAvailable(i, arrayList, i2);
        }
    }

    public String getEnvironment() {
        return new EnvironmentManager().getEnvironmentWithDefault(this.mContext, EnvironmentConstants.MODULE_CONTENT);
    }

    public UpdateProcess getCurrentUpdateProcess() {
        return this.mCurrentUpdateProcess;
    }
}
