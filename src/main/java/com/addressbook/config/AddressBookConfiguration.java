package com.addressbook.config;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AddressBookConfiguration extends Configuration {
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
    
    public MySQLConfiguration getMySQLConfiguration() {
        return mysql;
    }    
}
