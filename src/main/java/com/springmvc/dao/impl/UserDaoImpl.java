package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.User;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User findByUsernameAndPwd(String username, String password) {
		// TODO Auto-generated method stub
//		CustomerContextHolder.setDataSource(DataSourceEnum.MYSQL.getDbName());

		String sql = "select u.userName,u.userPassword from userInfo as u where u.userName=?  and u.userPassword=?" ;

		User user = null;
		try {
			RowMapper<User> rm = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
			user = (User) jdbcTemplate.queryForObject(sql, new Object[] { username, password }, rm);
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
