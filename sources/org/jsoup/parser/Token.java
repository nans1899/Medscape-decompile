package org.jsoup.parser;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;

abstract class Token {
    TokenType type;

    public enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    /* access modifiers changed from: package-private */
    public abstract Token reset();

    private Token() {
    }

    /* access modifiers changed from: package-private */
    public String tokenType() {
        return getClass().getSimpleName();
    }

    static void reset(StringBuilder sb) {
        if (sb != null) {
            sb.delete(0, sb.length());
        }
    }

    static final class Doctype extends Token {
        boolean forceQuirks = false;
        final StringBuilder name = new StringBuilder();
        String pubSysKey = null;
        final StringBuilder publicIdentifier = new StringBuilder();
        final StringBuilder systemIdentifier = new StringBuilder();

        Doctype() {
            super();
            this.type = TokenType.Doctype;
        }

        /* access modifiers changed from: package-private */
        public Token reset() {
            reset(this.name);
            this.pubSysKey = null;
            reset(this.publicIdentifier);
            reset(this.systemIdentifier);
            this.forceQuirks = false;
            return this;
        }

        /* access modifiers changed from: package-private */
        public String getName() {
            return this.name.toString();
        }

        /* access modifiers changed from: package-private */
        public String getPubSysKey() {
            return this.pubSysKey;
        }

        /* access modifiers changed from: package-private */
        public String getPublicIdentifier() {
            return this.publicIdentifier.toString();
        }

        public String getSystemIdentifier() {
            return this.systemIdentifier.toString();
        }

        public boolean isForceQuirks() {
            return this.forceQuirks;
        }
    }

    static abstract class Tag extends Token {
        Attributes attributes;
        private boolean hasEmptyAttributeValue = false;
        private boolean hasPendingAttributeValue = false;
        protected String normalName;
        private String pendingAttributeName;
        private StringBuilder pendingAttributeValue = new StringBuilder();
        private String pendingAttributeValueS;
        boolean selfClosing = false;
        protected String tagName;

        Tag() {
            super();
        }

        /* access modifiers changed from: package-private */
        public Tag reset() {
            this.tagName = null;
            this.normalName = null;
            this.pendingAttributeName = null;
            reset(this.pendingAttributeValue);
            this.pendingAttributeValueS = null;
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            this.selfClosing = false;
            this.attributes = null;
            return this;
        }

        /* access modifiers changed from: package-private */
        public final void newAttribute() {
            String str;
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            String str2 = this.pendingAttributeName;
            if (str2 != null) {
                String trim = str2.trim();
                this.pendingAttributeName = trim;
                if (trim.length() > 0) {
                    if (this.hasPendingAttributeValue) {
                        str = this.pendingAttributeValue.length() > 0 ? this.pendingAttributeValue.toString() : this.pendingAttributeValueS;
                    } else {
                        str = this.hasEmptyAttributeValue ? "" : null;
                    }
                    this.attributes.add(this.pendingAttributeName, str);
                }
            }
            this.pendingAttributeName = null;
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            reset(this.pendingAttributeValue);
            this.pendingAttributeValueS = null;
        }

        /* access modifiers changed from: package-private */
        public final void finaliseTag() {
            if (this.pendingAttributeName != null) {
                newAttribute();
            }
        }

        /* access modifiers changed from: package-private */
        public final String name() {
            String str = this.tagName;
            Validate.isFalse(str == null || str.length() == 0);
            return this.tagName;
        }

        /* access modifiers changed from: package-private */
        public final String normalName() {
            return this.normalName;
        }

        /* access modifiers changed from: package-private */
        public final Tag name(String str) {
            this.tagName = str;
            this.normalName = Normalizer.lowerCase(str);
            return this;
        }

        /* access modifiers changed from: package-private */
        public final boolean isSelfClosing() {
            return this.selfClosing;
        }

        /* access modifiers changed from: package-private */
        public final Attributes getAttributes() {
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            return this.attributes;
        }

        /* access modifiers changed from: package-private */
        public final void appendTagName(String str) {
            String str2 = this.tagName;
            if (str2 != null) {
                str = str2.concat(str);
            }
            this.tagName = str;
            this.normalName = Normalizer.lowerCase(str);
        }

        /* access modifiers changed from: package-private */
        public final void appendTagName(char c) {
            appendTagName(String.valueOf(c));
        }

        /* access modifiers changed from: package-private */
        public final void appendAttributeName(String str) {
            String str2 = this.pendingAttributeName;
            if (str2 != null) {
                str = str2.concat(str);
            }
            this.pendingAttributeName = str;
        }

        /* access modifiers changed from: package-private */
        public final void appendAttributeName(char c) {
            appendAttributeName(String.valueOf(c));
        }

