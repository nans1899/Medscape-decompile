package com.appboy.ui.contentcards.view;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;

public class ShortNewsContentCardView extends BaseContentCardView<ShortNewsCard> {
    private static final float DEFAULT_ASPECT_RATIO = 1.0f;

    public ShortNewsContentCardView(Context context) {
        super(context);
    }

    private class ViewHolder extends ContentCardViewHolder {
        private View mCardImage;
        private final TextView mDescription;
        private final TextView mTitle;

        ViewHolder(View view) {
            super(view, ShortNewsContentCardView.this.isUnreadIndicatorEnabled());
            this.mCardImage = createCardImageWithStyle(ShortNewsContentCardView.this.getContext(), view, R.style.Appboy_ContentCards_ShortNews_ImageContainer_Image, R.id.com_appboy_content_cards_short_news_card_image_container);
            this.mTitle = (TextView) view.findViewById(R.id.com_appboy_content_cards_short_news_card_title);
            this.mDescription = (TextView) view.findViewById(R.id.com_appboy_content_cards_short_news_card_description);
        }

        /* access modifiers changed from: package-private */
        public TextView getTitle() {
            return this.mTitle;
        }

        /* access modifiers changed from: package-private */
        public TextView getDescription() {
            return this.mDescription;
        }

        /* access modifiers changed from: package-private */
        public ImageView getImageView() {
            View view = this.mCardImage;
            if (view instanceof ImageView) {
                return (ImageView) view;
            }
            return null;
        }
    }

    public ContentCardViewHolder createViewHolder(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.com_appboy_short_news_content_card, viewGroup, false);
        inflate.setBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
        return new ViewHolder(inflate);
    }

    public void bindViewHolder(ContentCardViewHolder contentCardViewHolder, ShortNewsCard shortNewsCard) {
        super.bindViewHolder(contentCardViewHolder, shortNewsCard);
        ViewHolder viewHolder = (ViewHolder) contentCardViewHolder;
        viewHolder.getTitle().setText(shortNewsCard.getTitle());
        viewHolder.getDescription().setText(shortNewsCard.getDescription());
        viewHolder.setActionHintText(StringUtils.isNullOrBlank(shortNewsCard.getDomain()) ? shortNewsCard.getUrl() : shortNewsCard.getDomain());
        setOptionalCardImage(viewHolder.getImageView(), 1.0f, shortNewsCard.getImageUrl(), 1.0f);
        if (Build.VERSION.SDK_INT >= 21) {
            safeSetClipToOutline(viewHolder.getImageView());
        }
    }
}
