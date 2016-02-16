package com.addressbook.config;

import com.yammer.dropwizard.config.Configuration;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AddressBookConfiguration extends Configuration {
	@NotNull
	@JsonProperty
	private boolean useMiddleware;
	
	@NotNull
	@JsonProperty
	private boolean authenticationEnabled;
	
    @NotEmpty
    @JsonProperty
    private String template;
    
    @Valid
    @NotNull
    @JsonProperty
    private MySQLConfiguration mysql = new MySQLConfiguration();
    
    public String getTemplate() {
        return template;
    }
    
    public MySQLConfiguration getRabbitMQConfiguration() {
        return mysql;
    }
    
	public boolean getUseMiddleware() {
		return useMiddleware;
	}

	public void setUseMiddleware(boolean useMiddleware) {
		this.useMiddleware = useMiddleware;
	}
	
	public boolean getAuthenticationEnabled() {
		return authenticationEnabled;
	}
	
	public void setAuthenticationEnabled(boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}
}
