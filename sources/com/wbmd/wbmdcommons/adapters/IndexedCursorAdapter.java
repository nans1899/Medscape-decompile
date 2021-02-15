package com.wbmd.wbmdcommons.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.SectionIndexer;
import android.widget.TextView;
import androidx.cursoradapter.widget.CursorAdapter;
import com.wbmd.wbmdcommons.R;
import com.wbmd.wbmdcommons.utils.AlphabetNumberIndexer;
import java.util.Locale;

public class IndexedCursorAdapter extends CursorAdapter implements SectionIndexer {
    public static final int INDEX_NAME = 2;
    private AlphabetIndexer indexer = new AlphabetNumberIndexer((Cursor) null, 2, "ABCDEFGHIJKLMNOPQRSTUVWXYZ#");
    private final LayoutInflater inflater;

    public IndexedCursorAdapter(Context context) {
        super(context, (Cursor) null, 0);
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(R.layout.a_to_z_list_row, viewGroup, false);
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
            this.name = (TextView) view.findViewById(R.id.itemName);
        }
    }
}
