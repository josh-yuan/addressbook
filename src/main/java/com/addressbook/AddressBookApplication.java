package com.addressbook;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.addressbook.admin.ServiceShutdownTask;
import com.addressbook.api.application.dao.AddressBookDAO;
import com.addressbook.api.application.pojo.Student;
import com.addressbook.config.AddressBookConfiguration;
import com.addressbook.health.TemplateHealthCheck;
import com.addressbook.resources.AddressBookResource;

public class AddressBookApplication extends Application<AddressBookConfiguration> {

	/**
	 * Hibernate bundle.
	 */
	private final HibernateBundle<AddressBookConfiguration> hibernateBundle = 
			new HibernateBundle<AddressBookConfiguration>(Student.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(AddressBookConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	public static void main(String[] args) throws Exception {
		new AddressBookApplication().run(args);
	}

	@Override
	public String getName() {
		return "AddressBook";
	}

	@Override
	public void initialize(final Bootstrap<AddressBookConfiguration> bootstrap) {
		bootstrap.addBundle(hibernateBundle);
	}

	@Override
	public void run(AddressBookConfiguration configuration, Environment environment) {
		// nothing to do yet
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		final AddressBookDAO dao = new AddressBookDAO(hibernateBundle.getSessionFactory());
		final AddressBookResource resource = new AddressBookResource(dao);

		environment.healthChecks().register("template", healthCheck);
		environment.admin().addTask(new ServiceShutdownTask());
		environment.jersey().register(resource);
	}
}
