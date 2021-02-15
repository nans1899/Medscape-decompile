package org.simpleframework.xml.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.filter.PlatformFilter;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeBuilder;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.transform.Matcher;

public class Persister implements Serializer {
    private final Format format;
    private final SessionManager manager;
    private final Strategy strategy;
    private final Support support;

    public Persister() {
        this((Map) new HashMap());
    }

    public Persister(Format format2) {
        this((Strategy) new TreeStrategy(), format2);
    }

    public Persister(Map map) {
        this((Filter) new PlatformFilter(map));
    }

    public Persister(Map map, Format format2) {
        this((Filter) new PlatformFilter(map));
    }

    public Persister(Filter filter) {
        this((Strategy) new TreeStrategy(), filter);
    }

    public Persister(Filter filter, Format format2) {
        this((Strategy) new TreeStrategy(), filter, format2);
    }

    public Persister(Matcher matcher) {
        this((Strategy) new TreeStrategy(), matcher);
    }

    public Persister(Matcher matcher, Format format2) {
        this((Strategy) new TreeStrategy(), matcher, format2);
    }

    public Persister(Strategy strategy2) {
        this(strategy2, (Map) new HashMap());
    }

    public Persister(Strategy strategy2, Format format2) {
        this(strategy2, (Map) new HashMap(), format2);
    }

    public Persister(Filter filter, Matcher matcher) {
        this((Strategy) new TreeStrategy(), filter, matcher);
    }

    public Persister(Filter filter, Matcher matcher, Format format2) {
        this(new TreeStrategy(), filter, matcher, format2);
    }

    public Persister(Strategy strategy2, Map map) {
        this(strategy2, (Filter) new PlatformFilter(map));
    }

    public Persister(Strategy strategy2, Map map, Format format2) {
        this(strategy2, (Filter) new PlatformFilter(map), format2);
    }

    public Persister(Strategy strategy2, Filter filter) {
        this(strategy2, filter, new Format());
    }

    public Persister(Strategy strategy2, Filter filter, Format format2) {
        this(strategy2, filter, new EmptyMatcher(), format2);
    }

    public Persister(Strategy strategy2, Matcher matcher) {
        this(strategy2, (Filter) new PlatformFilter(), matcher);
    }

    public Persister(Strategy strategy2, Matcher matcher, Format format2) {
        this(strategy2, new PlatformFilter(), matcher, format2);
    }

    public Persister(Strategy strategy2, Filter filter, Matcher matcher) {
        this(strategy2, filter, matcher, new Format());
    }

    public Persister(Strategy strategy2, Filter filter, Matcher matcher, Format format2) {
        this.support = new Support(filter, matcher, format2);
        this.manager = new SessionManager();
        this.strategy = strategy2;
        this.format = format2;
    }

    public <T> T read(Class<? extends T> cls, String str) throws Exception {
        return read(cls, str, true);
    }

    public <T> T read(Class<? extends T> cls, File file) throws Exception {
        return read(cls, file, true);
    }

    public <T> T read(Class<? extends T> cls, InputStream inputStream) throws Exception {
        return read(cls, inputStream, true);
    }

    public <T> T read(Class<? extends T> cls, Reader reader) throws Exception {
        return read(cls, reader, true);
    }

    public <T> T read(Class<? extends T> cls, InputNode inputNode) throws Exception {
        return read(cls, inputNode, true);
    }

    public <T> T read(Class<? extends T> cls, String str, boolean z) throws Exception {
        return read(cls, (Reader) new StringReader(str), z);
    }

    public <T> T read(Class<? extends T> cls, File file, boolean z) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return read(cls, (InputStream) fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }

    public <T> T read(Class<? extends T> cls, InputStream inputStream, boolean z) throws Exception {
        return read(cls, NodeBuilder.read(inputStream), z);
    }

    public <T> T read(Class<? extends T> cls, Reader reader, boolean z) throws Exception {
        return read(cls, NodeBuilder.read(reader), z);
    }

    public <T> T read(Class<? extends T> cls, InputNode inputNode, boolean z) throws Exception {
        try {
            return read(cls, inputNode, this.manager.open(z));
        } finally {
            this.manager.close();
        }
    }

    private <T> T read(Class<? extends T> cls, InputNode inputNode, Session session) throws Exception {
        return read(cls, inputNode, (Context) new Source(this.strategy, this.support, session));
    }

