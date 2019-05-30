package com.springmvc.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.GsonBuilder;
import com.springmvc.entity.Black;
import com.springmvc.entity.Camera;
import com.springmvc.entity.Temp;

public class Test {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		 List<Temp> oldMessageList=new ArrayList<Temp>();
		 List<Temp> newMessageList=new ArrayList<Temp>();
		newMessageList.add(new Temp(BigDecimal.valueOf(5.9),BigDecimal.valueOf(10)));
		testList(oldMessageList,newMessageList);
		
		
		newMessageList.add(new Temp(BigDecimal.valueOf(6.9),BigDecimal.valueOf(11)));
		testList(oldMessageList,newMessageList);
		
		
		newMessageList.add(new Temp(BigDecimal.valueOf(7.9),BigDecimal.valueOf(12)));
		testList(oldMessageList,newMessageList);
		newMessageList.add(new Temp(BigDecimal.valueOf(8.9),BigDecimal.valueOf(13)));
		testList(oldMessageList,newMessageList);
		testList(oldMessageList,newMessageList);
		
	}
	
	public static void testList(List<Temp> oldMessageList, List<Temp> newMessageList){
		Collection<Temp> result=CollectionUtils.subtract(newMessageList,oldMessageList);
		if (result.size()==0) {
			newMessageList.clear();
			System.out.println("newMessageList:"+newMessageList); ;
		}else {
			oldMessageList.addAll(result);
			
			System.out.println("result:"+result);
		}
	}

	
	public void testRandom(){
		List<Camera> list=new ArrayList<Camera>();
		for(int i=0;i<20;i++){
			Camera camera=new Camera();
			camera.setCamera_id("10000"+(27+i)+"$1$0$0");
			camera.setLatitude(new BigDecimal(39.984633+new Random(1).nextDouble()));
			camera.setLongitude(new BigDecimal(116.339259+new Random(1).nextDouble()));
			list.add(camera);
			System.out.println(camera);
		}
//		System.out.println(list);
		
		for (int i = 0; i < 10; i++) {
//			System.out.println(Math.random());;
		}
	}
	
	public void testJson(){
		String s="{\"age\":18,\"alarm_device\":\"1000027$1$0$0\","
				+ "\"alarm_snapshot\":\"\\\\192.168.1.130\\face_pic\\capture\\1000027$1$0$0\\1765219a-64aa-48ed-a808-35f595ceca7e.jpg\","
				+ "\"alarm_time\":\"20181018T164829\",\"alarm_type\":0,\"face_id\":\"32108819999999993\",\"gender\":\"u5973\",\"name\":\"u738bu83b9\"}";

		String str=s.replace("\\", "/");
		System.out.println(str);
		Black black=new GsonBuilder()
//				.generateNonExecutableJson()
				.setPrettyPrinting()
                .disableHtmlEscaping()
                .create().fromJson(str,Black.class);
		
//		JsonParser jp=new JsonParser();
//		System.out.println(jp.parse(s));;
		
//		ObjectMapper m = new ObjectMapper();  
//		Black test1 = m.readValue(s, Black.class);  
		System.out.println(black);
	}
}
