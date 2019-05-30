package com.springmvc.jdbc.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private final static Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

	

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSource = CustomerContextHolder.getDataSource();
		log.info("当前操作使用的数据源：{}", dataSource);
		return dataSource;
	}

	
}
