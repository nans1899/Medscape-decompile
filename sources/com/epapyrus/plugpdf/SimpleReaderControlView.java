package com.epapyrus.plugpdf;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.epapyrus.plugpdf.SimpleReaderControlPanel;
import com.epapyrus.plugpdf.core.BaseReaderControl;
import com.epapyrus.plugpdf.core.PlugPDF;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.facebook.share.internal.ShareConstants;

public class SimpleReaderControlView extends RelativeLayout {
    private boolean enableHiddenBottomBar;
    private boolean enableHiddenTopBar;
    Activity mAct;
    /* access modifiers changed from: private */
    public AnnotSettingMenu mAnnotSettingMenu;
    /* access modifiers changed from: private */
    public Bitmap mBitmap = null;
    private Button mBrightnessButton;
    private boolean mButtonsVisible;
    /* access modifiers changed from: private */
    public SimpleReaderControlPanel mControlPanel;
    /* access modifiers changed from: private */
    public BaseReaderControl mController;
    private Button mEditButton;
    EditButtonClickHandler mEditButtonClickHandler = new EditButtonClickHandler();
    private Button mEditCancelButton;
    /* access modifiers changed from: private */
    public Button mEditEraserButton;
    /* access modifiers changed from: private */
    public Button mEditHighlightButton;
    /* access modifiers changed from: private */
    public Button mEditInkButton;
    /* access modifiers changed from: private */
    public Button mEditNoteButton;
    /* access modifiers changed from: private */
    public Button mEditStrikeoutButton;
    /* access modifiers changed from: private */
    public Button mEditUnderlineButton;
    private Button mOutlineButton;
    private Button mPageDisplayModeButton;
    /* access modifiers changed from: private */
    public int mPageIdx;
    /* access modifiers changed from: private */
    public TextView mPageNumberView;
    /* access modifiers changed from: private */
    public SeekBar mPageSlider;
    /* access modifiers changed from: private */
    public ImageView mPageThumbnail;
    /* access modifiers changed from: private */
    public Button mRotateButton;
    /* access modifiers changed from: private */
    public Button mSearchBack;
    private Button mSearchButton;
    private Button mSearchCancelButton;
    /* access modifiers changed from: private */
    public Button mSearchFwd;
    /* access modifiers changed from: private */
    public EditText mSearchText;
    private TextView mTitle;
    private boolean mTopBarIsSearch;
    /* access modifiers changed from: private */
    public ViewFlipper mTopBarSwitcher;

    public SimpleReaderControlView(Context context) {
        super(context);
    }

    public SimpleReaderControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void createUILayout(BaseReaderControl baseReaderControl) {
        this.mController = baseReaderControl;
        this.mTitle = (TextView) findViewById(R.id.rc_title);
        this.mTopBarSwitcher = (ViewFlipper) findViewById(R.id.flipper);
        this.mPageNumberView = (TextView) findViewById(R.id.rc_page_number);
        this.mPageThumbnail = (ImageView) findViewById(R.id.rc_page_thumbnail);
        this.mPageSlider = (SeekBar) findViewById(R.id.rc_page_slider);
        this.mSearchButton = (Button) findViewById(R.id.rc_search);
        this.mEditButton = (Button) findViewById(R.id.rc_edit);
        this.mSearchCancelButton = (Button) findViewById(R.id.rc_search_cancel);
        this.mSearchText = (EditText) findViewById(R.id.rc_search_text);
        this.mSearchBack = (Button) findViewById(R.id.rc_search_back);
        this.mSearchFwd = (Button) findViewById(R.id.rc_search_forward);
        this.mEditCancelButton = (Button) findViewById(R.id.rc_edit_cancel);
        this.mEditNoteButton = (Button) findViewById(R.id.rc_edit_note);
        this.mEditInkButton = (Button) findViewById(R.id.rc_edit_ink);
        this.mEditEraserButton = (Button) findViewById(R.id.rc_edit_eraser);
        this.mEditHighlightButton = (Button) findViewById(R.id.rc_edit_tm_highlight);
        this.mEditUnderlineButton = (Button) findViewById(R.id.rc_edit_tm_underline);
        this.mEditStrikeoutButton = (Button) findViewById(R.id.rc_edit_tm_strikeout);
        this.mOutlineButton = (Button) findViewById(R.id.rc_outline);
        this.mRotateButton = (Button) findViewById(R.id.rc_rotate);
        this.mPageDisplayModeButton = (Button) findViewById(R.id.rc_page_display_mode);
        this.mBrightnessButton = (Button) findViewById(R.id.rc_brightness);
    }

