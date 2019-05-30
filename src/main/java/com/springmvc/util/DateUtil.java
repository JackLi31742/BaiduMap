package com.springmvc.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到毫秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds)));  
    }  
    /** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();
        String t = String.valueOf(time);  
        return t;  
    }  

    public static void main(String[] args) {  
//        String timeStamp = timeStamp();  
//        System.out.println("timeStamp="+timeStamp); //运行输出:timeStamp=1470278082
//        System.out.println(System.currentTimeMillis());//运行输出:1470278082980
        //该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数
        
        String date1 = timeStamp2Date("1539852509917", "yyyy-MM-dd HH:mm:ss");  
        System.out.println("date="+date1);//运行输出:date=2016-08-04 10:34:42
        String date2 = timeStamp2Date("1545806658333", "yyyy-MM-dd HH:mm:ss");  
        System.out.println("date="+date2);//运行输出:date=2016-08-04 10:34:42
        String date3 = timeStamp2Date("1537174196000", "yyyy-MM-dd HH:mm:ss");  
        System.out.println("date="+date3);//运行输出:date=2016-08-04 10:34:42
        String date4 = timeStamp2Date("1537381396009", "yyyy-MM-dd HH:mm:ss");  
        System.out.println("date="+date4);//运行输出:date=2016-08-04 10:34:42
        
//        String timeStamp2 = date2TimeStamp("2018-09-17 19:49:56", "yyyy-MM-dd HH:mm:ss");  
//        System.out.println(timeStamp2);  //运行输出:1470278082
    }  
}