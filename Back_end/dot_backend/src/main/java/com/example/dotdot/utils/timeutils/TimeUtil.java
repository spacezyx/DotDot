package com.example.dotdot.utils.timeutils;

//import com.example.dotdot.controller.QASystemController;
import lombok.experimental.Helper;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
//    private static Logger log= LoggerFactory.getLogger(QASystemController.class);
    //获取时间  返回毫秒级时间
    public static String getTime() {
        System.out.println("getTime...util...");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        Date date1 = calendar.getTime();
        Long date = date1.getTime();

        return date.toString();
    }

    public static boolean cmpTime(String time) {
        System.out.println("cmpTime...util...");
        long tempTime = Long.parseLong(time);
        System.out.println("tempTime"+tempTime);

        //在获取现在的时间
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        long date = date1.getTime();
//        log.info("date to long : "+ date);
        //Long date = calendar.getTime().getTime();            //获取毫秒时间
        System.out.println("date"+date);

        if(date - tempTime > 30000 ) {   //10分钟
            return false;
        } else {
            return true;
        }

    }

    public static boolean cmpTimeForUrl(String time) {
        System.out.println("cmpTime...util...");
        long tempTime = Long.parseLong(time);
        System.out.println("tempTime"+tempTime);

        //在获取现在的时间
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        long date = date1.getTime();
//        log.info("date to long : "+ date);
        //Long date = calendar.getTime().getTime();            //获取毫秒时间
        System.out.println("date"+date);

        if(date - tempTime > 120000 ) {   //2分钟
            return false;
        } else {
            return true;
        }

    }
}
