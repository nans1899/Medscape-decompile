package com.appboy.ui.contentcards.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;

public class TextAnnouncementContentCardView extends BaseContentCardView<TextAnnouncementCard> {
    public TextAnnouncementContentCardView(Context context) {
        super(context);
    }

    private class ViewHolder extends ContentCardViewHolder {
        private final TextView mDescription;
        private final TextView mTitle;

        ViewHolder(View view) {
            super(view, TextAnnouncementContentCardView.this.isUnreadIndicatorEnabled());
            this.mTitle = (TextView) view.findViewById(R.id.com_appboy_content_cards_text_announcement_card_title);
            this.mDescription = (TextView) view.findViewById(R.id.com_appboy_content_cards_text_announcement_card_description);
        }

        /* access modifiers changed from: package-private */
        public TextView getTitle() {
            return this.mTitle;
        }

        /* access modifiers changed from: package-private */
        public TextView getDescription() {
            return this.mDescription;
        }
    }

    public ContentCardViewHolder createViewHolder(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.com_appboy_text_announcement_content_card, viewGroup, false);
        inflate.setBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
        return new ViewHolder(inflate);
    }

    public void bindViewHolder(ContentCardViewHolder contentCardViewHolder, TextAnnouncementCard textAnnouncementCard) {
        super.bindViewHolder(contentCardViewHolder, textAnnouncementCard);
        ViewHolder viewHolder = (ViewHolder) contentCardViewHolder;
        viewHolder.getTitle().setText(textAnnouncementCard.getTitle());
        viewHolder.getDescription().setText(textAnnouncementCard.getDescription());
        viewHolder.setActionHintText(StringUtils.isNullOrBlank(textAnnouncementCard.getDomain()) ? textAnnouncementCard.getUrl() : textAnnouncementCard.getDomain());
    }
}
