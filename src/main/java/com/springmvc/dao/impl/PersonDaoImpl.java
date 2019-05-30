package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.PersonDao;
import com.springmvc.entity.Person;
import com.springmvc.entity.User;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;

@Repository
public class PersonDaoImpl implements PersonDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Person> find(int camId) {
		String sql = "select p.pic_path,p.longitude,p.latitude,p.detail from casia_personInfo as p where p.cam_id=?" ;

		List<Person> list=null;
		try {
			RowMapper<Person> rm = ParameterizedBeanPropertyRowMapper.newInstance(Person.class);
			list=jdbcTemplate.query(sql,  new Object[] { camId },rm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
