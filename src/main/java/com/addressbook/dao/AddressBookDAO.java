package com.addressbook.dao;

import com.addressbook.pojo.Student;
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

	public List<Student> findByName(String name) {
		StringBuilder builder = new StringBuilder("%");
		builder.append(name).append("%"); // %name%
		return list(namedQuery("com.addressbook.api.application.pojo.Student.findByName").
				setParameter("name", builder.toString()));
	}
	
	public List<Student> findByParent(String parent) {
		StringBuilder builder = new StringBuilder("%");
		builder.append(parent).append("%"); // %Parent%
		return list(namedQuery("com.addressbook.api.application.pojo.Student.findByParent").
				setParameter("parent", builder.toString()));
	}
	
	public List<Student> findByGrade(int grade) {
		return list(namedQuery("com.addressbook.api.application.pojo.Student.findByGrade").
				setParameter("grade", grade));
	}
}
