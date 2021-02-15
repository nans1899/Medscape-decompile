package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.ast.ParseProblem;

public class ErrorCollector implements IdeErrorReporter {
    private List<ParseProblem> errors = new ArrayList();

    public void warning(String str, String str2, int i, String str3, int i2) {
        throw new UnsupportedOperationException();
    }

    public void warning(String str, String str2, int i, int i2) {
        this.errors.add(new ParseProblem(ParseProblem.Type.Warning, str, str2, i, i2));
    }

    public void error(String str, String str2, int i, String str3, int i2) {
        throw new UnsupportedOperationException();
    }

    public void error(String str, String str2, int i, int i2) {
        this.errors.add(new ParseProblem(ParseProblem.Type.Error, str, str2, i, i2));
    }

    public EvaluatorException runtimeError(String str, String str2, int i, String str3, int i2) {
        throw new UnsupportedOperationException();
    }

    public List<ParseProblem> getErrors() {
        return this.errors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.errors.size() * 100);
        for (ParseProblem parseProblem : this.errors) {
            sb.append(parseProblem.toString());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        return sb.toString();
    }
}
