package com.addressbook;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.hibernate.SessionFactory;

import com.addressbook.admin.ServiceShutdownTask;
import com.addressbook.config.AddressBookConfiguration;
import com.addressbook.dao.AddressBookDAO;
import com.addressbook.health.DatabaseHealthCheck;
import com.addressbook.health.TemplateHealthCheck;
import com.addressbook.pojo.Student;
import com.addressbook.resources.AddressBookResource;

public class AddressBookApplication extends Application<AddressBookConfiguration> {

	/**
	 * Hibernate bundle.
	 */
	private final HibernateBundle<AddressBookConfiguration> hibernateBundle = new HibernateBundle<AddressBookConfiguration>(
			Student.class) {
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
		final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());
		final SessionFactory database = hibernateBundle.getSessionFactory();
		final DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(hibernateBundle.getSessionFactory());
		final AddressBookDAO dao = new AddressBookDAO(database);
		final AddressBookResource resource = new AddressBookResource(dao);

		environment.healthChecks().register("template", templateHealthCheck);
		environment.healthChecks().register("database", databaseHealthCheck);
		environment.jersey().register(resource);
	}
}
