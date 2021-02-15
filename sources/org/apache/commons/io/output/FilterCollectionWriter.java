package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOIndexedException;

public class FilterCollectionWriter extends Writer {
    protected final Collection<Writer> EMPTY_WRITERS;
    protected final Collection<Writer> writers;

    protected FilterCollectionWriter(Collection<Writer> collection) {
        List emptyList = Collections.emptyList();
        this.EMPTY_WRITERS = emptyList;
        this.writers = collection == null ? emptyList : collection;
    }

    protected FilterCollectionWriter(Writer... writerArr) {
        List emptyList = Collections.emptyList();
        this.EMPTY_WRITERS = emptyList;
        this.writers = writerArr != null ? Arrays.asList(writerArr) : emptyList;
    }

    public Writer append(char c) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.append(c);
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i, e));
                }
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            return this;
        }
        throw new IOExceptionList(arrayList);
    }

    public Writer append(CharSequence charSequence) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.append(charSequence);
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i, e));
                }
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            return this;
        }
        throw new IOExceptionList(arrayList);
    }

    public Writer append(CharSequence charSequence, int i, int i2) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.append(charSequence, i, i2);
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i3, e));
                }
            }
            i3++;
        }
        if (arrayList.isEmpty()) {
            return this;
        }
        throw new IOExceptionList(arrayList);
    }

    public void close() throws IOException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.close();
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i, e));
                }
            }
            i++;
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(arrayList);
        }
    }

    public void flush() throws IOException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.flush();
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i, e));
                }
            }
            i++;
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(arrayList);
        }
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.write(cArr, i, i2);
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i3, e));
                }
            }
            i3++;
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(arrayList);
        }
    }

    public void write(char[] cArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.write(cArr);
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i, e));
                }
            }
            i++;
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(arrayList);
        }
    }

    public void write(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.write(i);
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i2, e));
                }
            }
            i2++;
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(arrayList);
        }
    }

    public void write(String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.write(str);
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i, e));
                }
            }
            i++;
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(arrayList);
        }
    }

    public void write(String str, int i, int i2) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        for (Writer next : this.writers) {
            if (next != null) {
                try {
                    next.write(str, i, i2);
                } catch (IOException e) {
                    arrayList.add(new IOIndexedException(i3, e));
                }
            }
            i3++;
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(arrayList);
        }
    }
}
