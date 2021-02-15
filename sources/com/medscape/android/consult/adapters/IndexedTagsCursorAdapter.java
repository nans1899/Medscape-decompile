package com.medscape.android.consult.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.SectionIndexer;
import android.widget.TextView;
import androidx.cursoradapter.widget.CursorAdapter;
import com.medscape.android.R;
import com.medscape.android.drugs.SpecialAlphabetIndexer;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.ViewHelper;
import java.util.Locale;

public class IndexedTagsCursorAdapter extends CursorAdapter implements SectionIndexer {
    private AlphabetIndexer indexer = new SpecialAlphabetIndexer((Cursor) null, 2, " ABCDEFGHIJKLMNOPQRSTUVWXYZ#");
    private final LayoutInflater inflater;

    public IndexedTagsCursorAdapter(Context context) {
        super(context, (Cursor) null, 0);
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(R.layout.tag_list_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ((ViewHolder) view.getTag()).name.setText(cursor.getString(2));
    }

    public Cursor getItem(int i) {
        return (Cursor) super.getItem(i);
    }

    public Object[] getSections() {
        return this.indexer.getSections();
    }

    public int getPositionForSection(int i) {
        return this.indexer.getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        return this.indexer.getSectionForPosition(i);
    }

    @Deprecated
    public long getHeaderId(int i) {
        return (long) getFirstCharacter(getItem(i).getString(2));
    }

    public char getFirstCharacter(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return '#';
        }
        char charAt = str.substring(0, 1).toUpperCase(Locale.US).charAt(0);
        if (Character.isDigit(charAt)) {
            return '#';
        }
        return charAt;
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor swapCursor = super.swapCursor(cursor);
        this.indexer.setCursor(cursor);
        return swapCursor;
    }

    private static final class ViewHolder {
        public final TextView name;

        private ViewHolder(View view) {
            this.name = (TextView) ViewHelper.findById(view, (int) R.id.tag_text);
        }
    }
}
