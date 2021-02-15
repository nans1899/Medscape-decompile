package org.jsoup.select;

import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.bytebuddy.pool.TypePool;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;
import org.jsoup.select.StructuralEvaluator;

public class QueryParser {
    private static final String[] AttributeEvals = {"=", "!=", "^=", "$=", "*=", "~="};
    private static final Pattern NTH_AB = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", 2);
    private static final Pattern NTH_B = Pattern.compile("([+-])?(\\d+)");
    private static final String[] combinators = {",", HtmlObject.HtmlMarkUp.CLOSE_BRACKER, "+", "~", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR};
    private List<Evaluator> evals = new ArrayList();
    private String query;
    private TokenQueue tq;

    private QueryParser(String str) {
        Validate.notEmpty(str);
        String trim = str.trim();
        this.query = trim;
        this.tq = new TokenQueue(trim);
    }

    public static Evaluator parse(String str) {
        try {
            return new QueryParser(str).parse();
        } catch (IllegalArgumentException e) {
            throw new Selector.SelectorParseException(e.getMessage(), new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public Evaluator parse() {
        this.tq.consumeWhitespace();
        if (this.tq.matchesAny(combinators)) {
            this.evals.add(new StructuralEvaluator.Root());
            combinator(this.tq.consume());
        } else {
            findElements();
        }
        while (!this.tq.isEmpty()) {
            boolean consumeWhitespace = this.tq.consumeWhitespace();
            if (this.tq.matchesAny(combinators)) {
                combinator(this.tq.consume());
            } else if (consumeWhitespace) {
                combinator(' ');
            } else {
                findElements();
            }
        }
        if (this.evals.size() == 1) {
            return this.evals.get(0);
        }
        return new CombiningEvaluator.And((Collection<Evaluator>) this.evals);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void combinator(char r11) {
        /*
            r10 = this;
            org.jsoup.parser.TokenQueue r0 = r10.tq
            r0.consumeWhitespace()
            java.lang.String r0 = r10.consumeSubQuery()
            org.jsoup.select.Evaluator r0 = parse(r0)
            java.util.List<org.jsoup.select.Evaluator> r1 = r10.evals
            int r1 = r1.size()
            r2 = 44
            r3 = 1
            r4 = 0
            if (r1 != r3) goto L_0x0033
            java.util.List<org.jsoup.select.Evaluator> r1 = r10.evals
            java.lang.Object r1 = r1.get(r4)
            org.jsoup.select.Evaluator r1 = (org.jsoup.select.Evaluator) r1
            boolean r5 = r1 instanceof org.jsoup.select.CombiningEvaluator.Or
            if (r5 == 0) goto L_0x003a
            if (r11 == r2) goto L_0x003a
            r5 = r1
            org.jsoup.select.CombiningEvaluator$Or r5 = (org.jsoup.select.CombiningEvaluator.Or) r5
            org.jsoup.select.Evaluator r5 = r5.rightMostEvaluator()
            r6 = 1
            r9 = r5
            r5 = r1
            r1 = r9
            goto L_0x003c
        L_0x0033:
            org.jsoup.select.CombiningEvaluator$And r1 = new org.jsoup.select.CombiningEvaluator$And
            java.util.List<org.jsoup.select.Evaluator> r5 = r10.evals
            r1.<init>((java.util.Collection<org.jsoup.select.Evaluator>) r5)
        L_0x003a:
            r5 = r1
            r6 = 0
        L_0x003c:
            java.util.List<org.jsoup.select.Evaluator> r7 = r10.evals
            r7.clear()
            r7 = 62
            r8 = 2
            if (r11 != r7) goto L_0x0057
            org.jsoup.select.CombiningEvaluator$And r11 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r2 = new org.jsoup.select.Evaluator[r8]
            r2[r4] = r0
            org.jsoup.select.StructuralEvaluator$ImmediateParent r0 = new org.jsoup.select.StructuralEvaluator$ImmediateParent
            r0.<init>(r1)
            r2[r3] = r0
            r11.<init>((org.jsoup.select.Evaluator[]) r2)
            goto L_0x00ae
        L_0x0057:
            r7 = 32
            if (r11 != r7) goto L_0x006c
            org.jsoup.select.CombiningEvaluator$And r11 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r2 = new org.jsoup.select.Evaluator[r8]
            r2[r4] = r0
            org.jsoup.select.StructuralEvaluator$Parent r0 = new org.jsoup.select.StructuralEvaluator$Parent
            r0.<init>(r1)
            r2[r3] = r0
            r11.<init>((org.jsoup.select.Evaluator[]) r2)
            goto L_0x00ae
        L_0x006c:
            r7 = 43
            if (r11 != r7) goto L_0x0081
            org.jsoup.select.CombiningEvaluator$And r11 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r2 = new org.jsoup.select.Evaluator[r8]
            r2[r4] = r0
            org.jsoup.select.StructuralEvaluator$ImmediatePreviousSibling r0 = new org.jsoup.select.StructuralEvaluator$ImmediatePreviousSibling
            r0.<init>(r1)
            r2[r3] = r0
            r11.<init>((org.jsoup.select.Evaluator[]) r2)
            goto L_0x00ae
        L_0x0081:
            r7 = 126(0x7e, float:1.77E-43)
            if (r11 != r7) goto L_0x0096
            org.jsoup.select.CombiningEvaluator$And r11 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r2 = new org.jsoup.select.Evaluator[r8]
            r2[r4] = r0
            org.jsoup.select.StructuralEvaluator$PreviousSibling r0 = new org.jsoup.select.StructuralEvaluator$PreviousSibling
            r0.<init>(r1)
            r2[r3] = r0
            r11.<init>((org.jsoup.select.Evaluator[]) r2)
            goto L_0x00ae
        L_0x0096:
            if (r11 != r2) goto L_0x00be
            boolean r11 = r1 instanceof org.jsoup.select.CombiningEvaluator.Or
            if (r11 == 0) goto L_0x00a3
            org.jsoup.select.CombiningEvaluator$Or r1 = (org.jsoup.select.CombiningEvaluator.Or) r1
            r1.add(r0)
            r11 = r1
            goto L_0x00ae
        L_0x00a3:
            org.jsoup.select.CombiningEvaluator$Or r11 = new org.jsoup.select.CombiningEvaluator$Or
            r11.<init>()
            r11.add(r1)
            r11.add(r0)
        L_0x00ae:
            if (r6 == 0) goto L_0x00b7
            r0 = r5
            org.jsoup.select.CombiningEvaluator$Or r0 = (org.jsoup.select.CombiningEvaluator.Or) r0
            r0.replaceRightMostEvaluator(r11)
            goto L_0x00b8
        L_0x00b7:
            r5 = r11
        L_0x00b8:
            java.util.List<org.jsoup.select.Evaluator> r11 = r10.evals
            r11.add(r5)
            return
        L_0x00be:
            org.jsoup.select.Selector$SelectorParseException r0 = new org.jsoup.select.Selector$SelectorParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown combinator: "
            r1.append(r2)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            java.lang.Object[] r1 = new java.lang.Object[r4]
            r0.<init>(r11, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.select.QueryParser.combinator(char):void");
    }

    private String consumeSubQuery() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        while (!this.tq.isEmpty()) {
            if (this.tq.matches("(")) {
                borrowBuilder.append("(");
                borrowBuilder.append(this.tq.chompBalanced(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN, ASCIIPropertyListParser.ARRAY_END_TOKEN));
                borrowBuilder.append(")");
            } else if (this.tq.matches("[")) {
                borrowBuilder.append("[");
                borrowBuilder.append(this.tq.chompBalanced(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH, ']'));
                borrowBuilder.append("]");
            } else if (this.tq.matchesAny(combinators)) {
                break;
            } else {
                borrowBuilder.append(this.tq.consume());
            }
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    private void findElements() {
        if (this.tq.matchChomp("#")) {
            byId();
        } else if (this.tq.matchChomp(".")) {
            byClass();
        } else if (this.tq.matchesWord() || this.tq.matches("*|")) {
            byTag();
        } else if (this.tq.matches("[")) {
            byAttribute();
        } else if (this.tq.matchChomp("*")) {
            allElements();
        } else if (this.tq.matchChomp(":lt(")) {
            indexLessThan();
        } else if (this.tq.matchChomp(":gt(")) {
            indexGreaterThan();
        } else if (this.tq.matchChomp(":eq(")) {
            indexEquals();
        } else if (this.tq.matches(":has(")) {
            has();
        } else if (this.tq.matches(":contains(")) {
            contains(false);
        } else if (this.tq.matches(":containsOwn(")) {
            contains(true);
        } else if (this.tq.matches(":containsData(")) {
            containsData();
        } else if (this.tq.matches(":matches(")) {
            matches(false);
        } else if (this.tq.matches(":matchesOwn(")) {
            matches(true);
        } else if (this.tq.matches(":not(")) {
            not();
        } else if (this.tq.matchChomp(":nth-child(")) {
            cssNthChild(false, false);
        } else if (this.tq.matchChomp(":nth-last-child(")) {
            cssNthChild(true, false);
        } else if (this.tq.matchChomp(":nth-of-type(")) {
            cssNthChild(false, true);
        } else if (this.tq.matchChomp(":nth-last-of-type(")) {
            cssNthChild(true, true);
        } else if (this.tq.matchChomp(":first-child")) {
            this.evals.add(new Evaluator.IsFirstChild());
        } else if (this.tq.matchChomp(":last-child")) {
            this.evals.add(new Evaluator.IsLastChild());
        } else if (this.tq.matchChomp(":first-of-type")) {
            this.evals.add(new Evaluator.IsFirstOfType());
        } else if (this.tq.matchChomp(":last-of-type")) {
            this.evals.add(new Evaluator.IsLastOfType());
        } else if (this.tq.matchChomp(":only-child")) {
            this.evals.add(new Evaluator.IsOnlyChild());
        } else if (this.tq.matchChomp(":only-of-type")) {
            this.evals.add(new Evaluator.IsOnlyOfType());
        } else if (this.tq.matchChomp(":empty")) {
            this.evals.add(new Evaluator.IsEmpty());
        } else if (this.tq.matchChomp(":root")) {
            this.evals.add(new Evaluator.IsRoot());
        } else if (this.tq.matchChomp(":matchText")) {
            this.evals.add(new Evaluator.MatchText());
        } else {
            throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.tq.remainder());
        }
    }

    private void byId() {
        String consumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.notEmpty(consumeCssIdentifier);
        this.evals.add(new Evaluator.Id(consumeCssIdentifier));
    }

    private void byClass() {
        String consumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.notEmpty(consumeCssIdentifier);
        this.evals.add(new Evaluator.Class(consumeCssIdentifier.trim()));
    }

    private void byTag() {
        String normalize = Normalizer.normalize(this.tq.consumeElementSelector());
        Validate.notEmpty(normalize);
        if (normalize.startsWith("*|")) {
            this.evals.add(new CombiningEvaluator.Or(new Evaluator.Tag(normalize), new Evaluator.TagEndsWith(normalize.replace("*|", ":"))));
            return;
        }
        if (normalize.contains("|")) {
            normalize = normalize.replace("|", ":");
        }
        this.evals.add(new Evaluator.Tag(normalize));
    }

    private void byAttribute() {
        TokenQueue tokenQueue = new TokenQueue(this.tq.chompBalanced(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH, ']'));
        String consumeToAny = tokenQueue.consumeToAny(AttributeEvals);
        Validate.notEmpty(consumeToAny);
        tokenQueue.consumeWhitespace();
        if (tokenQueue.isEmpty()) {
            if (consumeToAny.startsWith("^")) {
                this.evals.add(new Evaluator.AttributeStarting(consumeToAny.substring(1)));
            } else {
                this.evals.add(new Evaluator.Attribute(consumeToAny));
            }
        } else if (tokenQueue.matchChomp("=")) {
            this.evals.add(new Evaluator.AttributeWithValue(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("!=")) {
            this.evals.add(new Evaluator.AttributeWithValueNot(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("^=")) {
            this.evals.add(new Evaluator.AttributeWithValueStarting(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("$=")) {
            this.evals.add(new Evaluator.AttributeWithValueEnding(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("*=")) {
            this.evals.add(new Evaluator.AttributeWithValueContaining(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("~=")) {
            this.evals.add(new Evaluator.AttributeWithValueMatching(consumeToAny, Pattern.compile(tokenQueue.remainder())));
        } else {
            throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.query, tokenQueue.remainder());
        }
    }

    private void allElements() {
        this.evals.add(new Evaluator.AllElements());
    }

    private void indexLessThan() {
        this.evals.add(new Evaluator.IndexLessThan(consumeIndex()));
    }

    private void indexGreaterThan() {
        this.evals.add(new Evaluator.IndexGreaterThan(consumeIndex()));
    }

    private void indexEquals() {
        this.evals.add(new Evaluator.IndexEquals(consumeIndex()));
    }

    private void cssNthChild(boolean z, boolean z2) {
        String normalize = Normalizer.normalize(this.tq.chompTo(")"));
        Matcher matcher = NTH_AB.matcher(normalize);
        Matcher matcher2 = NTH_B.matcher(normalize);
        int i = 2;
        int i2 = 0;
        if ("odd".equals(normalize)) {
            i2 = 1;
        } else if (!"even".equals(normalize)) {
            if (matcher.matches()) {
                int parseInt = matcher.group(3) != null ? Integer.parseInt(matcher.group(1).replaceFirst("^\\+", "")) : 1;
                if (matcher.group(4) != null) {
                    i2 = Integer.parseInt(matcher.group(4).replaceFirst("^\\+", ""));
                }
                i = parseInt;
            } else if (matcher2.matches()) {
                i2 = Integer.parseInt(matcher2.group().replaceFirst("^\\+", ""));
                i = 0;
            } else {
                throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", normalize);
            }
        }
        if (z2) {
            if (z) {
                this.evals.add(new Evaluator.IsNthLastOfType(i, i2));
            } else {
                this.evals.add(new Evaluator.IsNthOfType(i, i2));
            }
        } else if (z) {
            this.evals.add(new Evaluator.IsNthLastChild(i, i2));
        } else {
            this.evals.add(new Evaluator.IsNthChild(i, i2));
        }
    }

    private int consumeIndex() {
        String trim = this.tq.chompTo(")").trim();
        Validate.isTrue(StringUtil.isNumeric(trim), "Index must be numeric");
        return Integer.parseInt(trim);
    }

    private void has() {
        this.tq.consume(":has");
        String chompBalanced = this.tq.chompBalanced(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN, ASCIIPropertyListParser.ARRAY_END_TOKEN);
        Validate.notEmpty(chompBalanced, ":has(el) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Has(parse(chompBalanced)));
    }

    private void contains(boolean z) {
        this.tq.consume(z ? ":containsOwn" : ":contains");
        String unescape = TokenQueue.unescape(this.tq.chompBalanced(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN, ASCIIPropertyListParser.ARRAY_END_TOKEN));
        Validate.notEmpty(unescape, ":contains(text) query must not be empty");
        if (z) {
            this.evals.add(new Evaluator.ContainsOwnText(unescape));
        } else {
            this.evals.add(new Evaluator.ContainsText(unescape));
        }
    }

    private void containsData() {
        this.tq.consume(":containsData");
        String unescape = TokenQueue.unescape(this.tq.chompBalanced(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN, ASCIIPropertyListParser.ARRAY_END_TOKEN));
        Validate.notEmpty(unescape, ":containsData(text) query must not be empty");
        this.evals.add(new Evaluator.ContainsData(unescape));
    }

    private void matches(boolean z) {
        this.tq.consume(z ? ":matchesOwn" : ":matches");
        String chompBalanced = this.tq.chompBalanced(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN, ASCIIPropertyListParser.ARRAY_END_TOKEN);
        Validate.notEmpty(chompBalanced, ":matches(regex) query must not be empty");
        if (z) {
            this.evals.add(new Evaluator.MatchesOwn(Pattern.compile(chompBalanced)));
        } else {
            this.evals.add(new Evaluator.Matches(Pattern.compile(chompBalanced)));
        }
    }

    private void not() {
        this.tq.consume(":not");
        String chompBalanced = this.tq.chompBalanced(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN, ASCIIPropertyListParser.ARRAY_END_TOKEN);
        Validate.notEmpty(chompBalanced, ":not(selector) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Not(parse(chompBalanced)));
    }
}
