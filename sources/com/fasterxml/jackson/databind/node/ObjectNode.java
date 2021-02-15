package com.fasterxml.jackson.databind.node;

import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ObjectNode extends ContainerNode<ObjectNode> {
    protected final Map<String, JsonNode> _children;

    public JsonNode get(int i) {
        return null;
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new LinkedHashMap();
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory, Map<String, JsonNode> map) {
        super(jsonNodeFactory);
        this._children = map;
    }

    /* access modifiers changed from: protected */
    public JsonNode _at(JsonPointer jsonPointer) {
        return get(jsonPointer.getMatchingProperty());
    }

    public ObjectNode deepCopy() {
        ObjectNode objectNode = new ObjectNode(this._nodeFactory);
        for (Map.Entry next : this._children.entrySet()) {
            objectNode._children.put(next.getKey(), ((JsonNode) next.getValue()).deepCopy());
        }
        return objectNode;
    }

    public boolean isEmpty(SerializerProvider serializerProvider) {
        return this._children.isEmpty();
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.OBJECT;
    }

    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    public int size() {
        return this._children.size();
    }

    public Iterator<JsonNode> elements() {
        return this._children.values().iterator();
    }

    public JsonNode get(String str) {
        return this._children.get(str);
    }

    public Iterator<String> fieldNames() {
        return this._children.keySet().iterator();
    }

    public JsonNode path(int i) {
        return MissingNode.getInstance();
    }

    public JsonNode path(String str) {
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode != null) {
            return jsonNode;
        }
        return MissingNode.getInstance();
    }

    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return this._children.entrySet().iterator();
    }

    public ObjectNode with(String str) {
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode == null) {
            ObjectNode objectNode = objectNode();
            this._children.put(str, objectNode);
            return objectNode;
        } else if (jsonNode instanceof ObjectNode) {
            return (ObjectNode) jsonNode;
        } else {
            throw new UnsupportedOperationException("Property '" + str + "' has value that is not of type ObjectNode (but " + jsonNode.getClass().getName() + ")");
        }
    }

    public ArrayNode withArray(String str) {
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode == null) {
            ArrayNode arrayNode = arrayNode();
            this._children.put(str, arrayNode);
            return arrayNode;
        } else if (jsonNode instanceof ArrayNode) {
            return (ArrayNode) jsonNode;
        } else {
            throw new UnsupportedOperationException("Property '" + str + "' has value that is not of type ArrayNode (but " + jsonNode.getClass().getName() + ")");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.util.Comparator<com.fasterxml.jackson.databind.JsonNode> r5, com.fasterxml.jackson.databind.JsonNode r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.fasterxml.jackson.databind.node.ObjectNode
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.fasterxml.jackson.databind.node.ObjectNode r6 = (com.fasterxml.jackson.databind.node.ObjectNode) r6
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r0 = r4._children
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r6 = r6._children
            int r2 = r0.size()
            int r3 = r6.size()
            if (r3 == r2) goto L_0x0017
            return r1
        L_0x0017:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x001f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0044
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r3 = r6.get(r3)
            com.fasterxml.jackson.databind.JsonNode r3 = (com.fasterxml.jackson.databind.JsonNode) r3
            if (r3 == 0) goto L_0x0043
            java.lang.Object r2 = r2.getValue()
            com.fasterxml.jackson.databind.JsonNode r2 = (com.fasterxml.jackson.databind.JsonNode) r2
            boolean r2 = r2.equals(r5, r3)
            if (r2 != 0) goto L_0x001f
        L_0x0043:
            return r1
        L_0x0044:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.ObjectNode.equals(java.util.Comparator, com.fasterxml.jackson.databind.JsonNode):boolean");
    }

    public JsonNode findValue(String str) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                return (JsonNode) next.getValue();
            }
            JsonNode findValue = ((JsonNode) next.getValue()).findValue(str);
            if (findValue != null) {
                return findValue;
            }
        }
        return null;
    }

    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(next.getValue());
            } else {
                list = ((JsonNode) next.getValue()).findValues(str, list);
            }
        }
        return list;
    }

    public List<String> findValuesAsText(String str, List<String> list) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(((JsonNode) next.getValue()).asText());
            } else {
                list = ((JsonNode) next.getValue()).findValuesAsText(str, list);
            }
        }
        return list;
    }

    public ObjectNode findParent(String str) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                return this;
            }
            JsonNode findParent = ((JsonNode) next.getValue()).findParent(str);
            if (findParent != null) {
                return (ObjectNode) findParent;
            }
        }
        return null;
    }

    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(this);
            } else {
                list = ((JsonNode) next.getValue()).findParents(str, list);
            }
        }
        return list;
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        for (Map.Entry next : this._children.entrySet()) {
            jsonGenerator.writeFieldName((String) next.getKey());
            ((BaseJsonNode) next.getValue()).serialize(jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        typeSerializer.writeTypePrefixForObject(this, jsonGenerator);
        for (Map.Entry next : this._children.entrySet()) {
            jsonGenerator.writeFieldName((String) next.getKey());
            ((BaseJsonNode) next.getValue()).serialize(jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(this, jsonGenerator);
    }

    public JsonNode set(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        this._children.put(str, jsonNode);
        return this;
    }

    public JsonNode setAll(Map<String, ? extends JsonNode> map) {
        for (Map.Entry next : map.entrySet()) {
            Object obj = (JsonNode) next.getValue();
            if (obj == null) {
                obj = nullNode();
            }
            this._children.put(next.getKey(), obj);
        }
        return this;
    }

    public JsonNode setAll(ObjectNode objectNode) {
        this._children.putAll(objectNode._children);
        return this;
    }

    public JsonNode replace(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return this._children.put(str, jsonNode);
    }

    public JsonNode without(String str) {
        this._children.remove(str);
        return this;
    }

    public ObjectNode without(Collection<String> collection) {
        this._children.keySet().removeAll(collection);
        return this;
    }

    @Deprecated
    public JsonNode put(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return this._children.put(str, jsonNode);
    }

    public JsonNode remove(String str) {
        return this._children.remove(str);
    }

    public ObjectNode remove(Collection<String> collection) {
        this._children.keySet().removeAll(collection);
        return this;
    }

    public ObjectNode removeAll() {
        this._children.clear();
        return this;
    }

    @Deprecated
    public JsonNode putAll(Map<String, ? extends JsonNode> map) {
        return setAll(map);
    }

    @Deprecated
    public JsonNode putAll(ObjectNode objectNode) {
        return setAll(objectNode);
    }

    public ObjectNode retain(Collection<String> collection) {
        this._children.keySet().retainAll(collection);
        return this;
    }

    public ObjectNode retain(String... strArr) {
        return retain((Collection<String>) Arrays.asList(strArr));
    }

    public ArrayNode putArray(String str) {
        ArrayNode arrayNode = arrayNode();
        _put(str, arrayNode);
        return arrayNode;
    }

    public ObjectNode putObject(String str) {
        ObjectNode objectNode = objectNode();
        _put(str, objectNode);
        return objectNode;
    }

    public ObjectNode putPOJO(String str, Object obj) {
        return _put(str, pojoNode(obj));
    }

    public ObjectNode putRawValue(String str, RawValue rawValue) {
        return _put(str, rawValueNode(rawValue));
    }

    public ObjectNode putNull(String str) {
        this._children.put(str, nullNode());
        return this;
    }

    public ObjectNode put(String str, short s) {
        return _put(str, numberNode(s));
    }

    public ObjectNode put(String str, Short sh) {
        return _put(str, sh == null ? nullNode() : numberNode(sh.shortValue()));
    }

    public ObjectNode put(String str, int i) {
        return _put(str, numberNode(i));
    }

    public ObjectNode put(String str, Integer num) {
        return _put(str, num == null ? nullNode() : numberNode(num.intValue()));
    }

    public ObjectNode put(String str, long j) {
        return _put(str, numberNode(j));
    }

    public ObjectNode put(String str, Long l) {
        return _put(str, l == null ? nullNode() : numberNode(l.longValue()));
    }

    public ObjectNode put(String str, float f) {
        return _put(str, numberNode(f));
    }

    public ObjectNode put(String str, Float f) {
        return _put(str, f == null ? nullNode() : numberNode(f.floatValue()));
    }

    public ObjectNode put(String str, double d) {
        return _put(str, numberNode(d));
    }

    public ObjectNode put(String str, Double d) {
        return _put(str, d == null ? nullNode() : numberNode(d.doubleValue()));
    }

    public ObjectNode put(String str, BigDecimal bigDecimal) {
        return _put(str, bigDecimal == null ? nullNode() : numberNode(bigDecimal));
    }

    public ObjectNode put(String str, String str2) {
        return _put(str, str2 == null ? nullNode() : textNode(str2));
    }

    public ObjectNode put(String str, boolean z) {
        return _put(str, booleanNode(z));
    }

    public ObjectNode put(String str, Boolean bool) {
        return _put(str, bool == null ? nullNode() : booleanNode(bool.booleanValue()));
    }

    public ObjectNode put(String str, byte[] bArr) {
        return _put(str, bArr == null ? nullNode() : binaryNode(bArr));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ObjectNode)) {
            return _childrenEqual((ObjectNode) obj);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean _childrenEqual(ObjectNode objectNode) {
        return this._children.equals(objectNode._children);
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() << 4) + 32);
        sb.append("{");
        int i = 0;
        for (Map.Entry next : this._children.entrySet()) {
            if (i > 0) {
                sb.append(",");
            }
            i++;
            TextNode.appendQuoted(sb, (String) next.getKey());
            sb.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
            sb.append(((JsonNode) next.getValue()).toString());
        }
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public ObjectNode _put(String str, JsonNode jsonNode) {
        this._children.put(str, jsonNode);
        return this;
    }
}
