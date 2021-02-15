package org.mozilla.javascript;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.Comment;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.EmptyStatement;
import org.mozilla.javascript.ast.ErrorNode;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.IdeErrorReporter;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.KeywordLiteral;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.LabeledStatement;
import org.mozilla.javascript.ast.LetNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NewExpression;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.ObjectLiteral;
import org.mozilla.javascript.ast.ObjectProperty;
import org.mozilla.javascript.ast.ParenthesizedExpression;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.RegExpLiteral;
import org.mozilla.javascript.ast.ReturnStatement;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.ast.SwitchStatement;
import org.mozilla.javascript.ast.Symbol;
import org.mozilla.javascript.ast.ThrowStatement;
import org.mozilla.javascript.ast.TryStatement;
import org.mozilla.javascript.ast.UnaryExpression;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;
import org.mozilla.javascript.ast.WhileLoop;
import org.mozilla.javascript.ast.WithStatement;
import org.mozilla.javascript.ast.XmlElemRef;
import org.mozilla.javascript.ast.XmlExpression;
import org.mozilla.javascript.ast.XmlLiteral;
import org.mozilla.javascript.ast.XmlMemberGet;
import org.mozilla.javascript.ast.XmlPropRef;
import org.mozilla.javascript.ast.XmlRef;
import org.mozilla.javascript.ast.XmlString;
import org.mozilla.javascript.ast.Yield;

public class Parser {
    public static final int ARGC_LIMIT = 65536;
    static final int CLEAR_TI_MASK = 65535;
    private static final int GET_ENTRY = 2;
    private static final int PROP_ENTRY = 1;
    private static final int SET_ENTRY = 4;
    static final int TI_AFTER_EOL = 65536;
    static final int TI_CHECK_LABEL = 131072;
    boolean calledByCompileFunction;
    CompilerEnvirons compilerEnv;
    private int currentFlaggedToken;
    private Comment currentJsDocComment;
    private LabeledStatement currentLabel;
    Scope currentScope;
    ScriptNode currentScriptOrFn;
    private int currentToken;
    /* access modifiers changed from: private */
    public int endFlags;
    private IdeErrorReporter errorCollector;
    private ErrorReporter errorReporter;
    private boolean inDestructuringAssignment;
    /* access modifiers changed from: private */
    public boolean inForInit;
    protected boolean inUseStrictDirective;
    /* access modifiers changed from: private */
    public Map<String, LabeledStatement> labelSet;
    /* access modifiers changed from: private */
    public List<Jump> loopAndSwitchSet;
    /* access modifiers changed from: private */
    public List<Loop> loopSet;
    protected int nestingOfFunction;
    private boolean parseFinished;
    private int prevNameTokenLineno;
    private int prevNameTokenStart;
    private String prevNameTokenString;
    private List<Comment> scannedComments;
    private char[] sourceChars;
    private String sourceURI;
    private int syntaxErrorCount;
    private TokenStream ts;

    private static final boolean nowAllSet(int i, int i2, int i3) {
        return (i & i3) != i3 && (i2 & i3) == i3;
    }

    private static class ParserException extends RuntimeException {
        static final long serialVersionUID = 5882582646773765630L;

        private ParserException() {
        }
    }

    public Parser() {
        this(new CompilerEnvirons());
    }

    public Parser(CompilerEnvirons compilerEnvirons) {
        this(compilerEnvirons, compilerEnvirons.getErrorReporter());
    }

    public Parser(CompilerEnvirons compilerEnvirons, ErrorReporter errorReporter2) {
        this.currentFlaggedToken = 0;
        this.prevNameTokenString = "";
        this.compilerEnv = compilerEnvirons;
        this.errorReporter = errorReporter2;
        if (errorReporter2 instanceof IdeErrorReporter) {
            this.errorCollector = (IdeErrorReporter) errorReporter2;
        }
    }

    /* access modifiers changed from: package-private */
    public void addStrictWarning(String str, String str2) {
        int i;
        TokenStream tokenStream = this.ts;
        int i2 = -1;
        if (tokenStream != null) {
            i2 = tokenStream.tokenBeg;
            i = this.ts.tokenEnd - this.ts.tokenBeg;
        } else {
            i = -1;
        }
        addStrictWarning(str, str2, i2, i);
    }

