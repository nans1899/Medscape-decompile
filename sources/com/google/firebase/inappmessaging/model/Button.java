package com.google.firebase.inappmessaging.model;

import android.text.TextUtils;
import com.google.firebase.inappmessaging.MessagesProto;
import com.google.firebase.inappmessaging.model.Text;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class Button {
    private final String buttonHexColor;
    private final Text text;

    public int hashCode() {
        return this.text.hashCode() + this.buttonHexColor.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Button)) {
            return false;
        }
        Button button = (Button) obj;
        return hashCode() == button.hashCode() && this.text.equals(button.text) && this.buttonHexColor.equals(button.buttonHexColor);
    }

    private Button(Text text2, String str) {
        this.text = text2;
        this.buttonHexColor = str;
    }

    public Text getText() {
        return this.text;
    }

    public String getButtonHexColor() {
        return this.buttonHexColor;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static class Builder {
        private String buttonHexColor;
        private Text text;

        public Builder setText(Text text2) {
            this.text = text2;
            return this;
        }

        public Builder setText(MessagesProto.Text text2) {
            Text.Builder builder = new Text.Builder();
            builder.setText(text2);
            this.text = builder.build();
            return this;
        }

        public Builder setButtonHexColor(String str) {
            this.buttonHexColor = str;
            return this;
        }

        public Button build() {
            if (!TextUtils.isEmpty(this.buttonHexColor)) {
                Text text2 = this.text;
                if (text2 != null) {
                    return new Button(text2, this.buttonHexColor);
                }
                throw new IllegalArgumentException("Button model must have text");
            }
            throw new IllegalArgumentException("Button model must have a color");
        }
    }
}
