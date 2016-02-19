package com.addressbook.api.application.dao;

import com.addressbook.api.application.pojo.Student;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class AddressBookDAO extends AbstractDAO<Student> {
	
    public AddressBookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Optional<Student> findById(long id) {
        return Optional.fromNullable(get(id));
    }
    
    public Student create(Student student) {
        return persist(student);
    }
    
    public List<Student> findAll() {
        return list(namedQuery("com.addressbook.api.application.pojo.Student.findAll"));
    }
}
