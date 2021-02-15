package com.medscape.android.contentviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdSize;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.R;
import com.medscape.android.activity.calc.ads.AdAppEvents;
import com.medscape.android.activity.calc.ads.AdConversions;
import com.medscape.android.activity.calc.ads.AdParams;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.bidding.MedianetBidder;
import com.medscape.android.ads.bidding.ProclivityBidder;
import com.medscape.android.landingfeed.repository.Status;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.reference.ClinicalReferenceArticleLandingActivity;
import com.medscape.android.reference.adapters.ReferenceTOCDataAdapter;
import com.medscape.android.reference.interfaces.ContentLoaderCallback;
import com.medscape.android.reference.interfaces.ISectionItemClickListener;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.reference.model.ClinicalReferenceContent;
import com.medscape.android.reference.model.Sect1;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import com.medscape.android.util.MedscapeException;
import com.wbmd.ads.AdManager;
import com.wbmd.ads.bidding.AdBiddingProvider;
import com.wbmd.ads.model.Pos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 r2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001rB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010RH\u0016J\u0012\u0010S\u001a\u00020P2\b\u0010T\u001a\u0004\u0018\u00010RH\u0016J\u001a\u0010U\u001a\u00020P2\b\u0010V\u001a\u0004\u0018\u00010R2\u0006\u0010W\u001a\u00020XH\u0016J\b\u0010Y\u001a\u00020PH\u0016J\u0012\u0010Z\u001a\u00020P2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0016J\b\u0010]\u001a\u00020PH\u0002J\b\u0010^\u001a\u00020PH\u0002J&\u0010_\u001a\u0004\u0018\u0001082\u0006\u0010`\u001a\u00020a2\b\u0010b\u001a\u0004\u0018\u00010c2\b\u0010d\u001a\u0004\u0018\u00010eH\u0016J\b\u0010f\u001a\u00020PH\u0016J\u0010\u0010g\u001a\u00020P2\u0006\u0010h\u001a\u00020iH\u0016J\b\u0010j\u001a\u00020PH\u0002J\u000e\u0010k\u001a\u00020P2\u0006\u0010l\u001a\u00020XJ\b\u0010m\u001a\u00020PH\u0002J\b\u0010n\u001a\u00020PH\u0002J\b\u0010o\u001a\u00020PH\u0002J\u0010\u0010p\u001a\u00020P2\u0006\u0010q\u001a\u00020iH\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001a\u0010\u0011\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u001a\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR\u001a\u0010#\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR\u001a\u0010&\u001a\u00020\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020,X.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u000202X.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001a\u00107\u001a\u000208X.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010=\u001a\u00020>X.¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010C\u001a\u00020DX.¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001a\u0010I\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\b\"\u0004\bK\u0010\nR\u001a\u0010L\u001a\u00020\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0017\"\u0004\bN\u0010\u0019¨\u0006s"}, d2 = {"Lcom/medscape/android/contentviewer/ClinicalReferenceArticleLandingFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/medscape/android/reference/interfaces/ContentLoaderCallback;", "Lcom/medscape/android/reference/interfaces/ISectionItemClickListener;", "()V", "articleAuthor", "Landroid/widget/TextView;", "getArticleAuthor", "()Landroid/widget/TextView;", "setArticleAuthor", "(Landroid/widget/TextView;)V", "articleName", "getArticleName", "setArticleName", "articleUpdateDate", "getArticleUpdateDate", "setArticleUpdateDate", "authorLabel", "getAuthorLabel", "setAuthorLabel", "authorLayout", "Landroid/widget/LinearLayout;", "getAuthorLayout", "()Landroid/widget/LinearLayout;", "setAuthorLayout", "(Landroid/widget/LinearLayout;)V", "binding", "Landroidx/databinding/ViewDataBinding;", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "chiefEditor", "getChiefEditor", "setChiefEditor", "chiefLabel", "getChiefLabel", "setChiefLabel", "chiefLayout", "getChiefLayout", "setChiefLayout", "crLandingViewModel", "Lcom/medscape/android/reference/viewmodels/ClinicalReferenceArticleViewModel;", "openContributorsBtn", "Landroid/widget/ImageView;", "getOpenContributorsBtn", "()Landroid/widget/ImageView;", "setOpenContributorsBtn", "(Landroid/widget/ImageView;)V", "progressBar", "Landroid/widget/ProgressBar;", "getProgressBar", "()Landroid/widget/ProgressBar;", "setProgressBar", "(Landroid/widget/ProgressBar;)V", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "sectionsList", "Landroidx/recyclerview/widget/RecyclerView;", "getSectionsList", "()Landroidx/recyclerview/widget/RecyclerView;", "setSectionsList", "(Landroidx/recyclerview/widget/RecyclerView;)V", "tocHeader", "Landroid/widget/RelativeLayout;", "getTocHeader", "()Landroid/widget/RelativeLayout;", "setTocHeader", "(Landroid/widget/RelativeLayout;)V", "updateLabel", "getUpdateLabel", "setUpdateLabel", "updateLayout", "getUpdateLayout", "setUpdateLayout", "handleContentDownloaded", "", "path", "", "handleContentLoadingError", "errorMessage", "handleContentNotAvailable", "url", "alert", "", "handleContentNotDownloaded", "handleContentloaded", "content", "Lcom/medscape/android/reference/model/ClinicalReferenceContent;", "initViews", "loadArticle", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "referenceSectionClicked", "position", "", "requestBottomBannerAd", "setupNightMode", "nightMode", "setupObserver", "setupViews", "showContributorsFragment", "startReferenceArticleContentActivity", "sectionPosition", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingFragment.kt */
public final class ClinicalReferenceArticleLandingFragment extends Fragment implements ContentLoaderCallback, ISectionItemClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    public TextView articleAuthor;
    public TextView articleName;
    public TextView articleUpdateDate;
    public TextView authorLabel;
    public LinearLayout authorLayout;
    private ViewDataBinding binding;
    public TextView chiefEditor;
    public TextView chiefLabel;
    public LinearLayout chiefLayout;
    /* access modifiers changed from: private */
    public ClinicalReferenceArticleViewModel crLandingViewModel;
    public ImageView openContributorsBtn;
    public ProgressBar progressBar;
    public View rootView;
    public RecyclerView sectionsList;
    public RelativeLayout tocHeader;
    public TextView updateLabel;
    public LinearLayout updateLayout;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ClinicalReferenceArticleViewModel.ContentError.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ClinicalReferenceArticleViewModel.ContentError.CONTENT_NOT_AVAILABLE.ordinal()] = 1;
            $EnumSwitchMapping$0[ClinicalReferenceArticleViewModel.ContentError.CONTENT_LOADING_ERROR.ordinal()] = 2;
            $EnumSwitchMapping$0[ClinicalReferenceArticleViewModel.ContentError.CONTENT_NOT_DOWNLOADED.ordinal()] = 3;
            int[] iArr2 = new int[Status.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[Status.SUCCESS.ordinal()] = 1;
            $EnumSwitchMapping$1[Status.RUNNING.ordinal()] = 2;
        }
    }

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

    public final View getRootView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view;
    }

    public final void setRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.rootView = view;
    }

    public final RecyclerView getSectionsList() {
        RecyclerView recyclerView = this.sectionsList;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sectionsList");
        }
        return recyclerView;
    }

    public final void setSectionsList(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.sectionsList = recyclerView;
    }

    public final ProgressBar getProgressBar() {
        ProgressBar progressBar2 = this.progressBar;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        return progressBar2;
    }

    public final void setProgressBar(ProgressBar progressBar2) {
        Intrinsics.checkNotNullParameter(progressBar2, "<set-?>");
        this.progressBar = progressBar2;
    }

    public final TextView getArticleName() {
        TextView textView = this.articleName;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("articleName");
        }
        return textView;
    }

    public final void setArticleName(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.articleName = textView;
    }

    public final TextView getArticleUpdateDate() {
        TextView textView = this.articleUpdateDate;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("articleUpdateDate");
        }
        return textView;
    }

    public final void setArticleUpdateDate(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.articleUpdateDate = textView;
    }

    public final TextView getArticleAuthor() {
        TextView textView = this.articleAuthor;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("articleAuthor");
        }
        return textView;
    }

    public final void setArticleAuthor(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.articleAuthor = textView;
    }

    public final TextView getChiefEditor() {
        TextView textView = this.chiefEditor;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chiefEditor");
        }
        return textView;
    }

    public final void setChiefEditor(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.chiefEditor = textView;
    }

    public final LinearLayout getUpdateLayout() {
        LinearLayout linearLayout = this.updateLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateLayout");
        }
        return linearLayout;
    }

    public final void setUpdateLayout(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.updateLayout = linearLayout;
    }

    public final LinearLayout getAuthorLayout() {
        LinearLayout linearLayout = this.authorLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authorLayout");
        }
        return linearLayout;
    }

    public final void setAuthorLayout(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.authorLayout = linearLayout;
    }

    public final LinearLayout getChiefLayout() {
        LinearLayout linearLayout = this.chiefLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chiefLayout");
        }
        return linearLayout;
    }

    public final void setChiefLayout(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.chiefLayout = linearLayout;
    }

    public final TextView getUpdateLabel() {
        TextView textView = this.updateLabel;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateLabel");
        }
        return textView;
    }

    public final void setUpdateLabel(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.updateLabel = textView;
    }

    public final TextView getAuthorLabel() {
        TextView textView = this.authorLabel;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authorLabel");
        }
        return textView;
    }

    public final void setAuthorLabel(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.authorLabel = textView;
    }

    public final TextView getChiefLabel() {
        TextView textView = this.chiefLabel;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chiefLabel");
        }
        return textView;
    }

    public final void setChiefLabel(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.chiefLabel = textView;
    }

    public final RelativeLayout getTocHeader() {
        RelativeLayout relativeLayout = this.tocHeader;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tocHeader");
        }
        return relativeLayout;
    }

    public final void setTocHeader(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        this.tocHeader = relativeLayout;
    }

    public final ImageView getOpenContributorsBtn() {
        ImageView imageView = this.openContributorsBtn;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("openContributorsBtn");
        }
        return imageView;
    }

    public final void setOpenContributorsBtn(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.openContributorsBtn = imageView;
    }

    public final ViewDataBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(ViewDataBinding viewDataBinding) {
        this.binding = viewDataBinding;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ClinicalReferenceArticle crArticle;
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(layoutInflater, R.layout.clref_toc_fragment, viewGroup, false);
        this.binding = inflate;
        Intrinsics.checkNotNull(inflate);
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding!!.root");
        this.rootView = root;
        FragmentActivity activity = getActivity();
        String str = null;
        this.crLandingViewModel = activity != null ? (ClinicalReferenceArticleViewModel) ViewModelProviders.of(activity).get(ClinicalReferenceArticleViewModel.class) : null;
        initViews();
        TextView textView = this.articleName;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("articleName");
        }
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
        if (!(clinicalReferenceArticleViewModel == null || (crArticle = clinicalReferenceArticleViewModel.getCrArticle()) == null)) {
            str = crArticle.getTitle();
        }
        textView.setText(str);
        setupObserver();
        loadArticle();
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
        if (!(clinicalReferenceArticleViewModel == null || clinicalReferenceArticleViewModel.getAssetId() == null)) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                ((ClinicalReferenceArticleLandingActivity) activity).sendOmniturePing();
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.reference.ClinicalReferenceArticleLandingActivity");
            }
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            ((ClinicalReferenceArticleLandingActivity) activity2).setTitle("");
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.reference.ClinicalReferenceArticleLandingActivity");
    }

    private final void initViews() {
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel;
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById = view.findViewById(R.id.article_name);
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootView.findViewById(R.id.article_name)");
        this.articleName = (TextView) findViewById;
        View view2 = this.rootView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById2 = view2.findViewById(R.id.date_updated);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "rootView.findViewById(R.id.date_updated)");
        this.articleUpdateDate = (TextView) findViewById2;
        View view3 = this.rootView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById3 = view3.findViewById(R.id.author);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "rootView.findViewById(R.id.author)");
        this.articleAuthor = (TextView) findViewById3;
        View view4 = this.rootView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById4 = view4.findViewById(R.id.chief_editor);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "rootView.findViewById(R.id.chief_editor)");
        this.chiefEditor = (TextView) findViewById4;
        View view5 = this.rootView;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById5 = view5.findViewById(R.id.update_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "rootView.findViewById(R.id.update_layout)");
        this.updateLayout = (LinearLayout) findViewById5;
        View view6 = this.rootView;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById6 = view6.findViewById(R.id.author_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "rootView.findViewById(R.id.author_layout)");
        this.authorLayout = (LinearLayout) findViewById6;
        View view7 = this.rootView;
        if (view7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById7 = view7.findViewById(R.id.chief_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "rootView.findViewById(R.id.chief_layout)");
        this.chiefLayout = (LinearLayout) findViewById7;
        View view8 = this.rootView;
        if (view8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById8 = view8.findViewById(R.id.article_sections);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "rootView.findViewById(R.id.article_sections)");
        this.sectionsList = (RecyclerView) findViewById8;
        View view9 = this.rootView;
        if (view9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById9 = view9.findViewById(R.id.progress);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "rootView.findViewById(R.id.progress)");
        this.progressBar = (ProgressBar) findViewById9;
        View view10 = this.rootView;
        if (view10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById10 = view10.findViewById(R.id.update_label);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "rootView.findViewById(R.id.update_label)");
        this.updateLabel = (TextView) findViewById10;
        View view11 = this.rootView;
        if (view11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById11 = view11.findViewById(R.id.author_label);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "rootView.findViewById(R.id.author_label)");
        this.authorLabel = (TextView) findViewById11;
        View view12 = this.rootView;
        if (view12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById12 = view12.findViewById(R.id.chief_label);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "rootView.findViewById(R.id.chief_label)");
        this.chiefLabel = (TextView) findViewById12;
        View view13 = this.rootView;
        if (view13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById13 = view13.findViewById(R.id.toc_header);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "rootView.findViewById(R.id.toc_header)");
        this.tocHeader = (RelativeLayout) findViewById13;
        View view14 = this.rootView;
        if (view14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById14 = view14.findViewById(R.id.open_contributors);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "rootView.findViewById(R.id.open_contributors)");
        this.openContributorsBtn = (ImageView) findViewById14;
        RecyclerView recyclerView = this.sectionsList;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sectionsList");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView recyclerView2 = this.sectionsList;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sectionsList");
        }
        FragmentActivity activity = getActivity();
        ReferenceTOCDataAdapter referenceTOCDataAdapter = null;
        if (!(activity == null || (clinicalReferenceArticleViewModel = this.crLandingViewModel) == null)) {
            Intrinsics.checkNotNullExpressionValue(activity, "it");
            referenceTOCDataAdapter = clinicalReferenceArticleViewModel.getAdapter(activity, this);
        }
        recyclerView2.setAdapter(referenceTOCDataAdapter);
        RelativeLayout relativeLayout = this.tocHeader;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tocHeader");
        }
        relativeLayout.setOnClickListener(new ClinicalReferenceArticleLandingFragment$initViews$2(this));
    }

    private final void loadArticle() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
            if ((clinicalReferenceArticleViewModel != null ? clinicalReferenceArticleViewModel.getCrArticle() : null) != null) {
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
                if (clinicalReferenceArticleViewModel2 != null) {
                    Intrinsics.checkNotNullExpressionValue(activity, "it");
                    clinicalReferenceArticleViewModel2.loadArticle(activity);
                    return;
                }
                return;
            }
            activity.finish();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r0.getCrArticle();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void setupViews() {
        /*
            r7 = this;
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r0 = r7.crLandingViewModel
            r1 = 0
            if (r0 == 0) goto L_0x0010
            com.medscape.android.reference.model.ClinicalReferenceArticle r0 = r0.getCrArticle()
            if (r0 == 0) goto L_0x0010
            com.medscape.android.reference.model.ClinicalReferenceContent r0 = r0.getContent()
            goto L_0x0011
        L_0x0010:
            r0 = r1
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            java.lang.String r2 = r0.lastUpdate
            goto L_0x0017
        L_0x0016:
            r2 = r1
        L_0x0017:
            boolean r2 = com.medscape.android.util.StringUtil.isNotEmpty(r2)
            java.lang.String r3 = "updateLayout"
            r4 = 0
            if (r2 == 0) goto L_0x003f
            android.widget.TextView r2 = r7.articleUpdateDate
            if (r2 != 0) goto L_0x0029
            java.lang.String r5 = "articleUpdateDate"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x0029:
            if (r0 == 0) goto L_0x002e
            java.lang.String r5 = r0.lastUpdate
            goto L_0x002f
        L_0x002e:
            r5 = r1
        L_0x002f:
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r2.setText(r5)
            android.widget.LinearLayout r2 = r7.updateLayout
            if (r2 != 0) goto L_0x003b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x003b:
            r2.setVisibility(r4)
            goto L_0x0049
        L_0x003f:
            android.widget.LinearLayout r2 = r7.updateLayout
            if (r2 != 0) goto L_0x0046
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x0046:
            r2.setVisibility(r4)
        L_0x0049:
            if (r0 == 0) goto L_0x004e
            java.util.ArrayList<com.medscape.android.reference.model.Contributor> r0 = r0.contributors
            goto L_0x004f
        L_0x004e:
            r0 = r1
        L_0x004f:
            if (r0 == 0) goto L_0x00c0
            java.util.Iterator r0 = r0.iterator()
        L_0x0055:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00c0
            java.lang.Object r2 = r0.next()
            com.medscape.android.reference.model.Contributor r2 = (com.medscape.android.reference.model.Contributor) r2
            if (r2 == 0) goto L_0x0066
            java.lang.String r3 = r2.author
            goto L_0x0067
        L_0x0066:
            r3 = r1
        L_0x0067:
            if (r3 == 0) goto L_0x0055
            java.lang.String r3 = r2.author
            boolean r3 = com.medscape.android.util.StringUtil.isNotEmpty(r3)
            if (r3 == 0) goto L_0x0055
            java.lang.String r3 = r2.typeLabel
            java.lang.String r5 = "Author"
            r6 = 1
            boolean r3 = kotlin.text.StringsKt.equals(r3, r5, r6)
            if (r3 == 0) goto L_0x0099
            android.widget.TextView r3 = r7.articleAuthor
            if (r3 != 0) goto L_0x0085
            java.lang.String r5 = "articleAuthor"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x0085:
            java.lang.String r2 = r2.author
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r3.setText(r2)
            android.widget.LinearLayout r2 = r7.authorLayout
            if (r2 != 0) goto L_0x0095
            java.lang.String r3 = "authorLayout"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x0095:
            r2.setVisibility(r4)
            goto L_0x0055
        L_0x0099:
            java.lang.String r3 = r2.typeLabel
            java.lang.String r5 = "Chief Editor"
            boolean r3 = kotlin.text.StringsKt.equals(r3, r5, r6)
            if (r3 == 0) goto L_0x0055
            android.widget.TextView r3 = r7.chiefEditor
            if (r3 != 0) goto L_0x00ac
            java.lang.String r5 = "chiefEditor"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x00ac:
            java.lang.String r2 = r2.author
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r3.setText(r2)
            android.widget.LinearLayout r2 = r7.chiefLayout
            if (r2 != 0) goto L_0x00bc
            java.lang.String r3 = "chiefLayout"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x00bc:
            r2.setVisibility(r4)
            goto L_0x0055
        L_0x00c0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment.setupViews():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0043, code lost:
        r4 = (r4 = r4.getCrArticle()).getContent();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void showContributorsFragment() {
        /*
            r6 = this;
            com.medscape.android.provider.SharedPreferenceProvider r0 = com.medscape.android.provider.SharedPreferenceProvider.get()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "com.medscape.reference.night.mode"
            r1.append(r2)
            androidx.fragment.app.FragmentActivity r2 = r6.getActivity()
            android.content.Context r2 = (android.content.Context) r2
            com.medscape.android.auth.AuthenticationManager r2 = com.medscape.android.auth.AuthenticationManager.getInstance(r2)
            java.lang.String r3 = "AuthenticationManager.getInstance(activity)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.String r2 = r2.getMaskedGuid()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            int r0 = r0.get((java.lang.String) r1, (int) r2)
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            com.medscape.android.contentviewer.ClinicalReferenceArticleFragment r1 = com.medscape.android.contentviewer.ClinicalReferenceArticleFragment.newInstance(r1)
            com.medscape.android.reference.view.ContributorsDataAdapter r3 = new com.medscape.android.reference.view.ContributorsDataAdapter
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r4 = r6.crLandingViewModel
            r5 = 0
            if (r4 == 0) goto L_0x004c
            com.medscape.android.reference.model.ClinicalReferenceArticle r4 = r4.getCrArticle()
            if (r4 == 0) goto L_0x004c
            com.medscape.android.reference.model.ClinicalReferenceContent r4 = r4.getContent()
            if (r4 == 0) goto L_0x004c
            java.util.ArrayList<com.medscape.android.reference.model.Contributor> r4 = r4.contributors
            goto L_0x004d
        L_0x004c:
            r4 = r5
        L_0x004d:
            r3.<init>(r4)
            r4 = 1
            if (r0 != r4) goto L_0x0054
            r2 = 1
        L_0x0054:
            r3.setNightMode(r2)
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment$showContributorsFragment$1 r0 = com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment$showContributorsFragment$1.INSTANCE
            com.medscape.android.contentviewer.view_holders.DataViewHolder$DataListClickListener r0 = (com.medscape.android.contentviewer.view_holders.DataViewHolder.DataListClickListener) r0
            r3.addDataListClickListener(r0)
            com.medscape.android.contentviewer.ContentSectionDataAdapter r3 = (com.medscape.android.contentviewer.ContentSectionDataAdapter) r3
            r1.setAdapter(r3)
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r0 = r6.crLandingViewModel
            if (r0 == 0) goto L_0x0076
            if (r0 == 0) goto L_0x006e
            com.medscape.android.reference.model.ClinicalReferenceArticle r2 = r0.getCrArticle()
            goto L_0x006f
        L_0x006e:
            r2 = r5
        L_0x006f:
            java.lang.String r3 = "contributor-information"
            java.lang.String r0 = r0.buildSubSectionNameForPing(r2, r3)
            goto L_0x0077
        L_0x0076:
            r0 = r5
        L_0x0077:
            r1.setSectionNameForOmniture(r0)
            androidx.fragment.app.FragmentActivity r0 = r6.getActivity()     // Catch:{ all -> 0x00d1 }
            if (r0 == 0) goto L_0x00a0
            androidx.fragment.app.FragmentManager r0 = r0.getSupportFragmentManager()     // Catch:{ all -> 0x00d1 }
            if (r0 == 0) goto L_0x00a0
            androidx.fragment.app.FragmentTransaction r0 = r0.beginTransaction()     // Catch:{ all -> 0x00d1 }
            if (r0 == 0) goto L_0x00a0
            r2 = 2131363811(0x7f0a07e3, float:1.8347441E38)
            androidx.fragment.app.Fragment r1 = (androidx.fragment.app.Fragment) r1     // Catch:{ all -> 0x00d1 }
            androidx.fragment.app.FragmentTransaction r0 = r0.replace(r2, r1)     // Catch:{ all -> 0x00d1 }
            if (r0 == 0) goto L_0x00a0
            androidx.fragment.app.FragmentTransaction r0 = r0.addToBackStack(r5)     // Catch:{ all -> 0x00d1 }
            if (r0 == 0) goto L_0x00a0
            r0.commit()     // Catch:{ all -> 0x00d1 }
        L_0x00a0:
            androidx.fragment.app.FragmentActivity r0 = r6.getActivity()     // Catch:{ all -> 0x00d1 }
            java.lang.String r1 = "null cannot be cast to non-null type com.medscape.android.reference.ClinicalReferenceArticleLandingActivity"
            if (r0 == 0) goto L_0x00cb
            com.medscape.android.reference.ClinicalReferenceArticleLandingActivity r0 = (com.medscape.android.reference.ClinicalReferenceArticleLandingActivity) r0     // Catch:{ all -> 0x00d1 }
            r2 = 2131951914(0x7f13012a, float:1.9540256E38)
            java.lang.String r2 = r6.getString(r2)     // Catch:{ all -> 0x00d1 }
            java.lang.String r3 = "getString(R.string.clini…ence_viewer_contributors)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x00d1 }
            r0.setTitle(r2)     // Catch:{ all -> 0x00d1 }
            androidx.fragment.app.FragmentActivity r0 = r6.getActivity()     // Catch:{ all -> 0x00d1 }
            if (r0 == 0) goto L_0x00c5
            com.medscape.android.reference.ClinicalReferenceArticleLandingActivity r0 = (com.medscape.android.reference.ClinicalReferenceArticleLandingActivity) r0     // Catch:{ all -> 0x00d1 }
            r0.setShowingContributors(r4)     // Catch:{ all -> 0x00d1 }
            goto L_0x00d1
        L_0x00c5:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x00d1 }
            r0.<init>(r1)     // Catch:{ all -> 0x00d1 }
            throw r0     // Catch:{ all -> 0x00d1 }
        L_0x00cb:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x00d1 }
            r0.<init>(r1)     // Catch:{ all -> 0x00d1 }
            throw r0     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment.showContributorsFragment():void");
    }

    public void referenceSectionClicked(int i) {
        if (getActivity() != null && isAdded()) {
            OmnitureManager.get().markModule(true, NotificationCompat.CATEGORY_NAVIGATION, "toc", (Map<String, Object>) null);
            startReferenceArticleContentActivity(i);
        }
    }

    public void handleContentLoadingError(String str) {
        ClinicalReferenceArticle crArticle;
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
        Integer num = null;
        if ((clinicalReferenceArticleViewModel != null ? clinicalReferenceArticleViewModel.getCrArticle() : null) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
            if (!(clinicalReferenceArticleViewModel2 == null || (crArticle = clinicalReferenceArticleViewModel2.getCrArticle()) == null)) {
                num = Integer.valueOf(crArticle.getArticleId());
            }
            sb.append(num);
            handleContentNotAvailable(sb.toString(), true);
            return;
        }
        ProgressBar progressBar2 = this.progressBar;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        progressBar2.setVisibility(8);
        String string = getResources().getString(R.string.alert_dialog_confirmation_positive_button_text_OK);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…_positive_button_text_OK)");
        MedscapeException medscapeException = new MedscapeException(str);
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        medscapeException.showSnackBar(view, -2, string, new ClinicalReferenceArticleLandingFragment$handleContentLoadingError$1(this));
    }

    public void handleContentloaded(ClinicalReferenceContent clinicalReferenceContent) {
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel;
        ClinicalReferenceArticle crArticle;
        ClinicalReferenceArticle clinicalReferenceArticle = null;
        if (clinicalReferenceContent != null) {
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
            if ((clinicalReferenceArticleViewModel2 != null ? clinicalReferenceArticleViewModel2.getAssetId() : null) == null) {
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel3 = this.crLandingViewModel;
                if (clinicalReferenceArticleViewModel3 != null) {
                    clinicalReferenceArticleViewModel3.setAssetId(clinicalReferenceContent.adSupportMap.get("article-id"));
                }
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    ((ClinicalReferenceArticleLandingActivity) activity).sendOmniturePing();
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.reference.ClinicalReferenceArticleLandingActivity");
                }
            }
        }
        ArrayList<Sect1> arrayList = clinicalReferenceContent != null ? clinicalReferenceContent.sections : null;
        if (arrayList != null) {
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel4 = this.crLandingViewModel;
            Boolean valueOf = (clinicalReferenceArticleViewModel4 == null || (crArticle = clinicalReferenceArticleViewModel4.getCrArticle()) == null) ? null : Boolean.valueOf(crArticle.isSinglePageArticle());
            if (valueOf == null) {
                return;
            }
            if (!valueOf.booleanValue()) {
                setupViews();
                FragmentActivity activity2 = getActivity();
                if (!(activity2 == null || (clinicalReferenceArticleViewModel = this.crLandingViewModel) == null)) {
                    Intrinsics.checkNotNullExpressionValue(activity2, "it");
                    ReferenceTOCDataAdapter adapter = clinicalReferenceArticleViewModel.getAdapter(activity2, this);
                    if (adapter != null) {
                        adapter.setData(arrayList);
                    }
                }
                RecyclerView recyclerView = this.sectionsList;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sectionsList");
                }
                recyclerView.setVisibility(0);
                ProgressBar progressBar2 = this.progressBar;
                if (progressBar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                }
                progressBar2.setVisibility(8);
                requestBottomBannerAd();
                return;
            }
            Intent intent = new Intent(getActivity(), ClinicalReferenceArticleActivity.class);
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel5 = this.crLandingViewModel;
            if (clinicalReferenceArticleViewModel5 != null) {
                clinicalReferenceArticle = clinicalReferenceArticleViewModel5.getCrArticle();
            }
            intent.putExtra("article", clinicalReferenceArticle);
            startActivity(intent);
            FragmentActivity activity3 = getActivity();
            if (activity3 != null) {
                activity3.finish();
            }
        }
    }

    public void handleContentNotDownloaded() {
        if (isAdded()) {
            ProgressBar progressBar2 = this.progressBar;
            if (progressBar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            }
            progressBar2.setVisibility(8);
            String string = getResources().getString(R.string.retry);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.retry)");
            new Handler().postDelayed(new ClinicalReferenceArticleLandingFragment$handleContentNotDownloaded$1(this, new MedscapeException(getResources().getString(R.string.alert_dialog_clinical_article_network_connection_error_message)), string), 200);
        }
    }

    public void handleContentNotAvailable(String str, boolean z) {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        view.setVisibility(8);
        ProgressBar progressBar2 = this.progressBar;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        progressBar2.setVisibility(8);
        MedscapeException medscapeException = new MedscapeException(getResources().getString(R.string.alert_dialog_content_unavailable_message));
        String string = getResources().getString(R.string.alert_dialog_open_in_browser);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…t_dialog_open_in_browser)");
        String string2 = getResources().getString(R.string.alert_dialog_confirmation_button_text_close);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…mation_button_text_close)");
        if (z) {
            medscapeException.showAlert(getActivity(), string, new ClinicalReferenceArticleLandingFragment$handleContentNotAvailable$1(this, str), string2, new ClinicalReferenceArticleLandingFragment$handleContentNotAvailable$2(this));
            return;
        }
        View view2 = this.rootView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        medscapeException.showSnackBar(view2, -2, getResources().getString(R.string.alert_dialog_open_in_browser), new ClinicalReferenceArticleLandingFragment$handleContentNotAvailable$3(this, str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0023, code lost:
        r2 = r2.getCrArticle();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleContentDownloaded(java.lang.String r4) {
        /*
            r3 = this;
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r0 = r3.crLandingViewModel
            if (r0 == 0) goto L_0x000d
            com.medscape.android.reference.model.ClinicalReferenceArticle r0 = r0.getCrArticle()
            if (r0 == 0) goto L_0x000d
            r0.setLocalXmlFilePath(r4)
        L_0x000d:
            com.medscape.android.reference.task.ParseClinicalReferenceArticleXMLTask r4 = new com.medscape.android.reference.task.ParseClinicalReferenceArticleXMLTask
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            android.content.Context r0 = (android.content.Context) r0
            r1 = r3
            com.medscape.android.reference.interfaces.ContentLoaderCallback r1 = (com.medscape.android.reference.interfaces.ContentLoaderCallback) r1
            r4.<init>(r0, r1)
            r0 = 1
            java.lang.String[] r0 = new java.lang.String[r0]
            r1 = 0
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r2 = r3.crLandingViewModel
            if (r2 == 0) goto L_0x002e
            com.medscape.android.reference.model.ClinicalReferenceArticle r2 = r2.getCrArticle()
            if (r2 == 0) goto L_0x002e
            java.lang.String r2 = r2.getLocalXMLPath()
            goto L_0x002f
        L_0x002e:
            r2 = 0
        L_0x002f:
            r0[r1] = r2
            r4.execute(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment.handleContentDownloaded(java.lang.String):void");
    }

    private final void startReferenceArticleContentActivity(int i) {
        if (isAdded()) {
            Intent intent = new Intent(getActivity(), ClinicalReferenceArticleActivity.class);
            intent.putExtra("fromLandingPage", true);
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
            String str = null;
            intent.putExtra("article", clinicalReferenceArticleViewModel != null ? clinicalReferenceArticleViewModel.getCrArticle() : null);
            intent.putExtra("section", i);
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
            intent.putExtra("uri", clinicalReferenceArticleViewModel2 != null ? clinicalReferenceArticleViewModel2.getUri() : null);
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel3 = this.crLandingViewModel;
            if (clinicalReferenceArticleViewModel3 != null) {
                str = clinicalReferenceArticleViewModel3.getPageNumber();
            }
            intent.putExtra("pageNumber", str);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.startActivity(intent);
            }
        }
    }

    public final void setupNightMode(boolean z) {
        if (z) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                Context context = activity;
                int color = ContextCompat.getColor(context, R.color.white);
                TextView textView = this.articleName;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("articleName");
                }
                textView.setTextColor(color);
                TextView textView2 = this.articleUpdateDate;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("articleUpdateDate");
                }
                textView2.setTextColor(color);
                TextView textView3 = this.articleAuthor;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("articleAuthor");
                }
                textView3.setTextColor(color);
                TextView textView4 = this.chiefEditor;
                if (textView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("chiefEditor");
                }
                textView4.setTextColor(color);
                TextView textView5 = this.updateLabel;
                if (textView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateLabel");
                }
                textView5.setTextColor(color);
                TextView textView6 = this.authorLabel;
                if (textView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("authorLabel");
                }
                textView6.setTextColor(color);
                TextView textView7 = this.chiefLabel;
                if (textView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("chiefLabel");
                }
                textView7.setTextColor(color);
                ImageView imageView = this.openContributorsBtn;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("openContributorsBtn");
                }
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_keyboard_arrow_right_white_24dp));
                int color2 = ContextCompat.getColor(context, R.color.black);
                RelativeLayout relativeLayout = this.tocHeader;
                if (relativeLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tocHeader");
                }
                relativeLayout.setBackgroundColor(color2);
                return;
            }
            return;
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            Context context2 = activity2;
            int color3 = ContextCompat.getColor(context2, R.color.text_main_group);
            TextView textView8 = this.articleName;
            if (textView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("articleName");
            }
            textView8.setTextColor(color3);
            TextView textView9 = this.updateLabel;
            if (textView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("updateLabel");
            }
            textView9.setTextColor(color3);
            TextView textView10 = this.authorLabel;
            if (textView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("authorLabel");
            }
            textView10.setTextColor(color3);
            TextView textView11 = this.chiefLabel;
            if (textView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chiefLabel");
            }
            textView11.setTextColor(color3);
            int color4 = ContextCompat.getColor(context2, R.color.hint_color);
            TextView textView12 = this.articleUpdateDate;
            if (textView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("articleUpdateDate");
            }
            textView12.setTextColor(color4);
            TextView textView13 = this.articleAuthor;
            if (textView13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("articleAuthor");
            }
            textView13.setTextColor(color4);
            TextView textView14 = this.chiefEditor;
            if (textView14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chiefEditor");
            }
            textView14.setTextColor(color4);
            ImageView imageView2 = this.openContributorsBtn;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("openContributorsBtn");
            }
            imageView2.setImageDrawable(ContextCompat.getDrawable(context2, R.drawable.ic_keyboard_arrow_right_black_24dp));
            int color5 = ContextCompat.getColor(context2, R.color.clinref_article_toc_header);
            RelativeLayout relativeLayout2 = this.tocHeader;
            if (relativeLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tocHeader");
            }
            relativeLayout2.setBackgroundColor(color5);
        }
    }

    private final void requestBottomBannerAd() {
        AdManager adManager;
        HashMap<String, String> screenSpecificMap;
        HashMap hashMap = new HashMap();
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
        if (!(clinicalReferenceArticleViewModel == null || (screenSpecificMap = clinicalReferenceArticleViewModel.getScreenSpecificMap()) == null)) {
            hashMap.putAll(screenSpecificMap);
        }
        int value = Pos.StickyAdhesive.value();
        AdSize adSize = AdSize.BANNER;
        Intrinsics.checkNotNullExpressionValue(adSize, "AdSize.BANNER");
        AdSize adSize2 = DFPAdAction.ADSIZE_300x50;
        Intrinsics.checkNotNullExpressionValue(adSize2, "ADSIZE_300x50");
        AdSize adSize3 = DFPAdAction.ADSIZE_3x3;
        Intrinsics.checkNotNullExpressionValue(adSize3, "ADSIZE_3x3");
        AdSize adSize4 = DFPAdAction.ADSIZE_1x3;
        Intrinsics.checkNotNullExpressionValue(adSize4, "ADSIZE_1x3");
        AdParams adParams = new AdParams(value, new AdSize[]{adSize, adSize2, adSize3, adSize4}, new String[]{"11864416", "11848473"}, hashMap);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Intrinsics.checkNotNullExpressionValue(activity, "it");
            Context context = activity;
            adManager = new AdManager(context, new AdAppEvents(context, false, (View) null, 4, (DefaultConstructorMarker) null), new AdConversions(context));
        } else {
            adManager = null;
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 != null && adManager != null) {
            Intrinsics.checkNotNullExpressionValue(activity2, "it");
            Context context2 = activity2;
            adManager.loadAd(context2, new AdBiddingProvider[]{new ProclivityBidder(context2), new MedianetBidder(context2)}, new ClinicalReferenceArticleLandingFragment$requestBottomBannerAd$$inlined$let$lambda$1(this, adManager, adParams), adParams);
        }
    }

    private final void setupObserver() {
        MutableLiveData<Status> networkStateStatus;
        MutableLiveData<Integer> textSize;
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
        if (!(clinicalReferenceArticleViewModel == null || (textSize = clinicalReferenceArticleViewModel.getTextSize()) == null)) {
            textSize.observe(getViewLifecycleOwner(), new ClinicalReferenceArticleLandingFragment$setupObserver$1(this));
        }
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
        if (clinicalReferenceArticleViewModel2 != null && (networkStateStatus = clinicalReferenceArticleViewModel2.getNetworkStateStatus()) != null) {
            networkStateStatus.observe(getViewLifecycleOwner(), new ClinicalReferenceArticleLandingFragment$setupObserver$2(this));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/contentviewer/ClinicalReferenceArticleLandingFragment$Companion;", "", "()V", "newInstance", "Lcom/medscape/android/contentviewer/ClinicalReferenceArticleLandingFragment;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ClinicalReferenceArticleLandingFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ClinicalReferenceArticleLandingFragment newInstance() {
            return new ClinicalReferenceArticleLandingFragment();
        }
    }
}
