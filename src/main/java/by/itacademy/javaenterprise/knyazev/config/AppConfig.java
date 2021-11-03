package by.itacademy.javaenterprise.knyazev.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import by.itacademy.javaenterprise.knyazev.db.DbConnection;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan(basePackages = {"by.itacademy.javaenterprise.knyazev.entities", "by.itacademy.javaenterprise.knyazev.queries", "by.itacademy.javaenterprise.knyazev.dao"})
public class AppConfig {
	@Autowired
	Environment env;
	
	@Bean
	HikariConfig hikariConfig() {
//		HikariConfig hikariConfig = new HikariConfig("src/main/resources/database.properties");
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("driverClassName"));
		hikariConfig.setJdbcUrl(env.getProperty("jdbcUrl"));
		hikariConfig.setUsername(env.getProperty("dataSource.user"));
		hikariConfig.setPassword(env.getProperty("dataSource.password"));
		hikariConfig.setTransactionIsolation(env.getProperty("dataSource.transactionIsolation"));
		hikariConfig.setMaximumPoolSize(Integer.valueOf(env.getProperty("dataSource.maxPoolSize")));
		return hikariConfig;
	}
	
	@Bean
	DataSource hikariDataSource() {
		DataSource hikariDataSource = new HikariDataSource(hikariConfig());
		return hikariDataSource;
	}
	
	@Bean
	DbConnection dbConnection() {
		return DbConnection.getDBO();
	}
	
	@Bean
	Logger saverLogger() {
		return LoggerFactory.getLogger("Saver");
	}
	
	@Bean
	Logger selecterLogger() {
		return LoggerFactory.getLogger("Selecter");
	}
	
	@Bean
	Logger categoriesDAOLogger() {
		return LoggerFactory.getLogger("CategoriesDAO");
	}
	
	@Bean
	Logger appLogger() {
		return LoggerFactory.getLogger("App");
	}
}
