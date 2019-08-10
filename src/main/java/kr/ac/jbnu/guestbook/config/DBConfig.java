package kr.ac.jbnu.guestbook.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer {

	private String driverClassName = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/bc_mysql?useUnicode=true&characterEncoding=utf-8";
	private String username = "root";
	private String password = "356924qwqw!";
	
	@Bean
	public DataSource datasource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(driverClassName);
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		
		return datasource;
	}
	
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return transactionManager();
	}

	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(datasource());
	}
}
