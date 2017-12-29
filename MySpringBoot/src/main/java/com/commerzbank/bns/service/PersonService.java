package com.commerzbank.bns.service;

import java.util.List;

import com.commerzbank.bns.model.Person;

public interface PersonService {

	public void addPerson(Person p);
	public Person updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(long id);
	public Person removePerson(long id);
	public Person getPersonByName(String name);
	
}
