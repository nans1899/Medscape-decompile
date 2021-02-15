package org.jsoup.parser;

import com.facebook.appevents.UserDataStore;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.medscape.android.slideshow.SlideshowPageFragment;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.webmd.wbmdcmepulse.models.articles.ActivityTest;
import com.webmd.wbmdcmepulse.models.articles.HtmlTableRow;
import java.util.ArrayList;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Token;

enum HtmlTreeBuilderState {
    Initial {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                Token.Doctype asDoctype = token.asDoctype();
                DocumentType documentType = new DocumentType(htmlTreeBuilder.settings.normalizeTag(asDoctype.getName()), asDoctype.getPublicIdentifier(), asDoctype.getSystemIdentifier());
                documentType.setPubSysKey(asDoctype.getPubSysKey());
                htmlTreeBuilder.getDocument().appendChild(documentType);
                if (asDoctype.isForceQuirks()) {
                    htmlTreeBuilder.getDocument().quirksMode(Document.QuirksMode.quirks);
                }
                htmlTreeBuilder.transition(BeforeHtml);
            } else {
                htmlTreeBuilder.transition(BeforeHtml);
                return htmlTreeBuilder.process(token);
            }
            return true;
        }
    },
    BeforeHtml {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                htmlTreeBuilder.insert(token.asStartTag());
                htmlTreeBuilder.transition(BeforeHead);
                return true;
            } else if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                if (!token.isEndTag()) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.insertStartTag("html");
            htmlTreeBuilder.transition(BeforeHead);
            return htmlTreeBuilder.process(token);
        }
    },
    BeforeHead {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return InBody.process(token, htmlTreeBuilder);
            } else {
                if (token.isStartTag() && token.asStartTag().normalName().equals("head")) {
                    htmlTreeBuilder.setHeadElement(htmlTreeBuilder.insert(token.asStartTag()));
                    htmlTreeBuilder.transition(InHead);
                    return true;
                } else if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                    htmlTreeBuilder.processStartTag("head");
                    return htmlTreeBuilder.process(token);
                } else if (token.isEndTag()) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    htmlTreeBuilder.processStartTag("head");
                    return htmlTreeBuilder.process(token);
                }
            }
        }
    },
    InHead {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            }
            int i = AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i == 1) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (i == 2) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (i == 3) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                if (normalName.equals("html")) {
                    return InBody.process(token, htmlTreeBuilder);
                }
                if (StringUtil.inSorted(normalName, Constants.InHeadEmpty)) {
                    Element insertEmpty = htmlTreeBuilder.insertEmpty(asStartTag);
                    if (normalName.equals("base") && insertEmpty.hasAttr("href")) {
                        htmlTreeBuilder.maybeSetBaseUri(insertEmpty);
                    }
                } else if (normalName.equals(JSONAPISpecConstants.META)) {
                    htmlTreeBuilder.insertEmpty(asStartTag);
                } else if (normalName.equals("title")) {
                    HtmlTreeBuilderState.handleRcData(asStartTag, htmlTreeBuilder);
                } else if (StringUtil.inSorted(normalName, Constants.InHeadRaw)) {
                    HtmlTreeBuilderState.handleRawtext(asStartTag, htmlTreeBuilder);
                } else if (normalName.equals("noscript")) {
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InHeadNoscript);
                } else if (normalName.equals("script")) {
                    htmlTreeBuilder.tokeniser.transition(TokeniserState.ScriptData);
                    htmlTreeBuilder.markInsertionMode();
                    htmlTreeBuilder.transition(Text);
                    htmlTreeBuilder.insert(asStartTag);
                } else if (!normalName.equals("head")) {
                    return anythingElse(token, htmlTreeBuilder);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            } else if (i != 4) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                String normalName2 = token.asEndTag().normalName();
                if (normalName2.equals("head")) {
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(AfterHead);
                } else if (StringUtil.inSorted(normalName2, Constants.InHeadEnd)) {
                    return anythingElse(token, htmlTreeBuilder);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
            return true;
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            treeBuilder.processEndTag("head");
            return treeBuilder.process(token);
        }
    },
    InHeadNoscript {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            } else if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (token.isEndTag() && token.asEndTag().normalName().equals("noscript")) {
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InHead);
                    return true;
                } else if (HtmlTreeBuilderState.isWhitespace(token) || token.isComment() || (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InHeadNoScriptHead))) {
                    return htmlTreeBuilder.process(token, InHead);
                } else {
                    if (token.isEndTag() && token.asEndTag().normalName().equals("br")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    if ((!token.isStartTag() || !StringUtil.inSorted(token.asStartTag().normalName(), Constants.InHeadNoscriptIgnore)) && !token.isEndTag()) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.insert(new Token.Character().data(token.toString()));
            return true;
        }
    },
    AfterHead {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            } else if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                if (normalName.equals("html")) {
                    return htmlTreeBuilder.process(token, InBody);
                }
                if (normalName.equals("body")) {
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    htmlTreeBuilder.transition(InBody);
                    return true;
                } else if (normalName.equals("frameset")) {
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InFrameset);
                    return true;
                } else if (StringUtil.inSorted(normalName, Constants.InBodyStartToHead)) {
                    htmlTreeBuilder.error(this);
                    Element headElement = htmlTreeBuilder.getHeadElement();
                    htmlTreeBuilder.push(headElement);
                    htmlTreeBuilder.process(token, InHead);
                    htmlTreeBuilder.removeFromStack(headElement);
                    return true;
                } else if (normalName.equals("head")) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    anythingElse(token, htmlTreeBuilder);
                    return true;
                }
            } else if (!token.isEndTag()) {
                anythingElse(token, htmlTreeBuilder);
                return true;
            } else if (StringUtil.inSorted(token.asEndTag().normalName(), Constants.AfterHeadBody)) {
                anythingElse(token, htmlTreeBuilder);
                return true;
            } else {
                htmlTreeBuilder.error(this);
                return false;
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.processStartTag("body");
            htmlTreeBuilder.framesetOk(true);
            return htmlTreeBuilder.process(token);
        }
    },
    InBody {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            int i = AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i == 1) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (i == 2) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (i == 3) {
                return inBodyStartTag(token, htmlTreeBuilder);
            } else {
                if (i == 4) {
                    return inBodyEndTag(token, htmlTreeBuilder);
                }
                if (i == 5) {
                    Token.Character asCharacter = token.asCharacter();
                    if (asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    } else if (!htmlTreeBuilder.framesetOk() || !HtmlTreeBuilderState.isWhitespace((Token) asCharacter)) {
                        htmlTreeBuilder.reconstructFormattingElements();
                        htmlTreeBuilder.insert(asCharacter);
                        htmlTreeBuilder.framesetOk(false);
                    } else {
                        htmlTreeBuilder.reconstructFormattingElements();
                        htmlTreeBuilder.insert(asCharacter);
                    }
                }
            }
            return true;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean inBodyStartTag(org.jsoup.parser.Token r18, org.jsoup.parser.HtmlTreeBuilder r19) {
            /*
                r17 = this;
                r0 = r17
                r1 = r19
                org.jsoup.parser.Token$StartTag r2 = r18.asStartTag()
                java.lang.String r3 = r2.normalName()
                int r4 = r3.hashCode()
                java.lang.String r5 = "isindex"
                java.lang.String r6 = "input"
                java.lang.String r7 = "svg"
                java.lang.String r9 = "nobr"
                java.lang.String r10 = "form"
                java.lang.String r11 = "body"
                java.lang.String r12 = "li"
                java.lang.String r13 = "hr"
                java.lang.String r14 = "option"
                java.lang.String r15 = "button"
                java.lang.String r8 = "a"
                r16 = r2
                switch(r4) {
                    case -1644953643: goto L_0x01ae;
                    case -1377687758: goto L_0x01a5;
                    case -1191214428: goto L_0x019a;
                    case -1010136971: goto L_0x0191;
                    case -1003243718: goto L_0x0186;
                    case -906021636: goto L_0x017b;
                    case -80773204: goto L_0x0170;
                    case 97: goto L_0x0168;
                    case 3200: goto L_0x015d;
                    case 3216: goto L_0x0152;
                    case 3338: goto L_0x0148;
                    case 3453: goto L_0x013f;
                    case 3646: goto L_0x0133;
                    case 3650: goto L_0x0127;
                    case 111267: goto L_0x011b;
                    case 114276: goto L_0x0111;
                    case 118811: goto L_0x0105;
                    case 3029410: goto L_0x00fc;
                    case 3148996: goto L_0x00f3;
                    case 3213227: goto L_0x00e8;
                    case 3344136: goto L_0x00dc;
                    case 3386833: goto L_0x00d2;
                    case 3536714: goto L_0x00c7;
                    case 100313435: goto L_0x00bb;
                    case 100358090: goto L_0x00b1;
                    case 110115790: goto L_0x00a5;
                    case 181975684: goto L_0x0099;
                    case 1973234167: goto L_0x008e;
                    case 2091304424: goto L_0x0084;
                    case 2115613112: goto L_0x0078;
                    default: goto L_0x002b;
                }
            L_0x002b:
                switch(r4) {
                    case 3273: goto L_0x006c;
                    case 3274: goto L_0x0060;
                    case 3275: goto L_0x0054;
                    case 3276: goto L_0x0048;
                    case 3277: goto L_0x003c;
                    case 3278: goto L_0x0030;
                    default: goto L_0x002e;
                }
            L_0x002e:
                goto L_0x01b8
            L_0x0030:
                java.lang.String r4 = "h6"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 27
                goto L_0x01b9
            L_0x003c:
                java.lang.String r4 = "h5"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 26
                goto L_0x01b9
            L_0x0048:
                java.lang.String r4 = "h4"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 25
                goto L_0x01b9
            L_0x0054:
                java.lang.String r4 = "h3"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 24
                goto L_0x01b9
            L_0x0060:
                java.lang.String r4 = "h2"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 23
                goto L_0x01b9
            L_0x006c:
                java.lang.String r4 = "h1"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 22
                goto L_0x01b9
            L_0x0078:
                java.lang.String r4 = "noembed"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 18
                goto L_0x01b9
            L_0x0084:
                boolean r4 = r3.equals(r5)
                if (r4 == 0) goto L_0x01b8
                r4 = 14
                goto L_0x01b9
            L_0x008e:
                java.lang.String r4 = "plaintext"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 7
                goto L_0x01b9
            L_0x0099:
                java.lang.String r4 = "listing"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 29
                goto L_0x01b9
            L_0x00a5:
                java.lang.String r4 = "table"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 10
                goto L_0x01b9
            L_0x00b1:
                boolean r4 = r3.equals(r6)
                if (r4 == 0) goto L_0x01b8
                r4 = 11
                goto L_0x01b9
            L_0x00bb:
                java.lang.String r4 = "image"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 13
                goto L_0x01b9
            L_0x00c7:
                java.lang.String r4 = "span"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 1
                goto L_0x01b9
            L_0x00d2:
                boolean r4 = r3.equals(r9)
                if (r4 == 0) goto L_0x01b8
                r4 = 9
                goto L_0x01b9
            L_0x00dc:
                java.lang.String r4 = "math"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 20
                goto L_0x01b9
            L_0x00e8:
                java.lang.String r4 = "html"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 3
                goto L_0x01b9
            L_0x00f3:
                boolean r4 = r3.equals(r10)
                if (r4 == 0) goto L_0x01b8
                r4 = 6
                goto L_0x01b9
            L_0x00fc:
                boolean r4 = r3.equals(r11)
                if (r4 == 0) goto L_0x01b8
                r4 = 4
                goto L_0x01b9
            L_0x0105:
                java.lang.String r4 = "xmp"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 16
                goto L_0x01b9
            L_0x0111:
                boolean r4 = r3.equals(r7)
                if (r4 == 0) goto L_0x01b8
                r4 = 21
                goto L_0x01b9
            L_0x011b:
                java.lang.String r4 = "pre"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 28
                goto L_0x01b9
            L_0x0127:
                java.lang.String r4 = "rt"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 35
                goto L_0x01b9
            L_0x0133:
                java.lang.String r4 = "rp"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 34
                goto L_0x01b9
            L_0x013f:
                boolean r4 = r3.equals(r12)
                if (r4 == 0) goto L_0x01b8
                r4 = 2
                goto L_0x01b9
            L_0x0148:
                boolean r4 = r3.equals(r13)
                if (r4 == 0) goto L_0x01b8
                r4 = 12
                goto L_0x01b9
            L_0x0152:
                java.lang.String r4 = "dt"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 31
                goto L_0x01b9
            L_0x015d:
                java.lang.String r4 = "dd"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 30
                goto L_0x01b9
            L_0x0168:
                boolean r4 = r3.equals(r8)
                if (r4 == 0) goto L_0x01b8
                r4 = 0
                goto L_0x01b9
            L_0x0170:
                java.lang.String r4 = "optgroup"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 32
                goto L_0x01b9
            L_0x017b:
                java.lang.String r4 = "select"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 19
                goto L_0x01b9
            L_0x0186:
                java.lang.String r4 = "textarea"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 15
                goto L_0x01b9
            L_0x0191:
                boolean r4 = r3.equals(r14)
                if (r4 == 0) goto L_0x01b8
                r4 = 33
                goto L_0x01b9
            L_0x019a:
                java.lang.String r4 = "iframe"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 17
                goto L_0x01b9
            L_0x01a5:
                boolean r4 = r3.equals(r15)
                if (r4 == 0) goto L_0x01b8
                r4 = 8
                goto L_0x01b9
            L_0x01ae:
                java.lang.String r4 = "frameset"
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x01b8
                r4 = 5
                goto L_0x01b9
            L_0x01b8:
                r4 = -1
            L_0x01b9:
                java.lang.String r2 = "p"
                switch(r4) {
                    case 0: goto L_0x05ee;
                    case 1: goto L_0x05e3;
                    case 2: goto L_0x059a;
                    case 3: goto L_0x0564;
                    case 4: goto L_0x050a;
                    case 5: goto L_0x04af;
                    case 6: goto L_0x0493;
                    case 7: goto L_0x047c;
                    case 8: goto L_0x045d;
                    case 9: goto L_0x0440;
                    case 10: goto L_0x041b;
                    case 11: goto L_0x03fe;
                    case 12: goto L_0x03ea;
                    case 13: goto L_0x03d2;
                    case 14: goto L_0x034a;
                    case 15: goto L_0x032a;
                    case 16: goto L_0x0313;
                    case 17: goto L_0x0308;
                    case 18: goto L_0x0301;
                    case 19: goto L_0x02ba;
                    case 20: goto L_0x02b0;
                    case 21: goto L_0x02a6;
                    case 22: goto L_0x0280;
                    case 23: goto L_0x0280;
                    case 24: goto L_0x0280;
                    case 25: goto L_0x0280;
                    case 26: goto L_0x0280;
                    case 27: goto L_0x0280;
                    case 28: goto L_0x0265;
                    case 29: goto L_0x0265;
                    case 30: goto L_0x0215;
                    case 31: goto L_0x0215;
                    case 32: goto L_0x01fa;
                    case 33: goto L_0x01fa;
                    case 34: goto L_0x01d4;
                    case 35: goto L_0x01d4;
                    default: goto L_0x01be;
                }
            L_0x01be:
                r4 = r16
                java.lang.String[] r5 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartEmptyFormatters
                boolean r5 = org.jsoup.internal.StringUtil.inSorted(r3, r5)
                if (r5 == 0) goto L_0x0613
                r19.reconstructFormattingElements()
                r1.insertEmpty(r4)
                r2 = 0
                r1.framesetOk(r2)
                goto L_0x05eb
            L_0x01d4:
                java.lang.String r2 = "ruby"
                boolean r3 = r1.inScope((java.lang.String) r2)
                if (r3 == 0) goto L_0x05eb
                r19.generateImpliedEndTags()
                org.jsoup.nodes.Element r3 = r19.currentElement()
                java.lang.String r3 = r3.normalName()
                boolean r3 = r3.equals(r2)
                if (r3 != 0) goto L_0x01f3
                r1.error(r0)
                r1.popStackToBefore(r2)
            L_0x01f3:
                r4 = r16
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x01fa:
                r4 = r16
                org.jsoup.nodes.Element r2 = r19.currentElement()
                java.lang.String r2 = r2.normalName()
                boolean r2 = r2.equals(r14)
                if (r2 == 0) goto L_0x020d
                r1.processEndTag(r14)
            L_0x020d:
                r19.reconstructFormattingElements()
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x0215:
                r4 = r16
                r3 = 0
                r1.framesetOk(r3)
                java.util.ArrayList r3 = r19.getStack()
                int r5 = r3.size()
                r6 = 1
                int r5 = r5 - r6
            L_0x0225:
                if (r5 <= 0) goto L_0x0257
                java.lang.Object r6 = r3.get(r5)
                org.jsoup.nodes.Element r6 = (org.jsoup.nodes.Element) r6
                java.lang.String r7 = r6.normalName()
                java.lang.String[] r8 = org.jsoup.parser.HtmlTreeBuilderState.Constants.DdDt
                boolean r7 = org.jsoup.internal.StringUtil.inSorted(r7, r8)
                if (r7 == 0) goto L_0x0241
                java.lang.String r3 = r6.normalName()
                r1.processEndTag(r3)
                goto L_0x0257
            L_0x0241:
                boolean r7 = r1.isSpecial(r6)
                if (r7 == 0) goto L_0x0254
                java.lang.String r6 = r6.normalName()
                java.lang.String[] r7 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartLiBreakers
                boolean r6 = org.jsoup.internal.StringUtil.inSorted(r6, r7)
                if (r6 != 0) goto L_0x0254
                goto L_0x0257
            L_0x0254:
                int r5 = r5 + -1
                goto L_0x0225
            L_0x0257:
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x0260
                r1.processEndTag(r2)
            L_0x0260:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x0265:
                r4 = r16
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x0270
                r1.processEndTag(r2)
            L_0x0270:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                org.jsoup.parser.CharacterReader r2 = r1.reader
                java.lang.String r3 = "\n"
                r2.matchConsume(r3)
                r2 = 0
                r1.framesetOk(r2)
                goto L_0x05eb
            L_0x0280:
                r4 = r16
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x028b
                r1.processEndTag(r2)
            L_0x028b:
                org.jsoup.nodes.Element r2 = r19.currentElement()
                java.lang.String r2 = r2.normalName()
                java.lang.String[] r3 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Headings
                boolean r2 = org.jsoup.internal.StringUtil.inSorted(r2, r3)
                if (r2 == 0) goto L_0x02a1
                r1.error(r0)
                r19.pop()
            L_0x02a1:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x02a6:
                r4 = r16
                r19.reconstructFormattingElements()
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x02b0:
                r4 = r16
                r19.reconstructFormattingElements()
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x02ba:
                r4 = r16
                r19.reconstructFormattingElements()
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                r2 = 0
                r1.framesetOk(r2)
                org.jsoup.parser.HtmlTreeBuilderState r2 = r19.state()
                org.jsoup.parser.HtmlTreeBuilderState r3 = InTable
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x02fa
                org.jsoup.parser.HtmlTreeBuilderState r3 = InCaption
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x02fa
                org.jsoup.parser.HtmlTreeBuilderState r3 = InTableBody
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x02fa
                org.jsoup.parser.HtmlTreeBuilderState r3 = InRow
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x02fa
                org.jsoup.parser.HtmlTreeBuilderState r3 = InCell
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x02f3
                goto L_0x02fa
            L_0x02f3:
                org.jsoup.parser.HtmlTreeBuilderState r2 = InSelect
                r1.transition(r2)
                goto L_0x05eb
            L_0x02fa:
                org.jsoup.parser.HtmlTreeBuilderState r2 = InSelectInTable
                r1.transition(r2)
                goto L_0x05eb
            L_0x0301:
                r4 = r16
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r4, r1)
                goto L_0x05eb
            L_0x0308:
                r4 = r16
                r3 = 0
                r1.framesetOk(r3)
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r4, r1)
                goto L_0x05eb
            L_0x0313:
                r4 = r16
                r3 = 0
                boolean r5 = r1.inButtonScope(r2)
                if (r5 == 0) goto L_0x031f
                r1.processEndTag(r2)
            L_0x031f:
                r19.reconstructFormattingElements()
                r1.framesetOk(r3)
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r4, r1)
                goto L_0x05eb
            L_0x032a:
                r4 = r16
                r3 = 0
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                boolean r2 = r4.isSelfClosing()
                if (r2 != 0) goto L_0x05eb
                org.jsoup.parser.Tokeniser r2 = r1.tokeniser
                org.jsoup.parser.TokeniserState r4 = org.jsoup.parser.TokeniserState.Rcdata
                r2.transition(r4)
                r19.markInsertionMode()
                r1.framesetOk(r3)
                org.jsoup.parser.HtmlTreeBuilderState r2 = Text
                r1.transition(r2)
                goto L_0x05eb
            L_0x034a:
                r4 = r16
                r3 = 0
                r1.error(r0)
                org.jsoup.nodes.FormElement r2 = r19.getFormElement()
                if (r2 == 0) goto L_0x0357
                return r3
            L_0x0357:
                r1.processStartTag(r10)
                org.jsoup.nodes.Attributes r2 = r4.attributes
                java.lang.String r3 = "action"
                boolean r2 = r2.hasKey(r3)
                if (r2 == 0) goto L_0x0371
                org.jsoup.nodes.FormElement r2 = r19.getFormElement()
                org.jsoup.nodes.Attributes r7 = r4.attributes
                java.lang.String r7 = r7.get(r3)
                r2.attr((java.lang.String) r3, (java.lang.String) r7)
            L_0x0371:
                r1.processStartTag(r13)
                java.lang.String r2 = "label"
                r1.processStartTag(r2)
                org.jsoup.nodes.Attributes r3 = r4.attributes
                java.lang.String r7 = "prompt"
                boolean r3 = r3.hasKey(r7)
                if (r3 == 0) goto L_0x038a
                org.jsoup.nodes.Attributes r3 = r4.attributes
                java.lang.String r3 = r3.get(r7)
                goto L_0x038c
            L_0x038a:
                java.lang.String r3 = "This is a searchable index. Enter search keywords: "
            L_0x038c:
                org.jsoup.parser.Token$Character r7 = new org.jsoup.parser.Token$Character
                r7.<init>()
                org.jsoup.parser.Token$Character r3 = r7.data(r3)
                r1.process(r3)
                org.jsoup.nodes.Attributes r3 = new org.jsoup.nodes.Attributes
                r3.<init>()
                org.jsoup.nodes.Attributes r4 = r4.attributes
                java.util.Iterator r4 = r4.iterator()
            L_0x03a3:
                boolean r7 = r4.hasNext()
                if (r7 == 0) goto L_0x03bf
                java.lang.Object r7 = r4.next()
                org.jsoup.nodes.Attribute r7 = (org.jsoup.nodes.Attribute) r7
                java.lang.String r8 = r7.getKey()
                java.lang.String[] r9 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartInputAttribs
                boolean r8 = org.jsoup.internal.StringUtil.inSorted(r8, r9)
                if (r8 != 0) goto L_0x03a3
                r3.put(r7)
                goto L_0x03a3
            L_0x03bf:
                java.lang.String r4 = "name"
                r3.put((java.lang.String) r4, (java.lang.String) r5)
                r1.processStartTag(r6, r3)
                r1.processEndTag(r2)
                r1.processStartTag(r13)
                r1.processEndTag(r10)
                goto L_0x05eb
            L_0x03d2:
                r4 = r16
                org.jsoup.nodes.Element r2 = r1.getFromStack(r7)
                if (r2 != 0) goto L_0x03e5
                java.lang.String r2 = "img"
                org.jsoup.parser.Token$Tag r2 = r4.name(r2)
                boolean r1 = r1.process(r2)
                return r1
            L_0x03e5:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x03ea:
                r4 = r16
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x03f5
                r1.processEndTag(r2)
            L_0x03f5:
                r1.insertEmpty(r4)
                r2 = 0
                r1.framesetOk(r2)
                goto L_0x05eb
            L_0x03fe:
                r4 = r16
                r2 = 0
                r19.reconstructFormattingElements()
                org.jsoup.nodes.Element r3 = r1.insertEmpty(r4)
                java.lang.String r4 = "type"
                java.lang.String r3 = r3.attr(r4)
                java.lang.String r4 = "hidden"
                boolean r3 = r3.equalsIgnoreCase(r4)
                if (r3 != 0) goto L_0x05eb
                r1.framesetOk(r2)
                goto L_0x05eb
            L_0x041b:
                r4 = r16
                org.jsoup.nodes.Document r3 = r19.getDocument()
                org.jsoup.nodes.Document$QuirksMode r3 = r3.quirksMode()
                org.jsoup.nodes.Document$QuirksMode r5 = org.jsoup.nodes.Document.QuirksMode.quirks
                if (r3 == r5) goto L_0x0432
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x0432
                r1.processEndTag(r2)
            L_0x0432:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                r2 = 0
                r1.framesetOk(r2)
                org.jsoup.parser.HtmlTreeBuilderState r2 = InTable
                r1.transition(r2)
                goto L_0x05eb
            L_0x0440:
                r4 = r16
                r19.reconstructFormattingElements()
                boolean r2 = r1.inScope((java.lang.String) r9)
                if (r2 == 0) goto L_0x0454
                r1.error(r0)
                r1.processEndTag(r9)
                r19.reconstructFormattingElements()
            L_0x0454:
                org.jsoup.nodes.Element r2 = r1.insert((org.jsoup.parser.Token.StartTag) r4)
                r1.pushActiveFormattingElements(r2)
                goto L_0x05eb
            L_0x045d:
                r4 = r16
                boolean r2 = r1.inButtonScope(r15)
                if (r2 == 0) goto L_0x0470
                r1.error(r0)
                r1.processEndTag(r15)
                r1.process(r4)
                goto L_0x05eb
            L_0x0470:
                r19.reconstructFormattingElements()
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                r2 = 0
                r1.framesetOk(r2)
                goto L_0x05eb
            L_0x047c:
                r4 = r16
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x0487
                r1.processEndTag(r2)
            L_0x0487:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                org.jsoup.parser.Tokeniser r1 = r1.tokeniser
                org.jsoup.parser.TokeniserState r2 = org.jsoup.parser.TokeniserState.PLAINTEXT
                r1.transition(r2)
                goto L_0x05eb
            L_0x0493:
                r4 = r16
                org.jsoup.nodes.FormElement r3 = r19.getFormElement()
                if (r3 == 0) goto L_0x04a0
                r1.error(r0)
                r1 = 0
                return r1
            L_0x04a0:
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x04a9
                r1.processEndTag(r2)
            L_0x04a9:
                r2 = 1
                r1.insertForm(r4, r2)
                goto L_0x05eb
            L_0x04af:
                r4 = r16
                r2 = 1
                r1.error(r0)
                java.util.ArrayList r3 = r19.getStack()
                int r5 = r3.size()
                if (r5 == r2) goto L_0x0508
                int r5 = r3.size()
                r6 = 2
                if (r5 <= r6) goto L_0x04d7
                java.lang.Object r5 = r3.get(r2)
                org.jsoup.nodes.Element r5 = (org.jsoup.nodes.Element) r5
                java.lang.String r5 = r5.normalName()
                boolean r5 = r5.equals(r11)
                if (r5 != 0) goto L_0x04d7
                goto L_0x0508
            L_0x04d7:
                boolean r5 = r19.framesetOk()
                if (r5 != 0) goto L_0x04df
                r5 = 0
                return r5
            L_0x04df:
                java.lang.Object r5 = r3.get(r2)
                org.jsoup.nodes.Element r5 = (org.jsoup.nodes.Element) r5
                org.jsoup.nodes.Element r6 = r5.parent()
                if (r6 == 0) goto L_0x04ee
                r5.remove()
            L_0x04ee:
                int r5 = r3.size()
                if (r5 <= r2) goto L_0x04fe
                int r5 = r3.size()
                int r5 = r5 - r2
                r3.remove(r5)
                r2 = 1
                goto L_0x04ee
            L_0x04fe:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                org.jsoup.parser.HtmlTreeBuilderState r2 = InFrameset
                r1.transition(r2)
                goto L_0x05eb
            L_0x0508:
                r1 = 0
                return r1
            L_0x050a:
                r4 = r16
                r1.error(r0)
                java.util.ArrayList r2 = r19.getStack()
                int r3 = r2.size()
                r5 = 1
                if (r3 == r5) goto L_0x0562
                int r3 = r2.size()
                r6 = 2
                if (r3 <= r6) goto L_0x0532
                java.lang.Object r3 = r2.get(r5)
                org.jsoup.nodes.Element r3 = (org.jsoup.nodes.Element) r3
                java.lang.String r3 = r3.normalName()
                boolean r3 = r3.equals(r11)
                if (r3 != 0) goto L_0x0532
                goto L_0x0562
            L_0x0532:
                r3 = 0
                r1.framesetOk(r3)
                java.lang.Object r1 = r2.get(r5)
                org.jsoup.nodes.Element r1 = (org.jsoup.nodes.Element) r1
                org.jsoup.nodes.Attributes r2 = r4.getAttributes()
                java.util.Iterator r2 = r2.iterator()
            L_0x0544:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x05eb
                java.lang.Object r3 = r2.next()
                org.jsoup.nodes.Attribute r3 = (org.jsoup.nodes.Attribute) r3
                java.lang.String r4 = r3.getKey()
                boolean r4 = r1.hasAttr(r4)
                if (r4 != 0) goto L_0x0544
                org.jsoup.nodes.Attributes r4 = r1.attributes()
                r4.put(r3)
                goto L_0x0544
            L_0x0562:
                r2 = 0
                return r2
            L_0x0564:
                r4 = r16
                r2 = 0
                r1.error(r0)
                java.util.ArrayList r1 = r19.getStack()
                java.lang.Object r1 = r1.get(r2)
                org.jsoup.nodes.Element r1 = (org.jsoup.nodes.Element) r1
                org.jsoup.nodes.Attributes r2 = r4.getAttributes()
                java.util.Iterator r2 = r2.iterator()
            L_0x057c:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x05eb
                java.lang.Object r3 = r2.next()
                org.jsoup.nodes.Attribute r3 = (org.jsoup.nodes.Attribute) r3
                java.lang.String r4 = r3.getKey()
                boolean r4 = r1.hasAttr(r4)
                if (r4 != 0) goto L_0x057c
                org.jsoup.nodes.Attributes r4 = r1.attributes()
                r4.put(r3)
                goto L_0x057c
            L_0x059a:
                r4 = r16
                r3 = 0
                r1.framesetOk(r3)
                java.util.ArrayList r3 = r19.getStack()
                int r5 = r3.size()
                r6 = 1
                int r5 = r5 - r6
            L_0x05aa:
                if (r5 <= 0) goto L_0x05d6
                java.lang.Object r6 = r3.get(r5)
                org.jsoup.nodes.Element r6 = (org.jsoup.nodes.Element) r6
                java.lang.String r7 = r6.normalName()
                boolean r7 = r7.equals(r12)
                if (r7 == 0) goto L_0x05c0
                r1.processEndTag(r12)
                goto L_0x05d6
            L_0x05c0:
                boolean r7 = r1.isSpecial(r6)
                if (r7 == 0) goto L_0x05d3
                java.lang.String r6 = r6.normalName()
                java.lang.String[] r7 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartLiBreakers
                boolean r6 = org.jsoup.internal.StringUtil.inSorted(r6, r7)
                if (r6 != 0) goto L_0x05d3
                goto L_0x05d6
            L_0x05d3:
                int r5 = r5 + -1
                goto L_0x05aa
            L_0x05d6:
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x05df
                r1.processEndTag(r2)
            L_0x05df:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x05e3:
                r4 = r16
                r19.reconstructFormattingElements()
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
            L_0x05eb:
                r1 = 1
                goto L_0x0684
            L_0x05ee:
                r4 = r16
                org.jsoup.nodes.Element r2 = r1.getActiveFormattingElement(r8)
                if (r2 == 0) goto L_0x0608
                r1.error(r0)
                r1.processEndTag(r8)
                org.jsoup.nodes.Element r2 = r1.getFromStack(r8)
                if (r2 == 0) goto L_0x0608
                r1.removeFromActiveFormattingElements(r2)
                r1.removeFromStack(r2)
            L_0x0608:
                r19.reconstructFormattingElements()
                org.jsoup.nodes.Element r2 = r1.insert((org.jsoup.parser.Token.StartTag) r4)
                r1.pushActiveFormattingElements(r2)
                goto L_0x05eb
            L_0x0613:
                java.lang.String[] r5 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartPClosers
                boolean r5 = org.jsoup.internal.StringUtil.inSorted(r3, r5)
                if (r5 == 0) goto L_0x0628
                boolean r3 = r1.inButtonScope(r2)
                if (r3 == 0) goto L_0x0624
                r1.processEndTag(r2)
            L_0x0624:
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x0628:
                java.lang.String[] r2 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartToHead
                boolean r2 = org.jsoup.internal.StringUtil.inSorted(r3, r2)
                if (r2 == 0) goto L_0x0639
                org.jsoup.parser.HtmlTreeBuilderState r2 = InHead
                r3 = r18
                boolean r1 = r1.process(r3, r2)
                return r1
            L_0x0639:
                java.lang.String[] r2 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Formatters
                boolean r2 = org.jsoup.internal.StringUtil.inSorted(r3, r2)
                if (r2 == 0) goto L_0x064c
                r19.reconstructFormattingElements()
                org.jsoup.nodes.Element r2 = r1.insert((org.jsoup.parser.Token.StartTag) r4)
                r1.pushActiveFormattingElements(r2)
                goto L_0x05eb
            L_0x064c:
                java.lang.String[] r2 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartApplets
                boolean r2 = org.jsoup.internal.StringUtil.inSorted(r3, r2)
                if (r2 == 0) goto L_0x0662
                r19.reconstructFormattingElements()
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                r19.insertMarkerToFormattingElements()
                r2 = 0
                r1.framesetOk(r2)
                goto L_0x05eb
            L_0x0662:
                r2 = 0
                java.lang.String[] r5 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartMedia
                boolean r5 = org.jsoup.internal.StringUtil.inSorted(r3, r5)
                if (r5 == 0) goto L_0x0670
                r1.insertEmpty(r4)
                goto L_0x05eb
            L_0x0670:
                java.lang.String[] r5 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartDrop
                boolean r3 = org.jsoup.internal.StringUtil.inSorted(r3, r5)
                if (r3 == 0) goto L_0x067c
                r1.error(r0)
                return r2
            L_0x067c:
                r19.reconstructFormattingElements()
                r1.insert((org.jsoup.parser.Token.StartTag) r4)
                goto L_0x05eb
            L_0x0684:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass7.inBodyStartTag(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean inBodyEndTag(org.jsoup.parser.Token r8, org.jsoup.parser.HtmlTreeBuilder r9) {
            /*
                r7 = this;
                org.jsoup.parser.Token$EndTag r0 = r8.asEndTag()
                java.lang.String r1 = r0.normalName()
                int r2 = r1.hashCode()
                r3 = 1
                java.lang.String r4 = "br"
                java.lang.String r5 = "body"
                r6 = 0
                switch(r2) {
                    case 112: goto L_0x00ba;
                    case 3152: goto L_0x00b1;
                    case 3200: goto L_0x00a7;
                    case 3216: goto L_0x009c;
                    case 3453: goto L_0x0092;
                    case 3029410: goto L_0x008a;
                    case 3148996: goto L_0x0080;
                    case 3213227: goto L_0x0076;
                    case 3536714: goto L_0x006c;
                    case 1869063452: goto L_0x0062;
                    default: goto L_0x0015;
                }
            L_0x0015:
                switch(r2) {
                    case 3273: goto L_0x0056;
                    case 3274: goto L_0x004a;
                    case 3275: goto L_0x003e;
                    case 3276: goto L_0x0032;
                    case 3277: goto L_0x0026;
                    case 3278: goto L_0x001a;
                    default: goto L_0x0018;
                }
            L_0x0018:
                goto L_0x00c4
            L_0x001a:
                java.lang.String r2 = "h6"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 14
                goto L_0x00c5
            L_0x0026:
                java.lang.String r2 = "h5"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 13
                goto L_0x00c5
            L_0x0032:
                java.lang.String r2 = "h4"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 12
                goto L_0x00c5
            L_0x003e:
                java.lang.String r2 = "h3"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 11
                goto L_0x00c5
            L_0x004a:
                java.lang.String r2 = "h2"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 10
                goto L_0x00c5
            L_0x0056:
                java.lang.String r2 = "h1"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 9
                goto L_0x00c5
            L_0x0062:
                java.lang.String r2 = "sarcasm"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 0
                goto L_0x00c5
            L_0x006c:
                java.lang.String r2 = "span"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 1
                goto L_0x00c5
            L_0x0076:
                java.lang.String r2 = "html"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 4
                goto L_0x00c5
            L_0x0080:
                java.lang.String r2 = "form"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 5
                goto L_0x00c5
            L_0x008a:
                boolean r2 = r1.equals(r5)
                if (r2 == 0) goto L_0x00c4
                r2 = 3
                goto L_0x00c5
            L_0x0092:
                java.lang.String r2 = "li"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 2
                goto L_0x00c5
            L_0x009c:
                java.lang.String r2 = "dt"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 8
                goto L_0x00c5
            L_0x00a7:
                java.lang.String r2 = "dd"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 7
                goto L_0x00c5
            L_0x00b1:
                boolean r2 = r1.equals(r4)
                if (r2 == 0) goto L_0x00c4
                r2 = 15
                goto L_0x00c5
            L_0x00ba:
                java.lang.String r2 = "p"
                boolean r2 = r1.equals(r2)
                if (r2 == 0) goto L_0x00c4
                r2 = 6
                goto L_0x00c5
            L_0x00c4:
                r2 = -1
            L_0x00c5:
                switch(r2) {
                    case 0: goto L_0x01bc;
                    case 1: goto L_0x01bc;
                    case 2: goto L_0x019a;
                    case 3: goto L_0x0189;
                    case 4: goto L_0x017e;
                    case 5: goto L_0x0150;
                    case 6: goto L_0x0126;
                    case 7: goto L_0x0103;
                    case 8: goto L_0x0103;
                    case 9: goto L_0x00dc;
                    case 10: goto L_0x00dc;
                    case 11: goto L_0x00dc;
                    case 12: goto L_0x00dc;
                    case 13: goto L_0x00dc;
                    case 14: goto L_0x00dc;
                    case 15: goto L_0x00d5;
                    default: goto L_0x00c8;
                }
            L_0x00c8:
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyEndAdoptionFormatters
                boolean r0 = org.jsoup.internal.StringUtil.inSorted(r1, r0)
                if (r0 == 0) goto L_0x01c1
                boolean r8 = r7.inBodyEndTagAdoption(r8, r9)
                return r8
            L_0x00d5:
                r9.error(r7)
                r9.processStartTag(r4)
                return r6
            L_0x00dc:
                java.lang.String[] r8 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Headings
                boolean r8 = r9.inScope((java.lang.String[]) r8)
                if (r8 != 0) goto L_0x00e8
                r9.error(r7)
                return r6
            L_0x00e8:
                r9.generateImpliedEndTags(r1)
                org.jsoup.nodes.Element r8 = r9.currentElement()
                java.lang.String r8 = r8.normalName()
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L_0x00fc
                r9.error(r7)
            L_0x00fc:
                java.lang.String[] r8 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Headings
                r9.popStackToClose((java.lang.String[]) r8)
                goto L_0x021f
            L_0x0103:
                boolean r8 = r9.inScope((java.lang.String) r1)
                if (r8 != 0) goto L_0x010d
                r9.error(r7)
                return r6
            L_0x010d:
                r9.generateImpliedEndTags(r1)
                org.jsoup.nodes.Element r8 = r9.currentElement()
                java.lang.String r8 = r8.normalName()
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L_0x0121
                r9.error(r7)
            L_0x0121:
                r9.popStackToClose((java.lang.String) r1)
                goto L_0x021f
            L_0x0126:
                boolean r8 = r9.inButtonScope(r1)
                if (r8 != 0) goto L_0x0137
                r9.error(r7)
                r9.processStartTag(r1)
                boolean r8 = r9.process(r0)
                return r8
            L_0x0137:
                r9.generateImpliedEndTags(r1)
                org.jsoup.nodes.Element r8 = r9.currentElement()
                java.lang.String r8 = r8.normalName()
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L_0x014b
                r9.error(r7)
            L_0x014b:
                r9.popStackToClose((java.lang.String) r1)
                goto L_0x021f
            L_0x0150:
                org.jsoup.nodes.FormElement r8 = r9.getFormElement()
                r0 = 0
                r9.setFormElement(r0)
                if (r8 == 0) goto L_0x017a
                boolean r0 = r9.inScope((java.lang.String) r1)
                if (r0 != 0) goto L_0x0161
                goto L_0x017a
            L_0x0161:
                r9.generateImpliedEndTags()
                org.jsoup.nodes.Element r0 = r9.currentElement()
                java.lang.String r0 = r0.normalName()
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L_0x0175
                r9.error(r7)
            L_0x0175:
                r9.removeFromStack(r8)
                goto L_0x021f
            L_0x017a:
                r9.error(r7)
                return r6
            L_0x017e:
                boolean r8 = r9.processEndTag(r5)
                if (r8 == 0) goto L_0x021f
                boolean r8 = r9.process(r0)
                return r8
            L_0x0189:
                boolean r8 = r9.inScope((java.lang.String) r5)
                if (r8 != 0) goto L_0x0193
                r9.error(r7)
                return r6
            L_0x0193:
                org.jsoup.parser.HtmlTreeBuilderState r8 = AfterBody
                r9.transition(r8)
                goto L_0x021f
            L_0x019a:
                boolean r8 = r9.inListItemScope(r1)
                if (r8 != 0) goto L_0x01a4
                r9.error(r7)
                return r6
            L_0x01a4:
                r9.generateImpliedEndTags(r1)
                org.jsoup.nodes.Element r8 = r9.currentElement()
                java.lang.String r8 = r8.normalName()
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L_0x01b8
                r9.error(r7)
            L_0x01b8:
                r9.popStackToClose((java.lang.String) r1)
                goto L_0x021f
            L_0x01bc:
                boolean r8 = r7.anyOtherEndTag(r8, r9)
                return r8
            L_0x01c1:
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyEndClosers
                boolean r0 = org.jsoup.internal.StringUtil.inSorted(r1, r0)
                if (r0 == 0) goto L_0x01eb
                boolean r8 = r9.inScope((java.lang.String) r1)
                if (r8 != 0) goto L_0x01d3
                r9.error(r7)
                return r6
            L_0x01d3:
                r9.generateImpliedEndTags()
                org.jsoup.nodes.Element r8 = r9.currentElement()
                java.lang.String r8 = r8.normalName()
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L_0x01e7
                r9.error(r7)
            L_0x01e7:
                r9.popStackToClose((java.lang.String) r1)
                goto L_0x021f
            L_0x01eb:
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartApplets
                boolean r0 = org.jsoup.internal.StringUtil.inSorted(r1, r0)
                if (r0 == 0) goto L_0x0220
                java.lang.String r8 = "name"
                boolean r8 = r9.inScope((java.lang.String) r8)
                if (r8 != 0) goto L_0x021f
                boolean r8 = r9.inScope((java.lang.String) r1)
                if (r8 != 0) goto L_0x0205
                r9.error(r7)
                return r6
            L_0x0205:
                r9.generateImpliedEndTags()
                org.jsoup.nodes.Element r8 = r9.currentElement()
                java.lang.String r8 = r8.normalName()
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L_0x0219
                r9.error(r7)
            L_0x0219:
                r9.popStackToClose((java.lang.String) r1)
                r9.clearFormattingElementsToLastMarker()
            L_0x021f:
                return r3
            L_0x0220:
                boolean r8 = r7.anyOtherEndTag(r8, r9)
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass7.inBodyEndTag(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        /* access modifiers changed from: package-private */
        public boolean anyOtherEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String str = token.asEndTag().normalName;
            ArrayList<Element> stack = htmlTreeBuilder.getStack();
            int size = stack.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                Element element = stack.get(size);
                if (element.normalName().equals(str)) {
                    htmlTreeBuilder.generateImpliedEndTags(str);
                    if (!str.equals(htmlTreeBuilder.currentElement().normalName())) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(str);
                } else if (htmlTreeBuilder.isSpecial(element)) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    size--;
                }
            }
            return true;
        }

        private boolean inBodyEndTagAdoption(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String normalName = token.asEndTag().normalName();
            ArrayList<Element> stack = htmlTreeBuilder.getStack();
            int i = 0;
            while (i < 8) {
                Element activeFormattingElement = htmlTreeBuilder.getActiveFormattingElement(normalName);
                if (activeFormattingElement == null) {
                    return anyOtherEndTag(token, htmlTreeBuilder);
                }
                if (!htmlTreeBuilder.onStack(activeFormattingElement)) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                    return true;
                } else if (!htmlTreeBuilder.inScope(activeFormattingElement.normalName())) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    if (htmlTreeBuilder.currentElement() != activeFormattingElement) {
                        htmlTreeBuilder.error(this);
                    }
                    int size = stack.size();
                    Element element = null;
                    Element element2 = null;
                    int i2 = 0;
                    boolean z = false;
                    while (true) {
                        if (i2 >= size || i2 >= 64) {
                            break;
                        }
                        Element element3 = stack.get(i2);
                        if (element3 != activeFormattingElement) {
                            if (z && htmlTreeBuilder.isSpecial(element3)) {
                                element = element3;
                                break;
                            }
                        } else {
                            element2 = stack.get(i2 - 1);
                            z = true;
                        }
                        i2++;
                    }
                    if (element == null) {
                        htmlTreeBuilder.popStackToClose(activeFormattingElement.normalName());
                        htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                        return true;
                    }
                    Element element4 = element;
                    Element element5 = element4;
                    for (int i3 = 0; i3 < 3; i3++) {
                        if (htmlTreeBuilder.onStack(element4)) {
                            element4 = htmlTreeBuilder.aboveOnStack(element4);
                        }
                        if (!htmlTreeBuilder.isInActiveFormattingElements(element4)) {
                            htmlTreeBuilder.removeFromStack(element4);
                        } else if (element4 == activeFormattingElement) {
                            break;
                        } else {
                            Element element6 = new Element(Tag.valueOf(element4.nodeName(), ParseSettings.preserveCase), htmlTreeBuilder.getBaseUri());
                            htmlTreeBuilder.replaceActiveFormattingElement(element4, element6);
                            htmlTreeBuilder.replaceOnStack(element4, element6);
                            if (element5.parent() != null) {
                                element5.remove();
                            }
                            element6.appendChild(element5);
                            element4 = element6;
                            element5 = element4;
                        }
                    }
                    if (StringUtil.inSorted(element2.normalName(), Constants.InBodyEndTableFosters)) {
                        if (element5.parent() != null) {
                            element5.remove();
                        }
                        htmlTreeBuilder.insertInFosterParent(element5);
                    } else {
                        if (element5.parent() != null) {
                            element5.remove();
                        }
                        element2.appendChild(element5);
                    }
                    Element element7 = new Element(activeFormattingElement.tag(), htmlTreeBuilder.getBaseUri());
                    element7.attributes().addAll(activeFormattingElement.attributes());
                    for (Node appendChild : (Node[]) element.childNodes().toArray(new Node[0])) {
                        element7.appendChild(appendChild);
                    }
                    element.appendChild(element7);
                    htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                    htmlTreeBuilder.removeFromStack(activeFormattingElement);
                    htmlTreeBuilder.insertOnStackAfter(element, element7);
                    i++;
                }
            }
            return true;
        }
    },
    Text {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isEOF()) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                return htmlTreeBuilder.process(token);
            } else if (!token.isEndTag()) {
                return true;
            } else {
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                return true;
            }
        }
    },
    InTable {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.newPendingTableCharacters();
                htmlTreeBuilder.markInsertionMode();
                htmlTreeBuilder.transition(InTableText);
                return htmlTreeBuilder.process(token);
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                if (normalName.equals(ShareConstants.FEED_CAPTION_PARAM)) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InCaption);
                } else if (normalName.equals("colgroup")) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InColumnGroup);
                } else if (normalName.equals("col")) {
                    htmlTreeBuilder.processStartTag("colgroup");
                    return htmlTreeBuilder.process(token);
                } else if (StringUtil.inSorted(normalName, Constants.InTableToBody)) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InTableBody);
                } else if (StringUtil.inSorted(normalName, Constants.InTableAddBody)) {
                    htmlTreeBuilder.processStartTag("tbody");
                    return htmlTreeBuilder.process(token);
                } else if (normalName.equals(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE)) {
                    htmlTreeBuilder.error(this);
                    if (htmlTreeBuilder.processEndTag(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE)) {
                        return htmlTreeBuilder.process(token);
                    }
                } else if (StringUtil.inSorted(normalName, Constants.InTableToHead)) {
                    return htmlTreeBuilder.process(token, InHead);
                } else {
                    if (normalName.equals("input")) {
                        if (!asStartTag.attributes.get("type").equalsIgnoreCase("hidden")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.insertEmpty(asStartTag);
                    } else if (!normalName.equals("form")) {
                        return anythingElse(token, htmlTreeBuilder);
                    } else {
                        htmlTreeBuilder.error(this);
                        if (htmlTreeBuilder.getFormElement() != null) {
                            return false;
                        }
                        htmlTreeBuilder.insertForm(asStartTag, false);
                    }
                }
                return true;
            } else if (token.isEndTag()) {
                String normalName2 = token.asEndTag().normalName();
                if (normalName2.equals(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE)) {
                    if (!htmlTreeBuilder.inTableScope(normalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.popStackToClose(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE);
                    htmlTreeBuilder.resetInsertionMode();
                    return true;
                } else if (!StringUtil.inSorted(normalName2, Constants.InTableEndErr)) {
                    return anythingElse(token, htmlTreeBuilder);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            } else if (!token.isEOF()) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                if (htmlTreeBuilder.currentElement().normalName().equals("html")) {
                    htmlTreeBuilder.error(this);
                }
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            if (!StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.InTableFoster)) {
                return htmlTreeBuilder.process(token, InBody);
            }
            htmlTreeBuilder.setFosterInserts(true);
            boolean process = htmlTreeBuilder.process(token, InBody);
            htmlTreeBuilder.setFosterInserts(false);
            return process;
        }
    },
    InTableText {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.type == Token.TokenType.Character) {
                Token.Character asCharacter = token.asCharacter();
                if (asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.getPendingTableCharacters().add(asCharacter.getData());
                return true;
            }
            if (htmlTreeBuilder.getPendingTableCharacters().size() > 0) {
                for (String next : htmlTreeBuilder.getPendingTableCharacters()) {
                    if (!HtmlTreeBuilderState.isWhitespace(next)) {
                        htmlTreeBuilder.error(this);
                        if (StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.InTableFoster)) {
                            htmlTreeBuilder.setFosterInserts(true);
                            htmlTreeBuilder.process(new Token.Character().data(next), InBody);
                            htmlTreeBuilder.setFosterInserts(false);
                        } else {
                            htmlTreeBuilder.process(new Token.Character().data(next), InBody);
                        }
                    } else {
                        htmlTreeBuilder.insert(new Token.Character().data(next));
                    }
                }
                htmlTreeBuilder.newPendingTableCharacters();
            }
            htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
            return htmlTreeBuilder.process(token);
        }
    },
    InCaption {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!token.isEndTag() || !token.asEndTag().normalName().equals(ShareConstants.FEED_CAPTION_PARAM)) {
                if ((token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InCellCol)) || (token.isEndTag() && token.asEndTag().normalName().equals(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE))) {
                    htmlTreeBuilder.error(this);
                    if (htmlTreeBuilder.processEndTag(ShareConstants.FEED_CAPTION_PARAM)) {
                        return htmlTreeBuilder.process(token);
                    }
                    return true;
                } else if (!token.isEndTag() || !StringUtil.inSorted(token.asEndTag().normalName(), Constants.InCaptionIgnore)) {
                    return htmlTreeBuilder.process(token, InBody);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            } else if (!htmlTreeBuilder.inTableScope(token.asEndTag().normalName())) {
                htmlTreeBuilder.error(this);
                return false;
            } else {
                htmlTreeBuilder.generateImpliedEndTags();
                if (!htmlTreeBuilder.currentElement().normalName().equals(ShareConstants.FEED_CAPTION_PARAM)) {
                    htmlTreeBuilder.error(this);
                }
                htmlTreeBuilder.popStackToClose(ShareConstants.FEED_CAPTION_PARAM);
                htmlTreeBuilder.clearFormattingElementsToLastMarker();
                htmlTreeBuilder.transition(InTable);
                return true;
            }
        }
    },
    InColumnGroup {
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x008d, code lost:
            if (r2.equals("html") == false) goto L_0x009a;
         */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x009d  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x00a8  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r9, org.jsoup.parser.HtmlTreeBuilder r10) {
            /*
                r8 = this;
                boolean r0 = org.jsoup.parser.HtmlTreeBuilderState.isWhitespace((org.jsoup.parser.Token) r9)
                r1 = 1
                if (r0 == 0) goto L_0x000f
                org.jsoup.parser.Token$Character r9 = r9.asCharacter()
                r10.insert((org.jsoup.parser.Token.Character) r9)
                return r1
            L_0x000f:
                int[] r0 = org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType
                org.jsoup.parser.Token$TokenType r2 = r9.type
                int r2 = r2.ordinal()
                r0 = r0[r2]
                if (r0 == r1) goto L_0x00b3
                r2 = 2
                if (r0 == r2) goto L_0x00af
                r2 = 3
                r3 = 0
                java.lang.String r4 = "html"
                if (r0 == r2) goto L_0x0071
                r2 = 4
                if (r0 == r2) goto L_0x0043
                r2 = 6
                if (r0 == r2) goto L_0x002f
                boolean r9 = r8.anythingElse(r9, r10)
                return r9
            L_0x002f:
                org.jsoup.nodes.Element r0 = r10.currentElement()
                java.lang.String r0 = r0.normalName()
                boolean r0 = r0.equals(r4)
                if (r0 == 0) goto L_0x003e
                return r1
            L_0x003e:
                boolean r9 = r8.anythingElse(r9, r10)
                return r9
            L_0x0043:
                org.jsoup.parser.Token$EndTag r0 = r9.asEndTag()
                java.lang.String r0 = r0.normalName
                java.lang.String r2 = "colgroup"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x006c
                org.jsoup.nodes.Element r9 = r10.currentElement()
                java.lang.String r9 = r9.normalName()
                boolean r9 = r9.equals(r4)
                if (r9 == 0) goto L_0x0063
                r10.error(r8)
                return r3
            L_0x0063:
                r10.pop()
                org.jsoup.parser.HtmlTreeBuilderState r9 = InTable
                r10.transition(r9)
                goto L_0x00ba
            L_0x006c:
                boolean r9 = r8.anythingElse(r9, r10)
                return r9
            L_0x0071:
                org.jsoup.parser.Token$StartTag r0 = r9.asStartTag()
                java.lang.String r2 = r0.normalName()
                r5 = -1
                int r6 = r2.hashCode()
                r7 = 98688(0x18180, float:1.38291E-40)
                if (r6 == r7) goto L_0x0090
                r7 = 3213227(0x3107ab, float:4.50269E-39)
                if (r6 == r7) goto L_0x0089
                goto L_0x009a
            L_0x0089:
                boolean r2 = r2.equals(r4)
                if (r2 == 0) goto L_0x009a
                goto L_0x009b
            L_0x0090:
                java.lang.String r3 = "col"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x009a
                r3 = 1
                goto L_0x009b
            L_0x009a:
                r3 = -1
            L_0x009b:
                if (r3 == 0) goto L_0x00a8
                if (r3 == r1) goto L_0x00a4
                boolean r9 = r8.anythingElse(r9, r10)
                return r9
            L_0x00a4:
                r10.insertEmpty(r0)
                goto L_0x00ba
            L_0x00a8:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InBody
                boolean r9 = r10.process(r9, r0)
                return r9
            L_0x00af:
                r10.error(r8)
                goto L_0x00ba
            L_0x00b3:
                org.jsoup.parser.Token$Comment r9 = r9.asComment()
                r10.insert((org.jsoup.parser.Token.Comment) r9)
            L_0x00ba:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass12.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            if (treeBuilder.processEndTag("colgroup")) {
                return treeBuilder.process(token);
            }
            return true;
        }
    },
    InTableBody {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            int i = AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i == 3) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                if (normalName.equals(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE)) {
                    htmlTreeBuilder.insert(asStartTag);
                    return true;
                } else if (normalName.equals("tr")) {
                    htmlTreeBuilder.clearStackToTableBodyContext();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InRow);
                    return true;
                } else if (StringUtil.inSorted(normalName, Constants.InCellNames)) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.processStartTag("tr");
                    return htmlTreeBuilder.process(asStartTag);
                } else if (StringUtil.inSorted(normalName, Constants.InTableBodyExit)) {
                    return exitTableBody(token, htmlTreeBuilder);
                } else {
                    return anythingElse(token, htmlTreeBuilder);
                }
            } else if (i != 4) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                String normalName2 = token.asEndTag().normalName();
                if (StringUtil.inSorted(normalName2, Constants.InTableEndIgnore)) {
                    if (!htmlTreeBuilder.inTableScope(normalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableBodyContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTable);
                    return true;
                } else if (normalName2.equals(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE)) {
                    return exitTableBody(token, htmlTreeBuilder);
                } else {
                    if (!StringUtil.inSorted(normalName2, Constants.InTableBodyEndIgnore)) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
        }

        private boolean exitTableBody(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.inTableScope("tbody") || htmlTreeBuilder.inTableScope("thead") || htmlTreeBuilder.inScope("tfoot")) {
                htmlTreeBuilder.clearStackToTableBodyContext();
                htmlTreeBuilder.processEndTag(htmlTreeBuilder.currentElement().normalName());
                return htmlTreeBuilder.process(token);
            }
            htmlTreeBuilder.error(this);
            return false;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }
    },
    InRow {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                if (normalName.equals(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE)) {
                    htmlTreeBuilder.insert(asStartTag);
                    return true;
                } else if (StringUtil.inSorted(normalName, Constants.InCellNames)) {
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InCell);
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    return true;
                } else if (StringUtil.inSorted(normalName, Constants.InRowMissing)) {
                    return handleMissingTr(token, htmlTreeBuilder);
                } else {
                    return anythingElse(token, htmlTreeBuilder);
                }
            } else if (!token.isEndTag()) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                String normalName2 = token.asEndTag().normalName();
                if (normalName2.equals("tr")) {
                    if (!htmlTreeBuilder.inTableScope(normalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return true;
                } else if (normalName2.equals(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE)) {
                    return handleMissingTr(token, htmlTreeBuilder);
                } else {
                    if (StringUtil.inSorted(normalName2, Constants.InTableToBody)) {
                        if (!htmlTreeBuilder.inTableScope(normalName2)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.processEndTag("tr");
                        return htmlTreeBuilder.process(token);
                    } else if (!StringUtil.inSorted(normalName2, Constants.InRowIgnore)) {
                        return anythingElse(token, htmlTreeBuilder);
                    } else {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                }
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }

        private boolean handleMissingTr(Token token, TreeBuilder treeBuilder) {
            if (treeBuilder.processEndTag("tr")) {
                return treeBuilder.process(token);
            }
            return false;
        }
    },
    InCell {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag()) {
                String normalName = token.asEndTag().normalName();
                if (StringUtil.inSorted(normalName, Constants.InCellNames)) {
                    if (!htmlTreeBuilder.inTableScope(normalName)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.transition(InRow);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags();
                    if (!htmlTreeBuilder.currentElement().normalName().equals(normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(normalName);
                    htmlTreeBuilder.clearFormattingElementsToLastMarker();
                    htmlTreeBuilder.transition(InRow);
                    return true;
                } else if (StringUtil.inSorted(normalName, Constants.InCellBody)) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else if (!StringUtil.inSorted(normalName, Constants.InCellTable)) {
                    return anythingElse(token, htmlTreeBuilder);
                } else {
                    if (!htmlTreeBuilder.inTableScope(normalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.process(token);
                }
            } else if (!token.isStartTag() || !StringUtil.inSorted(token.asStartTag().normalName(), Constants.InCellCol)) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                if (htmlTreeBuilder.inTableScope(HtmlTableRow.Row_TYPE_TABLE_DATA) || htmlTreeBuilder.inTableScope(HtmlTableRow.ROW_TYPE_TABLE_HEADER)) {
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.process(token);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InBody);
        }

        private void closeCell(HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.inTableScope(HtmlTableRow.Row_TYPE_TABLE_DATA)) {
                htmlTreeBuilder.processEndTag(HtmlTableRow.Row_TYPE_TABLE_DATA);
            } else {
                htmlTreeBuilder.processEndTag(HtmlTableRow.ROW_TYPE_TABLE_HEADER);
            }
        }
    },
    InSelect {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insert(token.asComment());
                    break;
                case 2:
                    htmlTreeBuilder.error(this);
                    return false;
                case 3:
                    Token.StartTag asStartTag = token.asStartTag();
                    String normalName = asStartTag.normalName();
                    if (normalName.equals("html")) {
                        return htmlTreeBuilder.process(asStartTag, InBody);
                    }
                    if (normalName.equals("option")) {
                        if (htmlTreeBuilder.currentElement().normalName().equals("option")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        htmlTreeBuilder.insert(asStartTag);
                        break;
                    } else if (normalName.equals("optgroup")) {
                        if (htmlTreeBuilder.currentElement().normalName().equals("option")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        if (htmlTreeBuilder.currentElement().normalName().equals("optgroup")) {
                            htmlTreeBuilder.processEndTag("optgroup");
                        }
                        htmlTreeBuilder.insert(asStartTag);
                        break;
                    } else if (normalName.equals("select")) {
                        htmlTreeBuilder.error(this);
                        return htmlTreeBuilder.processEndTag("select");
                    } else if (StringUtil.inSorted(normalName, Constants.InSelectEnd)) {
                        htmlTreeBuilder.error(this);
                        if (!htmlTreeBuilder.inSelectScope("select")) {
                            return false;
                        }
                        htmlTreeBuilder.processEndTag("select");
                        return htmlTreeBuilder.process(asStartTag);
                    } else if (normalName.equals("script")) {
                        return htmlTreeBuilder.process(token, InHead);
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                case 4:
                    String normalName2 = token.asEndTag().normalName();
                    char c = 65535;
                    int hashCode = normalName2.hashCode();
                    if (hashCode != -1010136971) {
                        if (hashCode != -906021636) {
                            if (hashCode == -80773204 && normalName2.equals("optgroup")) {
                                c = 0;
                            }
                        } else if (normalName2.equals("select")) {
                            c = 2;
                        }
                    } else if (normalName2.equals("option")) {
                        c = 1;
                    }
                    if (c != 0) {
                        if (c == 1) {
                            if (!htmlTreeBuilder.currentElement().normalName().equals("option")) {
                                htmlTreeBuilder.error(this);
                                break;
                            } else {
                                htmlTreeBuilder.pop();
                                break;
                            }
                        } else if (c == 2) {
                            if (htmlTreeBuilder.inSelectScope(normalName2)) {
                                htmlTreeBuilder.popStackToClose(normalName2);
                                htmlTreeBuilder.resetInsertionMode();
                                break;
                            } else {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                        } else {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    } else {
                        if (htmlTreeBuilder.currentElement().normalName().equals("option") && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()) != null && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()).normalName().equals("optgroup")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        if (!htmlTreeBuilder.currentElement().normalName().equals("optgroup")) {
                            htmlTreeBuilder.error(this);
                            break;
                        } else {
                            htmlTreeBuilder.pop();
                            break;
                        }
                    }
                case 5:
                    Token.Character asCharacter = token.asCharacter();
                    if (!asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.insert(asCharacter);
                        break;
                    } else {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                case 6:
                    if (!htmlTreeBuilder.currentElement().normalName().equals("html")) {
                        htmlTreeBuilder.error(this);
                        break;
                    }
                    break;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    InSelectInTable {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InSelecTableEnd)) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.processEndTag("select");
                return htmlTreeBuilder.process(token);
            } else if (!token.isEndTag() || !StringUtil.inSorted(token.asEndTag().normalName(), Constants.InSelecTableEnd)) {
                return htmlTreeBuilder.process(token, InSelect);
            } else {
                htmlTreeBuilder.error(this);
                if (!htmlTreeBuilder.inTableScope(token.asEndTag().normalName())) {
                    return false;
                }
                htmlTreeBuilder.processEndTag("select");
                return htmlTreeBuilder.process(token);
            }
        }
    },
    AfterBody {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (!token.isEndTag() || !token.asEndTag().normalName().equals("html")) {
                    if (token.isEOF()) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.transition(InBody);
                    return htmlTreeBuilder.process(token);
                } else if (htmlTreeBuilder.isFragmentParsing()) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    htmlTreeBuilder.transition(AfterAfterBody);
                    return true;
                }
            }
        }
    },
    InFrameset {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                char c = 65535;
                switch (normalName.hashCode()) {
                    case -1644953643:
                        if (normalName.equals("frameset")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 3213227:
                        if (normalName.equals("html")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 97692013:
                        if (normalName.equals("frame")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1192721831:
                        if (normalName.equals("noframes")) {
                            c = 3;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    return htmlTreeBuilder.process(asStartTag, InBody);
                }
                if (c == 1) {
                    htmlTreeBuilder.insert(asStartTag);
                } else if (c == 2) {
                    htmlTreeBuilder.insertEmpty(asStartTag);
                } else if (c == 3) {
                    return htmlTreeBuilder.process(asStartTag, InHead);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            } else if (!token.isEndTag() || !token.asEndTag().normalName().equals("frameset")) {
                if (!token.isEOF()) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else if (!htmlTreeBuilder.currentElement().normalName().equals("html")) {
                    htmlTreeBuilder.error(this);
                }
            } else if (htmlTreeBuilder.currentElement().normalName().equals("html")) {
                htmlTreeBuilder.error(this);
                return false;
            } else {
                htmlTreeBuilder.pop();
                if (!htmlTreeBuilder.isFragmentParsing() && !htmlTreeBuilder.currentElement().normalName().equals("frameset")) {
                    htmlTreeBuilder.transition(AfterFrameset);
                }
            }
            return true;
        }
    },
    AfterFrameset {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (token.isEndTag() && token.asEndTag().normalName().equals("html")) {
                    htmlTreeBuilder.transition(AfterAfterFrameset);
                    return true;
                } else if (token.isStartTag() && token.asStartTag().normalName().equals("noframes")) {
                    return htmlTreeBuilder.process(token, InHead);
                } else {
                    if (token.isEOF()) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
        }
    },
    AfterAfterBody {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype() || (token.isStartTag() && token.asStartTag().normalName().equals("html"))) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (HtmlTreeBuilderState.isWhitespace(token)) {
                    Element popStackToClose = htmlTreeBuilder.popStackToClose("html");
                    htmlTreeBuilder.insert(token.asCharacter());
                    htmlTreeBuilder.stack.add(popStackToClose);
                    htmlTreeBuilder.stack.add(popStackToClose.selectFirst("body"));
                    return true;
                } else if (token.isEOF()) {
                    return true;
                } else {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.transition(InBody);
                    return htmlTreeBuilder.process(token);
                }
            }
        }
    },
    AfterAfterFrameset {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype() || HtmlTreeBuilderState.isWhitespace(token) || (token.isStartTag() && token.asStartTag().normalName().equals("html"))) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (token.isEOF()) {
                    return true;
                }
                if (token.isStartTag() && token.asStartTag().normalName().equals("noframes")) {
                    return htmlTreeBuilder.process(token, InHead);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
        }
    },
    ForeignContent {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return true;
        }
    };
    
    /* access modifiers changed from: private */
    public static final String nullString = null;

    /* access modifiers changed from: package-private */
    public abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);

    static {
        nullString = String.valueOf(0);
    }

    /* renamed from: org.jsoup.parser.HtmlTreeBuilderState$24  reason: invalid class name */
    static /* synthetic */ class AnonymousClass24 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$parser$Token$TokenType = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.jsoup.parser.Token$TokenType[] r0 = org.jsoup.parser.Token.TokenType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jsoup$parser$Token$TokenType = r0
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.Comment     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.Doctype     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.StartTag     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.EndTag     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.Character     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.EOF     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass24.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static boolean isWhitespace(Token token) {
        if (token.isCharacter()) {
            return StringUtil.isBlank(token.asCharacter().getData());
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static boolean isWhitespace(String str) {
        return StringUtil.isBlank(str);
    }

    /* access modifiers changed from: private */
    public static void handleRcData(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rcdata);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
        htmlTreeBuilder.insert(startTag);
    }

    /* access modifiers changed from: private */
    public static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rawtext);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
        htmlTreeBuilder.insert(startTag);
    }

    static final class Constants {
        static final String[] AfterHeadBody = null;
        static final String[] BeforeHtmlToHead = null;
        static final String[] DdDt = null;
        static final String[] Formatters = null;
        static final String[] Headings = null;
        static final String[] InBodyEndAdoptionFormatters = null;
        static final String[] InBodyEndClosers = null;
        static final String[] InBodyEndTableFosters = null;
        static final String[] InBodyStartApplets = null;
        static final String[] InBodyStartDrop = null;
        static final String[] InBodyStartEmptyFormatters = null;
        static final String[] InBodyStartInputAttribs = null;
        static final String[] InBodyStartLiBreakers = null;
        static final String[] InBodyStartMedia = null;
        static final String[] InBodyStartPClosers = null;
        static final String[] InBodyStartToHead = null;
        static final String[] InCaptionIgnore = null;
        static final String[] InCellBody = null;
        static final String[] InCellCol = null;
        static final String[] InCellNames = null;
        static final String[] InCellTable = null;
        static final String[] InHeadEmpty = null;
        static final String[] InHeadEnd = null;
        static final String[] InHeadNoScriptHead = null;
        static final String[] InHeadNoscriptIgnore = null;
        static final String[] InHeadRaw = null;
        static final String[] InRowIgnore = null;
        static final String[] InRowMissing = null;
        static final String[] InSelecTableEnd = null;
        static final String[] InSelectEnd = null;
        static final String[] InTableAddBody = null;
        static final String[] InTableBodyEndIgnore = null;
        static final String[] InTableBodyExit = null;
        static final String[] InTableEndErr = null;
        static final String[] InTableEndIgnore = null;
        static final String[] InTableFoster = null;
        static final String[] InTableToBody = null;
        static final String[] InTableToHead = null;

        Constants() {
        }

        static {
            InHeadEmpty = new String[]{"base", "basefont", "bgsound", "command", "link"};
            InHeadRaw = new String[]{"noframes", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE};
            InHeadEnd = new String[]{"body", "br", "html"};
            AfterHeadBody = new String[]{"body", "html"};
            BeforeHtmlToHead = new String[]{"body", "br", "head", "html"};
            InHeadNoScriptHead = new String[]{"basefont", "bgsound", "link", JSONAPISpecConstants.META, "noframes", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE};
            InBodyStartToHead = new String[]{"base", "basefont", "bgsound", "command", "link", JSONAPISpecConstants.META, "noframes", "script", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "title"};
            InBodyStartPClosers = new String[]{"address", "article", "aside", "blockquote", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", Category.K_MENU_CATEGORY, "nav", "ol", "p", "section", "summary", "ul"};
            Headings = new String[]{"h1", "h2", "h3", "h4", "h5", "h6"};
            InBodyStartLiBreakers = new String[]{"address", "div", "p"};
            DdDt = new String[]{"dd", "dt"};
            Formatters = new String[]{"b", "big", "code", UserDataStore.EMAIL, "font", "i", com.appboy.Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY, "small", "strike", "strong", "tt", "u"};
            InBodyStartApplets = new String[]{"applet", "marquee", SlideshowPageFragment.ARG_OBJECT};
            InBodyStartEmptyFormatters = new String[]{"area", "br", "embed", "img", "keygen", "wbr"};
            InBodyStartMedia = new String[]{"param", "source", "track"};
            InBodyStartInputAttribs = new String[]{"action", "name", "prompt"};
            InBodyStartDrop = new String[]{ShareConstants.FEED_CAPTION_PARAM, "col", "colgroup", "frame", "head", "tbody", HtmlTableRow.Row_TYPE_TABLE_DATA, "tfoot", HtmlTableRow.ROW_TYPE_TABLE_HEADER, "thead", "tr"};
            InBodyEndClosers = new String[]{"address", "article", "aside", "blockquote", "button", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", Category.K_MENU_CATEGORY, "nav", "ol", ActivityTest.FORMAT_TYPE_PRE, "section", "summary", "ul"};
            InBodyEndAdoptionFormatters = new String[]{com.appboy.Constants.APPBOY_PUSH_CONTENT_KEY, "b", "big", "code", UserDataStore.EMAIL, "font", "i", "nobr", com.appboy.Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY, "small", "strike", "strong", "tt", "u"};
            InBodyEndTableFosters = new String[]{com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE, "tbody", "tfoot", "thead", "tr"};
            InTableToBody = new String[]{"tbody", "tfoot", "thead"};
            InTableAddBody = new String[]{HtmlTableRow.Row_TYPE_TABLE_DATA, HtmlTableRow.ROW_TYPE_TABLE_HEADER, "tr"};
            InTableToHead = new String[]{"script", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE};
            InCellNames = new String[]{HtmlTableRow.Row_TYPE_TABLE_DATA, HtmlTableRow.ROW_TYPE_TABLE_HEADER};
            InCellBody = new String[]{"body", ShareConstants.FEED_CAPTION_PARAM, "col", "colgroup", "html"};
            InCellTable = new String[]{com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE, "tbody", "tfoot", "thead", "tr"};
            InCellCol = new String[]{ShareConstants.FEED_CAPTION_PARAM, "col", "colgroup", "tbody", HtmlTableRow.Row_TYPE_TABLE_DATA, "tfoot", HtmlTableRow.ROW_TYPE_TABLE_HEADER, "thead", "tr"};
            InTableEndErr = new String[]{"body", ShareConstants.FEED_CAPTION_PARAM, "col", "colgroup", "html", "tbody", HtmlTableRow.Row_TYPE_TABLE_DATA, "tfoot", HtmlTableRow.ROW_TYPE_TABLE_HEADER, "thead", "tr"};
            InTableFoster = new String[]{com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE, "tbody", "tfoot", "thead", "tr"};
            InTableBodyExit = new String[]{ShareConstants.FEED_CAPTION_PARAM, "col", "colgroup", "tbody", "tfoot", "thead"};
            InTableBodyEndIgnore = new String[]{"body", ShareConstants.FEED_CAPTION_PARAM, "col", "colgroup", "html", HtmlTableRow.Row_TYPE_TABLE_DATA, HtmlTableRow.ROW_TYPE_TABLE_HEADER, "tr"};
            InRowMissing = new String[]{ShareConstants.FEED_CAPTION_PARAM, "col", "colgroup", "tbody", "tfoot", "thead", "tr"};
            InRowIgnore = new String[]{"body", ShareConstants.FEED_CAPTION_PARAM, "col", "colgroup", "html", HtmlTableRow.Row_TYPE_TABLE_DATA, HtmlTableRow.ROW_TYPE_TABLE_HEADER};
            InSelectEnd = new String[]{"input", "keygen", "textarea"};
            InSelecTableEnd = new String[]{ShareConstants.FEED_CAPTION_PARAM, com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE, "tbody", HtmlTableRow.Row_TYPE_TABLE_DATA, "tfoot", HtmlTableRow.ROW_TYPE_TABLE_HEADER, "thead", "tr"};
            InTableEndIgnore = new String[]{"tbody", "tfoot", "thead"};
            InHeadNoscriptIgnore = new String[]{"head", "noscript"};
            InCaptionIgnore = new String[]{"body", "col", "colgroup", "html", "tbody", HtmlTableRow.Row_TYPE_TABLE_DATA, "tfoot", HtmlTableRow.ROW_TYPE_TABLE_HEADER, "thead", "tr"};
        }
    }
}
