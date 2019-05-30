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

import com.springmvc.dao.PeopleDao;
import com.springmvc.entity.ItemStyle;
import com.springmvc.entity.Link;
import com.springmvc.entity.Normal;
import com.springmvc.entity.People;
import com.springmvc.entity.PeopleAttributes;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;

@Repository
public class PeopleDaoImpl implements PeopleDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * LANG
	 * @param id
	 * @return
	 */
	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<People> getPeople(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT p.`name`,p.category,p.prob,p.symbolSize,a.age,a.p_detail,a.sex,a.job,a.emotion,a.color,p.id from people as p "
				+ "LEFT JOIN peopleattributes as a on p.id=a.id WHERE p.id="+id+" ";

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
	    
	    List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(sql,paramMap);
	    
	    List<People> list = new ArrayList<People>();
	    for(Map<String, Object> map : mapList){
	    	People people=new People();
	    	PeopleAttributes attributes=new PeopleAttributes();
	    	ItemStyle itemStyle=new ItemStyle();
	    	Normal normal=new Normal();
	        for(String key : map.keySet()){
	            System.out.println("key:"+key+",value:"+map.get(key));
	            people.setCategory((String)map.get("category"));
	            people.setName((String)map.get("name"));
	            people.setProb((double)map.get("prob"));
	            people.setSymbolSize((int)map.get("symbolSize"));
	            normal.setColor((String)map.get("color"));
	            attributes.setAge((int)map.get("age"));
	            attributes.setDetail((String)map.get("p_detail"));
	            attributes.setEmotion((String)map.get("emotion"));
	            attributes.setJob((String)map.get("job"));
	            attributes.setSex((int)map.get("sex"));
	        }
	        itemStyle.setNormal(normal);
	        people.setAttributes(attributes);
	        people.setItemStyle(itemStyle);
	        list.add(people);
	    }
		
		System.out.println(list);
		return list;
	}

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<People> getLink(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from "
				+ "(SELECT p.`name`,p.category,p.prob,p.symbolSize,a.age,a.p_detail,a.sex,a.job,a.emotion,a.color,p.id from people as p "
				+ "LEFT JOIN peopleattributes as a on p.id=a.id WHERE p.id="+id+") "
				+ "as peo LEFT JOIN link as l on peo.id=l.source_id or peo.id=l.target_id";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
	    
	    List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(sql,paramMap);
	    
	    List<People> list = new ArrayList<People>();
	    for(Map<String, Object> map : mapList){
	    	People people=new People();
	    	PeopleAttributes attributes=new PeopleAttributes();
	    	ItemStyle itemStyle=new ItemStyle();
	    	Normal normal=new Normal();
	    	List<Link> links=new ArrayList<>();
	    	Link link=new Link();
	        for(String key : map.keySet()){
	            System.out.println("key:"+key+",value:"+map.get(key));
	            people.setCategory((String)map.get("category"));
	            people.setName((String)map.get("name"));
	            people.setProb((double)map.get("prob"));
	            people.setSymbolSize((int)map.get("symbolSize"));
	            normal.setColor((String)map.get("color"));
	            attributes.setAge((int)map.get("age"));
	            attributes.setDetail((String)map.get("p_detail"));
	            attributes.setEmotion((String)map.get("emotion"));
	            attributes.setJob((String)map.get("job"));
	            attributes.setSex((int)map.get("sex"));
	            link.setSourseId((int)map.get("source_id"));
	            link.setTargetId((int)map.get("target_id"));
	            link.setDetail((String)map.get("detail"));
	        }
	        itemStyle.setNormal(normal);
	        people.setAttributes(attributes);
	        people.setItemStyle(itemStyle);
	        links.add(link);
	        people.setLinks(links);
	        list.add(people);
	    }
		
		System.out.println(list);
		return list;
	}

}
