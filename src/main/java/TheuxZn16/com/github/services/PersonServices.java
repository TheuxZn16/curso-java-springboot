package TheuxZn16.com.github.services;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TheuxZn16.com.github.data.dto.PersonDTO;
import TheuxZn16.com.github.exception.ResourceNotFoundException;
import static TheuxZn16.com.github.mapper.ObjectMapper.parseListObjects;
import static TheuxZn16.com.github.mapper.ObjectMapper.parseObject;
import TheuxZn16.com.github.model.Person;
import TheuxZn16.com.github.repository.PersonRepository;

@Service
public class PersonServices {
  private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

  @Autowired
  PersonRepository repository;

  public List<PersonDTO> findAll() {
    logger.info("Finding all People!");

    return parseListObjects(repository.findAll(), PersonDTO.class);
  }

  public PersonDTO findById(Long id) {
    logger.info("Finding one Person!");

    var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
    return parseObject(entity, PersonDTO.class);
  }

  public PersonDTO create(PersonDTO person) {
    logger.info("Creating one Person!");

    var entity = parseObject(person, Person.class);

    return parseObject(repository.save(entity), PersonDTO.class);
  }

  public PersonDTO update(PersonDTO person) {
    logger.info("Updating one Person!");

    Long id = Objects.requireNonNull(person.getId(), "Person ID cannot be null");
    Person entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return parseObject(repository.save(entity), PersonDTO.class);

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
