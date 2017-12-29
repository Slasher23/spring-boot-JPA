package com.commerzbank.bns.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commerzbank.bns.dao.PersonDAO;
import com.commerzbank.bns.model.Person;
import com.google.common.collect.Lists;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;
	
	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDAO.save(p);
	}

	@Override
	@Transactional
	public Person updatePerson(Person p) {
		
		if(Objects.nonNull(getPersonById(p.getId()))){
			return this.personDAO.save(p);	
		}
		
		return null;

	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		List<Person> ret = Lists.newArrayList(personDAO.findAll());

		System.out.println(ret.size());

		return ret;
	}

	@Override
	@Transactional
	public Person getPersonById(long id) {
		return this.personDAO.findOne(id);
	}

	@Override
	@Transactional
	public Person removePerson(long id) {
		this.personDAO.delete(id);

		return null;
	}

	@Override
	public Person getPersonByName(String name) {
		return personDAO.getPersonByName(name);
	}

}
