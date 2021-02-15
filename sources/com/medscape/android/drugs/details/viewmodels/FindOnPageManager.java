package com.medscape.android.drugs.details.viewmodels;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import androidx.lifecycle.MutableLiveData;
import com.medscape.android.contentviewer.model.ReferenceFindPosition;
import com.medscape.android.drugs.details.datamodels.InteractionListItem;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.drugs.details.datamodels.ScrollPosition;
import com.medscape.android.drugs.model.DrugFindHelper;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u000200J \u00101\u001a\u00020-2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020\fH\u0002J\u0006\u00106\u001a\u00020-J\u001c\u00107\u001a\u00020-2\u0006\u00108\u001a\u00020\u00132\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u001f0:J\u0016\u0010;\u001a\u00020-2\u0006\u0010<\u001a\u00020\f2\u0006\u0010=\u001a\u00020\fJ\u000e\u0010>\u001a\u00020-2\u0006\u0010?\u001a\u000200R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR&\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0015\"\u0004\b!\u0010\u0017R \u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0015\"\u0004\b%\u0010\u0017R\u001a\u0010&\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006@"}, d2 = {"Lcom/medscape/android/drugs/details/viewmodels/FindOnPageManager;", "", "context", "Landroid/content/Context;", "drugTextFinder", "Lcom/medscape/android/drugs/model/DrugFindHelper;", "(Landroid/content/Context;Lcom/medscape/android/drugs/model/DrugFindHelper;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "currentRow", "", "getCurrentRow", "()I", "setCurrentRow", "(I)V", "findCount", "Landroidx/lifecycle/MutableLiveData;", "", "getFindCount", "()Landroidx/lifecycle/MutableLiveData;", "setFindCount", "(Landroidx/lifecycle/MutableLiveData;)V", "finder", "getFinder", "()Lcom/medscape/android/drugs/model/DrugFindHelper;", "setFinder", "(Lcom/medscape/android/drugs/model/DrugFindHelper;)V", "items", "", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "getItems", "setItems", "scrollPosition", "Lcom/medscape/android/drugs/details/datamodels/ScrollPosition;", "getScrollPosition", "setScrollPosition", "utils", "Lcom/medscape/android/util/Util;", "getUtils", "()Lcom/medscape/android/util/Util;", "setUtils", "(Lcom/medscape/android/util/Util;)V", "cancelFind", "", "moveFind", "isForward", "", "removeBackGroundSpans", "ssb", "Landroid/text/SpannableStringBuilder;", "spanStart", "spanEnd", "scrollToFirstPosition", "startFind", "findQuery", "listOfItems", "", "updateFindCount", "currentPosition", "totalFound", "updateHighlight", "isYellow", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FindOnPageManager.kt */
public final class FindOnPageManager {
    private Context context;
    private int currentRow;
    private MutableLiveData<String> findCount;
    private DrugFindHelper finder;
    private MutableLiveData<List<LineItem>> items;
    private MutableLiveData<ScrollPosition> scrollPosition;
    private Util utils;

    public FindOnPageManager(Context context2, DrugFindHelper drugFindHelper) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.currentRow = -1;
        this.findCount = new MutableLiveData<>();
        this.scrollPosition = new MutableLiveData<>();
        this.items = new MutableLiveData<>();
        this.utils = new Util();
        if (drugFindHelper != null) {
            this.finder = drugFindHelper;
        } else {
            this.finder = new DrugFindHelper(this.context);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FindOnPageManager(Context context2, DrugFindHelper drugFindHelper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? null : drugFindHelper);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public final DrugFindHelper getFinder() {
        return this.finder;
    }

    public final void setFinder(DrugFindHelper drugFindHelper) {
        this.finder = drugFindHelper;
    }

    public final int getCurrentRow() {
        return this.currentRow;
    }

    public final void setCurrentRow(int i) {
        this.currentRow = i;
    }

    public final MutableLiveData<String> getFindCount() {
        return this.findCount;
    }

    public final void setFindCount(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.findCount = mutableLiveData;
    }

    public final MutableLiveData<ScrollPosition> getScrollPosition() {
        return this.scrollPosition;
    }

    public final void setScrollPosition(MutableLiveData<ScrollPosition> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.scrollPosition = mutableLiveData;
    }

    public final MutableLiveData<List<LineItem>> getItems() {
        return this.items;
    }

    public final void setItems(MutableLiveData<List<LineItem>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.items = mutableLiveData;
    }

    public final Util getUtils() {
        return this.utils;
    }

    public final void setUtils(Util util) {
        Intrinsics.checkNotNullParameter(util, "<set-?>");
        this.utils = util;
    }

    public final void startFind(String str, List<LineItem> list) {
        ArrayList<ReferenceFindPosition> findPositions;
        HashSet<Integer> findRowsInsideDetail;
        HashSet<Integer> findRowsInsideDetail2;
        CharSequence charSequence;
        CharSequence charSequence2;
        Intrinsics.checkNotNullParameter(str, "findQuery");
        Intrinsics.checkNotNullParameter(list, "listOfItems");
        DrugFindHelper drugFindHelper = this.finder;
        if (drugFindHelper != null) {
            drugFindHelper.resetFind();
        }
        DrugFindHelper drugFindHelper2 = this.finder;
        if (drugFindHelper2 != null) {
            drugFindHelper2.setFindQuery(str);
        }
        this.currentRow = -1;
        List arrayList = new ArrayList();
        DrugFindHelper drugFindHelper3 = this.finder;
        int i = 0;
        if (drugFindHelper3 != null && drugFindHelper3.isInFindMode()) {
            int i2 = 0;
            for (LineItem lineItem : list) {
                if (!(lineItem.getText().length() == 0)) {
                    DrugFindHelper drugFindHelper4 = this.finder;
                    if (drugFindHelper4 == null || (charSequence = drugFindHelper4.addFindPositionMap(lineItem.getText(), i2, 0, false)) == null) {
                        charSequence = "";
                    }
                    lineItem.setText(charSequence);
                    if (lineItem instanceof InteractionListItem) {
                        InteractionListItem interactionListItem = (InteractionListItem) lineItem;
                        DrugFindHelper drugFindHelper5 = this.finder;
                        if (drugFindHelper5 == null || (charSequence2 = drugFindHelper5.addFindPositionMap(interactionListItem.getDetails(), i2, 0, true)) == null) {
                            charSequence2 = "";
                        }
                        interactionListItem.setDetails(charSequence2);
                    }
                }
                arrayList.add(lineItem);
                i2++;
            }
        }
        DrugFindHelper drugFindHelper6 = this.finder;
        if (!(drugFindHelper6 == null || (findRowsInsideDetail = drugFindHelper6.getFindRowsInsideDetail()) == null || findRowsInsideDetail.isEmpty())) {
            DrugFindHelper drugFindHelper7 = this.finder;
            for (Integer num : (drugFindHelper7 == null || (findRowsInsideDetail2 = drugFindHelper7.getFindRowsInsideDetail()) == null) ? SetsKt.emptySet() : findRowsInsideDetail2) {
                Intrinsics.checkNotNullExpressionValue(num, ContentParser.ITEM);
                if (arrayList.get(num.intValue()) instanceof InteractionListItem) {
                    Object obj = arrayList.get(num.intValue());
                    if (obj != null) {
                        ((InteractionListItem) obj).setDetailVisible(true);
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InteractionListItem");
                    }
                }
            }
        }
        this.items.setValue(arrayList);
        DrugFindHelper drugFindHelper8 = this.finder;
        int currentFindPos = drugFindHelper8 != null ? drugFindHelper8.getCurrentFindPos() : 0;
        DrugFindHelper drugFindHelper9 = this.finder;
        if (!(drugFindHelper9 == null || (findPositions = drugFindHelper9.getFindPositions()) == null)) {
            i = findPositions.size();
        }
        updateFindCount(currentFindPos, i);
        scrollToFirstPosition();
    }

    public final void cancelFind() {
        DrugFindHelper drugFindHelper = this.finder;
        if (drugFindHelper != null && drugFindHelper.isInFindMode()) {
            DrugFindHelper drugFindHelper2 = this.finder;
            if (drugFindHelper2 != null) {
                drugFindHelper2.resetFind();
            }
            this.currentRow = -1;
            this.findCount.setValue("");
            this.scrollPosition.setValue(null);
        }
    }

    public final void updateFindCount(int i, int i2) {
        int i3 = 0;
        if (i2 <= 0 || i <= -1) {
            i2 = 0;
        } else {
            int i4 = i + 1;
            i3 = i4 > i2 ? i2 : i4;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i3);
        sb.append('/');
        sb.append(i2);
        String sb2 = sb.toString();
        if (sb2.length() > 6) {
            sb2 = StringsKt.replace$default(sb2, "/", "/\n", false, 4, (Object) null);
        }
        this.findCount.setValue(sb2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r0 = r0.getCurrentFindItem();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void scrollToFirstPosition() {
        /*
            r4 = this;
            com.medscape.android.drugs.model.DrugFindHelper r0 = r4.finder
            if (r0 == 0) goto L_0x0048
            boolean r0 = r0.canScrolltoFind()
            r1 = 1
            if (r0 != r1) goto L_0x0048
            com.medscape.android.drugs.model.DrugFindHelper r0 = r4.finder
            if (r0 == 0) goto L_0x001a
            com.medscape.android.contentviewer.model.ReferenceFindPosition r0 = r0.getCurrentFindItem()
            if (r0 == 0) goto L_0x001a
            int r0 = r0.getContentRow()
            goto L_0x001b
        L_0x001a:
            r0 = -1
        L_0x001b:
            int r1 = r4.currentRow
            if (r1 == r0) goto L_0x0048
            com.medscape.android.util.Util r1 = r4.utils
            android.content.Context r2 = r4.context
            r3 = 55
            float r1 = r1.dpToPx(r2, r3)
            float r1 = -r1
            androidx.lifecycle.MutableLiveData<java.util.List<com.medscape.android.drugs.details.datamodels.LineItem>> r2 = r4.items
            java.lang.Object r2 = r2.getValue()
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x003b
            int r2 = r2.size()
            if (r0 != r2) goto L_0x003b
            r1 = 0
        L_0x003b:
            androidx.lifecycle.MutableLiveData<com.medscape.android.drugs.details.datamodels.ScrollPosition> r2 = r4.scrollPosition
            com.medscape.android.drugs.details.datamodels.ScrollPosition r3 = new com.medscape.android.drugs.details.datamodels.ScrollPosition
            int r1 = (int) r1
            r3.<init>(r0, r1)
            r2.setValue(r3)
            r4.currentRow = r0
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.viewmodels.FindOnPageManager.scrollToFirstPosition():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        r2 = r2.getFindPositions();
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void moveFind(boolean r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 1
            if (r6 == 0) goto L_0x000e
            com.medscape.android.drugs.model.DrugFindHelper r6 = r5.finder
            if (r6 == 0) goto L_0x001c
            int r6 = r6.getCurrentFindPos()
            int r6 = r6 + r1
            goto L_0x0017
        L_0x000e:
            com.medscape.android.drugs.model.DrugFindHelper r6 = r5.finder
            if (r6 == 0) goto L_0x001c
            int r6 = r6.getCurrentFindPos()
            int r6 = r6 - r1
        L_0x0017:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            goto L_0x001d
        L_0x001c:
            r6 = r0
        L_0x001d:
            com.medscape.android.drugs.model.DrugFindHelper r2 = r5.finder
            r3 = 0
            if (r2 == 0) goto L_0x002d
            java.util.ArrayList r2 = r2.getFindPositions()
            if (r2 == 0) goto L_0x002d
            int r2 = r2.size()
            goto L_0x002e
        L_0x002d:
            r2 = 0
        L_0x002e:
            kotlin.ranges.IntRange r2 = kotlin.ranges.RangesKt.until((int) r3, (int) r2)
            if (r6 == 0) goto L_0x0040
            int r4 = r6.intValue()
            boolean r2 = r2.contains((int) r4)
            if (r2 == 0) goto L_0x0040
            r2 = 1
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            if (r2 == 0) goto L_0x0095
            r5.updateHighlight(r1)
            com.medscape.android.drugs.model.DrugFindHelper r1 = r5.finder
            if (r1 == 0) goto L_0x0055
            if (r6 == 0) goto L_0x0051
            int r6 = r6.intValue()
            goto L_0x0052
        L_0x0051:
            r6 = -1
        L_0x0052:
            r1.setCurrentFindPos(r6)
        L_0x0055:
            com.medscape.android.drugs.model.DrugFindHelper r6 = r5.finder
            if (r6 == 0) goto L_0x0074
            if (r6 == 0) goto L_0x0071
            java.util.ArrayList r1 = r6.getFindPositions()
            if (r1 == 0) goto L_0x0071
            com.medscape.android.drugs.model.DrugFindHelper r0 = r5.finder
            if (r0 == 0) goto L_0x006a
            int r0 = r0.getCurrentFindPos()
            goto L_0x006b
        L_0x006a:
            r0 = 0
        L_0x006b:
            java.lang.Object r0 = r1.get(r0)
            com.medscape.android.contentviewer.model.ReferenceFindPosition r0 = (com.medscape.android.contentviewer.model.ReferenceFindPosition) r0
        L_0x0071:
            r6.setCurrentFindItem(r0)
        L_0x0074:
            r5.updateHighlight(r3)
            com.medscape.android.drugs.model.DrugFindHelper r6 = r5.finder
            if (r6 == 0) goto L_0x0080
            int r6 = r6.getCurrentFindPos()
            goto L_0x0081
        L_0x0080:
            r6 = 0
        L_0x0081:
            com.medscape.android.drugs.model.DrugFindHelper r0 = r5.finder
            if (r0 == 0) goto L_0x008f
            java.util.ArrayList r0 = r0.getFindPositions()
            if (r0 == 0) goto L_0x008f
            int r3 = r0.size()
        L_0x008f:
            r5.updateFindCount(r6, r3)
            r5.scrollToFirstPosition()
        L_0x0095:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.viewmodels.FindOnPageManager.moveFind(boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r0.getCurrentFindItem();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateHighlight(boolean r11) {
        /*
            r10 = this;
            com.medscape.android.drugs.model.DrugFindHelper r0 = r10.finder
            r1 = 0
            if (r0 == 0) goto L_0x000e
            com.medscape.android.contentviewer.model.ReferenceFindPosition r0 = r0.getCurrentFindItem()
            if (r0 == 0) goto L_0x000e
            int r0 = r0.contentRow
            goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            androidx.lifecycle.MutableLiveData<java.util.List<com.medscape.android.drugs.details.datamodels.LineItem>> r2 = r10.items
            java.lang.Object r2 = r2.getValue()
            if (r2 == 0) goto L_0x00d6
            java.util.List r2 = kotlin.jvm.internal.TypeIntrinsics.asMutableList(r2)
            if (r2 == 0) goto L_0x00d5
            int r3 = r2.size()
            if (r0 >= 0) goto L_0x0025
            goto L_0x00d5
        L_0x0025:
            if (r3 <= r0) goto L_0x00d5
            java.lang.Object r3 = r2.get(r0)
            com.medscape.android.drugs.details.datamodels.LineItem r3 = (com.medscape.android.drugs.details.datamodels.LineItem) r3
            if (r11 == 0) goto L_0x0033
            r11 = 2131100280(0x7f060278, float:1.7812937E38)
            goto L_0x0036
        L_0x0033:
            r11 = 2131100129(0x7f0601e1, float:1.781263E38)
        L_0x0036:
            com.medscape.android.drugs.model.DrugFindHelper r4 = r10.finder
            java.lang.String r5 = "null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InteractionListItem"
            r6 = 1
            if (r4 == 0) goto L_0x0057
            com.medscape.android.contentviewer.model.ReferenceFindPosition r4 = r4.getCurrentFindItem()
            if (r4 == 0) goto L_0x0057
            boolean r4 = r4.isDetailText
            if (r4 != r6) goto L_0x0057
            if (r3 == 0) goto L_0x0051
            r4 = r3
            com.medscape.android.drugs.details.datamodels.InteractionListItem r4 = (com.medscape.android.drugs.details.datamodels.InteractionListItem) r4
            java.lang.CharSequence r4 = r4.getDetails()
            goto L_0x005b
        L_0x0051:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            r11.<init>(r5)
            throw r11
        L_0x0057:
            java.lang.CharSequence r4 = r3.getText()
        L_0x005b:
            int r7 = r4.length()
            if (r7 <= 0) goto L_0x0063
            r7 = 1
            goto L_0x0064
        L_0x0063:
            r7 = 0
        L_0x0064:
            if (r7 == 0) goto L_0x00d5
            com.medscape.android.drugs.model.DrugFindHelper r7 = r10.finder
            if (r7 == 0) goto L_0x0073
            com.medscape.android.contentviewer.model.ReferenceFindPosition r7 = r7.getCurrentFindItem()
            if (r7 == 0) goto L_0x0073
            int r7 = r7.contentIndex
            goto L_0x0074
        L_0x0073:
            r7 = 0
        L_0x0074:
            com.medscape.android.drugs.model.DrugFindHelper r8 = r10.finder
            if (r8 == 0) goto L_0x0081
            com.medscape.android.contentviewer.model.ReferenceFindPosition r8 = r8.getCurrentFindItem()
            if (r8 == 0) goto L_0x0081
            int r8 = r8.contentIndex
            goto L_0x0082
        L_0x0081:
            r8 = 0
        L_0x0082:
            com.medscape.android.drugs.model.DrugFindHelper r9 = r10.finder
            if (r9 == 0) goto L_0x0090
            java.lang.String r9 = r9.getFindQuery()
            if (r9 == 0) goto L_0x0090
            int r1 = r9.length()
        L_0x0090:
            int r8 = r8 + r1
            android.text.SpannableStringBuilder r1 = new android.text.SpannableStringBuilder
            r1.<init>(r4)
            r10.removeBackGroundSpans(r1, r7, r8)
            android.content.Context r4 = r10.context
            int r11 = androidx.core.content.ContextCompat.getColor(r4, r11)
            android.text.style.BackgroundColorSpan r4 = new android.text.style.BackgroundColorSpan
            r4.<init>(r11)
            r11 = 18
            r1.setSpan(r4, r7, r8, r11)
            com.medscape.android.drugs.model.DrugFindHelper r11 = r10.finder
            if (r11 == 0) goto L_0x00c8
            com.medscape.android.contentviewer.model.ReferenceFindPosition r11 = r11.getCurrentFindItem()
            if (r11 == 0) goto L_0x00c8
            boolean r11 = r11.isDetailText
            if (r11 != r6) goto L_0x00c8
            if (r3 == 0) goto L_0x00c2
            r11 = r3
            com.medscape.android.drugs.details.datamodels.InteractionListItem r11 = (com.medscape.android.drugs.details.datamodels.InteractionListItem) r11
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r11.setDetails(r1)
            goto L_0x00cd
        L_0x00c2:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            r11.<init>(r5)
            throw r11
        L_0x00c8:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r3.setText(r1)
        L_0x00cd:
            r2.set(r0, r3)
            androidx.lifecycle.MutableLiveData<java.util.List<com.medscape.android.drugs.details.datamodels.LineItem>> r11 = r10.items
            r11.setValue(r2)
        L_0x00d5:
            return
        L_0x00d6:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.collections.MutableList<com.medscape.android.drugs.details.datamodels.LineItem>"
            r11.<init>(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.viewmodels.FindOnPageManager.updateHighlight(boolean):void");
    }

    private final void removeBackGroundSpans(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        BackgroundColorSpan[] backgroundColorSpanArr = (BackgroundColorSpan[]) spannableStringBuilder.getSpans(i, i2, BackgroundColorSpan.class);
        if (backgroundColorSpanArr != null && backgroundColorSpanArr.length > 0) {
            for (BackgroundColorSpan removeSpan : backgroundColorSpanArr) {
                spannableStringBuilder.removeSpan(removeSpan);
            }
        }
    }
}
