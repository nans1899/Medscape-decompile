package org.mockito.internal.junit;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoSession;
import org.mockito.internal.session.MockitoSessionLoggerAdapter;
import org.mockito.junit.MockitoRule;
import org.mockito.plugins.MockitoLogger;
import org.mockito.quality.Strictness;
import org.mockito.session.MockitoSessionBuilder;

public class JUnitRule implements MockitoRule {
    /* access modifiers changed from: private */
    public final MockitoLogger logger;
    /* access modifiers changed from: private */
    public MockitoSession session;
    /* access modifiers changed from: private */
    public Strictness strictness;

    public JUnitRule(MockitoLogger mockitoLogger, Strictness strictness2) {
        this.logger = mockitoLogger;
        this.strictness = strictness2;
    }

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object obj) {
        return new Statement() {
            public void evaluate() throws Throwable {
                if (JUnitRule.this.session == null) {
                    JUnitRule jUnitRule = JUnitRule.this;
                    MockitoSessionBuilder mockitoSession = Mockito.mockitoSession();
                    MockitoSession unused = jUnitRule.session = mockitoSession.name(obj.getClass().getSimpleName() + "." + frameworkMethod.getName()).strictness(JUnitRule.this.strictness).logger(new MockitoSessionLoggerAdapter(JUnitRule.this.logger)).initMocks(obj).startMocking();
                } else {
                    MockitoAnnotations.initMocks(obj);
                }
                Throwable evaluateSafely = evaluateSafely(statement);
                JUnitRule.this.session.finishMocking(evaluateSafely);
                if (evaluateSafely != null) {
                    throw evaluateSafely;
                }
            }

            private Throwable evaluateSafely(Statement statement) {
                try {
                    statement.evaluate();
                    return null;
                } catch (Throwable th) {
                    return th;
                }
            }
        };
    }

    public MockitoRule silent() {
        return strictness(Strictness.LENIENT);
    }

    public MockitoRule strictness(Strictness strictness2) {
        this.strictness = strictness2;
        MockitoSession mockitoSession = this.session;
        if (mockitoSession != null) {
            mockitoSession.setStrictness(strictness2);
        }
        return this;
    }
}
