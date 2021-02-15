package org.apache.commons.io.file;

import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public abstract class SimplePathVisitor extends SimpleFileVisitor<Path> {
    protected SimplePathVisitor() {
    }
}