    public void init(Activity activity) {
        this.mAct = activity;
        this.mAnnotSettingMenu = new AnnotSettingMenu(getContext());
        this.mControlPanel = new SimpleReaderControlPanel(getContext(), this, this.mController);
        showOutlineButton(true);
        setEnableHiddenTopBar(false);
        setEnableHiddenBottomBar(false);
        this.mTopBarSwitcher.setVisibility(4);
        this.mPageThumbnail.setVisibility(4);
        this.mPageNumberView.setVisibility(4);
        this.mPageSlider.setVisibility(4);
        this.mPageSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                SimpleReaderControlView.this.mController.goToPage(seekBar.getProgress());
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                SimpleReaderControlView.this.updatePageNumber(seekBar.getProgress() + 1, seekBar.getMax() + 1);
            }
        });
        this.mSearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlView.this.searchModeOn();
            }
        });
        if (this.mController.canModifyAnnot()) {
            this.mEditButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SimpleReaderControlView.this.editModeOn();
                }
            });
        } else {
            this.mEditButton.setVisibility(8);
        }
        this.mSearchCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlView.this.searchModeOff();
            }
        });
        this.mSearchBack.setEnabled(false);
        this.mSearchFwd.setEnabled(false);
        this.mSearchText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                boolean z = editable.toString().length() > 0;
                SimpleReaderControlView.this.mSearchBack.setEnabled(z);
                SimpleReaderControlView.this.mSearchFwd.setEnabled(z);
                SimpleReaderControlView.this.mController.resetSearchInfo();
            }
        });
        this.mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 5 && i != 6) {
                    return false;
                }
                SimpleReaderControlView.this.mController.search(SimpleReaderControlView.this.mSearchText.getText().toString(), 1);
                return false;
            }
        });
        this.mSearchBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlView.this.mController.search(SimpleReaderControlView.this.mSearchText.getText().toString(), -1);
            }
        });
        this.mSearchFwd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlView.this.mController.search(SimpleReaderControlView.this.mSearchText.getText().toString(), 1);
            }
        });
        this.mEditCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlView.this.editModeOff();
                SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.VIEW);
            }
        });
        this.mEditNoteButton.setOnClickListener(this.mEditButtonClickHandler);
        this.mEditInkButton.setOnClickListener(this.mEditButtonClickHandler);
        this.mEditInkButton.setOnLongClickListener(this.mEditButtonClickHandler);
        this.mEditEraserButton.setOnClickListener(this.mEditButtonClickHandler);
        this.mEditHighlightButton.setOnClickListener(this.mEditButtonClickHandler);
        this.mEditHighlightButton.setOnLongClickListener(this.mEditButtonClickHandler);
        this.mEditUnderlineButton.setOnClickListener(this.mEditButtonClickHandler);
        this.mEditUnderlineButton.setOnLongClickListener(this.mEditButtonClickHandler);
        this.mEditStrikeoutButton.setOnClickListener(this.mEditButtonClickHandler);
        this.mEditStrikeoutButton.setOnLongClickListener(this.mEditButtonClickHandler);
        this.mRotateButton.setTag(0);
        this.mRotateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SimpleReaderControlView.this.mRotateButton.getTag().equals(0)) {
                    SimpleReaderControlView.this.mRotateButton.setTag(1);
                    SimpleReaderControlView.this.mRotateButton.setBackgroundResource(R.drawable.st_btn_rotate_lock);
                    PlugPDFUtility.setRotationLock(SimpleReaderControlView.this.mAct, true);
                    return;
                }
                SimpleReaderControlView.this.mRotateButton.setTag(0);
                SimpleReaderControlView.this.mRotateButton.setBackgroundResource(R.drawable.st_btn_rotate);
                PlugPDFUtility.setRotationLock(SimpleReaderControlView.this.mAct, false);
            }
        });
        this.mPageDisplayModeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlView.this.mControlPanel.show(SimpleReaderControlPanel.PanelType.DISPLAYMODE, view);
            }
        });
        this.mBrightnessButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleReaderControlView.this.mControlPanel.show(SimpleReaderControlPanel.PanelType.BRIGHTNESS, view);
            }
        });
    }

    public void setTitle(String str) {
        this.mTitle.setText(str);
    }

    public void toggleControlTabBar() {
        if (!this.mButtonsVisible) {
            show();
        } else {
            hideTopMenu();
        }
    }

    public void refreshLayout() {
        this.mControlPanel.refreshLayout();
    }

    public void show() {
        if (!this.mButtonsVisible) {
            this.mButtonsVisible = true;
            if (this.mTopBarIsSearch) {
                this.mSearchText.requestFocus();
                showKeyboard();
            }
            if (!isEnableHiddenTopBar()) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (-this.mTopBarSwitcher.getHeight()), 0.0f);
                translateAnimation.setDuration(200);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                        SimpleReaderControlView.this.mTopBarSwitcher.setVisibility(0);
                    }
                });
                this.mTopBarSwitcher.startAnimation(translateAnimation);
            }
            if (!isEnableHiddenBottomBar()) {
                TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, (float) this.mPageSlider.getHeight(), 0.0f);
                translateAnimation2.setDuration(200);
                translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                        SimpleReaderControlView.this.mPageSlider.setVisibility(0);
                    }

                    public void onAnimationEnd(Animation animation) {
                        SimpleReaderControlView.this.mPageNumberView.setVisibility(0);
                        SimpleReaderControlView.this.mPageThumbnail.setVisibility(0);
                    }
                });
                this.mPageSlider.startAnimation(translateAnimation2);
            }
        }
    }

    public void hideTopMenu() {
        if (this.mButtonsVisible) {
            this.mButtonsVisible = false;
            hideKeyboard();
            if (!isEnableHiddenTopBar()) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-this.mTopBarSwitcher.getHeight()));
                translateAnimation.setDuration(200);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        SimpleReaderControlView.this.mTopBarSwitcher.setVisibility(4);
                    }
                });
                this.mTopBarSwitcher.startAnimation(translateAnimation);
            }
            if (!isEnableHiddenBottomBar()) {
                TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.mPageSlider.getHeight());
                translateAnimation2.setDuration(200);
                translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                        SimpleReaderControlView.this.mPageNumberView.setVisibility(4);
                        SimpleReaderControlView.this.mPageThumbnail.setVisibility(8);
                    }

                    public void onAnimationEnd(Animation animation) {
                        SimpleReaderControlView.this.mPageSlider.setVisibility(4);
                    }
                });
                this.mPageSlider.startAnimation(translateAnimation2);
            }
        }
    }

    public void updatePageNumber(int i, int i2) {
        this.mPageNumberView.setText(String.format("%d/%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        this.mPageSlider.setMax(i2 - 1);
        int i3 = i - 1;
        this.mPageSlider.setProgress(i3);
        this.mPageIdx = i3;
        if (this.mTopBarIsSearch && !this.mSearchText.getText().toString().isEmpty()) {
            this.mController.search(this.mSearchText.getText().toString(), 0);
        }
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                PointF pageSize = SimpleReaderControlView.this.mController.getPageSize(SimpleReaderControlView.this.mPageIdx);
                int i = (((int) pageSize.x) * 200) / ((int) pageSize.y);
                Bitmap unused = SimpleReaderControlView.this.mBitmap = Bitmap.createBitmap(i, 200, PlugPDF.bitmapConfig());
                SimpleReaderControlView.this.mController.drawPage(SimpleReaderControlView.this.mBitmap, SimpleReaderControlView.this.mPageIdx, i, 200, 0, 0, i, 200);
                return null;
            }

            /* access modifiers changed from: protected */
            public void onPreExecute() {
                SimpleReaderControlView.this.mPageThumbnail.setImageBitmap(SimpleReaderControlView.this.mBitmap);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Void voidR) {
                if (SimpleReaderControlView.this.mBitmap != null) {
                    SimpleReaderControlView.this.mPageThumbnail.setImageBitmap(SimpleReaderControlView.this.mBitmap);
                }
            }
        }.execute(new Void[0]);
    }

    private void setSearchMode(boolean z) {
        if (z) {
            this.mTopBarSwitcher.showNext();
        } else {
            this.mTopBarSwitcher.showPrevious();
        }
    }

    /* access modifiers changed from: package-private */
    public void searchModeOn() {
        this.mTopBarIsSearch = true;
        this.mSearchText.requestFocus();
        showKeyboard();
        setSearchMode(true);
    }

    /* access modifiers changed from: package-private */
    public void searchModeOff() {
        this.mTopBarIsSearch = false;
        hideKeyboard();
        setSearchMode(false);
        this.mController.resetSearchInfo();
    }

    /* access modifiers changed from: package-private */
    public void showKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(this.mSearchText, 0);
        }
    }

    /* access modifiers changed from: package-private */
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mSearchText.getWindowToken(), 0);
        }
    }

    private void setEditMode(boolean z) {
        if (z) {
            this.mTopBarSwitcher.showNext();
            this.mTopBarSwitcher.showNext();
            return;
        }
        this.mTopBarSwitcher.showPrevious();
        this.mTopBarSwitcher.showPrevious();
    }

    /* access modifiers changed from: package-private */
    public void editModeOn() {
        this.mPageSlider.setVisibility(0);
        setEditMode(true);
    }

    /* access modifiers changed from: package-private */
    public void editModeOff() {
        this.mPageSlider.setVisibility(4);
        this.mEditNoteButton.setSelected(false);
        this.mEditInkButton.setSelected(false);
        this.mEditEraserButton.setSelected(false);
        this.mEditHighlightButton.setSelected(false);
        this.mEditStrikeoutButton.setSelected(false);
        this.mEditUnderlineButton.setSelected(false);
        setEditMode(false);
    }

    public void showEditButton(boolean z) {
        if (z) {
            this.mEditButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SimpleReaderControlView.this.editModeOn();
                }
            });
        } else {
            this.mEditButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void showOutlineButton(boolean z) {
        if (z) {
            this.mOutlineButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SimpleReaderControlView.this.mControlPanel.show(SimpleReaderControlPanel.PanelType.OUTLINE, view);
                }
            });
        } else {
            this.mOutlineButton.setVisibility(8);
        }
    }

    public void saveState(Bundle bundle) {
        if (!this.mButtonsVisible) {
            bundle.putBoolean("ButtonsHidden", true);
        }
        if (this.mTopBarIsSearch) {
            bundle.putBoolean("SearchMode", true);
        }
    }

    public void restoreState(Bundle bundle) {
        if (bundle == null || !bundle.getBoolean("ButtonsHidden", false)) {
            show();
        }
        if (bundle != null && bundle.getBoolean("SearchMode", false)) {
            searchModeOn();
        }
    }

    /* access modifiers changed from: package-private */
    public void setHorizontalMode() {
        this.mPageDisplayModeButton.setBackgroundResource(R.drawable.st_btn_view_mode_horizontal);
        this.mController.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
    }

    /* access modifiers changed from: package-private */
    public void setBilateralVerticalMode() {
        this.mPageDisplayModeButton.setBackgroundResource(R.drawable.st_btn_view_mode_bilateral);
        this.mController.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.BILATERAL_VERTICAL);
    }

    /* access modifiers changed from: package-private */
    public void setBilateralHorizontalMode() {
        this.mPageDisplayModeButton.setBackgroundResource(R.drawable.st_btn_view_mode_bilateral);
        this.mController.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.BILATERAL_HORIZONTAL);
    }

    /* access modifiers changed from: package-private */
    public void setBilateralRealisticMode() {
        this.mPageDisplayModeButton.setBackgroundResource(R.drawable.st_btn_view_mode_bilateral);
        this.mController.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.BILATERAL_REALISTIC);
    }

    /* access modifiers changed from: package-private */
    public void setVerticalMode() {
        this.mPageDisplayModeButton.setBackgroundResource(R.drawable.st_btn_view_mode_vertical);
        this.mController.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.VERTICAL);
    }

    /* access modifiers changed from: package-private */
    public void setContinuosMode() {
        this.mPageDisplayModeButton.setBackgroundResource(R.drawable.st_btn_view_mode_vertical);
        this.mController.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.CONTINUOS);
    }

    /* access modifiers changed from: package-private */
    public void setThumbnailMode() {
        this.mPageDisplayModeButton.setBackgroundResource(R.drawable.st_btn_view_mode_thumbnail);
        this.mController.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL);
    }

    /* access modifiers changed from: package-private */
    public void setRealisticMode() {
        this.mController.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.REALISTIC);
    }

    private class EditButtonClickHandler implements View.OnClickListener, View.OnLongClickListener {
        private boolean mLongClickEvented;

        private EditButtonClickHandler() {
            this.mLongClickEvented = false;
        }

        public boolean onLongClick(View view) {
            this.mLongClickEvented = true;
            if (view.getId() == SimpleReaderControlView.this.mEditInkButton.getId()) {
                SimpleReaderControlView.this.mAnnotSettingMenu.show(view, 0, 0, BaseAnnotTool.AnnotToolType.INK);
            } else if (view.getId() == SimpleReaderControlView.this.mEditHighlightButton.getId()) {
                SimpleReaderControlView.this.mAnnotSettingMenu.show(view, 0, 0, BaseAnnotTool.AnnotToolType.HIGHLIGHT);
            } else if (view.getId() == SimpleReaderControlView.this.mEditUnderlineButton.getId()) {
                SimpleReaderControlView.this.mAnnotSettingMenu.show(view, 0, 0, BaseAnnotTool.AnnotToolType.UNDERLINE);
            } else if (view.getId() == SimpleReaderControlView.this.mEditStrikeoutButton.getId()) {
                SimpleReaderControlView.this.mAnnotSettingMenu.show(view, 0, 0, BaseAnnotTool.AnnotToolType.STRIKEOUT);
            }
            return false;
        }

        public void onClick(View view) {
            if (this.mLongClickEvented) {
                this.mLongClickEvented = false;
            } else if (view.getId() == SimpleReaderControlView.this.mEditInkButton.getId()) {
                SimpleReaderControlView.this.mEditEraserButton.setSelected(false);
                SimpleReaderControlView.this.mEditNoteButton.setSelected(false);
                SimpleReaderControlView.this.mEditHighlightButton.setSelected(false);
                SimpleReaderControlView.this.mEditUnderlineButton.setSelected(false);
                SimpleReaderControlView.this.mEditStrikeoutButton.setSelected(false);
                SimpleReaderControlView.this.mEditInkButton.setSelected(!SimpleReaderControlView.this.mEditInkButton.isSelected());
                if (SimpleReaderControlView.this.mEditInkButton.isSelected()) {
                    SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.EDIT);
                    SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.INK);
                    return;
                }
                SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.VIEW);
                SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.NONE);
            } else if (view.getId() == SimpleReaderControlView.this.mEditEraserButton.getId()) {
                SimpleReaderControlView.this.mEditInkButton.setSelected(false);
                SimpleReaderControlView.this.mEditNoteButton.setSelected(false);
                SimpleReaderControlView.this.mEditHighlightButton.setSelected(false);
                SimpleReaderControlView.this.mEditUnderlineButton.setSelected(false);
                SimpleReaderControlView.this.mEditStrikeoutButton.setSelected(false);
                SimpleReaderControlView.this.mEditEraserButton.setSelected(!SimpleReaderControlView.this.mEditEraserButton.isSelected());
                if (SimpleReaderControlView.this.mEditEraserButton.isSelected()) {
                    SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.EDIT);
                    SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.ERASER);
                    return;
                }
                SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.VIEW);
                SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.NONE);
            } else if (view.getId() == SimpleReaderControlView.this.mEditNoteButton.getId()) {
                SimpleReaderControlView.this.mEditInkButton.setSelected(false);
                SimpleReaderControlView.this.mEditEraserButton.setSelected(false);
                SimpleReaderControlView.this.mEditHighlightButton.setSelected(false);
                SimpleReaderControlView.this.mEditUnderlineButton.setSelected(false);
                SimpleReaderControlView.this.mEditStrikeoutButton.setSelected(false);
                SimpleReaderControlView.this.mEditNoteButton.setSelected(!SimpleReaderControlView.this.mEditNoteButton.isSelected());
                if (SimpleReaderControlView.this.mEditNoteButton.isSelected()) {
                    SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.EDIT);
                    SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.NOTE);
                    return;
                }
                SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.VIEW);
                SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.NONE);
            } else if (view.getId() == SimpleReaderControlView.this.mEditHighlightButton.getId()) {
                SimpleReaderControlView.this.mEditInkButton.setSelected(false);
                SimpleReaderControlView.this.mEditEraserButton.setSelected(false);
                SimpleReaderControlView.this.mEditNoteButton.setSelected(false);
                SimpleReaderControlView.this.mEditUnderlineButton.setSelected(false);
                SimpleReaderControlView.this.mEditStrikeoutButton.setSelected(false);
                SimpleReaderControlView.this.mEditHighlightButton.setSelected(!SimpleReaderControlView.this.mEditHighlightButton.isSelected());
                if (SimpleReaderControlView.this.mEditHighlightButton.isSelected()) {
                    SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.EDIT);
                    SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.HIGHLIGHT);
                    return;
                }
                SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.VIEW);
                SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.NONE);
            } else if (view.getId() == SimpleReaderControlView.this.mEditUnderlineButton.getId()) {
                SimpleReaderControlView.this.mEditInkButton.setSelected(false);
                SimpleReaderControlView.this.mEditEraserButton.setSelected(false);
                SimpleReaderControlView.this.mEditNoteButton.setSelected(false);
                SimpleReaderControlView.this.mEditHighlightButton.setSelected(false);
                SimpleReaderControlView.this.mEditStrikeoutButton.setSelected(false);
                SimpleReaderControlView.this.mEditUnderlineButton.setSelected(!SimpleReaderControlView.this.mEditUnderlineButton.isSelected());
                if (SimpleReaderControlView.this.mEditUnderlineButton.isSelected()) {
                    SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.EDIT);
                    SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.UNDERLINE);
                    return;
                }
                SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.VIEW);
                SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.NONE);
            } else if (view.getId() == SimpleReaderControlView.this.mEditStrikeoutButton.getId()) {
                SimpleReaderControlView.this.mEditInkButton.setSelected(false);
                SimpleReaderControlView.this.mEditEraserButton.setSelected(false);
                SimpleReaderControlView.this.mEditNoteButton.setSelected(false);
                SimpleReaderControlView.this.mEditHighlightButton.setSelected(false);
                SimpleReaderControlView.this.mEditUnderlineButton.setSelected(false);
                SimpleReaderControlView.this.mEditStrikeoutButton.setSelected(!SimpleReaderControlView.this.mEditStrikeoutButton.isSelected());
                if (SimpleReaderControlView.this.mEditStrikeoutButton.isSelected()) {
                    SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.EDIT);
                    SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.STRIKEOUT);
                    return;
                }
                SimpleReaderControlView.this.mController.changeGestureType(BaseGestureProcessor.GestureType.VIEW);
                SimpleReaderControlView.this.mController.setAnnotationTool(BaseAnnotTool.AnnotToolType.NONE);
            }
        }
    }

    private void enableAnnotButton(Button button, boolean z) {
        if (z) {
            button.setVisibility(0);
        } else {
            button.setVisibility(8);
        }
    }

    public void enableAnnotFeature(String str, boolean z) {
        for (String str2 : str.split(":")) {
            if (str2.equals("INK")) {
                enableAnnotButton(this.mEditInkButton, z);
            } else if (str2.equals("NOTE")) {
                enableAnnotButton(this.mEditNoteButton, z);
            } else {
                str2.equals(ShareConstants.CONTENT_URL);
            }
        }
        if (this.mEditInkButton.getVisibility() == 0 || this.mEditNoteButton.getVisibility() == 0) {
            enableAnnotButton(this.mEditEraserButton, true);
        } else {
            enableAnnotButton(this.mEditEraserButton, false);
        }
    }

    public boolean isEnableHiddenTopBar() {
        return this.enableHiddenTopBar;
    }

    public void setEnableHiddenTopBar(boolean z) {
        this.enableHiddenTopBar = z;
    }

    public boolean isEnableHiddenBottomBar() {
        return this.enableHiddenBottomBar;
    }

    public void setEnableHiddenBottomBar(boolean z) {
        this.enableHiddenBottomBar = z;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mControlPanel.refreshLayout();
    }
}
