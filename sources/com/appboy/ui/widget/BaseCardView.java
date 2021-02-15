package com.appboy.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appboy.Appboy;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.enums.AppboyViewBounds;
import com.appboy.enums.Channel;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.R;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.actions.UriAction;
import com.appboy.ui.feed.AppboyImageSwitcher;

public abstract class BaseCardView<T extends Card> extends RelativeLayout {
    private static final String ICON_READ_TAG = "icon_read";
    private static final String ICON_UNREAD_TAG = "icon_unread";
    private static final float SQUARE_ASPECT_RATIO = 1.0f;
    private static final String TAG = AppboyLogger.getAppboyLogTag(BaseCardView.class);
    private static Boolean sUnreadCardVisualIndicatorEnabled;
    protected AppboyConfigurationProvider mAppboyConfigurationProvider;
    protected T mCard;
    private final String mClassLogTag;
    /* access modifiers changed from: protected */
    public final Context mContext;
    protected AppboyImageSwitcher mImageSwitcher;

    /* access modifiers changed from: protected */
    public abstract boolean isClickHandled(Context context, Card card, IAction iAction);

    public BaseCardView(Context context) {
        super(context);
        this.mContext = context.getApplicationContext();
        if (this.mAppboyConfigurationProvider == null) {
            this.mAppboyConfigurationProvider = new AppboyConfigurationProvider(context);
        }
        if (sUnreadCardVisualIndicatorEnabled == null) {
            sUnreadCardVisualIndicatorEnabled = Boolean.valueOf(this.mAppboyConfigurationProvider.getIsNewsfeedVisualIndicatorOn());
        }
        this.mClassLogTag = AppboyLogger.getAppboyLogTag(getClass());
    }

    public void setOptionalTextView(TextView textView, String str) {
        if (str == null || str.trim().equals("")) {
            textView.setText("");
            textView.setVisibility(8);
            return;
        }
        textView.setText(str);
        textView.setVisibility(0);
    }

    public void setImageViewToUrl(ImageView imageView, String str) {
        setImageViewToUrl(imageView, str, 1.0f, false);
    }

    public void setImageViewToUrl(ImageView imageView, String str, float f) {
        setImageViewToUrl(imageView, str, f, true);
    }

    public void setImageViewToUrl(final ImageView imageView, String str, final float f, boolean z) {
        if (str == null) {
            AppboyLogger.w(TAG, "The image url to render is null. Not setting the card image.");
        } else if (f == 0.0f) {
            AppboyLogger.w(TAG, "The image aspect ratio is 0. Not setting the card image.");
        } else if (!str.equals(imageView.getTag(R.string.com_appboy_image_resize_tag_key))) {
            if (f != 1.0f) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            int width = imageView.getWidth();
                            imageView.setLayoutParams(new RelativeLayout.LayoutParams(width, (int) (((float) width) / f)));
                            imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                }
            }
            imageView.setImageResource(17170445);
            Appboy.getInstance(getContext()).getAppboyImageLoader().renderUrlIntoView(getContext(), str, imageView, AppboyViewBounds.BASE_CARD_VIEW);
            imageView.setTag(R.string.com_appboy_image_resize_tag_key, str);
        }
    }

    public void setCardViewedIndicator(AppboyImageSwitcher appboyImageSwitcher, Card card) {
        if (card == null) {
            AppboyLogger.d(getClassLogTag(), "The card is null. Not setting read/unread indicator.");
        } else if (appboyImageSwitcher != null) {
            String str = (String) appboyImageSwitcher.getTag(R.string.com_appboy_image_is_read_tag_key);
            if (str == null) {
                str = "";
            }
            if (card.isIndicatorHighlighted()) {
                if (!str.equals(ICON_READ_TAG)) {
                    if (appboyImageSwitcher.getReadIcon() != null) {
                        appboyImageSwitcher.setImageDrawable(appboyImageSwitcher.getReadIcon());
                    } else {
                        appboyImageSwitcher.setImageResource(R.drawable.icon_read);
                    }
                    appboyImageSwitcher.setTag(R.string.com_appboy_image_is_read_tag_key, ICON_READ_TAG);
                }
            } else if (!str.equals(ICON_UNREAD_TAG)) {
                if (appboyImageSwitcher.getUnReadIcon() != null) {
                    appboyImageSwitcher.setImageDrawable(appboyImageSwitcher.getUnReadIcon());
                } else {
                    appboyImageSwitcher.setImageResource(R.drawable.icon_unread);
                }
                appboyImageSwitcher.setTag(R.string.com_appboy_image_is_read_tag_key, ICON_UNREAD_TAG);
            }
        }
    }

    public String getClassLogTag() {
        return this.mClassLogTag;
    }

    public boolean isUnreadIndicatorEnabled() {
        return sUnreadCardVisualIndicatorEnabled.booleanValue();
    }

    protected static UriAction getUriActionForCard(Card card) {
        Bundle bundle = new Bundle();
        for (String next : card.getExtras().keySet()) {
            bundle.putString(next, card.getExtras().get(next));
        }
        return ActionFactory.createUriActionFromUrlString(card.getUrl(), bundle, card.getOpenUriInWebView(), Channel.NEWS_FEED);
    }

    /* access modifiers changed from: protected */
    public void handleCardClick(Context context, Card card, IAction iAction, String str) {
        card.setIndicatorHighlighted(true);
        if (iAction != null) {
            if (card.logClick()) {
                AppboyLogger.d(str, "Logged click for card: " + card.getId());
            } else {
                AppboyLogger.d(str, "Logging click failed for card: " + card.getId());
            }
            if (isClickHandled(context, card, iAction)) {
                String str2 = TAG;
                AppboyLogger.d(str2, "Card click was handled by custom listener for card: " + card.getId());
            } else if (iAction instanceof UriAction) {
                AppboyNavigator.getAppboyNavigator().gotoUri(context, (UriAction) iAction);
            } else {
                String str3 = TAG;
                AppboyLogger.d(str3, "Executing non uri action for click on card: " + card.getId());
                iAction.execute(context);
            }
        } else {
            String str4 = TAG;
            AppboyLogger.v(str4, "Card action is null. Not performing any click action for card: " + card.getId());
        }
    }
}
