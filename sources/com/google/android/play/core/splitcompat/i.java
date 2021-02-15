package com.google.android.play.core.splitcompat;

import android.util.Log;
import com.google.android.play.core.internal.cd;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

final class i implements k {
    final /* synthetic */ Set a;
    final /* synthetic */ r b;
    final /* synthetic */ ZipFile c;

    i(Set set, r rVar, ZipFile zipFile) {
        this.a = set;
        this.b = rVar;
        this.c = zipFile;
    }

    public final void a(l lVar, File file, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        this.a.add(file);
        if (!z) {
            Log.i("SplitCompat", String.format("NativeLibraryExtractor: split '%s' has native library '%s' that does not exist; extracting from '%s!%s' to '%s'", new Object[]{this.b.b(), lVar.a, this.b.a().getAbsolutePath(), lVar.b.getName(), file.getAbsolutePath()}));
            ZipFile zipFile = this.c;
            ZipEntry zipEntry = lVar.b;
            int i = m.a;
            byte[] bArr = new byte[4096];
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            try {
                fileOutputStream = new FileOutputStream(file);
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th2) {
                        cd.a(th, th2);
                    }
                }
                throw th;
            }
        } else {
            return;
        }
        throw th;
    }
}
