package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.feed.view.BaseFeedCardView;

public class TextAnnouncementCardView extends BaseFeedCardView<TextAnnouncementCard> {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(TextAnnouncementCardView.class);
    /* access modifiers changed from: private */
    public IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private final TextView mTitle;

    public TextAnnouncementCardView(Context context) {
        this(context, (TextAnnouncementCard) null);
    }

    public TextAnnouncementCardView(Context context, TextAnnouncementCard textAnnouncementCard) {
        super(context);
        this.mTitle = (TextView) findViewById(R.id.com_appboy_text_announcement_card_title);
        this.mDescription = (TextView) findViewById(R.id.com_appboy_text_announcement_card_description);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_text_announcement_card_domain);
        if (textAnnouncementCard != null) {
            setCard(textAnnouncementCard);
        }
        setBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_text_announcement_card;
    }

    public void onSetCard(final TextAnnouncementCard textAnnouncementCard) {
        this.mTitle.setText(textAnnouncementCard.getTitle());
        this.mDescription.setText(textAnnouncementCard.getDescription());
        setOptionalTextView(this.mDomain, textAnnouncementCard.getDomain());
        this.mCardAction = getUriActionForCard(textAnnouncementCard);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextAnnouncementCardView textAnnouncementCardView = TextAnnouncementCardView.this;
                textAnnouncementCardView.handleCardClick(textAnnouncementCardView.mContext, textAnnouncementCard, TextAnnouncementCardView.this.mCardAction, TextAnnouncementCardView.TAG);
            }
        });
    }
}
