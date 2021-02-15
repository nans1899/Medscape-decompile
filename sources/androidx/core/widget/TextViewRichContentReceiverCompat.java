package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.Spanned;
import android.widget.TextView;
import java.util.Collections;
import java.util.Set;
import org.apache.commons.io.IOUtils;

public abstract class TextViewRichContentReceiverCompat extends RichContentReceiverCompat<TextView> {
    private static final Set<String> MIME_TYPES_ALL_TEXT = Collections.singleton("text/*");

    public Set<String> getSupportedMimeTypes() {
        return MIME_TYPES_ALL_TEXT;
    }

    public boolean onReceive(TextView textView, ClipData clipData, int i, int i2) {
        CharSequence charSequence;
        if (i == 1 && !supports(clipData.getDescription())) {
            return false;
        }
        Editable editable = (Editable) textView.getText();
        Context context = textView.getContext();
        boolean z = false;
        for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
            if ((i2 & 1) != 0) {
                charSequence = clipData.getItemAt(i3).coerceToText(context);
                if (charSequence instanceof Spanned) {
                    charSequence = charSequence.toString();
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                charSequence = clipData.getItemAt(i3).coerceToStyledText(context);
            } else {
                charSequence = clipData.getItemAt(i3).coerceToText(context);
            }
            if (charSequence != null) {
                if (!z) {
                    int selectionStart = Selection.getSelectionStart(editable);
                    int selectionEnd = Selection.getSelectionEnd(editable);
                    int max = Math.max(0, Math.min(selectionStart, selectionEnd));
                    int max2 = Math.max(0, Math.max(selectionStart, selectionEnd));
                    Selection.setSelection(editable, max2);
                    editable.replace(max, max2, charSequence);
                    z = true;
                } else {
                    editable.insert(Selection.getSelectionEnd(editable), IOUtils.LINE_SEPARATOR_UNIX);
                    editable.insert(Selection.getSelectionEnd(editable), charSequence);
                }
            }
        }
        return z;
    }
}
