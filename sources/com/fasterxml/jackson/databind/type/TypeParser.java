package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TypeParser implements Serializable {
    private static final long serialVersionUID = 1;
    protected final TypeFactory _factory;

    public TypeParser(TypeFactory typeFactory) {
        this._factory = typeFactory;
    }

    public TypeParser withFactory(TypeFactory typeFactory) {
        return typeFactory == this._factory ? this : new TypeParser(typeFactory);
    }

    public JavaType parse(String str) throws IllegalArgumentException {
        MyTokenizer myTokenizer = new MyTokenizer(str.trim());
        JavaType parseType = parseType(myTokenizer);
        if (!myTokenizer.hasMoreTokens()) {
            return parseType;
        }
        throw _problem(myTokenizer, "Unexpected tokens after complete type");
    }

    /* access modifiers changed from: protected */
    public JavaType parseType(MyTokenizer myTokenizer) throws IllegalArgumentException {
        if (myTokenizer.hasMoreTokens()) {
            Class<?> findClass = findClass(myTokenizer.nextToken(), myTokenizer);
            if (myTokenizer.hasMoreTokens()) {
                String nextToken = myTokenizer.nextToken();
                if (HtmlObject.HtmlMarkUp.OPEN_BRACKER.equals(nextToken)) {
                    return this._factory._fromClass((ClassStack) null, findClass, TypeBindings.create(findClass, parseTypes(myTokenizer)));
                }
                myTokenizer.pushBack(nextToken);
            }
            return this._factory._fromClass((ClassStack) null, findClass, (TypeBindings) null);
        }
        throw _problem(myTokenizer, "Unexpected end-of-string");
    }

    /* access modifiers changed from: protected */
    public List<JavaType> parseTypes(MyTokenizer myTokenizer) throws IllegalArgumentException {
        ArrayList arrayList = new ArrayList();
        while (myTokenizer.hasMoreTokens()) {
            arrayList.add(parseType(myTokenizer));
            if (!myTokenizer.hasMoreTokens()) {
                break;
            }
            String nextToken = myTokenizer.nextToken();
            if (HtmlObject.HtmlMarkUp.CLOSE_BRACKER.equals(nextToken)) {
                return arrayList;
            }
            if (!",".equals(nextToken)) {
                throw _problem(myTokenizer, "Unexpected token '" + nextToken + "', expected ',' or '>')");
            }
        }
        throw _problem(myTokenizer, "Unexpected end-of-string");
    }

    /* access modifiers changed from: protected */
    public Class<?> findClass(String str, MyTokenizer myTokenizer) {
        try {
            return this._factory.findClass(str);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            throw _problem(myTokenizer, "Can not locate class '" + str + "', problem: " + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public IllegalArgumentException _problem(MyTokenizer myTokenizer, String str) {
        return new IllegalArgumentException("Failed to parse type '" + myTokenizer.getAllInput() + "' (remaining: '" + myTokenizer.getRemainingInput() + "'): " + str);
    }

    static final class MyTokenizer extends StringTokenizer {
        protected int _index;
        protected final String _input;
        protected String _pushbackToken;

        public MyTokenizer(String str) {
            super(str, "<,>", true);
            this._input = str;
        }

        public boolean hasMoreTokens() {
            return this._pushbackToken != null || super.hasMoreTokens();
        }

        public String nextToken() {
            String str = this._pushbackToken;
            if (str != null) {
                this._pushbackToken = null;
            } else {
                str = super.nextToken();
            }
            this._index += str.length();
            return str;
        }

        public void pushBack(String str) {
            this._pushbackToken = str;
            this._index -= str.length();
        }

        public String getAllInput() {
            return this._input;
        }

        public String getUsedInput() {
            return this._input.substring(0, this._index);
        }

        public String getRemainingInput() {
            return this._input.substring(this._index);
        }
    }
}
