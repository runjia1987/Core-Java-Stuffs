package org.jackJew.AOP.proxyTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.stereotype.Component;


@Component("propertiesProviderBean")
public class PropertiesLoaderSupportBeanTest extends PropertiesLoaderSupport
								implements FactoryBean<Properties>, InitializingBean {

	private static Logger logger = Logger.getLogger(PropertiesLoaderSupportBeanTest.class);
	private Properties prop;
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// PropertyPlaceholderConfigurer is inherited from BeanFactoryPostProcessor,
		// so should be initialized automatically in ApplicationContext.
		JdbcPropertyBean bean = context.getBean("jdbcPropertyBean", JdbcPropertyBean.class);
		logger.info("\n" + bean.getDriverName() + ",\n"  + bean.getJdbcUrl() + ",\n" + bean.getUsername());
		
		((AbstractApplicationContext)context).close();
	}
	
	@Override
	public Properties getObject() {
		return prop;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		loadFromProperties();   // bean lifecycle initialization invokes.
	}

	@Override
	public Class<?> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}	
	
	private void loadFromProperties() throws IOException {
		InputStream ins = null;
		try {
			ins = Thread.currentThread().getContextClassLoader()
										.getResourceAsStream("jdbc.properties");
			prop = new Properties();
			prop.load(ins);
			
		} finally {			
			ins.close();
		}
		logger.info(prop.get("jdbc.driverClassName"));
	}	
}

class JdbcPropertyBean {
	
	private String driverName;
	private String jdbcUrl;
	private String username;
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}