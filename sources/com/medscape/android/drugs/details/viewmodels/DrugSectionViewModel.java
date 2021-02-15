package com.medscape.android.drugs.details.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.medscape.android.db.model.Drug;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.drugs.details.datamodels.ScrollPosition;
import com.medscape.android.drugs.details.repositories.DrugDetailInteractionRepository;
import com.medscape.android.drugs.details.repositories.DrugSectionContent;
import com.medscape.android.drugs.helper.DrugMonographViewHelper;
import com.medscape.android.drugs.model.DrugFindHelper;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u00104\u001a\u000205J\u0006\u00106\u001a\u000207J\u0006\u00108\u001a\u000209J\f\u0010:\u001a\b\u0012\u0004\u0012\u0002090\u0006J\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010<J\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\"0\u00062\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\f\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u0006J\u0006\u0010A\u001a\u000209J\u0006\u0010B\u001a\u00020\bJ\u0006\u0010C\u001a\u00020\bJ\u0006\u0010D\u001a\u00020\"J\u0006\u0010E\u001a\u00020\"J\u0006\u0010F\u001a\u00020\"J\u000e\u0010G\u001a\u00020\"2\u0006\u0010H\u001a\u00020\bJ\u000e\u0010I\u001a\u0002052\u0006\u0010J\u001a\u00020\"J\u000e\u0010K\u001a\u0002052\u0006\u0010L\u001a\u00020\bJ\u000e\u0010M\u001a\u0002052\u0006\u0010H\u001a\u00020\bJ\u000e\u0010N\u001a\u0002052\u0006\u0010O\u001a\u00020PJ\u0006\u0010Q\u001a\u00020\"J\u000e\u0010R\u001a\u0002052\u0006\u0010S\u001a\u000209R,\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R&\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R \u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\n\"\u0004\b$\u0010\fR\"\u0010%\u001a\n '*\u0004\u0018\u00010&0&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u00020\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010.\"\u0004\b3\u00100¨\u0006T"}, d2 = {"Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "currentIndex", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Pair;", "", "getCurrentIndex", "()Landroidx/lifecycle/MutableLiveData;", "setCurrentIndex", "(Landroidx/lifecycle/MutableLiveData;)V", "drugSectionContentRepo", "Lcom/medscape/android/drugs/details/repositories/DrugSectionContent;", "getDrugSectionContentRepo", "()Lcom/medscape/android/drugs/details/repositories/DrugSectionContent;", "setDrugSectionContentRepo", "(Lcom/medscape/android/drugs/details/repositories/DrugSectionContent;)V", "findManager", "Lcom/medscape/android/drugs/details/viewmodels/FindOnPageManager;", "getFindManager", "()Lcom/medscape/android/drugs/details/viewmodels/FindOnPageManager;", "setFindManager", "(Lcom/medscape/android/drugs/details/viewmodels/FindOnPageManager;)V", "items", "Landroidx/lifecycle/MediatorLiveData;", "", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "getItems", "()Landroidx/lifecycle/MediatorLiveData;", "setItems", "(Landroidx/lifecycle/MediatorLiveData;)V", "loading", "", "getLoading", "setLoading", "mContext", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "nextSectionTrigger", "getNextSectionTrigger", "()Z", "setNextSectionTrigger", "(Z)V", "showTabs", "getShowTabs", "setShowTabs", "cancelFind", "", "getDrugForInteraction", "Lcom/medscape/android/db/model/Drug;", "getDrugName", "", "getFindCount", "getIndexes", "Ljava/util/ArrayList;", "getLoadingStatus", "list", "getScrollPosition", "Lcom/medscape/android/drugs/details/datamodels/ScrollPosition;", "getTabName", "getTabNameID", "goToNextSection", "hasInteractions", "isImageSection", "isInteractionSection", "isSectionEmpty", "index", "moveFind", "isForward", "setTab", "selectedTab", "setTabFlag", "setUp", "intent", "Landroid/content/Intent;", "shouldShowTabs", "startFind", "findQuery", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugSectionViewModel.kt */
public final class DrugSectionViewModel extends AndroidViewModel {
    private MutableLiveData<Pair<Integer, Integer>> currentIndex = new MutableLiveData<>();
    public DrugSectionContent drugSectionContentRepo;
    private FindOnPageManager findManager;
    private MediatorLiveData<List<LineItem>> items;
    private MutableLiveData<Boolean> loading;
    private Context mContext;
    private boolean nextSectionTrigger;
    private boolean showTabs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DrugSectionViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.mContext = application.getApplicationContext();
        Context context = this.mContext;
        Intrinsics.checkNotNullExpressionValue(context, "mContext");
        this.findManager = new FindOnPageManager(context, (DrugFindHelper) null, 2, (DefaultConstructorMarker) null);
        this.currentIndex.setValue(new Pair(0, 0));
        MediatorLiveData<List<LineItem>> mediatorLiveData = new MediatorLiveData<>();
        this.items = mediatorLiveData;
        LiveData switchMap = Transformations.switchMap(mediatorLiveData, new Function<List<? extends LineItem>, LiveData<Boolean>>(this) {
            final /* synthetic */ DrugSectionViewModel this$0;

            {
                this.this$0 = r1;
            }

            public final LiveData<Boolean> apply(List<? extends LineItem> list) {
                DrugSectionViewModel drugSectionViewModel = this.this$0;
                Intrinsics.checkNotNullExpressionValue(list, "it");
                return drugSectionViewModel.getLoadingStatus(list);
            }
        });
        if (switchMap != null) {
            this.loading = (MutableLiveData) switchMap;
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<kotlin.Boolean>");
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    public final MutableLiveData<Boolean> getLoading() {
        return this.loading;
    }

    public final void setLoading(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.loading = mutableLiveData;
    }

    public final DrugSectionContent getDrugSectionContentRepo() {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        return drugSectionContent;
    }

    public final void setDrugSectionContentRepo(DrugSectionContent drugSectionContent) {
        Intrinsics.checkNotNullParameter(drugSectionContent, "<set-?>");
        this.drugSectionContentRepo = drugSectionContent;
    }

    public final MediatorLiveData<List<LineItem>> getItems() {
        return this.items;
    }

    public final void setItems(MediatorLiveData<List<LineItem>> mediatorLiveData) {
        Intrinsics.checkNotNullParameter(mediatorLiveData, "<set-?>");
        this.items = mediatorLiveData;
    }

    public final MutableLiveData<Pair<Integer, Integer>> getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(MutableLiveData<Pair<Integer, Integer>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.currentIndex = mutableLiveData;
    }

    public final boolean getShowTabs() {
        return this.showTabs;
    }

    public final void setShowTabs(boolean z) {
        this.showTabs = z;
    }

    public final boolean getNextSectionTrigger() {
        return this.nextSectionTrigger;
    }

    public final void setNextSectionTrigger(boolean z) {
        this.nextSectionTrigger = z;
    }

    public final FindOnPageManager getFindManager() {
        return this.findManager;
    }

    public final void setFindManager(FindOnPageManager findOnPageManager) {
        Intrinsics.checkNotNullParameter(findOnPageManager, "<set-?>");
        this.findManager = findOnPageManager;
    }

    public final MutableLiveData<Boolean> getLoadingStatus(List<? extends LineItem> list) {
        Intrinsics.checkNotNullParameter(list, Constants.XML_LIST);
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Boolean.valueOf(list.isEmpty()));
        return mutableLiveData;
    }

    public final void setTabFlag(int i) {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        this.showTabs = drugSectionContent.shouldShowTabs(new Pair(Integer.valueOf(i), 0));
    }

    public final void setUp(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Context context = this.mContext;
        Intrinsics.checkNotNullExpressionValue(context, "mContext");
        this.drugSectionContentRepo = new DrugSectionContent(intent, context, (DrugDetailInteractionRepository) null, 4, (DefaultConstructorMarker) null);
        this.items.removeSource(this.currentIndex);
        this.items.addSource(this.currentIndex, new DrugSectionViewModel$setUp$1(this));
        MediatorLiveData<List<LineItem>> mediatorLiveData = this.items;
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        mediatorLiveData.removeSource(drugSectionContent.getList());
        MediatorLiveData<List<LineItem>> mediatorLiveData2 = this.items;
        DrugSectionContent drugSectionContent2 = this.drugSectionContentRepo;
        if (drugSectionContent2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        mediatorLiveData2.addSource(drugSectionContent2.getList(), new DrugSectionViewModel$setUp$2(this));
        this.items.removeSource(this.findManager.getItems());
        this.items.addSource(this.findManager.getItems(), new DrugSectionViewModel$setUp$3(this));
    }

    public final boolean shouldShowTabs() {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        boolean shouldShowTabs = drugSectionContent.shouldShowTabs(this.currentIndex.getValue());
        this.showTabs = shouldShowTabs;
        return shouldShowTabs;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r1 = (java.lang.Integer) r1.getFirst();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setTab(int r3) {
        /*
            r2 = this;
            kotlin.Pair r0 = new kotlin.Pair
            androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.Integer, java.lang.Integer>> r1 = r2.currentIndex
            java.lang.Object r1 = r1.getValue()
            kotlin.Pair r1 = (kotlin.Pair) r1
            if (r1 == 0) goto L_0x0019
            java.lang.Object r1 = r1.getFirst()
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 == 0) goto L_0x0019
            int r1 = r1.intValue()
            goto L_0x001a
        L_0x0019:
            r1 = 0
        L_0x001a:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.<init>(r1, r3)
            androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.Integer, java.lang.Integer>> r3 = r2.currentIndex
            r3.setValue(r0)
            com.medscape.android.drugs.details.viewmodels.FindOnPageManager r3 = r2.findManager
            com.medscape.android.drugs.model.DrugFindHelper r3 = r3.getFinder()
            if (r3 == 0) goto L_0x003e
            boolean r3 = r3.isInFindMode()
            r0 = 1
            if (r3 != r0) goto L_0x003e
            com.medscape.android.drugs.details.viewmodels.FindOnPageManager r3 = r2.findManager
            r3.cancelFind()
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel.setTab(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = (java.lang.Integer) r0.getFirst();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int goToNextSection() {
        /*
            r2 = this;
            androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.Integer, java.lang.Integer>> r0 = r2.currentIndex
            java.lang.Object r0 = r0.getValue()
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 == 0) goto L_0x0017
            java.lang.Object r0 = r0.getFirst()
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L_0x0017
            int r0 = r0.intValue()
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            com.medscape.android.drugs.details.viewmodels.FindOnPageManager r1 = r2.findManager
            r1.cancelFind()
            r1 = 1
            r2.nextSectionTrigger = r1
            int r0 = r0 + r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel.goToNextSection():int");
    }

    public final ArrayList<Integer> getIndexes() {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        return drugSectionContent.getIndexes(this.currentIndex.getValue());
    }

    public final boolean isInteractionSection() {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        return drugSectionContent.isInteractionSection(this.currentIndex.getValue());
    }

    public final boolean isImageSection() {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        return drugSectionContent.isImageSection(this.currentIndex.getValue());
    }

    public final boolean hasInteractions() {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        return drugSectionContent.hasInteractions();
    }

    public final boolean isSectionEmpty(int i) {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        return drugSectionContent.isSectionEmpty(i, this.currentIndex.getValue());
    }

    public final String getDrugName() {
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        return drugSectionContent.getDrugName();
    }

    public final Drug getDrugForInteraction() {
        Drug drug = new Drug();
        DrugSectionContent drugSectionContent = this.drugSectionContentRepo;
        if (drugSectionContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        drug.setContentId(drugSectionContent.getContentId());
        DrugSectionContent drugSectionContent2 = this.drugSectionContentRepo;
        if (drugSectionContent2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionContentRepo");
        }
        drug.setDrugName(drugSectionContent2.getDrugName());
        return drug;
    }

    public final String getTabName() {
        int tabNameID = getTabNameID();
        if (tabNameID <= 0) {
            return "";
        }
        String string = this.mContext.getString(tabNameID);
        Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(tabNameID)");
        return string;
    }

    public final int getTabNameID() {
        Pair value;
        if (!this.showTabs || isInteractionSection() || (value = this.currentIndex.getValue()) == null) {
            return -1;
        }
        return DrugMonographViewHelper.getSectionIndexResource(((Number) value.getSecond()).intValue(), false);
    }

    public final void startFind(String str) {
        Intrinsics.checkNotNullParameter(str, "findQuery");
        FindOnPageManager findOnPageManager = this.findManager;
        List<LineItem> value = this.items.getValue();
        if (value != null) {
            findOnPageManager.startFind(str, TypeIntrinsics.asMutableList(value));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableList<com.medscape.android.drugs.details.datamodels.LineItem>");
    }

    public final MutableLiveData<String> getFindCount() {
        return this.findManager.getFindCount();
    }

    public final void moveFind(boolean z) {
        this.findManager.moveFind(z);
    }

    public final void cancelFind() {
        this.currentIndex.setValue(this.currentIndex.getValue());
        this.findManager.cancelFind();
    }

    public final MutableLiveData<ScrollPosition> getScrollPosition() {
        return this.findManager.getScrollPosition();
    }
}
