package TheuxZn16.com.github.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import TheuxZn16.com.github.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
