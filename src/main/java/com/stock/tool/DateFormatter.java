package com.stock.tool;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {
    public static Date format(String date) throws ParseException {
        String ROCYear = date.substring(0, date.indexOf("/")).trim();
        String westYear = String.valueOf(Integer.parseInt(ROCYear)+1911);
        String westDate= date.replace("/", "-").replace(ROCYear, westYear);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date(formatter.parse(westDate).getTime());
        return convertedDate;
    }
}
