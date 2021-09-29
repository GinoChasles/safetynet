package com.safety.safetynet.repository;

import com.safety.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    public Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
