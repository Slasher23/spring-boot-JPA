package com.commerzbank.bns.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.commerzbank.bns.model.Person;

@Repository
public class PersonCustomDAOImpl implements PersonCustomDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Person getPersonByName(String name) {
		return this.entityManager.
				createQuery("from Person where name like '"+name+"'",Person.class).
					getSingleResult();
	}

}
