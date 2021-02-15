package org.jaxen;

import com.facebook.internal.ServerProtocol;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import org.jaxen.function.BooleanFunction;
import org.jaxen.function.CeilingFunction;
import org.jaxen.function.ConcatFunction;
import org.jaxen.function.ContainsFunction;
import org.jaxen.function.CountFunction;
import org.jaxen.function.FalseFunction;
import org.jaxen.function.FloorFunction;
import org.jaxen.function.IdFunction;
import org.jaxen.function.LangFunction;
import org.jaxen.function.LastFunction;
import org.jaxen.function.LocalNameFunction;
import org.jaxen.function.NameFunction;
import org.jaxen.function.NamespaceUriFunction;
import org.jaxen.function.NormalizeSpaceFunction;
import org.jaxen.function.NotFunction;
import org.jaxen.function.NumberFunction;
import org.jaxen.function.PositionFunction;
import org.jaxen.function.RoundFunction;
import org.jaxen.function.StartsWithFunction;
import org.jaxen.function.StringFunction;
import org.jaxen.function.StringLengthFunction;
import org.jaxen.function.SubstringAfterFunction;
import org.jaxen.function.SubstringBeforeFunction;
import org.jaxen.function.SubstringFunction;
import org.jaxen.function.SumFunction;
import org.jaxen.function.TranslateFunction;
import org.jaxen.function.TrueFunction;
import org.jaxen.function.ext.EndsWithFunction;
import org.jaxen.function.ext.EvaluateFunction;
import org.jaxen.function.ext.LowerFunction;
import org.jaxen.function.ext.UpperFunction;
import org.jaxen.function.xslt.DocumentFunction;

public class XPathFunctionContext extends SimpleFunctionContext {
    private static XPathFunctionContext instance = new XPathFunctionContext();

    public static FunctionContext getInstance() {
        return instance;
    }

    public XPathFunctionContext() {
        this(true);
    }

    public XPathFunctionContext(boolean z) {
        registerXPathFunctions();
        if (z) {
            registerXSLTFunctions();
            registerExtensionFunctions();
        }
    }

    private void registerXPathFunctions() {
        registerFunction((String) null, "boolean", new BooleanFunction());
        registerFunction((String) null, "ceiling", new CeilingFunction());
        registerFunction((String) null, "concat", new ConcatFunction());
        registerFunction((String) null, "contains", new ContainsFunction());
        registerFunction((String) null, "count", new CountFunction());
        registerFunction((String) null, "false", new FalseFunction());
        registerFunction((String) null, "floor", new FloorFunction());
        registerFunction((String) null, "id", new IdFunction());
        registerFunction((String) null, "lang", new LangFunction());
        registerFunction((String) null, JSONAPISpecConstants.LAST, new LastFunction());
        registerFunction((String) null, "local-name", new LocalNameFunction());
        registerFunction((String) null, "name", new NameFunction());
        registerFunction((String) null, "namespace-uri", new NamespaceUriFunction());
        registerFunction((String) null, "normalize-space", new NormalizeSpaceFunction());
        registerFunction((String) null, "not", new NotFunction());
        registerFunction((String) null, "number", new NumberFunction());
        registerFunction((String) null, "position", new PositionFunction());
        registerFunction((String) null, "round", new RoundFunction());
        registerFunction((String) null, "starts-with", new StartsWithFunction());
        registerFunction((String) null, "string", new StringFunction());
        registerFunction((String) null, "string-length", new StringLengthFunction());
        registerFunction((String) null, "substring-after", new SubstringAfterFunction());
        registerFunction((String) null, "substring-before", new SubstringBeforeFunction());
        registerFunction((String) null, "substring", new SubstringFunction());
        registerFunction((String) null, "sum", new SumFunction());
        registerFunction((String) null, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, new TrueFunction());
        registerFunction((String) null, "translate", new TranslateFunction());
    }

    private void registerXSLTFunctions() {
        registerFunction((String) null, "document", new DocumentFunction());
    }

    private void registerExtensionFunctions() {
        registerFunction((String) null, "evaluate", new EvaluateFunction());
        registerFunction((String) null, "lower-case", new LowerFunction());
        registerFunction((String) null, "upper-case", new UpperFunction());
        registerFunction((String) null, "ends-with", new EndsWithFunction());
    }
}
