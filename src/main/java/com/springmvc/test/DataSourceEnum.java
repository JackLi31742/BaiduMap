package com.springmvc.test;

public enum DataSourceEnum {
	 
    // 主库
    MYSQL("dataSource_mysql"),
    MYSQL2("dataSource_mysql2"),
    // 从库
    NEO4J("dataSource_neo4j"),;
 
    // 数据源名称
    private String dbName;

	private DataSourceEnum(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return dbName;
	}

	
 
	


	
}

