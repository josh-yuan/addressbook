package com.addressbook.api.application.dao;

import com.addressbook.api.application.pojo.Student;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class AddressBookDAO extends AbstractDAO<Student> {
	/**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public AddressBookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    /**
     * Method returns all employees stored in the database.
     *
     * @return list of all employees stored in the database
     */
    public List<Student> findAll() {
        return list(namedQuery("com.addressbook.dao.AddressBookDAO.findAll"));
    }
    /**
     * Looks for employees whose first or last name contains the passed
     * parameter as a substring.
     *
     * @param name query parameter
     * @return list of employees whose first or last name contains the passed
     * parameter as a substring.
     */
    public List<Student> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(
                namedQuery("com.addressbook.dao.AddressBookDAO.findByName")
                .setParameter("name", builder.toString())
        );
    }
    /**
     * Method looks for an employee by her id.
     *
     * @param id the id of an employee we are looking for.
     * @return Optional containing the found employee or an empty Optional
     * otherwise.
     */
    public Optional<Student> findById(long id) {
        return Optional.fromNullable(get(id));
    }
}
