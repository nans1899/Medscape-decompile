package org.mockito.internal.matchers.text;

class FormattedText {
    private final String text;

    public FormattedText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }
}
