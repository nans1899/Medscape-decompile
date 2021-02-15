package com.medscape.android.myinvites;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.medscape.android.myinvites.specific.Invitation;
import com.medscape.android.myinvites.specific.SpecificInvitations;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J&\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R0\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b0\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "errorData", "Landroidx/lifecycle/MutableLiveData;", "", "getErrorData", "()Landroidx/lifecycle/MutableLiveData;", "invitationData", "Ljava/util/ArrayList;", "Lcom/medscape/android/myinvites/specific/Invitation;", "Lkotlin/collections/ArrayList;", "getInvitationData", "setInvitationData", "(Landroidx/lifecycle/MutableLiveData;)V", "loadingData", "", "getLoadingData", "setLoadingData", "load", "", "context", "Landroid/content/Context;", "sortInvitations", "value", "Lcom/medscape/android/myinvites/specific/SpecificInvitations;", "UtfRequest", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MyInvitationsViewModel.kt */
public final class MyInvitationsViewModel extends ViewModel {
    private final MutableLiveData<Throwable> errorData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Invitation>> invitationData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingData = new MutableLiveData<>(true);

    public final MutableLiveData<Boolean> getLoadingData() {
        return this.loadingData;
    }

    public final void setLoadingData(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.loadingData = mutableLiveData;
    }

    public final MutableLiveData<ArrayList<Invitation>> getInvitationData() {
        return this.invitationData;
    }

    public final void setInvitationData(MutableLiveData<ArrayList<Invitation>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.invitationData = mutableLiveData;
    }

    public final MutableLiveData<Throwable> getErrorData() {
        return this.errorData;
    }

    public final void load(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.loadingData.postValue(true);
        RequestQueue newRequestQueue = Volley.newRequestQueue(context);
        UtfRequest utfRequest = new UtfRequest(0, MyInvitationsManager.Companion.get(context).getSpecificInvitationEndpoint(), new MyInvitationsViewModel$load$serverRequest$1(this), new MyInvitationsViewModel$load$serverRequest$2(this));
        utfRequest.setShouldCache(false);
        newRequestQueue.add(utfRequest);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsViewModel$UtfRequest;", "Lcom/android/volley/toolbox/StringRequest;", "method", "", "url", "", "listener", "Lcom/android/volley/Response$Listener;", "errorListener", "Lcom/android/volley/Response$ErrorListener;", "(Lcom/medscape/android/myinvites/MyInvitationsViewModel;ILjava/lang/String;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;)V", "parseNetworkResponse", "Lcom/android/volley/Response;", "response", "Lcom/android/volley/NetworkResponse;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MyInvitationsViewModel.kt */
    public final class UtfRequest extends StringRequest {
        public UtfRequest(int i, String str, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(i, str, listener, errorListener);
        }

        /* access modifiers changed from: protected */
        public Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
            try {
                Intrinsics.checkNotNull(networkResponse);
                byte[] bArr = networkResponse.data;
                Intrinsics.checkNotNullExpressionValue(bArr, "response!!.data");
                Charset charset = StandardCharsets.UTF_8;
                Intrinsics.checkNotNullExpressionValue(charset, "StandardCharsets.UTF_8");
                Response<String> success = Response.success(new String(bArr, charset), HttpHeaderParser.parseCacheHeaders(networkResponse));
                Intrinsics.checkNotNullExpressionValue(success, "Response.success(jsonStr…seCacheHeaders(response))");
                return success;
            } catch (UnsupportedEncodingException e) {
                Response<String> error = Response.error(new ParseError((Throwable) e));
                Intrinsics.checkNotNullExpressionValue(error, "Response.error(ParseError(e))");
                return error;
            } catch (JSONException e2) {
                Response<String> error2 = Response.error(new ParseError((Throwable) e2));
                Intrinsics.checkNotNullExpressionValue(error2, "Response.error(ParseError(je))");
                return error2;
            }
        }
    }

    /* access modifiers changed from: private */
    public final ArrayList<Invitation> sortInvitations(SpecificInvitations specificInvitations) {
        ArrayList<Invitation> invitations;
        ArrayList arrayList = new ArrayList();
        if (!(specificInvitations == null || (invitations = specificInvitations.getInvitations()) == null)) {
            for (Invitation formatInvitation : invitations) {
                arrayList.add(new MyInvitationsManager().formatInvitation(formatInvitation));
            }
        }
        List reversed = CollectionsKt.reversed(arrayList);
        Collection collection = reversed;
        if (collection == null || collection.isEmpty()) {
            return new ArrayList<>();
        }
        ((Invitation) reversed.get(0)).setFeatured(true);
        return new ArrayList<>(collection);
    }
}
