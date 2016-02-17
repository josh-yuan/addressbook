package com.addressbook.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

public class MySQLConfiguration {
	@NotEmpty
	@JsonProperty
	private String host = "localhost";

	@Min(1)
	@Max(65535)
	@JsonProperty
	private int port = 3307;

	@NotEmpty
	@JsonProperty
	private String database;
	
	@NotEmpty
	@JsonProperty
	private String user;

	@NotEmpty
	@JsonProperty
	private String password;	
	
	public String getDatabase(){
		return database;
	}
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
