package com.safety.safetynet.repository;

import com.safety.safetynet.model.Allergie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergieRepository  extends JpaRepository<Allergie, Long> {
}
