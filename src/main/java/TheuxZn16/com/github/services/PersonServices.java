package TheuxZn16.com.github.services;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TheuxZn16.com.github.exception.ResourceNotFoundException;
import TheuxZn16.com.github.model.Person;
import TheuxZn16.com.github.repository.PersonRepository;

@Service
public class PersonServices {
  private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

  @Autowired
  PersonRepository repository;

  public List<Person> findAll() {
    logger.info("Finding all People!");

    return repository.findAll();
  }

  public Person findById(Long id) {
    logger.info("Finding one Person!");

    Objects.requireNonNull(id, "ID cannot be null");
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
  }

  public Person create(Person person) {
    logger.info("Creating one Person!");

    Objects.requireNonNull(person, "Person cannot be null");
    return repository.save(person);
  }

  public Person update(Person person) {
    logger.info("Updating one Person!");

    Objects.requireNonNull(person, "Person cannot be null");
    Long id = Objects.requireNonNull(person.getId(), "Person ID cannot be null");
    Person entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return repository.save(entity);
  }

  public void delete(Long id) {
    logger.info("Deleting one Person!");
    Objects.requireNonNull(id, "ID cannot be null");
    Person entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
    Objects.requireNonNull(entity, "Person entity cannot be null");
    repository.delete(entity);

  }

}