    /* access modifiers changed from: package-private */
    public void addStrictWarning(String str, String str2, int i, int i2) {
        if (this.compilerEnv.isStrictMode()) {
            addWarning(str, str2, i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void addWarning(String str, String str2) {
        int i;
        TokenStream tokenStream = this.ts;
        int i2 = -1;
        if (tokenStream != null) {
            i2 = tokenStream.tokenBeg;
            i = this.ts.tokenEnd - this.ts.tokenBeg;
        } else {
            i = -1;
        }
        addWarning(str, str2, i2, i);
    }

    /* access modifiers changed from: package-private */
    public void addWarning(String str, int i, int i2) {
        addWarning(str, (String) null, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void addWarning(String str, String str2, int i, int i2) {
        String lookupMessage = lookupMessage(str, str2);
        if (this.compilerEnv.reportWarningAsError()) {
            addError(str, str2, i, i2);
            return;
        }
        IdeErrorReporter ideErrorReporter = this.errorCollector;
        if (ideErrorReporter != null) {
            ideErrorReporter.warning(lookupMessage, this.sourceURI, i, i2);
        } else {
            this.errorReporter.warning(lookupMessage, this.sourceURI, this.ts.getLineno(), this.ts.getLine(), this.ts.getOffset());
        }
    }

    /* access modifiers changed from: package-private */
    public void addError(String str) {
        addError(str, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    /* access modifiers changed from: package-private */
    public void addError(String str, int i, int i2) {
        addError(str, (String) null, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void addError(String str, String str2) {
        addError(str, str2, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    /* access modifiers changed from: package-private */
    public void addError(String str, String str2, int i, int i2) {
        int i3;
        String str3;
        int i4;
        this.syntaxErrorCount++;
        String lookupMessage = lookupMessage(str, str2);
        IdeErrorReporter ideErrorReporter = this.errorCollector;
        if (ideErrorReporter != null) {
            ideErrorReporter.error(lookupMessage, this.sourceURI, i, i2);
            return;
        }
        TokenStream tokenStream = this.ts;
        if (tokenStream != null) {
            int lineno = tokenStream.getLineno();
            str3 = this.ts.getLine();
            i3 = this.ts.getOffset();
            i4 = lineno;
        } else {
            str3 = "";
            i4 = 1;
            i3 = 1;
        }
        this.errorReporter.error(lookupMessage, this.sourceURI, i4, str3, i3);
    }

    /* access modifiers changed from: package-private */
    public String lookupMessage(String str) {
        return lookupMessage(str, (String) null);
    }

    /* access modifiers changed from: package-private */
    public String lookupMessage(String str, String str2) {
        return str2 == null ? ScriptRuntime.getMessage0(str) : ScriptRuntime.getMessage1(str, str2);
    }

    /* access modifiers changed from: package-private */
    public void reportError(String str) {
        reportError(str, (String) null);
    }

    /* access modifiers changed from: package-private */
    public void reportError(String str, String str2) {
        TokenStream tokenStream = this.ts;
        if (tokenStream == null) {
            reportError(str, str2, 1, 1);
        } else {
            reportError(str, str2, tokenStream.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
        }
    }

    /* access modifiers changed from: package-private */
    public void reportError(String str, int i, int i2) {
        reportError(str, (String) null, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void reportError(String str, String str2, int i, int i2) {
        addError(str, i, i2);
        if (!this.compilerEnv.recoverFromErrors()) {
            throw new ParserException();
        }
    }

    private int getNodeEnd(AstNode astNode) {
        return astNode.getPosition() + astNode.getLength();
    }

    private void recordComment(int i, String str) {
        if (this.scannedComments == null) {
            this.scannedComments = new ArrayList();
        }
        Comment comment = new Comment(this.ts.tokenBeg, this.ts.getTokenLength(), this.ts.commentType, str);
        if (this.ts.commentType == Token.CommentType.JSDOC && this.compilerEnv.isRecordingLocalJsDocComments()) {
            this.currentJsDocComment = comment;
        }
        comment.setLineno(i);
        this.scannedComments.add(comment);
    }

    private Comment getAndResetJsDoc() {
        Comment comment = this.currentJsDocComment;
        this.currentJsDocComment = null;
        return comment;
    }

    private int getNumberOfEols(String str) {
        int i = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) == 10) {
                i++;
            }
        }
        return i;
    }

    private int peekToken() throws IOException {
        if (this.currentFlaggedToken != 0) {
            return this.currentToken;
        }
        int lineno = this.ts.getLineno();
        int token = this.ts.getToken();
        int i = 0;
        boolean z = false;
        while (true) {
            if (token != 1 && token != 161) {
                break;
            }
            if (token == 1) {
                lineno++;
                z = true;
            } else if (this.compilerEnv.isRecordingComments()) {
                String andResetCurrentComment = this.ts.getAndResetCurrentComment();
                recordComment(lineno, andResetCurrentComment);
                lineno += getNumberOfEols(andResetCurrentComment);
            }
            token = this.ts.getToken();
        }
        this.currentToken = token;
        if (z) {
            i = 65536;
        }
        this.currentFlaggedToken = token | i;
        return this.currentToken;
    }

    private int peekFlaggedToken() throws IOException {
        peekToken();
        return this.currentFlaggedToken;
    }

    private void consumeToken() {
        this.currentFlaggedToken = 0;
    }

    private int nextToken() throws IOException {
        int peekToken = peekToken();
        consumeToken();
        return peekToken;
    }

    private int nextFlaggedToken() throws IOException {
        peekToken();
        int i = this.currentFlaggedToken;
        consumeToken();
        return i;
    }

    private boolean matchToken(int i) throws IOException {
        if (peekToken() != i) {
            return false;
        }
        consumeToken();
        return true;
    }

    private int peekTokenOrEOL() throws IOException {
        int peekToken = peekToken();
        if ((this.currentFlaggedToken & 65536) != 0) {
            return 1;
        }
        return peekToken;
    }

    private boolean mustMatchToken(int i, String str) throws IOException {
        return mustMatchToken(i, str, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    private boolean mustMatchToken(int i, String str, int i2, int i3) throws IOException {
        if (matchToken(i)) {
            return true;
        }
        reportError(str, i2, i3);
        return false;
    }

    private void mustHaveXML() {
        if (!this.compilerEnv.isXmlAvailable()) {
            reportError("msg.XML.not.available");
        }
    }

    public boolean eof() {
        return this.ts.eof();
    }

    /* access modifiers changed from: package-private */
    public boolean insideFunction() {
        return this.nestingOfFunction != 0;
    }

    /* access modifiers changed from: package-private */
    public void pushScope(Scope scope) {
        Scope parentScope = scope.getParentScope();
        if (parentScope == null) {
            this.currentScope.addChildScope(scope);
        } else if (parentScope != this.currentScope) {
            codeBug();
        }
        this.currentScope = scope;
    }

    /* access modifiers changed from: package-private */
    public void popScope() {
        this.currentScope = this.currentScope.getParentScope();
    }

    private void enterLoop(Loop loop) {
        if (this.loopSet == null) {
            this.loopSet = new ArrayList();
        }
        this.loopSet.add(loop);
        if (this.loopAndSwitchSet == null) {
            this.loopAndSwitchSet = new ArrayList();
        }
        this.loopAndSwitchSet.add(loop);
        pushScope(loop);
        LabeledStatement labeledStatement = this.currentLabel;
        if (labeledStatement != null) {
            labeledStatement.setStatement(loop);
            this.currentLabel.getFirstLabel().setLoop(loop);
            loop.setRelative(-this.currentLabel.getPosition());
        }
    }

    private void exitLoop() {
        List<Loop> list = this.loopSet;
        Loop remove = list.remove(list.size() - 1);
        List<Jump> list2 = this.loopAndSwitchSet;
        list2.remove(list2.size() - 1);
        if (remove.getParent() != null) {
            remove.setRelative(remove.getParent().getPosition());
        }
        popScope();
    }

    private void enterSwitch(SwitchStatement switchStatement) {
        if (this.loopAndSwitchSet == null) {
            this.loopAndSwitchSet = new ArrayList();
        }
        this.loopAndSwitchSet.add(switchStatement);
    }

    private void exitSwitch() {
        List<Jump> list = this.loopAndSwitchSet;
        list.remove(list.size() - 1);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r1.parseFinished = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        throw r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0026 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.mozilla.javascript.ast.AstRoot parse(java.lang.String r2, java.lang.String r3, int r4) {
        /*
            r1 = this;
            boolean r0 = r1.parseFinished
            if (r0 != 0) goto L_0x002f
            r1.sourceURI = r3
            org.mozilla.javascript.CompilerEnvirons r3 = r1.compilerEnv
            boolean r3 = r3.isIdeMode()
            if (r3 == 0) goto L_0x0014
            char[] r3 = r2.toCharArray()
            r1.sourceChars = r3
        L_0x0014:
            org.mozilla.javascript.TokenStream r3 = new org.mozilla.javascript.TokenStream
            r0 = 0
            r3.<init>(r1, r0, r2, r4)
            r1.ts = r3
            r2 = 1
            org.mozilla.javascript.ast.AstRoot r3 = r1.parse()     // Catch:{ IOException -> 0x0026 }
            r1.parseFinished = r2
            return r3
        L_0x0024:
            r3 = move-exception
            goto L_0x002c
        L_0x0026:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0024 }
            r3.<init>()     // Catch:{ all -> 0x0024 }
            throw r3     // Catch:{ all -> 0x0024 }
        L_0x002c:
            r1.parseFinished = r2
            throw r3
        L_0x002f:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "parser reused"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.parse(java.lang.String, java.lang.String, int):org.mozilla.javascript.ast.AstRoot");
    }

    public AstRoot parse(Reader reader, String str, int i) throws IOException {
        if (this.parseFinished) {
            throw new IllegalStateException("parser reused");
        } else if (this.compilerEnv.isIdeMode()) {
            return parse(readFully(reader), str, i);
        } else {
            try {
                this.sourceURI = str;
                this.ts = new TokenStream(this, reader, (String) null, i);
                return parse();
            } finally {
                this.parseFinished = true;
            }
        }
    }

    private AstRoot parse() throws IOException {
        AstNode astNode;
        AstRoot astRoot = new AstRoot(0);
        this.currentScriptOrFn = astRoot;
        this.currentScope = astRoot;
        int i = this.ts.lineno;
        boolean z = this.inUseStrictDirective;
        this.inUseStrictDirective = false;
        boolean z2 = true;
        int i2 = 0;
        while (true) {
            try {
                int peekToken = peekToken();
                if (peekToken <= 0) {
                    break;
                }
                if (peekToken == 109) {
                    consumeToken();
                    try {
                        astNode = function(this.calledByCompileFunction ? 2 : 1);
                    } catch (ParserException unused) {
                    }
                } else {
                    astNode = statement();
                    if (z2) {
                        String directive = getDirective(astNode);
                        if (directive == null) {
                            z2 = false;
                        } else if (directive.equals("use strict")) {
                            this.inUseStrictDirective = true;
                            astRoot.setInStrictMode(true);
                        }
                    }
                }
                i2 = getNodeEnd(astNode);
                astRoot.addChildToBack(astNode);
                astNode.setParent(astRoot);
            } catch (StackOverflowError unused2) {
                String lookupMessage = lookupMessage("msg.too.deep.parser.recursion");
                if (!this.compilerEnv.isIdeMode()) {
                    throw Context.reportRuntimeError(lookupMessage, this.sourceURI, this.ts.lineno, (String) null, 0);
                }
            } catch (Throwable th) {
                this.inUseStrictDirective = z;
                throw th;
            }
        }
        this.inUseStrictDirective = z;
        int i3 = this.syntaxErrorCount;
        if (i3 != 0) {
            String lookupMessage2 = lookupMessage("msg.got.syntax.errors", String.valueOf(i3));
            if (!this.compilerEnv.isIdeMode()) {
                throw this.errorReporter.runtimeError(lookupMessage2, this.sourceURI, i, (String) null, 0);
            }
        }
        List<Comment> list = this.scannedComments;
        if (list != null) {
            i2 = Math.max(i2, getNodeEnd(this.scannedComments.get(list.size() - 1)));
            for (Comment addComment : this.scannedComments) {
                astRoot.addComment(addComment);
            }
        }
        astRoot.setLength(i2 - 0);
        astRoot.setSourceName(this.sourceURI);
        astRoot.setBaseLineno(i);
        astRoot.setEndLineno(this.ts.lineno);
        return astRoot;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0058 A[Catch:{ ParserException -> 0x0095, all -> 0x008c }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0038 A[SYNTHETIC, Splitter:B:9:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.AstNode parseFunctionBody() throws java.io.IOException {
        /*
            r11 = this;
            r0 = 85
            boolean r0 = r11.matchToken(r0)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x001c
            org.mozilla.javascript.CompilerEnvirons r0 = r11.compilerEnv
            int r0 = r0.getLanguageVersion()
            r3 = 180(0xb4, float:2.52E-43)
            if (r0 >= r3) goto L_0x001a
            java.lang.String r0 = "msg.no.brace.body"
            r11.reportError(r0)
            goto L_0x001c
        L_0x001a:
            r0 = 1
            goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            int r3 = r11.nestingOfFunction
            int r3 = r3 + r2
            r11.nestingOfFunction = r3
            org.mozilla.javascript.TokenStream r3 = r11.ts
            int r3 = r3.tokenBeg
            org.mozilla.javascript.ast.Block r4 = new org.mozilla.javascript.ast.Block
            r4.<init>(r3)
            boolean r5 = r11.inUseStrictDirective
            org.mozilla.javascript.TokenStream r6 = r11.ts
            int r6 = r6.lineno
            r4.setLineno(r6)
            r6 = 86
            if (r0 == 0) goto L_0x0058
            org.mozilla.javascript.ast.ReturnStatement r1 = new org.mozilla.javascript.ast.ReturnStatement     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            org.mozilla.javascript.TokenStream r7 = r11.ts     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            int r7 = r7.lineno     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            r1.<init>(r7)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            org.mozilla.javascript.ast.AstNode r7 = r11.assignExpr()     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            r1.setReturnValue(r7)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            java.lang.Boolean r7 = java.lang.Boolean.TRUE     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            r8 = 25
            r1.putProp(r8, r7)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            java.lang.Boolean r7 = java.lang.Boolean.TRUE     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            r4.putProp(r8, r7)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            r4.addStatement(r1)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            goto L_0x0095
        L_0x0058:
            r7 = 1
        L_0x0059:
            int r8 = r11.peekToken()     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            r9 = -1
            if (r8 == r9) goto L_0x0095
            if (r8 == 0) goto L_0x0095
            if (r8 == r6) goto L_0x0095
            r9 = 109(0x6d, float:1.53E-43)
            if (r8 == r9) goto L_0x0081
            org.mozilla.javascript.ast.AstNode r8 = r11.statement()     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            if (r7 == 0) goto L_0x0088
            java.lang.String r9 = r11.getDirective(r8)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            if (r9 != 0) goto L_0x0076
            r7 = 0
            goto L_0x0088
        L_0x0076:
            java.lang.String r10 = "use strict"
            boolean r9 = r9.equals(r10)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            if (r9 == 0) goto L_0x0088
            r11.inUseStrictDirective = r2     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            goto L_0x0088
        L_0x0081:
            r11.consumeToken()     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            org.mozilla.javascript.ast.FunctionNode r8 = r11.function(r2)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
        L_0x0088:
            r4.addStatement(r8)     // Catch:{ ParserException -> 0x0095, all -> 0x008c }
            goto L_0x0059
        L_0x008c:
            r0 = move-exception
            int r1 = r11.nestingOfFunction
            int r1 = r1 - r2
            r11.nestingOfFunction = r1
            r11.inUseStrictDirective = r5
            throw r0
        L_0x0095:
            int r1 = r11.nestingOfFunction
            int r1 = r1 - r2
            r11.nestingOfFunction = r1
            r11.inUseStrictDirective = r5
            org.mozilla.javascript.TokenStream r1 = r11.ts
            int r1 = r1.tokenEnd
            r11.getAndResetJsDoc()
            if (r0 != 0) goto L_0x00b1
            java.lang.String r0 = "msg.no.brace.after.body"
            boolean r0 = r11.mustMatchToken(r6, r0)
            if (r0 == 0) goto L_0x00b1
            org.mozilla.javascript.TokenStream r0 = r11.ts
            int r1 = r0.tokenEnd
        L_0x00b1:
            int r1 = r1 - r3
            r4.setLength(r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.parseFunctionBody():org.mozilla.javascript.ast.AstNode");
    }

    private String getDirective(AstNode astNode) {
        if (!(astNode instanceof ExpressionStatement)) {
            return null;
        }
        AstNode expression = ((ExpressionStatement) astNode).getExpression();
        if (expression instanceof StringLiteral) {
            return ((StringLiteral) expression).getValue();
        }
        return null;
    }

    private void parseFunctionParams(FunctionNode functionNode) throws IOException {
        if (matchToken(88)) {
            functionNode.setRp(this.ts.tokenBeg - functionNode.getPosition());
            return;
        }
        HashMap hashMap = null;
        HashSet hashSet = new HashSet();
        do {
            int peekToken = peekToken();
            if (peekToken == 83 || peekToken == 85) {
                AstNode destructuringPrimaryExpr = destructuringPrimaryExpr();
                markDestructuring(destructuringPrimaryExpr);
                functionNode.addParam(destructuringPrimaryExpr);
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String nextTempName = this.currentScriptOrFn.getNextTempName();
                defineSymbol(87, nextTempName, false);
                hashMap.put(nextTempName, destructuringPrimaryExpr);
            } else if (mustMatchToken(39, "msg.no.parm")) {
                functionNode.addParam(createNameNode());
                String string = this.ts.getString();
                defineSymbol(87, string);
                if (this.inUseStrictDirective) {
                    if ("eval".equals(string) || "arguments".equals(string)) {
                        reportError("msg.bad.id.strict", string);
                    }
                    if (hashSet.contains(string)) {
                        addError("msg.dup.param.strict", string);
                    }
                    hashSet.add(string);
                }
            } else {
                functionNode.addParam(makeErrorNode());
            }
        } while (matchToken(89));
        if (hashMap != null) {
            Node node = new Node(89);
            for (Map.Entry entry : hashMap.entrySet()) {
                node.addChildToBack(createDestructuringAssignment(122, (Node) entry.getValue(), createName((String) entry.getKey())));
            }
            functionNode.putProp(23, node);
        }
        if (mustMatchToken(88, "msg.no.paren.after.parms")) {
            functionNode.setRp(this.ts.tokenBeg - functionNode.getPosition());
        }
    }

    private FunctionNode function(int i) throws IOException {
        Name name;
        String str;
        int i2 = this.ts.lineno;
        int i3 = this.ts.tokenBeg;
        AstNode astNode = null;
        if (matchToken(39)) {
            name = createNameNode(true, 39);
            if (this.inUseStrictDirective) {
                String identifier = name.getIdentifier();
                if ("eval".equals(identifier) || "arguments".equals(identifier)) {
                    reportError("msg.bad.id.strict", identifier);
                }
            }
            if (!matchToken(87)) {
                if (this.compilerEnv.isAllowMemberExprAsFunctionName()) {
                    astNode = memberExprTail(false, name);
                    name = null;
                }
                mustMatchToken(87, "msg.no.paren.parms");
            }
        } else if (matchToken(87)) {
            name = null;
        } else {
            AstNode memberExpr = this.compilerEnv.isAllowMemberExprAsFunctionName() ? memberExpr(false) : null;
            mustMatchToken(87, "msg.no.paren.parms");
            astNode = memberExpr;
            name = null;
        }
        int i4 = this.currentToken == 87 ? this.ts.tokenBeg : -1;
        if (!((astNode != null ? 2 : i) == 2 || name == null || name.length() <= 0)) {
            defineSymbol(109, name.getIdentifier());
        }
        FunctionNode functionNode = new FunctionNode(i3, name);
        functionNode.setFunctionType(i);
        if (i4 != -1) {
            functionNode.setLp(i4 - i3);
        }
        functionNode.setJsDocNode(getAndResetJsDoc());
        PerFunctionVariables perFunctionVariables = new PerFunctionVariables(functionNode);
        try {
            parseFunctionParams(functionNode);
            functionNode.setBody(parseFunctionBody());
            functionNode.setEncodedSourceBounds(i3, this.ts.tokenEnd);
            functionNode.setLength(this.ts.tokenEnd - i3);
            if (this.compilerEnv.isStrictMode() && !functionNode.getBody().hasConsistentReturnUsage()) {
                String str2 = (name == null || name.length() <= 0) ? "msg.anon.no.return.value" : "msg.no.return.value";
                if (name == null) {
                    str = "";
                } else {
                    str = name.getIdentifier();
                }
                addStrictWarning(str2, str);
            }
            if (astNode != null) {
                Kit.codeBug();
                functionNode.setMemberExprNode(astNode);
            }
            functionNode.setSourceName(this.sourceURI);
            functionNode.setBaseLineno(i2);
            functionNode.setEndLineno(this.ts.lineno);
            if (this.compilerEnv.isIdeMode()) {
                functionNode.setParentScope(this.currentScope);
            }
            return functionNode;
        } finally {
            perFunctionVariables.restore();
        }
    }

    private AstNode statements(AstNode astNode) throws IOException {
        if (this.currentToken != 85 && !this.compilerEnv.isIdeMode()) {
            codeBug();
        }
        int i = this.ts.tokenBeg;
        if (astNode == null) {
            astNode = new Block(i);
        }
        astNode.setLineno(this.ts.lineno);
        while (true) {
            int peekToken = peekToken();
            if (peekToken <= 0 || peekToken == 86) {
                astNode.setLength(this.ts.tokenBeg - i);
            } else {
                astNode.addChild(statement());
            }
        }
        astNode.setLength(this.ts.tokenBeg - i);
        return astNode;
    }

    private AstNode statements() throws IOException {
        return statements((AstNode) null);
    }

    private static class ConditionData {
        AstNode condition;
        int lp;
        int rp;

        private ConditionData() {
            this.lp = -1;
            this.rp = -1;
        }
    }

    private ConditionData condition() throws IOException {
        ConditionData conditionData = new ConditionData();
        if (mustMatchToken(87, "msg.no.paren.cond")) {
            conditionData.lp = this.ts.tokenBeg;
        }
        conditionData.condition = expr();
        if (mustMatchToken(88, "msg.no.paren.after.cond")) {
            conditionData.rp = this.ts.tokenBeg;
        }
        if (conditionData.condition instanceof Assignment) {
            addStrictWarning("msg.equal.as.assign", "", conditionData.condition.getPosition(), conditionData.condition.getLength());
        }
        return conditionData;
    }

    private AstNode statement() throws IOException {
        int peekTokenOrEOL;
        int i = this.ts.tokenBeg;
        try {
            AstNode statementHelper = statementHelper();
            if (statementHelper != null) {
                if (this.compilerEnv.isStrictMode() && !statementHelper.hasSideEffects()) {
                    int position = statementHelper.getPosition();
                    int max = Math.max(position, lineBeginningFor(position));
                    addStrictWarning(statementHelper instanceof EmptyStatement ? "msg.extra.trailing.semi" : "msg.no.side.effects", "", max, nodeEnd(statementHelper) - max);
                }
                return statementHelper;
            }
        } catch (ParserException unused) {
        }
        do {
            peekTokenOrEOL = peekTokenOrEOL();
            consumeToken();
            if (peekTokenOrEOL == -1 || peekTokenOrEOL == 0 || peekTokenOrEOL == 1 || peekTokenOrEOL == 82) {
            }
            peekTokenOrEOL = peekTokenOrEOL();
            consumeToken();
            break;
        } while (peekTokenOrEOL == 82);
        return new EmptyStatement(i, this.ts.tokenBeg - i);
    }

    private AstNode statementHelper() throws IOException {
        AstNode astNode;
        LabeledStatement labeledStatement = this.currentLabel;
        if (!(labeledStatement == null || labeledStatement.getStatement() == null)) {
            this.currentLabel = null;
        }
        int peekToken = peekToken();
        int i = this.ts.tokenBeg;
        if (peekToken != -1) {
            if (peekToken != 4) {
                if (peekToken == 39) {
                    astNode = nameOrLabel();
                    if (!(astNode instanceof ExpressionStatement)) {
                        return astNode;
                    }
                } else if (peekToken == 50) {
                    astNode = throwStatement();
                } else if (peekToken != 72) {
                    if (peekToken == 85) {
                        return block();
                    }
                    if (peekToken == 109) {
                        consumeToken();
                        return function(3);
                    } else if (peekToken == 112) {
                        return ifStatement();
                    } else {
                        if (peekToken == 114) {
                            return switchStatement();
                        }
                        if (peekToken == 160) {
                            consumeToken();
                            astNode = new KeywordLiteral(this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg, peekToken);
                            astNode.setLineno(this.ts.lineno);
                        } else if (peekToken == 81) {
                            return tryStatement();
                        } else {
                            if (peekToken == 82) {
                                consumeToken();
                                int i2 = this.ts.tokenBeg;
                                EmptyStatement emptyStatement = new EmptyStatement(i2, this.ts.tokenEnd - i2);
                                emptyStatement.setLineno(this.ts.lineno);
                                return emptyStatement;
                            } else if (peekToken != 153) {
                                if (peekToken != 154) {
                                    switch (peekToken) {
                                        case 116:
                                            astNode = defaultXmlNamespace();
                                            break;
                                        case 117:
                                            return whileLoop();
                                        case 118:
                                            return doLoop();
                                        case 119:
                                            return forLoop();
                                        case 120:
                                            astNode = breakStatement();
                                            break;
                                        case 121:
                                            astNode = continueStatement();
                                            break;
                                        case 122:
                                            break;
                                        case 123:
                                            if (this.inUseStrictDirective) {
                                                reportError("msg.no.with.strict");
                                            }
                                            return withStatement();
                                        default:
                                            int i3 = this.ts.lineno;
                                            astNode = new ExpressionStatement(expr(), true ^ insideFunction());
                                            astNode.setLineno(i3);
                                            break;
                                    }
                                }
                                consumeToken();
                                int i4 = this.ts.lineno;
                                astNode = variables(this.currentToken, this.ts.tokenBeg, true);
                                astNode.setLineno(i4);
                            } else {
                                AstNode letStatement = letStatement();
                                if (!(letStatement instanceof VariableDeclaration) || peekToken() != 82) {
                                    return letStatement;
                                }
                                astNode = letStatement;
                            }
                        }
                    }
                }
                autoInsertSemicolon(astNode);
                return astNode;
            }
            astNode = returnOrYield(peekToken, false);
            autoInsertSemicolon(astNode);
            return astNode;
        }
        consumeToken();
        return makeErrorNode();
    }

    private void autoInsertSemicolon(AstNode astNode) throws IOException {
        int peekFlaggedToken = peekFlaggedToken();
        int position = astNode.getPosition();
        int i = 65535 & peekFlaggedToken;
        if (!(i == -1 || i == 0)) {
            if (i == 82) {
                consumeToken();
                astNode.setLength(this.ts.tokenEnd - position);
                return;
            } else if (i != 86) {
                if ((peekFlaggedToken & 65536) == 0) {
                    reportError("msg.no.semi.stmt");
                    return;
                } else {
                    warnMissingSemi(position, nodeEnd(astNode));
                    return;
                }
            }
        }
        warnMissingSemi(position, nodeEnd(astNode));
    }

    private IfStatement ifStatement() throws IOException {
        if (this.currentToken != 112) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        int i2 = this.ts.lineno;
        int i3 = -1;
        ConditionData condition = condition();
        AstNode statement = statement();
        AstNode astNode = null;
        if (matchToken(113)) {
            i3 = this.ts.tokenBeg - i;
            astNode = statement();
        }
        IfStatement ifStatement = new IfStatement(i, getNodeEnd(astNode != null ? astNode : statement) - i);
        ifStatement.setCondition(condition.condition);
        ifStatement.setParens(condition.lp - i, condition.rp - i);
        ifStatement.setThenPart(statement);
        ifStatement.setElsePart(astNode);
        ifStatement.setElsePosition(i3);
        ifStatement.setLineno(i2);
        return ifStatement;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        reportError("msg.bad.switch");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.SwitchStatement switchStatement() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.currentToken
            r1 = 114(0x72, float:1.6E-43)
            if (r0 == r1) goto L_0x0009
            r11.codeBug()
        L_0x0009:
            r11.consumeToken()
            org.mozilla.javascript.TokenStream r0 = r11.ts
            int r0 = r0.tokenBeg
            org.mozilla.javascript.ast.SwitchStatement r1 = new org.mozilla.javascript.ast.SwitchStatement
            r1.<init>(r0)
            r2 = 87
            java.lang.String r3 = "msg.no.paren.switch"
            boolean r2 = r11.mustMatchToken(r2, r3)
            if (r2 == 0) goto L_0x0027
            org.mozilla.javascript.TokenStream r2 = r11.ts
            int r2 = r2.tokenBeg
            int r2 = r2 - r0
            r1.setLp(r2)
        L_0x0027:
            org.mozilla.javascript.TokenStream r2 = r11.ts
            int r2 = r2.lineno
            r1.setLineno(r2)
            org.mozilla.javascript.ast.AstNode r2 = r11.expr()
            r1.setExpression(r2)
            r11.enterSwitch(r1)
            r2 = 88
            java.lang.String r3 = "msg.no.paren.after.switch"
            boolean r2 = r11.mustMatchToken(r2, r3)     // Catch:{ all -> 0x00bf }
            if (r2 == 0) goto L_0x004a
            org.mozilla.javascript.TokenStream r2 = r11.ts     // Catch:{ all -> 0x00bf }
            int r2 = r2.tokenBeg     // Catch:{ all -> 0x00bf }
            int r2 = r2 - r0
            r1.setRp(r2)     // Catch:{ all -> 0x00bf }
        L_0x004a:
            r2 = 85
            java.lang.String r3 = "msg.no.brace.switch"
            r11.mustMatchToken(r2, r3)     // Catch:{ all -> 0x00bf }
            r2 = 0
        L_0x0052:
            int r3 = r11.nextToken()     // Catch:{ all -> 0x00bf }
            org.mozilla.javascript.TokenStream r4 = r11.ts     // Catch:{ all -> 0x00bf }
            int r4 = r4.tokenBeg     // Catch:{ all -> 0x00bf }
            org.mozilla.javascript.TokenStream r5 = r11.ts     // Catch:{ all -> 0x00bf }
            int r5 = r5.lineno     // Catch:{ all -> 0x00bf }
            r6 = 86
            if (r3 == r6) goto L_0x00b3
            java.lang.String r7 = "msg.no.colon.case"
            r8 = 103(0x67, float:1.44E-43)
            r9 = 116(0x74, float:1.63E-43)
            r10 = 115(0x73, float:1.61E-43)
            if (r3 == r10) goto L_0x0081
            if (r3 == r9) goto L_0x0074
            java.lang.String r0 = "msg.bad.switch"
            r11.reportError(r0)     // Catch:{ all -> 0x00bf }
            goto L_0x00bb
        L_0x0074:
            if (r2 == 0) goto L_0x007b
            java.lang.String r2 = "msg.double.switch.default"
            r11.reportError(r2)     // Catch:{ all -> 0x00bf }
        L_0x007b:
            r2 = 1
            r3 = 0
            r11.mustMatchToken(r8, r7)     // Catch:{ all -> 0x00bf }
            goto L_0x0088
        L_0x0081:
            org.mozilla.javascript.ast.AstNode r3 = r11.expr()     // Catch:{ all -> 0x00bf }
            r11.mustMatchToken(r8, r7)     // Catch:{ all -> 0x00bf }
        L_0x0088:
            org.mozilla.javascript.ast.SwitchCase r7 = new org.mozilla.javascript.ast.SwitchCase     // Catch:{ all -> 0x00bf }
            r7.<init>(r4)     // Catch:{ all -> 0x00bf }
            r7.setExpression(r3)     // Catch:{ all -> 0x00bf }
            org.mozilla.javascript.TokenStream r3 = r11.ts     // Catch:{ all -> 0x00bf }
            int r3 = r3.tokenEnd     // Catch:{ all -> 0x00bf }
            int r3 = r3 - r0
            r7.setLength(r3)     // Catch:{ all -> 0x00bf }
            r7.setLineno(r5)     // Catch:{ all -> 0x00bf }
        L_0x009b:
            int r3 = r11.peekToken()     // Catch:{ all -> 0x00bf }
            if (r3 == r6) goto L_0x00af
            if (r3 == r10) goto L_0x00af
            if (r3 == r9) goto L_0x00af
            if (r3 == 0) goto L_0x00af
            org.mozilla.javascript.ast.AstNode r3 = r11.statement()     // Catch:{ all -> 0x00bf }
            r7.addStatement(r3)     // Catch:{ all -> 0x00bf }
            goto L_0x009b
        L_0x00af:
            r1.addCase(r7)     // Catch:{ all -> 0x00bf }
            goto L_0x0052
        L_0x00b3:
            org.mozilla.javascript.TokenStream r2 = r11.ts     // Catch:{ all -> 0x00bf }
            int r2 = r2.tokenEnd     // Catch:{ all -> 0x00bf }
            int r2 = r2 - r0
            r1.setLength(r2)     // Catch:{ all -> 0x00bf }
        L_0x00bb:
            r11.exitSwitch()
            return r1
        L_0x00bf:
            r0 = move-exception
            r11.exitSwitch()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.switchStatement():org.mozilla.javascript.ast.SwitchStatement");
    }

    private WhileLoop whileLoop() throws IOException {
        if (this.currentToken != 117) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        WhileLoop whileLoop = new WhileLoop(i);
        whileLoop.setLineno(this.ts.lineno);
        enterLoop(whileLoop);
        try {
            ConditionData condition = condition();
            whileLoop.setCondition(condition.condition);
            whileLoop.setParens(condition.lp - i, condition.rp - i);
            AstNode statement = statement();
            whileLoop.setLength(getNodeEnd(statement) - i);
            whileLoop.setBody(statement);
            return whileLoop;
        } finally {
            exitLoop();
        }
    }

    /* JADX INFO: finally extract failed */
    private DoLoop doLoop() throws IOException {
        if (this.currentToken != 118) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        DoLoop doLoop = new DoLoop(i);
        doLoop.setLineno(this.ts.lineno);
        enterLoop(doLoop);
        try {
            AstNode statement = statement();
            mustMatchToken(117, "msg.no.while.do");
            doLoop.setWhilePosition(this.ts.tokenBeg - i);
            ConditionData condition = condition();
            doLoop.setCondition(condition.condition);
            doLoop.setParens(condition.lp - i, condition.rp - i);
            int nodeEnd = getNodeEnd(statement);
            doLoop.setBody(statement);
            exitLoop();
            if (matchToken(82)) {
                nodeEnd = this.ts.tokenEnd;
            }
            doLoop.setLength(nodeEnd - i);
            return doLoop;
        } catch (Throwable th) {
            exitLoop();
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: org.mozilla.javascript.ast.ForLoop} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: org.mozilla.javascript.ast.ForLoop} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: org.mozilla.javascript.ast.ForInLoop} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: org.mozilla.javascript.ast.ForLoop} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004e A[Catch:{ all -> 0x0133, all -> 0x0138 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0054 A[Catch:{ all -> 0x0133, all -> 0x0138 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067 A[Catch:{ all -> 0x0133, all -> 0x0138 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0074 A[Catch:{ all -> 0x0133, all -> 0x0138 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c7 A[Catch:{ all -> 0x0133, all -> 0x0138 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ce A[Catch:{ all -> 0x0133, all -> 0x0138 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f9 A[Catch:{ all -> 0x0133, all -> 0x0138 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0129  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.Loop forLoop() throws java.io.IOException {
        /*
            r17 = this;
            r1 = r17
            int r0 = r1.currentToken
            r2 = 119(0x77, float:1.67E-43)
            if (r0 == r2) goto L_0x000b
            r17.codeBug()
        L_0x000b:
            r17.consumeToken()
            org.mozilla.javascript.TokenStream r0 = r1.ts
            int r0 = r0.tokenBeg
            org.mozilla.javascript.TokenStream r2 = r1.ts
            int r2 = r2.lineno
            r3 = 0
            org.mozilla.javascript.ast.Scope r4 = new org.mozilla.javascript.ast.Scope
            r4.<init>()
            r1.pushScope(r4)
            r5 = 39
            boolean r5 = r1.matchToken(r5)     // Catch:{ all -> 0x0138 }
            java.lang.String r6 = "msg.no.paren.for"
            r7 = 0
            r8 = -1
            r9 = 1
            if (r5 == 0) goto L_0x0044
            java.lang.String r5 = "each"
            org.mozilla.javascript.TokenStream r10 = r1.ts     // Catch:{ all -> 0x0138 }
            java.lang.String r10 = r10.getString()     // Catch:{ all -> 0x0138 }
            boolean r5 = r5.equals(r10)     // Catch:{ all -> 0x0138 }
            if (r5 == 0) goto L_0x0041
            org.mozilla.javascript.TokenStream r5 = r1.ts     // Catch:{ all -> 0x0138 }
            int r5 = r5.tokenBeg     // Catch:{ all -> 0x0138 }
            int r5 = r5 - r0
            r10 = 1
            goto L_0x0046
        L_0x0041:
            r1.reportError(r6)     // Catch:{ all -> 0x0138 }
        L_0x0044:
            r5 = -1
            r10 = 0
        L_0x0046:
            r11 = 87
            boolean r6 = r1.mustMatchToken(r11, r6)     // Catch:{ all -> 0x0138 }
            if (r6 == 0) goto L_0x0054
            org.mozilla.javascript.TokenStream r6 = r1.ts     // Catch:{ all -> 0x0138 }
            int r6 = r6.tokenBeg     // Catch:{ all -> 0x0138 }
            int r6 = r6 - r0
            goto L_0x0055
        L_0x0054:
            r6 = -1
        L_0x0055:
            int r11 = r17.peekToken()     // Catch:{ all -> 0x0138 }
            org.mozilla.javascript.ast.AstNode r11 = r1.forLoopInit(r11)     // Catch:{ all -> 0x0138 }
            r12 = 52
            boolean r12 = r1.matchToken(r12)     // Catch:{ all -> 0x0138 }
            r13 = 88
            if (r12 == 0) goto L_0x0074
            org.mozilla.javascript.TokenStream r7 = r1.ts     // Catch:{ all -> 0x0138 }
            int r7 = r7.tokenBeg     // Catch:{ all -> 0x0138 }
            int r7 = r7 - r0
            org.mozilla.javascript.ast.AstNode r12 = r17.expr()     // Catch:{ all -> 0x0138 }
            r14 = r12
            r12 = r7
            r7 = 1
            goto L_0x00bf
        L_0x0074:
            java.lang.String r3 = "msg.no.semi.for"
            r12 = 82
            r1.mustMatchToken(r12, r3)     // Catch:{ all -> 0x0138 }
            int r3 = r17.peekToken()     // Catch:{ all -> 0x0138 }
            if (r3 != r12) goto L_0x0092
            org.mozilla.javascript.ast.EmptyExpression r3 = new org.mozilla.javascript.ast.EmptyExpression     // Catch:{ all -> 0x0138 }
            org.mozilla.javascript.TokenStream r14 = r1.ts     // Catch:{ all -> 0x0138 }
            int r14 = r14.tokenBeg     // Catch:{ all -> 0x0138 }
            r3.<init>(r14, r9)     // Catch:{ all -> 0x0138 }
            org.mozilla.javascript.TokenStream r14 = r1.ts     // Catch:{ all -> 0x0138 }
            int r14 = r14.lineno     // Catch:{ all -> 0x0138 }
            r3.setLineno(r14)     // Catch:{ all -> 0x0138 }
            goto L_0x0096
        L_0x0092:
            org.mozilla.javascript.ast.AstNode r3 = r17.expr()     // Catch:{ all -> 0x0138 }
        L_0x0096:
            java.lang.String r14 = "msg.no.semi.for.cond"
            r1.mustMatchToken(r12, r14)     // Catch:{ all -> 0x0138 }
            org.mozilla.javascript.TokenStream r12 = r1.ts     // Catch:{ all -> 0x0138 }
            int r12 = r12.tokenEnd     // Catch:{ all -> 0x0138 }
            int r14 = r17.peekToken()     // Catch:{ all -> 0x0138 }
            if (r14 != r13) goto L_0x00b8
            org.mozilla.javascript.ast.EmptyExpression r14 = new org.mozilla.javascript.ast.EmptyExpression     // Catch:{ all -> 0x0138 }
            r14.<init>(r12, r9)     // Catch:{ all -> 0x0138 }
            org.mozilla.javascript.TokenStream r12 = r1.ts     // Catch:{ all -> 0x0138 }
            int r12 = r12.lineno     // Catch:{ all -> 0x0138 }
            r14.setLineno(r12)     // Catch:{ all -> 0x0138 }
            r12 = -1
            r16 = r14
            r14 = r3
            r3 = r16
            goto L_0x00bf
        L_0x00b8:
            org.mozilla.javascript.ast.AstNode r12 = r17.expr()     // Catch:{ all -> 0x0138 }
            r14 = r3
            r3 = r12
            r12 = -1
        L_0x00bf:
            java.lang.String r15 = "msg.no.paren.for.ctrl"
            boolean r13 = r1.mustMatchToken(r13, r15)     // Catch:{ all -> 0x0138 }
            if (r13 == 0) goto L_0x00cc
            org.mozilla.javascript.TokenStream r8 = r1.ts     // Catch:{ all -> 0x0138 }
            int r8 = r8.tokenBeg     // Catch:{ all -> 0x0138 }
            int r8 = r8 - r0
        L_0x00cc:
            if (r7 == 0) goto L_0x00f9
            org.mozilla.javascript.ast.ForInLoop r3 = new org.mozilla.javascript.ast.ForInLoop     // Catch:{ all -> 0x0138 }
            r3.<init>(r0)     // Catch:{ all -> 0x0138 }
            boolean r7 = r11 instanceof org.mozilla.javascript.ast.VariableDeclaration     // Catch:{ all -> 0x0138 }
            if (r7 == 0) goto L_0x00e9
            r7 = r11
            org.mozilla.javascript.ast.VariableDeclaration r7 = (org.mozilla.javascript.ast.VariableDeclaration) r7     // Catch:{ all -> 0x0138 }
            java.util.List r7 = r7.getVariables()     // Catch:{ all -> 0x0138 }
            int r7 = r7.size()     // Catch:{ all -> 0x0138 }
            if (r7 <= r9) goto L_0x00e9
            java.lang.String r7 = "msg.mult.index"
            r1.reportError(r7)     // Catch:{ all -> 0x0138 }
        L_0x00e9:
            r3.setIterator(r11)     // Catch:{ all -> 0x0138 }
            r3.setIteratedObject(r14)     // Catch:{ all -> 0x0138 }
            r3.setInPosition(r12)     // Catch:{ all -> 0x0138 }
            r3.setIsForEach(r10)     // Catch:{ all -> 0x0138 }
            r3.setEachPosition(r5)     // Catch:{ all -> 0x0138 }
            goto L_0x0108
        L_0x00f9:
            org.mozilla.javascript.ast.ForLoop r5 = new org.mozilla.javascript.ast.ForLoop     // Catch:{ all -> 0x0138 }
            r5.<init>(r0)     // Catch:{ all -> 0x0138 }
            r5.setInitializer(r11)     // Catch:{ all -> 0x0138 }
            r5.setCondition(r14)     // Catch:{ all -> 0x0138 }
            r5.setIncrement(r3)     // Catch:{ all -> 0x0138 }
            r3 = r5
        L_0x0108:
            org.mozilla.javascript.ast.Scope r5 = r1.currentScope     // Catch:{ all -> 0x0138 }
            r5.replaceWith(r3)     // Catch:{ all -> 0x0138 }
            r17.popScope()     // Catch:{ all -> 0x0138 }
            r1.enterLoop(r3)     // Catch:{ all -> 0x0138 }
            org.mozilla.javascript.ast.AstNode r5 = r17.statement()     // Catch:{ all -> 0x0133 }
            int r7 = r1.getNodeEnd(r5)     // Catch:{ all -> 0x0133 }
            int r7 = r7 - r0
            r3.setLength(r7)     // Catch:{ all -> 0x0133 }
            r3.setBody(r5)     // Catch:{ all -> 0x0133 }
            r17.exitLoop()     // Catch:{ all -> 0x0138 }
            org.mozilla.javascript.ast.Scope r0 = r1.currentScope
            if (r0 != r4) goto L_0x012c
            r17.popScope()
        L_0x012c:
            r3.setParens(r6, r8)
            r3.setLineno(r2)
            return r3
        L_0x0133:
            r0 = move-exception
            r17.exitLoop()     // Catch:{ all -> 0x0138 }
            throw r0     // Catch:{ all -> 0x0138 }
        L_0x0138:
            r0 = move-exception
            org.mozilla.javascript.ast.Scope r2 = r1.currentScope
            if (r2 != r4) goto L_0x0140
            r17.popScope()
        L_0x0140:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.forLoop():org.mozilla.javascript.ast.Loop");
    }

    /* JADX INFO: finally extract failed */
    private AstNode forLoopInit(int i) throws IOException {
        AstNode astNode;
        try {
            this.inForInit = true;
            if (i == 82) {
                astNode = new EmptyExpression(this.ts.tokenBeg, 1);
                astNode.setLineno(this.ts.lineno);
            } else {
                if (i != 122) {
                    if (i != 153) {
                        astNode = expr();
                        markDestructuring(astNode);
                    }
                }
                consumeToken();
                astNode = variables(i, this.ts.tokenBeg, false);
            }
            this.inForInit = false;
            return astNode;
        } catch (Throwable th) {
            this.inForInit = false;
            throw th;
        }
    }

    private TryStatement tryStatement() throws IOException {
        ArrayList arrayList;
        int i;
        AstNode astNode;
        int i2;
        AstNode astNode2;
        int i3;
        if (this.currentToken != 81) {
            codeBug();
        }
        consumeToken();
        Comment andResetJsDoc = getAndResetJsDoc();
        int i4 = this.ts.tokenBeg;
        int i5 = this.ts.lineno;
        int i6 = 85;
        if (peekToken() != 85) {
            reportError("msg.no.brace.try");
        }
        AstNode statement = statement();
        int nodeEnd = getNodeEnd(statement);
        boolean z = false;
        int peekToken = peekToken();
        if (peekToken == 124) {
            arrayList = null;
            for (int i7 = 124; matchToken(i7); i7 = 124) {
                int i8 = this.ts.lineno;
                if (z) {
                    reportError("msg.catch.unreachable");
                }
                int i9 = this.ts.tokenBeg;
                int i10 = mustMatchToken(87, "msg.no.paren.catch") ? this.ts.tokenBeg : -1;
                mustMatchToken(39, "msg.bad.catchcond");
                Name createNameNode = createNameNode();
                String identifier = createNameNode.getIdentifier();
                if (this.inUseStrictDirective && ("eval".equals(identifier) || "arguments".equals(identifier))) {
                    reportError("msg.bad.id.strict", identifier);
                }
                if (matchToken(112)) {
                    i3 = this.ts.tokenBeg;
                    astNode2 = expr();
                } else {
                    z = true;
                    i3 = -1;
                    astNode2 = null;
                }
                int i11 = mustMatchToken(88, "msg.bad.catchcond") ? this.ts.tokenBeg : -1;
                mustMatchToken(i6, "msg.no.brace.catchblock");
                Block block = (Block) statements();
                int nodeEnd2 = getNodeEnd(block);
                CatchClause catchClause = new CatchClause(i9);
                catchClause.setVarName(createNameNode);
                catchClause.setCatchCondition(astNode2);
                catchClause.setBody(block);
                if (i3 != -1) {
                    catchClause.setIfPosition(i3 - i9);
                }
                catchClause.setParens(i10, i11);
                catchClause.setLineno(i8);
                nodeEnd = mustMatchToken(86, "msg.no.brace.after.body") ? this.ts.tokenEnd : nodeEnd2;
                catchClause.setLength(nodeEnd - i9);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(catchClause);
                i6 = 85;
            }
            i = 125;
        } else {
            i = 125;
            if (peekToken != 125) {
                mustMatchToken(125, "msg.try.no.catchfinally");
            }
            arrayList = null;
        }
        if (matchToken(i)) {
            int i12 = this.ts.tokenBeg;
            AstNode statement2 = statement();
            nodeEnd = getNodeEnd(statement2);
            int i13 = i12;
            astNode = statement2;
            i2 = i13;
        } else {
            i2 = -1;
            astNode = null;
        }
        TryStatement tryStatement = new TryStatement(i4, nodeEnd - i4);
        tryStatement.setTryBlock(statement);
        tryStatement.setCatchClauses(arrayList);
        tryStatement.setFinallyBlock(astNode);
        if (i2 != -1) {
            tryStatement.setFinallyPosition(i2 - i4);
        }
        tryStatement.setLineno(i5);
        if (andResetJsDoc != null) {
            tryStatement.setJsDocNode(andResetJsDoc);
        }
        return tryStatement;
    }

    private ThrowStatement throwStatement() throws IOException {
        if (this.currentToken != 50) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        int i2 = this.ts.lineno;
        if (peekTokenOrEOL() == 1) {
            reportError("msg.bad.throw.eol");
        }
        AstNode expr = expr();
        ThrowStatement throwStatement = new ThrowStatement(i, getNodeEnd(expr), expr);
        throwStatement.setLineno(i2);
        return throwStatement;
    }

    private LabeledStatement matchJumpLabelName() throws IOException {
        LabeledStatement labeledStatement = null;
        if (peekTokenOrEOL() == 39) {
            consumeToken();
            Map<String, LabeledStatement> map = this.labelSet;
            if (map != null) {
                labeledStatement = map.get(this.ts.getString());
            }
            if (labeledStatement == null) {
                reportError("msg.undef.label");
            }
        }
        return labeledStatement;
    }

    private BreakStatement breakStatement() throws IOException {
        int i;
        Name name;
        if (this.currentToken != 120) {
            codeBug();
        }
        consumeToken();
        int i2 = this.ts.lineno;
        int i3 = this.ts.tokenBeg;
        int i4 = this.ts.tokenEnd;
        Jump jump = null;
        if (peekTokenOrEOL() == 39) {
            name = createNameNode();
            i = getNodeEnd(name);
        } else {
            i = i4;
            name = null;
        }
        LabeledStatement matchJumpLabelName = matchJumpLabelName();
        if (matchJumpLabelName != null) {
            jump = matchJumpLabelName.getFirstLabel();
        }
        if (jump == null && name == null) {
            List<Jump> list = this.loopAndSwitchSet;
            if (list != null && list.size() != 0) {
                List<Jump> list2 = this.loopAndSwitchSet;
                jump = list2.get(list2.size() - 1);
            } else if (name == null) {
                reportError("msg.bad.break", i3, i - i3);
            }
        }
        BreakStatement breakStatement = new BreakStatement(i3, i - i3);
        breakStatement.setBreakLabel(name);
        if (jump != null) {
            breakStatement.setBreakTarget(jump);
        }
        breakStatement.setLineno(i2);
        return breakStatement;
    }

    private ContinueStatement continueStatement() throws IOException {
        int i;
        Name name;
        if (this.currentToken != 121) {
            codeBug();
        }
        consumeToken();
        int i2 = this.ts.lineno;
        int i3 = this.ts.tokenBeg;
        int i4 = this.ts.tokenEnd;
        Loop loop = null;
        if (peekTokenOrEOL() == 39) {
            name = createNameNode();
            i = getNodeEnd(name);
        } else {
            i = i4;
            name = null;
        }
        LabeledStatement matchJumpLabelName = matchJumpLabelName();
        if (matchJumpLabelName == null && name == null) {
            List<Loop> list = this.loopSet;
            if (list == null || list.size() == 0) {
                reportError("msg.continue.outside");
            } else {
                List<Loop> list2 = this.loopSet;
                loop = list2.get(list2.size() - 1);
            }
        } else {
            if (matchJumpLabelName == null || !(matchJumpLabelName.getStatement() instanceof Loop)) {
                reportError("msg.continue.nonloop", i3, i - i3);
            }
            if (matchJumpLabelName != null) {
                loop = (Loop) matchJumpLabelName.getStatement();
            }
        }
        ContinueStatement continueStatement = new ContinueStatement(i3, i - i3);
        if (loop != null) {
            continueStatement.setTarget(loop);
        }
        continueStatement.setLabel(name);
        continueStatement.setLineno(i2);
        return continueStatement;
    }

    private WithStatement withStatement() throws IOException {
        if (this.currentToken != 123) {
            codeBug();
        }
        consumeToken();
        Comment andResetJsDoc = getAndResetJsDoc();
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        int i3 = -1;
        int i4 = mustMatchToken(87, "msg.no.paren.with") ? this.ts.tokenBeg : -1;
        AstNode expr = expr();
        if (mustMatchToken(88, "msg.no.paren.after.with")) {
            i3 = this.ts.tokenBeg;
        }
        AstNode statement = statement();
        WithStatement withStatement = new WithStatement(i2, getNodeEnd(statement) - i2);
        withStatement.setJsDocNode(andResetJsDoc);
        withStatement.setExpression(expr);
        withStatement.setStatement(statement);
        withStatement.setParens(i4, i3);
        withStatement.setLineno(i);
        return withStatement;
    }

    private AstNode letStatement() throws IOException {
        AstNode astNode;
        if (this.currentToken != 153) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        if (peekToken() == 87) {
            astNode = let(true, i2);
        } else {
            astNode = variables(153, i2, true);
        }
        astNode.setLineno(i);
        return astNode;
    }

    private AstNode returnOrYield(int i, boolean z) throws IOException {
        AstNode astNode;
        int i2 = 4;
        if (!insideFunction()) {
            reportError(i == 4 ? "msg.bad.return" : "msg.bad.yield");
        }
        consumeToken();
        int i3 = this.ts.lineno;
        int i4 = this.ts.tokenBeg;
        int i5 = this.ts.tokenEnd;
        AstNode astNode2 = null;
        int peekTokenOrEOL = peekTokenOrEOL();
        if (!(peekTokenOrEOL == -1 || peekTokenOrEOL == 0 || peekTokenOrEOL == 1 || peekTokenOrEOL == 72 || peekTokenOrEOL == 82 || peekTokenOrEOL == 84 || peekTokenOrEOL == 86 || peekTokenOrEOL == 88)) {
            astNode2 = expr();
            i5 = getNodeEnd(astNode2);
        }
        int i6 = this.endFlags;
        if (i == 4) {
            if (astNode2 == null) {
                i2 = 2;
            }
            this.endFlags = i6 | i2;
            int i7 = i5 - i4;
            astNode = new ReturnStatement(i4, i7, astNode2);
            if (nowAllSet(i6, this.endFlags, 6)) {
                addStrictWarning("msg.return.inconsistent", "", i4, i7);
            }
        } else {
            if (!insideFunction()) {
                reportError("msg.bad.yield");
            }
            this.endFlags |= 8;
            astNode = new Yield(i4, i5 - i4, astNode2);
            setRequiresActivation();
            setIsGenerator();
            if (!z) {
                astNode = new ExpressionStatement(astNode);
            }
        }
        if (insideFunction() && nowAllSet(i6, this.endFlags, 12)) {
            Name functionName = ((FunctionNode) this.currentScriptOrFn).getFunctionName();
            if (functionName == null || functionName.length() == 0) {
                addError("msg.anon.generator.returns", "");
            } else {
                addError("msg.generator.returns", functionName.getIdentifier());
            }
        }
        astNode.setLineno(i3);
        return astNode;
    }

    private AstNode block() throws IOException {
        if (this.currentToken != 85) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        Scope scope = new Scope(i);
        scope.setLineno(this.ts.lineno);
        pushScope(scope);
        try {
            statements(scope);
            mustMatchToken(86, "msg.no.brace.block");
            scope.setLength(this.ts.tokenEnd - i);
            return scope;
        } finally {
            popScope();
        }
    }

    private AstNode defaultXmlNamespace() throws IOException {
        if (this.currentToken != 116) {
            codeBug();
        }
        consumeToken();
        mustHaveXML();
        setRequiresActivation();
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        if (!matchToken(39) || !"xml".equals(this.ts.getString())) {
            reportError("msg.bad.namespace");
        }
        if (!matchToken(39) || !"namespace".equals(this.ts.getString())) {
            reportError("msg.bad.namespace");
        }
        if (!matchToken(90)) {
            reportError("msg.bad.namespace");
        }
        AstNode expr = expr();
        UnaryExpression unaryExpression = new UnaryExpression(i2, getNodeEnd(expr) - i2);
        unaryExpression.setOperator(74);
        unaryExpression.setOperand(expr);
        unaryExpression.setLineno(i);
        return new ExpressionStatement((AstNode) unaryExpression, true);
    }

    private void recordLabel(Label label, LabeledStatement labeledStatement) throws IOException {
        if (peekToken() != 103) {
            codeBug();
        }
        consumeToken();
        String name = label.getName();
        Map<String, LabeledStatement> map = this.labelSet;
        if (map == null) {
            this.labelSet = new HashMap();
        } else {
            LabeledStatement labeledStatement2 = map.get(name);
            if (labeledStatement2 != null) {
                if (this.compilerEnv.isIdeMode()) {
                    Label labelByName = labeledStatement2.getLabelByName(name);
                    reportError("msg.dup.label", labelByName.getAbsolutePosition(), labelByName.getLength());
                }
                reportError("msg.dup.label", label.getPosition(), label.getLength());
            }
        }
        labeledStatement.addLabel(label);
        this.labelSet.put(name, labeledStatement);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private org.mozilla.javascript.ast.AstNode nameOrLabel() throws java.io.IOException {
        /*
            r8 = this;
            int r0 = r8.currentToken
            r1 = 39
            if (r0 != r1) goto L_0x00cb
            org.mozilla.javascript.TokenStream r0 = r8.ts
            int r0 = r0.tokenBeg
            int r2 = r8.currentFlaggedToken
            r3 = 131072(0x20000, float:1.83671E-40)
            r2 = r2 | r3
            r8.currentFlaggedToken = r2
            org.mozilla.javascript.ast.AstNode r2 = r8.expr()
            int r4 = r2.getType()
            r5 = 130(0x82, float:1.82E-43)
            if (r4 == r5) goto L_0x002d
            org.mozilla.javascript.ast.ExpressionStatement r0 = new org.mozilla.javascript.ast.ExpressionStatement
            boolean r1 = r8.insideFunction()
            r1 = r1 ^ 1
            r0.<init>((org.mozilla.javascript.ast.AstNode) r2, (boolean) r1)
            int r1 = r2.lineno
            r0.lineno = r1
            return r0
        L_0x002d:
            org.mozilla.javascript.ast.LabeledStatement r4 = new org.mozilla.javascript.ast.LabeledStatement
            r4.<init>(r0)
            org.mozilla.javascript.ast.Label r2 = (org.mozilla.javascript.ast.Label) r2
            r8.recordLabel(r2, r4)
            org.mozilla.javascript.TokenStream r2 = r8.ts
            int r2 = r2.lineno
            r4.setLineno(r2)
        L_0x003e:
            int r2 = r8.peekToken()
            r6 = 0
            if (r2 != r1) goto L_0x0069
            int r2 = r8.currentFlaggedToken
            r2 = r2 | r3
            r8.currentFlaggedToken = r2
            org.mozilla.javascript.ast.AstNode r2 = r8.expr()
            int r7 = r2.getType()
            if (r7 == r5) goto L_0x0063
            org.mozilla.javascript.ast.ExpressionStatement r1 = new org.mozilla.javascript.ast.ExpressionStatement
            boolean r3 = r8.insideFunction()
            r3 = r3 ^ 1
            r1.<init>((org.mozilla.javascript.ast.AstNode) r2, (boolean) r3)
            r8.autoInsertSemicolon(r1)
            goto L_0x006a
        L_0x0063:
            org.mozilla.javascript.ast.Label r2 = (org.mozilla.javascript.ast.Label) r2
            r8.recordLabel(r2, r4)
            goto L_0x003e
        L_0x0069:
            r1 = r6
        L_0x006a:
            r8.currentLabel = r4     // Catch:{ all -> 0x00a9 }
            if (r1 != 0) goto L_0x0072
            org.mozilla.javascript.ast.AstNode r1 = r8.statementHelper()     // Catch:{ all -> 0x00a9 }
        L_0x0072:
            r8.currentLabel = r6
            java.util.List r2 = r4.getLabels()
            java.util.Iterator r2 = r2.iterator()
        L_0x007c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0092
            java.lang.Object r3 = r2.next()
            org.mozilla.javascript.ast.Label r3 = (org.mozilla.javascript.ast.Label) r3
            java.util.Map<java.lang.String, org.mozilla.javascript.ast.LabeledStatement> r5 = r8.labelSet
            java.lang.String r3 = r3.getName()
            r5.remove(r3)
            goto L_0x007c
        L_0x0092:
            org.mozilla.javascript.ast.AstNode r2 = r1.getParent()
            if (r2 != 0) goto L_0x009e
            int r2 = r8.getNodeEnd(r1)
            int r2 = r2 - r0
            goto L_0x00a2
        L_0x009e:
            int r2 = r8.getNodeEnd(r1)
        L_0x00a2:
            r4.setLength(r2)
            r4.setStatement(r1)
            return r4
        L_0x00a9:
            r0 = move-exception
            r8.currentLabel = r6
            java.util.List r1 = r4.getLabels()
            java.util.Iterator r1 = r1.iterator()
        L_0x00b4:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00ca
            java.lang.Object r2 = r1.next()
            org.mozilla.javascript.ast.Label r2 = (org.mozilla.javascript.ast.Label) r2
            java.util.Map<java.lang.String, org.mozilla.javascript.ast.LabeledStatement> r3 = r8.labelSet
            java.lang.String r2 = r2.getName()
            r3.remove(r2)
            goto L_0x00b4
        L_0x00ca:
            throw r0
        L_0x00cb:
            java.lang.RuntimeException r0 = r8.codeBug()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.nameOrLabel():org.mozilla.javascript.ast.AstNode");
    }

    private VariableDeclaration variables(int i, int i2, boolean z) throws IOException {
        int i3;
        Name name;
        AstNode astNode;
        VariableDeclaration variableDeclaration = new VariableDeclaration(i2);
        variableDeclaration.setType(i);
        variableDeclaration.setLineno(this.ts.lineno);
        Comment andResetJsDoc = getAndResetJsDoc();
        if (andResetJsDoc != null) {
            variableDeclaration.setJsDocNode(andResetJsDoc);
        }
        do {
            int peekToken = peekToken();
            int i4 = this.ts.tokenBeg;
            int i5 = this.ts.tokenEnd;
            AstNode astNode2 = null;
            if (peekToken == 83 || peekToken == 85) {
                astNode = destructuringPrimaryExpr();
                int nodeEnd = getNodeEnd(astNode);
                if (!(astNode instanceof DestructuringForm)) {
                    reportError("msg.bad.assign.left", i4, nodeEnd - i4);
                }
                markDestructuring(astNode);
                i3 = nodeEnd;
                name = null;
            } else {
                mustMatchToken(39, "msg.bad.var");
                Name createNameNode = createNameNode();
                createNameNode.setLineno(this.ts.getLineno());
                if (this.inUseStrictDirective) {
                    String string = this.ts.getString();
                    if ("eval".equals(string) || "arguments".equals(this.ts.getString())) {
                        reportError("msg.bad.id.strict", string);
                    }
                }
                defineSymbol(i, this.ts.getString(), this.inForInit);
                i3 = i5;
                name = createNameNode;
                astNode = null;
            }
            int i6 = this.ts.lineno;
            Comment andResetJsDoc2 = getAndResetJsDoc();
            if (matchToken(90)) {
                astNode2 = assignExpr();
                i3 = getNodeEnd(astNode2);
            }
            VariableInitializer variableInitializer = new VariableInitializer(i4, i3 - i4);
            if (astNode != null) {
                if (astNode2 == null && !this.inForInit) {
                    reportError("msg.destruct.assign.no.init");
                }
                variableInitializer.setTarget(astNode);
            } else {
                variableInitializer.setTarget(name);
            }
            variableInitializer.setInitializer(astNode2);
            variableInitializer.setType(i);
            variableInitializer.setJsDocNode(andResetJsDoc2);
            variableInitializer.setLineno(i6);
            variableDeclaration.addVariable(variableInitializer);
        } while (matchToken(89));
        variableDeclaration.setLength(i3 - i2);
        variableDeclaration.setIsStatement(z);
        return variableDeclaration;
    }

    private AstNode let(boolean z, int i) throws IOException {
        LetNode letNode = new LetNode(i);
        letNode.setLineno(this.ts.lineno);
        if (mustMatchToken(87, "msg.no.paren.after.let")) {
            letNode.setLp(this.ts.tokenBeg - i);
        }
        pushScope(letNode);
        try {
            letNode.setVariables(variables(153, this.ts.tokenBeg, z));
            if (mustMatchToken(88, "msg.no.paren.let")) {
                letNode.setRp(this.ts.tokenBeg - i);
            }
            if (!z || peekToken() != 85) {
                AstNode expr = expr();
                letNode.setLength(getNodeEnd(expr) - i);
                letNode.setBody(expr);
                if (z) {
                    ExpressionStatement expressionStatement = new ExpressionStatement((AstNode) letNode, !insideFunction());
                    expressionStatement.setLineno(letNode.getLineno());
                    popScope();
                    return expressionStatement;
                }
            } else {
                consumeToken();
                int i2 = this.ts.tokenBeg;
                AstNode statements = statements();
                mustMatchToken(86, "msg.no.curly.let");
                statements.setLength(this.ts.tokenEnd - i2);
                letNode.setLength(this.ts.tokenEnd - i);
                letNode.setBody(statements);
                letNode.setType(153);
            }
            return letNode;
        } finally {
            popScope();
        }
    }

    /* access modifiers changed from: package-private */
    public void defineSymbol(int i, String str) {
        defineSymbol(i, str, false);
    }

    /* access modifiers changed from: package-private */
    public void defineSymbol(int i, String str, boolean z) {
        if (str == null) {
            if (!this.compilerEnv.isIdeMode()) {
                codeBug();
            } else {
                return;
            }
        }
        Scope definingScope = this.currentScope.getDefiningScope(str);
        Symbol symbol = definingScope != null ? definingScope.getSymbol(str) : null;
        int declType = symbol != null ? symbol.getDeclType() : -1;
        String str2 = "msg.var.redecl";
        if (symbol != null && (declType == 154 || i == 154 || (definingScope == this.currentScope && declType == 153))) {
            if (declType == 154) {
                str2 = "msg.const.redecl";
            } else if (declType == 153) {
                str2 = "msg.let.redecl";
            } else if (declType != 122) {
                str2 = declType == 109 ? "msg.fn.redecl" : "msg.parm.redecl";
            }
            addError(str2, str);
        } else if (i != 87) {
            if (!(i == 109 || i == 122)) {
                if (i != 153) {
                    if (i != 154) {
                        throw codeBug();
                    }
                } else if (z || (this.currentScope.getType() != 112 && !(this.currentScope instanceof Loop))) {
                    this.currentScope.putSymbol(new Symbol(i, str));
                    return;
                } else {
                    addError("msg.let.decl.not.in.block");
                    return;
                }
            }
            if (symbol == null) {
                this.currentScriptOrFn.putSymbol(new Symbol(i, str));
            } else if (declType == 122) {
                addStrictWarning(str2, str);
            } else if (declType == 87) {
                addStrictWarning("msg.var.hides.arg", str);
            }
        } else {
            if (symbol != null) {
                addWarning("msg.dup.parms", str);
            }
            this.currentScriptOrFn.putSymbol(new Symbol(i, str));
        }
    }

    private AstNode expr() throws IOException {
        AstNode assignExpr = assignExpr();
        int position = assignExpr.getPosition();
        while (matchToken(89)) {
            int i = this.ts.tokenBeg;
            if (this.compilerEnv.isStrictMode() && !assignExpr.hasSideEffects()) {
                addStrictWarning("msg.no.side.effects", "", position, nodeEnd(assignExpr) - position);
            }
            if (peekToken() == 72) {
                reportError("msg.yield.parenthesized");
            }
            assignExpr = new InfixExpression(89, assignExpr, assignExpr(), i);
        }
        return assignExpr;
    }

    private AstNode assignExpr() throws IOException {
        int peekToken = peekToken();
        if (peekToken == 72) {
            return returnOrYield(peekToken, true);
        }
        AstNode condExpr = condExpr();
        int peekToken2 = peekToken();
        if (90 <= peekToken2 && peekToken2 <= 101) {
            consumeToken();
            Comment andResetJsDoc = getAndResetJsDoc();
            markDestructuring(condExpr);
            Assignment assignment = new Assignment(peekToken2, condExpr, assignExpr(), this.ts.tokenBeg);
            if (andResetJsDoc != null) {
                assignment.setJsDocNode(andResetJsDoc);
            }
            return assignment;
        } else if (peekToken2 != 82 || this.currentJsDocComment == null) {
            return condExpr;
        } else {
            condExpr.setJsDocNode(getAndResetJsDoc());
            return condExpr;
        }
    }

    private AstNode condExpr() throws IOException {
        AstNode orExpr = orExpr();
        if (!matchToken(102)) {
            return orExpr;
        }
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        int i3 = -1;
        AstNode assignExpr = assignExpr();
        if (mustMatchToken(103, "msg.no.colon.cond")) {
            i3 = this.ts.tokenBeg;
        }
        AstNode assignExpr2 = assignExpr();
        int position = orExpr.getPosition();
        ConditionalExpression conditionalExpression = new ConditionalExpression(position, getNodeEnd(assignExpr2) - position);
        conditionalExpression.setLineno(i);
        conditionalExpression.setTestExpression(orExpr);
        conditionalExpression.setTrueExpression(assignExpr);
        conditionalExpression.setFalseExpression(assignExpr2);
        conditionalExpression.setQuestionMarkPosition(i2 - position);
        conditionalExpression.setColonPosition(i3 - position);
        return conditionalExpression;
    }

    private AstNode orExpr() throws IOException {
        AstNode andExpr = andExpr();
        if (!matchToken(104)) {
            return andExpr;
        }
        return new InfixExpression(104, andExpr, orExpr(), this.ts.tokenBeg);
    }

    private AstNode andExpr() throws IOException {
        AstNode bitOrExpr = bitOrExpr();
        if (!matchToken(105)) {
            return bitOrExpr;
        }
        return new InfixExpression(105, bitOrExpr, andExpr(), this.ts.tokenBeg);
    }

    private AstNode bitOrExpr() throws IOException {
        AstNode bitXorExpr = bitXorExpr();
        while (matchToken(9)) {
            bitXorExpr = new InfixExpression(9, bitXorExpr, bitXorExpr(), this.ts.tokenBeg);
        }
        return bitXorExpr;
    }

    private AstNode bitXorExpr() throws IOException {
        AstNode bitAndExpr = bitAndExpr();
        while (matchToken(10)) {
            bitAndExpr = new InfixExpression(10, bitAndExpr, bitAndExpr(), this.ts.tokenBeg);
        }
        return bitAndExpr;
    }

    private AstNode bitAndExpr() throws IOException {
        AstNode eqExpr = eqExpr();
        while (matchToken(11)) {
            eqExpr = new InfixExpression(11, eqExpr, eqExpr(), this.ts.tokenBeg);
        }
        return eqExpr;
    }

    private AstNode eqExpr() throws IOException {
        AstNode relExpr = relExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            if (peekToken != 12 && peekToken != 13 && peekToken != 46 && peekToken != 47) {
                return relExpr;
            }
            consumeToken();
            if (this.compilerEnv.getLanguageVersion() == 120) {
                if (peekToken == 12) {
                    peekToken = 46;
                } else if (peekToken == 13) {
                    peekToken = 47;
                }
            }
            relExpr = new InfixExpression(peekToken, relExpr, relExpr(), i);
        }
    }

    private AstNode relExpr() throws IOException {
        AstNode shiftExpr = shiftExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            if (peekToken != 52) {
                if (peekToken != 53) {
                    switch (peekToken) {
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                            break;
                    }
                } else {
                    continue;
                }
                consumeToken();
                shiftExpr = new InfixExpression(peekToken, shiftExpr, shiftExpr(), i);
            } else if (!this.inForInit) {
                consumeToken();
                shiftExpr = new InfixExpression(peekToken, shiftExpr, shiftExpr(), i);
            }
        }
        return shiftExpr;
    }

    private AstNode shiftExpr() throws IOException {
        AstNode addExpr = addExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            switch (peekToken) {
                case 18:
                case 19:
                case 20:
                    consumeToken();
                    addExpr = new InfixExpression(peekToken, addExpr, addExpr(), i);
                default:
                    return addExpr;
            }
        }
    }

    private AstNode addExpr() throws IOException {
        AstNode mulExpr = mulExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            if (peekToken != 21 && peekToken != 22) {
                return mulExpr;
            }
            consumeToken();
            mulExpr = new InfixExpression(peekToken, mulExpr, mulExpr(), i);
        }
    }

    private AstNode mulExpr() throws IOException {
        AstNode unaryExpr = unaryExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            switch (peekToken) {
                case 23:
                case 24:
                case 25:
                    consumeToken();
                    unaryExpr = new InfixExpression(peekToken, unaryExpr, unaryExpr(), i);
                default:
                    return unaryExpr;
            }
        }
    }

    private AstNode unaryExpr() throws IOException {
        int peekToken = peekToken();
        int i = this.ts.lineno;
        if (peekToken != -1) {
            if (peekToken != 14) {
                if (peekToken != 126) {
                    if (peekToken == 21) {
                        consumeToken();
                        UnaryExpression unaryExpression = new UnaryExpression(28, this.ts.tokenBeg, unaryExpr());
                        unaryExpression.setLineno(i);
                        return unaryExpression;
                    } else if (peekToken == 22) {
                        consumeToken();
                        UnaryExpression unaryExpression2 = new UnaryExpression(29, this.ts.tokenBeg, unaryExpr());
                        unaryExpression2.setLineno(i);
                        return unaryExpression2;
                    } else if (!(peekToken == 26 || peekToken == 27)) {
                        if (peekToken == 31) {
                            consumeToken();
                            UnaryExpression unaryExpression3 = new UnaryExpression(peekToken, this.ts.tokenBeg, unaryExpr());
                            unaryExpression3.setLineno(i);
                            return unaryExpression3;
                        } else if (peekToken != 32) {
                            if (peekToken == 106 || peekToken == 107) {
                                consumeToken();
                                UnaryExpression unaryExpression4 = new UnaryExpression(peekToken, this.ts.tokenBeg, memberExpr(true));
                                unaryExpression4.setLineno(i);
                                checkBadIncDec(unaryExpression4);
                                return unaryExpression4;
                            }
                        }
                    }
                }
                consumeToken();
                UnaryExpression unaryExpression5 = new UnaryExpression(peekToken, this.ts.tokenBeg, unaryExpr());
                unaryExpression5.setLineno(i);
                return unaryExpression5;
            } else if (this.compilerEnv.isXmlAvailable()) {
                consumeToken();
                return memberExprTail(true, xmlInitializer());
            }
            AstNode memberExpr = memberExpr(true);
            int peekTokenOrEOL = peekTokenOrEOL();
            if (peekTokenOrEOL != 106 && peekTokenOrEOL != 107) {
                return memberExpr;
            }
            consumeToken();
            UnaryExpression unaryExpression6 = new UnaryExpression(peekTokenOrEOL, this.ts.tokenBeg, memberExpr, true);
            unaryExpression6.setLineno(i);
            checkBadIncDec(unaryExpression6);
            return unaryExpression6;
        }
        consumeToken();
        return makeErrorNode();
    }

    private AstNode xmlInitializer() throws IOException {
        if (this.currentToken != 14) {
            codeBug();
        }
        int i = this.ts.tokenBeg;
        int firstXMLToken = this.ts.getFirstXMLToken();
        if (firstXMLToken == 145 || firstXMLToken == 148) {
            XmlLiteral xmlLiteral = new XmlLiteral(i);
            xmlLiteral.setLineno(this.ts.lineno);
            while (firstXMLToken == 145) {
                xmlLiteral.addFragment(new XmlString(this.ts.tokenBeg, this.ts.getString()));
                mustMatchToken(85, "msg.syntax");
                int i2 = this.ts.tokenBeg;
                AstNode emptyExpression = peekToken() == 86 ? new EmptyExpression(i2, this.ts.tokenEnd - i2) : expr();
                mustMatchToken(86, "msg.syntax");
                XmlExpression xmlExpression = new XmlExpression(i2, emptyExpression);
                xmlExpression.setIsXmlAttribute(this.ts.isXMLAttribute());
                xmlExpression.setLength(this.ts.tokenEnd - i2);
                xmlLiteral.addFragment(xmlExpression);
                firstXMLToken = this.ts.getNextXMLToken();
            }
            if (firstXMLToken != 148) {
                reportError("msg.syntax");
                return makeErrorNode();
            }
            xmlLiteral.addFragment(new XmlString(this.ts.tokenBeg, this.ts.getString()));
            return xmlLiteral;
        }
        reportError("msg.syntax");
        return makeErrorNode();
    }

    /* JADX INFO: finally extract failed */
    private List<AstNode> argumentList() throws IOException {
        if (matchToken(88)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = this.inForInit;
        this.inForInit = false;
        do {
            try {
                if (peekToken() == 72) {
                    reportError("msg.yield.parenthesized");
                }
                AstNode assignExpr = assignExpr();
                if (peekToken() == 119) {
                    try {
                        arrayList.add(generatorExpression(assignExpr, 0, true));
                    } catch (IOException unused) {
                    }
                } else {
                    arrayList.add(assignExpr);
                }
            } catch (Throwable th) {
                this.inForInit = z;
                throw th;
            }
        } while (matchToken(89));
        this.inForInit = z;
        mustMatchToken(88, "msg.no.paren.arg");
        return arrayList;
    }

    private AstNode memberExpr(boolean z) throws IOException {
        AstNode astNode;
        int peekToken = peekToken();
        int i = this.ts.lineno;
        if (peekToken != 30) {
            astNode = primaryExpr();
        } else {
            consumeToken();
            int i2 = this.ts.tokenBeg;
            NewExpression newExpression = new NewExpression(i2);
            AstNode memberExpr = memberExpr(false);
            int nodeEnd = getNodeEnd(memberExpr);
            newExpression.setTarget(memberExpr);
            if (matchToken(87)) {
                int i3 = this.ts.tokenBeg;
                List<AstNode> argumentList = argumentList();
                if (argumentList != null && argumentList.size() > 65536) {
                    reportError("msg.too.many.constructor.args");
                }
                int i4 = this.ts.tokenBeg;
                int i5 = this.ts.tokenEnd;
                if (argumentList != null) {
                    newExpression.setArguments(argumentList);
                }
                newExpression.setParens(i3 - i2, i4 - i2);
                nodeEnd = i5;
            }
            if (matchToken(85)) {
                ObjectLiteral objectLiteral = objectLiteral();
                nodeEnd = getNodeEnd(objectLiteral);
                newExpression.setInitializer(objectLiteral);
            }
            newExpression.setLength(nodeEnd - i2);
            astNode = newExpression;
        }
        astNode.setLineno(i);
        return memberExprTail(z, astNode);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: org.mozilla.javascript.ast.ElementGet} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: org.mozilla.javascript.ast.ElementGet} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: org.mozilla.javascript.ast.ElementGet} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.AstNode memberExprTail(boolean r9, org.mozilla.javascript.ast.AstNode r10) throws java.io.IOException {
        /*
            r8 = this;
            if (r10 != 0) goto L_0x0005
            r8.codeBug()
        L_0x0005:
            int r0 = r10.getPosition()
        L_0x0009:
            int r1 = r8.peekToken()
            r2 = 83
            r3 = -1
            if (r1 == r2) goto L_0x00bb
            r2 = 87
            if (r1 == r2) goto L_0x0072
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 == r2) goto L_0x0066
            r2 = 143(0x8f, float:2.0E-43)
            if (r1 == r2) goto L_0x0066
            r2 = 146(0x92, float:2.05E-43)
            if (r1 == r2) goto L_0x0023
            goto L_0x0074
        L_0x0023:
            r8.consumeToken()
            org.mozilla.javascript.TokenStream r1 = r8.ts
            int r1 = r1.tokenBeg
            org.mozilla.javascript.TokenStream r2 = r8.ts
            int r2 = r2.lineno
            r8.mustHaveXML()
            r8.setRequiresActivation()
            org.mozilla.javascript.ast.AstNode r4 = r8.expr()
            int r5 = r8.getNodeEnd(r4)
            r6 = 88
            java.lang.String r7 = "msg.no.paren"
            boolean r6 = r8.mustMatchToken(r6, r7)
            if (r6 == 0) goto L_0x004e
            org.mozilla.javascript.TokenStream r3 = r8.ts
            int r3 = r3.tokenBeg
            org.mozilla.javascript.TokenStream r5 = r8.ts
            int r5 = r5.tokenEnd
        L_0x004e:
            org.mozilla.javascript.ast.XmlDotQuery r6 = new org.mozilla.javascript.ast.XmlDotQuery
            int r5 = r5 - r0
            r6.<init>(r0, r5)
            r6.setLeft(r10)
            r6.setRight(r4)
            r6.setOperatorPosition(r1)
            int r3 = r3 - r0
            r6.setRp(r3)
            r6.setLineno(r2)
            goto L_0x00f2
        L_0x0066:
            org.mozilla.javascript.TokenStream r2 = r8.ts
            int r2 = r2.lineno
            org.mozilla.javascript.ast.AstNode r10 = r8.propertyAccess(r1, r10)
            r10.setLineno(r2)
            goto L_0x0009
        L_0x0072:
            if (r9 != 0) goto L_0x0075
        L_0x0074:
            return r10
        L_0x0075:
            org.mozilla.javascript.TokenStream r1 = r8.ts
            int r1 = r1.lineno
            r8.consumeToken()
            r8.checkCallRequiresActivation(r10)
            org.mozilla.javascript.ast.FunctionCall r2 = new org.mozilla.javascript.ast.FunctionCall
            r2.<init>(r0)
            r2.setTarget(r10)
            r2.setLineno(r1)
            org.mozilla.javascript.TokenStream r10 = r8.ts
            int r10 = r10.tokenBeg
            int r10 = r10 - r0
            r2.setLp(r10)
            java.util.List r10 = r8.argumentList()
            if (r10 == 0) goto L_0x00a5
            int r1 = r10.size()
            r3 = 65536(0x10000, float:9.18355E-41)
            if (r1 <= r3) goto L_0x00a5
            java.lang.String r1 = "msg.too.many.function.args"
            r8.reportError(r1)
        L_0x00a5:
            r2.setArguments(r10)
            org.mozilla.javascript.TokenStream r10 = r8.ts
            int r10 = r10.tokenBeg
            int r10 = r10 - r0
            r2.setRp(r10)
            org.mozilla.javascript.TokenStream r10 = r8.ts
            int r10 = r10.tokenEnd
            int r10 = r10 - r0
            r2.setLength(r10)
            r10 = r2
            goto L_0x0009
        L_0x00bb:
            r8.consumeToken()
            org.mozilla.javascript.TokenStream r1 = r8.ts
            int r1 = r1.tokenBeg
            org.mozilla.javascript.TokenStream r2 = r8.ts
            int r2 = r2.lineno
            org.mozilla.javascript.ast.AstNode r4 = r8.expr()
            int r5 = r8.getNodeEnd(r4)
            r6 = 84
            java.lang.String r7 = "msg.no.bracket.index"
            boolean r6 = r8.mustMatchToken(r6, r7)
            if (r6 == 0) goto L_0x00e0
            org.mozilla.javascript.TokenStream r3 = r8.ts
            int r3 = r3.tokenBeg
            org.mozilla.javascript.TokenStream r5 = r8.ts
            int r5 = r5.tokenEnd
        L_0x00e0:
            org.mozilla.javascript.ast.ElementGet r6 = new org.mozilla.javascript.ast.ElementGet
            int r5 = r5 - r0
            r6.<init>((int) r0, (int) r5)
            r6.setTarget(r10)
            r6.setElement(r4)
            r6.setParens(r1, r3)
            r6.setLineno(r2)
        L_0x00f2:
            r10 = r6
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.memberExprTail(boolean, org.mozilla.javascript.ast.AstNode):org.mozilla.javascript.ast.AstNode");
    }

    private AstNode propertyAccess(int i, AstNode astNode) throws IOException {
        AstNode astNode2;
        String keywordToName;
        if (astNode == null) {
            codeBug();
        }
        int i2 = 0;
        int i3 = this.ts.lineno;
        int i4 = this.ts.tokenBeg;
        consumeToken();
        if (i == 143) {
            mustHaveXML();
            i2 = 4;
        }
        if (!this.compilerEnv.isXmlAvailable()) {
            if (nextToken() != 39 && (!this.compilerEnv.isReservedKeywordAsIdentifier() || !TokenStream.isKeyword(this.ts.getString()))) {
                reportError("msg.no.name.after.dot");
            }
            PropertyGet propertyGet = new PropertyGet(astNode, createNameNode(true, 33), i4);
            propertyGet.setLineno(i3);
            return propertyGet;
        }
        int nextToken = nextToken();
        if (nextToken == 23) {
            saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
            astNode2 = propertyName(-1, "*", i2);
        } else if (nextToken == 39) {
            astNode2 = propertyName(-1, this.ts.getString(), i2);
        } else if (nextToken == 50) {
            saveNameTokenData(this.ts.tokenBeg, "throw", this.ts.lineno);
            astNode2 = propertyName(-1, "throw", i2);
        } else if (nextToken == 147) {
            astNode2 = attributeAccess();
        } else if (!this.compilerEnv.isReservedKeywordAsIdentifier() || (keywordToName = Token.keywordToName(nextToken)) == null) {
            reportError("msg.no.name.after.dot");
            return makeErrorNode();
        } else {
            saveNameTokenData(this.ts.tokenBeg, keywordToName, this.ts.lineno);
            astNode2 = propertyName(-1, keywordToName, i2);
        }
        boolean z = astNode2 instanceof XmlRef;
        InfixExpression xmlMemberGet = z ? new XmlMemberGet() : new PropertyGet();
        if (z && i == 108) {
            xmlMemberGet.setType(108);
        }
        int position = astNode.getPosition();
        xmlMemberGet.setPosition(position);
        xmlMemberGet.setLength(getNodeEnd(astNode2) - position);
        xmlMemberGet.setOperatorPosition(i4 - position);
        xmlMemberGet.setLineno(astNode.getLineno());
        xmlMemberGet.setLeft(astNode);
        xmlMemberGet.setRight(astNode2);
        return xmlMemberGet;
    }

    private AstNode attributeAccess() throws IOException {
        int nextToken = nextToken();
        int i = this.ts.tokenBeg;
        if (nextToken == 23) {
            saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
            return propertyName(i, "*", 0);
        } else if (nextToken == 39) {
            return propertyName(i, this.ts.getString(), 0);
        } else {
            if (nextToken == 83) {
                return xmlElemRef(i, (Name) null, -1);
            }
            reportError("msg.no.name.after.xmlAttr");
            return makeErrorNode();
        }
    }

    private AstNode propertyName(int i, String str, int i2) throws IOException {
        Name name;
        int i3;
        int i4 = i != -1 ? i : this.ts.tokenBeg;
        int i5 = this.ts.lineno;
        Name createNameNode = createNameNode(true, this.currentToken);
        if (matchToken(144)) {
            i3 = this.ts.tokenBeg;
            int nextToken = nextToken();
            if (nextToken == 23) {
                saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
                name = createNameNode(false, -1);
            } else if (nextToken == 39) {
                name = createNameNode();
            } else if (nextToken == 83) {
                return xmlElemRef(i, createNameNode, i3);
            } else {
                reportError("msg.no.name.after.coloncolon");
                return makeErrorNode();
            }
        } else {
            name = createNameNode;
            createNameNode = null;
            i3 = -1;
        }
        if (createNameNode == null && i2 == 0 && i == -1) {
            return name;
        }
        XmlPropRef xmlPropRef = new XmlPropRef(i4, getNodeEnd(name) - i4);
        xmlPropRef.setAtPos(i);
        xmlPropRef.setNamespace(createNameNode);
        xmlPropRef.setColonPos(i3);
        xmlPropRef.setPropName(name);
        xmlPropRef.setLineno(i5);
        return xmlPropRef;
    }

    private XmlElemRef xmlElemRef(int i, Name name, int i2) throws IOException {
        int i3 = this.ts.tokenBeg;
        int i4 = -1;
        int i5 = i != -1 ? i : i3;
        AstNode expr = expr();
        int nodeEnd = getNodeEnd(expr);
        if (mustMatchToken(84, "msg.no.bracket.index")) {
            i4 = this.ts.tokenBeg;
            nodeEnd = this.ts.tokenEnd;
        }
        XmlElemRef xmlElemRef = new XmlElemRef(i5, nodeEnd - i5);
        xmlElemRef.setNamespace(name);
        xmlElemRef.setColonPos(i2);
        xmlElemRef.setAtPos(i);
        xmlElemRef.setExpression(expr);
        xmlElemRef.setBrackets(i3, i4);
        return xmlElemRef;
    }

    /* JADX INFO: finally extract failed */
    private AstNode destructuringPrimaryExpr() throws IOException, ParserException {
        try {
            this.inDestructuringAssignment = true;
            AstNode primaryExpr = primaryExpr();
            this.inDestructuringAssignment = false;
            return primaryExpr;
        } catch (Throwable th) {
            this.inDestructuringAssignment = false;
            throw th;
        }
    }

    private AstNode primaryExpr() throws IOException {
        int nextFlaggedToken = nextFlaggedToken();
        int i = 65535 & nextFlaggedToken;
        if (i != -1) {
            if (i != 0) {
                if (i != 24) {
                    if (i == 83) {
                        return arrayLiteral();
                    }
                    if (i == 85) {
                        return objectLiteral();
                    }
                    if (i == 87) {
                        return parenExpr();
                    }
                    if (i != 100) {
                        if (i == 109) {
                            return function(2);
                        }
                        if (i == 127) {
                            reportError("msg.reserved.id");
                        } else if (i == 147) {
                            mustHaveXML();
                            return attributeAccess();
                        } else if (i != 153) {
                            switch (i) {
                                case 39:
                                    return name(nextFlaggedToken, i);
                                case 40:
                                    String string = this.ts.getString();
                                    if (this.inUseStrictDirective && this.ts.isNumberOctal()) {
                                        reportError("msg.no.octal.strict");
                                    }
                                    return new NumberLiteral(this.ts.tokenBeg, string, this.ts.getNumber());
                                case 41:
                                    return createStringLiteral();
                                case 42:
                                case 43:
                                case 44:
                                case 45:
                                    int i2 = this.ts.tokenBeg;
                                    return new KeywordLiteral(i2, this.ts.tokenEnd - i2, i);
                                default:
                                    reportError("msg.syntax");
                                    break;
                            }
                        } else {
                            return let(false, this.ts.tokenBeg);
                        }
                    }
                }
                this.ts.readRegExp(i);
                int i3 = this.ts.tokenBeg;
                RegExpLiteral regExpLiteral = new RegExpLiteral(i3, this.ts.tokenEnd - i3);
                regExpLiteral.setValue(this.ts.getString());
                regExpLiteral.setFlags(this.ts.readAndClearRegExpFlags());
                return regExpLiteral;
            }
            reportError("msg.unexpected.eof");
        }
        return makeErrorNode();
    }

    private AstNode parenExpr() throws IOException {
        boolean z = this.inForInit;
        this.inForInit = false;
        try {
            Comment andResetJsDoc = getAndResetJsDoc();
            int i = this.ts.lineno;
            int i2 = this.ts.tokenBeg;
            AstNode expr = expr();
            if (peekToken() == 119) {
                return generatorExpression(expr, i2);
            }
            ParenthesizedExpression parenthesizedExpression = new ParenthesizedExpression(expr);
            if (andResetJsDoc == null) {
                andResetJsDoc = getAndResetJsDoc();
            }
            if (andResetJsDoc != null) {
                parenthesizedExpression.setJsDocNode(andResetJsDoc);
            }
            mustMatchToken(88, "msg.no.paren");
            parenthesizedExpression.setLength(this.ts.tokenEnd - parenthesizedExpression.getPosition());
            parenthesizedExpression.setLineno(i);
            this.inForInit = z;
            return parenthesizedExpression;
        } finally {
            this.inForInit = z;
        }
    }

    private AstNode name(int i, int i2) throws IOException {
        String string = this.ts.getString();
        int i3 = this.ts.tokenBeg;
        int i4 = this.ts.lineno;
        if ((i & 131072) == 0 || peekToken() != 103) {
            saveNameTokenData(i3, string, i4);
            if (this.compilerEnv.isXmlAvailable()) {
                return propertyName(-1, string, 0);
            }
            return createNameNode(true, 39);
        }
        Label label = new Label(i3, this.ts.tokenEnd - i3);
        label.setName(string);
        label.setLineno(this.ts.lineno);
        return label;
    }

    private AstNode arrayLiteral() throws IOException {
        int peekToken;
        if (this.currentToken != 83) {
            codeBug();
        }
        int i = this.ts.tokenBeg;
        int i2 = this.ts.tokenEnd;
        ArrayList<AstNode> arrayList = new ArrayList<>();
        ArrayLiteral arrayLiteral = new ArrayLiteral(i);
        int i3 = 1;
        int i4 = 0;
        while (true) {
            int i5 = -1;
            while (true) {
                peekToken = peekToken();
                if (peekToken != 89) {
                    break;
                }
                consumeToken();
                i5 = this.ts.tokenEnd;
                if (i3 == 0) {
                    i3 = 1;
                } else {
                    arrayList.add(new EmptyExpression(this.ts.tokenBeg, 1));
                    i4++;
                }
            }
            if (peekToken == 84) {
                consumeToken();
                i2 = this.ts.tokenEnd;
                arrayLiteral.setDestructuringLength(arrayList.size() + i3);
                arrayLiteral.setSkipCount(i4);
                if (i5 != -1) {
                    warnTrailingComma(i, arrayList, i5);
                }
            } else if (peekToken == 119 && i3 == 0 && arrayList.size() == 1) {
                return arrayComprehension((AstNode) arrayList.get(0), i);
            } else {
                if (peekToken == 0) {
                    reportError("msg.no.bracket.arg");
                    break;
                }
                if (i3 == 0) {
                    reportError("msg.no.bracket.arg");
                }
                arrayList.add(assignExpr());
                i3 = 0;
            }
        }
        for (AstNode addElement : arrayList) {
            arrayLiteral.addElement(addElement);
        }
        arrayLiteral.setLength(i2 - i);
        return arrayLiteral;
    }

    private AstNode arrayComprehension(AstNode astNode, int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (peekToken() == 119) {
            arrayList.add(arrayComprehensionLoop());
        }
        int i2 = -1;
        ConditionData conditionData = null;
        if (peekToken() == 112) {
            consumeToken();
            i2 = this.ts.tokenBeg - i;
            conditionData = condition();
        }
        mustMatchToken(84, "msg.no.bracket.arg");
        ArrayComprehension arrayComprehension = new ArrayComprehension(i, this.ts.tokenEnd - i);
        arrayComprehension.setResult(astNode);
        arrayComprehension.setLoops(arrayList);
        if (conditionData != null) {
            arrayComprehension.setIfPosition(i2);
            arrayComprehension.setFilter(conditionData.condition);
            arrayComprehension.setFilterLp(conditionData.lp - i);
            arrayComprehension.setFilterRp(conditionData.rp - i);
        }
        return arrayComprehension;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042 A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048 A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050 A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066 A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074 A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0089 A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008f A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009e A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a4 A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bb A[Catch:{ all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bc A[Catch:{ all -> 0x00c7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.ArrayComprehensionLoop arrayComprehensionLoop() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.nextToken()
            r1 = 119(0x77, float:1.67E-43)
            if (r0 == r1) goto L_0x000b
            r11.codeBug()
        L_0x000b:
            org.mozilla.javascript.TokenStream r0 = r11.ts
            int r0 = r0.tokenBeg
            org.mozilla.javascript.ast.ArrayComprehensionLoop r1 = new org.mozilla.javascript.ast.ArrayComprehensionLoop
            r1.<init>(r0)
            r11.pushScope(r1)
            r2 = 39
            boolean r3 = r11.matchToken(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.String r4 = "msg.no.paren.for"
            r5 = -1
            if (r3 == 0) goto L_0x0039
            org.mozilla.javascript.TokenStream r3 = r11.ts     // Catch:{ all -> 0x00c7 }
            java.lang.String r3 = r3.getString()     // Catch:{ all -> 0x00c7 }
            java.lang.String r6 = "each"
            boolean r3 = r3.equals(r6)     // Catch:{ all -> 0x00c7 }
            if (r3 == 0) goto L_0x0036
            org.mozilla.javascript.TokenStream r3 = r11.ts     // Catch:{ all -> 0x00c7 }
            int r3 = r3.tokenBeg     // Catch:{ all -> 0x00c7 }
            int r3 = r3 - r0
            goto L_0x003a
        L_0x0036:
            r11.reportError(r4)     // Catch:{ all -> 0x00c7 }
        L_0x0039:
            r3 = -1
        L_0x003a:
            r6 = 87
            boolean r4 = r11.mustMatchToken(r6, r4)     // Catch:{ all -> 0x00c7 }
            if (r4 == 0) goto L_0x0048
            org.mozilla.javascript.TokenStream r4 = r11.ts     // Catch:{ all -> 0x00c7 }
            int r4 = r4.tokenBeg     // Catch:{ all -> 0x00c7 }
            int r4 = r4 - r0
            goto L_0x0049
        L_0x0048:
            r4 = -1
        L_0x0049:
            r6 = 0
            int r7 = r11.peekToken()     // Catch:{ all -> 0x00c7 }
            if (r7 == r2) goto L_0x0066
            r8 = 83
            if (r7 == r8) goto L_0x005e
            r8 = 85
            if (r7 == r8) goto L_0x005e
            java.lang.String r7 = "msg.bad.var"
            r11.reportError(r7)     // Catch:{ all -> 0x00c7 }
            goto L_0x006d
        L_0x005e:
            org.mozilla.javascript.ast.AstNode r6 = r11.destructuringPrimaryExpr()     // Catch:{ all -> 0x00c7 }
            r11.markDestructuring(r6)     // Catch:{ all -> 0x00c7 }
            goto L_0x006d
        L_0x0066:
            r11.consumeToken()     // Catch:{ all -> 0x00c7 }
            org.mozilla.javascript.ast.Name r6 = r11.createNameNode()     // Catch:{ all -> 0x00c7 }
        L_0x006d:
            int r7 = r6.getType()     // Catch:{ all -> 0x00c7 }
            r8 = 1
            if (r7 != r2) goto L_0x007f
            r2 = 153(0x99, float:2.14E-43)
            org.mozilla.javascript.TokenStream r7 = r11.ts     // Catch:{ all -> 0x00c7 }
            java.lang.String r7 = r7.getString()     // Catch:{ all -> 0x00c7 }
            r11.defineSymbol(r2, r7, r8)     // Catch:{ all -> 0x00c7 }
        L_0x007f:
            r2 = 52
            java.lang.String r7 = "msg.in.after.for.name"
            boolean r2 = r11.mustMatchToken(r2, r7)     // Catch:{ all -> 0x00c7 }
            if (r2 == 0) goto L_0x008f
            org.mozilla.javascript.TokenStream r2 = r11.ts     // Catch:{ all -> 0x00c7 }
            int r2 = r2.tokenBeg     // Catch:{ all -> 0x00c7 }
            int r2 = r2 - r0
            goto L_0x0090
        L_0x008f:
            r2 = -1
        L_0x0090:
            org.mozilla.javascript.ast.AstNode r7 = r11.expr()     // Catch:{ all -> 0x00c7 }
            r9 = 88
            java.lang.String r10 = "msg.no.paren.for.ctrl"
            boolean r9 = r11.mustMatchToken(r9, r10)     // Catch:{ all -> 0x00c7 }
            if (r9 == 0) goto L_0x00a4
            org.mozilla.javascript.TokenStream r9 = r11.ts     // Catch:{ all -> 0x00c7 }
            int r9 = r9.tokenBeg     // Catch:{ all -> 0x00c7 }
            int r9 = r9 - r0
            goto L_0x00a5
        L_0x00a4:
            r9 = -1
        L_0x00a5:
            org.mozilla.javascript.TokenStream r10 = r11.ts     // Catch:{ all -> 0x00c7 }
            int r10 = r10.tokenEnd     // Catch:{ all -> 0x00c7 }
            int r10 = r10 - r0
            r1.setLength(r10)     // Catch:{ all -> 0x00c7 }
            r1.setIterator(r6)     // Catch:{ all -> 0x00c7 }
            r1.setIteratedObject(r7)     // Catch:{ all -> 0x00c7 }
            r1.setInPosition(r2)     // Catch:{ all -> 0x00c7 }
            r1.setEachPosition(r3)     // Catch:{ all -> 0x00c7 }
            if (r3 == r5) goto L_0x00bc
            goto L_0x00bd
        L_0x00bc:
            r8 = 0
        L_0x00bd:
            r1.setIsForEach(r8)     // Catch:{ all -> 0x00c7 }
            r1.setParens(r4, r9)     // Catch:{ all -> 0x00c7 }
            r11.popScope()
            return r1
        L_0x00c7:
            r0 = move-exception
            r11.popScope()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.arrayComprehensionLoop():org.mozilla.javascript.ast.ArrayComprehensionLoop");
    }

    private AstNode generatorExpression(AstNode astNode, int i) throws IOException {
        return generatorExpression(astNode, i, false);
    }

    private AstNode generatorExpression(AstNode astNode, int i, boolean z) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (peekToken() == 119) {
            arrayList.add(generatorExpressionLoop());
        }
        int i2 = -1;
        ConditionData conditionData = null;
        if (peekToken() == 112) {
            consumeToken();
            i2 = this.ts.tokenBeg - i;
            conditionData = condition();
        }
        if (!z) {
            mustMatchToken(88, "msg.no.paren.let");
        }
        GeneratorExpression generatorExpression = new GeneratorExpression(i, this.ts.tokenEnd - i);
        generatorExpression.setResult(astNode);
        generatorExpression.setLoops(arrayList);
        if (conditionData != null) {
            generatorExpression.setIfPosition(i2);
            generatorExpression.setFilter(conditionData.condition);
            generatorExpression.setFilterLp(conditionData.lp - i);
            generatorExpression.setFilterRp(conditionData.rp - i);
        }
        return generatorExpression;
    }

    private GeneratorExpressionLoop generatorExpressionLoop() throws IOException {
        if (nextToken() != 119) {
            codeBug();
        }
        int i = this.ts.tokenBeg;
        GeneratorExpressionLoop generatorExpressionLoop = new GeneratorExpressionLoop(i);
        pushScope(generatorExpressionLoop);
        try {
            int i2 = -1;
            int i3 = mustMatchToken(87, "msg.no.paren.for") ? this.ts.tokenBeg - i : -1;
            AstNode astNode = null;
            int peekToken = peekToken();
            if (peekToken == 39) {
                consumeToken();
                astNode = createNameNode();
            } else if (peekToken == 83 || peekToken == 85) {
                astNode = destructuringPrimaryExpr();
                markDestructuring(astNode);
            } else {
                reportError("msg.bad.var");
            }
            if (astNode.getType() == 39) {
                defineSymbol(153, this.ts.getString(), true);
            }
            int i4 = mustMatchToken(52, "msg.in.after.for.name") ? this.ts.tokenBeg - i : -1;
            AstNode expr = expr();
            if (mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                i2 = this.ts.tokenBeg - i;
            }
            generatorExpressionLoop.setLength(this.ts.tokenEnd - i);
            generatorExpressionLoop.setIterator(astNode);
            generatorExpressionLoop.setIteratedObject(expr);
            generatorExpressionLoop.setInPosition(i4);
            generatorExpressionLoop.setParens(i3, i2);
            return generatorExpressionLoop;
        } finally {
            popScope();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0107 A[LOOP:0: B:5:0x0026->B:61:0x0107, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x010e A[EDGE_INSN: B:67:0x010e->B:62:0x010e ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.ObjectLiteral objectLiteral() throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            org.mozilla.javascript.TokenStream r1 = r0.ts
            int r1 = r1.tokenBeg
            org.mozilla.javascript.TokenStream r2 = r0.ts
            int r2 = r2.lineno
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            boolean r4 = r0.inUseStrictDirective
            if (r4 == 0) goto L_0x001e
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            goto L_0x0020
        L_0x001e:
            r4 = 0
            r6 = 0
        L_0x0020:
            org.mozilla.javascript.ast.Comment r7 = r17.getAndResetJsDoc()
            r8 = -1
            r9 = -1
        L_0x0026:
            int r10 = r17.peekToken()
            org.mozilla.javascript.ast.Comment r11 = r17.getAndResetJsDoc()
            r12 = 39
            r15 = 89
            r5 = 86
            if (r10 == r12) goto L_0x005c
            if (r10 == r5) goto L_0x0055
            org.mozilla.javascript.ast.AstNode r9 = r17.objliteralProperty()
            if (r9 != 0) goto L_0x0042
            r9 = 1
        L_0x003f:
            r12 = 0
            goto L_0x00bd
        L_0x0042:
            org.mozilla.javascript.TokenStream r12 = r0.ts
            java.lang.String r12 = r12.getString()
            r9.setJsDocNode(r11)
            org.mozilla.javascript.ast.ObjectProperty r9 = r0.plainProperty(r9, r10)
            r3.add(r9)
        L_0x0052:
            r9 = 1
            goto L_0x00bd
        L_0x0055:
            if (r9 == r8) goto L_0x010e
            r0.warnTrailingComma(r1, r3, r9)
            goto L_0x010e
        L_0x005c:
            org.mozilla.javascript.ast.Name r9 = r17.createNameNode()
            org.mozilla.javascript.TokenStream r12 = r0.ts
            java.lang.String r12 = r12.getString()
            org.mozilla.javascript.TokenStream r8 = r0.ts
            int r8 = r8.tokenBeg
            r17.consumeToken()
            int r14 = r17.peekToken()
            java.lang.String r13 = "get"
            boolean r16 = r13.equals(r12)
            if (r16 != 0) goto L_0x0084
            java.lang.String r5 = "set"
            boolean r5 = r5.equals(r12)
            if (r5 == 0) goto L_0x0082
            goto L_0x0084
        L_0x0082:
            r5 = 0
            goto L_0x0085
        L_0x0084:
            r5 = 1
        L_0x0085:
            if (r5 == 0) goto L_0x00b2
            if (r14 == r15) goto L_0x00b2
            r5 = 103(0x67, float:1.44E-43)
            if (r14 == r5) goto L_0x00b2
            r5 = 86
            if (r14 == r5) goto L_0x00b2
            boolean r5 = r13.equals(r12)
            if (r5 == 0) goto L_0x0099
            r9 = 2
            goto L_0x009a
        L_0x0099:
            r9 = 4
        L_0x009a:
            org.mozilla.javascript.ast.AstNode r10 = r17.objliteralProperty()
            if (r10 != 0) goto L_0x00a1
            goto L_0x003f
        L_0x00a1:
            org.mozilla.javascript.TokenStream r12 = r0.ts
            java.lang.String r12 = r12.getString()
            org.mozilla.javascript.ast.ObjectProperty r5 = r0.getterSetterProperty(r8, r10, r5)
            r10.setJsDocNode(r11)
            r3.add(r5)
            goto L_0x00bd
        L_0x00b2:
            r9.setJsDocNode(r11)
            org.mozilla.javascript.ast.ObjectProperty r5 = r0.plainProperty(r9, r10)
            r3.add(r5)
            goto L_0x0052
        L_0x00bd:
            boolean r5 = r0.inUseStrictDirective
            if (r5 == 0) goto L_0x00fe
            if (r12 == 0) goto L_0x00fe
            java.lang.String r5 = "msg.dup.obj.lit.prop.strict"
            r8 = 1
            if (r9 == r8) goto L_0x00e9
            r8 = 2
            if (r9 == r8) goto L_0x00dc
            r8 = 4
            if (r9 == r8) goto L_0x00cf
            goto L_0x00fe
        L_0x00cf:
            boolean r8 = r6.contains(r12)
            if (r8 == 0) goto L_0x00d8
            r0.addError(r5, r12)
        L_0x00d8:
            r6.add(r12)
            goto L_0x00fe
        L_0x00dc:
            boolean r8 = r4.contains(r12)
            if (r8 == 0) goto L_0x00e5
            r0.addError(r5, r12)
        L_0x00e5:
            r4.add(r12)
            goto L_0x00fe
        L_0x00e9:
            boolean r8 = r4.contains(r12)
            if (r8 != 0) goto L_0x00f5
            boolean r8 = r6.contains(r12)
            if (r8 == 0) goto L_0x00f8
        L_0x00f5:
            r0.addError(r5, r12)
        L_0x00f8:
            r4.add(r12)
            r6.add(r12)
        L_0x00fe:
            r17.getAndResetJsDoc()
            boolean r5 = r0.matchToken(r15)
            if (r5 == 0) goto L_0x010e
            org.mozilla.javascript.TokenStream r5 = r0.ts
            int r9 = r5.tokenEnd
            r8 = -1
            goto L_0x0026
        L_0x010e:
            java.lang.String r4 = "msg.no.brace.prop"
            r5 = 86
            r0.mustMatchToken(r5, r4)
            org.mozilla.javascript.ast.ObjectLiteral r4 = new org.mozilla.javascript.ast.ObjectLiteral
            org.mozilla.javascript.TokenStream r5 = r0.ts
            int r5 = r5.tokenEnd
            int r5 = r5 - r1
            r4.<init>(r1, r5)
            if (r7 == 0) goto L_0x0124
            r4.setJsDocNode(r7)
        L_0x0124:
            r4.setElements(r3)
            r4.setLineno(r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.objectLiteral():org.mozilla.javascript.ast.ObjectLiteral");
    }

    private AstNode objliteralProperty() throws IOException {
        AstNode astNode;
        switch (peekToken()) {
            case 39:
                astNode = createNameNode();
                break;
            case 40:
                astNode = new NumberLiteral(this.ts.tokenBeg, this.ts.getString(), this.ts.getNumber());
                break;
            case 41:
                astNode = createStringLiteral();
                break;
            default:
                if (this.compilerEnv.isReservedKeywordAsIdentifier() && TokenStream.isKeyword(this.ts.getString())) {
                    astNode = createNameNode();
                    break;
                } else {
                    reportError("msg.bad.prop");
                    return null;
                }
        }
        consumeToken();
        return astNode;
    }

    private ObjectProperty plainProperty(AstNode astNode, int i) throws IOException {
        int peekToken = peekToken();
        if ((peekToken == 89 || peekToken == 86) && i == 39 && this.compilerEnv.getLanguageVersion() >= 180) {
            if (!this.inDestructuringAssignment) {
                reportError("msg.bad.object.init");
            }
            Name name = new Name(astNode.getPosition(), astNode.getString());
            ObjectProperty objectProperty = new ObjectProperty();
            objectProperty.putProp(26, Boolean.TRUE);
            objectProperty.setLeftAndRight(astNode, name);
            return objectProperty;
        }
        mustMatchToken(103, "msg.no.colon.prop");
        ObjectProperty objectProperty2 = new ObjectProperty();
        objectProperty2.setOperatorPosition(this.ts.tokenBeg);
        objectProperty2.setLeftAndRight(astNode, assignExpr());
        return objectProperty2;
    }

    private ObjectProperty getterSetterProperty(int i, AstNode astNode, boolean z) throws IOException {
        FunctionNode function = function(2);
        Name functionName = function.getFunctionName();
        if (!(functionName == null || functionName.length() == 0)) {
            reportError("msg.bad.prop");
        }
        ObjectProperty objectProperty = new ObjectProperty(i);
        if (z) {
            objectProperty.setIsGetter();
        } else {
            objectProperty.setIsSetter();
        }
        int nodeEnd = getNodeEnd(function);
        objectProperty.setLeft(astNode);
        objectProperty.setRight(function);
        objectProperty.setLength(nodeEnd - i);
        return objectProperty;
    }

    private Name createNameNode() {
        return createNameNode(false, 39);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.Name createNameNode(boolean r6, int r7) {
        /*
            r5 = this;
            org.mozilla.javascript.TokenStream r0 = r5.ts
            int r0 = r0.tokenBeg
            org.mozilla.javascript.TokenStream r1 = r5.ts
            java.lang.String r1 = r1.getString()
            org.mozilla.javascript.TokenStream r2 = r5.ts
            int r2 = r2.lineno
            java.lang.String r3 = r5.prevNameTokenString
            java.lang.String r4 = ""
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x0025
            int r0 = r5.prevNameTokenStart
            java.lang.String r1 = r5.prevNameTokenString
            int r2 = r5.prevNameTokenLineno
            r3 = 0
            r5.prevNameTokenStart = r3
            r5.prevNameTokenString = r4
            r5.prevNameTokenLineno = r3
        L_0x0025:
            if (r1 != 0) goto L_0x0033
            org.mozilla.javascript.CompilerEnvirons r3 = r5.compilerEnv
            boolean r3 = r3.isIdeMode()
            if (r3 == 0) goto L_0x0030
            goto L_0x0034
        L_0x0030:
            r5.codeBug()
        L_0x0033:
            r4 = r1
        L_0x0034:
            org.mozilla.javascript.ast.Name r1 = new org.mozilla.javascript.ast.Name
            r1.<init>((int) r0, (java.lang.String) r4)
            r1.setLineno(r2)
            if (r6 == 0) goto L_0x0041
            r5.checkActivationName(r4, r7)
        L_0x0041:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.createNameNode(boolean, int):org.mozilla.javascript.ast.Name");
    }

    private StringLiteral createStringLiteral() {
        int i = this.ts.tokenBeg;
        StringLiteral stringLiteral = new StringLiteral(i, this.ts.tokenEnd - i);
        stringLiteral.setLineno(this.ts.lineno);
        stringLiteral.setValue(this.ts.getString());
        stringLiteral.setQuoteCharacter(this.ts.getQuoteChar());
        return stringLiteral;
    }

    /* access modifiers changed from: protected */
    public void checkActivationName(String str, int i) {
        if (insideFunction()) {
            boolean z = false;
            if ("arguments".equals(str) || ((this.compilerEnv.getActivationNames() != null && this.compilerEnv.getActivationNames().contains(str)) || (Name.LENGTH.equals(str) && i == 33 && this.compilerEnv.getLanguageVersion() == 120))) {
                z = true;
            }
            if (z) {
                setRequiresActivation();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setRequiresActivation() {
        if (insideFunction()) {
            ((FunctionNode) this.currentScriptOrFn).setRequiresActivation();
        }
    }

    private void checkCallRequiresActivation(AstNode astNode) {
        if ((astNode.getType() == 39 && "eval".equals(((Name) astNode).getIdentifier())) || (astNode.getType() == 33 && "eval".equals(((PropertyGet) astNode).getProperty().getIdentifier()))) {
            setRequiresActivation();
        }
    }

    /* access modifiers changed from: protected */
    public void setIsGenerator() {
        if (insideFunction()) {
            ((FunctionNode) this.currentScriptOrFn).setIsGenerator();
        }
    }

    private void checkBadIncDec(UnaryExpression unaryExpression) {
        int type = removeParens(unaryExpression.getOperand()).getType();
        if (type != 39 && type != 33 && type != 36 && type != 67 && type != 38) {
            reportError(unaryExpression.getType() == 106 ? "msg.bad.incr" : "msg.bad.decr");
        }
    }

    private ErrorNode makeErrorNode() {
        ErrorNode errorNode = new ErrorNode(this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
        errorNode.setLineno(this.ts.lineno);
        return errorNode;
    }

    private int nodeEnd(AstNode astNode) {
        return astNode.getPosition() + astNode.getLength();
    }

    private void saveNameTokenData(int i, String str, int i2) {
        this.prevNameTokenStart = i;
        this.prevNameTokenString = str;
        this.prevNameTokenLineno = i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0013  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0020 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int lineBeginningFor(int r6) {
        /*
            r5 = this;
            char[] r0 = r5.sourceChars
            r1 = -1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            r2 = 0
            if (r6 > 0) goto L_0x000a
            return r2
        L_0x000a:
            int r3 = r0.length
            if (r6 < r3) goto L_0x0010
            int r6 = r0.length
            int r6 = r6 + -1
        L_0x0010:
            int r6 = r6 + r1
            if (r6 < 0) goto L_0x0020
            char r3 = r0[r6]
            r4 = 10
            if (r3 == r4) goto L_0x001d
            r4 = 13
            if (r3 != r4) goto L_0x0010
        L_0x001d:
            int r6 = r6 + 1
            return r6
        L_0x0020:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.lineBeginningFor(int):int");
    }

    private void warnMissingSemi(int i, int i2) {
        if (this.compilerEnv.isStrictMode()) {
            int max = Math.max(i, lineBeginningFor(i2));
            if (i2 == -1) {
                i2 = this.ts.cursor;
            }
            addStrictWarning("msg.missing.semi", "", max, i2 - max);
        }
    }

    private void warnTrailingComma(int i, List<?> list, int i2) {
        if (this.compilerEnv.getWarnTrailingComma()) {
            if (!list.isEmpty()) {
                i = ((AstNode) list.get(0)).getPosition();
            }
            int max = Math.max(i, lineBeginningFor(i2));
            addWarning("msg.extra.trailing.comma", max, i2 - max);
        }
    }

    private String readFully(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            char[] cArr = new char[1024];
            StringBuilder sb = new StringBuilder(1024);
            while (true) {
                int read = bufferedReader.read(cArr, 0, 1024);
                if (read == -1) {
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } finally {
            bufferedReader.close();
        }
    }

    protected class PerFunctionVariables {
        private Scope savedCurrentScope;
        private ScriptNode savedCurrentScriptOrFn;
        private int savedEndFlags;
        private boolean savedInForInit;
        private Map<String, LabeledStatement> savedLabelSet;
        private List<Jump> savedLoopAndSwitchSet;
        private List<Loop> savedLoopSet;

        PerFunctionVariables(FunctionNode functionNode) {
            this.savedCurrentScriptOrFn = Parser.this.currentScriptOrFn;
            Parser.this.currentScriptOrFn = functionNode;
            this.savedCurrentScope = Parser.this.currentScope;
            Parser.this.currentScope = functionNode;
            this.savedLabelSet = Parser.this.labelSet;
            Map unused = Parser.this.labelSet = null;
            this.savedLoopSet = Parser.this.loopSet;
            List unused2 = Parser.this.loopSet = null;
            this.savedLoopAndSwitchSet = Parser.this.loopAndSwitchSet;
            List unused3 = Parser.this.loopAndSwitchSet = null;
            this.savedEndFlags = Parser.this.endFlags;
            int unused4 = Parser.this.endFlags = 0;
            this.savedInForInit = Parser.this.inForInit;
            boolean unused5 = Parser.this.inForInit = false;
        }

        /* access modifiers changed from: package-private */
        public void restore() {
            Parser.this.currentScriptOrFn = this.savedCurrentScriptOrFn;
            Parser.this.currentScope = this.savedCurrentScope;
            Map unused = Parser.this.labelSet = this.savedLabelSet;
            List unused2 = Parser.this.loopSet = this.savedLoopSet;
            List unused3 = Parser.this.loopAndSwitchSet = this.savedLoopAndSwitchSet;
            int unused4 = Parser.this.endFlags = this.savedEndFlags;
            boolean unused5 = Parser.this.inForInit = this.savedInForInit;
        }
    }

    /* access modifiers changed from: package-private */
    public Node createDestructuringAssignment(int i, Node node, Node node2) {
        String nextTempName = this.currentScriptOrFn.getNextTempName();
        Node destructuringAssignmentHelper = destructuringAssignmentHelper(i, node, node2, nextTempName);
        destructuringAssignmentHelper.getLastChild().addChildToBack(createName(nextTempName));
        return destructuringAssignmentHelper;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public Node destructuringAssignmentHelper(int i, Node node, Node node2, String str) {
        Scope createScopeNode = createScopeNode(158, node.getLineno());
        createScopeNode.addChildToFront(new Node(153, createName(39, str, node2)));
        try {
            pushScope(createScopeNode);
            boolean z = true;
            defineSymbol(153, str, true);
            popScope();
            Node node3 = new Node(89);
            createScopeNode.addChildToBack(node3);
            ArrayList arrayList = new ArrayList();
            int type = node.getType();
            if (type == 33 || type == 36) {
                if (i == 122 || i == 153 || i == 154) {
                    reportError("msg.bad.assign.left");
                }
                node3.addChildToBack(simpleAssignment(node, createName(str)));
            } else if (type == 65) {
                z = destructuringArray((ArrayLiteral) node, i, str, node3, arrayList);
            } else if (type != 66) {
                reportError("msg.bad.assign.left");
            } else {
                z = destructuringObject((ObjectLiteral) node, i, str, node3, arrayList);
            }
            if (z) {
                node3.addChildToBack(createNumber(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
            }
            createScopeNode.putProp(22, arrayList);
            return createScopeNode;
        } catch (Throwable th) {
            popScope();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean destructuringArray(ArrayLiteral arrayLiteral, int i, String str, Node node, List<String> list) {
        int i2 = i == 154 ? 155 : 8;
        boolean z = true;
        int i3 = 0;
        for (AstNode next : arrayLiteral.getElements()) {
            if (next.getType() == 128) {
                i3++;
            } else {
                Node node2 = new Node(36, createName(str), createNumber((double) i3));
                if (next.getType() == 39) {
                    String string = next.getString();
                    node.addChildToBack(new Node(i2, createName(49, string, (Node) null), node2));
                    if (i != -1) {
                        defineSymbol(i, string, true);
                        list.add(string);
                    }
                } else {
                    node.addChildToBack(destructuringAssignmentHelper(i, next, node2, this.currentScriptOrFn.getNextTempName()));
                }
                i3++;
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean destructuringObject(ObjectLiteral objectLiteral, int i, String str, Node node, List<String> list) {
        Node node2;
        int i2 = i == 154 ? 155 : 8;
        boolean z = true;
        for (ObjectProperty next : objectLiteral.getElements()) {
            TokenStream tokenStream = this.ts;
            int i3 = tokenStream != null ? tokenStream.lineno : 0;
            AstNode left = next.getLeft();
            if (left instanceof Name) {
                node2 = new Node(33, createName(str), Node.newString(((Name) left).getIdentifier()));
            } else if (left instanceof StringLiteral) {
                node2 = new Node(33, createName(str), Node.newString(((StringLiteral) left).getValue()));
            } else if (left instanceof NumberLiteral) {
                node2 = new Node(36, createName(str), createNumber((double) ((int) ((NumberLiteral) left).getNumber())));
            } else {
                throw codeBug();
            }
            node2.setLineno(i3);
            AstNode right = next.getRight();
            if (right.getType() == 39) {
                String identifier = ((Name) right).getIdentifier();
                node.addChildToBack(new Node(i2, createName(49, identifier, (Node) null), node2));
                if (i != -1) {
                    defineSymbol(i, identifier, true);
                    list.add(identifier);
                }
            } else {
                node.addChildToBack(destructuringAssignmentHelper(i, right, node2, this.currentScriptOrFn.getNextTempName()));
            }
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public Node createName(String str) {
        checkActivationName(str, 39);
        return Node.newString(39, str);
    }

    /* access modifiers changed from: protected */
    public Node createName(int i, String str, Node node) {
        Node createName = createName(str);
        createName.setType(i);
        if (node != null) {
            createName.addChildToBack(node);
        }
        return createName;
    }

    /* access modifiers changed from: protected */
    public Node createNumber(double d) {
        return Node.newNumber(d);
    }

    /* access modifiers changed from: protected */
    public Scope createScopeNode(int i, int i2) {
        Scope scope = new Scope();
        scope.setType(i);
        scope.setLineno(i2);
        return scope;
    }

    /* access modifiers changed from: protected */
    public Node simpleAssignment(Node node, Node node2) {
        Node node3;
        Node node4;
        int i;
        int type = node.getType();
        if (type == 33 || type == 36) {
            if (node instanceof PropertyGet) {
                PropertyGet propertyGet = (PropertyGet) node;
                node4 = propertyGet.getTarget();
                node3 = propertyGet.getProperty();
            } else if (node instanceof ElementGet) {
                ElementGet elementGet = (ElementGet) node;
                node4 = elementGet.getTarget();
                node3 = elementGet.getElement();
            } else {
                node4 = node.getFirstChild();
                node3 = node.getLastChild();
            }
            if (type == 33) {
                i = 35;
                node3.setType(41);
            } else {
                i = 37;
            }
            return new Node(i, node4, node3, node2);
        } else if (type == 39) {
            if (this.inUseStrictDirective) {
                Name name = (Name) node;
                if ("eval".equals(name.getIdentifier())) {
                    reportError("msg.bad.id.strict", name.getIdentifier());
                }
            }
            node.setType(49);
            return new Node(8, node, node2);
        } else if (type == 67) {
            Node firstChild = node.getFirstChild();
            checkMutableReference(firstChild);
            return new Node(68, firstChild, node2);
        } else {
            throw codeBug();
        }
    }

    /* access modifiers changed from: protected */
    public void checkMutableReference(Node node) {
        if ((node.getIntProp(16, 0) & 4) != 0) {
            reportError("msg.bad.assign.left");
        }
    }

    /* access modifiers changed from: protected */
    public AstNode removeParens(AstNode astNode) {
        while (astNode instanceof ParenthesizedExpression) {
            astNode = ((ParenthesizedExpression) astNode).getExpression();
        }
        return astNode;
    }

    /* access modifiers changed from: package-private */
    public void markDestructuring(AstNode astNode) {
        if (astNode instanceof DestructuringForm) {
            ((DestructuringForm) astNode).setIsDestructuring(true);
        } else if (astNode instanceof ParenthesizedExpression) {
            markDestructuring(((ParenthesizedExpression) astNode).getExpression());
        }
    }

    private RuntimeException codeBug() throws RuntimeException {
        throw Kit.codeBug("ts.cursor=" + this.ts.cursor + ", ts.tokenBeg=" + this.ts.tokenBeg + ", currentToken=" + this.currentToken);
    }
}
