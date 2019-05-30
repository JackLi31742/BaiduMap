package com.springmvc.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.springmvc.entity.Point2;

public class Util {
	
	public static Timestamp getDate(String s) throws ParseException{
		Timestamp tt=Timestamp.valueOf(s);
		System.out.println(tt);
		
		
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String dateString=df.format(new Date());
//		System.out.println(dateString);
//		
//		String dateString2=df.format(df.parse(s));
		Timestamp ts = new Timestamp(df.parse(s).getTime());
//		Long l=Long.parseLong(dateString2);
		System.out.println(ts);
		long time=System.currentTimeMillis();
		;
		System.out.println( df.parse(String.valueOf(time)));
		return tt;
    }
	
	public static boolean isPtInPoly (double ALon , double ALat , Point2[] ps) {  
        int iSum, iCount, iIndex;  
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;  
        if (ps.length < 3) {  
            return false;  
        }  
        iSum = 0;  
        iCount = ps.length;  
        for (iIndex = 0; iIndex<iCount;iIndex++) {  
            if (iIndex == iCount - 1) {  
                dLon1 = ps[iIndex].getLng();  
                dLat1 = ps[iIndex].getLat();  
                dLon2 = ps[0].getLng();  
                dLat2 = ps[0].getLat();  
            } else {  
                dLon1 = ps[iIndex].getLng();  
                dLat1 = ps[iIndex].getLat();  
                dLon2 = ps[iIndex + 1].getLng();  
                dLat2 = ps[iIndex + 1].getLat();  
            }  
            // 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上  
            if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {  
                if (Math.abs(dLat1 - dLat2) > 0) {  
                    //得到 A点向左射线与边的交点的x坐标：  
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat) ) / (dLat1 - dLat2);  
                    // 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：  
                    if (dLon < ALon) {  
                        iSum++;  
                    }  
                }  
            }  
        }  
        if ((iSum % 2) != 0) {  
            return true;  
        }  
        return false;  
    }  

	public static boolean isPtInPoly2 (double lng , double lat , List<Point2> ps) {  
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;  
        if (ps.size() < 3) {  
            return false;  
        }  
        int sum = 0;  
        int size = ps.size();  
        for (int i = 0; i<size;i++) {  
            if (i == size - 1) {  
                dLon1 = ps.get(i).getLng();  
                dLat1 = ps.get(i).getLat();  
                dLon2 = ps.get(0).getLng();  
                dLat2 = ps.get(0).getLat();  
            } else {  
                dLon1 = ps.get(i).getLng();  
                dLat1 = ps.get(i).getLat();  
                dLon2 = ps.get(i+1).getLng();  
                dLat2 = ps.get(i+1).getLat();  
            }  
            // 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上  
            if (((lat >= dLat1) && (lat < dLat2)) || ((lat >= dLat2) && (lat < dLat1))) {  
                if (Math.abs(dLat1 - dLat2) > 0) {  
                    //得到 A点向左射线与边的交点的x坐标：  
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - lat) ) / (dLat1 - dLat2);  
                    // 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：  
                    if (dLon < lng) {  
                        sum++;  
                    }  
                }  
            }  
        }  
        if ((sum % 2) != 0) {  
            return true;  
        }  
        return false;  
    }
	public static void main(String[] args) {
		try {
			String s="2018-08-28 16:12:24";
			getDate(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
