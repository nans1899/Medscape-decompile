package com.medscape.android.drugs.details.repositories;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.medscape.android.Constants;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.drugs.details.datamodels.InlineAdLineItem;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.drugs.model.DrugMonograph;
import com.medscape.android.drugs.model.DrugMonographIndexElement;
import com.medscape.android.drugs.model.DrugMonographSection;
import com.medscape.android.drugs.model.DrugMonographSectionIndex;
import com.medscape.android.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010>\u001a\u0004\u0018\u0001022\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010@J\u001a\u0010A\u001a\u00020B2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0@J$\u0010C\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010D2\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010@J\u001a\u0010E\u001a\u00020B2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0@J\u0006\u0010F\u001a\u00020GJ\u001c\u0010H\u001a\u00020G2\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010@J\u001c\u0010I\u001a\u00020G2\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010@J$\u0010J\u001a\u00020G2\u0006\u0010K\u001a\u00020\n2\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010@J\u001c\u0010L\u001a\u00020G2\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010@R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R&\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020100X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R,\u00107\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002090 08X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=¨\u0006M"}, d2 = {"Lcom/medscape/android/drugs/details/repositories/DrugSectionContent;", "", "intent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "repository", "Lcom/medscape/android/drugs/details/repositories/DrugDetailInteractionRepository;", "(Landroid/content/Intent;Landroid/content/Context;Lcom/medscape/android/drugs/details/repositories/DrugDetailInteractionRepository;)V", "contentId", "", "getContentId", "()I", "setContentId", "(I)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "drugMonograph", "Lcom/medscape/android/drugs/model/DrugMonograph;", "getDrugMonograph", "()Lcom/medscape/android/drugs/model/DrugMonograph;", "setDrugMonograph", "(Lcom/medscape/android/drugs/model/DrugMonograph;)V", "drugName", "", "getDrugName", "()Ljava/lang/String;", "setDrugName", "(Ljava/lang/String;)V", "drugSectionIndex", "", "Lcom/medscape/android/drugs/model/DrugMonographIndexElement;", "getDrugSectionIndex", "()Ljava/util/List;", "setDrugSectionIndex", "(Ljava/util/List;)V", "getIntent", "()Landroid/content/Intent;", "setIntent", "(Landroid/content/Intent;)V", "interactionRepo", "getInteractionRepo", "()Lcom/medscape/android/drugs/details/repositories/DrugDetailInteractionRepository;", "setInteractionRepo", "(Lcom/medscape/android/drugs/details/repositories/DrugDetailInteractionRepository;)V", "list", "Landroidx/lifecycle/MediatorLiveData;", "", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "getList", "()Landroidx/lifecycle/MediatorLiveData;", "setList", "(Landroidx/lifecycle/MediatorLiveData;)V", "mSections", "", "Lcom/medscape/android/drugs/model/DrugMonographSection;", "getMSections", "()Ljava/util/Map;", "setMSections", "(Ljava/util/Map;)V", "addNextSection", "currentIndex", "Lkotlin/Pair;", "getContentItems", "", "getIndexes", "Ljava/util/ArrayList;", "getItems", "hasInteractions", "", "isImageSection", "isInteractionSection", "isSectionEmpty", "index", "shouldShowTabs", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugSectionContent.kt */
public final class DrugSectionContent {
    private int contentId;
    private Context context;
    private DrugMonograph drugMonograph;
    private String drugName;
    private List<DrugMonographIndexElement> drugSectionIndex;
    private Intent intent;
    private DrugDetailInteractionRepository interactionRepo;
    private MediatorLiveData<List<LineItem>> list;
    private Map<Integer, ? extends List<DrugMonographSection>> mSections;

