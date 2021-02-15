package com.medscape.android.drugs;

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
import com.medscape.android.activity.calc.CalculatorIndexedListActivity;
import com.medscape.android.util.ViewHelper;
import java.util.Locale;

public class IndexedListCursorAdapter extends CursorAdapter implements SectionIndexer {
    public int columnName;
    private AlphabetIndexer indexer;
    private final LayoutInflater inflater;

    public IndexedListCursorAdapter(Context context) {
        super(context, (Cursor) null, 0);
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (context instanceof AbstractIndexedDrugListActivity) {
            this.columnName = 2;
        } else if (context instanceof CalculatorIndexedListActivity) {
            this.columnName = 0;
        }
        this.indexer = new SpecialAlphabetIndexer((Cursor) null, this.columnName, " ABCDEFGHIJKLMNOPQRSTUVWXYZ#");
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(R.layout.indexed_list_row, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ((ViewHolder) view.getTag()).name.setText(cursor.getString(this.columnName));
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
    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        HeaderViewHolder headerViewHolder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.indexed_list_header, viewGroup, false);
            headerViewHolder = new HeaderViewHolder(view);
            view.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) view.getTag();
        }
        headerViewHolder.text.setText(Character.toString(getFirstCharacter(getItem(i).getString(this.columnName))));
        return view;
    }

    @Deprecated
    public long getHeaderId(int i) {
        return (long) getFirstCharacter(getItem(i).getString(this.columnName));
    }

    public char getFirstCharacter(String str) {
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
            this.name = (TextView) ViewHelper.findById(view, (int) R.id.itemName);
        }
    }

    private static final class HeaderViewHolder {
        public final TextView text;

        private HeaderViewHolder(View view) {
            this.text = (TextView) ViewHelper.findById(view, (int) R.id.itemSectionHeader);
        }
    }
}
