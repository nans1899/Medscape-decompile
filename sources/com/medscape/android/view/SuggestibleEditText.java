package com.medscape.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.medscape.android.R;
import java.lang.reflect.Method;

public class SuggestibleEditText extends EditText {
    private static final int EXPAND_LIST_TIMEOUT = 250;
    private View headerView;
    /* access modifiers changed from: private */
    public ListAdapter mAdapter;
    private int mDropDownAnchorId;
    private View mDropDownAnchorView;
    private boolean mDropDownDismissedOnCompletion = true;
    private int mDropDownHeight = -2;
    private int mDropDownHorizontalOffset;
    private DropDownItemClickListener mDropDownItemClickListener = new DropDownItemClickListener();
    /* access modifiers changed from: private */
    public DropDownListView mDropDownList;
    private OnDropDownStateChangedListener mDropDownStateListener;
    private int mDropDownVerticalOffset;
    private int mDropDownWidth = -1;
    private boolean mForceIgnoreOutsideTouch = false;
    /* access modifiers changed from: private */
    public AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    private PopupDataSetObserver mObserver;
    /* access modifiers changed from: private */
    public Method mOnClickMethod;
    private PassThroughClickListener mPassThroughClickListener;
    private PopupWindow mPopup;
    private AbsListView.RecyclerListener mRecycleListener;
    /* access modifiers changed from: private */
    public OnSearchInputChangedListener mSearchChangedListener;
    private Runnable mShowDropDownRunnable;
    private final SuggestibleTextWatcher mTextWatcher = new SuggestibleTextWatcher();
    private int mThreshold;

    public interface OnDropDownStateChangedListener extends PopupWindow.OnDismissListener {
        void onDropDownVisible();
    }

    public interface OnSearchInputChangedListener {
        void onSearchInputChanged(String str);
    }

