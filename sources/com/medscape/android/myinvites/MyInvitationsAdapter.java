package com.medscape.android.myinvites;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.databinding.MyInvitationsCardBinding;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.myinvites.specific.Invitation;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u0016\u0017\u0018\u0019B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\b\u001a\u00020\t2\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "()V", "items", "Ljava/util/ArrayList;", "Lcom/medscape/android/myinvites/MyInvitationsAdapter$Row;", "Lkotlin/collections/ArrayList;", "addItems", "", "data", "Lcom/medscape/android/myinvites/specific/Invitation;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "MyInvitationRow", "MyInvitationViewHolder", "Row", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MyInvitationsAdapter.kt */
public final class MyInvitationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int TYPE_INVITATION = 0;
    private ArrayList<Row> items = new ArrayList<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsAdapter$Row;", "", "()V", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MyInvitationsAdapter.kt */
    public static abstract class Row {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsAdapter$MyInvitationRow;", "Lcom/medscape/android/myinvites/MyInvitationsAdapter$Row;", "invitation", "Lcom/medscape/android/myinvites/specific/Invitation;", "(Lcom/medscape/android/myinvites/specific/Invitation;)V", "getInvitation", "()Lcom/medscape/android/myinvites/specific/Invitation;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MyInvitationsAdapter.kt */
    public static final class MyInvitationRow extends Row {
        private final Invitation invitation;

        public MyInvitationRow(Invitation invitation2) {
            Intrinsics.checkNotNullParameter(invitation2, FeedConstants.INVITATION_AD);
            this.invitation = invitation2;
        }

        public final Invitation getInvitation() {
            return this.invitation;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsAdapter$Companion;", "", "()V", "TYPE_INVITATION", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MyInvitationsAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            MyInvitationsCardBinding inflate = MyInvitationsCardBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "MyInvitationsCardBinding…tInflater, parent, false)");
            return new MyInvitationViewHolder(this, inflate);
        }
        throw new IllegalArgumentException();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Row row = this.items.get(i);
        Intrinsics.checkNotNullExpressionValue(row, "items[position]");
        Row row2 = row;
        if (viewHolder.getItemViewType() == 0) {
            MyInvitationViewHolder myInvitationViewHolder = (MyInvitationViewHolder) viewHolder;
            if (row2 != null) {
                myInvitationViewHolder.bindData(((MyInvitationRow) row2).getInvitation());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.myinvites.MyInvitationsAdapter.MyInvitationRow");
        }
        throw new IllegalArgumentException();
    }

    public int getItemCount() {
        return this.items.size();
    }

    public int getItemViewType(int i) {
        if (this.items.get(i) instanceof MyInvitationRow) {
            return 0;
        }
        throw new IllegalArgumentException();
    }

    public final void addItems(ArrayList<Invitation> arrayList) {
        if (arrayList != null) {
            for (Invitation myInvitationRow : arrayList) {
                this.items.add(new MyInvitationRow(myInvitationRow));
            }
        }
        notifyDataSetChanged();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsAdapter$MyInvitationViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/medscape/android/databinding/MyInvitationsCardBinding;", "(Lcom/medscape/android/myinvites/MyInvitationsAdapter;Lcom/medscape/android/databinding/MyInvitationsCardBinding;)V", "getBinding", "()Lcom/medscape/android/databinding/MyInvitationsCardBinding;", "setBinding", "(Lcom/medscape/android/databinding/MyInvitationsCardBinding;)V", "bindData", "", "invitation", "Lcom/medscape/android/myinvites/specific/Invitation;", "openLink", "context", "Landroid/content/Context;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MyInvitationsAdapter.kt */
    public final class MyInvitationViewHolder extends RecyclerView.ViewHolder {
        private MyInvitationsCardBinding binding;
        final /* synthetic */ MyInvitationsAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MyInvitationViewHolder(MyInvitationsAdapter myInvitationsAdapter, MyInvitationsCardBinding myInvitationsCardBinding) {
            super(myInvitationsCardBinding.getRoot());
            Intrinsics.checkNotNullParameter(myInvitationsCardBinding, "binding");
            this.this$0 = myInvitationsAdapter;
            this.binding = myInvitationsCardBinding;
        }

        public final MyInvitationsCardBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(MyInvitationsCardBinding myInvitationsCardBinding) {
            Intrinsics.checkNotNullParameter(myInvitationsCardBinding, "<set-?>");
            this.binding = myInvitationsCardBinding;
        }

        public final void bindData(Invitation invitation) {
            Intrinsics.checkNotNullParameter(invitation, FeedConstants.INVITATION_AD);
            this.binding.setInvitation(invitation);
            this.binding.executePendingBindings();
            this.binding.textCta.setOnClickListener(new MyInvitationsAdapter$MyInvitationViewHolder$bindData$1(this, invitation));
            this.binding.rootContainer.setOnClickListener(new MyInvitationsAdapter$MyInvitationViewHolder$bindData$2(this, invitation));
        }

        /* access modifiers changed from: private */
        public final void openLink(Context context, Invitation invitation) {
            if (context != null && invitation.getDestUrl() != null) {
                WebviewUtil.Companion companion = WebviewUtil.Companion;
                String destUrl = invitation.getDestUrl();
                Intrinsics.checkNotNull(destUrl);
                companion.launchPlainWebView(context, destUrl, Html.fromHtml(invitation.getTitle()).toString(), "", "", "", "", 1, false);
            }
        }
    }
}