    public DrugSectionContent(Intent intent2, Context context2, DrugDetailInteractionRepository drugDetailInteractionRepository) {
        Intrinsics.checkNotNullParameter(intent2, "intent");
        Intrinsics.checkNotNullParameter(context2, "context");
        this.intent = intent2;
        this.context = context2;
        this.contentId = -1;
        this.list = new MediatorLiveData<>();
        Serializable serializableExtra = this.intent.getSerializableExtra("drug");
        if (serializableExtra != null) {
            this.drugMonograph = (DrugMonograph) serializableExtra;
            DrugMonographSectionIndex drugMonographSectionIndex = (DrugMonographSectionIndex) this.intent.getParcelableExtra(Constants.EXTRA_DRUG_SELECTION_INDEX_OBJ);
            ArrayList<DrugMonographIndexElement> navMenuElements = drugMonographSectionIndex != null ? drugMonographSectionIndex.getNavMenuElements() : null;
            if (navMenuElements != null) {
                this.drugSectionIndex = CollectionsKt.toMutableList(navMenuElements);
            }
            int intExtra = this.intent.getIntExtra("contentId", -1);
            this.contentId = intExtra;
            if (drugDetailInteractionRepository == null) {
                this.interactionRepo = new DrugDetailInteractionRepository(this.context, String.valueOf(intExtra));
            } else {
                this.interactionRepo = drugDetailInteractionRepository;
            }
            this.list.addSource(this.interactionRepo.getSortedList(), new Observer<List<? extends LineItem>>(this) {
                final /* synthetic */ DrugSectionContent this$0;

                {
                    this.this$0 = r1;
                }

                public final void onChanged(List<? extends LineItem> list) {
                    this.this$0.getList().setValue(list);
                }
            });
            HashMap<Integer, List<DrugMonographSection>> sections = this.drugMonograph.getSections();
            Intrinsics.checkNotNullExpressionValue(sections, "drugMonograph.sections");
            this.mSections = sections;
            String stringExtra = this.intent.getStringExtra("drugName");
            this.drugName = stringExtra == null ? "" : stringExtra;
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.model.DrugMonograph");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DrugSectionContent(Intent intent2, Context context2, DrugDetailInteractionRepository drugDetailInteractionRepository, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(intent2, context2, (i & 4) != 0 ? null : drugDetailInteractionRepository);
    }

    public final Context getContext() {
        return this.context;
    }

    public final Intent getIntent() {
        return this.intent;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public final void setIntent(Intent intent2) {
        Intrinsics.checkNotNullParameter(intent2, "<set-?>");
        this.intent = intent2;
    }

    public final DrugMonograph getDrugMonograph() {
        return this.drugMonograph;
    }

    public final void setDrugMonograph(DrugMonograph drugMonograph2) {
        Intrinsics.checkNotNullParameter(drugMonograph2, "<set-?>");
        this.drugMonograph = drugMonograph2;
    }

    public final Map<Integer, List<DrugMonographSection>> getMSections() {
        return this.mSections;
    }

    public final void setMSections(Map<Integer, ? extends List<DrugMonographSection>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.mSections = map;
    }

    public final List<DrugMonographIndexElement> getDrugSectionIndex() {
        return this.drugSectionIndex;
    }

    public final void setDrugSectionIndex(List<DrugMonographIndexElement> list2) {
        this.drugSectionIndex = list2;
    }

    public final int getContentId() {
        return this.contentId;
    }

    public final void setContentId(int i) {
        this.contentId = i;
    }

    public final DrugDetailInteractionRepository getInteractionRepo() {
        return this.interactionRepo;
    }

    public final void setInteractionRepo(DrugDetailInteractionRepository drugDetailInteractionRepository) {
        Intrinsics.checkNotNullParameter(drugDetailInteractionRepository, "<set-?>");
        this.interactionRepo = drugDetailInteractionRepository;
    }

    public final String getDrugName() {
        return this.drugName;
    }

    public final void setDrugName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.drugName = str;
    }

    public final MediatorLiveData<List<LineItem>> getList() {
        return this.list;
    }

    public final void setList(MediatorLiveData<List<LineItem>> mediatorLiveData) {
        Intrinsics.checkNotNullParameter(mediatorLiveData, "<set-?>");
        this.list = mediatorLiveData;
    }

    public final void getItems(Pair<Integer, Integer> pair) {
        Intrinsics.checkNotNullParameter(pair, "currentIndex");
        if (isInteractionSection(pair)) {
            LineItem addNextSection = addNextSection(pair);
            InlineAdLineItem inlineAdLineItem = new InlineAdLineItem();
            this.interactionRepo.getInteractionItems(pair.getSecond().intValue(), inlineAdLineItem, addNextSection);
            return;
        }
        getContentItems(pair);
    }

    public final void getContentItems(Pair<Integer, Integer> pair) {
        int i;
        String str;
        DrugMonographSection drugMonographSection;
        Iterator it;
        Intrinsics.checkNotNullParameter(pair, "currentIndex");
        List<DrugMonographIndexElement> list2 = this.drugSectionIndex;
        if (list2 != null) {
            Integer valueOf = list2 != null ? Integer.valueOf(list2.size()) : null;
            Intrinsics.checkNotNull(valueOf);
            int intValue = valueOf.intValue();
            int intValue2 = pair.getFirst().intValue();
            int i2 = -1;
            if (intValue2 >= 0 && intValue >= intValue2) {
                List<DrugMonographIndexElement> list3 = this.drugSectionIndex;
                Intrinsics.checkNotNull(list3);
                i = list3.get(pair.getFirst().intValue()).indexes.get(pair.getSecond().intValue());
            } else {
                i = -1;
            }
            List arrayList = new ArrayList();
            List list4 = (List) this.mSections.get(i);
            if (list4 != null) {
                List<DrugMonographIndexElement> list5 = this.drugSectionIndex;
                Intrinsics.checkNotNull(list5);
                if (list5.get(pair.getFirst().intValue()).wNativeAD) {
                    arrayList.add(InlineAdLineItem.Companion.createNativeAD());
                }
                Iterator it2 = list4.iterator();
                while (it2.hasNext()) {
                    DrugMonographSection drugMonographSection2 = (DrugMonographSection) it2.next();
                    int size = arrayList.size();
                    CharSequence title = drugMonographSection2.getTitle();
                    if (!(title == null || title.length() == 0)) {
                        String title2 = drugMonographSection2.getTitle();
                        if (title2 != null) {
                            str = "null cannot be cast to non-null type kotlin.CharSequence";
                            arrayList.add(new LineItem((CrossLink) null, title2, size, true, false, false, 32, (DefaultConstructorMarker) null));
                            i2 = size;
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                    } else {
                        str = "null cannot be cast to non-null type kotlin.CharSequence";
                    }
                    if (drugMonographSection2.getListItems2() != null) {
                        for (DrugMonographSection.subSection next : drugMonographSection2.getListItems2()) {
                            CharSequence charSequence = next.item;
                            if (!(charSequence == null || charSequence.length() == 0)) {
                                CrossLink crossLink = null;
                                if (!(next.crossLinkType == null || next.crossLinkId == null)) {
                                    crossLink = new CrossLink(next.crossLinkType, next.crossLinkId);
                                }
                                CrossLink crossLink2 = crossLink;
                                int i3 = i2 > 0 ? size : 0;
                                CharSequence charSequence2 = next.item;
                                if (Util.hasHTMLContent(charSequence2 != null ? charSequence2.toString() : null)) {
                                    charSequence2 = Html.fromHtml(next.item);
                                }
                                if (charSequence2 == null) {
                                    charSequence2 = "";
                                }
                                LineItem lineItem = new LineItem(crossLink2, charSequence2, i3, false, false, false, 32, (DefaultConstructorMarker) null);
                                lineItem.setCustomIndentation(next.index);
                                arrayList.add(lineItem);
                            }
                        }
                    }
                    if (drugMonographSection2.getSubsections() != null) {
                        for (DrugMonographSection next2 : drugMonographSection2.getSubsections()) {
                            Intrinsics.checkNotNullExpressionValue(next2, "subSection");
                            CharSequence title3 = next2.getTitle();
                            if (!(title3 == null || title3.length() == 0)) {
                                String title4 = next2.getTitle();
                                if (title4 != null) {
                                    LineItem lineItem2 = r7;
                                    drugMonographSection = next2;
                                    LineItem lineItem3 = new LineItem((CrossLink) null, title4, size, false, true, false, 32, (DefaultConstructorMarker) null);
                                    LineItem lineItem4 = lineItem2;
                                    lineItem4.setCustomIndentation(drugMonographSection.getIndex());
                                    arrayList.add(lineItem4);
                                } else {
                                    throw new NullPointerException(str);
                                }
                            } else {
                                drugMonographSection = next2;
                            }
                            for (DrugMonographSection.subSection next3 : drugMonographSection.getListItems2()) {
                                CharSequence charSequence3 = next3.item;
                                if (!(charSequence3 == null || charSequence3.length() == 0)) {
                                    CrossLink crossLink3 = null;
                                    if (!(next3.crossLinkType == null || next3.crossLinkId == null)) {
                                        crossLink3 = new CrossLink(next3.crossLinkType, next3.crossLinkId);
                                    }
                                    CrossLink crossLink4 = crossLink3;
                                    CharSequence charSequence4 = next3.item;
                                    if (Util.hasHTMLContent(charSequence4 != null ? charSequence4.toString() : null)) {
                                        charSequence4 = Html.fromHtml(next3.item);
                                    }
                                    if (charSequence4 == null) {
                                        charSequence4 = "";
                                    }
                                    LineItem lineItem5 = r7;
                                    it = it2;
                                    LineItem lineItem6 = new LineItem(crossLink4, charSequence4, size, false, false, false, 32, (DefaultConstructorMarker) null);
                                    LineItem lineItem7 = lineItem5;
                                    lineItem7.setCustomIndentation(next3.index);
                                    arrayList.add(lineItem7);
                                } else {
                                    it = it2;
                                }
                                it2 = it;
                            }
                        }
                        continue;
                    }
                    it2 = it2;
                }
            }
            LineItem addNextSection = addNextSection(pair);
            InlineAdLineItem inlineAdLineItem = new InlineAdLineItem();
            if (addNextSection != null) {
                arrayList.add(inlineAdLineItem);
                arrayList.add(addNextSection);
            }
            this.list.setValue(arrayList);
        }
    }

    public final boolean isInteractionSection(Pair<Integer, Integer> pair) {
        List<DrugMonographIndexElement> list2 = this.drugSectionIndex;
        if (list2 == null || pair == null) {
            return false;
        }
        Intrinsics.checkNotNull(list2);
        int size = list2.size();
        int intValue = pair.getFirst().intValue();
        if (intValue < 0 || size < intValue) {
            return false;
        }
        List<DrugMonographIndexElement> list3 = this.drugSectionIndex;
        Intrinsics.checkNotNull(list3);
        return Intrinsics.areEqual((Object) list3.get(pair.getFirst().intValue()), (Object) DrugMonographIndexElement.INTERACTIONS);
    }

    public final boolean isImageSection(Pair<Integer, Integer> pair) {
        List<DrugMonographIndexElement> list2 = this.drugSectionIndex;
        if (list2 == null || pair == null) {
            return false;
        }
        Intrinsics.checkNotNull(list2);
        int size = list2.size();
        int intValue = pair.getFirst().intValue();
        if (intValue < 0 || size < intValue) {
            return false;
        }
        List<DrugMonographIndexElement> list3 = this.drugSectionIndex;
        Intrinsics.checkNotNull(list3);
        return Intrinsics.areEqual((Object) list3.get(pair.getFirst().intValue()), (Object) DrugMonographIndexElement.IMAGES);
    }

    public final boolean hasInteractions() {
        List<DrugMonographIndexElement> list2 = this.drugSectionIndex;
        if (list2 == null) {
            return false;
        }
        Intrinsics.checkNotNull(list2);
        for (DrugMonographIndexElement areEqual : list2) {
            if (Intrinsics.areEqual((Object) areEqual, (Object) DrugMonographIndexElement.INTERACTIONS)) {
                return true;
            }
        }
        return false;
    }

    public final boolean shouldShowTabs(Pair<Integer, Integer> pair) {
        List<DrugMonographIndexElement> list2;
        if (pair == null || pair.getFirst().intValue() <= -1 || (list2 = this.drugSectionIndex) == null) {
            return false;
        }
        Intrinsics.checkNotNull(list2);
        if (list2.size() < pair.getFirst().intValue()) {
            return false;
        }
        List<DrugMonographIndexElement> list3 = this.drugSectionIndex;
        Intrinsics.checkNotNull(list3);
        if (!Intrinsics.areEqual((Object) list3.get(pair.getFirst().intValue()), (Object) DrugMonographIndexElement.INTERACTIONS)) {
            List<DrugMonographIndexElement> list4 = this.drugSectionIndex;
            Intrinsics.checkNotNull(list4);
            if (list4.get(pair.getFirst().intValue()).indexes.size() > 1) {
                return true;
            }
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r2 = r2.getFirst();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.ArrayList<java.lang.Integer> getIndexes(kotlin.Pair<java.lang.Integer, java.lang.Integer> r2) {
        /*
            r1 = this;
            java.util.List<com.medscape.android.drugs.model.DrugMonographIndexElement> r0 = r1.drugSectionIndex
            if (r0 == 0) goto L_0x001f
            if (r2 == 0) goto L_0x0013
            java.lang.Object r2 = r2.getFirst()
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L_0x0013
            int r2 = r2.intValue()
            goto L_0x0014
        L_0x0013:
            r2 = 0
        L_0x0014:
            java.lang.Object r2 = r0.get(r2)
            com.medscape.android.drugs.model.DrugMonographIndexElement r2 = (com.medscape.android.drugs.model.DrugMonographIndexElement) r2
            if (r2 == 0) goto L_0x001f
            java.util.ArrayList<java.lang.Integer> r2 = r2.indexes
            goto L_0x0020
        L_0x001f:
            r2 = 0
        L_0x0020:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.repositories.DrugSectionContent.getIndexes(kotlin.Pair):java.util.ArrayList");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r2 = r5.getFirst();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isSectionEmpty(int r4, kotlin.Pair<java.lang.Integer, java.lang.Integer> r5) {
        /*
            r3 = this;
            java.util.List<com.medscape.android.drugs.model.DrugMonographIndexElement> r0 = r3.drugSectionIndex
            r1 = 1
            if (r0 == 0) goto L_0x0053
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            if (r5 == 0) goto L_0x0017
            java.lang.Object r2 = r5.getFirst()
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L_0x0017
            int r2 = r2.intValue()
            goto L_0x0018
        L_0x0017:
            r2 = 0
        L_0x0018:
            java.lang.Object r0 = r0.get(r2)
            com.medscape.android.drugs.model.DrugMonographIndexElement r0 = (com.medscape.android.drugs.model.DrugMonographIndexElement) r0
            java.util.ArrayList<java.lang.Integer> r0 = r0.indexes
            int r0 = r0.size()
            if (r4 >= r0) goto L_0x0053
            if (r5 == 0) goto L_0x0053
            java.util.List<com.medscape.android.drugs.model.DrugMonographIndexElement> r0 = r3.drugSectionIndex
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.Object r5 = r5.getFirst()
            java.lang.Number r5 = (java.lang.Number) r5
            int r5 = r5.intValue()
            java.lang.Object r5 = r0.get(r5)
            com.medscape.android.drugs.model.DrugMonographIndexElement r5 = (com.medscape.android.drugs.model.DrugMonographIndexElement) r5
            java.util.ArrayList<java.lang.Integer> r5 = r5.indexes
            java.lang.Object r4 = r5.get(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            java.util.Map<java.lang.Integer, ? extends java.util.List<com.medscape.android.drugs.model.DrugMonographSection>> r5 = r3.mSections
            java.lang.Object r4 = r5.get(r4)
            java.util.List r4 = (java.util.List) r4
            if (r4 == 0) goto L_0x0053
            boolean r1 = r4.isEmpty()
        L_0x0053:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.repositories.DrugSectionContent.isSectionEmpty(int, kotlin.Pair):boolean");
    }

    public final LineItem addNextSection(Pair<Integer, Integer> pair) {
        List<DrugMonographIndexElement> list2;
        LineItem lineItem = new LineItem((CrossLink) null, (CharSequence) null, 0, false, false, false, 62, (DefaultConstructorMarker) null);
        lineItem.setNextSection(true);
        if (pair == null || (list2 = this.drugSectionIndex) == null) {
            return null;
        }
        Intrinsics.checkNotNull(list2);
        if (!(list2.size() > pair.getFirst().intValue() + 1)) {
            return null;
        }
        List<DrugMonographIndexElement> list3 = this.drugSectionIndex;
        Intrinsics.checkNotNull(list3);
        String str = list3.get(pair.getFirst().intValue() + 1).title;
        Intrinsics.checkNotNullExpressionValue(str, "drugSectionIndex!![currentIndex.first + 1].title");
        lineItem.setText(str);
        return lineItem;
    }
}
