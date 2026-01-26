package TheuxZn16.com.github.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import TheuxZn16.com.github.model.Person;
import TheuxZn16.com.github.repository.PersonRepository;
import TheuxZn16.com.github.unitetests.mapper.mocks.MockPerson;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTests {

  MockPerson input;

  @InjectMocks
  private PersonServices service;

  @Mock
  PersonRepository repository;

  @BeforeEach
  void setUp() {
    input = new MockPerson();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findAll() {

  }

  @Test
  void findById() {
    Person person = input.mockEntity(1);
    person.setId(1L);
    when(repository.findById(1L)).thenReturn(Optional.of(person));
    var result = service.findById(1L);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertNotNull(result.getLinks());
    assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/person/1")
            && link.getType().equals("GET")));
  }

  @Test
  void create() {

  }

  @Test
  void update() {

  }

  @Test
  void delete() {

  }
}
