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

import com.springmvc.dao.PersonDao2;
import com.springmvc.entity.Temp;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;
import com.springmvc.util.DateUtil;

@Repository
public class PersonDao2Impl implements PersonDao2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Temp> findByName(String name, long start, long end) {

		System.out.println("入参:" + name + "," + start + "," + end);
		

		String sql = "select p.pic_path,p.name,p.gender,p.age,p.timestamp,c.longitude,c.latitude from face_info "
				+ "as p left join camera_info as c on p.camera_id=c.camera_id where p.name=" + name
				+ " and p.timestamp>=" + start + " and p.timestamp<=" + end +" ORDER BY p.timestamp ASC";

		RowMapper<Temp> rm = new BeanPropertyRowMapper<>(Temp.class);
		List<Temp> list = jdbcTemplate.query(sql, rm);

		
		System.out.println(list);

		
		return list;
	}
	
	
	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Temp> findByNameAndCamerId(String name, long start, long end, List<String> canmerIds) {
		// TODO Auto-generated method stub
		String sql = "select p.pic_path,p.name,p.timestamp,p.gender,p.age,c.longitude,c.latitude from face_info "
				+ "as p left join camera_info as c on p.camera_id=c.camera_id where p.name=" + name
				+ " and p.timestamp>=" + start + " and p.timestamp<=" + end +" and c.camera_id in (:canmerIds)" +" ORDER BY p.timestamp ASC";
		System.out.println("canmerIds:"+canmerIds);
		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("canmerIds", canmerIds);
	    
	    List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(sql,paramMap);
	    List<Temp> list = new ArrayList<Temp>();
	    for(Map<String, Object> map : mapList){
	    	Temp t=new Temp();
	        for(String key : map.keySet()){
	            System.out.println("key:"+key+",value:"+map.get(key));
	            t.setPic_path((String)map.get("pic_path"));
	            t.setTimestamp((Long.valueOf((long)map.get("timestamp"))));
	            t.setName((String)map.get("name"));
	            t.setLongitude(BigDecimal.valueOf(Double.valueOf((String.valueOf(map.get("longitude"))))));
	            t.setLatitude(BigDecimal.valueOf(Double.valueOf((String.valueOf(map.get("latitude"))))));
	            t.setAge((int)map.get("age"));
	            t.setGender((String)map.get("gender"));
	        }
	        list.add(t);
	    }



	    System.out.println("return list:"+list.size()+","+list.toString());
		return list;
	}

}
