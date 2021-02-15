package org.mockito.internal.debugging;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.mockito.MockitoDebugger;
import org.mockito.internal.invocation.UnusedStubsFinder;
import org.mockito.internal.invocation.finder.AllInvocationsFinder;
import org.mockito.invocation.Invocation;

public class MockitoDebuggerImpl implements MockitoDebugger {
    private final UnusedStubsFinder unusedStubsFinder = new UnusedStubsFinder();

    @Deprecated
    public String printInvocations(Object... objArr) {
        String str = (("" + line("********************************")) + line("*** Mockito interactions log ***")) + line("********************************");
        for (Invocation next : AllInvocationsFinder.find(Arrays.asList(objArr))) {
            StringBuilder sb = new StringBuilder();
            sb.append(str + line(next.toString()));
            sb.append(line(" invoked: " + next.getLocation()));
            str = sb.toString();
            if (next.stubInfo() != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(line(" stubbed: " + next.stubInfo().stubbedAt().toString()));
                str = sb2.toString();
            }
        }
        List<Invocation> find = this.unusedStubsFinder.find(Arrays.asList(objArr));
        if (find.isEmpty()) {
            return print(str);
        }
        String str2 = ((str + line("********************************")) + line("***       Unused stubs       ***")) + line("********************************");
        for (Invocation next2 : find) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2 + line(next2.toString()));
            sb3.append(line(" stubbed: " + next2.getLocation()));
            str2 = sb3.toString();
        }
        return print(str2);
    }

    private String line(String str) {
        return str + IOUtils.LINE_SEPARATOR_UNIX;
    }

    private String print(String str) {
        System.out.println(str);
        return str;
    }
}
