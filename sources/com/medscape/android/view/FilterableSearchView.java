package com.medscape.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.appcompat.widget.SearchView;
import com.medscape.android.R;

public class FilterableSearchView extends LinearLayout implements CollapsibleActionView {
    /* access modifiers changed from: private */
    public ImageView mCloseButton;
    private int mCollapsedImeOptions;
    private View mDropDownAnchor;
    private boolean mExpandedInActionView;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    InputMethodManager mInputManager;
    /* access modifiers changed from: private */
    public CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == FilterableSearchView.this.mCloseButton) {
                FilterableSearchView.this.onCloseClicked();
            }
        }
    };
    private SearchView.OnCloseListener mOnCloseListener;
    private final TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            FilterableSearchView.this.onSubmitQuery();
            return true;
        }
    };
    private final AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            boolean unused = FilterableSearchView.this.onItemClicked(i, 0, (String) null);
        }
    };
    /* access modifiers changed from: private */
    public SearchView.OnQueryTextListener mOnQueryChangeListener;
    /* access modifiers changed from: private */
    public View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View.OnClickListener mOnSearchClickListener;
    private SearchView.OnSuggestionListener mOnSuggestionListener;
    private CharSequence mQueryHint;
    /* access modifiers changed from: private */
    public SuggestibleEditText mQueryTextView;
    private View mSearchEditFrame;
    private View mSearchPlate;
    private Runnable mShowImeRunnable = new Runnable() {
        public void run() {
            InputMethodManager inputMethodManager = (InputMethodManager) FilterableSearchView.this.getContext().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(FilterableSearchView.this.mQueryTextView, 0);
            }
        }
    };
    private Runnable mUpdateDrawableStateRunnable = new Runnable() {
        public void run() {
            FilterableSearchView.this.updateFocusedState();
        }
    };

    private int getSearchIconId() {
        return R.drawable.action_search;
    }

    public FilterableSearchView(Context context) {
        super(context);
        init(context);
    }

    public FilterableSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SearchView, 0, 0);
        setIconifiedByDefault(obtainStyledAttributes.getBoolean(8, true));
        CharSequence text = obtainStyledAttributes.getText(11);
        if (!TextUtils.isEmpty(text)) {
            setQueryHint(text);
        }
        obtainStyledAttributes.recycle();
    }

    private void init(Context context) {
        this.mSearchEditFrame = findViewById(R.id.search_edit_frame);
        this.mSearchPlate = findViewById(R.id.search_plate);
        ImageView imageView = (ImageView) findViewById(R.id.search_close_btn);
        this.mCloseButton = imageView;
        imageView.setOnClickListener(this.mOnClickListener);
        SuggestibleEditText suggestibleEditText = (SuggestibleEditText) findViewById(R.id.search_src_text);
        this.mQueryTextView = suggestibleEditText;
        suggestibleEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                FilterableSearchView.this.postUpdateFocusedState();
            }
        });
        this.mQueryTextView.setOnClickListener(this.mOnClickListener);
        this.mQueryTextView.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                FilterableSearchView.this.mQueryTextView.getText();
                FilterableSearchView.this.updateCloseButton();
                if (FilterableSearchView.this.mOnQueryChangeListener != null && !TextUtils.equals(charSequence, FilterableSearchView.this.mOldQueryText)) {
                    FilterableSearchView.this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
                }
                CharSequence unused = FilterableSearchView.this.mOldQueryText = charSequence.toString();
            }
        });
        this.mQueryTextView.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mQueryTextView.setOnItemClickListener(this.mOnItemClickListener);
        this.mQueryTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (FilterableSearchView.this.mOnQueryTextFocusChangeListener != null) {
                    FilterableSearchView.this.mOnQueryTextFocusChangeListener.onFocusChange(FilterableSearchView.this, z);
                }
            }
        });
        View findViewById = findViewById(this.mQueryTextView.getDropDownAnchor());
        this.mDropDownAnchor = findViewById;
        if (findViewById != null) {
            findViewById.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    FilterableSearchView.this.adjustDropDownSizeAndPosition();
                }
            });
        }
        updateViewsVisibility(this.mIconifiedByDefault);
        updateQueryHint();
        this.mInputManager = (InputMethodManager) getContext().getSystemService("input_method");
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    }

    public CharSequence getQuery() {
        return this.mQueryTextView.getText();
    }

    /* access modifiers changed from: private */
    public void onSubmitQuery() {
        Editable text = this.mQueryTextView.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            SearchView.OnQueryTextListener onQueryTextListener = this.mOnQueryChangeListener;
            if (onQueryTextListener == null || !onQueryTextListener.onQueryTextSubmit(text.toString())) {
                setImeVisibility(false);
            }
            dismissSuggestions();
        }
    }

    private void dismissSuggestions() {
        this.mQueryTextView.dismissDropDown();
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.mIconifiedByDefault != z) {
            this.mIconifiedByDefault = z;
            updateViewsVisibility(z);
            updateQueryHint();
        }
    }

    private CharSequence getDecoratedHint(CharSequence charSequence) {
        if (!this.mIconifiedByDefault) {
            return charSequence;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.append(charSequence);
        Drawable drawable = getContext().getResources().getDrawable(getSearchIconId());
        int textSize = (int) (((double) this.mQueryTextView.getTextSize()) * 1.25d);
        drawable.setBounds(0, 0, textSize, textSize);
        spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
        return spannableStringBuilder;
    }

    private void updateQueryHint() {
        CharSequence charSequence = this.mQueryHint;
        if (charSequence != null) {
            this.mQueryTextView.setHint(getDecoratedHint(charSequence));
        } else {
            this.mQueryTextView.setHint(getDecoratedHint(""));
        }
    }

    public void onActionViewCollapsed() {
        setImeVisibility(false);
        clearFocus();
        updateViewsVisibility(true);
        postUpdateFocusedState();
        this.mQueryTextView.setText("");
        this.mQueryTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    /* access modifiers changed from: private */
    public void adjustDropDownSizeAndPosition() {
        if (this.mDropDownAnchor.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.mSearchPlate.getPaddingLeft();
            Rect rect = new Rect();
            int dimensionPixelSize = this.mIconifiedByDefault ? resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left) : 0;
            this.mQueryTextView.getDropDownBackground().getPadding(rect);
            this.mQueryTextView.setDropDownWidth((((this.mDropDownAnchor.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    public void onActionViewExpanded() {
        this.mExpandedInActionView = true;
        int imeOptions = this.mQueryTextView.getImeOptions();
        this.mCollapsedImeOptions = imeOptions;
        this.mQueryTextView.setImeOptions(imeOptions | 33554432);
        setIconified(false);
        setImeVisibility(true);
        postUpdateFocusedState();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((View) getParent()).getLayoutParams().width = -1;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.mUpdateDrawableStateRunnable);
        super.onDetachedFromWindow();
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.mOnSearchClickListener = onClickListener;
    }

    /* access modifiers changed from: private */
    public void onCloseClicked() {
        if (!TextUtils.isEmpty(this.mQueryTextView.getText())) {
            this.mQueryTextView.setText("");
            this.mQueryTextView.requestFocus();
            setImeVisibility(true);
        } else if (this.mIconifiedByDefault) {
            SearchView.OnCloseListener onCloseListener = this.mOnCloseListener;
            if (onCloseListener == null || !onCloseListener.onClose()) {
                clearFocus();
                updateViewsVisibility(true);
            }
        }
    }

    public void setIconified(boolean z) {
        if (z) {
            onCloseClicked();
        } else {
            onSearchClicked();
        }
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    private void updateViewsVisibility(boolean z) {
        this.mIconified = z;
        this.mSearchEditFrame.setVisibility(z ? 8 : 0);
        updateCloseButton();
    }

    /* access modifiers changed from: private */
    public void updateCloseButton() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.mQueryTextView.getText());
        int i = 0;
        if (!z2 && (!this.mIconifiedByDefault || this.mExpandedInActionView)) {
            z = false;
        }
        ImageView imageView = this.mCloseButton;
        if (!z) {
            i = 8;
        }
        imageView.setVisibility(i);
        this.mCloseButton.getDrawable().setState(z2 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
    }

    /* access modifiers changed from: private */
    public void postUpdateFocusedState() {
        post(this.mUpdateDrawableStateRunnable);
    }

    /* access modifiers changed from: private */
    public void updateFocusedState() {
        this.mSearchEditFrame.getBackground().setState(hasFocus() ? FOCUSED_STATE_SET : EMPTY_STATE_SET);
        invalidate();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        postUpdateFocusedState();
    }

    private void onSearchClicked() {
        updateViewsVisibility(false);
        this.mQueryTextView.requestFocus();
        setImeVisibility(true);
        View.OnClickListener onClickListener = this.mOnSearchClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void setQueryHint(CharSequence charSequence) {
        this.mQueryHint = charSequence;
        updateQueryHint();
    }

    private void setImeVisibility(boolean z) {
        if (z) {
            post(this.mShowImeRunnable);
            return;
        }
        removeCallbacks(this.mShowImeRunnable);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mQueryTextView.getWindowToken(), 0);
        }
    }

    /* access modifiers changed from: private */
    public boolean onItemClicked(int i, int i2, String str) {
        SearchView.OnSuggestionListener onSuggestionListener = this.mOnSuggestionListener;
        if (onSuggestionListener != null && onSuggestionListener.onSuggestionClick(i)) {
            return false;
        }
        setImeVisibility(false);
        dismissSuggestions();
        return true;
    }
}
