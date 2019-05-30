package com.springmvc.jdbc.datasource;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 切换数据源(不同方法调用不同数据源)
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect implements MethodBeforeAdvice,AfterReturningAdvice{
    static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);



	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		CustomerContextHolder.clearDataSource();
		
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		// 首先取类上的数据源
		if (method.getDeclaringClass().isAnnotationPresent(DataSourceAnnotation.class)
				&& !method.isAnnotationPresent(DataSourceAnnotation.class)) {

			DataSourceAnnotation datasource = method.getDeclaringClass().getAnnotation(DataSourceAnnotation.class);
			CustomerContextHolder.setDataSource(datasource.name());

			// 方法上的数据源 优先级高于类上的
		} else if (method.isAnnotationPresent(DataSourceAnnotation.class)) {

			DataSourceAnnotation datasource = method.getAnnotation(DataSourceAnnotation.class);
			CustomerContextHolder.setDataSource(datasource.name());
		} else {
			CustomerContextHolder.setDataSource(DataSourceType.dataSource_mysql);
		}
	}
}

