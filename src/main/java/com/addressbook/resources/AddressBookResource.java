package com.addressbook.resources;

import com.addressbook.dao.AddressBookDAO;
import com.addressbook.pojo.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Path("/v1.0")
@Produces(MediaType.APPLICATION_JSON)
public class AddressBookResource {
	private static final Logger LOG = LoggerFactory.getLogger(AddressBookResource.class);
	private final AddressBookDAO dao;

	public AddressBookResource(AddressBookDAO dao) {
		this.dao = dao;
	}

	@GET
	@Path("/students")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@Timed
	public List<Student> findAll() {
		return dao.findAll();
	}

	@GET
	@Path("/student/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@Timed
	public Student findById(@PathParam("id") LongParam id) {
		return findSafely(id.get());
	}

	private Student findSafely(long id) {
		final Optional<Student> student = dao.findById(id);
		if (!student.isPresent()) {
			throw new NotFoundException("No such student found.");
		}
		return student.get();
	}

	@GET
	@Path("/student/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@Timed
	public List<Student> findByName(@PathParam("name") String name) {
		return dao.findByName(name);
	}

	@GET
	@Path("/student/parent/{parent}")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@Timed
	public List<Student> findByParent(@PathParam("parent") String parent) {
		return dao.findByParent(parent);
	}

	@GET
	@Path("/student/grade/{grade}")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@Timed
	public List<Student> findByGrade(@PathParam("grade") int grade) {
		return dao.findByGrade(grade);
	}
}
