package com.medscape.android.landingfeed.views;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.databinding.HomeCardInvitationBinding;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.myinvites.MyInvitationsActivity;
import com.wbmd.omniture.OmnitureTracker;
import com.wbmd.omniture.PageView;
import com.webmd.wbmdomnituremanager.WBMDOmnitureUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002J \u0010\u0012\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bJ \u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002J \u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0017"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemInvitationViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/medscape/android/databinding/HomeCardInvitationBinding;", "(Lcom/medscape/android/databinding/HomeCardInvitationBinding;)V", "getBinding", "()Lcom/medscape/android/databinding/HomeCardInvitationBinding;", "setBinding", "generateMLink", "", "feedType", "", "openDestinationUrl", "", "item", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "context", "Landroidx/fragment/app/FragmentActivity;", "setData", "setGenericInvitation", "setSpecificInvitation", "showInvitations", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedItemInvitationViewHolder.kt */
public final class FeedItemInvitationViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String MODULE_GENERIC_INVITATION = "gen-invite";
    public static final String MODULE_SPECIFIC_INVITATION = "spec-invite";
    private HomeCardInvitationBinding binding;

    private final String generateMLink(int i) {
        return (i == 1 || i != 2) ? "home" : "news";
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeedItemInvitationViewHolder(HomeCardInvitationBinding homeCardInvitationBinding) {
        super(homeCardInvitationBinding.getRoot());
        Intrinsics.checkNotNullParameter(homeCardInvitationBinding, "binding");
        this.binding = homeCardInvitationBinding;
    }

    public final HomeCardInvitationBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(HomeCardInvitationBinding homeCardInvitationBinding) {
        Intrinsics.checkNotNullParameter(homeCardInvitationBinding, "<set-?>");
        this.binding = homeCardInvitationBinding;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemInvitationViewHolder$Companion;", "", "()V", "MODULE_GENERIC_INVITATION", "", "MODULE_SPECIFIC_INVITATION", "create", "Lcom/medscape/android/landingfeed/views/FeedItemInvitationViewHolder;", "parent", "Landroid/view/ViewGroup;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeedItemInvitationViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FeedItemInvitationViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            HomeCardInvitationBinding inflate = HomeCardInvitationBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "HomeCardInvitationBindin….context), parent, false)");
            return new FeedItemInvitationViewHolder(inflate);
        }
    }

    public final void setData(FeedDataItem feedDataItem, FragmentActivity fragmentActivity, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        this.binding.setItem(feedDataItem);
        String type = feedDataItem != null ? feedDataItem.getType() : null;
        if (type != null) {
            int hashCode = type.hashCode();
            if (hashCode != 1116519331) {
                if (hashCode == 1195341721 && type.equals(FeedConstants.INVITATION_AD)) {
                    setSpecificInvitation(feedDataItem, fragmentActivity, i);
                }
            } else if (type.equals(FeedConstants.INVITATION_GENERIC)) {
                setGenericInvitation(feedDataItem, fragmentActivity, i);
            }
        }
        this.binding.executePendingBindings();
    }

    private final void setGenericInvitation(FeedDataItem feedDataItem, FragmentActivity fragmentActivity, int i) {
        this.binding.textType.setText(R.string.home_invitation_title_my);
        this.binding.textViewButton.setText(R.string.home_invitation_view_all);
        this.binding.rootContainer.setOnClickListener(new FeedItemInvitationViewHolder$setGenericInvitation$1(this, fragmentActivity, i));
        this.binding.textViewButton.setOnClickListener(new FeedItemInvitationViewHolder$setGenericInvitation$2(this, fragmentActivity, i));
        TextView textView = this.binding.textDescription;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.textDescription");
        textView.setVisibility(8);
        TextView textView2 = this.binding.textInformation;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.textInformation");
        textView2.setVisibility(8);
    }

    private final void setSpecificInvitation(FeedDataItem feedDataItem, FragmentActivity fragmentActivity, int i) {
        this.binding.textType.setText(R.string.home_invitation_title_new);
        this.binding.rootContainer.setOnClickListener(new FeedItemInvitationViewHolder$setSpecificInvitation$1(this, feedDataItem, fragmentActivity, i));
        CharSequence description = feedDataItem.getDescription();
        boolean z = true;
        if (description == null || description.length() == 0) {
            TextView textView = this.binding.textDescription;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.textDescription");
            textView.setVisibility(8);
        } else {
            TextView textView2 = this.binding.textDescription;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.textDescription");
            textView2.setText(HtmlCompat.fromHtml(feedDataItem.getDescription(), 0));
            TextView textView3 = this.binding.textDescription;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.textDescription");
            textView3.setVisibility(0);
        }
        CharSequence body = feedDataItem.getBody();
        if (body == null || body.length() == 0) {
            TextView textView4 = this.binding.textInformation;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.textInformation");
            textView4.setVisibility(8);
        } else {
            TextView textView5 = this.binding.textInformation;
            Intrinsics.checkNotNullExpressionValue(textView5, "binding.textInformation");
            textView5.setText(HtmlCompat.fromHtml(feedDataItem.getBody(), 0));
            TextView textView6 = this.binding.textInformation;
            Intrinsics.checkNotNullExpressionValue(textView6, "binding.textInformation");
            textView6.setVisibility(0);
        }
        CharSequence ctaLink = feedDataItem.getCtaLink();
        if (!(ctaLink == null || ctaLink.length() == 0)) {
            z = false;
        }
        if (z) {
            TextView textView7 = this.binding.textViewButton;
            Intrinsics.checkNotNullExpressionValue(textView7, "binding.textViewButton");
            textView7.setVisibility(8);
            return;
        }
        TextView textView8 = this.binding.textViewButton;
        Intrinsics.checkNotNullExpressionValue(textView8, "binding.textViewButton");
        textView8.setText(feedDataItem.getCtaLink());
        TextView textView9 = this.binding.textViewButton;
        Intrinsics.checkNotNullExpressionValue(textView9, "binding.textViewButton");
        textView9.setVisibility(0);
        this.binding.textViewButton.setOnClickListener(new FeedItemInvitationViewHolder$setSpecificInvitation$2(this, feedDataItem, fragmentActivity, i));
    }

    /* access modifiers changed from: private */
    public final void showInvitations(FragmentActivity fragmentActivity, int i) {
        OmnitureTracker omnitureTracker = new OmnitureTracker();
        PageView build = new PageView.PageViewBuilder().page("profile/myinvitations").channel("other").mlink(generateMLink(i)).mmodule(MODULE_GENERIC_INVITATION).build();
        String generateNewPvid = WBMDOmnitureUtil.generateNewPvid();
        Intrinsics.checkNotNullExpressionValue(generateNewPvid, "WBMDOmnitureUtil.generateNewPvid()");
        omnitureTracker.sendPageView(build, generateNewPvid);
        fragmentActivity.startActivity(new Intent(fragmentActivity, MyInvitationsActivity.class));
    }

    /* access modifiers changed from: private */
    public final void openDestinationUrl(FeedDataItem feedDataItem, FragmentActivity fragmentActivity, int i) {
        String url = feedDataItem.getUrl();
        if (url != null) {
            WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, fragmentActivity, url, feedDataItem.getTitle(), MODULE_SPECIFIC_INVITATION, generateMLink(i), "other", "", 1, false, 256, (Object) null);
        }
    }
}
