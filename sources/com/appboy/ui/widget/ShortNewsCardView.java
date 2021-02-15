package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.feed.view.BaseFeedCardView;

public class ShortNewsCardView extends BaseFeedCardView<ShortNewsCard> {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(ShortNewsCardView.class);
    private final float mAspectRatio;
    /* access modifiers changed from: private */
    public IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private ImageView mImage;
    private final TextView mTitle;

    public ShortNewsCardView(Context context) {
        this(context, (ShortNewsCard) null);
    }

    public ShortNewsCardView(Context context, ShortNewsCard shortNewsCard) {
        super(context);
        this.mAspectRatio = 1.0f;
        this.mDescription = (TextView) findViewById(R.id.com_appboy_short_news_card_description);
        this.mTitle = (TextView) findViewById(R.id.com_appboy_short_news_card_title);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_short_news_card_domain);
        ImageView imageView = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_short_news_card_imageview_stub);
        this.mImage = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.mImage.setAdjustViewBounds(true);
        if (shortNewsCard != null) {
            setCard(shortNewsCard);
        }
        setBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_short_news_card;
    }

    public void onSetCard(final ShortNewsCard shortNewsCard) {
        this.mDescription.setText(shortNewsCard.getDescription());
        setOptionalTextView(this.mTitle, shortNewsCard.getTitle());
        setOptionalTextView(this.mDomain, shortNewsCard.getDomain());
        this.mCardAction = getUriActionForCard(shortNewsCard);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShortNewsCardView shortNewsCardView = ShortNewsCardView.this;
                shortNewsCardView.handleCardClick(shortNewsCardView.mContext, shortNewsCard, ShortNewsCardView.this.mCardAction, ShortNewsCardView.TAG);
            }
        });
        setImageViewToUrl(this.mImage, shortNewsCard.getImageUrl(), 1.0f);
    }
}
