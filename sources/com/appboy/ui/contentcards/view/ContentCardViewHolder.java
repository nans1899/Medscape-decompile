package com.appboy.ui.contentcards.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.appboy.ui.R;

public class ContentCardViewHolder extends RecyclerView.ViewHolder {
    private final TextView mActionHint;
    private final ImageView mPinnedIcon;
    private final View mUnreadBar;

    public ContentCardViewHolder(View view, boolean z) {
        super(view);
        View findViewById = view.findViewById(R.id.com_appboy_content_cards_unread_bar);
        this.mUnreadBar = findViewById;
        if (findViewById != null) {
            if (z) {
                findViewById.setVisibility(0);
                this.mUnreadBar.setBackground(view.getContext().getResources().getDrawable(R.drawable.com_appboy_content_cards_unread_bar_background));
            } else {
                findViewById.setVisibility(8);
            }
        }
        this.mPinnedIcon = (ImageView) view.findViewById(R.id.com_appboy_content_cards_pinned_icon);
        this.mActionHint = (TextView) view.findViewById(R.id.com_appboy_content_cards_action_hint);
    }

    public void setPinnedIconVisible(boolean z) {
        ImageView imageView = this.mPinnedIcon;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    public void setUnreadBarVisible(boolean z) {
        View view = this.mUnreadBar;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    public void setActionHintVisible(boolean z) {
        TextView textView = this.mActionHint;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
        }
    }

    public void setActionHintText(String str) {
        TextView textView = this.mActionHint;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public View createCardImageWithStyle(Context context, View view, int i, int i2) {
        ImageView imageView = new ImageView(new ContextThemeWrapper(context, i), (AttributeSet) null, i);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        ((RelativeLayout) view.findViewById(i2)).addView(imageView);
        return imageView;
    }
}
