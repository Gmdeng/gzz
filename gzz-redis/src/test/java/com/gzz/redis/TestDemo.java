package com.gzz.redis;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDemo {

    @Test
    public void test(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,20);
        calendar.set(Calendar.MINUTE, 20);
        //calendar.set(Calendar.SECOND, 0);
        //calendar.set(Calendar.MILLISECOND, 0);

        Date time = calendar.getTime() ;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strime = sdf.format(time);
        System.out.println(strime);
        long bet = time.getTime() - new Date().getTime();
        System.out.println(bet);
    }
}
