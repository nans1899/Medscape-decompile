package com.afollestad.materialdialogs.list;

import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.list.DialogAdapter;
import com.afollestad.materialdialogs.internal.list.MultiChoiceDialogAdapter;
import com.afollestad.materialdialogs.utils.MDUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005\u001a´\u0001\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2Y\b\u0002\u0010\u0011\u001aS\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0004\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012j\u0002`\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a\n\u0010\u0018\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010\u001a\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0001\u0010\u001c\u001a\u00020\u0002*\u00020\u00022\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052Y\b\u0002\u0010\u0011\u001aS\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0004\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012j\u0002`\u0016¢\u0006\u0002\u0010\u001d¨\u0006\u001e"}, d2 = {"checkAllItems", "", "Lcom/afollestad/materialdialogs/MaterialDialog;", "checkItems", "indices", "", "listItemsMultiChoice", "res", "", "items", "", "", "disabledIndices", "initialSelection", "waitForPositiveButton", "", "allowEmptySelection", "selection", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "dialog", "Lcom/afollestad/materialdialogs/list/MultiChoiceListener;", "(Lcom/afollestad/materialdialogs/MaterialDialog;Ljava/lang/Integer;Ljava/util/List;[I[IZZLkotlin/jvm/functions/Function3;)Lcom/afollestad/materialdialogs/MaterialDialog;", "toggleAllItemsChecked", "toggleItemsChecked", "uncheckAllItems", "uncheckItems", "updateListItemsMultiChoice", "(Lcom/afollestad/materialdialogs/MaterialDialog;Ljava/lang/Integer;Ljava/util/List;[ILkotlin/jvm/functions/Function3;)Lcom/afollestad/materialdialogs/MaterialDialog;", "core"}, k = 2, mv = {1, 1, 16})
/* compiled from: DialogMultiChoiceExt.kt */
public final class DialogMultiChoiceExtKt {
    public static /* synthetic */ MaterialDialog listItemsMultiChoice$default(MaterialDialog materialDialog, Integer num, List list, int[] iArr, int[] iArr2, boolean z, boolean z2, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            list = null;
        }
        List list2 = list;
        if ((i & 4) != 0) {
            iArr = null;
        }
        int[] iArr3 = iArr;
        if ((i & 8) != 0) {
            iArr2 = new int[0];
        }
        int[] iArr4 = iArr2;
        boolean z3 = (i & 16) != 0 ? true : z;
        boolean z4 = (i & 32) != 0 ? false : z2;
        if ((i & 64) != 0) {
            function3 = null;
        }
        return listItemsMultiChoice(materialDialog, num, list2, iArr3, iArr4, z3, z4, function3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        if ((!(r4.length == 0)) != false) goto L_0x004e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.afollestad.materialdialogs.MaterialDialog listItemsMultiChoice(com.afollestad.materialdialogs.MaterialDialog r10, java.lang.Integer r11, java.util.List<? extends java.lang.CharSequence> r12, int[] r13, int[] r14, boolean r15, boolean r16, kotlin.jvm.functions.Function3<? super com.afollestad.materialdialogs.MaterialDialog, ? super int[], ? super java.util.List<? extends java.lang.CharSequence>, kotlin.Unit> r17) {
        /*
            r8 = r10
            r0 = r11
            r1 = r12
            r4 = r14
            java.lang.String r2 = "$this$listItemsMultiChoice"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r2)
            java.lang.String r2 = "initialSelection"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r2)
            com.afollestad.materialdialogs.utils.MDUtil r2 = com.afollestad.materialdialogs.utils.MDUtil.INSTANCE
            java.lang.String r3 = "listItemsMultiChoice"
            r2.assertOneSet(r3, r12, r11)
            if (r1 == 0) goto L_0x0019
            r2 = r1
            goto L_0x0027
        L_0x0019:
            com.afollestad.materialdialogs.utils.MDUtil r2 = com.afollestad.materialdialogs.utils.MDUtil.INSTANCE
            android.content.Context r3 = r10.getWindowContext()
            java.lang.String[] r2 = r2.getStringArray(r3, r11)
            java.util.List r2 = kotlin.collections.ArraysKt.toList((T[]) r2)
        L_0x0027:
            androidx.recyclerview.widget.RecyclerView$Adapter r3 = com.afollestad.materialdialogs.list.DialogListExtKt.getListAdapter(r10)
            if (r3 == 0) goto L_0x003c
            java.lang.String r2 = "MaterialDialogs"
            java.lang.String r3 = "Prefer calling updateListItemsMultiChoice(...) over listItemsMultiChoice(...) again."
            android.util.Log.w(r2, r3)
            r3 = r13
            r7 = r17
            com.afollestad.materialdialogs.MaterialDialog r0 = updateListItemsMultiChoice(r10, r11, r12, r13, r7)
            return r0
        L_0x003c:
            r3 = r13
            r7 = r17
            com.afollestad.materialdialogs.WhichButton r0 = com.afollestad.materialdialogs.WhichButton.POSITIVE
            r1 = 0
            r5 = 1
            if (r16 != 0) goto L_0x004e
            int r6 = r4.length
            if (r6 != 0) goto L_0x004a
            r6 = 1
            goto L_0x004b
        L_0x004a:
            r6 = 0
        L_0x004b:
            r6 = r6 ^ r5
            if (r6 == 0) goto L_0x004f
        L_0x004e:
            r1 = 1
        L_0x004f:
            com.afollestad.materialdialogs.actions.DialogActionExtKt.setActionButtonEnabled(r10, r0, r1)
            com.afollestad.materialdialogs.internal.list.MultiChoiceDialogAdapter r9 = new com.afollestad.materialdialogs.internal.list.MultiChoiceDialogAdapter
            r0 = r9
            r1 = r10
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            androidx.recyclerview.widget.RecyclerView$Adapter r9 = (androidx.recyclerview.widget.RecyclerView.Adapter) r9
            r0 = 2
            r1 = 0
            com.afollestad.materialdialogs.MaterialDialog r0 = com.afollestad.materialdialogs.list.DialogListExtKt.customListAdapter$default(r10, r9, r1, r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.list.DialogMultiChoiceExtKt.listItemsMultiChoice(com.afollestad.materialdialogs.MaterialDialog, java.lang.Integer, java.util.List, int[], int[], boolean, boolean, kotlin.jvm.functions.Function3):com.afollestad.materialdialogs.MaterialDialog");
    }

    public static /* synthetic */ MaterialDialog updateListItemsMultiChoice$default(MaterialDialog materialDialog, Integer num, List list, int[] iArr, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            list = null;
        }
        if ((i & 4) != 0) {
            iArr = null;
        }
        if ((i & 8) != 0) {
            function3 = null;
        }
        return updateListItemsMultiChoice(materialDialog, num, list, iArr, function3);
    }

    public static final MaterialDialog updateListItemsMultiChoice(MaterialDialog materialDialog, Integer num, List<? extends CharSequence> list, int[] iArr, Function3<? super MaterialDialog, ? super int[], ? super List<? extends CharSequence>, Unit> function3) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$updateListItemsMultiChoice");
        MDUtil.INSTANCE.assertOneSet("updateListItemsMultiChoice", list, num);
        if (list == null) {
            list = ArraysKt.toList((T[]) MDUtil.INSTANCE.getStringArray(materialDialog.getWindowContext(), num));
        }
        RecyclerView.Adapter<?> listAdapter = DialogListExtKt.getListAdapter(materialDialog);
        if (listAdapter instanceof MultiChoiceDialogAdapter) {
            MultiChoiceDialogAdapter multiChoiceDialogAdapter = (MultiChoiceDialogAdapter) listAdapter;
            multiChoiceDialogAdapter.replaceItems(list, function3);
            if (iArr != null) {
                multiChoiceDialogAdapter.disableItems(iArr);
            }
            return materialDialog;
        }
        throw new IllegalStateException("updateListItemsMultiChoice(...) can't be used before you've created a multiple choice list dialog.".toString());
    }

    public static final void checkItems(MaterialDialog materialDialog, int[] iArr) {
        String str;
        Class<?> cls;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$checkItems");
        Intrinsics.checkParameterIsNotNull(iArr, "indices");
        RecyclerView.Adapter<?> listAdapter = DialogListExtKt.getListAdapter(materialDialog);
        if (listAdapter instanceof DialogAdapter) {
            ((DialogAdapter) listAdapter).checkItems(iArr);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't check items on adapter: ");
        if (listAdapter == null || (cls = listAdapter.getClass()) == null || (str = cls.getName()) == null) {
            str = "null";
        }
        sb.append(str);
        throw new UnsupportedOperationException(sb.toString());
    }

    public static final void uncheckItems(MaterialDialog materialDialog, int[] iArr) {
        String str;
        Class<?> cls;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$uncheckItems");
        Intrinsics.checkParameterIsNotNull(iArr, "indices");
        RecyclerView.Adapter<?> listAdapter = DialogListExtKt.getListAdapter(materialDialog);
        if (listAdapter instanceof DialogAdapter) {
            ((DialogAdapter) listAdapter).uncheckItems(iArr);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't uncheck items on adapter: ");
        if (listAdapter == null || (cls = listAdapter.getClass()) == null || (str = cls.getName()) == null) {
            str = "null";
        }
        sb.append(str);
        throw new UnsupportedOperationException(sb.toString());
    }

    public static final void toggleItemsChecked(MaterialDialog materialDialog, int[] iArr) {
        String str;
        Class<?> cls;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$toggleItemsChecked");
        Intrinsics.checkParameterIsNotNull(iArr, "indices");
        RecyclerView.Adapter<?> listAdapter = DialogListExtKt.getListAdapter(materialDialog);
        if (listAdapter instanceof DialogAdapter) {
            ((DialogAdapter) listAdapter).toggleItems(iArr);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't toggle checked items on adapter: ");
        if (listAdapter == null || (cls = listAdapter.getClass()) == null || (str = cls.getName()) == null) {
            str = "null";
        }
        sb.append(str);
        throw new UnsupportedOperationException(sb.toString());
    }

    public static final void checkAllItems(MaterialDialog materialDialog) {
        String str;
        Class<?> cls;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$checkAllItems");
        RecyclerView.Adapter<?> listAdapter = DialogListExtKt.getListAdapter(materialDialog);
        if (listAdapter instanceof DialogAdapter) {
            ((DialogAdapter) listAdapter).checkAllItems();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't check all items on adapter: ");
        if (listAdapter == null || (cls = listAdapter.getClass()) == null || (str = cls.getName()) == null) {
            str = "null";
        }
        sb.append(str);
        throw new UnsupportedOperationException(sb.toString());
    }

    public static final void uncheckAllItems(MaterialDialog materialDialog) {
        String str;
        Class<?> cls;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$uncheckAllItems");
        RecyclerView.Adapter<?> listAdapter = DialogListExtKt.getListAdapter(materialDialog);
        if (listAdapter instanceof DialogAdapter) {
            ((DialogAdapter) listAdapter).uncheckAllItems();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't uncheck all items on adapter: ");
        if (listAdapter == null || (cls = listAdapter.getClass()) == null || (str = cls.getName()) == null) {
            str = "null";
        }
        sb.append(str);
        throw new UnsupportedOperationException(sb.toString());
    }

    public static final void toggleAllItemsChecked(MaterialDialog materialDialog) {
        String str;
        Class<?> cls;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$toggleAllItemsChecked");
        RecyclerView.Adapter<?> listAdapter = DialogListExtKt.getListAdapter(materialDialog);
        if (listAdapter instanceof DialogAdapter) {
            ((DialogAdapter) listAdapter).toggleAllChecked();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't uncheck all items on adapter: ");
        if (listAdapter == null || (cls = listAdapter.getClass()) == null || (str = cls.getName()) == null) {
            str = "null";
        }
        sb.append(str);
        throw new UnsupportedOperationException(sb.toString());
    }
}
