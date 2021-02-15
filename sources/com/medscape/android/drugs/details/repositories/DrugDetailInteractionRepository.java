package com.medscape.android.drugs.details.repositories;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.facebook.applinks.AppLinkData;
import com.medscape.android.drugs.details.datamodels.InlineAdLineItem;
import com.medscape.android.drugs.details.datamodels.InteractionListItem;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.drugs.details.util.DatabaseRequests;
import com.medscape.android.interfaces.ICallbackEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010$\u001a\u00020\u0011H\u0002J\u0016\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'J+\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020'2\u0016\u0010,\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001f0-\"\u0004\u0018\u00010\u001f¢\u0006\u0002\u0010.J\u001e\u0010/\u001a\u00020*2\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u000202\u0012\b\u0012\u000603j\u0002`401J+\u00105\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00102\u0006\u0010+\u001a\u00020'2\u0010\u00106\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001f0-¢\u0006\u0002\u00107J\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00102\u0006\u00109\u001a\u00020'J\u000e\u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020'R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR&\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006<"}, d2 = {"Lcom/medscape/android/drugs/details/repositories/DrugDetailInteractionRepository;", "", "mContext", "Landroid/content/Context;", "contentId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "getContentId", "()Ljava/lang/String;", "setContentId", "(Ljava/lang/String;)V", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "originalList", "", "Lcom/medscape/android/drugs/details/datamodels/InteractionListItem;", "getOriginalList", "()Ljava/util/List;", "setOriginalList", "(Ljava/util/List;)V", "requests", "Lcom/medscape/android/drugs/details/util/DatabaseRequests;", "getRequests", "()Lcom/medscape/android/drugs/details/util/DatabaseRequests;", "setRequests", "(Lcom/medscape/android/drugs/details/util/DatabaseRequests;)V", "sortedList", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "getSortedList", "()Landroidx/lifecycle/MutableLiveData;", "setSortedList", "(Landroidx/lifecycle/MutableLiveData;)V", "createNoInteractionsFoundHeader", "createSectionHeader", "strengthId", "", "headerPosition", "getInteractionItems", "", "selectedTab", "extraItems", "", "(I[Lcom/medscape/android/drugs/details/datamodels/LineItem;)V", "hasInteractions", "callback", "Lcom/medscape/android/interfaces/ICallbackEvent;", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "sortAndAddExtraItem", "extras", "(I[Lcom/medscape/android/drugs/details/datamodels/LineItem;)Ljava/util/List;", "sortInteractions", "sortType", "strengthNameById", "index", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugDetailInteractionRepository.kt */
public final class DrugDetailInteractionRepository {
    private String contentId;
    private Context mContext;
    private List<InteractionListItem> originalList = new ArrayList();
    private DatabaseRequests requests;
    private MutableLiveData<List<LineItem>> sortedList = new MutableLiveData<>();

    public DrugDetailInteractionRepository(Context context, String str) {
        this.mContext = context;
        this.contentId = str;
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setContentId(String str) {
        this.contentId = str;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    public final List<InteractionListItem> getOriginalList() {
        return this.originalList;
    }

    public final void setOriginalList(List<InteractionListItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.originalList = list;
    }

    public final DatabaseRequests getRequests() {
        return this.requests;
    }

    public final void setRequests(DatabaseRequests databaseRequests) {
        this.requests = databaseRequests;
    }

    public final MutableLiveData<List<LineItem>> getSortedList() {
        return this.sortedList;
    }

    public final void setSortedList(MutableLiveData<List<LineItem>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.sortedList = mutableLiveData;
    }

    public final void getInteractionItems(int i, LineItem... lineItemArr) {
        Intrinsics.checkNotNullParameter(lineItemArr, "extraItems");
        if (this.originalList.isEmpty()) {
            ThreadsKt.thread$default(true, false, (ClassLoader) null, (String) null, 0, new DrugDetailInteractionRepository$getInteractionItems$1(this, i, lineItemArr), 30, (Object) null);
        } else {
            this.sortedList.setValue(sortAndAddExtraItem(i, lineItemArr));
        }
    }

    public final void hasInteractions(ICallbackEvent<Boolean, Exception> iCallbackEvent) {
        Intrinsics.checkNotNullParameter(iCallbackEvent, "callback");
        ThreadsKt.thread$default(true, false, (ClassLoader) null, (String) null, 0, new DrugDetailInteractionRepository$hasInteractions$1(this, iCallbackEvent), 30, (Object) null);
    }

    public final List<LineItem> sortAndAddExtraItem(int i, LineItem[] lineItemArr) {
        Intrinsics.checkNotNullParameter(lineItemArr, AppLinkData.ARGUMENTS_EXTRAS_KEY);
        List<LineItem> sortInteractions = sortInteractions(i);
        for (LineItem lineItem : lineItemArr) {
            if (lineItem != null) {
                sortInteractions.add(lineItem);
            }
        }
        return sortInteractions;
    }

    public final List<LineItem> sortInteractions(int i) {
        List<LineItem> arrayList = new ArrayList<>();
        arrayList.add(InlineAdLineItem.Companion.createNativeAD());
        if (i == 0) {
            List<InteractionListItem> reversed = CollectionsKt.reversed(CollectionsKt.sortedWith(this.originalList, new DrugDetailInteractionRepository$sortInteractions$$inlined$compareBy$1()));
            List arrayList2 = new ArrayList();
            for (InteractionListItem interactionListItem : reversed) {
                arrayList2.add(new InteractionListItem(interactionListItem));
            }
            List<InteractionListItem> reversed2 = CollectionsKt.reversed(CollectionsKt.sortedWith(arrayList2, new DrugDetailInteractionRepository$sortInteractions$$inlined$compareBy$2()));
            int i2 = 1;
            if (!reversed2.isEmpty()) {
                int strength = ((InteractionListItem) reversed2.get(0)).getStrength();
                arrayList.add(createSectionHeader(strength, 1));
                for (InteractionListItem interactionListItem2 : reversed2) {
                    if (interactionListItem2.getStrength() == strength) {
                        interactionListItem2.setSectionHeaderPosition(i2);
                        arrayList.add(interactionListItem2);
                    } else {
                        strength = interactionListItem2.getStrength();
                        i2 = arrayList.size();
                        arrayList.add(createSectionHeader(strength, i2));
                        interactionListItem2.setSectionHeaderPosition(i2);
                        arrayList.add(interactionListItem2);
                    }
                }
            } else {
                arrayList.add(createNoInteractionsFoundHeader());
            }
        } else {
            List<InteractionListItem> sortedWith = CollectionsKt.sortedWith(this.originalList, new DrugDetailInteractionRepository$sortInteractions$$inlined$compareBy$3());
            if (!sortedWith.isEmpty()) {
                for (InteractionListItem interactionListItem3 : sortedWith) {
                    arrayList.add(new InteractionListItem(interactionListItem3));
                }
            } else {
                arrayList.add(createNoInteractionsFoundHeader());
            }
        }
        return arrayList;
    }

    public final InteractionListItem createSectionHeader(int i, int i2) {
        InteractionListItem interactionListItem = new InteractionListItem((CharSequence) null, 0, 3, (DefaultConstructorMarker) null);
        interactionListItem.setHeader(true);
        interactionListItem.setSectionHeaderPosition(i2);
        interactionListItem.setText(strengthNameById(i));
        return interactionListItem;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        r3 = (r3 = r2.mContext).getString(com.medscape.android.R.string.contraindicated);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String strengthNameById(int r3) {
        /*
            r2 = this;
            java.lang.String r0 = ""
            if (r3 == 0) goto L_0x003b
            r1 = 1
            if (r3 == r1) goto L_0x002c
            r1 = 2
            if (r3 == r1) goto L_0x001d
            r1 = 3
            if (r3 == r1) goto L_0x000e
            return r0
        L_0x000e:
            android.content.Context r3 = r2.mContext
            if (r3 == 0) goto L_0x001c
            r1 = 2131952146(0x7f130212, float:1.9540727E38)
            java.lang.String r3 = r3.getString(r1)
            if (r3 == 0) goto L_0x001c
            r0 = r3
        L_0x001c:
            return r0
        L_0x001d:
            android.content.Context r3 = r2.mContext
            if (r3 == 0) goto L_0x002b
            r1 = 2131953324(0x7f1306ac, float:1.9543116E38)
            java.lang.String r3 = r3.getString(r1)
            if (r3 == 0) goto L_0x002b
            r0 = r3
        L_0x002b:
            return r0
        L_0x002c:
            android.content.Context r3 = r2.mContext
            if (r3 == 0) goto L_0x003a
            r1 = 2131953420(0x7f13070c, float:1.954331E38)
            java.lang.String r3 = r3.getString(r1)
            if (r3 == 0) goto L_0x003a
            r0 = r3
        L_0x003a:
            return r0
        L_0x003b:
            android.content.Context r3 = r2.mContext
            if (r3 == 0) goto L_0x0049
            r1 = 2131952792(0x7f130498, float:1.9542037E38)
            java.lang.String r3 = r3.getString(r1)
            if (r3 == 0) goto L_0x0049
            r0 = r3
        L_0x0049:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.repositories.DrugDetailInteractionRepository.strengthNameById(int):java.lang.String");
    }

    private final InteractionListItem createNoInteractionsFoundHeader() {
        InteractionListItem interactionListItem = new InteractionListItem((CharSequence) null, 0, 3, (DefaultConstructorMarker) null);
        interactionListItem.setErrorMessage(true);
        interactionListItem.setText("No Interactions Found");
        return interactionListItem;
    }
}
