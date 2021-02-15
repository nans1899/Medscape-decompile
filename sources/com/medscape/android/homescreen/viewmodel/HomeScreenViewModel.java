package com.medscape.android.homescreen.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.formulary.FormularyDownloadService;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.FeedMaster;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.homescreen.interfaces.IDialogShowListener;
import com.medscape.android.homescreen.interfaces.IUpdateDownloadListener;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.myinvites.MyInvitationsManager;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.updater.SingleDataUpdateManager;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.Util;
import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.EnvironmentManager;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.managers.UserManager;
import java.io.File;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.anko.AsyncKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 b2\u00020\u00012\u00020\u0002:\u0001bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010;\u001a\u00020<J\b\u0010=\u001a\u00020<H\u0002J\u0006\u0010>\u001a\u00020?J\b\u0010@\u001a\u00020AH\u0002J\u0006\u0010B\u001a\u00020\u0007J\u0006\u0010C\u001a\u00020<J\u0006\u0010D\u001a\u00020<J\u0006\u0010E\u001a\u00020<J\b\u0010F\u001a\u00020%H\u0002J\b\u0010G\u001a\u00020<H\u0002J\b\u0010H\u001a\u00020%H\u0002J\u0006\u0010I\u001a\u00020%J\u0010\u0010J\u001a\u00020%2\u0006\u0010K\u001a\u00020AH\u0002J\b\u0010L\u001a\u00020%H\u0002J\u0006\u0010M\u001a\u00020%J\b\u0010N\u001a\u00020%H\u0002J\b\u0010O\u001a\u00020%H\u0002J\b\u0010P\u001a\u00020%H\u0002J\u000e\u0010Q\u001a\u00020<2\u0006\u0010R\u001a\u00020%J\u000e\u0010S\u001a\u00020<2\u0006\u0010T\u001a\u00020%J\b\u0010U\u001a\u00020<H\u0016J\b\u0010V\u001a\u00020<H\u0016J\b\u0010W\u001a\u00020<H\u0002J\b\u0010X\u001a\u00020<H\u0002J\b\u0010Y\u001a\u00020<H\u0002J\u000e\u0010Z\u001a\u00020<2\u0006\u0010[\u001a\u00020\\J\b\u0010]\u001a\u00020<H\u0002J\b\u0010^\u001a\u00020<H\u0002J\b\u0010_\u001a\u00020:H\u0002J\b\u0010`\u001a\u00020<H\u0002J\u0006\u0010a\u001a\u00020%R\u000e\u0010\u0006\u001a\u00020\u0007XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XD¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00070\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR&\u0010\r\u001a\n \n*\u0004\u0018\u00010\u000e0\u000e8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0007XD¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R\u001a\u0010+\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R\u001a\u0010-\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R\u001a\u0010/\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010&\"\u0004\b0\u0010(R\u000e\u00101\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000204X.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u000e\u00109\u001a\u00020:XD¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Lcom/medscape/android/homescreen/viewmodel/HomeScreenViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "Lcom/medscape/android/homescreen/interfaces/IUpdateDownloadListener;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "CALCULATE_DATA", "", "DATABASES", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "currentCalcDBVersion", "currentSpecialtyName", "getCurrentSpecialtyName", "setCurrentSpecialtyName", "(Ljava/lang/String;)V", "dialogListener", "Lcom/medscape/android/homescreen/interfaces/IDialogShowListener;", "getDialogListener", "()Lcom/medscape/android/homescreen/interfaces/IDialogShowListener;", "setDialogListener", "(Lcom/medscape/android/homescreen/interfaces/IDialogShowListener;)V", "downloadType", "", "getDownloadType", "()I", "setDownloadType", "(I)V", "isClinicalReferenceResume", "", "()Z", "setClinicalReferenceResume", "(Z)V", "isExpanded", "setExpanded", "isFreshLogin", "setFreshLogin", "isFromOnCreate", "setFromOnCreate", "isInActivityResultCallback", "setInActivityResultCallback", "isUpdateVersionChecked", "mIsActivityForeGround", "updateManager", "Lcom/medscape/android/updater/UpdateManager;", "getUpdateManager", "()Lcom/medscape/android/updater/UpdateManager;", "setUpdateManager", "(Lcom/medscape/android/updater/UpdateManager;)V", "wbmdUser", "", "dataUpdateFinish", "", "finishAllBackgroundUpdates", "getClientVersion", "", "getClinicalReferenceVersion", "", "getUpdateMessage", "handleNativeClinicalUpgrade", "handleNetworkError", "handleSpecialityChange", "hasUpgradedtoClinicalXML", "initializeCalc", "is90daysSinceLastUpdate", "isClinicalReferenceUpdateAvailable", "isClinicalRefererenceUpdateAvailable", "clinicalReferenceVersion", "isDatabaseCreated", "isFirstInstall", "isFormularyUpdateStarted", "isSingleUpdateStarted", "needMandatoryDataUpdate", "onPause", "isFinishing", "onResume", "isSessionValid", "onSingleDataUpdateSucess", "onUpdateDownloadComplete", "removeDBForHardCodedUser", "resetClientVersionCode", "setupDataUpdate", "setupUpdateManager", "activity", "Landroidx/fragment/app/FragmentActivity;", "startFormularyUpdates", "startSingleUpdates", "timeAtLastUpdate", "updateOldCalculators", "updateSpecialityChange", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HomeScreenViewModel.kt */
public final class HomeScreenViewModel extends AndroidViewModel implements IUpdateDownloadListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String RANDOM_FEED_ID = "randonmFeedID";
    private final String CALCULATE_DATA = "calculate_data";
    private final String DATABASES = "databases";
    private final String TAG = getClass().getSimpleName();
    private Context context;
    /* access modifiers changed from: private */
    public final String currentCalcDBVersion = "2.0";
    private String currentSpecialtyName;
    public IDialogShowListener dialogListener;
    private int downloadType = -1;
    private boolean isClinicalReferenceResume;
    private boolean isExpanded = true;
    private boolean isFreshLogin;
    private boolean isFromOnCreate;
    private boolean isInActivityResultCallback;
    private boolean isUpdateVersionChecked;
    private boolean mIsActivityForeGround;
    public UpdateManager updateManager;
    /* access modifiers changed from: private */
    public final long wbmdUser = 1234;

    private final boolean isClinicalRefererenceUpdateAvailable(double d) {
        return d > ((double) 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HomeScreenViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.context = application.getApplicationContext();
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context2) {
        this.context = context2;
    }

    public final boolean isExpanded() {
        return this.isExpanded;
    }

    public final void setExpanded(boolean z) {
        this.isExpanded = z;
    }

    public final IDialogShowListener getDialogListener() {
        IDialogShowListener iDialogShowListener = this.dialogListener;
        if (iDialogShowListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        }
        return iDialogShowListener;
    }

    public final void setDialogListener(IDialogShowListener iDialogShowListener) {
        Intrinsics.checkNotNullParameter(iDialogShowListener, "<set-?>");
        this.dialogListener = iDialogShowListener;
    }

    public final UpdateManager getUpdateManager() {
        UpdateManager updateManager2 = this.updateManager;
        if (updateManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateManager");
        }
        return updateManager2;
    }

    public final void setUpdateManager(UpdateManager updateManager2) {
        Intrinsics.checkNotNullParameter(updateManager2, "<set-?>");
        this.updateManager = updateManager2;
    }

    public final boolean isInActivityResultCallback() {
        return this.isInActivityResultCallback;
    }

    public final void setInActivityResultCallback(boolean z) {
        this.isInActivityResultCallback = z;
    }

    public final int getDownloadType() {
        return this.downloadType;
    }

    public final void setDownloadType(int i) {
        this.downloadType = i;
    }

    public final boolean isFreshLogin() {
        return this.isFreshLogin;
    }

    public final void setFreshLogin(boolean z) {
        this.isFreshLogin = z;
    }

    public final boolean isClinicalReferenceResume() {
        return this.isClinicalReferenceResume;
    }

    public final void setClinicalReferenceResume(boolean z) {
        this.isClinicalReferenceResume = z;
    }

    public final boolean isFromOnCreate() {
        return this.isFromOnCreate;
    }

    public final void setFromOnCreate(boolean z) {
        this.isFromOnCreate = z;
    }

    public final String getCurrentSpecialtyName() {
        return this.currentSpecialtyName;
    }

    public final void setCurrentSpecialtyName(String str) {
        this.currentSpecialtyName = str;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void onResume(boolean z) {
        this.mIsActivityForeGround = true;
        OmnitureManager.get().mSearchChannel = "other";
        if (!z) {
        }
    }

    public final void setupUpdateManager(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Context context2 = fragmentActivity;
        MyInvitationsManager.Companion.get(context2).fetchSpecificInvitations();
        AuthenticationManager instance = AuthenticationManager.getInstance(context2);
        Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.getInstance(activity)");
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new HomeScreenViewModel$setupUpdateManager$1(this, instance.getAuthStatus() != 3011, fragmentActivity, (Continuation) null), 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void setupDataUpdate() {
        double clientVersion = (double) getClientVersion();
        if (!Util.isOnline(this.context)) {
            if (clientVersion == -1.0d) {
                IDialogShowListener iDialogShowListener = this.dialogListener;
                if (iDialogShowListener == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
                }
                String string = this.context.getString(R.string.alert_dialog_install_network_connection_error_message);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…connection_error_message)");
                iDialogShowListener.showDialog(13, string);
            }
        } else if (clientVersion == -1.0d || !Util.isTestDriveTimeSet(this.context) || Util.isTestDriveTimeFinished(this.context)) {
            try {
                File file = new File(FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/" + ".nomedia");
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Settings.singleton(this.context).saveSetting(Constants.PREF_UPDATE_COMPLETE, "NO");
            MedscapeApplication medscapeApplication = MedscapeApplication.get();
            Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
            UpdateManager updateManager2 = medscapeApplication.getUpdateManager();
            Intrinsics.checkNotNullExpressionValue(updateManager2, "MedscapeApplication.get().updateManager");
            this.updateManager = updateManager2;
            if (!this.isUpdateVersionChecked) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new HomeScreenViewModel$setupDataUpdate$1(this, (Continuation) null), 2, (Object) null);
            }
            this.isUpdateVersionChecked = false;
        }
    }

    /* access modifiers changed from: private */
    public final void initializeCalc() {
        String str = SharedPreferenceProvider.get().get(Constants.PREF_CALC_DB_VERSION, "1.0");
        UserManager instance = UserManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "UserManager.getInstance()");
        if (instance.getActiveUserId() != null) {
            UserManager instance2 = UserManager.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance2, "UserManager.getInstance()");
            Long activeUserId = instance2.getActiveUserId();
            long j = this.wbmdUser;
            if (activeUserId != null && activeUserId.longValue() == j && StringsKt.equals(str, this.currentCalcDBVersion, true)) {
                UserManager.getInstance().loadDatabaseForCurrentUser();
                return;
            }
        }
        AsyncKt.doAsync$default(this, (Function1) null, new HomeScreenViewModel$initializeCalc$1(this), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void updateOldCalculators() {
        Iterator<CalcArticle> it = Util.getOldSavedCalculators(this.context).iterator();
        while (it.hasNext()) {
            Util.findMatchingQxCalcForMedscapeCalc(this.context, it.next(), (LaunchQxCallback) null);
        }
    }

    public final void handleNativeClinicalUpgrade() {
        if (isClinicalRefererenceUpdateAvailable(getClinicalReferenceVersion()) && !hasUpgradedtoClinicalXML()) {
            Settings.singleton(this.context).saveSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO);
            this.downloadType = 5;
            IDialogShowListener iDialogShowListener = this.dialogListener;
            if (iDialogShowListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
            }
            iDialogShowListener.showDialog(1, getUpdateMessage());
        }
    }

    private final double getClinicalReferenceVersion() {
        return Double.parseDouble(Settings.singleton(this.context).getSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    private final boolean hasUpgradedtoClinicalXML() {
        return new File(Constants.DIRECTORY_MAIN_CR).exists();
    }

    public final void dataUpdateFinish() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new HomeScreenViewModel$dataUpdateFinish$1(this, (Continuation) null), 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void startSingleUpdates() {
        Settings.singleton(this.context).saveSetting(Constants.PREF_UPDATE_COMPLETE, "NO");
        if (!isSingleUpdateStarted()) {
            startFormularyUpdates();
        }
    }

    private final void startFormularyUpdates() {
        Settings.singleton(this.context).saveSetting(Constants.PREF_UPDATE_COMPLETE, "NO");
        if (!isFormularyUpdateStarted()) {
            finishAllBackgroundUpdates();
        }
    }

    private final boolean isFormularyUpdateStarted() {
        if (!Util.isOnline(this.context) || !Intrinsics.areEqual((Object) Settings.singleton(this.context).getSetting(Constants.PREF_FORMULARY_VISITED, AppEventsConstants.EVENT_PARAM_VALUE_NO), (Object) AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            return false;
        }
        FormularyDownloadService formularyDownloadService = new FormularyDownloadService(this.context, this);
        EnvironmentManager environmentManager = new EnvironmentManager();
        Context context2 = this.context;
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        formularyDownloadService.execute(new String[]{OldConstants.getFormularyURL(environmentManager.getEnvironmentWithDefault(context2, EnvironmentConstants.MODULE_SERVICE)), ""});
        return true;
    }

    private final boolean isSingleUpdateStarted() {
        if (Util.isOnline(this.context)) {
            return new SingleDataUpdateManager(this.context, this).checkForSingleUpdate(0);
        }
        return false;
    }

    private final void finishAllBackgroundUpdates() {
        Settings.singleton(this.context).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
    }

    public final String getUpdateMessage() {
        int i = this.downloadType;
        if (i == 1) {
            return "Contains updated Drug and Clinical Reference information.\n\nTypically takes 2 - 10 minutes to install (depending on connection speed)";
        }
        if (i == 2) {
            return "Contains new Drug and Clinical Reference information.\n\nTypically takes 20 seconds to 2 minutes to install (depending on connection speed)";
        }
        if (i == 3) {
            return "Contains new Drug and Clinical Reference information.\n\nThis update is required.\n\nTypically takes 2 - 10 minutes to install (depending on connection speed)";
        }
        if (i != 4) {
            return i != 5 ? "" : "Contains updated Clinical Reference information.\n\nTypically takes 2 - 10 minutes to install (depending on connection speed)";
        }
        return "Contains new Drug and Clinical Reference information.\n\nThis update is required.\n\nTypically takes 20 seconds to 2 minutes to install (depending on connection speed)";
    }

    public final float getClientVersion() {
        return this.context.getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", -1.0f);
    }

    public final boolean isFirstInstall() {
        if (this.context.getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", -1.0f) == -1.0f) {
            return true;
        }
        return false;
    }

    private final boolean needMandatoryDataUpdate() {
        return isFirstInstall() || is90daysSinceLastUpdate() || !isDatabaseCreated();
    }

    private final boolean isDatabaseCreated() {
        return new DatabaseHelper(this.context).checkDataBase();
    }

    private final boolean is90daysSinceLastUpdate() {
        return timeAtLastUpdate() - System.currentTimeMillis() < 0;
    }

    private final long timeAtLastUpdate() {
        return Long.parseLong(Settings.singleton(this.context).getSetting(Constants.PREF_OPTIONAL_DATA_UPDATE_TIME, AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    public final void handleNetworkError() {
        if (needMandatoryDataUpdate()) {
            new Thread(new HomeScreenViewModel$handleNetworkError$thread$1(this)).start();
        }
        Settings.singleton(this.context).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
        dataUpdateFinish();
    }

    public final void onPause(boolean z) {
        this.mIsActivityForeGround = false;
        if (z) {
            Settings.singleton(this.context).saveSetting(Constants.PREF_SEARCH_FILTER_CACHE, ExifInterface.GPS_MEASUREMENT_2D);
            Settings.singleton(this.context).saveSetting(Constants.PREF_UPDATE_COMPLETE, "NO");
        }
    }

    public void onUpdateDownloadComplete() {
        finishAllBackgroundUpdates();
    }

    public void onSingleDataUpdateSucess() {
        startFormularyUpdates();
    }

    public final boolean isClinicalReferenceUpdateAvailable() {
        return Intrinsics.areEqual((Object) Settings.singleton(this.context).getSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO), (Object) AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public final void handleSpecialityChange() {
        this.currentSpecialtyName = Settings.singleton(this.context).getSetting(UserProfileProvider.INSTANCE.getUserSpecialityIDKey(this.context), "");
    }

    public final boolean updateSpecialityChange() {
        String specialtyNameOrDefaultBySpecialtyId = FeedMaster.getSpecialtyNameOrDefaultBySpecialtyId(new DatabaseHelper(this.context), Settings.singleton(this.context).getSetting(UserProfileProvider.INSTANCE.getUserSpecialityIDKey(this.context), ""), ExifInterface.GPS_MEASUREMENT_2D);
        String str = this.currentSpecialtyName;
        if (str == null || StringsKt.equals(str, specialtyNameOrDefaultBySpecialtyId, true)) {
            return false;
        }
        this.currentSpecialtyName = specialtyNameOrDefaultBySpecialtyId;
        return true;
    }

    private final void resetClientVersionCode() {
        this.context.getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).edit().putFloat("version", -1.0f).apply();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/homescreen/viewmodel/HomeScreenViewModel$Companion;", "", "()V", "RANDOM_FEED_ID", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HomeScreenViewModel.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* access modifiers changed from: private */
    public final void removeDBForHardCodedUser() {
        File file;
        StringBuilder sb = new StringBuilder();
        Context context2 = this.context;
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        File filesDir = context2.getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "context.filesDir");
        sb.append(filesDir.getPath());
        sb.append(File.separator);
        sb.append(this.CALCULATE_DATA);
        File file2 = new File(sb.toString());
        File[] listFiles = file2.listFiles();
        if (listFiles != null) {
            if (listFiles.length == 0) {
                return;
            }
        }
        UserManager.getInstance().logout();
        FilesKt.deleteRecursively(file2);
        if (Build.VERSION.SDK_INT >= 24) {
            StringBuilder sb2 = new StringBuilder();
            Context context3 = this.context;
            Intrinsics.checkNotNullExpressionValue(context3, "context");
            File dataDir = context3.getDataDir();
            Intrinsics.checkNotNullExpressionValue(dataDir, "context.dataDir");
            sb2.append(dataDir.getPath());
            sb2.append(File.separator);
            sb2.append(this.DATABASES);
            file = new File(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder();
            File dataDirectory = Environment.getDataDirectory();
            Intrinsics.checkNotNullExpressionValue(dataDirectory, "Environment.getDataDirectory()");
            sb3.append(dataDirectory.getPath());
            sb3.append(File.separator);
            sb3.append(this.DATABASES);
            file = new File(sb3.toString());
        }
        File[] listFiles2 = file.listFiles();
        if (listFiles2 != null) {
            if (!(listFiles2.length == 0)) {
                for (File file3 : listFiles2) {
                    if (file3 != null) {
                        String name = file3.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "dbFile.name");
                        if (StringsKt.contains$default((CharSequence) name, (CharSequence) "user" + this.wbmdUser, false, 2, (Object) null)) {
                            com.wbmd.qxcalculator.managers.FileHelper.getInstance().deleteFile(file3);
                        }
                    }
                }
            }
        }
    }
}
