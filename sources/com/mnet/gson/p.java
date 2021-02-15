package com.mnet.gson;

import java.io.IOException;
import java.io.Reader;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.k;

public final class p {
    public k a(Reader reader) {
        try {
            h hVar = new h(reader);
            k a = a(hVar);
            if (!a.l()) {
                if (hVar.f() != i.END_DOCUMENT) {
                    throw new t("Did not consume the entire document.");
                }
            }
            return a;
        } catch (k e) {
            throw new t((Throwable) e);
        } catch (IOException e2) {
            throw new l((Throwable) e2);
        } catch (NumberFormatException e3) {
            throw new t((Throwable) e3);
        }
    }

    public k a(h hVar) {
        boolean q = hVar.q();
        hVar.a(true);
        try {
            k a = com.mnet.gson.internal.i.a(hVar);
            hVar.a(q);
            return a;
        } catch (StackOverflowError e) {
            throw new o("Failed parsing JSON source: " + hVar + " to Json", e);
        } catch (OutOfMemoryError e2) {
            throw new o("Failed parsing JSON source: " + hVar + " to Json", e2);
        } catch (Throwable th) {
            hVar.a(q);
            throw th;
        }
    }
}
