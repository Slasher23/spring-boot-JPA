package com.commerzbank.bns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.commerzbank.bns.model.Person;
import com.commerzbank.bns.service.PersonService;

@RestController
public class PersonAPIController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/hello")
	public String hello(){
		return "Hello!";
	}
	@RequestMapping("/person")
	public Person greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Person("Hello", name);
	}

	@GetMapping(value = "/persons", produces = { "application/json","application/xml" })
	public @ResponseBody List<Person> listPersons() {
		return this.personService.listPersons();
	}

	@GetMapping("/person/edit/{id}")
	public Person editPerson(@PathVariable("id") long id) {
		return this.personService.getPersonById(id);
	}

	@GetMapping("/person/{name}")
	public Person getPerson(@PathVariable("name") String name) {
		return this.personService.getPersonByName(name);
	}

	@PostMapping(value="/person/save", consumes= {"application/json","application/xml"})
	public ResponseEntity<Person> saveNewPerson(@RequestBody Person person) {
		this.personService.addPerson(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	@DeleteMapping("/person/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		if (null == personService.removePerson(id)) {
			return new ResponseEntity<String>("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PutMapping("/person/update")
	public ResponseEntity<String> updateCustomer( @RequestBody Person person) {
		Person retPerson = personService.updatePerson(person);
		if (null == retPerson) {
			return new ResponseEntity<String>("No Customer found for ID " + person.getId(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(person.toString(), HttpStatus.OK);
	}
}