package TheuxZn16.com.github.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TheuxZn16.com.github.model.Person;
import TheuxZn16.com.github.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonServices services;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Person findById(@PathVariable("id") Long id) {
    return services.findById(id);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> findAll() {
    return services.findAll();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public Person create(@RequestBody Person person) {
    return services.create(person);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    services.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public Person update(@RequestBody Person person) {
    return services.update(person);
  }
}
