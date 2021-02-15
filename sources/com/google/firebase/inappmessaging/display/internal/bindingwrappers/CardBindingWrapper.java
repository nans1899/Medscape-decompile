package com.google.firebase.inappmessaging.display.internal.bindingwrappers;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.firebase.inappmessaging.display.R;
import com.google.firebase.inappmessaging.display.internal.InAppMessageLayoutConfig;
import com.google.firebase.inappmessaging.display.internal.layout.BaseModalLayout;
import com.google.firebase.inappmessaging.display.internal.layout.FiamCardView;
import com.google.firebase.inappmessaging.model.Action;
import com.google.firebase.inappmessaging.model.CardMessage;
import com.google.firebase.inappmessaging.model.InAppMessage;
import com.google.firebase.inappmessaging.model.MessageType;
import java.util.Map;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class CardBindingWrapper extends BindingWrapper {
    private ScrollView bodyScroll;
    private BaseModalLayout cardContentRoot;
    private CardMessage cardMessage;
    private FiamCardView cardRoot;
    private View.OnClickListener dismissListener;
    /* access modifiers changed from: private */
    public ImageView imageView;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ScrollViewAdjustableListener();
    private TextView messageBody;
    private TextView messageTitle;
    private Button primaryButton;
    private Button secondaryButton;

    @Inject
    public CardBindingWrapper(InAppMessageLayoutConfig inAppMessageLayoutConfig, LayoutInflater layoutInflater, InAppMessage inAppMessage) {
        super(inAppMessageLayoutConfig, layoutInflater, inAppMessage);
    }

    public ViewTreeObserver.OnGlobalLayoutListener inflate(Map<Action, View.OnClickListener> map, View.OnClickListener onClickListener) {
        View inflate = this.inflater.inflate(R.layout.card, (ViewGroup) null);
        this.bodyScroll = (ScrollView) inflate.findViewById(R.id.body_scroll);
        this.primaryButton = (Button) inflate.findViewById(R.id.primary_button);
        this.secondaryButton = (Button) inflate.findViewById(R.id.secondary_button);
        this.imageView = (ImageView) inflate.findViewById(R.id.image_view);
        this.messageBody = (TextView) inflate.findViewById(R.id.message_body);
        this.messageTitle = (TextView) inflate.findViewById(R.id.message_title);
        this.cardRoot = (FiamCardView) inflate.findViewById(R.id.card_root);
        this.cardContentRoot = (BaseModalLayout) inflate.findViewById(R.id.card_content_root);
        if (this.message.getMessageType().equals(MessageType.CARD)) {
            CardMessage cardMessage2 = (CardMessage) this.message;
            this.cardMessage = cardMessage2;
            setMessage(cardMessage2);
            setImage(this.cardMessage);
            setButtons(map);
            setLayoutConfig(this.config);
            setDismissListener(onClickListener);
            setViewBgColorFromHex(this.cardContentRoot, this.cardMessage.getBackgroundHexColor());
        }
        return this.layoutListener;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public View getScrollView() {
        return this.bodyScroll;
    }

    public View getTitleView() {
        return this.messageTitle;
    }

    public ViewGroup getRootView() {
        return this.cardRoot;
    }

    public View getDialogView() {
        return this.cardContentRoot;
    }

    public InAppMessageLayoutConfig getConfig() {
        return this.config;
    }

    public View.OnClickListener getDismissListener() {
        return this.dismissListener;
    }

    public Button getPrimaryButton() {
        return this.primaryButton;
    }

    public Button getSecondaryButton() {
        return this.secondaryButton;
    }

    private void setMessage(CardMessage cardMessage2) {
        this.messageTitle.setText(cardMessage2.getTitle().getText());
        this.messageTitle.setTextColor(Color.parseColor(cardMessage2.getTitle().getHexColor()));
        if (cardMessage2.getBody() == null || cardMessage2.getBody().getText() == null) {
            this.bodyScroll.setVisibility(8);
            this.messageBody.setVisibility(8);
            return;
        }
        this.bodyScroll.setVisibility(0);
        this.messageBody.setVisibility(0);
        this.messageBody.setText(cardMessage2.getBody().getText());
        this.messageBody.setTextColor(Color.parseColor(cardMessage2.getBody().getHexColor()));
    }

    private void setButtons(Map<Action, View.OnClickListener> map) {
        Action primaryAction = this.cardMessage.getPrimaryAction();
        Action secondaryAction = this.cardMessage.getSecondaryAction();
        setupViewButtonFromModel(this.primaryButton, primaryAction.getButton());
        setButtonActionListener(this.primaryButton, map.get(primaryAction));
        this.primaryButton.setVisibility(0);
        if (secondaryAction == null || secondaryAction.getButton() == null) {
            this.secondaryButton.setVisibility(8);
            return;
        }
        setupViewButtonFromModel(this.secondaryButton, secondaryAction.getButton());
        setButtonActionListener(this.secondaryButton, map.get(secondaryAction));
        this.secondaryButton.setVisibility(0);
    }

    private void setImage(CardMessage cardMessage2) {
        if (cardMessage2.getPortraitImageData() == null && cardMessage2.getLandscapeImageData() == null) {
            this.imageView.setVisibility(8);
        } else {
            this.imageView.setVisibility(0);
        }
    }

    private void setLayoutConfig(InAppMessageLayoutConfig inAppMessageLayoutConfig) {
        this.imageView.setMaxHeight(inAppMessageLayoutConfig.getMaxImageHeight());
        this.imageView.setMaxWidth(inAppMessageLayoutConfig.getMaxImageWidth());
    }

    private void setDismissListener(View.OnClickListener onClickListener) {
        this.dismissListener = onClickListener;
        this.cardRoot.setDismissListener(onClickListener);
    }

    public void setLayoutListener(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        this.layoutListener = onGlobalLayoutListener;
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    public class ScrollViewAdjustableListener implements ViewTreeObserver.OnGlobalLayoutListener {
        public ScrollViewAdjustableListener() {
        }

        public void onGlobalLayout() {
            CardBindingWrapper.this.imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }
}
