package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.InputNode;

interface Repeater extends Converter {
    Object read(InputNode inputNode, Object obj) throws Exception;
}
