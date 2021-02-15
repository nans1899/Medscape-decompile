package org.mockito.internal.debugging;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Collection;
import org.apache.commons.io.IOUtils;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

public class InvocationsPrinter {
    public String printInvocations(Object obj) {
        Collection<Invocation> invocations = Mockito.mockingDetails(obj).getInvocations();
        Collection<Stubbing> stubbings = Mockito.mockingDetails(obj).getStubbings();
        if (!invocations.isEmpty() || !stubbings.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int i = 1;
            int i2 = 1;
            for (Invocation next : invocations) {
                if (i2 == 1) {
                    sb.append("[Mockito] Interactions of: ");
                    sb.append(obj);
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                int i3 = i2 + 1;
                sb.append(i2);
                sb.append(". ");
                sb.append(next.toString());
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append("  ");
                sb.append(next.getLocation());
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                if (next.stubInfo() != null) {
                    sb.append("   - stubbed ");
                    sb.append(next.stubInfo().stubbedAt());
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
                i2 = i3;
            }
            if (ListUtil.filter(stubbings, new ListUtil.Filter<Stubbing>() {
                public boolean isOut(Stubbing stubbing) {
                    return stubbing.wasUsed();
                }
            }).isEmpty()) {
                return sb.toString();
            }
            sb.append("[Mockito] Unused stubbings of: ");
            sb.append(obj);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            for (Stubbing next2 : stubbings) {
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(i);
                sb.append(". ");
                sb.append(next2.getInvocation());
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append("  - stubbed ");
                sb.append(next2.getInvocation().getLocation());
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                i++;
            }
            return sb.toString();
        }
        return "No interactions and stubbings found for mock: " + obj;
    }
}
