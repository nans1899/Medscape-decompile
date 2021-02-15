package com.appboy.ui.contentcards.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.ui.R;

public class BannerImageContentCardView extends BaseContentCardView<BannerImageCard> {
    private static final float DEFAULT_ASPECT_RATIO = 6.0f;

    public BannerImageContentCardView(Context context) {
        super(context);
    }

    private class ViewHolder extends ContentCardViewHolder {
        private View mCardImage;

        ViewHolder(View view) {
            super(view, BannerImageContentCardView.this.isUnreadIndicatorEnabled());
            this.mCardImage = createCardImageWithStyle(BannerImageContentCardView.this.getContext(), view, R.style.Appboy_ContentCards_BannerImage_ImageContainer_Image, R.id.com_appboy_content_cards_banner_image_card_image_container);
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
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.com_appboy_banner_image_content_card, viewGroup, false);
        inflate.setBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
        return new ViewHolder(inflate);
    }

    public void bindViewHolder(ContentCardViewHolder contentCardViewHolder, BannerImageCard bannerImageCard) {
        super.bindViewHolder(contentCardViewHolder, bannerImageCard);
        setOptionalCardImage(((ViewHolder) contentCardViewHolder).getImageView(), bannerImageCard.getAspectRatio(), bannerImageCard.getImageUrl(), DEFAULT_ASPECT_RATIO);
    }
}
