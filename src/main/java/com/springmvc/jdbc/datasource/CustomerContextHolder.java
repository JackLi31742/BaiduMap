package com.springmvc.jdbc.datasource;

public class CustomerContextHolder {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
	/**
	 * 设置数据源
	 * 
	 * @param dataSource
	 */
	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public static String getDataSource() {
		String dataSource = contextHolder.get();
		// 如果没有指定数据源，使用默认数据源
//		if (null == dataSource) {
//			setDataSource(DataSourceEnum.MYSQL.getDbName());
//			dataSource = contextHolder.get();
//		}
		return dataSource;
	}

	/**
	 * 清除数据源
	 */
	public static void clearDataSource() {
		contextHolder.remove();
	}

}
