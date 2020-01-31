/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary_springboot.test_task.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DataBaseConnection {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseConnection.class);

	@Value("${spring.datasource.driver}")
	private String dbDriver;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUser;

	@Value("${spring.datasource.password}")
	private String dbPass;

	private Connection conn;

	public Connection createConnection() {
		try {
			Class.forName(dbDriver);
			LOGGER.info("Connecting to the database...");
			conn = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return conn;
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
				LOGGER.info("Database connection is closed");
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
