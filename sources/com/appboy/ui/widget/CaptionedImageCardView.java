package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.feed.view.BaseFeedCardView;

public class CaptionedImageCardView extends BaseFeedCardView<CaptionedImageCard> {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(CaptionedImageCardView.class);
    private float mAspectRatio;
    /* access modifiers changed from: private */
    public IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private ImageView mImage;
    private final TextView mTitle;

    public CaptionedImageCardView(Context context) {
        this(context, (CaptionedImageCard) null);
    }

    public CaptionedImageCardView(Context context, CaptionedImageCard captionedImageCard) {
        super(context);
        this.mAspectRatio = 1.3333334f;
        ImageView imageView = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_captioned_image_card_imageview_stub);
        this.mImage = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.mImage.setAdjustViewBounds(true);
        this.mTitle = (TextView) findViewById(R.id.com_appboy_captioned_image_title);
        this.mDescription = (TextView) findViewById(R.id.com_appboy_captioned_image_description);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_captioned_image_card_domain);
        if (captionedImageCard != null) {
            setCard(captionedImageCard);
        }
        setBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_captioned_image_card;
    }

    public void onSetCard(final CaptionedImageCard captionedImageCard) {
        boolean z;
        this.mTitle.setText(captionedImageCard.getTitle());
        this.mDescription.setText(captionedImageCard.getDescription());
        setOptionalTextView(this.mDomain, captionedImageCard.getDomain());
        this.mCardAction = getUriActionForCard(captionedImageCard);
        if (captionedImageCard.getAspectRatio() != 0.0f) {
            this.mAspectRatio = captionedImageCard.getAspectRatio();
            z = true;
        } else {
            z = false;
        }
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CaptionedImageCardView captionedImageCardView = CaptionedImageCardView.this;
                captionedImageCardView.handleCardClick(captionedImageCardView.mContext, captionedImageCard, CaptionedImageCardView.this.mCardAction, CaptionedImageCardView.TAG);
            }
        });
        setImageViewToUrl(this.mImage, captionedImageCard.getImageUrl(), this.mAspectRatio, z);
    }
}
