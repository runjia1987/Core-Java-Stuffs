package org.jackJew.spring.MyMapperScanner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ClassUtils;

public class MyFactoryBean<T> implements FactoryBean<T>, InitializingBean {
	
	private Class<T> mapperInterface;
	
	private JdbcTemplate jdbcTemplate;
	
	private static ConcurrentMap<String, String> sqlCache = new ConcurrentHashMap<String, String>();
	
	static {
		sqlCache.put("org.jackJew.spring.MyMapperScanner.StudentDao.getName",
				"select name from man;");
		sqlCache.put("org.jackJew.spring.MyMapperScanner.TeacherDao.getId",
				"select id from man;");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(mapperInterface == null) {
			throw new IllegalArgumentException("mapperInterface is not set.");
		}
		if(jdbcTemplate == null) {
			throw new IllegalArgumentException("jdbcTemplate is not set.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObject() throws Exception {
		Object proxy = new DaoProxy().createProxy();
		return (T) proxy;
	}

	@Override
	public Class<T> getObjectType() {
		return mapperInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	// JDK proxy
	final class DaoProxy implements InvocationHandler {
		
		public Object createProxy() {
			Object proxy = Proxy.newProxyInstance(
					ClassUtils.getDefaultClassLoader(),
					new Class<?>[]{ mapperInterface },
					this);
			return proxy;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			String key = method.getDeclaringClass().getName()+ "." + method.getName();
			
			String statement = sqlCache.get(key);
			System.out.println("DaoProxy key: " + key + ", statement: " + statement);
			List<?> result = jdbcTemplate.queryForList(statement, Object.class);
			if(result == null || result.isEmpty()) {
				return null;
			} else {
				return result.get(0);
			}
		}
		
	}

	public Class<?> getMapperInterface() {
		return mapperInterface;
	}

	public void setMapperInterface(Class<T> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