    private <T> T read(Class<? extends T> cls, InputNode inputNode, Context context) throws Exception {
        return new Traverser(context).read(inputNode, (Class) cls);
    }

    public <T> T read(T t, String str) throws Exception {
        return read(t, str, true);
    }

    public <T> T read(T t, File file) throws Exception {
        return read(t, file, true);
    }

    public <T> T read(T t, InputStream inputStream) throws Exception {
        return read(t, inputStream, true);
    }

    public <T> T read(T t, Reader reader) throws Exception {
        return read(t, reader, true);
    }

    public <T> T read(T t, InputNode inputNode) throws Exception {
        return read(t, inputNode, true);
    }

    public <T> T read(T t, String str, boolean z) throws Exception {
        return read(t, (Reader) new StringReader(str), z);
    }

    public <T> T read(T t, File file, boolean z) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return read(t, (InputStream) fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }

    public <T> T read(T t, InputStream inputStream, boolean z) throws Exception {
        return read(t, NodeBuilder.read(inputStream), z);
    }

    public <T> T read(T t, Reader reader, boolean z) throws Exception {
        return read(t, NodeBuilder.read(reader), z);
    }

    public <T> T read(T t, InputNode inputNode, boolean z) throws Exception {
        try {
            return read(t, inputNode, this.manager.open(z));
        } finally {
            this.manager.close();
        }
    }

    private <T> T read(T t, InputNode inputNode, Session session) throws Exception {
        return read(t, inputNode, (Context) new Source(this.strategy, this.support, session));
    }

    private <T> T read(T t, InputNode inputNode, Context context) throws Exception {
        return new Traverser(context).read(inputNode, (Object) t);
    }

    public boolean validate(Class cls, String str) throws Exception {
        return validate(cls, str, true);
    }

    public boolean validate(Class cls, File file) throws Exception {
        return validate(cls, file, true);
    }

    public boolean validate(Class cls, InputStream inputStream) throws Exception {
        return validate(cls, inputStream, true);
    }

    public boolean validate(Class cls, Reader reader) throws Exception {
        return validate(cls, reader, true);
    }

    public boolean validate(Class cls, InputNode inputNode) throws Exception {
        return validate(cls, inputNode, true);
    }

    public boolean validate(Class cls, String str, boolean z) throws Exception {
        return validate(cls, (Reader) new StringReader(str), z);
    }

    public boolean validate(Class cls, File file, boolean z) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return validate(cls, (InputStream) fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }

    public boolean validate(Class cls, InputStream inputStream, boolean z) throws Exception {
        return validate(cls, NodeBuilder.read(inputStream), z);
    }

    public boolean validate(Class cls, Reader reader, boolean z) throws Exception {
        return validate(cls, NodeBuilder.read(reader), z);
    }

    public boolean validate(Class cls, InputNode inputNode, boolean z) throws Exception {
        try {
            return validate(cls, inputNode, this.manager.open(z));
        } finally {
            this.manager.close();
        }
    }

    private boolean validate(Class cls, InputNode inputNode, Session session) throws Exception {
        return validate(cls, inputNode, (Context) new Source(this.strategy, this.support, session));
    }

    private boolean validate(Class cls, InputNode inputNode, Context context) throws Exception {
        return new Traverser(context).validate(inputNode, cls);
    }

    public void write(Object obj, OutputNode outputNode) throws Exception {
        try {
            write(obj, outputNode, this.manager.open());
        } finally {
            this.manager.close();
        }
    }

    private void write(Object obj, OutputNode outputNode, Session session) throws Exception {
        write(obj, outputNode, (Context) new Source(this.strategy, this.support, session));
    }

    private void write(Object obj, OutputNode outputNode, Context context) throws Exception {
        new Traverser(context).write(outputNode, obj);
    }

    public void write(Object obj, File file) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            write(obj, (OutputStream) fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    public void write(Object obj, OutputStream outputStream) throws Exception {
        write(obj, outputStream, "utf-8");
    }

    public void write(Object obj, OutputStream outputStream, String str) throws Exception {
        write(obj, (Writer) new OutputStreamWriter(outputStream, str));
    }

    public void write(Object obj, Writer writer) throws Exception {
        write(obj, NodeBuilder.write(writer, this.format));
    }
}
