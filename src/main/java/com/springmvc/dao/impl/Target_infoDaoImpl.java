package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.Target_infoDao;
import com.springmvc.entity.Attributes;
import com.springmvc.entity.Targets;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;


@Repository
public class Target_infoDaoImpl implements Target_infoDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Targets> getTarget_info(int id) {
		String sql = "SELECT t.id,t.name,t.age,t.id_photo,t.prob,t.danger_type,a.mental,a.criminal,a.family,a.education "
				+ "from targets as t LEFT JOIN attributes as a on t.id=a.id WHERE t.id="+id;
		


		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("id", id);
	    
	    List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(sql,paramMap);
	    
	    List<Targets> list = new ArrayList<Targets>();
	    for(Map<String, Object> map : mapList){
	    	Targets target_info=new Targets();
	    	Attributes attributes=new Attributes();
	    	
	        for(String key : map.keySet()){
	            System.out.println("key:"+key+",value:"+map.get(key));
	            target_info.setName((String)map.get("name"));
	            target_info.setDanger_type((String)map.get("danger_type"));
	            target_info.setId((int)map.get("id"));
	            target_info.setProb((double)map.get("prob"));
	            target_info.setAge((int)map.get("age"));
	            target_info.setId_photo((String)map.get("id_photo"));
	            attributes.setCriminal((String)map.get("criminal"));
	            attributes.setEducation((String)map.get("education"));
	            attributes.setFamily((String)map.get("family"));
	            attributes.setMental((String)map.get("mental"));
	            target_info.setAttributes(attributes);
	        }
	        list.add(target_info);
	    }
		
		System.out.println(list);
		return list;
	}

}
