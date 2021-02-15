package androidx.core.widget;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import java.util.Set;

public abstract class RichContentReceiverCompat<T extends View> {
    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
    public static final int SOURCE_CLIPBOARD = 0;
    public static final int SOURCE_INPUT_METHOD = 1;
    private static final String TAG = "RichContentReceiver";

    public abstract Set<String> getSupportedMimeTypes();

    public abstract boolean onReceive(T t, ClipData clipData, int i, int i2);

    public final boolean supports(ClipDescription clipDescription) {
        for (String hasMimeType : getSupportedMimeTypes()) {
            if (clipDescription.hasMimeType(hasMimeType)) {
                return true;
            }
        }
        return false;
    }

    public final void populateEditorInfoContentMimeTypes(InputConnection inputConnection, EditorInfo editorInfo) {
        if (inputConnection != null && editorInfo != null) {
            EditorInfoCompat.setContentMimeTypes(editorInfo, (String[]) getSupportedMimeTypes().toArray(new String[0]));
        }
    }

    public final InputConnectionCompat.OnCommitContentListener buildOnCommitContentListener(final T t) {
        return new InputConnectionCompat.OnCommitContentListener() {
            public boolean onCommitContent(InputContentInfoCompat inputContentInfoCompat, int i, Bundle bundle) {
                ClipDescription description = inputContentInfoCompat.getDescription();
                if ((i & 1) != 0) {
                    try {
                        inputContentInfoCompat.requestPermission();
                    } catch (Exception e) {
                        Log.w(RichContentReceiverCompat.TAG, "Can't insert from IME; requestPermission() failed: " + e);
                        return false;
                    }
                }
                return RichContentReceiverCompat.this.onReceive(t, new ClipData(description, new ClipData.Item(inputContentInfoCompat.getContentUri())), 1, 0);
            }
        };
    }
}
