package org.mockito.internal.exceptions.util;

import java.util.List;
import org.apache.commons.io.IOUtils;
import org.mockito.internal.exceptions.VerificationAwareInvocation;

public class ScenarioPrinter {
    public String print(List<VerificationAwareInvocation> list) {
        if (list.size() == 1) {
            return "Actually, above is the only interaction with this mock.";
        }
        StringBuilder sb = new StringBuilder("***\nFor your reference, here is the list of all invocations ([?] - means unverified).\n");
        int i = 0;
        for (VerificationAwareInvocation next : list) {
            i++;
            sb.append(i);
            sb.append(". ");
            if (!next.isVerified()) {
                sb.append("[?]");
            }
            sb.append(next.getLocation());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        return sb.toString();
    }
}
