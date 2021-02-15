package com.epapyrus.plugpdf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import com.epapyrus.plugpdf.OutlineAdapter;
import com.epapyrus.plugpdf.core.BaseReaderControl;
import com.epapyrus.plugpdf.core.OutlineItem;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.viewer.ReaderView;
import java.util.ArrayList;
import java.util.List;

public class SimpleReaderControlPanel {
    private View mBrightnessPanel;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public BaseReaderControl mController;
    /* access modifiers changed from: private */
    public boolean mIsModifiedOutline = false;
    /* access modifiers changed from: private */
    public LayoutInflater mLayoutInflater;
    /* access modifiers changed from: private */
    public View mOutlinePanel;
    private View mPageDisplayPanel;
    /* access modifiers changed from: private */
    public SimpleReaderControlView mParent;
    private PopupWindow mPopupPanel;
    /* access modifiers changed from: private */
    public boolean outlineEditMode;
    /* access modifiers changed from: private */
    public List<OutlineItem> outlineItemList;
    /* access modifiers changed from: private */
    public OutlineAdapter.OutlintEditListener outlintEditListener = new OutlineAdapter.OutlintEditListener() {
        public void onClickToRemove(List<OutlineItem> list, int i) {
            SimpleReaderControlPanel.this.removeOutlineItem(list, i);
        }

        public void onClickToAdd(List<OutlineItem> list, int i, String str, boolean z) {
            SimpleReaderControlPanel simpleReaderControlPanel = SimpleReaderControlPanel.this;
            simpleReaderControlPanel.addOutlineItem(list, i, str, ((ReaderView) simpleReaderControlPanel.mController).getPageIdx(), z);
        }
    };

    public enum PanelType {
        DISPLAYMODE,
        BRIGHTNESS,
        OUTLINE
    }

    public SimpleReaderControlPanel(Context context, SimpleReaderControlView simpleReaderControlView, BaseReaderControl baseReaderControl) {
        this.mContext = context;
        this.mParent = simpleReaderControlView;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mController = baseReaderControl;
        createPageDisplayPanel();
        createBrightnessPanel();
        createOutlinePanel();
    }

