package com.cartoon.tinytips.Util.Util;

import java.util.Calendar;

/**
 * Created by cartoon on 2017/12/7.
 */

public class GetCurrentTime {
    //获取当前时间
    private String year;
    private String month;
    private String day;
    private Calendar calendar;
    public GetCurrentTime(){
        calendar=Calendar.getInstance();
        year=Integer.toString(calendar.get(Calendar.YEAR));
        month=Integer.toString(calendar.get(Calendar.MONTH)+1);
        day=Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    }
    public String getCurrentTime(){
        return year+"年"+month+"月"+day+"日";
    }

    public String getYear() {
        return year+"年";
    }

    public String getMonth() {
        return month+"月";
    }

    public String getDay() {
        return day+"日";
    }
}
