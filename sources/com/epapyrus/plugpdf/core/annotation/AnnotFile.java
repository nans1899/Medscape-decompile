package com.epapyrus.plugpdf.core.annotation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;

public class AnnotFile extends BaseAnnot {
    private byte[] mAttachedFile;
    private String mAuthor;
    private String mContents;
    protected FileDialog mDialog;
    /* access modifiers changed from: private */
    public String mFileName;
    private AnnotFileListner mListener;
    private PointF mPos;
    private String mTitle;

    public interface AnnotFileListner {
        void onAddedContents(PointF pointF, String str, boolean z);
    }

    public AnnotFile(Context context) {
        super(context, "FILE_ATTACHMENT");
    }

    public void setAttachedFileData(byte[] bArr) {
        if (bArr != null) {
            this.mAttachedFile = bArr;
            FileDialog fileDialog = this.mDialog;
            if (fileDialog != null) {
                fileDialog.setAttachedFileData(bArr);
            }
        }
    }

    public void showContentsBox(Context context) {
        if (this.mDialog == null) {
            createDialog(context);
        }
        this.mDialog.show();
    }

    public void setListener(AnnotFileListner annotFileListner) {
        this.mListener = annotFileListner;
    }

    /* access modifiers changed from: protected */
    public void createDialog(Context context) {
        FileDialog fileDialog = new FileDialog(context, this.mFileName, this.mAttachedFile);
        this.mDialog = fileDialog;
        fileDialog.setCancelable(true);
        this.mDialog.setOkBtnListener(new View.OnClickListener() {
            public void onClick(View view) {
                String filePath = AnnotFile.this.mDialog.getFilePath();
                if (filePath != null) {
                    String unused = AnnotFile.this.mFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
                    AnnotFile.this.addContents(filePath);
                }
                AnnotFile.this.mDialog.dismiss();
            }
        });
    }

    public void setPos(PointF pointF) {
        this.mPos = pointF;
    }

    public PointF getPos(PointF pointF) {
        return this.mPos;
    }

    public void setPos(float f, float f2) {
        setPos(new PointF(f, f2));
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
    public void addContents(String str) {
        if (this.mDialog != null && this.mListener != null) {
            this.mListener.onAddedContents(this.mPos, str, isValid());
        }
    }

    public void setAuthor(String str) {
        this.mAuthor = str;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public String getFileName() {
        return this.mFileName;
    }
}
