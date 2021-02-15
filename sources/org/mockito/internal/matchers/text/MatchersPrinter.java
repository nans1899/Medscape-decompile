package org.mockito.internal.matchers.text;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.ContainsExtraTypeInfo;
import org.mockito.internal.reporting.PrintSettings;

public class MatchersPrinter {
    public String getArgumentsLine(List<ArgumentMatcher> list, PrintSettings printSettings) {
        return ValuePrinter.printValues("(", ", ", ");", applyPrintSettings(list, printSettings));
    }

    public String getArgumentsBlock(List<ArgumentMatcher> list, PrintSettings printSettings) {
        return ValuePrinter.printValues("(\n    ", ",\n    ", "\n);", applyPrintSettings(list, printSettings));
    }

    private Iterator<FormattedText> applyPrintSettings(List<ArgumentMatcher> list, PrintSettings printSettings) {
        LinkedList linkedList = new LinkedList();
        int i = 0;
        for (ArgumentMatcher next : list) {
            if (!(next instanceof ContainsExtraTypeInfo) || !printSettings.extraTypeInfoFor(i)) {
                linkedList.add(new FormattedText(MatcherToString.toString(next)));
            } else {
                linkedList.add(new FormattedText(((ContainsExtraTypeInfo) next).toStringWithType()));
            }
            i++;
        }
        return linkedList.iterator();
    }
}
