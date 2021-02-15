package com.epapyrus.plugpdf.core.annotation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.google.logging.type.LogSeverity;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileDialog extends Dialog {
    private Button mAttachBtn;
    /* access modifiers changed from: private */
    public byte[] mAttachedFile;
    private Button mCancelBtn;
    private Button mDownloadBtn;
    /* access modifiers changed from: private */
    public String mFileName;
    /* access modifiers changed from: private */
    public String mFilePath;
    /* access modifiers changed from: private */
    public TextView mFileView;
    private Button mOkBtn;
    /* access modifiers changed from: private */
    public TextView mSizeView;
    private final String[] units = {"Byte", "KB", "MB", "GB", "TB"};

    public void setAttachedFileData(byte[] bArr) {
        this.mAttachedFile = bArr;
    }

    /* access modifiers changed from: private */
    public String getDateLength(long j) {
        float f = (float) j;
        int i = 0;
        while (f > 1024.0f) {
            f /= 1024.0f;
            i++;
            if (i == 4) {
                break;
            }
        }
        String format = String.format("%.2f", new Object[]{Float.valueOf(f)});
        return format + this.units[i];
    }

    public FileDialog(final Context context, String str, byte[] bArr) {
        super(context);
        if (!(str == null || bArr == null)) {
            this.mFileName = str;
            this.mAttachedFile = bArr;
        }
        requestWindowFeature(1);
        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        LinearLayout linearLayout3 = new LinearLayout(getContext());
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        linearLayout.setPadding(50, 50, 50, 50);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout3);
        linearLayout.addView(relativeLayout);
        linearLayout.setBackgroundColor(Color.rgb(245, 236, 206));
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.setOrientation(1);
        TextView textView = new TextView(getContext());
        TextView textView2 = new TextView(getContext());
        this.mFileView = textView2;
        textView2.setTextSize(15.0f);
        String str2 = this.mFileName;
        if (str2 != null) {
            this.mFileView.setText(str2);
        }
        TextView textView3 = new TextView(getContext());
        this.mSizeView = new TextView(getContext());
        this.mFileView.setTextSize(15.0f);
        this.mCancelBtn = new Button(getContext());
        this.mOkBtn = new Button(getContext());
        this.mAttachBtn = new Button(getContext());
        this.mDownloadBtn = new Button(getContext());
        linearLayout2.addView(textView);
        linearLayout2.addView(this.mFileView);
        linearLayout2.setOrientation(1);
        textView.setTextSize(25.0f);
        textView.setText("File Name");
        textView.setLines(1);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mFileView.setPadding(50, 0, 0, 0);
        linearLayout3.addView(textView3);
        linearLayout3.addView(this.mSizeView);
        linearLayout3.setOrientation(1);
        textView3.setTextSize(25.0f);
        textView3.setText("Size");
        textView3.setLines(1);
        textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mSizeView.setPadding(50, 0, 0, 0);
        relativeLayout.addView(this.mCancelBtn);
        relativeLayout.addView(this.mOkBtn);
        relativeLayout.addView(this.mAttachBtn);
        relativeLayout.addView(this.mDownloadBtn);
        relativeLayout.setClickable(true);
        this.mCancelBtn.setText("Cancel");
        this.mOkBtn.setText("OK");
        this.mOkBtn.setClickable(true);
        this.mCancelBtn.setClickable(true);
        this.mAttachBtn.setText("Attach File");
        this.mDownloadBtn.setText("Download");
        this.mDownloadBtn.setClickable(true);
        int nextInt = new Random().nextInt();
        this.mCancelBtn.setId(nextInt);
        this.mOkBtn.setId(nextInt + 1);
        this.mAttachBtn.setId(nextInt + 2);
        relativeLayout.setPadding(0, 100, 0, 0);
        this.mDownloadBtn.setId(nextInt + 3);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(0, this.mOkBtn.getId());
        this.mCancelBtn.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(11);
        this.mOkBtn.setLayoutParams(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(9);
        this.mAttachBtn.setLayoutParams(layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(0, this.mCancelBtn.getId());
        byte[] bArr2 = this.mAttachedFile;
        if (bArr2 != null) {
            this.mSizeView.setText(getDateLength((long) bArr2.length));
            this.mFileView.setText(this.mFileName);
        }
        this.mDownloadBtn.setLayoutParams(layoutParams4);
        this.mCancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FileDialog.this.dismiss();
            }
        });
        this.mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FileDialog.this.mFileName != null) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + FileDialog.this.mFileName);
                        fileOutputStream.write(FileDialog.this.mAttachedFile);
                        fileOutputStream.close();
                        Toast.makeText(FileDialog.this.getContext(), "file is download", 1).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                FileDialog.this.dismiss();
            }
        });
        this.mAttachBtn.setOnClickListener(new View.OnClickListener() {
            private Button buttonup;
            /* access modifiers changed from: private */
            public File curFolder;
            private ListView dialogListView;
            /* access modifiers changed from: private */
            public List<String> fileList = new ArrayList();
            /* access modifiers changed from: private */
            public int filePathLength;
            private List<String> filenameList = new ArrayList();
            private File root;
            private TextView textFolder;

            public void onClick(View view) {
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
                    this.root = file;
                    this.curFolder = file;
                    LinearLayout linearLayout = new LinearLayout(FileDialog.this.getContext());
                    linearLayout.setOrientation(1);
                    linearLayout.setPadding(20, 20, 20, 20);
                    linearLayout.setMinimumWidth(LogSeverity.CRITICAL_VALUE);
                    this.buttonup = new Button(FileDialog.this.getContext());
                    final Dialog dialog = new Dialog(FileDialog.this.getContext());
                    this.dialogListView = new ListView(FileDialog.this.getContext());
                    this.textFolder = new TextView(FileDialog.this.getContext());
                    linearLayout.addView(this.buttonup);
                    linearLayout.addView(this.textFolder);
                    linearLayout.addView(this.dialogListView);
                    dialog.setContentView(linearLayout);
                    dialog.setTitle("Custon Dialog");
                    dialog.setCancelable(true);
                    dialog.setCanceledOnTouchOutside(true);
                    this.buttonup.setClickable(true);
                    this.buttonup.setText("Parent folder");
                    this.buttonup.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            AnonymousClass3 r2 = AnonymousClass3.this;
                            r2.listDir(r2.curFolder.getParentFile());
                        }
                    });
                    this.dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                            File file = new File((String) AnonymousClass3.this.fileList.get(i));
                            if (file.isDirectory()) {
                                AnonymousClass3.this.listDir(file);
                                return;
                            }
                            Context context = context;
                            Toast.makeText(context, file.toString() + " selected", 1).show();
                            String unused = FileDialog.this.mFilePath = file.toString();
                            String unused2 = FileDialog.this.mFileName = FileDialog.this.mFilePath.substring(AnonymousClass3.this.filePathLength + 1);
                            FileDialog.this.mFileView.setText(FileDialog.this.mFileName);
                            FileDialog.this.mSizeView.setText(FileDialog.this.getDateLength(new File(file.toString()).length()));
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } catch (Exception e) {
                    Log.e("PlugPDF", "[ERROR] AnnotFile.createDialog ", e);
                }
                listDir(this.curFolder);
            }

            /* access modifiers changed from: private */
            public void listDir(File file) {
                if (file.equals(this.root)) {
                    this.buttonup.setEnabled(false);
                } else {
                    this.buttonup.setEnabled(true);
                }
                this.curFolder = file;
                this.textFolder.setText(file.getPath());
                File[] listFiles = file.listFiles();
                this.fileList.clear();
                this.filenameList.clear();
                this.filePathLength = file.getPath().length();
                for (File file2 : listFiles) {
                    String unused = FileDialog.this.mFileName = file2.toString().substring(this.filePathLength + 1);
                    this.fileList.add(file2.toString());
                    this.filenameList.add(FileDialog.this.mFileName);
                }
                this.dialogListView.setAdapter(new ArrayAdapter(FileDialog.this.getContext(), 17367046, this.filenameList));
            }
        });
        setContentView(linearLayout);
    }

    public void setOkBtnListener(View.OnClickListener onClickListener) {
        this.mOkBtn.setOnClickListener(onClickListener);
    }

    public String getFilePath() {
        return this.mFilePath;
    }
}
