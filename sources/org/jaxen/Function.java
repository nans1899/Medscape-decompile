package org.jaxen;

import java.util.List;

public interface Function {
    Object call(Context context, List list) throws FunctionCallException;
}
