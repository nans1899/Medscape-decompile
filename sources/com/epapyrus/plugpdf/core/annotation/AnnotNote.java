package com.epapyrus.plugpdf.core.annotation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.epapyrus.plugpdf.core.ResourceManager;

public class AnnotNote extends BaseAnnot {
    /* access modifiers changed from: private */
    public String mContents;
    protected Dialog mDialog;
    private AnnotNoteListner mListener;
    private PointF mPos;
    /* access modifiers changed from: private */
    public String mTitle;

    public interface AnnotNoteListner {
        void onAddedContents(PointF pointF, String str, String str2, boolean z);
    }

    public AnnotNote(Context context) {
        super(context, "NOTE");
    }

    public void setListener(AnnotNoteListner annotNoteListner) {
        this.mListener = annotNoteListner;
    }

    /* access modifiers changed from: protected */
    public void createDialog(final Context context) {
        Dialog dialog = new Dialog(context);
        this.mDialog = dialog;
        dialog.requestWindowFeature(1);
        this.mDialog.setContentView(ResourceManager.getLayoutId(context, "annot_note"));
        this.mDialog.setCancelable(true);
        ((EditText) this.mDialog.findViewById(ResourceManager.getId(context, "annot_note_title"))).setText(this.mTitle);
        ((EditText) this.mDialog.findViewById(ResourceManager.getId(context, "annot_note_contents"))).setText(this.mContents);
        ((Button) this.mDialog.findViewById(ResourceManager.getId(context, "annot_note_cancel"))).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((EditText) AnnotNote.this.mDialog.findViewById(ResourceManager.getId(context, "annot_note_title"))).setText(AnnotNote.this.mTitle);
                ((EditText) AnnotNote.this.mDialog.findViewById(ResourceManager.getId(context, "annot_note_contents"))).setText(AnnotNote.this.mContents);
                AnnotNote.this.mDialog.dismiss();
            }
        });
        ((Button) this.mDialog.findViewById(ResourceManager.getId(context, "annot_note_add"))).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AnnotNote annotNote = AnnotNote.this;
                String unused = annotNote.mTitle = ((EditText) annotNote.mDialog.findViewById(ResourceManager.getId(context, "annot_note_title"))).getText().toString();
                AnnotNote annotNote2 = AnnotNote.this;
                String unused2 = annotNote2.mContents = ((EditText) annotNote2.mDialog.findViewById(ResourceManager.getId(context, "annot_note_contents"))).getText().toString();
                AnnotNote.this.addContents();
                AnnotNote.this.mDialog.dismiss();
            }
        });
    }

    public void setPos(PointF pointF) {
        this.mPos = pointF;
    }

    public PointF getPos() {
        return this.mPos;
    }

    public void setPos(float f, float f2) {
        setPos(new PointF(f, f2));
    }

    public void showContentsBox(Context context) {
        if (this.mDialog == null) {
            createDialog(context);
        }
        if (isValid()) {
            ((Button) this.mDialog.findViewById(ResourceManager.getId(getContext(), "annot_note_add"))).setText("Update");
        }
        this.mDialog.show();
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getContents() {
        return this.mContents;
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public void setContents(String str) {
        this.mContents = str;
    }

    /* access modifiers changed from: private */
    public void addContents() {
        if (this.mDialog != null && this.mListener != null) {
            this.mListener.onAddedContents(this.mPos, this.mTitle, this.mContents, isValid());
        }
    }
}
