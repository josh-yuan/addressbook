package com.addressbook.db;

import com.addressbook.config.AddressBookConfiguration;
import com.addressbook.config.MySQLConfiguration;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codahale.metrics.annotation.Timed;

public class MySQLConnection {
	private static Connection connection = null;
	private static final Logger LOG = LoggerFactory.getLogger(MySQLConnection.class);

	public static void connect(AddressBookConfiguration config) {
		// check JDBC dependency
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOG.error("MySQL JDBC Driver is not working.", e);
		}
		LOG.info("MySQL JDBC Driver Registered!");
		
		// Initialize connection
		MySQLConfiguration mysqlConf = config.getMySQLConfiguration();
		String DB_ENDPOINT = "jdbc:mysql://" + mysqlConf.getHost() + 
				             ":" + mysqlConf.getPort() + 
				             "/" + mysqlConf.getDatabase();
		String DB_LOGIN = mysqlConf.getUser();
		String DB_PASSWORD = mysqlConf.getPassword();
		
		try {
			LOG.debug("MySQL connection endpoint: " + DB_ENDPOINT + 
					  ", login: " + DB_LOGIN 
					  + ", password: " + DB_PASSWORD);
			connection = DriverManager.getConnection(DB_ENDPOINT, DB_LOGIN, DB_PASSWORD);
			if (connection != null) {
				LOG.info("MySQL DB is connected!");
			} else {
				LOG.error("ERROR, failed to make DB connection!");
			}

		} catch (SQLException e) {
			LOG.error("MYSQL connection failed!", e);
		}
		LOG.info("DB connection is successfully established.");
	}
}