        /* access modifiers changed from: package-private */
        public final void appendAttributeValue(String str) {
            ensureAttributeValue();
            if (this.pendingAttributeValue.length() == 0) {
                this.pendingAttributeValueS = str;
            } else {
                this.pendingAttributeValue.append(str);
            }
        }

        /* access modifiers changed from: package-private */
        public final void appendAttributeValue(char c) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(c);
        }

        /* access modifiers changed from: package-private */
        public final void appendAttributeValue(char[] cArr) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(cArr);
        }

        /* access modifiers changed from: package-private */
        public final void appendAttributeValue(int[] iArr) {
            ensureAttributeValue();
            for (int appendCodePoint : iArr) {
                this.pendingAttributeValue.appendCodePoint(appendCodePoint);
            }
        }

        /* access modifiers changed from: package-private */
        public final void setEmptyAttributeValue() {
            this.hasEmptyAttributeValue = true;
        }

        private void ensureAttributeValue() {
            this.hasPendingAttributeValue = true;
            String str = this.pendingAttributeValueS;
            if (str != null) {
                this.pendingAttributeValue.append(str);
                this.pendingAttributeValueS = null;
            }
        }
    }

    static final class StartTag extends Tag {
        StartTag() {
            this.type = TokenType.StartTag;
        }

        /* access modifiers changed from: package-private */
        public Tag reset() {
            super.reset();
            this.attributes = null;
            return this;
        }

        /* access modifiers changed from: package-private */
        public StartTag nameAttr(String str, Attributes attributes) {
            this.tagName = str;
            this.attributes = attributes;
            this.normalName = Normalizer.lowerCase(this.tagName);
            return this;
        }

        public String toString() {
            if (this.attributes == null || this.attributes.size() <= 0) {
                return HtmlObject.HtmlMarkUp.OPEN_BRACKER + name() + HtmlObject.HtmlMarkUp.CLOSE_BRACKER;
            }
            return HtmlObject.HtmlMarkUp.OPEN_BRACKER + name() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.attributes.toString() + HtmlObject.HtmlMarkUp.CLOSE_BRACKER;
        }
    }

    static final class EndTag extends Tag {
        EndTag() {
            this.type = TokenType.EndTag;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("</");
            sb.append(this.tagName != null ? this.tagName : "(unset)");
            sb.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
            return sb.toString();
        }
    }

    static final class Comment extends Token {
        boolean bogus = false;
        private final StringBuilder data = new StringBuilder();
        private String dataS;

        /* access modifiers changed from: package-private */
        public Token reset() {
            reset(this.data);
            this.dataS = null;
            this.bogus = false;
            return this;
        }

        Comment() {
            super();
            this.type = TokenType.Comment;
        }

        /* access modifiers changed from: package-private */
        public String getData() {
            String str = this.dataS;
            return str != null ? str : this.data.toString();
        }

        /* access modifiers changed from: package-private */
        public final Comment append(String str) {
            ensureData();
            if (this.data.length() == 0) {
                this.dataS = str;
            } else {
                this.data.append(str);
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public final Comment append(char c) {
            ensureData();
            this.data.append(c);
            return this;
        }

        private void ensureData() {
            String str = this.dataS;
            if (str != null) {
                this.data.append(str);
                this.dataS = null;
            }
        }

        public String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    static class Character extends Token {
        private String data;

        Character() {
            super();
            this.type = TokenType.Character;
        }

        /* access modifiers changed from: package-private */
        public Token reset() {
            this.data = null;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Character data(String str) {
            this.data = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public String getData() {
            return this.data;
        }

        public String toString() {
            return getData();
        }
    }

    static final class CData extends Character {
        CData(String str) {
            data(str);
        }

        public String toString() {
            return "<![CDATA[" + getData() + "]]>";
        }
    }

    static final class EOF extends Token {
        /* access modifiers changed from: package-private */
        public Token reset() {
            return this;
        }

        EOF() {
            super();
            this.type = TokenType.EOF;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    /* access modifiers changed from: package-private */
    public final Doctype asDoctype() {
        return (Doctype) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    /* access modifiers changed from: package-private */
    public final StartTag asStartTag() {
        return (StartTag) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    /* access modifiers changed from: package-private */
    public final EndTag asEndTag() {
        return (EndTag) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean isComment() {
        return this.type == TokenType.Comment;
    }

    /* access modifiers changed from: package-private */
    public final Comment asComment() {
        return (Comment) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    /* access modifiers changed from: package-private */
    public final boolean isCData() {
        return this instanceof CData;
    }

    /* access modifiers changed from: package-private */
    public final Character asCharacter() {
        return (Character) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean isEOF() {
        return this.type == TokenType.EOF;
    }
}
