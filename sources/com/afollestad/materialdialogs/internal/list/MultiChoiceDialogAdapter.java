package com.afollestad.materialdialogs.internal.list;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.R;
import com.afollestad.materialdialogs.WhichButton;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.list.DialogListExtKt;
import com.afollestad.materialdialogs.utils.ColorsKt;
import com.afollestad.materialdialogs.utils.IntArraysKt;
import com.afollestad.materialdialogs.utils.MDUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012_\u0012\u0004\u0012\u00020\u0004\u0012U\u0012S\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0005j\u0002`\u000f0\u0003B\u0001\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0011\u001a\u00020\n\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012W\u0010\u0015\u001aS\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0005j\u0002`\u000f¢\u0006\u0002\u0010\u0016J\b\u0010$\u001a\u00020\u000eH\u0016J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010&\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020(H\u0016J\u0015\u0010+\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020(H\u0000¢\u0006\u0002\b,J\u0018\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u00020(H\u0016J&\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u00020(2\f\u00100\u001a\b\u0012\u0004\u0012\u00020201H\u0016J\u0018\u00103\u001a\u00020\u00022\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020(H\u0016J\b\u00107\u001a\u00020\u000eH\u0016Jo\u00108\u001a\u00020\u000e2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\f2W\u00109\u001aS\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0005j\u0002`\u000fH\u0016J\b\u0010:\u001a\u00020\u000eH\u0016J\u0010\u0010;\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010<\u001a\u00020\u000eH\u0016J\u0010\u0010=\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u000e\u0010\u0014\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\t\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fRk\u0010\u0015\u001aS\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0005j\u0002`\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/afollestad/materialdialogs/internal/list/MultiChoiceDialogAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/afollestad/materialdialogs/internal/list/MultiChoiceViewHolder;", "Lcom/afollestad/materialdialogs/internal/list/DialogAdapter;", "", "Lkotlin/Function3;", "Lcom/afollestad/materialdialogs/MaterialDialog;", "Lkotlin/ParameterName;", "name", "dialog", "", "indices", "", "items", "", "Lcom/afollestad/materialdialogs/list/MultiChoiceListener;", "disabledItems", "initialSelection", "waitForActionButton", "", "allowEmptySelection", "selection", "(Lcom/afollestad/materialdialogs/MaterialDialog;Ljava/util/List;[I[IZZLkotlin/jvm/functions/Function3;)V", "value", "currentSelection", "setCurrentSelection", "([I)V", "disabledIndices", "getItems$core", "()Ljava/util/List;", "setItems$core", "(Ljava/util/List;)V", "getSelection$core", "()Lkotlin/jvm/functions/Function3;", "setSelection$core", "(Lkotlin/jvm/functions/Function3;)V", "checkAllItems", "checkItems", "disableItems", "getItemCount", "", "isItemChecked", "index", "itemClicked", "itemClicked$core", "onBindViewHolder", "holder", "position", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "positiveButtonClicked", "replaceItems", "listener", "toggleAllChecked", "toggleItems", "uncheckAllItems", "uncheckItems", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: MultiChoiceDialogAdapter.kt */
public final class MultiChoiceDialogAdapter extends RecyclerView.Adapter<MultiChoiceViewHolder> implements DialogAdapter<CharSequence, Function3<? super MaterialDialog, ? super int[], ? super List<? extends CharSequence>, ? extends Unit>> {
    private final boolean allowEmptySelection;
    private int[] currentSelection;
    private MaterialDialog dialog;
    private int[] disabledIndices;
    private List<? extends CharSequence> items;
    private Function3<? super MaterialDialog, ? super int[], ? super List<? extends CharSequence>, Unit> selection;
    private final boolean waitForActionButton;

    public final List<CharSequence> getItems$core() {
        return this.items;
    }

    public final void setItems$core(List<? extends CharSequence> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.items = list;
    }

    public final Function3<MaterialDialog, int[], List<? extends CharSequence>, Unit> getSelection$core() {
        return this.selection;
    }

    public final void setSelection$core(Function3<? super MaterialDialog, ? super int[], ? super List<? extends CharSequence>, Unit> function3) {
        this.selection = function3;
    }

    public MultiChoiceDialogAdapter(MaterialDialog materialDialog, List<? extends CharSequence> list, int[] iArr, int[] iArr2, boolean z, boolean z2, Function3<? super MaterialDialog, ? super int[], ? super List<? extends CharSequence>, Unit> function3) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "dialog");
        Intrinsics.checkParameterIsNotNull(list, FirebaseAnalytics.Param.ITEMS);
        Intrinsics.checkParameterIsNotNull(iArr2, "initialSelection");
        this.dialog = materialDialog;
        this.items = list;
        this.waitForActionButton = z;
        this.allowEmptySelection = z2;
        this.selection = function3;
        this.currentSelection = iArr2;
        this.disabledIndices = iArr == null ? new int[0] : iArr;
    }

    private final void setCurrentSelection(int[] iArr) {
        int[] iArr2 = this.currentSelection;
        this.currentSelection = iArr;
        for (int i : iArr2) {
            if (!ArraysKt.contains(iArr, i)) {
                notifyItemChanged(i, UncheckPayload.INSTANCE);
            }
        }
        for (int i2 : iArr) {
            if (!ArraysKt.contains(iArr2, i2)) {
                notifyItemChanged(i2, CheckPayload.INSTANCE);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4, types: [int] */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        if ((!(r5.currentSelection.length == 0)) != false) goto L_0x0049;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void itemClicked$core(int r6) {
        /*
            r5 = this;
            int[] r0 = r5.currentSelection
            java.util.List r0 = kotlin.collections.ArraysKt.toMutableList((int[]) r0)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            boolean r1 = r0.contains(r1)
            if (r1 == 0) goto L_0x0018
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r0.remove(r6)
            goto L_0x001f
        L_0x0018:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r0.add(r6)
        L_0x001f:
            java.util.Collection r0 = (java.util.Collection) r0
            int[] r6 = kotlin.collections.CollectionsKt.toIntArray(r0)
            r5.setCurrentSelection(r6)
            boolean r6 = r5.waitForActionButton
            r0 = 0
            if (r6 == 0) goto L_0x004e
            com.afollestad.materialdialogs.MaterialDialog r6 = r5.dialog
            boolean r6 = com.afollestad.materialdialogs.actions.DialogActionExtKt.hasActionButtons(r6)
            if (r6 == 0) goto L_0x004e
            com.afollestad.materialdialogs.MaterialDialog r6 = r5.dialog
            com.afollestad.materialdialogs.WhichButton r1 = com.afollestad.materialdialogs.WhichButton.POSITIVE
            boolean r2 = r5.allowEmptySelection
            r3 = 1
            if (r2 != 0) goto L_0x0049
            int[] r2 = r5.currentSelection
            int r2 = r2.length
            if (r2 != 0) goto L_0x0045
            r2 = 1
            goto L_0x0046
        L_0x0045:
            r2 = 0
        L_0x0046:
            r2 = r2 ^ r3
            if (r2 == 0) goto L_0x004a
        L_0x0049:
            r0 = 1
        L_0x004a:
            com.afollestad.materialdialogs.actions.DialogActionExtKt.setActionButtonEnabled(r6, r1, r0)
            goto L_0x008b
        L_0x004e:
            java.util.List<? extends java.lang.CharSequence> r6 = r5.items
            int[] r1 = r5.currentSelection
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.List r2 = (java.util.List) r2
            int r3 = r1.length
        L_0x005a:
            if (r0 >= r3) goto L_0x0068
            r4 = r1[r0]
            java.lang.Object r4 = r6.get(r4)
            r2.add(r4)
            int r0 = r0 + 1
            goto L_0x005a
        L_0x0068:
            kotlin.jvm.functions.Function3<? super com.afollestad.materialdialogs.MaterialDialog, ? super int[], ? super java.util.List<? extends java.lang.CharSequence>, kotlin.Unit> r6 = r5.selection
            if (r6 == 0) goto L_0x0076
            com.afollestad.materialdialogs.MaterialDialog r0 = r5.dialog
            int[] r1 = r5.currentSelection
            java.lang.Object r6 = r6.invoke(r0, r1, r2)
            kotlin.Unit r6 = (kotlin.Unit) r6
        L_0x0076:
            com.afollestad.materialdialogs.MaterialDialog r6 = r5.dialog
            boolean r6 = r6.getAutoDismissEnabled()
            if (r6 == 0) goto L_0x008b
            com.afollestad.materialdialogs.MaterialDialog r6 = r5.dialog
            boolean r6 = com.afollestad.materialdialogs.actions.DialogActionExtKt.hasActionButtons(r6)
            if (r6 != 0) goto L_0x008b
            com.afollestad.materialdialogs.MaterialDialog r6 = r5.dialog
            r6.dismiss()
        L_0x008b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.internal.list.MultiChoiceDialogAdapter.itemClicked$core(int):void");
    }

    public MultiChoiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        MultiChoiceViewHolder multiChoiceViewHolder = new MultiChoiceViewHolder(MDUtil.INSTANCE.inflate(viewGroup, this.dialog.getWindowContext(), R.layout.md_listitem_multichoice), this);
        MDUtil.maybeSetTextColor$default(MDUtil.INSTANCE, multiChoiceViewHolder.getTitleView(), this.dialog.getWindowContext(), Integer.valueOf(R.attr.md_color_content), (Integer) null, 4, (Object) null);
        int[] resolveColors$default = ColorsKt.resolveColors$default(this.dialog, new int[]{R.attr.md_color_widget, R.attr.md_color_widget_unchecked}, (Function1) null, 2, (Object) null);
        CompoundButtonCompat.setButtonTintList(multiChoiceViewHolder.getControlView(), MDUtil.INSTANCE.createColorSelector(this.dialog.getWindowContext(), resolveColors$default[1], resolveColors$default[0]));
        return multiChoiceViewHolder;
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void onBindViewHolder(MultiChoiceViewHolder multiChoiceViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(multiChoiceViewHolder, "holder");
        multiChoiceViewHolder.setEnabled(!ArraysKt.contains(this.disabledIndices, i));
        multiChoiceViewHolder.getControlView().setChecked(ArraysKt.contains(this.currentSelection, i));
        multiChoiceViewHolder.getTitleView().setText((CharSequence) this.items.get(i));
        View view = multiChoiceViewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        view.setBackground(DialogListExtKt.getItemSelector(this.dialog));
        if (this.dialog.getBodyFont() != null) {
            multiChoiceViewHolder.getTitleView().setTypeface(this.dialog.getBodyFont());
        }
    }

    public void onBindViewHolder(MultiChoiceViewHolder multiChoiceViewHolder, int i, List<Object> list) {
        Intrinsics.checkParameterIsNotNull(multiChoiceViewHolder, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        Object firstOrNull = CollectionsKt.firstOrNull(list);
        if (Intrinsics.areEqual(firstOrNull, (Object) CheckPayload.INSTANCE)) {
            multiChoiceViewHolder.getControlView().setChecked(true);
        } else if (Intrinsics.areEqual(firstOrNull, (Object) UncheckPayload.INSTANCE)) {
            multiChoiceViewHolder.getControlView().setChecked(false);
        } else {
            RecyclerView.ViewHolder viewHolder = multiChoiceViewHolder;
            super.onBindViewHolder(viewHolder, i, list);
            super.onBindViewHolder(viewHolder, i, list);
        }
    }

    public void positiveButtonClicked() {
        if (!this.allowEmptySelection) {
            if (!(!(this.currentSelection.length == 0))) {
                return;
            }
        }
        List<? extends CharSequence> list = this.items;
        int[] iArr = this.currentSelection;
        List arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.add(list.get(i));
        }
        Function3<? super MaterialDialog, ? super int[], ? super List<? extends CharSequence>, Unit> function3 = this.selection;
        if (function3 != null) {
            Unit invoke = function3.invoke(this.dialog, this.currentSelection, arrayList);
        }
    }

    public void replaceItems(List<? extends CharSequence> list, Function3<? super MaterialDialog, ? super int[], ? super List<? extends CharSequence>, Unit> function3) {
        Intrinsics.checkParameterIsNotNull(list, FirebaseAnalytics.Param.ITEMS);
        this.items = list;
        if (function3 != null) {
            this.selection = function3;
        }
        notifyDataSetChanged();
    }

    public void disableItems(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "indices");
        this.disabledIndices = iArr;
        notifyDataSetChanged();
    }

    public void checkItems(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "indices");
        int[] iArr2 = this.currentSelection;
        Collection arrayList = new ArrayList();
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            if (i2 >= 0 && i2 < this.items.size()) {
                if (true ^ ArraysKt.contains(iArr2, i2)) {
                    arrayList.add(Integer.valueOf(i2));
                }
                i++;
            } else {
                throw new IllegalStateException(("Index " + i2 + " is out of range for this adapter of " + this.items.size() + " items.").toString());
            }
        }
        setCurrentSelection(IntArraysKt.appendAll(this.currentSelection, (List) arrayList));
        if (iArr2.length == 0) {
            z = true;
        }
        if (z) {
            DialogActionExtKt.setActionButtonEnabled(this.dialog, WhichButton.POSITIVE, true);
        }
    }

    public void uncheckItems(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "indices");
        int[] iArr2 = this.currentSelection;
        Collection arrayList = new ArrayList();
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i < length) {
                int i2 = iArr[i];
                if (i2 < 0 || i2 >= this.items.size()) {
                    z2 = false;
                }
                if (z2) {
                    if (ArraysKt.contains(iArr2, i2)) {
                        arrayList.add(Integer.valueOf(i2));
                    }
                    i++;
                } else {
                    throw new IllegalStateException(("Index " + i2 + " is out of range for this adapter of " + this.items.size() + " items.").toString());
                }
            } else {
                int[] removeAll = IntArraysKt.removeAll(this.currentSelection, (List) arrayList);
                if (removeAll.length == 0) {
                    z = true;
                }
                if (z) {
                    DialogActionExtKt.setActionButtonEnabled(this.dialog, WhichButton.POSITIVE, this.allowEmptySelection);
                }
                setCurrentSelection(removeAll);
                return;
            }
        }
    }

    public void toggleItems(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "indices");
        List<Integer> mutableList = ArraysKt.toMutableList(this.currentSelection);
        boolean z = false;
        for (int i : iArr) {
            if (!ArraysKt.contains(this.disabledIndices, i)) {
                if (mutableList.contains(Integer.valueOf(i))) {
                    mutableList.remove(Integer.valueOf(i));
                } else {
                    mutableList.add(Integer.valueOf(i));
                }
            }
        }
        int[] intArray = CollectionsKt.toIntArray(mutableList);
        MaterialDialog materialDialog = this.dialog;
        WhichButton whichButton = WhichButton.POSITIVE;
        boolean z2 = true;
        if (intArray.length == 0) {
            z = true;
        }
        if (z) {
            z2 = this.allowEmptySelection;
        }
        DialogActionExtKt.setActionButtonEnabled(materialDialog, whichButton, z2);
        setCurrentSelection(intArray);
    }

    public void checkAllItems() {
        int[] iArr = this.currentSelection;
        int itemCount = getItemCount();
        int[] iArr2 = new int[itemCount];
        boolean z = false;
        for (int i = 0; i < itemCount; i++) {
            iArr2[i] = i;
        }
        Collection arrayList = new ArrayList();
        for (int i2 = 0; i2 < itemCount; i2++) {
            int i3 = iArr2[i2];
            if (true ^ ArraysKt.contains(iArr, i3)) {
                arrayList.add(Integer.valueOf(i3));
            }
        }
        setCurrentSelection(IntArraysKt.appendAll(this.currentSelection, (List) arrayList));
        if (iArr.length == 0) {
            z = true;
        }
        if (z) {
            DialogActionExtKt.setActionButtonEnabled(this.dialog, WhichButton.POSITIVE, true);
        }
    }

    public void uncheckAllItems() {
        setCurrentSelection(new int[0]);
        DialogActionExtKt.setActionButtonEnabled(this.dialog, WhichButton.POSITIVE, this.allowEmptySelection);
    }

    public void toggleAllChecked() {
        if (this.currentSelection.length == 0) {
            checkAllItems();
        } else {
            uncheckAllItems();
        }
    }

    public boolean isItemChecked(int i) {
        return ArraysKt.contains(this.currentSelection, i);
    }
}
