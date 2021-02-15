package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.feed.view.BaseFeedCardView;

public class BannerImageCardView extends BaseFeedCardView<BannerImageCard> {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(BannerImageCardView.class);
    private float mAspectRatio;
    /* access modifiers changed from: private */
    public IAction mCardAction;
    private ImageView mImage;

    public BannerImageCardView(Context context) {
        this(context, (BannerImageCard) null);
    }

    public BannerImageCardView(Context context, BannerImageCard bannerImageCard) {
        super(context);
        this.mAspectRatio = 6.0f;
        ImageView imageView = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_banner_image_card_imageview_stub);
        this.mImage = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.mImage.setAdjustViewBounds(true);
        if (bannerImageCard != null) {
            setCard(bannerImageCard);
        }
        setBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_banner_image_card;
    }

    public void onSetCard(final BannerImageCard bannerImageCard) {
        boolean z;
        if (bannerImageCard.getAspectRatio() != 0.0f) {
            this.mAspectRatio = bannerImageCard.getAspectRatio();
            z = true;
        } else {
            z = false;
        }
        setImageViewToUrl(this.mImage, bannerImageCard.getImageUrl(), this.mAspectRatio, z);
        this.mCardAction = getUriActionForCard(bannerImageCard);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BannerImageCardView bannerImageCardView = BannerImageCardView.this;
                bannerImageCardView.handleCardClick(bannerImageCardView.mContext, bannerImageCard, BannerImageCardView.this.mCardAction, BannerImageCardView.TAG);
            }
        });
    }
}
