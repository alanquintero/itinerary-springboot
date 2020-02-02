/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.test.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class CommonFunctionalityForTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonFunctionalityForTest.class);

	public long flightId = System.currentTimeMillis() / 1000L;

	/**
	 * Insert one flight using epoch id
	 */
	public void createFlightData() {
		try (PreparedStatement preparedStatement = createConnection().prepareStatement(
				"INSERT INTO flights (id, origin_fk, destination_fk, price, currency_fk, book) values (?, 1, 2, 1000, 1, false)");) {
			preparedStatement.setLong(1, flightId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Delete created flight using epoch id generated
	 */
	public void clearFlightData() {
		try (PreparedStatement preparedStatement = createConnection()
				.prepareStatement("DELETE FROM flights WHERE id = ?");) {
			preparedStatement.setLong(1, flightId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/***
	 * %%%%%%%%%%%%%% DATABASE CONNECTION STARTS %%%%%%%%%%%%%%
	 */

	@Value("${spring.datasource.driver}")
	private String dbDriver;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUser;

	@Value("${spring.datasource.password}")
	private String dbPass;

	private Connection conn = null;

	/**
	 * Creates a new db connection.
	 * 
	 * @return Connection
	 */
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

	/**
	 * Closes and set to null existent connection.
	 */
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

	/***
	 * %%%%%%%%%%%%%% DATABASE CONNECTION ENDS %%%%%%%%%%%%%%
	 */

}
