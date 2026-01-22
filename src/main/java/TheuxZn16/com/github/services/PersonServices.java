package TheuxZn16.com.github.services;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import TheuxZn16.com.github.controllers.PersonController;
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

    var people = parseListObjects(repository.findAll(), PersonDTO.class);
    people.forEach(this::addHateoasLinks);

    return people;
  }

  public PersonDTO findById(Long id) {
    logger.info("Finding one Person!");

    var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
    var dto = parseObject(entity, PersonDTO.class);
    addHateoasLinks(dto);
    return dto;
  }

  public PersonDTO create(PersonDTO person) {
    logger.info("Creating one Person!");

    var entity = parseObject(person, Person.class);
    var dto = parseObject(repository.save(entity), PersonDTO.class);
    addHateoasLinks(dto);

    return dto;
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

    var dto = parseObject(repository.save(entity), PersonDTO.class);
    addHateoasLinks(dto);

    return dto;

  }

  public void delete(Long id) {
    logger.info("Deleting one Person!");
    Objects.requireNonNull(id, "ID cannot be null");
    Person entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
    Objects.requireNonNull(entity, "Person entity cannot be null");
    repository.delete(entity);

  }

  private void addHateoasLinks(PersonDTO dto) {
    dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));

    dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));

    dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));

    dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));

    dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
  }
}
