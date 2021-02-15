package com.github.jasminb.jsonapi;

import java.util.ArrayList;
import java.util.List;

public class SerializationSettings {
    /* access modifiers changed from: private */
    public List<String> relationshipExludes;
    /* access modifiers changed from: private */
    public List<String> relationshipIncludes;
    /* access modifiers changed from: private */
    public Boolean serializeLinks;
    /* access modifiers changed from: private */
    public Boolean serializeMeta;

    private SerializationSettings() {
    }

    public boolean isRelationshipIncluded(String str) {
        return this.relationshipIncludes.contains(str);
    }

    public boolean isRelationshipExcluded(String str) {
        return this.relationshipExludes.contains(str);
    }

    public Boolean serializeMeta() {
        return this.serializeMeta;
    }

    public Boolean serializeLinks() {
        return this.serializeLinks;
    }

    public static class Builder {
        private List<String> relationshipExludes = new ArrayList();
        private List<String> relationshipIncludes = new ArrayList();
        private Boolean serializeLinks;
        private Boolean serializeMeta;

        public Builder includeRelationship(String str) {
            this.relationshipIncludes.add(str);
            return this;
        }

        public Builder excludedRelationships(String str) {
            this.relationshipExludes.add(str);
            return this;
        }

        public Builder serializeMeta(Boolean bool) {
            this.serializeMeta = bool;
            return this;
        }

        public Builder serializeLinks(Boolean bool) {
            this.serializeLinks = bool;
            return this;
        }

        public SerializationSettings build() {
            SerializationSettings serializationSettings = new SerializationSettings();
            List unused = serializationSettings.relationshipIncludes = new ArrayList(this.relationshipIncludes);
            List unused2 = serializationSettings.relationshipExludes = new ArrayList(this.relationshipExludes);
            Boolean unused3 = serializationSettings.serializeLinks = this.serializeLinks;
            Boolean unused4 = serializationSettings.serializeMeta = this.serializeMeta;
            return serializationSettings;
        }
    }
}
