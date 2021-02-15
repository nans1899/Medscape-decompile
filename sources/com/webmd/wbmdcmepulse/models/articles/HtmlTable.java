package com.webmd.wbmdcmepulse.models.articles;

import java.util.ArrayList;
import java.util.List;

public class HtmlTable {
    public HtmlTableRow tableHeader = new HtmlTableRow(HtmlTableRow.ROW_NUMBER_HEADER, HtmlTableRow.ROW_TYPE_TABLE_HEADER);
    public List<HtmlTableRow> tableRows = new ArrayList();
}
