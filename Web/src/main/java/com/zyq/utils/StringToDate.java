package com.zyq.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：张翼麒
 * Date:2019-6-17
 */
public class StringToDate implements Converter<String, Date> {


    @Override
    public Date convert(String s) {
        Date parse = null;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            if (s==null){
                throw new RuntimeException("输入日期~~");
            }
            parse = simpleDateFormat.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parse;
    }
}
