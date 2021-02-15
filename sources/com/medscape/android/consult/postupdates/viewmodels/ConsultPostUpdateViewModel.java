package com.medscape.android.consult.postupdates.viewmodels;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IPostUploadedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.BodyUpdates;
import com.medscape.android.consult.models.ConsultPost;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#H\u0002J$\u0010$\u001a\u00020\u001f2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#H\u0002J\u000e\u0010'\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0010\u0010(\u001a\u00020\u001f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0018\u0010)\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010*\u001a\u00020\u0011J\u000e\u0010+\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J*\u0010,\u001a\u00020\u001f2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010-\u001a\u00020\u000b2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#J\u000e\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u00020\u000bJ\u000e\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\u001a\u0010\u0019\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u00062"}, d2 = {"Lcom/medscape/android/consult/postupdates/viewmodels/ConsultPostUpdateViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "caseResolved", "Landroidx/lifecycle/MutableLiveData;", "", "getCaseResolved", "()Landroidx/lifecycle/MutableLiveData;", "setCaseResolved", "(Landroidx/lifecycle/MutableLiveData;)V", "consultPost", "Lcom/medscape/android/consult/models/ConsultPost;", "getConsultPost", "()Lcom/medscape/android/consult/models/ConsultPost;", "setConsultPost", "(Lcom/medscape/android/consult/models/ConsultPost;)V", "postUpdate", "getPostUpdate", "setPostUpdate", "submitButtonEnabled", "getSubmitButtonEnabled", "()Z", "setSubmitButtonEnabled", "(Z)V", "addNewPostUpdate", "", "activity", "Landroid/app/Activity;", "uploadedListener", "Lcom/medscape/android/consult/interfaces/IPostUploadedListener;", "editPostUpdate", "bodyUpdates", "Lcom/medscape/android/consult/models/BodyUpdates;", "handleCancel", "setConsultPostObject", "setPostDetailsActivity", "post", "showConfirmDiscardAlert", "submitUpdate", "editing", "updateCheckBox", "checked", "updatePostBody", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultPostUpdateViewModel.kt */
public final class ConsultPostUpdateViewModel extends ViewModel {
    private String TAG = "ConsultPostUpdateViewModel";
    private MutableLiveData<Boolean> caseResolved = new MutableLiveData<>();
    private ConsultPost consultPost;
    private MutableLiveData<String> postUpdate = new MutableLiveData<>();
    private boolean submitButtonEnabled;

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.TAG = str;
    }

    public final MutableLiveData<Boolean> getCaseResolved() {
        return this.caseResolved;
    }

    public final void setCaseResolved(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.caseResolved = mutableLiveData;
    }

    public final MutableLiveData<String> getPostUpdate() {
        return this.postUpdate;
    }

    public final void setPostUpdate(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.postUpdate = mutableLiveData;
    }

    public final ConsultPost getConsultPost() {
        return this.consultPost;
    }

    public final void setConsultPost(ConsultPost consultPost2) {
        this.consultPost = consultPost2;
    }

    public final boolean getSubmitButtonEnabled() {
        return this.submitButtonEnabled;
    }

    public final void setSubmitButtonEnabled(boolean z) {
        this.submitButtonEnabled = z;
    }

    public final void handleCancel(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.submitButtonEnabled) {
            showConfirmDiscardAlert(activity);
        } else {
            activity.finish();
        }
    }

    public final void showConfirmDiscardAlert(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(activity.getResources().getString(R.string.consult_post_discard));
            builder.setNegativeButton(activity.getResources().getString(R.string.cancel), ConsultPostUpdateViewModel$showConfirmDiscardAlert$1.INSTANCE);
            builder.setPositiveButton(activity.getResources().getString(R.string.landing_activity_continue), new ConsultPostUpdateViewModel$showConfirmDiscardAlert$2(activity));
            builder.show();
        } catch (Exception unused) {
            Trace.w(this.TAG, "Failed to show discard message alert");
        }
    }

    public final void submitUpdate(BodyUpdates bodyUpdates, boolean z, Activity activity, IPostUploadedListener iPostUploadedListener) {
        Intrinsics.checkNotNullParameter(iPostUploadedListener, "uploadedListener");
        if (z) {
            editPostUpdate(bodyUpdates, activity, iPostUploadedListener);
        } else {
            addNewPostUpdate(activity, iPostUploadedListener);
        }
    }

    private final void editPostUpdate(BodyUpdates bodyUpdates, Activity activity, IPostUploadedListener iPostUploadedListener) {
        String str;
        ConsultPost consultPost2 = this.consultPost;
        if (!(consultPost2 == null || bodyUpdates == null)) {
            List<BodyUpdates> consultBodyUpdateList = consultPost2.getConsultBodyUpdateList();
            Intrinsics.checkNotNullExpressionValue(consultBodyUpdateList, "consultBodyUpdates");
            int size = consultBodyUpdateList.size();
            for (int i = 0; i < size; i++) {
                if (consultBodyUpdateList.size() > i) {
                    BodyUpdates bodyUpdates2 = consultBodyUpdateList.get(i);
                    if (!Intrinsics.areEqual((Object) bodyUpdates2.getId(), (Object) bodyUpdates.getId())) {
                        continue;
                    } else {
                        Boolean value = this.caseResolved.getValue();
                        if (value != null) {
                            Intrinsics.checkNotNullExpressionValue(value, "case");
                            consultPost2.setCaseResolved(value.booleanValue());
                        } else {
                            ConsultPostUpdateViewModel consultPostUpdateViewModel = this;
                            consultPost2.setCaseResolved(false);
                        }
                        String body = bodyUpdates.getBody();
                        if (body == null || !(!StringsKt.isBlank(body))) {
                            consultPost2.getConsultBodyUpdateList().remove(i);
                        } else {
                            String body2 = bodyUpdates.getBody();
                            if (body2 == null) {
                                str = null;
                            } else if (body2 != null) {
                                str = StringsKt.trim((CharSequence) body2).toString();
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                            bodyUpdates2.setBody(str);
                        }
                    }
                }
            }
        }
        ConsultDataManager.getInstance((Context) null, activity).editPostUpdate(this.consultPost, iPostUploadedListener);
    }

    private final void addNewPostUpdate(Activity activity, IPostUploadedListener iPostUploadedListener) {
        List<BodyUpdates> consultBodyUpdateList;
        String value = this.postUpdate.getValue();
        if (value != null) {
            Intrinsics.checkNotNullExpressionValue(value, "postUpdate");
            CharSequence charSequence = value;
            if (!StringsKt.isBlank(charSequence)) {
                BodyUpdates bodyUpdates = new BodyUpdates();
                bodyUpdates.setBody(StringsKt.trim(charSequence).toString());
                ConsultPost consultPost2 = this.consultPost;
                if (!(consultPost2 == null || (consultBodyUpdateList = consultPost2.getConsultBodyUpdateList()) == null)) {
                    consultBodyUpdateList.add(bodyUpdates);
                }
            }
        }
        ConsultPost consultPost3 = this.consultPost;
        if (consultPost3 != null) {
            Boolean value2 = this.caseResolved.getValue();
            if (value2 != null) {
                Intrinsics.checkNotNullExpressionValue(value2, "case");
                consultPost3.setCaseResolved(value2.booleanValue());
            } else {
                ConsultPostUpdateViewModel consultPostUpdateViewModel = this;
                consultPost3.setCaseResolved(false);
            }
        }
        ConsultDataManager.getInstance((Context) null, activity).addPostUpdate(this.consultPost, iPostUploadedListener);
    }

    public final void setPostDetailsActivity(Activity activity, ConsultPost consultPost2) {
        Intrinsics.checkNotNullParameter(consultPost2, "post");
        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_CONSULT_POST, consultPost2);
        intent.putExtra(Constants.EXTRA_CONSULT_SCROLL_TO_DETAIL_FILTER, false);
        if (activity != null) {
            activity.setResult(-1, intent);
        }
        Intent intent2 = new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION);
        if (activity != null) {
            LocalBroadcastManager.getInstance(activity).sendBroadcast(intent2);
        }
        if (activity != null) {
            activity.finish();
        }
    }

    public final void setConsultPostObject(ConsultPost consultPost2) {
        if (consultPost2 != null) {
            this.consultPost = consultPost2;
        }
    }

    public final void updatePostBody(String str) {
        Intrinsics.checkNotNullParameter(str, "toString");
        this.postUpdate.setValue(str);
    }

    public final void updateCheckBox(boolean z) {
        this.caseResolved.setValue(Boolean.valueOf(z));
    }
}
