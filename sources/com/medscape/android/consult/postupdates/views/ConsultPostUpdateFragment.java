package com.medscape.android.consult.postupdates.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IPostUploadedListener;
import com.medscape.android.consult.models.BodyUpdates;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.postupdates.viewmodels.ConsultPostUpdateViewModel;
import com.medscape.android.util.MedscapeException;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 <2\u00020\u00012\u00020\u0002:\u0001<B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010&\u001a\u00020'H\u0002J\u0006\u0010(\u001a\u00020'J\u0006\u0010)\u001a\u00020'J\b\u0010*\u001a\u00020'H\u0002J$\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0012\u00102\u001a\u00020'2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0012\u00105\u001a\u00020'2\b\u00106\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u00107\u001a\u00020'H\u0016J\b\u00108\u001a\u00020'H\u0002J\b\u00109\u001a\u00020'H\u0002J\b\u0010:\u001a\u00020'H\u0002J\b\u0010;\u001a\u00020'H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u000e\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X.¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/medscape/android/consult/postupdates/views/ConsultPostUpdateFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/medscape/android/consult/interfaces/IPostUploadedListener;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "addUpdateText", "Landroid/widget/TextView;", "bodyUpdates", "Lcom/medscape/android/consult/models/BodyUpdates;", "caseResolvedCheckbox", "Landroid/widget/CheckBox;", "caseResolvedObserver", "Landroidx/lifecycle/Observer;", "", "getCaseResolvedObserver", "()Landroidx/lifecycle/Observer;", "characterCount", "consultPost", "Lcom/medscape/android/consult/models/ConsultPost;", "isEditing", "originalText", "postBodyObserver", "getPostBodyObserver", "postUpdateText", "Landroid/widget/EditText;", "progressBar", "Landroid/widget/ProgressBar;", "rootView", "Landroid/view/View;", "submitButton", "Landroid/widget/Button;", "viewModel", "Lcom/medscape/android/consult/postupdates/viewmodels/ConsultPostUpdateViewModel;", "checkFields", "", "disableSubmitButton", "enableSubmitButton", "getAllValuesAndSetToConsultPostBody", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPostFailed", "exception", "Lcom/medscape/android/util/MedscapeException;", "onPostSentToServer", "post", "onResume", "setContentDescriptionForViews", "setUpListeners", "setUpObservers", "updateViews", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultPostUpdateFragment.kt */
public final class ConsultPostUpdateFragment extends Fragment implements IPostUploadedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String TAG = "ConsultPostUpdateFragment";
    private HashMap _$_findViewCache;
    private TextView addUpdateText;
    /* access modifiers changed from: private */
    public BodyUpdates bodyUpdates;
    /* access modifiers changed from: private */
    public CheckBox caseResolvedCheckbox;
    private final Observer<Boolean> caseResolvedObserver = new ConsultPostUpdateFragment$caseResolvedObserver$1(this);
    /* access modifiers changed from: private */
    public TextView characterCount;
    /* access modifiers changed from: private */
    public ConsultPost consultPost;
    /* access modifiers changed from: private */
    public boolean isEditing;
    private String originalText = "";
    private final Observer<String> postBodyObserver = new ConsultPostUpdateFragment$postBodyObserver$1(this);
    private EditText postUpdateText;
    private ProgressBar progressBar;
    private View rootView;
    private Button submitButton;
    /* access modifiers changed from: private */
    public ConsultPostUpdateViewModel viewModel;

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

    public static final /* synthetic */ CheckBox access$getCaseResolvedCheckbox$p(ConsultPostUpdateFragment consultPostUpdateFragment) {
        CheckBox checkBox = consultPostUpdateFragment.caseResolvedCheckbox;
        if (checkBox == null) {
            Intrinsics.throwUninitializedPropertyAccessException("caseResolvedCheckbox");
        }
        return checkBox;
    }

    public static final /* synthetic */ TextView access$getCharacterCount$p(ConsultPostUpdateFragment consultPostUpdateFragment) {
        TextView textView = consultPostUpdateFragment.characterCount;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("characterCount");
        }
        return textView;
    }

    public static final /* synthetic */ ConsultPostUpdateViewModel access$getViewModel$p(ConsultPostUpdateFragment consultPostUpdateFragment) {
        ConsultPostUpdateViewModel consultPostUpdateViewModel = consultPostUpdateFragment.viewModel;
        if (consultPostUpdateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return consultPostUpdateViewModel;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.TAG = str;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Lcom/medscape/android/consult/postupdates/views/ConsultPostUpdateFragment$Companion;", "", "()V", "newInstance", "Lcom/medscape/android/consult/postupdates/views/ConsultPostUpdateFragment;", "editing", "", "consultPost", "Lcom/medscape/android/consult/models/ConsultPost;", "bodyUpdates", "Lcom/medscape/android/consult/models/BodyUpdates;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ConsultPostUpdateFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConsultPostUpdateFragment newInstance(boolean z, ConsultPost consultPost, BodyUpdates bodyUpdates) {
            ConsultPostUpdateFragment consultPostUpdateFragment = new ConsultPostUpdateFragment();
            consultPostUpdateFragment.isEditing = z;
            consultPostUpdateFragment.consultPost = consultPost;
            consultPostUpdateFragment.bodyUpdates = bodyUpdates;
            return consultPostUpdateFragment;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_consult_post_update, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…update, container, false)");
        this.rootView = inflate;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ViewModel viewModel2 = ViewModelProviders.of(activity).get(ConsultPostUpdateViewModel.class);
            Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProviders.of(it…ateViewModel::class.java)");
            this.viewModel = (ConsultPostUpdateViewModel) viewModel2;
        }
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById = view.findViewById(R.id.add_update_text);
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootView.findViewById(R.id.add_update_text)");
        this.addUpdateText = (TextView) findViewById;
        View view2 = this.rootView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById2 = view2.findViewById(R.id.post_update_text);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "rootView.findViewById(R.id.post_update_text)");
        this.postUpdateText = (EditText) findViewById2;
        View view3 = this.rootView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById3 = view3.findViewById(R.id.character_count);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "rootView.findViewById(R.id.character_count)");
        this.characterCount = (TextView) findViewById3;
        View view4 = this.rootView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById4 = view4.findViewById(R.id.case_resolved_checkbox);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "rootView.findViewById(R.id.case_resolved_checkbox)");
        this.caseResolvedCheckbox = (CheckBox) findViewById4;
        View view5 = this.rootView;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById5 = view5.findViewById(R.id.button_submit);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "rootView.findViewById(R.id.button_submit)");
        this.submitButton = (Button) findViewById5;
        View view6 = this.rootView;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById6 = view6.findViewById(R.id.white_progress);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "rootView.findViewById(R.id.white_progress)");
        ProgressBar progressBar2 = (ProgressBar) findViewById6;
        this.progressBar = progressBar2;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        progressBar2.getIndeterminateDrawable().setColorFilter(-1, PorterDuff.Mode.MULTIPLY);
        setUpListeners();
        updateViews();
        setUpObservers();
        setContentDescriptionForViews();
        ConsultPostUpdateViewModel consultPostUpdateViewModel = this.viewModel;
        if (consultPostUpdateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        consultPostUpdateViewModel.setConsultPostObject(this.consultPost);
        View view7 = this.rootView;
        if (view7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view7;
    }

    private final void setContentDescriptionForViews() {
        TextView textView = this.addUpdateText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addUpdateText");
        }
        TextView textView2 = this.addUpdateText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addUpdateText");
        }
        textView.setContentDescription(textView2.getText());
        EditText editText = this.postUpdateText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("postUpdateText");
        }
        EditText editText2 = this.postUpdateText;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("postUpdateText");
        }
        editText.setContentDescription(editText2.getText());
        TextView textView3 = this.characterCount;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("characterCount");
        }
        TextView textView4 = this.characterCount;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("characterCount");
        }
        textView3.setContentDescription(textView4.getText());
        CheckBox checkBox = this.caseResolvedCheckbox;
        if (checkBox == null) {
            Intrinsics.throwUninitializedPropertyAccessException("caseResolvedCheckbox");
        }
        CheckBox checkBox2 = this.caseResolvedCheckbox;
        if (checkBox2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("caseResolvedCheckbox");
        }
        checkBox.setContentDescription(checkBox2.getText());
        Button button = this.submitButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("submitButton");
        }
        Button button2 = this.submitButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("submitButton");
        }
        button.setContentDescription(button2.getText());
    }

    private final void setUpObservers() {
        ConsultPostUpdateViewModel consultPostUpdateViewModel = this.viewModel;
        if (consultPostUpdateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        LifecycleOwner lifecycleOwner = this;
        consultPostUpdateViewModel.getCaseResolved().observe(lifecycleOwner, this.caseResolvedObserver);
        ConsultPostUpdateViewModel consultPostUpdateViewModel2 = this.viewModel;
        if (consultPostUpdateViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        consultPostUpdateViewModel2.getPostUpdate().observe(lifecycleOwner, this.postBodyObserver);
    }

    private final void updateViews() {
        if (this.isEditing) {
            TextView textView = this.addUpdateText;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addUpdateText");
            }
            textView.setText(getString(R.string.edit_update_below));
            BodyUpdates bodyUpdates2 = this.bodyUpdates;
            if (bodyUpdates2 != null) {
                this.originalText = bodyUpdates2.getBody();
                EditText editText = this.postUpdateText;
                if (editText == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("postUpdateText");
                }
                editText.setText(this.originalText);
                EditText editText2 = this.postUpdateText;
                if (editText2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("postUpdateText");
                }
                String str = this.originalText;
                editText2.setSelection(str != null ? str.length() : 0);
            }
        }
        ConsultPost consultPost2 = this.consultPost;
        if (consultPost2 != null) {
            CheckBox checkBox = this.caseResolvedCheckbox;
            if (checkBox == null) {
                Intrinsics.throwUninitializedPropertyAccessException("caseResolvedCheckbox");
            }
            checkBox.setChecked(consultPost2.isCaseResolved());
            ConsultPostUpdateViewModel consultPostUpdateViewModel = this.viewModel;
            if (consultPostUpdateViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            consultPostUpdateViewModel.getCaseResolved().setValue(Boolean.valueOf(consultPost2.isCaseResolved()));
            ConsultPostUpdateViewModel consultPostUpdateViewModel2 = this.viewModel;
            if (consultPostUpdateViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            consultPostUpdateViewModel2.getPostUpdate().setValue(this.originalText);
        }
    }

    private final void setUpListeners() {
        EditText editText = this.postUpdateText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("postUpdateText");
        }
        editText.addTextChangedListener(new ConsultPostUpdateFragment$setUpListeners$1(this));
        Button button = this.submitButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("submitButton");
        }
        button.setOnClickListener(new ConsultPostUpdateFragment$setUpListeners$2(this));
        CheckBox checkBox = this.caseResolvedCheckbox;
        if (checkBox == null) {
            Intrinsics.throwUninitializedPropertyAccessException("caseResolvedCheckbox");
        }
        checkBox.setOnClickListener(new ConsultPostUpdateFragment$setUpListeners$3(this));
    }

    /* access modifiers changed from: private */
    public final void getAllValuesAndSetToConsultPostBody() {
        Resources resources;
        String str = null;
        if (Utilities.isNetworkAvailable(getActivity())) {
            Button button = this.submitButton;
            if (button == null) {
                Intrinsics.throwUninitializedPropertyAccessException("submitButton");
            }
            button.setText("");
            ProgressBar progressBar2 = this.progressBar;
            if (progressBar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            }
            progressBar2.setVisibility(0);
            BodyUpdates bodyUpdates2 = this.bodyUpdates;
            if (bodyUpdates2 != null) {
                ConsultPostUpdateViewModel consultPostUpdateViewModel = this.viewModel;
                if (consultPostUpdateViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                }
                String value = consultPostUpdateViewModel.getPostUpdate().getValue();
                if (value != null) {
                    if (value != null) {
                        str = StringsKt.trim((CharSequence) value).toString();
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                }
                bodyUpdates2.setBody(str);
            }
            ConsultPostUpdateViewModel consultPostUpdateViewModel2 = this.viewModel;
            if (consultPostUpdateViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            consultPostUpdateViewModel2.submitUpdate(this.bodyUpdates, this.isEditing, getActivity(), this);
            return;
        }
        FragmentActivity activity = getActivity();
        MedscapeException medscapeException = new MedscapeException(activity != null ? activity.getString(R.string.error_connection_required) : null);
        Activity activity2 = getActivity();
        FragmentActivity activity3 = getActivity();
        if (!(activity3 == null || (resources = activity3.getResources()) == null)) {
            str = resources.getString(R.string.alert_dialog_confirmation_ok_button_text);
        }
        medscapeException.showAlert(activity2, str, ConsultPostUpdateFragment$getAllValuesAndSetToConsultPostBody$2.INSTANCE, (String) null, (DialogInterface.OnClickListener) null);
    }

    /* access modifiers changed from: private */
    public final void checkFields() {
        ConsultPostUpdateViewModel consultPostUpdateViewModel = this.viewModel;
        if (consultPostUpdateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Boolean value = consultPostUpdateViewModel.getCaseResolved().getValue();
        ConsultPost consultPost2 = this.consultPost;
        if (!(!Intrinsics.areEqual((Object) value, (Object) consultPost2 != null ? Boolean.valueOf(consultPost2.isCaseResolved()) : null))) {
            ConsultPostUpdateViewModel consultPostUpdateViewModel2 = this.viewModel;
            if (consultPostUpdateViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            if (!(!Intrinsics.areEqual((Object) consultPostUpdateViewModel2.getPostUpdate().getValue(), (Object) this.originalText))) {
                disableSubmitButton();
                return;
            }
        }
        enableSubmitButton();
    }

    public final void enableSubmitButton() {
        Button button = this.submitButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("submitButton");
        }
        button.setAlpha(1.0f);
        Button button2 = this.submitButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("submitButton");
        }
        button2.setEnabled(true);
        ConsultPostUpdateViewModel consultPostUpdateViewModel = this.viewModel;
        if (consultPostUpdateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        consultPostUpdateViewModel.setSubmitButtonEnabled(true);
    }

    public final void disableSubmitButton() {
        Button button = this.submitButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("submitButton");
        }
        button.setAlpha(0.5f);
        Button button2 = this.submitButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("submitButton");
        }
        button2.setEnabled(false);
        ConsultPostUpdateViewModel consultPostUpdateViewModel = this.viewModel;
        if (consultPostUpdateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        consultPostUpdateViewModel.setSubmitButtonEnabled(false);
    }

    public final Observer<String> getPostBodyObserver() {
        return this.postBodyObserver;
    }

    public final Observer<Boolean> getCaseResolvedObserver() {
        return this.caseResolvedObserver;
    }

    public void onPostSentToServer(ConsultPost consultPost2) {
        Trace.i(this.TAG, "Success");
        if (this.isEditing) {
            OmnitureManager.get().trackModule(getActivity(), "consult", "consult-update", "edit", (Map<String, Object>) null);
        } else {
            OmnitureManager.get().trackModule(getActivity(), "consult", "consult-update", "add", (Map<String, Object>) null);
        }
        if (consultPost2 != null) {
            ConsultPostUpdateViewModel consultPostUpdateViewModel = this.viewModel;
            if (consultPostUpdateViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            consultPostUpdateViewModel.setPostDetailsActivity(getActivity(), consultPost2);
        }
    }

    public void onPostFailed(MedscapeException medscapeException) {
        Log.e(this.TAG, "Consult Post Update Failed");
        ProgressBar progressBar2 = this.progressBar;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        progressBar2.setVisibility(8);
        Button button = this.submitButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("submitButton");
        }
        button.setText(getString(R.string.consult_post_submit));
        if (medscapeException != null) {
            View view = this.rootView;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            }
            medscapeException.showSnackBar(view, -1, getString(R.string.alert_dialog_confirmation_ok_button_text), new ConsultPostUpdateFragment$onPostFailed$1(medscapeException));
        }
    }

    public void onResume() {
        super.onResume();
        if (this.isEditing) {
            OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "editupdate", (String) null, (String) null, (Map<String, Object>) null);
        } else {
            OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "addupdate", (String) null, (String) null, (Map<String, Object>) null);
        }
    }
}
