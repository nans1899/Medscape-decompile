package org.liquidplayer.javascript;

import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import java.util.Iterator;

class JSIterator<T> extends JSObjectWrapper implements Iterator<T> {
    private JSIterator<T>.Next next = _jsnext();

    public class Next extends JSObjectWrapper {
        public /* bridge */ /* synthetic */ JSObject getJSObject() {
            return super.getJSObject();
        }

        Next(JSObject jSObject) {
            super(jSObject);
        }

        /* access modifiers changed from: package-private */
        public boolean done() {
            return getJSObject().property("done").toBoolean().booleanValue();
        }

        public JSValue value() {
            return getJSObject().property("value");
        }
    }

    JSIterator(JSObject jSObject) {
        super(jSObject);
    }

    private JSIterator<T>.Next _jsnext() {
        return new Next(getJSObject().property(JSONAPISpecConstants.NEXT).toFunction().call(getJSObject(), new Object[0]).toObject());
    }

    /* access modifiers changed from: package-private */
    public JSIterator<T>.Next jsnext() {
        JSIterator<T>.Next next2 = this.next;
        this.next = _jsnext();
        return next2;
    }

    public T next() {
        return jsnext().value();
    }

    public boolean hasNext() {
        return !this.next.done();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
