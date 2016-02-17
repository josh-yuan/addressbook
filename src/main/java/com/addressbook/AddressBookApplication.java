package com.addressbook;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.addressbook.admin.ServiceShutdownTask;
import com.addressbook.config.AddressBookConfiguration;
import com.addressbook.db.MySQLConnection;
import com.addressbook.health.TemplateHealthCheck;
import com.addressbook.resources.AddressBookResource;

public class AddressBookApplication extends Application<AddressBookConfiguration> {

	public static void main(String[] args) throws Exception {
		new AddressBookApplication().run(args);
	}

	@Override
	public String getName() {
		return "AddressBook";
	}

	@Override
	public void initialize(Bootstrap<AddressBookConfiguration> bootstrap) {
	}

	@Override
	public void run(AddressBookConfiguration configuration, Environment environment) {
		// nothing to do yet
		final AddressBookResource resource = new AddressBookResource();
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.admin().addTask(new ServiceShutdownTask());
		environment.jersey().register(resource);
		MySQLConnection.connect(configuration);
	}
}
