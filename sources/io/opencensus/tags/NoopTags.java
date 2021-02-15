package io.opencensus.tags;

import com.wbmd.qxcalculator.util.legacy.ContentParser;
import io.opencensus.common.Scope;
import io.opencensus.internal.NoopScope;
import io.opencensus.internal.Utils;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagContextDeserializationException;
import io.opencensus.tags.propagation.TagContextSerializationException;
import io.opencensus.tags.propagation.TagContextTextFormat;
import io.opencensus.tags.propagation.TagPropagationComponent;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class NoopTags {
    private NoopTags() {
    }

    static TagsComponent newNoopTagsComponent() {
        return new NoopTagsComponent();
    }

    static Tagger getNoopTagger() {
        return NoopTagger.INSTANCE;
    }

    static TagContextBuilder getNoopTagContextBuilder() {
        return NoopTagContextBuilder.INSTANCE;
    }

    static TagContext getNoopTagContext() {
        return NoopTagContext.INSTANCE;
    }

    static TagPropagationComponent getNoopTagPropagationComponent() {
        return NoopTagPropagationComponent.INSTANCE;
    }

    static TagContextBinarySerializer getNoopTagContextBinarySerializer() {
        return NoopTagContextBinarySerializer.INSTANCE;
    }

    static TagContextTextFormat getNoopTagContextTextSerializer() {
        return NoopTagContextTextFormat.INSTANCE;
    }

    private static final class NoopTagsComponent extends TagsComponent {
        private volatile boolean isRead;

        private NoopTagsComponent() {
        }

        public Tagger getTagger() {
            return NoopTags.getNoopTagger();
        }

        public TagPropagationComponent getTagPropagationComponent() {
            return NoopTags.getNoopTagPropagationComponent();
        }

        public TaggingState getState() {
            this.isRead = true;
            return TaggingState.DISABLED;
        }

        @Deprecated
        public void setState(TaggingState taggingState) {
            Utils.checkNotNull(taggingState, "state");
            Utils.checkState(!this.isRead, "State was already read, cannot set state.");
        }
    }

    private static final class NoopTagger extends Tagger {
        static final Tagger INSTANCE = new NoopTagger();

        private NoopTagger() {
        }

        public TagContext empty() {
            return NoopTags.getNoopTagContext();
        }

        public TagContext getCurrentTagContext() {
            return NoopTags.getNoopTagContext();
        }

        public TagContextBuilder emptyBuilder() {
            return NoopTags.getNoopTagContextBuilder();
        }

        public TagContextBuilder toBuilder(TagContext tagContext) {
            Utils.checkNotNull(tagContext, ContentParser.TAGS);
            return NoopTags.getNoopTagContextBuilder();
        }

        public TagContextBuilder currentBuilder() {
            return NoopTags.getNoopTagContextBuilder();
        }

        public Scope withTagContext(TagContext tagContext) {
            Utils.checkNotNull(tagContext, ContentParser.TAGS);
            return NoopScope.getInstance();
        }
    }

    private static final class NoopTagContextBuilder extends TagContextBuilder {
        static final TagContextBuilder INSTANCE = new NoopTagContextBuilder();

        private NoopTagContextBuilder() {
        }

        public TagContextBuilder put(TagKey tagKey, TagValue tagValue) {
            Utils.checkNotNull(tagKey, "key");
            Utils.checkNotNull(tagValue, "value");
            return this;
        }

        public TagContextBuilder put(TagKey tagKey, TagValue tagValue, TagMetadata tagMetadata) {
            Utils.checkNotNull(tagKey, "key");
            Utils.checkNotNull(tagValue, "value");
            Utils.checkNotNull(tagMetadata, "tagMetadata");
            return this;
        }

        public TagContextBuilder remove(TagKey tagKey) {
            Utils.checkNotNull(tagKey, "key");
            return this;
        }

        public TagContext build() {
            return NoopTags.getNoopTagContext();
        }

        public Scope buildScoped() {
            return NoopScope.getInstance();
        }
    }

    private static final class NoopTagContext extends TagContext {
        static final TagContext INSTANCE = new NoopTagContext();

        private NoopTagContext() {
        }

        /* access modifiers changed from: protected */
        public Iterator<Tag> getIterator() {
            return Collections.emptySet().iterator();
        }
    }

    private static final class NoopTagPropagationComponent extends TagPropagationComponent {
        static final TagPropagationComponent INSTANCE = new NoopTagPropagationComponent();

        private NoopTagPropagationComponent() {
        }

        public TagContextBinarySerializer getBinarySerializer() {
            return NoopTags.getNoopTagContextBinarySerializer();
        }

        public TagContextTextFormat getCorrelationContextFormat() {
            return NoopTags.getNoopTagContextTextSerializer();
        }
    }

    private static final class NoopTagContextBinarySerializer extends TagContextBinarySerializer {
        static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
        static final TagContextBinarySerializer INSTANCE = new NoopTagContextBinarySerializer();

        private NoopTagContextBinarySerializer() {
        }

        public byte[] toByteArray(TagContext tagContext) {
            Utils.checkNotNull(tagContext, ContentParser.TAGS);
            return EMPTY_BYTE_ARRAY;
        }

        public TagContext fromByteArray(byte[] bArr) {
            Utils.checkNotNull(bArr, "bytes");
            return NoopTags.getNoopTagContext();
        }
    }

    private static final class NoopTagContextTextFormat extends TagContextTextFormat {
        static final NoopTagContextTextFormat INSTANCE = new NoopTagContextTextFormat();

        private NoopTagContextTextFormat() {
        }

        public List<String> fields() {
            return Collections.emptyList();
        }

        public <C> void inject(TagContext tagContext, C c, TagContextTextFormat.Setter<C> setter) throws TagContextSerializationException {
            Utils.checkNotNull(tagContext, "tagContext");
            Utils.checkNotNull(c, "carrier");
            Utils.checkNotNull(setter, "setter");
        }

        public <C> TagContext extract(C c, TagContextTextFormat.Getter<C> getter) throws TagContextDeserializationException {
            Utils.checkNotNull(c, "carrier");
            Utils.checkNotNull(getter, "getter");
            return NoopTags.getNoopTagContext();
        }
    }
}
