package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ObservableInputStream extends ProxyInputStream {
    private final List<Observer> observers = new ArrayList();

    public static abstract class Observer {
        public void closed() throws IOException {
        }

        public void data(int i) throws IOException {
        }

        public void data(byte[] bArr, int i, int i2) throws IOException {
        }

        public void finished() throws IOException {
        }

        public void error(IOException iOException) throws IOException {
            throw iOException;
        }
    }

    public ObservableInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public void add(Observer observer) {
        this.observers.add(observer);
    }

    public void remove(Observer observer) {
        this.observers.remove(observer);
    }

    public void removeAllObservers() {
        this.observers.clear();
    }

    public int read() throws IOException {
        int i;
        try {
            i = super.read();
            e = null;
        } catch (IOException e) {
            e = e;
            i = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (i == -1) {
            noteFinished();
        } else {
            noteDataByte(i);
        }
        return i;
    }

    public int read(byte[] bArr) throws IOException {
        int i;
        try {
            i = super.read(bArr);
            e = null;
        } catch (IOException e) {
            e = e;
            i = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (i == -1) {
            noteFinished();
        } else if (i > 0) {
            noteDataBytes(bArr, 0, i);
        }
        return i;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        try {
            i3 = super.read(bArr, i, i2);
            e = null;
        } catch (IOException e) {
            e = e;
            i3 = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (i3 == -1) {
            noteFinished();
        } else if (i3 > 0) {
            noteDataBytes(bArr, i, i3);
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    public void noteDataBytes(byte[] bArr, int i, int i2) throws IOException {
        for (Observer data : getObservers()) {
            data.data(bArr, i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void noteFinished() throws IOException {
        for (Observer finished : getObservers()) {
            finished.finished();
        }
    }

    /* access modifiers changed from: protected */
    public void noteDataByte(int i) throws IOException {
        for (Observer data : getObservers()) {
            data.data(i);
        }
    }

    /* access modifiers changed from: protected */
    public void noteError(IOException iOException) throws IOException {
        for (Observer error : getObservers()) {
            error.error(iOException);
        }
    }

    /* access modifiers changed from: protected */
    public void noteClosed() throws IOException {
        for (Observer closed : getObservers()) {
            closed.closed();
        }
    }

    /* access modifiers changed from: protected */
    public List<Observer> getObservers() {
        return this.observers;
    }

    public void close() throws IOException {
        try {
            super.close();
            e = null;
        } catch (IOException e) {
            e = e;
        }
        if (e == null) {
            noteClosed();
        } else {
            noteError(e);
        }
    }

    public void consume() throws IOException {
        do {
        } while (read(new byte[8192]) != -1);
    }
}
