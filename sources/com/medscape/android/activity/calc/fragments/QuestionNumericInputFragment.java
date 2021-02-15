package com.medscape.android.activity.calc.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.medscape.android.R;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/medscape/android/activity/calc/fragments/QuestionNumericInputFragment;", "Lcom/wbmd/qxcalculator/fragments/calculator/QuestionNumericInputFragment;", "()V", "initViews", "", "view", "Landroid/view/View;", "newInstance", "question", "Lcom/wbmd/qxcalculator/model/contentItems/calculator/Question;", "contentItem", "Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuestionNumericInputFragment.kt */
public final class QuestionNumericInputFragment extends com.wbmd.qxcalculator.fragments.calculator.QuestionNumericInputFragment {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final QuestionNumericInputFragment newInstance(Question question, ContentItem contentItem) {
        Intrinsics.checkNotNullParameter(question, "question");
        Intrinsics.checkNotNullParameter(contentItem, "contentItem");
        QuestionNumericInputFragment questionNumericInputFragment = new QuestionNumericInputFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("QuestionFragment.KEY_ARG_QUESTION", question);
        bundle.putParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM", contentItem);
        questionNumericInputFragment.setArguments(bundle);
        return questionNumericInputFragment;
    }

    public void initViews(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        View findViewById = view.findViewById(R.id.result_title_text_view);
        if (findViewById != null) {
            ((TextView) findViewById).setText(Html.fromHtml(this.question.title));
            View findViewById2 = view.findViewById(R.id.numeric_entry_edit_text);
            if (findViewById2 != null) {
                this.editText = (EditText) findViewById2;
                EditText editText = this.editText;
                Intrinsics.checkNotNullExpressionValue(editText, "editText");
                editText.setInputType(0);
                this.editText.setTextIsSelectable(true);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.EditText");
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
    }
}
