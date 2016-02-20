package com.addressbook.health;

import org.hibernate.SessionFactory;
import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {
	private final SessionFactory database;

	public DatabaseHealthCheck(SessionFactory database) {
		this.database = database;
	}

	@Override
	protected Result check() throws Exception {
		if (!database.isClosed()) {
			return Result.healthy();
		}
		return Result.unhealthy("Database is not connected!");
	}
}
