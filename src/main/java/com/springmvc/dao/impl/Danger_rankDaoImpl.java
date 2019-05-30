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

import com.springmvc.dao.Danger_rankDao;
import com.springmvc.entity.Danger_level;
import com.springmvc.entity.Danger_rank;
import com.springmvc.entity.Factors;
import com.springmvc.entity.Targets;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;

@Repository
public class Danger_rankDaoImpl implements Danger_rankDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Danger_rank> getDanger_rank() {
		// TODO Auto-generated method stub
		String sql = "SELECT d.danger_type,d.num,t.`name`,t.id_photo,t.prob,t.id "
				+ "from danger_rank as d LEFT JOIN targets as t on d.danger_type=t.danger_type ";
		


		Map<String, Object> paramMap = new HashMap<>();
	    
	    List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(sql,paramMap);
	    
	    List<Danger_rank> list = new ArrayList<Danger_rank>();
	    for(Map<String, Object> map : mapList){
	    	Danger_rank danger_rank=new Danger_rank();
	    	Targets targets=new Targets();
	    	
	        for(String key : map.keySet()){
	            System.out.println("key:"+key+",value:"+map.get(key));
	            danger_rank.setDanger_type((String)map.get("danger_type"));
	            danger_rank.setNum((int)map.get("num"));
	            targets.setName((String)map.get("name"));
	            targets.setId_photo((String)map.get("id_photo"));
	            targets.setProb((double)map.get("prob"));
	            targets.setId((int)map.get("id"));
	            danger_rank.setTargets(targets);
	            
	            
	        }
	        list.add(danger_rank);
	    }
		
		System.out.println(list);
		return list;
	}

}
