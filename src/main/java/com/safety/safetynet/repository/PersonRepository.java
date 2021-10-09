package com.safety.safetynet.repository;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);


    List<Person> findAllByAddressIn(List<String> addresses);

    @Query("select p from Person p where p.address = :address")
    List<Person> findAllByAddress(String address);

    List<Person> findPersonByAddressContaining(String address);
    List<Person> findPersonByAddress(String address);
    List<Person> findByAddress(String address);
}