    public SuggestibleEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        PopupWindow popupWindow = new PopupWindow(context, attributeSet);
        this.mPopup = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.grey)));
        this.mPopup.setSoftInputMode(16);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Medscape);
        this.mThreshold = Math.max(obtainStyledAttributes.getInt(7, 2), 1);
        this.mDropDownAnchorId = obtainStyledAttributes.getResourceId(2, -1);
        this.mDropDownHorizontalOffset = (int) obtainStyledAttributes.getDimension(5, 0.0f);
        this.mDropDownVerticalOffset = (int) obtainStyledAttributes.getDimension(17, 0.0f);
        this.mDropDownWidth = obtainStyledAttributes.getLayoutDimension(4, -1);
        this.mDropDownHeight = obtainStyledAttributes.getLayoutDimension(3, -2);
        String string = obtainStyledAttributes.getString(9);
        int inputType = getInputType();
        if (!obtainStyledAttributes.getBoolean(14, false) && (inputType & 15) == 1) {
            setRawInputType(inputType | 65536);
        }
        obtainStyledAttributes.recycle();
        setFocusable(true);
        PassThroughClickListener passThroughClickListener = new PassThroughClickListener();
        this.mPassThroughClickListener = passThroughClickListener;
        super.setOnClickListener(passThroughClickListener);
        if (string != null) {
            try {
                this.mOnClickMethod = getContext().getClass().getMethod(string, new Class[]{View.class});
                setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        try {
                            SuggestibleEditText.this.mOnClickMethod.invoke(SuggestibleEditText.this.getContext(), new Object[]{SuggestibleEditText.this});
                        } catch (Exception unused) {
                        }
                    }
                });
            } catch (Exception unused) {
            }
        }
        addTextChangedListener(this.mTextWatcher);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        View.OnClickListener unused = this.mPassThroughClickListener.mWrapped = onClickListener;
    }

    /* access modifiers changed from: private */
    public void onClickImpl() {
        if (this.mPopup.isShowing()) {
            ensureImeVisible(true);
        }
    }

    public int getDropDownWidth() {
        return this.mDropDownWidth;
    }

    public void setDropDownWidth(int i) {
        this.mDropDownWidth = i;
    }

    public int getDropDownHeight() {
        return this.mDropDownHeight;
    }

    public void setDropDownHeight(int i) {
        this.mDropDownHeight = i;
    }

    public int getDropDownAnchor() {
        return this.mDropDownAnchorId;
    }

    public void setDropDownAnchor(int i) {
        this.mDropDownAnchorId = i;
        this.mDropDownAnchorView = null;
    }

    public Drawable getDropDownBackground() {
        return this.mPopup.getBackground();
    }

    public void setDropDownBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setDropDownBackgroundResource(int i) {
        this.mPopup.setBackgroundDrawable(getResources().getDrawable(i));
    }

    public void setDropDownAnimationStyle(int i) {
        this.mPopup.setAnimationStyle(i);
    }

    public int getDropDownAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    public boolean isDropDownDismissedOnCompletion() {
        return this.mDropDownDismissedOnCompletion;
    }

    public void setDropDownDismissedOnCompletion(boolean z) {
        this.mDropDownDismissedOnCompletion = z;
    }

    public int getThreshold() {
        return this.mThreshold;
    }

    public void setThreshold(int i) {
        this.mThreshold = Math.max(1, i);
    }

    public void setOnSearchInputChangedListener(OnSearchInputChangedListener onSearchInputChangedListener) {
        this.mSearchChangedListener = onSearchInputChangedListener;
    }

    public void setOnDropDownStateChangedListener(OnDropDownStateChangedListener onDropDownStateChangedListener) {
        this.mDropDownStateListener = onDropDownStateChangedListener;
        this.mPopup.setOnDismissListener(onDropDownStateChangedListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    public void setRecycleListener(AbsListView.RecyclerListener recyclerListener) {
        this.mRecycleListener = recyclerListener;
    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return this.mItemClickListener;
    }

    public AdapterView.OnItemSelectedListener getOnItemSelectedListener() {
        return this.mItemSelectedListener;
    }

    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(ListAdapter listAdapter) {
        PopupDataSetObserver popupDataSetObserver = this.mObserver;
        if (popupDataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(popupDataSetObserver);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            AbsListView.RecyclerListener recyclerListener = this.mRecycleListener;
            if (recyclerListener != null) {
                dropDownListView.setRecyclerListener(recyclerListener);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }

    public int getDropDownListHeadersCount() {
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView == null) {
            return 0;
        }
        return dropDownListView.getHeaderViewsCount();
    }

    public void setDropDownListHeader(View view) {
        DropDownListView dropDownListView;
        View view2 = this.headerView;
        if (view2 != view) {
            if (!(view2 == null || (dropDownListView = this.mDropDownList) == null)) {
                dropDownListView.removeHeaderView(view2);
            }
            this.headerView = view;
            if (this.mDropDownList != null) {
                doSetDropDownListHeader();
            }
        }
    }

    private void doSetDropDownListHeader() {
        this.mDropDownList.addHeaderView(this.headerView, (Object) null, true);
    }

    public void removeDropDownListHeader() {
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.removeHeaderView(this.headerView);
        }
        this.headerView = null;
    }

    public boolean isPopupShowing() {
        return this.mPopup.isShowing();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    /* access modifiers changed from: protected */
    public void onDisplayHint(int i) {
        super.onDisplayHint(i);
        if (i == 4 || i == 8) {
            dismissDropDown();
        }
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (!z) {
            dismissDropDown();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        clearFocus();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        dismissDropDown();
        super.onDetachedFromWindow();
    }

    public void dismissDropDown() {
        this.mPopup.dismiss();
        this.mPopup.setContentView((View) null);
        this.mDropDownList = null;
    }

    /* access modifiers changed from: protected */
    public boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (this.mPopup.isShowing()) {
            showDropDown();
        }
        return frame;
    }

    /* access modifiers changed from: private */
    public View getDropDownAnchorView() {
        if (this.mDropDownAnchorView == null && this.mDropDownAnchorId != -1) {
            this.mDropDownAnchorView = getRootView().findViewById(this.mDropDownAnchorId);
        }
        View view = this.mDropDownAnchorView;
        return view == null ? this : view;
    }

    private int getDropDownVerticalOffset() {
        return this.mDropDownVerticalOffset;
    }

    public void showDropDownAfterLayout() {
        post(this.mShowDropDownRunnable);
    }

    public void ensureImeVisible(boolean z) {
        this.mPopup.setInputMethodMode(z ? 1 : 2);
        showDropDown();
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }

    public void showDropDown() {
        int i;
        OnDropDownStateChangedListener onDropDownStateChangedListener;
        int i2;
        int min = Math.min(buildDropDown(), this.mDropDownHeight);
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        int i3 = 0;
        int i4 = -1;
        if (this.mPopup.isShowing()) {
            int i5 = this.mDropDownWidth;
            if (i5 == -1) {
                i2 = -1;
            } else {
                if (i5 == -2) {
                    i5 = getDropDownAnchorView().getWidth();
                }
                i2 = i5;
            }
            if (this.mDropDownHeight == -1) {
                if (!isInputMethodNotNeeded) {
                    min = -1;
                }
                if (isInputMethodNotNeeded) {
                    PopupWindow popupWindow = this.mPopup;
                    if (this.mDropDownWidth != -1) {
                        i4 = 0;
                    }
                    popupWindow.setWindowLayoutMode(i4, 0);
                } else {
                    PopupWindow popupWindow2 = this.mPopup;
                    if (this.mDropDownWidth == -1) {
                        i3 = -1;
                    }
                    popupWindow2.setWindowLayoutMode(i3, -1);
                }
            }
            this.mPopup.setOutsideTouchable(!this.mForceIgnoreOutsideTouch);
            this.mPopup.update(getDropDownAnchorView(), this.mDropDownHorizontalOffset, getDropDownVerticalOffset(), i2, min);
            return;
        }
        int i6 = this.mDropDownWidth;
        if (i6 == -1) {
            i = -1;
        } else {
            if (i6 == -2) {
                this.mPopup.setWidth(getDropDownAnchorView().getWidth());
            } else {
                this.mPopup.setWidth(i6);
            }
            i = 0;
        }
        int i7 = this.mDropDownHeight;
        if (i7 == -1) {
            i3 = -1;
        } else if (i7 == -2) {
            this.mPopup.setHeight(min);
        } else {
            this.mPopup.setHeight(min);
        }
        this.mPopup.setWindowLayoutMode(i, i3);
        this.mPopup.setInputMethodMode(1);
        this.mPopup.setOutsideTouchable(!this.mForceIgnoreOutsideTouch);
        if (!this.mPopup.isShowing() && (onDropDownStateChangedListener = this.mDropDownStateListener) != null) {
            onDropDownStateChangedListener.onDropDownVisible();
        }
        this.mPopup.showAsDropDown(getDropDownAnchorView(), this.mDropDownHorizontalOffset, getDropDownVerticalOffset());
        this.mDropDownList.setSelection(-1);
    }

    public void setForceIgnoreOutsideTouch(boolean z) {
        this.mForceIgnoreOutsideTouch = z;
    }

    private int buildDropDown() {
        ListAdapter listAdapter = this.mAdapter;
        if (this.mDropDownList == null) {
            Context context = getContext();
            this.mShowDropDownRunnable = new Runnable() {
                public void run() {
                    View access$600 = SuggestibleEditText.this.getDropDownAnchorView();
                    if (access$600 != null && access$600.getWindowToken() != null) {
                        SuggestibleEditText.this.showDropDown();
                    }
                }
            };
            DropDownListView dropDownListView = new DropDownListView(context);
            this.mDropDownList = dropDownListView;
            dropDownListView.setRecyclerListener(this.mRecycleListener);
            View view = this.headerView;
            if (view != null) {
                this.mDropDownList.addHeaderView(view, (Object) null, true);
            }
            this.mDropDownList.setAdapter(listAdapter);
            this.mDropDownList.setVerticalFadingEdgeEnabled(true);
            this.mDropDownList.setOnItemClickListener(this.mDropDownItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    DropDownListView access$700;
                    if (i != -1 && (access$700 = SuggestibleEditText.this.mDropDownList) != null) {
                        access$700.setListSelectionHidden(false);
                    }
                }
            });
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.mItemSelectedListener;
            if (onItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(onItemSelectedListener);
            }
            this.mPopup.setContentView(this.mDropDownList);
        }
        int maxAvailableHeight = this.mPopup.getMaxAvailableHeight(getDropDownAnchorView(), this.mDropDownVerticalOffset);
        if (this.mDropDownHeight == -1) {
            return maxAvailableHeight;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mDropDownList.getWidth(), Integer.MIN_VALUE);
        int i = 0;
        for (int i2 = 0; i2 < this.mAdapter.getCount(); i2++) {
            View view2 = this.mAdapter.getView(i2, (View) null, this.mDropDownList);
            view2.measure(makeMeasureSpec, 0);
            i += view2.getMeasuredHeight();
        }
        return i + (this.mDropDownList.getVerticalFadingEdgeLength() / 2);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (this.mPopup.isShowing() && inputMethodManager.isActive()) {
                dismissDropDown();
            }
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    private class DropDownItemClickListener implements AdapterView.OnItemClickListener {
        private DropDownItemClickListener() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (SuggestibleEditText.this.mItemClickListener != null) {
                SuggestibleEditText.this.mItemClickListener.onItemClick(adapterView, view, i, j);
            } else if (view != null && (view instanceof TextView)) {
                SuggestibleEditText.this.setText(((TextView) view).getText());
                SuggestibleEditText suggestibleEditText = SuggestibleEditText.this;
                suggestibleEditText.setSelection(suggestibleEditText.length());
            }
        }
    }

    private class PassThroughClickListener implements View.OnClickListener {
        /* access modifiers changed from: private */
        public View.OnClickListener mWrapped;

        private PassThroughClickListener() {
        }

        public void onClick(View view) {
            SuggestibleEditText.this.onClickImpl();
            View.OnClickListener onClickListener = this.mWrapped;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    private final class SuggestibleTextWatcher implements TextWatcher {
        private String content;

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private SuggestibleTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String trim = charSequence.toString().trim();
            if (SuggestibleEditText.this.mSearchChangedListener != null && !TextUtils.equals(this.content, trim)) {
                SuggestibleEditText.this.mSearchChangedListener.onSearchInputChanged(trim);
            }
            this.content = trim;
        }
    }

    private class PopupDataSetObserver extends DataSetObserver {
        private PopupDataSetObserver() {
        }

        public void onChanged() {
            if (SuggestibleEditText.this.mAdapter != null) {
                SuggestibleEditText.this.post(new Runnable() {
                    public void run() {
                        ListAdapter access$1100 = SuggestibleEditText.this.mAdapter;
                        if (access$1100 != null && access$1100.getCount() > 0 && SuggestibleEditText.this.length() > 0 && SuggestibleEditText.this.hasFocus() && SuggestibleEditText.this.hasWindowFocus()) {
                            SuggestibleEditText.this.showDropDown();
                        } else if (access$1100 != null && access$1100.getCount() <= 0) {
                            SuggestibleEditText.this.dismissDropDown();
                        }
                    }
                });
            } else if (SuggestibleEditText.this.isPopupShowing()) {
                SuggestibleEditText.this.showDropDown();
            }
        }

        public void onInvalidated() {
            SuggestibleEditText.this.dismissDropDown();
        }
    }
}
