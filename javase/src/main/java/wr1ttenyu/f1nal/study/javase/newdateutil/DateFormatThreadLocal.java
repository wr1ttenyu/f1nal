package wr1ttenyu.f1nal.study.javase.newdateutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {

    private static final ThreadLocal<SimpleDateFormat> formatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    public static final Date convert(String source) throws ParseException {
        return formatThreadLocal.get().parse(source);
    }
}
