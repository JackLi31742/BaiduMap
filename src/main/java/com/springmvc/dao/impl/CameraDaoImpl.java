package com.springmvc.dao.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.CameraDao;
import com.springmvc.entity.Camera;
import com.springmvc.jdbc.datasource.DataSourceAnnotation;
import com.springmvc.jdbc.datasource.DataSourceType;


@Repository
public class CameraDaoImpl implements CameraDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Camera> findAll() {
		// TODO Auto-generated method stub
		

		String sql = "select c.longitude,c.latitude,c.camera_id from camera_info as c ";
				

		RowMapper<Camera> rm = new BeanPropertyRowMapper<>(Camera.class);
		List<Camera> list = jdbcTemplate.query(sql, rm);

		
		System.out.println(list);

		
		return list;
	}

	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Camera> findById(String camera_id) {
		// TODO Auto-generated method stub
		String sql = "select c.longitude,c.latitude,c.camera_id from camera_info as c where c.camera_id='"+camera_id+"'";
		

		RowMapper<Camera> rm = new BeanPropertyRowMapper<>(Camera.class);
		List<Camera> list = jdbcTemplate.query(sql, rm);

		
		System.out.println(list);

		
		return list;
	}
	
	@DataSourceAnnotation(name = DataSourceType.dataSource_mysql2)
	@Transactional( propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void insert() {

		List<Camera> list=new ArrayList<Camera>();
		for(int i=0;i<20;i++){
			Camera camera=new Camera();
			camera.setCamera_id("10000"+(45+i)+"$1$0$0");
			camera.setLatitude(new BigDecimal(39.26+2*Math.random()));
			camera.setLongitude(new BigDecimal(115.25+2*Math.random()));
			list.add(camera);
		}
		String sql="insert into camera_info(camera_info.camera_id,camera_info.latitude,camera_info.longitude) values(?,?,?)";
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				Camera camera=list.get(i);
				ps.setString(1, camera.getCamera_id());
				ps.setBigDecimal(2, camera.getLatitude());
				ps.setBigDecimal(3, camera.getLongitude());
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list.size();
			}});
	}

}
