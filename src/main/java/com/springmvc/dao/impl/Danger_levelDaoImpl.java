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

import com.springmvc.dao.Danger_levelDao;
import com.springmvc.entity.Danger_level;
import com.springmvc.entity.Factors;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;

@Repository
public class Danger_levelDaoImpl implements Danger_levelDao {

	
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Danger_level> getDangerLevel(String danger_type) {
		// TODO Auto-generated method stub
		String sql = "SELECT d.danger_type,d.score,f.factorA,f.factorB,f.factorC "
				+ "from danger_level as d LEFT JOIN factors as f on d.id=f.id WHERE d.danger_type="+danger_type;
		


		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("danger_type", danger_type);
	    
	    List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(sql,paramMap);
	    
	    List<Danger_level> list = new ArrayList<Danger_level>();
	    for(Map<String, Object> map : mapList){
	    	Danger_level danger_level=new Danger_level();
	    	Factors factors=new Factors();
	        for(String key : map.keySet()){
	            System.out.println("key:"+key+",value:"+map.get(key));
	            danger_level.setDanger_type((String)map.get("danger_type"));
	            danger_level.setScore((int)map.get("score"));
	            factors.setFactorA((int)map.get("factorA"));
	            factors.setFactorB((int)map.get("factorB"));
	            factors.setFactorC((int)map.get("factorC"));
	            danger_level.setFactors(factors);
	        }
	        list.add(danger_level);
	    }
		
		System.out.println(list);
		return list;
	}

}
