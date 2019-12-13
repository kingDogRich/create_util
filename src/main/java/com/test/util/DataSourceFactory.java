package com.test.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;


@Component
public class DataSourceFactory {

	public static HashMap<Integer,BasicDataSource> dataSourceList = new HashMap<Integer,BasicDataSource>();
	static {
		try {
			//InputStream in = DataSourceFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			//Properties prop = new Properties();
			//prop.load(in);
			
			int id = 0;

			BasicDataSource dataSource = new BasicDataSource();

			//配置数据库信息
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/test?Useunicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
			dataSource.setUsername("root");
			dataSource.setPassword("123456");

			/*dataSource.setDriverClassName(prop.getProperty("driverClassName"));
			dataSource.setUrl(prop.getProperty("jdbc_url"));
			dataSource.setUsername(prop.getProperty("jdbc_username"));
			dataSource.setPassword(prop.getProperty("jdbc_password"));*/

			/*if(prop.containsKey("initialSize")){
				dataSource.setInitialSize(Integer.valueOf(prop.getProperty("initialSize")));
			}
			if(prop.containsKey("maxActive")){
				dataSource.setMaxActive(Integer.valueOf(prop.getProperty("maxActive")));
			}
			if(prop.containsKey("maxIdle")){
				dataSource.setMaxIdle(Integer.valueOf(prop.getProperty("maxIdle")));
			}
			if(prop.containsKey("minIdle")){
				dataSource.setMinIdle(Integer.valueOf(prop.getProperty("minIdle")));
			}
			if(prop.containsKey("maxWait")){
				dataSource.setMaxWait(Integer.valueOf(prop.getProperty("maxWait")));
			}
			if(prop.containsKey("connectionProperties")){
				dataSource.setConnectionProperties(prop.getProperty("connectionProperties"));
			}
			if(prop.containsKey("defaultAutoCommit")){
				dataSource.setDefaultAutoCommit(Boolean.valueOf(prop.getProperty("defaultAutoCommit")));
			}
			if(prop.containsKey("defaultReadOnly")){
				dataSource.setDefaultReadOnly(Boolean.valueOf(prop.getProperty("defaultReadOnly")));
			}
			if(prop.containsKey("defaultTransactionIsolation")){
				String transactionIsolation = prop.getProperty("defaultTransactionIsolation");
				if(transactionIsolation.equalsIgnoreCase("NONE")){
					dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_NONE);
				} else if(transactionIsolation.equalsIgnoreCase("READ_COMMITTED")){
					dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				} else if(transactionIsolation.equalsIgnoreCase("READ_UNCOMMITTED")){
					dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
				} else if(transactionIsolation.equalsIgnoreCase("REPEATABLE_READ")){
					dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
				} else if(transactionIsolation.equalsIgnoreCase("SERIALIZABLE")){
					dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				}
			}
			if(prop.containsKey("removeAbandonedOnBorrow")){
				dataSource.setRemoveAbandoned(Boolean.valueOf(prop.getProperty("removeAbandonedOnBorrow")));
			}
			if(prop.containsKey("removeAbandonedTimeout")){
				dataSource.setRemoveAbandonedTimeout(Integer.valueOf(prop.getProperty("removeAbandonedTimeout")));
			}
			if(prop.containsKey("validationQuery")){
				dataSource.setValidationQuery(prop.getProperty("validationQuery"));
			}
			if(prop.containsKey("validationQueryTimeout")){
				dataSource.setValidationQueryTimeout(Integer.valueOf(prop.getProperty("validationQueryTimeout")));
			}*/
			dataSourceList.put(id++,dataSource);
		
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection(int id) throws SQLException {
		Connection connection = null;
		BasicDataSource dataSource = dataSourceList.get(id);
		connection = dataSource.getConnection();
		if(dataSource.getNumActive() > 10) {
			System.out.println("========id: "+ id +"  NumActive:"+ dataSource.getNumActive() +"====on "+Thread.currentThread().getName()+"=============");
		}
		
		return connection;
	}

}
