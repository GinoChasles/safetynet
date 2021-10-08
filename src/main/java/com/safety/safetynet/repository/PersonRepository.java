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

//    @Query("select u.firstName, u.lastName, u.phone from Person u where FireStation.stationNumber = :id and u.address = FireStation.address")
//    @Query("select u from Person u join u.fireStation as f where f.stationNumber=?1")
//    List<Person> findPersonByStationNumber(@Param("id") long id);

//    List<Person> findAllByFireStation(FireStation fireStation);

    List<Person> findAllByAddressIn(List<String> addresses);
}
