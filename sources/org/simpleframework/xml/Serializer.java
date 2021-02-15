package org.simpleframework.xml;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public interface Serializer {
    <T> T read(Class<? extends T> cls, File file) throws Exception;

    <T> T read(Class<? extends T> cls, File file, boolean z) throws Exception;

    <T> T read(Class<? extends T> cls, InputStream inputStream) throws Exception;

    <T> T read(Class<? extends T> cls, InputStream inputStream, boolean z) throws Exception;

    <T> T read(Class<? extends T> cls, Reader reader) throws Exception;

    <T> T read(Class<? extends T> cls, Reader reader, boolean z) throws Exception;

    <T> T read(Class<? extends T> cls, String str) throws Exception;

    <T> T read(Class<? extends T> cls, String str, boolean z) throws Exception;

    <T> T read(Class<? extends T> cls, InputNode inputNode) throws Exception;

    <T> T read(Class<? extends T> cls, InputNode inputNode, boolean z) throws Exception;

    <T> T read(T t, File file) throws Exception;

    <T> T read(T t, File file, boolean z) throws Exception;

    <T> T read(T t, InputStream inputStream) throws Exception;

    <T> T read(T t, InputStream inputStream, boolean z) throws Exception;

    <T> T read(T t, Reader reader) throws Exception;

    <T> T read(T t, Reader reader, boolean z) throws Exception;

    <T> T read(T t, String str) throws Exception;

    <T> T read(T t, String str, boolean z) throws Exception;

    <T> T read(T t, InputNode inputNode) throws Exception;

    <T> T read(T t, InputNode inputNode, boolean z) throws Exception;

    boolean validate(Class cls, File file) throws Exception;

    boolean validate(Class cls, File file, boolean z) throws Exception;

    boolean validate(Class cls, InputStream inputStream) throws Exception;

    boolean validate(Class cls, InputStream inputStream, boolean z) throws Exception;

    boolean validate(Class cls, Reader reader) throws Exception;

    boolean validate(Class cls, Reader reader, boolean z) throws Exception;

    boolean validate(Class cls, String str) throws Exception;

    boolean validate(Class cls, String str, boolean z) throws Exception;

    boolean validate(Class cls, InputNode inputNode) throws Exception;

    boolean validate(Class cls, InputNode inputNode, boolean z) throws Exception;

    void write(Object obj, File file) throws Exception;

    void write(Object obj, OutputStream outputStream) throws Exception;

    void write(Object obj, Writer writer) throws Exception;

    void write(Object obj, OutputNode outputNode) throws Exception;
}
