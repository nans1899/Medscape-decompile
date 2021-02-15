package com.medscape.android.reference.viewmodels;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeMenu;
import com.medscape.android.R;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.contentviewer.ContentUtils;
import com.medscape.android.landingfeed.repository.Status;
import com.medscape.android.parser.model.Article;
import com.medscape.android.reference.ClinicalReferenceContentManager;
import com.medscape.android.reference.adapters.ReferenceTOCDataAdapter;
import com.medscape.android.reference.interfaces.ISectionItemClickListener;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.tapstream.sdk.http.RequestBuilders;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0001rB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010T\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010U\u001a\u00020\u0005J\u0016\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020HJ\u0018\u0010[\u001a\u00020L2\u0006\u0010\\\u001a\u00020]2\b\u0010^\u001a\u0004\u0018\u00010_J\u0016\u0010`\u001a\u00020W2\u0006\u0010\\\u001a\u00020]2\u0006\u0010a\u001a\u00020bJ\u001a\u0010c\u001a\u00020 2\u0006\u0010\\\u001a\u00020]2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u000e\u0010d\u001a\u00020W2\u0006\u0010e\u001a\u00020fJ\u0010\u0010g\u001a\u00020W2\u0006\u0010e\u001a\u00020fH\u0002J\u0012\u0010h\u001a\u0004\u0018\u00010\u00052\b\u0010i\u001a\u0004\u0018\u00010\u0005J\u0012\u0010j\u001a\u0004\u0018\u00010\u00052\b\u0010i\u001a\u0004\u0018\u00010\u0005J\u0018\u0010k\u001a\u00020W2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\\\u001a\u00020]H\u0002J \u0010l\u001a\u00020W2\u0006\u0010\\\u001a\u00020]2\u0006\u0010e\u001a\u00020f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u001a\u0010m\u001a\u00020 2\u0006\u0010\\\u001a\u00020]2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010n\u001a\u00020W2\u0006\u0010\\\u001a\u00020]H\u0002J\u0016\u0010o\u001a\u00020W2\u0006\u0010\\\u001a\u00020]2\u0006\u0010p\u001a\u00020 J\u0010\u0010q\u001a\u00020W2\u0006\u0010\\\u001a\u00020]H\u0002R:\u0010\u0003\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000fR\u001e\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010&\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\r\"\u0004\b(\u0010\u000fR\u0010\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R:\u00101\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000202\u0018\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000202\u0018\u0001`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\b\"\u0004\b4\u0010\nR\u0010\u00105\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u001c\u00106\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\r\"\u0004\b8\u0010\u000fR \u00109\u001a\b\u0012\u0004\u0012\u00020;0:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u000e\u0010@\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010A\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\r\"\u0004\bC\u0010\u000fR\u000e\u0010D\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R1\u0010E\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\bR \u0010G\u001a\b\u0012\u0004\u0012\u00020H0:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010=\"\u0004\bJ\u0010?R\u001c\u0010K\u001a\u0004\u0018\u00010LX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001c\u0010Q\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\r\"\u0004\bS\u0010\u000f¨\u0006s"}, d2 = {"Lcom/medscape/android/reference/viewmodels/ClinicalReferenceArticleViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "adSegVars", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getAdSegVars", "()Ljava/util/HashMap;", "setAdSegVars", "(Ljava/util/HashMap;)V", "assetId", "getAssetId", "()Ljava/lang/String;", "setAssetId", "(Ljava/lang/String;)V", "contentErrorLoading", "Lcom/medscape/android/reference/viewmodels/ClinicalReferenceArticleViewModel$ContentError;", "getContentErrorLoading", "()Lcom/medscape/android/reference/viewmodels/ClinicalReferenceArticleViewModel$ContentError;", "setContentErrorLoading", "(Lcom/medscape/android/reference/viewmodels/ClinicalReferenceArticleViewModel$ContentError;)V", "crArticle", "Lcom/medscape/android/reference/model/ClinicalReferenceArticle;", "getCrArticle", "()Lcom/medscape/android/reference/model/ClinicalReferenceArticle;", "setCrArticle", "(Lcom/medscape/android/reference/model/ClinicalReferenceArticle;)V", "errMessage", "getErrMessage", "setErrMessage", "errorAlert", "", "getErrorAlert", "()Ljava/lang/Boolean;", "setErrorAlert", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "errorUrl", "getErrorUrl", "setErrorUrl", "mArticleForEmail", "Lcom/medscape/android/parser/model/Article;", "mClinicalReferenceContentManager", "Lcom/medscape/android/reference/ClinicalReferenceContentManager;", "getMClinicalReferenceContentManager", "()Lcom/medscape/android/reference/ClinicalReferenceContentManager;", "setMClinicalReferenceContentManager", "(Lcom/medscape/android/reference/ClinicalReferenceContentManager;)V", "mOmnitureContentData", "", "getMOmnitureContentData", "setMOmnitureContentData", "mSelectedSectionId", "mTitle", "getMTitle", "setMTitle", "networkStateStatus", "Landroidx/lifecycle/MutableLiveData;", "Lcom/medscape/android/landingfeed/repository/Status;", "getNetworkStateStatus", "()Landroidx/lifecycle/MutableLiveData;", "setNetworkStateStatus", "(Landroidx/lifecycle/MutableLiveData;)V", "onCreateCompleted", "pageNumber", "getPageNumber", "setPageNumber", "pclass", "screenSpecificMap", "getScreenSpecificMap", "textSize", "", "getTextSize", "setTextSize", "tocAdapter", "Lcom/medscape/android/reference/adapters/ReferenceTOCDataAdapter;", "getTocAdapter", "()Lcom/medscape/android/reference/adapters/ReferenceTOCDataAdapter;", "setTocAdapter", "(Lcom/medscape/android/reference/adapters/ReferenceTOCDataAdapter;)V", "uri", "getUri", "setUri", "buildSubSectionNameForPing", "subSectionName", "changeHeaderTextSize", "", "textView", "Landroid/widget/TextView;", "progress", "getAdapter", "context", "Landroid/content/Context;", "clickListener", "Lcom/medscape/android/reference/interfaces/ISectionItemClickListener;", "getIntentData", "intent", "Landroid/content/Intent;", "isContentSaved", "loadArticle", "activity", "Landroid/app/Activity;", "loadClinicalReferenceData", "parseArticleIdFromUrl", "url", "parseArticleSectionIdFromUrl", "removeInfo", "saveContent", "saveInfo", "saveToRecentlyViewed", "setScreenSpecificMap", "adRequest", "setTitle", "ContentError", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleViewModel.kt */
public final class ClinicalReferenceArticleViewModel extends ViewModel {
    private HashMap<String, String> adSegVars;
    private String assetId;
    private ContentError contentErrorLoading;
    private ClinicalReferenceArticle crArticle;
    private String errMessage;
    private Boolean errorAlert;
    private String errorUrl;
    private Article mArticleForEmail;
    private ClinicalReferenceContentManager mClinicalReferenceContentManager;
    private HashMap<String, Object> mOmnitureContentData;
    private String mSelectedSectionId;
    private String mTitle;
    private MutableLiveData<Status> networkStateStatus = new MutableLiveData<>();
    private boolean onCreateCompleted;
    private String pageNumber;
    private final String pclass = "content";
    private final HashMap<String, String> screenSpecificMap = new HashMap<>();
    private MutableLiveData<Integer> textSize = new MutableLiveData<>();
    private ReferenceTOCDataAdapter tocAdapter;
    private String uri;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/medscape/android/reference/viewmodels/ClinicalReferenceArticleViewModel$ContentError;", "", "(Ljava/lang/String;I)V", "CONTENT_NOT_DOWNLOADED", "CONTENT_NOT_AVAILABLE", "CONTENT_LOADING_ERROR", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ClinicalReferenceArticleViewModel.kt */
    public enum ContentError {
        CONTENT_NOT_DOWNLOADED,
        CONTENT_NOT_AVAILABLE,
        CONTENT_LOADING_ERROR
    }

    public final ClinicalReferenceArticle getCrArticle() {
        return this.crArticle;
    }

    public final void setCrArticle(ClinicalReferenceArticle clinicalReferenceArticle) {
        this.crArticle = clinicalReferenceArticle;
    }

    public final String getUri() {
        return this.uri;
    }

    public final void setUri(String str) {
        this.uri = str;
    }

    public final String getPageNumber() {
        return this.pageNumber;
    }

    public final void setPageNumber(String str) {
        this.pageNumber = str;
    }

    public final String getMTitle() {
        return this.mTitle;
    }

    public final void setMTitle(String str) {
        this.mTitle = str;
    }

    public final HashMap<String, String> getScreenSpecificMap() {
        return this.screenSpecificMap;
    }

    public final HashMap<String, String> getAdSegVars() {
        return this.adSegVars;
    }

    public final void setAdSegVars(HashMap<String, String> hashMap) {
        this.adSegVars = hashMap;
    }

    public final HashMap<String, Object> getMOmnitureContentData() {
        return this.mOmnitureContentData;
    }

    public final void setMOmnitureContentData(HashMap<String, Object> hashMap) {
        this.mOmnitureContentData = hashMap;
    }

    public final String getAssetId() {
        return this.assetId;
    }

    public final void setAssetId(String str) {
        this.assetId = str;
    }

    public final ClinicalReferenceContentManager getMClinicalReferenceContentManager() {
        return this.mClinicalReferenceContentManager;
    }

    public final void setMClinicalReferenceContentManager(ClinicalReferenceContentManager clinicalReferenceContentManager) {
        this.mClinicalReferenceContentManager = clinicalReferenceContentManager;
    }

    public final ReferenceTOCDataAdapter getTocAdapter() {
        return this.tocAdapter;
    }

    public final void setTocAdapter(ReferenceTOCDataAdapter referenceTOCDataAdapter) {
        this.tocAdapter = referenceTOCDataAdapter;
    }

    public final MutableLiveData<Integer> getTextSize() {
        return this.textSize;
    }

    public final void setTextSize(MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.textSize = mutableLiveData;
    }

    public final MutableLiveData<Status> getNetworkStateStatus() {
        return this.networkStateStatus;
    }

    public final void setNetworkStateStatus(MutableLiveData<Status> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.networkStateStatus = mutableLiveData;
    }

    public final ContentError getContentErrorLoading() {
        return this.contentErrorLoading;
    }

    public final void setContentErrorLoading(ContentError contentError) {
        this.contentErrorLoading = contentError;
    }

    public final String getErrMessage() {
        return this.errMessage;
    }

    public final void setErrMessage(String str) {
        this.errMessage = str;
    }

    public final String getErrorUrl() {
        return this.errorUrl;
    }

    public final void setErrorUrl(String str) {
        this.errorUrl = str;
    }

    public final Boolean getErrorAlert() {
        return this.errorAlert;
    }

    public final void setErrorAlert(Boolean bool) {
        this.errorAlert = bool;
    }

    public final void getIntentData(Context context, Intent intent) {
        ClinicalReferenceArticle clinicalReferenceArticle;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        this.mClinicalReferenceContentManager = new ClinicalReferenceContentManager(context);
        String dataString = intent.getDataString();
        this.uri = dataString;
        if (dataString == null) {
            Serializable serializableExtra = intent.getSerializableExtra("article");
            if (serializableExtra != null) {
                clinicalReferenceArticle = (ClinicalReferenceArticle) serializableExtra;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.reference.model.ClinicalReferenceArticle");
            }
        } else {
            ClinicalReferenceContentManager clinicalReferenceContentManager = this.mClinicalReferenceContentManager;
            clinicalReferenceArticle = clinicalReferenceContentManager != null ? clinicalReferenceContentManager.fetchArticleWithId(parseArticleIdFromUrl(dataString)) : null;
        }
        this.crArticle = clinicalReferenceArticle;
    }

    private final void loadClinicalReferenceData(Activity activity) {
        this.pageNumber = Util.getPageNumberFromUrl(this.uri);
        String urlWithoutPageNumber = Util.getUrlWithoutPageNumber(this.uri);
        this.uri = urlWithoutPageNumber;
        this.mSelectedSectionId = parseArticleSectionIdFromUrl(urlWithoutPageNumber);
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        if (clinicalReferenceArticle != null) {
            Util.sendFirebaseContentInfo(activity, "ckb", String.valueOf(clinicalReferenceArticle != null ? Integer.valueOf(clinicalReferenceArticle.getArticleId()) : null));
            Context context = activity;
            setScreenSpecificMap(context, false);
            this.mArticleForEmail = new Article();
            setTitle(context);
            saveToRecentlyViewed(context);
            this.onCreateCompleted = true;
        }
    }

    public final void loadArticle(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        loadClinicalReferenceData(activity);
        this.networkStateStatus.setValue(Status.RUNNING);
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new ClinicalReferenceArticleViewModel$loadArticle$1(this, activity, (Continuation) null), 2, (Object) null);
    }

    public final ReferenceTOCDataAdapter getAdapter(Context context, ISectionItemClickListener iSectionItemClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.tocAdapter == null) {
            this.tocAdapter = iSectionItemClickListener != null ? new ReferenceTOCDataAdapter(context, iSectionItemClickListener) : null;
        }
        ReferenceTOCDataAdapter referenceTOCDataAdapter = this.tocAdapter;
        if (referenceTOCDataAdapter != null) {
            return referenceTOCDataAdapter;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.reference.adapters.ReferenceTOCDataAdapter");
    }

    private final void setTitle(Context context) {
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        String str = null;
        if (clinicalReferenceArticle != null) {
            if (!Intrinsics.areEqual((Object) clinicalReferenceArticle != null ? clinicalReferenceArticle.getTitle() : null, (Object) "")) {
                ClinicalReferenceArticle clinicalReferenceArticle2 = this.crArticle;
                if (clinicalReferenceArticle2 != null) {
                    str = clinicalReferenceArticle2.getTitle();
                }
                this.mTitle = str;
            }
        }
        str = context.getResources().getString(R.string.clinical_procedure_header_text_view);
        this.mTitle = str;
    }

    public final String parseArticleIdFromUrl(String str) {
        String lastPathSegment;
        String lastPathSegment2;
        if (str != null) {
            Uri parse = Uri.parse(str);
            Intrinsics.checkNotNullExpressionValue(parse, "uri");
            String scheme = parse.getScheme();
            if (Intrinsics.areEqual((Object) "http", (Object) scheme) || Intrinsics.areEqual((Object) RequestBuilders.DEFAULT_SCHEME, (Object) scheme)) {
                if (Intrinsics.areEqual((Object) "emedicine.medscape.com", (Object) parse.getHost()) && (lastPathSegment = parse.getLastPathSegment()) != null) {
                    Object[] array = StringsKt.split$default((CharSequence) lastPathSegment, new String[]{"-"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    if (array != null) {
                        String[] strArr = (String[]) array;
                        if (strArr.length > 0) {
                            return strArr[0];
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            } else if (Intrinsics.areEqual((Object) "ckb", (Object) scheme)) {
                return parse.getHost();
            } else {
                if (scheme == null && (lastPathSegment2 = parse.getLastPathSegment()) != null) {
                    Object[] array2 = StringsKt.split$default((CharSequence) lastPathSegment2, new String[]{"-"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    if (array2 != null) {
                        String[] strArr2 = (String[]) array2;
                        if (strArr2.length > 0) {
                            return strArr2[0];
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            }
        }
        return str;
    }

    private final void saveToRecentlyViewed(Context context) {
        Bundle bundle = new Bundle(2);
        bundle.putString("type", "T");
        bundle.putSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE, this.crArticle);
        RecentlyViewedSuggestionHelper.addToRecentlyViewed(context, this.mTitle, bundle);
    }

    public final void setScreenSpecificMap(Context context, boolean z) {
        HashMap<String, String> hashMap;
        Intrinsics.checkNotNullParameter(context, "context");
        this.screenSpecificMap.put("pos", context.getResources().getString(R.string.banner_ad_pos));
        this.screenSpecificMap.put("pc", this.pclass);
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        if (clinicalReferenceArticle != null && !z) {
            String str = null;
            HashMap<String, String> queryDatabase = clinicalReferenceArticle != null ? AdsSegvar.getInstance().queryDatabase(context, clinicalReferenceArticle.getArticleId(), 2) : null;
            this.adSegVars = queryDatabase;
            if (queryDatabase != null) {
                Boolean valueOf = queryDatabase != null ? Boolean.valueOf(queryDatabase.containsKey("article-id")) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue() && (hashMap = this.adSegVars) != null) {
                    str = hashMap.get("article-id");
                }
            }
            this.assetId = str;
            this.mOmnitureContentData = OmnitureManager.get().getContentBasedOmnitureData(this.adSegVars, -1);
            HashMap<String, String> hashMap2 = this.adSegVars;
            if (hashMap2 != null) {
                this.screenSpecificMap.putAll(hashMap2);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("");
            ClinicalReferenceArticle clinicalReferenceArticle2 = this.crArticle;
            Intrinsics.checkNotNull(clinicalReferenceArticle2);
            sb.append(clinicalReferenceArticle2.getArticleId());
            this.screenSpecificMap.put("art", sb.toString());
        }
    }

    public final String parseArticleSectionIdFromUrl(String str) {
        String lastPathSegment;
        String lastPathSegment2;
        if (str != null) {
            Uri parse = Uri.parse(str);
            Intrinsics.checkNotNullExpressionValue(parse, "uri");
            String scheme = parse.getScheme();
            if (Intrinsics.areEqual((Object) "http", (Object) scheme) || Intrinsics.areEqual((Object) RequestBuilders.DEFAULT_SCHEME, (Object) scheme)) {
                if (Intrinsics.areEqual((Object) "emedicine.medscape.com", (Object) parse.getHost()) && (lastPathSegment = parse.getLastPathSegment()) != null) {
                    Object[] array = StringsKt.split$default((CharSequence) lastPathSegment, new String[]{"-"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    if (array != null) {
                        String[] strArr = (String[]) array;
                        if (strArr.length <= 1 || !StringUtil.isNotEmpty(strArr[1])) {
                            return null;
                        }
                        return strArr[1];
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } else if (Intrinsics.areEqual((Object) "ckb", (Object) scheme)) {
                if (StringUtil.isNotEmpty(parse.getQueryParameter("section"))) {
                    return parse.getQueryParameter("section");
                }
                return null;
            } else if (scheme == null && (lastPathSegment2 = parse.getLastPathSegment()) != null) {
                Object[] array2 = StringsKt.split$default((CharSequence) lastPathSegment2, new String[]{"-"}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array2 != null) {
                    String[] strArr2 = (String[]) array2;
                    if (strArr2.length <= 1 || !StringUtil.isNotEmpty(strArr2[1])) {
                        return null;
                    }
                    return strArr2[1];
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        return str;
    }

    public final boolean isContentSaved(Context context, ClinicalReferenceArticle clinicalReferenceArticle) {
        Cursor query;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            AuthenticationManager instance = AuthenticationManager.getInstance(context);
            Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.getInstance(context)");
            String maskedGuid = instance.getMaskedGuid();
            if (StringUtil.isNotEmpty(maskedGuid) && clinicalReferenceArticle != null && (query = context.getContentResolver().query(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, (String[]) null, "articleId=? AND (userGuid='' OR userGuid=?)", new String[]{String.valueOf(clinicalReferenceArticle.getArticleId()), maskedGuid}, (String) null)) != null && query.moveToFirst()) {
                query.close();
                return true;
            }
        } catch (Exception e) {
            Trace.e("ClinicalReferenceArticle", e.getMessage());
        }
        return false;
    }

    public final void saveContent(Context context, Activity activity, ClinicalReferenceArticle clinicalReferenceArticle) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (clinicalReferenceArticle != null) {
            if (isContentSaved(context, clinicalReferenceArticle)) {
                removeInfo(clinicalReferenceArticle, context);
                if (clinicalReferenceArticle.getType() == 1) {
                    OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_REFERENCE, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
                } else {
                    OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_REFERENCE, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
                }
            } else if (saveInfo(context, clinicalReferenceArticle)) {
                AppboyEventsHandler.logDailyEvent(context, AppboyConstants.APPBOY_EVENT_CKB_SAVED, activity);
                if (!activity.isFinishing()) {
                    Toast.makeText(context, context.getResources().getString(R.string.reference_artical_saved), 0).show();
                }
                if (clinicalReferenceArticle.getType() == 1) {
                    MedscapeMenu.sendSaveBIPings(context, Constants.OMNITURE_CHANNEL_REFERENCE, "ref");
                } else {
                    MedscapeMenu.sendSaveBIPings(context, Constants.OMNITURE_CHANNEL_REFERENCE, "ref");
                }
            }
        }
        activity.invalidateOptionsMenu();
    }

    private final void removeInfo(ClinicalReferenceArticle clinicalReferenceArticle, Context context) {
        try {
            if (clinicalReferenceArticle.getUniqueId() == 0) {
                context.getContentResolver().delete(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, "articleId = ? ", new String[]{String.valueOf(clinicalReferenceArticle.getArticleId())});
                return;
            }
            context.getContentResolver().delete(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, "uniqueId = ? ", new String[]{String.valueOf(clinicalReferenceArticle.getUniqueId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final boolean saveInfo(Context context, ClinicalReferenceArticle clinicalReferenceArticle) {
        try {
            AuthenticationManager instance = AuthenticationManager.getInstance(context);
            Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.getInstance(context)");
            String maskedGuid = instance.getMaskedGuid();
            if (StringUtil.isNotEmpty(maskedGuid)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("isSaved", 1);
                Integer num = null;
                contentValues.put("title", clinicalReferenceArticle != null ? clinicalReferenceArticle.getTitle() : null);
                contentValues.put("type", clinicalReferenceArticle != null ? Integer.valueOf(clinicalReferenceArticle.getType()) : null);
                contentValues.put("uniqueId", clinicalReferenceArticle != null ? Integer.valueOf(clinicalReferenceArticle.getUniqueId()) : null);
                if (clinicalReferenceArticle != null) {
                    num = Integer.valueOf(clinicalReferenceArticle.getArticleId());
                }
                contentValues.put("articleId", num);
                contentValues.put("userGuid", maskedGuid);
                context.getContentResolver().insert(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, contentValues);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void changeHeaderTextSize(TextView textView, int i) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        textView.setTextSize((float) ContentUtils.getTextFontSize(i));
    }

    public final String buildSubSectionNameForPing(ClinicalReferenceArticle clinicalReferenceArticle, String str) {
        Intrinsics.checkNotNullParameter(str, "subSectionName");
        StringBuilder sb = new StringBuilder();
        if (clinicalReferenceArticle != null) {
            sb.append(clinicalReferenceArticle.getArticleId());
            sb.append("-");
            sb.append(str);
        }
        return sb.toString();
    }
}
