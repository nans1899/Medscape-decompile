package com.medscape.android.drugs.details.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.medscape.android.CP.CPEventWithAdRequest;
import com.medscape.android.CP.FireCPEventWithAdsSegvarAsyncTask;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.cache.CacheManager;
import com.medscape.android.drugs.details.repositories.SaveDrugManager;
import com.medscape.android.drugs.model.DrugMonograph;
import com.medscape.android.drugs.model.DrugMonographHeader;
import com.medscape.android.drugs.model.DrugMonographIndexElement;
import com.medscape.android.drugs.model.DrugMonographSectionIndex;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010D\u001a\u00020\u001cJ\u0006\u0010E\u001a\u00020\u0006J\u0006\u0010F\u001a\u00020\u001cJ*\u0010G\u001a\u00020H2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010I\u001a\u00020\u001c2\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001c0KJ\u000e\u0010*\u001a\u00020H2\u0006\u0010L\u001a\u00020\"J\u000e\u0010M\u001a\u00020H2\u0006\u0010N\u001a\u00020OJ\u0006\u0010P\u001a\u00020HR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\b\"\u0004\b\u001a\u0010\nR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010'\u001a\b\u0012\u0004\u0012\u00020\"0(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010$\"\u0004\b.\u0010&R\u001c\u0010/\u001a\u0004\u0018\u000100X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\b\"\u0004\b7\u0010\nR \u00108\u001a\b\u0012\u0004\u0012\u00020:09X.¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0016\u0010?\u001a\n \r*\u0004\u0018\u00010@0@X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010A\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u001e\"\u0004\bC\u0010 ¨\u0006Q"}, d2 = {"Lcom/medscape/android/drugs/details/viewmodels/DrugDetailsActivityViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "contentId", "", "getContentId", "()I", "setContentId", "(I)V", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "cpRequest", "Lcom/medscape/android/CP/CPEventWithAdRequest;", "getCpRequest", "()Lcom/medscape/android/CP/CPEventWithAdRequest;", "setCpRequest", "(Lcom/medscape/android/CP/CPEventWithAdRequest;)V", "currentMenu", "getCurrentMenu", "setCurrentMenu", "drugName", "", "getDrugName", "()Ljava/lang/String;", "setDrugName", "(Ljava/lang/String;)V", "goingToNextSection", "", "getGoingToNextSection", "()Z", "setGoingToNextSection", "(Z)V", "isSearchMode", "Landroidx/lifecycle/MutableLiveData;", "()Landroidx/lifecycle/MutableLiveData;", "setSearchMode", "(Landroidx/lifecycle/MutableLiveData;)V", "loadingItemsFinished", "getLoadingItemsFinished", "setLoadingItemsFinished", "saveManager", "Lcom/medscape/android/drugs/details/repositories/SaveDrugManager;", "getSaveManager", "()Lcom/medscape/android/drugs/details/repositories/SaveDrugManager;", "setSaveManager", "(Lcom/medscape/android/drugs/details/repositories/SaveDrugManager;)V", "selectedSpinnerOption", "getSelectedSpinnerOption", "setSelectedSpinnerOption", "spinnerOptions", "", "Lcom/medscape/android/drugs/model/DrugMonographIndexElement;", "getSpinnerOptions", "()Ljava/util/List;", "setSpinnerOptions", "(Ljava/util/List;)V", "threadPoolExecutor", "Ljava/util/concurrent/ExecutorService;", "toolbarHeader", "getToolbarHeader", "setToolbarHeader", "getContentLink", "getOptionMenu", "getSubjetForShareEmail", "sendCpEvent", "", "tabName", "screenMap", "Ljava/util/HashMap;", "flag", "setUp", "intent", "Landroid/content/Intent;", "updateContentSaveStatus", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivityViewModel.kt */
public final class DrugDetailsActivityViewModel extends AndroidViewModel {
    private int contentId = -1;
    private Context context;
    private CPEventWithAdRequest cpRequest;
    private int currentMenu = R.menu.drug_content_page_save_empty;
    private String drugName = "";
    private boolean goingToNextSection;
    private MutableLiveData<Boolean> isSearchMode = new MutableLiveData<>();
    private boolean loadingItemsFinished;
    private SaveDrugManager saveManager;
    private int selectedSpinnerOption;
    public List<DrugMonographIndexElement> spinnerOptions;
    private final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    private String toolbarHeader = "";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DrugDetailsActivityViewModel(Application application) {
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

    public final String getDrugName() {
        return this.drugName;
    }

    public final void setDrugName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.drugName = str;
    }

    public final String getToolbarHeader() {
        return this.toolbarHeader;
    }

    public final void setToolbarHeader(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.toolbarHeader = str;
    }

    public final List<DrugMonographIndexElement> getSpinnerOptions() {
        List<DrugMonographIndexElement> list = this.spinnerOptions;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerOptions");
        }
        return list;
    }

    public final void setSpinnerOptions(List<DrugMonographIndexElement> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.spinnerOptions = list;
    }

    public final MutableLiveData<Boolean> isSearchMode() {
        return this.isSearchMode;
    }

    public final void setSearchMode(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.isSearchMode = mutableLiveData;
    }

    public final int getSelectedSpinnerOption() {
        return this.selectedSpinnerOption;
    }

    public final void setSelectedSpinnerOption(int i) {
        this.selectedSpinnerOption = i;
    }

    public final int getContentId() {
        return this.contentId;
    }

    public final void setContentId(int i) {
        this.contentId = i;
    }

    public final SaveDrugManager getSaveManager() {
        return this.saveManager;
    }

    public final void setSaveManager(SaveDrugManager saveDrugManager) {
        this.saveManager = saveDrugManager;
    }

    public final int getCurrentMenu() {
        return this.currentMenu;
    }

    public final void setCurrentMenu(int i) {
        this.currentMenu = i;
    }

    public final CPEventWithAdRequest getCpRequest() {
        return this.cpRequest;
    }

    public final void setCpRequest(CPEventWithAdRequest cPEventWithAdRequest) {
        this.cpRequest = cPEventWithAdRequest;
    }

    public final boolean getGoingToNextSection() {
        return this.goingToNextSection;
    }

    public final void setGoingToNextSection(boolean z) {
        this.goingToNextSection = z;
    }

    public final boolean getLoadingItemsFinished() {
        return this.loadingItemsFinished;
    }

    public final void setLoadingItemsFinished(boolean z) {
        this.loadingItemsFinished = z;
    }

    public final void setUp(Intent intent) {
        String str;
        DrugMonographHeader header;
        Intrinsics.checkNotNullParameter(intent, "intent");
        this.spinnerOptions = new ArrayList();
        DrugMonographSectionIndex drugMonographSectionIndex = (DrugMonographSectionIndex) intent.getParcelableExtra(Constants.EXTRA_DRUG_SELECTION_INDEX_OBJ);
        if (drugMonographSectionIndex != null) {
            ArrayList<DrugMonographIndexElement> navMenuElements = drugMonographSectionIndex.getNavMenuElements();
            Intrinsics.checkNotNullExpressionValue(navMenuElements, "drugMonographSectionIndex.navMenuElements");
            this.spinnerOptions = navMenuElements;
        }
        this.selectedSpinnerOption = intent.getIntExtra(Constants.EXTRA_DRUG_SELECTION_INDEX, 0);
        String stringExtra = intent.getStringExtra("drugName");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.drugName = stringExtra;
        DrugMonograph drugMonograph = (DrugMonograph) intent.getSerializableExtra("drug");
        if (drugMonograph == null || (header = drugMonograph.getHeader()) == null || (str = header.getGc()) == null) {
            str = this.drugName;
        }
        this.toolbarHeader = str;
        this.contentId = intent.getIntExtra("contentId", -1);
        if (this.saveManager == null) {
            Context context2 = this.context;
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            this.saveManager = new SaveDrugManager(context2, (CacheManager) null, 2, (DefaultConstructorMarker) null);
        }
    }

    public final void setSearchMode(boolean z) {
        this.isSearchMode.setValue(Boolean.valueOf(z));
    }

    public final int getOptionMenu() {
        if (this.context == null) {
            return this.currentMenu;
        }
        SaveDrugManager saveDrugManager = this.saveManager;
        return saveDrugManager != null ? saveDrugManager.isContentSaved(this.contentId) : false ? R.menu.drug_content_page_save_full : R.menu.drug_content_page_save_empty;
    }

    public final void updateContentSaveStatus() {
        SaveDrugManager saveDrugManager = this.saveManager;
        if (saveDrugManager == null || !saveDrugManager.isContentSaved(this.contentId)) {
            SaveDrugManager saveDrugManager2 = this.saveManager;
            if (saveDrugManager2 != null) {
                saveDrugManager2.saveContent(this.contentId, this.drugName);
            }
            this.currentMenu = R.menu.drug_content_page_save_full;
            return;
        }
        SaveDrugManager saveDrugManager3 = this.saveManager;
        if (saveDrugManager3 != null) {
            saveDrugManager3.removeDrug(this.contentId);
        }
        this.currentMenu = R.menu.drug_content_page_save_empty;
    }

    public final String getContentLink() {
        return OldConstants.DRUG_WEB_URL + this.contentId;
    }

    public final String getSubjetForShareEmail() {
        return "Medscape: " + this.drugName;
    }

    public final void sendCpEvent(Context context2, String str, HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(str, "tabName");
        Intrinsics.checkNotNullParameter(hashMap, "screenMap");
        if (this.contentId > 0) {
            String str2 = "https://reference.medscape.com/drug/view/" + this.contentId;
            String str3 = "" + this.contentId;
            List<DrugMonographIndexElement> list = this.spinnerOptions;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("spinnerOptions");
            }
            String str4 = list.get(this.selectedSpinnerOption).title;
            if (!StringsKt.isBlank(str)) {
                str4 = str4 + '-' + str;
            }
            if (StringUtil.isNotEmpty(str4)) {
                str2 = str2 + '-' + str4;
            }
            AsyncTaskHelper.execute(this.threadPoolExecutor, new FireCPEventWithAdsSegvarAsyncTask(context2, "drug", "view", str3, str2, (String) null), hashMap);
        }
    }
}
