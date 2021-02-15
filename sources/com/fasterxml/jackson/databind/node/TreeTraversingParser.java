package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.node.NodeCursor;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TreeTraversingParser extends ParserMinimalBase {
    protected boolean _closed;
    protected JsonToken _nextToken;
    protected NodeCursor _nodeCursor;
    protected ObjectCodec _objectCodec;
    protected boolean _startContainer;

    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    public boolean hasTextCharacters() {
        return false;
    }

    public TreeTraversingParser(JsonNode jsonNode) {
        this(jsonNode, (ObjectCodec) null);
    }

    public TreeTraversingParser(JsonNode jsonNode, ObjectCodec objectCodec) {
        super(0);
        this._objectCodec = objectCodec;
        if (jsonNode.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            this._nodeCursor = new NodeCursor.ArrayCursor(jsonNode, (NodeCursor) null);
        } else if (jsonNode.isObject()) {
            this._nextToken = JsonToken.START_OBJECT;
            this._nodeCursor = new NodeCursor.ObjectCursor(jsonNode, (NodeCursor) null);
        } else {
            this._nodeCursor = new NodeCursor.RootCursor(jsonNode, (NodeCursor) null);
        }
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            this._nodeCursor = null;
            this._currToken = null;
        }
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken jsonToken = this._nextToken;
        if (jsonToken != null) {
            this._currToken = jsonToken;
            this._nextToken = null;
            return this._currToken;
        } else if (this._startContainer) {
            this._startContainer = false;
            if (!this._nodeCursor.currentHasChildren()) {
                this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                return this._currToken;
            }
            NodeCursor iterateChildren = this._nodeCursor.iterateChildren();
            this._nodeCursor = iterateChildren;
            this._currToken = iterateChildren.nextToken();
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        } else {
            NodeCursor nodeCursor = this._nodeCursor;
            if (nodeCursor == null) {
                this._closed = true;
                return null;
            }
            this._currToken = nodeCursor.nextToken();
            if (this._currToken != null) {
                if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                    this._startContainer = true;
                }
                return this._currToken;
            }
            this._currToken = this._nodeCursor.endToken();
            this._nodeCursor = this._nodeCursor.getParent();
            return this._currToken;
        }
    }

    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }

    public boolean isClosed() {
        return this._closed;
    }

    public String getCurrentName() {
        NodeCursor nodeCursor = this._nodeCursor;
        if (nodeCursor == null) {
            return null;
        }
        return nodeCursor.getCurrentName();
    }

    public void overrideCurrentName(String str) {
        NodeCursor nodeCursor = this._nodeCursor;
        if (nodeCursor != null) {
            nodeCursor.overrideCurrentName(str);
        }
    }

    public JsonStreamContext getParsingContext() {
        return this._nodeCursor;
    }

    public JsonLocation getTokenLocation() {
        return JsonLocation.NA;
    }

    public JsonLocation getCurrentLocation() {
        return JsonLocation.NA;
    }

    public String getText() {
        JsonNode currentNode;
        if (this._closed) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()];
        if (i == 1) {
            return this._nodeCursor.getCurrentName();
        }
        if (i == 2) {
            return currentNode().textValue();
        }
        if (i == 3 || i == 4) {
            return String.valueOf(currentNode().numberValue());
        }
        if (i == 5 && (currentNode = currentNode()) != null && currentNode.isBinary()) {
            return currentNode.asText();
        }
        if (this._currToken == null) {
            return null;
        }
        return this._currToken.asString();
    }

    /* renamed from: com.fasterxml.jackson.databind.node.TreeTraversingParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.fasterxml.jackson.core.JsonToken[] r0 = com.fasterxml.jackson.core.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$core$JsonToken = r0
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x003e }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_EMBEDDED_OBJECT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.TreeTraversingParser.AnonymousClass1.<clinit>():void");
        }
    }

    public char[] getTextCharacters() throws IOException, JsonParseException {
        return getText().toCharArray();
    }

    public int getTextLength() throws IOException, JsonParseException {
        return getText().length();
    }

    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        JsonNode currentNumericNode = currentNumericNode();
        if (currentNumericNode == null) {
            return null;
        }
        return currentNumericNode.numberType();
    }

    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        return currentNumericNode().bigIntegerValue();
    }

    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        return currentNumericNode().decimalValue();
    }

    public double getDoubleValue() throws IOException, JsonParseException {
        return currentNumericNode().doubleValue();
    }

    public float getFloatValue() throws IOException, JsonParseException {
        return (float) currentNumericNode().doubleValue();
    }

    public long getLongValue() throws IOException, JsonParseException {
        return currentNumericNode().longValue();
    }

    public int getIntValue() throws IOException, JsonParseException {
        return currentNumericNode().intValue();
    }

    public Number getNumberValue() throws IOException, JsonParseException {
        return currentNumericNode().numberValue();
    }

    public Object getEmbeddedObject() {
        JsonNode currentNode;
        if (this._closed || (currentNode = currentNode()) == null) {
            return null;
        }
        if (currentNode.isPojo()) {
            return ((POJONode) currentNode).getPojo();
        }
        if (currentNode.isBinary()) {
            return ((BinaryNode) currentNode).binaryValue();
        }
        return null;
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        JsonNode currentNode = currentNode();
        if (currentNode == null) {
            return null;
        }
        byte[] binaryValue = currentNode.binaryValue();
        if (binaryValue != null) {
            return binaryValue;
        }
        if (!currentNode.isPojo()) {
            return null;
        }
        Object pojo = ((POJONode) currentNode).getPojo();
        if (pojo instanceof byte[]) {
            return (byte[]) pojo;
        }
        return null;
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException, JsonParseException {
        byte[] binaryValue = getBinaryValue(base64Variant);
        if (binaryValue == null) {
            return 0;
        }
        outputStream.write(binaryValue, 0, binaryValue.length);
        return binaryValue.length;
    }

    /* access modifiers changed from: protected */
    public JsonNode currentNode() {
        NodeCursor nodeCursor;
        if (this._closed || (nodeCursor = this._nodeCursor) == null) {
            return null;
        }
        return nodeCursor.currentNode();
    }

    /* access modifiers changed from: protected */
    public JsonNode currentNumericNode() throws JsonParseException {
        JsonToken jsonToken;
        JsonNode currentNode = currentNode();
        if (currentNode != null && currentNode.isNumber()) {
            return currentNode;
        }
        if (currentNode == null) {
            jsonToken = null;
        } else {
            jsonToken = currentNode.asToken();
        }
        throw _constructError("Current token (" + jsonToken + ") not numeric, can not use numeric value accessors");
    }

    /* access modifiers changed from: protected */
    public void _handleEOF() throws JsonParseException {
        _throwInternal();
    }
}
