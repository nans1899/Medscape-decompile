package com.medscape.android.landingfeed.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.view.PageIndicator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u0018\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\bJ\u0018\u0010\u0018\u001a\u00020\u00102\u000e\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001aH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemConsultViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "clickListener", "Landroid/view/View$OnClickListener;", "mContext", "Landroidx/fragment/app/FragmentActivity;", "getMContext", "()Landroidx/fragment/app/FragmentActivity;", "setMContext", "(Landroidx/fragment/app/FragmentActivity;)V", "selectedData", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "applyText", "", "textView", "Landroid/widget/TextView;", "text", "", "setData", "data", "context", "setupImageViewer", "imageUrls", "", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedItemConsultViewHolder.kt */
public final class FeedItemConsultViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final View.OnClickListener clickListener = new FeedItemConsultViewHolder$clickListener$1(this);
    private FragmentActivity mContext;
    /* access modifiers changed from: private */
    public FeedDataItem selectedData;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeedItemConsultViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
        new PagerSnapHelper().attachToRecyclerView((RecyclerView) view.findViewById(R.id.content_image_viewer));
    }

    public final FragmentActivity getMContext() {
        return this.mContext;
    }

    public final void setMContext(FragmentActivity fragmentActivity) {
        this.mContext = fragmentActivity;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0129, code lost:
        if (r13.into((android.widget.ImageView) r4.findViewById(com.medscape.android.R.id.consult_partner_logo)) != null) goto L_0x0141;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setData(com.medscape.android.landingfeed.model.FeedDataItem r12, androidx.fragment.app.FragmentActivity r13) {
        /*
            r11 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "itemView.root_layout"
            r1 = 8
            java.lang.String r2 = "itemView"
            if (r12 == 0) goto L_0x02cb
            r11.selectedData = r12
            r11.mContext = r13
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r3 = com.medscape.android.R.id.root_layout
            android.view.View r13 = r13.findViewById(r3)
            android.widget.FrameLayout r13 = (android.widget.FrameLayout) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r0)
            r0 = 0
            r13.setVisibility(r0)
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r3 = com.medscape.android.R.id.type_label
            android.view.View r13 = r13.findViewById(r3)
            com.medscape.android.view.CustomFontTextView r13 = (com.medscape.android.view.CustomFontTextView) r13
            java.lang.String r3 = "itemView.type_label"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            android.widget.TextView r13 = (android.widget.TextView) r13
            java.lang.String r3 = r12.getType()
            r11.applyText(r13, r3)
            com.medscape.android.landingfeed.model.Author r13 = r12.getAuthor()
            java.lang.String r3 = "itemView.consult_header_body"
            if (r13 != 0) goto L_0x005d
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r4 = com.medscape.android.R.id.consult_header_body
            android.view.View r13 = r13.findViewById(r4)
            android.widget.RelativeLayout r13 = (android.widget.RelativeLayout) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            r13.setVisibility(r1)
            goto L_0x0141
        L_0x005d:
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r4 = com.medscape.android.R.id.consult_header_body
            android.view.View r13 = r13.findViewById(r4)
            android.widget.RelativeLayout r13 = (android.widget.RelativeLayout) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            r13.setVisibility(r0)
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            android.content.Context r13 = r13.getContext()
            com.medscape.android.util.GlideRequests r13 = com.medscape.android.util.GlideApp.with((android.content.Context) r13)
            com.medscape.android.landingfeed.model.Author r3 = r12.getAuthor()
            java.lang.String r3 = r3.getAvatarUrl()
            com.medscape.android.util.GlideRequest r13 = r13.load((java.lang.String) r3)
            com.bumptech.glide.request.RequestOptions r3 = com.bumptech.glide.request.RequestOptions.circleCropTransform()
            com.bumptech.glide.request.BaseRequestOptions r3 = (com.bumptech.glide.request.BaseRequestOptions) r3
            com.medscape.android.util.GlideRequest r13 = r13.apply((com.bumptech.glide.request.BaseRequestOptions) r3)
            r3 = 2131230820(0x7f080064, float:1.8077704E38)
            com.medscape.android.util.GlideRequest r13 = r13.placeholder((int) r3)
            android.view.View r3 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            int r4 = com.medscape.android.R.id.contact_image
            android.view.View r3 = r3.findViewById(r4)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            r13.into((android.widget.ImageView) r3)
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r3 = com.medscape.android.R.id.contact_name
            android.view.View r13 = r13.findViewById(r3)
            android.widget.TextView r13 = (android.widget.TextView) r13
            java.lang.String r3 = "itemView.contact_name"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            com.medscape.android.landingfeed.model.Author r3 = r12.getAuthor()
            java.lang.String r3 = r3.getDisplayName()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r13.setText(r3)
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r3 = com.medscape.android.R.id.contact_field
            android.view.View r13 = r13.findViewById(r3)
            android.widget.TextView r13 = (android.widget.TextView) r13
            java.lang.String r3 = "itemView.contact_field"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            com.medscape.android.landingfeed.model.Author r3 = r12.getAuthor()
            java.lang.String r3 = r3.getSpecialty()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r13.setText(r3)
            com.medscape.android.landingfeed.model.Author r13 = r12.getAuthor()
            java.lang.String r13 = r13.getInstitutionLogo()
            java.lang.String r3 = "itemView.consult_partner_logo"
            if (r13 == 0) goto L_0x012c
            android.view.View r4 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            int r5 = com.medscape.android.R.id.consult_partner_logo
            android.view.View r4 = r4.findViewById(r5)
            android.widget.ImageView r4 = (android.widget.ImageView) r4
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r3)
            r4.setVisibility(r0)
            android.view.View r4 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            android.content.Context r4 = r4.getContext()
            com.medscape.android.util.GlideRequests r4 = com.medscape.android.util.GlideApp.with((android.content.Context) r4)
            com.medscape.android.util.GlideRequest r13 = r4.load((java.lang.String) r13)
            android.view.View r4 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            int r5 = com.medscape.android.R.id.consult_partner_logo
            android.view.View r4 = r4.findViewById(r5)
            android.widget.ImageView r4 = (android.widget.ImageView) r4
            com.bumptech.glide.request.target.ViewTarget r13 = r13.into((android.widget.ImageView) r4)
            if (r13 == 0) goto L_0x012c
            goto L_0x0141
        L_0x012c:
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r4 = com.medscape.android.R.id.consult_partner_logo
            android.view.View r13 = r13.findViewById(r4)
            android.widget.ImageView r13 = (android.widget.ImageView) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            r13.setVisibility(r1)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
        L_0x0141:
            java.util.List r13 = r12.getImageUrls()
            if (r13 != 0) goto L_0x0151
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.List r13 = (java.util.List) r13
            r12.setImageUrls(r13)
        L_0x0151:
            java.lang.String r13 = r12.getImageUrl()
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            r3 = 1
            if (r13 == 0) goto L_0x0163
            boolean r13 = kotlin.text.StringsKt.isBlank(r13)
            if (r13 == 0) goto L_0x0161
            goto L_0x0163
        L_0x0161:
            r13 = 0
            goto L_0x0164
        L_0x0163:
            r13 = 1
        L_0x0164:
            if (r13 != 0) goto L_0x0173
            java.util.List r13 = r12.getImageUrls()
            if (r13 == 0) goto L_0x0173
            java.lang.String r4 = r12.getImageUrl()
            r13.add(r0, r4)
        L_0x0173:
            java.util.List r13 = r12.getImageUrls()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            int r13 = r13.size()
            java.lang.String r4 = "itemView.content_image_layout"
            if (r13 != 0) goto L_0x0196
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r5 = com.medscape.android.R.id.content_image_layout
            android.view.View r13 = r13.findViewById(r5)
            android.widget.LinearLayout r13 = (android.widget.LinearLayout) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r4)
            r13.setVisibility(r1)
            goto L_0x01b3
        L_0x0196:
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r5 = com.medscape.android.R.id.content_image_layout
            android.view.View r13 = r13.findViewById(r5)
            android.widget.LinearLayout r13 = (android.widget.LinearLayout) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r4)
            r13.setVisibility(r0)
            java.util.List r13 = r12.getImageUrls()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            r11.setupImageViewer(r13)
        L_0x01b3:
            java.lang.String r13 = r12.getTitle()
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            if (r13 == 0) goto L_0x01c4
            boolean r13 = kotlin.text.StringsKt.isBlank(r13)
            if (r13 == 0) goto L_0x01c2
            goto L_0x01c4
        L_0x01c2:
            r13 = 0
            goto L_0x01c5
        L_0x01c4:
            r13 = 1
        L_0x01c5:
            java.lang.String r4 = "itemView.content_title"
            if (r13 != 0) goto L_0x0213
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r5 = com.medscape.android.R.id.content_title
            android.view.View r13 = r13.findViewById(r5)
            com.medscape.android.view.CustomFontTextView r13 = (com.medscape.android.view.CustomFontTextView) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r4)
            r13.setVisibility(r0)
            android.text.SpannableStringBuilder r13 = new android.text.SpannableStringBuilder
            java.lang.String r5 = r12.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r8 = 0
            r9 = 4
            r10 = 0
            java.lang.String r6 = "\n"
            java.lang.String r7 = "<br />"
            java.lang.String r5 = kotlin.text.StringsKt.replace$default((java.lang.String) r5, (java.lang.String) r6, (java.lang.String) r7, (boolean) r8, (int) r9, (java.lang.Object) r10)
            android.text.Spanned r5 = android.text.Html.fromHtml(r5)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r13.<init>(r5)
            android.view.View r5 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r2)
            int r6 = com.medscape.android.R.id.content_title
            android.view.View r5 = r5.findViewById(r6)
            com.medscape.android.view.CustomFontTextView r5 = (com.medscape.android.view.CustomFontTextView) r5
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r4)
            android.text.SpannableStringBuilder r13 = com.medscape.android.util.HtmlFormatter.removeTrailingLineBreaks(r13)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            r5.setText(r13)
            goto L_0x0226
        L_0x0213:
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r5 = com.medscape.android.R.id.content_title
            android.view.View r13 = r13.findViewById(r5)
            com.medscape.android.view.CustomFontTextView r13 = (com.medscape.android.view.CustomFontTextView) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r4)
            r13.setVisibility(r1)
        L_0x0226:
            java.lang.String r13 = r12.getBody()
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            if (r13 == 0) goto L_0x0236
            boolean r13 = kotlin.text.StringsKt.isBlank(r13)
            if (r13 == 0) goto L_0x0235
            goto L_0x0236
        L_0x0235:
            r3 = 0
        L_0x0236:
            java.lang.String r13 = "itemView.content_body"
            if (r3 != 0) goto L_0x0276
            android.view.View r1 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            int r3 = com.medscape.android.R.id.content_body
            android.view.View r1 = r1.findViewById(r3)
            com.medscape.android.view.CustomFontTextView r1 = (com.medscape.android.view.CustomFontTextView) r1
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r13)
            r1.setVisibility(r0)
            android.text.SpannableStringBuilder r0 = new android.text.SpannableStringBuilder
            java.lang.String r1 = r12.getBody()
            android.text.Spanned r1 = android.text.Html.fromHtml(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.<init>(r1)
            android.view.View r1 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            int r3 = com.medscape.android.R.id.content_body
            android.view.View r1 = r1.findViewById(r3)
            com.medscape.android.view.CustomFontTextView r1 = (com.medscape.android.view.CustomFontTextView) r1
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r13)
            android.text.SpannableStringBuilder r13 = com.medscape.android.util.HtmlFormatter.removeTrailingLineBreaks(r0)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            r1.setText(r13)
            goto L_0x0289
        L_0x0276:
            android.view.View r0 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            int r3 = com.medscape.android.R.id.content_body
            android.view.View r0 = r0.findViewById(r3)
            com.medscape.android.view.CustomFontTextView r0 = (com.medscape.android.view.CustomFontTextView) r0
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r13)
            r0.setVisibility(r1)
        L_0x0289:
            java.lang.Integer r13 = r12.getReplyCount()
            java.lang.String r0 = "itemView.comment_count"
            if (r13 == 0) goto L_0x02b3
            android.view.View r13 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            int r1 = com.medscape.android.R.id.comment_count
            android.view.View r13 = r13.findViewById(r1)
            android.widget.TextView r13 = (android.widget.TextView) r13
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r0)
            java.lang.Integer r12 = r12.getReplyCount()
            int r12 = r12.intValue()
            java.lang.String r12 = com.medscape.android.consult.util.ConsultUtils.getFormattedConsultCount(r12)
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            r13.setText(r12)
            goto L_0x02de
        L_0x02b3:
            android.view.View r12 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r2)
            int r13 = com.medscape.android.R.id.comment_count
            android.view.View r12 = r12.findViewById(r13)
            android.widget.TextView r12 = (android.widget.TextView) r12
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            java.lang.String r13 = "0"
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            r12.setText(r13)
            goto L_0x02de
        L_0x02cb:
            android.view.View r12 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r2)
            int r13 = com.medscape.android.R.id.root_layout
            android.view.View r12 = r12.findViewById(r13)
            android.widget.FrameLayout r12 = (android.widget.FrameLayout) r12
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            r12.setVisibility(r1)
        L_0x02de:
            android.view.View r12 = r11.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r2)
            int r13 = com.medscape.android.R.id.root_layout
            android.view.View r12 = r12.findViewById(r13)
            android.widget.FrameLayout r12 = (android.widget.FrameLayout) r12
            android.view.View$OnClickListener r13 = r11.clickListener
            r12.setOnClickListener(r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.views.FeedItemConsultViewHolder.setData(com.medscape.android.landingfeed.model.FeedDataItem, androidx.fragment.app.FragmentActivity):void");
    }

    private final void setupImageViewer(List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            CharSequence charSequence = next;
            if (charSequence != null && !StringsKt.isBlank(charSequence)) {
                z = false;
            }
            if (!z) {
                Intrinsics.checkNotNull(next);
                arrayList.add(next);
            }
        }
        InlineImageViewerAdapter inlineImageViewerAdapter = new InlineImageViewerAdapter(arrayList, this.clickListener);
        View view = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "itemView");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.content_image_viewer);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "itemView.content_image_viewer");
        View view2 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view2, "itemView");
        recyclerView.setLayoutManager(new LinearLayoutManager(view2.getContext(), 0, false));
        View view3 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view3, "itemView");
        RecyclerView recyclerView2 = (RecyclerView) view3.findViewById(R.id.content_image_viewer);
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "itemView.content_image_viewer");
        recyclerView2.setAdapter(inlineImageViewerAdapter);
        if (arrayList.size() > 1) {
            View view4 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view4, "itemView");
            PageIndicator pageIndicator = (PageIndicator) view4.findViewById(R.id.content_image_indicator);
            Intrinsics.checkNotNullExpressionValue(pageIndicator, "itemView.content_image_indicator");
            pageIndicator.setVisibility(0);
            View view5 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view5, "itemView");
            PageIndicator pageIndicator2 = (PageIndicator) view5.findViewById(R.id.content_image_indicator);
            Intrinsics.checkNotNullExpressionValue(pageIndicator2, "itemView.content_image_indicator");
            pageIndicator2.setTotalPages(arrayList.size());
            View view6 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view6, "itemView");
            PageIndicator pageIndicator3 = (PageIndicator) view6.findViewById(R.id.content_image_indicator);
            Intrinsics.checkNotNullExpressionValue(pageIndicator3, "itemView.content_image_indicator");
            pageIndicator3.setSelectedPage(0);
            View view7 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view7, "itemView");
            ((RecyclerView) view7.findViewById(R.id.content_image_viewer)).addOnScrollListener(new FeedItemConsultViewHolder$setupImageViewer$1(this));
            return;
        }
        View view8 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view8, "itemView");
        PageIndicator pageIndicator4 = (PageIndicator) view8.findViewById(R.id.content_image_indicator);
        Intrinsics.checkNotNullExpressionValue(pageIndicator4, "itemView.content_image_indicator");
        pageIndicator4.setVisibility(8);
    }

    private final void applyText(TextView textView, String str) {
        CharSequence charSequence = str;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(charSequence);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemConsultViewHolder$Companion;", "", "()V", "create", "Lcom/medscape/android/landingfeed/views/FeedItemConsultViewHolder;", "parent", "Landroid/view/ViewGroup;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeedItemConsultViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FeedItemConsultViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_card_consult, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "view");
            return new FeedItemConsultViewHolder(inflate);
        }
    }
}
