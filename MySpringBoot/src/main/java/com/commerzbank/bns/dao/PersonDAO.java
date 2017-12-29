package com.commerzbank.bns.dao;

import org.springframework.data.repository.CrudRepository;

import com.commerzbank.bns.model.Person;

public interface PersonDAO extends CrudRepository<Person, Long> , PersonCustomDAO{

} 