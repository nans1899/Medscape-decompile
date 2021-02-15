package com.google.firebase.inappmessaging;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class MessagesProto {

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ActionOrBuilder extends MessageLiteOrBuilder {
        String getActionUrl();

        ByteString getActionUrlBytes();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface BannerMessageOrBuilder extends MessageLiteOrBuilder {
        Action getAction();

        String getBackgroundHexColor();

        ByteString getBackgroundHexColorBytes();

        Text getBody();

        String getImageUrl();

        ByteString getImageUrlBytes();

        Text getTitle();

        boolean hasAction();

        boolean hasBody();

        boolean hasTitle();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ButtonOrBuilder extends MessageLiteOrBuilder {
        String getButtonHexColor();

        ByteString getButtonHexColorBytes();

        Text getText();

        boolean hasText();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface CardMessageOrBuilder extends MessageLiteOrBuilder {
        String getBackgroundHexColor();

        ByteString getBackgroundHexColorBytes();

        Text getBody();

        String getLandscapeImageUrl();

        ByteString getLandscapeImageUrlBytes();

        String getPortraitImageUrl();

        ByteString getPortraitImageUrlBytes();

        Action getPrimaryAction();

        Button getPrimaryActionButton();

        Action getSecondaryAction();

        Button getSecondaryActionButton();

        Text getTitle();

        boolean hasBody();

        boolean hasPrimaryAction();

        boolean hasPrimaryActionButton();

        boolean hasSecondaryAction();

        boolean hasSecondaryActionButton();

        boolean hasTitle();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ContentOrBuilder extends MessageLiteOrBuilder {
        BannerMessage getBanner();

        CardMessage getCard();

        ImageOnlyMessage getImageOnly();

        Content.MessageDetailsCase getMessageDetailsCase();

        ModalMessage getModal();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ImageOnlyMessageOrBuilder extends MessageLiteOrBuilder {
        Action getAction();

        String getImageUrl();

        ByteString getImageUrlBytes();

        boolean hasAction();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ModalMessageOrBuilder extends MessageLiteOrBuilder {
        Action getAction();

        Button getActionButton();

        String getBackgroundHexColor();

        ByteString getBackgroundHexColorBytes();

        Text getBody();

        String getImageUrl();

        ByteString getImageUrlBytes();

        Text getTitle();

        boolean hasAction();

        boolean hasActionButton();

        boolean hasBody();

        boolean hasTitle();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface TextOrBuilder extends MessageLiteOrBuilder {
        String getHexColor();

        ByteString getHexColorBytes();

        String getText();

        ByteString getTextBytes();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MessagesProto() {
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Content extends GeneratedMessageLite<Content, Builder> implements ContentOrBuilder {
        public static final int BANNER_FIELD_NUMBER = 1;
        public static final int CARD_FIELD_NUMBER = 4;
        /* access modifiers changed from: private */
        public static final Content DEFAULT_INSTANCE;
        public static final int IMAGE_ONLY_FIELD_NUMBER = 3;
        public static final int MODAL_FIELD_NUMBER = 2;
        private static volatile Parser<Content> PARSER;
        private int messageDetailsCase_ = 0;
        private Object messageDetails_;

        private Content() {
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public enum MessageDetailsCase implements Internal.EnumLite {
            BANNER(1),
            MODAL(2),
            IMAGE_ONLY(3),
            CARD(4),
            MESSAGEDETAILS_NOT_SET(0);
            
            private final int value;

            private MessageDetailsCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static MessageDetailsCase valueOf(int i) {
                return forNumber(i);
            }

            public static MessageDetailsCase forNumber(int i) {
                if (i == 0) {
                    return MESSAGEDETAILS_NOT_SET;
                }
                if (i == 1) {
                    return BANNER;
                }
                if (i == 2) {
                    return MODAL;
                }
                if (i == 3) {
                    return IMAGE_ONLY;
                }
                if (i != 4) {
                    return null;
                }
                return CARD;
            }

            public int getNumber() {
                return this.value;
            }
        }

        public MessageDetailsCase getMessageDetailsCase() {
            return MessageDetailsCase.forNumber(this.messageDetailsCase_);
        }

        /* access modifiers changed from: private */
        public void clearMessageDetails() {
            this.messageDetailsCase_ = 0;
            this.messageDetails_ = null;
        }

        public BannerMessage getBanner() {
            if (this.messageDetailsCase_ == 1) {
                return (BannerMessage) this.messageDetails_;
            }
            return BannerMessage.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setBanner(BannerMessage bannerMessage) {
            if (bannerMessage != null) {
                this.messageDetails_ = bannerMessage;
                this.messageDetailsCase_ = 1;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setBanner(BannerMessage.Builder builder) {
            this.messageDetails_ = builder.build();
            this.messageDetailsCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void mergeBanner(BannerMessage bannerMessage) {
            if (this.messageDetailsCase_ != 1 || this.messageDetails_ == BannerMessage.getDefaultInstance()) {
                this.messageDetails_ = bannerMessage;
            } else {
                this.messageDetails_ = ((BannerMessage.Builder) BannerMessage.newBuilder((BannerMessage) this.messageDetails_).mergeFrom(bannerMessage)).buildPartial();
            }
            this.messageDetailsCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void clearBanner() {
            if (this.messageDetailsCase_ == 1) {
                this.messageDetailsCase_ = 0;
                this.messageDetails_ = null;
            }
        }

        public ModalMessage getModal() {
            if (this.messageDetailsCase_ == 2) {
                return (ModalMessage) this.messageDetails_;
            }
            return ModalMessage.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setModal(ModalMessage modalMessage) {
            if (modalMessage != null) {
                this.messageDetails_ = modalMessage;
                this.messageDetailsCase_ = 2;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setModal(ModalMessage.Builder builder) {
            this.messageDetails_ = builder.build();
            this.messageDetailsCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeModal(ModalMessage modalMessage) {
            if (this.messageDetailsCase_ != 2 || this.messageDetails_ == ModalMessage.getDefaultInstance()) {
                this.messageDetails_ = modalMessage;
            } else {
                this.messageDetails_ = ((ModalMessage.Builder) ModalMessage.newBuilder((ModalMessage) this.messageDetails_).mergeFrom(modalMessage)).buildPartial();
            }
            this.messageDetailsCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearModal() {
            if (this.messageDetailsCase_ == 2) {
                this.messageDetailsCase_ = 0;
                this.messageDetails_ = null;
            }
        }

        public ImageOnlyMessage getImageOnly() {
            if (this.messageDetailsCase_ == 3) {
                return (ImageOnlyMessage) this.messageDetails_;
            }
            return ImageOnlyMessage.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setImageOnly(ImageOnlyMessage imageOnlyMessage) {
            if (imageOnlyMessage != null) {
                this.messageDetails_ = imageOnlyMessage;
                this.messageDetailsCase_ = 3;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setImageOnly(ImageOnlyMessage.Builder builder) {
            this.messageDetails_ = builder.build();
            this.messageDetailsCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeImageOnly(ImageOnlyMessage imageOnlyMessage) {
            if (this.messageDetailsCase_ != 3 || this.messageDetails_ == ImageOnlyMessage.getDefaultInstance()) {
                this.messageDetails_ = imageOnlyMessage;
            } else {
                this.messageDetails_ = ((ImageOnlyMessage.Builder) ImageOnlyMessage.newBuilder((ImageOnlyMessage) this.messageDetails_).mergeFrom(imageOnlyMessage)).buildPartial();
            }
            this.messageDetailsCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearImageOnly() {
            if (this.messageDetailsCase_ == 3) {
                this.messageDetailsCase_ = 0;
                this.messageDetails_ = null;
            }
        }

        public CardMessage getCard() {
            if (this.messageDetailsCase_ == 4) {
                return (CardMessage) this.messageDetails_;
            }
            return CardMessage.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setCard(CardMessage cardMessage) {
            if (cardMessage != null) {
                this.messageDetails_ = cardMessage;
                this.messageDetailsCase_ = 4;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setCard(CardMessage.Builder builder) {
            this.messageDetails_ = builder.build();
            this.messageDetailsCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void mergeCard(CardMessage cardMessage) {
            if (this.messageDetailsCase_ != 4 || this.messageDetails_ == CardMessage.getDefaultInstance()) {
                this.messageDetails_ = cardMessage;
            } else {
                this.messageDetails_ = ((CardMessage.Builder) CardMessage.newBuilder((CardMessage) this.messageDetails_).mergeFrom(cardMessage)).buildPartial();
            }
            this.messageDetailsCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void clearCard() {
            if (this.messageDetailsCase_ == 4) {
                this.messageDetailsCase_ = 0;
                this.messageDetails_ = null;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.messageDetailsCase_ == 1) {
                codedOutputStream.writeMessage(1, (BannerMessage) this.messageDetails_);
            }
            if (this.messageDetailsCase_ == 2) {
                codedOutputStream.writeMessage(2, (ModalMessage) this.messageDetails_);
            }
            if (this.messageDetailsCase_ == 3) {
                codedOutputStream.writeMessage(3, (ImageOnlyMessage) this.messageDetails_);
            }
            if (this.messageDetailsCase_ == 4) {
                codedOutputStream.writeMessage(4, (CardMessage) this.messageDetails_);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.messageDetailsCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, (BannerMessage) this.messageDetails_);
            }
            if (this.messageDetailsCase_ == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, (ModalMessage) this.messageDetails_);
            }
            if (this.messageDetailsCase_ == 3) {
                i2 += CodedOutputStream.computeMessageSize(3, (ImageOnlyMessage) this.messageDetails_);
            }
            if (this.messageDetailsCase_ == 4) {
                i2 += CodedOutputStream.computeMessageSize(4, (CardMessage) this.messageDetails_);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Content parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Content) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Content parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Content) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Content parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Content) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Content parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Content) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Content parseFrom(InputStream inputStream) throws IOException {
            return (Content) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Content parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Content) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Content parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Content) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Content parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Content) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Content parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Content) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Content parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Content) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Content content) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(content);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<Content, Builder> implements ContentOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Content.DEFAULT_INSTANCE);
            }

            public MessageDetailsCase getMessageDetailsCase() {
                return ((Content) this.instance).getMessageDetailsCase();
            }

            public Builder clearMessageDetails() {
                copyOnWrite();
                ((Content) this.instance).clearMessageDetails();
                return this;
            }

            public BannerMessage getBanner() {
                return ((Content) this.instance).getBanner();
            }

            public Builder setBanner(BannerMessage bannerMessage) {
                copyOnWrite();
                ((Content) this.instance).setBanner(bannerMessage);
                return this;
            }

            public Builder setBanner(BannerMessage.Builder builder) {
                copyOnWrite();
                ((Content) this.instance).setBanner(builder);
                return this;
            }

            public Builder mergeBanner(BannerMessage bannerMessage) {
                copyOnWrite();
                ((Content) this.instance).mergeBanner(bannerMessage);
                return this;
            }

            public Builder clearBanner() {
                copyOnWrite();
                ((Content) this.instance).clearBanner();
                return this;
            }

            public ModalMessage getModal() {
                return ((Content) this.instance).getModal();
            }

            public Builder setModal(ModalMessage modalMessage) {
                copyOnWrite();
                ((Content) this.instance).setModal(modalMessage);
                return this;
            }

            public Builder setModal(ModalMessage.Builder builder) {
                copyOnWrite();
                ((Content) this.instance).setModal(builder);
                return this;
            }

            public Builder mergeModal(ModalMessage modalMessage) {
                copyOnWrite();
                ((Content) this.instance).mergeModal(modalMessage);
                return this;
            }

            public Builder clearModal() {
                copyOnWrite();
                ((Content) this.instance).clearModal();
                return this;
            }

            public ImageOnlyMessage getImageOnly() {
                return ((Content) this.instance).getImageOnly();
            }

            public Builder setImageOnly(ImageOnlyMessage imageOnlyMessage) {
                copyOnWrite();
                ((Content) this.instance).setImageOnly(imageOnlyMessage);
                return this;
            }

            public Builder setImageOnly(ImageOnlyMessage.Builder builder) {
                copyOnWrite();
                ((Content) this.instance).setImageOnly(builder);
                return this;
            }

            public Builder mergeImageOnly(ImageOnlyMessage imageOnlyMessage) {
                copyOnWrite();
                ((Content) this.instance).mergeImageOnly(imageOnlyMessage);
                return this;
            }

            public Builder clearImageOnly() {
                copyOnWrite();
                ((Content) this.instance).clearImageOnly();
                return this;
            }

            public CardMessage getCard() {
                return ((Content) this.instance).getCard();
            }

            public Builder setCard(CardMessage cardMessage) {
                copyOnWrite();
                ((Content) this.instance).setCard(cardMessage);
                return this;
            }

            public Builder setCard(CardMessage.Builder builder) {
                copyOnWrite();
                ((Content) this.instance).setCard(builder);
                return this;
            }

            public Builder mergeCard(CardMessage cardMessage) {
                copyOnWrite();
                ((Content) this.instance).mergeCard(cardMessage);
                return this;
            }

            public Builder clearCard() {
                copyOnWrite();
                ((Content) this.instance).clearCard();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            int i;
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Content();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Content content = (Content) obj2;
                    int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$inappmessaging$MessagesProto$Content$MessageDetailsCase[content.getMessageDetailsCase().ordinal()];
                    if (i2 == 1) {
                        if (this.messageDetailsCase_ == 1) {
                            z = true;
                        }
                        this.messageDetails_ = visitor.visitOneofMessage(z, this.messageDetails_, content.messageDetails_);
                    } else if (i2 == 2) {
                        if (this.messageDetailsCase_ == 2) {
                            z = true;
                        }
                        this.messageDetails_ = visitor.visitOneofMessage(z, this.messageDetails_, content.messageDetails_);
                    } else if (i2 == 3) {
                        if (this.messageDetailsCase_ == 3) {
                            z = true;
                        }
                        this.messageDetails_ = visitor.visitOneofMessage(z, this.messageDetails_, content.messageDetails_);
                    } else if (i2 == 4) {
                        if (this.messageDetailsCase_ == 4) {
                            z = true;
                        }
                        this.messageDetails_ = visitor.visitOneofMessage(z, this.messageDetails_, content.messageDetails_);
                    } else if (i2 == 5) {
                        if (this.messageDetailsCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = content.messageDetailsCase_) != 0) {
                        this.messageDetailsCase_ = i;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    BannerMessage.Builder builder = this.messageDetailsCase_ == 1 ? (BannerMessage.Builder) ((BannerMessage) this.messageDetails_).toBuilder() : null;
                                    MessageLite readMessage = codedInputStream.readMessage(BannerMessage.parser(), extensionRegistryLite);
                                    this.messageDetails_ = readMessage;
                                    if (builder != null) {
                                        builder.mergeFrom((BannerMessage) readMessage);
                                        this.messageDetails_ = builder.buildPartial();
                                    }
                                    this.messageDetailsCase_ = 1;
                                } else if (readTag == 18) {
                                    ModalMessage.Builder builder2 = this.messageDetailsCase_ == 2 ? (ModalMessage.Builder) ((ModalMessage) this.messageDetails_).toBuilder() : null;
                                    MessageLite readMessage2 = codedInputStream.readMessage(ModalMessage.parser(), extensionRegistryLite);
                                    this.messageDetails_ = readMessage2;
                                    if (builder2 != null) {
                                        builder2.mergeFrom((ModalMessage) readMessage2);
                                        this.messageDetails_ = builder2.buildPartial();
                                    }
                                    this.messageDetailsCase_ = 2;
                                } else if (readTag == 26) {
                                    ImageOnlyMessage.Builder builder3 = this.messageDetailsCase_ == 3 ? (ImageOnlyMessage.Builder) ((ImageOnlyMessage) this.messageDetails_).toBuilder() : null;
                                    MessageLite readMessage3 = codedInputStream.readMessage(ImageOnlyMessage.parser(), extensionRegistryLite);
                                    this.messageDetails_ = readMessage3;
                                    if (builder3 != null) {
                                        builder3.mergeFrom((ImageOnlyMessage) readMessage3);
                                        this.messageDetails_ = builder3.buildPartial();
                                    }
                                    this.messageDetailsCase_ = 3;
                                } else if (readTag == 34) {
                                    CardMessage.Builder builder4 = this.messageDetailsCase_ == 4 ? (CardMessage.Builder) ((CardMessage) this.messageDetails_).toBuilder() : null;
                                    MessageLite readMessage4 = codedInputStream.readMessage(CardMessage.parser(), extensionRegistryLite);
                                    this.messageDetails_ = readMessage4;
                                    if (builder4 != null) {
                                        builder4.mergeFrom((CardMessage) readMessage4);
                                        this.messageDetails_ = builder4.buildPartial();
                                    }
                                    this.messageDetailsCase_ = 4;
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (Content.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            Content content = new Content();
            DEFAULT_INSTANCE = content;
            content.makeImmutable();
        }

        public static Content getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Content> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firebase.inappmessaging.MessagesProto$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$inappmessaging$MessagesProto$Content$MessageDetailsCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|(2:23|24)|25|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|(2:23|24)|25|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|(2:23|24)|25|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x008f */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                r1 = 1
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r6 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r5 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r6 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r7 = 6
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r5 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r6 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7 = 7
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r5 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r6 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r7 = 8
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                com.google.firebase.inappmessaging.MessagesProto$Content$MessageDetailsCase[] r5 = com.google.firebase.inappmessaging.MessagesProto.Content.MessageDetailsCase.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$google$firebase$inappmessaging$MessagesProto$Content$MessageDetailsCase = r5
                com.google.firebase.inappmessaging.MessagesProto$Content$MessageDetailsCase r6 = com.google.firebase.inappmessaging.MessagesProto.Content.MessageDetailsCase.BANNER     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r1 = $SwitchMap$com$google$firebase$inappmessaging$MessagesProto$Content$MessageDetailsCase     // Catch:{ NoSuchFieldError -> 0x007b }
                com.google.firebase.inappmessaging.MessagesProto$Content$MessageDetailsCase r5 = com.google.firebase.inappmessaging.MessagesProto.Content.MessageDetailsCase.MODAL     // Catch:{ NoSuchFieldError -> 0x007b }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r1[r5] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$MessagesProto$Content$MessageDetailsCase     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.google.firebase.inappmessaging.MessagesProto$Content$MessageDetailsCase r1 = com.google.firebase.inappmessaging.MessagesProto.Content.MessageDetailsCase.IMAGE_ONLY     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$MessagesProto$Content$MessageDetailsCase     // Catch:{ NoSuchFieldError -> 0x008f }
                com.google.firebase.inappmessaging.MessagesProto$Content$MessageDetailsCase r1 = com.google.firebase.inappmessaging.MessagesProto.Content.MessageDetailsCase.CARD     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$MessagesProto$Content$MessageDetailsCase     // Catch:{ NoSuchFieldError -> 0x0099 }
                com.google.firebase.inappmessaging.MessagesProto$Content$MessageDetailsCase r1 = com.google.firebase.inappmessaging.MessagesProto.Content.MessageDetailsCase.MESSAGEDETAILS_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.MessagesProto.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class CardMessage extends GeneratedMessageLite<CardMessage, Builder> implements CardMessageOrBuilder {
        public static final int BACKGROUND_HEX_COLOR_FIELD_NUMBER = 5;
        public static final int BODY_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final CardMessage DEFAULT_INSTANCE;
        public static final int LANDSCAPE_IMAGE_URL_FIELD_NUMBER = 4;
        private static volatile Parser<CardMessage> PARSER = null;
        public static final int PORTRAIT_IMAGE_URL_FIELD_NUMBER = 3;
        public static final int PRIMARY_ACTION_BUTTON_FIELD_NUMBER = 6;
        public static final int PRIMARY_ACTION_FIELD_NUMBER = 7;
        public static final int SECONDARY_ACTION_BUTTON_FIELD_NUMBER = 8;
        public static final int SECONDARY_ACTION_FIELD_NUMBER = 9;
        public static final int TITLE_FIELD_NUMBER = 1;
        private String backgroundHexColor_ = "";
        private Text body_;
        private String landscapeImageUrl_ = "";
        private String portraitImageUrl_ = "";
        private Button primaryActionButton_;
        private Action primaryAction_;
        private Button secondaryActionButton_;
        private Action secondaryAction_;
        private Text title_;

        private CardMessage() {
        }

        public boolean hasTitle() {
            return this.title_ != null;
        }

        public Text getTitle() {
            Text text = this.title_;
            return text == null ? Text.getDefaultInstance() : text;
        }

        /* access modifiers changed from: private */
        public void setTitle(Text text) {
            if (text != null) {
                this.title_ = text;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setTitle(Text.Builder builder) {
            this.title_ = (Text) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeTitle(Text text) {
            Text text2 = this.title_;
            if (text2 == null || text2 == Text.getDefaultInstance()) {
                this.title_ = text;
            } else {
                this.title_ = (Text) ((Text.Builder) Text.newBuilder(this.title_).mergeFrom(text)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = null;
        }

        public boolean hasBody() {
            return this.body_ != null;
        }

        public Text getBody() {
            Text text = this.body_;
            return text == null ? Text.getDefaultInstance() : text;
        }

        /* access modifiers changed from: private */
        public void setBody(Text text) {
            if (text != null) {
                this.body_ = text;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setBody(Text.Builder builder) {
            this.body_ = (Text) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeBody(Text text) {
            Text text2 = this.body_;
            if (text2 == null || text2 == Text.getDefaultInstance()) {
                this.body_ = text;
            } else {
                this.body_ = (Text) ((Text.Builder) Text.newBuilder(this.body_).mergeFrom(text)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearBody() {
            this.body_ = null;
        }

        public String getPortraitImageUrl() {
            return this.portraitImageUrl_;
        }

        public ByteString getPortraitImageUrlBytes() {
            return ByteString.copyFromUtf8(this.portraitImageUrl_);
        }

        /* access modifiers changed from: private */
        public void setPortraitImageUrl(String str) {
            if (str != null) {
                this.portraitImageUrl_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearPortraitImageUrl() {
            this.portraitImageUrl_ = getDefaultInstance().getPortraitImageUrl();
        }

        /* access modifiers changed from: private */
        public void setPortraitImageUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.portraitImageUrl_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getLandscapeImageUrl() {
            return this.landscapeImageUrl_;
        }

        public ByteString getLandscapeImageUrlBytes() {
            return ByteString.copyFromUtf8(this.landscapeImageUrl_);
        }

        /* access modifiers changed from: private */
        public void setLandscapeImageUrl(String str) {
            if (str != null) {
                this.landscapeImageUrl_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearLandscapeImageUrl() {
            this.landscapeImageUrl_ = getDefaultInstance().getLandscapeImageUrl();
        }

        /* access modifiers changed from: private */
        public void setLandscapeImageUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.landscapeImageUrl_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getBackgroundHexColor() {
            return this.backgroundHexColor_;
        }

        public ByteString getBackgroundHexColorBytes() {
            return ByteString.copyFromUtf8(this.backgroundHexColor_);
        }

        /* access modifiers changed from: private */
        public void setBackgroundHexColor(String str) {
            if (str != null) {
                this.backgroundHexColor_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearBackgroundHexColor() {
            this.backgroundHexColor_ = getDefaultInstance().getBackgroundHexColor();
        }

        /* access modifiers changed from: private */
        public void setBackgroundHexColorBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.backgroundHexColor_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasPrimaryActionButton() {
            return this.primaryActionButton_ != null;
        }

        public Button getPrimaryActionButton() {
            Button button = this.primaryActionButton_;
            return button == null ? Button.getDefaultInstance() : button;
        }

        /* access modifiers changed from: private */
        public void setPrimaryActionButton(Button button) {
            if (button != null) {
                this.primaryActionButton_ = button;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setPrimaryActionButton(Button.Builder builder) {
            this.primaryActionButton_ = (Button) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergePrimaryActionButton(Button button) {
            Button button2 = this.primaryActionButton_;
            if (button2 == null || button2 == Button.getDefaultInstance()) {
                this.primaryActionButton_ = button;
            } else {
                this.primaryActionButton_ = (Button) ((Button.Builder) Button.newBuilder(this.primaryActionButton_).mergeFrom(button)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearPrimaryActionButton() {
            this.primaryActionButton_ = null;
        }

        public boolean hasPrimaryAction() {
            return this.primaryAction_ != null;
        }

        public Action getPrimaryAction() {
            Action action = this.primaryAction_;
            return action == null ? Action.getDefaultInstance() : action;
        }

        /* access modifiers changed from: private */
        public void setPrimaryAction(Action action) {
            if (action != null) {
                this.primaryAction_ = action;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setPrimaryAction(Action.Builder builder) {
            this.primaryAction_ = (Action) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergePrimaryAction(Action action) {
            Action action2 = this.primaryAction_;
            if (action2 == null || action2 == Action.getDefaultInstance()) {
                this.primaryAction_ = action;
            } else {
                this.primaryAction_ = (Action) ((Action.Builder) Action.newBuilder(this.primaryAction_).mergeFrom(action)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearPrimaryAction() {
            this.primaryAction_ = null;
        }

        public boolean hasSecondaryActionButton() {
            return this.secondaryActionButton_ != null;
        }

        public Button getSecondaryActionButton() {
            Button button = this.secondaryActionButton_;
            return button == null ? Button.getDefaultInstance() : button;
        }

        /* access modifiers changed from: private */
        public void setSecondaryActionButton(Button button) {
            if (button != null) {
                this.secondaryActionButton_ = button;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setSecondaryActionButton(Button.Builder builder) {
            this.secondaryActionButton_ = (Button) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeSecondaryActionButton(Button button) {
            Button button2 = this.secondaryActionButton_;
            if (button2 == null || button2 == Button.getDefaultInstance()) {
                this.secondaryActionButton_ = button;
            } else {
                this.secondaryActionButton_ = (Button) ((Button.Builder) Button.newBuilder(this.secondaryActionButton_).mergeFrom(button)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearSecondaryActionButton() {
            this.secondaryActionButton_ = null;
        }

        public boolean hasSecondaryAction() {
            return this.secondaryAction_ != null;
        }

        public Action getSecondaryAction() {
            Action action = this.secondaryAction_;
            return action == null ? Action.getDefaultInstance() : action;
        }

        /* access modifiers changed from: private */
        public void setSecondaryAction(Action action) {
            if (action != null) {
                this.secondaryAction_ = action;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setSecondaryAction(Action.Builder builder) {
            this.secondaryAction_ = (Action) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeSecondaryAction(Action action) {
            Action action2 = this.secondaryAction_;
            if (action2 == null || action2 == Action.getDefaultInstance()) {
                this.secondaryAction_ = action;
            } else {
                this.secondaryAction_ = (Action) ((Action.Builder) Action.newBuilder(this.secondaryAction_).mergeFrom(action)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearSecondaryAction() {
            this.secondaryAction_ = null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.title_ != null) {
                codedOutputStream.writeMessage(1, getTitle());
            }
            if (this.body_ != null) {
                codedOutputStream.writeMessage(2, getBody());
            }
            if (!this.portraitImageUrl_.isEmpty()) {
                codedOutputStream.writeString(3, getPortraitImageUrl());
            }
            if (!this.landscapeImageUrl_.isEmpty()) {
                codedOutputStream.writeString(4, getLandscapeImageUrl());
            }
            if (!this.backgroundHexColor_.isEmpty()) {
                codedOutputStream.writeString(5, getBackgroundHexColor());
            }
            if (this.primaryActionButton_ != null) {
                codedOutputStream.writeMessage(6, getPrimaryActionButton());
            }
            if (this.primaryAction_ != null) {
                codedOutputStream.writeMessage(7, getPrimaryAction());
            }
            if (this.secondaryActionButton_ != null) {
                codedOutputStream.writeMessage(8, getSecondaryActionButton());
            }
            if (this.secondaryAction_ != null) {
                codedOutputStream.writeMessage(9, getSecondaryAction());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.title_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getTitle());
            }
            if (this.body_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getBody());
            }
            if (!this.portraitImageUrl_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getPortraitImageUrl());
            }
            if (!this.landscapeImageUrl_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(4, getLandscapeImageUrl());
            }
            if (!this.backgroundHexColor_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getBackgroundHexColor());
            }
            if (this.primaryActionButton_ != null) {
                i2 += CodedOutputStream.computeMessageSize(6, getPrimaryActionButton());
            }
            if (this.primaryAction_ != null) {
                i2 += CodedOutputStream.computeMessageSize(7, getPrimaryAction());
            }
            if (this.secondaryActionButton_ != null) {
                i2 += CodedOutputStream.computeMessageSize(8, getSecondaryActionButton());
            }
            if (this.secondaryAction_ != null) {
                i2 += CodedOutputStream.computeMessageSize(9, getSecondaryAction());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static CardMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CardMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CardMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CardMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CardMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CardMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CardMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CardMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CardMessage parseFrom(InputStream inputStream) throws IOException {
            return (CardMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CardMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CardMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CardMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CardMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CardMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CardMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CardMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CardMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CardMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CardMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CardMessage cardMessage) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(cardMessage);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<CardMessage, Builder> implements CardMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(CardMessage.DEFAULT_INSTANCE);
            }

            public boolean hasTitle() {
                return ((CardMessage) this.instance).hasTitle();
            }

            public Text getTitle() {
                return ((CardMessage) this.instance).getTitle();
            }

            public Builder setTitle(Text text) {
                copyOnWrite();
                ((CardMessage) this.instance).setTitle(text);
                return this;
            }

            public Builder setTitle(Text.Builder builder) {
                copyOnWrite();
                ((CardMessage) this.instance).setTitle(builder);
                return this;
            }

            public Builder mergeTitle(Text text) {
                copyOnWrite();
                ((CardMessage) this.instance).mergeTitle(text);
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((CardMessage) this.instance).clearTitle();
                return this;
            }

            public boolean hasBody() {
                return ((CardMessage) this.instance).hasBody();
            }

            public Text getBody() {
                return ((CardMessage) this.instance).getBody();
            }

            public Builder setBody(Text text) {
                copyOnWrite();
                ((CardMessage) this.instance).setBody(text);
                return this;
            }

            public Builder setBody(Text.Builder builder) {
                copyOnWrite();
                ((CardMessage) this.instance).setBody(builder);
                return this;
            }

            public Builder mergeBody(Text text) {
                copyOnWrite();
                ((CardMessage) this.instance).mergeBody(text);
                return this;
            }

            public Builder clearBody() {
                copyOnWrite();
                ((CardMessage) this.instance).clearBody();
                return this;
            }

            public String getPortraitImageUrl() {
                return ((CardMessage) this.instance).getPortraitImageUrl();
            }

            public ByteString getPortraitImageUrlBytes() {
                return ((CardMessage) this.instance).getPortraitImageUrlBytes();
            }

            public Builder setPortraitImageUrl(String str) {
                copyOnWrite();
                ((CardMessage) this.instance).setPortraitImageUrl(str);
                return this;
            }

            public Builder clearPortraitImageUrl() {
                copyOnWrite();
                ((CardMessage) this.instance).clearPortraitImageUrl();
                return this;
            }

            public Builder setPortraitImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((CardMessage) this.instance).setPortraitImageUrlBytes(byteString);
                return this;
            }

            public String getLandscapeImageUrl() {
                return ((CardMessage) this.instance).getLandscapeImageUrl();
            }

            public ByteString getLandscapeImageUrlBytes() {
                return ((CardMessage) this.instance).getLandscapeImageUrlBytes();
            }

            public Builder setLandscapeImageUrl(String str) {
                copyOnWrite();
                ((CardMessage) this.instance).setLandscapeImageUrl(str);
                return this;
            }

            public Builder clearLandscapeImageUrl() {
                copyOnWrite();
                ((CardMessage) this.instance).clearLandscapeImageUrl();
                return this;
            }

            public Builder setLandscapeImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((CardMessage) this.instance).setLandscapeImageUrlBytes(byteString);
                return this;
            }

            public String getBackgroundHexColor() {
                return ((CardMessage) this.instance).getBackgroundHexColor();
            }

            public ByteString getBackgroundHexColorBytes() {
                return ((CardMessage) this.instance).getBackgroundHexColorBytes();
            }

            public Builder setBackgroundHexColor(String str) {
                copyOnWrite();
                ((CardMessage) this.instance).setBackgroundHexColor(str);
                return this;
            }

            public Builder clearBackgroundHexColor() {
                copyOnWrite();
                ((CardMessage) this.instance).clearBackgroundHexColor();
                return this;
            }

            public Builder setBackgroundHexColorBytes(ByteString byteString) {
                copyOnWrite();
                ((CardMessage) this.instance).setBackgroundHexColorBytes(byteString);
                return this;
            }

            public boolean hasPrimaryActionButton() {
                return ((CardMessage) this.instance).hasPrimaryActionButton();
            }

            public Button getPrimaryActionButton() {
                return ((CardMessage) this.instance).getPrimaryActionButton();
            }

            public Builder setPrimaryActionButton(Button button) {
                copyOnWrite();
                ((CardMessage) this.instance).setPrimaryActionButton(button);
                return this;
            }

            public Builder setPrimaryActionButton(Button.Builder builder) {
                copyOnWrite();
                ((CardMessage) this.instance).setPrimaryActionButton(builder);
                return this;
            }

            public Builder mergePrimaryActionButton(Button button) {
                copyOnWrite();
                ((CardMessage) this.instance).mergePrimaryActionButton(button);
                return this;
            }

            public Builder clearPrimaryActionButton() {
                copyOnWrite();
                ((CardMessage) this.instance).clearPrimaryActionButton();
                return this;
            }

            public boolean hasPrimaryAction() {
                return ((CardMessage) this.instance).hasPrimaryAction();
            }

            public Action getPrimaryAction() {
                return ((CardMessage) this.instance).getPrimaryAction();
            }

            public Builder setPrimaryAction(Action action) {
                copyOnWrite();
                ((CardMessage) this.instance).setPrimaryAction(action);
                return this;
            }

            public Builder setPrimaryAction(Action.Builder builder) {
                copyOnWrite();
                ((CardMessage) this.instance).setPrimaryAction(builder);
                return this;
            }

            public Builder mergePrimaryAction(Action action) {
                copyOnWrite();
                ((CardMessage) this.instance).mergePrimaryAction(action);
                return this;
            }

            public Builder clearPrimaryAction() {
                copyOnWrite();
                ((CardMessage) this.instance).clearPrimaryAction();
                return this;
            }

            public boolean hasSecondaryActionButton() {
                return ((CardMessage) this.instance).hasSecondaryActionButton();
            }

            public Button getSecondaryActionButton() {
                return ((CardMessage) this.instance).getSecondaryActionButton();
            }

            public Builder setSecondaryActionButton(Button button) {
                copyOnWrite();
                ((CardMessage) this.instance).setSecondaryActionButton(button);
                return this;
            }

            public Builder setSecondaryActionButton(Button.Builder builder) {
                copyOnWrite();
                ((CardMessage) this.instance).setSecondaryActionButton(builder);
                return this;
            }

            public Builder mergeSecondaryActionButton(Button button) {
                copyOnWrite();
                ((CardMessage) this.instance).mergeSecondaryActionButton(button);
                return this;
            }

            public Builder clearSecondaryActionButton() {
                copyOnWrite();
                ((CardMessage) this.instance).clearSecondaryActionButton();
                return this;
            }

            public boolean hasSecondaryAction() {
                return ((CardMessage) this.instance).hasSecondaryAction();
            }

            public Action getSecondaryAction() {
                return ((CardMessage) this.instance).getSecondaryAction();
            }

            public Builder setSecondaryAction(Action action) {
                copyOnWrite();
                ((CardMessage) this.instance).setSecondaryAction(action);
                return this;
            }

            public Builder setSecondaryAction(Action.Builder builder) {
                copyOnWrite();
                ((CardMessage) this.instance).setSecondaryAction(builder);
                return this;
            }

            public Builder mergeSecondaryAction(Action action) {
                copyOnWrite();
                ((CardMessage) this.instance).mergeSecondaryAction(action);
                return this;
            }

            public Builder clearSecondaryAction() {
                copyOnWrite();
                ((CardMessage) this.instance).clearSecondaryAction();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new CardMessage();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CardMessage cardMessage = (CardMessage) obj2;
                    this.title_ = (Text) visitor.visitMessage(this.title_, cardMessage.title_);
                    this.body_ = (Text) visitor.visitMessage(this.body_, cardMessage.body_);
                    this.portraitImageUrl_ = visitor.visitString(!this.portraitImageUrl_.isEmpty(), this.portraitImageUrl_, !cardMessage.portraitImageUrl_.isEmpty(), cardMessage.portraitImageUrl_);
                    this.landscapeImageUrl_ = visitor.visitString(!this.landscapeImageUrl_.isEmpty(), this.landscapeImageUrl_, !cardMessage.landscapeImageUrl_.isEmpty(), cardMessage.landscapeImageUrl_);
                    this.backgroundHexColor_ = visitor.visitString(!this.backgroundHexColor_.isEmpty(), this.backgroundHexColor_, true ^ cardMessage.backgroundHexColor_.isEmpty(), cardMessage.backgroundHexColor_);
                    this.primaryActionButton_ = (Button) visitor.visitMessage(this.primaryActionButton_, cardMessage.primaryActionButton_);
                    this.primaryAction_ = (Action) visitor.visitMessage(this.primaryAction_, cardMessage.primaryAction_);
                    this.secondaryActionButton_ = (Button) visitor.visitMessage(this.secondaryActionButton_, cardMessage.secondaryActionButton_);
                    this.secondaryAction_ = (Action) visitor.visitMessage(this.secondaryAction_, cardMessage.secondaryAction_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    Text.Builder builder = this.title_ != null ? (Text.Builder) this.title_.toBuilder() : null;
                                    Text text = (Text) codedInputStream.readMessage(Text.parser(), extensionRegistryLite);
                                    this.title_ = text;
                                    if (builder != null) {
                                        builder.mergeFrom(text);
                                        this.title_ = (Text) builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    Text.Builder builder2 = this.body_ != null ? (Text.Builder) this.body_.toBuilder() : null;
                                    Text text2 = (Text) codedInputStream.readMessage(Text.parser(), extensionRegistryLite);
                                    this.body_ = text2;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(text2);
                                        this.body_ = (Text) builder2.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    this.portraitImageUrl_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 34) {
                                    this.landscapeImageUrl_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 42) {
                                    this.backgroundHexColor_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 50) {
                                    Button.Builder builder3 = this.primaryActionButton_ != null ? (Button.Builder) this.primaryActionButton_.toBuilder() : null;
                                    Button button = (Button) codedInputStream.readMessage(Button.parser(), extensionRegistryLite);
                                    this.primaryActionButton_ = button;
                                    if (builder3 != null) {
                                        builder3.mergeFrom(button);
                                        this.primaryActionButton_ = (Button) builder3.buildPartial();
                                    }
                                } else if (readTag == 58) {
                                    Action.Builder builder4 = this.primaryAction_ != null ? (Action.Builder) this.primaryAction_.toBuilder() : null;
                                    Action action = (Action) codedInputStream.readMessage(Action.parser(), extensionRegistryLite);
                                    this.primaryAction_ = action;
                                    if (builder4 != null) {
                                        builder4.mergeFrom(action);
                                        this.primaryAction_ = (Action) builder4.buildPartial();
                                    }
                                } else if (readTag == 66) {
                                    Button.Builder builder5 = this.secondaryActionButton_ != null ? (Button.Builder) this.secondaryActionButton_.toBuilder() : null;
                                    Button button2 = (Button) codedInputStream.readMessage(Button.parser(), extensionRegistryLite);
                                    this.secondaryActionButton_ = button2;
                                    if (builder5 != null) {
                                        builder5.mergeFrom(button2);
                                        this.secondaryActionButton_ = (Button) builder5.buildPartial();
                                    }
                                } else if (readTag == 74) {
                                    Action.Builder builder6 = this.secondaryAction_ != null ? (Action.Builder) this.secondaryAction_.toBuilder() : null;
                                    Action action2 = (Action) codedInputStream.readMessage(Action.parser(), extensionRegistryLite);
                                    this.secondaryAction_ = action2;
                                    if (builder6 != null) {
                                        builder6.mergeFrom(action2);
                                        this.secondaryAction_ = (Action) builder6.buildPartial();
                                    }
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (CardMessage.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            CardMessage cardMessage = new CardMessage();
            DEFAULT_INSTANCE = cardMessage;
            cardMessage.makeImmutable();
        }

        public static CardMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CardMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class ImageOnlyMessage extends GeneratedMessageLite<ImageOnlyMessage, Builder> implements ImageOnlyMessageOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final ImageOnlyMessage DEFAULT_INSTANCE;
        public static final int IMAGE_URL_FIELD_NUMBER = 1;
        private static volatile Parser<ImageOnlyMessage> PARSER;
        private Action action_;
        private String imageUrl_ = "";

        private ImageOnlyMessage() {
        }

        public String getImageUrl() {
            return this.imageUrl_;
        }

        public ByteString getImageUrlBytes() {
            return ByteString.copyFromUtf8(this.imageUrl_);
        }

        /* access modifiers changed from: private */
        public void setImageUrl(String str) {
            if (str != null) {
                this.imageUrl_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearImageUrl() {
            this.imageUrl_ = getDefaultInstance().getImageUrl();
        }

        /* access modifiers changed from: private */
        public void setImageUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.imageUrl_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasAction() {
            return this.action_ != null;
        }

        public Action getAction() {
            Action action = this.action_;
            return action == null ? Action.getDefaultInstance() : action;
        }

        /* access modifiers changed from: private */
        public void setAction(Action action) {
            if (action != null) {
                this.action_ = action;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setAction(Action.Builder builder) {
            this.action_ = (Action) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeAction(Action action) {
            Action action2 = this.action_;
            if (action2 == null || action2 == Action.getDefaultInstance()) {
                this.action_ = action;
            } else {
                this.action_ = (Action) ((Action.Builder) Action.newBuilder(this.action_).mergeFrom(action)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.action_ = null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.imageUrl_.isEmpty()) {
                codedOutputStream.writeString(1, getImageUrl());
            }
            if (this.action_ != null) {
                codedOutputStream.writeMessage(2, getAction());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.imageUrl_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getImageUrl());
            }
            if (this.action_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getAction());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ImageOnlyMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ImageOnlyMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ImageOnlyMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ImageOnlyMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ImageOnlyMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ImageOnlyMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ImageOnlyMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ImageOnlyMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ImageOnlyMessage parseFrom(InputStream inputStream) throws IOException {
            return (ImageOnlyMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ImageOnlyMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ImageOnlyMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ImageOnlyMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ImageOnlyMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ImageOnlyMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ImageOnlyMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ImageOnlyMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ImageOnlyMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ImageOnlyMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ImageOnlyMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ImageOnlyMessage imageOnlyMessage) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(imageOnlyMessage);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<ImageOnlyMessage, Builder> implements ImageOnlyMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ImageOnlyMessage.DEFAULT_INSTANCE);
            }

            public String getImageUrl() {
                return ((ImageOnlyMessage) this.instance).getImageUrl();
            }

            public ByteString getImageUrlBytes() {
                return ((ImageOnlyMessage) this.instance).getImageUrlBytes();
            }

            public Builder setImageUrl(String str) {
                copyOnWrite();
                ((ImageOnlyMessage) this.instance).setImageUrl(str);
                return this;
            }

            public Builder clearImageUrl() {
                copyOnWrite();
                ((ImageOnlyMessage) this.instance).clearImageUrl();
                return this;
            }

            public Builder setImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((ImageOnlyMessage) this.instance).setImageUrlBytes(byteString);
                return this;
            }

            public boolean hasAction() {
                return ((ImageOnlyMessage) this.instance).hasAction();
            }

            public Action getAction() {
                return ((ImageOnlyMessage) this.instance).getAction();
            }

            public Builder setAction(Action action) {
                copyOnWrite();
                ((ImageOnlyMessage) this.instance).setAction(action);
                return this;
            }

            public Builder setAction(Action.Builder builder) {
                copyOnWrite();
                ((ImageOnlyMessage) this.instance).setAction(builder);
                return this;
            }

            public Builder mergeAction(Action action) {
                copyOnWrite();
                ((ImageOnlyMessage) this.instance).mergeAction(action);
                return this;
            }

            public Builder clearAction() {
                copyOnWrite();
                ((ImageOnlyMessage) this.instance).clearAction();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ImageOnlyMessage();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ImageOnlyMessage imageOnlyMessage = (ImageOnlyMessage) obj2;
                    this.imageUrl_ = visitor.visitString(!this.imageUrl_.isEmpty(), this.imageUrl_, true ^ imageOnlyMessage.imageUrl_.isEmpty(), imageOnlyMessage.imageUrl_);
                    this.action_ = (Action) visitor.visitMessage(this.action_, imageOnlyMessage.action_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.imageUrl_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    Action.Builder builder = this.action_ != null ? (Action.Builder) this.action_.toBuilder() : null;
                                    Action action = (Action) codedInputStream.readMessage(Action.parser(), extensionRegistryLite);
                                    this.action_ = action;
                                    if (builder != null) {
                                        builder.mergeFrom(action);
                                        this.action_ = (Action) builder.buildPartial();
                                    }
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (ImageOnlyMessage.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            ImageOnlyMessage imageOnlyMessage = new ImageOnlyMessage();
            DEFAULT_INSTANCE = imageOnlyMessage;
            imageOnlyMessage.makeImmutable();
        }

        public static ImageOnlyMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ImageOnlyMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class BannerMessage extends GeneratedMessageLite<BannerMessage, Builder> implements BannerMessageOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 4;
        public static final int BACKGROUND_HEX_COLOR_FIELD_NUMBER = 5;
        public static final int BODY_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final BannerMessage DEFAULT_INSTANCE;
        public static final int IMAGE_URL_FIELD_NUMBER = 3;
        private static volatile Parser<BannerMessage> PARSER = null;
        public static final int TITLE_FIELD_NUMBER = 1;
        private Action action_;
        private String backgroundHexColor_ = "";
        private Text body_;
        private String imageUrl_ = "";
        private Text title_;

        private BannerMessage() {
        }

        public boolean hasTitle() {
            return this.title_ != null;
        }

        public Text getTitle() {
            Text text = this.title_;
            return text == null ? Text.getDefaultInstance() : text;
        }

        /* access modifiers changed from: private */
        public void setTitle(Text text) {
            if (text != null) {
                this.title_ = text;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setTitle(Text.Builder builder) {
            this.title_ = (Text) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeTitle(Text text) {
            Text text2 = this.title_;
            if (text2 == null || text2 == Text.getDefaultInstance()) {
                this.title_ = text;
            } else {
                this.title_ = (Text) ((Text.Builder) Text.newBuilder(this.title_).mergeFrom(text)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = null;
        }

        public boolean hasBody() {
            return this.body_ != null;
        }

        public Text getBody() {
            Text text = this.body_;
            return text == null ? Text.getDefaultInstance() : text;
        }

        /* access modifiers changed from: private */
        public void setBody(Text text) {
            if (text != null) {
                this.body_ = text;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setBody(Text.Builder builder) {
            this.body_ = (Text) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeBody(Text text) {
            Text text2 = this.body_;
            if (text2 == null || text2 == Text.getDefaultInstance()) {
                this.body_ = text;
            } else {
                this.body_ = (Text) ((Text.Builder) Text.newBuilder(this.body_).mergeFrom(text)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearBody() {
            this.body_ = null;
        }

        public String getImageUrl() {
            return this.imageUrl_;
        }

        public ByteString getImageUrlBytes() {
            return ByteString.copyFromUtf8(this.imageUrl_);
        }

        /* access modifiers changed from: private */
        public void setImageUrl(String str) {
            if (str != null) {
                this.imageUrl_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearImageUrl() {
            this.imageUrl_ = getDefaultInstance().getImageUrl();
        }

        /* access modifiers changed from: private */
        public void setImageUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.imageUrl_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasAction() {
            return this.action_ != null;
        }

        public Action getAction() {
            Action action = this.action_;
            return action == null ? Action.getDefaultInstance() : action;
        }

        /* access modifiers changed from: private */
        public void setAction(Action action) {
            if (action != null) {
                this.action_ = action;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setAction(Action.Builder builder) {
            this.action_ = (Action) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeAction(Action action) {
            Action action2 = this.action_;
            if (action2 == null || action2 == Action.getDefaultInstance()) {
                this.action_ = action;
            } else {
                this.action_ = (Action) ((Action.Builder) Action.newBuilder(this.action_).mergeFrom(action)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.action_ = null;
        }

        public String getBackgroundHexColor() {
            return this.backgroundHexColor_;
        }

        public ByteString getBackgroundHexColorBytes() {
            return ByteString.copyFromUtf8(this.backgroundHexColor_);
        }

        /* access modifiers changed from: private */
        public void setBackgroundHexColor(String str) {
            if (str != null) {
                this.backgroundHexColor_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearBackgroundHexColor() {
            this.backgroundHexColor_ = getDefaultInstance().getBackgroundHexColor();
        }

        /* access modifiers changed from: private */
        public void setBackgroundHexColorBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.backgroundHexColor_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.title_ != null) {
                codedOutputStream.writeMessage(1, getTitle());
            }
            if (this.body_ != null) {
                codedOutputStream.writeMessage(2, getBody());
            }
            if (!this.imageUrl_.isEmpty()) {
                codedOutputStream.writeString(3, getImageUrl());
            }
            if (this.action_ != null) {
                codedOutputStream.writeMessage(4, getAction());
            }
            if (!this.backgroundHexColor_.isEmpty()) {
                codedOutputStream.writeString(5, getBackgroundHexColor());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.title_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getTitle());
            }
            if (this.body_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getBody());
            }
            if (!this.imageUrl_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getImageUrl());
            }
            if (this.action_ != null) {
                i2 += CodedOutputStream.computeMessageSize(4, getAction());
            }
            if (!this.backgroundHexColor_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getBackgroundHexColor());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static BannerMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BannerMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static BannerMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BannerMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static BannerMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BannerMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static BannerMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BannerMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static BannerMessage parseFrom(InputStream inputStream) throws IOException {
            return (BannerMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BannerMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BannerMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BannerMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BannerMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BannerMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BannerMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BannerMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BannerMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static BannerMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BannerMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BannerMessage bannerMessage) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(bannerMessage);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<BannerMessage, Builder> implements BannerMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(BannerMessage.DEFAULT_INSTANCE);
            }

            public boolean hasTitle() {
                return ((BannerMessage) this.instance).hasTitle();
            }

            public Text getTitle() {
                return ((BannerMessage) this.instance).getTitle();
            }

            public Builder setTitle(Text text) {
                copyOnWrite();
                ((BannerMessage) this.instance).setTitle(text);
                return this;
            }

            public Builder setTitle(Text.Builder builder) {
                copyOnWrite();
                ((BannerMessage) this.instance).setTitle(builder);
                return this;
            }

            public Builder mergeTitle(Text text) {
                copyOnWrite();
                ((BannerMessage) this.instance).mergeTitle(text);
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((BannerMessage) this.instance).clearTitle();
                return this;
            }

            public boolean hasBody() {
                return ((BannerMessage) this.instance).hasBody();
            }

            public Text getBody() {
                return ((BannerMessage) this.instance).getBody();
            }

            public Builder setBody(Text text) {
                copyOnWrite();
                ((BannerMessage) this.instance).setBody(text);
                return this;
            }

            public Builder setBody(Text.Builder builder) {
                copyOnWrite();
                ((BannerMessage) this.instance).setBody(builder);
                return this;
            }

            public Builder mergeBody(Text text) {
                copyOnWrite();
                ((BannerMessage) this.instance).mergeBody(text);
                return this;
            }

            public Builder clearBody() {
                copyOnWrite();
                ((BannerMessage) this.instance).clearBody();
                return this;
            }

            public String getImageUrl() {
                return ((BannerMessage) this.instance).getImageUrl();
            }

            public ByteString getImageUrlBytes() {
                return ((BannerMessage) this.instance).getImageUrlBytes();
            }

            public Builder setImageUrl(String str) {
                copyOnWrite();
                ((BannerMessage) this.instance).setImageUrl(str);
                return this;
            }

            public Builder clearImageUrl() {
                copyOnWrite();
                ((BannerMessage) this.instance).clearImageUrl();
                return this;
            }

            public Builder setImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((BannerMessage) this.instance).setImageUrlBytes(byteString);
                return this;
            }

            public boolean hasAction() {
                return ((BannerMessage) this.instance).hasAction();
            }

            public Action getAction() {
                return ((BannerMessage) this.instance).getAction();
            }

            public Builder setAction(Action action) {
                copyOnWrite();
                ((BannerMessage) this.instance).setAction(action);
                return this;
            }

            public Builder setAction(Action.Builder builder) {
                copyOnWrite();
                ((BannerMessage) this.instance).setAction(builder);
                return this;
            }

            public Builder mergeAction(Action action) {
                copyOnWrite();
                ((BannerMessage) this.instance).mergeAction(action);
                return this;
            }

            public Builder clearAction() {
                copyOnWrite();
                ((BannerMessage) this.instance).clearAction();
                return this;
            }

            public String getBackgroundHexColor() {
                return ((BannerMessage) this.instance).getBackgroundHexColor();
            }

            public ByteString getBackgroundHexColorBytes() {
                return ((BannerMessage) this.instance).getBackgroundHexColorBytes();
            }

            public Builder setBackgroundHexColor(String str) {
                copyOnWrite();
                ((BannerMessage) this.instance).setBackgroundHexColor(str);
                return this;
            }

            public Builder clearBackgroundHexColor() {
                copyOnWrite();
                ((BannerMessage) this.instance).clearBackgroundHexColor();
                return this;
            }

            public Builder setBackgroundHexColorBytes(ByteString byteString) {
                copyOnWrite();
                ((BannerMessage) this.instance).setBackgroundHexColorBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new BannerMessage();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    BannerMessage bannerMessage = (BannerMessage) obj2;
                    this.title_ = (Text) visitor.visitMessage(this.title_, bannerMessage.title_);
                    this.body_ = (Text) visitor.visitMessage(this.body_, bannerMessage.body_);
                    this.imageUrl_ = visitor.visitString(!this.imageUrl_.isEmpty(), this.imageUrl_, !bannerMessage.imageUrl_.isEmpty(), bannerMessage.imageUrl_);
                    this.action_ = (Action) visitor.visitMessage(this.action_, bannerMessage.action_);
                    this.backgroundHexColor_ = visitor.visitString(!this.backgroundHexColor_.isEmpty(), this.backgroundHexColor_, true ^ bannerMessage.backgroundHexColor_.isEmpty(), bannerMessage.backgroundHexColor_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    Text.Builder builder = this.title_ != null ? (Text.Builder) this.title_.toBuilder() : null;
                                    Text text = (Text) codedInputStream.readMessage(Text.parser(), extensionRegistryLite);
                                    this.title_ = text;
                                    if (builder != null) {
                                        builder.mergeFrom(text);
                                        this.title_ = (Text) builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    Text.Builder builder2 = this.body_ != null ? (Text.Builder) this.body_.toBuilder() : null;
                                    Text text2 = (Text) codedInputStream.readMessage(Text.parser(), extensionRegistryLite);
                                    this.body_ = text2;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(text2);
                                        this.body_ = (Text) builder2.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    this.imageUrl_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 34) {
                                    Action.Builder builder3 = this.action_ != null ? (Action.Builder) this.action_.toBuilder() : null;
                                    Action action = (Action) codedInputStream.readMessage(Action.parser(), extensionRegistryLite);
                                    this.action_ = action;
                                    if (builder3 != null) {
                                        builder3.mergeFrom(action);
                                        this.action_ = (Action) builder3.buildPartial();
                                    }
                                } else if (readTag == 42) {
                                    this.backgroundHexColor_ = codedInputStream.readStringRequireUtf8();
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (BannerMessage.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            BannerMessage bannerMessage = new BannerMessage();
            DEFAULT_INSTANCE = bannerMessage;
            bannerMessage.makeImmutable();
        }

        public static BannerMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BannerMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class ModalMessage extends GeneratedMessageLite<ModalMessage, Builder> implements ModalMessageOrBuilder {
        public static final int ACTION_BUTTON_FIELD_NUMBER = 4;
        public static final int ACTION_FIELD_NUMBER = 5;
        public static final int BACKGROUND_HEX_COLOR_FIELD_NUMBER = 6;
        public static final int BODY_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final ModalMessage DEFAULT_INSTANCE;
        public static final int IMAGE_URL_FIELD_NUMBER = 3;
        private static volatile Parser<ModalMessage> PARSER = null;
        public static final int TITLE_FIELD_NUMBER = 1;
        private Button actionButton_;
        private Action action_;
        private String backgroundHexColor_ = "";
        private Text body_;
        private String imageUrl_ = "";
        private Text title_;

        private ModalMessage() {
        }

        public boolean hasTitle() {
            return this.title_ != null;
        }

        public Text getTitle() {
            Text text = this.title_;
            return text == null ? Text.getDefaultInstance() : text;
        }

        /* access modifiers changed from: private */
        public void setTitle(Text text) {
            if (text != null) {
                this.title_ = text;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setTitle(Text.Builder builder) {
            this.title_ = (Text) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeTitle(Text text) {
            Text text2 = this.title_;
            if (text2 == null || text2 == Text.getDefaultInstance()) {
                this.title_ = text;
            } else {
                this.title_ = (Text) ((Text.Builder) Text.newBuilder(this.title_).mergeFrom(text)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = null;
        }

        public boolean hasBody() {
            return this.body_ != null;
        }

        public Text getBody() {
            Text text = this.body_;
            return text == null ? Text.getDefaultInstance() : text;
        }

        /* access modifiers changed from: private */
        public void setBody(Text text) {
            if (text != null) {
                this.body_ = text;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setBody(Text.Builder builder) {
            this.body_ = (Text) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeBody(Text text) {
            Text text2 = this.body_;
            if (text2 == null || text2 == Text.getDefaultInstance()) {
                this.body_ = text;
            } else {
                this.body_ = (Text) ((Text.Builder) Text.newBuilder(this.body_).mergeFrom(text)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearBody() {
            this.body_ = null;
        }

        public String getImageUrl() {
            return this.imageUrl_;
        }

        public ByteString getImageUrlBytes() {
            return ByteString.copyFromUtf8(this.imageUrl_);
        }

        /* access modifiers changed from: private */
        public void setImageUrl(String str) {
            if (str != null) {
                this.imageUrl_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearImageUrl() {
            this.imageUrl_ = getDefaultInstance().getImageUrl();
        }

        /* access modifiers changed from: private */
        public void setImageUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.imageUrl_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasActionButton() {
            return this.actionButton_ != null;
        }

        public Button getActionButton() {
            Button button = this.actionButton_;
            return button == null ? Button.getDefaultInstance() : button;
        }

        /* access modifiers changed from: private */
        public void setActionButton(Button button) {
            if (button != null) {
                this.actionButton_ = button;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setActionButton(Button.Builder builder) {
            this.actionButton_ = (Button) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeActionButton(Button button) {
            Button button2 = this.actionButton_;
            if (button2 == null || button2 == Button.getDefaultInstance()) {
                this.actionButton_ = button;
            } else {
                this.actionButton_ = (Button) ((Button.Builder) Button.newBuilder(this.actionButton_).mergeFrom(button)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearActionButton() {
            this.actionButton_ = null;
        }

        public boolean hasAction() {
            return this.action_ != null;
        }

        public Action getAction() {
            Action action = this.action_;
            return action == null ? Action.getDefaultInstance() : action;
        }

        /* access modifiers changed from: private */
        public void setAction(Action action) {
            if (action != null) {
                this.action_ = action;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setAction(Action.Builder builder) {
            this.action_ = (Action) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeAction(Action action) {
            Action action2 = this.action_;
            if (action2 == null || action2 == Action.getDefaultInstance()) {
                this.action_ = action;
            } else {
                this.action_ = (Action) ((Action.Builder) Action.newBuilder(this.action_).mergeFrom(action)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.action_ = null;
        }

        public String getBackgroundHexColor() {
            return this.backgroundHexColor_;
        }

        public ByteString getBackgroundHexColorBytes() {
            return ByteString.copyFromUtf8(this.backgroundHexColor_);
        }

        /* access modifiers changed from: private */
        public void setBackgroundHexColor(String str) {
            if (str != null) {
                this.backgroundHexColor_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearBackgroundHexColor() {
            this.backgroundHexColor_ = getDefaultInstance().getBackgroundHexColor();
        }

        /* access modifiers changed from: private */
        public void setBackgroundHexColorBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.backgroundHexColor_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.title_ != null) {
                codedOutputStream.writeMessage(1, getTitle());
            }
            if (this.body_ != null) {
                codedOutputStream.writeMessage(2, getBody());
            }
            if (!this.imageUrl_.isEmpty()) {
                codedOutputStream.writeString(3, getImageUrl());
            }
            if (this.actionButton_ != null) {
                codedOutputStream.writeMessage(4, getActionButton());
            }
            if (this.action_ != null) {
                codedOutputStream.writeMessage(5, getAction());
            }
            if (!this.backgroundHexColor_.isEmpty()) {
                codedOutputStream.writeString(6, getBackgroundHexColor());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.title_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getTitle());
            }
            if (this.body_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getBody());
            }
            if (!this.imageUrl_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getImageUrl());
            }
            if (this.actionButton_ != null) {
                i2 += CodedOutputStream.computeMessageSize(4, getActionButton());
            }
            if (this.action_ != null) {
                i2 += CodedOutputStream.computeMessageSize(5, getAction());
            }
            if (!this.backgroundHexColor_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(6, getBackgroundHexColor());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ModalMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ModalMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ModalMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ModalMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ModalMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ModalMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ModalMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ModalMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ModalMessage parseFrom(InputStream inputStream) throws IOException {
            return (ModalMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ModalMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ModalMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ModalMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ModalMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ModalMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ModalMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ModalMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ModalMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ModalMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ModalMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ModalMessage modalMessage) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(modalMessage);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<ModalMessage, Builder> implements ModalMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ModalMessage.DEFAULT_INSTANCE);
            }

            public boolean hasTitle() {
                return ((ModalMessage) this.instance).hasTitle();
            }

            public Text getTitle() {
                return ((ModalMessage) this.instance).getTitle();
            }

            public Builder setTitle(Text text) {
                copyOnWrite();
                ((ModalMessage) this.instance).setTitle(text);
                return this;
            }

            public Builder setTitle(Text.Builder builder) {
                copyOnWrite();
                ((ModalMessage) this.instance).setTitle(builder);
                return this;
            }

            public Builder mergeTitle(Text text) {
                copyOnWrite();
                ((ModalMessage) this.instance).mergeTitle(text);
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((ModalMessage) this.instance).clearTitle();
                return this;
            }

            public boolean hasBody() {
                return ((ModalMessage) this.instance).hasBody();
            }

            public Text getBody() {
                return ((ModalMessage) this.instance).getBody();
            }

            public Builder setBody(Text text) {
                copyOnWrite();
                ((ModalMessage) this.instance).setBody(text);
                return this;
            }

            public Builder setBody(Text.Builder builder) {
                copyOnWrite();
                ((ModalMessage) this.instance).setBody(builder);
                return this;
            }

            public Builder mergeBody(Text text) {
                copyOnWrite();
                ((ModalMessage) this.instance).mergeBody(text);
                return this;
            }

            public Builder clearBody() {
                copyOnWrite();
                ((ModalMessage) this.instance).clearBody();
                return this;
            }

            public String getImageUrl() {
                return ((ModalMessage) this.instance).getImageUrl();
            }

            public ByteString getImageUrlBytes() {
                return ((ModalMessage) this.instance).getImageUrlBytes();
            }

            public Builder setImageUrl(String str) {
                copyOnWrite();
                ((ModalMessage) this.instance).setImageUrl(str);
                return this;
            }

            public Builder clearImageUrl() {
                copyOnWrite();
                ((ModalMessage) this.instance).clearImageUrl();
                return this;
            }

            public Builder setImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((ModalMessage) this.instance).setImageUrlBytes(byteString);
                return this;
            }

            public boolean hasActionButton() {
                return ((ModalMessage) this.instance).hasActionButton();
            }

            public Button getActionButton() {
                return ((ModalMessage) this.instance).getActionButton();
            }

            public Builder setActionButton(Button button) {
                copyOnWrite();
                ((ModalMessage) this.instance).setActionButton(button);
                return this;
            }

            public Builder setActionButton(Button.Builder builder) {
                copyOnWrite();
                ((ModalMessage) this.instance).setActionButton(builder);
                return this;
            }

            public Builder mergeActionButton(Button button) {
                copyOnWrite();
                ((ModalMessage) this.instance).mergeActionButton(button);
                return this;
            }

            public Builder clearActionButton() {
                copyOnWrite();
                ((ModalMessage) this.instance).clearActionButton();
                return this;
            }

            public boolean hasAction() {
                return ((ModalMessage) this.instance).hasAction();
            }

            public Action getAction() {
                return ((ModalMessage) this.instance).getAction();
            }

            public Builder setAction(Action action) {
                copyOnWrite();
                ((ModalMessage) this.instance).setAction(action);
                return this;
            }

            public Builder setAction(Action.Builder builder) {
                copyOnWrite();
                ((ModalMessage) this.instance).setAction(builder);
                return this;
            }

            public Builder mergeAction(Action action) {
                copyOnWrite();
                ((ModalMessage) this.instance).mergeAction(action);
                return this;
            }

            public Builder clearAction() {
                copyOnWrite();
                ((ModalMessage) this.instance).clearAction();
                return this;
            }

            public String getBackgroundHexColor() {
                return ((ModalMessage) this.instance).getBackgroundHexColor();
            }

            public ByteString getBackgroundHexColorBytes() {
                return ((ModalMessage) this.instance).getBackgroundHexColorBytes();
            }

            public Builder setBackgroundHexColor(String str) {
                copyOnWrite();
                ((ModalMessage) this.instance).setBackgroundHexColor(str);
                return this;
            }

            public Builder clearBackgroundHexColor() {
                copyOnWrite();
                ((ModalMessage) this.instance).clearBackgroundHexColor();
                return this;
            }

            public Builder setBackgroundHexColorBytes(ByteString byteString) {
                copyOnWrite();
                ((ModalMessage) this.instance).setBackgroundHexColorBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ModalMessage();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ModalMessage modalMessage = (ModalMessage) obj2;
                    this.title_ = (Text) visitor.visitMessage(this.title_, modalMessage.title_);
                    this.body_ = (Text) visitor.visitMessage(this.body_, modalMessage.body_);
                    this.imageUrl_ = visitor.visitString(!this.imageUrl_.isEmpty(), this.imageUrl_, !modalMessage.imageUrl_.isEmpty(), modalMessage.imageUrl_);
                    this.actionButton_ = (Button) visitor.visitMessage(this.actionButton_, modalMessage.actionButton_);
                    this.action_ = (Action) visitor.visitMessage(this.action_, modalMessage.action_);
                    this.backgroundHexColor_ = visitor.visitString(!this.backgroundHexColor_.isEmpty(), this.backgroundHexColor_, true ^ modalMessage.backgroundHexColor_.isEmpty(), modalMessage.backgroundHexColor_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    Text.Builder builder = this.title_ != null ? (Text.Builder) this.title_.toBuilder() : null;
                                    Text text = (Text) codedInputStream.readMessage(Text.parser(), extensionRegistryLite);
                                    this.title_ = text;
                                    if (builder != null) {
                                        builder.mergeFrom(text);
                                        this.title_ = (Text) builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    Text.Builder builder2 = this.body_ != null ? (Text.Builder) this.body_.toBuilder() : null;
                                    Text text2 = (Text) codedInputStream.readMessage(Text.parser(), extensionRegistryLite);
                                    this.body_ = text2;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(text2);
                                        this.body_ = (Text) builder2.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    this.imageUrl_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 34) {
                                    Button.Builder builder3 = this.actionButton_ != null ? (Button.Builder) this.actionButton_.toBuilder() : null;
                                    Button button = (Button) codedInputStream.readMessage(Button.parser(), extensionRegistryLite);
                                    this.actionButton_ = button;
                                    if (builder3 != null) {
                                        builder3.mergeFrom(button);
                                        this.actionButton_ = (Button) builder3.buildPartial();
                                    }
                                } else if (readTag == 42) {
                                    Action.Builder builder4 = this.action_ != null ? (Action.Builder) this.action_.toBuilder() : null;
                                    Action action = (Action) codedInputStream.readMessage(Action.parser(), extensionRegistryLite);
                                    this.action_ = action;
                                    if (builder4 != null) {
                                        builder4.mergeFrom(action);
                                        this.action_ = (Action) builder4.buildPartial();
                                    }
                                } else if (readTag == 50) {
                                    this.backgroundHexColor_ = codedInputStream.readStringRequireUtf8();
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (ModalMessage.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            ModalMessage modalMessage = new ModalMessage();
            DEFAULT_INSTANCE = modalMessage;
            modalMessage.makeImmutable();
        }

        public static ModalMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ModalMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Text extends GeneratedMessageLite<Text, Builder> implements TextOrBuilder {
        /* access modifiers changed from: private */
        public static final Text DEFAULT_INSTANCE;
        public static final int HEX_COLOR_FIELD_NUMBER = 2;
        private static volatile Parser<Text> PARSER = null;
        public static final int TEXT_FIELD_NUMBER = 1;
        private String hexColor_ = "";
        private String text_ = "";

        private Text() {
        }

        public String getText() {
            return this.text_;
        }

        public ByteString getTextBytes() {
            return ByteString.copyFromUtf8(this.text_);
        }

        /* access modifiers changed from: private */
        public void setText(String str) {
            if (str != null) {
                this.text_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearText() {
            this.text_ = getDefaultInstance().getText();
        }

        /* access modifiers changed from: private */
        public void setTextBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.text_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getHexColor() {
            return this.hexColor_;
        }

        public ByteString getHexColorBytes() {
            return ByteString.copyFromUtf8(this.hexColor_);
        }

        /* access modifiers changed from: private */
        public void setHexColor(String str) {
            if (str != null) {
                this.hexColor_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearHexColor() {
            this.hexColor_ = getDefaultInstance().getHexColor();
        }

        /* access modifiers changed from: private */
        public void setHexColorBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.hexColor_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.text_.isEmpty()) {
                codedOutputStream.writeString(1, getText());
            }
            if (!this.hexColor_.isEmpty()) {
                codedOutputStream.writeString(2, getHexColor());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.text_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getText());
            }
            if (!this.hexColor_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getHexColor());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Text parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Text parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Text parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Text parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Text parseFrom(InputStream inputStream) throws IOException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Text parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Text parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Text) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Text parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Text) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Text parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Text parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Text text) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(text);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<Text, Builder> implements TextOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Text.DEFAULT_INSTANCE);
            }

            public String getText() {
                return ((Text) this.instance).getText();
            }

            public ByteString getTextBytes() {
                return ((Text) this.instance).getTextBytes();
            }

            public Builder setText(String str) {
                copyOnWrite();
                ((Text) this.instance).setText(str);
                return this;
            }

            public Builder clearText() {
                copyOnWrite();
                ((Text) this.instance).clearText();
                return this;
            }

            public Builder setTextBytes(ByteString byteString) {
                copyOnWrite();
                ((Text) this.instance).setTextBytes(byteString);
                return this;
            }

            public String getHexColor() {
                return ((Text) this.instance).getHexColor();
            }

            public ByteString getHexColorBytes() {
                return ((Text) this.instance).getHexColorBytes();
            }

            public Builder setHexColor(String str) {
                copyOnWrite();
                ((Text) this.instance).setHexColor(str);
                return this;
            }

            public Builder clearHexColor() {
                copyOnWrite();
                ((Text) this.instance).clearHexColor();
                return this;
            }

            public Builder setHexColorBytes(ByteString byteString) {
                copyOnWrite();
                ((Text) this.instance).setHexColorBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Text();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Text text = (Text) obj2;
                    this.text_ = visitor.visitString(!this.text_.isEmpty(), this.text_, !text.text_.isEmpty(), text.text_);
                    this.hexColor_ = visitor.visitString(!this.hexColor_.isEmpty(), this.hexColor_, true ^ text.hexColor_.isEmpty(), text.hexColor_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.text_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.hexColor_ = codedInputStream.readStringRequireUtf8();
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (Text.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            Text text = new Text();
            DEFAULT_INSTANCE = text;
            text.makeImmutable();
        }

        public static Text getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Text> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Button extends GeneratedMessageLite<Button, Builder> implements ButtonOrBuilder {
        public static final int BUTTON_HEX_COLOR_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final Button DEFAULT_INSTANCE;
        private static volatile Parser<Button> PARSER = null;
        public static final int TEXT_FIELD_NUMBER = 1;
        private String buttonHexColor_ = "";
        private Text text_;

        private Button() {
        }

        public boolean hasText() {
            return this.text_ != null;
        }

        public Text getText() {
            Text text = this.text_;
            return text == null ? Text.getDefaultInstance() : text;
        }

        /* access modifiers changed from: private */
        public void setText(Text text) {
            if (text != null) {
                this.text_ = text;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setText(Text.Builder builder) {
            this.text_ = (Text) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeText(Text text) {
            Text text2 = this.text_;
            if (text2 == null || text2 == Text.getDefaultInstance()) {
                this.text_ = text;
            } else {
                this.text_ = (Text) ((Text.Builder) Text.newBuilder(this.text_).mergeFrom(text)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearText() {
            this.text_ = null;
        }

        public String getButtonHexColor() {
            return this.buttonHexColor_;
        }

        public ByteString getButtonHexColorBytes() {
            return ByteString.copyFromUtf8(this.buttonHexColor_);
        }

        /* access modifiers changed from: private */
        public void setButtonHexColor(String str) {
            if (str != null) {
                this.buttonHexColor_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearButtonHexColor() {
            this.buttonHexColor_ = getDefaultInstance().getButtonHexColor();
        }

        /* access modifiers changed from: private */
        public void setButtonHexColorBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.buttonHexColor_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.text_ != null) {
                codedOutputStream.writeMessage(1, getText());
            }
            if (!this.buttonHexColor_.isEmpty()) {
                codedOutputStream.writeString(2, getButtonHexColor());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.text_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getText());
            }
            if (!this.buttonHexColor_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getButtonHexColor());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Button parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Button) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Button parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Button) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Button parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Button) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Button parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Button) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Button parseFrom(InputStream inputStream) throws IOException {
            return (Button) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Button parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Button) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Button parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Button) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Button parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Button) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Button parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Button) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Button parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Button) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Button button) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(button);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<Button, Builder> implements ButtonOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Button.DEFAULT_INSTANCE);
            }

            public boolean hasText() {
                return ((Button) this.instance).hasText();
            }

            public Text getText() {
                return ((Button) this.instance).getText();
            }

            public Builder setText(Text text) {
                copyOnWrite();
                ((Button) this.instance).setText(text);
                return this;
            }

            public Builder setText(Text.Builder builder) {
                copyOnWrite();
                ((Button) this.instance).setText(builder);
                return this;
            }

            public Builder mergeText(Text text) {
                copyOnWrite();
                ((Button) this.instance).mergeText(text);
                return this;
            }

            public Builder clearText() {
                copyOnWrite();
                ((Button) this.instance).clearText();
                return this;
            }

            public String getButtonHexColor() {
                return ((Button) this.instance).getButtonHexColor();
            }

            public ByteString getButtonHexColorBytes() {
                return ((Button) this.instance).getButtonHexColorBytes();
            }

            public Builder setButtonHexColor(String str) {
                copyOnWrite();
                ((Button) this.instance).setButtonHexColor(str);
                return this;
            }

            public Builder clearButtonHexColor() {
                copyOnWrite();
                ((Button) this.instance).clearButtonHexColor();
                return this;
            }

            public Builder setButtonHexColorBytes(ByteString byteString) {
                copyOnWrite();
                ((Button) this.instance).setButtonHexColorBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Button();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Button button = (Button) obj2;
                    this.text_ = (Text) visitor.visitMessage(this.text_, button.text_);
                    this.buttonHexColor_ = visitor.visitString(!this.buttonHexColor_.isEmpty(), this.buttonHexColor_, true ^ button.buttonHexColor_.isEmpty(), button.buttonHexColor_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    Text.Builder builder = this.text_ != null ? (Text.Builder) this.text_.toBuilder() : null;
                                    Text text = (Text) codedInputStream.readMessage(Text.parser(), extensionRegistryLite);
                                    this.text_ = text;
                                    if (builder != null) {
                                        builder.mergeFrom(text);
                                        this.text_ = (Text) builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    this.buttonHexColor_ = codedInputStream.readStringRequireUtf8();
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (Button.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            Button button = new Button();
            DEFAULT_INSTANCE = button;
            button.makeImmutable();
        }

        public static Button getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Button> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Action extends GeneratedMessageLite<Action, Builder> implements ActionOrBuilder {
        public static final int ACTION_URL_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Action DEFAULT_INSTANCE;
        private static volatile Parser<Action> PARSER;
        private String actionUrl_ = "";

        private Action() {
        }

        public String getActionUrl() {
            return this.actionUrl_;
        }

        public ByteString getActionUrlBytes() {
            return ByteString.copyFromUtf8(this.actionUrl_);
        }

        /* access modifiers changed from: private */
        public void setActionUrl(String str) {
            if (str != null) {
                this.actionUrl_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearActionUrl() {
            this.actionUrl_ = getDefaultInstance().getActionUrl();
        }

        /* access modifiers changed from: private */
        public void setActionUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.actionUrl_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.actionUrl_.isEmpty()) {
                codedOutputStream.writeString(1, getActionUrl());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.actionUrl_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getActionUrl());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Action parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Action parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Action parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Action parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Action parseFrom(InputStream inputStream) throws IOException {
            return (Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Action parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Action parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Action) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Action parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Action) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Action parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Action parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Action action) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(action);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<Action, Builder> implements ActionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Action.DEFAULT_INSTANCE);
            }

            public String getActionUrl() {
                return ((Action) this.instance).getActionUrl();
            }

            public ByteString getActionUrlBytes() {
                return ((Action) this.instance).getActionUrlBytes();
            }

            public Builder setActionUrl(String str) {
                copyOnWrite();
                ((Action) this.instance).setActionUrl(str);
                return this;
            }

            public Builder clearActionUrl() {
                copyOnWrite();
                ((Action) this.instance).clearActionUrl();
                return this;
            }

            public Builder setActionUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((Action) this.instance).setActionUrlBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Action();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    Action action = (Action) obj2;
                    this.actionUrl_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.actionUrl_.isEmpty(), this.actionUrl_, true ^ action.actionUrl_.isEmpty(), action.actionUrl_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.actionUrl_ = codedInputStream.readStringRequireUtf8();
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (Action.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            Action action = new Action();
            DEFAULT_INSTANCE = action;
            action.makeImmutable();
        }

        public static Action getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Action> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
