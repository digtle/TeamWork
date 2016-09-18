package com.phone1000.groupproject.bean;

import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * Created by ${USER_NAME} on 2016/9/17.
 */
public class TimeForamt {
    //格式化时间的一种方法
    public static  String dateInfo(String dateLine){
        long currentTime = Long.valueOf(dateLine+"000");
        Date date = new Date(currentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return  simpleDateFormat.format(date);
    }
    public  static String createTime(String dateLine){
        long currentTime = Long.valueOf(dateLine+"000");
        Date date = new Date(currentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("今天HH:mm");
        return  simpleDateFormat.format(date);
    }

}
