package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.Neo4jDao;
import com.springmvc.entity.User;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;

@Repository
public class Neo4jDaoImpl implements Neo4jDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@DataSourceAnnotation(name = DataSourceType.dataSource_neo4j)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String findByUsernameAndPwd(String username, String password) {
		// TODO Auto-generated method stub
//		String dbString=DataSourceEnum.NEO4J.getDbName();
//		CustomerContextHolder.setDataSource(dbString);
		
		String id="CAM01-20140328154502-20140328155658_tarid5";

		String sql = "MATCH (n:motion20180616:Person)where n.trackletID=? RETURN n.reidFeature LIMIT 25" ;

		String user = null;
		try {
			RowMapper<String> rm = ParameterizedBeanPropertyRowMapper.newInstance(String.class);
			user = (String) jdbcTemplate.queryForObject(sql, new Object[] { id }, rm);
			System.out.println("测试neo4j："+user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;

	}

	@Override
	public List<User> find(User User) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(User User) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User User) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

}
