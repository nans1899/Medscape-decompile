package com.medscape.android.activity.help.viewholders;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.help.ProfileMenuItems;
import com.medscape.android.myinvites.MyInvitationsManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\b\u0010*\u001a\u00020%H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010\u0004¨\u0006+"}, d2 = {"Lcom/medscape/android/activity/help/viewholders/HelpViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "viewItem", "Landroid/view/View;", "(Landroid/view/View;)V", "arrow", "Landroid/widget/ImageView;", "getArrow", "()Landroid/widget/ImageView;", "setArrow", "(Landroid/widget/ImageView;)V", "invitesText", "Landroid/widget/TextView;", "getInvitesText", "()Landroid/widget/TextView;", "setInvitesText", "(Landroid/widget/TextView;)V", "logoutButton", "Landroid/widget/Button;", "getLogoutButton", "()Landroid/widget/Button;", "setLogoutButton", "(Landroid/widget/Button;)V", "rootView", "Landroid/widget/RelativeLayout;", "kotlin.jvm.PlatformType", "getRootView", "()Landroid/widget/RelativeLayout;", "setRootView", "(Landroid/widget/RelativeLayout;)V", "textView", "getTextView", "setTextView", "getViewItem", "()Landroid/view/View;", "setViewItem", "bind", "", "activity", "Landroid/app/Activity;", "item", "Lcom/medscape/android/activity/help/ProfileMenuItems;", "resetDefaultUnits", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HelpViewHolder.kt */
public final class HelpViewHolder extends RecyclerView.ViewHolder {
    private ImageView arrow;
    private TextView invitesText;
    private Button logoutButton;
    private RelativeLayout rootView = ((RelativeLayout) this.itemView.findViewById(R.id.help_root_view));
    private TextView textView;
    private View viewItem;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HelpViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "viewItem");
        this.viewItem = view;
        View findViewById = this.itemView.findViewById(R.id.rowTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.rowTitle)");
        this.textView = (TextView) findViewById;
        View findViewById2 = this.itemView.findViewById(R.id.yes_button);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.yes_button)");
        this.logoutButton = (Button) findViewById2;
        View findViewById3 = this.itemView.findViewById(R.id.open_invitations);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.open_invitations)");
        this.invitesText = (TextView) findViewById3;
        View findViewById4 = this.itemView.findViewById(R.id.editButton);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.editButton)");
        this.arrow = (ImageView) findViewById4;
    }

    public final View getViewItem() {
        return this.viewItem;
    }

    public final void setViewItem(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.viewItem = view;
    }

    public final RelativeLayout getRootView() {
        return this.rootView;
    }

    public final void setRootView(RelativeLayout relativeLayout) {
        this.rootView = relativeLayout;
    }

    public final TextView getTextView() {
        return this.textView;
    }

    public final void setTextView(TextView textView2) {
        Intrinsics.checkNotNullParameter(textView2, "<set-?>");
        this.textView = textView2;
    }

    public final Button getLogoutButton() {
        return this.logoutButton;
    }

    public final void setLogoutButton(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.logoutButton = button;
    }

    public final TextView getInvitesText() {
        return this.invitesText;
    }

    public final void setInvitesText(TextView textView2) {
        Intrinsics.checkNotNullParameter(textView2, "<set-?>");
        this.invitesText = textView2;
    }

    public final ImageView getArrow() {
        return this.arrow;
    }

    public final void setArrow(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.arrow = imageView;
    }

    public final void bind(Activity activity, ProfileMenuItems profileMenuItems) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(profileMenuItems, ContentParser.ITEM);
        this.textView.setText(profileMenuItems.getText());
        String text = profileMenuItems.getText();
        View view = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "itemView");
        if (text.equals(view.getContext().getString(R.string.title_logout_preference))) {
            this.arrow.setVisibility(8);
        } else {
            this.arrow.setVisibility(0);
        }
        if (profileMenuItems.isLogOut()) {
            this.arrow.setVisibility(8);
            this.logoutButton.setVisibility(0);
            this.logoutButton.setOnClickListener(new HelpViewHolder$bind$1(this, activity));
        } else {
            this.logoutButton.setVisibility(8);
        }
        if (profileMenuItems.isInvites()) {
            MyInvitationsManager.Companion companion = MyInvitationsManager.Companion;
            View view2 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view2, "itemView");
            Context context = view2.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "itemView.context");
            Integer openInvitations = companion.get(context).getOpenInvitations();
            if (openInvitations == null) {
                return;
            }
            if (openInvitations.intValue() > 0) {
                this.invitesText.setVisibility(0);
                this.invitesText.setText(String.valueOf(openInvitations.intValue()));
                return;
            }
            this.invitesText.setVisibility(8);
            return;
        }
        this.invitesText.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public final void resetDefaultUnits() {
        UserManager instance = UserManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "UserManager.getInstance()");
        DBUser dbUser = instance.getDbUser();
        Intrinsics.checkNotNullExpressionValue(dbUser, "dbUser");
        dbUser.setAutoEnterFirstQuestion(false);
        dbUser.setDefaultUnits(Unit.UnitType.convertTypeToString(Unit.UnitType.US_UNITS));
        dbUser.update();
    }
}
