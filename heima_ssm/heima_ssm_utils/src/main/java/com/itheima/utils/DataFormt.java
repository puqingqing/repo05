package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormt {
    public static String dataToStr(Date date,String patten){
        SimpleDateFormat sf=new SimpleDateFormat(patten);
        String dateStr = sf.format(date);
        return  dateStr;
    }

    public static Date strToDate(String str,String patten) throws Exception {
        SimpleDateFormat sf=new SimpleDateFormat(patten);
        Date date01 = sf.parse(str);
        return date01;
    }
}
