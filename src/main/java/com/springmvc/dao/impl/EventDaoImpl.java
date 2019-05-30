package com.springmvc.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.EventDao;
import com.springmvc.entity.Temp;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;

@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Temp> findByType(String event_type, long start, long end) {
		// TODO Auto-generated method stub
		System.out.println("入参:" + event_type + "," + start + "," + end);
		

		String sql = "select c.longitude,c.latitude,e.pic_path,e.describe,e.start_time,e.end_time from camera_info as c "
				+ "left join abnormal_event_info as e on c.camera_id=e.camera_id where e.event_type=" + event_type
				+ " and e.start_time>=" + start + " and e.end_time<=" + end +" ORDER BY e.start_time ASC";

		RowMapper<Temp> rm = new BeanPropertyRowMapper<>(Temp.class);
		List<Temp> list = jdbcTemplate.query(sql, rm);

		
		System.out.println(list);

		
		return list;
	}

	
	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Temp> findByTypeAndCamerId(String event_type, long start, long end, List<String> canmerIds) {
		// TODO Auto-generated method stub
		String sql = "select c.longitude,c.latitude,e.pic_path,e.describe,e.start_time,e.end_time from camera_info as c "
				+ "left join abnormal_event_info as e on c.camera_id=e.camera_id where e.event_type=" + event_type
				+ " and e.start_time>=" + start + " and e.end_time<=" + end +" and c.camera_id in (:canmerIds)"+" ORDER BY e.start_time ASC";
		
		System.out.println("canmerIds:"+canmerIds);
		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("canmerIds", canmerIds);
	    
	    List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(sql,paramMap);
	    List<Temp> list = new ArrayList<Temp>();
	    for(Map<String, Object> map : mapList){
	    	Temp t=new Temp();
	        for(String key : map.keySet()){
	            System.out.println("key:"+key+",value:"+map.get(key));
	            t.setLongitude(BigDecimal.valueOf(Double.valueOf((String.valueOf(map.get("longitude"))))));
	            t.setLatitude(BigDecimal.valueOf(Double.valueOf((String.valueOf(map.get("latitude"))))));
	            t.setPic_path((String)map.get("pic_path"));
	            t.setDescribe((String)map.get("describe"));
	            t.setStart_time((long)map.get("start_time"));
	            t.setEnd_time((long)map.get("end_time"));
	        }
	        list.add(t);
	    }



	    System.out.println("return list:"+list.size()+","+list.toString());
		return list;
	}

}
