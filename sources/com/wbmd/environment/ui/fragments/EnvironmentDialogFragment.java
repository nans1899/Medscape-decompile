package com.wbmd.environment.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wbmd.environment.EnvironmentManager;
import com.wbmd.environment.data.Environment;
import com.wbmd.environment.interfaces.OnEnvironmentListener;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bR\"\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/wbmd/environment/ui/fragments/EnvironmentDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "environments", "Ljava/util/ArrayList;", "Lcom/wbmd/environment/data/Environment;", "Lkotlin/collections/ArrayList;", "listener", "Lcom/wbmd/environment/interfaces/OnEnvironmentListener;", "manager", "Lcom/wbmd/environment/EnvironmentManager;", "moduleId", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "setListener", "Companion", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EnvironmentDialogFragment.kt */
public final class EnvironmentDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String KEY_ENVIRONMENTS = "environment_dialog_items";
    private static final String KEY_MODULE_ID = "environment_dialog_module_id";
    public static final String TAG = "environment_dialog_fragment";
    private HashMap _$_findViewCache;
    private ArrayList<Environment> environments;
    /* access modifiers changed from: private */
    public OnEnvironmentListener listener;
    /* access modifiers changed from: private */
    public final EnvironmentManager manager = new EnvironmentManager();
    /* access modifiers changed from: private */
    public String moduleId;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ OnEnvironmentListener access$getListener$p(EnvironmentDialogFragment environmentDialogFragment) {
        OnEnvironmentListener onEnvironmentListener = environmentDialogFragment.listener;
        if (onEnvironmentListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        }
        return onEnvironmentListener;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/wbmd/environment/ui/fragments/EnvironmentDialogFragment$Companion;", "", "()V", "KEY_ENVIRONMENTS", "", "KEY_MODULE_ID", "TAG", "newInstance", "Lcom/wbmd/environment/ui/fragments/EnvironmentDialogFragment;", "environments", "Ljava/util/ArrayList;", "Lcom/wbmd/environment/data/Environment;", "Lkotlin/collections/ArrayList;", "moduleId", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EnvironmentDialogFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EnvironmentDialogFragment newInstance(ArrayList<Environment> arrayList, String str) {
            Intrinsics.checkNotNullParameter(arrayList, "environments");
            Intrinsics.checkNotNullParameter(str, "moduleId");
            EnvironmentDialogFragment environmentDialogFragment = new EnvironmentDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(EnvironmentDialogFragment.KEY_ENVIRONMENTS, arrayList);
            bundle.putString(EnvironmentDialogFragment.KEY_MODULE_ID, str);
            environmentDialogFragment.setArguments(bundle);
            return environmentDialogFragment;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.environments = arguments.getParcelableArrayList(KEY_ENVIRONMENTS);
            this.moduleId = arguments.getString(KEY_MODULE_ID);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ArrayList<Environment> arrayList = this.environments;
        if (arrayList != null) {
            int size = arrayList.size();
            String[] strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = arrayList.get(i).getName();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            CharSequence[] charSequenceArr = (CharSequence[]) strArr;
            builder.setSingleChoiceItems(charSequenceArr, CollectionsKt.indexOf(arrayList, this.manager.getEnvironment(getContext(), this.moduleId)), new EnvironmentDialogFragment$onCreateDialog$$inlined$let$lambda$1(arrayList, this));
            AlertDialog create = builder.create();
            Intrinsics.checkNotNullExpressionValue(create, "builder.create()");
            return create;
        }
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkNotNullExpressionValue(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        return onCreateDialog;
    }

    public final void setListener(OnEnvironmentListener onEnvironmentListener) {
        Intrinsics.checkNotNullParameter(onEnvironmentListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = onEnvironmentListener;
    }
}