    private void createOutlinePanel() {
        View inflate = this.mLayoutInflater.inflate(R.layout.panel_outline, this.mParent, false);
        this.mOutlinePanel = inflate;
        final ListView listView = (ListView) inflate.findViewById(R.id.panel_outline_list);
        listView.setAdapter((ListAdapter) null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                /*
                    r0 = this;
                    com.epapyrus.plugpdf.SimpleReaderControlPanel r2 = com.epapyrus.plugpdf.SimpleReaderControlPanel.this
                    boolean r2 = r2.outlineEditMode
                    if (r2 != 0) goto L_0x0024
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    com.epapyrus.plugpdf.core.OutlineItem r1 = (com.epapyrus.plugpdf.core.OutlineItem) r1
                    com.epapyrus.plugpdf.SimpleReaderControlPanel r2 = com.epapyrus.plugpdf.SimpleReaderControlPanel.this
                    com.epapyrus.plugpdf.core.BaseReaderControl r2 = r2.mController
                    int r1 = r1.getPageIdx()
                    r2.goToPage(r1)
                    com.epapyrus.plugpdf.SimpleReaderControlPanel r1 = com.epapyrus.plugpdf.SimpleReaderControlPanel.this
                    r1.hide()
                L_0x0024:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.SimpleReaderControlPanel.AnonymousClass2.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
        final ToggleButton toggleButton = (ToggleButton) this.mOutlinePanel.findViewById(R.id.panel_outline_add);
        final ToggleButton toggleButton2 = (ToggleButton) this.mOutlinePanel.findViewById(R.id.panel_outline_remove);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SimpleReaderControlPanel.this.outlineItemList.size() == 0) {
                    View inflate = LayoutInflater.from(SimpleReaderControlPanel.this.mContext).inflate(R.layout.outline_dialog, SimpleReaderControlPanel.this.mParent, false);
                    final EditText editText = (EditText) inflate.findViewById(R.id.outline_dialog_text_field);
                    final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.outline_dialog_child_check_box);
                    final InputMethodManager inputMethodManager = (InputMethodManager) SimpleReaderControlPanel.this.mContext.getSystemService("input_method");
                    inputMethodManager.showSoftInput(editText, 2);
                    AlertDialog.Builder builder = new AlertDialog.Builder(SimpleReaderControlPanel.this.mContext);
                    builder.setView(inflate);
                    builder.setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((OutlineAdapter) listView.getAdapter()).setEnableAdd(true);
                            SimpleReaderControlPanel.this.addOutlineItem(SimpleReaderControlPanel.this.outlineItemList, 0, editText.getText().toString(), ((ReaderView) SimpleReaderControlPanel.this.mController).getPageIdx(), checkBox.isChecked());
                            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        }
                    });
                    builder.create().show();
                    return;
                }
                boolean unused = SimpleReaderControlPanel.this.outlineEditMode = toggleButton.isChecked();
                toggleButton2.setChecked(false);
                OutlineAdapter outlineAdapter = new OutlineAdapter(SimpleReaderControlPanel.this.mLayoutInflater, SimpleReaderControlPanel.this.outlineItemList, SimpleReaderControlPanel.this.outlintEditListener);
                outlineAdapter.setEnableAdd(SimpleReaderControlPanel.this.outlineEditMode);
                ((ListView) SimpleReaderControlPanel.this.mOutlinePanel.findViewById(R.id.panel_outline_list)).setAdapter(outlineAdapter);
            }
        });
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = SimpleReaderControlPanel.this.outlineEditMode = toggleButton2.isChecked();
                toggleButton.setChecked(false);
                OutlineAdapter outlineAdapter = new OutlineAdapter(SimpleReaderControlPanel.this.mLayoutInflater, SimpleReaderControlPanel.this.outlineItemList, SimpleReaderControlPanel.this.outlintEditListener);
                outlineAdapter.setEnableRemove(SimpleReaderControlPanel.this.outlineEditMode);
                ((ListView) SimpleReaderControlPanel.this.mOutlinePanel.findViewById(R.id.panel_outline_list)).setAdapter(outlineAdapter);
            }
        });
    }

    private void createPageDisplayPanel() {
        View inflate = this.mLayoutInflater.inflate(R.layout.panel_doc_flow, this.mParent, false);
        this.mPageDisplayPanel = inflate;
        ((Button) inflate.findViewById(R.id.panel_display_horizontal)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlPanel.this.mParent.setHorizontalMode();
                SimpleReaderControlPanel.this.hide();
            }
        });
        ((Button) this.mPageDisplayPanel.findViewById(R.id.panel_display_vertical)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlPanel.this.mParent.setVerticalMode();
                SimpleReaderControlPanel.this.hide();
            }
        });
        ((Button) this.mPageDisplayPanel.findViewById(R.id.panel_display_bilateral)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlPanel.this.mParent.setBilateralHorizontalMode();
                SimpleReaderControlPanel.this.hide();
            }
        });
        ((Button) this.mPageDisplayPanel.findViewById(R.id.panel_display_thumbnail)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlPanel.this.mParent.setThumbnailMode();
                SimpleReaderControlPanel.this.hide();
            }
        });
    }

    private void createBrightnessPanel() {
        View inflate = this.mLayoutInflater.inflate(R.layout.panel_brightness, this.mParent, false);
        this.mBrightnessPanel = inflate;
        SeekBar seekBar = (SeekBar) inflate.findViewById(R.id.panel_brightness_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PlugPDFUtility.setDisplayBrightness(SimpleReaderControlPanel.this.mParent.mAct.getWindow(), ((float) (i + 20)) * 0.01f);
            }
        });
        PlugPDFUtility.setDisplayBrightness(this.mParent.mAct.getWindow(), ((float) seekBar.getProgress()) + 0.19999999f);
    }

    /* renamed from: com.epapyrus.plugpdf.SimpleReaderControlPanel$11  reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$SimpleReaderControlPanel$PanelType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.epapyrus.plugpdf.SimpleReaderControlPanel$PanelType[] r0 = com.epapyrus.plugpdf.SimpleReaderControlPanel.PanelType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$SimpleReaderControlPanel$PanelType = r0
                com.epapyrus.plugpdf.SimpleReaderControlPanel$PanelType r1 = com.epapyrus.plugpdf.SimpleReaderControlPanel.PanelType.DISPLAYMODE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$SimpleReaderControlPanel$PanelType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.SimpleReaderControlPanel$PanelType r1 = com.epapyrus.plugpdf.SimpleReaderControlPanel.PanelType.BRIGHTNESS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$SimpleReaderControlPanel$PanelType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.SimpleReaderControlPanel$PanelType r1 = com.epapyrus.plugpdf.SimpleReaderControlPanel.PanelType.OUTLINE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.SimpleReaderControlPanel.AnonymousClass11.<clinit>():void");
        }
    }

    public void show(PanelType panelType, View view) {
        View view2;
        int i;
        float f;
        int i2 = AnonymousClass11.$SwitchMap$com$epapyrus$plugpdf$SimpleReaderControlPanel$PanelType[panelType.ordinal()];
        if (i2 == 1) {
            view2 = this.mPageDisplayPanel;
            f = PlugPDFUtility.convertDipToPx(this.mContext, 17.0f);
        } else if (i2 != 2) {
            if (i2 != 3) {
                view2 = null;
                i = 0;
            } else {
                view2 = this.mOutlinePanel;
                i = (int) PlugPDFUtility.convertDipToPx(this.mContext, 100.0f);
                List<OutlineItem> outlineItem = this.mController.getOutlineItem();
                this.outlineItemList = outlineItem;
                if (outlineItem == null) {
                    this.outlineItemList = new ArrayList();
                }
                ((ListView) this.mOutlinePanel.findViewById(R.id.panel_outline_list)).setAdapter(new OutlineAdapter(this.mLayoutInflater, this.outlineItemList, this.outlintEditListener));
                ((ToggleButton) this.mOutlinePanel.findViewById(R.id.panel_outline_add)).setChecked(false);
                ((ToggleButton) this.mOutlinePanel.findViewById(R.id.panel_outline_remove)).setChecked(false);
            }
            PopupWindow popupWindow = new PopupWindow(view2, -2, -2, true);
            this.mPopupPanel = popupWindow;
            popupWindow.setAnimationStyle(16973826);
            this.mPopupPanel.setOutsideTouchable(true);
            this.mPopupPanel.setBackgroundDrawable(new BitmapDrawable());
            this.mPopupPanel.showAsDropDown(view, -i, 0);
            this.mPopupPanel.setOnDismissListener(new PopupWindow.OnDismissListener() {
                public void onDismiss() {
                    if (SimpleReaderControlPanel.this.mIsModifiedOutline) {
                        boolean unused = SimpleReaderControlPanel.this.mIsModifiedOutline = false;
                        SimpleReaderControlPanel.this.mController.updateOutline(SimpleReaderControlPanel.this.outlineItemList);
                    }
                }
            });
        } else {
            view2 = this.mBrightnessPanel;
            f = PlugPDFUtility.convertDipToPx(this.mContext, 45.0f);
        }
        i = (int) f;
        PopupWindow popupWindow2 = new PopupWindow(view2, -2, -2, true);
        this.mPopupPanel = popupWindow2;
        popupWindow2.setAnimationStyle(16973826);
        this.mPopupPanel.setOutsideTouchable(true);
        this.mPopupPanel.setBackgroundDrawable(new BitmapDrawable());
        this.mPopupPanel.showAsDropDown(view, -i, 0);
        this.mPopupPanel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                if (SimpleReaderControlPanel.this.mIsModifiedOutline) {
                    boolean unused = SimpleReaderControlPanel.this.mIsModifiedOutline = false;
                    SimpleReaderControlPanel.this.mController.updateOutline(SimpleReaderControlPanel.this.outlineItemList);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void hide() {
        PopupWindow popupWindow = this.mPopupPanel;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.mPopupPanel = null;
        }
    }

    public void refreshLayout() {
        hide();
    }

    public void addOutlineItem(List<OutlineItem> list, int i, String str, int i2, boolean z) {
        OutlineItem outlineItem;
        if (i < 1) {
            outlineItem = new OutlineItem(0, str, i2);
        } else {
            outlineItem = new OutlineItem(list.get(i - 1).getDeps() + (z ? 1 : 0), str, i2);
        }
        list.add(i, outlineItem);
        ((BaseAdapter) ((ListView) this.mOutlinePanel.findViewById(R.id.panel_outline_list)).getAdapter()).notifyDataSetChanged();
        this.mOutlinePanel.requestLayout();
        this.mIsModifiedOutline = true;
    }

    public void removeOutlineItem(List<OutlineItem> list, int i) {
        list.remove(i);
        ((BaseAdapter) ((ListView) this.mOutlinePanel.findViewById(R.id.panel_outline_list)).getAdapter()).notifyDataSetChanged();
        this.mOutlinePanel.requestLayout();
        this.mIsModifiedOutline = true;
    }
}
