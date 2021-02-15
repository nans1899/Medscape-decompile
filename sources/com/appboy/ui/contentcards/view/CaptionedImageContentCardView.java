package com.appboy.ui.contentcards.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;

public class CaptionedImageContentCardView extends BaseContentCardView<CaptionedImageCard> {
    private static final float DEFAULT_ASPECT_RATIO = 1.3333334f;

    public CaptionedImageContentCardView(Context context) {
        super(context);
    }

    private class ViewHolder extends ContentCardViewHolder {
        private View mCardImage;
        private final TextView mDescription;
        private final TextView mTitle;

        ViewHolder(View view) {
            super(view, CaptionedImageContentCardView.this.isUnreadIndicatorEnabled());
            this.mCardImage = createCardImageWithStyle(CaptionedImageContentCardView.this.getContext(), view, R.style.Appboy_ContentCards_CaptionedImage_ImageContainer_Image, R.id.com_appboy_content_cards_captioned_image_card_image_container);
            this.mTitle = (TextView) view.findViewById(R.id.com_appboy_content_cards_captioned_image_title);
            this.mDescription = (TextView) view.findViewById(R.id.com_appboy_content_cards_captioned_image_description);
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
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.com_appboy_captioned_image_content_card, viewGroup, false);
        inflate.setBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
        return new ViewHolder(inflate);
    }

    public void bindViewHolder(ContentCardViewHolder contentCardViewHolder, CaptionedImageCard captionedImageCard) {
        super.bindViewHolder(contentCardViewHolder, captionedImageCard);
        ViewHolder viewHolder = (ViewHolder) contentCardViewHolder;
        viewHolder.getTitle().setText(captionedImageCard.getTitle());
        viewHolder.getDescription().setText(captionedImageCard.getDescription());
        viewHolder.setActionHintText(StringUtils.isNullOrBlank(captionedImageCard.getDomain()) ? captionedImageCard.getUrl() : captionedImageCard.getDomain());
        setOptionalCardImage(viewHolder.getImageView(), captionedImageCard.getAspectRatio(), captionedImageCard.getImageUrl(), DEFAULT_ASPECT_RATIO);
    }
}
